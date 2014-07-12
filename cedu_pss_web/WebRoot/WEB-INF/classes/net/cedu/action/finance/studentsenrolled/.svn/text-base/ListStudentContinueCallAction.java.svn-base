package net.cedu.action.finance.studentsenrolled;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 老生续读
 *
 */
public class ListStudentContinueCallAction extends BaseAction
{
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	private Branch branch=new Branch();//学习中心实体
	private List<Branch> branchList = new ArrayList<Branch>();
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			branchList=this.branchBiz.findBranchAllByDeleteFlag();
			Collections.sort(branchList, new Comparator() {
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

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}
	
}
