package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.RolePrivilege;


/**
 * 角色权限业务层接口
 * @author Jack
 *
 */
public interface RolePrivilegeBiz {
	
	/**
	 * 添加角色权限
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(RolePrivilege rolePrivilege)throws Exception;
	
	/**
	 * 修改角色权限
	 * @param RolePrivilege
	 * @throws Exception
	 */
	public void modify(RolePrivilege rolePrivilege)throws Exception;
	
	/**
	 * 根据ID删除角色权限
	 * @param RolePrivilege
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 根据ID查询角色权限
	 */
	public RolePrivilege findRolePrivilegeById(int id) throws Exception;
	
	/**
	 * 根据条件查找角色权限数量
	 */
	public int findCountByConditionForHQL(RolePrivilege rolePrivilege)throws Exception;
	
	/**
	 * 根据条件查找角色权限
	 */
	public List<RolePrivilege> findListByConditionForHQL(RolePrivilege rolePrivilege)throws Exception;
	
	/**
	 * 根据角色ID查找角色权限
	 */
	public RolePrivilege findByRoleId(int roleId)throws Exception;
}
