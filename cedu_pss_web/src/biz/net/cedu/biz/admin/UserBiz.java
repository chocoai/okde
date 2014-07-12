package net.cedu.biz.admin;

import java.util.List;

import net.cedu.entity.admin.User;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

/**
 * 用户业务层接口
 * 
 * @author Jack
 * 
 */
public interface UserBiz {

	/**
	 * 查询用户(供其他模块调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserForModel() throws Exception;

	/**
	 * 根据ID查询用户
	 */
	public User findUserById(int id) throws Exception ;

	/**
	 * 添加用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public boolean createNew(User user) throws Exception;

	/**
	 * 根据ID删除用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception;

	/**
	 * 修改用户
	 * 
	 * @param User
	 * @throws Exception
	 */
	public void modify(User user) throws Exception;

	/**
	 * 按用户名查找用户
	 */
	public User findByUserName(String userName) throws Exception;

	/**
	 * 按学习中心ID查找用户_供业务模块调用
	 */
	public List<User> findUserPageListByBranchId(User user, PageResult<User> pr)throws Exception;

	/**
	 * 按学习中心ID查找用户数量_供业务模块调用
	 */
	public int findUserPageCountByBranchId(User user, PageResult<User> pr)throws Exception;
	
	/**
	 * 按学习中心ID查找用户_权限系统用
	 */
	public List<User> findUserPageListByBranchIdAdmin(User user, PageResult<User> pr)throws Exception;

	/**
	 * 按学习中心ID查找用户数量_权限系统用
	 */
	public int findUserPageCountByBranchIdAdmin(User user, PageResult<User> pr)throws Exception;
	
	/**
	 * 按条件查询用户列表
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByCondition(User user) throws Exception;
	
	/**
	 * 通过中心ID查询用户集合_供业务模块调用
	 * @param orgId 学习中心ID
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByOrgId(int orgId) throws Exception;
	
	/**
	 * 通过中心ID查询用户集合_供业务模块调用
	 * @param orgId 学习中心ID
	 * @param areaManager 是否为区域经理
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersByOrgId(int orgId,int areaManager) throws Exception;
	
	/**
	 * 通过中心ID查询用户集合
	 * @param orgId
	 * @return
	 * @throws Exception
	 */
	public int countUserByOrgId(int orgId)throws Exception;
	
	/**
	 * 获取用户Ids
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public Long[]  findUserIds(PageParame p)throws Exception;
	
	/**
	 * 查询总部用户
	 * 
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersForAdmin() throws Exception ;
	
	/**
	 * 查询中心用户
	 * 
	 * return 启用状态未删除的用户
	 */
	public List<User> findUsersForBranch() throws Exception ;
	
	/**
	 * 通过中心ID查询跟进人集合
	 * @param orgId  中心Id
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserFollowUpListByOrgId(int orgId) throws Exception;
	/**
	 * 
	 * @功能：通过部门ID字符串查询查询用户集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-7 下午02:15:10
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param departmentIds
	 * @return
	 * @throws Exception
	 */
	public List<User> findUsersByDepartmentIds(String departmentIds)throws Exception;
	
	/**
	 * 
	 * @功能：查询所有区域经理
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-9 下午03:18:42
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return
	 * @throws Exception
	 */
	public List<User> findAllAreaManager()throws Exception;
	
	/**
	 * 
	 * @功能：查询所有申请人
	 *
	 * @作者： 杨阳
	 * @作成时间：2012-2-2 下午03:18:42
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 *@param name
	 * @return  
	 * @throws Exception
	 */
	
	public Long[] findUserByNames(String name)throws Exception;
	
	/**
	 * 
	 * @功能：查询所有区域经理(不包括已停用 已删除)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-23 上午10:33:00
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @return
	 * @throws Exception
	 */
	public List<User> findAllAreaManagerByStatusAndDeleteFlag() throws Exception;
	
	/**
	 * @功能：假删除用户 有验证条件
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-6-11 上午09:44:36
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param deleteUserId 要删除的用户id
	 * @param currentUserId 当前操作的用户id
	 * @return null:正确执行，非空则是错误消息
	 * @throws Exception
	 */
	public List<String> deleteFalseUserById(int deleteUserId,int currentUserId) throws Exception;
	
	/**
	 * @功能：还原假删除的用户
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-12 上午09:17:58
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param reductionUserId 要还原的用户id
	 * @param currentUserId 当前操作的用户id
	 * @return
	 * @throws Exception
	 */
	public boolean updateReductionUserById(int reductionUserId,int currentUserId) throws Exception;
	
	/**
	 * @功能：根据条件查询全部用户数量(分页)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-12 上午10:21:32
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param user
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findAllUserCountByParams(User user,PageResult<User> pr) throws Exception;
	
	/**
	 * @功能：根据条件查询全部用户集合(分页)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-12 上午10:23:00
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param user
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<User> findAllUserListByParams(User user,PageResult<User> pr) throws Exception;
	
	/**
	 * 按学习中心ID查找用户（总部管理学习中心用户使用）
	 * @param user
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserPageListByUserForBranch(User user, PageResult<User> pr) throws Exception;
	
	
	/**
	 * 按学习中心ID查找用户数量（总部管理学习中心用户使用）
	 * @param user
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findUserPageCountByUserForBranch(User user, PageResult<User> pr) throws Exception;
	
}
