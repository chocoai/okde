package net.cedu.action.finance.studentaccountmanagement;

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
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.common.Constants;
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
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.model.finance.ExportStuAccountAmTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生单纯充值情况查询ajax
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonListStudentSimplyRechargeAction extends BaseAction
{
	@Autowired
	private StudentAccountAmountManagementBiz saamBiz;    //学生账户biz
	@Autowired
	private TaskBiz taskBiz;
	// 分页结果
	private PageResult<StudentAccountAmountManagement> result = new PageResult<StudentAccountAmountManagement>();
	
	//查询 条件
	private Student student=new Student();//学生实体
	private String starttime; //开始时间
	private String endtime; //结束时间
	
	//统计总金额
	private String allStudentSimpleMoney;//总金额
	
	private String feeSubjectIds;//缴费科目Ids字符串
	private String allStuaaMoney;//统计学生充值金额
	private String stuxiaofeiMoney;//统计学生消费金额
	private String stuTuiFeiMoney;//统计学生退费金额
	private String stuNotUseMoney;//统计学生未使用金额
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private int levelId;//层次
	
	//**********************导出************************//
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private UserBiz userBiz;
	//导出的压缩包下载地址
	private String downloadUrl = "";
	//导出的最大数据量
	private int dataMaxCount = 0;
	
	/**
	 * 学生单纯充值数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_simple_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countStusaAjax() throws Exception {
		result.setRecordCount(saamBiz.findStudentSimpleRechargeCountByPage(student, starttime, endtime));
		return SUCCESS;
	}
	
	/**
	 * 学生单纯充值集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_simple_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String listStusaAjax() throws Exception {
		result.setList(saamBiz.findStudentSimpleRechargeListByPage(student, starttime, endtime, result));
		return SUCCESS;
	}
	
	/**
	 * 统计学生单纯充值金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_simple_account_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countssaamCountAllMoney() throws Exception {
		
		allStudentSimpleMoney=this.saamBiz.countStudentSimplyAccountMoney(student, starttime, endtime);
		return SUCCESS;
	}
	
	/**
	 * 学生充值数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_account_amount_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countStusaaAjax() throws Exception {
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		result.setRecordCount(saamBiz.findStudentRechargeCountByPage(student, starttime, endtime,feeSubjectIds));
		return SUCCESS;
	}
	
	/**
	 * 学生充值集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_account_amount_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String listStusaaAjax() throws Exception {
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		result.setList(saamBiz.findStudentRechargeListByPage(student, starttime, endtime,feeSubjectIds, result));
		return SUCCESS;
	}
	
	/**
	 * 统计学生充值金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_account_account_all_money_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countsaaamCountAllMoney() throws Exception {
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		//学生总充值金额
		allStuaaMoney=this.saamBiz.countStudentRechargeMoney(student, starttime, endtime, feeSubjectIds);
		//学生消费金额
		stuxiaofeiMoney=this.saamBiz.countStudentXiaoFeiMoney(student, starttime, endtime, feeSubjectIds);
		//学生退费金额
		stuTuiFeiMoney=this.saamBiz.countStudentTuiFeiMoney(student, starttime, endtime, feeSubjectIds);
		//学生未使用的金额
		stuNotUseMoney=new BigDecimal(allStuaaMoney).subtract(new BigDecimal(stuxiaofeiMoney)).subtract(new BigDecimal(stuTuiFeiMoney)).toString();
		return SUCCESS;
	}
	
	/**
	 * 导出学生充值集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_stu_account_amount_management_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportStuaamAjax() throws Exception
	{

		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		
		//检查导出结果是否为空
		int count = saamBiz.findStudentRechargeCountByPage(student, starttime, endtime,feeSubjectIds);
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
		List<StudentAccountAmountManagement> saamList = null;
		List<ExportStuAccountAmTemplate> exportStuAccountAmTemplateList=null;
		ExportStuAccountAmTemplate exportStuAccountAmTemplate=null;
		
		
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|费用科目查询|导出学生充值");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		
		
		// 创建导出的工具类对象
		ExcelExport<ExportStuAccountAmTemplate> ex = new ExcelExport<ExportStuAccountAmTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<StudentAccountAmountManagement> result = new PageResult<StudentAccountAmountManagement>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (saamList != null && saamList.size() != 0)) 
		{			
			result.setCurrentPageIndex($page);
			
			saamList = saamBiz.findStudentRechargeListByPage(student, starttime, endtime,feeSubjectIds, result);
			
			if(saamList!=null && saamList.size()!=0)
			{
				exportStuAccountAmTemplateList=new ArrayList<ExportStuAccountAmTemplate>();
				for (StudentAccountAmountManagement saam : saamList) 
				{					
					FeePayment feePayment=saam.getFeePayment();
					Student student=saam.getStudent();
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
					//招生途径
					EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
					//市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					
					exportStuAccountAmTemplate=new ExportStuAccountAmTemplate();
					exportStuAccountAmTemplate.setNian(DateUtil.dateToString(saam.getCreatedTime(),"yyyy"));//年
					exportStuAccountAmTemplate.setYue(DateUtil.dateToString(saam.getCreatedTime(),"MM"));
					exportStuAccountAmTemplate.setRi(DateUtil.dateToString(saam.getCreatedTime(),"dd"));
							
					exportStuAccountAmTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					User user=userBiz.findUserById(saam.getCreatorId());
					exportStuAccountAmTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
					exportStuAccountAmTemplate.setXueShengXingMing(saam.getStudentName());
					exportStuAccountAmTemplate.setZhaoShengPiCi(saam.getAcademyenrollbatchName());
					exportStuAccountAmTemplate.setYuanXiaoMingCheng(saam.getSchoolName());
					exportStuAccountAmTemplate.setCengCi(saam.getLevelName());
					exportStuAccountAmTemplate.setZhuanYe(saam.getMajorName());
					exportStuAccountAmTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
					exportStuAccountAmTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
					exportStuAccountAmTemplate.setShiChangTuJing(basedict==null?"未知":basedict.getName());//市场途径
					exportStuAccountAmTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
					exportStuAccountAmTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportStuAccountAmTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
					exportStuAccountAmTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

					//exportStuAccountAmTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
					
				
					exportStuAccountAmTemplate.setJiaoFeiDanHao(feePayment==null?"--":feePayment.getCode());
					if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
					{
						//报名费
						exportStuAccountAmTemplate.setFeiYongKeMu("报名费");//费用项目		
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_TEST)
					{
						//测试费
						exportStuAccountAmTemplate.setFeiYongKeMu("测试费");//费用项目	
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_TUITION)
					{
						//学费
						exportStuAccountAmTemplate.setFeiYongKeMu("学费");//费用项目
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_BOOK)
					{
						//教材费
						exportStuAccountAmTemplate.setFeiYongKeMu("教材费");//费用项目
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
					{
						//补考费
						exportStuAccountAmTemplate.setFeiYongKeMu("补考费");//费用项目
					}
					else
					{
						exportStuAccountAmTemplate.setFeiYongKeMu("其他");//费用项目
					}
					//类型
					if(saam.getTypes()==Constants.STATUS_RECHARGE)
					{
						exportStuAccountAmTemplate.setLeiXing("充值");
						exportStuAccountAmTemplate.setChongZhiJinE(saam.getAccountMoney().toString());
					}
					else if(saam.getTypes()==Constants.STATUS_CONSUMPTION)
					{
						exportStuAccountAmTemplate.setLeiXing("消费");
						exportStuAccountAmTemplate.setChongZhiJinE("-"+saam.getAccountMoney().toString());
					}
					else if(saam.getTypes()==Constants.STATUS_APPLY_CONSUMPTION_TRUE)
					{
						exportStuAccountAmTemplate.setLeiXing("退费");
						exportStuAccountAmTemplate.setChongZhiJinE("-"+saam.getAccountMoney().toString());
					}
					else
					{
						exportStuAccountAmTemplate.setLeiXing("未知");
						exportStuAccountAmTemplate.setChongZhiJinE(saam.getAccountMoney().toString());
					}
					exportStuAccountAmTemplate.setShouJuHaoMa(feePayment==null?"--":feePayment.getBarCode());//收据单号
					exportStuAccountAmTemplateList.add(exportStuAccountAmTemplate);
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
			ex.exportExcel("充值详情", exportStuAccountAmTemplateList, os);
			// 关闭流
			os.close();
			exportStuAccountAmTemplateList.clear();
			os = null;
			$page++;
			only = false;
			if (saamList.size() < $pageSize) {
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
	 * 导出学生充值集合(学习中心)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_stu_account_amount_management_ajax_branch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportStuaamAjaxBranch() throws Exception
	{

		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		
		//检查导出结果是否为空
		int count = saamBiz.findStudentRechargeCountByPage(student, starttime, endtime,feeSubjectIds);
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
		List<StudentAccountAmountManagement> saamList = null;
		List<ExportStuAccountAmTemplate> exportStuAccountAmTemplateList=null;
		ExportStuAccountAmTemplate exportStuAccountAmTemplate=null;
		
		
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|缴费退费|费用科目查询|导出学生充值");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		
		
		// 创建导出的工具类对象
		ExcelExport<ExportStuAccountAmTemplate> ex = new ExcelExport<ExportStuAccountAmTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<StudentAccountAmountManagement> result = new PageResult<StudentAccountAmountManagement>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (saamList != null && saamList.size() != 0)) 
		{			
			result.setCurrentPageIndex($page);
			
			saamList = saamBiz.findStudentRechargeListByPage(student, starttime, endtime,feeSubjectIds, result);
			
			if(saamList!=null && saamList.size()!=0)
			{
				exportStuAccountAmTemplateList=new ArrayList<ExportStuAccountAmTemplate>();
				for (StudentAccountAmountManagement saam : saamList) 
				{					
					FeePayment feePayment=saam.getFeePayment();
					Student student=saam.getStudent();
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
					//招生途径
					EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
					//市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					
					exportStuAccountAmTemplate=new ExportStuAccountAmTemplate();
					exportStuAccountAmTemplate.setNian(DateUtil.dateToString(saam.getCreatedTime(),"yyyy"));//年
					exportStuAccountAmTemplate.setYue(DateUtil.dateToString(saam.getCreatedTime(),"MM"));
					exportStuAccountAmTemplate.setRi(DateUtil.dateToString(saam.getCreatedTime(),"dd"));
							
					exportStuAccountAmTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					User user=userBiz.findUserById(saam.getCreatorId());
					exportStuAccountAmTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
					exportStuAccountAmTemplate.setXueShengXingMing(saam.getStudentName());
					exportStuAccountAmTemplate.setZhaoShengPiCi(saam.getAcademyenrollbatchName());
					exportStuAccountAmTemplate.setYuanXiaoMingCheng(saam.getSchoolName());
					exportStuAccountAmTemplate.setCengCi(saam.getLevelName());
					exportStuAccountAmTemplate.setZhuanYe(saam.getMajorName());
					exportStuAccountAmTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
					exportStuAccountAmTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
					exportStuAccountAmTemplate.setShiChangTuJing(basedict==null?"未知":basedict.getName());//市场途径
					exportStuAccountAmTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
					exportStuAccountAmTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportStuAccountAmTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
					exportStuAccountAmTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

					//exportStuAccountAmTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
					
				
					exportStuAccountAmTemplate.setJiaoFeiDanHao(feePayment==null?"--":feePayment.getCode());
					if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
					{
						//报名费
						exportStuAccountAmTemplate.setFeiYongKeMu("报名费");//费用项目		
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_TEST)
					{
						//测试费
						exportStuAccountAmTemplate.setFeiYongKeMu("测试费");//费用项目	
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_TUITION)
					{
						//学费
						exportStuAccountAmTemplate.setFeiYongKeMu("学费");//费用项目
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_BOOK)
					{
						//教材费
						exportStuAccountAmTemplate.setFeiYongKeMu("教材费");//费用项目
					}
					else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
					{
						//补考费
						exportStuAccountAmTemplate.setFeiYongKeMu("补考费");//费用项目
					}
					else
					{
						exportStuAccountAmTemplate.setFeiYongKeMu("其他");//费用项目
					}
					//类型
					if(saam.getTypes()==Constants.STATUS_RECHARGE)
					{
						exportStuAccountAmTemplate.setLeiXing("充值");
						exportStuAccountAmTemplate.setChongZhiJinE(saam.getAccountMoney().toString());
					}
					else if(saam.getTypes()==Constants.STATUS_CONSUMPTION)
					{
						exportStuAccountAmTemplate.setLeiXing("消费");
						exportStuAccountAmTemplate.setChongZhiJinE("-"+saam.getAccountMoney().toString());
					}
					else if(saam.getTypes()==Constants.STATUS_APPLY_CONSUMPTION_TRUE)
					{
						exportStuAccountAmTemplate.setLeiXing("退费");
						exportStuAccountAmTemplate.setChongZhiJinE("-"+saam.getAccountMoney().toString());
					}
					else
					{
						exportStuAccountAmTemplate.setLeiXing("未知");
						exportStuAccountAmTemplate.setChongZhiJinE(saam.getAccountMoney().toString());
					}					
					exportStuAccountAmTemplate.setShouJuHaoMa(feePayment==null?"--":feePayment.getBarCode());//收据单号
					exportStuAccountAmTemplateList.add(exportStuAccountAmTemplate);
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
			ex.exportExcel("充值详情", exportStuAccountAmTemplateList, os);
			// 关闭流
			os.close();
			exportStuAccountAmTemplateList.clear();
			os = null;
			$page++;
			only = false;
			if (saamList.size() < $pageSize) {
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
	
	public PageResult<StudentAccountAmountManagement> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentAccountAmountManagement> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
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

	public String getAllStudentSimpleMoney() {
		//转换金额
		allStudentSimpleMoney=MoneyUtil.formatMoney(allStudentSimpleMoney);
		return allStudentSimpleMoney;
	}

	public void setAllStudentSimpleMoney(String allStudentSimpleMoney) {
		this.allStudentSimpleMoney = allStudentSimpleMoney;
	}

	public String getFeeSubjectIds() {
		return feeSubjectIds;
	}

	public void setFeeSubjectIds(String feeSubjectIds) {
		this.feeSubjectIds = feeSubjectIds;
	}

	public String getAllStuaaMoney() {
		//转换金额
		allStuaaMoney=MoneyUtil.formatMoney(allStuaaMoney);
		return allStuaaMoney;
	}

	public void setAllStuaaMoney(String allStuaaMoney) {
		this.allStuaaMoney = allStuaaMoney;
	}
	
	public String getStuxiaofeiMoney() {
		//转换金额
		stuxiaofeiMoney=MoneyUtil.formatMoney(stuxiaofeiMoney);
		return stuxiaofeiMoney;
	}

	public void setStuxiaofeiMoney(String stuxiaofeiMoney) {
		this.stuxiaofeiMoney = stuxiaofeiMoney;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
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

	public String getStuTuiFeiMoney() {
		//转换金额
		stuTuiFeiMoney=MoneyUtil.formatMoney(stuTuiFeiMoney);
		return stuTuiFeiMoney;
	}

	public void setStuTuiFeiMoney(String stuTuiFeiMoney) {
		this.stuTuiFeiMoney = stuTuiFeiMoney;
	}

	public String getStuNotUseMoney() {
		//转换金额
		stuNotUseMoney=MoneyUtil.formatMoney(stuNotUseMoney);
		return stuNotUseMoney;
	}

	public void setStuNotUseMoney(String stuNotUseMoney) {
		this.stuNotUseMoney = stuNotUseMoney;
	}
	
	
	
}
