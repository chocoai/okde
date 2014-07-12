package net.cedu.action.crm.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.crm.FollowUpBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsSexMap;
import net.cedu.common.ConstantsStudentDataSourceMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.dao.enrollment.ReturningVisitDao;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.crm.FollowUp;
import net.cedu.entity.crm.Student;
import net.cedu.model.crm.CCStudentExportTemplate;
import net.cedu.model.crm.CCStudentExportTemplate1;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonExportAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private ReturningVisitDao returningVisitDao;
	@Autowired
	private TaskBiz taskBiz;
	@Autowired
	private FollowUpBiz followUpBiz;

	private Student student;

	private String downloadUrl;
	//导出的最大数据量
	private int dataMaxCount = 0;

	/**
	 * 导出查询集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_cc_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportCCStudent() throws Exception {
		student.setStartStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		student.setStudentDataSource(Constants.STU_SOURCE_CC);
		student.setCallStatus(-1);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);

		student.setGender(Integer.parseInt(request
				.getParameter("student.gender")));

		if (request.getParameter("student.startDate") != null
				&& !request.getParameter("student.startDate").equals("")) {
			student.setStartDate(DateUtil.getDate(request
					.getParameter("student.startDate")));
		}
		if (request.getParameter("student.endDate") != null
				&& !request.getParameter("student.endDate").equals("")) {
			student.setEndDate(DateUtil.getDate(request
					.getParameter("student.endDate")));
		}
		
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
		List<CCStudentExportTemplate> ccStudentExportTemplateList = new ArrayList<CCStudentExportTemplate>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("呼叫中心|在线客服|呼叫报名表|已推送|学生导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<CCStudentExportTemplate> ex = new ExcelExport<CCStudentExportTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		pr.setOrder("branchId");

		

		List<Student> studentList = null;

		while (only || (studentList != null && studentList.size() != 0)) {
			pr.setCurrentPageIndex($page);
			studentList = studentBiz.findStudentsPageList(student, pr);
			if (studentList != null) {
				for (Student student : studentList) {
					CCStudentExportTemplate ccStudentExportTemplate = new CCStudentExportTemplate();
					ccStudentExportTemplate.setBranchName(student
							.getBranchName());
					ccStudentExportTemplate.setCengCi(student.getLevelName());
					ccStudentExportTemplate.setFollowUpUserName(student
							.getFollowUpName());
					ccStudentExportTemplate.setMobile(student.getMobile());
					ccStudentExportTemplate.setName(student.getName());
					ccStudentExportTemplate.setPhone(student.getPhone());
					ccStudentExportTemplate.setPiCi(student
							.getAcademyenrollbatchName());
					ccStudentExportTemplate.setSexName(ConstantsSexMap
							.getCode(student.getGender()));
					ccStudentExportTemplate
							.setStatusName(ConstantsStudentStatusMap
									.getCode(student.getStatus()));
					ccStudentExportTemplate
							.setPushDate(student.getPushDate() != null ? DateUtil
									.getDate(student.getPushDate(),
											"yyyy-MM-dd HH:mm") : "");
					ccStudentExportTemplate.setPushName(student.getPushName());
					ccStudentExportTemplate
							.setYuanXiao(student.getSchoolName());
					ccStudentExportTemplate.setZhuanYe(student.getMajorName());
					ccStudentExportTemplate.setIdCode(student.getCertNo());
					ccStudentExportTemplate.setZhaoshengTuJin(student
							.getEnrollmentSourceName1());
					ccStudentExportTemplate.setShichangTuJin(student
							.getEnrollmentWayName());
					// 学习中心首次跟进日期
					FollowUp fu1 = getFirstFollowUp(student.getId());
					ccStudentExportTemplate.setFirstFollowUpDate(fu1.getCreatedTime()!=null? DateUtil
							.getDate(fu1.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					// 学习中心最新跟进日期
					FollowUp fu2 = getLatestFollowUp(student.getId());
					ccStudentExportTemplate.setLatestFollowUpDate(fu2.getCreatedTime()!=null? DateUtil
							.getDate(fu2.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					ccStudentExportTemplateList.add(ccStudentExportTemplate);
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
				ex.exportExcel("导出结果", ccStudentExportTemplateList, os);
				// 关闭流
				os.close();
				ccStudentExportTemplateList.clear();
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

	/**
	 * 导出查询集合(已推送)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_cc_student1", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportCCStudent1() throws Exception {
		student.setStartStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		student.setCallStatus(-1);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);

		student.setGender(Integer.parseInt(request
				.getParameter("student.gender")));

		if (request.getParameter("student.startDate") != null
				&& !request.getParameter("student.startDate").equals("")) {
			student.setStartDate(DateUtil.getDate(request
					.getParameter("student.startDate")));
		}
		if (request.getParameter("student.endDate") != null
				&& !request.getParameter("student.endDate").equals("")) {
			student.setEndDate(DateUtil.getDate(request
					.getParameter("student.endDate")));
		}
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
		List<CCStudentExportTemplate1> ccStudentExportTemplateList = new ArrayList<CCStudentExportTemplate1>();
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("呼叫中心|在线客服|呼叫中心报名表|已推送|学生导出");
		//userTask.setStatus(1);
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}
		
		// 导出的随机目录
		String path = Config.getProperty("export.excel.tmp")+ctm;
		// 创建导出的工具类对象
		ExcelExport<CCStudentExportTemplate1> ex = new ExcelExport<CCStudentExportTemplate1>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		pr.setOrder("branchId");

		
//		String studentIds = "";
		List<Student> studentList = null;
		// 学生的回访记录
		Map<String, String> student_return_visit_map = null;

		while (only || (studentList != null && studentList.size() != 0)) {
			pr.setCurrentPageIndex($page);

			studentList = studentBiz.findStudentsPageList(student, pr);
			if (studentList != null) {
//				studentIds = ",";
				StringBuffer studentIdsSB = new StringBuffer(",");
				for (Student student : studentList) {
//					if (studentIds.equals(",")) {
//						studentIds = "" + student.getId();
//					} else {
//						studentIds += "," + student.getId();
//					}
					if (studentIdsSB.toString().equals(",")){
						studentIdsSB = new StringBuffer("" + student.getId());
					}else {
						studentIdsSB.append("," + student.getId());
					}
				}
//				if (studentIds.equals(",")) {
//					studentIds = "0";
//				}
				if (studentIdsSB.toString().equals(",")){
					studentIdsSB = new StringBuffer("0");
				}
				// 回访记录
				student_return_visit_map = returningVisitDao
						.findReturningContentByIds(studentIdsSB.toString());
				String content = "";
				for (Student student : studentList) {
					CCStudentExportTemplate1 ccStudentExportTemplate = new CCStudentExportTemplate1();
					ccStudentExportTemplate.setBranchName(student
							.getBranchName());
					ccStudentExportTemplate.setCengCi(student.getLevelName());
					ccStudentExportTemplate.setFollowUpUserName(student
							.getFollowUpName());
					ccStudentExportTemplate.setMobile(student.getMobile());
					ccStudentExportTemplate.setName(student.getName());
					ccStudentExportTemplate.setPhone(student.getPhone());
					ccStudentExportTemplate.setPiCi(student
							.getAcademyenrollbatchName());
					ccStudentExportTemplate.setSexName(ConstantsSexMap
							.getCode(student.getGender()));
					ccStudentExportTemplate
							.setStatusName(ConstantsStudentStatusMap
									.getCode(student.getStatus()));
					ccStudentExportTemplate
							.setPushDate(student.getPushDate() != null ? DateUtil
									.getDate(student.getPushDate(),
											"yyyy-MM-dd HH:mm") : "");
					ccStudentExportTemplate.setCreateDate(student
							.getCreateDate() != null ? DateUtil.getDate(
							student.getCreateDate(), "yyyy-MM-dd") : "");
					ccStudentExportTemplate.setPushName(student.getPushName());
					ccStudentExportTemplate
							.setYuanXiao(student.getSchoolName());
					ccStudentExportTemplate.setZhuanYe(student.getMajorName());
					ccStudentExportTemplate.setIdCode(student.getCertNo());
					ccStudentExportTemplate.setZhaoshengTuJin(student
							.getEnrollmentSourceName1());
					ccStudentExportTemplate.setShichangTuJin(student
							.getEnrollmentWayName());
					ccStudentExportTemplate
							.setShuJuLaiYuan(ConstantsStudentDataSourceMap
									.getCode(student.getStudentDataSource()));
					// 回访记录
					content = student_return_visit_map
							.get(student.getId() + "");
					ccStudentExportTemplate
							.setReturnVisitContent(content == null ? ""
									: content);
					// 学习中心首次跟进日期
					FollowUp fu1 = getFirstFollowUp(student.getId());
					ccStudentExportTemplate.setFirstFollowUpDate(fu1.getCreatedTime()!=null? DateUtil
							.getDate(fu1.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					// 学习中心最新跟进日期
					FollowUp fu2 = getLatestFollowUp(student.getId());
					ccStudentExportTemplate.setLatestFollowUpDate(fu2.getCreatedTime()!=null? DateUtil
							.getDate(fu2.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					//跟进次数
					ccStudentExportTemplate.setFollowCount(student.getFollowCount()+"");
					ccStudentExportTemplateList.add(ccStudentExportTemplate);
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
				ex.exportExcel("导出结果", ccStudentExportTemplateList, os);
				// 关闭流
				os.close();
				ccStudentExportTemplateList.clear();
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

	/**
	 * 导出查询集合(无意愿)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_cc_student2", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportCCStudent2() throws Exception {
		student.setStatus(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		student.setCallStatus(-1);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);

		student.setGender(Integer.parseInt(request
				.getParameter("student.gender")));

		if (request.getParameter("student.startDate") != null
				&& !request.getParameter("student.startDate").equals("")) {
			student.setStartDate(DateUtil.getDate(request
					.getParameter("student.startDate")));
		}
		if (request.getParameter("student.endDate") != null
				&& !request.getParameter("student.endDate").equals("")) {
			student.setEndDate(DateUtil.getDate(request
					.getParameter("student.endDate")));
		}
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
		List<CCStudentExportTemplate1> ccStudentExportTemplateList = new ArrayList<CCStudentExportTemplate1>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("呼叫中心|在线客服|呼叫中心报名表|无意愿|学生导出");
		//userTask.setStatus(1);
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}
		
		// 导出的随机目录
		String path = Config.getProperty("export.excel.tmp")+ctm;
		
		// 创建导出的工具类对象
		ExcelExport<CCStudentExportTemplate1> ex = new ExcelExport<CCStudentExportTemplate1>();
		
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		pr.setOrder("branchId");

		
//		String studentIds = "";
		List<Student> studentList = null;
		// 学生的回访记录
		Map<String, String> student_return_visit_map = null;

		while (only || (studentList != null && studentList.size() != 0)) {
			pr.setCurrentPageIndex($page);

			studentList = studentBiz.findStudentsPageList(student, pr);
			if (studentList != null) {
//				studentIds = ",";
				StringBuffer studentIdsSB = new StringBuffer(",");
				for (Student student : studentList) {
//					if (studentIds.equals(",")) {
//						studentIds = "" + student.getId();
//					} else {
//						studentIds += "," + student.getId();
//					}
					if (studentIdsSB.toString().equals(",")){
						studentIdsSB = new StringBuffer("" + student.getId());
					}else {
						studentIdsSB.append("," + student.getId());
					}
				}
//				if (studentIds.equals(",")) {
//					studentIds = "0";
//				}
				if (studentIdsSB.toString().equals(",")){
					studentIdsSB = new StringBuffer("0");
				}
				// 回访记录
				student_return_visit_map = returningVisitDao
						.findReturningContentByIds(studentIdsSB.toString());
				String content = "";
				for (Student student : studentList) {
					CCStudentExportTemplate1 ccStudentExportTemplate = new CCStudentExportTemplate1();
					ccStudentExportTemplate.setBranchName(student
							.getBranchName());
					ccStudentExportTemplate.setCengCi(student.getLevelName());
					ccStudentExportTemplate.setFollowUpUserName(student
							.getFollowUpName());
					ccStudentExportTemplate.setMobile(student.getMobile());
					ccStudentExportTemplate.setName(student.getName());
					ccStudentExportTemplate.setPhone(student.getPhone());
					ccStudentExportTemplate.setPiCi(student
							.getAcademyenrollbatchName());
					ccStudentExportTemplate.setSexName(ConstantsSexMap
							.getCode(student.getGender()));
					ccStudentExportTemplate
							.setStatusName(ConstantsStudentStatusMap
									.getCode(student.getStatus()));
					ccStudentExportTemplate
							.setPushDate(student.getPushDate() != null ? DateUtil
									.getDate(student.getPushDate(),
											"yyyy-MM-dd HH:mm") : "");
					ccStudentExportTemplate.setCreateDate(student
							.getCreateDate() != null ? DateUtil.getDate(
							student.getCreateDate(), "yyyy-MM-dd") : "");
					ccStudentExportTemplate.setPushName(student.getPushName());
					ccStudentExportTemplate
							.setYuanXiao(student.getSchoolName());
					ccStudentExportTemplate.setZhuanYe(student.getMajorName());
					ccStudentExportTemplate.setIdCode(student.getCertNo());
					ccStudentExportTemplate.setZhaoshengTuJin(student
							.getEnrollmentSourceName1());
					ccStudentExportTemplate.setShichangTuJin(student
							.getEnrollmentWayName());
					ccStudentExportTemplate
							.setShuJuLaiYuan(ConstantsStudentDataSourceMap
									.getCode(student.getStudentDataSource()));
					// 回访记录
					content = student_return_visit_map
							.get(student.getId() + "");
					ccStudentExportTemplate
							.setReturnVisitContent(content == null ? ""
									: content);
					// 学习中心首次跟进日期
					FollowUp fu1 = getFirstFollowUp(student.getId());
					ccStudentExportTemplate.setFirstFollowUpDate(fu1.getCreatedTime()!=null? DateUtil
							.getDate(fu1.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					// 学习中心最新跟进日期
					FollowUp fu2 = getLatestFollowUp(student.getId());
					ccStudentExportTemplate.setLatestFollowUpDate(fu2.getCreatedTime()!=null? DateUtil
							.getDate(fu2.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					//跟进次数
					ccStudentExportTemplate.setFollowCount(student.getFollowCount()+"");
					ccStudentExportTemplateList.add(ccStudentExportTemplate);
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
				ex.exportExcel("导出结果", ccStudentExportTemplateList, os);
				// 关闭流
				os.close();
				ccStudentExportTemplateList.clear();
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
	
	/**
	 * 导出查询集合(已推送)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_online_yi_tui_song_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportOnlineYiTuiSongStudent() throws Exception {
		student.setStartStatusId(Constants.STU_CALL_STATUS_YU_BAO_MING);
		student.setEndStatusId(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI);
		student.setStudentDataSource(Constants.STU_SOURCE_NP);
		student.setCallStatus(-1);
		student.setMonitorStatus(-1);
		student.setTuitionStatus(-1);
		
		student.setGender(Integer.parseInt(request.getParameter("student.gender")));
		
		if(request.getParameter("student.startDate")!=null&&!request.getParameter("student.startDate").equals("")){
			student.setStartDate(DateUtil.getDate(request.getParameter("student.startDate")));
		}
		if(request.getParameter("student.endDate")!=null&&!request.getParameter("student.endDate").equals("")){
			student.setEndDate(DateUtil.getDate(request.getParameter("student.endDate")));
		}
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
		List<CCStudentExportTemplate> ccStudentExportTemplateList = new ArrayList<CCStudentExportTemplate>();
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("呼叫中心|在线客服|网上报名表|已推送|学生导出");
		//userTask.setStatus(1);
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}
		
		// 导出的随机目录
		String path = Config.getProperty("export.excel.tmp")+ctm;
		// 创建导出的工具类对象
		ExcelExport<CCStudentExportTemplate> ex = new ExcelExport<CCStudentExportTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		pr.setOrder("branchId");

		List<Student> studentList = null;

		while (only || (studentList != null && studentList.size() != 0)) {
			pr.setCurrentPageIndex($page);

			studentList=studentBiz.findStudentsPageList(student, pr);
			if (studentList != null) {
				for (Student student : studentList) {
					CCStudentExportTemplate ccStudentExportTemplate=new CCStudentExportTemplate();
					ccStudentExportTemplate.setBranchName(student.getBranchName());
					ccStudentExportTemplate.setCengCi(student.getLevelName());
					ccStudentExportTemplate.setFollowUpUserName(student.getFollowUpName());
					ccStudentExportTemplate.setMobile(student.getMobile());
					ccStudentExportTemplate.setName(student.getName());
					ccStudentExportTemplate.setPhone(student.getPhone());
					ccStudentExportTemplate.setPiCi(student.getAcademyenrollbatchName());
					ccStudentExportTemplate.setSexName(ConstantsSexMap.getCode(student.getGender()));
					ccStudentExportTemplate.setStatusName(ConstantsStudentStatusMap.getCode(student.getStatus()));
					//ccStudentExportTemplate.setUpFollowUpUserName(student.getUpFollowUpName());
					ccStudentExportTemplate.setPushDate(student.getPushDate()!=null?DateUtil.getDate(student.getPushDate(), "yyyy-MM-dd HH:mm"):"");
					ccStudentExportTemplate.setPushName(student.getPushName());
					ccStudentExportTemplate.setYuanXiao(student.getSchoolName());
					ccStudentExportTemplate.setZhuanYe(student.getMajorName());
					ccStudentExportTemplate.setIdCode(student.getCertNo());
				//	ccStudentExportTemplate.setShuJuLaiYuan(ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));
					ccStudentExportTemplate.setZhaoshengTuJin(student.getEnrollmentSourceName1());
					ccStudentExportTemplate.setShichangTuJin(student.getEnrollmentWayName());
					// 学习中心首次跟进日期
					FollowUp fu1 = getFirstFollowUp(student.getId());
					ccStudentExportTemplate.setFirstFollowUpDate(fu1.getCreatedTime()!=null? DateUtil
							.getDate(fu1.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					// 学习中心最新跟进日期
					FollowUp fu2 = getLatestFollowUp(student.getId());
					ccStudentExportTemplate.setLatestFollowUpDate(fu2.getCreatedTime()!=null? DateUtil
							.getDate(fu2.getCreatedTime(), "yyyy-MM-dd HH:mm") : "");
					ccStudentExportTemplateList.add(ccStudentExportTemplate);
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
				ex.exportExcel("导出结果", ccStudentExportTemplateList, os);
				// 关闭流
				os.close();
				ccStudentExportTemplateList.clear();
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
	
	/*
	 * 根据学生id查询学习中心首次跟进记录
	 */
	private FollowUp getFirstFollowUp(int stuId) throws Exception{
		FollowUp followUp = followUpBiz.findFirstFollowUpByStudentId(stuId);
		if(followUp==null){
			return new FollowUp();
		}
		return followUp;
	}
	
	/*
	 * 根据学生id查询学习中心最新跟进记录
	 */
	private FollowUp getLatestFollowUp(int stuId) throws Exception{
		FollowUp followUp = followUpBiz.findLatestFollowUpByStudentId(stuId);
		if(followUp==null){
			return new FollowUp();
		}
		return followUp;
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

}
