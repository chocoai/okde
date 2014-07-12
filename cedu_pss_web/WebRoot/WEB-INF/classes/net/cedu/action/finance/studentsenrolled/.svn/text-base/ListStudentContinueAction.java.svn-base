package net.cedu.action.finance.studentsenrolled;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

/**
 * 老生续读
 *
 */
public class ListStudentContinueAction extends BaseAction
{
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	private Branch branch=new Branch();//学习中心实体
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			return INPUT;
		}
		return SUCCESS;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
