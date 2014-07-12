package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialPurchaseDetail;

/**
 * 采购单明细 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface MeterialPurchaseDetailBiz {

	/**
	 * 总部采购增加
	 * @param meterialpurchasedetail
	 * @throws Exception
	 */
	
	public void saveMeterialPurchaseDetail(MeterialPurchaseDetail meterialpurchasedetail) throws Exception ;
	
	
	/**
	 * 根据id查询总部采购对象
	 * @param id
	 * @return List
	 * @throws Exception
	 */
	public List<MeterialPurchaseDetail> findById(int id) throws Exception;
}
