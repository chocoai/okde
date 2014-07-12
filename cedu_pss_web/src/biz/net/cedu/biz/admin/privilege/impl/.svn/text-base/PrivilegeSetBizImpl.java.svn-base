package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.PrivilegeSetDao;
import net.cedu.entity.admin.privilege.PrivilegeSet;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限集业务层实现类
 * @author Jack
 *
 */
@Service
public class PrivilegeSetBizImpl implements PrivilegeSetBiz 
{
	@Autowired
	private PrivilegeSetDao PrivilegeSetDao;
	@Autowired
	private ModularBiz modularBiz;
	
	/**
	 * 添加权限集
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(PrivilegeSet privilegeSet)throws Exception
	{
		if(0>=findCountByConditionForHQL(privilegeSet))
		{
			PrivilegeSetDao.save(privilegeSet);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void modify(PrivilegeSet privilegeSet)throws Exception
	{
		PrivilegeSetDao.update(privilegeSet);
	}
	
	/**
	 * 根据ID删除权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		PrivilegeSetDao.deleteConfig(id);
	}
	
	/**
	 * 查询权限集(供其他权限集调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PrivilegeSet> findPrivilegeSetForModel() throws Exception {
		return findAll();
	}
	
	/**
	 * 根据条件查找权限集列表_分页
	 */
	public List<PrivilegeSet> findListByCondition(PageResult<PrivilegeSet> pr,PrivilegeSet privilegeSet)
	{
		try {
			List<PrivilegeSet> privilegeSets = null;
			
			PageParame p = new PageParame(pr);
			
//			p.setCurrentPageIndex(pr.getCurrentPageIndex());
//			p.setPageSize(pr.getPageSize());
//			p.setOrder("orders");
//			p.setSort("asc");
			String params="";
			String canshu="";
			
			//名称
			if(StringUtils.isNotBlank(privilegeSet.getName()))
			{
				params+=" and name="+ Constants.PLACEHOLDER;
				canshu+=privilegeSet.getName()+",";
			}
			
			//模块
			if(-1<privilegeSet.getModularId())
			{
				params+=" and modularId="+ Constants.PLACEHOLDER;
				canshu+=privilegeSet.getModularId()+",";
			}
			
			params+=" order by orders asc";
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			// Ids集合
			Long[] privilegeSetIds = PrivilegeSetDao.getIDs(p);
			if (privilegeSetIds != null && privilegeSetIds.length != 0) 
			{
				privilegeSets = new ArrayList<PrivilegeSet>();
				for (int i = 0; i < privilegeSetIds.length; i++) {
					// 内存获取
					PrivilegeSet privilegeSetObj = PrivilegeSetDao.findById(Integer.valueOf(privilegeSetIds[i].toString()));
					if (privilegeSetObj != null) 
					{
						privilegeSetObj.setModular(modularBiz.findModularById(privilegeSetObj.getModularId()));
						privilegeSets.add(privilegeSetObj);
					}
				}
			}
			return privilegeSets;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找权限集列表
	 */
	public List<PrivilegeSet> findListByCondition(PrivilegeSet privilegeSet)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		//名称
		if(StringUtils.isNotBlank(privilegeSet.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(privilegeSet.getName());
		}
		//模块
		if(-1<privilegeSet.getModularId())
		{
			hqlcon+=" and modularId="+ Constants.PLACEHOLDER;
			paramList.add(privilegeSet.getModularId());
		}
				
		hqlcon+=" order by orders asc";
		
		return PrivilegeSetDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找权限集数量
	 */
	public int findCountByConditionForHQL(PrivilegeSet privilegeSet)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		PageParame p = new PageParame();
		//权限ID
		if(StringUtils.isNotBlank(privilegeSet.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(privilegeSet.getName());
		}
		//模块
		if(-1<privilegeSet.getModularId())
		{
			hqlcon+=" and modular="+ Constants.PLACEHOLDER;
			paramList.add(privilegeSet.getModularId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		int count=PrivilegeSetDao.getCounts(p);
		return count;
	}
	
	/**
	 * 查询所有权限集
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PrivilegeSet> findAll() throws Exception {
		return PrivilegeSetDao.findAll();
	}
	
	/**
	 * 根据ID查询权限集
	 */
	public PrivilegeSet findPrivilegeSetById(int id) throws Exception
	{
		return PrivilegeSetDao.findById(id);
	}
	
	/**
	 * 根据模块ID查询权限集
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<PrivilegeSet> findPrivilegeSetByModularId(int id)throws Exception
	{
		PrivilegeSet ps=new PrivilegeSet();
		ps.setModularId(id);
		return findListByCondition(ps);
	}
}
