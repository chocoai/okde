package net.cedu.action.orgstructure.jurisdiction;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.orgstructure.Department;
import net.cedu.entity.orgstructure.Jurisdiction;
import net.cedu.model.orgstructure.BranchDepartment;

import org.springframework.beans.factory.annotation.Autowired;

public class UpdateJurisdictionAction extends BaseAction {

	private static final long serialVersionUID = 1873940625023530861L;
	
	@Autowired
	private BranchBiz branchBiz;
	private List<Branch> blist=new ArrayList<Branch>();
	
	@Autowired
	private DepartmentBiz departmentBiz;
	@Autowired
	private JurisdictionBiz jurisdictionBiz;
	
	private int userId;
	
	private List<BranchDepartment> bdlist=new ArrayList<BranchDepartment>();
	private List<String> haslist=new ArrayList<String>();
	
	public String execute()
	{
		try
		{
			init();
			Jurisdiction j=jurisdictionBiz.findById(userId);
			if(null!=j)
				haslist=StringUtil.strToList(j.getDepartmentIds(),"@");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private void init()throws Exception
	{
		blist=branchBiz.findListById(getUser().getOrgId());
		for(int i=0,k=blist.size();i<k;i++)
		{
			BranchDepartment bd=new BranchDepartment();
			Department department=new Department();
			int s=0;
			bd.setBranch(blist.get(i));
			s=blist.get(i).getId();
			department.setOfficeId(s);
			List<Department> dlist=new ArrayList<Department>();
			dlist=departmentBiz.findListByCondition(department);
			if(null==dlist)
				dlist=new ArrayList<Department>();
			
			bd.setDepartments(dlist);
			bdlist.add(bd);
		}
	}

	public List<BranchDepartment> getBdlist() {
		return bdlist;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<String> getHaslist() {
		return haslist;
	}
}
