package net.cedu.action.crm.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.crm.FollowUpBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsCallStatusMap;
import net.cedu.common.ConstantsIDMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.crm.FollowUp;
import net.cedu.entity.crm.Student;
import net.cedu.model.crm.StudentGenJinZhongExportTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonExportStudentFollowUpAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private TaskBiz taskBiz;
	@Autowired
	private FollowUpBiz followUpBiz;
	
	private Student student;
	private int callStatusId;
	
	// 导出路径
	private String downloadUrl;
	// 导出的最大数据量
	private int dataMaxCount = 0;
	
	/**
	 * 导出跟进中学生集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_list_student_gen_jin_zhong", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportListStudentGenJinZhong() throws Exception {
		// 查询数量
		// 未分配学生添加学习中心查询条件 为了独立查询页面学习中心我赋值为-1
		if (student.getBranchId() == -1) {
			student.setBranchId(super.getUser().getOrgId());
		}
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		
		student.setUserId(super.getUser().getUserId());
		student.setStatus(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG);
		student.setCallStatus(-1);
		student.setCallStatusId(callStatusId);
		
		PageResult<Student> pr = new PageResult<Student>();
		
		//检查导出结果是否为空
		int count = studentBiz.findStudentsPageCount(student, pr);
		if(count==0){
			downloadUrl="error"+Constants.EXPORT_EXCEL_ERROR_NO_3;
			return SUCCESS;
		}
		//设置下载任务导出数据数量上限
		dataMaxCount = Constants.EXPORT_EXCEL_MAX_COUNT;//如果配置文件异常则默认导出数据数量上限
		if(Config.getProperty("export.maxcount")!=null){
			 try {
				 dataMaxCount = Config.getIntProperty("export.maxcount");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//检查导入结果是否超过上限
		if(count>dataMaxCount){
			downloadUrl="error"+Constants.EXPORT_EXCEL_ERROR_NO_4;
			return SUCCESS;
		}

		int $page = 1;// 起始索引
		//设置一次导入的数据量
		int $pageSize = Constants.EXPORT_EXCEL_MAX_PAGE_SIZE;//如果配置文件异常则默认导出数据数量上限
		if(Config.getProperty("export.maxpagesize")!=null){
			 try {
				 $pageSize = Config.getIntProperty("export.maxpagesize");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		boolean only = true;
		OutputStream os = null;

		// 导出的excel数据集合
		List<StudentGenJinZhongExportTemplate> studentGenJinZhongExportTemplateList = new ArrayList<StudentGenJinZhongExportTemplate>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		userTask.setTitle("招生管理|学生跟进|跟进中学生导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<StudentGenJinZhongExportTemplate> ex = new ExcelExport<StudentGenJinZhongExportTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		
		List<Student> studentList = null;

		while (only || (studentList != null && studentList.size() != 0)) {
			pr.setCurrentPageIndex($page);
			studentList = studentBiz.findStudentsPageList(student, pr);
			if (studentList != null) {
				int index = 1;
				for (Student student : studentList) {
					// 最新跟进记录
					FollowUp followUp = followUpBiz.findLatestFollowUpByStudentId(student.getId());
					StudentGenJinZhongExportTemplate studentGenJinZhongExportTemplate = new StudentGenJinZhongExportTemplate();
					studentGenJinZhongExportTemplate.setXuHao(index+"");
					studentGenJinZhongExportTemplate.setXingMing(student.getName());
					studentGenJinZhongExportTemplate.setZhengJianLeiXing(ConstantsIDMap.getCode(student.getCertType()));
					studentGenJinZhongExportTemplate.setZhengJianHao(student.getCertNo());
					studentGenJinZhongExportTemplate.setShouJi(student.getMobile());
					studentGenJinZhongExportTemplate.setZuoJi(student.getPhone());
					studentGenJinZhongExportTemplate.setZhuangTai(ConstantsStudentStatusMap.getCode(student.getStatus()));
					studentGenJinZhongExportTemplate.setHuJiaoDengJi(ConstantsCallStatusMap.getCode(student.getCallStatusId()));
					studentGenJinZhongExportTemplate.setGenJinCiShu(student.getFollowCount()+"");
					studentGenJinZhongExportTemplate.setBaoMingShiJian(DateUtil.getDate(student.getRegistrationTime(),"yyyy-MM-dd"));
					studentGenJinZhongExportTemplate.setDangQianGenJinRen(student.getFollowUpName());
					studentGenJinZhongExportTemplate.setZuiXinGenJinShiJian(DateUtil.getDate(student.getLatestFollowUpDate(),"yyyy-MM-dd"));
					studentGenJinZhongExportTemplate.setZuiXinGouTongNeiRong(followUp==null?"":followUp.getRemark());
					studentGenJinZhongExportTemplateList.add(studentGenJinZhongExportTemplate);
					index++;
				}
				// 随机产生的文件名称前缀
				String dateStr = DateUtil.dateToStringWithTime(new Date());
				// 随机产生的文件名称以及后缀
				String filePath = dateStr + ".xls";
				// 文件全目录
				String tempPath = application.getRealPath(path)
						+ File.separator + filePath;
				// 创建流对象

				os = new FileOutputStream(tempPath);
				// 开始导出
				ex.exportExcel("导出结果", studentGenJinZhongExportTemplateList, os);
				// 关闭流
				os.close();
				studentGenJinZhongExportTemplateList.clear();
				os = null;
				$page++;
				only = false;
				if (studentList.size() < $pageSize) {
					break;
				}
			}
		}
		// 压缩文件
		File inFile = new File(application.getRealPath(path));
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
				(application.getRealPath(path) + ".zip.tmp")));
		ZipUtil.zipFile(inFile, zos, "");
		
		zos.close();
		zos = null;
		// 执行改名
		FileUtil.renamed(application.getRealPath(path), ".zip.tmp", ".zip");
		System.out.println(downloadUrl = path + ".zip");
		return SUCCESS;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public int getDataMaxCount() {
		return dataMaxCount;
	}

	public void setDataMaxCount(int dataMaxCount) {
		this.dataMaxCount = dataMaxCount;
	}

	public int getCallStatusId() {
		return callStatusId;
	}

	public void setCallStatusId(int callStatusId) {
		this.callStatusId = callStatusId;
	}
	
}
