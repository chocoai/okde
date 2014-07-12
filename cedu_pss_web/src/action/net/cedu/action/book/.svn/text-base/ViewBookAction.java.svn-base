package net.cedu.action.book;

import org.springframework.beans.factory.annotation.Autowired;
import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.entity.book.Book;
/**
 * 教材View
 * @author XFY
 *
 */
public class ViewBookAction extends BaseAction {

	private static final long serialVersionUID = -5403440088122657863L;
	@Autowired
	private BookBiz bookbiz;
	
	private int id;
	private Book book;
	
	//@Action(value="view_book",results={@Result(location="/book/viwe_books")})
	public String execute()
	{
		try {
			book=bookbiz.findBookById(id);
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
