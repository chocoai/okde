package net.cedu.action.finance.invoicebook;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 票本
 * 
 * @author gaolei
 * 
 */

public class ListInvoiceBookAction extends BaseAction {

	
	@Autowired
	private BranchBiz branchbiz;                             //学习中心Biz
	private List<Branch> branchlst=new ArrayList<Branch>();  //学习中心数据
	private int tab;                                         //页签
	@Action(results = { @Result(name = "input", location = "list_invoicebook.jsp"),
			 @Result(name = "invoice", location = "list_invoicebookno.jsp")
	})
	public String excute() throws Exception
	{
		//执行get请求
		if(super.isGetRequest())
		{
			if(tab==1)
			{
				//获取学习中心
				branchlst=branchbiz.findListById(super.getUser().getOrgId());
				Collections.sort(branchlst, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Branch) arg0).getName();
						String name2 = ((Branch) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
				return INPUT;
			}else
			{
			
				return "invoice";
			}
			
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
