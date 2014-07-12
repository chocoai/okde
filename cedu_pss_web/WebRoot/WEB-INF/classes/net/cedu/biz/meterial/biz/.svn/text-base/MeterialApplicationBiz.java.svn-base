package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialApplication;
import net.cedu.model.page.PageResult;

/**
 * 中心申请单 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface MeterialApplicationBiz {

	/**
	 * 增加方法
	 * @param meterialapplication
	 * @throws Exception
	 */
	public void saveMeterialApplication(MeterialApplication meterialapplication) throws Exception;
	
	
	/**条件查询
	 * 
	 * @param meterialapplication
	 * @return List
	 * @throws Exception
	 */
	public List<MeterialApplication> cribyMeterialApplication(MeterialApplication meterialapplication) throws Exception;
	
/**
 * 查询全部
 * @return
 * @throws Exception
 */
	public List<MeterialApplication> findall() throws Exception;
	
	
   /**
    * 分页数量
    * @param pr
    * @return int
    * @throws Exception
    */
	public int findAllMeterialApplicationCount(PageResult<MeterialApplication> pr)throws Exception;
	
	 /**
	  * 分页显示信息
	  * @param pr
	  * @return List
	  * @throws Exception
	  */
	public List<MeterialApplication> findAllMeterialApplication( PageResult<MeterialApplication> pr)throws Exception;
	
	/**
	 * 删除
	 * @param ma
	 * @throws Exception
	 */
	public void deleteMeterialApplication(int id) throws Exception;
	
	
	/**
	 * 修改操作
	 * @param ma
	 * @throws Exception
	 */
	public void updateMeterialApplication(MeterialApplication ma) throws Exception;
	
	/**
	 * 低级条件查询
	 * @param id
	 * @return MeterialApplication
	 * @throws Exception
	 */
	public MeterialApplication findByIdMeterialApplication(int id) throws Exception;
	
	/**
	 * 按编号和名称查询中心申请单
	 */
	public List<MeterialApplication> findMeterialApplicationPageListByCodeApplication(MeterialApplication meterialapplication, PageResult<MeterialApplication> pr)
			throws Exception;

		 
	/**
	 * 按编号和名称查询中心申请单的数量
	 */
	public int findMeterialApplicationPageCountByCodeApplication(MeterialApplication meterialapplication, PageResult<MeterialApplication> pr)
			throws Exception ;
	
	/**
	 * 根据主键id查询对象
	 * @param id
	 * @return MeterialApplication
	 * @throws Exception
	 */
	public MeterialApplication findbyId(int id) throws Exception;
	
	/**
	 * 根据申请单编号查询对象
	 * @param id
	 * @return MeterialApplication
	 * @throws Exception
	 */
	public MeterialApplication findByMeterialId(String id) throws Exception;
	
}
