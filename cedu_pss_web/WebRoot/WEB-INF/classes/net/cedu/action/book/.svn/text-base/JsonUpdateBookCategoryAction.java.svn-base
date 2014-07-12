package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.entity.book.BookCategory;
/**
 * 教材类型-修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateBookCategoryAction extends BaseAction {
 
	private static final long serialVersionUID = -2336352997473708992L;

	@Autowired
	private BookCategoryBiz bookcategorybiz;
	
	private BookCategory bookcategory;
	
	private boolean results=false;
	
	@Action(value="update_bookcategory",
			results={@Result(name="success",type="json",params={
				"contentType","text/json",
				"includeproperties","results"
			})})
	public String execute()
	{
		try {
			if(null!=bookcategory)
			{
				if(bookcategory.getId()!=0)
				{
					BookCategory bc=bookcategorybiz.findBookCategoryById(bookcategory.getId());
					bc.setUpdater_id(this.getUser().getUserId());
					bc.setName(bookcategory.getName());
					bc.setCode(bookcategory.getCode());
					bc.setUpdatedTime(new Date());
					BookCategory book=bookcategorybiz.findByNameOrCodeBookCategory(bookcategory.getCode(), bookcategory.getName());
					if(null==book){
					bookcategorybiz.updateBookCategory(bc);
					results=true;
					}else
					{
						return INPUT;
					}
				}
				
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public BookCategory getBookcategory() {
		return bookcategory;
	}

	public void setBookcategory(BookCategory bookcategory) {
		this.bookcategory = bookcategory;
	}

	public boolean isResults() {
		return results;
	}

	public void setResults(boolean results) {
		this.results = results;
	}
	
	
}
