package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 已使用的优惠卷
 * @author lixiaojun
 *
 */
public class ListStuDiscountAppUsingAction extends BaseAction
{
	
	@Autowired 
	private BranchBiz branchBiz;//学习中心业务接口
	private Branch branch=new Branch();//学习中心集合
	private List<Branch> branchlist=new ArrayList<Branch>();
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			//branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			branchlist=this.branchBiz.findBranchForModel();
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
	public List<Branch> getBranchlist() {
		return branchlist;
	}
	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}
	
}
