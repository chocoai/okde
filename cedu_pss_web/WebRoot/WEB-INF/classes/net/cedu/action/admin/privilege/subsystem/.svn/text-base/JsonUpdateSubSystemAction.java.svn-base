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
 * 修改模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateSubSystemAction extends BaseAction implements ModelDriven<SubSystem>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private SubSystem subSystem=new SubSystem();
	
	private boolean results=false;
	
	@Action(value = "update_sub_system", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			subSystemBiz.modify(subSystem);
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

	public void setSubSystem(SubSystem subSystem) {
		this.subSystem = subSystem;
	}

	public SubSystem getModel() {
		return subSystem;
	}

	public SubSystem getSubSystem() {
		return subSystem;
	}
}
