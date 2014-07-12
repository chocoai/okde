/**
 * 文件名：AdminUploadStudentDataAction.java
 * 包名：net.cedu.action.crm.importdata
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-2-1 下午03:05:18
 *
 */
package net.cedu.action.crm.importdata;

import java.io.File;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentImportRecordBiz;
import net.cedu.common.file.FileUtil;
import net.cedu.common.properties.Config;
import net.cedu.entity.crm.StudentImportRecord;

public class AdminUploadStudentDataAction extends BaseAction {

	@Autowired
	private StudentImportRecordBiz studentImportRecordBiz;
	private StudentImportRecord studentImportRecord;

	private File file;
	private String fileFileName;

	@Override
	public String execute() throws Exception {
		// get请求
		if (isGetRequest()) {
			request.setAttribute("errorCode", "100");
			return INPUT;
		}
		try{
			if (!fileFileName.endsWith(".xls")) {
				request.setAttribute("errorCode", "500");
				return INPUT;
			}
			// 获取上传路径
			String path = FileUtil.FileUploads(ServletActionContext.getServletContext().getRealPath(Config.getProperty("file.path.import.student.excel")),fileFileName, file);
			studentImportRecord.setUploadedFile(path);
			studentImportRecordBiz.addStudentImportRecord(studentImportRecord);
			request.setAttribute("errorCode", "200");
		}catch(Exception e){
			request.setAttribute("errorCode", "500");
			return INPUT;
		}
		return INPUT;
	}

	public StudentImportRecord getStudentImportRecord() {
		return studentImportRecord;
	}

	public void setStudentImportRecord(StudentImportRecord studentImportRecord) {
		this.studentImportRecord = studentImportRecord;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	
}
