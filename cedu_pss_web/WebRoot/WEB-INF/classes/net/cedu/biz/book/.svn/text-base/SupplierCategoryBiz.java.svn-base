package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.SupplierCategory;
import net.cedu.model.page.PageResult;

/**
 * 书商分类业务层接口
 * 
 * @author XFY
 * 
 */
public interface SupplierCategoryBiz {
	/**
	 * 增加
	 * @param textbook
	 * @return
	 * @throws Exception
	 */
	Object addSupplierCategory(SupplierCategory suppliercategory)throws Exception;
	
	/**
	 * 查询-分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<SupplierCategory> findAllSupplierCategory( PageResult<SupplierCategory> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllSupplierCategoryCount(PageResult<SupplierCategory> pr)throws Exception;
	/**
	 * 根据id删除教材分类
	 * 
	 * @param BookCategory
	 * @throws Exception
	 */
	void deleteSupplierCategoryById(int id) throws Exception;
	/**
	 * 修改
	 * @param suppliercategory
	 * @throws Exception
	 */
	void updateSupplierCategory(SupplierCategory suppliercategory) throws Exception;
	/**
	 * 按ID查询
	 * @param id
	 * @throws Exception
	 */
	SupplierCategory findSupplierCategoryById(int id)throws Exception;
	
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	List<SupplierCategory> findAllSupplierCategory()throws Exception;
	/**
	 *验证方法 
	 */
	public SupplierCategory findByNameOrCodeSupplierCategory(String code,String name) throws Exception;
}
