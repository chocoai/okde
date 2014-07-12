package net.cedu.action.examination.examroom;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ClassRoomBiz;


import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;


@ParentPackage("json-default")
public class JsonDeleteClassroomAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5586351046522514937L;
	@Autowired
	private ClassRoomBiz classroombiz;
	private int id;
	private boolean results=false;
	
	public boolean isResults() {
		return results;
	}



	public void setResults(boolean results) {
		this.results = results;
	}

	@Action(value = "delete_classroom", 
			results = { @Result(name = "success", type = "json", params = {
					"contentType", "text/json"
					}) })
	public String DeleteClassroom()
	{
		try
	{
		Object obj=classroombiz.deleteClassRoomById(id);
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
