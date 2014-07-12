package net.cedu.action.finance.studentaccountmanagement;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

/**
 * 学生账号_学习中心
 * 
 * @author lixiaojun
 * 
 */
public class ListStudentAccountManagementBranchAction extends BaseAction 
{
	@Autowired 
	private BranchBiz branchBiz;//学习中心_业务层接口
	private Branch branch=new Branch();//学习中心实体
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(super.getUser().getOrgId()>0)
			{
				branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			}
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
