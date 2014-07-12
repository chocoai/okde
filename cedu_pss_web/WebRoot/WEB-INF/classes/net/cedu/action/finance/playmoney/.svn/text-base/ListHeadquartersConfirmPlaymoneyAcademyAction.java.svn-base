package net.cedu.action.finance.playmoney;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校打款确认(总部)
 * 
 * @author lixiaojun
 * 
 */
public class ListHeadquartersConfirmPlaymoneyAcademyAction extends BaseAction
{
	
	@Autowired 
	private BranchBiz branchBiz;//学习中心业务接口
	private Branch branch=new Branch();//学习中心实体
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	private List<Academy> academyList=new ArrayList<Academy>();//院校集合
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			academyList=this.academyBiz.findAllAcademyByFlagFalse();
			Collections.sort(academyList, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Academy) arg0).getName();
					String name2 = ((Academy) arg1).getName();
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

	public List<Academy> getAcademyList() {
		return academyList;
	}

	public void setAcademyList(List<Academy> academyList) {
		this.academyList = academyList;
	}
	
}
