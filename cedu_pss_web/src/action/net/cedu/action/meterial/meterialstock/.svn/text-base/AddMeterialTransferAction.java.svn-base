package net.cedu.action.meterial.meterialstock;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.biz.meterial.biz.MeterialStockBiz;
import net.cedu.biz.meterial.biz.MeterialTransferBiz;
import net.cedu.entity.meterial.MeterialStock;
import net.cedu.entity.meterial.MeterialTransfer;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 批量增加
 * @author YY
 *
 */
public class AddMeterialTransferAction extends BaseAction   {
 
	private static final long serialVersionUID = 1960570233209558189L;

	@Autowired
	private MeterialTransferBiz meterialTransferBiz; //移库业务层
	@Autowired
	private MeterialStockBiz  meterialStockBiz;    //库存业务层
	@Autowired
	private MeterialCategoryBiz meterialCategoryBiz;  //物料分类业务层
	private MeterialTransfer meterialTransfer=new MeterialTransfer();   //移库实体
	
	private MeterialStock meterialStock; //库存数量
	
	private String  array; //物料数组
	private String quntion; //移库数量数组
	
	private int fromid; //移出库房位置
	private int toid; //移入库房位置
	@Action(results={@Result(name="success",type="redirect",location="index_meterial_transfer")})
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
				meterialTransfer.setDeleteFlag(0);
				meterialTransfer.setCreatorId(this.getUser().getUserId());
				meterialTransfer.setUpdatedTime(new Date());
				meterialTransfer.setUpdaterId(this.getUser().getUserId());				 
				meterialTransfer.setMeterialId(Integer.parseInt(arr[i]));
				meterialTransfer.setQuantity(Integer.parseInt(quan[i]));
				meterialTransfer.setToId(toid);
				meterialTransfer.setFromId(fromid);
				meterialTransferBiz.saveMeterialTransfer(meterialTransfer);
			}
		}
		} catch (Exception e) {
	
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public MeterialTransfer getMeterialTransfer() {
		return meterialTransfer;
	}

	public void setMeterialTransfer(MeterialTransfer meterialTransfer) {
		this.meterialTransfer = meterialTransfer;
	}

	public MeterialStock getMeterialStock() {
		return meterialStock;
	}

	public void setMeterialStock(MeterialStock meterialStock) {
		this.meterialStock = meterialStock;
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

 
 

 
	
}
