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
 * 修改模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonUpdateModularAction extends BaseAction implements ModelDriven<Modular>
{
	private static final long serialVersionUID = 5158621067783985998L;

	@Autowired
	private ModularBiz modularBiz;
	
	private Modular modular=new Modular();
	
	private boolean results=false;
	
	@Action(value = "update_modular", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try
		{
			modularBiz.modify(modular);
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

	public void setModular(Modular modular) {
		this.modular = modular;
	}

	public Modular getModel() {
		return modular;
	}

	public Modular getModular() {
		return modular;
	}
}
