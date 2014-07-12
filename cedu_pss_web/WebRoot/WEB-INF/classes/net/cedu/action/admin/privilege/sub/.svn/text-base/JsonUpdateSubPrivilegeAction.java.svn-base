package net.cedu.action.admin.privilege.sub;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubPrivilegeBiz;
import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.entity.admin.privilege.SubPrivilege;

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
public class JsonUpdateSubPrivilegeAction extends BaseAction implements ModelDriven<SubPrivilege>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private SubPrivilegeBiz subPrivilegeBiz;
	
	private SubPrivilege subPrivilege=new SubPrivilege();
	
	private boolean results=false;
	
	@Action(value = "update_sub_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			SubPrivilege old=subPrivilegeBiz.findSubPrivilegeById(subPrivilege.getId());
			old.setName(subPrivilege.getName());
			old.setOrders(subPrivilege.getOrders());
			old.setPrivilegeId(subPrivilege.getPrivilegeId());
			old.setIsPopUp(subPrivilege.getIsPopUp());
			old.setIsShow(subPrivilege.getIsShow());
			subPrivilegeBiz.modify(old);
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

	public SubPrivilege getSubPrivilege() {
		return subPrivilege;
	}

	public void setSubPrivilege(SubPrivilege subPrivilege) {
		this.subPrivilege = subPrivilege;
	}

	public SubPrivilege getModel() {
		return subPrivilege;
	}
}
