package net.cedu.biz.enrollment;

import java.util.Collection;
import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.model.page.PageResult;

/**
 * 院校返款政策 明细  业务逻辑接口
 * 
 * @author Sauntor
 *
 */
public interface AcademyRebatePolicyDetailBiz
{
	/**
	 * 根据条件查询所有明细返款政策
	 * @param condition 查询条件
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetail> findByCondition(AcademyRebatePolicyDetail condition,PageResult<AcademyRebatePolicyDetail> pr) throws Exception;

	/**
	 * 条件查询，并包装查询到的列表
	 * 
	 * @param condition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetail> findWrappedARPDByCondition(AcademyRebatePolicyDetail condition, PageResult<AcademyRebatePolicyDetail> pr)  throws Exception;
	
	/**
	 * 根据条件查询政策明细总数
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int countByCondition(AcademyRebatePolicyDetail condition) throws Exception;
	
	/**
	 * 根据明细ID查询明细
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	public AcademyRebatePolicyDetail findPolicyDetailById(int detailId) throws Exception;
	
	/**
	 * 保存
	 * @param entity
	 * @return id
	 * @throws Exception
	 */
	public int addDetail(AcademyRebatePolicyDetail entity) throws Exception;
	
	/**
	 * 更新政策明细
	 * 
	 * @param entity
	 * 
	 * @throws Exception
	 */
	public void updateDetail(AcademyRebatePolicyDetail entity) throws Exception;
	
	/**
	 * 批量更新政策明细
	 * 
	 * @param list 明细列表
	 * 
	 * @throws Exception
	 */
	public void updateList(List<AcademyRebatePolicyDetail> list) throws Exception;
	
	/**
	 * 批量添加政策
	 * 
	 * @param list 批量操作的列表
	 * @return 有冲突的政策
	 * @throws Exception
	 */
	public List<AcademyRebatePolicyDetail> addList(List<AcademyRebatePolicyDetail> list) throws Exception;
	
	/**
	 * 批量更改政策之政策标准
	 * @param detailIds 政策ID列表
	 * @param policyId 政策标准ID
	 * @param updaterId 操作者
	 * @throws Exception
	 */
	public void updateListToStandard(int[] detailIds, int policyId, int updaterId) throws Exception;
	
	/**
	 * 根据ID删除政策明细
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception;
	
	/**
	 * 查询使用与某个学生的院校返款政策
	 * 
	 * @param student
	 * @param feeSubjectId
	 * @param forView 是否是用于查看目的查询，为true时不过滤已标记删除和未启用的政策
	 * @return
	 */
	public AcademyRebatePolicyDetail findForStudnet(Student student, int feeSubjectId, boolean forView) throws Exception;
	
	/**
	 * 批量操作异常
	 * 
	 * @author Sauntor
	 */
	public class BatchOperationException extends RuntimeException
	{
		private static final long serialVersionUID = -3605754156390285109L;
		private String errorMsg;
		private Collection batchSource;
		private Object exceptionSource;
		
		public BatchOperationException(Collection batchSource, Object exceptionSource)
		{
			this.batchSource = batchSource;
			this.exceptionSource = exceptionSource;
		}

		public void setErrorMsg(String errorMsg) {
			this.errorMsg = errorMsg;
		}

		public String getErrorMsg() {
			return errorMsg;
		}

		public Collection getBatchSource() {
			return batchSource;
		}

		public Object getExceptionSource() {
			return exceptionSource;
		}
	}
	
	/**
	 * 根据返款标准Id和审批状态查询政策数量
	 * @param policyId  返款标准Id
	 * @param auditStatus  审批状态
	 * @return
	 * @throws Exception
	 */
	public int findAcademyRebatePolicyDetailCountByPolicyIdAuditStatus(int policyId,int auditStatus)throws Exception;
}
