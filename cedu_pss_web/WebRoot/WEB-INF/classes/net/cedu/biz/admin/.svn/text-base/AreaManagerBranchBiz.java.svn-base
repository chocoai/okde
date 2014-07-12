package net.cedu.biz.admin;

import java.util.List;

import net.cedu.entity.admin.AreaManagerBranch;
import net.cedu.entity.admin.Branch;

/**
 * 区域经理学习中心业务层接口
 * 
 * @author gaolei
 * 
 */
public interface AreaManagerBranchBiz {

	/**
	 * 查询区域经理学习中心按区域经理Id
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public List<AreaManagerBranch> findAreaManagerBranchByManagerId(int managerId) throws Exception;

	
	/**
	 * 查询区域经理学习中心按主键Id
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AreaManagerBranch findAreaManagerBranchById(int id) throws Exception;
	
	/**
	 * 添加区域经理学习中心
	 * @param managerId
	 * @param branchIds
	 * @return
	 * @throws Exception
	 */
	public boolean addAreaManagerBranch(int managerId,Object[] branchIds)throws Exception;
	
	
	/**
	 * 删除区域经理所分配学习中心
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAreaManagerBranchByManagerId(int managerId)throws Exception;
	
	/**
	 * 查询某个区域经理所关联的所有学习中心
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findBranchListByManagerId(int managerId) throws Exception;
	
}
