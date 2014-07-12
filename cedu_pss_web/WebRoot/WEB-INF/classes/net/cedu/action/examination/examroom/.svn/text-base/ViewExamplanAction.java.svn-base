package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.entity.examination.ExamPlan;

import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class ViewExamplanAction extends BaseAction implements ModelDriven<ExamPlan> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7615750771878987736L;
	@Autowired
	private ExamPlanBiz examplanbiz;
	private ExamPlan examplan;
	private int id; 
	public String execute(){
		try {
			examplan=examplanbiz.findExamplanByExamplanId(id);
			if(examplan!=null){
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
