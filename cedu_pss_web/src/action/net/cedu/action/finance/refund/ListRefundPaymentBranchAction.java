package net.cedu.action.finance.refund;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退费单查询（中心）
 * 
 * @author xiao
 * 
 */
public class ListRefundPaymentBranchAction extends BaseAction
{
	@Autowired
	private BranchBiz branchBiz;// 学习中心业务接口
	private Branch branch;// 学习中心集合

	@Override
	public String execute() throws Exception {
		branch = this.branchBiz.findBranchById(super.getUser().getOrgId());
		return super.execute();
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

}

