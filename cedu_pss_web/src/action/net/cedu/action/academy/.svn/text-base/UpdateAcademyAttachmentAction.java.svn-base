package net.cedu.action.academy;

import java.io.File;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyAttachmentBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.academy.AcademyAttachment;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 修改附件信息
 * 
 * 
 * @author gaolei
 * 
 */

public class UpdateAcademyAttachmentAction extends BaseAction {

	
	@Autowired
	private AcademyAttachmentBiz academyattachmentbiz;              //院校Biz
	
	private AcademyAttachment academyattachment;      //院校实体
	private File files;                               //附件文件 
	private String filesFileName;                     //附件文件名称
	private String savePath;                          //服务器路径
	private String title;                             //标题
	private int type;
	private int id;                                   //附件ID
	private int academyId;                            //院校ID
	
	
	
	
	/**
	 * 修改院校附件
	 * @return
	 * @throws Exception
	 */
	@Action(results = { @Result(name = "input",location = "update_academy_attachment.jsp"),
						@Result(name = "view",type="redirect" ,location = "list_academy_attachment?id=${academyId}")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			academyattachment=academyattachmentbiz.findAcademyAttachmentById(id);
			return INPUT;
		}
		academyattachment=academyattachmentbiz.findAcademyAttachmentById(id);
		if(title!=null && title!="")
		{	
			academyattachment.setTitle(title);
		}
		if(files!=null)
		{
			String delpath=ServletActionContext.getServletContext().getRealPath(academyattachment.getAttachmentUrl());
			
			//获取服务器路径
			academyattachment.setName(filesFileName);
			academyattachment.setAttachmentUrl(uploadFile(filesFileName));
			deleteFile(delpath);
			//原附件删除
			

		}
		academyattachment.setType(type);
		academyattachment.setUpdaterId(super.getUser().getUserId());
		academyattachment.setUpdatedTime(new Date());
		academyattachmentbiz.updateAcademyAttachment(academyattachment);
		academyId=academyattachment.getAcademyId();	
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

	public AcademyAttachment getAcademyattachment() {
		return academyattachment;
	}

	public void setAcademyattachment(AcademyAttachment academyattachment) {
		this.academyattachment = academyattachment;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	


	
}
