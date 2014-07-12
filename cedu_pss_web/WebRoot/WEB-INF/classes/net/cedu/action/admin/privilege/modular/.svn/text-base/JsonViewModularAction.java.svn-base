package net.cedu.action.admin.privilege.modular;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.entity.admin.privilege.Modular;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询模块
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewModularAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private ModularBiz modularBiz;
	
	private Modular modular=null;
	
	private int id=0;
	
	@Action(value = "view_modular", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			modular=modularBiz.findModularById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Modular getModular() {
		return modular;
	}

	public void setId(int id) {
		this.id = id;
	}
}
