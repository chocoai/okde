package net.cedu.action.finance.managerinvoice;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.InvoiceManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询收据详情
 * 
 * @author gaolei
 * 
 */

public class ViewManagementInvoiceAction extends BaseAction {

	
	@Autowired
	private BranchBiz branchbiz;              //学习中心Biz
	
	@Autowired
	private StudentBiz studentbiz;              //学生Biz
	
	@Autowired
	private InvoiceManagementBiz invoicemanagementbiz;              //发票Biz
	
	private InvoiceManagement invoicemanagement;                    //发票实体
	private int id;                                                 //发票ID
	
	@Action(results = { @Result(name = "success", location = "view_management_invoice.jsp")	
	})
	public String excute() throws Exception
	{
		invoicemanagement=invoicemanagementbiz.findInvoiceManagementById(id);
		Branch branch=new Branch();
		Student student=new Student();
		if(invoicemanagement!=null)
		{
			branch=branchbiz.findBranchById(invoicemanagement.getBranchId());
			student=studentbiz.findStudentById(invoicemanagement.getStudentId());
		}
		if(branch!=null)
		{
			invoicemanagement.setBranchName(branch.getName());
		}
		if(student!=null)
		{
			invoicemanagement.setStudentName(student.getName());
		}
		
		return SUCCESS;	
	}

	public InvoiceManagement getInvoicemanagement() {
		return invoicemanagement;
	}

	public void setInvoicemanagement(InvoiceManagement invoicemanagement) {
		this.invoicemanagement = invoicemanagement;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
