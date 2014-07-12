package net.cedu.action.finance.managerinvoice;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发票管理
 * 
 * @author gaolei
 * 
 */

public class ListManagerInvoiceCenterAction extends BaseAction {

	
	@Autowired
	private BranchBiz branchbiz;            //学习中心biz
	private Branch branch;                  //学习中心实体 

	@Action(results = { @Result(name = "input", location = "list_manager_invoice_center.jsp")
	})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			
				branch=branchbiz.findBranchById(super.getUser().getOrgId());
				return INPUT;
		}
		return INPUT;
		
	}

	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	
	
}
