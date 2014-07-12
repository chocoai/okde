package net.cedu.action.admin.privilege;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.entity.admin.privilege.Privilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加权限_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddPrivilegeAction extends BaseAction implements ModelDriven<Privilege>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	private Privilege privilege=new Privilege();
	
	private boolean results=false;
	
	@Action(value = "add_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=privilegeBiz.addNew(privilege);
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

	public Privilege getModel() {
		return privilege;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}	
}
