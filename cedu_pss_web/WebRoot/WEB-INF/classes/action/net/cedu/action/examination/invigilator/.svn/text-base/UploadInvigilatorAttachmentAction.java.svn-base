package net.cedu.action.examination.invigilator;

import java.io.File;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorAttachmentBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;

import net.cedu.entity.admin.User;
import net.cedu.entity.examination.InvigilatorAttachment;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class UploadInvigilatorAttachmentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3799220552270756604L;
	
	@Autowired
	private InvigilatorAttachmentBiz invigilatorattachmentbiz;       //附件Biz
	private InvigilatorAttachment invigilatorattachment;             //附件实体
	private User userinfo;                                   //上传者            
	private int id;                                          //获取连接参数ID
	private String title;                                    //附件标题
	private int types;                                        //附件类型
	private File files;                                      //附件文件
	private String filesFileName;                            //文件名称
	private String savepath;                                 //物理路径
	private String attachmentUrl;                            //服务器路径
	
	
	/**
	 * 附件上传
	 * @return
	 * @throws Exception
	 */
	@Action(results = {@Result(name = "success",type="redirect", location = "index_invigilatorattachement.jsp?id=${id}&tab=5")
					  
	})
			public String excute() throws Exception
			{
				try {
					invigilatorattachment=new InvigilatorAttachment();
					invigilatorattachment.setInvigilatorId(id);
					invigilatorattachment.setUploaderUid(super.getUser().getUserId());
					invigilatorattachment.setTitle(title);
					invigilatorattachment.setTypes(types);
					invigilatorattachment.setAttachmentUrl(uploadFile(filesFileName));
					invigilatorattachment.setName(filesFileName);
					invigilatorattachment.setCreatorId(super.getUser().getUserId());
					invigilatorattachment.setCreatedTime(new Date());
					invigilatorattachmentbiz.addInvigilatorAttachment(invigilatorattachment);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			
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
			savepath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("academy","uploadattachmentpath"));
			return FileUtil.FileUploads(savepath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}


	
	public InvigilatorAttachment getInvigilatorattachment() {
		return invigilatorattachment;
	}

	public void setInvigilatorattachment(InvigilatorAttachment invigilatorattachment) {
		this.invigilatorattachment = invigilatorattachment;
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


	public void setTypes(int types) {
		this.types = types;
	}


	public File getFiles() {
		return files;
	}


	public void setFiles(File files) {
		this.files = files;
	}


	public String getSavepath() {
		return savepath;
	}


	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	

	




}
