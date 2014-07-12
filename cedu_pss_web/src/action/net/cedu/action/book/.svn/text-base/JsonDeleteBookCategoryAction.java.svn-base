package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
/**
 * 删除教材分类
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonDeleteBookCategoryAction extends BaseAction {
	 
	private static final long serialVersionUID = 6523830569568463896L;

	@Autowired
	private BookCategoryBiz bookcagegorybiz;
	
	private int id;
	private boolean results;
	
	@Action(value="delete_bookcategory",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			bookcagegorybiz.deleteBookCategoryById(id);
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
