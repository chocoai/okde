package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.finance.FeePaymentDetailDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.AcademyEnrollmentReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AcademyEnrollmentReportImpl extends BaseReportDaoImpl implements
		AcademyEnrollmentReport {
	@Autowired
	private BranchDao  branchDao;
	@Autowired
	FeePaymentDetailDao feePaymentDetailDao;
	private JdbcTemplatePlus jdbcTemplatePlus = null;

	public Map statistics(Map<String, Integer> params,Map<String, Date> dateParams) {
		
		jdbcTemplatePlus = getJdbcTemplatePlus();
		
		final int school = params.get("school");// 学院
		final int gbatch = params.get("batch");// 全局批次
		final int studentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int enrollmentSource = params.get("source");// 招生途径
		final int branchId = params.get("branch");// 学习中心
		final Date startDate = dateParams.get("startDate");// 开始日期
		final Date endDate = dateParams.get("endDate");// 结束日期
		
		//1.初始化mqp集合
		//院校ID_学习中心ID＝招生指标数
		final Map<String,Integer> key_a_b_value_zhaoshengzhibiao_map=initZhaoShengZhiBiaoMap(school,gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		//院校ID_学习中心ID＝报名人数
		final Map<String,Integer> key_a_b_value_baomingrenshu_map=initBaoMingRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		//院校ID_学习中心ID＝录取人数
		final Map<String,Integer> key_a_b_value_luqurenshu_map=initLuQuRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		//院校ID_学习中心ID＝未录取人数
		final Map<String,Integer> key_a_b_value_weiluqurenshu_map=initWeiLuQuRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		//院校ID_学习中心ID＝缴费人数
		final Map<String,Integer> key_a_b_value_jiaofeirenshu_map=initJiaoFeRenShuMap(school,gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		//院校ID_学习中心ID＝缴费金额
		final Map<String,Double> key_a_b_value_jiaofeijine_map=initJiaoFeiJinEMap(school,gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		//院校ID_=授权的学习中心IDs
		final Map<String,List<Integer>> key_a_value_bids_map=initAcademyAuthorizeBranchs(school, gbatch, branchId);
		
		//生成%数
		final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(2);// 设置小数位
		//金钱
		final DecimalFormat format_=new DecimalFormat();
		format_.setMinimumFractionDigits(2);
		//2.获取所有院校
		String academySql=" select id,name from tb_e_academy where delete_flag=0 ";
		if(school!=-2){
			academySql+=" and id="+school;
		}
		
		//合计List
		final List<Integer> zhibaoList=new ArrayList<Integer>();
		final List<Integer> baomingCountList=new ArrayList<Integer>();
		final List<Integer> luquCountList=new ArrayList<Integer>();
		final List<Integer> noluquCountList=new ArrayList<Integer>();
		final List<Integer> jiaofeiCountList=new ArrayList<Integer>();
		final List<Double> jiaofeiJinEList=new ArrayList<Double>();
		
		List academyList = jdbcTemplatePlus.query(academySql, new RowMapper() {
			public Object mapRow(ResultSet resultSet, int index)throws SQLException {
				Map<String, Object> academyMap = new HashMap<String, Object>();
				academyMap.put("academy_id", resultSet.getInt("id"));
				academyMap.put("academy_name", resultSet.getString("name"));
				//3,获取所有院校的授权学习中心（如果为空，查询历史所有的授权学习中心）
					List resultBranchList=new ArrayList();
					List<Integer> branchIdList=key_a_value_bids_map.get(resultSet.getInt("id")+"_");
					
					Map<String, Object> branchMap=null;
					Branch branch=null;
					int zhibiao=0;
					int baomingCount=0;
					int luquCount=0;
					int noluquCount=0;
					int jiaofeiCount=0;
					double jiaofeiJinE=0.00d;
					
					int zhibiaoSum=0;
					int baomingCountSum=0;
					int luquCountSum=0;
					int noluquCountSum=0;
					int jiaofeiCountSum=0;
					double jiaofeiJinESum=0.00d;
					if(branchIdList!=null){
						for (Integer branchId : branchIdList) {
							//学习中心
							branchMap = new HashMap<String, Object>();
							branchMap.put("branch_id", branchId);
							//名称
							branch=branchDao.findById(branchId);
							if(branch!=null){
								branchMap.put("branch_name", branch.getName());
							}else{
								branchMap.put("branch_name", "--");
							}
							//4,通过 院校ID_学习中心ID获取相应的值
							//指标
							branchMap.put("branch_zhi_biao",zhibiao= getIntValue(key_a_b_value_zhaoshengzhibiao_map,resultSet.getInt("id")+"_"+branchId));
							zhibiaoSum+=zhibiao;
							//报名人数
							branchMap.put("branch_bao_ming_count",baomingCount= getIntValue(key_a_b_value_baomingrenshu_map,resultSet.getInt("id")+"_"+branchId));
							baomingCountSum+=baomingCount;
							//报名完成率
							branchMap.put("branch_bao_ming_count_p", getP(format, baomingCount,zhibiao));
							
							//录取人数
							branchMap.put("branch_lu_qu_count", luquCount=getIntValue(key_a_b_value_luqurenshu_map,resultSet.getInt("id")+"_"+branchId));
							luquCountSum+=luquCount;
							//未录取人数
							branchMap.put("branch_no_lu_qu_count",noluquCount=getIntValue(key_a_b_value_weiluqurenshu_map,resultSet.getInt("id")+"_"+branchId));
							noluquCountSum+=noluquCount;
							//录取率
							branchMap.put("branch_lu_qu_count_p", getP(format, luquCount,baomingCount));
							
							//缴费人数
							branchMap.put("branch_jiao_fei_count",jiaofeiCount= getIntValue(key_a_b_value_jiaofeirenshu_map,resultSet.getInt("id")+"_"+branchId));
							jiaofeiCountSum+=jiaofeiCount;
							//缴费金额
							branchMap.put("branch_jiao_fei_jin_e",format_.format(jiaofeiJinE=getDoubleValue(key_a_b_value_jiaofeijine_map,resultSet.getInt("id")+"_"+branchId)));
							jiaofeiJinESum+=jiaofeiJinE;
							//缴费率
							branchMap.put("branch_jiao_fei_count_p", getP(format, jiaofeiCount,baomingCount));
							
							resultBranchList.add(branchMap);
						}
					}
				//院校合计
				academyMap.put("academy_branch_zhi_biao", zhibiaoSum);
				academyMap.put("academy_branch_bao_ming_count", baomingCountSum);
				academyMap.put("academy_branch_bao_ming_count_p", getP(format, baomingCountSum,zhibiaoSum));
				
				academyMap.put("academy_branch_lu_qu_count", luquCountSum);
				academyMap.put("academy_branch_no_lu_qu_count", noluquCountSum);
				academyMap.put("academy_branch_lu_qu_count_p",  getP(format, luquCountSum,baomingCountSum));
				
				academyMap.put("academy_branch_jiao_fei_count", jiaofeiCountSum);
				academyMap.put("academy_branch_jiao_fei_jin_e", format_.format(jiaofeiJinESum));
				academyMap.put("academy_branch_jiao_fei_count_p", getP(format, jiaofeiCountSum,baomingCountSum));
				
				zhibaoList.add(zhibiaoSum);
				baomingCountList.add(baomingCountSum);
				luquCountList.add(luquCountSum);
				noluquCountList.add(noluquCountSum);
				jiaofeiCountList.add(jiaofeiCountSum);
				jiaofeiJinEList.add(jiaofeiJinESum);
				
				
				academyMap.put("academy_branch_list", resultBranchList);
				return academyMap;
			}
		});
		
		Map<String,Object> sumMap=new HashMap<String, Object>();
		//总合计
		sumMap.put("sum_academy_branch_zhi_biao", getListSum(zhibaoList));
		sumMap.put("sum_academy_branch_bao_ming_count", getListSum(baomingCountList));
		sumMap.put("sum_academy_branch_bao_ming_count_p", getP(format, getListSum(baomingCountList),getListSum(zhibaoList)));
		
		sumMap.put("sum_academy_branch_lu_qu_count", getListSum(luquCountList));
		sumMap.put("sum_academy_branch_no_lu_qu_count", getListSum(noluquCountList));
		sumMap.put("sum_academy_branch_lu_qu_count_p",  getP(format, getListSum(luquCountList),getListSum(baomingCountList)));
		
		sumMap.put("sum_academy_branch_jiao_fei_count", getListSum(jiaofeiCountList));
		sumMap.put("sum_academy_branch_jiao_fei_jin_e", format_.format(getListSum_(jiaofeiJinEList)));
		sumMap.put("sum_academy_branch_jiao_fei_count_p", getP(format, getListSum(jiaofeiCountList),getListSum(baomingCountList)));
		
		sumMap.put("academyList", academyList);
		
		return sumMap;
	}
	/**
	 * 招生指标(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param studentDataSource 数据来源
	 * @param way 市场途径
	 * @param enrollmentSource 招生来源
	 * @param branchId 学习中心
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	private Map<String,Integer> initZhaoShengZhiBiaoMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
		
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		
		String sql="select academy_id,branch_id,IFNULL(sum(target),0) as target  from tb_e_academy_enroll_quota where delete_flag=0 ";
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//全局批次
		if(gbatch!=-2){
			sql+=" and batch_id = "+gbatch;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id = "+branchId;
		}
		
		sql+=" group by academy_id,branch_id";
		
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("target", resultSet.getInt("target"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("target"));
			}
		}
		return mapResult;
	}
	/**
	 * 报名人数(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param studentDataSource 数据来源
	 * @param way 市场途径
	 * @param enrollmentSource 招生来源
	 * @param branchId 学习中心
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	private Map<String,Integer> initBaoMingRenShuMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql="select academy_id,branch_id,IFNULL(count(*),0) as count_ from tb_e_student where status<>"+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING ;
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(school,gbatch)+")";
		}
		//数据来源
		if(studentDataSource!=-2){
			sql+=" and student_data_source="+studentDataSource;
		}
		//招生途径
		if(enrollmentSource!=-2){
			sql+=" and enrollment_source="+enrollmentSource;
		}
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
		//时间
		if(startDate!=null&&endDate!=null){
			sql+=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
		}
		if(startDate!=null&&endDate==null){
			sql+=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"'";
		}
		if(startDate==null&&endDate!=null){
			sql+=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
		}
		sql+=" group by academy_id,branch_id ";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("count", resultSet.getInt("count_"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("count"));
			}
		}
		return mapResult;
	}
	/**
	 * 录取人数(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param studentDataSource 数据来源
	 * @param way 市场途径
	 * @param enrollmentSource 招生来源
	 * @param branchId 学习中心
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	private Map<String,Integer> initLuQuRenShuMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql="select academy_id,branch_id,IFNULL(count(*),0) as count_ from tb_e_student where status="+Constants.STU_CALL_STATUS_YI_LU_QU+" ";
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(school,gbatch)+")";
		}
		//数据来源
		if(studentDataSource!=-2){
			sql+=" and student_data_source="+studentDataSource;
		}
		//招生途径
		if(enrollmentSource!=-2){
			sql+=" and enrollment_source="+enrollmentSource;
		}
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
		//时间
		if(startDate!=null&&endDate!=null){
			sql+=" and admit_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
		}
		if(startDate!=null&&endDate==null){
			sql+=" and admit_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"'";
		}
		if(startDate==null&&endDate!=null){
			sql+=" and admit_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
		}
		sql+=" group by academy_id,branch_id ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("count", resultSet.getInt("count_"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("count"));
			}
		}
		return mapResult;
	}
	/**
	 * 未录取人数(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param studentDataSource 数据来源
	 * @param way 市场途径
	 * @param enrollmentSource 招生来源
	 * @param branchId 学习中心
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	private Map<String,Integer> initWeiLuQuRenShuMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql="select academy_id,branch_id,IFNULL(count(*),0) as count_ from tb_e_student where status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and status<"+Constants.STU_CALL_STATUS_YI_LU_QU+" ";
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(school,gbatch)+")";
		}
		//数据来源
		if(studentDataSource!=-2){
			sql+=" and student_data_source="+studentDataSource;
		}
		//招生途径
		if(enrollmentSource!=-2){
			sql+=" and enrollment_source="+enrollmentSource;
		}
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
//		//时间
//		if(startDate!=null&&endDate!=null){
//			sql+=" and admit_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
//		}
//		if(startDate!=null&&endDate==null){
//			sql+=" and admit_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"'";
//		}
//		if(startDate==null&&endDate!=null){
//			sql+=" and admit_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
//		}
		sql+=" group by academy_id,branch_id ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("count", resultSet.getInt("count_"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("count"));
			}
		}
		return mapResult;
	}
//	/**
//	 * 缴费人数(Map初始化)
//	 * @param school 院校
//	 * @param gbatch 全局批次
//	 * @param studentDataSource 数据来源
//	 * @param way 市场途径
//	 * @param enrollmentSource 招生来源
//	 * @param branchId 学习中心
//	 * @param startDate 开始时间
//	 * @param endDate 结束时间
//	 * @return
//	 */
//	private Map<String,Integer> initJiaoFeRenShuMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
//		Map<String,Integer> mapResult=new HashMap<String,Integer>();
//		String sql="select academy_id,branch_id,IFNULL(count(*),0) as count_ from tb_e_student where status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and  tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";
//		//院校
//		if(school!=-2){
//			sql+=" and academy_id="+school;
//		}
//		//学习中心
//		if(branchId!=-2){
//			sql+=" and branch_id="+branchId;
//		}
//		//批次
//		if(gbatch!=-2){
//			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(school,gbatch)+")";
//		}
//		//数据来源
//		if(studentDataSource!=-2){
//			sql+=" and student_data_source="+studentDataSource;
//		}
//		//招生途径
//		if(enrollmentSource!=-2){
//			sql+=" and enrollment_source="+enrollmentSource;
//		}
//		//市场途径
//		if(way!=-2){
//			sql+=" and enrollment_way="+way;
//		}
////		//时间
////		if(startDate!=null&&endDate!=null){
////			sql+=" and admit_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
////		}
////		if(startDate!=null&&endDate==null){
////			sql+=" and admit_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"'";
////		}
////		if(startDate==null&&endDate!=null){
////			sql+=" and admit_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
////		}
//		sql+=" group by academy_id,branch_id ";
//		@SuppressWarnings("unchecked")
//		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
//				new RowMapper() {
//					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
//						Map<String, Integer> map = new HashMap<String, Integer>();
//						map.put("academy_id", resultSet.getInt("academy_id"));
//						map.put("branch_id", resultSet.getInt("branch_id"));
//						map.put("count", resultSet.getInt("count_"));
//						return map;
//					}
//				});
//		if(listResult!=null){
//			for (Map<String, Integer> map : listResult) {
//				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("count"));
//			}
//		}
//		return mapResult;
//	}
	/**
	 * 获取用户对应的缴费学生数量Map
	 * 
	 * @return  key:academyId_branchId   value:缴费学生人数
	 */
	final public Map<String,Integer> initJiaoFeRenShuMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql1 ="select DISTINCT student_id from tb_e_fee_payment_detail where fee_subject_id="+FeeSubjectEnum.TuitionFee.value() + " "
					+" and types = "+Constants.FEE_PAYMENT_TYPE_OFFICIALLY_SINGLE+" "
					+" and status >= "+Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN+" "
					+" and delete_flag = "+Constants.DELETE_FALSE+" ";
		//时间
		if(startDate!=null&&endDate!=null){
			sql1+=" and created_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql1+=" and created_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql1+=" and created_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		
		String sql ="select branch_id,academy_id,count(id) as jiaofei_count from tb_e_student where  status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and id in ("+sql1+")  and tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";		
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and enrollment_batch_id in ( "+getEnrollmentBatchId(school,gbatch)+" ) ";
		}
		//数据来源
		if(studentDataSource!=-2){
			sql+=" and student_data_source="+studentDataSource;
		}
		//招生途径
		if(enrollmentSource!=-2){
			sql+=" and enrollment_source="+enrollmentSource;
		}
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
		sql+=" group by branch_id,academy_id";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("jiaofei_count", resultSet.getInt("jiaofei_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("academy_id")+"_"+map.get("branch_id"), map.get("jiaofei_count"));
			}
		}
		return mapResult;
	}
	/**
	 * 缴费金额(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param studentDataSource 数据来源
	 * @param way 市场途径
	 * @param enrollmentSource 招生来源
	 * @param branchId 学习中心
	 * @param startDate 开始时间
	 * @param endDate 结束时间
	 * @return
	 */
	private Map<String,Double> initJiaoFeiJinEMap(int school,int gbatch,int studentDataSource,int way,int enrollmentSource,int branchId,Date startDate,Date endDate){
		Map<String,Double> map = new HashMap<String, Double>();
		try {
			map = feePaymentDetailDao.findSumPaymentByStudentAndFPD(school, gbatch, studentDataSource, way, enrollmentSource, branchId, startDate, endDate);
		} catch (Exception e) {
			return map;
		}
		return map;
	}
	
	/**
	 * 初始化院校授权的学习中心
	 * @param school
	 * @param gbatch
	 * @param branchId
	 * @return
	 */
	private Map<String,List<Integer>> initAcademyAuthorizeBranchs(int school,int gbatch,int branchId){
		Map<String,List<Integer>> mapResult=new HashMap<String,List<Integer>>();
		
		String sql=" select academy_id,branch_id from tb_r_academy_batch_branch where 1=1 ";
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(gbatch!=-2){
			sql+=" and global_id in ("+gbatch+")";
		}
		sql+=" group by academy_id,branch_id ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult_ = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						return map;
					}
				});
		List<Integer> branchIds=null;
		if(listResult_!=null){
			for (Map<String, Integer> map : listResult_) {
				if(map!=null){
					if(!mapResult.containsKey(map.get("academy_id")+"_")){
						branchIds=new ArrayList<Integer>();
						branchIds.add(map.get("branch_id"));
						mapResult.put(map.get("academy_id")+"_", branchIds);
					}else{
						mapResult.get(map.get("academy_id")+"_").add(map.get("branch_id"));
					}
				}
			}
		}
		return mapResult;
	}
	
	/**
	 * 通过全局批次，以及院校ID查询院校的招生批次Id字符串
	 * @param school院校
	 * @param globalEnrollBatchId 全局批次ID
	 * @return
	 */
	final public String getEnrollmentBatchId(int school,int globalEnrollBatchId){
		
		if(globalEnrollBatchId==-2){
			return "0";
		}
		
		String ids=",";
		String sql="select IFNULL(id,0) as id from tb_e_academy_enroll_batch where global_enroll_batch_id="+globalEnrollBatchId;
		if(school!=-2){
			sql+=" and academy_id="+school;
			return jdbcTemplatePlus.queryForInt(sql)+"";
		}
		
		List<HashMap<String,Long>> list=jdbcTemplatePlus.queryForList(sql);
		if(list!=null){
			for (int i = 0; i < list.size(); i++) {
				Map<String,Long> map=list.get(i);
				if(ids.equals(",")){
					ids=map.get("id")+"";
				}else{
					ids+=","+map.get("id");
				}
			}
		}
		if(ids.equals(","))
			ids="0";
		
		return ids;
	}
	
	final private int getIntValue(Map<String,Integer> map ,String key){
		return map.get(key)!=null?map.get(key):0;
	}
	final private double getDoubleValue(Map<String,Double> map ,String key){
		return map.get(key)!=null?map.get(key):0.00d;
	}
	
	final private String getP(final NumberFormat format,int number1,int number2){
		if(number2==0){
			return "-";
		}
		return format.format(new Float(number1)/ new Float(getDayuOne(getDayuOne(number2))));
	}
	final private int getDayuOne(int number){
		return number==0?1:number;
	}
	final private int getListSum(List<Integer> list){
		int count=0;
		if(list!=null){
			for (Integer c : list) {
				count+=c;
			}
		}
		return count;
	}
	final private double getListSum_(List<Double> list){
		double count=0;
		if(list!=null){
			for (Double c : list) {
				count+=c;
			}
		}
		return count;
	}
}
