package net.cedu.action.admin.privilege.groupprivilege;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.UserGroup;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户组权限范围首页
 * @author Jack
 *
 */
public class IndexGroupPrivilegeAction extends BaseAction implements ModelDriven<UserGroup>
{
	private static final long serialVersionUID = 4384055175835481869L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup userGroup=new UserGroup();
	
	public String execute()
	{
		try
		{
			userGroup=userGroupBiz.findUserGroupById(userGroup.getId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public UserGroup getModel() {
		return userGroup;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}	
}
