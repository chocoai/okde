package net.cedu.action.admin.privilege.roleprivilege;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.RolePrivilegeBiz;
import net.cedu.entity.admin.privilege.RolePrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色权限范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewRolePrivilegeAction extends BaseAction
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private RolePrivilegeBiz rolePrivilegeBiz;

	private int roleId;
	
	private List<String> slist=new ArrayList<String>();
	private List<String> mlist=new ArrayList<String>();
	private List<String> pslist=new ArrayList<String>();
	private List<String> plist=new ArrayList<String>();
	
	@Action(value = "view_role_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			RolePrivilege rolePrivilege=rolePrivilegeBiz.findByRoleId(roleId);
			if(null!=rolePrivilege)
			{
				slist=initList(rolePrivilege.getSubSystemIds(),"s");
				mlist=initList(rolePrivilege.getModularIds(),"m");
				pslist=initList(rolePrivilege.getSetIds(),"ps");
				plist=initList(rolePrivilege.getPrivilegeIds(),"p");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	private List<String> initList(String strings,String keys)
	{
		List<String> list=new ArrayList<String>();
		String temp="";
		int index=-1;
		while(strings.length()>1)
		{
			index=strings.indexOf("_");
			if(0<=index&&strings.length()>index+1)
			{
				strings=strings.substring(index+1);
				temp=strings.substring(0,strings.indexOf("_"));
				temp="_"+keys+"_"+temp;
				list.add(temp);
			}
			else
				break;
		}
		return list;
	}

	public List<String> getSlist() {
		return slist;
	}

	public List<String> getMlist() {
		return mlist;
	}

	public List<String> getPslist() {
		return pslist;
	}

	public List<String> getPlist() {
		return plist;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
