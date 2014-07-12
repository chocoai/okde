package net.cedu.biz.enrollment;

import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;

/**
 * 招生返款政策 业明细务逻辑层实现_招生返款需求重构专用_2012-05-18
 * 
 * @author xiao
 *
 */
public interface ChannelPolicyDetailStandardOverLoadBiz 
{
	
	/**
	 * 根据id查询渠道政策 标准规则
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findChannelPolicyDetailStandardById(int id) throws Exception;
	
	/**
	 * 根据学生和费用科目Id、缴费单明细id字符串集合匹配相关的招生返款标准---招生返款用_____2012-05-24重构
	 * @param student  学生实体
	 * @param feeSubjectId 费用科目
	 * @param fpdIds 缴费单Ids字符串
	 * @param countRebatePeople  第一次招生返款人数
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateReview(Student student,int feeSubjectId,String fpdIds,int countRebatePeople) throws Exception;
	
	/**
	 * 根据缴费单id和招生返款期id、缴费单Ids字符串计算出单个缴费单id招生返款金额_____2012-05-24重构-单院校返款
	 * @param newFpdId 缴费单id
	 * @param oldFpdIds 缴费单Ids字符串
	 * @param channelRebateTimeId 招生返款期Id
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int newFpdId, String oldFpdIds,int channelRebateTimeId) throws Exception;
	
	/**
	 * 根据费单明细Ids字符串和招生返款期id计算招生返款---招生返款用_____2012-05-24重构-单院校返款
	 * 直接更新缴费单明细的返款金额、
	 * 冗余招生返款标准id、招生返款期id和本次招生返款总人数到缴费单明细里面，后面可以绑定到合作方与招生返款标准关系表里（考虑到招生返款单删除，后面标准和前标准不匹配问题）
	 * @param fpdIds
	 * @param channelRebateTimeId
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateReviewByOldFpdIdsChannelRebateTimeId(String fpdIds,int channelRebateTimeId) throws Exception;
	
	/**
	 * 根据缴费单id和招生返款期id、缴费单Ids字符串计算出单个缴费单id招生返款金额_____2012-05-24重构-多院校返款
	 * @param newFpdId 缴费单id
	 * @param oldFpdIds 缴费单Ids字符串
	 * @param channelRebateTimeId 招生返款期Id
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateCommonReviewByNewFpdIdOldFpdIdsChannelRebateTimeId(int newFpdId, String oldFpdIds,int channelRebateTimeId) throws Exception;
	
	/**
	 * 特殊合作方
	 * 
	 * 根据学生和费用科目Id、缴费单明细id字符串集合匹配相关的招生返款标准---招生返款用_____2012-05-26重构
	 * @param student  学生实体
	 * @param feeSubjectId 费用科目
	 * @param fpdIds 缴费单Ids字符串
	 * @param countRebatePeople 第一次返款人数
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicyDetailStandard findCpdsByStudentFeeSubjectIdFpdIdsForChannelRebateSpecialReview(Student student,int feeSubjectId,String fpdIds,int countRebatePeople) throws Exception;
	
	/**
	 * 特殊合作方
	 * 
	 * 根据缴费单id和学生实体、主合作方id、缴费单Ids字符串计算出单个缴费单id招生返款金额
	 * @param newFpdId 缴费单id
	 * @param oldFpdIds 缴费单Ids字符串
	 * @param student 学生实体
	 * @param channelId 合作方Id
	 * @param policeStatus  返款类型：通用、院校
	 * @return
	 * @throws Exception
	 */
	public boolean updateStuFpdChannelRebateSpecialReviewByNewFpdIdOldFpdIdsStudentChannelId(int newFpdId, String oldFpdIds,Student student,int channelId,int policeStatus) throws Exception;
	
	
}
