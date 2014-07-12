package net.cedu.action.academy;

import java.io.File;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.common.Constants;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyLinkMan;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 新增院校，院校联系人
 * 
 * @author gaolei
 * 
 */

public class AddAcademyAction extends BaseAction {

	
	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;//院校联系人Biz
	private Academy academy;                    //院校实体
	private AcademyLinkMan academylinkman;      //院校联系人实体
	private boolean  msg=false;                        //消息
	private File img;                           //图片文件 
	private String imgFileName;                 //img文件名称
	private String savePath;                    //服务器路径
	private String IsForceFeePolicy;            //是否强制执行收费政策
	private int id;                             //院校ID
	private int projectManagerId;               //项目经理ID
	
	
	@Action(results = { @Result(name = "input",type="dispatcher", location = "add_academy.jsp"),
						@Result(name = "view",type="redirect", location = "view_academy?id=${id}")})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			return INPUT;
		}
		if(academybiz.findAcademyByNameAndTelephone(academy.getName(),academy.getTelephone() )>0)
		{
			msg=true;
			return INPUT;
		}
		//是否强制执行收费政策  1：是  0：否
		if(IsForceFeePolicy!=null){
			academy.setIsForceFeePolicy(Constants.ISNEED_REBATES_TRUE);
		}else
		{
			academy.setIsForceFeePolicy(Constants.ISNEED_REBATES_FALSE);
		}
		
		//是否上传图片
		if(img!=null)
		{  	
			//保存图片引用路径
			academy.setPictureUrl(uploadFile(imgFileName));
		}
 		//添加院校
		academy.setAuditStatus(Constants.AUDIT_STATUS_INIT);
		academy.setCreatorId(super.getUser().getUserId());
		academy.setProjectManagerId(projectManagerId);
		academy.setCreatedTime(new Date());
		academy.setUpdaterId(super.getUser().getUserId());
		academy.setUpdatedTime(new Date());
		boolean bol=academybiz.addAcademy(academy);
		if(bol)
		{
			//院校联系人获取院校ID
			academylinkman.setAcademyId(academy.getId());
			if(academylinkman!=null)
			{
				if(academylinkman.getName()!=null && academylinkman.getTelephone()!=null)
				{
						//新增院校联系人
						academylinkmanbiz.addAcademyLinkMan(academylinkman);
				}
			}
		}
		id=academy.getId();
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
	
	
	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public AcademyLinkMan getAcademylinkman() {
		return academylinkman;
	}

	public void setAcademylinkman(AcademyLinkMan academylinkman) {
		this.academylinkman = academylinkman;
	}

	public boolean isMsg() {
		return msg;
	}

	public void setMsg(boolean msg) {
		this.msg = msg;
	}

	public File getExcel() {
		return img;
	}

	public void setExcel(File img) {
		this.img = img;
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

	public String getIsForceFeePolicy() {
		return IsForceFeePolicy;
	}

	public void setIsForceFeePolicy(String isForceFeePolicy) {
		IsForceFeePolicy = isForceFeePolicy;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProjectManagerId() {
		return projectManagerId;
	}

	public void setProjectManagerId(int projectManagerId) {
		this.projectManagerId = projectManagerId;
	}
	
	
	
}
