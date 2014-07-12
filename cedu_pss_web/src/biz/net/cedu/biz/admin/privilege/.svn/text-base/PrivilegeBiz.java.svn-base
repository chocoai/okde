package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.model.page.PageResult;

/**
 * 权限集业务层接口
 * @author Jack
 *
 */
public interface PrivilegeBiz 
{
	/**
	 * 添加权限集
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Privilege privilege)throws Exception;
	
	/**
	 * 修改权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void modify(Privilege privilege)throws Exception;
	
	/**
	 * 根据ID删除权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 查询权限集(供其他权限集调用)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Privilege> findPrivilegeForModel() throws Exception;
	
	/**
	 * 根据条件查找权限集列表_分页
	 */
	public List<Privilege> findListByCondition(PageResult<Privilege> pr,Privilege privilege);
	
	/**
	 * 根据条件查找权限集列表
	 */
	public List<Privilege> findListByCondition(Privilege privilege);
	
	/**
	 * 根据条件查找权限集数量
	 */
	public int findCountByConditionForHQL(Privilege privilege);
	
	/**
	 * 查询所有权限集(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Privilege> findAll() throws Exception;
	
	/**
	 * 根据ID查询权限集
	 */
	public Privilege findPrivilegeById(int id) throws Exception;
	
	/**
	 * 根据权限集ID查询权限
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<Privilege> findPrivilegeBySetId(int id)throws Exception;
}
