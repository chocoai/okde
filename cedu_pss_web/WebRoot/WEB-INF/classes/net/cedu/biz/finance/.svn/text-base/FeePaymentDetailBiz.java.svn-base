package net.cedu.biz.finance;

import java.math.BigDecimal;
import java.util.List;

import net.cedu.entity.admin.Branch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;



/**
 * 缴费单明细  业务逻辑接口
 * 
 * @author gaole
 *
 */
public interface FeePaymentDetailBiz {
	
	/**
	 * 按缴费单Id查询缴费单明细
	 * @param feePaymentId
	 * @return
	 */
	public FeePaymentDetail findFeePaymentDetailByFeePaymentId(int feePaymentId)throws Exception;
	
	/**
	 * 根据缴费单Id查询缴费单明细列表
	 * @author xiao
	 * @param feePaymentId 缴费单Id
	 * @return
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByFeePaymentId(int feePaymentId)throws Exception;
	
	/**
	 * 根据收费政策标准明细Id和学生Id查询缴费单明细数量
	 * @param studentId
	 * @param feeStandardId
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentCountByStudentIdFeeStandardId(int studentId,int feeStandardId)throws Exception;
	
	/**
	 * 添加缴费单明细
	 * @param feePaymentDetail
	 * @return
	 * @throws Exception
	 */
	public boolean addFeePaymentDetail(FeePaymentDetail feePaymentDetail) throws Exception;
	
	/**
	 * 查询适用于招生返款的缴费单明细
	 * @param student 查询条件
	 * @param result 分页参数，此接口应实现为<b><em>不分页查询</em></b>
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findForCreatePayBranchChannel(Student student, PageResult<FeePaymentDetail> result) throws Exception;
	
	/**
	 * 根据合作方返款单ID查询已返款缴费单明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findByOrderCeduChannelId(int id) throws Exception;
	
	/**
	 * 根据合作方返款单ID查询已返款缴费单明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFpdListByOrderCeduChannelId(int id) throws Exception;
	
	/**
	 * 根据ID查询缴费单明细
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public FeePaymentDetail findById(int id) throws Exception;
	
	/**
	 * 修改缴费单明细
	 * 
	 * @param fpd
	 * @throws Exception
	 */
	public void modify(FeePaymentDetail fpd) throws Exception;
	
	/**
	 * 修改缴费单明细
	 * @param feePaymentDetail 缴费单明细实体
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeePaymentDetail(FeePaymentDetail feePaymentDetail) throws Exception;
	
	/**
	 * 修改缴费单明细及其对应的退费单明细
	 * @param feePaymentDetail 缴费单明细实体
	 * @param tfd  缴费单明细对应的退费单明细实体
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeePaymentDetailAndTfd(FeePaymentDetail feePaymentDetail,FeePaymentDetail tfd) throws Exception;
	
	/**
	 * 修改缴费单明细集合及其对应的退费单明细集合
	 * @param fpdlist 缴费单明细集合
	 * @param tfdlist 缴费单明细对应的退费单明细集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateFpdListAndTfdList(List<FeePaymentDetail> fpdlist,List<FeePaymentDetail> tfdlist) throws Exception;
	
	/**
	 * 根据费用科目Id和学生Id、缴费单明细状态查询缴费单明细集合
	 * @param studentId 学生Id
	 * @param feeSubjectId 费用科目Id
	 * @param status 缴费单明细状态
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByStudentIdFeeSubjectId(int studentId,int feeSubjectId,int status)throws Exception;
	
	
	/**
	 * 按条件查询缴费单明细数量（分页） (打款单(院校打款(中心)))
	 * @param feePaymentDetail  缴费单明细实体
	 * @param feePayment	缴费单实体
	 * @param student	学生实体
	 * @param pr	分页参数
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByPage(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 按条件查询缴费单明细集合（分页）(打款单(院校打款(中心)))
	 * @param feePaymentDetail 缴费单明细实体
	 * @param feePayment	缴费单实体
	 * @param student	学生实体
	 * @param pr	分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPage(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 按条件查询缴费单明细数量（分页）(打款单(院校打款(总部)))
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByCeduPlaymoneyAcademy(FeePaymentDetail feePaymentDetail,Student student) throws Exception;
	
	/**
	 * 按条件查询缴费单明细集合（分页）(打款单(院校打款(总部)))
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByCeduPlaymoneyAcademy(FeePaymentDetail feePaymentDetail,Student student,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 根据打款单Id查询该打款单下的缴费单明细集合
	 * @param payCeduAcademyId 打款单Id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPayCeduAcademyId(int payCeduAcademyId) throws Exception;
	
	/**
	 *  按条件查询缴费单明细数量（分页）(汇款总部确认（总部）中的(pos直汇总部))
	 * @param feePaymentDetail 缴费单明细实体
	 * @param feePayment	缴费单实体
	 * @param student	学生实体
	 * @param starttime	开始时间
	 * @param endtime	结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByPagePosCedu(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 按条件查询缴费单明细集合（分页）(汇款总部确认（总部）中的(pos直汇总部))
	 * @param feePaymentDetail 缴费单明细实体
	 * @param feePayment	缴费单实体
	 * @param student	学生实体
	 * @param starttime 开始时间
	 * @param endtime	结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPagePosCedu(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 修改缴费单及其明细
	 * @param feePayment 缴费单实体
	 * @param feePaymentDetailList 缴费单明细集合
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeePaymentAndDetailList(FeePayment feePayment,List<FeePaymentDetail> feePaymentDetailList) throws Exception;
	
	/**
	 * 根据费用科目Id和学生Id、缴费单明细状态查询缴费单明细集合(缴费（学费）模块使用)
	 * @param feePaymentDetail 缴费单明细
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByStudentIdFeeSubjectIdStartStatusIdEndStatusId(FeePaymentDetail feePaymentDetail)throws Exception;
	
	/**
	 * 
	 * @功能：通过主键集合查询缴费单明细集合
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-16 下午04:28:09
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailByIds(String ids)throws Exception;
	
	/**
	 * 查询符合院校返款的所有缴费单明细数量（添加院校返款单）（分页）
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByPageForAcademyRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询符合院校返款的所有缴费单明细集合（添加院校返款单）（分页）
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForAcademyRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询需要院校返款的所有缴费单明细集合（添加院校返款单）（分页）
	 * @param feepdIds 缴费单明细ids字符串
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddAcademyRebate(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询院校返款单的所有缴费单明细数量（查看院校返款单）（分页）
	 * @param feePaymentDetail 缴费单明细实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByPageForViewAcademyRebate(FeePaymentDetail feePaymentDetail,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询院校返款单的所有缴费单明细集合（查看院校返款单）（分页）
	 * @param feePaymentDetail 缴费单明细实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForViewAcademyRebate(FeePaymentDetail feePaymentDetail,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 根据院校返款单Id查询缴费单明细列表
	 * @param orderAcademyCeduId  院校返款单Id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByOrderAcademyCeduId(int orderAcademyCeduId)throws Exception;
	
	/**
	 * 根据院校年度返款单Id查询缴费单明细列表
	 * @param orderAcademyCeduId 院校年度返款单Id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFpdListByOrderAcademyCeduIdForAcademyYearRebate(int orderAcademyCeduId)throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public int findFeePaymentDetailCountByPageForChannelRebate(FeePaymentDetail feePaymentDetail,Student student,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForChannelRebate(FeePaymentDetail feePaymentDetail,Student student,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单）（分页）
	 * @param feepdIds 缴费单明细ids字符串
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddChannelRebate(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单）（分页）通用政策
	 * @param feepdIds 缴费单明细ids字符串
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddChannelRebateCommon(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询需要汇款总部的所有缴费单明细集合（添加汇款总部）（分页）
	 * @param feepdIds 缴费单明细ids字符串
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFeePaymentDetailListByPageForNowAddOrderBranchCedu(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_特殊合作方）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail  缴费单明细实体
	 * @param student 学生实体
	 * @param minorChannelType 次合作方类型
	 * @param minorChannelId 次合作方Id
	 * @return
	 * @throws Exception
	 */
	public int findFpdCountByPageForChannelRebateSpecial(FeePaymentDetail feePaymentDetail,Student student,int minorChannelType,int minorChannelId) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_特殊合作方）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param minorChannelType 次合作方类型
	 * @param minorChannelId 次合作方Id
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFpdListByPageForChannelRebateSpecial(FeePaymentDetail feePaymentDetail,Student student,int minorChannelType,int minorChannelId,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单_特殊合作方）（分页）
	 * @param feepdIds 缴费单明细Id字符串集合
	 * @param channelId 主合作方Id
	 * @param minorChannelId 次合作方Id
	 * @param policeStatus 返款政策类型
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForNowAddChannelRebateSpecial(String feepdIds,int channelId, int minorChannelId, int policeStatus, PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_重构_2012-04-05）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param feepdIds 已经选择过的缴费单明细ids字符串集合
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByPageForChannelRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_重构_2012-04-05）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @param feepdIds 已经选择过的缴费单明细ids字符串集合
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询需要招生返款的所有缴费单明细集合（添加招生返款单_重构_2012-04-05）（分页）
	 * @param feepdIds
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRebateAcademyNeedReview(String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	
	
	
	
	/**
	 * 通过缴费单明细匹配该合作方招生总人数（必须符合: i.已缴学费_总部或院校已确认 ii.已开课 iii.已招生途径复核、）__重构
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByChannelAllPeopleCount(Student student) throws Exception;
	
	/**
	 * * 特殊合作方
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体）
	 * 暂时只对学费的缴费单明细进行统计
	 * （必须符合: i.已缴学费_总部或院校已确认 ii.已开课 iii.已招生途径复核、）__重构
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public int countStudCountForChannelAdmissionsSpecialByStudent(Student student) throws Exception; 
	
	
	/**
	 * 指定缴费单id字符串集合，查处属于某个学生的缴费单数量
	 * @param feepdIds 缴费单id字符串集合
	 * @param stuId 学生Id
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByFpdIdsAndStuId(String feepdIds,int stuId) throws Exception;
	
	
	/**
	 * 指定缴费单id字符串集合，查处属于某个学生的缴费单列表
	 * @param feepdIds 缴费单id字符串集合
	 * @param stuId 学生Id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByFpdIdsAndStuId(String feepdIds,int stuId) throws Exception;
	
	/**
	 * 统计需要添加招生返款单的缴费单招生返款总金额（根据缴费单明细ids字符串）
	 * @param fpdIds 缴费单明细ids字符串
	 * @return
	 * @throws Exception
	 */
	public String countFpdAllChannelRebateMoneyByFpdIds(String fpdIds) throws Exception;
	
	/**
	 * 统计(pos直汇总部)金额
	 * @param feePaymentDetail 缴费单明细
	 * @param feePayment 缴费单
	 * @param student 学生
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public String countFpdAllMoneyForPOSEbank(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime) throws Exception;
	
	/**
	 * 统计(pos直汇院校金额)金额
	 * @param feePaymentDetail 缴费单明细
	 * @param feePayment 缴费单
	 * @param student 学生
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public String countFpdPayAcademyMoneyForPOSEbank(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime) throws Exception;
	
	
	/**
	 * * 特殊合作方
	 * 
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_重构2012-04-09）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生实体
	 * @param feepdIds 选中的缴费单Ids字符串
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByPageForChannelRebateSpecialReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime) throws Exception;
	
	
	/**
	 *  * 特殊合作方
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_重构2012-04-09）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生实体
	 * @param feepdIds 选中的缴费单Ids字符串
	 * @param starttime 开始时间
	 * @param endtime 结束时间
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRebateSpecialReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,String starttime,String endtime,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 2012-05-08 重构
	 * 根据缴费单明细统计满足条件的学生个数_院校返款匹配院校返款标准用（根据学生实体）
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目
	 * @return
	 * @throws Exception
	 */
	public int countStudCountForAcademyRebateByStudent(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 2012-07-04 重构
	 * 根据缴费单明细统计满足条件的学生个数_院校返款匹配院校返款标准用（根据学生实体）
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目
	 * @param fpdIds  缴费单Id字符串集合
	 * @return
	 * @throws Exception
	 */
	public int countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(Student student,int feeSubjectId,String fpdIds) throws Exception;
	
	/**
	 * 统计需要添加院校返款单的缴费单院校返款总金额（根据缴费单明细ids字符串）
	 * @param fpdIds
	 * @return
	 * @throws Exception
	 */
	public String countFpdAllAcademyRebateMoneyByFpdIds(String fpdIds) throws Exception;
	
	/**
	 * 2012-05-21重构
	 * 暂时用于显示符合条件的招生返款单查询
	 * 
	 * 查询符合招生返款的所有缴费单明细数量（添加院校招生返款单_重构）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail   缴费单明细实体
	 * @param student	学生实体
	 * @param feepdIds   已选择过的缴费单明细Ids字符串集合
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByPageForChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds) throws Exception;
	
	/**
	 * 2012-05-21重构
	 * 暂时用于显示符合条件的招生返款单查询
	 * 
	 * 查询符合招生返款的所有缴费单明细集合（添加院校招生返款单_重构）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail   缴费单明细实体
	 * @param student	学生实体
	 * @param feepdIds   已选择过的缴费单明细Ids字符串集合
	 * @param pr	分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细数量（添加招生返款单_重构_2012-05-24）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail   缴费单明细实体
	 * @param student	学生实体
	 * @param feepdIds   已选择过的缴费单明细Ids字符串集合
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByPageForChannelRecruitRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds) throws Exception;
	
	/**
	 * 查询符合招生返款的所有缴费单明细集合（添加招生返款单_重构_2012-05-24）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail   缴费单明细实体
	 * @param student	学生实体
	 * @param feepdIds   已选择过的缴费单明细Ids字符串集合
	 * @param pr	分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRecruitRebateAcademyReview(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 *  * 2012-05-24重构
	 * 
	 * 通过缴费单明细匹配该合作方招生总人数（必须符合: i.已缴学费_总部或院校已确认 ii.已开课 iii.已招生途径复核、）__重构
	 * @param student 学生实体
	 * @param fpdIds 缴费单明细ids字符串
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByChannelRebateReviewAllPeopleCount(Student student,String fpdIds) throws Exception;
	
	/**
	 * * 2012-05-26 重构   跨中心合作方返款
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体和缴费单Ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @param student 学生实体
	 * @param fpdIds 缴费单明细Ids字符串
	 * @return
	 * @throws Exception
	 */
	public int countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(Student student,String fpdIds) throws Exception;
	
	
	/**
	 *  2012-05-26 重构    招生返款后续功能
	 *  如果学生有招生返款单则不让操作来源复核
	 * @param studentId  学生Id
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountForChannelTypeChecked(int studentId) throws Exception;
	
	/**
	 * * 2012-06-07 重构  
	 * 不符合未返款查询数量
	 * 查询不符合招生返款的所有缴费单明细数量（招生返款单未提交查询_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @param feePaymentDetail  缴费单明细实体
	 * @param student	学生实体
	 * @param feepdIds	缴费单明细ids字符串
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByPageForAllNotChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds) throws Exception;
	
	/**
	 * * 2012-06-07 重构  
	 * 不符合未返款查询集合
	 * 查询不符合招生返款的所有缴费单明细集合（招生返款单未提交查询_重构）（分页）-------暂时只对学费进行招生返款
	 * 
	 * @param feePaymentDetail  缴费单明细实体
	 * @param student	学生实体
	 * @param feepdIds	缴费单明细ids字符串
	 * @param pr	分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForAllNotChannelRecruitRebate(FeePaymentDetail feePaymentDetail,Student student,String feepdIds,PageResult<FeePaymentDetail> pr) throws Exception;
	
	
	/**
	 * 2012-06-11	用于显示已招生返款单查询
	 * 查询已招生返款的所有缴费单明细数量（查询招生返款缴费单_重构）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail  缴费单明细实体
	 * @param student	学生实体
	 * @return
	 * @throws Exception
	 */
	public int findfpdCountByPageForChannelRecruitRebateSearch(FeePaymentDetail feePaymentDetail,Student student) throws Exception;
	
	/**
	 *  2012-06-11	用于显示已招生返款单查询
	 * 查询已招生返款的所有缴费单明细集合（查询招生返款缴费单_重构）（分页）-------暂时只对学费进行招生返款
	 * @param feePaymentDetail  缴费单明细实体
	 * @param student	学生实体
	 * @param pr	分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByPageForChannelRecruitRebateSearch(FeePaymentDetail feePaymentDetail,Student student,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 统计已招生返款的缴费单招生返款总金额
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public String countFpdAllChannelRebateMoneyForChannelRebateSearch(FeePaymentDetail feePaymentDetail, Student student) throws Exception;
	
	/**
	 * 2012-07-06 重构
	 * 
	 * 根据需要添加院校返款单的缴费单追加其他已经院校返款的缴费单金额(包括已填打款单的缴费单明细)（根据缴费单明细ids字符串）
	 * @param fpdIds  需要返款的缴费单ids字符串
	 * @return
	 * @throws Exception
	 */
	public String countFpdAddAcademyRebateMoneyByFpdIds(String fpdIds) throws Exception;
	
	/**
	 * 2012-07-06	用于查询已添加院校返款单之后的缴费单明细
	 * @param student 学生实体
	 * @param feeSubjectId 费用科目Id
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findfpdListByAcademyIdBatchIdFeeSubjectIdForAcademyRebateAddMoney(Student student,int feeSubjectId) throws Exception;
	
	/**
	 * 2012-07-10	
	 * 根据缴费单明细Ids查询学习中心列表
	 * @param fpdIds 缴费单明细Ids字符串
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findBranchListByFpdIds(String fpdIds) throws Exception;
	
	/**
	 * 2012-07-22
	 * 查询符合年度院校返款的所有缴费单明细数量（添加院校年度返款单）（分页）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次列表
	 * @return
	 * @throws Exception
	 */
	public int findFpdCountByPageForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
	/**
	 * 2012-07-22
	 * 查询符合年度院校返款的所有缴费单明细数量（添加院校年度返款单）（分页）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次列表
	 * @return
	 * @throws Exception
	 */
	public int findFpdCountByPageForAcademyYearRebateLoad(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
	/**
	 * 2012-07-22
	 * 查询符合年度院校返款的所有缴费单明细集合（添加院校年度返款单）（分页）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次列表
	 * @param pr 分页参数
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFpdlListByPageForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist,PageResult<FeePaymentDetail> pr) throws Exception;
	
	/**
	 * 2012-07-22
	 * 
	 * 查询符合年度院校返款的缴费单明细数量（每种费用科目都不一样）（计算院校年度返款单）（分页）
	 * @param feePaymentDetail  缴费单明细实体
	 * @param student 学生实体
	 * @param aeblist 招生批次集合
	 * @return
	 * @throws Exception
	 */
	public int findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
	/**
	 * 2012-07-22	
	 * 查询学习中心列表（年度返款用）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次列表
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findBranchListByFpdStuAebList(FeePaymentDetail feePaymentDetail ,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
	/**
	 * 2012-07-23
	 * 统计符合年度院校返款的所有缴费单明细集合的追加金额总和（添加院校年度返款单）（分页）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次列表
	 * @return
	 * @throws Exception
	 */
	public BigDecimal updateFpdForAcademyYearRebateCountAllAddMoney(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
	/**
	 * 2012-07-23
	 * 查询符合年度院校返款的所有缴费单明细集合的追加金额总和（添加院校年度返款单）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次列表
	 * @return
	 * @throws Exception
	 */
	public List<FeePaymentDetail> findFpdListForAddAcademyYearRebateCountAllAddMoney(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
	/**
	 * 根据学生Id缴费单为作废的数量
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public int findFpdCountByStudentIdStatus(int studentId)throws Exception;
	
}
