package net.cedu.action.admin.privilege.groupprivilege;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.GroupPrivilegeBiz;
import net.cedu.entity.admin.privilege.GroupPrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户组权限范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonViewGroupPrivilegeAction extends BaseAction
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private GroupPrivilegeBiz groupPrivilegeBiz;

	private int groupId;
	
	private List<String> slist=new ArrayList<String>();
	private List<String> mlist=new ArrayList<String>();
	private List<String> pslist=new ArrayList<String>();
	private List<String> plist=new ArrayList<String>();
	
	@Action(value = "view_group_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			GroupPrivilege groupPrivilege=groupPrivilegeBiz.findBygroupId(groupId);
			if(null!=groupPrivilege)
			{
				slist=initList(groupPrivilege.getSubSystemIds(),"s");
				mlist=initList(groupPrivilege.getModularIds(),"m");
				pslist=initList(groupPrivilege.getSetIds(),"ps");
				plist=initList(groupPrivilege.getPrivilegeIds(),"p");
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

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
}
