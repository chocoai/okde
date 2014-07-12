package net.cedu.action.admin.user;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.entity.admin.User;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 用户详情
 * @author Jack
 *
 */
public class ViewUserAction extends BaseAction implements ModelDriven<User>
{
	private static final long serialVersionUID = 8073791778496107728L;

	@Autowired
	private UserBiz userBiz;
	
	private User users=new User();
	
	public String execute()
	{
		try
		{
			users=userBiz.findUserById(users.getId());
			if(null==users.getPhotoUrl())
			{
				users.setPhotoUrl(ResourcesTool.getText("admin","photo.default.view"));
			}
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
}
