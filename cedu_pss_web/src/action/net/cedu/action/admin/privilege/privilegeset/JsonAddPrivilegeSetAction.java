package net.cedu.action.admin.privilege.privilegeset;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.entity.admin.privilege.PrivilegeSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加模块_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddPrivilegeSetAction extends BaseAction implements ModelDriven<PrivilegeSet>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	
	private PrivilegeSet privilegeSet=new PrivilegeSet();
	
	private boolean results=false;
	
	@Action(value = "add_privilegeset", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=privilegeSetBiz.addNew(privilegeSet);
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

	public PrivilegeSet getModel() {
		return privilegeSet;
	}

	public PrivilegeSet getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(PrivilegeSet privilegeSet) {
		this.privilegeSet = privilegeSet;
	}	
}
