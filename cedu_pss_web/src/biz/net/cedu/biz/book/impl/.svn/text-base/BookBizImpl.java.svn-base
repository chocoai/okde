package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.book.BookDao;
import net.cedu.entity.book.Book;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;
/**
 * 教材业务层
 * @author Administrator
 *
 */
@Service
public class BookBizImpl extends BaseAction implements BookBiz{
	 
	private static final long serialVersionUID = 4614909194549120971L;
	@Autowired
	private BookDao bookdao;
	/**
	 * 增加
	 */
	public Object addBook(Book book) throws Exception {
		Object obj=null;
		obj=bookdao.save(book);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	/**
	 * 删除
	 */
	public void deleteBook(int id) throws Exception {
		bookdao.deleteById(id);	
	}
	/**
	 * 分页-数量
	 */
	public int findAllBookCount(PageResult<Book> pr) throws Exception {
		PageParame p = new PageParame(pr);
		
		return bookdao.getCounts(p);
	}
	/**
	 * 按ID查询
	 */
	public Book findBookById(int id) throws Exception {
		return bookdao.findById(id);
	}
	/**
	 * 修改
	 */
	public void updateBook(Book book) throws Exception {
		bookdao.update(book);
	}
	public List<Book> findBookAll() throws Exception {
		return bookdao.findAll();
	}
	public List<Book> findBookPageListByConditions(Book book, PageResult<Book> pr)
			throws Exception {
		
		List<Book> books = new ArrayList<Book>();
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (book!= null) 
		{
			if(0!=book.getCategory())
			{
				hql+=" and category=" + Constants.PLACEHOLDER;
				list.add(book.getCategory());
			}
			if(StringUtils.isNotBlank(book.getName()))
			{
				hql+=" and name=" + Constants.PLACEHOLDER;
				list.add(book.getName());
			}
			if(StringUtils.isNotBlank(book.getIsbn()))
			{
				hql+=" and isbn=" + Constants.PLACEHOLDER;
				list.add(book.getIsbn());
			}
			if(-1<book.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				list.add(UserEnum.DeleteNo.value());
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
		}

		Long[] bookIds = bookdao.getIDs(p);
		if (bookIds!= null && bookIds.length != 0) {
			for (int i = 0; i < bookIds.length; i++) {
				Book u = bookdao.findById(Integer.parseInt(bookIds[i]
						.toString()));
				if (u != null) {
					books.add(u);
				}
			}
		}

		return books;
	}
	/**
	 * 分页数量
	 */
	public int findBookPageCountByConditions(Book book, PageResult<Book> pr)
	throws Exception {
			
		 
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (book!= null) 
		{
			if(0!=book.getCategory())
			{
				hql+=" and category=" + Constants.PLACEHOLDER;
				list.add(book.getCategory());
			}
			if(StringUtils.isNotBlank(book.getName()))
			{
				hql+=" and name=" + Constants.PLACEHOLDER;
				list.add(book.getName());
			}
			if(StringUtils.isNotBlank(book.getIsbn()))
			{
				hql+=" and isbn=" + Constants.PLACEHOLDER;
				list.add(book.getIsbn());
			}
			if(-1<book.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				list.add(UserEnum.DeleteNo.value());
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
		}

		Long[] bookIds = bookdao.getIDs(p);
		return bookIds.length;
	}
	
	/**
	 * 根据材料名称查询材料集合id
	 */
	public Long[] findBookByNames(String name) throws Exception {
		 
		List<Object> list=new ArrayList<Object>();
		Long [] ids =  null;
		String hql="";
		if(StringUtils.isNotBlank(name))
		{
			hql += " and name = " + Constants.PLACEHOLDER;
			list.add(name);
		}
		ids = bookdao.getIDs(hql, list.toArray());
		return ids;
	}
	
	/**
	 * 根据材料版次查询材料集合id
	 */
	public Long[] findBookByeditions(String name) throws Exception {
		 
		List<Object> list=new ArrayList<Object>();
		Long [] ids =  null;
		String hql="";
		if(StringUtils.isNotBlank(name))
		{
			hql += " and edition = " + Constants.PLACEHOLDER;
			list.add(name);
		}
		ids = bookdao.getIDs(hql, list.toArray());
		return ids;
	}
	
	/**
	 * 根据姓名模糊查询id集合
	 */
	public Long[] findBookByLikeNames(String name) throws Exception {
		 
		 
			List<Object> list=new ArrayList<Object>();
			Long [] ids =  null;
			String hql="";
			if(StringUtils.isNotBlank(name))
			{
				hql += " and name like " + Constants.PLACEHOLDER;
				list.add('%'+name+'%');
			}
			ids = bookdao.getIDs(hql, list.toArray());
			return ids;
		}
	
	/**
	 * 根据版次模糊查询id集合
	 */
	public Long[] findBookByLikeeditions(String edition) throws Exception {
		 
		 
			List<Object> list=new ArrayList<Object>();
			Long [] ids =  null;
			String hql="";
			if(StringUtils.isNotBlank(edition))
			{
				hql += " and edition like " + Constants.PLACEHOLDER;
				list.add('%'+edition+'%');
			}
			ids = bookdao.getIDs(hql, list.toArray());
			return ids;
		}
	
	
	public List<Book> findnameorisbnByBook(String name, String isbn)
			throws Exception {
		
		List<Book> booklist=new ArrayList<Book>();
		String sql="";
		List<Object> objlist=new ArrayList<Object>();
		if(null!=name)
		{
			sql+="and name like"+Constants.PLACEHOLDER;
			objlist.add('%'+name+'%');
		}
		if(null!=isbn)
		{
			sql+="and isbn like"+Constants.PLACEHOLDER;
			objlist.add('%'+isbn+'%');
		}
		booklist=bookdao.getByProperty(sql, objlist);
		
		return booklist;
		} 




	/**
	 * 根据姓名对象 
	 */
	public  Book findBookByName(String name) throws Exception {
		 
		 
			List<Object> list=new ArrayList<Object>();
			Book bok = new Book();;
			String hql="";
			if(StringUtils.isNotBlank(name))
			{
				hql += " and name = " + Constants.PLACEHOLDER;
				list.add(name);
			}
			bok = bookdao.getObjByProperty(hql, list.toArray());
			return bok;
		}
	
} 
 