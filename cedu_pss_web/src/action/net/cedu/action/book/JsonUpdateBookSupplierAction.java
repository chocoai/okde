package net.cedu.action.book;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.entity.book.Book;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 修改采购价格
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonUpdateBookSupplierAction extends BaseAction {
 
	private static final long serialVersionUID = 6078745444947113824L;

	@Autowired
	private BookBiz bookBiz;    //教材业务层
	
	private Book book=new Book();    //教材实体
	private boolean results=false;  //判断参数
	
	
	@Action(value = "update_book", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" ,
			"includeProperties","results"
			}) })
	public String execute()
	{
		try {
				if(book!=null)
				{
					 
						Book bok=bookBiz.findBookById(book.getId());
						bok.setPurchasePrice(book.getPurchasePrice());
						bookBiz.updateBook(bok);
						results=true;
						
				}
				
			} catch (Exception e) { 
				e.printStackTrace();					
			}
		return SUCCESS;
	}


	public BookBiz getBookBiz() {
		return bookBiz;
	}


	public void setBookBiz(BookBiz bookBiz) {
		this.bookBiz = bookBiz;
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
