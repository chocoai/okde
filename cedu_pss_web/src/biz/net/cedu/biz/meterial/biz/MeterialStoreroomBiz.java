package net.cedu.biz.meterial.biz;

import java.util.List;

 
import net.cedu.entity.meterial.MeterialStoreroom;

/**
 * 物料库房设置  业务逻辑接口
 * @author YY
 *
 */

public interface MeterialStoreroomBiz   {

	
	 /**
	  * 查询全部物料库房
	  * @return List
	  * @throws Exception
	  */
	public List<MeterialStoreroom> finMeterialStoreroomall() throws Exception;
	/**
	 * 物料库房删除
	 * @param id
	 * @throws Exception
	 */
	public void deleteMeterialStoreroom(int id) throws Exception;
	
	/**
	 * 物料库房增加
	 * @param meterialstoryage
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveMeterialStoreroom(MeterialStoreroom meterialstoreroom) throws Exception;
	/**
	 * 判断名称是否存在
	 * @param name
	 * @return boolean
	 * @throws Exception
	 */
	public boolean FinMeterialStoreroomByName(String name) throws Exception;;
	
	/**
	 * 物料库房修改方法
	 * @param meterialstorage
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateMeterialStoreroom(MeterialStoreroom meterialstoreroom) throws Exception;
	
	/**
	 * 根据id查询库房设置对象
	 * @param id
	 * @return MeterialStoreroom
	 * @throws Exception
	 */
	public MeterialStoreroom findMeterialStoreroomById(int id) throws Exception;
	
	/**
	 * 根据库房位置查询库房名称集合
	 * @param id
	 * @return List
	 * @throws Exception
	 */
	public List<MeterialStoreroom> findMeterialStoreroomByPosition(int position) throws Exception;
	/**
	 * 根库房存名称查询库存id数组
	 * @param storeroomname
	 * @return Long
	 */
	public Long[] findMeterialStoreroomByNames(String storeroomname)throws Exception;
	/**
	 * 根据库房名称查询库房设置对象
	 * @param name
	 * @return MeterialStoreroom
	 * @throws Exception
	 */
	public MeterialStoreroom findMeterialMeterialStoreroomByName(String name,String code) throws Exception;
	
	/**
	 * 根据库房位置查询库房对象
	 * @param id
	 * @return MeterialStoreroom
	 * @throws Exception
	 */
	public MeterialStoreroom findStoreroomByPosition(int position) throws Exception;
}
