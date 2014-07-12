package net.cedu.action.admin.privilege.groupprivilege;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.biz.admin.privilege.GroupPrivilegeBiz;
import net.cedu.entity.admin.UserGroup;
import net.cedu.entity.admin.privilege.GroupPrivilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 复制当前用户组权限到所有学习中心同名组
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonCopyToAllGroupPrivilegeAction extends BaseAction implements ModelDriven<UserGroup>
{
	private static final long serialVersionUID = -60700478052737755L;
	@Autowired
	private GroupPrivilegeBiz groupPrivilegeBiz;
	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup group=new UserGroup();
	
	private boolean results=false;
	
	@Action(value = "copy_to_all_group_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			group=userGroupBiz.findUserGroupById(group.getId());
			List<UserGroup> glist=userGroupBiz.findUserGroupsByName(group.getName());
			GroupPrivilege gp=groupPrivilegeBiz.findBygroupId(group.getId());
			if(null!=glist&&null!=gp)
			{
				for(int i=0,ic=glist.size();i<ic;i++)
				{
					GroupPrivilege newgp=new GroupPrivilege();
					newgp.setGroupId(glist.get(i).getId());
					newgp.setModularIds(gp.getModularIds());
					newgp.setPrivilegeIds(gp.getPrivilegeIds());
					newgp.setSetIds(gp.getSetIds());
					newgp.setSubSystemIds(gp.getSubSystemIds());
					groupPrivilegeBiz.modify(newgp);
				}					
			}
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public UserGroup getModel() {
		return group;
	}

	public boolean getResults() {
		return results;
	}

	public UserGroup getGroup() {
		return group;
	}

	public void setGroup(UserGroup group) {
		this.group = group;
	}
}
