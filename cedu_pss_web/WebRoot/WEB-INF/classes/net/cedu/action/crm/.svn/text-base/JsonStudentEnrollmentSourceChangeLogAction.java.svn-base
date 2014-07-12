package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentEnrollmentSourceChangeLogBiz;
import net.cedu.entity.crm.StudentEnrollmentSourceChangeLog;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonStudentEnrollmentSourceChangeLogAction extends BaseAction {

	@Autowired
	private StudentEnrollmentSourceChangeLogBiz studentEnrollmentSourceChangeLogBiz;
	/**
	 * 查询条件
	 */
	private StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog;

	// 分页结果
	private PageResult<StudentEnrollmentSourceChangeLog> result = new PageResult<StudentEnrollmentSourceChangeLog>();

	@Action(value = "crm_student_enrollment_source_change_log_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmStudentEnrollmentSourceChangeLogCount() throws Exception {
		// 查询数量
		result.setRecordCount(studentEnrollmentSourceChangeLogBiz
				.findStudentEnrollmentSourceChangeLogsPageCount(
						studentEnrollmentSourceChangeLog, result));
		return SUCCESS;
	}
	@Action(value = "crm_student_enrollment_source_change_log_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmStudentEnrollmentSourceChangeLogList() throws Exception {

		result.setList(studentEnrollmentSourceChangeLogBiz
				.findStudentEnrollmentSourceChangeLogsPageList(
						studentEnrollmentSourceChangeLog, result));
		return SUCCESS;
	}

	public StudentEnrollmentSourceChangeLog getStudentEnrollmentSourceChangeLog() {
		return studentEnrollmentSourceChangeLog;
	}

	public void setStudentEnrollmentSourceChangeLog(
			StudentEnrollmentSourceChangeLog studentEnrollmentSourceChangeLog) {
		this.studentEnrollmentSourceChangeLog = studentEnrollmentSourceChangeLog;
	}

	public PageResult<StudentEnrollmentSourceChangeLog> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentEnrollmentSourceChangeLog> result) {
		this.result = result;
	}

}
