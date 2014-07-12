package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.ModularDao;
import net.cedu.entity.admin.privilege.Modular;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 模块业务层实现类
 * @author Jack
 *
 */
@Service
public class ModularBizImpl implements ModularBiz {

	@Autowired
	private ModularDao modularDao;
	@Autowired
	private SubSystemBiz subSystemBiz;
	
	/**
	 * 添加模块
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Modular modular)throws Exception
	{
		if(0>=findCountByConditionForHQL(modular))
		{
			modularDao.save(modular);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改模块
	 * @param Modular
	 * @throws Exception
	 */
	public void modify(Modular modular)throws Exception
	{
		modularDao.update(modular);
	}
	
	/**
	 * 根据ID删除模块
	 * @param Modular
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		modularDao.deleteConfig(id);
	}
	
	/**
	 * 查询模块(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Modular> findModularForModel() throws Exception {
		return findAll();
	}
	
	/**
	 * 根据条件查找模块列表_分页
	 */
	public List<Modular> findListByCondition(PageResult<Modular> pr,Modular modular)
	{
		try {
			List<Modular> modulars = null;
			
			PageParame p = new PageParame(pr);
			
//			p.setCurrentPageIndex(pr.getCurrentPageIndex());
//			p.setPageSize(pr.getPageSize());
//			p.setOrder("orders");
//			p.setSort("asc");
			String params="";
			String canshu="";
			
			//名称
			if(StringUtils.isNotBlank(modular.getName()))
			{
				params+=" and name="+ Constants.PLACEHOLDER;
				canshu+=modular.getName()+",";
			}
			//模块所属子系统
			if(0<modular.getSubSystemId())
			{
				params+=" and subSystemId="+ Constants.PLACEHOLDER;
				canshu+=modular.getSubSystemId()+",";
			}
			
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			// Ids集合
			Long[] modularIds = modularDao.getIDs(p);
			if (modularIds != null && modularIds.length != 0) 
			{
				modulars = new ArrayList<Modular>();
				for (int i = 0; i < modularIds.length; i++) {
					// 内存获取
					Modular modularObj = modularDao.findById(Integer.valueOf(modularIds[i].toString()));
					if (modularObj != null) 
					{
						modularObj.setSubSystem(subSystemBiz.findSubSystemById(modularObj.getSubSystemId()));
						modulars.add(modularObj);
					}
				}
			}
			return modulars;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找模块列表
	 */
	public List<Modular> findListByCondition(Modular modular)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		//名称
		if(StringUtils.isNotBlank(modular.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(modular.getName());
		}
		//模块所属子系统
		if(-1<modular.getSubSystemId())
		{
			hqlcon+=" and subSystemId="+ Constants.PLACEHOLDER;
			paramList.add(modular.getSubSystemId());
		}		
		hqlcon+=" order by orders asc";
		
		return modularDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找模块数量
	 */
	public int findCountByConditionForHQL(Modular modular)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		PageParame p = new PageParame();
		//模块名称
		if(StringUtils.isNotBlank(modular.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(modular.getName());
		}
		//模块所属子系统
		if(0<modular.getSubSystemId())
		{
			hqlcon+=" and subSystemId="+ Constants.PLACEHOLDER;
			paramList.add(modular.getSubSystemId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		int count=modularDao.getCounts(p);
		return count;
	}
	
	/**
	 * 查询所有模块(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Modular> findAll() throws Exception {
		return modularDao.findAll();
	}
	
	/**
	 * 根据ID查询模块
	 */
	public Modular findModularById(int id) throws Exception
	{
		return modularDao.findById(id);
	}
	
	/**
	 * 根据子系统ID查询模块列表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Modular> findModularBySubSystemId(int id)throws Exception 
	{
		Modular m=new Modular();
		m.setSubSystemId(id);
		return findListByCondition(m);
	}
}
