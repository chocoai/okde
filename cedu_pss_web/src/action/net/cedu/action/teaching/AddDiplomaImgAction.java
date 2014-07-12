package net.cedu.action.teaching;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.common.Constants;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.teaching.Diploma;

public class AddDiplomaImgAction extends BaseAction {
	private File img;                           //图片文件 
	private String imgFileName;                 //img文件名称
	private String savePath; 
	private Diploma diploma=new Diploma();
	private int id;//学生id
	private boolean isback=false;
	//服务器路径
	@Autowired
	private DiplomaBiz diplomaBiz;
	@Action(results = {
			@Result(name="success",location="view_diploma_student",params={"id","${id}","isback","${isback}"}, type="redirectAction")})
	public String uploadDiplomaPicture() throws Exception
	{
		diploma=diplomaBiz.findDiplomaByStudentId(id);
		if(diploma!=null)
		{
			//是否上传图片
			if(img!=null)
			{  	
				//保存图片引用路径
				diploma.setDiplomaUrl(uploadFile(imgFileName));
			}
			diploma.setStatus(Constants.DIPLOMA_STATUS_YES_QU);//已领取
		    diplomaBiz.updateDiploma(diploma);
		    isback=true;
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
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("teaching","uploadpath"));
			return FileUtil.FileUploads(savePath,name,img);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public Diploma getDiploma() {
		return diploma;
	}
	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isIsback() {
		return isback;
	}
	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
	
	
}
