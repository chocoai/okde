package net.cedu.action.finance.playmoney;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校打款(总部)_添加
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonAddHeadquartersPlaymoneyAcademyAction extends BaseAction
{
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//缴费单明细集合
	// 分页结果
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	//查询参数
	private Student student=new Student();//学生实体
	private String batchIds;//批次Ids字符串集合
	private String starttime;//缴费开始时间
	private String endtime;//缴费结束时间
	private int feeSubjectId;//费用科目Id
	
	//统计选择的缴费单明细
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层接口
	@Autowired
	private AcademyBiz academyBiz;// 院校_业务接口
	@Autowired
	private BranchBiz branchBiz; // 学习中心_业务接口
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 _业务接口
	
	private List<FeePaymentDetail> feepdList=new ArrayList<FeePaymentDetail>();//缴费单明细集合
	private String feePaymentDetailIds;//缴费单明细ids字符串集合
	
	//*****************************添加打款单************************************//
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;//打款单_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	private PayCeduAcademy payCeduAcademy=new PayCeduAcademy();//打款单实体
	private String feepaymentIds;//缴费单明细ids集合
	private boolean isback=false;//返回参数
	private int indexcount;//判断重复添加问题
	private boolean replayadd=false;//
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//生成编号_业务层接口
	
	
	//*****************************删除打款单************************************//
	private boolean isfail=false;//返回参数
	private int payCeduAcademyId;//打款单Id
	
	//*****************************更新打款单状态************************************//
	private int payCeduAcademyStatus;//打款单状态
	
	/**
	 * 获取缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_feepaymentdetail_headpalymoneyacademy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		FeePaymentDetail feePaymentDetail=new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
		feePaymentDetail.setFeeSubjectId(feeSubjectId);
		//// 测试需要  测试完需要改为上面的
		//feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		student.setAcademyenrollbatchName(batchIds);
		if(student.getLevelId()>0 && this.academyLevelBiz.findById(student.getLevelId())!=null)
		{
			//层次转换为基础层次的id
			student.setLevelId(this.academyLevelBiz.findById(student.getLevelId()).getLevelId());
		}
		result.setPage(false);
		result.setOrder("createdTime");
		result.setSort("asc");
		//student.setAcademyId(academyId);
		feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByCeduPlaymoneyAcademy(feePaymentDetail, student,starttime,endtime,result);
		return SUCCESS;
	}
	
	/**
	 * 统计选择的缴费单明细（总部）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "feepaymentdetail_count_all_cedupalymoneyacademy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyallceduList() throws Exception 
	{	
		if(feePaymentDetailIds!=null && !feePaymentDetailIds.equals(""))
		{
			String[] ids=feePaymentDetailIds.split(",");
			for(int i=0;i<ids.length;i++)
			{
				boolean isfail=false;
				FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(Integer.valueOf(ids[i]));
				DecimalFormat dfpub = new DecimalFormat("0.00"); // 保留几位小数
				if(feepd!=null)
				{
					//设置招生批次id
					Student student=this.studentBiz.findStudentById(feepd.getStudentId());
					if(null!=student)
					{
						//批次
						feepd.setFeeWayId(student.getEnrollmentBatchId());
						//学习中心
						feepd.setIsSupper(student.getBranchId());
						
						//打款金额
						//基数
						BigDecimal jishu=new BigDecimal(1);
						if((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
						{
							jishu=(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).subtract(new BigDecimal(feepd.getRefundAmount()))).divide(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
						}
						DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
						//feepd.setPayCeduAcademy(Double.valueOf(new BigDecimal(feepd.getMoneyToPay()).subtract(new BigDecimal(feepd.getAcademyDiscount()).add(new BigDecimal(feepd.getAcademyCeduDiscount()))).toString()));
						feepd.setPayCeduAcademy(Double.valueOf(df.format(((new BigDecimal(feepd.getMoneyToPay()).subtract(new BigDecimal(feepd.getAcademyDiscount()).add(new BigDecimal(feepd.getAcademyCeduDiscount())))).multiply(jishu)).doubleValue())));
					}
					//判断
					if(feepdList==null || feepdList.size()<=0)
					{				
						//feepdList.add(feepd);
					}
					else
					{
						for(FeePaymentDetail fp:feepdList)
						{
							if(fp.getFeeSubjectId()==feepd.getFeeSubjectId() && fp.getFeeWayId()==feepd.getFeeWayId() && fp.getIsSupper()==feepd.getIsSupper())
							{
								fp.setPayCeduAcademy(Double.valueOf(dfpub.format(new BigDecimal(fp.getPayCeduAcademy()).add(new BigDecimal(feepd.getPayCeduAcademy())).doubleValue())));
								isfail=true;
								break;
							}
						}
					}
					//不相等则不添加
					if(!isfail)
					{
						feepdList.add(feepd);
					}
				}
			}
			if(feepdList!=null && feepdList.size()>0)
			{
				for(FeePaymentDetail feeObj:feepdList)
				{
					//费用科目
					if(feeObj.getFeeSubjectId()>0 && feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId())!=null)
					{
						feeObj.setPaymentSubjectName(feeSubjectBiz.findFeeSubjectById(feeObj.getFeeSubjectId()).getName());
					}
					//学生相关
					if(feeObj.getStudentId()>0 && studentBiz.findStudentById(feeObj.getStudentId())!=null)
					{
						//院校
						if(studentBiz.findStudentById(feeObj.getStudentId()).getAcademyId()>0 && academyBiz.findAcademyById(studentBiz.findStudentById(feeObj.getStudentId()).getAcademyId())!=null)
						{
							feeObj.setSchoolName(academyBiz.findAcademyById(studentBiz.findStudentById(feeObj.getStudentId()).getAcademyId()).getName());
						}
						//学习中心
						if(studentBiz.findStudentById(feeObj.getStudentId()).getBranchId()>0 && branchBiz.findBranchById(studentBiz.findStudentById(feeObj.getStudentId()).getBranchId())!=null)
						{
							feeObj.setBranchName(branchBiz.findBranchById(studentBiz.findStudentById(feeObj.getStudentId()).getBranchId()).getName());
						}
						//批次
						if(studentBiz.findStudentById(feeObj.getStudentId()).getEnrollmentBatchId()>0 && academyenrollbatchBiz.findAcademyEnrollBatchById(studentBiz.findStudentById(feeObj.getStudentId()).getEnrollmentBatchId())!=null)
						{
							feeObj.setAcademyenrollbatchName(academyenrollbatchBiz.findAcademyEnrollBatchById(studentBiz.findStudentById(feeObj.getStudentId()).getEnrollmentBatchId()).getEnrollmentName());
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 添加打款单(总部)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_cedu_playmoney_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addplaymoney() throws Exception 
	{
		if(indexcount==1)
		{
			replayadd=true;
			return SUCCESS;
		}
		if(null!=payCeduAcademy && null!=feepaymentIds && !"".equals(feepaymentIds))
		{
			payCeduAcademy.setRemittanceDate(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payCeduAcademy.setCode(buildCodeBiz.getCodes(CodeEnum.PayCeduAcademyTB.getCode(),
					CodeEnum.PayCeduAcademy.getCode()));
			payCeduAcademy.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN);
			payCeduAcademy.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payCeduAcademy.setDeleteFlag(Constants.DELETE_FALSE);
			payCeduAcademy.setCreatorId(super.getUser().getUserId());
			payCeduAcademy.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			payCeduAcademy.setUpdaterId(super.getUser().getUserId());
			payCeduAcademy.setTypes(Constants.FALLBACK_TYPES_SUBMIT);
			 
			List<FeePaymentDetail> fpdlist=new ArrayList<FeePaymentDetail>();//缴费单明细集合
			List<FeePayment> fplist=new ArrayList<FeePayment>();//缴费单集合
			String[] ids=feepaymentIds.split(",");
			for(int i=0;i<ids.length;i++)
			{
				boolean isfail=false;
				FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(Integer.valueOf(ids[i]));
				if(feepd!=null)
				{
					//打款金额
					//基数
					BigDecimal jishu=new BigDecimal(1);
					if((new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
					{
						jishu=(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())).subtract(new BigDecimal(feepd.getRefundAmount()))).divide(new BigDecimal(feepd.getAmountPaied()).add(new BigDecimal(feepd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
					}
					DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
					//feepd.setPayCeduAcademy(Double.valueOf(new BigDecimal(feepd.getMoneyToPay()).subtract(new BigDecimal(feepd.getAcademyDiscount()).add(new BigDecimal(feepd.getAcademyCeduDiscount()))).toString()));
					feepd.setPayCeduAcademy(Double.valueOf(df.format(((new BigDecimal(feepd.getMoneyToPay()).subtract(new BigDecimal(feepd.getAcademyDiscount()).add(new BigDecimal(feepd.getAcademyCeduDiscount())))).multiply(jishu)).doubleValue())));
					
					//状态
					feepd.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN);
					feepd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					feepd.setUpdaterId(super.getUser().getUserId());
					fpdlist.add(feepd);
					
//					//缴费单
//					FeePayment fp=this.feePaymentBiz.findFeePaymentById(feepd.getFeePaymentId());
//					//过滤掉重复的数据，不然修改报错
//					if(fplist!=null && fplist.size()>0)
//					{
//						for(FeePayment feep:fplist)
//						{
//							if(feep.getId()==feepd.getFeePaymentId())
//							{
//								isfail=true;
//								break;
//							}
//						}
//					}
//					if(fp!=null && isfail==false)
//					{
//						fp.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN);
//						fplist.add(fp);
//					}
				}
			}
			isback=this.payCeduAcademyBiz.addPayCeduAcademyUpdateFeePaymentDetails(payCeduAcademy, fpdlist);
		}
		return SUCCESS;
	}
	
	/**
	 * 删除打款单及其修改相应的缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "del_cedu_palymoney_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String delplaymoneyList() throws Exception 
	{	
		if(payCeduAcademyId>0)
		{
			List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findFeePaymentDetailListByPayCeduAcademyId(payCeduAcademyId);
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					//缴费单明细相应的修改
					fpd.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
					fpd.setOrderCeduAcademyId(0);
					fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					fpd.setUpdaterId(super.getUser().getUserId());
				}
			}
			if(fpdlist!=null)
			{
				isfail=this.payCeduAcademyBiz.deletePayCeduAcademyUpdateFeePaymentDetails(payCeduAcademyId, fpdlist);
			}
		}
		return SUCCESS;
	}

	/**
	 * 更新打款单及其相应的缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_cedu_palymoney_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upplaymoneyList() throws Exception 
	{	
		if(payCeduAcademyId>0)
		{
			PayCeduAcademy payceduAcademy=this.payCeduAcademyBiz.findById(payCeduAcademyId);
			List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findFeePaymentDetailListByPayCeduAcademyId(payCeduAcademyId);
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					//缴费单明细相应的修改
					fpd.setStatus(payCeduAcademyStatus);
					fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					fpd.setUpdaterId(super.getUser().getUserId());
				}
			}
			if(fpdlist!=null && payceduAcademy!=null)
			{
				payceduAcademy.setStatus(payCeduAcademyStatus);
				payceduAcademy.setUpdaterId(super.getUser().getUserId());
				payceduAcademy.setUpdatedTime(new Date());
				isfail=this.payCeduAcademyBiz.updatePayCeduAcademyUpdateFeePaymentDetails(payceduAcademy, fpdlist);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 更新打款单及其相应的缴费单明细集合(状态变为已打款院校时调用这个方法，更新所有的)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_cedu_palymoney_academy_for_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upplaymoneyforacademyList() throws Exception 
	{	
		if(payCeduAcademyId>0)
		{
			PayCeduAcademy payceduAcademy=this.payCeduAcademyBiz.findById(payCeduAcademyId);
			List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findFeePaymentDetailListByPayCeduAcademyId(payCeduAcademyId);
			List<FeePaymentDetail> hislist = new ArrayList<FeePaymentDetail>();//退费单明细集合
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fpd.getId(), Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN, Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
					if(refundlist!=null && refundlist.size()>0)
					{
						
						FeePaymentDetail rfpd=refundlist.get(0);
						//还原历史数据
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						
						fpd.setStatus(payCeduAcademyStatus);	
						fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						
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
						hislist.add(rfpd);
						//更新历史缴费单(重新计算各个账户的值)
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						fpd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
						//2012-05-09  添加   招生返款需求变更   所有缴费单都必须打款院校才能招生返款
						//fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
						
					}
					else
					{
						fpd.setStatus(payCeduAcademyStatus);
						//2012-05-09  添加   招生返款需求变更   所有缴费单都必须打款院校才能招生返款
						//fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
						fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
					}
				}
			}
			////退费单
			//List<FeePaymentDetail> refundlist=null;
			if(fpdlist!=null && payceduAcademy!=null)
			{
				//refundlist=fpdlist;
				payceduAcademy.setStatus(payCeduAcademyStatus);
				payceduAcademy.setUpdaterId(super.getUser().getUserId());
				payceduAcademy.setUpdatedTime(new Date());
				if(hislist!=null && hislist.size()>0)
				{
					for(FeePaymentDetail rfpd:hislist)
					{
						fpdlist.add(rfpd);
					}
				}
				//isfail=this.payCeduAcademyBiz.updatePayCeduAcademyUpdateFeePaymentDetails(payceduAcademy, fpdlist);
				isfail=this.payCeduAcademyBiz.updatePayCeduAcademyFeePaymentDetailAndRefundfpdlist(payceduAcademy, fpdlist, hislist);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 更新打款单及其相应的缴费单明细集合(状态变为已打款院校时调用这个方法，更新所有的)_现金汇院校时总部确认问题
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_cedu_confirm_branch_palymoney_academy_for_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ceduupplaymoneyforacademyList() throws Exception 
	{	
		if(payCeduAcademyId>0)
		{
			PayCeduAcademy payceduAcademy=this.payCeduAcademyBiz.findById(payCeduAcademyId);
			List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findFeePaymentDetailListByPayCeduAcademyId(payCeduAcademyId);
			List<FeePaymentDetail> hislist = new ArrayList<FeePaymentDetail>();//退费单明细集合
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fpd.getId(), Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN, Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
					if(refundlist!=null && refundlist.size()>0)
					{
						
						FeePaymentDetail rfpd=refundlist.get(0);
						//还原历史数据
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						
						fpd.setStatus(payCeduAcademyStatus);	
						fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						
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
						hislist.add(rfpd);
						//更新历史缴费单(重新计算各个账户的值)
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						fpd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
						fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
						//总部确认时间和操作相关人员2012-04-01
						fpd.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setCeduConfirmId(super.getUser().getUserId());
					}
					else
					{
						fpd.setStatus(payCeduAcademyStatus);
						fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
						//总部确认时间和操作相关人员2012-04-01
						fpd.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setCeduConfirmId(super.getUser().getUserId());
					}
				}
			}
			////退费单
			//List<FeePaymentDetail> refundlist=null;
			if(fpdlist!=null && payceduAcademy!=null)
			{
				//refundlist=fpdlist;
				payceduAcademy.setStatus(payCeduAcademyStatus);
				payceduAcademy.setUpdaterId(super.getUser().getUserId());
				payceduAcademy.setUpdatedTime(new Date());
				if(hislist!=null && hislist.size()>0)
				{
					for(FeePaymentDetail rfpd:hislist)
					{
						fpdlist.add(rfpd);
					}
				}
				//isfail=this.payCeduAcademyBiz.updatePayCeduAcademyUpdateFeePaymentDetails(payceduAcademy, fpdlist);
				isfail=this.payCeduAcademyBiz.updatePayCeduAcademyFeePaymentDetailAndRefundfpdlist(payceduAcademy, fpdlist, hislist);
			}
		}
		return SUCCESS;
	}

	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}


	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
	}


	public String getBatchIds() {
		return batchIds;
	}


	public void setBatchIds(String batchIds) {
		this.batchIds = batchIds;
	}


	public Student getStudent() {
		return student;
	}


	public void setStudent(Student student) {
		this.student = student;
	}

	public List<FeePaymentDetail> getFeepdList() {
		return feepdList;
	}

	public void setFeepdList(List<FeePaymentDetail> feepdList) {
		this.feepdList = feepdList;
	}

	public String getFeePaymentDetailIds() {
		return feePaymentDetailIds;
	}

	public void setFeePaymentDetailIds(String feePaymentDetailIds) {
		this.feePaymentDetailIds = feePaymentDetailIds;
	}

	public PayCeduAcademy getPayCeduAcademy() {
		return payCeduAcademy;
	}

	public void setPayCeduAcademy(PayCeduAcademy payCeduAcademy) {
		this.payCeduAcademy = payCeduAcademy;
	}

	public String getFeepaymentIds() {
		return feepaymentIds;
	}

	public void setFeepaymentIds(String feepaymentIds) {
		this.feepaymentIds = feepaymentIds;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}

	public boolean isReplayadd() {
		return replayadd;
	}

	public void setReplayadd(boolean replayadd) {
		this.replayadd = replayadd;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public int getPayCeduAcademyId() {
		return payCeduAcademyId;
	}

	public void setPayCeduAcademyId(int payCeduAcademyId) {
		this.payCeduAcademyId = payCeduAcademyId;
	}

	public int getPayCeduAcademyStatus() {
		return payCeduAcademyStatus;
	}

	public void setPayCeduAcademyStatus(int payCeduAcademyStatus) {
		this.payCeduAcademyStatus = payCeduAcademyStatus;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
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
	
	
}
