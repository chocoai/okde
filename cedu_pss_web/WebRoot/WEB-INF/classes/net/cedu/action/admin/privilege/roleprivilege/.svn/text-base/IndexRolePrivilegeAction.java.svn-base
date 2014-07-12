package net.cedu.action.admin.privilege.roleprivilege;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.RoleBiz;
import net.cedu.entity.admin.Role;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色权限范围首页
 * @author Jack
 *
 */
public class IndexRolePrivilegeAction extends BaseAction 
{
	private static final long serialVersionUID = 4384055175835481869L;

	@Autowired
	private RoleBiz roleBiz;
	
	private List<Role> rlist=new ArrayList<Role>();
	
	public String execute()
	{
		try
		{
			rlist=roleBiz.findRoleByCondition(new Role());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Role> getRlist() {
		return rlist;
	}
}
