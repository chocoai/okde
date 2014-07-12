package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.model.page.PageResult;

/**
 * 招生返款政策 业务接口
 * 
 * @author gaolei
 *
 */
public interface ChannelPolicyDetailBiz
{
	/**
	 * 对返回结果进行何种程度的包装(即对外键、主键字段如何处理)
	 * @author Sauntor
	 */
	public enum WrapType
	{
		ToName, //查询并填充名称
		ToEntity //用外键实体填充
	};
	
	/**
	 * 根据条件查询渠道政策 列表
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicyDetail> findChannelPolicyDetailBypolicychannelId(int policychannelId) throws Exception;
	
	
	/**
	 * 根据条件查询渠道政策 明细
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetail findChannelPolicyDetailById(int Id) throws Exception;
	
	/**
	 * 创建招生政策
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int addDetail(ChannelPolicyDetail entity) throws Exception;
	
	/**
	 * 修改审批状态
	 * @param detailId
	 * @param newStatus
	 * @param auditer
	 * @throws Exception
	 */
	public void updateAuditStatus(int detailId, int newStatus, int auditer) throws Exception;
	
	/**
	 * 根据Id查询
	 * 
	 * @param detailId
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetail findById(int detailId) throws Exception;
	
	/**
	 * 批量添加
	 * @param list
	 * @return 冲突政策
	 * @throws Exception
	 */
	public List<ChannelPolicyDetail> addList(List<ChannelPolicyDetail> list) throws Exception;
	
	/**
	 * 更新政策
	 * 
	 * @param detail
	 * @throws Exception
	 */
	public void update(ChannelPolicyDetail detail) throws Exception;
	
	/**
	 * 根据院校、批次、层次、专业、费用项目分页查询合作方招生返款政策
	 * @param channelId 合作方ID
	 * @param branchId 学习中心ID
	 * @param academyId
	 * @param batchId
	 * @param levelId
	 * @param majorId
	 * @param feeSubjectId
	 * @parma result
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicyDetail> findForChannel(int channelId, int branchId, int academyId, int batchId, int levelId, int majorId, int feeSubjectId, int auditStatus, PageResult<ChannelPolicyDetail> result) throws Exception;
	
	/**
	 * 根据院校、批次、层次、专业、费用项目查询合作方招生返款政策总数
	 * @param channelId 合作方ID
	 * @param branchId 学习中心ID
	 * @param academyId
	 * @param batchId
	 * @param levelId
	 * @param majorId
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public int countForChannel(int channelId, int branchId, int academyId, int batchId, int levelId, int majorId, int feeSubjectId,int auditStatus, PageResult<ChannelPolicyDetail> result) throws Exception;
	
	/**
	 * 查询外键表相关数据（ID==>Name,Entity）
	 * @param list
	 * @param wrapTo
	 * @throws Exception
	 */
	public void wrapList(List<ChannelPolicyDetail> list, WrapType wrapTo) throws Exception;
	
	/**
	 * 删除政策
	 * 
	 * @param detailId
	 * @throws Exception
	 */
	public void deleteById(int detailId) throws Exception;
	
	/**
	 * 批量更新审批状态
	 * 
	 * @param ids
	 * @param auditStatus
	 * @param uid
	 * @throws Exception
	 */
	public void updateAuditStatusBatched(int[] ids, int auditStatus,int uid) throws Exception;
	
	/**
	 * 批量更新政策为指定之标准
	 * 
	 * @param ids 政策ID列表
	 * @param policyId 政策标准ID
	 * @param uid 执行当前操作之用户
	 * @throws Exception
	 */
	public void updatePolicyId(int[] ids, int policyId,int uid) throws Exception;
	
	/**
	 * 批量更新
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void updateList(List<ChannelPolicyDetail> list) throws Exception;
	
	/**
	 * 查询适用于某个学生的招生返款政策
	 * @param student
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetail findByStudent(Student student, int feeSubjectId) throws Exception;
	
	/**
	 * 查询通过审批的数量
	 * @param channelId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public int findChannelPolicyDetailBychannelIdAndauditStatus(int channelId,int auditStatus) throws Exception;
	
	/**
	 * 查询审批的数量
	 * @param channelId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public int findChannelPolicyDetailBychannelId(int channelId) throws Exception;
	
	/**
	 * 根据招生返款标准Id和审批状态查询政策数量
	 * @param channelPolicyId
	 * @param auditStatus
	 * @return
	 * @throws Exception
	 */
	public int findChannelPolicyDetailCountByChannelPolicyIdAuditStatus(int channelPolicyId,int auditStatus)throws Exception;
	
	
}
