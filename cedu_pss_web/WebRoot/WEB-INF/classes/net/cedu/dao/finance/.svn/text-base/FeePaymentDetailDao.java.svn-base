package net.cedu.dao.finance;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;

/**
 * 缴费单明细  数据访问接口
 * @author gaolei
 *
 */
public interface FeePaymentDetailDao extends BaseDao<FeePaymentDetail>
{

	/**
	 * 通过学生ID集合查询学生的缴费信息
	 * @param studentIds
	 * @return key: 学生ID_缴费类型ID   value:缴费金额
	 * @throws Exception
	 */
	public Map<String,Double> findStudentFeePaymentByStudentIds(String studentIds)throws Exception;
	
	/**
	 * 统计需要添加招生返款单的缴费单招生返款总金额（根据缴费单明细ids字符串）
	 * @param fpdIds 缴费单明细ids字符串
	 * @return
	 * @throws Exception
	 */
	public String countFpdAllChannelRebateMoneyByFpdIds(String fpdIds) throws Exception;
	
	/**
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @param stuIds 学生ids字符串
	 * @return
	 * @throws Exception
	 */
	public int countStudCountForChannelAdmissionsByStuIds(String stuIds) throws Exception;
	
	/**
	 * * 特殊合作方
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体）
	 * 暂时只对学费的缴费单明细进行统计
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public int countStudCountForChannelAdmissionsSpecialByStudent(Student student) throws Exception; 
	
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
	 * 缴费金额(Map key:院校ID_学习中心ID value:缴费金额)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param studentDataSource 数据来源
	 * @param way 市场途径
	 * @param enrollmentSource 招生来源
	 * @param branchId 学习中心
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 * @throws Exception
	 */
	public Map<String,Double> findSumPaymentByStudentAndFPD(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate)throws Exception;
	
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
	 * * 2012-05-24 重构
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体和缴费单Ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @param student 学生实体
	 * @param fpdIds 缴费单明细Ids字符串
	 * @return
	 * @throws Exception
	 */
	public int countStuCountForChannelRebateReviewByStuAndFpdIds(Student student,String fpdIds) throws Exception;
	
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
	 * 统计已招生返款的缴费单招生返款总金额
	 * @param feePaymentDetail 缴费单明细实体
	 * @param student 学生实体
	 * @return
	 * @throws Exception
	 */
	public String countFpdAllChannelRebateMoneyForChannelRebateSearch(FeePaymentDetail feePaymentDetail, Student student) throws Exception;
	
	/**
	 * 2012-07-22
	 * 
	 * 查询符合年度院校返款的缴费单明细数量（每种费用科目都不一样）（计算院校年度返款单）
	 * @param feePaymentDetail 缴费单明细
	 * @param student 学生
	 * @param aeblist 招生批次集合
	 * @return
	 * @throws Exception
	 */
	public int findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception;
	
}

