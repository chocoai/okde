package net.cedu.action.finance.channelrebatereview;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardOverLoadBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
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
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.finance.ExportFpdForChannelRebateTemplate;
import net.cedu.model.page.PageResult;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款申请（查询）显示已返款的缴费单明细  2012-06-11
 * 
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonListChannelRebateHasFpdAction extends BaseAction
{	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	private Student student=new Student(); //学生实体
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细实体	
		
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private AcademyLevel academyLevel=new AcademyLevel();//院校招生批次与层次的关系
	
	//***********  统计招生返款的总金额    ********************//
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;//招生返款单_业务层接口
	private String allchannelmoney;//缴费单返款金额
	private String allOrderAdjustMoney;//招生返款单调整金额
	
	//**********************导出************************//
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径_业务层接口
	@Autowired
	private BaseDictBiz basedictBiz;//基础设置_业务层接口
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	@Autowired
	private UserBiz userBiz;//用户_业务层接口
	@Autowired
	private TaskBiz taskBiz;//任务_业务层接口
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	@Autowired
	private ChannelPolicyDetailStandardOverLoadBiz cpdsOverLoadBiz;//招生返款标准_业务层接口
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目_业务层接口
	@Autowired
	private AcademyBiz academyBiz;// 院校_业务接口
	@Autowired	
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;// 院校招生批次 _业务接口	
	@Autowired
	private LevelBiz levelbiz; // 层次_业务接口	
	@Autowired
	private MajorBiz majorbiz; // 专业_业务接口
	//导出的压缩包下载地址
	private String downloadUrl = "";
	//导出的最大数据量
	private int dataMaxCount = 0;
	
	
	/**
	 * 显示已招生返款的缴费单明细数量_重构_2012-06-11
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_has_fpd_show_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewCount() throws Exception 
	{
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
		//总部确认时间
		if(feePaymentDetail.getCeduConfirmTime()!=null && !feePaymentDetail.getCeduConfirmTime().equals(""))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endtime=df.format(feePaymentDetail.getCeduConfirmTime()).substring(0,10)+" 23:59:59";
			feePaymentDetail.setCeduConfirmTime(df.parse(endtime));
		}
		result.setRecordCount(this.feePaymentDetailBiz.findfpdCountByPageForChannelRecruitRebateSearch(feePaymentDetail, student));
		return SUCCESS;
	}

	/**
	 * 显示已招生返款的缴费单明细列表_重构_2012-06-11
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channel_rebate_has_fpd_show_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewList() throws Exception 
	{			
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
		//总部确认时间
		if(feePaymentDetail.getCeduConfirmTime()!=null && !feePaymentDetail.getCeduConfirmTime().equals(""))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endtime=df.format(feePaymentDetail.getCeduConfirmTime()).substring(0,10)+" 23:59:59";
			feePaymentDetail.setCeduConfirmTime(df.parse(endtime));
		}
		result.setList(this.feePaymentDetailBiz.findfpdListByPageForChannelRecruitRebateSearch(feePaymentDetail, student, result));
		return SUCCESS;
	}
	
	/**
	 * 统计已招生返款的缴费单明细返款金额_重构_2012-06-11
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_has_fpd_show_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewMoney() throws Exception 
	{			
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
		//总部确认时间
		if(feePaymentDetail.getCeduConfirmTime()!=null && !feePaymentDetail.getCeduConfirmTime().equals(""))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endtime=df.format(feePaymentDetail.getCeduConfirmTime()).substring(0,10)+" 23:59:59";
			feePaymentDetail.setCeduConfirmTime(df.parse(endtime));
		}
		//缴费单返款金额
		allchannelmoney=this.feePaymentDetailBiz.countFpdAllChannelRebateMoneyForChannelRebateSearch(feePaymentDetail,student);
		
		//返款单调整金额
		allOrderAdjustMoney=this.orderCeduChannelBiz.countOrderCeduChannelAdjustPaiedByConditions(student, feePaymentDetail);
		
		return SUCCESS;
	}
	
	/**
	 * 导出查询集合(已招生返款的缴费单明细)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_fpd_has_channel_rebate_review_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportFpdHasChannelRebateAjax() throws Exception
	{
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
		//总部确认时间
		if(feePaymentDetail.getCeduConfirmTime()!=null && !feePaymentDetail.getCeduConfirmTime().equals(""))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String endtime=df.format(feePaymentDetail.getCeduConfirmTime()).substring(0,10)+" 23:59:59";
			feePaymentDetail.setCeduConfirmTime(df.parse(endtime));
		}
		
		//检查导出结果是否为空
		int count = this.feePaymentDetailBiz.findfpdCountByPageForChannelRecruitRebateSearch(feePaymentDetail, student);
		if(count==0)
		{
			downloadUrl="error"+Constants.EXPORT_EXCEL_ERROR_NO_3;
			return SUCCESS;
		}
		//设置下载任务导出数据数量上限
		dataMaxCount = Constants.EXPORT_EXCEL_MAX_COUNT;//如果配置文件异常则默认导出数据数量上限
		if(Config.getProperty("export.maxcount")!=null)
		{
			try 
			{
				 dataMaxCount = Config.getIntProperty("export.maxcount");
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		//检查导入结果是否超过上限
		if(count>dataMaxCount)
		{
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
		List<ExportFpdForChannelRebateTemplate> fpdForChannelRebateTemplateList=null;
		ExportFpdForChannelRebateTemplate fpdForChannelRebateTemplate=null;
		// 导出的随机目录
		long ctm = System.currentTimeMillis();

		UserTask userTask = new UserTask();
		userTask.setCreateTime(new Date());
		userTask.setDownloadSumCount(0);
		userTask.setPath(ctm+"");
		//userTask.setRemark("");
		userTask.setTitle("财务管理|招生返款查询|导出招生返款缴费单详情");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=Constants.EXPORT_EXCEL_FINISH){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		
		// 创建导出的工具类对象
		ExcelExport<ExportFpdForChannelRebateTemplate> ex = new ExcelExport<ExportFpdForChannelRebateTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;
		
		PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
		result.setPageSize($pageSize);
		result.setPage(true);
		setIl8nName("finance_payment");	
		while (only || (feePaymentDetailList != null && feePaymentDetailList.size() != 0)) 
		{			
			result.setCurrentPageIndex($page);
			
			feePaymentDetailList = this.feePaymentDetailBiz.findfpdListByPageForChannelRecruitRebateSearch(feePaymentDetail, student, result);
			
			if(feePaymentDetailList!=null&&feePaymentDetailList.size()!=0)
			{
				fpdForChannelRebateTemplateList=new ArrayList<ExportFpdForChannelRebateTemplate>();
				for (FeePaymentDetail fpd : feePaymentDetailList) 
				{					
					FeePayment feePayment=this.feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId());
					Student student=this.studentBiz.findStudentById(fpd.getStudentId());
					//学习中心
					Branch branch=branchBiz.findBranchById(student.getBranchId());
					//招生途径
					EnrollmentSource enrollmentsource = enrollmentSourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
					//合作方
					Channel channel=this.channelBiz.findChannel(student.getChannelId());
					//市场途径
					BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
					//缴费科目
					FeeSubject feeSubject =this.feeSubjectBiz.findFeeSubjectById(fpd.getFeeSubjectId());
					//招生批次
					AcademyEnrollBatch academyEnrollBatch=null;
					//院校
					Academy academy=null;
					//层次
					Level level=null;
					//专业
					Major major=null;
					if(student!=null)
					{
						academyEnrollBatch=academyEnrollBatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
						academy=this.academyBiz.findAcademyById(student.getAcademyId());
						level=this.levelbiz.findLevelById(student.getLevelId());
						major=majorbiz.findMajorById(student.getMajorId());
					}
						
					fpdForChannelRebateTemplate=new ExportFpdForChannelRebateTemplate();
					fpdForChannelRebateTemplate.setNian(DateUtil.dateToString(fpd.getCreatedTime(),"yyyy"));//年
					fpdForChannelRebateTemplate.setYue(DateUtil.dateToString(fpd.getCreatedTime(),"MM"));
					fpdForChannelRebateTemplate.setRi(DateUtil.dateToString(fpd.getCreatedTime(),"dd"));							
					fpdForChannelRebateTemplate.setXueXiZhongXing(branch==null?"未知":branch.getName());//学习中心名称
					User user=userBiz.findUserById(student.getUserId());
					fpdForChannelRebateTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
					fpdForChannelRebateTemplate.setXueShengXingMing(student.getName());
					fpdForChannelRebateTemplate.setZhaoShengPiCi(academyEnrollBatch==null?"未知":academyEnrollBatch.getEnrollmentName());
					fpdForChannelRebateTemplate.setYuanXiaoMingCheng(academy==null?"未知":academy.getName());
					fpdForChannelRebateTemplate.setCengCi(level==null?"未知":level.getName());
					fpdForChannelRebateTemplate.setZhuanYe(major==null?"未知":major.getName());
					fpdForChannelRebateTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
					fpdForChannelRebateTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
					
					fpdForChannelRebateTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
					
					fpdForChannelRebateTemplate.setShiChangTuJing(basedict==null?"--":basedict.getName());//市场途径
					
					fpdForChannelRebateTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
								
					fpdForChannelRebateTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//身份证
					
					fpdForChannelRebateTemplate.setShouJiHaoma(student==null?"未知":student.getMobile());//手机号码
					
					fpdForChannelRebateTemplate.setZuoJiHaoMa(student==null?"未知":student.getPhone());//座机号码
				
					fpdForChannelRebateTemplate.setJiaoFeiDanHao(feePayment==null?"未知":feePayment.getCode());//缴费单号
					
					fpdForChannelRebateTemplate.setFeiYongKeMu(feeSubject==null?"未知":feeSubject.getName());//费用科目
					
					fpdForChannelRebateTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
					
					String youHUiZhuTi=";";
					if(fpd.getAcademyDiscount()>0.00){//院校优惠
						if(youHUiZhuTi.equals(";")){
							youHUiZhuTi="院校优惠";
						}else{
							youHUiZhuTi+=";"+"院校优惠";
						}
					}
					if(fpd.getBranchDiscount()>0.00){//中心优惠
						if(youHUiZhuTi.equals(";")){
							youHUiZhuTi="中心优惠";
						}else{
							youHUiZhuTi+=";"+"中心优惠";
						}
					}
					if(fpd.getCeduDiscount()>0.00){//弘成优惠
						if(youHUiZhuTi.equals(";")){
							youHUiZhuTi="弘成优惠";
						}else{
							youHUiZhuTi+=";"+"弘成优惠";
						}
					}
					if(fpd.getChannelDiscount()>0.00){//渠道优惠
						if(youHUiZhuTi.equals(";")){
							youHUiZhuTi="渠道优惠";
						}else{
							youHUiZhuTi+=";"+"渠道优惠";
						}
					}
					if(youHUiZhuTi.equals(";")){
						youHUiZhuTi="";
					}
					
					fpdForChannelRebateTemplate.setYouHuiXiangMu(feeSubject==null?"未知":feeSubject.getName());//优惠项目
					
					fpdForChannelRebateTemplate.setYouHuiZhuTi(youHUiZhuTi);//优惠主体
					
					fpdForChannelRebateTemplate.setYouHuiJinE(fpd.getDiscountAmount()+"");//优惠金额
					
					fpdForChannelRebateTemplate.setShouJuHaoMa(feePayment==null?"未知":feePayment.getBarCode());//收据单号
					
					//缴费金额
					fpdForChannelRebateTemplate.setJiaoFeiJinE((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).toString());
					
					//返款金额
					fpdForChannelRebateTemplate.setFanKuanJinE(fpd.getPaymentByChannel()+"");
					
					//缴费单状态
					fpdForChannelRebateTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getStatus()));
								
					//返款金额
					fpdForChannelRebateTemplate.setZhaoShengFanKuanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getRebateStatus()));
					
					//合作方返款标准
					ChannelPolicyDetailStandard cpds=this.cpdsOverLoadBiz.findChannelPolicyDetailStandardById(fpd.getChannelPolicyStandardId());
					if(cpds!=null)
					{
						//返款标准显示字符串
						String cpdsstring =getText("rebatestandardshow")+cpds.getEnrollmentFloor()+"--"+cpds.getEnrollmentCeil()+getText("peopleunit")+cpds.getValue();
						if(cpds.getRebatesId()==Constants.MONEY_FORM_RATIO)
						{
							cpdsstring+=getText("baifenhao");
						}
						else
						{
							cpdsstring+=getText("jinedanwei");
						}
						fpdForChannelRebateTemplate.setFanKuanBiaoZhun(cpdsstring);
					}
					fpdForChannelRebateTemplateList.add(fpdForChannelRebateTemplate);
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
			// 开始导出
			//2012-07-25  为了导出返款金额的调整金额和总金额
			if (feePaymentDetailList.size() < $pageSize)
			{
				//缴费单返款金额
				allchannelmoney=this.feePaymentDetailBiz.countFpdAllChannelRebateMoneyForChannelRebateSearch(feePaymentDetail,student);				
				//返款单调整金额
				allOrderAdjustMoney=this.orderCeduChannelBiz.countOrderCeduChannelAdjustPaiedByConditions(student, feePaymentDetail);
				fpdForChannelRebateTemplate=new ExportFpdForChannelRebateTemplate();
				fpdForChannelRebateTemplate.setNian("调整金额：");
				fpdForChannelRebateTemplate.setYue(allOrderAdjustMoney);
				fpdForChannelRebateTemplateList.add(fpdForChannelRebateTemplate);
				fpdForChannelRebateTemplate=new ExportFpdForChannelRebateTemplate();
				fpdForChannelRebateTemplate.setNian("缴费单返款金额：");
				fpdForChannelRebateTemplate.setYue(allchannelmoney);
				fpdForChannelRebateTemplateList.add(fpdForChannelRebateTemplate);
				fpdForChannelRebateTemplate=new ExportFpdForChannelRebateTemplate();
				fpdForChannelRebateTemplate.setNian("总金额：");
				fpdForChannelRebateTemplate.setYue((new BigDecimal(allchannelmoney).add(new BigDecimal(allOrderAdjustMoney))).toString());
				fpdForChannelRebateTemplateList.add(fpdForChannelRebateTemplate);
				ex.exportExcel("招生返款详情", fpdForChannelRebateTemplateList, os,(feePaymentDetailList.size()+1),(feePaymentDetailList.size()+3),1,2,HSSFColor.RED.index);
			}
			else
			{
				ex.exportExcel("招生返款详情", fpdForChannelRebateTemplateList, os);
			}
			// 关闭流
			os.close();
			fpdForChannelRebateTemplateList.clear();
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

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}
	
	public String getAllchannelmoney() {
		//转换金额
		allchannelmoney=MoneyUtil.formatMoney(allchannelmoney);
		return allchannelmoney;
	}

	public void setAllchannelmoney(String allchannelmoney) {
		this.allchannelmoney = allchannelmoney;
	}
	

	public String getAllOrderAdjustMoney() {
		//转换金额
		allOrderAdjustMoney=MoneyUtil.formatMoney(allOrderAdjustMoney);
		return allOrderAdjustMoney;
	}

	public void setAllOrderAdjustMoney(String allOrderAdjustMoney) {
		this.allOrderAdjustMoney = allOrderAdjustMoney;
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
