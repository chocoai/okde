package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.entity.book.BookCategory;
/**
 *教材
 * @author XFY
 *
 */
public class IndexBookAction extends BaseAction {
 
	private static final long serialVersionUID = -6749823854099680411L;

	@Autowired
	private BookCategoryBiz bookcategorybiz;
	
	private List<BookCategory> categorylist=new ArrayList<BookCategory>();
	
	public String execute()
	{
		try {
			categorylist=bookcategorybiz.findBookCategoryAll();
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<BookCategory> getCategorylist() {
		return categorylist;
	}

	public void setCategorylist(List<BookCategory> categorylist) {
		this.categorylist = categorylist;
	}
	
	
}
