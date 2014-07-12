package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.entity.book.BookCategory;

/**
 * 添加教材分类
 * @author XFY
 *
 */
public class addBookCategory extends BaseAction {
 
	private static final long serialVersionUID = -8718067304501708995L;

	@Autowired
	private BookCategoryBiz bookcategorybiz;
	
	private BookCategory bookcategory;
	
	private String resultiterate; //结果
	
	@Action(value="add_bookcategory",results={@Result(type="redirect",location="index_category_book"),@Result(name="input",location="../book/error.jsp")})
	
	public String execute()
	{
		
		try {
			bookcategory.setCreatorId(this.getUser().getUserId());
			bookcategory.setCreatedTime(new Date());
			BookCategory book=bookcategorybiz.findByNameOrCodeBookCategory(bookcategory.getCode(), bookcategory.getName());
			if(null==book)
			{
			bookcategorybiz.addBookCategory(bookcategory);
			}else
			{
				return INPUT;
			}
			
			} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public BookCategory getBookcategory() {
		return bookcategory;
	}

	public void setBookcategory(BookCategory bookcategory) {
		this.bookcategory = bookcategory;
	}

	public String getResultiterate() {
		return resultiterate;
	}

	public void setResultiterate(String resultiterate) {
		this.resultiterate = resultiterate;
	}
	
	
	
}
