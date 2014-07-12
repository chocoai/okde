package net.cedu.action.admin.user.group;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.UserGroup;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 修改模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateUserGroupAction extends BaseAction implements ModelDriven<UserGroup>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup userGroup=new UserGroup();
	
	private boolean results=false;
	
	@Action(value = "update_user_group", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			//TODO目前只考虑总部和学习中心角色
			if(1==userGroup.getOrgId())
				userGroup.setRoleId(1);
			else if(0<userGroup.getOrgId())
				userGroup.setRoleId(2);
			userGroupBiz.modify(userGroup);
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

	public void setuserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}

	public UserGroup getModel() {
		return userGroup;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}
}
