package net.cedu.action.finance.managerinvoice;

import java.io.File;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.InvoiceManagementBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.finance.InvoiceManagement;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 上传签领扫描件
 * 
 * @author gaolei
 * 
 */
public class UploadInvoiceManagementAttachmentAction extends BaseAction {

	@Autowired
	private InvoiceManagementBiz invoiceManagementbiz;   //发票biz
	private int invoiceId;                       //发票ID
	private String title;                        //附件标题
	private File files;                          //文件 
	private String filesFileName;                //文件名称
	private String savePath;                     //服务器路径
	private int tab;                             //页签
	@Action(results = { @Result(name = "success", type="redirect",location = "list_manager_invoice_center_sign_true?tab=${tab}")
	})
	public String excute() throws Exception
	{
		
		InvoiceManagement im=invoiceManagementbiz.findInvoiceManagementById(invoiceId);
		if(im!=null)
		{
			im.setAttachmentName(title);
			im.setAttachmentPath(uploadFile(filesFileName));
			im.setUpdaterId(super.getUser().getUserId());
			im.setUpdatedTime(new Date());
			invoiceManagementbiz.updateInvoiceManagement(im);
		}
		tab=3;
		
		return SUCCESS;
	}
	
	
	/**
	 * 附件上传_返回
	 * @param name 实际文件名
	 * @return	存储的相对路径
	 */
	private String uploadFile(String name)
	{
		try 
		{
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("finance","uploadattachment"));
			return FileUtil.FileUploads(savePath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	public String getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	
}
