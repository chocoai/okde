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
 * 查询收据按票本
 * 
 * @author gaolei
 * 
 */

public class ListInvoiceAllAction extends BaseAction {

	
	@Action(results = { @Result(name = "success", location = "list_invoice_all.jsp")	
	})
	public String excute() throws Exception
	{
		return SUCCESS;	
	}

	

	
	
}
