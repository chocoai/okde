package net.cedu.action.admin.user;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.UserEnum;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学习中心用户管理首页_学习中心
 * @author Jack
 *
 */
public class IndexBranchUserAction extends BaseAction
{
	private static final long serialVersionUID = 6035821999343665939L;
	
	@Autowired
	private BranchBiz branchBiz; 
	
	private int type,orgId;
	private String branchName;
	
	public String execute()throws Exception
	{
		type=UserEnum.TypeBranch.value();
		orgId=getUser().getOrgId();
		branchName=branchBiz.findBranchById(orgId).getName();
		return SUCCESS;
	}
	
	public int getType() {
		return type;
	}

	public int getOrgId() {
		return orgId;
	}

	public String getBranchName() {
		return branchName;
	}
}
