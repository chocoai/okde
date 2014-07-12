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
 * 收据查询
 * 
 * @author gaolei
 * 
 */

public class ListInvoiceBooksAction extends BaseAction {

	
	@Autowired
	private BranchBiz branchbiz;                           //学习中心Biz
	private List<Branch> branchlst=new ArrayList<Branch>();//学习中心数据
	private int tab;                                       //页签
	@Action(results = {
			 @Result(name = "input", location = "list_invoice_books.jsp")
	})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			
				//获取学习中心
				branchlst=branchbiz.findListById(super.getUser().getOrgId());
				return INPUT;
			
			
		}
		return INPUT;
		
	}

	public List<Branch> getBranchlst() {
		return branchlst;
	}

	public void setBranchlst(List<Branch> branchlst) {
		this.branchlst = branchlst;
	}

	public int getTab() {
		return tab;
	}

	public void setTab(int tab) {
		this.tab = tab;
	}

	
	
}
