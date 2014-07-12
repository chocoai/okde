package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.Meterial;
import net.cedu.model.page.PageResult;

/**
 * 物料 业务逻辑接口
 * 
 * @author yy
 * 
 */

public interface MeterialBiz {

	/**
	 * 根据分类id查询分类物品
	 * 
	 * @param catrogryid
	 * @return List
	 * @throws Exception
	 */
	public List<Meterial> findMeterialByCatrgoryid(int categoryid)
			throws Exception;
	
	/**
	 * 查询全部
	 * @return List
	 * @throws Exception
	 */
	public List<Meterial> findall() throws Exception;

	/**
	 * 增加物料
	 * 
	 * @param meterial
	 * @return
	 * @throws Exception
	 */
	public void addMeterial(Meterial meterial) throws Exception;

	/**
	 * 删除物料
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteMeterial(int id) throws Exception;

	/**
	 * 修改物料
	 * 
	 * @param meterial
	 * @return
	 * @throws Exception
	 */
	public void updateMeterial(Meterial meterial) throws Exception;

	
	  /**
	    * 分页数量
	    * @param pr
	    * @return int
	    * @throws Exception
	    */
		public int findAllMeterialCount(PageResult<Meterial> pr)throws Exception;
		
		 /**
		  * 分页显示信息
		  * @param pr
		  * @return List
		  * @throws Exception
		  */
		public List<Meterial> findAllMeterial( PageResult<Meterial> pr)throws Exception;
		
		
		/**
		 * 按编号和名称查询中心申请单
		 */
		public List<Meterial> findMeterialPageListByCodeApplication(Meterial meterial, PageResult<Meterial> pr)
				throws Exception;

			 
		/**
		 * 按编号和名称查询中心申请单的数量
		 */
		public int findMeterialPageCountByCodeApplication(Meterial meterial, PageResult<Meterial> pr)
				throws Exception ;
		
		/**
		 * 根据id查询对象
		 * @param id
		 * @return Meterial
		 * @throws Exception
		 */
		public Meterial findById(int id) throws Exception;
		
		/**
		 * 根据用户名查询对象
		 * @param name
		 * @return Meterial
		 * @throws Exception
		 */
		public Meterial findByName(String name) throws Exception;
		
		/**
		 * 根据物料名称查询id集合
		 * @param name
		 * @return Long
		 * @throws Exception
		 */
		public Long[] findMeterialByNames(String name) throws Exception;
}
