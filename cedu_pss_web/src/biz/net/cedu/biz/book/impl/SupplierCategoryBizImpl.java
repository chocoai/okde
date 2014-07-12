package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.SupplierCategoryBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.SupplierCategoryDao;
import net.cedu.entity.book.SupplierCategory;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书商分类业务层实现类
 * 
 * @author XFY
 * 
 */
@Service
public class SupplierCategoryBizImpl implements SupplierCategoryBiz{
	@Autowired
	private	SupplierCategoryDao suppliercategorydao;
	/**
	 * 增加
	 */
	public Object addSupplierCategory(SupplierCategory suppliercategory) throws Exception {
		Object obj=null;
		obj=suppliercategorydao.save(suppliercategory);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	/**
	 * 分页查询
	 */
	public List<SupplierCategory> findAllSupplierCategory(PageResult<SupplierCategory> pr) throws Exception {
			List<SupplierCategory> suppliercategorylist=new ArrayList<SupplierCategory>();
			PageParame p = new PageParame(pr);
			Long[] suppliercategoryIDs= suppliercategorydao.getIDs(p);
			if(null!=suppliercategoryIDs && suppliercategoryIDs.length>0)
			{
				for(int i=0;i<suppliercategoryIDs.length;i++)
				{
					SupplierCategory sc= suppliercategorydao.findById(Integer.parseInt(suppliercategoryIDs[i].toString()));
					if(null!=sc)
					{
						suppliercategorylist.add(sc);
					}
				}
			}
			return suppliercategorylist;
	}
	/**
	 * 分页数量
	 */
	public int findAllSupplierCategoryCount(PageResult<SupplierCategory> pr)
			throws Exception {
		
		PageParame p = new PageParame(pr);
		
		return suppliercategorydao.getCounts( p);	
	}
	/**
	 * 按ID删除
	 */
	public void deleteSupplierCategoryById(int id) throws Exception {
		suppliercategorydao.deleteById(id);
		
	}
	/**
	 * 修改
	 */
	public void updateSupplierCategory(SupplierCategory suppliercategory)
			throws Exception {
		suppliercategorydao.update(suppliercategory);
		
	}
	/**
	 * 按ID查询
	 */
	public SupplierCategory findSupplierCategoryById(int id) throws Exception {
		
		return suppliercategorydao.findById(id);
	}
	/**
	 * 查询所有
	 */
	public List<SupplierCategory> findAllSupplierCategory() throws Exception {
		return suppliercategorydao.findAll();
	}
	
	/**
	 *验证方法 
	 */
	public SupplierCategory findByNameOrCodeSupplierCategory(String code,String name) throws Exception
	{
		SupplierCategory category=new SupplierCategory();
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
		category=suppliercategorydao.getObjByProperty(hql, list.toArray());
		 
		return  category;
		
	}
	
}
