package net.cedu.action.finance.playmoney;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.finance.PayCeduAcademy;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校打款单（上传单据）
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonUploadPlaymoneyAcademyAction extends BaseAction
{
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;//打款单业务接口
	
	private int orderId;
	private Date remittanceDate;
	private String orderNo;
	private int errno = 0;
	private File orderImage;
	private String orderImageFileName;
	
	/**
	 * 上传单据号
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upload_order_palymoney_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/html" }) })
	public String playmoneyCount() throws Exception 
	{
		PayCeduAcademy pca=this.payCeduAcademyBiz.findById(orderId);
		pca.setRemittanceNo(orderNo);
		pca.setRemittanceDate(remittanceDate);	
		if(orderImage!=null)
		{
			try {
				String imageUrl = saveImage();
					
				pca.setUploadedBillPath(imageUrl);
			} catch (Exception e) {	
				errno = -2;
				e.printStackTrace();
			}
		}
		pca.setUpdaterId(super.getUser().getUserId());
		pca.setUpdatedTime(new Date());
		this.payCeduAcademyBiz.updateById(pca);
		return SUCCESS;
	}
	
	private String saveImage() throws IOException
	{
		String path = ResourcesTool.getText("finance_payment", "playmoney.academy.uploadpath");
		path = ServletActionContext.getServletContext().getRealPath(path);
		
		try {
			return FileUtil.FileUploads(path, orderImageFileName, orderImage);
		} catch (Exception e) {
			throw new IOException();
		}
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getRemittanceDate() {
		return remittanceDate;
	}

	public void setRemittanceDate(Date remittanceDate) {
		this.remittanceDate = remittanceDate;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public File getOrderImage() {
		return orderImage;
	}

	public void setOrderImage(File orderImage) {
		this.orderImage = orderImage;
	}

	public String getOrderImageFileName() {
		return orderImageFileName;
	}

	public void setOrderImageFileName(String orderImageFileName) {
		this.orderImageFileName = orderImageFileName;
	}

	public int getErrno() {
		return errno;
	}

	public void setErrno(int errno) {
		this.errno = errno;
	}
	
}
