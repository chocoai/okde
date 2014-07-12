package net.cedu.action.examination.examroom;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.entity.examination.ExamRoom;

import org.springframework.beans.factory.annotation.Autowired;

public class IndexClassroomAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -774096370541159738L;
	@Autowired
	private ExamRoomBiz examroombiz;
	private ExamRoom examroom;
	private List<ExamRoom> examroomlist=new ArrayList<ExamRoom>();
	public List<ExamRoom> getExamroomlist() {
		return examroomlist;
	}
	public void setExamroomlist(List<ExamRoom> examroomlist) {
		this.examroomlist = examroomlist;
	}
	private int id; 
	public String execute(){
		if(super.isGetRequest()){
			try {
				examroomlist=examroombiz.findAllexamroom();
				examroom=examroombiz.findByExamroomId(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return INPUT;
		}	
		return SUCCESS;
		
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
