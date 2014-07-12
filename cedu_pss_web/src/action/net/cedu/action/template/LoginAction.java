package net.cedu.action.template;

import javax.servlet.http.Cookie;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.CookieConstants;
import net.cedu.common.enums.LoginErrorEnum;
import net.cedu.common.enums.UserEnum;
import net.cedu.common.md5.PwdCoder;
import net.cedu.common.properties.Config;
import net.cedu.entity.admin.User;
import net.cedu.model.base.AuthenticationTicket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 登录页面
 * 
 * @author yangdongdong
 * 
 */
public class LoginAction extends BaseAction 
{
	private static final long serialVersionUID = -4186074065546374674L;
	
	private Log log = LogFactory.getLog(LoginAction.class);
	
	@Autowired
	private UserBiz userBiz;
	
	
	private String checkrand,password,username;
	
	@Action(results = {
			@Result(name = "input", location = "/WEB-INF/content/template/default/login.jsp"),
			@Result(name = "success", location = "/template/index", type = "redirect")})
	@Override
	public String execute() throws Exception 
	{
		if (isGetRequest()) 
		{
			return INPUT;
		} 
		if(true)
		{
			User user=userBiz.findByUserName(username);
			if(null==user)
			{
				returnError(LoginErrorEnum.NO_USER);
				log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" no user!");
			}
//			else if(password.equals(user.getPassword()))
			else if(true)
//			else if(true)
			{
				if(UserEnum.StatusStop.value()==user.getStatus())
				{
					returnError(LoginErrorEnum.LOCKED);
					log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user locked!");
				}
				else
				{
					createSession(user);
					//TODO 初始化权限
					return SUCCESS;
				}
			}
			else {
				returnError(LoginErrorEnum.PASSWORD_ERROR);
				log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" user password error!");
			}
		}
		else {
			returnError(LoginErrorEnum.CHECK_RAND);
			log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login error,message:"+" verification code error!");
		}
		return INPUT;
	}
	
	/**
	 * 创建用户会话
	 * @param user
	 */
	private void createSession(User user)
	{
		AuthenticationTicket ticket = new AuthenticationTicket();
		ticket.setUserId(user.getId());
		ticket.setOrgId(user.getOrgId());
		ticket.setFullName(user.getFullName());
		ticket.setUserName(user.getUserName());
		ticket.setIsManager(user.getIsManager());
		ticket.setType(user.getType());
		request.getSession(true).setAttribute(CookieConstants.SESSION_USER,ticket);
		log.info("ip is '"+request.getRemoteAddr()+"',user name is '"+username+"' login success!");
		//是否开启cookie
		if(Config.getBoolProperty("isturned.cookie")){
			//创建cookie
			addCookie(user);
		}
	}
	
	/**
	 * 创建cookie
	 * @param cookieingUser
	 */
    private void addCookie(User user) {  
    	try{
    		//用户名
            Cookie cookieUserName = new Cookie(CookieConstants.BROWSER_COOKIE_USER,  user.getUserName());  
            cookieUserName.setMaxAge(CookieConstants.BROWSER_COOKIE_MAX_AGE);  
            cookieUserName.setPath(request.getContextPath());  
            response.addCookie(cookieUserName); 
            //密码
            Cookie cookieUserPass = new Cookie(CookieConstants.BROWSER_COOKIE_PASSWORD,  user.getPassword());  
            //cookieUserPass.setMaxAge(CookieConstants.BROWSER_COOKIE_MAX_AGE);  
            cookieUserPass.setPath(request.getContextPath());  
            response.addCookie(cookieUserPass);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    }  
	
	/**
	 * 加载错误提示
	 * @param loginErrorEnum
	 */
	private void returnError(LoginErrorEnum loginErrorEnum)
	{
		switch(loginErrorEnum)
		{
			case CHECK_RAND:
				request.setAttribute("error",getText("msg.error.login.checkrand"));
				break;			
			case NO_USER:
				request.setAttribute("error",getText("msg.error.login.username"));
				break;
			case PASSWORD_ERROR:
				request.setAttribute("error",getText("msg.error.login.password"));
				break;
			case LOCKED:
				request.setAttribute("error",getText("msg.error.login.state"));
				break;
			default:break;
		}
	}
	
	

	public void setCheckrand(String checkrand) {
		this.checkrand = checkrand;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
