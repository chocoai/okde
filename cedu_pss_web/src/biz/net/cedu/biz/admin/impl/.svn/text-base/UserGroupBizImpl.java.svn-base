package net.cedu.biz.admin.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.RoleBiz;
import net.cedu.biz.admin.UserGroupBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.dao.admin.UserGroupDao;
import net.cedu.entity.admin.UserGroup;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.xwork.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户组业务层实现类
 * 
 * @author Jack
 * 
 */
@Service
public class UserGroupBizImpl implements UserGroupBiz {

	@Autowired
	private UserGroupDao userGroupDao;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private RoleBiz roleBiz;

	/**
	 * 查询用户组(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserGroup> findUserGroupForModel() throws Exception {
		return userGroupDao.findAll();
	}

	/**
	 * 根据ID查询用户组
	 */
	public UserGroup findUserGroupById(int id) throws Exception 
	{
		UserGroup userGroup=userGroupDao.findById(id);
		if(null!=userGroup)
		{
			userGroup.setOrg(branchBiz.findBranchById(userGroup.getOrgId()));
			userGroup.setRole(roleBiz.findRoleById(userGroup.getRoleId()));
		}
		return userGroup;
	}

	/**
	 * 添加用户组
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void createNew(UserGroup userGroup) throws Exception {
		userGroupDao.save(userGroup);

	}

	/**
	 * 根据ID删除用户组
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception {
		userGroupDao.deleteConfig(id);

	}

	/**
	 * 修改用户组
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void modify(UserGroup UserGroup) throws Exception {
		userGroupDao.update(UserGroup);

	}

	/**
	 * 按学习中心ID查找用户组_供业务模块调用
	 */
	public List<UserGroup> findUserGroupPageListByBranchId(UserGroup userGroup, PageResult<UserGroup> pr)throws Exception 
	{
		List<UserGroup> userGroups = new ArrayList<UserGroup>();
		PageParame p = new PageParame(pr);
		String hql="";
		List<Object> list=new ArrayList<Object>();
		if (userGroup != null) 
		{
			if(BranchEnum.Root.value()-1<userGroup.getOrgId())
			{
				hql+=" and orgId=" + Constants.PLACEHOLDER;
				list.add(userGroup.getOrgId());
			}			
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());

		Long[] UserGroupIds = userGroupDao.getIDs(p);
		if (UserGroupIds != null && UserGroupIds.length != 0) {
			for (int i = 0; i < UserGroupIds.length; i++) {
				UserGroup u = userGroupDao.findById(Integer.parseInt(UserGroupIds[i].toString()));
				if (u != null) 
				{
					u.setOrg(branchBiz.findBranchById(u.getOrgId()));
					userGroups.add(u);
				}
			}
		}

		return userGroups;

	}

	/**
	 * 按学习中心ID查找用户组数量_供业务模块调用
	 */
	public int findUserGroupPageCountByBranchId(UserGroup userGroup, PageResult<UserGroup> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		String hql="";
		List<Object> list=new ArrayList<Object>();
		if (userGroup != null) 
		{
			if(BranchEnum.Root.value()-1<userGroup.getOrgId())
			{
				hql+=" and orgId=" + Constants.PLACEHOLDER;
				list.add(userGroup.getOrgId());
			}			
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());

		return userGroupDao.getCounts(p);
	}
	
	/**
	 * 按学习中心ID查找用户组_权限系统用
	 */
	public List<UserGroup> findUserGroupPageListByBranchIdAdmin(UserGroup userGroup, PageResult<UserGroup> pr)
			throws Exception {
		List<UserGroup> userGroups = new ArrayList<UserGroup>();
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (userGroup != null) 
		{
			if(BranchEnum.Root.value()-1<userGroup.getOrgId())
			{
				hql+=" and orgId=" + Constants.PLACEHOLDER;
				list.add(userGroup.getOrgId());
			}			
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());

		Long[] UserGroupIds = userGroupDao.getIDs(p);
		if (UserGroupIds != null && UserGroupIds.length != 0) 
		{
			for (int i = 0; i < UserGroupIds.length; i++) 
			{
				UserGroup u = userGroupDao.findById(Integer.parseInt(UserGroupIds[i].toString()));
				if (u != null) 
				{
					u.setOrg(branchBiz.findBranchById(u.getOrgId()));
					userGroups.add(u);
				}
			}
		}

		return userGroups;

	}

	/**
	 * 按学习中心ID查找用户组数量_权限系统用
	 */
	public int findUserGroupPageCountByBranchIdAdmin(UserGroup userGroup, PageResult<UserGroup> pr)
			throws Exception {
		PageParame p = new PageParame(pr);

		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (userGroup != null) 
		{
			if(BranchEnum.Root.value()-1<userGroup.getOrgId())
			{
				hql+=" and orgId=" + Constants.PLACEHOLDER;
				list.add(userGroup.getOrgId());
			}			
		}
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());

		return userGroupDao.getCounts(p);
	}

	/**
	 * 通过中心ID查询用户组集合_供业务模块调用
	 * @param orgId 学习中心ID
	 * return 启用状态未删除的用户组
	 */
	public List<UserGroup> findUserGroupsByOrgId(int orgId) throws Exception 
	{
		String hqlcon = "";
		List<UserGroup> list = null;
		List<Object> paramList = new ArrayList<Object>();

		//学习中心ID
		if (BranchEnum.Root.value()-1<orgId) {
			hqlcon += " and orgId=" + Constants.PLACEHOLDER;
			paramList.add(orgId);
		}
		list = userGroupDao.getByProperty(hqlcon, paramList);
		return list;
	}
	
	/**
	 * 通过用户组名称查询所有学习中心同名用户组
	 * @param name 用户组名称
	 * return 启用状态未删除的用户组
	 */
	public List<UserGroup> findUserGroupsByName(String name) throws Exception 
	{
		String hqlcon = "";
		List<UserGroup> list = null;
		List<Object> paramList = new ArrayList<Object>();

		//用户组名称
		if (StringUtils.isNotBlank(name)) 
		{
			hqlcon += " and name=" + Constants.PLACEHOLDER;
			paramList.add(name);
		}
		hqlcon+=" and orgId>"+Constants.PLACEHOLDER;
		paramList.add(BranchEnum.Admin.value());
		
		list = userGroupDao.getByProperty(hqlcon, paramList);
		return list;
	}

}
