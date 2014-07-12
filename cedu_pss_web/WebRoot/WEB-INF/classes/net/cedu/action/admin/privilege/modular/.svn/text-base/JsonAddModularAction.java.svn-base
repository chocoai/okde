package net.cedu.action.admin.privilege.modular;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.entity.admin.privilege.Modular;

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
public class JsonAddModularAction extends BaseAction implements ModelDriven<Modular>
{
	private static final long serialVersionUID = -3724410920328060484L;

	@Autowired
	private ModularBiz modularBiz;
	
	private Modular modular=new Modular();
	
	private boolean results=false;
	
	@Action(value = "add_modular", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			results=modularBiz.addNew(modular);
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

	public Modular getModular() {
		return modular;
	}

	public void setModular(Modular modular) {
		this.modular = modular;
	}

	public Modular getModel() {
		return modular;
	}
	
	
}
