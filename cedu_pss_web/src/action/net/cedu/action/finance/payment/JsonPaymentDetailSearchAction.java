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
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
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
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.finance.ExportPaymentTemplate;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生缴费单明细查询ajax
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonPaymentDetailSearchAction extends BaseAction
{
	@Autowired
	private StudentAccountAmountManagementBiz saamBiz;    //学生账户biz
	@Autowired
	private TaskBiz taskBiz;
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private ChannelBiz channelBiz;
	
	//查询 条件
	private Student student=new Student();//学生实体
	private String starttime; //开始时间
	private String endtime; //结束时间
	private String feeSubjectIds;//缴费科目Ids字符串
	private int status;//缴费单明细状态
	private String ccStartTime;//总部确认时间起
	private String ccEndTime;//总部确认时间止
	private int feeWayId;//缴费方式
	
	private String allStuAllMoney;//统计学生所有金额
	private String allStuFpdMoney;//统计学生缴费单明细金额
	private String stuShiYongChongzhiMoney;//统计学生使用充值金额
	private String allShiJIaoMoney;//统计学生实缴金额
	
	private String stuaaMoney;//统计学生充值金额
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
	 * 学生缴费单明细数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_fee_payment_detail_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countfpdsAjax() throws Exception {
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		result.setRecordCount(saamBiz.findFeePaymentDetailCountByPageForSearch(student, starttime, endtime,feeSubjectIds,status,ccStartTime,ccEndTime,feeWayId));
		return SUCCESS;
	}
	
	/**
	 * 学生缴费单明细集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_fee_payment_detail_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String listfpdsAjax() throws Exception {
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		result.setList(saamBiz.findFeePaymentDetailListByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId, result));
		return SUCCESS;
	}
	
	/**
	 * 统计学生所有金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_fpd_all_money_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countFpdmsCountAllMoney() throws Exception {
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		allShiJIaoMoney=this.saamBiz.countStudentFeePaymentDetailMoney(student, starttime, endtime, feeSubjectIds,status,ccStartTime,ccEndTime,feeWayId);
		//String stuaaMoney=this.saamBiz.countStudentRechargeMoney(student, starttime, endtime, feeSubjectIds);
		//学生总充值金额
		stuaaMoney=this.saamBiz.countStudentRechargeMoney(student, starttime, endtime, feeSubjectIds);
		//学生消费金额
		stuxiaofeiMoney=this.saamBiz.countStudentXiaoFeiMoney(student, starttime, endtime, feeSubjectIds);
		//学生退费金额
		stuTuiFeiMoney=this.saamBiz.countStudentTuiFeiMoney(student, starttime, endtime, feeSubjectIds);
		//学生未使用的金额
		stuNotUseMoney=new BigDecimal(stuaaMoney).subtract(new BigDecimal(stuxiaofeiMoney)).subtract(new BigDecimal(stuTuiFeiMoney)).toString();
		
		BigDecimal fpdm=new BigDecimal(0);
		BigDecimal stum=new BigDecimal(0);
		
		if(allShiJIaoMoney!=null && !allShiJIaoMoney.equals(""))
		{
			fpdm=new BigDecimal(allShiJIaoMoney);
		}
		if(stuNotUseMoney!=null && !stuNotUseMoney.equals(""))
		{
			stum=new BigDecimal(stuNotUseMoney);
		}
		allStuAllMoney=fpdm.add(stum).toString();
		return SUCCESS;
	}
	
	/**
	 * 统计学生缴费充值金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_fpd_money_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countFpdallCountAllMoney() throws Exception {
		if(levelId!=0)
		{
			student.setLevelId(this.academyLevelBiz.findById(levelId).getLevelId());
		}
		if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
		{
			feeSubjectIds=feeSubjectIds.substring(0,(feeSubjectIds.length()-1));
		}
		//实缴金额(+使用的充值金额-退费金额）
		allShiJIaoMoney=this.saamBiz.countStudentFeePaymentDetailMoney(student, starttime, endtime, feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId);
		//使用充值金额
		stuShiYongChongzhiMoney=this.saamBiz.countStudentFeePaymentDetailShiYongChongZhiMoney(student, starttime, endtime, feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId);
		//实缴金额(包括使用充值金额)
		//allShiJIaoMoney=new BigDecimal(allStuFpdMoney).add(new BigDecimal(stuShiYongChongzhiMoney)).toString();
		return SUCCESS;
	}

	/**
	 * 导出查询集合(缴费单查询)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_payment_detail_search_admin_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentDetailSearchAdminAjax() throws Exception
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
		int count = saamBiz.findFeePaymentDetailCountByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId);
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
		List<FeePaymentDetail> feePaymentDetailList = null;
		List<ExportPaymentTemplate> exportPaymentTemplateList=null;
		ExportPaymentTemplate exportPaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|缴费单查询|导出缴费单详情");
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
		
		PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentDetailList != null && feePaymentDetailList.size() != 0)) 
		{			
			result.setCurrentPageIndex($page);
			
			feePaymentDetailList = saamBiz.findFeePaymentDetailListByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId, result);
			
			if(feePaymentDetailList!=null&&feePaymentDetailList.size()!=0)
			{
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePaymentDetail fpd : feePaymentDetailList) 
				{					
					FeePayment feePayment=fpd.getFeePayment();
					Student student=fpd.getStudent();
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
					//招生途径
					EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
					//市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					//合作方
					Channel channel=this.channelBiz.findChannel(student.getChannelId());
					
					exportPaymentTemplate=new ExportPaymentTemplate();
					exportPaymentTemplate.setNian(DateUtil.dateToString(fpd.getCreatedTime(),"yyyy"));//年
					exportPaymentTemplate.setYue(DateUtil.dateToString(fpd.getCreatedTime(),"MM"));
					exportPaymentTemplate.setRi(DateUtil.dateToString(fpd.getCreatedTime(),"dd"));
							
					exportPaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					//User user=userBiz.findUserById(fpd.getCreatorId());
					User user=userBiz.findUserById(student.getUserId());
					exportPaymentTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
					exportPaymentTemplate.setXueShengXingMing(fpd.getStudentName());
					exportPaymentTemplate.setZhaoShengPiCi(fpd.getAcademyenrollbatchName());
					exportPaymentTemplate.setYuanXiaoMingCheng(fpd.getSchoolName());
					exportPaymentTemplate.setCengCi(fpd.getLevelName());
					exportPaymentTemplate.setZhuanYe(fpd.getMajorName());
					exportPaymentTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
					exportPaymentTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
					exportPaymentTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
					exportPaymentTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
					exportPaymentTemplate.setLaiYuanFuHe(student==null?"未知":ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));//来源是否复核
					exportPaymentTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
					exportPaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportPaymentTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
					exportPaymentTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

					exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
							
					//缴费单状态
					exportPaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getStatus()));
					//总部确认时间
					exportPaymentTemplate.setZongBuQueRenShiJian(fpd.getCeduConfirmTime()==null?"--":fpd.getCeduConfirmTime().toString().substring(0,10));		
					
					String youHUiZhuTi=";";
					if(fpd.getAcademyDiscount()>0.00)
					{
						//院校优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="院校优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"院校优惠";
						}
					}
					if(fpd.getBranchDiscount()>0.00)
					{
						//中心优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="中心优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"中心优惠";
						}
					}
					if(fpd.getCeduDiscount()>0.00)
					{
						//弘成优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="弘成优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"弘成优惠";
						}
					}
					if(fpd.getChannelDiscount()>0.00)
					{
						//渠道优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="渠道优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"渠道优惠";
						}
					}
					if(fpd.getAcademyCeduDiscount()>0.00)
					{
						//院校_弘成共同优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="院校_弘成共同优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"院校_弘成共同优惠";
						}
					}
					
					if(youHUiZhuTi.equals(";"))
					{
						youHUiZhuTi="";
					}
					//缴费金额=实缴金额+使用充值金额-退费金额
					String jiaofeivalue=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).toString();
					exportPaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
					if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
					{
						//报名费
						exportPaymentTemplate.setFeiYongKeMu("报名费");//费用项目
						exportPaymentTemplate.setYiJiaoNaBaoMingFei(jiaofeivalue);//报名费
								
						exportPaymentTemplate.setYouHuiXiangMu("报名费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST)
					{
						//测试费
						exportPaymentTemplate.setFeiYongKeMu("测试费");//费用项目
						exportPaymentTemplate.setYiJiaoNaCeShiFei(jiaofeivalue);//测试费
								
						exportPaymentTemplate.setYouHuiXiangMu("测试费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION)
					{
						//学费
						exportPaymentTemplate.setFeiYongKeMu("学费");//费用项目
						exportPaymentTemplate.setYiJiaoNaXueFei(jiaofeivalue);//学费
								
						exportPaymentTemplate.setYouHuiXiangMu("学费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK)
					{
						//教材费
						exportPaymentTemplate.setFeiYongKeMu("教材费");//费用项目
						exportPaymentTemplate.setYiJiaoNaJiaoCaiFei(jiaofeivalue);//教材费
								
						exportPaymentTemplate.setYouHuiXiangMu("教材费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
					{
						//补考费
						exportPaymentTemplate.setFeiYongKeMu("补考费");//费用项目
						exportPaymentTemplate.setYiJiaoNaBuKaoFei(jiaofeivalue);//补考费
								
						exportPaymentTemplate.setYouHuiXiangMu("补考费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REHABILITATION)
					{
						//重修费
						exportPaymentTemplate.setFeiYongKeMu("重修费");//费用项目
						exportPaymentTemplate.setYiJiaoNaChongXiuFei(jiaofeivalue);//重修费
								
						exportPaymentTemplate.setYouHuiXiangMu("重修费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_INCIDENTALS)
					{
						//杂费
						exportPaymentTemplate.setFeiYongKeMu("杂费");//费用项目
						exportPaymentTemplate.setYiJiaoNaZaFei(jiaofeivalue);//杂费
								
						exportPaymentTemplate.setYouHuiXiangMu("杂费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_EXAM_TRAINING)
					{
						//统考培训费
						exportPaymentTemplate.setFeiYongKeMu("统考培训费");//费用项目
						exportPaymentTemplate.setYiJiaoNaTongKaoPeiXunFei(jiaofeivalue);//统考培训费费
								
						exportPaymentTemplate.setYouHuiXiangMu("统考培训费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_IMAGE_ACQUISITION)
					{
						//图像采集费
						exportPaymentTemplate.setFeiYongKeMu("图像采集费");//费用项目
						exportPaymentTemplate.setYiJiaoNaTuXiangCaiJiFei(jiaofeivalue);//图像采集费
								
						exportPaymentTemplate.setYouHuiXiangMu("图像采集费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else
					{
						exportPaymentTemplate.setFeiYongKeMu("未知");//费用项目
								
						exportPaymentTemplate.setYouHuiXiangMu("未知");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi("");//优惠主体
					}
					
					exportPaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
					exportPaymentTemplateList.add(exportPaymentTemplate);
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
			if (feePaymentDetailList.size() < $pageSize) {
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
	 * 导出查询集合（费用科目查询）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_payment_detail_search_admin_ajax_", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentDetailSearchAdminAjax_() throws Exception
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
		int count = saamBiz.findFeePaymentDetailCountByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId);
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
		List<FeePaymentDetail> feePaymentDetailList = null;
		List<ExportPaymentTemplate> exportPaymentTemplateList=null;
		ExportPaymentTemplate exportPaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|财务管理|费用科目查询|导出缴费单详情");
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
		
		PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentDetailList != null && feePaymentDetailList.size() != 0)) 
		{			
			result.setCurrentPageIndex($page);
			
			feePaymentDetailList = saamBiz.findFeePaymentDetailListByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId, result);
			
			if(feePaymentDetailList!=null&&feePaymentDetailList.size()!=0)
			{
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePaymentDetail fpd : feePaymentDetailList) 
				{					
					FeePayment feePayment=fpd.getFeePayment();
					Student student=fpd.getStudent();
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
					//招生途径
					EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
					//市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					//合作方
					Channel channel=this.channelBiz.findChannel(student.getChannelId());
					
					exportPaymentTemplate=new ExportPaymentTemplate();
					exportPaymentTemplate.setNian(DateUtil.dateToString(fpd.getCreatedTime(),"yyyy"));//年
					exportPaymentTemplate.setYue(DateUtil.dateToString(fpd.getCreatedTime(),"MM"));
					exportPaymentTemplate.setRi(DateUtil.dateToString(fpd.getCreatedTime(),"dd"));
							
					exportPaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					//User user=userBiz.findUserById(fpd.getCreatorId());
					User user=userBiz.findUserById(student.getUserId());
					exportPaymentTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
					exportPaymentTemplate.setXueShengXingMing(fpd.getStudentName());
					exportPaymentTemplate.setZhaoShengPiCi(fpd.getAcademyenrollbatchName());
					exportPaymentTemplate.setYuanXiaoMingCheng(fpd.getSchoolName());
					exportPaymentTemplate.setCengCi(fpd.getLevelName());
					exportPaymentTemplate.setZhuanYe(fpd.getMajorName());
					exportPaymentTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
					exportPaymentTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
					exportPaymentTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
					exportPaymentTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
					exportPaymentTemplate.setLaiYuanFuHe(student==null?"未知":ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));//来源是否复核
					exportPaymentTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
					exportPaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportPaymentTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
					exportPaymentTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

					exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
							
					//缴费单状态
					exportPaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getStatus()));
					//总部确认时间
					exportPaymentTemplate.setZongBuQueRenShiJian(fpd.getCeduConfirmTime()==null?"--":fpd.getCeduConfirmTime().toString().substring(0,10));	
					
					String youHUiZhuTi=";";
					if(fpd.getAcademyDiscount()>0.00)
					{
						//院校优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="院校优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"院校优惠";
						}
					}
					if(fpd.getBranchDiscount()>0.00)
					{
						//中心优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="中心优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"中心优惠";
						}
					}
					if(fpd.getCeduDiscount()>0.00)
					{
						//弘成优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="弘成优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"弘成优惠";
						}
					}
					if(fpd.getChannelDiscount()>0.00)
					{
						//渠道优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="渠道优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"渠道优惠";
						}
					}
					if(fpd.getAcademyCeduDiscount()>0.00)
					{
						//院校_弘成共同优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="院校_弘成共同优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"院校_弘成共同优惠";
						}
					}
					
					if(youHUiZhuTi.equals(";"))
					{
						youHUiZhuTi="";
					}
					////缴费金额=实缴金额+使用充值金额-退费金额
					//String jiaofeivalue=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).toString();
					//显示什么导出什么（09-23）
					String jiaofeivalue=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).toString();
					exportPaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
					if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
					{
						//报名费
						exportPaymentTemplate.setFeiYongKeMu("报名费");//费用项目
						exportPaymentTemplate.setYiJiaoNaBaoMingFei(jiaofeivalue);//报名费
								
						exportPaymentTemplate.setYouHuiXiangMu("报名费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST)
					{
						//测试费
						exportPaymentTemplate.setFeiYongKeMu("测试费");//费用项目
						exportPaymentTemplate.setYiJiaoNaCeShiFei(jiaofeivalue);//测试费
								
						exportPaymentTemplate.setYouHuiXiangMu("测试费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION)
					{
						//学费
						exportPaymentTemplate.setFeiYongKeMu("学费");//费用项目
						exportPaymentTemplate.setYiJiaoNaXueFei(jiaofeivalue);//学费
								
						exportPaymentTemplate.setYouHuiXiangMu("学费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK)
					{
						//教材费
						exportPaymentTemplate.setFeiYongKeMu("教材费");//费用项目
						exportPaymentTemplate.setYiJiaoNaJiaoCaiFei(jiaofeivalue);//教材费
								
						exportPaymentTemplate.setYouHuiXiangMu("教材费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
					{
						//补考费
						exportPaymentTemplate.setFeiYongKeMu("补考费");//费用项目
						exportPaymentTemplate.setYiJiaoNaBuKaoFei(jiaofeivalue);//补考费
								
						exportPaymentTemplate.setYouHuiXiangMu("补考费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REHABILITATION)
					{
						//重修费
						exportPaymentTemplate.setFeiYongKeMu("重修费");//费用项目
						exportPaymentTemplate.setYiJiaoNaChongXiuFei(jiaofeivalue);//重修费
								
						exportPaymentTemplate.setYouHuiXiangMu("重修费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_INCIDENTALS)
					{
						//杂费
						exportPaymentTemplate.setFeiYongKeMu("杂费");//费用项目
						exportPaymentTemplate.setYiJiaoNaZaFei(jiaofeivalue);//杂费
								
						exportPaymentTemplate.setYouHuiXiangMu("杂费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_EXAM_TRAINING)
					{
						//统考培训费
						exportPaymentTemplate.setFeiYongKeMu("统考培训费");//费用项目
						exportPaymentTemplate.setYiJiaoNaTongKaoPeiXunFei(jiaofeivalue);//统考培训费费
								
						exportPaymentTemplate.setYouHuiXiangMu("统考培训费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_IMAGE_ACQUISITION)
					{
						//图像采集费
						exportPaymentTemplate.setFeiYongKeMu("图像采集费");//费用项目
						exportPaymentTemplate.setYiJiaoNaTuXiangCaiJiFei(jiaofeivalue);//图像采集费
								
						exportPaymentTemplate.setYouHuiXiangMu("图像采集费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else
					{
						exportPaymentTemplate.setFeiYongKeMu("未知");//费用项目
								
						exportPaymentTemplate.setYouHuiXiangMu("未知");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi("");//优惠主体
					}
					
					exportPaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
					exportPaymentTemplateList.add(exportPaymentTemplate);
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
			if (feePaymentDetailList.size() < $pageSize) {
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
	 * 导出查询集合（费用科目查询）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_payment_detail_search_admin_ajax_branch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentDetailSearchAdminAjax_Branch() throws Exception
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
		int count = saamBiz.findFeePaymentDetailCountByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId);
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
		List<FeePaymentDetail> feePaymentDetailList = null;
		List<ExportPaymentTemplate> exportPaymentTemplateList=null;
		ExportPaymentTemplate exportPaymentTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|缴费退费|费用科目查询|导出缴费单详情");
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
		
		PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
		result.setPageSize($pageSize);
		result.setPage(true);

		while (only || (feePaymentDetailList != null && feePaymentDetailList.size() != 0)) 
		{			
			result.setCurrentPageIndex($page);
			
			feePaymentDetailList = saamBiz.findFeePaymentDetailListByPageForSearch(student, starttime, endtime,feeSubjectIds, status,ccStartTime,ccEndTime,feeWayId, result);
			
			if(feePaymentDetailList!=null&&feePaymentDetailList.size()!=0)
			{
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePaymentDetail fpd : feePaymentDetailList) 
				{					
					FeePayment feePayment=fpd.getFeePayment();
					Student student=fpd.getStudent();
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
					//招生途径
					EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
					//市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					
					//合作方
					Channel channel=this.channelBiz.findChannel(student.getChannelId());
					
					exportPaymentTemplate=new ExportPaymentTemplate();
					exportPaymentTemplate.setNian(DateUtil.dateToString(fpd.getCreatedTime(),"yyyy"));//年
					exportPaymentTemplate.setYue(DateUtil.dateToString(fpd.getCreatedTime(),"MM"));
					exportPaymentTemplate.setRi(DateUtil.dateToString(fpd.getCreatedTime(),"dd"));
							
					exportPaymentTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					//User user=userBiz.findUserById(fpd.getCreatorId());
					User user=userBiz.findUserById(student.getUserId());
					exportPaymentTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
					exportPaymentTemplate.setXueShengXingMing(fpd.getStudentName());
					exportPaymentTemplate.setZhaoShengPiCi(fpd.getAcademyenrollbatchName());
					exportPaymentTemplate.setYuanXiaoMingCheng(fpd.getSchoolName());
					exportPaymentTemplate.setCengCi(fpd.getLevelName());
					exportPaymentTemplate.setZhuanYe(fpd.getMajorName());
					exportPaymentTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
					exportPaymentTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
					exportPaymentTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
					exportPaymentTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
					exportPaymentTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方		
					exportPaymentTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
					exportPaymentTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());
					exportPaymentTemplate.setLaiYuanFuHe(student==null?"未知":ConstantsChannelTypeCheckedMap.getCode(student.getIsChannelTypeChecked()));//来源是否复核
					exportPaymentTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());

					exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
							
					//缴费单状态
					exportPaymentTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getStatus()));
					//总部确认时间
					exportPaymentTemplate.setZongBuQueRenShiJian(fpd.getCeduConfirmTime()==null?"--":fpd.getCeduConfirmTime().toString().substring(0,10));	
					
					String youHUiZhuTi=";";
					if(fpd.getAcademyDiscount()>0.00)
					{
						//院校优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="院校优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"院校优惠";
						}
					}
					if(fpd.getBranchDiscount()>0.00)
					{
						//中心优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="中心优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"中心优惠";
						}
					}
					if(fpd.getCeduDiscount()>0.00)
					{
						//弘成优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="弘成优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"弘成优惠";
						}
					}
					if(fpd.getChannelDiscount()>0.00)
					{
						//渠道优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="渠道优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"渠道优惠";
						}
					}
					if(fpd.getAcademyCeduDiscount()>0.00)
					{
						//院校_弘成共同优惠
						if(youHUiZhuTi.equals(";"))
						{
							youHUiZhuTi="院校_弘成共同优惠";
						}
						else
						{
							youHUiZhuTi+=";"+"院校_弘成共同优惠";
						}
					}
					
					if(youHUiZhuTi.equals(";"))
					{
						youHUiZhuTi="";
					}
					////缴费金额=实缴金额+使用充值金额-退费金额
					//String jiaofeivalue=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).toString();
					//显示什么导出什么（09-23）
					String jiaofeivalue=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).toString();
					exportPaymentTemplate.setJiaoFeiDanHao(feePayment.getCode());
					if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
					{
						//报名费
						exportPaymentTemplate.setFeiYongKeMu("报名费");//费用项目
						exportPaymentTemplate.setYiJiaoNaBaoMingFei(jiaofeivalue);//报名费
								
						exportPaymentTemplate.setYouHuiXiangMu("报名费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST)
					{
						//测试费
						exportPaymentTemplate.setFeiYongKeMu("测试费");//费用项目
						exportPaymentTemplate.setYiJiaoNaCeShiFei(jiaofeivalue);//测试费
								
						exportPaymentTemplate.setYouHuiXiangMu("测试费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION)
					{
						//学费
						exportPaymentTemplate.setFeiYongKeMu("学费");//费用项目
						exportPaymentTemplate.setYiJiaoNaXueFei(jiaofeivalue);//学费
								
						exportPaymentTemplate.setYouHuiXiangMu("学费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
								
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK)
					{
						//教材费
						exportPaymentTemplate.setFeiYongKeMu("教材费");//费用项目
						exportPaymentTemplate.setYiJiaoNaJiaoCaiFei(jiaofeivalue);//教材费
								
						exportPaymentTemplate.setYouHuiXiangMu("教材费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
					{
						//补考费
						exportPaymentTemplate.setFeiYongKeMu("补考费");//费用项目
						exportPaymentTemplate.setYiJiaoNaBuKaoFei(jiaofeivalue);//补考费
								
						exportPaymentTemplate.setYouHuiXiangMu("补考费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REHABILITATION)
					{
						//重修费
						exportPaymentTemplate.setFeiYongKeMu("重修费");//费用项目
						exportPaymentTemplate.setYiJiaoNaChongXiuFei(jiaofeivalue);//重修费
								
						exportPaymentTemplate.setYouHuiXiangMu("重修费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_INCIDENTALS)
					{
						//杂费
						exportPaymentTemplate.setFeiYongKeMu("杂费");//费用项目
						exportPaymentTemplate.setYiJiaoNaZaFei(jiaofeivalue);//杂费
								
						exportPaymentTemplate.setYouHuiXiangMu("杂费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_EXAM_TRAINING)
					{
						//统考培训费
						exportPaymentTemplate.setFeiYongKeMu("统考培训费");//费用项目
						exportPaymentTemplate.setYiJiaoNaTongKaoPeiXunFei(jiaofeivalue);//统考培训费费
								
						exportPaymentTemplate.setYouHuiXiangMu("统考培训费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_IMAGE_ACQUISITION)
					{
						//图像采集费
						exportPaymentTemplate.setFeiYongKeMu("图像采集费");//费用项目
						exportPaymentTemplate.setYiJiaoNaTuXiangCaiJiFei(jiaofeivalue);//图像采集费
								
						exportPaymentTemplate.setYouHuiXiangMu("图像采集费");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
						exportPaymentTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					}
					else
					{
						exportPaymentTemplate.setFeiYongKeMu("未知");//费用项目
								
						exportPaymentTemplate.setYouHuiXiangMu("未知");//优惠项目
						exportPaymentTemplate.setYouHuiZhuTi("");//优惠主体
					}
					
					exportPaymentTemplate.setShouJuHaoMa(feePayment.getBarCode());//收据单号
					exportPaymentTemplateList.add(exportPaymentTemplate);
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
			if (feePaymentDetailList.size() < $pageSize) {
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

	public String getFeeSubjectIds() {
		return feeSubjectIds;
	}

	public void setFeeSubjectIds(String feeSubjectIds) {
		this.feeSubjectIds = feeSubjectIds;
	}

	public String getAllStuAllMoney() {
		//转换金额
		allStuAllMoney=MoneyUtil.formatMoney(allStuAllMoney);
		return allStuAllMoney;
	}

	public void setAllStuAllMoney(String allStuAllMoney) {
		this.allStuAllMoney = allStuAllMoney;
	}

	public String getAllStuFpdMoney() {
		//转换金额
		allStuFpdMoney=MoneyUtil.formatMoney(allStuFpdMoney);
		return allStuFpdMoney;
	}

	public void setAllStuFpdMoney(String allStuFpdMoney) {
		this.allStuFpdMoney = allStuFpdMoney;
	}

	public String getStuShiYongChongzhiMoney() {
		//转换金额
		stuShiYongChongzhiMoney=MoneyUtil.formatMoney(stuShiYongChongzhiMoney);
		return stuShiYongChongzhiMoney;
	}

	public void setStuShiYongChongzhiMoney(String stuShiYongChongzhiMoney) {
		this.stuShiYongChongzhiMoney = stuShiYongChongzhiMoney;
	}

	public String getAllShiJIaoMoney() {
		//转换金额
		allShiJIaoMoney=MoneyUtil.formatMoney(allShiJIaoMoney);
		return allShiJIaoMoney;
	}

	public void setAllShiJIaoMoney(String allShiJIaoMoney) {
		this.allShiJIaoMoney = allShiJIaoMoney;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCcStartTime() {
		return ccStartTime;
	}

	public void setCcStartTime(String ccStartTime) {
		this.ccStartTime = ccStartTime;
	}

	public String getCcEndTime() {
		return ccEndTime;
	}

	public void setCcEndTime(String ccEndTime) {
		this.ccEndTime = ccEndTime;
	}

	public int getDataMaxCount() {
		return dataMaxCount;
	}

	public void setDataMaxCount(int dataMaxCount) {
		this.dataMaxCount = dataMaxCount;
	}

	public int getFeeWayId() {
		return feeWayId;
	}

	public void setFeeWayId(int feeWayId) {
		this.feeWayId = feeWayId;
	}

	public String getStuaaMoney() {
		//转换金额
		stuaaMoney=MoneyUtil.formatMoney(stuaaMoney);
		return stuaaMoney;
	}

	public void setStuaaMoney(String stuaaMoney) {
		this.stuaaMoney = stuaaMoney;
	}

	public String getStuxiaofeiMoney() {
		//转换金额
		stuxiaofeiMoney=MoneyUtil.formatMoney(stuxiaofeiMoney);
		return stuxiaofeiMoney;
	}

	public void setStuxiaofeiMoney(String stuxiaofeiMoney) {
		this.stuxiaofeiMoney = stuxiaofeiMoney;
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
