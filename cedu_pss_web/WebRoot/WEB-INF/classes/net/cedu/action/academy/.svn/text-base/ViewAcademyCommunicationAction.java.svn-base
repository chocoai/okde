package net.cedu.action.academy;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
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
 * 院校沟通记录
 * 
 * @author gaolei
 * 
 * */
public class ViewAcademyCommunicationAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;                       //院校Biz
	@Autowired
	private UserBiz userbiz;                             //院校Biz
	@Autowired
	private AcademyLinkManBiz academylinkmanbiz;         //院校联系人Biz
	private User userinfo;                               //沟通人            
	private int id;                                      //获取连接参数ID
	private Academy academy;                             //院校实体
	private List<AcademyLinkMan> academylinkman;         //院校联系人实体 
	
	

	
	
	@Action(results = {@Result(name = "success", location = "list_communication_record.jsp")
					  
	})
			public String excute() throws Exception
			{
				if(super.isGetRequest())
				{
					//获取院校数据
					academy=academybiz.findAcademyById(id);
					//获取该院校下的院校联系人
					academylinkman=academylinkmanbiz.findAcademyLinkManByAcademyId(id);
					if(academylinkman!=null && academylinkman.size()>0)
					{
						Collections.sort(academylinkman, new Comparator() {
							public int compare(Object arg0, Object arg1) {
								Comparator cmp = Collator
										.getInstance(java.util.Locale.CHINA);
								String name1 = ((AcademyLinkMan) arg0).getName();
								String name2 = ((AcademyLinkMan) arg1).getName();
								return cmp.compare(name1, name2);
							}
						});
					}
					//获取该院校下的沟通人
					if(academy!=null)
					{
						userinfo=userbiz.findUserById(academy.getProjectManagerId());
					}
					
					return SUCCESS;
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



	
	
}
