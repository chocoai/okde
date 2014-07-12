package net.cedu.action.finance.orderbranchcedu;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * POS直汇总部单分页查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonFeePaymentDetailAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private int fpdId;
	private int branchId;
	private int status;
	private String fpdCode;
	
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();

	@Autowired
	private PaymentBiz paymentBiz;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	
	/* ====================== POS ==================== */
	// POS分页缴费单明细
	@Action("json_list_pos_fpd")
	public String execute() throws Exception {
		return list(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU);
	}
	
	// POS缴费单明细总数
	@Action("json_count_pos_fpd")
	public String countPos() throws Exception {
		return count(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU);
	}
	
	/* ====================== Third Party ==================== */
	// 第三方缴费单明细
	@Action("json_list_3rd_party_fpd")
	public String listTP() throws Exception {
		return list(Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU);
	}
	
	// 第三方缴费单明细总数
	@Action("json_count_3rd_fpd")
	public String countTP() throws Exception {
		return count(Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU);
	}
	
	/* ====================== InternetBank ==================== */
	// 缴费单明细
	@Action("json_list_e_bank_fpd")
	public String listEB() throws Exception {
		return list(Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU);
	}
	
	// 明细总数
	@Action("json_count_e_bank_fpd")
	public String countEB() throws Exception {
		return count(Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU);
	}
	
	/**
	 * 分页缴费单明细
	 */
	public String list(int feeWayId) throws Exception {
		FeePaymentDetail feePaymentDetail = new FeePaymentDetail();
		feePaymentDetail.setStatus(status);
		
		FeePayment fp = new FeePayment();
		fp.setFeeWayId(feeWayId);
		
		if(StringUtils.isNotBlank(fpdCode))
			feePaymentDetail.setCode(fpdCode);
		
		Student student = new Student();
		student.setBranchId(branchId);
		
		List<FeePaymentDetail> list = paymentBiz.findFeePaymentDetailsPageList(feePaymentDetail, fp, student, result);
		
		result.setList(list);
		
		return SUCCESS;
	}
	
	/**
	 * 缴费单明细总数
	 * @return
	 * @throws Exception
	 */
	public String count(int feeWayId) throws Exception {
		FeePaymentDetail feePaymentDetail = new FeePaymentDetail();
		feePaymentDetail.setStatus(status);
		
		FeePayment fp = new FeePayment();
		fp.setFeeWayId(feeWayId);
		
		if(StringUtils.isNotBlank(fpdCode))
			feePaymentDetail.setCode(fpdCode);
		
		Student student = new Student();
		student.setBranchId(branchId);
		
		int count = paymentBiz.findFeePaymentDetailsPageCount(feePaymentDetail, fp, student, result);
		
		result.setRecordCount(count);
		
		return SUCCESS;
	}
	
	/**
	 * 总部确认缴费单明细
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("json_confirm_fpd_by_cedu")
	public String confirm() throws Exception {
		FeePaymentDetail fpd = feePaymentDetailBiz.findById(fpdId);
		if(fpd.getStatus()<Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN)
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
			}
			else
			{			
				//账户金额变动
				fpd.setBranchAccount((new BigDecimal(fpd.getBranchAccount()).subtract(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());
				fpd.setCeduAccount((new BigDecimal(fpd.getCeduAccount()).add(new BigDecimal(fpd.getPayBranchCedu()))).doubleValue());			
			}
			//feePaymentDetailBiz.modify(fpd);
			this.feePaymentDetailBiz.updateFeePaymentDetailAndTfd(fpd, rfpd);
		}
		return SUCCESS;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public void setFpdId(int fpdId) {
		this.fpdId = fpdId;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void setFpdCode(String fpdCode) {
		this.fpdCode = fpdCode;
	}
}
