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
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsCallStatusMap;
import net.cedu.common.ConstantsChannelTypeCheckedMap;
import net.cedu.common.ConstantsStudentDataSourceMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.Channel;
import net.cedu.model.crm.StudentDataExportTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonExportListStudentAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private TaskBiz taskBiz;
	@Autowired 
	private StuStatusBiz stuStatusBiz;//学生状态_业务层接口
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private ChannelBiz channelBiz;
	
	private Student student;
	private String stuStage;//阶段编码
	
	// 导出路径
	private String downloadUrl;
	// 导出的最大数据量
	private int dataMaxCount = 0;
	
	/**
	 * 导出学生集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_list_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportListStudent() throws Exception {
		if(stuStage!=null && !"".equals(stuStage) && !stuStage.equals("0") && student.getStatus()==0)
		{
			List<StudentStatus> stuStatusList=this.stuStatusBiz.findStatusNamesByStageCode(stuStage);
			StringBuffer statusSB = new StringBuffer("");
			if(stuStatusList!=null && stuStatusList.size()>0)
			{
				for(StudentStatus ss:stuStatusList)
				{
					if(statusSB.toString().equals("")){
						statusSB = new StringBuffer(ss.getId()+"");
					}else{
						statusSB.append(","+ss.getId());
					}
				}
				if(!statusSB.toString().equals("")){
					student.setStatusIds(statusSB.toString());
				}
			}
		}
		
		PageResult<Student> pr = new PageResult<Student>();
		
		//检查导出结果是否为空
		int count = studentBiz.findStudentsPageCountByBaseConditions(student);
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
		List<StudentDataExportTemplate> studentDataExportTemplateList = new ArrayList<StudentDataExportTemplate>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		userTask.setTitle("呼叫中心|在线客服|学生列表|学生导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<StudentDataExportTemplate> ex = new ExcelExport<StudentDataExportTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		pr.setOrder("branchId");
		
		List<Student> studentList = null;

		while (only || (studentList != null && studentList.size() != 0)) {
			pr.setCurrentPageIndex($page);
			studentList = studentBiz.findStudentsPageListByBaseConditions(student, pr);
			if (studentList != null) {
				for (Student student : studentList) {
					// 市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					// 合作方
					Channel channel = channelBiz.findChannel(student.getChannelId());
					StudentDataExportTemplate studentDataExportTemplate = new StudentDataExportTemplate();
					studentDataExportTemplate.setBranchName(student.getBranchName());
					studentDataExportTemplate.setFollowUpUserName(student.getFollowUpName());
					studentDataExportTemplate.setStudentName(student.getName());
					studentDataExportTemplate.setEnrollmentBatchName(student.getAcademyenrollbatchName());// ???
					studentDataExportTemplate.setAcademyName(student.getSchoolName());
					studentDataExportTemplate.setLevelName(student.getLevelName());
					studentDataExportTemplate.setMajorName(student.getMajorName());
					studentDataExportTemplate.setStudentDataSource(
							ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));
					studentDataExportTemplate.setEnrollmentSource(student.getEnrollmentSourceName1());
					studentDataExportTemplate.setChannelName(channel==null?"未知":channel.getName());
					studentDataExportTemplate.setEnrollmentWay(basedict==null?"--":basedict.getName());
					studentDataExportTemplate.setCallStatus(
							ConstantsCallStatusMap.getCode(student.getCallStatus()));
					studentDataExportTemplate.setIsChannelTypeChecked(
							ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));
					studentDataExportTemplate.setStatus(
							ConstantsStudentStatusMap.getCode(student.getStatus()));
					studentDataExportTemplate.setCertNo(student.getCertNo());
					studentDataExportTemplate.setMobile(student.getMobile());
					studentDataExportTemplate.setPhone(student.getPhone());
					studentDataExportTemplate.setRegistrationTime(student.getRegistrationTime()==null?"":student.getRegistrationTime().toString().substring(0,10));
					studentDataExportTemplate.setRemark(student.getRemark());
					studentDataExportTemplateList.add(studentDataExportTemplate);
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
				ex.exportExcel("导出结果", studentDataExportTemplateList, os);
				// 关闭流
				os.close();
				studentDataExportTemplateList.clear();
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

	public String getStuStage() {
		return stuStage;
	}

	public void setStuStage(String stuStage) {
		this.stuStage = stuStage;
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
