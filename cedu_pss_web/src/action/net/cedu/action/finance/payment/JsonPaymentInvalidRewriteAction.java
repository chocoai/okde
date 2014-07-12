package net.cedu.action.finance.payment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单作废
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonPaymentInvalidRewriteAction extends BaseAction
{
	
	@Autowired
	private PaymentBiz paymentBiz;//缴费单业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单业务层接口
	@Autowired
	private AcademyLevelBiz academylevelBiz;//院校层次关系_业务层接口

	// 分页结果
	private PageResult<FeePayment> result = new PageResult<FeePayment>();

	// 查询条件
	private FeePayment feePayment; //缴费单实体
	private Student student; //学生实体
	private String feemoney; //缴费金额
	private String starttime; //开始时间
	private String endtime; //结束时间
	
	//缴费单作废参数
	private int feePaymentId;//缴费单Id
	private int count=0;//返回参数
	
	//缴费单修改缴费日期
	private int fpId;//缴费单Id
	private String createdTime;//缴费日期
	private int index=0;//返回参数
	
	//释放收据号
	private boolean isback=false;
	
	
	
	/**
	 * 缴费单数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_invalid_rewrite_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String paymentInvalidRewriteCount() throws Exception
	{
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询数量
		result.setRecordCount(paymentBiz.findFeePaymentCountBySearch(feePayment, student, feemoney, starttime, endtime));
		return SUCCESS;
	}

	/**
	 * 缴费单集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_payment_invalid_rewrite_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String paymentInvalidRewriteList() throws Exception 
	{
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询集合
		result.setList(paymentBiz.findFeePaymentListBySearch(feePayment, student, feemoney, starttime, endtime, result));
		return SUCCESS;
	}
	
	
	/**
	 * 缴费单作废
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_payment_invalid_rewrite_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String paymentInvalidRewriteUpdate() throws Exception 
	{
		feePayment = this.feePaymentBiz.findFeePaymentById(feePaymentId);
		if(feePaymentId>0 && feePayment!=null)
		{	
			if(feePayment.getPamentType()==Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE)
			{
				count=this.paymentBiz.updateFeePaymentInvalidStatusRewrite(feePaymentId);
			}
			else if(feePayment.getPamentType()==Constants.FEE_PAYMENT_TYPE_PRE_BILLING)
			{
				count=this.paymentBiz.updateFeePaymentInvalidStatusRewriteYuJiaoFei(feePaymentId);
			}
			else
			{
				count=3;
			}
		}
		else
		{
			count=3;
		}
		return SUCCESS;
	}
	
	/**
	 * 缴费单修改缴费日期
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_payment_created_time_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String paymentCreatedTimeUpdate() throws Exception 
	{
		if(fpId>0 && createdTime!=null && !createdTime.equals(""))
		{	
			index=this.feePaymentBiz.updateFeePaymentCreatedTime(fpId, createdTime, super.getUser().getUserId());
		}
		else
		{
			index=3;
		}
		return SUCCESS;
	}
	
	/**
	 * 释放缴费单收据号
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_payment_bar_code_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String paymentBarCodeUpdate() throws Exception 
	{
		if(fpId>0)
		{	
			FeePayment fp=this.feePaymentBiz.findFeePaymentById(fpId);
			if(fp!=null && fp.getStatus()==Constants.PAYMENT_STATUS_ZUO_FEI && fp.getBarCode()!=null && !fp.getBarCode().equals(""))
			{
				fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				fp.setUpdaterId(super.getUser().getUserId());
				isback=this.paymentBiz.updateFpdBarCode(fp);
			}
		}
		
		return SUCCESS;
	}
	
	public PageResult<FeePayment> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePayment> result) {
		this.result = result;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getFeemoney() {
		return feemoney;
	}

	public void setFeemoney(String feemoney) {
		this.feemoney = feemoney;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getFpId() {
		return fpId;
	}

	public void setFpId(int fpId) {
		this.fpId = fpId;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
}
