package net.cedu.biz.admin;

import java.util.List;

import net.cedu.entity.admin.UserGroup;
import net.cedu.model.page.PageResult;

/**
 * 用户组业务层接口
 * 
 * @author Jack
 * 
 */
public interface UserGroupBiz {

	/**
	 * 查询用户组(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserGroup> findUserGroupForModel() throws Exception;

	/**
	 * 根据ID查询用户组
	 */
	public UserGroup findUserGroupById(int id) throws Exception ;

	/**
	 * 添加用户组
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void createNew(UserGroup userGroup) throws Exception;

	/**
	 * 根据ID删除用户组
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception;

	/**
	 * 修改用户组
	 * 
	 * @param UserGroup
	 * @throws Exception
	 */
	public void modify(UserGroup userGroup) throws Exception;

	/**
	 * 按学习中心ID查找用户组_供业务模块调用
	 */
	public List<UserGroup> findUserGroupPageListByBranchId(UserGroup userGroup, PageResult<UserGroup> pr)throws Exception;

	/**
	 * 按学习中心ID查找用户组数量_供业务模块调用
	 */
	public int findUserGroupPageCountByBranchId(UserGroup userGroup, PageResult<UserGroup> pr)throws Exception;
	
	/**
	 * 按学习中心ID查找用户组_权限系统用
	 */
	public List<UserGroup> findUserGroupPageListByBranchIdAdmin(UserGroup userGroup, PageResult<UserGroup> pr)throws Exception;

	/**
	 * 按学习中心ID查找用户组数量_权限系统用
	 */
	public int findUserGroupPageCountByBranchIdAdmin(UserGroup userGroup, PageResult<UserGroup> pr)throws Exception;

	/**
	 * 通过中心ID查询用户组集合_供业务模块调用
	 * @param orgId 学习中心ID
	 * return 启用状态未删除的用户组
	 */
	public List<UserGroup> findUserGroupsByOrgId(int orgId) throws Exception ;
	
	/**
	 * 通过用户组名称查询所有学习中心同名用户组
	 * @param name 用户组名称
	 * return 启用状态未删除的用户组
	 */
	public List<UserGroup> findUserGroupsByName(String name) throws Exception ;
}
