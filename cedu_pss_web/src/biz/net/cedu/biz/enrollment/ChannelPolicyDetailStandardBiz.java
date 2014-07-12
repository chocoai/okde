package net.cedu.biz.enrollment;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;

/**
 * 招生返款政策 标准规则业务接口
 * 
 * @author gaolei
 *
 */
public interface ChannelPolicyDetailStandardBiz
{
	
	/**
	 * 根据条件查询渠道政策 标准规则
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findChannelPolicyDetailStandardchannelId(int channelpolicyId) throws Exception;
	
	/**
	 * 批量添加标准规则
	 * 
	 * @param list
	 * @throws Exception
	 */
	public void addList(List<ChannelPolicyDetailStandard> list) throws Exception;
	
	/**
	 * 查询指定政策标准的所有准则
	 * 
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicyDetailStandard> findByPolicyId(int policyId) throws Exception;
	
	/**
	 * 删除招生政策标准为下所有标准准则
	 * @param policyId
	 * @return
	 * @throws Exception
	 */
	public int deleteByPolicyId(int policyId) throws Exception;
	
	/**
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findByStudentFeeSubjectIdForChannelRebate(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 根据学生、费用科目Id和实缴金额、渠道优惠金额计算出相关的招生返款金额---招生返款用
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目
	 * @param money 实缴金额
	 * @param channelDiscount 渠道优惠金额
	 * @return
	 * @throws Exception
	 */
	public double findChannelRebateByStudentFeeSubjectIdMoney(Student student,int feeSubjectId,BigDecimal money,BigDecimal channelDiscount) throws Exception;
	
	/**
	 * 根据缴费单明细Id计算出相关的招生返款金额---招生返款用
	 * @param feePaymentDetailId 
	 * @return
	 * @throws Exception
	 */
	public double findChannelRebateByfeePaymentDetailId(int feePaymentDetailId) throws Exception;
	
	/**
	 * 根据缴费单明细Id计算出相关的招生返款金额---招生返款用(通用政策)
	 * @param feePaymentDetailId 缴费单明细Id
	 * @return
	 * @throws Exception
	 */
	public double findChannelRebateCommonByfeePaymentDetailId(int feePaymentDetailId) throws Exception;
	
	/**
	 * 根据学生和费用科目Id、主合作方Id、次合作方Id匹配相关的招生返款标准---招生返款用___特殊合作方
	 * @param student 学生实体
	 * @param channelId 合作方Id
	 * @param minorChannelId 次合作方Id
	 * @param feeSubjectId 使用政策类型
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findByStudentFeeSubjectIdForChannelRebateSpecial(Student student,int channelId,int minorChannelId,int feeSubjectId) throws Exception;
	
	/**
	 * 根据学生、费用科目Id和实缴金额、渠道优惠金额、主合作方Id、次合作方Id计算出相关的招生返款金额---招生返款用
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目Id
	 * @param money 实缴金额
	 * @param channelDiscount 渠道优惠金额
	 * @param channelId 主合作方Id
	 * @param minorChannelId 次合作方Id
	 * @return 
	 * @throws Exception
	 */
	public double findChannelRebateSpecialByStudentFeeSubjectIdMoneyChannelId(Student student,int feeSubjectId,BigDecimal money,BigDecimal channelDiscount,int channelId,int minorChannelId) throws Exception;
	
	/**
	 * 根据缴费单明细Id计算出相关的招生返款金额---招生返款用____特殊合作方
	 * @param feePaymentDetailId 缴费单明细Id
	 * @param channelId 主合作方Id
	 * @param minorChannelId 次合作方Id
	 * @param policeStatus 返款政策类型
	 * @return
	 * @throws Exception
	 */
	public double findChannelRebateSpecialByfeePaymentDetailIdChannelIdPolicyStatus(int feePaymentDetailId,int channelId,int minorChannelId,int policeStatus) throws Exception;	
	

	
	/**
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用_____2012-04-05重构
	 * @param student
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdForChannelRebateReview(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 根据原缴费单明细Ids字符串、要添加的缴费单明细id计算招生返款---招生返款用_____2012-04-05重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @param newFpdId
	 * @param oldFpdIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateByNewFpdIdOldFpdIdsReview(int newFpdId, String oldFpdIds) throws Exception;
	
	/**
	 * 根据原缴费单明细Ids字符串、要添加的缴费单明细Ids字符串计算招生返款---招生返款用_____2012-04-05重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @param newFpdIds
	 * @param oldFpdIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateByNewFpdIdsOldFpdIdsReview(String newFpdIds, String oldFpdIds) throws Exception;
	
	/**
	 * 根据原缴费单明细Ids字符串、要移除的缴费单明细Ids字符串移除招生返款---招生返款用_____2012-04-05重构
	 * @param delFpdIds
	 * @param oldFpdIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateByDelFpdIdsOldFpdIdsReview(String delFpdIds, String oldFpdIds) throws Exception;
	
	
	/**
	 *  * 多院校____
	 * 根据所有缴费单明细Ids字符串、要添加的缴费单明细Ids字符串计算招生返款---招生返款用__多院校___2012-04-05重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @param newFpdIds
	 * @param allFpdIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateCommonByNewFpdIdsAllFpdIdsReview(String newFpdIds, String allFpdIds) throws Exception;
	
	/**
	 * * 多院校____
	 * 根据剩下的缴费单明细Ids字符串、要移除的缴费单明细Ids字符串移除招生返款---招生返款用__多院校___2012-04-05重构
	 * @param delFpdIds
	 * @param newFpdIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateCommonByDelFpdIdsNewFpdIdsReview(String delFpdIds, String newFpdIds) throws Exception;
	
	/**
	 * * 特殊合作方
	 * 
	 * 根据学生和费用科目Id匹配相关的招生返款政策---招生返款用_____2012-05-09重构
	 * @param student  学生实体
	 * @param feeSubjectId 费用科目Id
	 * @param policeStatus 匹配政策类型
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetail findCpdByStuFsIdForChannelRebateSpecialReview(Student student,int feeSubjectId,int policeStatus) throws Exception;
	
	/**
	 *  * 特殊合作方
	 * 
	 * 根据学生和费用科目Id匹配相关的招生返款标准---招生返款用_____2012-04-10重构__
	 * @param student  学生实体
	 * @param feeSubjectId 费用科目Id
	 * @param policeStatus 匹配政策类型
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdForChannelRebateSpecialReview(Student student,int feeSubjectId,int policeStatus) throws Exception;
	
	/**
	 * 特殊合作方
	 * 
	 * 根据原缴费单明细Ids字符串、要添加的缴费单明细Ids字符串计算招生返款---招生返款用_____2012-04-10重构_直接更新缴费单明细的返款金额、冗余招生返款标准id到缴费单明细里面，后面可以绑定到学生实体里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @param newFpdIds
	 * @param oldFpdIds
	 * @param policeStatus 匹配政策类型channelId
	 * @param channelId 主合作方Id   匹配政策用
	 * @param minorChannelIds 次合作方Ids  计算招生总人数匹配政策标准用
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateSpecialByNewFpdIdsOldFpdIdsReview(String newFpdIds, String oldFpdIds,int policeStatus,int channelId,String minorChannelIds) throws Exception;
	
	/**
	 * 特殊合作方
	 * 
	 * 根据原缴费单明细Ids字符串、要移除的缴费单明细Ids字符串移除招生返款---招生返款用_____2012-04-10重构
	 * @param delFpdIds
	 * @param oldFpdIds
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateSpecialByDelFpdIdsOldFpdIdsReview(String delFpdIds, String oldFpdIds) throws Exception;
	
	
}
