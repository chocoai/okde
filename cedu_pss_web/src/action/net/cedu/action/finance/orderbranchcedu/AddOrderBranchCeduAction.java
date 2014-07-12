package net.cedu.action.finance.orderbranchcedu;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加汇款总部单 页面
 * @author Sauntor
 *
 */
public class AddOrderBranchCeduAction extends BaseAction
{
	private static final long serialVersionUID = 2662391448744405565L;
	
	private int branchId;
	
	private Branch branch;
	
	@Autowired
	private BranchBiz branchBiz;

	public String execute() throws Exception
	{
		branch = branchBiz.findBranchById(branchId);

		return SUCCESS;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Branch getBranch() {
		return branch;
	}
}
