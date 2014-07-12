package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.Stock;

/**
 * 库存 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface StockBiz {

	/**
	 * 条件查询 根据教材id 库房id 查询
	 * @param meterialstock
	 * @return
	 * @throws Exception
	 */
	List<Stock> findStockidByStock(Stock stock) throws Exception;
	 /**
	  * 根据库房Id查询库存
	  * @param id
	  * @return
	  */
	 Stock findStockByroomId(int id) throws Exception;
	/**
	 * 根据教材id查询对象
	 * @param bookid
	 * @return
	 * @throws Exception
	 */
	 public Stock findStockByBookId(int bookid) throws Exception;

}
