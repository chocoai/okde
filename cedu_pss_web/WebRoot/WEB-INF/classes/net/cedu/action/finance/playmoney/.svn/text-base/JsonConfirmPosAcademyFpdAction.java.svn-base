package net.cedu.action.finance.playmoney;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.string.MoneyUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * POS直汇院校——总部确认
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonConfirmPosAcademyFpdAction extends BaseAction
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
	private int feeWayId;//缴费方式
	
	//统计
	private String allMoney;//统计金额
	
	//*****************确认缴费单明细*********************//
	private int fpdId;//缴费单明细Id
	private boolean isback=false;//返回参数
	
	private String fpdIds;//缴费单明细Ids字符串集合     批量确认
	
	
	/**
	 * 获取缴费单明细数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pos_academy_fpd_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyCount() throws Exception 
	{
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(feeWayId);
		feePayment.setCode(code);
		result.setRecordCount(this.feePaymentDetailBiz.findFeePaymentDetailCountByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result));
		return SUCCESS;
	}
	
	/**
	 * 获取缴费单明细列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_pos_academy_fpd_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String playmoneyList() throws Exception 
	{	
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(feeWayId);	
		feePayment.setCode(code);
		result.setList(this.feePaymentDetailBiz.findFeePaymentDetailListByPagePosCedu(feePaymentDetail, feePayment, student, startDate, endDate, result));
		return SUCCESS;
	}
	
	/**
	 * 统计缴费单明细金额
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_pos_academy_fpd_all_money_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String countPosAcademy() throws Exception 
	{	
		feePaymentDetail.setStartStatusId(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN);
		feePaymentDetail.setEndStatusId(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO);
		FeePayment feePayment=new FeePayment();
		feePayment.setFeeWayId(feeWayId);	
		feePayment.setCode(code);
		//实缴金额
		//allMoney=this.feePaymentDetailBiz.countFpdAllMoneyForPOSEbank(feePaymentDetail, feePayment, student, startDate, endDate);
		//打款院校金额
		allMoney=this.feePaymentDetailBiz.countFpdPayAcademyMoneyForPOSEbank(feePaymentDetail, feePayment, student, startDate, endDate);
		return SUCCESS;
	}
	
	/**
	 * 确认缴费单明细已打款院校
	 * @return
	 * @throws Exception
	 */
	@Action(value = "confirm_pos_eback_fpd_for_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String confrimplaymoneyList() throws Exception 
	{	
		FeePaymentDetail fpd = feePaymentDetailBiz.findById(fpdId);
		if(fpd!=null && fpd.getStatus()<Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO)
		{
			FeePaymentDetail rfpd=null;//缴费单明细对应的退费单明细
			//汇款金额(不需要乘以基数)
	//		//基数
	//		BigDecimal jishu=new BigDecimal(1);
	//		if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
	//		{
	//			jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
	//		}
	//		DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
			
			fpd.setPayCeduAcademy((new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyDiscount()).add(new BigDecimal(fpd.getAcademyCeduDiscount())))).doubleValue());
			fpd.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
			fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
			//总部确认时间和操作相关人员2012-04-01
			fpd.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
			fpd.setCeduConfirmId(super.getUser().getUserId());
			
			fpd.setUpdaterId(super.getUser().getUserId());
			fpd.setUpdatedTime(new Date());
			
			//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
			List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fpd.getId(), Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN, Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
			if(refundlist!=null && refundlist.size()>0)
			{
				rfpd=refundlist.get(0);
				//还原历史数据
				fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
				fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
				fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
				fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
				
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
				//更新历史缴费单(重新计算各个账户的值)
				fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
				fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
				fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
				fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
				fpd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
			}
			else
			{			
				//账户金额变动
				fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
				fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());			
			}
			
			isback=this.feePaymentDetailBiz.updateFeePaymentDetailAndTfd(fpd, rfpd);
		}
		return SUCCESS;
	}
	
	/**
	 * 批量确认缴费单明细已打款院校
	 * @return
	 * @throws Exception
	 */
	@Action(value = "batch_confirm_pos_eback_fpd_for_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String batchconfrimplaymoneyList() throws Exception 
	{	
		if(fpdIds!=null && fpdIds.length()>0)
		{
			String[] ids=fpdIds.split(",");
			FeePaymentDetail fpd=null;
			List<FeePaymentDetail> fpdList=new ArrayList<FeePaymentDetail>();
			List<FeePaymentDetail> rfpdList=new ArrayList<FeePaymentDetail>();
			for(int i=0;i<ids.length;i++)
			{
				fpd = feePaymentDetailBiz.findById(Integer.valueOf(ids[i]));
				if(fpd!=null && fpd.getStatus()<Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO)
				{
					FeePaymentDetail rfpd=null;//缴费单明细对应的退费单明细
					//汇款金额(不需要乘以基数)
			//		//基数
			//		BigDecimal jishu=new BigDecimal(1);
			//		if((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount()))).compareTo(new BigDecimal(0))!=0)
			//		{
			//			jishu=(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).divide(new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())),12,BigDecimal.ROUND_HALF_UP);
			//		}
			//		DecimalFormat df = new DecimalFormat("0.00"); // 保留几位小数
					
					fpd.setPayCeduAcademy((new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyDiscount()).add(new BigDecimal(fpd.getAcademyCeduDiscount())))).doubleValue());
					fpd.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
					fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN);//可以开始招生返款
					//总部确认时间和操作相关人员2012-04-01
					fpd.setCeduConfirmTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
					fpd.setCeduConfirmId(super.getUser().getUserId());
					fpd.setUpdaterId(super.getUser().getUserId());
					fpd.setUpdatedTime(new Date());
					//统计退费明细审批通过但是不可以退费的情况（已经处理过，这种状态下一个缴费单只允许有一条）
					List<FeePaymentDetail> refundlist=this.feePaymentBiz.findRefundPaymentDetailListByFpdIdStartIdEndIdTfStatusId(fpd.getId(), Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN, Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO, Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
					if(refundlist!=null && refundlist.size()>0)
					{
						rfpd=refundlist.get(0);
						//还原历史数据
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).subtract(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).subtract(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						
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
						//更新历史缴费单(重新计算各个账户的值)
						fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(rfpd.getCeduAccount()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(rfpd.getAcademyAccount()))).doubleValue());
						fpd.setChannelAccount((new BigDecimal(fpd.getChannelAccount()).add(new BigDecimal(rfpd.getChannelAccount()))).doubleValue());
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(rfpd.getBranchAccount()))).doubleValue());
						fpd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
					}
					else
					{			
						//账户金额变动
						fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
						fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());			
					}
					
					fpdList.add(fpd);
					if(rfpd!=null)
					{
						rfpdList.add(rfpd);
					}
				}
			}
			isback=this.feePaymentDetailBiz.updateFpdListAndTfdList(fpdList, rfpdList);
		}
		return SUCCESS;
	}
	
	/**
	 * 对直汇院校确认过后的pos直汇院校、网银院校进行回退   已进行退费的不能回退、已招生返款的不能回退
	 * @return
	 * @throws Exception
	 */
	@Action(value = "fallback_pos_eback_fpd_for_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fallbackposcedu() throws Exception 
	{	
		FeePaymentDetail fpd = feePaymentDetailBiz.findById(fpdId);
		if(fpd!=null && fpd.getStatus()==Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO && fpd.getRefundAmount()==0 && fpd.getRefundLock()==Constants.LOCKING_FALSE && fpd.getRebateStatus()<=Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN)
		{
			FeePayment fp=this.feePaymentBiz.findFeePaymentById(fpd.getFeePaymentId());
			if(fp!=null && (fp.getFeeWayId()==Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO || fp.getFeeWayId()==Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO))
			{
				//打款院校钱
				fpd.setPayCeduAcademy((new BigDecimal(fpd.getMoneyToPay()).subtract(new BigDecimal(fpd.getAcademyDiscount()).add(new BigDecimal(fpd.getAcademyCeduDiscount())))).doubleValue());
				fpd.setStatus(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN); //回退一个状态
				fpd.setRebateStatus(Constants.PAYMENT_REBATE_STATUS_CHU_SHI_ZHUANG_TAI);//返款  初始状态
				
				//总部确认时间和操作相关人员
				fpd.setCeduConfirmTime(null);
				fpd.setCeduConfirmId(0);
						
				//账户金额变动 回退到原来状态
				fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).add(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());
				fpd.setAcademyAccount((new BigDecimal(fpd.getAcademyAccount()).subtract(new BigDecimal(fpd.getPayCeduAcademy()))).doubleValue());			
				
				fpd.setUpdaterId(super.getUser().getUserId());
				fpd.setUpdatedTime(new Date());
				
				isback=this.feePaymentDetailBiz.updateFeePaymentDetail(fpd);
			}
		}
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

	public int getFeeWayId() {
		return feeWayId;
	}

	public void setFeeWayId(int feeWayId) {
		this.feeWayId = feeWayId;
	}

	public int getFpdId() {
		return fpdId;
	}

	public void setFpdId(int fpdId) {
		this.fpdId = fpdId;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
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
	
	
}
