package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamroomPlanBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonDeleteExamroomPlanAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2570163658721822723L;

	@Autowired
	private ExamroomPlanBiz examroomPlanBiz;
	
	private int id;
	private boolean results=false;

	public boolean isResults() {
		return results;
	}


	public void setResults(boolean results) {
		this.results = results;
	}


@Action(value = "delete_examroomplan", 
			results = { @Result(name = "success", type = "json", params = {
					"contentType", "text/json"
					}) })
	public String DeleteExamroom()
	{
		try
	{
		Object obj=examroomPlanBiz.deleteExamroomPlanById(id);
		if(null!=obj)
		{
				results=true;
			}else
		{
				results=false;
			}
			
		}
		catch(Exception e)
		{e.printStackTrace();}
		return SUCCESS;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}
	

}
