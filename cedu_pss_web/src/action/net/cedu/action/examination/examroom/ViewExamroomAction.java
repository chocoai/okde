package net.cedu.action.examination.examroom;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.entity.admin.User;
import net.cedu.entity.examination.ExamRoom;


import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class ViewExamroomAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4507400954427016804L;
	@Autowired
	private ExamRoomBiz examroombiz;
	private ExamRoom examroom;
	private int id; 
	@Autowired
	private UserBiz useriz;                     //院校联系人Biz
	private User updateuserinfo;                //最后修改用户
	private User userinfo;   
	private List<ExamRoom> examroomlist=new ArrayList<ExamRoom>();
	public List<ExamRoom> getExamroomlist() {
		return examroomlist;
	}
	public void setExamroomlist(List<ExamRoom> examroomlist) {
		this.examroomlist = examroomlist;
	}
	public String execute()throws Exception{
		try {
			examroom=examroombiz.findByExamroomId(id);
			updateuserinfo=useriz.findUserById(examroom.getUpdaterId());
			userinfo=useriz.findUserById(examroom.getCreatorId());
			examroomlist=examroombiz.findAllexamroom();
			if(examroom!=null){
				System.out.println("ok");
			}else{
				System.out.println("no");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	public User getUpdateuserinfo() {
		return updateuserinfo;
	}
	public void setUpdateuserinfo(User updateuserinfo) {
		this.updateuserinfo = updateuserinfo;
	}
	public User getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}
	public ExamRoom getModel() {
		
		return examroom;
	}
	public ExamRoom getExamroom() {
		return examroom;
	}
	public void setExamroom(ExamRoom examroom) {
		this.examroom = examroom;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	

}
