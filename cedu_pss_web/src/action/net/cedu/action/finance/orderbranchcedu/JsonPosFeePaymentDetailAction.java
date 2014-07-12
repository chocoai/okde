package net.cedu.action.finance.orderbranchcedu;

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
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
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
 * 汇款总部确认（总部）中的(pos直汇总部)
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonPosFeePaymentDetailAction extends BaseAction
{
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	private Student student=new Student();//学生实体
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细
	
	private String startDate;//缴费时间起	
	private String endDate;//缴费时间止
	private String code;//缴费单号
	
	//统计
	private String allMoney;//统计金额
	
	//批量确认
	private String fpdIds;
	private boolean isback=false;
	
	//回退总部确认过的缴费单明细
	private int fpdId;//缴费单Id
	private boolean isfail=false;//返回参数
	
	//**********************导出************************//
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private TaskBiz taskBiz;//任务
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	//导出的压缩包下载地址
	private String downloadUrl = "";
	//导出的最大数据量
	private int dataMaxCount = 0;
	
	
	/**
	 * 获取缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pos_feepaymentdetail_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"result.*,feePaymentDetail,student, startDate, endDate,code"
	}) })
	public String playmoneyCount() throws Exception 
	{
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU);
		feePayment.setCode(code);
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result));
		return SUCCESS;
	}
	
	/**
	 * 获取缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_pos_feepaymentdetail_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"result.*,feePaymentDetail,student, startDate, endDate,code"
			}) })
	public String playmoneyList() throws Exception 
	{	
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU);	
		feePayment.setCode(code);
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result));
		return SUCCESS;
	}
	
	/**
	 * 统计金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pos_cedu_fpd_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties",
			"allMoney"
			}) })
	public String countposcedu() throws Exception 
	{	
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU);	
		feePayment.setCode(code);
		allMoney=this.feePaymentDetailBiz.countFpdAllMoneyForPOSEbank(feePaymentDetail, feePayment, student, startDate, endDate);
		return SUCCESS;
	}
	
	/**
	 * 批量确认
	 * @return
	 * @throws Exception
	 */
	@Action(value = "confirm_batch_pos_cedu_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String confirmposcedu() throws Exception 
	{	
		if(fpdIds!=null && !fpdIds.equals(""))
		{
			List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();//缴费单明细_集合
			List<FeePaymentDetail> reflist=new ArrayList<FeePaymentDetail>();//退费单明细_集合
			FeePaymentDetail fpd = null;
			FeePaymentDetail rfpd=null;
			String[] fIds=fpdIds.split(",");
			for(int i=0;i<fIds.length;i++)
			{			
				fpd = feePaymentDetailBiz.findById(Integer.valueOf(fIds[i]));
				if(fpd.getStatus()<Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN)
				{
					//汇款金额(不需要乘以基数)
					fpd.setPayBranchCedu(Double.valueOf(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).toString()));
					//fpd.setPayBranchCedu(Double.valueOf(df.format(((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).multiply(jishu)).doubleValue())));
					fpd.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
					//2012-05-09  屏蔽   招生返款需求变更   所有缴费单都必须打款院校才能招生返款
					fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
					
					//总部确认时间和操作相关人员2012-04-01
					fpd.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					fpd.setCeduConfirmId(super.getUser().getUserId());
					
					fpd.setUpdaterId(super.getUser().getUserId());
					fpd.setUpdatedTime(new Date());
					
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fpd.getId(), Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN, Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
					if(refundlist!=null && refundlist.size()>0)
					{
						rfpd=refundlist.get(0);
						//还原历史数据
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());
					
						//重新计算退费的值
						if(new BigDecimal(rfpd.getRefundBase()).compareTo(new BigDecimal(0))==0)
						{
							rfpd.setRefundBase(1);
						}
						rfpd.setCeduAccount(0-((new BigDecimal(fpd.getCeduAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setAcademyAccount(0-((new BigDecimal(fpd.getAcademyAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setChannelAccount(0-((new BigDecimal(fpd.getChannelAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setBranchAccount(0-((new BigDecimal(fpd.getBranchAccount()).multiply(new BigDecimal(0-rfpd.getAmountPaied())).divide(new BigDecimal(rfpd.getRefundBase()),2,BigDecimal.ROUND_HALF_UP)).doubleValue()));
						rfpd.setStatus(Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN);
						rfpd.setUpdaterId(super.getUser().getUserId());
						rfpd.setUpdatedTime(new Date());
						
						//更新历史缴费单(重新计算各个账户的值)
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						fpd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
						
						reflist.add(rfpd);
					}
					else
					{			
						//账户金额变动
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());			
					}
					
					fpdlist.add(fpd);
					//this.feePaymentDetailBiz.updateFeePaymentDetailAndTfd(fpd, rfpd);aaa
				}
			}
			isback=this.feePaymentDetailBiz.updateFpdListAndTfdList(fpdlist, reflist);
		}
		return SUCCESS;
	}
	
	/**
	 * 对总部确认过后的pos、网银、第三方支付进行回退   已进行退费的不能回退、已招生返款的不能会提
	 * @return
	 * @throws Exception
	 */
	@Action(value = "fallback_pos_ebank_cedu_confirm_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fallbackposcedu() throws Exception 
	{	
		FeePaymentDetail fpd = feePaymentDetailBiz.findById(fpdId);
		if(fpd!=null && fpd.getStatus()==Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN && fpd.getRefundAmount()==0 && fpd.getRefundLock()==Constants.LOCKING_FALSE && fpd.getRebateStatus()<=Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
		{
			
			//汇款金额(不需要乘以基数)
			fpd.setPayBranchCedu(Double.valueOf(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).toString()));
			
			fpd.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU); //回退一个状态
			fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_CHU_SHI_ZHUANG_TAI);//返款  初始状态
			
			//总部确认时间和操作相关人员
			fpd.setCeduConfirmTime(null);
			fpd.setCeduConfirmId(0);
					
			//账户金额变动   回退到原来状态
			fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());
			fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());			
			
			fpd.setUpdaterId(super.getUser().getUserId());
			fpd.setUpdatedTime(new Date());
			
			isfail=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
		}
		return SUCCESS;
	}

	/**
	 * 导出查询集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_pos_fee_payment_detail_search_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPosFeePaymentDetailAjax() throws Exception
	{

		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU);	
		feePayment.setCode(code);
		
		//检查导出结果是否为空
		int count = feePaymentDetailBiz.findFeePaymentDetailCountByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result);
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
		userTask.setTitle("财务管理|学习中心汇款确认|导出POS直汇总部缴费单详情");
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
			
			feePaymentDetailList = this.feePaymentDetailBiz.findFeePaymentDetailListByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result);
			
			if(feePaymentDetailList!=null&&feePaymentDetailList.size()!=0)
			{
				exportPaymentTemplateList=new ArrayList<ExportPaymentTemplate>();
				for (FeePaymentDetail fpd : feePaymentDetailList) 
				{					
					FeePayment fp=fpd.getFeePayment();
					Student student=this.studentBiz.findStudentById(fpd.getStudentId());
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

					exportPaymentTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(fp.getFeeWayId()));//缴费方式ID
							
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
					exportPaymentTemplate.setJiaoFeiDanHao(fp.getCode());
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
					
					exportPaymentTemplate.setShouJuHaoMa(fp.getBarCode());//收据单号
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

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAllMoney() {
		//转换金额
		allMoney=MoneyUtil.formatMoney(allMoney);
		return allMoney;
	}

	public void setAllMoney(String allMoney) {
		this.allMoney = allMoney;
	}

	public String getFpdIds() {
		return fpdIds;
	}

	public void setFpdIds(String fpdIds) {
		this.fpdIds = fpdIds;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getFpdId() {
		return fpdId;
	}

	public void setFpdId(int fpdId) {
		this.fpdId = fpdId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
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
