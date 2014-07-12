package net.cedu.action.finance.studentaccountmanagement;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json学生账户管理
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonStudentAccountManagementAction extends BaseAction {

	
	@Autowired
	private StudentAccountManagementBiz studentaccountmanagementbiz;    //学生账户biz
	// 分页结果
	private PageResult<StudentAccountManagement> result = new PageResult<StudentAccountManagement>();
	private int id;               //学生账户Id
	private String code;          //学生账号
	private String studentName;   //学生姓名 
	private String certNo;        //学生身份证
	private int usingStatus;      //启用状态
	private boolean errorbol;     //错误异常
	private int count;            //创建学生账户数量
	/**
	 * 学生账户集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "liststudentaccountmanagement", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListStudentAccountManagement() throws Exception {
		result.setList(studentaccountmanagementbiz.findStudentAccountManagementByParams(code, studentName, certNo, result));
		return SUCCESS;
	}
	
	/**
	 * 学生账户集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countstudentaccountmanagement", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String CountStudentAccountManagement() throws Exception {
		result.setRecordCount(studentaccountmanagementbiz.countStudentAccountManagementByParams(code, studentName, certNo, result));
		return SUCCESS;
	}
	
	/**
	 * 删除学生账户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deletestudentaccountmanagement", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String DeleteStudentAccountManagement() throws Exception {
		
		studentaccountmanagementbiz.deleteStudentAccountManagementById(id);
		return SUCCESS;
	}
	
	/**
	 * 批量创建学生账户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "addstudentaccounts", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddStudentAccounts() throws Exception {
		
		try {
			count=studentaccountmanagementbiz.addStudentAccounts(super.getUser().getUserId());
			errorbol=true;
		} catch (Exception e) {
			e.printStackTrace();
			errorbol=false;
		}
		return SUCCESS;
	}
	
	
	/**
	 * 修改学生账户启用状态
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatestudentaccountmanagement", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateStudentAccountManagement() throws Exception {
		
		StudentAccountManagement sam=studentaccountmanagementbiz.findStudentAccountManagementById(id);
		sam.setUsingStatus(usingStatus);
		sam.setUpdaterId(super.getUser().getUserId());
		sam.setUpdatedTime(new Date());
		studentaccountmanagementbiz.updateStudentAccountManagementById(sam);
		return SUCCESS;
	}
	
	public PageResult<StudentAccountManagement> getResult() {
		return result;
	}
	public void setResult(PageResult<StudentAccountManagement> result) {
		this.result = result;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUsingStatus() {
		return usingStatus;
	}

	public void setUsingStatus(int usingStatus) {
		this.usingStatus = usingStatus;
	}

	public boolean isErrorbol() {
		return errorbol;
	}

	public void setErrorbol(boolean errorbol) {
		this.errorbol = errorbol;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	

}
