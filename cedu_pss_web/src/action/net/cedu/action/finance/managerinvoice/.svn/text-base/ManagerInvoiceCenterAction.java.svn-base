package net.cedu.action.finance.managerinvoice;

import net.cedu.action.BaseAction;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;


/**
 * 发票管理（中心）
 * 
 * @author gaolei
 * 
 */

public class ManagerInvoiceCenterAction extends BaseAction {

	
	
	
	
	private int tab=2;     //页签

//	@Action(results = { @Result(name = "success",type="redirect" ,location = "list_manager_invoice_center?tab=${tab}")
//	})
	@Action(results = { @Result(name = "success",type="redirect" ,location = "list_manager_invoice_center_sign_false?tab=${tab}")
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
