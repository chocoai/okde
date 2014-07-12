package net.cedu.action.admin.user;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.Major;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询学习中心列表_分页
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageListUserAction extends BaseAction implements ModelDriven<User>
{
	private static final long serialVersionUID = -250195408377127464L;

	@Autowired
	private UserBiz userBiz;
	
	private User users=new User();
	
	// 分页结果
	private PageResult<User> result = new PageResult<User>();
	
	@Action(value="page_list_user", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json", 
					"excludeProperties",
					"result.*.password"
					} )})
	@SuppressWarnings("unchecked")
	public String execute()throws Exception
	{
		try
		{
			//过滤掉root
			users.getObjParams().put("pibiOrgId", -1);
			List<User> lst=userBiz.findUserPageListByUserForBranch(users, result);
			
			Collections.sort(lst, new Comparator() {
				public int compare(Object obj0, Object obj1) {
				Comparator cmp = Collator
				.getInstance(java.util.Locale.CHINA);
				String name1 = ((User) obj0).getFullName();
				String name2 = ((User) obj1).getFullName();
				return cmp.compare(name1, name2);
				}
				});
			
			
			result.setList(lst);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public PageResult<User> getResult() {
		return result;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public User getModel() {
		return users;
	}	
}
