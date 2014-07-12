package net.cedu.biz.admin;

import java.util.List;

import net.cedu.entity.admin.UGroupUser;
import net.cedu.entity.admin.User;


/**
 * 用户组业务层接口
 * 
 * @author Jack
 * 
 */
public interface UGroupUserBiz {
	
	/**
	 * 添加用户组
	 * 
	 * @param UGroupUser
	 * @throws Exception
	 */
	public void createNew(UGroupUser UGroupUser) throws Exception;
	
	/**
	 * 根据条件批量删除
	 * @param uGroupUser
	 * @throws Exception
	 */
	public void deleteByGroupId(UGroupUser uGroupUser) throws Exception;
	
	/**
	 * 根据条件查询
	 */
	public List<UGroupUser> findUGroupUserListByCondition(UGroupUser uGroupUser)throws Exception;
	
	/**
	 * 通过用户组ID获取用户列表
	 * @param userGroupId 用户组ID
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserByUserGroupId(int userGroupId)throws Exception;
	
}
