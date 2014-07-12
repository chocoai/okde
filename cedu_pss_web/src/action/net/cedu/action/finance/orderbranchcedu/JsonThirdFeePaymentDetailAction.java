package net.cedu.action.finance.orderbranchcedu;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部确认（总部）中的(第三方支付)
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonThirdFeePaymentDetailAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	private Student student=new Student();//学生实体
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细
	
	private String startDate;//缴费时间起	
	private String endDate;//缴费时间止
	private String code;//缴费单号
	
	//统计
	private String allMoney;//统计金额
	
	
	/**
	 * 获取缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_third_feepaymentdetail_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU);
		feePayment.setCode(code);
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result));
		return SUCCESS;
	}
	
	/**
	 * 获取缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_third_feepaymentdetail_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU);	
		feePayment.setCode(code);
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result));
		return SUCCESS;
	}
	
	/**
	 * 统计金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_third_cedu_fpd_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countthirdcedu() throws Exception 
	{	
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU);	
		feePayment.setCode(code);
		allMoney=this.feePaymentDetailBiz.countFpdAllMoneyForPOSEbank(feePaymentDetail, feePayment, student, startDate, endDate);
		return SUCCESS;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAllMoney() {
		//转换金额
		allMoney=MoneyUtil.formatMoney(allMoney);
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}
	

}
