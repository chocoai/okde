package net.cedu.action.meterial.meterial;

import java.io.File;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.meterial.Meterial;

/**
 * 增加物料
 * @author YY
 *
 */
public class AddMeterialAction extends BaseAction implements ModelDriven<Meterial> {
	private static final long serialVersionUID = 8115399564377382485L;

	@Autowired
	private MeterialBiz meterialbiz; //物料业务层

	private Meterial meterial =new Meterial(); //物料实体
	
	private boolean bol=true;  //是否重名变量
	
	private File img; //图片路径
	private String imgFileName; //图片
	private String savePath; //服务器路径
	@Action(value = "add_meterial", results = { @Result(name = "success", type="redirect", location = "index_meterial") })
	public String execute() {
		
		if(super.isGetRequest())
		{
			return INPUT;
		}
		
		try {
		
			meterial.setDeleteFlag(0);
			meterial.setCreatorId(this.getUser().getUserId());
			meterial.setUpdaterId(this.getUser().getUserId());
			meterial.setUpdatedTime(new Date());
			//是否上传图片
			if(img!=null)
			{  	
				//保存图片引用路径
				meterial.setPicture(uploadFile(imgFileName));
			}
			//判断是否重名
			Meterial me=meterialbiz.findByName(meterial.getName());
			if(me==null){
			meterialbiz.addMeterial(meterial);
			bol=true;
			}else{
				bol=false;
				return INPUT;
			}
		} catch (Exception e) {
			e.getStackTrace();
		return INPUT;
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
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("meterial","uploadpath"));
			return FileUtil.FileUploads(savePath,name,img);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	public Meterial getMeterial() {
		return meterial;
	}

	public void setMeterial(Meterial meterial) {
		this.meterial = meterial;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public Meterial getModel() {
		 
		return meterial;
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
