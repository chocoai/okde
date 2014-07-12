package net.cedu.action.tool;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.common.md5.PwdCoder;
import net.cedu.entity.admin.User;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户修改密码
 * @author Jack
 *
 */
public class ModifyPasswordAdminAction extends BaseAction 
{
	private static final long serialVersionUID = -6937045329855340392L;
	
	@Autowired
	private UserBiz userBiz;
	
	private String oldpwd="",newpwd="";
	
	public String execute()
	{
		try
		{
			if(isGetRequest())
			{
				return INPUT;
			}
			User user=userBiz.findUserById(getUser().getUserId());
			if(PwdCoder.getMD5(oldpwd).equals(user.getPassword()))
			{
				user.setPassword(PwdCoder.getMD5(newpwd));
				user.setUpdatePasswordTime(new Date());
				userBiz.modify(user);
				addActionMessage(ResourcesTool.getText("admin","update.success"));
			}
			else
				addActionMessage(ResourcesTool.getText("admin","pwd.error"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setOldpwd(String oldpwd) {
		this.oldpwd = oldpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
}
