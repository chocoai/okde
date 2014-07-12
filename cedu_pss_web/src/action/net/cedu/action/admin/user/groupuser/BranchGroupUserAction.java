package net.cedu.action.admin.user.groupuser;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.admin.UserGroup;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学习中心用户用户组关系设置首页
 * @author Jack
 *
 */
public class BranchGroupUserAction extends BaseAction 
{
	private static final long serialVersionUID = 8272042796098000524L;
	
	@Autowired
	private UserGroupBiz userGroupBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private UserBiz userBiz;
	
	private Branch branch=new Branch();
	private List<UserGroup> uglist=new ArrayList<UserGroup>();
	private List<User> ulist=new ArrayList<User>();
	
	@SuppressWarnings("unchecked")
	public String execute()
	{
		try
		{
			int orgId=getUser().getOrgId();
			uglist=userGroupBiz.findUserGroupsByOrgId(orgId);
			branch=branchBiz.findBranchById(orgId);
			setIl8nName("admin");
			UserGroup b=new UserGroup();
			b.setId(-1);
			b.setName(getText("select_default"));
			if(0>=uglist.size())
				uglist.add(0,b);
			ulist=userBiz.findUsersByOrgId(orgId);
			Collections.sort(ulist, new Comparator() {
				public int compare(Object obj0, Object obj1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((User) obj0).getFullName();
					String name2 = ((User) obj1).getFullName();
					return cmp.compare(name1, name2);
				}
			});
		}
		catch(Exception e)
		{e.printStackTrace();}
		return SUCCESS;
	}

	public List<UserGroup> getUglist() {
		return uglist;
	}

	public Branch getBranch() {
		return branch;
	}

	public List<User> getUlist() {
		return ulist;
	}
}
