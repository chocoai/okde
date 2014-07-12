package net.cedu.action.admin.role;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.RoleBiz;
import net.cedu.entity.admin.Role;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询角色
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonListRoleAction extends BaseAction implements ModelDriven<Role>
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private RoleBiz roleBiz;
	
	private Role role=new Role();
	
	private List<Role> rlist = new ArrayList<Role>();
	
	@Action(value="list_role", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		rlist=roleBiz.findRoleByCondition(role);
		return SUCCESS;
	}

	public List<Role> getRlist() {
		return rlist;
	}

	public Role getModel() {
		return role;
	}
}
