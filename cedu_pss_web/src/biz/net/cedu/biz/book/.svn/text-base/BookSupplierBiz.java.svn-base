package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.BookSupplier;
import net.cedu.model.page.PageResult;

/**
 * 书商分类业务层接口
 * @author XFY
 *
 */
public interface BookSupplierBiz {
	/**
	 * 增加
	 * @param bs
	 * @throws Exception
	 */
	void addBookSupplier(BookSupplier bs)throws Exception;
	
	/**
	 * 教材分类-分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<BookSupplier> findAllBookSupplier( PageResult<BookSupplier> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllBookSupplierCount(PageResult<BookSupplier> pr)throws Exception;
	/**
	 * 按ID查询
	 * @return
	 * @throws Exception
	 */
	BookSupplier findBookSupplierById(int id)throws Exception;
	/**
	 * 删除
	 * @param bs
	 * @throws Exception
	 */
	void deleteBookSupplier(int id)throws Exception;
	/**
	 * 修改
	 * @param bs
	 * @throws Exception
	 */
	void updateBookSupplier(BookSupplier bs)throws Exception;
	/**
	 * 查询所有
	 * @return
	 */
	List<BookSupplier> findAllBookSupplier() throws Exception;
	
	/**
	 * 通过姓名查询id集合
	 * 
	 * @作者： 杨阳
	 * @作成时间：2012-2-22 下午04:22:44
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 */
	Long[] findBookSupplierByNames(String name) throws Exception;
	
	
	
	
}