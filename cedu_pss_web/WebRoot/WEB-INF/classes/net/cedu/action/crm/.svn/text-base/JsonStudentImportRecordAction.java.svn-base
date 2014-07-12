package net.cedu.action.crm;

import java.io.File;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.crm.StudentImportRecordBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.excel.ImportExcel;
import net.cedu.common.md5.Encryption;
import net.cedu.common.properties.Config;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.crm.Student;
import net.cedu.entity.crm.StudentImportRecord;
import net.cedu.entity.enrollment.Channel;
import net.cedu.model.crm.ImportResult;
import net.cedu.model.page.PageResult;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生信息导入管理
 * 
 * @author yangdongdong
 * 
 */
@SuppressWarnings("serial")
@ParentPackage("json-default")
public class JsonStudentImportRecordAction extends BaseAction {

	@Autowired
	private StudentImportRecordBiz studentImportRecordBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;

	// 分页结果
	private PageResult<StudentImportRecord> result = new PageResult<StudentImportRecord>();

	// 查询条件
	private StudentImportRecord studentImportRecord;

	private File file;
	private String fileFileName;

	private String message = super.getText("message.import.excel.file.success");

	private String id;// 加密以后的ID

	private String channelName;// 合作方名称
	
	private String enrollmentWayName;//市场途径名称

	public JsonStudentImportRecordAction() {
		super();
		this.setIl8nName("crm");
	}

	/**
	 * 查询学生信息导入数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_import_record_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,studentImportRecord,message"
	}) })
	public String crmStudentImportRecordCount() throws Exception {
		// 查询数量
		if(studentImportRecord==null){
			studentImportRecord=new StudentImportRecord();
		}
		studentImportRecord.setOrgId(super.getUser().getOrgId());
		result.setRecordCount(studentImportRecordBiz.findStudentImportRecordsPageCount(studentImportRecord,result));
		return SUCCESS;
	}

	/**
	 * 查询学生信息导入集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_import_record_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,studentImportRecord,message"
	}) })
	public String crmStudentImportRecordList() throws Exception {
		// 查询数量
		if(studentImportRecord==null){
			studentImportRecord=new StudentImportRecord();
		}
		studentImportRecord.setOrgId(super.getUser().getOrgId());
		// 查询集合
		result.setList(studentImportRecordBiz.findStudentImportRecordsPageList(studentImportRecord, result));
		return SUCCESS;
	}

	/**
	 * 上传xls文件，并导入学生信息
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "crm_student_import_record_upload", results = {
			@Result(name = "success", type = "json", params = { "contentType",
					"text/html", "includeProperties", "message" }),
			@Result(name = "error", type = "json", params = { "contentType",
					"text/html", "includeProperties", "message" }) })
	public String crmStudentImportRecordUpload() throws Exception {
		// 导入纪录ID//解密
		message = super.getText("message.import.excel.file.success");
		String sirId = Encryption.decryption(id);
		try {

			if (!fileFileName.endsWith(".xls")) {
				message = this
						.getText("message.import.excel.file.format.error");
				studentImportRecordBiz.deleteStudentImportRecordById(Integer
						.valueOf(sirId));
				return ERROR;
			}
			if (!sirId.equals("")) {
				StudentImportRecord si = studentImportRecordBiz
						.findStudentImportRecordById(Integer.valueOf(sirId));
				if (si != null) {
					String path = FileUtil
							.FileUploads(
									ServletActionContext
											.getServletContext()
											.getRealPath(
													Config
															.getProperty("file.path.import.student.excel")),
									fileFileName, file);
					si.setUploadedFile(path);
					studentImportRecordBiz.updateStudentImportRecord(si);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			message = this.getText("message.import.excel.file.exception.error");
			studentImportRecordBiz.deleteStudentImportRecordById(Integer
					.valueOf(sirId));
		}
		return SUCCESS;
	}

	/**
	 * 新建学生信息导入(总部－－历史数据导入)
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "crm_student_import_record_create_admin_history", results = {
			@Result(name = "success", type = "json", params = { "contentType",
					"text/json", "includeProperties", "id" }),
			@Result(name = "error", type = "json", params = { "contentType",
					"text/json", "includeProperties", "id" }) })
	public String crmStudentImportRecordCreateAdminHistory() throws Exception {

		try {	
			//总部
			studentImportRecord.setType(StudentImportRecord.IMPORT_TYPE_MANAGER);
			//数据来源为历史数据
			studentImportRecord.setSourceId(Constants.STU_SOURCE_HS);
			// 默认为禁用
			studentImportRecord.setStatus(Constants.STATUS_DISABLE);
			//创建时间
			studentImportRecord.setCreatedDate(DateUtil.getNow());
			//创建人
			studentImportRecord.setCreatorId(super.getUser().getUserId());
			//招生来源默认社招
			studentImportRecord.setEnrollmentSourceId(Constants.WEB_STU_SOURCE_DEFAULT);
			//市场途径默认为未知
			studentImportRecord.setEnrollmentWayId(47);
			Object obj = studentImportRecordBiz.addStudentImportRecord(studentImportRecord);
			if (obj != null) {
				// 加密字符串
				id = Encryption.encryption(obj.toString());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 新建学生信息导入(总部--呼叫中心)
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "crm_student_import_record_create_admin", results = {
			@Result(name = "success", type = "json", params = { "contentType",
					"text/json", "includeProperties", "id" }),
			@Result(name = "error", type = "json", params = { "contentType",
					"text/json", "includeProperties", "id" }) })
	public String crmStudentImportRecordCreateAdmin() throws Exception {

		
		try {	
			//总部呼叫中心
			studentImportRecord.setType(StudentImportRecord.IMPORT_TYPE_MANAGER_CC);
			//学生状态为欲报名
			studentImportRecord.setStudentSttaus(Constants.STU_CALL_STATUS_YU_BAO_MING);
			//数据来源为呼叫中心
			studentImportRecord.setSourceId(Constants.STU_SOURCE_CC);
			// 默认为禁用
			studentImportRecord.setStatus(Constants.STATUS_DISABLE);
			//创建时间
			studentImportRecord.setCreatedDate(DateUtil.getNow());
			//总部ID
			studentImportRecord.setOrgId(super.getUser().getOrgId());
			//创建人
			studentImportRecord.setCreatorId(super.getUser().getUserId());
			
			//如果市场途径为社招的话
			if (studentImportRecord.getEnrollmentWayId() != Constants.WEB_STU_SOURCE_DEFAULT) {
				if (studentImportRecord.getEnrollmentWayName() != null&& !studentImportRecord.getEnrollmentWayName().equals("")) {
					// 根据来源名称查询市场途径
					System.out.println(studentImportRecord.getEnrollmentWayName());
					BaseDict bd = baseDictBiz.findBaseDictsByTypeAndName(Constants.BASEDICT_STYLE_ENROLLMENTWAY,studentImportRecord.getEnrollmentWayName());
					if (bd != null) {
						studentImportRecord.setEnrollmentWayId(bd.getId());
					}else{
						//市场途径默认为未知
						studentImportRecord.setEnrollmentWayId(47);
					}

				}
			}
			Object obj = studentImportRecordBiz.addStudentImportRecord(studentImportRecord);
			if (obj != null) {
				// 加密字符串
				id = Encryption.encryption(obj.toString());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 新建学生信息导入(学习中心)
	 * 
	 * @return
	 * @throws Exception
	 */

	@Action(value = "crm_student_import_record_create_branch", results = {
			@Result(name = "success", type = "json", params = { "contentType",
					"text/json", "includeProperties", "id" }),
			@Result(name = "error", type = "json", params = { "contentType",
					"text/json", "includeProperties", "id" }) })
	public String crmStudentImportRecordCreateBranch() throws Exception {

		try {	
			//学习中心
			studentImportRecord.setType(StudentImportRecord.IMPORT_TYPE_Branch);
			//学生状态为已导入，未分配
			studentImportRecord.setStudentSttaus(Constants.STU_CALL_STATUS_YI_DAO_RU);
			//数据来源为学习中心
			studentImportRecord.setSourceId(Constants.STU_SOURCE_LC);
			// 默认为禁用
			studentImportRecord.setStatus(Constants.STATUS_DISABLE);
			//创建时间
			studentImportRecord.setCreatedDate(DateUtil.getNow());
			//创建人
			studentImportRecord.setCreatorId(super.getUser().getUserId());
			//如果市场途径为社招的话
			if (studentImportRecord.getEnrollmentWayId() != Constants.WEB_STU_SOURCE_DEFAULT) {
				if (studentImportRecord.getEnrollmentWayName() != null&& !studentImportRecord.getEnrollmentWayName().equals("")) {
					// 根据来源名称查询市场途径
					System.out.println(studentImportRecord.getEnrollmentWayName());
					BaseDict bd = baseDictBiz.findBaseDictsByTypeAndName(Constants.BASEDICT_STYLE_ENROLLMENTWAY,studentImportRecord.getEnrollmentWayName());
					if (bd != null) {
						studentImportRecord.setEnrollmentWayId(bd.getId());
					}

				}
			}
			Object obj = studentImportRecordBiz.addStudentImportRecord(studentImportRecord);
			if (obj != null) {
				// 加密字符串
				id = Encryption.encryption(obj.toString());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 查看学生信息导入
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_import_record_view", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmStudentImportRecordView() throws Exception {
		studentImportRecord = studentImportRecordBiz
				.findStudentImportRecordById(studentImportRecord.getId());

		if (studentImportRecord != null) {
			// 老带新
			if (studentImportRecord.getEnrollmentSourceId() == Constants.WEB_STU_ENROLLMENT_SOURCE) {
				Student student = studentBiz
						.findStudentById(studentImportRecord.getChannelId());
				if (student != null) {
					channelName = student.getName();
				} else {
					channelName = "";
				}
			} else {
				Channel channel = channelBiz.findChannel(studentImportRecord
						.getChannelId());
				if (channel != null) {
					channelName = channel.getName();
				} else {
					channelName = "";
				}
			}
			//市场途径
			BaseDict baseDict=baseDictBiz.findBaseDictById(studentImportRecord.getEnrollmentWayId());
			if(baseDict!=null){
				enrollmentWayName=baseDict.getName();
			}else{
				enrollmentWayName="未知";
			}
		}

		return SUCCESS;
	}

	/**
	 * 更新学生信息导入
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_import_record_update", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmStudentImportRecordUpdate() throws Exception {
		try {
			StudentImportRecord oldStudentImportRecord = studentImportRecordBiz
					.findStudentImportRecordById(studentImportRecord.getId());
			if(oldStudentImportRecord!=null){
				oldStudentImportRecord.setUpdatedId(super.getUser().getUserId());
				oldStudentImportRecord.setUpdatedTime(new Date());
			}
			// 执行更新
			studentImportRecordBiz
					.updateStudentImportRecord(oldStudentImportRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 删除学生信息导入
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_import_record_delete", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmStudentImportRecordDelete() throws Exception {
		StudentImportRecord si = studentImportRecordBiz
				.findStudentImportRecordById(studentImportRecord.getId());
		if (si != null&&si!=null) {
			FileUtil.deleteFile(ServletActionContext.getServletContext()
					.getRealPath(si.getUploadedFile()));
			studentImportRecordBiz.deleteStudentImportRecordById(si.getId());
		}
		return SUCCESS;
	}

	/**
	 * 启用并分配学生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_student_import_record_change_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmStudentImportRecordChangeStatus() throws Exception {
		try {
			StudentImportRecord si = studentImportRecordBiz.findStudentImportRecordById(studentImportRecord.getId());
			// 启用并分配学生
			if (si != null && (si.getType() == StudentImportRecord.IMPORT_TYPE_Branch||si.getType() == StudentImportRecord.IMPORT_TYPE_MANAGER_CC)) {

				ImportResult<Student> ir = new ImportResult<Student>();
				// 导入学生
				ImportExcel<Student> test = new ImportExcel<Student>(Student.class);
				
				File file = new File(ServletActionContext.getServletContext().getRealPath(si.getUploadedFile()));
				List<Student> results = test.importExcel(file);
				for (Student student : results) {
					// 导入信息ID
					student.setStudentImportRecordId(si.getId());
					// 学生状态已导入
					student.setStatus(si.getStudentSttaus());
					// 数据来源
					student.setStudentDataSource(si.getSourceId());
					//市场途径
					student.setEnrollmentWay(si.getEnrollmentWayId());
					//招生途径
					student.setEnrollmentSource(si.getEnrollmentSourceId());
					//合作方
					student.setChannelId(si.getChannelId());
					//学习中心
					student.setBranchId(si.getOrgId());
					//创建人
					student.setCreatorId(super.getUser().getUserId());
					ir = studentBiz.importBranchStudents(student, ir);
				}

//				String errorLog = "";
				StringBuffer errorLogSB = new StringBuffer("");
				for (Student student : ir.getErrorList()) {
//					errorLog += student.getErrorMessage() + "<br/>";
					errorLogSB.append(student.getErrorMessage() + "<br/>");
				}
				si.setErrorCount(ir.getErrorCount());
				si.setSuccessCount(ir.getSuccessCount());
				si.setErrorLog(errorLogSB.toString());
				si.setStatus(studentImportRecord.getStatus());
				//如果中心机构选择为总部
				if(si.getOrgId()!=BranchEnum.Admin.value())
				{
					if (si.getAssignmentType() == 1) {
						// 分配学生,纪录日志
						si.setFenpeiLog(studentBiz.updateAverageDistribution(si.getId(), super.getUser().getOrgId()));
					}
				}
				
				si.setUpdatedId(super.getUser().getUserId());
				si.setUpdatedTime(DateUtil.getNow());
				studentImportRecordBiz.updateStudentImportRecord(si);

			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}

	}

	/**
	 * 导入历史数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_history_student_import_record_change_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmHistoryStudentImportRecordChangeStatus() throws Exception {
		try {
			StudentImportRecord si = studentImportRecordBiz.findStudentImportRecordById(studentImportRecord.getId());

			// 历史数据
			if (si != null && si.getType() == StudentImportRecord.IMPORT_TYPE_MANAGER) {

				// 纪录导入日志
				ImportResult<Student> ir = new ImportResult<Student>();
				// 导入学生
				ImportExcel<Student> test = new ImportExcel<Student>(Student.class);
				File file = new File(ServletActionContext.getServletContext()
						.getRealPath(si.getUploadedFile()));
				List<Student> results = test.importExcel(file);

				for (Student student : results) {
					// 导入信息ID
					student.setStudentImportRecordId(si.getId());
					// 学生状态已导入
					student.setStatus(si.getStudentSttaus());
					// 数据来源
					student.setStudentDataSource(si.getSourceId());
					//市场途径
					student.setEnrollmentWay(si.getEnrollmentWayId());
					//招生途径
					student.setEnrollmentSource(si.getEnrollmentSourceId());
					//合作方
					student.setChannelId(si.getChannelId());
					//学习中心
					student.setBranchId(si.getOrgId());
					//创建人
					student.setCreatorId(super.getUser().getUserId());
					ir = studentBiz.importStudents(student, ir);

				}
//				String errorLog = "";
				StringBuffer errorLogSB = new StringBuffer("");
				int i = 1;
				for (Student student : ir.getErrorList()) {
//					errorLog += i + "." + student.getErrorMessage() + "<br/>";
					errorLogSB.append(i + "." + student.getErrorMessage() + "<br/>");
					i++;
				}
				si.setErrorCount(ir.getErrorCount());
				si.setSuccessCount(ir.getSuccessCount());
				si.setErrorLog(errorLogSB.toString());
				si.setStatus(studentImportRecord.getStatus());
				si.setUpdatedId(super.getUser().getUserId());
				si.setUpdatedTime(DateUtil.getNow());
				studentImportRecordBiz.updateStudentImportRecord(si);

			}
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}

	}

	public void setStudentImportRecord(StudentImportRecord studentImportRecord) {
		this.studentImportRecord = studentImportRecord;
	}

	public StudentImportRecord getStudentImportRecord() {
		return studentImportRecord;
	}

	public PageResult<StudentImportRecord> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentImportRecord> result) {
		this.result = result;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getMessage() {
		return message;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChannelName() {
		return channelName;
	}

	public String getEnrollmentWayName() {
		return enrollmentWayName;
	}

}
