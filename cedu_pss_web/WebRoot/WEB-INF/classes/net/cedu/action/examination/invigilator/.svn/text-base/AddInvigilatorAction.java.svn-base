package net.cedu.action.examination.invigilator;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.examination.Invigilator;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;


public class AddInvigilatorAction extends BaseAction {
	private static final long serialVersionUID = -1893184640223273165L;

	@Autowired
	private InvigilatorBiz invigilatorbiz;
	
	private Invigilator invigilator;

	private File files;    
	
	private String filesFileName;                 
	
	private String savePath;                      
	
	private boolean results=false;
	

	public String execute()
	{
		if(isGetRequest())
		{
			return INPUT;
		}
		try
		{
			if(null!=files)
			invigilator.setPhoto(uploadFile(filesFileName));
			System.out.print(filesFileName);
			invigilator.setCreatorId(super.getUser().getUserId());
			invigilator.setUpdaterId(super.getUser().getUserId());
			invigilator.setCreatedTime(new Date());
			invigilator.setStatus(0);
			invigilator.setDeleteFlag(0);
			results=invigilatorbiz.createNew(invigilator);
			if(results)
				addActionMessage(ResourcesTool.getText("examination","add.success"));
			else
				addActionMessage(ResourcesTool.getText("examination", "name.repeat.error"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			addActionMessage(ResourcesTool.getText("examination","add.error"));
		}
		return SUCCESS;
	
	}
	
	
	private String uploadFile(String name)
	{
		try 
		{
			savePath = ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("examination","uploadpath"));
			System.out.print(savePath);
			return FileUtil.FileUploads(savePath,name,files);
		}
		catch (Exception e) 
		{			
			e.printStackTrace();
			return null;
		}
	}
	
	public Invigilator getInvigilator() {
		return invigilator;
	}
	public void setInvigilator(Invigilator invigilator) {
		this.invigilator = invigilator;
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
}
