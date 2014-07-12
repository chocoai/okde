package net.cedu.biz.enrollment.impl;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.StudentAcademyRebateBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyRebatePolicyDetailStandardDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAcademyRebate;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返款政策明细标准  业务逻辑层 实现
 */
@Service
public class AcademyRebatePolicyDetailStandardBizImpl implements
		AcademyRebatePolicyDetailStandardBiz {
	@Autowired
	private AcademyRebatePolicyDetailStandardDao academyRebatePolicyDetailStandardDao;
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;//院校返款政策_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired 
	private StudentAcademyRebateBiz studentAcademyRebateBiz;//学生绑定院校返款标准关系表
	
	@Autowired
	private AcademyBatchRebateCountBiz academyBatchRebateCountBiz;//院校返款每个批次的返款总人数
	
	
	/**
	 * 根据ID查询明细标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetailStandard findById(int id) throws Exception
	{
		return academyRebatePolicyDetailStandardDao.findById(id);
	}
	
	/**
	 * 批量保存
	 * @param list
	 * @throws Exception
	 */
	public void addList(List<AcademyRebatePolicyDetailStandard> list) throws Exception
	{
		if(list==null) return;
		
		int i=0, len=list.size();
		try
		{
			for(; i<len ; i++)
			{
				AcademyRebatePolicyDetailStandard standard = list.get(i);
				academyRebatePolicyDetailStandardDao.save(standard);
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException("Exception occurs at ["+i+"]:\t"+e.getMessage(), e);
		}
	}
	
	/**
	 * 保存
	 * @param entity
	 * @return 刚保存政策明细标准的ID
	 * @throws Exception
	 */
	public int addStandard(AcademyRebatePolicyDetailStandard entity) throws Exception
	{
		Object id = academyRebatePolicyDetailStandardDao.save(entity);
		if(id!=null && id instanceof Integer){
			return (Integer)id;
		}
		
		return 0;
	}
	
	/**
	 * 查询明细所有标准项
	 * @param policyId 政策明细
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetailStandard> findByPolicyId(int policyId) throws Exception
	{
		return academyRebatePolicyDetailStandardDao.getByProperty(" and policyId="+Constants.PLACEHOLDER+" order by floor asc", new Object[]{ policyId });
	}
	
	/**
	 * 删除指定政策明细的所有标准项
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public int deleteForPolicy(int policyId) throws Exception
	{
		int rowCount = 0;
		
		PageParame p = new PageParame();
//		p.setPage(false);
		p.setHqlConditionExpression(" and policyId="+Constants.PLACEHOLDER);
		p.setValues(new Object[]{policyId});
		
		Long[] ids = academyRebatePolicyDetailStandardDao.getIDs(p);
		
		if(ids != null)
		{
			try
			{
				for(rowCount=0; rowCount<ids.length; rowCount++)
				{
					academyRebatePolicyDetailStandardDao.deleteConfig(ids[rowCount].intValue());
				}
			}
			catch(Exception e)
			{
				throw new RuntimeException(String.format("The total to be deleted is %1$d ，and exception occurs at %2$d ：\t %3s", ids.length, rowCount, e.getMessage()), e);
			}
		}
		
		return rowCount;
	}
	
	/*
	 * 根据学生和费用科目Id匹配相关的院校返款标准---院校返款用
	 * 
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findByStudentIdFeeSubjectIdForAcademyRebate(net.cedu.entity.crm.Student, int)
	 */
	public AcademyRebatePolicyDetailStandard findByStudentIdFeeSubjectIdForAcademyRebate(Student student,int feeSubjectId) throws Exception
	{
		AcademyRebatePolicyDetailStandard arpds=null;
		if(student!=null && feeSubjectId>0)
		{
			AcademyRebatePolicyDetail arpd=this.academyRebatePolicyDetailBiz.findForStudnet(student,feeSubjectId,false);
			if(arpd!=null)
			{
				List<AcademyRebatePolicyDetailStandard> arpdslist=this.findByPolicyId(arpd.getPolicyId());
				if(arpdslist!=null && arpdslist.size()>0)
				{
					//查询招生人数
					int count =this.studentBiz.findHaveSignedUpStudentCountByAcademyIdenrollmentBatchId(student.getAcademyId(), student.getEnrollmentBatchId());
					int asize=arpdslist.size();
					int index=1;
					for(AcademyRebatePolicyDetailStandard as:arpdslist)
					{
						if(index==1 && count<as.getFloor())
						{
							arpds=as;
							break;
						}
						else if(as.getFloor()<=count && as.getCeil()>=count)
						{
							arpds=as;
							break;
						}
						else if(index==asize) //保证匹配到一个标准
						{
							arpds=as;
							break;
						}
						index++;
					}
				}
			}
		}
		return arpds;
	}
	
	/*
	 * 根据学生、费用科目Id和金额计算出相关的院校返款金额---院校返款用
	 * 
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findAcademyRebateByStudentFeeSubjectIdMoney(net.cedu.entity.crm.Student, int, java.math.BigDecimal)
	 */
	public double findAcademyRebateByStudentFeeSubjectIdMoney(Student student,int feeSubjectId,BigDecimal money) throws Exception
	{
		double count=0;
		AcademyRebatePolicyDetailStandard arpds=findByStudentIdFeeSubjectIdForAcademyRebate(student,feeSubjectId);
		if(arpds!=null)
		{
			if(arpds.getValueForm()==Constants.MONEY_FORM_RATIO)
			{
				count=(money.multiply(arpds.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			}
			else if(arpds.getValueForm()==Constants.MONEY_FORM_AMOUNT)
			{
				count=arpds.getValue().doubleValue();
			}
		}
		return count;
	}
	
	/*
	 * 根据缴费单明细Id计算出相关的院校返款金额---院校返款用
	 * 
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findAcademyRebateByfeePaymentDetailId(int)
	 */
	public double findAcademyRebateByfeePaymentDetailId(int feePaymentDetailId) throws Exception
	{
		double count=0;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(feePaymentDetailId);
		if(fpd!=null)
		{
			//基数  由于退费，所以要乘以一个基数
			BigDecimal jishu=new BigDecimal(1);
			if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
			{
				jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
			}
			
			count=findAcademyRebateByStudentFeeSubjectIdMoney(studentBiz.findStudentById(fpd.getStudentId()),fpd.getFeeSubjectId(),(new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyCeduDiscount()))).multiply(jishu));
		}
		return count;
	}
	
	
	/*
	 * 2012-05-08  重构
	 * 根据学生和费用科目Id匹配相关的院校返款标准---院校返款用
	 * 
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findByStudentIdFeeSubjectIdForAcademyRebate(net.cedu.entity.crm.Student, int)
	 */
	public AcademyRebatePolicyDetailStandard findAcademyRebateStandardByStudentIdFeeSubjectId(Student student,int feeSubjectId) throws Exception
	{
		AcademyRebatePolicyDetailStandard arpds=null;
		if(student!=null && feeSubjectId>0)
		{
			AcademyRebatePolicyDetail arpd=this.academyRebatePolicyDetailBiz.findForStudnet(student,feeSubjectId,false);
			if(arpd!=null)
			{
				List<AcademyRebatePolicyDetailStandard> arpdslist=this.findByPolicyId(arpd.getPolicyId());
				if(arpdslist!=null && arpdslist.size()>0)
				{
					//查询招生人数
					int count =feePaymentDetailBiz.countStudCountForAcademyRebateByStudent(student, feeSubjectId);
					int asize=arpdslist.size();
					int index=1;
					for(AcademyRebatePolicyDetailStandard as:arpdslist)
					{
						if(index==1 && count<as.getFloor())
						{
							//arpds=as;
							break;
						}
						if(as.getFloor()<=count && as.getCeil()>=count)
						{
							arpds=as;
							break;
						}
						else if(index==asize && count>as.getCeil()) //保证匹配到一个标准
						{
							arpds=as;
							break;
						}
						index++;
					}
				}
			}
		}
		return arpds;
	}
	
	/*
	 * 2012-05-08 重构
	 * 根据缴费单明细Id计算出相关的院校返款金额---院校返款用
	 * 
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findAcademyRebateByStudentFeeSubjectIdMoney(net.cedu.entity.crm.Student, int, java.math.BigDecimal)
	 */
	public boolean updateFpdForAcademyRebateByfpdId(int fpdId) throws Exception
	{
		boolean isback=false;
		double count=0;
		BigDecimal money=new BigDecimal(0);//金额
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(fpdId);
		if(fpd!=null)
		{
			AcademyRebatePolicyDetailStandard arpds=null;
			//查询该学生是否已经绑定了院校返款标准
			StudentAcademyRebate sarfind=new StudentAcademyRebate();
			sarfind.setStudentId(fpd.getStudentId());
			sarfind.setFeeSubjectId(fpd.getFeeSubjectId());
			StudentAcademyRebate sar=this.studentAcademyRebateBiz.findStudentAcademyRebateListBy(sarfind);
			if(sar!=null)
			{
				arpds=this.academyRebatePolicyDetailStandardDao.findById(sar.getAcademyPoliceStandardId());
			}
			else
			{
				arpds=findAcademyRebateStandardByStudentIdFeeSubjectId(studentBiz.findStudentById(fpd.getStudentId()),fpd.getFeeSubjectId());
			}
			
			//基数  由于退费，所以要乘以一个基数
			BigDecimal jishu=new BigDecimal(1);
			if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
			{
				jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
			}						
			money=(new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyCeduDiscount()))).multiply(jishu);
			
			
			if(arpds!=null)
			{
				if(arpds.getValueForm()==Constants.MONEY_FORM_RATIO)
				{
					count=(money.multiply(arpds.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
				}
				else if(arpds.getValueForm()==Constants.MONEY_FORM_AMOUNT)
				{
					count=arpds.getValue().doubleValue();
				}
				fpd.setAcademyPolicyStandardId(arpds.getId());
			}
			fpd.setPayAcademyCedu(count);
			isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
		}
		return isback;
	}
	
	/*
	 * 2012-05-08 重构
	 *  根据缴费单明细Ids字符串批量计算出相关的院校返款金额---院校返款用
	 * 
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findAcademyRebateByfeePaymentDetailId(int)
	 */
	public boolean updateBatchFpdForAcademyRebateByfpdIds(String fpdIds) throws Exception
	{
		boolean isback=false;
		if(fpdIds!=null && !fpdIds.equals(""))
		{
			String[] fIds=fpdIds.split(",");
			for(int i=0;i<fIds.length;i++)
			{
				isback=updateFpdForAcademyRebateByfpdId(Integer.valueOf(fIds[i]));
			}
		}
		return isback;
	}
	
	
	/*
	 * 2012-07-04  重构
	 * 根据学生和费用科目Id、招生人数匹配相关的院校返款标准---院校返款用
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(net.cedu.entity.crm.Student, int, int)
	 */
	public AcademyRebatePolicyDetailStandard findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(Student student,int feeSubjectId,int count) throws Exception
	{
		AcademyRebatePolicyDetailStandard arpds=null;
		if(student!=null && feeSubjectId>0)
		{
			AcademyRebatePolicyDetail arpd=this.academyRebatePolicyDetailBiz.findForStudnet(student,feeSubjectId,false);
			if(arpd!=null)
			{
				List<AcademyRebatePolicyDetailStandard> arpdslist=this.findByPolicyId(arpd.getPolicyId());
				if(arpdslist!=null && arpdslist.size()>0)
				{
					int asize=arpdslist.size();
					int index=1;
					for(AcademyRebatePolicyDetailStandard as:arpdslist)
					{
						if(index==1 && count<as.getFloor())
						{
							//arpds=as;
							break;
						}
						if(as.getFloor()<=count && as.getCeil()>=count)
						{
							arpds=as;
							break;
						}
						else if(index==asize && count>as.getCeil()) //保证匹配到一个标准
						{
							arpds=as;
							break;
						}
						index++;
					}
				}
			}
		}
		return arpds;
	}
	
	/*
	 * 2012-07-04 重构
	 * 根据缴费单明细Id和本次需要返款的缴费单明细ids字符串计算出相关的院校返款金额---院校返款用
	 * @see net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz#updateFpdForAcademyRebateReviewByFpdIdAndFpdIds(int, java.lang.String)
	 */
	public boolean updateFpdForAcademyRebateReviewByFpdIdAndFpdIds(int fpdId,String fpdIds) throws Exception
	{
		boolean isback=false;
		double count=0;
		BigDecimal money=new BigDecimal(0);//金额
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(fpdId);
		if(fpd!=null)
		{
			Student stu=this.studentBiz.findStudentById(fpd.getStudentId());
			if(stu!=null)
			{
				AcademyRebatePolicyDetailStandard arpds=null;
				//查询该学生所在院校批次的返款人数记录
				AcademyBatchRebateCount abrcount=new AcademyBatchRebateCount();
				abrcount.setAcademyId(stu.getAcademyId());
				abrcount.setBatchId(stu.getEnrollmentBatchId());
				abrcount.setFeeSubjectId(fpd.getFeeSubjectId());
				AcademyBatchRebateCount abrct=this.academyBatchRebateCountBiz.findAcademyBatchRebateCountListBy(abrcount);
				int academyrc=0;//院校当前批次下计算返款的人数
				//本次应计算的返款人数
				int arcount=feePaymentDetailBiz.countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(stu,fpd.getFeeSubjectId(),fpdIds);
				if(abrct!=null)
				{		
					if(abrct.getIsYearCount()==Constants.ACADEMY_REBATE_IS_YEAR_COUNT_TRUE || abrct.getRebateCount()>=arcount)
					{
						academyrc=abrct.getRebateCount();
					}
					else
					{
						academyrc=arcount;
					}					
				}
				else
				{
					academyrc=arcount;
				}
				arpds=findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,fpd.getFeeSubjectId(),academyrc);
				if(arpds!=null)
				{
					//基数  由于退费，所以要乘以一个基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
					}						
					money=(new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyCeduDiscount()))).multiply(jishu);				
				
					if(arpds.getValueForm()==Constants.MONEY_FORM_RATIO)
					{
						count=(money.multiply(arpds.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
					}
					else if(arpds.getValueForm()==Constants.MONEY_FORM_AMOUNT)
					{
						count=arpds.getValue().doubleValue();
					}
					fpd.setAcademyPolicyStandardId(arpds.getId());
					fpd.setAcademyRebateCount(academyrc);
					fpd.setAcademyRebateBenciCount(arcount);
					fpd.setPayAcademyCedu(count);
					isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
				}				
			}
		}
		return isback;
	}
	
	
}
