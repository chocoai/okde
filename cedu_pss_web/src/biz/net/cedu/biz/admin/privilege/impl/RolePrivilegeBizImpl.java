package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.RolePrivilegeBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.RolePrivilegeDao;
import net.cedu.entity.admin.privilege.RolePrivilege;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 子系统业务层实现类
 * @author Jack
 *
 */
@Service
public class RolePrivilegeBizImpl implements RolePrivilegeBiz {

	@Autowired
	private RolePrivilegeDao rolePrivilegeDao;
	
	/**
	 * 添加角色权限
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(RolePrivilege rolePrivilege)throws Exception
	{
		rolePrivilegeDao.save(rolePrivilege);
	}
	
	/**
	 * 修改角色权限
	 * @param RolePrivilege
	 * @throws Exception
	 */
	public void modify(RolePrivilege rolePrivilege)throws Exception
	{
		RolePrivilege old=findByRoleId(rolePrivilege.getRoleId());
		if(null==old)
		{
			addNew(rolePrivilege);
		}
		else
		{
			rolePrivilege.setId(old.getId());
			rolePrivilegeDao.update(rolePrivilege);
		}
	}
	
	/**
	 * 根据ID删除角色权限
	 * @param RolePrivilege
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		rolePrivilegeDao.deleteConfig(id);
	}
	
	/**
	 * 根据ID查询角色权限
	 */
	public RolePrivilege findRolePrivilegeById(int id) throws Exception
	{
		return rolePrivilegeDao.findById(id);
	}
	
	/**
	 * 根据条件查找角色权限数量
	 */
	public int findCountByConditionForHQL(RolePrivilege rolePrivilege)throws Exception
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(0<rolePrivilege.getRoleId())
		{
			hqlcon+=" and roleId="+ Constants.PLACEHOLDER;
			paramList.add(rolePrivilege.getRoleId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return rolePrivilegeDao.getCounts(p);
	}
	
	/**
	 * 根据条件查找角色权限
	 */
	public List<RolePrivilege> findListByConditionForHQL(RolePrivilege rolePrivilege)throws Exception
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(0<rolePrivilege.getRoleId())
		{
			hqlcon+=" and roleId="+ Constants.PLACEHOLDER;
			paramList.add(rolePrivilege.getRoleId());
		}
		return rolePrivilegeDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据角色ID查找角色权限
	 */
	public RolePrivilege findByRoleId(int roleId)throws Exception
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		hqlcon+=" and roleId="+ Constants.PLACEHOLDER;
		paramList.add(roleId);
		
		List<RolePrivilege> list=rolePrivilegeDao.getByProperty(hqlcon,paramList);
		if(null!=list&&0<list.size())
			return list.get(0);
		else
			return null;
	}

}
