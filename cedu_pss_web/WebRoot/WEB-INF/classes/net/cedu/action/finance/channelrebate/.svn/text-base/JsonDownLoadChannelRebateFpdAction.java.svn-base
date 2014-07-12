package net.cedu.action.finance.channelrebate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.base.TaskBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.ConstantsFeeWayMap;
import net.cedu.common.ConstantsPaymentStatusMap;
import net.cedu.common.ConstantsStudentDataSourceMap;
import net.cedu.common.ConstantsStudentStatusMap;
import net.cedu.common.date.DateUtil;
import net.cedu.common.file.FileUtil;
import net.cedu.common.file.ZipUtil;
import net.cedu.common.file.excel.ExcelExport;
import net.cedu.common.properties.Config;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.base.UserTask;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.finance.ExportFpdForChannelRebateTemplate;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 导出招生返款单
 * 
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonDownLoadChannelRebateFpdAction extends BaseAction
{
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径_业务层接口
	
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	
	@Autowired
	private UserBiz userBiz;//用户_业务层接口
	
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
	
	@Autowired
	private TaskBiz taskBiz;
	
	private int orderId;//招生返款单Id
	
	//导出的压缩包下载地址
	private String downloadUrl = "";
	
	/**
	 * 导出查询集合（费用科目查询）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "excel_export_fpd_for_channel_rebate_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String excelExportPaymentDetailSearchAdminAjax_() throws Exception
	{

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
		userTask.setTitle("招生返款|导出招生返款缴费单明细详情");
		userTask.setUserId(super.getUser().getUserId());
		//任务添加失败
		int errorno = taskBiz.addTask(userTask);
		if(errorno!=-1){
			downloadUrl="error"+errorno;
			return SUCCESS;
		}

		String path = Config.getProperty("export.excel.tmp") + ctm;
		
		// 创建导出的工具类对象
		ExcelExport<ExportFpdForChannelRebateTemplate> ex = new ExcelExport<ExportFpdForChannelRebateTemplate>();
		// 创建随机目录
		FileUtil.CheckCreateDR(application.getRealPath(path));

		OutputStream os = null;		
			
		feePaymentDetailList = feePaymentDetailBiz.findByOrderCeduChannelId(orderId);
			
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
				//User user=userBiz.findUserById(fpd.getCreatorId());
				//User user=userBiz.findUserById(student.getUserId());
				//fpdForChannelRebateTemplate.setLuRuZheXingMing(user==null?"未知":user.getFullName());//录入者姓名
				fpdForChannelRebateTemplate.setXueShengXingMing(student.getName());
				fpdForChannelRebateTemplate.setZhaoShengPiCi(academyEnrollBatch==null?"未知":academyEnrollBatch.getEnrollmentName());
				fpdForChannelRebateTemplate.setYuanXiaoMingCheng(academy==null?"未知":academy.getName());
				fpdForChannelRebateTemplate.setCengCi(level==null?"未知":level.getName());
				fpdForChannelRebateTemplate.setZhuanYe(major==null?"未知":major.getName());
				fpdForChannelRebateTemplate.setShuJuLaiYuan(student==null?"未知":ConstantsStudentDataSourceMap.getCode(student.getStudentDataSource()));//数据来源
				fpdForChannelRebateTemplate.setZhaoShengTuJing(enrollmentsource==null?"未知":enrollmentsource.getName());//招生途径
				
				fpdForChannelRebateTemplate.setHeZuoFangName(channel==null?"未知":channel.getName());//合作方
				
				fpdForChannelRebateTemplate.setXueShengZhuangTai(student==null?"未知":ConstantsStudentStatusMap.getCode(student.getStatus()));//学生状态
							
				fpdForChannelRebateTemplate.setShenFengZhengHaoMa(student==null?"未知":student.getCertNo());//
			
				fpdForChannelRebateTemplate.setJiaoFeiDanHao(feePayment==null?"未知":feePayment.getCode());//缴费单号
				
				fpdForChannelRebateTemplate.setFeiYongKeMu(feeSubject==null?"未知":feeSubject.getName());//费用科目
				
				fpdForChannelRebateTemplate.setJiaoFeiFangShi(ConstantsFeeWayMap.getCode(feePayment.getFeeWayId()));//缴费方式ID
				
				//缴费金额
				fpdForChannelRebateTemplate.setJiaoFeiJinE((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).toString());
				
				//返款金额
				fpdForChannelRebateTemplate.setFanKuanJinE(fpd.getPaymentByChannel()+"");
				
				//缴费单状态
				fpdForChannelRebateTemplate.setJiaoFeiDanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getStatus()));
							
				//返款金额
				fpdForChannelRebateTemplate.setZhaoShengFanKuanZhuangTai(ConstantsPaymentStatusMap.getCode(fpd.getRebateStatus()));
				
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
		ex.exportExcel("招生返款详情", fpdForChannelRebateTemplateList, os);
		// 关闭流
		os.close();
		fpdForChannelRebateTemplateList.clear();
		os = null;
	
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
}
