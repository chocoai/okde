package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.SupplierCategoryBiz;
/**
 * 书商分类-删除
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteSupplierCategoryAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4777743712018062028L;

	@Autowired
	private SupplierCategoryBiz suppliercategorybiz;
	
	private int id;
	private boolean results=false; 
	
	@Action(value="delete_suppliercategory",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			suppliercategorybiz.deleteSupplierCategoryById(id);
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
