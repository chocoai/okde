package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
/**
 * 教材删除
 */
@ParentPackage("json-default")
public class JsonDeleteBookAction extends BaseAction {
 
	private static final long serialVersionUID = 4160121067673344563L;

	@Autowired
	private BookBiz bookbiz;
	
	private int id;
	private boolean results=false;
	
	@Action(value="delete_book",results={@Result(
			name="success",type="json",params={
					"contentType","text/json","includeProperties","results"
			})})
	public String execute()
	{
		try {
			bookbiz.deleteBook(id);
			results=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
