package net.cedu.action.admin.branch;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 更新学习中心
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateBranchAction extends BaseAction implements ModelDriven<Branch> 
{
	private static final long serialVersionUID = -1059865364657531438L;

	@Autowired
	private BranchBiz branchBiz;
	
	private Branch branch=new Branch();
	private boolean results=false;
	
	@Action(value = "update_branch", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Branch parent=branchBiz.findBranchById(branch.getParentId());
			Branch old=branchBiz.findBranchById(branch.getId());
			old.setLevel(parent.getLevel()+1);
			old.setUpdaterId(getUser().getUserId());
			old.setUpdatedTime(new Date());
			old.setLogicNode(StringUtil.ChangeLogicNode(parent.getLogicNode(),branch.getId()));
			old.setParentId(branch.getParentId());
			old.setName(branch.getName());
			old.setShortName(branch.getShortName());
			branchBiz.modify(old);
			results=true;
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

	public boolean getResults() {
		return results;
	}
}
