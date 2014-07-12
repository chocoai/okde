package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamRoomBiz;
import net.cedu.entity.examination.ExamRoom;



import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@ParentPackage("json-default")
public class JsonUpdateExamroomStatusAction extends BaseAction implements ModelDriven<ExamRoom> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9160487232730689062L;
	@Autowired
	private ExamRoomBiz examroombiz;
	private ExamRoom examroom;
	private boolean results;
	private int id;
	private int status;
	


	@Action(value="update_examroom_status", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"includeProperties","results"
					} )})
	public String execute()
	{
		try {
			examroom=examroombiz.findByExamroomId(id);
			examroom.setStatus(status);
			examroombiz.updateExamroom(examroom);
			results=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public ExamRoom getModel() {	
		return examroom;
	}

	public ExamRoomBiz getExamroombiz() {
		return examroombiz;
	}



	public void setExamroombiz(ExamRoomBiz examroombiz) {
		this.examroombiz = examroombiz;
	}



	public ExamRoom getExamroom() {
		return examroom;
	}



	public void setExamroom(ExamRoom examroom) {
		this.examroom = examroom;
	}



	public boolean isResults() {
		return results;
	}



	public void setResults(boolean results) {
		this.results = results;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}

}
