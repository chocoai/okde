package net.cedu.action.admin.user;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 查询学习中心条数_分页
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountUserAction extends BaseAction implements ModelDriven<User>
{
	private static final long serialVersionUID = 5171454483821092586L;

	@Autowired
	private UserBiz userBiz;
	
	private User users=new User();
	
	// 分页结果
	private PageResult<User> result = new PageResult<User>();
	
	/**
	 * 根据条件查询学习中心数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "page_count_user", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		//过滤掉root
		users.getObjParams().put("pibiOrgId", -1);
		result.setRecordCount(userBiz.findUserPageCountByUserForBranch(users,result));
		return SUCCESS;
	}

	public User getUsers() {
		return users;
	}



	public void setUser(User users) {
		this.users = users;
	}



	public PageResult<User> getResult() {
		return result;
	}

	public User getModel() {
		return users;
	}	
}
