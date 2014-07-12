package net.cedu.action.finance.branchchannelpayment;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款单审核(包括商务审核、财务审核等)
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonAuditOrderCeduChannel extends BaseAction
{
	private static final long serialVersionUID = -8983268594345215706L;

	private int orderId;
	
	private int errno;
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;
	
	/**
	 * 商务审核
	 */
	@Action(value="audit_order_by_business")
	public String execute() throws Exception
	{
		return doAudit(Constants.PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE);
	}
	
	/**
	 * 财务审核
	 */
	@Action(value="audit_order_by_finance")
	public String financeAudit() throws Exception
	{
		return doAudit(Constants.PAYMENT_STATUS_CAI_WU_YI_SHEN_HE);
	}
	
	/**
	 * 执行审核操作
	 * @param newStatus 审核成功后将会成为的新状态
	 * @return
	 * @throws Exception
	 */
	private String doAudit(int newStatus) throws Exception
	{
		try{
			orderCeduChannelBiz.updateForAudition(orderId, newStatus, getUser().getUserId());
		} catch(Exception e){
			errno = -1;
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public int getErrno() {
		return errno;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
}
