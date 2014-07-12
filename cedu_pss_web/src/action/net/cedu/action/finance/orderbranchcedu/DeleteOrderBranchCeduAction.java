package net.cedu.action.finance.orderbranchcedu;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.finance.FeePaymentDetail;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 删除 汇款总部单
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class DeleteOrderBranchCeduAction extends BaseAction
{
	private static final long serialVersionUID = -2834421224878766027L;
	
	private int orderId;
	
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;
	
	@Autowired
	private PaymentBiz paymentBiz;

	public String execute() throws Exception
	{	
		if(orderId>0)
		{
			List<FeePaymentDetail> list = paymentBiz.findDetailByOrderBranchCeduId(orderId);
			if(list!=null && list.size()>0)
			{
				for(FeePaymentDetail fp:list)
				{
					fp.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
					fp.setOrderBranchCeduId(0);
					fp.setUpdaterId(super.getUser().getUserId());
					fp.setUpdatedTime(new Date());
				}
			}
			orderBranchCeduBiz.deleteOrderBranchCeduUpdateFeePaymentDetails(orderId, list);
		}
		return SUCCESS;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
