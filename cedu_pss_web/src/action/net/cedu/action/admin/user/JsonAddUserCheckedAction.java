package net.cedu.action.admin.user;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.crm.Student;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 验证用户添加时，用户名是否重复
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonAddUserCheckedAction extends BaseAction
{
	
	@Autowired
	private UserBiz userBiz;//用户_业务层接口
	private String userName;//用户名称
	private Boolean exist=false;//返回参数
	
	
	/**
	 * 用户名是否存在
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "exist_user_user_name_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String existStudentPhone() throws Exception 
	{
		if (userName!= null && !userName.equals(""))
		{
			if (userBiz.findByUserName(userName)== null)
			{
				exist = true;
			}
		}
		else 
		{
			exist = true;
		}

		return SUCCESS;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Boolean getExist() {
		return exist;
	}


	public void setExist(Boolean exist) {
		this.exist = exist;
	}
	
}
