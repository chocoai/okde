package net.cedu.action.admin.privilege;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.entity.admin.privilege.Privilege;

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
public class JsonShowPrivilegeAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	private Privilege privilege=new Privilege();
	
	private int id=0;
	
	@Action(value = "show_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			privilege=privilegeBiz.findPrivilegeById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setId(int id) {
		this.id = id;
	}
}
