package net.cedu.action.admin.branch;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询学习中心列表_分页
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageListBranchAction extends BaseAction implements ModelDriven<Branch>
{
	private static final long serialVersionUID = -250195408377127464L;

	@Autowired
	private BranchBiz branchBiz;
	
	private Branch branch=new Branch();
	
	// 分页结果
	private PageResult<Branch> result = new PageResult<Branch>();
	
	@Action(value="page_list_branch", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"result.list.*.code,"+
					"result.list.*.parentId,"+
					"result.list.*.logicNode,"+
					"result.list.*.creator,"+
					"result.list.*.updater,"+
					"list,results,branch"
					} )})
	public String execute()throws Exception
	{
		if(0<branch.getParentId())
			result.setList(branchBiz.findListComposite(branch.getParentId(),branch,result, BranchEnum.Default));
		else
			result.setList(branchBiz.findListComposite(branch.getParentId(),branch,result, BranchEnum.Root));
		return SUCCESS;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public PageResult<Branch> getResult() {
		return result;
	}

	public Branch getModel() {
		return branch;
	}	
}
