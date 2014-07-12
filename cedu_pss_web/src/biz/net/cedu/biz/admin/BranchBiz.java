package net.cedu.biz.admin;

import java.util.List;

import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;


/**
 * 学习中心 业务层接口
 * @author Jack
 *
 */
public interface BranchBiz 
{	
	
	/**
	 * 添加学习中心
	 * @param branch
	 * @throws Exception
	 */
	public void addNew(Branch branch)throws Exception;
	
	/**
	 * 修改学习中心
	 * @param branch
	 * @throws Exception
	 */
	public void modify(Branch branch)throws Exception;
	
	/**
	 * 根据ID删除学习中心
	 * @param branch
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 根据ID查询学习中心
	 */
	public Branch findBranchById(int id) throws Exception;
	
	/**
	 * 根据条件查找学习中心
	 */
	public List<Branch> findListByCondition(Branch branch) throws Exception;
	
	/**
	 * 查询学习中心集合(供其他模块调用)
	 * 
	 * @return 返回不包括总部的其他所有正常数据的学习中心
	 * @throws Exception
	 */
	public List<Branch> findBranchForModel() throws Exception;
	
	/**
	 * 根据学习中心ID查询其及下属学习中心集合
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findListById(int id)throws Exception;

	/**
	 * 查询区域经理没有分配的学习中心
	 * @param branchIds
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findAllNotInIds(String branchIds) throws Exception;
	
	
	/**
	 * 查询学习中心按照多个Id
	 * @param branchIds 学习中心Ids
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findAllByIds(String branchIds) throws Exception;
	
	
	/**
	 * 获取学习中心Ids
	 * 
	 */
	public Long[] findBranchIds(PageParame p);
	
	/**
	 * 根据学习中心ID查询条数_综合查询方法
	 * @return
	 * @throws Exception
	 */
	public int findCountComposite(int id,Branch branchs,BranchEnum branchEnum)throws Exception;
	
	/**
	 * 根据学习中心ID查询集合_综合查询方法
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findListComposite(int id,Branch branchs,PageResult<Branch> pr,BranchEnum branchEnum)throws Exception;
	
	/**
	 * 查询学习中心通过名称
	 * @param Name
	 * @return
	 * @throws Exception
	 */
	public Branch findBranchByName(String name)throws Exception;
	
	/**
	 * 查询所有学习中心(未删除)
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findBranchAllByDeleteFlag()throws Exception;
	
	
}
