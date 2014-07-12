package net.cedu.action.enrollment.enrollmonitor;

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
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.EnrollMonitorBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsMonitorMap;
import net.cedu.common.ConstantsSexMap;
import net.cedu.common.ConstantsStudentDataSourceMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.dao.basesetting.MonitorResultsDao;
import net.cedu.dao.enrollment.ReturningVisitDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ReturningVisit;
import net.cedu.model.enrollment.enrollmonitor.ExportJianKongStudentTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewEnrollMonitorAction extends BaseAction{

	private static final long serialVersionUID = 7240008523958420022L;
	@Autowired
	private EnrollMonitorBiz enrollMonitorBiz;
	@Autowired
	private FeePaymentDetailDao feePaymentDetailDao;
	@Autowired
	private ReturningVisitDao returningVisitDao;
	@Autowired
	private MonitorResultsDao monitorResultsDao;
	@Autowired
	private TaskBiz taskBiz;
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	
	private String downloadUrl;
	//导出的最大数据量
	private int dataMaxCount = 0;
	private Student student;
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();

	/**
	 * 查询学生列表集合通过条件
	 */
	@Action(value="list_monitor_student",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute()throws Exception{
//		result.setOrder("createDate");
//		result.setSort("desc");
		student.setTuitionStatus(-1);
		result.setList(enrollMonitorBiz.findAllStudent(student, result));
		return SUCCESS;
	}
	
	/**
	 * 学生列表纪录数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value="count_monitor_student",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String StudentCount() throws Exception {
		// 查询数量
		student.setTuitionStatus(-1);
		result.setRecordCount(enrollMonitorBiz.findAllStudentCount(student, result));
		return SUCCESS;
	}
	
	/**
	 * 导出查询集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_jian_kong_student_admin", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportJianKongStudentAdmin() throws Exception {
		
		student.setMonitorStatus(-1);
		student.setStatusIds(Constants.STU_CALL_STATUS_YI_DAO_MING+","+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+","+Constants.STU_CALL_STATUS_YI_CE_SHI +","+Constants.STU_CALL_STATUS_YI_FU_HE +","+Constants.STU_CALL_STATUS_YI_LU_QU+","+Constants.STU_CALL_STATUS_YI_JIAO_XUE_FEI );
		student.setGender(-1);
		student.setCallStatus(-1);
		student.setTuitionStatus(-1);
		
		//检查导出结果是否为空
		int count = enrollMonitorBiz.findAllStudentCount(student, result);
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
		List<Student> studentList = null;
		List<ExportJianKongStudentTemplate> exportJianKongStudentTemplateList=null;
		ExportJianKongStudentTemplate exportJianKongStudentTemplate=null;
		
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("运营管理|运营管理|报名监控|全部信息|学生导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}
		
		
		// 导出的随机目录
		String path = Config.getProperty("export.excel.tmp")+ ctm;
		// 创建导出的工具类对象
		ExcelExport<ExportJianKongStudentTemplate> ex = new ExcelExport<ExportJianKongStudentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		String studentIds=",";
		Map<String,Double> studentId_feeSubjectId_money=null;
		Map<String,ReturningVisit> studentId_returningVisit = null;
		Map<String,String> monitorresults_name = null;
		
		PageResult<Student> result = new PageResult<Student>();
		result.setPageSize($pageSize);
		result.setPage(true);
		result.setOrder("branchId");

		while (only || (studentList != null && studentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			studentList = enrollMonitorBiz.findAllStudent(student, result);
			if(studentList!=null){
				exportJianKongStudentTemplateList=new ArrayList<ExportJianKongStudentTemplate>();
				studentIds=",";
				StringBuffer studentIdsSB = new StringBuffer(",");
				for (Student student : studentList) {
//					if(studentIds.equals(",")){
//						studentIds=""+student.getId();
//					}else{
//						studentIds+=","+student.getId();
//					}
					if(studentIdsSB.toString().equals(",")){
						studentIdsSB = new StringBuffer(""+student.getId());
					}else{
						studentIdsSB.append(","+student.getId());
					}
				}
				studentIds=studentIdsSB.toString();
				if(studentIds.equals(",")){
					studentIds="0";
				}
				studentId_feeSubjectId_money=feePaymentDetailDao.findStudentFeePaymentByStudentIds(studentIds);
				studentId_returningVisit=returningVisitDao.findReturningVisitByIds(studentIds);
				monitorresults_name=monitorResultsDao.findAllMap();
				returningVisitDao.findReturningContentByIds(studentIds);
				//合作方
				Channel channel=null;
				for (Student student : studentList) {
					
					exportJianKongStudentTemplate=new ExportJianKongStudentTemplate();
					exportJianKongStudentTemplate.setXueShengXingMing(student.getName());
					exportJianKongStudentTemplate.setXinBie(ConstantsSexMap.getCode(student.getGender()));
					exportJianKongStudentTemplate.setShenFengZhengHaoMa(student.getCertNo());
					exportJianKongStudentTemplate.setYuanXiaoMingCheng(student.getSchoolName());
					exportJianKongStudentTemplate.setZhuanYe(student.getMajorName());
					exportJianKongStudentTemplate.setCengCi(student.getLevelName());
					exportJianKongStudentTemplate.setZhaoShengPiCi(student.getAcademyenrollbatchName());
					exportJianKongStudentTemplate.setXueShengZhuangTai(ConstantsStudentStatusMap.getCode(student.getStatus()));
					exportJianKongStudentTemplate.setJianKongZhuangTai(ConstantsMonitorMap.getCode(student.getMonitorStatus()));
					exportJianKongStudentTemplate.setXueXiZhongXing(student.getBranchName());
					exportJianKongStudentTemplate.setShuJuLaiYuan(ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));
					exportJianKongStudentTemplate.setZhaoShengTuJing(student.getEnrollmentSourceName1());
					channel=this.channelBiz.findChannel(student.getChannelId());
					exportJianKongStudentTemplate.setHeZuoFang(channel==null?"":channel.getName());
					exportJianKongStudentTemplate.setShiChangTuJing(student.getEnrollmentWayName());
					exportJianKongStudentTemplate.setShouJiHaoma(student.getMobile());
					exportJianKongStudentTemplate.setZuoJiHaoMa(student.getPhone());
					exportJianKongStudentTemplate.setGenJinRen(student.getFollowUpName());
					Double baomingfei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.RegistrationFee.value());
					exportJianKongStudentTemplate.setYiJiaoNaBaoMingFei(baomingfei==null?"0.0":baomingfei.toString());
					Double ceshifei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.TestFee.value());
					exportJianKongStudentTemplate.setYiJiaoNaCeShiFei(ceshifei==null?"0.0":ceshifei.toString());
					Double xuefei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.TuitionFee.value());
					exportJianKongStudentTemplate.setYiJiaoNaXueFei(xuefei==null?"0.0":xuefei.toString());
					Double jiaocaifei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.Textbooks.value());
					exportJianKongStudentTemplate.setYiJiaoNaJiaoCaiFei(jiaocaifei==null?"0.0":jiaocaifei.toString());
					exportJianKongStudentTemplate.setJianKongJieGuo(monitorresults_name.get(studentId_returningVisit.get(student.getId()+"")==null?"":studentId_returningVisit.get(student.getId()+"").getMonitorResults()+""));
					exportJianKongStudentTemplate.setHuiFangNeiRong(studentId_returningVisit.get(student.getId()+"")==null?"":studentId_returningVisit.get(student.getId()+"").getContent());
					exportJianKongStudentTemplateList.add(exportJianKongStudentTemplate);
				}

				// 随机产生的文件名称前缀
				String dateStr = DateUtil.dateToStringWithTime(new Date());
				// 随机产生的文件名称以及后缀
				String filePath = dateStr + ".xls";
				// 文件全目录
				String tempPath = application.getRealPath(path) + File.separator
						+ filePath;
				// 创建流对象

				os = new FileOutputStream(tempPath);
				// 开始导出
				ex.exportExcel("导出结果", exportJianKongStudentTemplateList, os);
				// 关闭流
				os.close();
				os = null;
				exportJianKongStudentTemplateList.clear();
				$page++;
				only = false;
				if (studentList.size() < $pageSize) {
					break;
				}
			}
			
			
		}
		// 压缩文件
		File inFile = new File(application.getRealPath(path));
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(( application.getRealPath(path) + ".zip")));
		// zos.setComment("");
		ZipUtil.zipFile(inFile, zos, "");

		zos.close();
		zos = null;
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}
	
	/**
	 * 导出查询集合(重点核查导出)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_repeat_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportRepeatStudent() throws Exception {
		
		student.setMonitorStatus(-1);
		//student.setStatusIds(Constants.STU_CALL_STATUS_YI_DAO_MING+","+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+","+Constants.STU_CALL_STATUS_YI_CE_SHI +","+Constants.STU_CALL_STATUS_YI_FU_HE +","+Constants.STU_CALL_STATUS_YI_LU_QU+","+Constants.STU_CALL_STATUS_YI_JIAO_XUE_FEI );
		student.setGender(-1);
		student.setCallStatus(-1);
		
		//检查导出结果是否为空
		int count = enrollMonitorBiz.findAllStudentCount(student, result);
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
		List<Student> studentList = null;
		List<ExportJianKongStudentTemplate> exportJianKongStudentTemplateList=null;
		ExportJianKongStudentTemplate exportJianKongStudentTemplate=null;
		
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("运营管理|运营管理|重点核查|学生导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}
		
		
		// 导出的随机目录
		String path = Config.getProperty("export.excel.tmp")+ ctm;
		// 创建导出的工具类对象
		ExcelExport<ExportJianKongStudentTemplate> ex = new ExcelExport<ExportJianKongStudentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		String studentIds=",";
		Map<String,Double> studentId_feeSubjectId_money=null;
		Map<String,ReturningVisit> studentId_returningVisit = null;
		Map<String,String> monitorresults_name = null;
		
		PageResult<Student> result = new PageResult<Student>();
		result.setPageSize($pageSize);
		result.setPage(true);
		result.setOrder("branchId");

		while (only || (studentList != null && studentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			studentList = enrollMonitorBiz.findAllStudent(student, result);
			if(studentList!=null){
				exportJianKongStudentTemplateList=new ArrayList<ExportJianKongStudentTemplate>();
				studentIds=",";
				StringBuffer studentIdsSB = new StringBuffer(",");
				for (Student student : studentList) {
//					if(studentIds.equals(",")){
//						studentIds=""+student.getId();
//					}else{
//						studentIds+=","+student.getId();
//					}
					if(studentIdsSB.toString().equals(",")){
						studentIdsSB=new StringBuffer(""+student.getId());
					}else{
						studentIdsSB.append(","+student.getId());
					}
				}
				studentIds=studentIdsSB.toString();
				if(studentIds.equals(",")){
					studentIds="0";
				}
				studentId_feeSubjectId_money=feePaymentDetailDao.findStudentFeePaymentByStudentIds(studentIds);
				studentId_returningVisit=returningVisitDao.findReturningVisitByIds(studentIds);
				monitorresults_name=monitorResultsDao.findAllMap();
				returningVisitDao.findReturningContentByIds(studentIds);
				//合作方
				Channel channel=null;
				for (Student student : studentList) {
					exportJianKongStudentTemplate=new ExportJianKongStudentTemplate();
					exportJianKongStudentTemplate.setXueShengXingMing(student.getName());
					exportJianKongStudentTemplate.setXinBie(ConstantsSexMap.getCode(student.getGender()));
					exportJianKongStudentTemplate.setShenFengZhengHaoMa(student.getCertNo());
					exportJianKongStudentTemplate.setYuanXiaoMingCheng(student.getSchoolName());
					exportJianKongStudentTemplate.setZhuanYe(student.getMajorName());
					exportJianKongStudentTemplate.setCengCi(student.getLevelName());
					exportJianKongStudentTemplate.setZhaoShengPiCi(student.getAcademyenrollbatchName());
					exportJianKongStudentTemplate.setXueShengZhuangTai(ConstantsStudentStatusMap.getCode(student.getStatus()));
					exportJianKongStudentTemplate.setJianKongZhuangTai(ConstantsMonitorMap.getCode(student.getMonitorStatus()));
					exportJianKongStudentTemplate.setXueXiZhongXing(student.getBranchName());
					exportJianKongStudentTemplate.setShuJuLaiYuan(ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));
					exportJianKongStudentTemplate.setZhaoShengTuJing(student.getEnrollmentSourceName1());
					channel=this.channelBiz.findChannel(student.getChannelId());
					exportJianKongStudentTemplate.setHeZuoFang(channel==null?"":channel.getName());
					exportJianKongStudentTemplate.setShiChangTuJing(student.getEnrollmentWayName());
					exportJianKongStudentTemplate.setShouJiHaoma(student.getMobile());
					exportJianKongStudentTemplate.setZuoJiHaoMa(student.getPhone());
					exportJianKongStudentTemplate.setGenJinRen(student.getFollowUpName());
					Double baomingfei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.RegistrationFee.value());
					exportJianKongStudentTemplate.setYiJiaoNaBaoMingFei(baomingfei==null?"0.0":baomingfei.toString());
					Double ceshifei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.TestFee.value());
					exportJianKongStudentTemplate.setYiJiaoNaCeShiFei(ceshifei==null?"0.0":ceshifei.toString());
					Double xuefei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.TuitionFee.value());
					exportJianKongStudentTemplate.setYiJiaoNaXueFei(xuefei==null?"0.0":xuefei.toString());
					Double jiaocaifei=studentId_feeSubjectId_money.get(student.getId()+"_"+FeeSubjectEnum.Textbooks.value());
					exportJianKongStudentTemplate.setYiJiaoNaJiaoCaiFei(jiaocaifei==null?"0.0":jiaocaifei.toString());
					exportJianKongStudentTemplate.setJianKongJieGuo(monitorresults_name.get(studentId_returningVisit.get(student.getId()+"")==null?"":studentId_returningVisit.get(student.getId()+"").getMonitorResults()+""));
					exportJianKongStudentTemplate.setHuiFangNeiRong(studentId_returningVisit.get(student.getId()+"")==null?"":studentId_returningVisit.get(student.getId()+"").getContent());
					exportJianKongStudentTemplateList.add(exportJianKongStudentTemplate);
				}

				// 随机产生的文件名称前缀
				String dateStr = DateUtil.dateToStringWithTime(new Date());
				// 随机产生的文件名称以及后缀
				String filePath = dateStr + ".xls";
				// 文件全目录
				String tempPath = application.getRealPath(path) + File.separator
						+ filePath;
				// 创建流对象

				os = new FileOutputStream(tempPath);
				// 开始导出
				ex.exportExcel("导出结果", exportJianKongStudentTemplateList, os);
				// 关闭流
				os.close();
				os = null;
				exportJianKongStudentTemplateList.clear();
				$page++;
				only = false;
				if (studentList.size() < $pageSize) {
					break;
				}
			}
			
			
		}
		// 压缩文件
		File inFile = new File(application.getRealPath(path));
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(( application.getRealPath(path) + ".zip")));
		// zos.setComment("");
		ZipUtil.zipFile(inFile, zos, "");

		zos.close();
		zos = null;
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}

	//--------------------------------------get and set methods-----------------------------------------
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
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
