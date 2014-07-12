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
 * 发票管理（总部）
 * 
 * @author gaolei
 * 
 */

public class ManagerInvoiceCeduAction extends BaseAction {

	
	private int tab=1;   //页签

	@Action(results = { @Result(name = "success",type="redirect" ,location = "list_manager_invoice_cedu?tab=${tab}")
	})
	public String excute() throws Exception
	{
		return SUCCESS;		
	}
	public int getTab() {
		return tab;
	}
	public void setTab(int tab) {
		this.tab = tab;
	}
	
	
	
}
