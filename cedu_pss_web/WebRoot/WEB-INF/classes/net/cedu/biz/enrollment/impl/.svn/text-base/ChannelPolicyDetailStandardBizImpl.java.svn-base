package net.cedu.biz.enrollment.impl;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.ChannelPolicyDetailStandardDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.finance.FeePaymentDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 招生返款政策 业明细务逻辑层实现
 * 
 * @author gaolei
 *
 */
@Service
public class ChannelPolicyDetailStandardBizImpl implements ChannelPolicyDetailStandardBiz
{
	@Autowired
	private ChannelPolicyDetailStandardDao channelPolicyDetailStandardDao;  //招生返款政策明细 业务接口
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;//合作方政策_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 业务接口
	
	
	/*
	 * 根据条件查询渠道政策 列表
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findChannelPolicyDetailStandardchannelId(int channelpolicyId) throws Exception
	{
		return channelPolicyDetailStandardDao.findById(channelpolicyId);
	}
	
	/**
	 * 批量添加标准规则
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void addList(List<ChannelPolicyDetailStandard> list) throws Exception
	{
		if(list == null) return;
		ChannelPolicyDetailStandard std = null;
		Iterator<ChannelPolicyDetailStandard> iter = list.iterator();
		while(iter.hasNext())
		{
			std = iter.next();
			channelPolicyDetailStandardDao.save(std);
		}
	}
	
	/**
	 * 查询指定政策标准的所有准则
	 * 
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicyDetailStandard> findByPolicyId(int policyId) throws Exception
	{
		return channelPolicyDetailStandardDao.getByProperty(" and policyId="+Constants.PLACEHOLDER+" order by enrollmentFloor asc", new Object[]{ policyId });
	}
	
	/**
	 * 删除招生政策标准为下所有标准准则
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public int deleteByPolicyId(int policyId) throws Exception
	{
		List<ChannelPolicyDetailStandard> standards = channelPolicyDetailStandardDao.getByProperty("policyId", policyId);
		if(standards==null) return 0;
		for(ChannelPolicyDetailStandard std : standards)
		{
			channelPolicyDetailStandardDao.deleteConfig(std);
		}

		return standards.size();
	}
	
	/*
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findByStudentFeeSubjectIdForChannelRebate(net.cedu.entity.crm.Student, int)
	 */
	public ChannelPolicyDetailStandard findByStudentFeeSubjectIdForChannelRebate(Student student,int feeSubjectId) throws Exception
	{
		ChannelPolicyDetailStandard cpds=null;
		if(student!=null && feeSubjectId>0)
		{
			ChannelPolicyDetail cpd=this.channelPolicyDetailBiz.findByStudent(student,feeSubjectId);
			if(cpd!=null)
			{
				List<ChannelPolicyDetailStandard> cpdslist=this.findByPolicyId(cpd.getPolicyId());
				if(cpdslist!=null && cpdslist.size()>0)
				{
					//查询招生人数
					int count =this.studentBiz.findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(student.getAcademyId(), student.getEnrollmentBatchId(), student.getChannelId(),student.getGlbtach());
					int csize=cpdslist.size();
					int index=1;
					for(ChannelPolicyDetailStandard cpo:cpdslist)
					{
						if(index==1 && count<cpo.getEnrollmentFloor())
						{
							cpds=cpo;
							break;
						}
						else if(cpo.getEnrollmentFloor()<=count && cpo.getEnrollmentCeil()>=count)
						{
							cpds=cpo;
							break;
						}
						else if(index==csize) //保证匹配到一个标准
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
	 * 根据学生、费用科目Id和实缴金额、渠道优惠金额计算出相关的招生返款金额---招生返款用
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findChannelRebateByStudentFeeSubjectIdMoney(net.cedu.entity.crm.Student, int, java.math.BigDecimal, java.math.BigDecimal)
	 */
	public double findChannelRebateByStudentFeeSubjectIdMoney(Student student,int feeSubjectId,BigDecimal money,BigDecimal channelDiscount) throws Exception
	{
		double count=0;
		ChannelPolicyDetailStandard cpds=this.findByStudentFeeSubjectIdForChannelRebate(student, feeSubjectId);
		if(cpds!=null)
		{
			if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
			{
				count=(money.multiply(new BigDecimal(cpds.getValue())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			}
			else if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
			{
				count=(new BigDecimal(cpds.getValue()).subtract(channelDiscount)).doubleValue();
				//不能减出负数
				if(count<0)
				{
					count=0;
				}
			}
		}
		return count;
	}
	
	/*
	 * 根据缴费单明细Id计算出相关的招生返款金额---招生返款用
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findChannelRebateByfeePaymentDetailId(int)
	 */
	public double findChannelRebateByfeePaymentDetailId(int feePaymentDetailId) throws Exception
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
			
			count=findChannelRebateByStudentFeeSubjectIdMoney(studentBiz.findStudentById(fpd.getStudentId()),fpd.getFeeSubjectId(),(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).multiply(jishu),new BigDecimal(fpd.getChannelDiscount()));
		}
		return count;
	}
	
	/*
	 * 根据缴费单明细Id计算出相关的招生返款金额---招生返款用(通用政策)
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findChannelRebateCommonByfeePaymentDetailId(int)
	 */
	public double findChannelRebateCommonByfeePaymentDetailId(int feePaymentDetailId) throws Exception
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
			Student stu=studentBiz.findStudentById(fpd.getStudentId());
			Student s1=null;
			if(stu!=null)
			{
				//为了匹配通用政策和招生人数
//				stu.setBranchId(-1);
//				stu.setAcademyId(-1);
//				stu.setEnrollmentBatchId(-1);
//				stu.setLevelId(-1);
//				stu.setMajorId(-1);
				s1=new Student();
				s1.setChannelId(stu.getChannelId());
				s1.setBranchId(-1);
				s1.setAcademyId(-1);
				s1.setEnrollmentBatchId(-1);
				s1.setLevelId(-1);
				s1.setMajorId(-1);
				//查找全局批次   通用返款政策匹配招生人数用到
				AcademyEnrollBatch aeb= academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
				if(aeb!=null)
				{
					s1.setGlbtach(aeb.getGlobalEnrollBatchId());
				}
			}
			count=findChannelRebateByStudentFeeSubjectIdMoney(s1,fpd.getFeeSubjectId(),(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).multiply(jishu),new BigDecimal(fpd.getChannelDiscount()));
		}
		return count;
	}
	
	
	/*
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用___特殊合作方
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findByStudentFeeSubjectIdForChannelRebateSpecial(net.cedu.entity.crm.Student, int, int, int)
	 */
	public ChannelPolicyDetailStandard findByStudentFeeSubjectIdForChannelRebateSpecial(Student student,int channelId,int minorChannelId,int feeSubjectId) throws Exception
	{
		ChannelPolicyDetailStandard cpds=null;
		if(student!=null && feeSubjectId>0)
		{
			ChannelPolicyDetail cpd=this.channelPolicyDetailBiz.findByStudent(student,feeSubjectId);
			if(cpd!=null)
			{
				List<ChannelPolicyDetailStandard> cpdslist=this.findByPolicyId(cpd.getPolicyId());
				if(cpdslist!=null && cpdslist.size()>0)
				{
					//查询招生人数(两个合作方一起的人数)
					int count =this.studentBiz.findHaveSignedUpStudentCountByAcademyIdEnrollmentBatchIdChannelId(student.getAcademyId(), student.getEnrollmentBatchId(), channelId, minorChannelId,student.getGlbtach());
					int csize=cpdslist.size();
					int index=1;
					for(ChannelPolicyDetailStandard cpo:cpdslist)
					{
						if(index==1 && count<cpo.getEnrollmentFloor())
						{
							cpds=cpo;
							break;
						}
						else if(cpo.getEnrollmentFloor()<=count && cpo.getEnrollmentCeil()>=count)
						{
							cpds=cpo;
							break;
						}
						else if(index==csize) //保证匹配到一个标准
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
	 * 根据学生、费用科目Id和实缴金额、渠道优惠金额、主合作方Id、次合作方Id计算出相关的招生返款金额---招生返款用____特殊合作方
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findChannelRebateSpecialByStudentFeeSubjectIdMoneyChannelId(net.cedu.entity.crm.Student, int, java.math.BigDecimal, java.math.BigDecimal, int, int)
	 */
	public double findChannelRebateSpecialByStudentFeeSubjectIdMoneyChannelId(Student student,int feeSubjectId,BigDecimal money,BigDecimal channelDiscount,int channelId,int minorChannelId) throws Exception
	{
		double count=0;
		ChannelPolicyDetailStandard cpds=this.findByStudentFeeSubjectIdForChannelRebateSpecial(student,channelId,minorChannelId, feeSubjectId);
		if(cpds!=null)
		{
			if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
			{
				count=(money.multiply(new BigDecimal(cpds.getValue())).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)).doubleValue();
			}
			else if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
			{
				count=(new BigDecimal(cpds.getValue()).subtract(channelDiscount)).doubleValue();
				//不能减出负数
				if(count<0)
				{
					count=0;
				}
			}
		}
		return count;
	}
	
	/*
	 * 根据缴费单明细Id计算出相关的招生返款金额---招生返款用____特殊合作方
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findChannelRebateByfeePaymentDetailId(int)
	 */
	public double findChannelRebateSpecialByfeePaymentDetailIdChannelIdPolicyStatus(int feePaymentDetailId,int channelId,int minorChannelId,int policeStatus) throws Exception
	{
		double count=0;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(feePaymentDetailId);
		if(fpd!=null)
		{
			Student stu=studentBiz.findStudentById(fpd.getStudentId());
			Student s1=null;
			if(stu!=null)
			{
				s1=new Student();
				if(policeStatus==Constants.CHANNEL_REBATE_POLICE_STATUS_ACADEMY)
				{
					//stu.setChannelId(channelId);
					s1=stu;
					s1.setChannelId(channelId);
				}
				else
				{
					
					//为了匹配通用政策
					s1.setChannelId(channelId);
					s1.setBranchId(-1);
					s1.setAcademyId(-1);
					s1.setEnrollmentBatchId(-1);
					s1.setLevelId(-1);
					s1.setMajorId(-1);
					//查找全局批次   通用返款政策匹配招生人数用到
					AcademyEnrollBatch aeb= academyenrollbatchBiz.findAcademyEnrollBatchById(stu.getEnrollmentBatchId());
					if(aeb!=null)
					{
						s1.setGlbtach(aeb.getGlobalEnrollBatchId());
					}

				}	
			}
			//基数  由于退费，所以要乘以一个基数
			BigDecimal jishu=new BigDecimal(1);
			if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
			{
				jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
			}
			count=findChannelRebateSpecialByStudentFeeSubjectIdMoneyChannelId(s1,fpd.getFeeSubjectId(),(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).multiply(jishu),new BigDecimal(fpd.getChannelDiscount()), channelId, minorChannelId);
		}
		return count;
	}
	
	
	// ******************************************招生返款重构_____2012-04-05*********************************************//
	
	/*
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用_____2012-04-05重构
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findCpdsByStudentFeeSubjectIdForChannelRebateReview(net.cedu.entity.crm.Student, int)
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdForChannelRebateReview(Student student,int feeSubjectId) throws Exception
	{
		ChannelPolicyDetailStandard cpds=null;
		if(student!=null && feeSubjectId>0)
		{
			ChannelPolicyDetail cpd=this.channelPolicyDetailBiz.findByStudent(student,feeSubjectId);
			if(cpd!=null)
			{
				List<ChannelPolicyDetailStandard> cpdslist=this.findByPolicyId(cpd.getPolicyId());
				if(cpdslist!=null && cpdslist.size()>0)
				{
					//查询招生人数
					int count =this.feePaymentDetailBiz.findfpdCountByChannelAllPeopleCount(student);
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
	 * 根据原缴费单明细Ids字符串、要添加的缴费单明细id计算招生返款---招生返款用_____2012-04-05重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateByNewFpdIdOldFpdIdsReview(int, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateByNewFpdIdOldFpdIdsReview(int newFpdId, String oldFpdIds) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(newFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=null;
				if(student.getChannelPolicyStandardId()>0 && student.getChannelPolicyStandardLock()==Constants.LOCKING_TRUE)
				{
					cpds=this.channelPolicyDetailStandardDao.findById(student.getChannelPolicyStandardId());
				}
				else
				{
					cpds=this.findCpdsByStudentFeeSubjectIdForChannelRebateReview(student, fpd.getFeeSubjectId());
				}
				if(cpds!=null)
				{
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
						if(oldFpdIds!=null && !oldFpdIds.equals(""))
						{
							List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(oldFpdIds,student.getId());
							if(fpdlist!=null && fpdlist.size()>0)
							{
								fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
//								if((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
//								{
//									fpd.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpd.getChannelDiscount()))).doubleValue());
//								}
//								else
//								{
//									fpd.setPaymentByChannel(0);
//								}			
//								fpd.setChannelPolicyStandardId(cpds.getId());
//								isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
								for(FeePaymentDetail fpdetail :fpdlist)
								{
									//不能减出负数
									if((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
									{
										fpdetail.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount()))).doubleValue());
									}
									else
									{
										fpdetail.setPaymentByChannel(0);
									}			
									fpdetail.setChannelPolicyStandardId(cpds.getId());
									isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpdetail);
								}
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
								isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
							}
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
							isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
						}
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 根据原缴费单明细Ids字符串、要添加的缴费单明细Ids字符串计算招生返款---招生返款用_____2012-04-05重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateByNewFpdIdsOldFpdIdsReview(java.lang.String, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateByNewFpdIdsOldFpdIdsReview(String newFpdIds, String oldFpdIds) throws Exception
	{
		boolean isback=false;
		if(newFpdIds!=null && !newFpdIds.equals(""))
		{
			String[] newIds=newFpdIds.split(",");
			for(int i=0;i<newIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateByNewFpdIdOldFpdIdsReview(Integer.valueOf(newIds[i]), oldFpdIds);
			}
		}
		return isback;
	}
	
	/*
	 * 根据原缴费单明细Ids字符串、要移除的缴费单明细Id移除招生返款---招生返款用_____2012-04-05重构
	 */
	public boolean updateStuFpdChannelRebateByDelFpdIdOldFpdIdsReview(int delFpdId, String oldFpdIds) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(delFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=this.channelPolicyDetailStandardDao.findById(fpd.getChannelPolicyStandardId());
				if(cpds!=null)
				{
					if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
					{
						//按金额
						BigDecimal rebateMon=new BigDecimal(cpds.getValue());//固定金额总额度
						BigDecimal fpdrm=new BigDecimal(0);//平均分配到每张缴费单上
						if(oldFpdIds!=null && !oldFpdIds.equals(""))
						{
							List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(oldFpdIds,student.getId());
							if(fpdlist!=null && fpdlist.size()>0)
							{
								fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
								for(FeePaymentDetail fpdetail :fpdlist)
								{
									if(fpdetail.getId()!=fpd.getId());
									{
										//不能减出负数
										if((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
										{
											fpdetail.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount()))).doubleValue());
										}
										else
										{
											fpdetail.setPaymentByChannel(0);
										}			
										fpdetail.setChannelPolicyStandardId(cpds.getId());
										isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpdetail);
									}
								}
							}	
						}
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 根据原缴费单明细Ids字符串、要移除的缴费单明细Ids字符串移除招生返款---招生返款用_____2012-04-05重构
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateByDelFpdIdsOldFpdIdsReview(java.lang.String, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateByDelFpdIdsOldFpdIdsReview(String delFpdIds, String oldFpdIds) throws Exception
	{
		boolean isback=false;
		if(delFpdIds!=null && !delFpdIds.equals(""))
		{
			String[] delIds=delFpdIds.split(",");
			for(int i=0;i<delIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateByDelFpdIdOldFpdIdsReview(Integer.valueOf(delIds[i]), oldFpdIds);
			}
		}
		return isback;
	}
	
	
	/*
	 * 多院校____
	 * 根据所有缴费单明细Ids字符串、要添加的缴费单明细id计算招生返款---招生返款用___多院校__2012-04-08重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateCommonByNewFpdIdAllFpdIdsReview(int, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateCommonByNewFpdIdAllFpdIdsReview(int newFpdId, String allFpdIds) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(newFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			Student s1=null;
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=null;
				if(student.getChannelPolicyStandardId()>0 && student.getChannelPolicyStandardLock()==Constants.LOCKING_TRUE)
				{
					cpds=this.channelPolicyDetailStandardDao.findById(student.getChannelPolicyStandardId());
				}
				else
				{
					s1=new Student();
					s1.setEnrollmentSource(student.getEnrollmentSource());
					s1.setChannelId(student.getChannelId());
					s1.setBranchId(-1);
					s1.setAcademyId(-1);
					s1.setEnrollmentBatchId(-1);
					s1.setLevelId(-1);
					s1.setMajorId(-1);
					//查找全局批次   通用返款政策匹配招生人数用到
					AcademyEnrollBatch aeb= academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
					if(aeb!=null)
					{
						s1.setGlbtach(aeb.getGlobalEnrollBatchId());
					}
					cpds=this.findCpdsByStudentFeeSubjectIdForChannelRebateReview(s1, fpd.getFeeSubjectId());
				}
				if(cpds!=null)
				{
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
						if(allFpdIds!=null && !allFpdIds.equals(""))
						{
							List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(allFpdIds,student.getId());
							if(fpdlist!=null && fpdlist.size()>0)
							{
								fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
								for(FeePaymentDetail fpdetail :fpdlist)
								{
									//不能减出负数
									if((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
									{
										fpdetail.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount()))).doubleValue());
									}
									else
									{
										fpdetail.setPaymentByChannel(0);
									}			
									fpdetail.setChannelPolicyStandardId(cpds.getId());
									isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpdetail);
								}
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
								isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
							}
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
							isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
						}
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 多院校____
	 * 根据所有缴费单明细Ids字符串、要添加的缴费单明细Ids字符串计算招生返款---招生返款用__多院校___2012-04-05重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateCommonByNewFpdIdsAllFpdIdsReview(java.lang.String, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateCommonByNewFpdIdsAllFpdIdsReview(String newFpdIds, String allFpdIds) throws Exception
	{
		boolean isback=false;
		if(newFpdIds!=null && !newFpdIds.equals(""))
		{
			String[] newIds=newFpdIds.split(",");
			for(int i=0;i<newIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateCommonByNewFpdIdAllFpdIdsReview(Integer.valueOf(newIds[i]), allFpdIds);
			}
		}
		return isback;
	}
	
	/*
	 * 多院校____
	 * 根据剩下的缴费单明细Ids字符串、要移除的缴费单明细Id移除招生返款---招生返款用__多院校___2012-04-05重构
	 */
	public boolean updateStuFpdChannelRebateCommonByDelFpdIdNewFpdIdsReview(int delFpdId, String newFpdIds) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(delFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=this.channelPolicyDetailStandardDao.findById(fpd.getChannelPolicyStandardId());
				if(cpds!=null)
				{
					if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
					{
						//按金额
						BigDecimal rebateMon=new BigDecimal(cpds.getValue());//固定金额总额度
						BigDecimal fpdrm=new BigDecimal(0);//平均分配到每张缴费单上
						if(newFpdIds!=null && !newFpdIds.equals(""))
						{
							List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(newFpdIds,student.getId());
							if(fpdlist!=null && fpdlist.size()>0)
							{
								fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
								for(FeePaymentDetail fpdetail :fpdlist)
								{
									if(fpdetail.getId()!=fpd.getId());
									{
										//不能减出负数
										if((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
										{
											fpdetail.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount()))).doubleValue());
										}
										else
										{
											fpdetail.setPaymentByChannel(0);
										}			
										fpdetail.setChannelPolicyStandardId(cpds.getId());
										isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpdetail);
									}
								}
							}	
						}
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 多院校____
	 * 根据剩下的缴费单明细Ids字符串、要移除的缴费单明细Ids字符串移除招生返款---招生返款用__多院校___2012-04-05重构
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateByDelFpdIdsOldFpdIdsReview(java.lang.String, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateCommonByDelFpdIdsNewFpdIdsReview(String delFpdIds, String newFpdIds) throws Exception
	{
		boolean isback=false;
		if(delFpdIds!=null && !delFpdIds.equals(""))
		{
			String[] delIds=delFpdIds.split(",");
			for(int i=0;i<delIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateCommonByDelFpdIdNewFpdIdsReview(Integer.valueOf(delIds[i]), newFpdIds);
			}
		}
		return isback;
	}
	
	/*
	 * 特殊合作方
	 * 
	 * 根据学生和费用科目Id匹配相关的招生返款政策---招生返款用_____2012-05-09重构
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findCpdByStuFsIdForChannelRebateSpecialReview(net.cedu.entity.crm.Student, int, int)
	 */
	public ChannelPolicyDetail findCpdByStuFsIdForChannelRebateSpecialReview(Student student,int feeSubjectId,int policeStatus) throws Exception
	{
		ChannelPolicyDetail cpd=null;
		if(student!=null && feeSubjectId>0)
		{
			Student s1=new Student();
			if(policeStatus==Constants.CHANNEL_REBATE_POLICE_STATUS_COMMON)
			{				
				s1.setEnrollmentSource(student.getEnrollmentSource());
				s1.setChannelId(student.getChannelId());
				s1.getParamsString().put("channelIds", student.getParamsString().get("channelIds"));
				s1.setBranchId(-1);
				s1.setAcademyId(-1);
				s1.setEnrollmentBatchId(-1);
				s1.setLevelId(-1);
				s1.setMajorId(-1);
				//查找全局批次   通用返款政策匹配招生人数用到
				AcademyEnrollBatch aeb= academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				if(aeb!=null)
				{
					s1.setGlbtach(aeb.getGlobalEnrollBatchId());
				}
			}
			else
			{
				s1=student;
			}
			cpd=this.channelPolicyDetailBiz.findByStudent(s1,feeSubjectId);
			
		}
		return cpd;
	}
	
	
	/*
	 * 特殊合作方
	 * 
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用_____2012-04-10重构
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#findCpdsByStudentFeeSubjectIdForChannelRebateSpecialReview(net.cedu.entity.crm.Student, int, int)
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdForChannelRebateSpecialReview(Student student,int feeSubjectId,int policeStatus) throws Exception
	{
		ChannelPolicyDetailStandard cpds=null;
		if(student!=null && feeSubjectId>0)
		{
			Student s1=new Student();
			if(policeStatus==Constants.CHANNEL_REBATE_POLICE_STATUS_COMMON)
			{				
				s1.setEnrollmentSource(student.getEnrollmentSource());
				s1.setChannelId(student.getChannelId());
				s1.getParamsString().put("channelIds", student.getParamsString().get("channelIds"));
				s1.setBranchId(-1);
				s1.setAcademyId(-1);
				s1.setEnrollmentBatchId(-1);
				s1.setLevelId(-1);
				s1.setMajorId(-1);
				//查找全局批次   通用返款政策匹配招生人数用到
				AcademyEnrollBatch aeb= academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
				if(aeb!=null)
				{
					s1.setGlbtach(aeb.getGlobalEnrollBatchId());
				}
			}
			else
			{
				s1=student;
			}
			ChannelPolicyDetail cpd=this.channelPolicyDetailBiz.findByStudent(s1,feeSubjectId);
			if(cpd!=null)
			{
				List<ChannelPolicyDetailStandard> cpdslist=this.findByPolicyId(cpd.getPolicyId());
				if(cpdslist!=null && cpdslist.size()>0)
				{
					//查询招生人数
					int count =this.feePaymentDetailBiz.countStudCountForChannelAdmissionsSpecialByStudent(s1);
					int csize=cpdslist.size();
					int index=1;
					for(ChannelPolicyDetailStandard cpo:cpdslist)
					{
						if(index==1 && count<cpo.getEnrollmentFloor())
						{
							//cpds=cpo;
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
	 * 根据新缴费单明细Ids字符串、要添加的缴费单明细id计算招生返款---招生返款用_____2012-04-10重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateSpecialByNewFpdIdNewFpdIdsReview(int, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateSpecialByNewFpdIdNewFpdIdsReview(int newFpdId, String newFpdIds,int policeStatus,int channelId,String minorChannelIds) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(newFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=null;
				if(student.getChannelPolicyStandardId()>0 && student.getChannelPolicyStandardLock()==Constants.LOCKING_TRUE)
				{
					cpds=this.channelPolicyDetailStandardDao.findById(student.getChannelPolicyStandardId());
				}
				else
				{
					String channelIds=channelId+"";
					if(minorChannelIds!=null && !minorChannelIds.equals(""))
					{
						channelIds+=","+minorChannelIds;
					}
					student.getParamsString().put("channelIds", channelIds);//所有合作方
					
					student.setChannelId(channelId);//匹配主合作方的政策
					
					cpds=this.findCpdsByStudentFeeSubjectIdForChannelRebateSpecialReview(student, fpd.getFeeSubjectId(),policeStatus);
				}
				if(cpds!=null)
				{
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
						if(newFpdIds!=null && !newFpdIds.equals(""))
						{
							List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(newFpdIds,student.getId());
							if(fpdlist!=null && fpdlist.size()>0)
							{
								fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);

								for(FeePaymentDetail fpdetail :fpdlist)
								{
									//不能减出负数
									if((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
									{
										fpdetail.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount()))).doubleValue());
									}
									else
									{
										fpdetail.setPaymentByChannel(0);
									}			
									fpdetail.setChannelPolicyStandardId(cpds.getId());
									isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpdetail);
								}
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
								isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
							}
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
							isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
						}
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 特殊合作方
	 * 
	 * 根据原缴费单明细Ids字符串、要添加的缴费单明细Ids字符串计算招生返款---招生返款用_____2012-04-10重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateSpecialByNewFpdIdsOldFpdIdsReview(java.lang.String, java.lang.String, int)
	 */
	public boolean updateStuFpdChannelRebateSpecialByNewFpdIdsOldFpdIdsReview(String newFpdIds, String oldFpdIds,int policeStatus,int channelId,String minorChannelIds) throws Exception
	{
		boolean isback=false;
		if(newFpdIds!=null && !newFpdIds.equals(""))
		{
			String[] newIds=newFpdIds.split(",");
			for(int i=0;i<newIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateSpecialByNewFpdIdNewFpdIdsReview(Integer.valueOf(newIds[i]), oldFpdIds, policeStatus,channelId,minorChannelIds);
			}
		}
		return isback;
	}
	
	/*
	 * 特殊合作方
	 * 
	 * 根据原缴费单明细Ids字符串、要移除的缴费单明细Id移除招生返款---招生返款用_____2012-04-10重构
	 */
	public boolean updateStuFpdChannelRebateSpecialByDelFpdIdOldFpdIdsReview(int delFpdId, String oldFpdIds) throws Exception
	{
		boolean isback=false;
		FeePaymentDetail fpd=this.feePaymentDetailBiz.findById(delFpdId);
		if(fpd!=null && fpd.getFeeSubjectId()>0)
		{
			Student student=this.studentBiz.findStudentById(fpd.getStudentId());
			if(student!=null)
			{
				ChannelPolicyDetailStandard cpds=this.channelPolicyDetailStandardDao.findById(fpd.getChannelPolicyStandardId());
				if(cpds!=null)
				{
					if(cpds.getRebatesId()==Constants.MONEY_FORM_AMOUNT)
					{
						//按金额
						BigDecimal rebateMon=new BigDecimal(cpds.getValue());//固定金额总额度
						BigDecimal fpdrm=new BigDecimal(0);//平均分配到每张缴费单上
						if(oldFpdIds!=null && !oldFpdIds.equals(""))
						{
							List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findfpdListByFpdIdsAndStuId(oldFpdIds,student.getId());
							if(fpdlist!=null && fpdlist.size()>0)
							{
								fpdrm=rebateMon.divide(new BigDecimal(fpdlist.size()),2,BigDecimal.ROUND_HALF_UP);
								for(FeePaymentDetail fpdetail :fpdlist)
								{
									if(fpdetail.getId()!=fpd.getId());
									{
										//不能减出负数
										if((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount())).compareTo(new BigDecimal(0)))>=0)
										{
											fpdetail.setPaymentByChannel((fpdrm.subtract(new BigDecimal(fpdetail.getChannelDiscount()))).doubleValue());
										}
										else
										{
											fpdetail.setPaymentByChannel(0);
										}			
										fpdetail.setChannelPolicyStandardId(cpds.getId());
										isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpdetail);
									}
								}
							}	
						}
					}
				}
			}
		}
		return isback;
	}
	
	/*
	 * 特殊合作方
	 * 
	 * 根据原缴费单明细Ids字符串、要移除的缴费单明细Ids字符串移除招生返款---招生返款用_____2012-04-10重构
	 * 
	 * @see net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz#updateStuFpdChannelRebateSpecialByDelFpdIdsOldFpdIdsReview(java.lang.String, java.lang.String)
	 */
	public boolean updateStuFpdChannelRebateSpecialByDelFpdIdsOldFpdIdsReview(String delFpdIds, String oldFpdIds) throws Exception
	{
		boolean isback=false;
		if(delFpdIds!=null && !delFpdIds.equals(""))
		{
			String[] delIds=delFpdIds.split(",");
			for(int i=0;i<delIds.length;i++)
			{
				isback=this.updateStuFpdChannelRebateByDelFpdIdOldFpdIdsReview(Integer.valueOf(delIds[i]), oldFpdIds);
			}
		}
		return isback;
	}
	
	
}
