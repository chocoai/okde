package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.entity.book.Book;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 教材状态修改
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateBookStatusAction extends BaseAction implements
		ModelDriven<Book> {
	private static final long serialVersionUID = -250195408377127464L;
	@Autowired
	private BookBiz bookbiz;
	
	private Book book=new Book();
	private boolean results=false;
	
	@Action(value="update_book_status", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"includeProperties","results"
					} )})
	public String execute()
	{
		int status=book.getStatus();
		try {
			book=bookbiz.findBookById(book.getId());
			book.setStatus(status);
			bookbiz.updateBook(book);
			results=true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public Book getModel() {
		// TODO Auto-generated method stub
		return book;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public boolean isResults() {
		return results;
	}
	public void setResults(boolean results) {
		this.results = results;
	}

}
