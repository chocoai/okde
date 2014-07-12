package net.cedu.action.examination.invigilator;

import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;

import net.cedu.biz.examination.InvigilatorAttachmentBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;

import net.cedu.entity.examination.InvigilatorAttachment;

public class UpdateInvigilatorAttachmentAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2427017806487963581L;

	@Autowired
	private InvigilatorAttachmentBiz invigilatorattachmentbiz;       //附件Biz
	private InvigilatorAttachment invigilatorattachment;             //附件实体
	private File files;                               //附件文件 
	private String filesFileName;                     //附件文件名称
	private String savePath;                          //服务器路径
	private String title;                             //标题
	private int types;
	private int id;                                   //附件ID
	private int invigilatorId;                           
	
	
	
	
	/**
	 * 修改院校附件
	 * @return
	 * @throws Exception
	 */
	@Action(results = { @Result(name = "input",location = "update_invigilator_attachment.jsp"),
						@Result(name = "view",type="redirect" ,location = "index_invigilatorattachement?id=${invigilatorId}")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			invigilatorattachment=invigilatorattachmentbiz.findInvigilatorAttachmentById(id);
			return INPUT;
		}
		invigilatorattachment=invigilatorattachmentbiz.findInvigilatorAttachmentById(id);
		if(title!=null && title!="")
		{	
			invigilatorattachment.setTitle(title);
		}
		if(files!=null)
		{
			String delpath=ServletActionContext.getServletContext().getRealPath(invigilatorattachment.getAttachmentUrl());
			
			//获取服务器路径
			invigilatorattachment.setName(filesFileName);
			invigilatorattachment.setAttachmentUrl(uploadFile(filesFileName));
			deleteFile(delpath);
			//原附件删除
			

		}
		invigilatorattachment.setTypes(types);
		invigilatorattachment.setUpdaterId(super.getUser().getUserId());
		invigilatorattachment.setUpdatedTime(new Date());
		invigilatorattachmentbiz.updateInvigilatorAttachment(invigilatorattachment);
		invigilatorId=invigilatorattachment.getInvigilatorId();	
		return "view";
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
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("academy","uploadattachmentpath"));
			return FileUtil.FileUploads(savePath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * 删除源附件
	 * @param filepath
	 * @throws Exception
	 */
	private void deleteFile(String filepath)throws Exception 
	{
		File file=new File(filepath);
		file.delete();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTypes() {
		return types;
	}

	public void setType(int types) {
		this.types = types;
	}


	public InvigilatorAttachment getInvigilatorattachment() {
		return invigilatorattachment;
	}


	public void setInvigilatorattachment(InvigilatorAttachment invigilatorattachment) {
		this.invigilatorattachment = invigilatorattachment;
	}


	public int getInvigilatorId() {
		return invigilatorId;
	}


	public void setInvigilatorId(int invigilatorId) {
		this.invigilatorId = invigilatorId;
	}

	

	

}
