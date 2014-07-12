package net.cedu.action.enrollment.enrollquota.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.model.enrollment.enrollquota.EnrollquotaRecruitStudentsExportTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonEnrollquotaAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private BranchEnrollQuotaBiz branchenrollquotaBiz;
	@Autowired
	private TaskBiz taskBiz;
	
	private int batchId = 0;
	
	// 导出路径
	private String downloadUrl;
	
	/**
	 * 导出查询集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_enrollquota_recruit_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportCCStudent() throws Exception {
		
		PageResult<BranchEnrollQuota> pr = new PageResult<BranchEnrollQuota>();
		
		//检查导出结果是否为空
		List<BranchEnrollQuota> beqList = null;
		if(batchId>0){
			beqList = branchenrollquotaBiz.findBranchEnrollQuotalist(batchId, pr);
		}
		int count = beqList==null?0:beqList.size();
		if(count==0){
			downloadUrl="error"+Constants.EXPORT_EXCEL_ERROR_NO_3;
			return SUCCESS;
		}
		// 指标导出没有数据上限限制，取消上限验证
		
		OutputStream os = null;

		// 导出的excel数据集合
		List<EnrollquotaRecruitStudentsExportTemplate> ersetList = new ArrayList<EnrollquotaRecruitStudentsExportTemplate>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("招生管理|招生指标|学习中心招生指标|导出学习中心招生指标");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<EnrollquotaRecruitStudentsExportTemplate> ex = new ExcelExport<EnrollquotaRecruitStudentsExportTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPage(false);

		if (beqList != null) {
			// 遍历中心指标集合
			for (BranchEnrollQuota beq : beqList) {
				List<Academy> academyList = beq.getAcademylst();
				// 有授权院校
				if(academyList!=null && academyList.size()>0){
					EnrollquotaRecruitStudentsExportTemplate erset = null;
					int index = 0;
					for(Academy academy : academyList){
						erset = new EnrollquotaRecruitStudentsExportTemplate();
						if(index==0){
							erset.setBranchName(beq.getBranchName());
							erset.setBranchTarget(beq.getTarget()+"");
							index++;
						}
						erset.setAcademyName(academy.getName());
						erset.setAcademyTarget(academy.getTarget()+"");
						erset.setAcademyCompletedTarget(academy.getComplete()+"");
						ersetList.add(erset);
					}
				}
				// 没有授权院校
				else{
					EnrollquotaRecruitStudentsExportTemplate erset = new EnrollquotaRecruitStudentsExportTemplate();
					erset.setBranchName(beq.getBranchName());
					erset.setBranchTarget(beq.getTarget()+"");
					erset.setAcademyName("该学习中心没有授权的院校");
					ersetList.add(erset);
				}
				// 每插入完一个学习中心空一行
				ersetList.add( new EnrollquotaRecruitStudentsExportTemplate());
			}
			// 随机产生的文件名称前缀
			String dateStr = DateUtil.dateToStringWithTime(new Date());
			// 随机产生的文件名称以及后缀
			String filePath = dateStr + ".xls";
			// 文件全目录
			String tempPath = application.getRealPath(path) + File.separator + filePath;
			// 创建流对象
			os = new FileOutputStream(tempPath);
			// 开始导出
			ex.exportExcel("导出结果", ersetList, os);
			// 关闭流
			os.close();
			ersetList.clear();
			os = null;
		}
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

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
