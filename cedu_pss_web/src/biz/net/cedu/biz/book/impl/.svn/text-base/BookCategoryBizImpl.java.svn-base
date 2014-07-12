package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.BookCategoryBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.BookCategoryDao;
import net.cedu.entity.book.BookCategory;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 教材分类业务层实现类
 * 
 * @author XFY
 * 
 */
@Service
public class BookCategoryBizImpl implements BookCategoryBiz {
	
	@Autowired
	private BookCategoryDao bookcategorydao;
	
	/**
	 * 增加教材类型
	 */
	public Object addBookCategory(BookCategory bookcategory) throws Exception {
		Object obj=null;
		obj=bookcategorydao.save(bookcategory);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	/**
	 * 分页数量
	 */
	public int findAllBookCategoryCount(PageResult<BookCategory> pr) throws Exception {
		PageParame p = new PageParame(pr);
		
		return bookcategorydao.getCounts( p);
	}
	/*
	 * 查询教材类型
	 * @see net.cedu.biz.academy.AcademyBiz#findAllAcademy(net.cedu.model.page.PageResult)
	 */
	public List<BookCategory> findAllBookCategory( PageResult<BookCategory> pr)throws Exception
	{
		List<BookCategory> bookcategorylist=new ArrayList<BookCategory>();
		PageParame p = new PageParame(pr);
		Long[] bookcategoryIDs= bookcategorydao.getIDs(p);
		if(null!=bookcategoryIDs && bookcategoryIDs.length>0)
		{
			for(int i=0;i<bookcategoryIDs.length;i++)
			{
				BookCategory bc= bookcategorydao.findById(Integer.parseInt(bookcategoryIDs[i].toString()));
				if(null!=bc)
				{
					bookcategorylist.add(bc);
				}
			}
		}
		return bookcategorylist;
	}
	/**
	 * 按用户名查找用户
	 */
	public BookCategory findByBookCategoryName(String bookcategoryname)
			throws Exception {
			String hqlcon = "";
			List<BookCategory> list = null;
			List<Object> paramList = new ArrayList<Object>();

			// 名称
			if (StringUtils.isNotBlank(bookcategoryname)) {
				hqlcon += " and bookcategoryname=" + Constants.PLACEHOLDER;
				paramList.add(bookcategoryname);
			}
			if (0 < hqlcon.length())
				list = bookcategorydao.getByProperty(hqlcon, paramList);
			if (null != list && 0 < list.size())
				return list.get(0);
			return null;
	}
	/**
	 * 查询所有教材分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<BookCategory> findBookCategoryAll() throws Exception {
		return bookcategorydao.findAll();
	}
	/**
	 * 根据ID删除教材分类
	 * 
	 * @param BookCategory
	 * @throws Exception
	 */
	public void deleteBookCategoryById(int id) throws Exception {
		bookcategorydao.deleteById(id);
	}
	/**
	 * 按ID查询
	 */
	public BookCategory findBookCategoryById(int id) throws Exception {
		return bookcategorydao.findById(id);
	}
	/**
	 * 修改
	 */
	public void updateBookCategory(BookCategory bookcategory) throws Exception {
		bookcategorydao.update(bookcategory);
		
	}
	
	/**
	 *验证方法 
	 */
	public BookCategory findByNameOrCodeBookCategory(String code,String name) throws Exception
	{
		BookCategory book=new BookCategory();
		String hql="";
		List<Object> list=new ArrayList<Object>();
		if(null!=code)
		{
			hql+=" and code ="+Constants.PLACEHOLDER;
			list.add(code);
		}
		if(null!=name)
		{
			hql+=" and name = "+Constants.PLACEHOLDER;
			list.add(name);
		}
		book=bookcategorydao.getObjByProperty(hql, list.toArray());
		 
		return  book;
		
	}
}
