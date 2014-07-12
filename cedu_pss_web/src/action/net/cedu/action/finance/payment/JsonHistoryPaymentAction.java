package net.cedu.action.finance.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 历史缴费单
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonHistoryPaymentAction extends BaseAction 
{
	
	@Autowired
	private PaymentBiz paymentBiz;//缴费单业务层接口
	private FeePayment feePayment=new FeePayment();//缴费单实体
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务接口
	private FeePaymentDetail feePaymentDetail =new FeePaymentDetail();//缴费单明细实体
	private List<FeePaymentDetail> feePaymentList=new ArrayList<FeePaymentDetail>();//缴费单明细集合
	
	private int globalIndex;
	private int isFee;//是否收款
	private int indexcount;
	
	private boolean isback=false;//
	private int count;
	
	
	/**
	 * 添加历史缴费单
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_history_fee_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addhistorypayment() throws Exception 
	{
		if(indexcount==1)
		{
			isback=true;
			return SUCCESS;
		}
		//feePayment.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setCreatorId(super.getUser().getUserId());
		feePayment.setDeleteFlag(Constants.DELETE_FALSE);
		feePayment.setPamentType(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
		feePayment.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
		feePayment.setUpdaterId(super.getUser().getUserId());
		if(isFee==1)
		{
			//缴费方式不同收费时的缴费单状态不同
			if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU)
			{
				feePayment.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);//缴费单状态(前期跟明细一样)
			}
			else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU)
			{
				feePayment.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);//缴费单状态(前期跟明细一样)
			}
			else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO)
			{
				feePayment.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);//缴费单状态(前期跟明细一样)
			}
			else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU || feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU)
			{
				feePayment.setStatus(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU);//缴费单状态(前期跟明细一样)
			}
			else if(feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO || feePayment.getFeeWayId()==Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO)
			{
				feePayment.setStatus(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN);//缴费单状态(前期跟明细一样)
			}
		}
		else
		{
			feePayment.setStatus(Constants.PAYMENT_STATUS_YI_KAI_DAN);
		}
		if(globalIndex>0)
		{
			FeePaymentDetail fpDetail=null;
			for(int i=1;i<=globalIndex;i++)
			{
				fpDetail=new FeePaymentDetail();
				String feeSubjectId="feeSubjectId"+i;
				//String batchId="batchId"+i;
				String academyDiscount="academyDiscount"+i;
				String academyCeduDiscount="academyCeduDiscount"+i;
				String ceduDiscount="ceduDiscount"+i;
				String branchDiscount="branchDiscount"+i;
				String channelDiscount="channelDiscount"+i;
				String amountPaied="amountPaied"+i;
				fpDetail.setCode(i+"");
				fpDetail.setStudentId(feePayment.getStudentId());
				fpDetail.setFeeSubjectId(Integer.valueOf(request.getParameter(feeSubjectId)));
				//fpDetail.setBatchId(Integer.valueOf(request.getParameter(batchId)));
				fpDetail.setAcademyDiscount(Double.valueOf(request.getParameter(academyDiscount)));
				fpDetail.setAcademyCeduDiscount(Double.valueOf(request.getParameter(academyCeduDiscount)));
				fpDetail.setCeduDiscount(Double.valueOf(request.getParameter(ceduDiscount)));
				fpDetail.setBranchDiscount(Double.valueOf(request.getParameter(branchDiscount)));
				fpDetail.setChannelDiscount(Double.valueOf(request.getParameter(channelDiscount)));
				fpDetail.setAmountPaied(Double.valueOf(request.getParameter(amountPaied)));
				fpDetail.setDiscountAmount(Double.valueOf((new BigDecimal(fpDetail.getAcademyDiscount()).add(new BigDecimal(fpDetail.getAcademyCeduDiscount())).add(new BigDecimal(fpDetail.getCeduDiscount())).add(new BigDecimal(fpDetail.getBranchDiscount())).add(new BigDecimal(fpDetail.getChannelDiscount()))).toString()));
				fpDetail.setTypes(Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE);
				fpDetail.setIsSupper(Constants.FEE_PAYMENT_IS_SUPPER_FALSE);
				fpDetail.setIsInvoiced(Constants.FEE_PAYMENT_IS_INVOICED_FALSE);
				fpDetail.setMoneyToPay(Double.valueOf((new BigDecimal(fpDetail.getDiscountAmount()).add(new BigDecimal(fpDetail.getAmountPaied()))).toString()));
				fpDetail.setStatus(feePayment.getStatus());
				fpDetail.setCreatedTime(feePayment.getCreatedTime());
				fpDetail.setCreatorId(super.getUser().getUserId());
				fpDetail.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				fpDetail.setUpdaterId(super.getUser().getUserId());
				feePaymentList.add(fpDetail);
			}
			count=this.paymentBiz.addHistoryFeePaymenAndDetails(feePaymentList, feePayment,isFee);
		}
		return SUCCESS;
	}


	public FeePayment getFeePayment() {
		return feePayment;
	}


	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}


	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}


	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}


	public boolean isIsback() {
		return isback;
	}


	public void setIsback(boolean isback) {
		this.isback = isback;
	}


	public int getGlobalIndex() {
		return globalIndex;
	}


	public void setGlobalIndex(int globalIndex) {
		this.globalIndex = globalIndex;
	}


	public int getIsFee() {
		return isFee;
	}


	public void setIsFee(int isFee) {
		this.isFee = isFee;
	}


	public int getIndexcount() {
		return indexcount;
	}


	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}
	
}
