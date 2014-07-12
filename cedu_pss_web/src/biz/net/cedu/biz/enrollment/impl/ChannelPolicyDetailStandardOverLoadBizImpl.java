package net.cedu.biz.enrollment.impl;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz;
import net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.ChannelPolicyDetailStandardDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.ChannelBatchRecruitRebateStandard;
import net.cedu.entity.finance.FeePaymentDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招生返款政策 业明细务逻辑层实现_招生返款需求重构专用_2012-05-18
 * 
 * @author xiao
 *
 */
@Service
public class ChannelPolicyDetailStandardOverLoadBizImpl implements ChannelPolicyDetailStandardOverLoadBiz
{
	@Autowired
	private ChannelPolicyDetailStandardDao channelPolicyDetailStandardDao;  //招生返款政策明细 业务接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;//合作方政策_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private ChannelBatchRecruitRebateStandardBiz cbrrsBiz;//合作方绑定招生返款标准关系_业务层接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 业务接口

	
	/*
	 * 根据id查询渠道政策 标准规则
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#findChannelPolicyDetailStandardById(int)
	 */
	public ChannelPolicyDetailStandard findChannelPolicyDetailStandardById(int id) throws Exception
	{
		return this.channelPolicyDetailStandardDao.findById(id);
	}
	
	/**
	 * 查询指定政策的所有招生返款标准
	 * 
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicyDetailStandard> findChannelPolicyDetailStandardListByPolicyId(int policyId) throws Exception
	{
		return channelPolicyDetailStandardDao.getByProperty(" and policyId="+Constants.PLACEHOLDER+" order by enrollmentFloor asc", new Object[]{ policyId });
	}
	
	
	/*
	 * 根据学生和费用科目Id、缴费单明细id字符串集合匹配相关的招生返款标准---招生返款用_____2012-05-24重构
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateReview(net.cedu.entity.crm.Student, int, java.lang.String, int)
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateReview(Student student,int feeSubjectId,String fpdIds,int countRebatePeople) throws Exception
	{
		ChannelPolicyDetailStandard cpds=null;
		if(student!=null && feeSubjectId>0)
		{
			ChannelPolicyDetail cpd=this.channelPolicyDetailBiz.findByStudent(student,feeSubjectId);
			if(cpd!=null)
			{
				List<ChannelPolicyDetailStandard> cpdslist=this.findChannelPolicyDetailStandardListByPolicyId(cpd.getPolicyId());
				if(cpdslist!=null && cpdslist.size()>0)
				{
					//查询招生人数
					int count =0;
					if(countRebatePeople>0)
					{
						count=countRebatePeople;
					}
					else
					{
						count=this.feePaymentDetailBiz.findfpdCountByChannelRebateReviewAllPeopleCount(student,fpdIds);
					}
					int csize=cpdslist.size();
					int index=1;
					for(ChannelPolicyDetailStandard cpo:cpdslist)
					{
						if(index==1 && count<cpo.getEnrollmentFloor())
						{
							//cpds=cpo; 小于最小数是不需匹配到标准
							break;
						}
						else if(cpo.getEnrollmentFloor()<=count && cpo.getEnrollmentCeil()>=count)
						{
							cpds=cpo;
							break;
						}
						else if(index==csize && count>cpo.getEnrollmentCeil()) //保证匹配到一个标准
						{
							cpds=cpo;
							break;
						}
						index++;
					}	
				}
			}
		}
		return cpds;
	}
	
	/*
	 * 院校政策
	 * 根据缴费单id和招生返款期id、缴费单Ids字符串计算出单个缴费单id招生返款金额
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#updateStuFpdChannelRebateReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int, java.lang.String, int)
	 */
	public boolean updateStuFpdChannelRebateReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int newFpdId, String oldFpdIds,int channelRebateTimeId) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(newFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=null;
				int countRebatePeople=0;//第一次招生返款人数
				//匹配合作方是否已经第一次返款
				ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
				findCbrrs.setAcademyId(student.getAcademyId());
				findCbrrs.setChannelId(student.getChannelId());
				findCbrrs.setBatchId(student.getEnrollmentBatchId());
				ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
				if(cbrrs!=null)
				{
					//cpds=this.channelPolicyDetailStandardDao.findById(cbrrs.getChannelPoliceStandardId());
					countRebatePeople=cbrrs.getRebateCount();
				}
				//else
				//{
				cpds=this.findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateReview(student, fpd.getFeeSubjectId(),oldFpdIds,countRebatePeople);
				//}
				if(cpds!=null)
				{
					//查询本次招生人数
					int rebatecount =this.feePaymentDetailBiz.findfpdCountByChannelRebateReviewAllPeopleCount(student,oldFpdIds);
					fpd.setChannelRebateCount(rebatecount);//本次招生返款该院校该批次总人数
					fpd.setChannelRebateTimeId(channelRebateTimeId);//本次招生返款期Id
					
					if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
					{
						//按比例
						BigDecimal money=new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()));
						Double count=(money.multiply(new BigDecimal(cpds.getValue())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
						fpd.setPaymentByChannel(count);
						fpd.setChannelPolicyStandardId(cpds.getId());
						isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
					}
					else if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
					{
						//按金额
						BigDecimal rebateMon=new BigDecimal(cpds.getValue());//固定金额总额度
						BigDecimal fpdrm=new BigDecimal(0);//平均分配到每张缴费单上
						
						List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(oldFpdIds,student.getId());
						if(fpdlist!=null && fpdlist.size()>0)
						{
							fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
							//不能减出负数
							if((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
							{
								fpd.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
							}
							else
							{
								fpd.setPaymentByChannel(0);
							}			
							fpd.setChannelPolicyStandardId(cpds.getId());
							
						}
						else
						{
							//不能减出负数
							if((rebateMon.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
							{
								fpd.setPaymentByChannel((rebateMon.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
							}
							else
							{
								fpd.setPaymentByChannel(0);
							}			
							fpd.setChannelPolicyStandardId(cpds.getId());
						}
						isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 根据费单明细Ids字符串和招生返款期id计算招生返款---招生返款用_____2012-05-24重构_
	 * 直接更新缴费单明细的返款金额、
	 * 冗余招生返款标准id、招生返款期id和本次招生返款总人数到缴费单明细里面，后面可以绑定到合作方与招生返款标准关系表里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#updateStuFpdChannelRebateReviewByOldFpdIdsChannelRebateTimeId(java.lang.String, int)
	 */
	public boolean updateStuFpdChannelRebateReviewByOldFpdIdsChannelRebateTimeId(String fpdIds,int channelRebateTimeId) throws Exception
	{
		boolean isback=false;
		if(fpdIds!=null && !fpdIds.equals(""))
		{
			String[] newIds=fpdIds.split(",");
			for(int i=0;i<newIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(Integer.valueOf(newIds[i]), fpdIds,channelRebateTimeId);
			}
		}
		return isback;
	}

	/*
	 * 通用政策
	 * 根据缴费单id和招生返款期id、缴费单Ids字符串计算出单个缴费单id招生返款金额
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#updateStuFpdChannelRebateReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int, java.lang.String, int)
	 */
	public boolean updateStuFpdChannelRebateCommonReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int newFpdId, String oldFpdIds,int channelRebateTimeId) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(newFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				//通用政策学生匹配人数和政策时都得变化
				Student stu=new Student();
				stu.setEnrollmentSource(student.getEnrollmentSource());
				stu.setChannelId(student.getChannelId());
				stu.setBranchId(-1);
				stu.setAcademyId(-1);
				stu.setEnrollmentBatchId(-1);
				stu.setLevelId(-1);
				stu.setMajorId(-1);
				//查找全局批次   通用返款政策匹配招生人数用到
				AcademyEnrollBatch aeb= academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				if(aeb!=null)
				{
					stu.setGlbtach(aeb.getGlobalEnrollBatchId());
				}
				
				
				ChannelPolicyDetailStandard cpds=null;
				//匹配合作方是否已经第一次返款
				ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
				findCbrrs.setAcademyId(student.getAcademyId());
				findCbrrs.setChannelId(student.getChannelId());
				findCbrrs.setBatchId(student.getEnrollmentBatchId());
				ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
				if(cbrrs!=null)
				{
					cpds=this.channelPolicyDetailStandardDao.findById(cbrrs.getChannelPoliceStandardId());
				}
				else
				{
					
					cpds=this.findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateReview(stu, fpd.getFeeSubjectId(),oldFpdIds,0);
				}
				if(cpds!=null)
				{
					//查询本次招生人数
					int rebatecount =this.feePaymentDetailBiz.findfpdCountByChannelRebateReviewAllPeopleCount(stu,oldFpdIds);
					fpd.setChannelRebateCount(rebatecount);//本次招生返款该院校该批次总人数
					fpd.setChannelRebateTimeId(channelRebateTimeId);//本次招生返款期Id
					
					if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
					{
						//按比例
						BigDecimal money=new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()));
						Double count=(money.multiply(new BigDecimal(cpds.getValue())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
						fpd.setPaymentByChannel(count);
						fpd.setChannelPolicyStandardId(cpds.getId());
						isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
					}
					else if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
					{
						//按金额
						BigDecimal rebateMon=new BigDecimal(cpds.getValue());//固定金额总额度
						BigDecimal fpdrm=new BigDecimal(0);//平均分配到每张缴费单上
						
						List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(oldFpdIds,student.getId());
						if(fpdlist!=null && fpdlist.size()>0)
						{
							fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
							//不能减出负数
							if((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
							{
								fpd.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
							}
							else
							{
								fpd.setPaymentByChannel(0);
							}			
							fpd.setChannelPolicyStandardId(cpds.getId());
							
						}
						else
						{
							//不能减出负数
							if((rebateMon.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
							{
								fpd.setPaymentByChannel((rebateMon.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
							}
							else
							{
								fpd.setPaymentByChannel(0);
							}			
							fpd.setChannelPolicyStandardId(cpds.getId());
						}
						isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
					}
				}
			}
		}
		return isback;
	}
	
	
	/*
	 *  * 特殊合作方
	 * 
	 * 根据学生和费用科目Id、缴费单明细id字符串集合匹配相关的招生返款标准---招生返款用_____2012-05-26重构
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateSpecialReview(net.cedu.entity.crm.Student, int, java.lang.String, int)
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateSpecialReview(Student student,int feeSubjectId,String fpdIds,int countRebatePeople) throws Exception
	{
		ChannelPolicyDetailStandard cpds=null;
		if(student!=null && feeSubjectId>0)
		{
			ChannelPolicyDetail cpd=this.channelPolicyDetailBiz.findByStudent(student,feeSubjectId);
			if(cpd!=null)
			{
				List<ChannelPolicyDetailStandard> cpdslist=this.findChannelPolicyDetailStandardListByPolicyId(cpd.getPolicyId());
				if(cpdslist!=null && cpdslist.size()>0)
				{
					//查询招生人数
					int count =0;
					if(countRebatePeople>0)
					{
						count=countRebatePeople;
					}
					else
					{
						count=this.feePaymentDetailBiz.countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(student,fpdIds);
					}					
					int csize=cpdslist.size();
					int index=1;
					for(ChannelPolicyDetailStandard cpo:cpdslist)
					{
						if(index==1 && count<cpo.getEnrollmentFloor())
						{
							//cpds=cpo; 小于最小数是不需匹配到标准
							break;
						}
						else if(cpo.getEnrollmentFloor()<=count && cpo.getEnrollmentCeil()>=count)
						{
							cpds=cpo;
							break;
						}
						else if(index==csize && count>cpo.getEnrollmentCeil()) //保证匹配到一个标准
						{
							cpds=cpo;
							break;
						}
						index++;
					}	
				}
			}
		}
		return cpds;
	}
	
	/*
	 * 特殊合作方
	 * 
	 * 根据缴费单id和学生实体、主合作方id、缴费单Ids字符串计算出单个缴费单id招生返款金额
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz#updateStuFpdChannelRebateReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int, java.lang.String, int)
	 */
	public boolean updateStuFpdChannelRebateSpecialReviewByNewFpdIdOldFpdIdsStudentChannelId(int newFpdId, String oldFpdIds,Student student,int channelId,int policeStatus) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(newFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			if(student!=null)
			{			
				ChannelPolicyDetailStandard cpds=null;
				//匹配合作方是否已经第一次返款
				int countRebatePeople=0;//第一次招生返款人数
				Student stu=this.studentBiz.findStudentById(fpd.getStudentId());
				ChannelBatchRecruitRebateStandard findCbrrs=new ChannelBatchRecruitRebateStandard();
				findCbrrs.setAcademyId(stu.getAcademyId());
				findCbrrs.setChannelId(stu.getChannelId());
				findCbrrs.setBatchId(stu.getEnrollmentBatchId());
				ChannelBatchRecruitRebateStandard cbrrs=this.cbrrsBiz.findChannelBatchRecruitRebateStandardListBy(findCbrrs);
				//院校政策
				if(policeStatus==Constants.CHANNEL_REBATE_POLICE_STATUS_ACADEMY)
				{
					if(cbrrs!=null)
					{
						countRebatePeople=cbrrs.getRebateCount();
					}				
					student.setChannelId(channelId);//将合作方设置为主合作方id去匹配政策
					cpds=this.findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateSpecialReview(student, fpd.getFeeSubjectId(),oldFpdIds,countRebatePeople);
					
				}
				else
				{
					if(cbrrs!=null)
					{
						cpds=this.channelPolicyDetailStandardDao.findById(cbrrs.getChannelPoliceStandardId());
					}
					else
					{				
						student.setChannelId(channelId);//将合作方设置为主合作方id去匹配政策
						cpds=this.findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateSpecialReview(student, fpd.getFeeSubjectId(),oldFpdIds,countRebatePeople);
					}
				}
				if(cpds!=null)
				{
					//查询本次招生人数
					int rebatecount =this.feePaymentDetailBiz.countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(student,oldFpdIds);
					fpd.setChannelRebateCount(rebatecount);//本次招生返款该院校该批次总人数
					
					if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
					{
						//按比例
						BigDecimal money=new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()));
						Double count=(money.multiply(new BigDecimal(cpds.getValue())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
						fpd.setPaymentByChannel(count);
						fpd.setChannelPolicyStandardId(cpds.getId());
						isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
					}
					else if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
					{
						//按金额
						BigDecimal rebateMon=new BigDecimal(cpds.getValue());//固定金额总额度
						BigDecimal fpdrm=new BigDecimal(0);//平均分配到每张缴费单上
						
						List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(oldFpdIds,student.getId());
						if(fpdlist!=null && fpdlist.size()>0)
						{
							fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
							//不能减出负数
							if((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
							{
								fpd.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
							}
							else
							{
								fpd.setPaymentByChannel(0);
							}			
							fpd.setChannelPolicyStandardId(cpds.getId());
							
						}
						else
						{
							//不能减出负数
							if((rebateMon.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
							{
								fpd.setPaymentByChannel((rebateMon.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
							}
							else
							{
								fpd.setPaymentByChannel(0);
							}			
							fpd.setChannelPolicyStandardId(cpds.getId());
						}
						isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
					}
				}
			}
		}
		return isback;
	}
	
}
