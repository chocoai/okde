package net.cedu.action.admin.branch;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Json数据返回
 * @author Jack
 *
 */ 
@ParentPackage("json-default")
public class JsonListBranchAction extends BaseAction 
{
	private static final long serialVersionUID = -9007096419088908044L;
	
	@Autowired
	private BranchBiz branchBiz;
	private List<Branch> list=new ArrayList<Branch>();

	
	/**
	 * 获取学习中心集合
	 * @return
	 * @throws Exception
	 */
	@Action(value="list_branch", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties",
					"list.*.code,"+
					"list.*.parent,"+
					"list.*.logicNode,"+
					"list.*.level,"+
					"list.*.type,"+
					"list.*.creatorId,"+
					"list.*.creator,"+
					"list.*.createdTime,"+
					"list.*.updaterId,"+					
					"list.*.updater,"+
					"list.*.updatedTime,"+
					"list.*.deleteFlag,"+
					"results,branch,result"
					} )})
	public String execute()throws Exception
	{
//		Branch branch=new Branch();
//		if(BranchEnum.Root.value()==getUser().getOrgId())
//			type=type-1;
//		if(0<type)
//			branch.setLevel(BranchEnum.Level.value());
//		if(0>type)
//			branch.setLevel(BranchEnum.All.value());
		list=branchBiz.findListComposite(getUser().getOrgId(),null,null,BranchEnum.Default);
		return SUCCESS;
	}

	public List<Branch> getList() {
		return list;
	}

//	public void setType(int type) {
//		this.type = type;
//	}
}
