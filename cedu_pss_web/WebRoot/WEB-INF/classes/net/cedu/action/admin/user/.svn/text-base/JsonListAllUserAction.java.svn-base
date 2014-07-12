package net.cedu.action.admin.user;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonListAllUserAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserBiz userBiz;

	private User users = new User();
	
	// 分页结果
	private PageResult<User> result = new PageResult<User>();
	
	// 错误消息
	private List<String> errorList = null;
	// 是否成功
	private boolean isback = false;

	@Action(value = "list_all_user", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	@SuppressWarnings("unchecked")
	public String ListAllUser() throws Exception {
		try
		{
			List<User> lst=userBiz.findAllUserListByParams(users, result);
			
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
	
	// 分页数量
	@Action(value = "count_all_user", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	public String CountAllUser() throws Exception {
		try
		{
			result.setRecordCount(userBiz.findAllUserCountByParams(users, result));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	// 删除用户
	@Action(value = "delete_all_user", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	public String DeleteAllUser() throws Exception {
		try
		{
			errorList = userBiz.deleteFalseUserById(users.getId(),super.getUser().getUserId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errorList = new ArrayList<String>();
			errorList.add("删除失败！");
		}
		return SUCCESS;
	}
	// 还原用户
	@Action(value = "reduction_all_user", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json"
			}) })
	public String ReductionAllUser() throws Exception {
		try
		{
			isback = userBiz.updateReductionUserById(users.getId(), super.getUser().getUserId());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public User getModel() {
		return users;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public PageResult<User> getResult() {
		return result;
	}

	public void setResult(PageResult<User> result) {
		this.result = result;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

}
