package net.cedu.action.examination.examroom;



import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorCommentBiz;
import net.cedu.entity.examination.InvigilatorComment;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonInvigilatorCommentsAction extends BaseAction 
		 {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1062974430160317532L;
	@Autowired
	private InvigilatorCommentBiz invigilatorcommentbiz;
	private InvigilatorComment invigilatorcomment=new InvigilatorComment();
	private String comments;
	private int score;
	private int invigilatorid;
	
	public int getInvigilatorid() {
		return invigilatorid;
	}
	public void setInvigilatorid(int invigilatorid) {
		this.invigilatorid = invigilatorid;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public InvigilatorComment getInvigilatorcomment() {
		return invigilatorcomment;
	}
	public void setInvigilatorcomment(InvigilatorComment invigilatorcomment) {
		this.invigilatorcomment = invigilatorcomment;
	}
	@Action(value ="add_invigilatorcomments", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {
		System.out.println(invigilatorid);

		if(score>0){
			invigilatorcomment.setScore(score);
		}
		if(comments!=null){
			invigilatorcomment.setComments(comments);
		}
		invigilatorcomment.setInvigilatorId(invigilatorid);
		invigilatorcomment.setCreatedTime(new Date());
		invigilatorcomment.setCreatorId(super.getUser().getUserId());
		

			// 执行添加操作
			invigilatorcommentbiz.createNew(invigilatorcomment);
		
		return SUCCESS;
	}
	

}
