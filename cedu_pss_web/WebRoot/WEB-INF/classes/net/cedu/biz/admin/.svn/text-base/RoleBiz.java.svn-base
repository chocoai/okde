package net.cedu.biz.admin;

import java.util.List;

import net.cedu.entity.admin.Role;
import net.cedu.model.page.PageResult;


/**
 * 角色业务层接口
 * 
 * @author Jack
 * 
 */
public interface RoleBiz {

	/**
	 * 根据ID查询角色
	 */
	public Role findRoleById(int id) throws Exception;

	/**
	 * 添加角色
	 * 
	 * @param Role
	 * @throws Exception
	 */
	public boolean createNew(Role role) throws Exception;

	/**
	 * 根据ID删除角色
	 * 
	 * @param Role
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception;

	/**
	 * 修改角色
	 * 
	 * @param Role
	 * @throws Exception
	 */
	public boolean modify(Role role) throws Exception;
	
	/**
	 * 按学习中心ID查找角色_权限系统用
	 */
	public List<Role> findRolePageListByBranchIdAdmin(Role role, PageResult<Role> pr)
			throws Exception;

	/**
	 * 按学习中心ID查找角色数量_权限系统用
	 */
	public int findRolePageCountByBranchIdAdmin(Role role, PageResult<Role> pr)
			throws Exception;
	
	/**
	 * 根据条件查询角色列表
	 * @param role
	 * @return
	 * @throws Exception
	 */
	public List<Role> findRoleByCondition(Role role) throws Exception ;
}
