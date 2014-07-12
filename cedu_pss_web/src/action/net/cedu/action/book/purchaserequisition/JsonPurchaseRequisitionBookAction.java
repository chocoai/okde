package net.cedu.action.book.purchaserequisition;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.Stock;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 总部申购ajax
 * @author YY
 *
 */

@ParentPackage("json-default")
public class JsonPurchaseRequisitionBookAction extends BaseAction{
 
	private static final long serialVersionUID = 7582402307381665885L;
	
	@Autowired
	private BookBiz bookBiz; //教材业务层
	@Autowired
	private StockBiz stockBiz; //库存
	
	private List<Book> bookLists=new ArrayList<Book>();  //教材集合
	
	private String array=""; //选中的教材Id集合
	
	@Action(value = "ajax_load_purchaserequisition_book", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })	
	public String execute(){		
		try {
			String[] arr = array.split(","); //转换成数组
			//循环id查询各个对象
			for (int i = 0; i < arr.length; i++) {
				Book book=bookBiz.findBookById(Integer.parseInt(arr[i]));				
				//为库存数赋值
				Stock stock=stockBiz.findStockByBookId(book.getId());
				if(null!=stock)
				{
					book.setTotal(stock.getTotal());
				}
				//把对象加到集合里
				bookLists.add(book);
			} 	
		} catch (Exception e) {			 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Book> getBookLists() {
		return bookLists;
	}

	public void setBookLists(List<Book> bookLists) {
		this.bookLists = bookLists;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}
}
