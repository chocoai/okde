package net.cedu.action.book.stock;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.StockBiz;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.Stock;
import net.cedu.entity.book.Storeroom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 库存
 * @author YY
 *
 */
public class IndexStockAction extends BaseAction  {
 
 
	private static final long serialVersionUID = -8399664131714688752L;



	@Autowired
	private StockBiz StockBiz; //库存业务层
	@Autowired
	private BookBiz bookBiz; //教材业务层
	@Autowired
	private StoreroomBiz storeroomBiz; //库房设置业务层
	@Autowired
	private BranchBiz branchBiz; //中心业务层
 	
	private List<Stock> list=new ArrayList<Stock>();
	
	private  Stock stock=new  Stock();
	
 
	
	private double com;
	
	@Action(results={@Result(name="success" ,location="index_stock.jsp")})
	public String execute()
	{
		try {
		List<Stock>	lists=StockBiz.findStockidByStock(stock);
		//循环数组赋值	
		for (Stock stock : lists) {
			Book book=bookBiz.findBookById(stock.getBookId());
			if(null!=book){
			stock.setBookedition(book.getEdition());
			stock.setBookname(book.getName());
			stock.setBookprice(book.getPrice());
			}
			Storeroom sr= storeroomBiz.findStoreroomById(stock.getStoreroomId());
			if(sr!=null)
			{
				stock.setStoreroomname(sr.getName());
				 Branch br=branchBiz.findBranchById(sr.getPosition());
				 stock.setStoreroomweizhi(br.getName());
			}
				com+=stock.getPrice();
				list.add(stock);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;

		}
		return SUCCESS;
	}

	public List<Stock> getList() {
		return list;
	}

	public void setList(List<Stock> list) {
		this.list = list;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getCom() {
		return com;
	}

	public void setCom(double com) {
		this.com = com;
	}


	 
	
}
