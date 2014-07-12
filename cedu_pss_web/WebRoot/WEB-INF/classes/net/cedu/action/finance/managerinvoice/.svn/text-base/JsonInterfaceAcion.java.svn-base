/**
 * 文件名：JsonInterfaceAcion.java
 * 包名：net.cedu.action.finance.managerinvoice
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-17 上午10:31:32
 *
 */
package net.cedu.action.finance.managerinvoice;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.InvoiceManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonInterfaceAcion extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	
	@Autowired
	private InvoiceManagementBiz invoiceManagementBiz;    //发票管理Biz
	private String studentIds = "0";
	private Student student;
	private String invoiceCode;//发票号
	private boolean result;//

	@Action(value = "search_student_ids_by_student_params", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String searchStudentIdsByStudentParams() throws Exception {
		studentIds = studentBiz.findStudentIdsByStudentParams(student);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @功能：是否存在发票号
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-17 下午03:07:30
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return
	 * @throws Exception
	 */
	@Action(value = "is_exist_invoicen_number", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String isExistInvoicenNumber() throws Exception {
		//验证重复
		InvoiceManagement invoiceManagement=invoiceManagement=invoiceManagementBiz.findInvoiceManagementByInvoiceCode(invoiceCode);
		if(invoiceManagement==null){
			result=false;
		}else{
			result=true;
		}
		
		return SUCCESS;
	}

	public String getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
