package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialCategory;
import net.cedu.model.page.PageResult;

/**
 * 物料分类 业务逻辑接口
 * 
 * @author YY
 * 
 */

public interface MeterialCategoryBiz {

	/**
	 * 新增物料分类
	 * @param textbook
	 * @return Object
	 * @throws Exception
	 */
	Object addMeterialCategory(MeterialCategory meterialcategory)throws Exception;
	

	/**
	 * 按物料分类名称查询
	 * @param meterialCategoryName
	 * @return MeterialCategory
	 * @throws Exception
	 */
	MeterialCategory findByMeterialCategoryName(String meterialCategoryName) throws Exception;
	
	/**
	 * 物料分类-分页
	 * @param pr
	 * @return List
	 * @throws Exception
	 */
	List<MeterialCategory> findAllMeterialCategoryList( PageResult<MeterialCategory> pr)throws Exception;
	
	/**
	 * 分页数量
	 * @param condition
	 * @param result
	 * @return  int
	 * @throws Exception
	 */
	int findAllMeterialCategoryCount(PageResult<MeterialCategory> pr)throws Exception;
	
	/**
	 * 查询所有物料分类
	 * 
	 * @return List
	 * @throws Exception
	 */
	List<MeterialCategory> findMeterialCategoryAll() throws Exception;
	
	/**
	 * 根据id删除物料分类
	 * 
	 * @param BookCategory
	 * @throws Exception
	 */
	void deleteMeterialCategoryById(int id) throws Exception;
	
	/**
	 * 根据id 查询物料分类
	 * @param id
	 * @return MeterialCategory
	 * @throws Exception
	 */
	MeterialCategory findMeterialCategoryById(int id) throws Exception;
	
	/**
	 * 修改物料分类
	 * @param bookcategory
	 * @throws Exception
	 */
	void updateMeterialCategory(MeterialCategory meterialcategory)throws Exception;
	
	/**
	 * 查询全部
	 * @return List
	 * @throws Exception
	 */
	List<MeterialCategory> findall() throws Exception;
	/**
	 * 按用户名，和编号查询对象
	 * @param meterialcategoryname
	 * @param code
	 * @return
	 */
	public MeterialCategory findByMeterialCategoryNameAndMeterialCategoryCode(String meterialcategoryname,String code) throws Exception;
	
	/**
	 * 根据id查询对象
	 * @param id
	 * @return MeterialCategory
	 * @throws Exception
	 */
	MeterialCategory findById(int id) throws Exception;
}


