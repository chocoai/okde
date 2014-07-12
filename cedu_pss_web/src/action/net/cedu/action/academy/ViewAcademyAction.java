package net.cedu.action.academy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.AcademyLinkManBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyLinkMan;
import net.cedu.entity.admin.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 院校详情
 * 
 * @author gaolei
 * 
 * */

public class ViewAcademyAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;              //院校Biz
	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;//院校联系人Biz
	@Autowired
	private UserBiz useriz;                     //院校联系人Biz
	
	private User userinfo;                      //项目经理用户
	
	private User updateuserinfo;                //最后修改用户
	
	private int id;           //获取连接参数ID
	private Academy academy;  //院校实体
	private List<AcademyLinkMan> academylinkman;//院校联系人实体 
	
	
	
	
	@Action(results = {@Result(name = "success", location = "view_academy.jsp")})
			public String excute() throws Exception
			{
				if(super.isGetRequest())
				{
					//院校信息
					academy=academybiz.findAcademyById(id);
					//院校联系人信息
					academylinkman=academylinkmanbiz.findAcademyLinkManByAcademyId(id);
					//项目经理信息
					userinfo=useriz.findUserById(academy.getProjectManagerId());
					updateuserinfo=useriz.findUserById(academy.getUpdaterId());
				}
				return SUCCESS;
			}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public List<AcademyLinkMan> getAcademylinkman() {
		return academylinkman;
	}

	public void setAcademylinkman(List<AcademyLinkMan> academylinkman) {
		this.academylinkman = academylinkman;
	}

	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}

	public User getUpdateuserinfo() {
		return updateuserinfo;
	}

	public void setUpdateuserinfo(User updateuserinfo) {
		this.updateuserinfo = updateuserinfo;
	}

	
}
