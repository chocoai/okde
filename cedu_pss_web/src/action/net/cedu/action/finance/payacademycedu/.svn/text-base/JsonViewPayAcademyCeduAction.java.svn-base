package net.cedu.action.finance.payacademycedu;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款单详细
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonViewPayAcademyCeduAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	//查询条件
	private int payAcademyCeduId;//院校返款单Id
	
	
	/**
	 * 获取院校返款单的缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "view_academy_rebate_count_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setOrderAcademyCeduId(payAcademyCeduId);	
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPageForViewAcademyRebate(feePaymentDetail, result));
		return SUCCESS;
	}

	/**
	 * 获取院校返款单的缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "view_academy_rebate_list_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setOrderAcademyCeduId(payAcademyCeduId);
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPageForViewAcademyRebate(feePaymentDetail,result));
		return SUCCESS;
	}
	

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}

	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
	}
	
}
