package net.cedu.action.finance.refund;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.RefundCeduAcademyBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.RefundCeduAcademy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonListRefundCeduAcademyAction extends BaseAction {
	private int status=0;//确认状态
	private int refundCeduAcademyId = 0;//总部替院校垫付退费id
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//层次_业务层接口
	
	// 分页结果
	private PageResult<RefundCeduAcademy> result = new PageResult<RefundCeduAcademy>();
	//分页参数
	private RefundCeduAcademy refundCeduAcademy = new RefundCeduAcademy();//总部替院校垫付退费
	private Student student = new Student();//学生信息
	private FeePayment feePayment = new FeePayment();//退费单
	private String starttime = "";//开始时间
	private String endtime = "";//结束时间
	
	@Autowired
	private RefundCeduAcademyBiz refundCeduAcademyBiz;
	
	//返回值
	private boolean isback=false;
	//退费总额
	private String allRefundPaymentMoney = "0";

	/**
	 * 总部替院校垫付退费集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_refund_payment_cedu_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String listRefundPaymentCeduAcademy() throws Exception 
	{	
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			//中心(总部替院校垫付退费的院校和学生院校一样)
			refundCeduAcademy.setAcademyId(student.getAcademyId());
		}
		result.setList(refundCeduAcademyBiz.findRefundCeduAcademyPageList(refundCeduAcademy, student, feePayment, starttime, endtime, result));
		return SUCCESS;
	}
	
	/**
	 * 总部替院校垫付退费数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_refund_payment_cedu_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countRefundPaymentCeduAcademy() throws Exception 
	{	
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			//中心(总部替院校垫付退费的院校和学生院校一样)
			refundCeduAcademy.setAcademyId(student.getAcademyId());
		}
		result.setRecordCount(refundCeduAcademyBiz.findRefundCeduAcademyPageCount(refundCeduAcademy, student, feePayment, starttime, endtime, result));
		return SUCCESS;
	}
	
	/**
	 * 总部替院校垫付退费总退费金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "sum_refund_payment_cedu_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String sumRefundPaymentCeduAcademy() throws Exception 
	{	
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academyLevelBiz.findById(student.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
			//中心(总部替院校垫付退费的院校和学生院校一样)
			refundCeduAcademy.setAcademyId(student.getAcademyId());
		}
		allRefundPaymentMoney = String.valueOf(refundCeduAcademyBiz.findPaymentSum(refundCeduAcademy, student, feePayment, starttime, endtime));
		return SUCCESS;
	}
	
	/**
	 * 修改总部替院校垫付退费状态
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_refund_payment_cedu_academy_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updateRefundPaymentCeduAcademy() throws Exception 
	{	
		if(refundCeduAcademyId!=0 && status!=0)
		{
			RefundCeduAcademy old = refundCeduAcademyBiz.findRefundCeduAcademyById(refundCeduAcademyId);
			//状态判断（避免多人同时操作BUG）
			if(old!=null && old.getStatus()<status)
			{
				old.setStatus(status);
				old.setUpdaterId(super.getUser().getUserId());
				old.setUpdatedTime(DateUtil.getNow());
				isback = refundCeduAcademyBiz.updateRefundCeduAcademy(old);
			}
		}
		return SUCCESS;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PageResult<RefundCeduAcademy> getResult() {
		return result;
	}

	public void setResult(PageResult<RefundCeduAcademy> result) {
		this.result = result;
	}

	public RefundCeduAcademy getRefundCeduAcademy() {
		return refundCeduAcademy;
	}

	public void setRefundCeduAcademy(RefundCeduAcademy refundCeduAcademy) {
		this.refundCeduAcademy = refundCeduAcademy;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
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

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public String getAllRefundPaymentMoney() {
		allRefundPaymentMoney=MoneyUtil.formatMoney(allRefundPaymentMoney);
		return allRefundPaymentMoney;
	}

	public void setAllRefundPaymentMoney(String allRefundPaymentMoney) {
		this.allRefundPaymentMoney = allRefundPaymentMoney;
	}

	public int getRefundCeduAcademyId() {
		return refundCeduAcademyId;
	}

	public void setRefundCeduAcademyId(int refundCeduAcademyId) {
		this.refundCeduAcademyId = refundCeduAcademyId;
	}
}
