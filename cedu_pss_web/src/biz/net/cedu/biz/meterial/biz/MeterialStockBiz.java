package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialStock;

/**
 * 物料库存 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface MeterialStockBiz {

	/**
	 * 条件查询 根据物料id 库房id 查询
	 * @param meterialstock
	 * @return List
	 * @throws Exception
	 */
	List<MeterialStock> findMeterialStockByStock(MeterialStock meterialstock) throws Exception;
	 /**
	  * 根据Id查询库存
	  * @param id
	  * @return MeterialStock
	  */
	MeterialStock findMeterialStockByroomId(int id) throws Exception;
	

}
