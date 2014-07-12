package net.cedu.biz.basesetting;

import java.util.List;

import net.cedu.entity.basesetting.BranchEnrollmentWay;


/**
 * 学习中心授权市场途径业务层接口
 * @author Jack
 *
 */
public interface BranchEnrollmentWayBiz {
	
	/**
	 * 添加学习中心授权市场途径
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(BranchEnrollmentWay branchEnrollmentWay)throws Exception;
	
	/**
	 * 修改学习中心授权市场途径
	 * @param BranchEnrollmentWay
	 * @throws Exception
	 */
	public void modify(BranchEnrollmentWay branchEnrollmentWay)throws Exception;
	
	/**
	 * 根据ID删除学习中心授权市场途径
	 * @param BranchEnrollmentWay
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception;
	
	/**
	 * 根据ID查询学习中心授权市场途径
	 */
	public BranchEnrollmentWay findBranchEnrollmentWayById(int id) throws Exception;
	
	/**
	 * 根据条件查找学习中心授权市场途径数量
	 */
	public int findCountByConditionForHQL(BranchEnrollmentWay branchEnrollmentWay)throws Exception;
	
	/**
	 * 根据条件查找学习中心授权市场途径
	 */
	public List<BranchEnrollmentWay> findListByConditionForHQL(BranchEnrollmentWay branchEnrollmentWay)throws Exception;
	
	/**
	 * 根据角色ID查找学习中心授权市场途径
	 */
	public BranchEnrollmentWay findByBranchId(int branchId)throws Exception;
}
