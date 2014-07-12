package net.cedu.dao.finance.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.finance.FeePayment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *  缴费单  数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class FeePaymentDaoImpl extends BaseMDDaoImpl<FeePayment> implements FeePaymentDao 
{
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;// 院校招生批次 业务接口
	
	
	/*
	 * 统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#countFeePaymentAllMoneyByConditions()
	 */
	public String countFeePaymentAllMoneyByConditions(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime) throws Exception 
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select sum(fp.total_amount) from tb_e_fee_payment fp left outer join tb_e_student s on fp.student_id=s.id where 1=1 ";
		List list=new ArrayList();
		// 缴费方式
		if (feePayment.getFeeWayId() != 0) 
		{
			sql+= " and fp.fee_way_id = ? ";
			list.add(feePayment.getFeeWayId());
		}
		 // 缴费单类别
		 if (feePayment.getPamentType() != 0) 
		 {
			 sql+= " and fp.pament_type =? ";
			 list.add(feePayment.getPamentType());
		 }
		// 缴费单状态
		if (feePayment.getStatus() != 0) 
		{
			sql += " and fp.status =? ";
			list.add(feePayment.getStatus());
		}
		//是否打印
		if(feePayment.getIsPrint()!=-1)
		{
			sql+= " and fp.is_print =? ";
			list.add(feePayment.getIsPrint());
		}
		//缴费单号
		if(feePayment.getCode()!=null && !feePayment.getCode().equals(""))
		{
			sql += " and fp.code like ? ";
			list.add("%"+feePayment.getCode()+"%");
		}
		//缴费金额
		if(feemoney!=null && !feemoney.equals(""))
		{
			sql+= " and fp.fee_payment =? ";
			list.add(Double.valueOf(feemoney+""));
		}
		//缴费日期起
		if (starttime!=null && !starttime.equals("")) 
		{
			sql+= " and  fp.created_time >= ? ";
			list.add(starttime);
		}
		// 缴费日期止
		if (endtime!=null && !endtime.equals("")) 
		{
			sql += " and  fp.created_time <= ? ";
			list.add(endtime);
		}

		//统计缴费单状态大于等于收费确认   2012-02-03
		sql += " and ( fp.status >=? or fp.status <=? ) ";
		list.add(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		list.add(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
		
		
		// 姓名
		if (student.getName() != null && !student.getName().equals("")) 
		{
			sql += " and s.name like ? ";
			list.add("%" + student.getName() + "%");
		}
		// 证件号
		if (student.getCertNo() != null && !"".equals(student.getCertNo())) 
		{
			sql+= " and s.cert_no like ? ";
			list.add("%" + student.getCertNo() + "%");
		}
		// 院校
		if (student.getAcademyId() != 0) 
		{
			sql += " and s.academy_id= ? ";
			list.add(student.getAcademyId());
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
				sql += " and s.enrollment_batch_id in ( "+gbatchIds.substring(1, gbatchIds.length())+ " ) ";	
			} 
			else 
			{
				return null;
			}
		}
		// 批次
		if (student.getEnrollmentBatchId() != 0) 
		{
			sql += " and  s.enrollment_batch_id=? ";
			list.add(student.getEnrollmentBatchId());
		}
		// 层次
		if (student.getLevelId() != 0) 
		{
			sql += " and  s.level_id=? ";
			list.add(student.getLevelId());
		}
		// 专业
		if (student.getMajorId() != 0) 
		{
			sql += " and  s.major_id=? ";
			list.add(student.getMajorId());
		}
		// 学习中心
		if (student.getBranchId() != 0) 
		{
			sql += " and  s.branch_id=? ";
			list.add(student.getBranchId());
		}
		// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
		// 如果起始状态ID>0,结束状态ID=0;则为无穷大
		// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
		// 如果都大于0,则取交集
		if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
			sql += " and  s.status < ? ";
			list.add(student.getEndStatusId());
		}
		if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
		{
			sql += " and  s.status > ? ";
			list.add(student.getStartStatusId());
		}
		if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
		{
			sql += " and  s.status> ? and s.status < ? ";
			list.add(student.getStartStatusId());
			list.add(student.getEndStatusId());
		}
		
		String allmoney =(String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
		//String allmoney =(String)jt.queryForObject("select sum(fp.fee_payment) from tb_e_fee_payment fp left outer join tb_e_student s on fp.student_id=s.id where 1=1 and fp.student_id=? or s.id=? ",new Object[]{1,2}, java.lang.String.class);
		if(allmoney==null)
		{
			return "0";
		}
		return allmoney;
	}
	
	/*
	 * (重载方法)统计缴费单总金额(如果缴费单没有打印条件查询则赋值为isPrint=-1)
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#countFeePaymentAllMoneyByConditions()
	 */
	public String countFeePaymentAllMoneyByConditions(FeePayment feePayment, Student student,
			String feemoney,String starttime,String endtime,String globalids) throws Exception 
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql="select sum(fp.total_amount) from tb_e_fee_payment fp left outer join tb_e_student s on fp.student_id=s.id where 1=1 ";
		List list=new ArrayList();
		// 缴费方式
		if (feePayment.getFeeWayId() != 0) 
		{
			sql+= " and fp.fee_way_id = ? ";
			list.add(feePayment.getFeeWayId());
		}
		 // 缴费单类别
		 if (feePayment.getPamentType() != 0) 
		 {
			 sql+= " and fp.pament_type =? ";
			 list.add(feePayment.getPamentType());
		 }
		// 缴费单状态
		if (feePayment.getStatus() != 0) 
		{
			sql += " and fp.status =? ";
			list.add(feePayment.getStatus());
		}
		//是否打印
		if(feePayment.getIsPrint()!=-1)
		{
			sql+= " and fp.is_print =? ";
			list.add(feePayment.getIsPrint());
		}
		//缴费单号
		if(feePayment.getCode()!=null && !feePayment.getCode().equals(""))
		{
			sql += " and fp.code like ? ";
			list.add("%"+feePayment.getCode()+"%");
		}
		//缴费金额
		if(feemoney!=null && !feemoney.equals(""))
		{
			sql+= " and fp.fee_payment =? ";
			list.add(Double.valueOf(feemoney+""));
		}
		//缴费日期起
		if (starttime!=null && !starttime.equals("")) 
		{
			sql+= " and  fp.created_time >= ? ";
			list.add(starttime);
		}
		// 缴费日期止
		if (endtime!=null && !endtime.equals("")) 
		{
			sql += " and  fp.created_time <= ? ";
			list.add(endtime);
		}
		//全局批次
		if (globalids!=null && !globalids.equals(""))
		{
			Object[] ids = globalids.split(",");
			sql += " and ( ";
			for(int i=0;i<ids.length;i++){
				if(i!=0)
				{
					sql += " or ";
				}
				sql += " common_batch = ? ";
				list.add(ids[i].toString());
			}
			sql += " ) ";
		}

		//统计缴费单状态大于等于收费确认   2012-02-03
		sql += " and ( fp.status >=? or fp.status <=? ) ";
		list.add(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		list.add(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
		
		
		
		// 姓名
		if (student.getName() != null && !student.getName().equals("")) 
		{
			sql += " and s.name like ? ";
			list.add("%" + student.getName() + "%");
		}
		// 证件号
		if (student.getCertNo() != null && !"".equals(student.getCertNo())) 
		{
			sql+= " and s.cert_no like ? ";
			list.add("%" + student.getCertNo() + "%");
		}
		// 院校
		if (student.getAcademyId() != 0) 
		{
			sql += " and s.academy_id= ? ";
			list.add(student.getAcademyId());
		}
		// 批次
		if (student.getEnrollmentBatchId() != 0) 
		{
			sql += " and  s.enrollment_batch_id=? ";
			list.add(student.getEnrollmentBatchId());
		}
		// 层次
		if (student.getLevelId() != 0) 
		{
			sql += " and  s.level_id=? ";
			list.add(student.getLevelId());
		}
		// 专业
		if (student.getMajorId() != 0) 
		{
			sql += " and  s.major_id=? ";
			list.add(student.getMajorId());
		}
		// 学习中心
		if (student.getBranchId() != 0) 
		{
			sql += " and  s.branch_id=? ";
			list.add(student.getBranchId());
		}
		// 如果起始状态ID＝0,结束状态ID>0;则为无穷小
		// 如果起始状态ID>0,结束状态ID=0;则为无穷大
		// 如果起始状态ID＝0,结束状态ID=0;则为所有状态的学生
		// 如果都大于0,则取交集
		if (student.getStartStatusId() == 0 && student.getEndStatusId() > 0) {
			sql += " and  s.status < ? ";
			list.add(student.getEndStatusId());
		}
		if (student.getStartStatusId() > 0 && student.getEndStatusId() == 0)
		{
			sql += " and  s.status > ? ";
			list.add(student.getStartStatusId());
		}
		if (student.getStartStatusId() > 0 && student.getEndStatusId() > 0)
		{
			sql += " and  s.status> ? and s.status < ? ";
			list.add(student.getStartStatusId());
			list.add(student.getEndStatusId());
		}
		
		String allmoney =(String)jt.queryForObject(sql, list.toArray(), java.lang.String.class);
		//String allmoney =(String)jt.queryForObject("select sum(fp.fee_payment) from tb_e_fee_payment fp left outer join tb_e_student s on fp.student_id=s.id where 1=1 and fp.student_id=? or s.id=? ",new Object[]{1,2}, java.lang.String.class);
		if(allmoney==null)
		{
			return "0";
		}
		return allmoney;
	}

	/**
	 * 通过学生ID查询全局批次
	  * @see net.cedu.dao.finance.FeePaymentDao#getCommonBatch(int)
	 */
	public int getCommonBatch(int studentId) throws Exception {
		JdbcTemplate jt = this.getJdbcTemplate();
		String sql ="select IFNULL(global_enroll_batch_id,0) from tb_e_academy_enroll_batch where id=(select IFNULL(enrollment_batch_id,0) from tb_e_student where id="+studentId+")";
		return jt.queryForInt(sql);
	}

	/**
	 * 修复缴费单全局批次ID
	  * @see net.cedu.dao.finance.FeePaymentDao#repairFeePaymentCommonBatch()
	 */
	public void repairFeePaymentCommonBatch() throws Exception {
		String [] sqlArray=null;
		String sql="select id from tb_e_fee_payment";
		JdbcTemplate jt = this.getJdbcTemplate();
		List<HashMap<String,Long>> list=jt.queryForList(sql);
		
		if(list!=null){
			sqlArray=new String[list.size()];
			for (int i = 0; i < list.size(); i++) {
				Map<String,Long> map=list.get(i);
				String studentIdSql="select student_id from tb_e_fee_payment where id="+map.get("id");
				int student_id=jt.queryForInt(studentIdSql);
				jt.execute("update tb_e_fee_payment set common_batch=(select IFNULL(global_enroll_batch_id,0) from tb_e_academy_enroll_batch where id=(select IFNULL(enrollment_batch_id,0) from tb_e_student where id="+student_id+")) where id="+map.get("id"));
				System.out.println(student_id);
			}
			//批量执行更新操作
			//jt.batchUpdate(sqlArray);
		}
		
	}
	
	/*
	 * 修复缴费单明细各个账户值（只能是完成缴费还没进行其他缴费流程才能修复）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#repairFeePamymentDetailAllAccount()
	 */
	@SuppressWarnings("unchecked")
	public void repairFeePamymentDetailAllAccount() throws Exception
	{
		List<String> sqllist=new ArrayList<String>();//更新缴费单明细sql语句集合
		try {
			//缴费方式
			String sql="select id from tb_e_base_dict where types= "+Constants.BASEDICT_STYLE_FEEWAY;
			JdbcTemplate jt = this.getJdbcTemplate();
			List<HashMap<String,Long>> jfwaylist=jt.queryForList(sql);//缴费方式集合
			
			if(jfwaylist!=null)
			{
				for (int i = 0; i < jfwaylist.size(); i++) 
				{
					Map<String,Long> jfwaymap=jfwaylist.get(i);
					int jfdmxstatus=0;//缴费单明细状态
					//缴费单分组查询
					int pIndex = 1;// 起始索引
					int pSize = 3000;// 每次3000条数据
					boolean istrue=true;
					List<FeePayment> fplist=null; //缴费单集合
					if(Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU || Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU || Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU)
					{
						jfdmxstatus=Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU;
	//					String jfdsql="select id from tb_e_fee_payment where fee_way_id= "+jfwaymap.get("id");
	//					List<HashMap<String,Long>> jfdlist=jt.queryForList(jfdsql);//缴费单集合
	//					if(jfdlist!=null)
	//					{
	//						for (int k = 0; k < jfdlist.size(); k++) 
	//						{
	//							Map<String,Long> jfdmap=jfdlist.get(k);
	//							String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(amount_paied+recharge_amount) where fee_payment_id= "+jfdmap.get("id")+" and status="+jfdmxstatus;
	//							sqllist.add(jfdmxsql);
	//						}
	//					}
						while (istrue||(fplist != null && fplist.size() != 0))
						{
							String jfdsql="select id from tb_e_fee_payment where fee_way_id= "+jfwaymap.get("id")+" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
							fplist = jt.query(jfdsql,
									new RowMapper() {
										public FeePayment mapRow(ResultSet resultSet, int index)
												throws SQLException {
											FeePayment fp = new FeePayment();
											fp.setId(resultSet.getInt("id"));
											return fp;
										}
									});
							String feePaymentIds=",";//缴费单Ids字符串
							if(fplist!=null && fplist.size()>0)
							{
								for(FeePayment fp:fplist)
								{
									if (feePaymentIds.equals(",")) 
									{
										feePaymentIds = fp.getId() + "";
									} 
									else
									{
										feePaymentIds += "," + fp.getId();
									}
								}
							}
							if (!feePaymentIds.equals(",")) 
							{
								// 更新缴费单明细
								String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(amount_paied+recharge_amount) where fee_payment_id in( "+feePaymentIds+" ) and status="+jfdmxstatus;			
								jt.execute(jfdmxsql);
							}
							pIndex++;
							istrue=false;
							if(fplist.size()<pSize)
							{
								break;
							}
						}
					}
					else if(Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU || Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO)
					{
						jfdmxstatus=Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN;
	//					String jfdsql="select id from tb_e_fee_payment where fee_way_id= "+jfwaymap.get("id");
	//					List<HashMap<String,Long>> jfdlist=jt.queryForList(jfdsql);//缴费单集合
	//					if(jfdlist!=null)
	//					{
	//						for (int k = 0; k < jfdlist.size(); k++) 
	//						{
	//							Map<String,Long> jfdmap=jfdlist.get(k);
	//							String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(amount_paied+recharge_amount) where fee_payment_id= "+jfdmap.get("id")+" and status="+jfdmxstatus;
	//							sqllist.add(jfdmxsql);
	//						}
	//					}
						while (istrue||(fplist != null && fplist.size() != 0))
						{
							String jfdsql="select id from tb_e_fee_payment where fee_way_id= "+jfwaymap.get("id")+" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
							fplist = jt.query(jfdsql,
									new RowMapper() {
										public FeePayment mapRow(ResultSet resultSet, int index)
												throws SQLException {
											FeePayment fp = new FeePayment();
											fp.setId(resultSet.getInt("id"));
											return fp;
										}
									});
							String feePaymentIds=",";//缴费单Ids字符串
							if(fplist!=null && fplist.size()>0)
							{
								for(FeePayment fp:fplist)
								{
									if (feePaymentIds.equals(",")) 
									{
										feePaymentIds = fp.getId() + "";
									} 
									else
									{
										feePaymentIds += "," + fp.getId();
									}
								}
							}
							if (!feePaymentIds.equals(",")) 
							{
								// 更新缴费单明细
								String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(amount_paied+recharge_amount) where fee_payment_id in( "+feePaymentIds+" ) and status="+jfdmxstatus;			
								jt.execute(jfdmxsql);
							}
							pIndex++;
							istrue=false;
							if(fplist.size()<pSize)
							{
								break;
							}
						}
					}
					else if(Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO || Integer.valueOf(jfwaymap.get("id")+"")==Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO)
					{
						jfdmxstatus=Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO;
//					String jfdsql="select id from tb_e_fee_payment where fee_way_id= "+jfwaymap.get("id");
//					List<HashMap<String,Long>> jfdlist=jt.queryForList(jfdsql);//缴费单集合
//					if(jfdlist!=null)
//					{
//						for (int k = 0; k < jfdlist.size(); k++) 
//						{
//							Map<String,Long> jfdmap=jfdlist.get(k);
//							String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(academy_discount+academy_cedu_discount-discount_amount),academy_account=(amount_paied+recharge_amount+discount_amount-academy_discount-academy_cedu_discount) where fee_payment_id= "+jfdmap.get("id")+" and status="+jfdmxstatus;
//							sqllist.add(jfdmxsql);
//						}
//					}
						while (istrue||(fplist != null && fplist.size() != 0))
						{
							String jfdsql="select id from tb_e_fee_payment where fee_way_id= "+jfwaymap.get("id")+" order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
							fplist = jt.query(jfdsql,
									new RowMapper() {
										public FeePayment mapRow(ResultSet resultSet, int index)
												throws SQLException {
											FeePayment fp = new FeePayment();
											fp.setId(resultSet.getInt("id"));
											return fp;
										}
									});
							String feePaymentIds=",";//缴费单Ids字符串
							if(fplist!=null && fplist.size()>0)
							{
								for(FeePayment fp:fplist)
								{
									if (feePaymentIds.equals(",")) 
									{
										feePaymentIds = fp.getId() + "";
									} 
									else
									{
										feePaymentIds += "," + fp.getId();
									}
								}
							}
							if (!feePaymentIds.equals(",")) 
							{
								// 更新缴费单明细			
								String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(academy_discount+academy_cedu_discount-discount_amount),academy_account=(amount_paied+recharge_amount+discount_amount-academy_discount-academy_cedu_discount) where fee_payment_id in( "+feePaymentIds+" ) and status="+jfdmxstatus;
								jt.execute(jfdmxsql);
							}
							pIndex++;
							istrue=false;
							if(fplist.size()<pSize)
							{
								break;
							}
						}
					}
				}
			}
	//		if(sqllist!=null && sqllist.size()>0)
	//		{
	//			super.getJdbcTemplatePlus().batchUpdate((String[])sqllist.toArray());
	//		}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 修复缴费单充值金额和总金额（明细的使用充值金额，在缴费单里的充值金额应为负数）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#repairFeePamymentTotalAmountAndRechargeAmount()
	 */
	@SuppressWarnings("unchecked")
	public void repairFeePamymentTotalAmountAndRechargeAmount() throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//缴费单分组查询
			int pIndex = 1;// 起始索引
			int pSize = 5000;// 每次5000条数据
			boolean istrue=true;
			List<FeePayment> fplist=null; //缴费单集合
			List<String> sqlList = null;//执行sql集合
			Map<String,String> amountCountMap=getChongZhiZhangHuCount();//充值金额的统计
			Map<String,String> fpdCountMap=getJiaoFeiDanCount();//缴费单明细的统计
			long start=System.currentTimeMillis();
			int i=1;
			while (istrue||(fplist != null && fplist.size() != 0))
			{
				sqlList = new ArrayList<String>();//执行sql集合
				String jfdsql="select id,fee_payment,recharge_amount,total_amount from tb_e_fee_payment order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
				fplist = jt.query(jfdsql,
					new RowMapper() {
						public FeePayment mapRow(ResultSet resultSet, int index)
							throws SQLException {
								FeePayment fp = new FeePayment();
								fp.setId(resultSet.getInt("id"));
								fp.setFeePayment(resultSet.getDouble("fee_payment"));
								fp.setTotalAmount(resultSet.getDouble("total_amount"));
								fp.setRechargeAmount(resultSet.getDouble("recharge_amount"));
								return fp;
							}
					});
				//遍历缴费单
				if(fplist!=null && fplist.size()>0)
				{
					double allamount=0;//总充值账户金额
					double alltotal=0;//总金额
					double amountcount=0;
					double fpdcount=0;
					for(FeePayment fp:fplist)
					{					
						if(amountCountMap.get(fp.getId()+"")!=null)
						{
							amountcount=Double.parseDouble(amountCountMap.get(fp.getId()+""));
						}
						else
						{
							amountcount=0;
						}
						if(fpdCountMap.get(fp.getId()+"")!=null)
						{
							fpdcount=Double.parseDouble(fpdCountMap.get(fp.getId()+""));
						}
						else
						{
							fpdcount=0;
						}
						allamount=(new BigDecimal(amountcount).subtract(new BigDecimal(fpdcount)).doubleValue());
						
						alltotal=(new BigDecimal(fp.getFeePayment()).add(new BigDecimal(allamount)).doubleValue());
						//jt.execute("update tb_e_fee_payment set recharge_amount='"+allamount+"' ,total_amount='"+alltotal+"' where id="+fp.getId());
						sqlList.add("update tb_e_fee_payment set recharge_amount='"+allamount+"' ,total_amount='"+alltotal+"' where id="+fp.getId());
					}
					
				}
				//批量执行update
				jt.batchUpdate(sqlList.toArray(new String[sqlList.size()]));
				
				pIndex++;
				istrue=false;
				if(fplist.size()<pSize)
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
	 * 统计缴费单明细的金额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	final public Map<String,String> getJiaoFeiDanCount()
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String fpdsql="SELECT fee_payment_id,IFNULL(sum(recharge_amount),0) as money from tb_e_fee_payment_detail group by fee_payment_id" ;
		List<Map<String,String>> list = jt.query(fpdsql,
				new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,String> map =new HashMap<String,String>();
						map.put("key", resultSet.getString("fee_payment_id"));
						map.put("value", resultSet.getString("money"));
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
	 * 统计充值账户的金额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	final public Map<String,String> getChongZhiZhangHuCount()
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String amountsql="SELECT fee_payment_id,IFNULL(sum(account_money),0) as money from tb_e_student_account_amount_management where types in ("+Constants.STATUS_RECHARGE +","+Constants.STATUS_RECHARGE_REFUND+" ) group by fee_payment_id ";
		List<Map<String,String>> list = jt.query(amountsql,
				new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,String> map =new HashMap<String,String>();
						map.put("key", resultSet.getString("fee_payment_id"));
						map.put("value", resultSet.getString("money"));
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
	 * 还原pos直汇院校和网银院校缴费单明细各个账户值、状态还原到前一个状态（由于需求变动，历史数据处理问题    只允许用一次————2012-04-16已用）
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#revertFeePamymentDetailStatusAccountForPosEbank
	 */
	@SuppressWarnings("unchecked")
	public void revertFeePamymentDetailStatusAccountForPosEbank() throws Exception
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		List<String> sqllist=new ArrayList<String>();//更新缴费单明细sql语句集合
		try 
		{			
			//缴费单分组查询
			int pIndex = 1;// 起始索引
			int pSize = 3000;// 每次3000条数据
			boolean istrue=true;
			List<FeePayment> fplist=null; //缴费单集合
					
			while (istrue||(fplist != null && fplist.size() != 0))
			{
				String jfdsql="select id from tb_e_fee_payment where fee_way_id in ("+Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO+","+Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO+") order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
				fplist = jt.query(jfdsql,
						new RowMapper() {
								public FeePayment mapRow(ResultSet resultSet, int index)
										throws SQLException {
											FeePayment fp = new FeePayment();
											fp.setId(resultSet.getInt("id"));
											return fp;
										}
									});
				String feePaymentIds=",";//缴费单Ids字符串
				if(fplist!=null && fplist.size()>0)
				{
					for(FeePayment fp:fplist)
					{
						if (feePaymentIds.equals(",")) 
						{
							feePaymentIds = fp.getId() + "";
						} 
						else
						{
							feePaymentIds += "," + fp.getId();
						}
					}
				}
				if (!feePaymentIds.equals(",")) 
				{
					// 更新缴费单明细			
					String jfdmxsql="update tb_e_fee_payment_detail set branch_account=(amount_paied+recharge_amount),academy_account=0,rebate_status="+Constants.PAYMENT_REBATE_STATUS_CHU_SHI_ZHUANG_TAI+",status="+Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN+" where fee_payment_id in( "+feePaymentIds+" ) and status="+Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO;
					jt.execute(jfdmxsql);
				}
				pIndex++;
				istrue=false;
				if(fplist.size()<pSize)
				{
					break;
				}
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/*
	 * 修复缴费单优惠金额和总金额
	 * 
	 * @see net.cedu.dao.finance.FeePaymentDao#repairFeePamymentTotalAmountAndDiscountAmount()
	 */
	@SuppressWarnings("unchecked")
	public void repairFeePamymentTotalAmountAndDiscountAmount() throws Exception
	{
		try
		{
			JdbcTemplate jt = this.getJdbcTemplate();
			//缴费单分组查询
			int pIndex = 1;// 起始索引
			int pSize = 5000;// 每次5000条数据
			boolean istrue=true;
			List<FeePayment> fplist=null; //缴费单集合
			List<String> sqlList = null;//执行sql集合
			Map<String,String> fpdCountMap=getJiaoFeiDanDiscountAmount();//缴费单明细的统计
			long start=System.currentTimeMillis();
			int i=1;
			while (istrue||(fplist != null && fplist.size() != 0))
			{
				sqlList = new ArrayList<String>();//执行sql集合
				String jfdsql="select id,total_amount,discount_amount from tb_e_fee_payment order by id limit "+ ((pIndex - 1) * pSize) + "," + pSize;
				fplist = jt.query(jfdsql,
					new RowMapper() {
						public FeePayment mapRow(ResultSet resultSet, int index)
							throws SQLException {
								FeePayment fp = new FeePayment();
								fp.setId(resultSet.getInt("id"));
								fp.setTotalAmount(resultSet.getDouble("total_amount"));
								fp.setDiscountAmount(resultSet.getDouble("discount_amount"));
								return fp;
							}
					});
				//遍历缴费单
				if(fplist!=null && fplist.size()>0)
				{
					double alltotal=0;//总金额
					double fpdcount=0;
					for(FeePayment fp:fplist)
					{					
						if(fpdCountMap.get(fp.getId()+"")!=null)
						{
							fpdcount=Double.parseDouble(fpdCountMap.get(fp.getId()+""));
						}
						else
						{
							fpdcount=0;
						}
						
						alltotal=(new BigDecimal(fp.getTotalAmount()).add(new BigDecimal(fp.getDiscountAmount())).subtract(new BigDecimal(fpdcount))).doubleValue();
						sqlList.add("update tb_e_fee_payment set discount_amount='"+fpdcount+"' ,total_amount='"+alltotal+"' where id="+fp.getId());
					}
					
				}
				//批量执行update
				jt.batchUpdate(sqlList.toArray(new String[sqlList.size()]));
				
				pIndex++;
				istrue=false;
				if(fplist.size()<pSize)
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
	 * 统计缴费单明细的总优惠金额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	final public Map<String,String> getJiaoFeiDanDiscountAmount()
	{
		JdbcTemplate jt = this.getJdbcTemplate();
		String fpdsql="SELECT fee_payment_id,IFNULL(sum(discount_amount),0) as money from tb_e_fee_payment_detail group by fee_payment_id" ;
		List<Map<String,String>> list = jt.query(fpdsql,
				new RowMapper() {
					public Map<String,String> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,String> map =new HashMap<String,String>();
						map.put("key", resultSet.getString("fee_payment_id"));
						map.put("value", resultSet.getString("money"));
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
	
}
