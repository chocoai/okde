package net.cedu.action.finance.channelrebatereview;

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
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.ReturningVisitBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsChannelTypeCheckedMap;
import net.cedu.common.ConstantsFeeWayMap;
import net.cedu.common.ConstantsMonitorMap;
import net.cedu.common.ConstantsPaymentStatusMap;
import net.cedu.common.ConstantsStartCourseStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.ReturningVisit;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;
import net.cedu.model.finance.ExportFpdForChannelRebateSufficeShowBranchTemplate;
import net.cedu.model.finance.ExportFpdForChannelRebateSufficeShowCeduTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款申请（查询）显示可以返款的缴费单明细  2012-05-28 重构
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonListChannelRebateSufficeFpdAction extends BaseAction
{
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	@Autowired
	private TaskBiz taskBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	@Autowired
	private ReturningVisitBiz returningVisitBiz;
	
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

	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	private String feepdIds;//选中的缴费单明细Ids字符串集合
	
	private Student student=new Student(); //学生实体
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细实体	
	
	private OrderCeduChannel orderCeduChannel=new OrderCeduChannel();//招生返款单实体
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private AcademyLevel academyLevel=new AcademyLevel();//院校招生批次与层次的关系
	
	// 导出路径
	private String downloadUrl;
	// 导出的最大数据量
	private int dataMaxCount = 0;
	
	/**
	 * 显示符合条件未招生返款的缴费单明细数量_重构_2012-05-28
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_suffice_fpd_show_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewCount() throws Exception 
	{
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			academyLevel=this.academyLevelBiz.findById(student.getLevelId());
			if(academyLevel!=null)
			{
				student.setLevelId(academyLevel.getLevelId());
			}
			else
			{
				student.setLevelId(0);
			}
		}
		result.setRecordCount(this.feePaymentDetailBiz.findfpdCountByPageForChannelRecruitRebate(feePaymentDetail, student,feepdIds));
		return SUCCESS;
	}

	/**
	 * 显示符合条件未招生返款的缴费单明细列表_重构_2012-05-28
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channel_rebate_suffice_fpd_show_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewList() throws Exception 
	{			
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			academyLevel=this.academyLevelBiz.findById(student.getLevelId());
			if(academyLevel!=null)
			{
				student.setLevelId(academyLevel.getLevelId());
			}
			else
			{
				student.setLevelId(0);
			}
		}
		result.setList(this.feePaymentDetailBiz.findfpdListByPageForChannelRecruitRebate(feePaymentDetail, student,feepdIds, result));
		return SUCCESS;
	}
	
	/**
	 * 导出招生返款符合未返款的缴费单集合（总部）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_list_channel_rebate_suffice_fpd", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportListChannelRebateSufficeFpd() throws Exception {
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			academyLevel=this.academyLevelBiz.findById(student.getLevelId());
			if(academyLevel!=null)
			{
				student.setLevelId(academyLevel.getLevelId());
			}
			else
			{
				student.setLevelId(0);
			}
		}
		
		PageResult<FeePaymentDetail> pr = new PageResult<FeePaymentDetail>();
		
		//检查导出结果是否为空
		int count = this.feePaymentDetailBiz.findfpdCountByPageForChannelRecruitRebate(feePaymentDetail, student,feepdIds);
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
		List<ExportFpdForChannelRebateSufficeShowCeduTemplate> eFFCRSSCTList = new ArrayList<ExportFpdForChannelRebateSufficeShowCeduTemplate>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		userTask.setTitle("财务管理|财务查询|招生返款查询|符合未返款缴费单导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<ExportFpdForChannelRebateSufficeShowCeduTemplate> ex = new ExcelExport<ExportFpdForChannelRebateSufficeShowCeduTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		
		List<FeePaymentDetail> feePaymentDetailList = null;

		while (only || (feePaymentDetailList != null && feePaymentDetailList.size() != 0)) {
			pr.setCurrentPageIndex($page);
			feePaymentDetailList = this.feePaymentDetailBiz.findfpdListByPageForChannelRecruitRebate(feePaymentDetail, student,feepdIds, pr);
			if (feePaymentDetailList != null) {
				for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
					Student stu = null;
					if(feePaymentDetail.getStudentId()>0){
						stu = studentBiz.findStudentById(feePaymentDetail.getStudentId());
					}
					if(stu == null){
						stu = new Student();
					}
					// 招生途径
					EnrollmentSource enrollmentsource = enrollmentSourceBiz.findEnrollmentSourceById(stu.getEnrollmentSource());
					// 市场途径
					BaseDict basedict = baseDictBiz.findBaseDictById(stu.getEnrollmentWay());
					// 数据来源
					BaseDict bd = this.baseDictBiz.findBaseDictById(stu.getStudentDataSource());
					// 监控结果
					MonitorResults mr = this.monitorResultsBiz.findbyresultid(stu.getLastMonitorResult());
					// 最新回访内容
					// 取最新一条记录
					PageResult<ReturningVisit> returningVisitPR = new PageResult<ReturningVisit>();
					returningVisitPR.setPageSize(1);
					returningVisitPR.setPage(true);
					returningVisitPR.setOrder("id");
					returningVisitPR.setSort("desc");
					ReturningVisit rv = new ReturningVisit();
					rv.setStudentId(stu.getId());
					List<ReturningVisit> returningVisitList = returningVisitBiz.findReturningVisitPageList(rv, returningVisitPR);
					
					ExportFpdForChannelRebateSufficeShowCeduTemplate eFFCRSSCT = new ExportFpdForChannelRebateSufficeShowCeduTemplate();
					eFFCRSSCT.setNian(DateUtil.getDate(feePaymentDetail.getCreatedTime(), "yyyy"));
					eFFCRSSCT.setYue(DateUtil.getDate(feePaymentDetail.getCreatedTime(), "MM"));
					eFFCRSSCT.setRi(DateUtil.getDate(feePaymentDetail.getCreatedTime(), "dd"));
					eFFCRSSCT.setJiaoFeiDanHao(feePaymentDetail.getPaymentCode());
					eFFCRSSCT.setXingMing(stu.getName());
					eFFCRSSCT.setShenFenZhengHao(stu.getCertNo());
					eFFCRSSCT.setShouJi(stu.getMobile());
					eFFCRSSCT.setZuoJi(stu.getPhone());
					eFFCRSSCT.setXueXiZhongXin(feePaymentDetail.getBranchName());
					eFFCRSSCT.setYuanXiao(feePaymentDetail.getSchoolName());
					eFFCRSSCT.setPiCi(feePaymentDetail.getAcademyenrollbatchName());
					eFFCRSSCT.setCengCi(feePaymentDetail.getLevelName());
					eFFCRSSCT.setZhuanYe(feePaymentDetail.getMajorName());
					eFFCRSSCT.setZhaoShengTuJing(enrollmentsource==null?"":enrollmentsource.getName());
					eFFCRSSCT.setHeZuoFang(feePaymentDetail.getChannelName());
					eFFCRSSCT.setShiChangTuJing(basedict==null?"":basedict.getName());
					eFFCRSSCT.setShuJuLaiYuan(bd==null?"":bd.getName());
					eFFCRSSCT.setJiaoFeiKeMu(feePaymentDetail.getPaymentSubjectName());//缴费科目
					eFFCRSSCT.setShiJiaoJinE(feePaymentDetail.getJiaofeiValue()+"");
					eFFCRSSCT.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePaymentDetail.getFeeWayId()));
					eFFCRSSCT.setZhuangTai(ConstantsPaymentStatusMap.getCode(feePaymentDetail.getStatus()));
					eFFCRSSCT.setZongBuQueRenShiJian(DateUtil.getDate(feePaymentDetail.getCeduConfirmTime(), "yyyy-MM-dd"));
					eFFCRSSCT.setKaiKeZhuangTai(ConstantsStartCourseStatusMap.getCode(Constants.STU_IS_START_COURSE_TRUE));
					eFFCRSSCT.setZhaoShengLaiYuanFuHe(ConstantsChannelTypeCheckedMap.getCode(Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE));
					eFFCRSSCT.setBeiZhu("");
					eFFCRSSCT.setJianKongJieGuo(mr==null?"":mr.getName());
					eFFCRSSCT.setJianKongZhuangTai(ConstantsMonitorMap.getCode(stu.getMonitorStatus()));
					if(returningVisitList!=null && returningVisitList.size()>0){
						eFFCRSSCT.setHuiFangRen(returningVisitList.get(0).getStrParams().get("returningVisitName"));
						eFFCRSSCT.setHuiFangNeiRong(returningVisitList.get(0).getContent());
					}
					else{
						eFFCRSSCT.setHuiFangRen("");
						eFFCRSSCT.setHuiFangNeiRong("");
					}
					eFFCRSSCTList.add(eFFCRSSCT);
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
				ex.exportExcel("导出结果", eFFCRSSCTList, os);
				// 关闭流
				os.close();
				eFFCRSSCTList.clear();
				os = null;
				$page++;
				only = false;
				if (feePaymentDetailList.size() < $pageSize) {
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
	 * 导出招生返款符合未返款的缴费单集合（中心）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_list_channel_rebate_suffice_fpd_branch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportListChannelRebateSufficeFpdBranch() throws Exception {
		feePaymentDetail.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setStartStatusId(Constants.STU_CALL_STATUS_YI_DAO_MING);
		//转换为基础的层次id
		if(student.getLevelId()!=0)
		{
			academyLevel=this.academyLevelBiz.findById(student.getLevelId());
			if(academyLevel!=null)
			{
				student.setLevelId(academyLevel.getLevelId());
			}
			else
			{
				student.setLevelId(0);
			}
		}
		
		PageResult<FeePaymentDetail> pr = new PageResult<FeePaymentDetail>();
		
		//检查导出结果是否为空
		int count = this.feePaymentDetailBiz.findfpdCountByPageForChannelRecruitRebate(feePaymentDetail, student,feepdIds);
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
		List<ExportFpdForChannelRebateSufficeShowBranchTemplate> eFFCRSSBTList = new ArrayList<ExportFpdForChannelRebateSufficeShowBranchTemplate>();

		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		userTask.setTitle("财务管理|财务查询|招生返款查询|符合未返款缴费单导出");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;

		// 创建导出的工具类对象
		ExcelExport<ExportFpdForChannelRebateSufficeShowBranchTemplate> ex = new ExcelExport<ExportFpdForChannelRebateSufficeShowBranchTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		// 不分页
		pr.setPageSize($pageSize);
		pr.setPage(true);
		
		List<FeePaymentDetail> feePaymentDetailList = null;

		while (only || (feePaymentDetailList != null && feePaymentDetailList.size() != 0)) {
			pr.setCurrentPageIndex($page);
			feePaymentDetailList = this.feePaymentDetailBiz.findfpdListByPageForChannelRecruitRebate(feePaymentDetail, student,feepdIds, pr);
			if (feePaymentDetailList != null) {
				for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
					Student stu = null;
					if(feePaymentDetail.getStudentId()>0){
						stu = studentBiz.findStudentById(feePaymentDetail.getStudentId());
					}
					if(stu == null){
						stu = new Student();
					}
					// 招生途径
					EnrollmentSource enrollmentsource = enrollmentSourceBiz.findEnrollmentSourceById(stu.getEnrollmentSource());
					// 市场途径
					BaseDict basedict = baseDictBiz.findBaseDictById(stu.getEnrollmentWay());
					// 数据来源
					BaseDict bd = this.baseDictBiz.findBaseDictById(stu.getStudentDataSource());
					// 监控结果
					//MonitorResults mr = this.monitorResultsBiz.findbyresultid(stu.getLastMonitorResult());
					
					ExportFpdForChannelRebateSufficeShowBranchTemplate eFFCRSSBT = new ExportFpdForChannelRebateSufficeShowBranchTemplate();
					eFFCRSSBT.setNian(DateUtil.getDate(feePaymentDetail.getCreatedTime(), "yyyy"));
					eFFCRSSBT.setYue(DateUtil.getDate(feePaymentDetail.getCreatedTime(), "MM"));
					eFFCRSSBT.setRi(DateUtil.getDate(feePaymentDetail.getCreatedTime(), "dd"));
					eFFCRSSBT.setJiaoFeiDanHao(feePaymentDetail.getPaymentCode());
					eFFCRSSBT.setXingMing(stu.getName());
					eFFCRSSBT.setShenFenZhengHao(stu.getCertNo());
					eFFCRSSBT.setShouJi(stu.getMobile());
					eFFCRSSBT.setZuoJi(stu.getPhone());
					eFFCRSSBT.setXueXiZhongXin(feePaymentDetail.getBranchName());
					eFFCRSSBT.setYuanXiao(feePaymentDetail.getSchoolName());
					eFFCRSSBT.setPiCi(feePaymentDetail.getAcademyenrollbatchName());
					eFFCRSSBT.setCengCi(feePaymentDetail.getLevelName());
					eFFCRSSBT.setZhuanYe(feePaymentDetail.getMajorName());
					eFFCRSSBT.setZhaoShengTuJing(enrollmentsource==null?"":enrollmentsource.getName());
					eFFCRSSBT.setHeZuoFang(feePaymentDetail.getChannelName());
					eFFCRSSBT.setShiChangTuJing(basedict==null?"":basedict.getName());
					eFFCRSSBT.setShuJuLaiYuan(bd==null?"":bd.getName());
					eFFCRSSBT.setJiaoFeiKeMu(feePaymentDetail.getPaymentSubjectName());//缴费科目
					eFFCRSSBT.setShiJiaoJinE(feePaymentDetail.getJiaofeiValue()+"");
					eFFCRSSBT.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePaymentDetail.getFeeWayId()));
					eFFCRSSBT.setZhuangTai(ConstantsPaymentStatusMap.getCode(feePaymentDetail.getStatus()));
					eFFCRSSBT.setZongBuQueRenShiJian(DateUtil.getDate(feePaymentDetail.getCeduConfirmTime(), "yyyy-MM-dd"));
					eFFCRSSBT.setKaiKeZhuangTai(ConstantsStartCourseStatusMap.getCode(Constants.STU_IS_START_COURSE_TRUE));
					eFFCRSSBT.setZhaoShengLaiYuanFuHe(ConstantsChannelTypeCheckedMap.getCode(Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE));
					eFFCRSSBT.setBeiZhu("");
					eFFCRSSBTList.add(eFFCRSSBT);
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
				ex.exportExcel("导出结果", eFFCRSSBTList, os);
				// 关闭流
				os.close();
				eFFCRSSBTList.clear();
				os = null;
				$page++;
				only = false;
				if (feePaymentDetailList.size() < $pageSize) {
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

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePaymentDetail> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public OrderCeduChannel getOrderCeduChannel() {
		return orderCeduChannel;
	}

	public void setOrderCeduChannel(OrderCeduChannel orderCeduChannel) {
		this.orderCeduChannel = orderCeduChannel;
	}

	public String getFeepdIds() {
		return feepdIds;
	}

	public void setFeepdIds(String feepdIds) {
		this.feepdIds = feepdIds;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}
	
	
	
}
