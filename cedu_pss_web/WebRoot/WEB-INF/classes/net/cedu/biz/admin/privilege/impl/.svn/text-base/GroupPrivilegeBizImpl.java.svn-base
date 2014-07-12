package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.GroupPrivilegeBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.GroupPrivilegeDao;
import net.cedu.entity.admin.privilege.GroupPrivilege;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户组权限业务层实现类
 * @author Jack
 *
 */
@Service
public class GroupPrivilegeBizImpl implements GroupPrivilegeBiz {

	@Autowired
	private GroupPrivilegeDao groupPrivilegeDao;
	
	/**
	 * 添加用户组权限
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(GroupPrivilege GroupPrivilege)throws Exception
	{
		groupPrivilegeDao.save(GroupPrivilege);
	}
	
	/**
	 * 修改用户组权限
	 * @param GroupPrivilege
	 * @throws Exception
	 */
	public void modify(GroupPrivilege GroupPrivilege)throws Exception
	{
		GroupPrivilege old=findBygroupId(GroupPrivilege.getGroupId());
		if(null==old)
		{
			addNew(GroupPrivilege);
		}
		else
		{
			GroupPrivilege.setId(old.getId());
			groupPrivilegeDao.update(GroupPrivilege);
		}
	}
	
	/**
	 * 根据ID删除用户组权限
	 * @param GroupPrivilege
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		groupPrivilegeDao.deleteConfig(id);
	}
	
	/**
	 * 根据ID查询用户组权限
	 */
	public GroupPrivilege findGroupPrivilegeById(int id) throws Exception
	{
		return groupPrivilegeDao.findById(id);
	}
	
	/**
	 * 根据条件查找用户组权限数量
	 */
	public int findCountByConditionForHQL(GroupPrivilege GroupPrivilege)throws Exception
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(0<GroupPrivilege.getGroupId())
		{
			hqlcon+=" and groupId="+ Constants.PLACEHOLDER;
			paramList.add(GroupPrivilege.getGroupId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return groupPrivilegeDao.getCounts(p);
	}
	
	/**
	 * 根据条件查找用户组权限
	 */
	public List<GroupPrivilege> findListByConditionForHQL(GroupPrivilege GroupPrivilege)throws Exception
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(0<GroupPrivilege.getGroupId())
		{
			hqlcon+=" and groupId="+ Constants.PLACEHOLDER;
			paramList.add(GroupPrivilege.getGroupId());
		}
		return groupPrivilegeDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据用户组ID查找用户组权限
	 */
	public GroupPrivilege findBygroupId(int groupId)throws Exception
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		hqlcon+=" and groupId="+ Constants.PLACEHOLDER;
		paramList.add(groupId);
		
		List<GroupPrivilege> list=groupPrivilegeDao.getByProperty(hqlcon,paramList);
		if(null!=list&&0<list.size())
			return list.get(0);
		else
			return null;
	}

}
