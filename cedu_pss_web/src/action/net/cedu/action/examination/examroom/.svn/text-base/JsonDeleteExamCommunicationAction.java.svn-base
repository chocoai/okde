package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamCommunicationRecordBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
@ParentPackage("json-default")
public class JsonDeleteExamCommunicationAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8864244405314317965L;
	@Autowired
	private ExamCommunicationRecordBiz examcommunicationrecordbiz;
	private int id;
	private boolean results=false;

	@Action(value = "delete_examcommunication", 
			results = { @Result(name = "success", type = "json", params = {
					"contentType", "text/json"
					}) })
	public String DeleteExamroom()
	{
		try
	{
		Object obj=examcommunicationrecordbiz.deleteExamCommunicationRecordById(id);
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

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}

}
