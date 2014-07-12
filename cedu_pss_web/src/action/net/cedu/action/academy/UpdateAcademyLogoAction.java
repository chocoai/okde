package net.cedu.action.academy;

import java.io.File;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyLinkMan;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 设置院校LoGo
 * 
 * 
 * @author gaolei
 * 
 */

public class UpdateAcademyLogoAction extends BaseAction {

	
	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	private Academy academy=new Academy();      //院校实体
	private File img;                           //图片文件 
	private String imgFileName;                 //img文件名称
	private String savePath;                    //服务器路径
	private int id;                             //院校ID

	/**
	 * 设置院校LoGo
	 * @return
	 * @throws Exception
	 */
	@Action(results = { @Result(name = "input",location = "update_academy_logo.jsp"),
						@Result(name = "view",type="redirect" ,location = "view_academy?id=${id}")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			//获取院校原始数据
			academy=academybiz.findAcademyById(id);
			
			return INPUT;
		}
		 Academy upacademy=new Academy();
		try {
		upacademy=academybiz.findAcademyById(id);
		
		//保存图片物理路径
		if(img!=null)
		{
			upacademy.setPictureUrl(uploadFile(imgFileName));
		}
		
		upacademy.setUpdaterId(super.getUser().getUserId());
		upacademy.setUpdatedTime(new Date());
		academybiz.updateAcademy(upacademy);
		//删除原图片
		if(upacademy.getPictureUrl()!=null)
		{
			
			deleteFile(savePath);
		}
	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
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
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("academy","uploadpath"));
			return FileUtil.FileUploads(savePath,name,img);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 删除上传的图片文件
	 * @param filepath
	 * @throws Exception
	 */
	private void deleteFile(String filepath)throws Exception 
	{
		File file=new File(filepath);
		file.delete();
	}
	

	public Academy getAcademy() {
		return academy;
	}
	
	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	


	
}
