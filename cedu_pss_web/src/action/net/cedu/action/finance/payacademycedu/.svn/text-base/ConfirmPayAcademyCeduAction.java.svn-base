package net.cedu.action.finance.payacademycedu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.biz.finance.StudentAcademyRebateBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.entity.finance.StudentAcademyRebate;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单确认
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json")})
public class ConfirmPayAcademyCeduAction extends BaseAction
{
	private static final long serialVersionUID = -661978686037129120L;

	private int payAcademyCeduId; //院校返款单ID
	
	private boolean isback=false;//页面返回参数
	
	@Autowired
	private PayAcademyCeduBiz payAcademyCeduBiz;
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	@Autowired
	private PlanedAcademyBillBiz pabBiz;//应收学校款_业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	@Autowired
	private StudentAcademyRebateBiz studentAcademyRebateBiz;//学生_业务层接口
	
	
	
	@Override
	public String execute() throws Exception
	{
		if(payAcademyCeduId!=0)
		{
			//返款单实体
			PayAcademyCedu payAcademyCedu = payAcademyCeduBiz.findById(payAcademyCeduId);
			payAcademyCedu.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
			//缴费单明细集合
			List<FeePaymentDetail> fpdList=this.fpdBiz.findFeePaymentDetailListByOrderAcademyCeduId(payAcademyCeduId);
			List<FeePaymentDetail> hislist = new ArrayList<FeePaymentDetail>();//退费单明细集合
			
			//每个学生第一次院校返款时要更新学生信息绑定院校返款标准关系
			List<StudentAcademyRebate> sarlist=new ArrayList<StudentAcademyRebate>();//学生列表
			String stuFeeIds="";
			
			//增加退费情况的处理
			if(fpdList!=null && fpdList.size()>0)
			{
				for(FeePaymentDetail fp:fpdList)
				{
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fp.getId(), Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN, Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
					if(refundlist!=null && refundlist.size()>0)
					{
						
						BigDecimal allrefundmon=new BigDecimal(0);
//						for(FeePaymentDetail rfpd:refundlist)
//						{
//							allrefundmon=allrefundmon.add(new BigDecimal(rfpd.getAmountPaied()));
//							//还原历史数据
//							fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).subtract(new BigDecimal(rfpd.getPayAcademyCedu()))).doubleValue());
//							fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).subtract(new BigDecimal(rfpd.getPayCeduAcademy()))).doubleValue());
//							fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).subtract(new BigDecimal(rfpd.getPaymentByChannel()))).doubleValue());
//							fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).subtract(new BigDecimal(rfpd.getPayBranchCedu()))).doubleValue());
//							
//						}
						FeePaymentDetail rfpd=refundlist.get(0);
						//还原历史数据
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						
						fp.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
						fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).add(new BigDecimal(fp.getPayAcademyCedu()))).doubleValue());
						fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).subtract(new BigDecimal(fp.getPayAcademyCedu()))).doubleValue());
						
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setUpdatedTime(new Date());
						
						//重新计算退费的值
						if(new BigDecimal(rfpd.getRefundBase()).compareTo(new BigDecimal(0))==0)
						{
							rfpd.setRefundBase(1);
						}
						rfpd.setCeduAccount(0-((new BigDecimal(fp.getCeduAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setAcademyAccount(0-((new BigDecimal(fp.getAcademyAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setChannelAccount(0-((new BigDecimal(fp.getChannelAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setBranchAccount(0-((new BigDecimal(fp.getBranchAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setStatus(Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN);
						rfpd.setUpdaterId(super.getUser().getUserId());
						rfpd.setUpdatedTime(new Date());
						hislist.add(rfpd);
						//更新历史缴费单(重新计算各个账户的值)
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fp.setChannelAccount((new BigDecimal(fp.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fp.setBranchAccount((new BigDecimal(fp.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						fp.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
						
					}
					else
					{
						fp.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
						fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fp.setUpdaterId(super.getUser().getUserId());
						fp.setCeduAccount((new BigDecimal(fp.getCeduAccount()).add(new BigDecimal(fp.getPayAcademyCedu()))).doubleValue());
						fp.setAcademyAccount((new BigDecimal(fp.getAcademyAccount()).subtract(new BigDecimal(fp.getPayAcademyCedu()))).doubleValue());
					}
					
					//2012-07-12屏蔽  返款重构不需这个
//					//每个学生第一次院校返款时要更新学生信息绑定院校返款标准
//					//查询该学生是否已经绑定了院校返款标准
//					StudentAcademyRebate sarfind=new StudentAcademyRebate();
//					sarfind.setStudentId(fp.getStudentId());
//					sarfind.setFeeSubjectId(fp.getFeeSubjectId());
//					int count=this.studentAcademyRebateBiz.findStudentAcademyRebateCountBy(sarfind);
//					if(count==0)
//					{
//						if(stuFeeIds!=null && !stuFeeIds.equals(""))
//						{
//							String zuhefpd=","+stuFeeIds+",";
//							if(zuhefpd.indexOf(","+fp.getStudentId()+"#"+fp.getFeeSubjectId()+",")==-1)
//							{
//								sarfind.setAcademyPoliceStandardId(fp.getAcademyPolicyStandardId());
//								sarlist.add(sarfind);
//								stuFeeIds+=","+fp.getStudentId()+"#"+fp.getFeeSubjectId();
//							}
//						}
//						else
//						{
//							sarfind.setAcademyPoliceStandardId(fp.getAcademyPolicyStandardId());
//							sarlist.add(sarfind);
//							stuFeeIds+=fp.getStudentId()+"#"+fp.getFeeSubjectId();
//						}
//					}
				}
			}
			
			//应收学校款
			List<PlanedAcademyBill> pabList=this.pabBiz.findPlanedAcademyBillListByacademyRebateId(payAcademyCeduId);
			//退费单
			//List<FeePaymentDetail> refundlist=null;
			//把退费单和缴费单明细整合一起（防止类似错误）
			if(fpdList!=null && fpdList.size()>0)
			{
				//refundlist=fpdList;
				if(hislist!=null && hislist.size()>0)
				{
					for(FeePaymentDetail rfpd:hislist)
					{
						fpdList.add(rfpd);
					}
				}
			}
			//isback=this.payAcademyCeduBiz.updatePayAcademyCedu(payAcademyCedu, fpdList, pabList);
			//isback=this.payAcademyCeduBiz.updatePayAcademyCeduForRefund(payAcademyCedu, fpdList, hislist, pabList);//加上退费功能
			isback=this.payAcademyCeduBiz.updatePayAcademyCeduForRefund(payAcademyCedu, fpdList, hislist, pabList,sarlist);//加上返款标准的绑定
		}
		return SUCCESS;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}

	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

}
