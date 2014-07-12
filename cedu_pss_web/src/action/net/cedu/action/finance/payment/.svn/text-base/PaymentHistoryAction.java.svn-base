package net.cedu.action.finance.payment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

/**
 * 历史缴费
 * 
 * @author xiao
 * 
 */
public class PaymentHistoryAction  extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz;// 学习中心业务接口
	private Branch branch;// 学习中心集合
	private List<Branch> branchlist=new ArrayList<Branch>();
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			//branch = this.branchBiz.findBranchById(super.getUser().getOrgId());
			branchlist=this.branchBiz.findBranchForModel();
			Collections.sort(branchlist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
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
