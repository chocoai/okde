package net.cedu.action.finance.branchchannelpayment;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderCeduChannel;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 合作方返款单详情
 * 
 * @author Sauntor
 *
 */
public class ViewBranchChannelAction extends BaseAction
{
	private static final long serialVersionUID = 702478970614759299L;

	private int id;
	
	private OrderCeduChannel order;
	private double adjustedAmount = 0D; //调整金额
	
	private double totalAmount = 0D; //交款总额
	private int count = 0; //总人数
	
	private String branchName;
	private String channelTypeName;
	private String channelName;
	
	private List<FeePaymentDetail> fpdList;
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private PaymentBiz paymentBiz;
	
	public String execute() throws Exception
	{
		order = orderCeduChannelBiz.findById(id);
		if(order!=null)
		{
			//招生途径
			EnrollmentSource enrollmentSource=enrollmentSourceBiz.findEnrollmentSourceById(order.getChannelType());
			if(enrollmentSource!=null)
			{
				channelTypeName = enrollmentSource.getName();
			}
			//合作方
			Channel channel = channelBiz.findChannel(order.getChannelId());
			if(channel!=null)
			{
				channelName = channel.getName();
			}
			//学习中心
			Branch branch=branchBiz.findBranchById(order.getBranchId());
			if(branch!=null)
			{
				branchName =branch.getName();
			}
			fpdList = feePaymentDetailBiz.findByOrderCeduChannelId(order.getId());
			if(fpdList!=null && fpdList.size()>0)
			{
				paymentBiz.wrapFeePaymentDetail(fpdList);
				
				setIl8nName("finance_payment");
				
				for(FeePaymentDetail fpd : fpdList)
				{
					fpd.setStatusName(getText("detail.status."+fpd.getStatus()));
					fpd.setRebateStatusName(getText("detail.status."+fpd.getRebateStatus()));
					fpd.setJiaofeiValue((new BigDecimal(fpd.getAmountPaied()).add(new BigDecimal(fpd.getRechargeAmount())).subtract(new BigDecimal(fpd.getRefundAmount()))).doubleValue());
				}
			}
			adjustedAmount = order.getAmountPaied().subtract(order.getAmountToPay()).doubleValue();
			
		}
		return SUCCESS;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public OrderCeduChannel getOrder() {
		return order;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public int getCount() {
		return count;
	}

	public String getBranchName() {
		return branchName;
	}

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public String getChannelName() {
		return channelName;
	}

	public List<FeePaymentDetail> getFpdList() {
		return fpdList;
	}

	public double getAdjustedAmount() {
		return adjustedAmount;
	}
}
