package net.cedu.action.book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.entity.book.Book;
import net.cedu.model.page.PageResult;

import com.opensymphony.xwork2.ModelDriven;
/**
 * 教材数量
 * @author XFY
 *
 */
@ParentPackage("json-default")
public class JsonPageCountBookAction extends BaseAction implements
		ModelDriven<Book> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8787555648245648730L;

	@Autowired
	private BookBiz bookbiz;
	
	private Book book=new Book();
	private PageResult<Book> result=new PageResult<Book>();
	
	
	@Action(value="page_count_book", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()
	{
		try {
			result.setRecordCount(bookbiz.findBookPageCountByConditions(book, result));
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public Book getModel() {
		 
		return book;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public PageResult<Book> getResult() {
		return result;
	}

	public void setResult(PageResult<Book> result) {
		this.result = result;
	}

	
}
