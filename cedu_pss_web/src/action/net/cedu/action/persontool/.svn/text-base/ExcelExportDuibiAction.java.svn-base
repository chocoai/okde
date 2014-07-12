/**
 * 文件名：ExcelExportStudentEnrollmentSourceAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-28 下午08:27:31
 *
*/
package net.cedu.action.persontool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.persontool.DuibiStudentGongfuBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.persontool.DuibiStudentGongfu;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class ExcelExportDuibiAction extends BaseAction {

	private static final long serialVersionUID = 6145675316949050562L;

	@Autowired
	private DuibiStudentGongfuBiz duibiStudentGongfuBiz;
	
	@Autowired
	private TaskBiz taskBiz;

	private String downloadUrl;
	
	

	/**
	 * 导出查询集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_cc_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportCCStudent() throws Exception {

		
	
		OutputStream os = null;

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("公服平台与预报名导出数据对比结果");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=-1){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<DuibiStudentGongfu> ex = new ExcelExport<DuibiStudentGongfu>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		

		List<DuibiStudentGongfu> exportList = null;

		exportList = duibiStudentGongfuBiz.findStudentByPrePurchaseCenter();
			
		// 随机产生的文件名称前缀
		String dateStr = DateUtil.dateToStringWithTime(new Date());
		// 随机产生的文件名称以及后缀
		String filePath = dateStr + ".xls";
		// 文件全目录
		String tempPath = application.getRealPath(path)	+ File.separator + filePath;
		// 创建流对象

		os = new FileOutputStream(tempPath);
		// 开始导出
		ex.exportExcel("导出结果", exportList, os);
		// 关闭流
		os.close();
		exportList.clear();
		os = null;

	
		// 压缩文件
		File inFile = new File(application.getRealPath(path));
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
				(application.getRealPath(path) + ".zip")));
		// zos.setComment("");
		ZipUtil.zipFile(inFile, zos, "");

		zos.close();
		zos = null;
		System.out.println(downloadUrl = path + ".zip");
		return SUCCESS;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
