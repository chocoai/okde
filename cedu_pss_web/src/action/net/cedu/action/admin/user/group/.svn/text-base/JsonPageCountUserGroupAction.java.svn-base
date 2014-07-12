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
 * 查询模块数量_json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountUserGroupAction extends BaseAction 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private UserGroupBiz userGroupBiz;
	
	private UserGroup userGroup=new UserGroup();
	
	// 分页结果
	private PageResult<UserGroup> result = new PageResult<UserGroup>();
	
	@Action(value = "page_count_user_group", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		if(-1==userGroup.getOrgId())
		{
			userGroup.setOrgId(getUser().getOrgId());
		}
		result.setRecordCount(userGroupBiz.findUserGroupPageCountByBranchIdAdmin(userGroup,result));
		return SUCCESS;
	}

	public PageResult<UserGroup> getResult() {
		return result;
	}

	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
}
