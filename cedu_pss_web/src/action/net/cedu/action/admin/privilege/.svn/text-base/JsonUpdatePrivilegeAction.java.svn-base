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
 * 修改权限
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdatePrivilegeAction extends BaseAction implements ModelDriven<Privilege>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	private Privilege privilege=new Privilege();
	
	private boolean results=false;
	
	@Action(value = "update_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			Privilege old=privilegeBiz.findPrivilegeById(privilege.getId());
			privilege.setFullPath(old.getFullPath());
			privilege.setIsShow(old.getIsShow());
			privilegeBiz.modify(privilege);
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

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Privilege getModel() {
		return privilege;
	}
}
