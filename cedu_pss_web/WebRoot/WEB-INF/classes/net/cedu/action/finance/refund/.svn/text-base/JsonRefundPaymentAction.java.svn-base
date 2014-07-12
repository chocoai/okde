package net.cedu.action.finance.refund;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.RefundBranchBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.RefundBranch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonRefundPaymentAction extends BaseAction {
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private RefundBranchBiz refundBranchBiz;
	
	private int feePaymentId = 0;
	private int status = 0;//退费单/退费单明细状态
	private int status2 = 0;//费用表状态
	private boolean isback = false;
	
	/**
	 * 修改退费单状态及其对应退费单明细（成功退费）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_status_refund_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateStatusRefundPayment() throws Exception 
	{	
		if(feePaymentId!=0 && status!=0 && status2!=0)
		{
			FeePayment fp = feePaymentBiz.findFeePaymentById(feePaymentId);
			if(fp!=null)
			{
				//修改退费单状态
				fp.setStatus(status);
				fp.setUpdaterId(super.getUser().getUserId());
				fp.setUpdatedTime(DateUtil.getNow());
				//修改退费单明细状态
				List<FeePaymentDetail> fpdList = feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePaymentId);
				if(fpdList!=null && fpdList.size()>0)
				{
					for(FeePaymentDetail fpd : fpdList)
					{
						fpd.setStatus(status);
						fpd.setUpdaterId(super.getUser().getUserId());
						fpd.setUpdatedTime(DateUtil.getNow());
					}
				}
				//修改费用状态
				RefundBranch temp = new RefundBranch();
				temp.setRefundPaymentId(feePaymentId);
				List<RefundBranch> rbList = refundBranchBiz.findRefundBranchByParam(temp);
				if(rbList!=null && rbList.size()>0)
				{
					for(RefundBranch rb : rbList)
					{
						rb.setStatus(status2);
						rb.setUpdaterId(super.getUser().getUserId());
						rb.setUpdatedTime(DateUtil.getNow());
						refundBranchBiz.updateRefundBranch(rb);
					}
				}
				isback = feePaymentBiz.updateFeePaymentAndFeePaymentDetailList(fp, fpdList);
			}
		}
		return SUCCESS;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getStatus2() {
		return status2;
	}

	public void setStatus2(int status2) {
		this.status2 = status2;
	}

}
