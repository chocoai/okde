package net.cedu.action.admin.branch;

import java.util.Date;
import java.util.List;

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
 * 添加学习中心
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddBranchAction extends BaseAction implements ModelDriven<Branch> 
{
	private static final long serialVersionUID = -6269671244358345372L;

	@Autowired
	private BranchBiz branchBiz;
	
	private Branch branch=new Branch();
	private List<Branch> list;
	private boolean results=false;

	@Action(value = "add_branch", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Branch parent=branchBiz.findBranchById(branch.getParentId());
			branch.setLevel(parent.getLevel()+1);
			branch.setLogicNode(parent.getLogicNode());
			branch.setCreatorId(getUser().getUserId());
			branch.setUpdaterId(getUser().getUserId());
			branch.setUpdatedTime(new Date());
			branchBiz.addNew(branch);
			list=branchBiz.findListByCondition(branch);
			if(null!=list&&0<list.size())
			{
				branch=list.get(0);
				branch.setLogicNode(StringUtil.ChangeLogicNode(branch.getLogicNode(),branch.getId()));
				branchBiz.modify(branch);
				results=true;
			}
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

	public List<Branch> getList() {
		return list;
	}
}
