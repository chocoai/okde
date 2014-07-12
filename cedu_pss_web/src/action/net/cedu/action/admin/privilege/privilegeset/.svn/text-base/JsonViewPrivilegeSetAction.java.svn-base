package net.cedu.action.admin.privilege.privilegeset;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.entity.admin.privilege.PrivilegeSet;

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
public class JsonViewPrivilegeSetAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	
	private PrivilegeSet privilegeSet=new PrivilegeSet();
	
	private int id=0;
	
	@Action(value = "view_privilegeset", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			privilegeSet=privilegeSetBiz.findPrivilegeSetById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PrivilegeSet getPrivilegeSet() {
		return privilegeSet;
	}

	public void setId(int id) {
		this.id = id;
	}
}
