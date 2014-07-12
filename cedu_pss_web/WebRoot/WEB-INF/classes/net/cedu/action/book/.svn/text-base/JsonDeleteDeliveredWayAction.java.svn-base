package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.DeliveredWayBiz;
/**
 * 删除配送方式
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteDeliveredWayAction extends BaseAction {
 
	private static final long serialVersionUID = 8608440139236904L;

	@Autowired
	private DeliveredWayBiz deliveredwaybiz;
	
	private int id; 
	private boolean results=false; 
	
	@Action(value = "delete_deliveredway", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try {
			deliveredwaybiz.deleteDeliveredWayById(id);
			results=true;
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
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
