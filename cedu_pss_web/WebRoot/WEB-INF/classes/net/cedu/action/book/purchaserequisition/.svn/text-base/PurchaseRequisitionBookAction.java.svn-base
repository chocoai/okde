package net.cedu.action.book.purchaserequisition;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.Stock;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * 教材信息
 * @author YY 
 *
 */
public class PurchaseRequisitionBookAction extends BaseAction {

	private static final long serialVersionUID = -7715443293116237280L;
	@Autowired
	private BookBiz bookBiz; //教材业务层
	@Autowired
	private StockBiz stockBiz; //库存	
	private List<Book> bookList=new ArrayList<Book>();  //教材集合
	private String bookname; //教材名称
	private String isbn;  //教材isbn

	//显示全部教材信息
	public String execute() throws Exception {
		
		bookList=bookBiz.findnameorisbnByBook(bookname,isbn);
		//为库存赋值
		for (Book book : bookList) {
			Stock stock=stockBiz.findStockByBookId(book.getId());
			if(null!=stock)
			book.setTotal(stock.getTotal());
		}
		return SUCCESS;
	}
 
	public List<Book> getBookList() {
		return bookList;
	}


	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
 
	public String getBookname() {
		return bookname;
	}
 
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
 
	public String getIsbn() {
		return isbn;
	}
 
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
 
}
