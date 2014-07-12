package net.cedu.action.examination.invigilator;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.InvigilatorBiz;
import net.cedu.entity.examination.Invigilator;
/**
 * 修改状态
 * @author panyuheng
 *
 */
@ParentPackage("json-default")
public class JsonUpdateInvigilatorStatusAction extends BaseAction implements ModelDriven<Invigilator> {

	private static final long serialVersionUID = 6035821999343665939L;
	@Autowired
	private InvigilatorBiz invigilatorbiz;
	private Invigilator invigilator;
	private boolean results;
	private int id;
	private int status;
	


	@Action(value="update_invigilator_status", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"includeProperties","results"
					} )})
	public String execute()
	{
		try {
			invigilator=invigilatorbiz.findByInvigilatorId(id);
			invigilator.setStatus(status);
			invigilatorbiz.updateInvigilate(invigilator);
			results=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Invigilator getModel() {	
		return invigilator;
	}
	public Invigilator getInvigilator() {
		return invigilator;
	}

	public void setInvigilator(Invigilator invigilator) {
		this.invigilator = invigilator;
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
