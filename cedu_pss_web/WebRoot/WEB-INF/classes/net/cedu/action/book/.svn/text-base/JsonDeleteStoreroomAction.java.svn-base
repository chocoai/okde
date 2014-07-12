package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StoreroomBiz;
/**
 * 库房位置删除
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteStoreroomAction extends BaseAction {
 
	private static final long serialVersionUID = -4701141954870800752L;

	@Autowired
	private StoreroomBiz storeroombiz;
	
	private int id;
	private boolean results=false;
	
	@Action(value="delete_storeroom",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			storeroombiz.deleteStoreroom(id);
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
