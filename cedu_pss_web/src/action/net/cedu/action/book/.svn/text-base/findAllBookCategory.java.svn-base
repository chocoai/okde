package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.entity.book.BookCategory;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询教材分类
 * @author XFY
 *
 */
public class findAllBookCategory extends BaseAction  {
 
	private static final long serialVersionUID = -2564515372914657914L;

	@Autowired
	private BookCategoryBiz bookcategorybiz;
	
	private List<BookCategory> bookcategorylist=new ArrayList<BookCategory>();
	
	@Action(value="findall_bookcategory",results={@Result(location="view_book.jsp"),@Result(name="input",location="error.jsp")})
	public String execute()
	{
		try
		{
			bookcategorylist=bookcategorybiz.findBookCategoryAll();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public List<BookCategory> getBookcategorylist() {
		return bookcategorylist;
	}

	public void setBookcategorylist(List<BookCategory> bookcategorylist) {
		this.bookcategorylist = bookcategorylist;
	}


}
