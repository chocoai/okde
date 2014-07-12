package net.cedu.action.admin.privilege.subsystem;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.entity.admin.privilege.SubSystem;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 添加子系统_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddSubSystemAction extends BaseAction implements ModelDriven<SubSystem>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private SubSystem subSystem=new SubSystem();
	
	private boolean results=false;
	
	@Action(value = "add_sub_system", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=subSystemBiz.addNew(subSystem);
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

	public SubSystem getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(SubSystem subSystem) {
		this.subSystem = subSystem;
	}

	public SubSystem getModel() {
		return subSystem;
	}
	
	
}
