package net.cedu.action.academy;

import java.io.File;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyAttachmentBiz;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyAttachment;
import net.cedu.entity.admin.User;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 附件上传
 * 
 * @author gaolei
 * 
 * */
public class UploadAcademyAttachmentAction extends BaseAction {

	
	@Autowired
	private UserBiz userbiz;                                 //附件Biz
	@Autowired
	private AcademyAttachmentBiz academyattachmentbiz;       //附件Biz
	private AcademyAttachment academyattachment;             //附件实体
	private User userinfo;                                   //上传者            
	private int id;                                          //获取连接参数ID
	private String title;                                    //附件标题
	private int type;                                        //附件类型
	private File files;                                      //附件文件
	private String filesFileName;                            //文件名称
	private String savepath;                                 //物理路径
	private String attachmentUrl;                            //服务器路径
	
	
	
	/**
	 * 附件上传
	 * @return
	 * @throws Exception
	 */
	@Action(results = {@Result(name = "success",type="redirect", location = "view_academy_attachment?id=${id}&tab=4")
					  
	})
			public String excute() throws Exception
			{
				try {
					academyattachment=new AcademyAttachment();
					academyattachment.setAcademyId(id);
					academyattachment.setUploaderUid(super.getUser().getUserId());
					academyattachment.setTitle(title);
					academyattachment.setType(type);
					academyattachment.setAttachmentUrl(uploadFile(filesFileName));
					academyattachment.setName(filesFileName);
					academyattachment.setCreatorId(super.getUser().getUserId());
					academyattachment.setCreatedTime(new Date());
					academyattachmentbiz.addAcademyAttachment(academyattachment);					
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


	public AcademyAttachment getAcademyattachment() {
		return academyattachment;
	}


	public void setAcademyattachment(AcademyAttachment academyattachment) {
		this.academyattachment = academyattachment;
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
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
