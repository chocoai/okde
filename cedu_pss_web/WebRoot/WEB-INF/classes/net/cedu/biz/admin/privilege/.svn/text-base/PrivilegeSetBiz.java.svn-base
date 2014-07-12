package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.PrivilegeSet;
import net.cedu.model.page.PageResult;

/**
 * 权限集业务层接口
 * @author Jack
 *
 */
public interface PrivilegeSetBiz 
{
	/**
	 * 添加权限集
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(PrivilegeSet privilegeSet)throws Exception;
	
	/**
	 * 修改权限集
	 * @param PrivilegeSet
	 * @throws Exception
	 */
	public void modify(PrivilegeSet privilegeSet)throws Exception;
	
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
	public List<PrivilegeSet> findPrivilegeSetForModel() throws Exception;
	
	/**
	 * 根据条件查找权限集列表_分页
	 */
	public List<PrivilegeSet> findListByCondition(PageResult<PrivilegeSet> pr,PrivilegeSet privilegeSet);
	
	/**
	 * 根据条件查找权限集列表
	 */
	public List<PrivilegeSet> findListByCondition(PrivilegeSet privilegeSet);
	
	/**
	 * 根据条件查找权限集数量
	 */
	public int findCountByConditionForHQL(PrivilegeSet privilegeSet);
	
	/**
	 * 查询所有权限集(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<PrivilegeSet> findAll() throws Exception;
	
	/**
	 * 根据ID查询权限集
	 */
	public PrivilegeSet findPrivilegeSetById(int id) throws Exception;
	
	/**
	 * 根据模块ID查询权限集
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<PrivilegeSet> findPrivilegeSetByModularId(int id)throws Exception;
}
