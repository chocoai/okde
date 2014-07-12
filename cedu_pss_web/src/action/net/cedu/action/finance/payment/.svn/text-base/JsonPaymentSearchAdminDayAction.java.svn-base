package net.cedu.action.finance.payment;

import java.math.BigDecimal;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 日收款单查询ajax
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonPaymentSearchAdminDayAction extends BaseAction
{
	@Autowired
	private StudentAccountAmountManagementBiz saamBiz;    //学生账户biz
	@Autowired
	private TaskBiz taskBiz;
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private ChannelBiz channelBiz;
	
	//查询 条件
	private Student student=new Student();//学生实体
	private String starttime; //开始时间
	private String endtime; //结束时间
	private String feeSubjectIds;//缴费科目Ids字符串
	private int status;//缴费单明细状态
	private String ccStartTime;//总部确认时间
	private int feeWayId;//缴费方式
	private String allStuFpdMoney;//统计学生缴费单明细金额
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private int levelId;//层次
	
	
	/**
	 * 学生缴费单明细数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_search_admin_day_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,student, starttime, endtime,feeSubjectIds,status,ccStartTime,ccStartTime"
	}) })
	public String countfpdsAjax() throws Exception {
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		result.setRecordCount(saamBiz.findFeePaymentDetailCountByPageForSearch(student, starttime, endtime,feeSubjectIds,status,ccStartTime,ccStartTime,feeWayId));
		return SUCCESS;
	}
	
	/**
	 * 学生缴费单明细集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_payment_search_admin_day_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String listfpdsAjax() throws Exception {
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		result.setList(saamBiz.findFeePaymentDetailListByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccStartTime,feeWayId, result));
		return SUCCESS;
	}
	
	
	/**
	 * 统计学生缴费充值金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_search_admin_day_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countFpdallCountAllMoney() throws Exception {
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		allStuFpdMoney=this.saamBiz.countStudentfpdMoneyForCeduConfirmDay(student, starttime, endtime, feeSubjectIds, status,ccStartTime,ccStartTime);
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

	public String getFeeSubjectIds() {
		return feeSubjectIds;
	}

	public void setFeeSubjectIds(String feeSubjectIds) {
		this.feeSubjectIds = feeSubjectIds;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCcStartTime() {
		return ccStartTime;
	}

	public void setCcStartTime(String ccStartTime) {
		this.ccStartTime = ccStartTime;
	}

	public String getAllStuFpdMoney() {
		//转换金额
		allStuFpdMoney=MoneyUtil.formatMoney(allStuFpdMoney);
		return allStuFpdMoney;
	}

	public void setAllStuFpdMoney(String allStuFpdMoney) {
		this.allStuFpdMoney = allStuFpdMoney;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getFeeWayId() {
		return feeWayId;
	}

	public void setFeeWayId(int feeWayId) {
		this.feeWayId = feeWayId;
	}
	
	
}
