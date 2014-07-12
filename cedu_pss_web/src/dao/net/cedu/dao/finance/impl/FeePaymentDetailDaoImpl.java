package net.cedu.dao.finance.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyBatchFeeSubject;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *  缴费单明细  数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class FeePaymentDetailDaoImpl extends BaseMDDaoImpl<FeePaymentDetail> implements FeePaymentDetailDao
{
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;

	/**
	 * 通过学生ID集合查询学生的缴费信息
	 * 
	 * edited by dongminghao 2012-07-03 16:28:44
	 * 增加状态筛选（总部已确认之后，可返款状态）
	 */
	public Map<String, Double> findStudentFeePaymentByStudentIds(
			String studentIds) throws Exception {
		Map<String,Double> map =new HashMap<String,Double>();
		String sql="select  student_id,fee_subject_id,sum(amount_paied+recharge_amount-refund_amount) as money_  from tb_e_fee_payment_detail  where student_id in ("+studentIds+") "
					+" and status >= " + Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN+" "
					+" and rebate_status >= " + Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN+" "
					+" group by student_id,fee_subject_id";
		@SuppressWarnings("unchecked")
		List<Map<String,Double>> list = super.getJdbcTemplatePlus().query(sql,
				new RowMapper() {
					public Map<String,Double> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,Double> map =new HashMap<String,Double>();
						map.put(resultSet.getInt("student_id")+"_"+resultSet.getInt("fee_subject_id"), resultSet.getDouble("money_"));
						return map;
					}
		});
		if(list!=null){
			for (Map<String, Double> map2 : list) {
				for(Map.Entry<String, Double> entry:map2.entrySet()){
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return map;
	}
	
	/*
	 * 统计需要添加招生返款单的缴费单招生返款总金额（根据缴费单明细ids字符串）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countFpdAllChannelRebateMoneyByFpdIds(java.lang.String)
	 */
	public String countFpdAllChannelRebateMoneyByFpdIds(String fpdIds) throws Exception 
	{
		if(fpdIds!=null && !fpdIds.equals(""))
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql="select IFNULL(sum(payment_by_channel),0) as money from tb_e_fee_payment_detail where id in ("+fpdIds+") ";
			
			String allmoney =(String)jt.queryForObject(sql, java.lang.String.class);
			if(allmoney==null)
			{
				return "0";
			}
			return allmoney;
		}
		else
		{
			return "0";
		}
		
	}
	
	/*
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countStudCountForChannelAdmissionsByStuIds(java.lang.String)
	 */
	public int countStudCountForChannelAdmissionsByStuIds(String stuIds) throws Exception 
	{
		int count=0;
		if(stuIds!=null && !stuIds.equals(""))
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where rebate_status>="+Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN
				+"  and fee_subject_id="+FeeSubjectEnum.TuitionFee.value()
				+"  and student_id in ( "+stuIds+" ) ";
			
			count =jt.queryForInt(sql);	
		}
		return count;
		
	}
	
	/*
	 * * 特殊合作方
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体）
	 * 暂时只对学费的缴费单明细进行统计
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countStudCountForChannelAdmissionsSpecialByStudent(net.cedu.entity.crm.Student)
	 */
	public int countStudCountForChannelAdmissionsSpecialByStudent(Student student) throws Exception 
	{
		int count=0;
		if(student!=null)
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where rebate_status>="+Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN
				+"  and fee_subject_id="+FeeSubjectEnum.TuitionFee.value()
				+"  and student_id in ( "+findStuSqlForChannelSpecialReview(student)+" ) ";
			
			count =jt.queryForInt(sql);	
		}
		return count;
		
	}
	
	/**
	 * 查询学生Sql集合(匹配该合作方的人数)__重构__特殊合作方
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStuSqlForChannelSpecialReview(Student student) throws Exception 
	{
		// 查询学生Sql
		String stuHql = "select id from tb_e_student where 1=1 ";
		if (student != null)
		{			
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channel_id in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channel_id ="+student.getChannelId();
			}
			// 院校
			if (student.getAcademyId() > 0) 
			{
				stuHql += "and academy_id="+student.getAcademyId();
			}
			// 批次
			if (student.getEnrollmentBatchId() > 0)
			{
				stuHql += " and  enrollment_batch_id= "+student.getEnrollmentBatchId();
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollment_batch_id in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuition_status >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			
			stuHql += " and  is_start_course= "+Constants.STU_IS_START_COURSE_TRUE;
			
			stuHql += " and  is_channel_type_checked= "+Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE;
					
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) 
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+ student.getStartStatusId() + " and status<"+ student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * 统计(pos直汇总部)金额
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countFpdAllMoneyForPOSEbank(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	public String countFpdAllMoneyForPOSEbank(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime) throws Exception
	{
		
		String sql = "select IFNULL(sum(amount_paied+recharge_amount),0) from tb_e_fee_payment_detail where 1=1 ";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) 
			{
				sql += " and status =? ";
				list.add(feePaymentDetail.getStatus());
			}
			//缴费单起止状态判断
			if (feePaymentDetail.getStartStatusId() == 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				sql += " and  status <=? ";
				list.add(feePaymentDetail.getEndStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() == 0) 
			{
				sql += " and  status >=? ";
				list.add(feePaymentDetail.getStartStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				sql += " and  status>= ?  and status<= ? ";
				list.add(feePaymentDetail.getStartStatusId());
				list.add(feePaymentDetail.getEndStatusId());
			}
			//缴费科目
			if (feePaymentDetail.getFeeSubjectId()!=0) 
			{
				sql += " and  fee_subject_id =? ";
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//缴费开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				sql += " and  created_time >=? ";
				list.add(starttime);
			}
			//缴费结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				sql += " and  created_time <=? ";
				list.add(endtime);
			}				
		}
		//缴费单
		String feepaymentSql = getFeePaymentSql(feePayment,student);
		if(feepaymentSql!=null && !feepaymentSql.equals(""))
		{
			if(feepaymentSql!="select id from tb_e_fee_payment where 1=1 ")
			{
				sql += " and fee_payment_id in ("+feepaymentSql+")";
			}
		}
		String allmoney =(String)super.getJdbcTemplatePlus().queryForObject(sql, list.toArray(), java.lang.String.class);
		if(allmoney==null)
		{
			return "0";
		}
		return allmoney;
		
	}
	
	/*
	 * 统计(pos直汇院校金额)金额
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countFpdPayAcademyMoneyForPOSEbank(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.finance.FeePayment, net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	public String countFpdPayAcademyMoneyForPOSEbank(FeePaymentDetail feePaymentDetail,FeePayment feePayment,Student student,String starttime,String endtime) throws Exception
	{
		String sql = "select IFNULL(sum(money_to_pay-academy_discount-academy_cedu_discount),0) from tb_e_fee_payment_detail where 1=1 ";
		List<Object> list = new ArrayList<Object>();
		if (feePaymentDetail != null)
		{						
			// 缴费单状态
			if (feePaymentDetail.getStatus() != 0) 
			{
				sql += " and status =? ";
				list.add(feePaymentDetail.getStatus());
			}
			//缴费单起止状态判断
			if (feePaymentDetail.getStartStatusId() == 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				sql += " and  status <=? ";
				list.add(feePaymentDetail.getEndStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() == 0) 
			{
				sql += " and  status >=? ";
				list.add(feePaymentDetail.getStartStatusId());
			}
			if (feePaymentDetail.getStartStatusId() > 0 && feePaymentDetail.getEndStatusId() > 0) 
			{
				sql += " and  status>= ?  and status<= ? ";
				list.add(feePaymentDetail.getStartStatusId());
				list.add(feePaymentDetail.getEndStatusId());
			}
			//缴费科目
			if (feePaymentDetail.getFeeSubjectId()!=0) 
			{
				sql += " and  fee_subject_id =? ";
				list.add(feePaymentDetail.getFeeSubjectId());
			}
			//缴费开始时间
			if (starttime!=null && !starttime.equals("")) 
			{
				sql += " and  created_time >=? ";
				list.add(starttime);
			}
			//缴费结束时间
			if (endtime!=null && !endtime.equals("")) 
			{
				sql += " and  created_time <=? ";
				list.add(endtime);
			}				
		}
		//缴费单
		String feepaymentSql = getFeePaymentSql(feePayment,student);
		if(feepaymentSql!=null && !feepaymentSql.equals(""))
		{
			if(feepaymentSql!="select id from tb_e_fee_payment where 1=1 ")
			{
				sql += " and fee_payment_id in ("+feepaymentSql+")";
			}
		}
		String allmoney =(String)super.getJdbcTemplatePlus().queryForObject(sql, list.toArray(), java.lang.String.class);
		if(allmoney==null)
		{
			return "0";
		}
		return allmoney;
		
	}
	
	/*
	 * 获取查询缴费单Sql语句(in堆栈溢出解决方案)
	 */
	private String getFeePaymentSql(FeePayment feePayment,Student student)
	{
		String sql = "select id from tb_e_fee_payment where 1=1 ";
		if(feePayment!=null)
		{
			// 缴费方式
			if (feePayment.getFeeWayId() != 0) 
			{
				sql += " and fee_way_id = " + feePayment.getFeeWayId();
			}
			// 缴费单号
			if (feePayment.getCode() != null && feePayment.getCode().length()>0) 
			{
				sql += " and code = '" + feePayment.getCode() + "'";
			}
			if(student!=null)
			{
				String stuSql = "select id from tb_e_student where 1=1 ";
				// 姓名
				if (student.getName() != null && !student.getName().equals("")) {
					stuSql += " and name like '%"+student.getName()+"%'";
				}
				// 证件号
				if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
					stuSql += " and cert_no like '%"+student.getCertNo()+"%'";
				}
				//招生途径enrollmentSource
				if (student.getEnrollmentSource() != 0) {
					stuSql += " and enrollment_source = " + student.getEnrollmentSource();
				}
				//合作方channelId
				if (student.getChannelId() != 0) {
					stuSql += " and channel_id = " + student.getChannelId();
				}
				// 院校
				if (student.getAcademyId() != 0) {
					stuSql += " and academy_id= " + student.getAcademyId();
				}
				// 批次
				if (student.getEnrollmentBatchId() != 0) {
					stuSql += " and  enrollment_batch_id= " + student.getEnrollmentBatchId();
				}
				//多批次
				if(student.getAcademyenrollbatchName()!=null && !"".equals(student.getAcademyenrollbatchName()))
				{
					stuSql += " and  enrollment_batch_id in (" + student.getAcademyenrollbatchName() + ")";
				}
				// 层次
				if (student.getLevelId() != 0) {
					stuSql += " and  level_id= " + student.getLevelId();
				}
				// 专业
				if (student.getMajorId() != 0) {
					stuSql += " and  major_id= " + student.getMajorId();
				}
				// 学习中心
				if (student.getBranchId() != 0) {
					stuSql += " and  branch_id= " + student.getBranchId();
				}
				// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
				// 如果起始状态ID>0,结束状态ID=0;则为无穷大
				// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
				// 如果都大于0,则取交集
				if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
					stuSql += " and  status <" + student.getEndStatusId();
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
					stuSql += " and  status >" + student.getStartStatusId();
				}
				if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
					stuSql += " and  status> " + student.getStartStatusId()
							+ " and status< " + student.getEndStatusId();
				}
				if(stuSql!="select id from tb_e_student where 1=1 ")
				{
					sql += " and student_id in ("+stuSql+")";
				}
			}
		}
		else
		{
			return null;
		}
		return sql;
	}

	/*
	 * 缴费金额(Map key:院校ID_学习中心ID value:缴费金额)
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#findSumPaymentByStudentAndFPD(int, int, int, int, int, int, java.util.Date, java.util.Date)
	 */
	public Map<String, Double> findSumPaymentByStudentAndFPD(int school,
			int gbatch, int studentDataSource, int way, int enrollmentSource,
			int branchId, Date startDate, Date endDate) throws Exception {
		Map<String,Double> map =new HashMap<String,Double>();
		String sql = "select "
					+" s.academy_id,"
					+" s.branch_id,"
					+" sum((f.amount_paied+f.recharge_amount-f.refund_amount)) as fsum "
					+"from "
					+" tb_e_fee_payment_detail as f,tb_e_student as s "
					+"where "
					+" f.student_id = s.id "
					+" and f.fee_subject_id = "+FeeSubjectEnum.TuitionFee.value()+" "
					+" and f.types = "+Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE+" "
					+" and f.status >= "+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN+" "
					+" and f.delete_flag = "+Constants.DELETE_FALSE+" ";
		if(school>0)
		{
			sql +=" and s.academy_id = "+school+" ";
		}
		if(gbatch>0)
		{
			sql +=" and s.global_batch = "+gbatch+" ";
		}
		if(studentDataSource>0)
		{
			sql +=" and s.student_data_source = "+studentDataSource+" ";
		}
		if(way>0)
		{
			sql +=" and s.enrollment_way = "+way+" ";
		}
		if(enrollmentSource>0)
		{
			sql +=" and s.enrollment_source = "+enrollmentSource+" ";
		}
		if(branchId>0)
		{
			sql +=" and s.branch_id = "+branchId+" ";
		}
		if(startDate!=null)
		{
			sql +=" and f.created_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		if(endDate!=null)
		{
			sql +=" and f.created_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59' ";
		}
		sql +=" group by s.academy_id,s.branch_id";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String,Double>> list = super.getJdbcTemplatePlus().query(sql,
				new RowMapper() {
					public Map<String,Double> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,Double> map =new HashMap<String,Double>();
						map.put(resultSet.getInt("academy_id")+"_"+resultSet.getInt("branch_id"), resultSet.getDouble("fsum"));
						return map;
					}
		});
		if(list!=null){
			for (Map<String, Double> map2 : list) {
				for(Map.Entry<String, Double> entry:map2.entrySet()){
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return map;
	}
	
	/*
	 * 2012-05-08 重构
	 * 根据缴费单明细统计满足条件的学生个数_院校返款匹配院校返款标准用（根据学生实体）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countStudCountForAcademyRebateByStudent(net.cedu.entity.crm.Student, int)
	 */
	public int countStudCountForAcademyRebateByStudent(Student student,int feeSubjectId) throws Exception 
	{
		int count=0;
		if(student!=null)
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where status>="+Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO
				+"  and fee_subject_id="+feeSubjectId
				+"  and student_id in ( "+findStuSqlForAcademyRebateReview(student)+" ) ";
			
			count =jt.queryForInt(sql);	
		}
		return count;
		
	}
	/*
	 * 2012-07-04 重构
	 * 根据缴费单明细统计满足条件的学生个数_院校返款匹配院校返款标准用（根据学生实体）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(net.cedu.entity.crm.Student, int, java.lang.String)
	 */
	public int countStudCountForAcademyRebateReviewByStudentFeeSubjectIdFpdIds(Student student,int feeSubjectId,String fpdIds) throws Exception 
	{
		int count=0;
		if(student!=null)
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where (status>"+Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO+" or id in ("+fpdIds+" )) "
				+"  and fee_subject_id="+feeSubjectId
				+"  and student_id in ( "+findStuSqlForAcademyRebateReview(student)+" ) ";
			
			count =jt.queryForInt(sql);	
		}
		return count;
		
	}
	
	/**
	 * 查询学生Sql集合(匹配该院校某个全局批次的总人数)__重构__院校返款
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStuSqlForAcademyRebateReview(Student student) throws Exception 
	{
		// 查询学生Sql
		String stuHql = "select id from tb_e_student where 1=1 ";
		if (student != null)
		{			
			// 院校
			if (student.getAcademyId() > 0) 
			{
				stuHql += "and academy_id="+student.getAcademyId();
			}
			// 批次
			if (student.getEnrollmentBatchId() > 0)
			{
				stuHql += " and  enrollment_batch_id= "+student.getEnrollmentBatchId();
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollment_batch_id in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) 
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+ student.getStartStatusId() + " and status<"+ student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * 统计需要添加院校返款单的缴费单院校返款总金额（根据缴费单明细ids字符串）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countFpdAllAcademyRebateMoneyByFpdIds(java.lang.String)
	 */
	public String countFpdAllAcademyRebateMoneyByFpdIds(String fpdIds) throws Exception 
	{
		if(fpdIds!=null && !fpdIds.equals(""))
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql="select IFNULL(sum(pay_academy_cedu),0) as money from tb_e_fee_payment_detail where id in ("+fpdIds+") ";
			
			String allmoney =(String)jt.queryForObject(sql, java.lang.String.class);
			if(allmoney==null)
			{
				return "0";
			}
			return allmoney;
		}
		else
		{
			return "0";
		}
		
	}
	
	/*
	 * * 2012-05-24 重构
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体和缴费单Ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countStuCountForChannelRebateReviewByStuAndFpdIds(net.cedu.entity.crm.Student, java.lang.String)
	 */
	public int countStuCountForChannelRebateReviewByStuAndFpdIds(Student student,String fpdIds) throws Exception 
	{
		int count=0;
		if(fpdIds!=null && !fpdIds.equals("") )
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where rebate_status>="+Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN
				+"  and fee_subject_id="+FeeSubjectEnum.TuitionFee.value()
				+"  and id in ( "+fpdIds+" ) ";
			//学生Ids__Hql
			String stuHql=this.findStudentIdsForChannelCountChannelRebateReview(student);
			if (stuHql != null && !stuHql.equals("select id from tb_e_student where 1=1 ")) 
			{
				sql += " and student_id in ("+ stuHql + ")";
			}
			else
			{
				return 0;
			}
			count =jt.queryForInt(sql);	
		}
		return count;
		
	}
	
	/**
	 * 查询学生ID_HQL集合(匹配该合作方的人数)__重构 2012-05-24
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsForChannelCountChannelRebateReview(Student student) throws Exception 
	{
		// 查询学生Sql
		String stuHql = "select id from tb_e_student where 1=1 ";
		if (student != null)
		{
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() > 0) 
			{
				stuHql += " and enrollment_source = "+student.getEnrollmentSource();
			}
			//合作方channelId
			if (student.getChannelId() > 0)
			{
				stuHql += " and channel_id ="+ student.getChannelId();
			}
			// 院校
			if (student.getAcademyId() > 0)
			{
				stuHql += " and academy_id="+ student.getAcademyId();
			}
			// 批次
			if (student.getEnrollmentBatchId() > 0) 
			{
				stuHql += " and  enrollment_batch_id="+student.getEnrollmentBatchId();
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollment_batch_id in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";
				} 
				else 
				{
					return null;
				}
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuition_status >= "+ Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			
			stuHql += " and  is_start_course= "+ Constants.STU_IS_START_COURSE_TRUE;
			
			stuHql += " and  is_channel_type_checked= "+ Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE;
					
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+ student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+ student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+ student.getStartStatusId() + " and status<"+ student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * * 2012-05-26 重构   跨中心合作方返款
	 * 根据缴费单明细统计满足条件的学生个数_招生返款匹配合作方招生总人数用（根据学生实体和缴费单Ids字符串）
	 * 暂时只对学费的缴费单明细进行统计
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(net.cedu.entity.crm.Student, java.lang.String)
	 */
	public int countStuCountForChannelRebateSpecialReviewByStuAndFpdIds(Student student,String fpdIds) throws Exception 
	{
		int count=0;
		if(fpdIds!=null && !fpdIds.equals("") )
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where rebate_status>="+Constants.PAYMENT_REBATE_STATUS_KE_YI_ZHAO_SHENG_FAN_KAN
				+"  and fee_subject_id="+FeeSubjectEnum.TuitionFee.value()
				+"  and id in ( "+fpdIds+" ) ";
			//学生Ids__Hql
			String stuHql=this.findStudentIdsForChannelCountChannelRebateSpecialReview(student);
			if (stuHql != null && !stuHql.equals("select id from tb_e_student where 1=1 ")) 
			{
				sql += " and student_id in ("+ stuHql + ")";
			}
			else
			{
				return 0;
			}
			count =jt.queryForInt(sql);	
		}
		return count;
		
	}
	
	/**
	 * 跨中心合作方返款
	 * 
	 * 查询学生ID_HQL集合(匹配该合作方的人数)__重构 2012-05-26
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsForChannelCountChannelRebateSpecialReview(Student student) throws Exception 
	{
		// 查询学生Sql
		String stuHql = "select id from tb_e_student where 1=1 ";
		if (student != null)
		{			
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channel_id in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() > 0)
			{
				stuHql += " and channel_id ="+ student.getChannelId();
			}
			// 院校
			if (student.getAcademyId() > 0)
			{
				stuHql += " and academy_id="+ student.getAcademyId();
			}
			// 批次
			if (student.getEnrollmentBatchId() > 0) 
			{
				stuHql += " and  enrollment_batch_id="+student.getEnrollmentBatchId();
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollment_batch_id in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";
				} 
				else 
				{
					return null;
				}
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and  tuition_status >= "+ Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;
			
			stuHql += " and  is_start_course= "+ Constants.STU_IS_START_COURSE_TRUE;
			
			stuHql += " and  is_channel_type_checked= "+ Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE;
					
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+ student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+ student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+ student.getStartStatusId() + " and status<"+ student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * 统计已招生返款的缴费单招生返款总金额
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#countFpdAllChannelRebateMoneyForChannelRebateSearch(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student)
	 */
	public String countFpdAllChannelRebateMoneyForChannelRebateSearch(FeePaymentDetail feePaymentDetail, Student student) throws Exception 
	{
		if(feePaymentDetail!=null && student!=null)
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			String sql="select IFNULL(sum(payment_by_channel),0) as money from tb_e_fee_payment_detail where 1=1 ";
			
			// 缴费单招生返款状态
			if (feePaymentDetail.getRebateStatus() != 0) 
			{
				sql += " and rebate_status ="+ feePaymentDetail.getRebateStatus();
			}
			else
			{
				sql += " and rebate_status >="+Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN;
			}
				
			//总部确认时间
			if(feePaymentDetail.getCeduConfirmTime()!=null)
			{
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sql += " and ( cedu_confirm_time <= '"+df.format(feePaymentDetail.getCeduConfirmTime())+"' or cedu_confirm_time is null ) ";				
			}
			
			//招生返款期
			if(feePaymentDetail.getChannelRebateTimeId()>0)
			{
				sql += " and channel_rebate_time_id ="+ feePaymentDetail.getChannelRebateTimeId();
			}
				
			//缴费单状态
			sql += " and status >="+Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN;		
				
			//费用科目    暂时只对学费进行招生返款
			sql += " and fee_subject_id ="+ FeeSubjectEnum.TuitionFee.value();
							
			//学生Ids__Sql
			String stuSql=this.findStudentIdsHqlForChannelRecruitRebateSearch(student);
			if (stuSql != null && !stuSql.equals("select id from tb_e_student where 1=1 ")) 
			{
				sql += " and student_id in ("
								+ stuSql + ")";
			}
			else
			{
				return "0";
			}

			String allmoney =(String)jt.queryForObject(sql, java.lang.String.class);
			if(allmoney==null)
			{
				return "0";
			}
			return allmoney;
		}
		else
		{
			return "0";
		}
		
	}
	/**
	 * 查询学生ID_Sql 集合(招生返款查询用)__重构
	 * 
	 * @param student
	 * @return
	 * @throws Exception
	 */
	private String findStudentIdsHqlForChannelRecruitRebateSearch(Student student) throws Exception 
	{
		String stuHql = "select id from tb_e_student where 1=1 ";
		if (student != null)
		{			
			//招生途径enrollmentSource
			if (student.getEnrollmentSource() != 0)
			{
				stuHql += " and enrollment_source ="+student.getEnrollmentSource();						
			}
			//合作方channelIds
			if(student.getParamsString().get("channelIds")!=null)
			{
				stuHql += " and channel_id in ( "+student.getParamsString().get("channelIds")+" ) ";
			}
			else if (student.getChannelId() != 0) 
			{
				stuHql += " and channel_id ="+student.getChannelId();
			}
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) 
			{
				stuHql += " and name like '%"+student.getName()+"%' ";
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo()))
			{
				stuHql += " and cert_no like '%"+student.getCertNo()+"%' ";
			}
			// 院校
			if (student.getAcademyId() != 0)
			{
				stuHql += " and academy_id="+student.getAcademyId();					
			}
			// 全局批次
			if (student.getGlbtach() > 0) 
			{
				List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
							.findAcademyEnrollBatchByGId(student.getGlbtach());
				String gbatchIds = "";
				if (null != aeblst && aeblst.size() > 0)
				{
					for (int i = 0; i < aeblst.size(); i++)
					{
						gbatchIds += "," + aeblst.get(i).getId();
					}
					stuHql += " and enrollment_batch_id in ( "+ gbatchIds.substring(1, gbatchIds.length()) + " ) ";	
				} 
				else 
				{
					return null;
				}
			}
			// 学习中心
			if (student.getBranchId() != 0) 
			{
				stuHql += " and  branch_id= "+student.getBranchId();
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0)
			{
				stuHql += " and  enrollment_batch_id= "+student.getEnrollmentBatchId();
			}
			
			// 层次
			if (student.getLevelId() != 0) 
			{
				stuHql += " and  level_id= "+student.getLevelId();
			}
			// 专业
			if (student.getMajorId() != 0) 
			{
				stuHql += " and  major_id= "+student.getMajorId();
			}
			
			//已缴学费   且已开课、已招生途径复核的学生才能招生返款
			stuHql += " and tuition_status >= "+Constants.STU_TUITION_STATUS_SHOU_CI_JIAO_FEI;		
			
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status <"+student.getEndStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
			{
				stuHql += " and  status >"+student.getStartStatusId();
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
			{
				stuHql += " and  status> "+student.getStartStatusId() + " and status<"+student.getEndStatusId();
			}
		}
		return stuHql;
	}
	
	/*
	 * 2012-07-22
	 * 
	 * 查询符合年度院校返款的缴费单明细数量（每种费用科目都不一样）（计算院校年度返款单）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDetailDao#findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(net.cedu.entity.finance.FeePaymentDetail, net.cedu.entity.crm.Student, java.util.List)
	 */
	public int findJiaoFeiCountByFpdStudentAebForAcademyYearRebate(FeePaymentDetail feePaymentDetail,Student student,List<AcademyEnrollBatch> aeblist) throws Exception
	{	
		int count=0;
		if(feePaymentDetail!=null && student!=null && aeblist!=null && aeblist.size()>0)
		{
			JdbcTemplate jt = this.getJdbcTemplate();			
			String sql="select count(DISTINCT(student_id)) from tb_e_fee_payment_detail where status>="+Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN
				+"  and fee_subject_id="+feePaymentDetail.getFeeSubjectId();
			
			sql += " and student_id in ( select id from tb_e_student where 1=1 ";						
			// 院校
			if (student.getAcademyId() != 0) 
			{
				sql += " and academy_id="+ student.getAcademyId();										
			}
			// 批次
			String aebIds="";
			for(AcademyEnrollBatch aebatch:aeblist)
			{
				if(aebatch!=null)
				{
					if(!aebIds.equals(""))
					{
						aebIds+=","+aebatch.getId();
					}
					else
					{
						aebIds+=aebatch.getId();
					}
				}
			}
			if(!aebIds.equals(""))
			{
				sql += " and  enrollment_batch_id in ( "+aebIds+" ) ";	
			}
			sql += " and  status >"+Constants.STU_CALL_STATUS_YI_DAO_MING;										
			sql += " ) ";
			
			count =jt.queryForInt(sql);	
		}
		else
		{
			return 0;
		}
		return count;
		
	}
	
}
