package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.GroupPrivilege;


/**
 * 用户组权限业务层接口
 * @author Jack
 *
 */
public interface GroupPrivilegeBiz {
	
	/**
	 * 添加用户组权限
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(GroupPrivilege groupPrivilege)throws Exception;
	
	/**
	 * 修改用户组权限
	 * @param GroupPrivilege
	 * @throws Exception
	 */
	public void modify(GroupPrivilege groupPrivilege)throws Exception;
	
	/**
	 * 根据ID删除用户组权限
	 * @param GroupPrivilege
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 根据ID查询用户组权限
	 */
	public GroupPrivilege findGroupPrivilegeById(int id) throws Exception;
	
	/**
	 * 根据条件查找用户组权限数量
	 */
	public int findCountByConditionForHQL(GroupPrivilege groupPrivilege)throws Exception;
	
	/**
	 * 根据条件查找用户组权限
	 */
	public List<GroupPrivilege> findListByConditionForHQL(GroupPrivilege groupPrivilege)throws Exception;
	
	/**
	 * 根据用户组ID查找用户组权限
	 */
	public GroupPrivilege findBygroupId(int groupId)throws Exception;
}
