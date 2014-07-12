package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.PrivilegeDao;
import net.cedu.entity.admin.privilege.Privilege;
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
public class PrivilegeBizImpl implements PrivilegeBiz 
{
	@Autowired
	private PrivilegeDao privilegeDao;
	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	
	/**
	 * 添加权限集
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Privilege privilege)throws Exception
	{
		if(0>=findCountByConditionForHQL(privilege))
		{
			privilegeDao.save(privilege);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void modify(Privilege privilege)throws Exception
	{
		privilegeDao.update(privilege);
	}
	
	/**
	 * 根据ID删除权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		privilegeDao.deleteConfig(id);
	}
	
	/**
	 * 查询权限集(供其他权限集调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Privilege> findPrivilegeForModel() throws Exception {
		return findAll();
	}
	
	/**
	 * 根据条件查找权限集列表_分页
	 */
	public List<Privilege> findListByCondition(PageResult<Privilege> pr,Privilege privilege)
	{
		try {
			List<Privilege> privileges = null;
			
			PageParame p = new PageParame(pr);
			
//			p.setCurrentPageIndex(pr.getCurrentPageIndex());
//			p.setPageSize(pr.getPageSize());
//			p.setOrder("orders");
//			p.setSort("asc");
			String params="";
			String canshu="";
			
			//名称
			if(StringUtils.isNotBlank(privilege.getName()))
			{
				params+=" and name="+ Constants.PLACEHOLDER;
				canshu+=privilege.getName()+",";
			}
			
			//权限集
			if(-1<privilege.getSetId())
			{
				params+=" and setId="+ Constants.PLACEHOLDER;
				canshu+=privilege.getSetId()+",";
			}
			
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			// Ids集合
			Long[] privilegeIds = privilegeDao.getIDs(p);
			if (privilegeIds != null && privilegeIds.length != 0) 
			{
				privileges = new ArrayList<Privilege>();
				for (int i = 0; i < privilegeIds.length; i++) {
					// 内存获取
					Privilege privilegeObj = privilegeDao.findById(Integer.valueOf(privilegeIds[i].toString()));
					if (privilegeObj != null) 
					{
						privilegeObj.setPrivilegeSet(privilegeSetBiz.findPrivilegeSetById(privilegeObj.getSetId()));
						privileges.add(privilegeObj);
					}
				}
			}
			return privileges;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找权限集列表
	 */
	public List<Privilege> findListByCondition(Privilege privilege)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		//名称
		if(StringUtils.isNotBlank(privilege.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(privilege.getName());
		}
		//权限集
		if(-1<privilege.getSetId())
		{
			hqlcon+=" and setId="+ Constants.PLACEHOLDER;
			paramList.add(privilege.getSetId());
		}
				
		hqlcon+=" order by orders asc";
		
		return privilegeDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找权限集数量
	 */
	public int findCountByConditionForHQL(Privilege privilege)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		PageParame p = new PageParame();
		//权限ID
		if(StringUtils.isNotBlank(privilege.getName()))
		{
			hqlcon+=" and name="+ Constants.PLACEHOLDER;
			paramList.add(privilege.getName());
		}
		//权限集
		if(-1<privilege.getSetId())
		{
			hqlcon+=" and setId="+ Constants.PLACEHOLDER;
			paramList.add(privilege.getSetId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		int count=privilegeDao.getCounts(p);
		return count;
	}
	
	/**
	 * 查询所有权限集
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Privilege> findAll() throws Exception {
		return privilegeDao.findAll();
	}
	
	/**
	 * 根据ID查询权限集
	 */
	public Privilege findPrivilegeById(int id) throws Exception
	{
		return privilegeDao.findById(id);
	}
	
	/**
	 * 根据权限集ID查询权限
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Privilege> findPrivilegeBySetId(int id)throws Exception
	{
		Privilege pr=new Privilege();
		pr.setSetId(id);
		return findListByCondition(pr);
	}
}
