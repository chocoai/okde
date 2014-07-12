package net.cedu.action.academy;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyAttachmentBiz;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.AcademyAttachment;
import net.cedu.entity.academy.AcademyContract;
import net.cedu.entity.admin.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 附件
 * 
 * @author gaolei
 * 
 * */
public class ViewAcademyAttachmentAction extends BaseAction {

	@Autowired
	private AcademyBiz academybiz;                           //院校Biz
	
	@Autowired
	private UserBiz userbiz;                                 //附件Biz
	@Autowired
	private AcademyAttachmentBiz academyattachmentbiz;       //附件Biz
	private Academy academy;                                 //院校实体
	private AcademyAttachment academyattachment;             //附件实体
	private User userinfo;                                   //上传者            
	private int id;                                          //获取连接参数ID

	
	

	@Action(results = {@Result(name = "success", location = "list_academy_attachment.jsp")
					  
	})
			public String excute() throws Exception
			{
				//院校信息
				academy=academybiz.findAcademyById(id);
				return SUCCESS;
			}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public User getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}


	public AcademyAttachment getAcademyattachment() {
		return academyattachment;
	}


	public void setAcademyattachment(AcademyAttachment academyattachment) {
		this.academyattachment = academyattachment;
	}


	public Academy getAcademy() {
		return academy;
	}


	public void setAcademy(Academy academy) {
		this.academy = academy;
	}


	



	
	
}
