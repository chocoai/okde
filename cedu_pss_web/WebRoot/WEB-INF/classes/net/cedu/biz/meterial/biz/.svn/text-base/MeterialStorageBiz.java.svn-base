package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialStorage;

/**
 * 物料入库方式  业务逻辑接口
 * @author YY
 *
 */
public interface MeterialStorageBiz   {

	/**
	 * 物料入库方式查询全部
	 * 
	 * @return List
	 * @throws Exception
	 */
	public List<MeterialStorage> finmeterialstorageall() throws Exception;

	/**
	 * 物料入库方式删除
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void deleteMeterialStorage(int id) throws Exception;

	/**
	 * 物料入库方式增加
	 * 
	 * @param meterialstoryage
	 * @return boolean
	 * @throws Exception
	 */
	public boolean saveMeterialStorage(MeterialStorage meterialstoryage)
			throws Exception;

	/**
	 * 判断名称是否存在
	 * 
	 * @param name
	 * @return boolean
	 * @throws Exception
	 */
	public boolean FinMeterialStorageByName(String name) throws Exception;;

	/**
	 * 修改方法
	 * 
	 * @param meterialstorage
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateMeterialStoryage(MeterialStorage meterialstorage)
			throws Exception;

	/**
	 * 根据id查询对象
	 * @param id
	 * @return MeterialStorage
	 * @throws Exception
	 */
	public MeterialStorage findMeterialStorageById(int id) throws Exception;
	/**
	 * 根据名称，编号查询对象
	 * @param name
	 * @param code
	 * @return MeterialStorage
	 * @throws Exception
	 */
	public MeterialStorage findmeterialStorageByName(String name,String code) throws Exception;
}
