package net.cedu.action.finance.academybill;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.entity.finance.PlanedAcademyBill;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 下载应收院校款
 * 
 * @author Sauntor
 *
 */
@Results({
	@Result(name="success", type="stream", params={"inputName", "file", "contentDisposition", "attachment;filename=${fileName}"}),
	@Result(name="error", type="dispatcher", location="download_academy_bill_attachment_error.jsp")
})
public class DownloadAcademyBillAttachmentAction extends BaseAction
{
	private static final long serialVersionUID = 4200585665684398902L;

	private int billId;
	
	private PlanedAcademyBill planedAcademyBill;
	
	private File attachment;
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademyBillBiz;
	
	public String execute() throws Exception
	{
		planedAcademyBill = planedAcademyBillBiz.findById(billId);
		
		String path = ServletActionContext.getServletContext().getRealPath("/"+planedAcademyBill.getAttachmentPath());
		attachment = new File(path);
		
		if(!attachment.exists()){
			System.err.println("文件不存在");
			
			return ERROR;
		}
	
		return SUCCESS;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}
	
	public InputStream getFile() throws Exception
	{
		if(attachment != null){
			return new FileInputStream(attachment);
		}
		
		return null;
	}
	
	public String getFileName(){
		return attachment.getName();
	}
}
