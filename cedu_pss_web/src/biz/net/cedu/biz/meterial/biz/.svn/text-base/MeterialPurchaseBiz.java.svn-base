package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialPurchase;
import net.cedu.model.page.PageResult;

/**
 * 总部采购 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface MeterialPurchaseBiz {

	/**
	 * 增加方法
	 * 
	 * @param meterialpurchase
	 * @throws Exception
	 */

	public void saveMeterialPurchase(MeterialPurchase meterialpurchase)
			throws Exception;

	/**
	 * 查询全部
	 * @return List
	 * @throws Exception
	 */
 
	public List<MeterialPurchase> findall() throws Exception;

	/**
	 * 删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteMeterialPurchase(int id) throws Exception;

	/**
	 * 修改操作
	 * 
	 * @param mp
	 * @throws Exception
	 */
	public void updateMeterialPurchase(MeterialPurchase mp)
			throws Exception;

 
	/**
	 * 根据id查询总部采购单
	 * @param id
	 * @return MeterialPurchase
	 * @throws Exception
	 */
	public MeterialPurchase findByIdMeterialPurchase(int id)
			throws Exception;

	/**
	 * 按编号查询总部采购
	 * @param meterialpurchase
	 * @param pr
	 * @return List
	 * @throws Exception
	 */
	public List<MeterialPurchase> findMeterialPurchasePageListByCodeApplication(
			MeterialPurchase meterialpurchase,
			PageResult<MeterialPurchase> pr) throws Exception;

	/**
	 * 按编号和名称查询总部采购单的数量
	 * @param meterialpurchase
	 * @param pr
	 * @return int
	 * @throws Exception
	 */
	public int findMeterialPurchasePageCountByCodeApplication(
			MeterialPurchase meterialpurchase,
			PageResult<MeterialPurchase> pr) throws Exception;

}
