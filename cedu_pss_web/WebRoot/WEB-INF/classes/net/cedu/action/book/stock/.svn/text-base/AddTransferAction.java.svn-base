package net.cedu.action.book.stock;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.StockTransferBiz;
import net.cedu.entity.book.StockTransfer;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 批量增加
 * @author YY
 *
 */
public class AddTransferAction extends BaseAction   {
 
 
	private static final long serialVersionUID = 1873410150358716832L;
	@Autowired
	private StockTransferBiz stockTransferBiz; //移库业务层
	private StockTransfer stockTransfer=new StockTransfer();   //移库实体	
	private String  array; //物料数组
	private String quntion; //移库数量数组	
	private int fromid; //移出库房位置
	private int toid; //移入库房位置
	@Action(results={@Result(name="success",type="redirect",location="index_transfer")})
	public String execute()
	{
		if(super.isGetRequest())
		{
			 
			return INPUT;		
		}
		try {	
			if(null!=array)
			{
				String[] arr = array.split(","); //转换成数组
				String[] quan =quntion.split(","); //转换成数组
				for (int i = 0; i <arr.length ; i++) {      //循环实现批量增加
					stockTransfer.setDeleteFlag(0);
					stockTransfer.setCreatorId(this.getUser().getUserId());
					stockTransfer.setUpdatedTime(new Date());
					stockTransfer.setUpdaterId(this.getUser().getUserId());
					stockTransfer.setBookId(Integer.parseInt(arr[i]));
					stockTransfer.setAmount((Integer.parseInt(quan[i])));
					stockTransfer.setToId(toid);
					stockTransfer.setFromId(fromid);
					stockTransferBiz.addStockTransfer(stockTransfer);
				}
			}
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}

	public String getQuntion() {
		return quntion;
	}

	public void setQuntion(String quntion) {
		this.quntion = quntion;
	}

	public int getFromid() {
		return fromid;
	}

	public void setFromid(int fromid) {
		this.fromid = fromid;
	}

	public int getToid() {
		return toid;
	}

	public void setToid(int toid) {
		this.toid = toid;
	}

	public StockTransfer getStockTransfer() {
		return stockTransfer;
	}

	public void setStockTransfer(StockTransfer stockTransfer) {
		this.stockTransfer = stockTransfer;
	}
	
}
