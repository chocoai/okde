package net.cedu.dao.finance.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.StudentAccountAmountManagementDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *  学生账户金额变动   数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class StudentAccountAmountManagementDaoImpl extends BaseMDDaoImpl<StudentAccountAmountManagement> implements StudentAccountAmountManagementDao
{
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 _业务接口
	
	@Autowired
	private MajorBiz majorbiz;//专业_业务层接口
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	@Autowired
	private FeePaymentDao feePaymentDao;
	
	
	/**
	 * 检查学生的查询条件是否为空    为空则不查询学生ID （为空的时候  学生为全部in学生id太多）
	 * @param student
	 * @return
	 */
	private boolean checkStudentInfo(Student student) {
		String hqlConditionExpression = "";
		if (student != null) {
			// 姓名
			if (student.getName() != null && !student.getName().equals("")) {
				hqlConditionExpression += "and name like"
						+ Constants.PLACEHOLDER;
			}
			// 证件号
			if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
				hqlConditionExpression += "and certNo like"
						+ Constants.PLACEHOLDER;
			}
			
			// 院校
			if (student.getAcademyId() != 0) {
				hqlConditionExpression += "and academyId="
						+ Constants.PLACEHOLDER;
			}
			// 全局批次
			if (student.getGlbtach() != 0)
			{
				hqlConditionExpression += " and  enrollmentBatchId= "
					+ Constants.PLACEHOLDER;
			}
			// 批次
			if (student.getEnrollmentBatchId() != 0) {
				hqlConditionExpression += " and  enrollmentBatchId= "
						+ Constants.PLACEHOLDER;
			}
			
			// 层次
			if (student.getLevelId() != 0) {
				hqlConditionExpression += " and  levelId= "
						+ Constants.PLACEHOLDER;
			}
			// 专业
			if (student.getMajorId() != 0) {
				hqlConditionExpression += " and  majorId= "
						+ Constants.PLACEHOLDER;
			}
			// 基础专业
			if (student.getGlmajor() != 0) {
				hqlConditionExpression += " and  majorId= "
					+ Constants.PLACEHOLDER;
			}
			// 学习中心
			if (student.getBranchId() != 0) {
				hqlConditionExpression += " and  branchId= "
						+ Constants.PLACEHOLDER;
			}
			// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
			// 如果起始状态ID>0,结束状态ID=0;则为无穷大
			// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
			// 如果都大于0,则取交集
			if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status <"
						+ Constants.PLACEHOLDER;
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0) {
				hqlConditionExpression += " and  status >"
						+ Constants.PLACEHOLDER;
			}
			if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0) {
				hqlConditionExpression += " and  status> "
						+ Constants.PLACEHOLDER + " and status<"
						+ Constants.PLACEHOLDER;
			}
			if (!hqlConditionExpression.equals("")) {

			}

		}
		if (hqlConditionExpression != null
				&& !hqlConditionExpression.equals("")) {
			return true;
		}
		return false;
	}
	/*
	 * 统计学生单纯充值
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentSimplyAccountMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentSimplyAccountMoney(Student student ,String starttime,String endtime) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//查询充值账户
						String samsql="select id from tb_e_student_account_management where student_id in ("+stuIds+")";
						samlist = jt.query(samsql,
								new RowMapper() {
									public StudentAccountManagement mapRow(ResultSet resultSet, int index)
										throws SQLException {
										StudentAccountManagement sam = new StudentAccountManagement();
											sam.setId(resultSet.getInt("id"));
											return sam;
										}
								});
						String accountIds=",";//学生账户Ids集合
						if(samlist!=null && samlist.size()>0)
						{
							for(StudentAccountManagement sam:samlist)
							{					
								if (accountIds.equals(",")) 
								{
									accountIds = sam.getId() + "";
								} 
								else
								{
									accountIds += "," + sam.getId();
								}
							}
							//统计金额
							String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  account_id in ( "+accountIds+" ) and fee_payment_id=0 and fee_payment_detail_id=0 and types="+Constants.STATUS_RECHARGE;
							if(starttime!=null && !starttime.equals(""))
							{
								smsql+=" and created_time>= '"+starttime+"'";
							}
							if(endtime!=null && !endtime.equals(""))
							{
								smsql+=" and created_time<= '"+endtime+"'";
							}
							allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						}
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  fee_payment_id=0 and fee_payment_detail_id=0 and types="+Constants.STATUS_RECHARGE;
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}	
	
	
	/*
	 * 修复单纯充值情况，把单纯充值情况都作为预缴费单处理
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#repairStudentAccountDanChunChongZhi()
	 */
	@SuppressWarnings("unchecked")
	public void repairStudentAccountDanChunChongZhi() throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//充值明细分组查询
			int pIndex = 1;// 起始索引
			int pSize = 2000;// 每次2000条数据
			boolean istrue=true;
			List<StudentAccountAmountManagement> saamlist=null; //充值明细集合
			List<String> sqlList = null;//执行sql集合
			Map<String,String> stuAccMap=getStudentAccount();//学生充值账户
			long start=System.currentTimeMillis();
			int i=1;
			while (istrue||(saamlist != null && saamlist.size() != 0))
			{
				sqlList = new ArrayList<String>();//执行sql集合
				String saamsql="select id,account_id,fee_subject,account_money,creator_id,created_time from tb_e_student_account_amount_management where fee_payment_id=0 and fee_payment_detail_id=0 and types="+Constants.STATUS_RECHARGE+" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
				saamlist = jt.query(saamsql,
					new RowMapper() {
						public StudentAccountAmountManagement mapRow(ResultSet resultSet, int index)
							throws SQLException {
							StudentAccountAmountManagement saam = new StudentAccountAmountManagement();
								saam.setId(resultSet.getInt("id"));
								saam.setAccountId(resultSet.getInt("account_id"));
								saam.setFeeSubject(resultSet.getInt("fee_subject"));
								saam.setAccountMoney(new BigDecimal(resultSet.getDouble("account_money")));
								saam.setCreatorId(resultSet.getInt("creator_id"));
								saam.setCreatedTime(resultSet.getDate("created_time"));
								return saam;
							}
					});
				//遍历充值明细
				if(saamlist!=null && saamlist.size()>0)
				{
					FeePayment fp=null;
					for(StudentAccountAmountManagement sa:saamlist)
					{					
						if(stuAccMap.get(sa.getAccountId()+"")!=null)
						{
							fp=new FeePayment();
							fp.setRechargeAmount(sa.getAccountMoney().doubleValue());
							fp.setTotalAmount(sa.getAccountMoney().doubleValue());
							fp.setStudentId(Integer.valueOf(stuAccMap.get(sa.getAccountId()+"")));
							fp.setCode(buildCodeBiz.getCodes(CodeEnum.Payment.getCode(),
											CodeEnum.PaymentPrefix.getCode()));
							fp.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
							fp.setPamentType(Constants.FEE_PAYMENT_TYPE_PRE_BILLING);
							fp.setDeleteFlag(Constants.DELETE_FALSE);
							fp.setCreatorId(sa.getCreatorId());
							fp.setCreatedTime(sa.getCreatedTime());
							fp.setUpdaterId(sa.getCreatorId());
							fp.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));							
							fp.setCommonBatch(this.feePaymentDao.getCommonBatch(Integer.valueOf(stuAccMap.get(sa.getAccountId()+""))));
						
							this.feePaymentDao.save(fp);
							
							sqlList.add("update tb_e_student_account_amount_management set fee_payment_id='"+fp.getId()+"' where id="+sa.getId());
							
						}
						
					}
					
				}
				//批量执行update
				jt.batchUpdate(sqlList.toArray(new String[sqlList.size()]));
				
				//pIndex++;
				istrue=false;
				if(saamlist.size()<pSize)
				{
					break;
				}
				
				System.out.println("执行"+i+++"次");
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	
	/*
	 * 学生充值账户map
	 * @return
	 */
	@SuppressWarnings("unchecked")
	final public Map<String,String> getStudentAccount()
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String fpdsql="SELECT id,student_id from tb_e_student_account_management " ;
		List<Map<String,String>> list = jt.query(fpdsql,
				new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,String> map =new HashMap<String,String>();
						map.put("key", resultSet.getString("id"));
						map.put("value", resultSet.getString("student_id"));
						return map;
					}
		});
		if(list!=null){
			Map<String,String> resultMap =new HashMap<String,String>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key"),map.get("value"));
			}
			return resultMap;
		}

		return new HashMap<String,String>();
	}
	
	
	/*
	 * 统计学生充值_缴费单明细查询页面
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentRechargeMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentRechargeMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					
					// 批次
					if (student.getEnrollmentBatchId() != 0)
					{
						jfdsql += " and  enrollment_batch_id= "
								+ student.getEnrollmentBatchId();
					}
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					// 专业
					if (student.getMajorId()!= 0)
					{
						jfdsql += " and  major_id= "
								+ student.getMajorId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					// 复核状态
					if (student.getParamsInt().get("isChannelTypeChecked")!=null && student.getParamsInt().get("isChannelTypeChecked")!=-1)
					{
						jfdsql += " and is_channel_type_checked = "+student.getParamsInt().get("isChannelTypeChecked");
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//查询充值账户
						String samsql="select id from tb_e_student_account_management where student_id in ("+stuIds+")";
						samlist = jt.query(samsql,
								new RowMapper() {
									public StudentAccountManagement mapRow(ResultSet resultSet, int index)
										throws SQLException {
										StudentAccountManagement sam = new StudentAccountManagement();
											sam.setId(resultSet.getInt("id"));
											return sam;
										}
								});
						String accountIds=",";//学生账户Ids集合
						if(samlist!=null && samlist.size()>0)
						{
							for(StudentAccountManagement sam:samlist)
							{					
								if (accountIds.equals(",")) 
								{
									accountIds = sam.getId() + "";
								} 
								else
								{
									accountIds += "," + sam.getId();
								}
							}
							//统计金额
							String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  account_id in ( "+accountIds+" ) and types="+Constants.STATUS_RECHARGE;
							if(starttime!=null && !starttime.equals(""))
							{
								smsql+=" and created_time>= '"+starttime+"'";
							}
							if(endtime!=null && !endtime.equals(""))
							{
								smsql+=" and created_time<= '"+endtime+"'";
							}
							//费用科目
							if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
							{
								smsql += " and fee_subject in ("+feeSubjectIds + ")";
							}
							allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						}
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  types="+Constants.STATUS_RECHARGE;
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				//费用科目
				if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
				{
					smsql += " and fee_subject in ("+feeSubjectIds + ")";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}	
	
	/*
	 * 统计学生消费_缴费单明细查询页面
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentXiaoFeiMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentXiaoFeiMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					
					// 批次
					if (student.getEnrollmentBatchId() != 0)
					{
						jfdsql += " and  enrollment_batch_id= "
								+ student.getEnrollmentBatchId();
					}
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					// 专业
					if (student.getMajorId()!= 0)
					{
						jfdsql += " and  major_id= "
								+ student.getMajorId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					// 复核状态
					if (student.getParamsInt().get("isChannelTypeChecked")!=null && student.getParamsInt().get("isChannelTypeChecked")!=-1)
					{
						jfdsql += " and is_channel_type_checked = "+student.getParamsInt().get("isChannelTypeChecked");
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//查询充值账户
						String samsql="select id from tb_e_student_account_management where student_id in ("+stuIds+")";
						samlist = jt.query(samsql,
								new RowMapper() {
									public StudentAccountManagement mapRow(ResultSet resultSet, int index)
										throws SQLException {
										StudentAccountManagement sam = new StudentAccountManagement();
											sam.setId(resultSet.getInt("id"));
											return sam;
										}
								});
						String accountIds=",";//学生账户Ids集合
						if(samlist!=null && samlist.size()>0)
						{
							for(StudentAccountManagement sam:samlist)
							{					
								if (accountIds.equals(",")) 
								{
									accountIds = sam.getId() + "";
								} 
								else
								{
									accountIds += "," + sam.getId();
								}
							}
							//统计金额
							String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  account_id in ( "+accountIds+" ) and types ="+Constants.STATUS_CONSUMPTION;
							if(starttime!=null && !starttime.equals(""))
							{
								smsql+=" and created_time>= '"+starttime+"'";
							}
							if(endtime!=null && !endtime.equals(""))
							{
								smsql+=" and created_time<= '"+endtime+"'";
							}
							//费用科目
							if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
							{
								smsql += " and fee_subject in ("+feeSubjectIds + ")";
							}
							allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						}
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  types ="+Constants.STATUS_CONSUMPTION;
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				//费用科目
				if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
				{
					smsql += " and fee_subject in ("+feeSubjectIds + ")";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}
	
	/*
	 * 统计学生退费_缴费单明细查询页面
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentTuiFeiMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentTuiFeiMoney(Student student ,String starttime,String endtime,String feeSubjectIds) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					
					// 批次
					if (student.getEnrollmentBatchId() != 0)
					{
						jfdsql += " and  enrollment_batch_id= "
								+ student.getEnrollmentBatchId();
					}
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					// 专业
					if (student.getMajorId()!= 0)
					{
						jfdsql += " and  major_id= "
								+ student.getMajorId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					// 复核状态
					if (student.getParamsInt().get("isChannelTypeChecked")!=null && student.getParamsInt().get("isChannelTypeChecked")!=-1)
					{
						jfdsql += " and is_channel_type_checked = "+student.getParamsInt().get("isChannelTypeChecked");
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//查询充值账户
						String samsql="select id from tb_e_student_account_management where student_id in ("+stuIds+")";
						samlist = jt.query(samsql,
								new RowMapper() {
									public StudentAccountManagement mapRow(ResultSet resultSet, int index)
										throws SQLException {
										StudentAccountManagement sam = new StudentAccountManagement();
											sam.setId(resultSet.getInt("id"));
											return sam;
										}
								});
						String accountIds=",";//学生账户Ids集合
						if(samlist!=null && samlist.size()>0)
						{
							for(StudentAccountManagement sam:samlist)
							{					
								if (accountIds.equals(",")) 
								{
									accountIds = sam.getId() + "";
								} 
								else
								{
									accountIds += "," + sam.getId();
								}
							}
							//统计金额
							String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  account_id in ( "+accountIds+" ) and types ="+Constants.STATUS_APPLY_CONSUMPTION_TRUE;
							if(starttime!=null && !starttime.equals(""))
							{
								smsql+=" and created_time>= '"+starttime+"'";
							}
							if(endtime!=null && !endtime.equals(""))
							{
								smsql+=" and created_time<= '"+endtime+"'";
							}
							//费用科目
							if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
							{
								smsql += " and fee_subject in ("+feeSubjectIds + ")";
							}
							allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						}
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(account_money),0) from tb_e_student_account_amount_management where  types ="+Constants.STATUS_APPLY_CONSUMPTION_TRUE;
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				//费用科目
				if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
				{
					smsql += " and fee_subject in ("+feeSubjectIds + ")";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}
	
	/*
	 * 统计缴费单明细金额_缴费单明细查询页面
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentRechargeMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentFeePaymentDetailMoney(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					// 批次
					if (student.getEnrollmentBatchId() != 0)
					{
						jfdsql += " and  enrollment_batch_id= "
								+ student.getEnrollmentBatchId();
					}
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					// 专业
					if (student.getMajorId()!= 0)
					{
						jfdsql += " and  major_id= "
								+ student.getMajorId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					// 复核状态
					if (student.getParamsInt().get("isChannelTypeChecked")!=null && student.getParamsInt().get("isChannelTypeChecked")!=-1)
					{
						jfdsql += " and is_channel_type_checked = "+student.getParamsInt().get("isChannelTypeChecked");
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//统计金额
						String smsql="select IFNULL(sum(amount_paied+recharge_amount),0) from tb_e_fee_payment_detail where  student_id in ( "+stuIds+" ) ";
						if(starttime!=null && !starttime.equals(""))
						{
							smsql+=" and created_time>= '"+starttime+"'";
						}
						if(endtime!=null && !endtime.equals(""))
						{
							smsql+=" and created_time<= '"+endtime+"'";
						}
						//总部确认时间起
						if (ccStartTime != null && !"".equals(ccStartTime)) 
						{
							smsql+=" and cedu_confirm_time>= '"+ccStartTime+"'";
						}
						//总部确认时间止
						if (ccEndTime != null && !"".equals(ccEndTime)) 
						{
							smsql+=" and cedu_confirm_time<= '"+ccEndTime+" 23:59:59"+"'";
						}
						//费用科目
						if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
						{
							smsql += " and fee_subject_id in ("+feeSubjectIds + ")";
						}
						//状态
						if(status!=0)
						{
							smsql+=" and status = "+status;
						}
						else
						{
							smsql+=" and ( status >="+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN +" or status<="+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+" ) ";
						}
						//缴费方式
						if(feeWayId>0)
						{
							smsql += " and fee_payment_id in ( select id from tb_e_fee_payment where fee_way_id="
								+ feeWayId + ")";
						}
						allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(amount_paied+recharge_amount),0) from tb_e_fee_payment_detail where 1=1 ";
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				//总部确认时间起
				if (ccStartTime != null && !"".equals(ccStartTime)) 
				{
					smsql+=" and cedu_confirm_time>= '"+ccStartTime+"'";
				}
				//总部确认时间止
				if (ccEndTime != null && !"".equals(ccEndTime)) 
				{
					smsql+=" and cedu_confirm_time<= '"+ccEndTime+" 23:59:59"+"'";
				}
				//费用科目
				if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
				{
					smsql += " and fee_subject_id in ("+feeSubjectIds + ")";
				}
				//状态
				if(status!=0)
				{
					smsql+=" and status = "+status;
				}
				else
				{
					smsql+=" and ( status >="+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN +" or status<="+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+" ) ";
				}
				//缴费方式
				if(feeWayId>0)
				{
					smsql += " and fee_payment_id in ( select id from tb_e_fee_payment where fee_way_id="
						+ feeWayId + ")";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}	
	
	/*
	 * 统计缴费单明细使用充值金额_缴费单明细查询页面
	 * 
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentRechargeMoney(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentFeePaymentDetailShiYongChongZhiMoney(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime,int feeWayId) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					// 批次
					if (student.getEnrollmentBatchId() != 0)
					{
						jfdsql += " and  enrollment_batch_id= "
								+ student.getEnrollmentBatchId();
					}
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					// 专业
					if (student.getMajorId()!= 0)
					{
						jfdsql += " and  major_id= "
								+ student.getMajorId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					// 复核状态
					if (student.getParamsInt().get("isChannelTypeChecked")!=null && student.getParamsInt().get("isChannelTypeChecked")!=-1)
					{
						jfdsql += " and is_channel_type_checked = "+student.getParamsInt().get("isChannelTypeChecked");
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//统计金额
						String smsql="select IFNULL(sum(recharge_amount),0) from tb_e_fee_payment_detail where  student_id in ( "+stuIds+" ) ";
						if(starttime!=null && !starttime.equals(""))
						{
							smsql+=" and created_time>= '"+starttime+"'";
						}
						if(endtime!=null && !endtime.equals(""))
						{
							smsql+=" and created_time<= '"+endtime+"'";
						}
						//总部确认时间起
						if (ccStartTime != null && !"".equals(ccStartTime)) 
						{
							smsql+=" and cedu_confirm_time>= '"+ccStartTime+"'";
						}
						//总部确认时间止
						if (ccEndTime != null && !"".equals(ccEndTime)) 
						{
							smsql+=" and cedu_confirm_time<= '"+ccEndTime+" 23:59:59"+"'";
						}
						//费用科目
						if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
						{
							smsql += " and fee_subject_id in ("+feeSubjectIds + ")";
						}
						//状态
						if(status!=0)
						{
							smsql+=" and status = "+status;
						}
						else
						{
							smsql+=" and ( status >="+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN +" or status<="+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+" ) ";
						}
						//缴费方式
						if(feeWayId>0)
						{
							smsql += " and fee_payment_id in ( select id from tb_e_fee_payment where fee_way_id="
								+ feeWayId + ")";
						}
						allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(recharge_amount),0) from tb_e_fee_payment_detail where 1=1 ";
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				//总部确认时间起
				if (ccStartTime != null && !"".equals(ccStartTime)) 
				{
					smsql+=" and cedu_confirm_time>= '"+ccStartTime+"'";
				}
				//总部确认时间止
				if (ccEndTime != null && !"".equals(ccEndTime)) 
				{
					smsql+=" and cedu_confirm_time<= '"+ccEndTime+" 23:59:59"+"'";
				}
				//费用科目
				if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
				{
					smsql += " and fee_subject_id in ("+feeSubjectIds + ")";
				}
				//状态
				if(status!=0)
				{
					smsql+=" and status = "+status;
				}
				else
				{
					smsql+=" and ( status >="+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN +" or status<="+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+" ) ";
				}
				//缴费方式
				if(feeWayId>0)
				{
					smsql += " and fee_payment_id in ( select id from tb_e_fee_payment where fee_way_id="
						+ feeWayId + ")";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}	
	
	/*
	 * 统计缴费单明细金额_日收款单查询页面
	 * @see net.cedu.dao.finance.StudentAccountAmountManagementDao#countStudentfpdMoneyForCeduConfirmDay(net.cedu.entity.crm.Student, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String countStudentfpdMoneyForCeduConfirmDay(Student student ,String starttime,String endtime,String feeSubjectIds,int status,String ccStartTime,String ccEndTime) throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//统计总金额
			String allmoney="0";
			long start=System.currentTimeMillis();
			if(checkStudentInfo(student))
			{
				//学生分组查询
				int pIndex = 1;// 起始索引
				int pSize = 5000;// 每次5000条数据
				boolean istrue=true;
				List<Student> stulist=null; //学生集合	
				List<StudentAccountManagement> samlist=null; //学生账户集合
				int i=1;
				while (istrue||(stulist != null && stulist.size() != 0))
				{
					
					String jfdsql="select id from tb_e_student where 1=1 ";
					// 姓名
					if (student.getName() != null && !student.getName().equals("")) {
						jfdsql+= " and name like '%"+student.getName()+"%'";
					}
					// 证件号
					if (student.getCertNo() != null && !"".equals(student.getCertNo())) {
						jfdsql+= " and cert_no like '%"+student.getCertNo()+"%'";
					}
					
					// 院校
					if (student.getAcademyId() != 0) {
						jfdsql+= " and academy_id="
								+ student.getAcademyId();
					}
					// 全局批次
					if (student.getGlbtach() != 0)
					{
						// 全局批次和院校批次可以固定一个招生批次，这样减轻数据库负荷
						if (student.getAcademyId() != 0) 
						{
							AcademyEnrollBatch aeb = academyenrollbatchBiz
									.findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(
											student.getGlbtach(),
											student.getAcademyId());
							if (aeb != null) 
							{
								jfdsql+= " and  enrollment_batch_id = "+ aeb.getId();
							} 
							else 
							{
								return "0";
							}
						} 
						else 
						{
							List<AcademyEnrollBatch> aeblst = academyenrollbatchBiz
									.findAcademyEnrollBatchByGId(student.getGlbtach());
							String gbatchIds = "";
							if (null != aeblst && aeblst.size() > 0) 
							{
								for (int k = 0; k < aeblst.size(); k++) 
								{
									gbatchIds += "," + aeblst.get(k).getId();
								}
								jfdsql += " and  enrollment_batch_id in ( "
										+ gbatchIds.substring(1, gbatchIds.length())+ " ) ";
								
							} 
							else 
							{
								return "0";
							}
						}
					}
					// 批次
					if (student.getEnrollmentBatchId() != 0)
					{
						jfdsql += " and  enrollment_batch_id= "
								+ student.getEnrollmentBatchId();
					}
					// 层次
					if (student.getLevelId() != 0)
					{
						jfdsql += " and  level_id= "
								+ student.getLevelId();
					}
					// 专业
					if (student.getMajorId()!= 0)
					{
						jfdsql += " and  major_id= "
								+ student.getMajorId();
					}
					
					// 基础专业
					if (student.getGlmajor() != 0) {
						List<Major> majorList = this.majorbiz
						.findMajorListByBaseMajorId(student.getGlmajor());
						String glmajors = "";
						if (null != majorList && majorList.size() > 0) {
							for (int k = 0; k < majorList.size(); k++) {
								glmajors += "," + majorList.get(k).getId();
							}
							jfdsql += " and  major_id in ( "
									+ glmajors.substring(1, glmajors.length())+ " ) ";
		
						} else {
							return "0";
						}
					}
					// 学习中心
					if (student.getBranchId() != 0) {
						jfdsql += " and  branch_id= "
								+ student.getBranchId();
					}
					
					jfdsql+=" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
					
					stulist = jt.query(jfdsql,
						new RowMapper() {
							public Student mapRow(ResultSet resultSet, int index)
								throws SQLException {
									Student stu = new Student();
									stu.setId(resultSet.getInt("id"));
									return stu;
								}
						});
					//遍历学生
					String stuIds=",";//学生Ids字符串
					if(stulist!=null && stulist.size()>0)
					{
						for(Student stud:stulist)
						{					
							if (stuIds.equals(",")) 
							{
								stuIds = stud.getId() + "";
							} 
							else
							{
								stuIds += "," + stud.getId();
							}
						}
						//统计金额
						String smsql="select IFNULL(sum(amount_paied+recharge_amount-refund_amount),0) from tb_e_fee_payment_detail where  student_id in ( "+stuIds+" ) ";
						if(starttime!=null && !starttime.equals(""))
						{
							smsql+=" and created_time>= '"+starttime+"'";
						}
						if(endtime!=null && !endtime.equals(""))
						{
							smsql+=" and created_time<= '"+endtime+"'";
						}
						//总部确认时间起
						if (ccStartTime != null && !"".equals(ccStartTime)) 
						{
							smsql+=" and cedu_confirm_time>= '"+ccStartTime+"'";
						}
						//总部确认时间止
						if (ccEndTime != null && !"".equals(ccEndTime)) 
						{
							smsql+=" and cedu_confirm_time<= '"+ccEndTime+" 23:59:59"+"'";
						}
						//费用科目
						if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
						{
							smsql += " and fee_subject_id in ("+feeSubjectIds + ")";
						}
						//状态
						if(status!=0)
						{
							smsql+=" and status = "+status;
						}
						else
						{
							smsql+=" and ( status >="+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN +" or status<="+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+" ) ";
						}
						allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
						
					}
					
					pIndex++;
					istrue=false;
					if(stulist.size()<pSize)
					{
						break;
					}
					
					System.out.println("执行"+i+++"次");
				}
			}
			else
			{
				//统计金额
				String smsql="select IFNULL(sum(amount_paied+recharge_amount-refund_amount),0) from tb_e_fee_payment_detail where 1=1 ";
				if(starttime!=null && !starttime.equals(""))
				{
					smsql+=" and created_time>= '"+starttime+"'";
				}
				if(endtime!=null && !endtime.equals(""))
				{
					smsql+=" and created_time<= '"+endtime+"'";
				}
				//总部确认时间起
				if (ccStartTime != null && !"".equals(ccStartTime)) 
				{
					smsql+=" and cedu_confirm_time>= '"+ccStartTime+"'";
				}
				//总部确认时间止
				if (ccEndTime != null && !"".equals(ccEndTime)) 
				{
					smsql+=" and cedu_confirm_time<= '"+ccEndTime+" 23:59:59"+"'";
				}
				//费用科目
				if(feeSubjectIds!=null && !feeSubjectIds.equals(""))
				{
					smsql += " and fee_subject_id in ("+feeSubjectIds + ")";
				}
				//状态
				if(status!=0)
				{
					smsql+=" and status = "+status;
				}
				else
				{
					smsql+=" and ( status >="+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN +" or status<="+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN+" ) ";
				}
				allmoney=(new BigDecimal(jt.queryForObject(smsql, java.lang.String.class).toString()).add(new BigDecimal(allmoney))).toString();
			}
			long end=System.currentTimeMillis();
			System.out.println("执行了："+(end-start));
			return allmoney;
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "0";
		}
	}	
}
