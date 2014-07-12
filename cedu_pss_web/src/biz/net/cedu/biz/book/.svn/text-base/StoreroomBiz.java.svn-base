package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.Storeroom;
import net.cedu.model.page.PageResult;


/**
 * 库房位置业务接口
 * @author XFY
 *
 */
public interface StoreroomBiz {
	/**
	 * 增加
	 * @param storeroom
	 * @return
	 */
	Object addStoreroomBiz(Storeroom storeroom)throws Exception;
	/**
	 * 分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<Storeroom> findAllStoreroom( PageResult<Storeroom> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllStoreroomCount(PageResult<Storeroom> pr)throws Exception;
	/**
	 * 按id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Storeroom findStoreroomById(int id)throws Exception;
	/**
	 * 按id删除
	 * @param id
	 * @throws Exception
	 */
	void deleteStoreroom(int id)throws Exception;
	/**
	 * 修改
	 * @param storeroom
	 */
	void updateStoreroom(Storeroom storeroom)throws Exception;
	
	/**
	 * 根据名称，编号 查询对象 
	 * @param code 
	 * @param name
	 * @return  
	 * @throws Exception
	 */
	public Storeroom findByNameOrCodeStoreroom(String code,String name) throws Exception;
	
	/**
	 * 根库房存名称查询库存id数组
	 * @param storeroomname
	 * @return
	 * @author YY
	 */
	public Long[] findBookStoreroomByNames(String storeroomname)throws Exception;
	/**
	 * 查询全部 
	 * @return
	 */
	List<Storeroom> findStoreroomAll()throws Exception;
	/*根据库房位置查询库房名称
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialStoreroomBiz#findMeterialStoreroomByPosition(java.lang.String)
	 */
	public  List<Storeroom>  findStoreroomByPosition(int position)
			throws Exception;
	/**
	 * 根据姓名查询对象
	 * @param name
	 * @return
	 * @throws Exception
	 */
	Storeroom findNameByStoreroom(String name)throws Exception;
	
	/**
	 * 根据位置查询对象
	 * @param position
	 * @return
	 * @throws Exception
	 */
	public Storeroom findpositionByStoreroom(int position) throws Exception;
}
