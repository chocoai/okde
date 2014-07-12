package net.cedu.action.finance.payacademycedu;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonListPayAcademyCeduAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	
	@Autowired
	private FeePaymentDetailBiz fpdBiz;//缴费单明细_业务层接口
	
	@Autowired
	private PlanedAcademyBillBiz pabBiz;//应收学校款_业务层接口
	
	private int payAcademyCeduId;//院校返款单Id
	private boolean isback=false;//页面返回参数
	
	
	/**
	 * 删除院校返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_academy_rebate_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String darca() throws Exception 
	{
		if(payAcademyCeduId!=0)
		{
			List<FeePaymentDetail> fpdList=this.fpdBiz.findFeePaymentDetailListByOrderAcademyCeduId(payAcademyCeduId);
			List<PlanedAcademyBill> pabList=this.pabBiz.findPlanedAcademyBillListByacademyRebateId(payAcademyCeduId);
			isback=this.payAcademyCeduBiz.deletePayAcademyCedu(payAcademyCeduId, fpdList, pabList);
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
