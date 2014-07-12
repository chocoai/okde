package net.cedu.action.admin.privilege.groupprivilege;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.GroupPrivilegeBiz;
import net.cedu.entity.admin.privilege.GroupPrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 用户组权限范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateGroupPrivilegeAction extends BaseAction implements ModelDriven<GroupPrivilege>
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private GroupPrivilegeBiz groupPrivilegeBiz;
	
	private GroupPrivilege groupPrivilege=new GroupPrivilege();
	
	private boolean results=false;
	
	@Action(value = "update_group_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			groupPrivilegeBiz.modify(groupPrivilege);
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public GroupPrivilege getModel() {
		return groupPrivilege;
	}

	public GroupPrivilege getGroupPrivilege() {
		return groupPrivilege;
	}

	public void setGroupPrivilege(GroupPrivilege groupPrivilege) {
		this.groupPrivilege = groupPrivilege;
	}

	public boolean getResults() {
		return results;
	}
}
