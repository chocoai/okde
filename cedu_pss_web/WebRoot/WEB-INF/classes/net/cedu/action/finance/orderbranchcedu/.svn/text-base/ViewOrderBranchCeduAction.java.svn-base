package net.cedu.action.finance.orderbranchcedu;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部单 详情
 * 
 * @author Sauntor
 *
 */
public class ViewOrderBranchCeduAction extends BaseAction
{
	private static final long serialVersionUID = -852284843586874028L;
	
	private int orderId;
	
	private OrderBranchCedu order;
	
	private List<FeePaymentDetail> list;
	private Branch branch;
	private Branch cedu;
	
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;
	@Autowired
	private PaymentBiz paymentBiz;
	@Autowired
	private BranchBiz branchBiz;

	public String execute() throws Exception
	{
		order = orderBranchCeduBiz.findById(orderId);
		
		branch = branchBiz.findBranchById(order.getRemitterId());
		cedu = branchBiz.findBranchById(order.getRemitteeId());
		
		list = paymentBiz.findDetailByOrderBranchCeduId(orderId);
		paymentBiz.wrapFeePaymentDetail(list);
		
		setIl8nName("finance_payment");

		return SUCCESS;
	}

	public OrderBranchCedu getOrder() {
		return order;
	}

	public List<FeePaymentDetail> getList() {
		return list;
	}

	public Branch getBranch() {
		return branch;
	}

	public Branch getCedu() {
		return cedu;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
}
