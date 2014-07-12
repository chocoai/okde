package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人招生指标（潜在学生）
 * 
 * @author yangdongdong
 * 
 */
public class IndividualEnrollmentTargetsAction extends BaseAction {
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