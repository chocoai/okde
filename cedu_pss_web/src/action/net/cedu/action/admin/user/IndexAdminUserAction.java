package net.cedu.action.admin.user;

import net.cedu.action.BaseAction;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.UserEnum;

/**
 * 总部用户管理首页
 * @author Jack
 *
 */
public class IndexAdminUserAction extends BaseAction
{
	private static final long serialVersionUID = 6035821999343665939L;
	
	private int type,orgId;
	
	public String execute()throws Exception
	{
		type=UserEnum.TypeAdmin.value();
		orgId=BranchEnum.Admin.value();
		return SUCCESS;
	}

	public int getType() {
		return type;
	}

	public int getOrgId() {
		return orgId;
	}
}
