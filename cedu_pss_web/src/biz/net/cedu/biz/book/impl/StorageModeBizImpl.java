package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.StorageModeBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.StorageModeDao;
import net.cedu.entity.book.StorageMode;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 入库方式业务层实现
 * @author XFY
 *
 */
@Service
public class StorageModeBizImpl implements StorageModeBiz {
	@Autowired
	private  StorageModeDao storagemodedao;

	
	/**
	 * 增加
	 */
	
	public Object addStorageMode(StorageMode storagemode) throws Exception {
		Object obj=null;
		obj=storagemodedao.save(storagemode);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	
	/**
	 * 分页
	 */
	
	public List<StorageMode> findAllStorageMode(PageResult<StorageMode> pr)
			throws Exception {
		List<StorageMode> storagemodelist=new ArrayList<StorageMode>();
		PageParame p = new PageParame(pr);
		Long[] storagemodeIDs= storagemodedao.getIDs(p);
		if(null!=storagemodeIDs && storagemodeIDs.length>0)
		{
			for(int i=0;i<storagemodeIDs.length;i++)
			{
				StorageMode sm= storagemodedao.findById(Integer.parseInt(storagemodeIDs[i].toString()));
				if(null!=sm)
				{
					storagemodelist.add(sm);
				}
			}
		}
		return storagemodelist;
	}
	
	/**
	 * 分页-数量
	 */
	
	public int findAllStorageModeBCount(PageResult<StorageMode> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		
		return storagemodedao.getCounts( p);
	}
	
	/**
	 * 按ID查询
	 */
	
	public StorageMode findStorageModeById(int id) throws Exception {
		return storagemodedao.findById(id);
	}
	
	/**
	 * 修改
	 */
	
	public void updateStorageMode(StorageMode storagemode) throws Exception {
		storagemodedao.update(storagemode);
	}
	
	/**
	 * 删除
	 */
	public void deleteStorageMode(int id) throws Exception {
		storagemodedao.deleteById(id);
	}
	
	/**
	 *验证方法 
	 */
	public StorageMode findByNameOrCodeStorageMode(String code,String name) throws Exception
	{
		StorageMode mode=new StorageMode();
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
		 mode=storagemodedao.getObjByProperty(hql, list.toArray());
		 
		return  mode;
		
	}

}
