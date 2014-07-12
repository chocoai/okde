package net.cedu.action.admin.privilege;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.biz.admin.privilege.SubPrivilegeBiz;
import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.entity.admin.privilege.SubPrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 校验并修正权限显示及链接地址_Json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonCheckUpdatePrivilegeAction extends BaseAction 
{
	private static final long serialVersionUID = 4478091089654696982L;

	@Autowired
	private PrivilegeBiz privilegeBiz;
	@Autowired
	private SubPrivilegeBiz subPrivilegeBiz;
	
	private boolean results=false;
	private String doublePIds="";
	
	@Action(value = "check_up_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	public String execute()
	{
		try
		{
			SubPrivilege subPrivilege=new SubPrivilege();
			Privilege privilege=new Privilege();
			List<Privilege> plist=privilegeBiz.findAll();
			
			for(int i=0,ic=plist.size();i<ic;i++)
			{
				privilege=plist.get(i);
				List<SubPrivilege> splist=subPrivilegeBiz.findViewListByPrId(privilege.getId());
				if(null!=splist)
				{
					if(1<splist.size())
						doublePIds+="id:"+privilege.getId()+"_"+privilege.getName()+",";
					if(0<splist.size())
					{
						subPrivilege=splist.get(0);
						privilege.setFullPath(subPrivilege.getFullPath());
						privilege.setIsShow(subPrivilege.getIsShow());
						privilegeBiz.modify(privilege);
					}
				}
			}
			//TODO 检查权限绑定子权限，修正是否显示及链接地址
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

	public String getDoublePIds() {
		return doublePIds;
	}	
}
