package net.cedu.action.finance.playmoney;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校打款(中心)_添加
 * 
 * @author lixiaojun
 * 
 */
public class AddBranchPlaymoneyAcademyAction extends BaseAction
{
	
	@Autowired 
	private BranchBiz branchBiz;//学习中心业务接口
	private Branch branch=new Branch();//学习中心实体
	
	@Autowired 
	private AcademyBiz academyBiz;//院校业务接口
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			branch=this.branchBiz.findBranchById(super.getUser().getOrgId());
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
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

	public List<Academy> getAcademylist() {
		return academylist;
	}

	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}
	
}
