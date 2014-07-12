package net.cedu.biz.admin.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UGroupUserBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.entity.admin.UGroupUser;
import net.cedu.entity.admin.User;
import net.cedu.entity.admin.UserGroup;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户组业务层实现类
 * 
 * @author Jack
 * 
 */
@Service
public class UGroupUserBizImpl implements UGroupUserBiz 
{
	@Autowired
	private UGroupUserDao uGroupUserDao;
	@Autowired
	private UserBiz userBiz;

	/**
	 * 添加用户组
	 * 
	 * @param UGroupUser
	 * @throws Exception
	 */
	public void createNew(UGroupUser UGroupUser) throws Exception {
		uGroupUserDao.save(UGroupUser);

	}
	
	/**
	 * 根据条件批量删除
	 * @param uGroupUser
	 * @throws Exception
	 */
	public void deleteByGroupId(UGroupUser uGroupUser) throws Exception
	{
		String hql="";
		List<Object> list=new ArrayList<Object>();
		if(0<uGroupUser.getGroupId()||BranchEnum.Root.value()==uGroupUser.getGroupId())
		{
			hql+=" and groupId="+Constants.PLACEHOLDER;
			list.add(uGroupUser.getGroupId());
		}
		if(0<uGroupUser.getUserId()||BranchEnum.Root.value()==uGroupUser.getUserId())
		{
			hql+=" and userId="+Constants.PLACEHOLDER;
			list.add(uGroupUser.getUserId());
		}
		if(0<hql.length()&&0<list.size())
			uGroupUserDao.deleteByProperty(hql, list.toArray());
	}
	
	/**
	 * 根据条件查询_普通
	 */
	public List<UGroupUser> findUGroupUserListByCondition(UGroupUser uGroupUser)
			throws Exception {
		List<UGroupUser> UGroupUsers = new ArrayList<UGroupUser>();
		List<Object> list=new ArrayList<Object>();
		String hql="";
		if (uGroupUser != null) 
		{
			if(0<uGroupUser.getGroupId()||BranchEnum.Root.value()==uGroupUser.getGroupId())
			{
				hql+=" and groupId=" + Constants.PLACEHOLDER;
				list.add(uGroupUser.getGroupId());
			}	
			if(0<uGroupUser.getUserId()||BranchEnum.Root.value()==uGroupUser.getUserId())
			{
				hql+=" and userId=" + Constants.PLACEHOLDER;
				list.add(uGroupUser.getUserId());
			}	
		}
		PageParame p = new PageParame();
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		Long[] uGroupUserIds = uGroupUserDao.getIDs(p);
		if (uGroupUserIds != null && uGroupUserIds.length != 0) 
		{
			for (int i = 0; i < uGroupUserIds.length; i++) 
			{
				int id=Integer.parseInt(uGroupUserIds[i].toString());
				UGroupUser u = uGroupUserDao.findById(id);
				if (u != null) 
				{
					u.setUser(userBiz.findUserById(u.getUserId()));
					UGroupUsers.add(u);
				}
			}
		}
		return UGroupUsers;
	}

	/*
	 * 通过用户组ID获取用户列表
	 * @see net.cedu.biz.admin.UGroupUserBiz#findUserByUserGroupId(int)
	 */
	public List<User> findUserByUserGroupId(int userGroupId) throws Exception {
		List<User> userList=null;
		List<UGroupUser> uGroupUserList = uGroupUserDao.getByProperty("groupId", userGroupId);
		if(uGroupUserList!=null&&uGroupUserList.size()!=0){
			userList=new ArrayList<User>();
			for (UGroupUser uGroupUser : uGroupUserList) {
				User user=userBiz.findUserById(uGroupUser.getUserId());
				if(user!=null){
					userList.add(user);
				}
			}
		}
		return userList;
	}
}
