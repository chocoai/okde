package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.BookCategory;
import net.cedu.model.page.PageResult;

/**
 * 教材分类业务层接口
 * 
 * @author XFY
 * 
 */
public interface BookCategoryBiz{
	
	/**
	 * 新增教材分类
	 * @param textbook
	 * @return
	 * @throws Exception
	 */
	Object addBookCategory(BookCategory bookcategory)throws Exception;
	

	/**
	 * 按教材分类名称查询
	 */
	BookCategory findByBookCategoryName(String bookCategoryName) throws Exception;
	
	/**
	 * 教材分类-分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<BookCategory> findAllBookCategory( PageResult<BookCategory> pr)throws Exception;
	
	/**
	 * 分页数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllBookCategoryCount(PageResult<BookCategory> pr)throws Exception;
	
	/**
	 * 查询所有
	 * 
	 * @return
	 * @throws Exception
	 */
	List<BookCategory> findBookCategoryAll() throws Exception;
	
	/**
	 * 根据id删除教材分类
	 * 
	 * @param BookCategory
	 * @throws Exception
	 */
	void deleteBookCategoryById(int id) throws Exception;
	
	/**
	 * 根据id 查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	BookCategory findBookCategoryById(int id) throws Exception;
	
	/**
	 * 修改
	 * @param bookcategory
	 * @throws Exception
	 */
	void updateBookCategory(
			BookCategory bookcategory)throws Exception;
	/**
	 *验证方法 
	 */
	public BookCategory findByNameOrCodeBookCategory(String code,String name) throws Exception;
}

