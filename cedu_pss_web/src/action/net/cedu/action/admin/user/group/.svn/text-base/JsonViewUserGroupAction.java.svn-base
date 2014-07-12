package net.cedu.action.admin.user.group;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.UserGroup;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewUserGroupAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup userGroup;
	
	private int id=0;
	
	@Action(value = "view_user_group", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			userGroup=userGroupBiz.findUserGroupById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public UserGroup getUserGroup() {
		return userGroup;
	}

	public void setId(int id) {
		this.id = id;
	}
}
