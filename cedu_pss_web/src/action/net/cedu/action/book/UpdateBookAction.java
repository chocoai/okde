package net.cedu.action.book;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.entity.book.Book;
/**
 * 教材修改
 * @author XFY
 *
 */
public class UpdateBookAction extends BaseAction {
 
	private static final long serialVersionUID = -2765161170970335996L;


	@Autowired
	private BookBiz bookbiz;
	
	
	private Book book;
	private boolean result=false;
	
	@Action(value="find_update_book",results={@Result(type="redirect",location="index_book")})
	public String execute()
	{
		try {
			if(book!=null)
			{
				if(book.getId()!=0)
				{
					Book b=bookbiz.findBookById(book.getId());
					b.setUpdatedTime(new Date());
					b.setUpdater_id(this.getUser().getUserId());
					b.setName(book.getName());
					b.setSnapshot(book.getSnapshot());
					b.setIsbn(book.getIsbn());
					b.setEdition(book.getEdition());
					b.setPress(book.getPress());
					b.setAuthor(book.getAuthor());
					b.setCategory(book.getCategory());
					b.setUnit(book.getUnit());
					b.setPrice(book.getPrice());
					b.setNote(book.getNote());
					bookbiz.updateBook(b);
					result=true;
				}
			}
			
			
		} catch (Exception e) {
		 
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	public boolean isResult() {
		return result;
	}


	public void setResult(boolean result) {
		this.result = result;
	}
	
}
