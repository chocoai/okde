package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamScheduleBiz;
import net.cedu.entity.examination.ExamSchedule;


import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class ViewExamscheduleAction extends BaseAction implements ModelDriven<ExamSchedule> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5836938274180686387L;
	@Autowired
	private ExamScheduleBiz examschedulebiz;
	private ExamSchedule examschedule;
	private int id;
	
	public String execute(){
		try {
			examschedule=examschedulebiz.findExamscheduleByExamplanId(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
public ExamSchedule getModel() {
		
		return examschedule;
	}

public ExamSchedule getExamschedule() {
	return examschedule;
}
public void setExamschedule(ExamSchedule examschedule) {
	this.examschedule = examschedule;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
}
