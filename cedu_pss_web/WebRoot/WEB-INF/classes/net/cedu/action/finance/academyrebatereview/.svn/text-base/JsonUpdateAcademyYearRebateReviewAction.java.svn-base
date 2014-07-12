package net.cedu.action.finance.academyrebatereview;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.biz.finance.AcademyRebateAddRecordsBiz;
import net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校年度返款单
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonUpdateAcademyYearRebateReviewAction extends BaseAction
{
	
	@Autowired 
	private PayAcademyCeduBiz payAcademyCeduBiz;//院校返款_业务层接口
	
	private int payAcademyCeduId;//院校返款单Id
	
	private boolean isback=false;//返回参数
	
	/**
	 * 更新院校年度返款单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "confirm_academy_year_rebate_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String darca() throws Exception 
	{
		if(payAcademyCeduId!=0)
		{
			//返款单实体			
			PayAcademyCedu payAcademyCedu = payAcademyCeduBiz.findById(payAcademyCeduId);
			payAcademyCedu.setStatus(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN);
			payAcademyCedu.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payAcademyCedu.setUpdaterId(super.getUser().getUserId());
			isback=this.payAcademyCeduBiz.updatePayAcademyCedu(payAcademyCedu);
			
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
