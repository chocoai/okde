package net.cedu.action.admin.privilege.modular;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.entity.admin.privilege.Modular;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询权限集列表
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonListModularAction extends BaseAction implements ModelDriven<Modular>
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private ModularBiz modularBiz;
	
	private Modular modular=new Modular();
	
	private List<Modular> mslist = new ArrayList<Modular>();
	
	@Action(value="list_modular", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		mslist=modularBiz.findListByCondition(modular);
		return SUCCESS;
	}

	public List<Modular> getMslist() {
		return mslist;
	}

	public Modular getPrivilegeSet() {
		return modular;
	}

	public void setPrivilegeSet(Modular modular) {
		this.modular = modular;
	}

	public Modular getModel() {
		return modular;
	}
}
