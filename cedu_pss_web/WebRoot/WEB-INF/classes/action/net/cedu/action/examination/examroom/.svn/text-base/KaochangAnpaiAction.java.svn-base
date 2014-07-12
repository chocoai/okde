package net.cedu.action.examination.examroom;




import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ClassRoomBiz;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.entity.examination.ClassRoom;
import net.cedu.entity.examination.ExamPlan;
import net.cedu.entity.examination.ExamRoom;

import org.springframework.beans.factory.annotation.Autowired;

public class KaochangAnpaiAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 245975194600823462L;
	@Autowired
	private ExamPlanBiz examplanbiz;
	private ExamPlan examplan;
	@Autowired
	private ExamRoomBiz examroombiz;
	@Autowired
	private ClassRoomBiz classroombiz;
	private List<ClassRoom> classroomlist=new ArrayList<ClassRoom>();
	public List<ClassRoom> getClassroomlist() {
		return classroomlist;
	}
	public void setClassroomlist(List<ClassRoom> classroomlist) {
		this.classroomlist = classroomlist;
	}

	private List<ExamRoom> examroomlist=new ArrayList<ExamRoom>();
	public List<ExamRoom> getExamroomlist() {
		return examroomlist;
	}
	public void setExamroomlist(List<ExamRoom> examroomlist) {
		this.examroomlist = examroomlist;
	}
	
	private int id;
	
	public String execute()throws Exception{
		try {
			examplan=examplanbiz.findExamplanByExamplanId(id);
			examroomlist=examroombiz.findAllexamroom();
			classroomlist=classroombiz.findAllclassroom();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public ExamPlan getExamplan() {
		return examplan;
	}

	public void setExamplan(ExamPlan examplan) {
		this.examplan = examplan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ExamPlan getModel(){
		
		return examplan;
		
	}

}
