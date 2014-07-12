package net.cedu.action.finance.payment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

public class UpdateStudentForceFeeAction extends BaseAction
{
	
	@Autowired
	private BranchBiz branchBiz;// 学习中心业务接口
	@Autowired
	private AcademyBiz academyBiz;//院校
	private Branch branch;// 学习中心集合
	private List<Branch> branchlist=new ArrayList<Branch>();
	private String academyIds;
	
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
			academyIds=academyBiz.findAcademyIdsByForceFeePolicyStatus(1);
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

	public String getAcademyIds() {
		return academyIds;
	}

	public void setAcademyIds(String academyIds) {
		this.academyIds = academyIds;
	}
	
}
