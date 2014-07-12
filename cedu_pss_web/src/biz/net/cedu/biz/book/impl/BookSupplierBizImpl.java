package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.BookSupplierDao;
import net.cedu.entity.book.BookSupplier;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

/**
 * 书商业务层实现
 * @author XFY
 *
 */
@Service
public class BookSupplierBizImpl implements BookSupplierBiz {
	@Autowired
	private BookSupplierDao bsdao;
	
	/**
	 * 增加
	 */
	public void addBookSupplier(BookSupplier bs) throws Exception {
		bsdao.save(bs);
	}
	/**
	 * 删除
	 */
	public void deleteBookSupplier(int id) throws Exception {
		bsdao.deleteById(id);
	}
	/**
	 * 分页
	 */
	public List<BookSupplier> findAllBookSupplier(PageResult<BookSupplier> pr)
			throws Exception {
		List<BookSupplier> bslist=new ArrayList<BookSupplier>();
		PageParame p = new PageParame(pr);
		Long[] bsIDs= bsdao.getIDs(p);
		if(null!=bsIDs && bsIDs.length>0)
		{
			for(int i=0;i<bsIDs.length;i++)
			{
				BookSupplier bs= bsdao.findById(Integer.parseInt(bsIDs[i].toString()));
				if(null!=bs)
				{
					bslist.add(bs);
				}
			}
		}
		return bslist;
	}
	/**
	 * 分页-数量
	 */
	public int findAllBookSupplierCount(PageResult<BookSupplier> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		return bsdao.getCounts( p);	
	}
	/**
	 * 按ID查询
	 */
	public BookSupplier findBookSupplierById(int id) throws Exception {
		return bsdao.findById(id);
	}
	/**
	 * 修改
	 */
	public void updateBookSupplier(BookSupplier bs) throws Exception {
		bsdao.update(bs);
	}
	/**
	 * 查询全部
	 */
	public List<BookSupplier> findAllBookSupplier() throws Exception {
		return bsdao.findAll();
	}
	/**
	 * 根据名称模糊查询id数组
	 */
	public Long[] findBookSupplierByNames(String name) throws Exception {
		String sql="";
		List<Object> list=new ArrayList<Object>();
		if(null!=name)
		{
			sql+=" and name like"+Constants.PLACEHOLDER;
			list.add("%"+name+"%");
		}
		Long[] ids=bsdao.getIDs(sql, list.toArray());
		return ids;
	}
}
