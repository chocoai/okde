package net.cedu.action.finance.orderbranchcedu;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.entity.finance.OrderBranchCedu;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 汇款总部单 Ajax 列表查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonListOrderBranchCeduAction extends BaseAction implements ModelDriven<OrderBranchCedu>
{
	private static final long serialVersionUID = -7537001716784282950L;
	
//	private int branchId;
	
	private OrderBranchCedu order = new OrderBranchCedu();
	
	private Date startDate;
	private Date endDate;
	private String amount;//
	
	private PageResult<OrderBranchCedu> result = new PageResult<OrderBranchCedu>();
	
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;
	
	public String execute() throws Exception
	{
//		order.setRemitterId(branchId);
//		order.setRemitteeId(BranchEnum.Admin.value());
		
		List<OrderBranchCedu> list = orderBranchCeduBiz.find(order, startDate, endDate,amount, result);
		
		orderBranchCeduBiz.wrap(list); //查询汇款/收款单位名称

		result.setList(list);
		
		return SUCCESS;
	}

	public OrderBranchCedu getModel() {
		return order;
	}

	public PageResult<OrderBranchCedu> getResult() {
		return result;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

//	public void setBranchId(int branchId) {
//		this.branchId = branchId;
//	}
}
