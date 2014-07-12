package net.cedu.biz.enrollment;

import java.util.List;
import java.util.Set;

import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.model.page.PageResult;

/**
 * 院校返款政策  业务逻辑接口
 * 
 * @author Sauntor
 *
 */
public interface AcademyRebatePolicyBiz
{
	/**
	 * 院校返款政策检索
	 * 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicy> list(AcademyRebatePolicy condition, PageResult<AcademyRebatePolicy> pr) throws Exception;
	
	/**
	 * 按条件查询院校返款政策并返回符合条件的总记录数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int countList(AcademyRebatePolicy condition) throws Exception;
	
	/**
	 * 新建收费政策
	 * 
	 * @param policy
	 * @return 持久化实体的ID
	 * @throws Exception
	 */
	public int addAcademyRebatePolicy(AcademyRebatePolicy policy) throws Exception;
	
	/**
	 * 根据id查询院校返款政策
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicy getAcademyRebatePolicyById(int id) throws Exception;
	
	/**
	 * 根据id删除院校收费政策
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicy deleteAcademyRebatePolicyById(int id) throws Exception;
	
	/**
	 * 修改 院校返款政策
	 * 
	 * @param policy
	 * @throws Exception
	 */
	public AcademyRebatePolicy updateAcademyRebatePolicy(AcademyRebatePolicy policy) throws Exception;
	
	/**
	 * 修改院校返款政策并保存其新的标准准则
	 * 
	 * @param policy
	 * @throws Exception
	 */
	public void updateWithStandards(AcademyRebatePolicy policy) throws Exception;
	
	/**
	 * 查询某个院校可用的返款政策
	 * 
	 * @param academyId 院校ID
	 * @param feeSubjectId 费用项目
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicy> findAvailableForAcademy(int academyId, int feeSubjectId) throws Exception;
	
	/**
	 * 判断某个政策是否存在
	 * @param academyId
	 * @param batchId
	 * @param levelId
	 * @param majorId
	 * @param branchId
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicy isPolicyExist(int academyId, int batchId, int levelId, int majorId, int branchId, int policyId) throws Exception;
}
