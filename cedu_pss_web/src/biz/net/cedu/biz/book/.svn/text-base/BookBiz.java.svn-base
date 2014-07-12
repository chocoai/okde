package net.cedu.biz.book;
import java.util.List;
import net.cedu.entity.book.Book;
import net.cedu.model.page.PageResult;

/**
 * 教材业务层接口
 * @author Administrator
 *
 */
public interface BookBiz {
	/**
	 * 增加
	 * @param book
	 * @return
	 * @throws Exception
	 */
	Object addBook(Book book)throws Exception;
	/**
	 * 按id删除
	 * @param id
	 * @throws Exception
	 */
	void deleteBook(int id)throws Exception;
	/**
	 * 修改
	 * @param book
	 * @throws Exception
	 */
	void updateBook(Book book)throws Exception;
	/**
	 * 按id查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Book findBookById(int id)throws Exception;
	/**
	 * 分页结果
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Book> findBookPageListByConditions(Book book, PageResult<Book> pr)throws Exception;
	/**
	 * 分页数量
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findBookPageCountByConditions(Book book, PageResult<Book> pr)throws Exception;
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	List<Book> findBookAll() throws Exception;
	/**
	 * 根据材料名称查询材料集合id
	 */
	public Long[] findBookByNames(String name) throws Exception ;
	/**
	 * 根据材料版次查询材料集合id
	 */
	public Long[] findBookByeditions(String name) throws Exception;
	
	/**
	 * 
	 * @功能：根据教材姓名模糊查询教材id数组
	 *
	 * @作者： 杨阳
	 * @作成时间：2012-2-21 下午06:12:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param name
	 * @return ids
	 * @throws Exception
	 */
	public Long[] findBookByLikeNames(String name) throws Exception;
	/**
	 * 
	 * @功能：根据教材姓名模糊查询教材id数组
	 *
	 * @作者： 杨阳
	 * @作成时间：2012-2-21 下午06:32:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param edition
	 * @return ids
	 * @throws Exception
	 */
	public Long[] findBookByLikeeditions(String edition) throws Exception;
	
	/**
	 * 
	 * @功能：根据教材姓名和isbn模糊查询教材集合
	 *
	 * @作者： 杨阳
	 * @作成时间：2012-3-1 下午03:32:44
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param edition
	 * @return ids
	 * @throws Exception
	 */
	public List<Book> findnameorisbnByBook(String name,String isbn)throws Exception;
	
	/**
	 * 根据姓名查询对象
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public  Book findBookByName(String name) throws Exception;
}
