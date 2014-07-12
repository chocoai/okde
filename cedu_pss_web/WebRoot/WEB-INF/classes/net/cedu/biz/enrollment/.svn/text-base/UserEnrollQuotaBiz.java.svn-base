package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.UserEnrollQuota;
import net.cedu.model.page.PageResult;

/**
 * 中心人员指标  业务逻辑接口
 * 
 * @author gaolei
 *
 */
public interface UserEnrollQuotaBiz {
	

	/**
	 *  添加中心人员指标
	 * @param batchId
	 * @param branchId
	 * @param academyId
	 * @param target
	 * @param completed
	 * @param userId
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
	public boolean addUserEnrollQuota(int batchId,int branchId,int academyId,int target,int completed,int userId,int managerId)throws Exception;
	
	
	
	/**
	 * 删除中心人员指标
	 * @param batchId
	 * @param branchId
	 * @param academyId
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public  boolean deleteUserEnrollQuotaBybranchIdAndAcademyId(int batchId,int branchId,int academyId,int userId)throws Exception;
	

	
	/**
	 * 查询中心人员分配指标按批次
	 * @param batchId
	 * @param branchId
	 * @param pr
	 * @return
	 */
	public List<User> findUserEnrollQuotaByBatchIdAndBranchId(int batchId,int branchId,PageResult<User> pr)throws Exception;
	
	
	
	/**
	 * 查询中心人员指标按院校Id用户Id
	 * @param academy
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public UserEnrollQuota findUserEnrollQuotaByAcademyId(int academy,int userId)throws Exception;
	
	/**
	 * 查询中心人员指标按院校Id用户Id中心Id批次id
	 * @param academy
	 * @param userId
	 * @param branchId
	 * @return
	 * @throws Exception
	 */
	public UserEnrollQuota findUserEnrollQuotaByAcademyId(int academy,
			int userId,int branchId, int batchId) throws Exception;
	
	/**
	 * 查询中心人员分配指标按批次用户Id
	 * @param batchId
	 * @param userId
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<User> findUserEnrollQuotaByBatchIdAndUserId(int batchId,int userId, PageResult<User> pr)throws Exception;;
	
	/**
	 * 查询中心人员指标总数按用户Id(并且按用户所属中心id)
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public int findUserEnrollQuotaSumByUserId(int userId)throws Exception;

	/**
	 * @功能：通过条件查询中心人员指标集合(删除用户功能的查询方法)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-11 上午10:40:54
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param userEnrollQuota
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public List<UserEnrollQuota> findUserEnrollQuotaListByParams(UserEnrollQuota userEnrollQuota,int count) throws Exception;
	
	/**
	 * @功能：通过条件删除中心人员指标(删除用户功能用的方法)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-06-11 下午15:04:17
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param userEnrollQuota
	 * @throws Exception
	 */
	public void deleteUserEnrollQuotaByParams(UserEnrollQuota userEnrollQuota) throws Exception;
}
