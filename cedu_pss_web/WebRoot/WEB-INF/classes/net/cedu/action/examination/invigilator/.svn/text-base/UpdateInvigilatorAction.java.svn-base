package net.cedu.action.examination.invigilator;

import java.io.File;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.examination.Invigilator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class UpdateInvigilatorAction extends BaseAction {

	private static final long serialVersionUID = -1059865364657531438L;
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	private Invigilator invigilator;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	private File files; // 文件
	private String filesFileName; // 原始文件名称
	private String savePath; // 服务器路
	
	@Action(results = {
			@Result(name = "input", location = "update_invigilator.jsp"),
			@Result(name = "success", type = "redirect", location = "index_invigilator") })
	public String executes() throws Exception {

		if (super.isGetRequest()) {
			// 获取该详细数据
			invigilator = invigilatorbiz.findByInvigilatorId(id);
			return INPUT;
		}
		Invigilator er = new Invigilator();
		try {
			// 获取该原始详细数据
			er = invigilatorbiz.findByInvigilatorId(invigilator.getId());
			//if (null != files)
			if(invigilator!=null){
				er.setName(invigilator.getName());
				er.setNote(invigilator.getNote());
				er.setCertNo(invigilator.getCertNo());
				er.setCode(invigilator.getCode());
				er.setDegree(invigilator.getDegree());
				er.setEmail(invigilator.getEmail());
				er.setFeeStandard(invigilator.getFeeStandard());
				er.setFeeType(invigilator.getCertType());
				er.setGender(invigilator.getGender());
				er.setInvigilationExperience(invigilator.getInvigilationExperience());
			  // invigilator.setPhoto(uploadFile(filesFileName));
				er.setUpdaterId(super.getUser().getUserId());
				er.setUpdatedTime(new Date());
				id=invigilator.getId();
				// 执行修改
				invigilatorbiz.updateInvigilate(er);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 附件上传_返回
	 * 
	 * @param name
	 *            实际文件名
	 * @return 存储的相对路径
	 */
	// private String uploadFile(String name)
	// {
	// try
	// {
	// savePath =
	// ServletActionContext.getServletContext().getRealPath(ResourcesTool.getText("examination","uploadpath"));
	// System.out.print(savePath);
	// return FileUtil.FileUploads(savePath,name,files);
	// }
	// catch (Exception e)
	// {
	// e.printStackTrace();
	// return null;
	// }
	// }

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
