package net.cedu.action.admin.privilege.subsystem;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.entity.admin.privilege.SubSystem;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据ID查询子系统
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewSubSystemAction extends BaseAction
{
	private static final long serialVersionUID = -2245039549486831510L;

	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private SubSystem subSystem=null;
	
	private int id=0;
	
	@Action(value = "view_sub_system", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{
			subSystem=subSystemBiz.findSubSystemById(id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public SubSystem getSubSystem() {
		return subSystem;
	}

	public void setId(int id) {
		this.id = id;
	}
}
