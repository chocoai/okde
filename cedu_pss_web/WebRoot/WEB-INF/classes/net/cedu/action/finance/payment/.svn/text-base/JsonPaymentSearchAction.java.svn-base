package net.cedu.action.finance.payment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.ConstantsChannelTypeCheckedMap;
import net.cedu.common.ConstantsFeeWayMap;
import net.cedu.common.ConstantsPaymentStatusMap;
import net.cedu.common.ConstantsStudentDataSourceMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;
import net.cedu.model.finance.ExportFeePaymentTemplate;
import net.cedu.model.finance.ExportPaymentTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单查询
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonPaymentSearchAction extends BaseAction {

	@Autowired
	private PaymentBiz paymentBiz;// 缴费单业务层接口
	@Autowired
	private AcademyLevelBiz academylevelBiz;// 院校层次关系_业务层接口
	
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private TaskBiz taskBiz;
	
	
	// 分页结果
	private PageResult<FeePayment> result = new PageResult<FeePayment>();

	// 查询条件
	private FeePayment feePayment; // 缴费单实体
	private Student student; // 学生实体
	private String feemoney; // 缴费金额
	private String starttime; // 开始时间
	private String endtime; // 结束时间
	private String globalids; // 全局批次集合

	// 统计金额
	@Autowired
	private FeePaymentBiz feePaymentBiz;// 缴费单_业务层接口
	private String allFeePaymentMoney;
	
	//导出的压缩包下载地址
	private String downloadUrl = "";
	//导出的最大数据量
	private int dataMaxCount = 0;
	
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	@Autowired 
	private FeeSubjectBiz feeSubjectBiz;//缴费科目业务层接口
	
	

	/**
	 * 缴费单查询数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_serach_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,feePayment, student, feemoney, starttime, endtime,student"
	}) })
	public String financePaymentCount() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询数量
		result.setRecordCount(paymentBiz.findFeePaymentCountBySearch(
				feePayment, student, feemoney, starttime, endtime));
		return SUCCESS;
	}

	/**
	 * 缴费单查询数量2
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_serach_page_ajax2", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentCount2() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询数量
		result.setRecordCount(paymentBiz.findFeePaymentCountBySearch(
				feePayment, student, feemoney, starttime, endtime, globalids));
		return SUCCESS;
	}

	/**
	 * 缴费单查询集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_payment_serach_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,feePayment, student, feemoney, starttime, endtime,student"
	}) })
	public String financePaymentList() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询集合
		result.setList(paymentBiz.findFeePaymentListBySearch(feePayment,
				student, feemoney, starttime, endtime, result));
		return SUCCESS;
	}

	/**
	 * 导出查询集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_payment_search_admin", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentSearchAdmin() throws Exception {

		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		
		//检查导出结果是否为空
		int count = paymentBiz.findFeePaymentCountBySearch(feePayment,student, feemoney, starttime, endtime);
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
		List<FeePayment> feePaymentList = null;
		List<ExportPaymentTemplate> exportPaymentTemplateList=null;
		ExportPaymentTemplate exportPaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|缴费单查询(总部)|导出缴费单详情");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		// 创建导出的工具类对象
		ExcelExport<ExportPaymentTemplate> ex = new ExcelExport<ExportPaymentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<FeePayment> result = new PageResult<FeePayment>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentList != null && feePaymentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			feePaymentList = paymentBiz.findFeePaymentListBySearch(feePayment,student, feemoney, starttime, endtime, result);
			
			if(feePaymentList!=null&&feePaymentList.size()!=0){
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePayment feePayment : feePaymentList) {
					List<FeePaymentDetail> feePaymentDetailList=feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
					if(feePaymentDetailList!=null){

						Student student=feePayment.getStudent();
						//充值帐户金额
						StudentAccountManagement studentAccountManagement=studentAccountManagementBiz.findStudentAccountManagementByStudentId(student.getId());
						//学习中心
						Branch branch=branchBiz.findBranchById(student.getBranchId());
						//招生途径
						EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
						//市场途径
						BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
						//合作方
						Channel channel=this.channelBiz.findChannel(student.getChannelId());
						
						for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
							exportPaymentTemplate=new ExportPaymentTemplate();
							exportPaymentTemplate.setNian(DateUtil.dateToString(feePayment.getCreatedTime(),"yyyy"));//年
							exportPaymentTemplate.setYue(DateUtil.dateToString(feePayment.getCreatedTime(),"MM"));
							exportPaymentTemplate.setRi(DateUtil.dateToString(feePayment.getCreatedTime(),"dd"));
							
							exportPaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
							//User user=userBiz.findUserById(feePayment.getCreatorId());
							User user=userBiz.findUserById(student.getUserId());
							exportPaymentTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
							exportPaymentTemplate.setXueShengXingMing(feePayment.getStudentName());
							exportPaymentTemplate.setZhaoShengPiCi(feePayment.getAcademyenrollbatchName());
							exportPaymentTemplate.setYuanXiaoMingCheng(feePayment.getSchoolName());
							exportPaymentTemplate.setCengCi(feePayment.getLevelName());
							exportPaymentTemplate.setZhuanYe(feePayment.getMajorName());
							exportPaymentTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
							exportPaymentTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
							exportPaymentTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
							exportPaymentTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
							exportPaymentTemplate.setLaiYuanFuHe(student==null?"未知":ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));//来源是否复核
							exportPaymentTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
							exportPaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
							exportPaymentTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
							exportPaymentTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

							exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
							
							//缴费单状态
							exportPaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(feePaymentDetail.getStatus()));
							//总部确认时间
							exportPaymentTemplate.setZongBuQueRenShiJian(feePaymentDetail.getCeduConfirmTime()==null?"--":feePaymentDetail.getCeduConfirmTime().toString().substring(0,10));	
							
							String youHUiZhuTi=";";
							if(feePaymentDetail.getAcademyDiscount()>0.00){//院校优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="院校优惠";
								}else{
									youHUiZhuTi+=";"+"院校优惠";
								}
							}
							if(feePaymentDetail.getBranchDiscount()>0.00){//中心优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="中心优惠";
								}else{
									youHUiZhuTi+=";"+"中心优惠";
								}
							}
							if(feePaymentDetail.getCeduDiscount()>0.00){//弘成优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="弘成优惠";
								}else{
									youHUiZhuTi+=";"+"弘成优惠";
								}
							}
							if(feePaymentDetail.getChannelDiscount()>0.00){//渠道优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="渠道优惠";
								}else{
									youHUiZhuTi+=";"+"渠道优惠";
								}
							}
							if(youHUiZhuTi.equals(";")){
								youHUiZhuTi="";
							}
							//缴费金额=实缴金额+使用充值金额-退费金额
							String jiaofeivalue=(new BigDecimal(feePaymentDetail.getAmountPaied()).add(new BigDecimal(feePaymentDetail.getRechargeAmount())).subtract(new BigDecimal(feePaymentDetail.getRefundAmount()))).toString();
							exportPaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
							if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION){//学费
								exportPaymentTemplate.setFeiYongKeMu("报名费");//费用项目
								exportPaymentTemplate.setYiJiaoNaBaoMingFei(jiaofeivalue);//报名费
								
								exportPaymentTemplate.setYouHuiXiangMu("报名费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST){//学费
								exportPaymentTemplate.setFeiYongKeMu("测试费");//费用项目
								exportPaymentTemplate.setYiJiaoNaCeShiFei(jiaofeivalue);//测试费
								
								exportPaymentTemplate.setYouHuiXiangMu("测试费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION){//学费
								exportPaymentTemplate.setFeiYongKeMu("学费");//费用项目
								exportPaymentTemplate.setYiJiaoNaXueFei(jiaofeivalue);//学费
								
								exportPaymentTemplate.setYouHuiXiangMu("学费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK){//学费
								exportPaymentTemplate.setFeiYongKeMu("教材费");//费用项目
								exportPaymentTemplate.setYiJiaoNaJiaoCaiFei(jiaofeivalue);//教材费
								
								exportPaymentTemplate.setYouHuiXiangMu("教材费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
							{
								//补考费
								exportPaymentTemplate.setFeiYongKeMu("补考费");//费用项目
								exportPaymentTemplate.setYiJiaoNaBuKaoFei(jiaofeivalue);//补考费
										
								exportPaymentTemplate.setYouHuiXiangMu("补考费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REHABILITATION)
							{
								//重修费
								exportPaymentTemplate.setFeiYongKeMu("重修费");//费用项目
								exportPaymentTemplate.setYiJiaoNaChongXiuFei(jiaofeivalue);//重修费
										
								exportPaymentTemplate.setYouHuiXiangMu("重修费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_INCIDENTALS)
							{
								//杂费
								exportPaymentTemplate.setFeiYongKeMu("杂费");//费用项目
								exportPaymentTemplate.setYiJiaoNaZaFei(jiaofeivalue);//杂费
										
								exportPaymentTemplate.setYouHuiXiangMu("杂费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_EXAM_TRAINING)
							{
								//统考培训费
								exportPaymentTemplate.setFeiYongKeMu("统考培训费");//费用项目
								exportPaymentTemplate.setYiJiaoNaTongKaoPeiXunFei(jiaofeivalue);//统考培训费费
										
								exportPaymentTemplate.setYouHuiXiangMu("统考培训费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_IMAGE_ACQUISITION)
							{
								//图像采集费
								exportPaymentTemplate.setFeiYongKeMu("图像采集费");//费用项目
								exportPaymentTemplate.setYiJiaoNaTuXiangCaiJiFei(jiaofeivalue);//图像采集费
										
								exportPaymentTemplate.setYouHuiXiangMu("图像采集费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else{
								exportPaymentTemplate.setFeiYongKeMu("未知");//费用项目
								
								exportPaymentTemplate.setYouHuiXiangMu("未知");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi("");//优惠主体
							}
							exportPaymentTemplate.setChongZhiZhangHuJinE(studentAccountManagement==null?"0.00":(studentAccountManagement.getAccountBalance()+""));//充值账户金额
							exportPaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
							exportPaymentTemplateList.add(exportPaymentTemplate);
						}
					}
				}
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
			ex.exportExcel("缴费详情", exportPaymentTemplateList, os);
			// 关闭流
			os.close();
			exportPaymentTemplateList.clear();
			os = null;
			$page++;
			only = false;
			if (feePaymentList.size() < $pageSize) {
				break;
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
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}
	
	/**
	 * 导出查询集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_payment_search_admin_day", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentSearchAdminDay() throws Exception {

		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		
		//检查导出结果是否为空
		int count = paymentBiz.findFeePaymentCountBySearch(feePayment,student, feemoney, starttime, endtime);
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
		List<FeePayment> feePaymentList = null;
		List<ExportPaymentTemplate> exportPaymentTemplateList=null;
		ExportPaymentTemplate exportPaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|日收款单查询|导出缴费单详情");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		// 创建导出的工具类对象
		ExcelExport<ExportPaymentTemplate> ex = new ExcelExport<ExportPaymentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<FeePayment> result = new PageResult<FeePayment>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentList != null && feePaymentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			feePaymentList = paymentBiz.findFeePaymentListBySearch(feePayment,student, feemoney, starttime, endtime, result);
			
			if(feePaymentList!=null&&feePaymentList.size()!=0){
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePayment feePayment : feePaymentList) {
					List<FeePaymentDetail> feePaymentDetailList=feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
					if(feePaymentDetailList!=null){

						Student student=feePayment.getStudent();
						//充值帐户金额
						StudentAccountManagement studentAccountManagement=studentAccountManagementBiz.findStudentAccountManagementByStudentId(student.getId());
						//学习中心
						Branch branch=branchBiz.findBranchById(student.getBranchId());
						//招生途径
						EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
						//市场途径
						BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
						//合作方
						Channel channel=this.channelBiz.findChannel(student.getChannelId());
						
						for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
							exportPaymentTemplate=new ExportPaymentTemplate();
							exportPaymentTemplate.setNian(DateUtil.dateToString(feePayment.getCreatedTime(),"yyyy"));//年
							exportPaymentTemplate.setYue(DateUtil.dateToString(feePayment.getCreatedTime(),"MM"));
							exportPaymentTemplate.setRi(DateUtil.dateToString(feePayment.getCreatedTime(),"dd"));
							
							exportPaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
							//User user=userBiz.findUserById(feePayment.getCreatorId());
							User user=userBiz.findUserById(student.getUserId());
							exportPaymentTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
							exportPaymentTemplate.setXueShengXingMing(feePayment.getStudentName());
							exportPaymentTemplate.setZhaoShengPiCi(feePayment.getAcademyenrollbatchName());
							exportPaymentTemplate.setYuanXiaoMingCheng(feePayment.getSchoolName());
							exportPaymentTemplate.setCengCi(feePayment.getLevelName());
							exportPaymentTemplate.setZhuanYe(feePayment.getMajorName());
							exportPaymentTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
							exportPaymentTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
							exportPaymentTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
							exportPaymentTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
							exportPaymentTemplate.setLaiYuanFuHe(student==null?"未知":ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));//来源是否复核
							exportPaymentTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
							exportPaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
							exportPaymentTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
							exportPaymentTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

							exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
							
							//缴费单状态
							exportPaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(feePaymentDetail.getStatus()));
							//总部确认时间
							exportPaymentTemplate.setZongBuQueRenShiJian(feePaymentDetail.getCeduConfirmTime()==null?"--":feePaymentDetail.getCeduConfirmTime().toString().substring(0,10));	
							
							String youHUiZhuTi=";";
							if(feePaymentDetail.getAcademyDiscount()>0.00){//院校优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="院校优惠";
								}else{
									youHUiZhuTi+=";"+"院校优惠";
								}
							}
							if(feePaymentDetail.getBranchDiscount()>0.00){//中心优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="中心优惠";
								}else{
									youHUiZhuTi+=";"+"中心优惠";
								}
							}
							if(feePaymentDetail.getCeduDiscount()>0.00){//弘成优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="弘成优惠";
								}else{
									youHUiZhuTi+=";"+"弘成优惠";
								}
							}
							if(feePaymentDetail.getChannelDiscount()>0.00){//渠道优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="渠道优惠";
								}else{
									youHUiZhuTi+=";"+"渠道优惠";
								}
							}
							if(youHUiZhuTi.equals(";")){
								youHUiZhuTi="";
							}
							//缴费金额=实缴金额+使用充值金额-退费金额
							String jiaofeivalue=(new BigDecimal(feePaymentDetail.getAmountPaied()).add(new BigDecimal(feePaymentDetail.getRechargeAmount())).subtract(new BigDecimal(feePaymentDetail.getRefundAmount()))).toString();
							exportPaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
							if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION){//学费
								exportPaymentTemplate.setFeiYongKeMu("报名费");//费用项目
								exportPaymentTemplate.setYiJiaoNaBaoMingFei(jiaofeivalue);//报名费
								
								exportPaymentTemplate.setYouHuiXiangMu("报名费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST){//学费
								exportPaymentTemplate.setFeiYongKeMu("测试费");//费用项目
								exportPaymentTemplate.setYiJiaoNaCeShiFei(jiaofeivalue);//测试费
								
								exportPaymentTemplate.setYouHuiXiangMu("测试费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION){//学费
								exportPaymentTemplate.setFeiYongKeMu("学费");//费用项目
								exportPaymentTemplate.setYiJiaoNaXueFei(jiaofeivalue);//学费
								
								exportPaymentTemplate.setYouHuiXiangMu("学费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK){//学费
								exportPaymentTemplate.setFeiYongKeMu("教材费");//费用项目
								exportPaymentTemplate.setYiJiaoNaJiaoCaiFei(jiaofeivalue);//教材费
								
								exportPaymentTemplate.setYouHuiXiangMu("教材费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
							{
								//补考费
								exportPaymentTemplate.setFeiYongKeMu("补考费");//费用项目
								exportPaymentTemplate.setYiJiaoNaBuKaoFei(jiaofeivalue);//补考费
										
								exportPaymentTemplate.setYouHuiXiangMu("补考费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REHABILITATION)
							{
								//重修费
								exportPaymentTemplate.setFeiYongKeMu("重修费");//费用项目
								exportPaymentTemplate.setYiJiaoNaChongXiuFei(jiaofeivalue);//重修费
										
								exportPaymentTemplate.setYouHuiXiangMu("重修费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_INCIDENTALS)
							{
								//杂费
								exportPaymentTemplate.setFeiYongKeMu("杂费");//费用项目
								exportPaymentTemplate.setYiJiaoNaZaFei(jiaofeivalue);//杂费
										
								exportPaymentTemplate.setYouHuiXiangMu("杂费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_EXAM_TRAINING)
							{
								//统考培训费
								exportPaymentTemplate.setFeiYongKeMu("统考培训费");//费用项目
								exportPaymentTemplate.setYiJiaoNaTongKaoPeiXunFei(jiaofeivalue);//统考培训费费
										
								exportPaymentTemplate.setYouHuiXiangMu("统考培训费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_IMAGE_ACQUISITION)
							{
								//图像采集费
								exportPaymentTemplate.setFeiYongKeMu("图像采集费");//费用项目
								exportPaymentTemplate.setYiJiaoNaTuXiangCaiJiFei(jiaofeivalue);//图像采集费
										
								exportPaymentTemplate.setYouHuiXiangMu("图像采集费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else{
								exportPaymentTemplate.setFeiYongKeMu("未知");//费用项目
								
								exportPaymentTemplate.setYouHuiXiangMu("未知");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi("");//优惠主体
							}
							exportPaymentTemplate.setChongZhiZhangHuJinE(studentAccountManagement==null?"0.00":(studentAccountManagement.getAccountBalance()+""));//充值账户金额
							exportPaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
							exportPaymentTemplateList.add(exportPaymentTemplate);
						}
					}
				}
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
			ex.exportExcel("缴费详情", exportPaymentTemplateList, os);
			// 关闭流
			os.close();
			exportPaymentTemplateList.clear();
			os = null;
			$page++;
			only = false;
			if (feePaymentList.size() < $pageSize) {
				break;
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
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}
	
	/**
	 * 导出预缴费单集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_yujiaofei_payment_search_admin", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportYujiaofeiPaymentSearchAdmin() throws Exception {

		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_PRE_BILLING);//预缴费单
		
		//检查导出结果是否为空
		int count = paymentBiz.findFeePaymentCountBySearch(feePayment,student, feemoney, starttime, endtime);
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
		List<FeePayment> feePaymentList = null;
		List<ExportFeePaymentTemplate> exportFeePaymentTemplateList=null;
		ExportFeePaymentTemplate exportFeePaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|缴费单查询(总部)|导出预缴费单");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		
		// 创建导出的工具类对象
		ExcelExport<ExportFeePaymentTemplate> ex = new ExcelExport<ExportFeePaymentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<FeePayment> result = new PageResult<FeePayment>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentList != null && feePaymentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			feePaymentList = paymentBiz.findFeePaymentListBySearch(feePayment,student, feemoney, starttime, endtime, result);
			
			if(feePaymentList!=null&&feePaymentList.size()!=0){
				exportFeePaymentTemplateList=new ArrayList<ExportFeePaymentTemplate>();
				for (FeePayment feePayment : feePaymentList)
				{
			
					Student student=feePayment.getStudent();
					//充值帐户金额
					StudentAccountManagement studentAccountManagement=studentAccountManagementBiz.findStudentAccountManagementByStudentId(student.getId());
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
						
					exportFeePaymentTemplate=new ExportFeePaymentTemplate();
					
					exportFeePaymentTemplate.setJiaoFeiDanId(feePayment.getId()+"");//缴费单Id
					exportFeePaymentTemplate.setNian(DateUtil.dateToString(feePayment.getCreatedTime(),"yyyy"));//年
					exportFeePaymentTemplate.setYue(DateUtil.dateToString(feePayment.getCreatedTime(),"MM"));
					exportFeePaymentTemplate.setRi(DateUtil.dateToString(feePayment.getCreatedTime(),"dd"));
							
					exportFeePaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					exportFeePaymentTemplate.setXueShengXingMing(feePayment.getStudentName());
					exportFeePaymentTemplate.setZhaoShengPiCi(feePayment.getAcademyenrollbatchName());
					exportFeePaymentTemplate.setYuanXiaoMingCheng(feePayment.getSchoolName());
					exportFeePaymentTemplate.setCengCi(feePayment.getLevelName());
					exportFeePaymentTemplate.setZhuanYe(feePayment.getMajorName());
							
							
					exportFeePaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportFeePaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID	
					//缴费单状态
					exportFeePaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(feePayment.getStatus()));
					exportFeePaymentTemplate.setJiaoFeiDanLeiBie("预缴费单");		
					exportFeePaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
					
					//充值金额明细
					List<StudentAccountAmountManagement> saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(feePayment.getId());
					if(saamlist!=null && saamlist.size()>0)
					{
						String fsnames="";
						for(StudentAccountAmountManagement saam:saamlist)
						{
							FeeSubject fs=this.feeSubjectBiz.findFeeSubjectById(saam.getFeeSubject());
							if(fs!=null)
							{
								fsnames+=fs.getName()+";";
							}
						}
						if(!fsnames.equals("") && fsnames.length()>0)
						{
							fsnames=fsnames.substring(0,(fsnames.length()-1));
						}
						exportFeePaymentTemplate.setFeiYongKeMu(fsnames);
					}
					exportFeePaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
					
					exportFeePaymentTemplate.setJiaoFeiJinE(feePayment.getFeePayment()+"");
					exportFeePaymentTemplate.setChongZhiJinE(feePayment.getRechargeAmount()+"");
					exportFeePaymentTemplate.setZongJinE(feePayment.getTotalAmount()+"");
					
					exportFeePaymentTemplateList.add(exportFeePaymentTemplate);
				}
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
			ex.exportExcel("预缴费单详情", exportFeePaymentTemplateList, os);
			// 关闭流
			os.close();
			exportFeePaymentTemplateList.clear();
			os = null;
			$page++;
			only = false;
			if (feePaymentList.size() < $pageSize) {
				break;
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
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}
	
	/**
	 * 导出预缴费单集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_yujiaofei_payment_search_admin_day", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportYujiaofeiPaymentSearchAdminDay() throws Exception {

		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_PRE_BILLING);//预缴费单
		
		//检查导出结果是否为空
		int count = paymentBiz.findFeePaymentCountBySearch(feePayment,student, feemoney, starttime, endtime);
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
		List<FeePayment> feePaymentList = null;
		List<ExportFeePaymentTemplate> exportFeePaymentTemplateList=null;
		ExportFeePaymentTemplate exportFeePaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|日收款单查询|导出预缴费单");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		
		// 创建导出的工具类对象
		ExcelExport<ExportFeePaymentTemplate> ex = new ExcelExport<ExportFeePaymentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<FeePayment> result = new PageResult<FeePayment>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentList != null && feePaymentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			feePaymentList = paymentBiz.findFeePaymentListBySearch(feePayment,student, feemoney, starttime, endtime, result);
			
			if(feePaymentList!=null&&feePaymentList.size()!=0){
				exportFeePaymentTemplateList=new ArrayList<ExportFeePaymentTemplate>();
				for (FeePayment feePayment : feePaymentList)
				{
			
					Student student=feePayment.getStudent();
					//充值帐户金额
					StudentAccountManagement studentAccountManagement=studentAccountManagementBiz.findStudentAccountManagementByStudentId(student.getId());
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
						
					exportFeePaymentTemplate=new ExportFeePaymentTemplate();
					
					exportFeePaymentTemplate.setJiaoFeiDanId(feePayment.getId()+"");//缴费单Id
					exportFeePaymentTemplate.setNian(DateUtil.dateToString(feePayment.getCreatedTime(),"yyyy"));//年
					exportFeePaymentTemplate.setYue(DateUtil.dateToString(feePayment.getCreatedTime(),"MM"));
					exportFeePaymentTemplate.setRi(DateUtil.dateToString(feePayment.getCreatedTime(),"dd"));
							
					exportFeePaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					exportFeePaymentTemplate.setXueShengXingMing(feePayment.getStudentName());
					exportFeePaymentTemplate.setZhaoShengPiCi(feePayment.getAcademyenrollbatchName());
					exportFeePaymentTemplate.setYuanXiaoMingCheng(feePayment.getSchoolName());
					exportFeePaymentTemplate.setCengCi(feePayment.getLevelName());
					exportFeePaymentTemplate.setZhuanYe(feePayment.getMajorName());
							
							
					exportFeePaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportFeePaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID	
					//缴费单状态
					exportFeePaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(feePayment.getStatus()));
					exportFeePaymentTemplate.setJiaoFeiDanLeiBie("预缴费单");		
					exportFeePaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
					
					//充值金额明细
					List<StudentAccountAmountManagement> saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(feePayment.getId());
					if(saamlist!=null && saamlist.size()>0)
					{
						String fsnames="";
						for(StudentAccountAmountManagement saam:saamlist)
						{
							FeeSubject fs=this.feeSubjectBiz.findFeeSubjectById(saam.getFeeSubject());
							if(fs!=null)
							{
								fsnames+=fs.getName()+";";
							}
						}
						if(!fsnames.equals("") && fsnames.length()>0)
						{
							fsnames=fsnames.substring(0,(fsnames.length()-1));
						}
						exportFeePaymentTemplate.setFeiYongKeMu(fsnames);
					}
					exportFeePaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
					
					exportFeePaymentTemplate.setJiaoFeiJinE(feePayment.getFeePayment()+"");
					exportFeePaymentTemplate.setChongZhiJinE(feePayment.getRechargeAmount()+"");
					exportFeePaymentTemplate.setZongJinE(feePayment.getTotalAmount()+"");
					
					exportFeePaymentTemplateList.add(exportFeePaymentTemplate);
				}
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
			ex.exportExcel("预缴费单详情", exportFeePaymentTemplateList, os);
			// 关闭流
			os.close();
			exportFeePaymentTemplateList.clear();
			os = null;
			$page++;
			only = false;
			if (feePaymentList.size() < $pageSize) {
				break;
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
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}

	/**
	 * 缴费单查询集合2
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_payment_serach_page_ajax2", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentList2() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		// 查询集合
		result.setList(paymentBiz.findFeePaymentListBySearch(feePayment,
				student, feemoney, starttime, endtime, globalids, result));
		return SUCCESS;
	}
	
	/**
	 * 导出查询集合2
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_payment_search_admin2", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentSearchAdmin2() throws Exception {

		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		
		//检查导出结果是否为空
		int count = paymentBiz.findFeePaymentCountBySearch(feePayment,student, feemoney, starttime, endtime, globalids);
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
		List<FeePayment> feePaymentList = null;
		List<ExportPaymentTemplate> exportPaymentTemplateList=null;
		ExportPaymentTemplate exportPaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|缴费单查询2(总部)|导出缴费单详情");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}
		String path = Config.getProperty("export.excel.tmp")+ctm;
		// 创建导出的工具类对象
		ExcelExport<ExportPaymentTemplate> ex = new ExcelExport<ExportPaymentTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<FeePayment> result = new PageResult<FeePayment>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentList != null && feePaymentList.size() != 0)) {
			
			result.setCurrentPageIndex($page);
			
			feePaymentList = paymentBiz.findFeePaymentListBySearch(feePayment,student, feemoney, starttime, endtime, globalids, result);
			
			if(feePaymentList!=null&&feePaymentList.size()!=0){
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePayment feePayment : feePaymentList) {
					List<FeePaymentDetail> feePaymentDetailList=feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
					if(feePaymentDetailList!=null){

						Student student=feePayment.getStudent();
						//充值帐户金额
						StudentAccountManagement studentAccountManagement=studentAccountManagementBiz.findStudentAccountManagementByStudentId(student.getId());
						//学习中心
						Branch branch=branchBiz.findBranchById(student.getBranchId());
						//招生途径
						EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
						//市场途径
						BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
						//合作方
						Channel channel=this.channelBiz.findChannel(student.getChannelId());
						
						for (FeePaymentDetail feePaymentDetail : feePaymentDetailList) {
							exportPaymentTemplate=new ExportPaymentTemplate();
							exportPaymentTemplate.setNian(DateUtil.dateToString(feePayment.getCreatedTime(),"yyyy"));//年
							exportPaymentTemplate.setYue(DateUtil.dateToString(feePayment.getCreatedTime(),"MM"));
							exportPaymentTemplate.setRi(DateUtil.dateToString(feePayment.getCreatedTime(),"dd"));
							
							exportPaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
							//User user=userBiz.findUserById(feePayment.getCreatorId());
							User user=userBiz.findUserById(student.getUserId());
							exportPaymentTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
							exportPaymentTemplate.setXueShengXingMing(feePayment.getStudentName());
							exportPaymentTemplate.setZhaoShengPiCi(feePayment.getAcademyenrollbatchName());
							exportPaymentTemplate.setYuanXiaoMingCheng(feePayment.getSchoolName());
							exportPaymentTemplate.setCengCi(feePayment.getLevelName());
							exportPaymentTemplate.setZhuanYe(feePayment.getMajorName());
							exportPaymentTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
							exportPaymentTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
							exportPaymentTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
							exportPaymentTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
							exportPaymentTemplate.setLaiYuanFuHe(student==null?"未知":ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));//来源是否复核
							exportPaymentTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
							exportPaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
							exportPaymentTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
							exportPaymentTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

							exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
							
							//缴费单状态
							exportPaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(feePaymentDetail.getStatus()));
							//总部确认时间
							exportPaymentTemplate.setZongBuQueRenShiJian(feePaymentDetail.getCeduConfirmTime()==null?"--":feePaymentDetail.getCeduConfirmTime().toString().substring(0,10));
							
							String youHUiZhuTi=";";
							if(feePaymentDetail.getAcademyDiscount()>0.00){//院校优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="院校优惠";
								}else{
									youHUiZhuTi+=";"+"院校优惠";
								}
							}
							if(feePaymentDetail.getBranchDiscount()>0.00){//中心优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="中心优惠";
								}else{
									youHUiZhuTi+=";"+"中心优惠";
								}
							}
							if(feePaymentDetail.getCeduDiscount()>0.00){//弘成优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="弘成优惠";
								}else{
									youHUiZhuTi+=";"+"弘成优惠";
								}
							}
							if(feePaymentDetail.getChannelDiscount()>0.00){//渠道优惠
								if(youHUiZhuTi.equals(";")){
									youHUiZhuTi="渠道优惠";
								}else{
									youHUiZhuTi+=";"+"渠道优惠";
								}
							}
							if(youHUiZhuTi.equals(";")){
								youHUiZhuTi="";
							}
							//缴费金额=实缴金额+使用充值金额-退费金额
							String jiaofeivalue=(new BigDecimal(feePaymentDetail.getAmountPaied()).add(new BigDecimal(feePaymentDetail.getRechargeAmount())).subtract(new BigDecimal(feePaymentDetail.getRefundAmount()))).toString();
							exportPaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
							if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION){//学费
								exportPaymentTemplate.setFeiYongKeMu("报名费");//费用项目
								exportPaymentTemplate.setYiJiaoNaBaoMingFei(jiaofeivalue);//报名费
								
								exportPaymentTemplate.setYouHuiXiangMu("报名费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST){//学费
								exportPaymentTemplate.setFeiYongKeMu("测试费");//费用项目
								exportPaymentTemplate.setYiJiaoNaCeShiFei(jiaofeivalue);//测试费
								
								exportPaymentTemplate.setYouHuiXiangMu("测试费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION){//学费
								exportPaymentTemplate.setFeiYongKeMu("学费");//费用项目
								exportPaymentTemplate.setYiJiaoNaXueFei(jiaofeivalue);//学费
								
								exportPaymentTemplate.setYouHuiXiangMu("学费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
								
							}else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK){//学费
								exportPaymentTemplate.setFeiYongKeMu("教材费");//费用项目
								exportPaymentTemplate.setYiJiaoNaJiaoCaiFei(jiaofeivalue);//教材费
								
								exportPaymentTemplate.setYouHuiXiangMu("教材费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
							{
								//补考费
								exportPaymentTemplate.setFeiYongKeMu("补考费");//费用项目
								exportPaymentTemplate.setYiJiaoNaBuKaoFei(jiaofeivalue);//补考费
										
								exportPaymentTemplate.setYouHuiXiangMu("补考费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REHABILITATION)
							{
								//重修费
								exportPaymentTemplate.setFeiYongKeMu("重修费");//费用项目
								exportPaymentTemplate.setYiJiaoNaChongXiuFei(jiaofeivalue);//重修费
										
								exportPaymentTemplate.setYouHuiXiangMu("重修费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_INCIDENTALS)
							{
								//杂费
								exportPaymentTemplate.setFeiYongKeMu("杂费");//费用项目
								exportPaymentTemplate.setYiJiaoNaZaFei(jiaofeivalue);//杂费
										
								exportPaymentTemplate.setYouHuiXiangMu("杂费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_EXAM_TRAINING)
							{
								//统考培训费
								exportPaymentTemplate.setFeiYongKeMu("统考培训费");//费用项目
								exportPaymentTemplate.setYiJiaoNaTongKaoPeiXunFei(jiaofeivalue);//统考培训费费
										
								exportPaymentTemplate.setYouHuiXiangMu("统考培训费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else if(feePaymentDetail.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_IMAGE_ACQUISITION)
							{
								//图像采集费
								exportPaymentTemplate.setFeiYongKeMu("图像采集费");//费用项目
								exportPaymentTemplate.setYiJiaoNaTuXiangCaiJiFei(jiaofeivalue);//图像采集费
										
								exportPaymentTemplate.setYouHuiXiangMu("图像采集费");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
								exportPaymentTemplate.setYouHuiJinE(feePaymentDetail.getDiscountAmount()+"");//优惠金额
							}
							else{
								exportPaymentTemplate.setFeiYongKeMu("未知");//费用项目
								
								exportPaymentTemplate.setYouHuiXiangMu("未知");//优惠项目
								exportPaymentTemplate.setYouHuiZhuTi("");//优惠主体
							}
							exportPaymentTemplate.setChongZhiZhangHuJinE(studentAccountManagement==null?"0.00":(studentAccountManagement.getAccountBalance()+""));//充值账户金额
							exportPaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
							exportPaymentTemplateList.add(exportPaymentTemplate);
						}
					}
				}
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
			ex.exportExcel("缴费详情", exportPaymentTemplateList, os);
			// 关闭流
			os.close();
			os = null;
			$page++;
			only = false;
			if (feePaymentList.size() < $pageSize) {
				break;
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
		System.out.println(downloadUrl=path+ ".zip");
		return SUCCESS;
	}
	
	/**
	 * 统计缴费单金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_serach_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentCountAllMoney() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		allFeePaymentMoney = this.feePaymentBiz
				.countFeePaymentAllMoneyByConditions(feePayment, student,
						feemoney, starttime, endtime);
		return SUCCESS;
	}

	/**
	 * 统计缴费单金额2
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_payment_serach_all_money_ajax2", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String financePaymentCountAllMoney2() throws Exception {
		if (student != null) {
			// 层次
			if (student.getLevelId() != 0) {
				AcademyLevel academyLevel = academylevelBiz.findById(student
						.getLevelId());
				student.setLevelId(academyLevel.getLevelId());
			}
		}
		allFeePaymentMoney = this.feePaymentBiz
				.countFeePaymentAllMoneyByConditions(feePayment, student,
						feemoney, starttime, endtime, globalids);
		return SUCCESS;
	}

	public PageResult<FeePayment> getResult() {
		return result;
	}

	public void setResult(PageResult<FeePayment> result) {
		this.result = result;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getFeemoney() {
		return feemoney;
	}

	public void setFeemoney(String feemoney) {
		this.feemoney = feemoney;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getAllFeePaymentMoney() {
		//转换金额
		allFeePaymentMoney=MoneyUtil.formatMoney(allFeePaymentMoney);
		return allFeePaymentMoney;
	}

	public void setAllFeePaymentMoney(String allFeePaymentMoney) {
		this.allFeePaymentMoney = allFeePaymentMoney;
	}

	public String getGlobalids() {
		return globalids;
	}

	public void setGlobalids(String globalids) {
		this.globalids = globalids;
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
