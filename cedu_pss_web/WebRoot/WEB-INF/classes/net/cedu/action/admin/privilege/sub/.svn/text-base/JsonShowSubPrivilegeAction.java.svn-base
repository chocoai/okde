package net.cedu.action.admin.privilege.sub;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubPrivilegeBiz;
import net.cedu.entity.admin.privilege.SubPrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询权限集
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonShowSubPrivilegeAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private SubPrivilegeBiz subPrivilegeBiz;
	
	private SubPrivilege subPrivilege=new SubPrivilege();
	
	private int id=0;
	
	@Action(value = "show_sub_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			subPrivilege=subPrivilegeBiz.findSubPrivilegeById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public SubPrivilege getSubPrivilege() {
		return subPrivilege;
	}

	public void setId(int id) {
		this.id = id;
	}
}
