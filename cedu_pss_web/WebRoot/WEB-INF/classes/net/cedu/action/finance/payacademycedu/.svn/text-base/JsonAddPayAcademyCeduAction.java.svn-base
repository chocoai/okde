package net.cedu.action.finance.payacademycedu;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
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
public class JsonAddPayAcademyCeduAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz arpdsBiz;//院校返款标准明细_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	@Autowired
	private PlanedAcademyBillBiz pabBiz;//应收学校款_业务层接口

	private PayAcademyCedu payAcademyCedu=new PayAcademyCedu();//院校返款实体
	private String feepdIds;//缴费单明细ids字符串
	private String planedAcademyBillIds;//应收学校款Ids字符串
	private boolean isback=false;
	
	
	/**
	 * 添加院校返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_academy_rebate_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
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
			
			//缴费单明细
			List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();
			if(feepdIds!=null && !feepdIds.equals(""))
			{
				//BigDecimal paymentAmount =new BigDecimal(0);//统计缴费单明细返款金额
				String[] fpdIds=feepdIds.split(",");
				for(int i=0;i<fpdIds.length;i++)
				{
					FeePaymentDetail feeObj=this.fpdBiz.findById(Integer.parseInt(fpdIds[i]));
					if(feeObj!=null)
					{
						//feeObj.setPayAcademyCedu(arpdsBiz.findAcademyRebateByfeePaymentDetailId(feeObj.getId()));
						feeObj.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						feeObj.setUpdaterId(super.getUser().getUserId());
						feeObj.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);
						fpdlist.add(feeObj);
						//paymentAmount=paymentAmount.add(new BigDecimal(feeObj.getPayAcademyCedu()));
					}
				}
				//payAcademyCedu.setPaymentAmount(paymentAmount);
			}
			//应收学校款
			List<PlanedAcademyBill> pablist=new ArrayList<PlanedAcademyBill>();
			if(planedAcademyBillIds!=null && !planedAcademyBillIds.equals(""))
			{
				//BigDecimal academyAmount=new BigDecimal(0);//统计应收学校款
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
						//academyAmount=academyAmount.add(pab.getAmount());
					}
				}
				//payAcademyCedu.setAcademyAmount(academyAmount);
			}
			isback=this.payAcademyCeduBiz.addPayAcademyCedu(payAcademyCedu, fpdlist, pablist);
		}
		return SUCCESS;
	}

	public PayAcademyCedu getPayAcademyCedu() {
		return payAcademyCedu;
	}


	public void setPayAcademyCedu(PayAcademyCedu payAcademyCedu) {
		this.payAcademyCedu = payAcademyCedu;
	}


	public String getFeepdIds() {
		return feepdIds;
	}


	public void setFeepdIds(String feepdIds) {
		this.feepdIds = feepdIds;
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
	
	
}
