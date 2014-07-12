package net.cedu.action.admin.user.group;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.UserGroup;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加用户组_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddUserGroupAction extends BaseAction implements ModelDriven<UserGroup>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	@Autowired
	private BranchBiz branchBiz;
	
	private UserGroup userGroup=new UserGroup();
	
	private boolean results=false;
	
	private boolean addAll=false;
	
	@Action(value = "add_user_group", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			//TODO目前只考虑总部和学习中心角色
			if(addAll)
			{
				List<Branch> list=branchBiz.findBranchForModel();
				if(null!=list)
					for(int i=0,ic=list.size();i<ic;i++)
					{
						userGroup.setOrgId(list.get(i).getId());
						userGroup.setRoleId(2);
						userGroupBiz.createNew(userGroup);
					}
			}
			else
			{
				if(1==userGroup.getOrgId())
					userGroup.setRoleId(1);
				else if(0<userGroup.getOrgId())
					userGroup.setRoleId(2);
				userGroupBiz.createNew(userGroup);
			}
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public boolean getResults() {
		return results;
	}

	public UserGroup getModular() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public UserGroup getModel() {
		return userGroup;
	}

	public void setAddAll(boolean addAll) {
		this.addAll = addAll;
	}	
}
