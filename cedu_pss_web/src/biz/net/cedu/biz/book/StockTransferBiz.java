package net.cedu.biz.book;

import java.util.List;

 
import net.cedu.entity.book.StockTransfer;
import net.cedu.model.page.PageResult;

/**
 * 库存 业务逻辑层接口
 * @author YY
 *
 */
public interface StockTransferBiz {
	/**
	 * 查询移库(数量)
	 * @param pr 
	 * @return
	 * @throws Exception
	 */
	public int countStockTransferByParams(StockTransfer stockTransfer,PageResult<StockTransfer> pr)throws Exception;
	
	/**
	 * 查询移库(集合)
	 * @param pr 
	 * @return
	 * @throws Exception
	 */
	public List<StockTransfer> findStockTransferByParams(StockTransfer stockTransfer,PageResult<StockTransfer> pr)throws Exception;
	
	/**
	 * 增加方法
	 * @param meterialTransfer
	 * @return
	 * @throws Exception
	 */
     public void addStockTransfer(StockTransfer stockTransfer) throws Exception;
	 
}
