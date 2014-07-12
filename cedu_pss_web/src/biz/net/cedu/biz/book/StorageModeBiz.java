package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.StorageMode;
import net.cedu.model.page.PageResult;

/**
 * 入库方式业务层接口
 * @author XFY
 *
 */
public interface StorageModeBiz {
	/**
	 * 增加
	 * @param textbook
	 * @return
	 * @throws Exception
	 */
	Object addStorageMode(StorageMode storagemode)throws Exception;
	
	/**
	 * 分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<StorageMode> findAllStorageMode( PageResult<StorageMode> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllStorageModeBCount(PageResult<StorageMode> pr)throws Exception;
	
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	
	StorageMode findStorageModeById(int id)throws Exception;
	
	/**
	 * 修改
	 * @param storagemode
	 * @throws Exception
	 */
	
	void updateStorageMode(StorageMode storagemode)throws Exception;
	
	/**
	 * 删除
	 * @param id
	 * @throws Exception
	 */
	
	void deleteStorageMode(int id)throws Exception;
	/**
	 *  
	 *验证方法 
	 * 
	 * @param code
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public StorageMode findByNameOrCodeStorageMode(String code,String name) throws Exception;
}
