package net.cedu.action.admin.user.group;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.UserGroup;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 根据条件查询学习中心列表_分页
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonPageListUserGroupAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup userGroup=new UserGroup();
	
	// 分页结果
	private PageResult<UserGroup> result = new PageResult<UserGroup>();
	
	@Action(value="page_list_user_group", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		if(-1==userGroup.getOrgId())
		{
			userGroup.setOrgId(getUser().getOrgId());
		}
		result.setList(userGroupBiz.findUserGroupPageListByBranchIdAdmin(userGroup,result));
		return SUCCESS;
	}

	public PageResult<UserGroup> getResult() {
		return result;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
