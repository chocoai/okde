package net.cedu.action.finance.academyrebatereview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.entity.finance.PlanedAcademyBill;

/**
 * 院校返款单新增
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddAcademyRebateReviewAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz arpdsBiz;//院校返款标准明细_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	@Autowired
	private PlanedAcademyBillBiz pabBiz;//应收学校款_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private AcademyBatchRebateCountBiz academyBatchRebateCountBiz;//院校返款每个批次的返款总人数

	private PayAcademyCedu payAcademyCedu=new PayAcademyCedu();//院校返款实体
	private String newNeedFpdIds;//缴费单明细ids字符串
	private String planedAcademyBillIds;//应收学校款Ids字符串
	private String branchtzmoney;//学习中心分配的调整金额字符串
	
	private boolean isback=false;
	
	
	/**
	 * 添加院校返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_academy_rebate_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String aarca() throws Exception 
	{
		if(payAcademyCedu!=null)
		{
			//返款单
			payAcademyCedu.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payAcademyCedu.setCreatorId(super.getUser().getUserId());
			payAcademyCedu.setDeleteFlag(Constants.DELETE_FALSE);
			payAcademyCedu.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);
			payAcademyCedu.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payAcademyCedu.setRemittanceDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payAcademyCedu.setUpdaterId(super.getUser().getUserId());
			payAcademyCedu.setTypes(Constants.FALLBACK_TYPES_SUBMIT);
			payAcademyCedu.setAmount(payAcademyCedu.getPaymentAmount().add(payAcademyCedu.getAcademyAmount()));
			payAcademyCedu.setIsYearCount(Constants.ACADEMY_REBATE_DAN_YEAR_COUNT_ACADEMY);
			
			String abamString="";//院校批次人数已经追加过的
			//院校返款时记录每次返款时每个批次的返款总人数
			List<AcademyBatchRebateCount> abrclist=new ArrayList<AcademyBatchRebateCount>();
			// 院校返款追加记录
			List<AcademyRebateAddRecords> ararlist=new ArrayList<AcademyRebateAddRecords>();
			//追加缴费单明细
			List<FeePaymentDetail> feeplist=new ArrayList<FeePaymentDetail>();
			//缴费单明细
			List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();
			if(newNeedFpdIds!=null && !newNeedFpdIds.equals(""))
			{
				String[] fpdIds=newNeedFpdIds.split(",");
				for(int i=0;i<fpdIds.length;i++)
				{
					FeePaymentDetail feeObj=this.fpdBiz.findById(Integer.parseInt(fpdIds[i]));
					if(feeObj!=null)
					{
						//缴费单明细
						feeObj.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						feeObj.setUpdaterId(super.getUser().getUserId());
						feeObj.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);
						fpdlist.add(feeObj);
						
						//如果有追加金额则绑定返款人数和追加记录
						Student stu=this.studentBiz.findStudentById(feeObj.getStudentId());
						if(stu!=null)
						{						
							//过滤掉重复添加的问题    按院校批次 费用科目过滤（主要是按记录的人数去过滤掉重复的计算）
							if(abamString!=null && !abamString.equals(""))
							{
								String zuhecbrs=","+abamString+",";
								if(zuhecbrs.indexOf(","+stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+feeObj.getFeeSubjectId()+",")==-1)
								{
									//查询该学生所在院校批次的最新返款人数记录
									AcademyBatchRebateCount abrcount=new AcademyBatchRebateCount();
									abrcount.setAcademyId(stu.getAcademyId());
									abrcount.setBatchId(stu.getEnrollmentBatchId());
									abrcount.setFeeSubjectId(feeObj.getFeeSubjectId());
									AcademyBatchRebateCount abrct=this.academyBatchRebateCountBiz.findAcademyBatchRebateCountListBy(abrcount);
									//院校返款时记录每次返款时每个批次的返款总人数  (feeObj.getAcademyRebateCount()在统计结算更新时已经考虑过年度返款，这里就不需要再考虑)
									if((abrct==null) || (abrct!=null && abrct.getRebateCount()<feeObj.getAcademyRebateCount()))
									{
										AcademyBatchRebateCount abrc=new AcademyBatchRebateCount();
										abrc.setAcademyId(stu.getAcademyId());
										abrc.setBatchId(stu.getEnrollmentBatchId());
										abrc.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
										abrc.setCreatorId(super.getUser().getUserId());
										abrc.setFeeSubjectId(feeObj.getFeeSubjectId());
										abrc.setGlobalBatchId(stu.getGlobalBatch());
										abrc.setIsYearCount(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
										abrc.setRebateCount(feeObj.getAcademyRebateCount());
										abrc.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
										abrc.setUpdaterId(super.getUser().getUserId());
										abrclist.add(abrc);
									}
									//追加记录
									if(abrct!=null && abrct.getRebateCount()<feeObj.getAcademyRebateCount() && payAcademyCedu.getAddPaied().compareTo(new BigDecimal(0))>0)
									{
										AcademyRebatePolicyDetailStandard arpdone=null;
										AcademyRebatePolicyDetailStandard arpdtwo=null;
										BigDecimal countone=new BigDecimal(0);
										BigDecimal counttwo=new BigDecimal(0);
										BigDecimal money=new BigDecimal(0);//金额
										//计算追加金额     按批次和院校、费用科目计算
										List<FeePaymentDetail> oldfpdlist=fpdBiz.findfpdListByAcademyIdBatchIdFeeSubjectIdForAcademyRebateAddMoney(stu,feeObj.getFeeSubjectId());
										if(oldfpdlist!=null && oldfpdlist.size()>0)
										{
											for(FeePaymentDetail feep:oldfpdlist)
											{
												arpdone=arpdsBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,feep.getFeeSubjectId(),abrct.getRebateCount());
												arpdtwo=arpdsBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,feep.getFeeSubjectId(),feeObj.getAcademyRebateCount());
												if(arpdone.getId()!=arpdtwo.getId())
												{
													//基数  由于退费，所以要乘以一个基数
													BigDecimal jishu=new BigDecimal(1);
													if((new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
													{
														jishu=(new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount())).subtract(new BigDecimal(feep.getRefundAmount()))).divide(new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
													}						
													money=(new BigDecimal(feep.getMoneyToPay()).subtract(new BigDecimal(feep.getAcademyCeduDiscount()))).multiply(jishu);				
													//第一个标准计算金额
													if(arpdone.getValueForm()==Constants.MONEY_FORM_RATIO)
													{
														countone=money.multiply(arpdone.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
													}
													else if(arpdone.getValueForm()==Constants.MONEY_FORM_AMOUNT)
													{
														countone=arpdone.getValue();
													}
													//第二个标准计算金额
													if(arpdtwo.getValueForm()==Constants.MONEY_FORM_RATIO)
													{
														counttwo=money.multiply(arpdtwo.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
													}
													else if(arpdtwo.getValueForm()==Constants.MONEY_FORM_AMOUNT)
													{
														counttwo=arpdtwo.getValue();
													}
													//比较计算的金额是否大于0
													if(((counttwo.subtract(countone)).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
													{
														feep.setAcademyRebateAddMoney(feep.getAcademyRebateAddMoney().add(counttwo.subtract(countone)));
														feep.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
														feep.setUpdaterId(super.getUser().getUserId());
														feeplist.add(feep);
														//院校返款追加记录
														AcademyRebateAddRecords arar=new AcademyRebateAddRecords();
														arar.setAddMoney(counttwo.subtract(countone));
														arar.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
														arar.setCreatorId(super.getUser().getUserId());
														arar.setFeePaymentDetailId(feep.getId());
														arar.setPayAcademyCeduAddId(feep.getOrderAcademyCeduId());
														arar.setStudentId(feep.getStudentId());
														arar.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
														arar.setUpdaterId(super.getUser().getUserId());
														ararlist.add(arar);
													}
												}
											}
										}
									}
									abamString+=","+stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+feeObj.getFeeSubjectId();
								}
							}
							else
							{	
								//查询该学生所在院校批次的最新返款人数记录
								AcademyBatchRebateCount abrcount=new AcademyBatchRebateCount();
								abrcount.setAcademyId(stu.getAcademyId());
								abrcount.setBatchId(stu.getEnrollmentBatchId());
								abrcount.setFeeSubjectId(feeObj.getFeeSubjectId());
								AcademyBatchRebateCount abrct=this.academyBatchRebateCountBiz.findAcademyBatchRebateCountListBy(abrcount);
								//院校返款时记录每次返款时每个批次的返款总人数
								if((abrct==null) || (abrct!=null && abrct.getRebateCount()<feeObj.getAcademyRebateCount()))
								{
									AcademyBatchRebateCount abrc=new AcademyBatchRebateCount();
									abrc.setAcademyId(stu.getAcademyId());
									abrc.setBatchId(stu.getEnrollmentBatchId());
									abrc.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									abrc.setCreatorId(super.getUser().getUserId());
									abrc.setFeeSubjectId(feeObj.getFeeSubjectId());
									abrc.setGlobalBatchId(stu.getGlobalBatch());
									abrc.setIsYearCount(Constants.ACADEMY_REBATE_IS_YEAR_COUNT_FALSE);
									abrc.setRebateCount(feeObj.getAcademyRebateCount());
									abrc.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
									abrc.setUpdaterId(super.getUser().getUserId());
									abrclist.add(abrc);
								}	
								//追加记录
								if(abrct!=null && abrct.getRebateCount()<feeObj.getAcademyRebateCount() && payAcademyCedu.getAddPaied().compareTo(new BigDecimal(0))>0)
								{
									AcademyRebatePolicyDetailStandard arpdone=null;
									AcademyRebatePolicyDetailStandard arpdtwo=null;
									BigDecimal countone=new BigDecimal(0);
									BigDecimal counttwo=new BigDecimal(0);
									BigDecimal money=new BigDecimal(0);//金额
									//计算追加金额     按批次和院校、费用科目计算
									List<FeePaymentDetail> oldfpdlist=fpdBiz.findfpdListByAcademyIdBatchIdFeeSubjectIdForAcademyRebateAddMoney(stu,feeObj.getFeeSubjectId());
									if(oldfpdlist!=null && oldfpdlist.size()>0)
									{
										for(FeePaymentDetail feep:oldfpdlist)
										{
											arpdone=arpdsBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,feep.getFeeSubjectId(),abrct.getRebateCount());
											arpdtwo=arpdsBiz.findAcademyRebateReviewStandardByStudentIdFeeSubjectIdCount(stu,feep.getFeeSubjectId(),feeObj.getAcademyRebateCount());
											if(arpdone.getId()!=arpdtwo.getId())
											{
												//基数  由于退费，所以要乘以一个基数
												BigDecimal jishu=new BigDecimal(1);
												if((new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
												{
													jishu=(new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount())).subtract(new BigDecimal(feep.getRefundAmount()))).divide(new BigDecimal(feep.getAmountPaied()).add(new BigDecimal(feep.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
												}						
												money=(new BigDecimal(feep.getMoneyToPay()).subtract(new BigDecimal(feep.getAcademyCeduDiscount()))).multiply(jishu);				
												//第一个标准计算金额
												if(arpdone.getValueForm()==Constants.MONEY_FORM_RATIO)
												{
													countone=money.multiply(arpdone.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
												}
												else if(arpdone.getValueForm()==Constants.MONEY_FORM_AMOUNT)
												{
													countone=arpdone.getValue();
												}
												//第二个标准计算金额
												if(arpdtwo.getValueForm()==Constants.MONEY_FORM_RATIO)
												{
													counttwo=money.multiply(arpdtwo.getValue()).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP);
												}
												else if(arpdtwo.getValueForm()==Constants.MONEY_FORM_AMOUNT)
												{
													counttwo=arpdtwo.getValue();
												}
												//比较计算的金额是否大于0
												if(((counttwo.subtract(countone)).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
												{
													feep.setAcademyRebateAddMoney(feep.getAcademyRebateAddMoney().add(counttwo.subtract(countone)));
													feep.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
													feep.setUpdaterId(super.getUser().getUserId());
													feeplist.add(feep);
													//院校返款追加记录
													AcademyRebateAddRecords arar=new AcademyRebateAddRecords();
													arar.setAddMoney(counttwo.subtract(countone));
													arar.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
													arar.setCreatorId(super.getUser().getUserId());
													arar.setFeePaymentDetailId(feep.getId());
													arar.setPayAcademyCeduAddId(feep.getOrderAcademyCeduId());
													arar.setStudentId(feep.getStudentId());
													arar.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
													arar.setUpdaterId(super.getUser().getUserId());
													ararlist.add(arar);
												}
											}
										}
									}
								}
								abamString+=stu.getAcademyId()+"#"+stu.getEnrollmentBatchId()+"#"+feeObj.getFeeSubjectId();
							}
						}
						
					}
				}
			}
			//应收学校款
			List<PlanedAcademyBill> pablist=new ArrayList<PlanedAcademyBill>();
			if(planedAcademyBillIds!=null && !planedAcademyBillIds.equals(""))
			{
				String[] pbIds=planedAcademyBillIds.split(",");
				for(int i=0;i<pbIds.length;i++)					
				{
					PlanedAcademyBill pab=this.pabBiz.findById(Integer.parseInt(pbIds[i]));
					if(pab!=null)
					{
						pab.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						pab.setUpdaterId(super.getUser().getUserId());
						pab.setIsRebate(Constants.FEE_PAYMENT_CHANNEL_REBATE_TRUE);
						pablist.add(pab);
					}
				}
			}
			//调整金额分配给学习中心
			List<AcademyRebateFenPeiBranch> arfpblist=new ArrayList<AcademyRebateFenPeiBranch>();
			if(branchtzmoney!=null && !branchtzmoney.equals("") && payAcademyCedu.getAdjustPaied().compareTo(new BigDecimal(0))>0)
			{
				String[] btzm=branchtzmoney.split(",");
				for(int i=0;i<btzm.length;i++)
				{
					String[] mid=btzm[i].split("#");
					if(mid.length>=2)
					{
						AcademyRebateFenPeiBranch arfpb=new AcademyRebateFenPeiBranch();
						arfpb.setAddMoney(new BigDecimal(mid[1]));
						arfpb.setBranchId(Integer.valueOf(mid[0]));
						arfpb.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						arfpb.setCreatorId(super.getUser().getUserId());
						arfpb.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						arfpb.setUpdaterId(super.getUser().getUserId());
						arfpblist.add(arfpb);
					}
				}
 			}
			if(feeplist!=null && feeplist.size()>0)
			{
				fpdlist.addAll(feeplist);
			}
			isback=this.payAcademyCeduBiz.addPayAcademyCeduReview(payAcademyCedu, fpdlist, pablist,arfpblist,abrclist,ararlist);
		}
		return SUCCESS;
	}

	public PayAcademyCedu getPayAcademyCedu() {
		return payAcademyCedu;
	}


	public void setPayAcademyCedu(PayAcademyCedu payAcademyCedu) {
		this.payAcademyCedu = payAcademyCedu;
	}


	public String getPlanedAcademyBillIds() {
		return planedAcademyBillIds;
	}


	public void setPlanedAcademyBillIds(String planedAcademyBillIds) {
		this.planedAcademyBillIds = planedAcademyBillIds;
	}


	public boolean isIsback() {
		return isback;
	}


	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public String getNewNeedFpdIds() {
		return newNeedFpdIds;
	}

	public void setNewNeedFpdIds(String newNeedFpdIds) {
		this.newNeedFpdIds = newNeedFpdIds;
	}

	public String getBranchtzmoney() {
		return branchtzmoney;
	}

	public void setBranchtzmoney(String branchtzmoney) {
		this.branchtzmoney = branchtzmoney;
	}
	
	
}
