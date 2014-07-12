package net.cedu.biz.admin.privilege;

import java.util.List;

import net.cedu.entity.admin.privilege.SubPrivilege;
import net.cedu.model.page.PageResult;


/**
 * 权限子项 业务层接口
 * @author Jack
 *
 */
public interface SubPrivilegeBiz 
{	
	/**
	 * 添加权限子项
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(SubPrivilege subPrivilege)throws Exception;
	
	/**
	 * 修改权限子项
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void modify(SubPrivilege subPrivilege)throws Exception;
	
	/**
	 * 根据ID删除权限子项
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void deleteById(int id)throws Exception;
	
	/**
	 * 根据条件查找权限子项数量
	 */
	public int findCountByConditionForHQL(SubPrivilege subPrivilege) throws Exception;
	
	/**
	 * 根据条件查找用户列表_分页
	 */
	public List<SubPrivilege> findListByCondition(PageResult<SubPrivilege> pr,SubPrivilege subPrivilege);
	
	/**
	 * 根据条件查找权限子项列表
	 */
	public List<SubPrivilege> findListByCondition(SubPrivilege subPrivilege);
	
	/**
	 * 根据权限ID查找显示的权限子项列表
	 * @param privilegeId 权限ID
	 * 
	 */
	public List<SubPrivilege> findViewListByPrId(int privilegeId);
	
	/**
	 * 查询所有权限子项(包含停用的)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SubPrivilege> findAll() throws Exception;
	
	/**
	 * 根据ID查询权限子项
	 */
	public SubPrivilege findSubPrivilegeById(int id) throws Exception;
}
