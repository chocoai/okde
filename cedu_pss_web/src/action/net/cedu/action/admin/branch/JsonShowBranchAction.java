package net.cedu.action.admin.branch;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 学习中心详情
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonShowBranchAction extends BaseAction implements ModelDriven<Branch>
{
	private static final long serialVersionUID = 8073791778496107728L;

	@Autowired
	private BranchBiz branchBiz;
	
	private Branch branch=new Branch();
	
	@Action(value = "show_branch", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	public String execute()
	{
		try
		{
			branch=branchBiz.findBranchById(branch.getId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Branch getModel() {
		return branch;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
}
