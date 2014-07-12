package net.cedu.action.finance.orderbranchcedu;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 汇款总部(中心)_添加
 * 
 * @author lixiaojun
 * 
 */
public class AddOrderBranchCedu1Action extends BaseAction
{
	
	@Autowired 
	private BranchBiz branchBiz;//学习中心业务接口
	private Branch branch=new Branch();//学习中心实体
	private Branch cedu=new Branch();//总部
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			cedu = this.branchBiz.findBranchById(BranchEnum.Admin.value());
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

	public Branch getCedu() {
		return cedu;
	}

	public void setCedu(Branch cedu) {
		this.cedu = cedu;
	}
	
}
