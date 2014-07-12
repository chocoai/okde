package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.book.StoreroomBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.StoreroomDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.book.Storeroom;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 库房位置业务层实现
 * @author XFY
 *
 */
@Service
public class StoreroomBizImpl implements StoreroomBiz {
	
	@Autowired
	private StoreroomDao storeroomdao;
	@Autowired 
	private BranchBiz branchBiz; //个人中心业务层
	
	/**
	 * 增加
	 */
	public Object addStoreroomBiz(Storeroom storeroom) throws Exception {
		Object obj=null;
		obj=storeroomdao.save(storeroom);
		if(obj==null)
			return obj;
		else
			return obj;
	}

	/**
	 * 分页
	 */
	public List<Storeroom> findAllStoreroom(PageResult<Storeroom> pr)
			throws Exception {
		List<Storeroom> storeroomlist=new ArrayList<Storeroom>();
		PageParame p = new PageParame(pr);
		Long[] storeroomIDs= storeroomdao.getIDs(p);
		if(null!=storeroomIDs && storeroomIDs.length>0)
		{
			for(int i=0;i<storeroomIDs.length;i++)
			{
				Storeroom s= storeroomdao.findById(Integer.parseInt(storeroomIDs[i].toString()));
				if(null!=s)
				{
					Storeroom sr=s;
					Branch bh=branchBiz.findBranchById(sr.getTypes());
					if(null!=bh)
					{
						sr.setTypesname(bh.getName());
					}
					storeroomlist.add(s);
				}
			}
		}
		return storeroomlist;
	}

	/**
	 * 分页-数量
	 */
	public int findAllStoreroomCount(PageResult<Storeroom> pr) throws Exception {
		PageParame p = new PageParame(pr);
		
		return storeroomdao.getCounts( p);	
	}
	/**
	 * 按ID删除
	 */
	public void deleteStoreroom(int id) throws Exception {
		storeroomdao.deleteById(id);
		
	}
	/**
	 * 按ID查询
	 */
	public Storeroom findStoreroomById(int id) throws Exception {
		return storeroomdao.findById(id);
	}
	/**
	 * 修改
	 */
	public void updateStoreroom(Storeroom storeroom)throws Exception {
		storeroomdao.update(storeroom);
		
	}
	/**
	 *验证方法 
	 */
	public Storeroom findByNameOrCodeStoreroom(String code,String name) throws Exception
	{
		Storeroom room=new Storeroom();
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
		 room=storeroomdao.getObjByProperty(hql, list.toArray());
		 
		return  room;
		
	}
	/*根据库房名称查库房Id
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialStoreroomBiz#findMeterialStoreroomByPosition(java.lang.String)
	 */
	public Long[] findBookStoreroomByNames(String storeroomname)
				throws Exception {		 
				List<Object> list=new ArrayList<Object>();
				Long [] ids =  null;
				String hql="";
				if(StringUtils.isNotBlank(storeroomname))
				{
					hql += " and name =  " + Constants.PLACEHOLDER;
					list.add(storeroomname);
				}
				 
				
				ids = storeroomdao.getIDs(hql, list.toArray());
				return ids;
			}

	/*根据库房位置查询库房名称
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialStoreroomBiz#findMeterialStoreroomByPosition(java.lang.String)
	 */
	public List<Storeroom> findStoreroomByPosition(int position)
			throws Exception {
		List<Storeroom> storeroomllist= new ArrayList<Storeroom>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>(); 
		if(0<position){
				hqlparam += " and  position= " + Constants.PLACEHOLDER;
				paramList.add(position);
				
		}
		storeroomllist = storeroomdao.getByProperty(hqlparam, paramList);
		return  storeroomllist;
	}
	/**
	 * 查询全部库房
	 */
	public List<Storeroom> findStoreroomAll()throws Exception {
		 
		return storeroomdao.findAll();
	}
	
	/**
	 * 通过名称查询id
	 */
	public Storeroom findNameByStoreroom(String name) throws Exception {
		 String sql="";
		 Storeroom sr=new Storeroom();
		 List<Object> list=new ArrayList<Object>();
		 if(null!=name)
		 {
			 sql+="and name ="+Constants.PLACEHOLDER;
			 list.add(name);
		 }
		 sr=storeroomdao.getObjByProperty(sql, list.toArray());
		return sr;
	}
	
	/**
	 * 通过名称查询id
	 */
	public Storeroom findpositionByStoreroom(int position) throws Exception {
		 String sql="";
		 Storeroom sr=new Storeroom();
		 List<Object> list=new ArrayList<Object>();
		 if(0!=position)
		 {
			 sql+="and position ="+Constants.PLACEHOLDER;
			 list.add(position);
		 }
		 sr=storeroomdao.getObjByProperty(sql, list.toArray());
		return sr;
	}

	
}
