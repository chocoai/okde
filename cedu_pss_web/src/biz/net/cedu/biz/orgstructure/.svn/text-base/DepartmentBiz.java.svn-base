package net.cedu.biz.orgstructure;

import java.util.List;

import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.orgstructure.Department;
import net.cedu.model.page.PageResult;


public interface DepartmentBiz
{
	
	/**
	 * 添加部门
	 * 
	 * @param branch
	 * @throws Exception
	 */
	public void addNew(Department department) throws Exception;

	/**
	 * 修改部门
	 * 
	 * @param Department
	 * @throws Exception
	 */
	public void modify(Department department) throws Exception;

	/**
	 * 根据ID删除部门
	 * 
	 * @param Department
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception;

	/**
	 * 根据ID查询部门
	 */
	public Department findDepartmentById(int id) throws Exception;
	
	/**
	 * 根据学习中心ID查询集合_综合查询方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Department> findListComposite(Department departments,
			PageResult<Department> pr, BranchEnum branchEnum) throws Exception;
	
	/**
	 * 根据学习中心ID查询条数_综合查询方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public int findCountComposite(Department departments, BranchEnum branchEnum)throws Exception;
	
	/**
	 * 根据条件查找部门
	 */
	public List<Department> findListByCondition(Department department) throws Exception;
	/**
	 * 
	 * @功能：通过用户ID，学习中心ID查询部门集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-5 上午11:23:13
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public List<Department> findDepartmentsByUserIdAndBranchId(int userId,int branchId)throws Exception;
	/**
	 * 
	 * @功能：通过部门IDs获取部门集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-7 下午02:23:18
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param departmentIds
	 * @return
	 * @throws Exception
	 */
	public List<Department> findDepartmentsByDepartmentIds(String departmentIds)throws Exception;
	
}
