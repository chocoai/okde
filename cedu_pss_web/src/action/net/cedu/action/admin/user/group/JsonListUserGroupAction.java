package net.cedu.action.admin.user.group;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.UserGroup;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询用户组
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonListUserGroupAction extends BaseAction implements ModelDriven<UserGroup>
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup userGroup=new UserGroup();
	
	private List<UserGroup> glist = new ArrayList<UserGroup>();
	
	@Action(value="list_user_group", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		glist=userGroupBiz.findUserGroupsByOrgId(userGroup.getOrgId());
		return SUCCESS;
	}

	public List<UserGroup> getGlist() {
		return glist;
	}

	public UserGroup getModel() {
		return userGroup;
	}
}
