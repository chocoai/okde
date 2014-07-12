package net.cedu.action.finance.invoicebook;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收据授权
 * 
 * @author gaolei
 * 
 */

public class InvoiceBookAuthorizeAction extends BaseAction {

	private int tab=1;   //页签
	@Action(results = { @Result(name = "success",type="redirect", location = "list_invoice_book?tab=${tab}")	
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
