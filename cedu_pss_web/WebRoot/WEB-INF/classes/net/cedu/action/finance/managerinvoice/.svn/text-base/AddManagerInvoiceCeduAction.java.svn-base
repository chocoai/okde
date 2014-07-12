package net.cedu.action.finance.managerinvoice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.InvoiceManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登记发票
 * 
 * @author gaolei
 * 
 */

@Results( {
	@Result(name = "input", location = "/WEB-INF/content/finance/managerinvoice/add_manager_invoice_cedu.jsp"),
	@Result(name = "cedu_success",type="redirect", location = "list_manager_invoice_cedu?tab=1"),
	@Result(name = "branch_success",type="redirect", location = "list_manager_invoice_center_sign_false?tab=2")
})
public class AddManagerInvoiceCeduAction extends BaseAction {

	
	
	@Autowired
	private BranchBiz branchbiz;                   //学习中心Biz
	@Autowired
	private StudentBiz studentBiz;

	@Autowired
	private InvoiceManagementBiz inovicemanagementbiz;    //发票管理Biz
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	
	
	
	private List<Branch> branchlst=new ArrayList<Branch>();//学习中心数据
	
	private String invoiceCode;      //发票号
	private Date  issuedTime;        //开票日期
	private int branchId;            //学习中心
	private int studentId;           //学生ID
	private Object[] chk;            //缴费单Id
	
	
	@Override
	public String execute() throws Exception
	{
		if(super.isGetRequest())
		{
			return INPUT;	
		}
		//验证重复
		InvoiceManagement invoiceManagement=null;
		invoiceManagement=inovicemanagementbiz.findInvoiceManagementByInvoiceCode(invoiceCode);
		
		if(invoiceManagement!=null){
			return INPUT;
		}else{
			invoiceManagement=new InvoiceManagement();
		}
		
		invoiceManagement.setInvoiceCode(invoiceCode);
		invoiceManagement.setIssuedTime(issuedTime);
		
		//invoiceManagement.setBranchId(branchId);修复bug
		Student student=studentBiz.findStudentById(studentId);
		if(student!=null){
			invoiceManagement.setBranchId(student.getBranchId());
		}else{
			return INPUT;
		}
		invoiceManagement.setStudentId(studentId);
		if(chk!=null)
		{
			invoiceManagement.setFeePaymentDetailId(StringUtil.strObjects(chk));
			Object[] obj=StringUtil.strToObject(invoiceManagement.getFeePaymentDetailId());
			String ids=",";
			//循环缴费单ID
			for(int j=0;j<obj.length;j++)
			{
				FeePaymentDetail feepaymentdetail=feePaymentDetailBiz.findFeePaymentDetailByFeePaymentId(Integer.valueOf(obj[j].toString()));
				if(feepaymentdetail!=null){
					if(ids.equals(",")){
						ids=""+feepaymentdetail.getFeePaymentId();
					}else{
						ids+=","+feepaymentdetail.getFeePaymentId();
					}
					
				}
			}
			if(ids.equals(",")){
				ids="0";
			}
			List<FeePayment> feePaymentList=feePaymentBiz.findFeePaymentByIds(ids);
			if(feePaymentList!=null){
				String feePaymentCodes="";
				for (FeePayment feePayment : feePaymentList) {
					feePaymentCodes+="#"+feePayment.getCode();
				}
				feePaymentCodes+="#";
				//缴费单号
				invoiceManagement.setFeePaymentCode(feePaymentCodes);
			}
			
		}
		
		
		
		invoiceManagement.setCreatorId(super.getUser().getUserId());
		invoiceManagement.setCreatedTime(new Date());
		//登记发票
		//inovicemanagementbiz.addInvoiceManagement(invoiceManagement);
		//总部
		if(super.getUser().getOrgId()==BranchEnum.Admin.value()){
			invoiceManagement.setRegistrationInvoiceType(1);
			
			//登记发票
			inovicemanagementbiz.addInvoiceManagement(invoiceManagement);
			return "cedu_"+SUCCESS;	
		}else{
			//学习中心
			//登记发票
			invoiceManagement.setRegistrationInvoiceType(2);
			//已配送
			invoiceManagement.setIsPost(1);
			inovicemanagementbiz.addInvoiceManagement(invoiceManagement);
			return "branch_"+SUCCESS;	
		}
		
	}

	public List<Branch> getBranchlst() {
		return branchlst;
	}

	public void setBranchlst(List<Branch> branchlst) {
		this.branchlst = branchlst;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public Date getIssuedTime() {
		return issuedTime;
	}

	public void setIssuedTime(Date issuedTime) {
		this.issuedTime = issuedTime;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public Object[] getChk() {
		return chk;
	}

	public void setChk(Object[] chk) {
		this.chk = chk;
	}


	

	

	
	

	
	
}
