package net.cedu.biz.admin.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.RoleBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.RoleDao;
import net.cedu.entity.admin.Role;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 角色业务层实现类
 * 
 * @author Jack
 * 
 */
@Service
public class RoleBizImpl implements RoleBiz {

	@Autowired
	private RoleDao roleDao;

	/**
	 * 根据ID查询角色
	 */
	public Role findRoleById(int id) throws Exception {
		return roleDao.findById(id);
	}

	/**
	 * 添加角色
	 * 
	 * @param Role
	 * @throws Exception
	 */
	public boolean createNew(Role role) throws Exception 
	{
		if(0==findRoleCountByCondition(role))
		{
			roleDao.save(role);
			return true;
		}
		return false;
	}

	/**
	 * 根据ID删除角色
	 * 
	 * @param Role
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception {
		roleDao.deleteConfig(id);

	}

	/**
	 * 修改角色
	 * 
	 * @param Role
	 * @throws Exception
	 */
	public boolean modify(Role role) throws Exception 
	{
		if(0==findRoleCountByCondition(role))
		{
			roleDao.update(role);
			return true;
		}
		return false;

	}
	
	/**
	 * 按学习中心ID查找角色_权限系统用
	 */
	public List<Role> findRolePageListByBranchIdAdmin(Role role, PageResult<Role> pr)
			throws Exception {
		List<Role> roles = new ArrayList<Role>();
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (role != null) 
		{
			if(StringUtils.isNotBlank(role.getName()))
			{
				hql+=" and name like " + Constants.PLACEHOLDER;
				list.add("%"+role.getName()+"%");
			}			
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());

		Long[] RoleIds = roleDao.getIDs(p);
		if (RoleIds != null && RoleIds.length != 0) 
		{
			for (int i = 0; i < RoleIds.length; i++) 
			{
				Role r = roleDao.findById(Integer.parseInt(RoleIds[i].toString()));
				roles.add(r);
			}
		}
		return roles;

	}

	/**
	 * 按学习中心ID查找角色数量_权限系统用
	 */
	public int findRolePageCountByBranchIdAdmin(Role role, PageResult<Role> pr)
			throws Exception {
		PageParame p = new PageParame(pr);

		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (role != null) 
		{
			if(StringUtils.isNotBlank(role.getName()))
			{
				hql+=" and name like " + Constants.PLACEHOLDER;
				list.add("%"+role.getName()+"%");
			}			
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());

		return roleDao.getCounts(p);
	}
	
	/**
	 * 根据条件查询角色列表
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Role> findRoleByCondition(Role role) throws Exception 
	{
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (role != null) 
		{
			if(StringUtils.isNotBlank(role.getName()))
			{
				hql+=" and name like " + Constants.PLACEHOLDER;
				list.add("%"+role.getName()+"%");
			}			
		}
		return roleDao.getByProperty(hql,list.toArray());
	}
	
	/**
	 * 根据条件查询角色列表
	 * @param role
	 * @return
	 * @throws Exception
	 */
	private int findRoleCountByCondition(Role role) throws Exception 
	{
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (role != null) 
		{
			if(StringUtils.isNotBlank(role.getName()))
			{
				hql+=" and name = " + Constants.PLACEHOLDER;
				list.add(role.getName());
			}			
		}
		return roleDao.findCountByProperty(hql,list.toArray());
	}
}
