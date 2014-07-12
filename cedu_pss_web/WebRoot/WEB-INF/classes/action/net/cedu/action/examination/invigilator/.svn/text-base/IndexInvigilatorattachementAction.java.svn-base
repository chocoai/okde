package net.cedu.action.examination.invigilator;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.examination.InvigilatorAttachmentBiz;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.academy.AcademyAttachment;
import net.cedu.entity.admin.User;
import net.cedu.entity.examination.Invigilator;
import net.cedu.entity.examination.InvigilatorAttachment;

import org.springframework.beans.factory.annotation.Autowired;

public class IndexInvigilatorattachementAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6698601443761021197L;
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	private Invigilator invigilator;

	@Autowired
	private UserBiz userbiz;                                 //附件Biz
	@Autowired
	private InvigilatorAttachmentBiz invigilatorattachmentbiz;       //附件Biz                           
	private InvigilatorAttachment invigilatorattachment;             //附件实体
	public InvigilatorAttachment getInvigilatorattachment() {
		return invigilatorattachment;
	}
	public void setInvigilatorattachment(InvigilatorAttachment invigilatorattachment) {
		this.invigilatorattachment = invigilatorattachment;
	}
	public User getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}
	private User userinfo;                                   //上传者        
	public Invigilator getInvigilator() {
		return invigilator;
	}
	public void setInvigilator(Invigilator invigilator) {
		this.invigilator = invigilator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private int id;
	
	public String execute(){
		try {
			invigilator=invigilatorbiz.findByInvigilatorId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	

}
