/**
 * 文件名：EnrollmentWayReportImpl.java
 * 包名：net.cedu.student.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-26 下午03:48:12
 *
 */
package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.EnrollmentWayReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：学生市场途径报表实现类
 * 
 * @作者： 杨栋栋
 * @作成时间：2011-12-26 下午03:50:01
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Repository
public class EnrollmentWayReportImpl extends BaseReportDaoImpl implements
		EnrollmentWayReport {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;

	private JdbcTemplatePlus jdbcTemplatePlus = null;

	/**
	 * 接口方法实现
	 * 
	 * @see net.cedu.report.dao.EnrollmentSourceReport#statistics(java.util.Map)
	 */
	public List statistics(Map<String, Integer> params,Map<String, String> strParams,Map<String, Date> dateParams) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int school = params.get("school");// 学院
		final int batch = params.get("batch");// 批次
		final int studentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int source = params.get("source");// 学生来源
		final int quyuId = params.get("manager");// 区域经理ID
		final int xuexiId = params.get("branch");// 学习中心
		final int fuwuId = params.get("fuwu");// 服务站ID
		final int userId = params.get("user");// 用户ID
		final String baseDictIds=strParams.get("baseDictIds");//市场途径ID
		
		final Date startDate=dateParams.get("startDate");//开始日期
		final Date endDate=dateParams.get("endDate");//结束日期
		try {
			// long s = System.currentTimeMillis();
			// 生成%数
			final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
			format.setMinimumFractionDigits(2);// 设置小数位

			// 查询所有区域经理ID
			String quyuIdsSql = "select DISTINCT manager_id from tb_r_area_manager_branch where 1=1";
			//市场途径名称
			final List<BaseDict> baseDictList = getEnrollmentWayColumnNames(baseDictIds);
			//获取用户在不同的市场途径对应的学生数量
			final Map<String, Integer> userId_EnrollmentWayId_count = getUserEnrollmentWayStudentCountMap( school,  batch,  studentDataSource,  way,  source, xuexiId, startDate, endDate);
			//获取用户在不同的市场途径对应的学生总数量
			final Map<String, Integer> userId__sum_count = getUserEnrollmentWayStudentSumCountMap( school,  batch,  studentDataSource,  way,  source, xuexiId, startDate, endDate);
			//获取学习中心在不同的市场途径对应的学生数量
			final Map<String, Integer> branchId_EnrollmentWayId_count = getBranchEnrollmentWayStudentCountMap( school,  batch,  studentDataSource,  way,  source, xuexiId, startDate, endDate);
			//获取学习中心在不同的市场途径对应的学生总数量
			final Map<String, Integer> branchId__sum_count = getBranchEnrollmentWayStudentSumCountMap( school,  batch,  studentDataSource,  way,  source, xuexiId, startDate, endDate);
			//获取区域经理在不同的市场途径对应的学生数量
			final Map<String, Integer> managerId_EnrollmentWayId_count = getManagerEnrollmentWayStudentCountMap( school,  batch,  studentDataSource,  way,  source, xuexiId, startDate, endDate);
			//获取区域经理在不同的市场途径对应的学生总数量
			final Map<String, Integer> managerId_sum_count = getManagerEnrollmentWayStudentSumCountMap( school,  batch,  studentDataSource,  way,  source, xuexiId, startDate, endDate);
			
			
			final Map<Integer,List<Integer>> pbranch_cbranch_map= getBranchMap();

			// 默认查询所有
			if (quyuId != -2) {
				quyuIdsSql += " and manager_id=" + quyuId;
			}
			if(xuexiId!=-2){
				quyuIdsSql+=" and branch_id="+xuexiId;
			}
			if(fuwuId!=-2){
				quyuIdsSql+=" and branch_id=(select parent_id from tb_e_branch where id="+fuwuId+")";
			}
			List quyuList = jdbcTemplatePlus.query(quyuIdsSql, new RowMapper() {
				@SuppressWarnings("unchecked")
				public Object mapRow(ResultSet resultSet, int index)
						throws SQLException {
					Map<String, Object> quyuMap = new HashMap<String, Object>();
					// 区域经理ID
					quyuMap.put("quyuId", resultSet.getInt("manager_id"));
					// 区域经理名称
					User user = userDao.findById(resultSet.getInt("manager_id"));
					quyuMap.put("quyuName", user != null ? user.getFullName()
							: "");
					// 区域经理下的所有学习中心
					String xuxiSql = "select DISTINCT branch_id from tb_r_area_manager_branch where manager_id="
							+ resultSet.getInt("manager_id");
					xuxiSql += " and branch_id in (select id from tb_e_branch where delete_flag=0 and parent_id=1)";
					if (xuexiId != -2) {
						xuxiSql += " and branch_id=" + xuexiId;
					}

					if (fuwuId != -2) {
						xuxiSql += " and branch_id=(select parent_id from tb_e_branch where id="
								+ fuwuId + ")";
					}

					if (userId != -2) {
						xuxiSql += " and branch_id=(select org_id from tb_p_e_user where id="
								+ userId + ")";
					}
					
					final int manager_sum_count=getCount(managerId_sum_count, resultSet.getInt("manager_id")+"_");
					
					List xuexiList = jdbcTemplatePlus.query(xuxiSql,
							new RowMapper() {
								public Object mapRow(ResultSet resultSet,
										int index) throws SQLException {
									Map<String, Object> xuexiMap = new HashMap<String, Object>();
									// 学习中心ID
									xuexiMap.put("xuexiId",
											resultSet.getInt("branch_id"));
									// 学习中心名称
									Branch branch = branchDao
											.findById(resultSet
													.getInt("branch_id"));
									xuexiMap.put("xuexiName",
											branch != null ? branch.getName()
													: "");

									// 服务站集合
									String fuwuSql = "select id as fuwuId from tb_e_branch where parent_id="
											+ resultSet.getInt("branch_id");
									if (fuwuId != -2) {
										fuwuSql += " and id=" + fuwuId;
									}
									if (userId != -2) {
										fuwuSql += " and id=(select org_id from tb_p_e_user where id="
												+ userId + ")";
									}
									final int branch_sumcount_=getCount(branchId__sum_count, resultSet.getInt("branch_id")+"_");
									List fuwuList = jdbcTemplatePlus.query(
											fuwuSql, new RowMapper() {
												public Object mapRow(
														ResultSet resultSet,
														int index)
														throws SQLException {
													Map<String, Object> fuwuMap = new HashMap<String, Object>();
													// 服务ID
													fuwuMap.put("fuwuId",resultSet.getInt("fuwuId"));
													// 服务站名称
													Branch branch = branchDao.findById(resultSet.getInt("fuwuId"));
													fuwuMap.put("fuwuName",branch != null ? branch.getName(): "");
													// 服务站下的所有用户
													// 查询所有用户学习中心ID
													// 过滤已删除，停用，root，总部，禁用
//													String userSql = "select u.id as userId,u.full_name as name,org_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="
//															+ resultSet.getInt("fuwuId")+ " and u.status=0 ";
													String userSql = "select u.id as userId,u.full_name as name,org_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="
														+ resultSet.getInt("fuwuId");
													if (userId != -2) {
														userSql += " and u.id="+ userId;
													}
													// 服务站
													if (xuexiId == -2
															&& fuwuId != -2) {
														userSql += " and u.org_id="
																+ fuwuId;
													}
													if (xuexiId != -2
															&& fuwuId != -2) {
														userSql += " and u.org_id="
																+ fuwuId;
													}
													final int branch_sumcount=getCount(branchId__sum_count, resultSet.getInt("fuwuId")+"_");
													List userList = jdbcTemplatePlus
															.query(userSql,
																	new RowMapper() {
																		public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																			int sumcount=getCount(userId__sum_count,resultSet.getInt("userId")+"_");
																			
																			
																			Map<String, Object> userMap = new HashMap<String, Object>();
																			userMap.put("userId",resultSet.getInt("userId"));
																			userMap.put("name",resultSet.getString("name"));
																			if (baseDictList != null) {
																				for (BaseDict baseDict : baseDictList) {
																					int count=getCount(userId_EnrollmentWayId_count,resultSet.getInt("userId")+"_"+baseDict.getId());
																					
																					userMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_count",count);
																					userMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_percentage",getP(format,count,getDayuOne(sumcount)));
																				}
																			}
																			userMap.put("count", sumcount);
																			userMap.put("countP", getP(format,sumcount,getDayuOne(branch_sumcount)));
																			
																			return userMap;
																		}
																	});
													
													if (baseDictList != null) {
														for (BaseDict baseDict : baseDictList) {
															int branchcount=getCount(branchId_EnrollmentWayId_count,resultSet.getInt("fuwuId")+"_"+baseDict.getId());
															fuwuMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_fuwu_count",branchcount);
															fuwuMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_fuwu_percentage",getP(format,branchcount,getDayuOne(branch_sumcount)));
														}
													}
													fuwuMap.put("count", branch_sumcount);
													fuwuMap.put("countP", getP(format,branch_sumcount,getDayuOne(branch_sumcount_)));
													fuwuMap.put("userList",
															userList);
													return fuwuMap;
												}
											});
									
									if (fuwuId == -2) {
										Map<String, Object> fuwuMap = new HashMap<String, Object>();
										// 服务ID
										fuwuMap.put("fuwuId",
												resultSet.getInt("branch_id"));
										Branch zongBuBranch = branchDao
												.findById(resultSet
														.getInt("branch_id"));
										fuwuMap.put(
												"fuwuName",
												zongBuBranch != null ? zongBuBranch
														.getName() + "总部"
														: "");
//										String userSql = "select u.id as userId,u.full_name as name,org_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="
//												+ resultSet.getInt("branch_id")
//												+ " and u.status=0 ";
										String userSql = "select u.id as userId,u.full_name as name,org_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="
											+ resultSet.getInt("branch_id");
										if (userId != -2) {
											userSql += " and u.id=" + userId;
										}
										// 服务站
										if (xuexiId == -2 && fuwuId != -2) {
											userSql += " and u.org_id="
													+ fuwuId;
										}
										if (xuexiId != -2 && fuwuId != -2) {
											userSql += " and u.org_id="
													+ fuwuId;
										}
										final int branch_sumcount=getCount(branchId__sum_count, resultSet.getInt("branch_id")+"_");
										// 服务站下的所有用户
										// 过滤已删除，停用，root，总部，禁用
										List userList = jdbcTemplatePlus.query(
												userSql, new RowMapper() {
													public Object mapRow(
															ResultSet resultSet,
															int index)
															throws SQLException {
														int sumcount=getCount(userId__sum_count,resultSet.getInt("userId")+"_");
														
														Map<String, Object> userMap = new HashMap<String, Object>();
														userMap.put("userId",resultSet.getInt("userId"));
														userMap.put("name",resultSet.getString("name"));
														if (baseDictList != null) {
															for (BaseDict baseDict : baseDictList) {
																int count=getCount(userId_EnrollmentWayId_count,resultSet.getInt("userId")+"_"+baseDict.getId());
																
																userMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_count",count);
																userMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_percentage",getP(format,count,getDayuOne(sumcount)));
															}
														}
														userMap.put("count", sumcount);
														userMap.put("countP", getP(format,sumcount,getDayuOne(branch_sumcount)));
														return userMap;
													}
												});
										if (baseDictList != null) {
											for (BaseDict baseDict : baseDictList) {
												int branchcount=getCount(branchId_EnrollmentWayId_count,resultSet.getInt("branch_id")+"_"+baseDict.getId());
												fuwuMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_fuwu_count",branchcount);
												fuwuMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_fuwu_percentage",getP(format,branchcount,getDayuOne(branch_sumcount)));
											}
										}
										
										fuwuMap.put("count", branch_sumcount);
										fuwuMap.put("countP", getP(format,branch_sumcount,getDayuOne(branch_sumcount_)));
										fuwuMap.put("userList", userList);
										fuwuList.add(0, fuwuMap);
									}

									if (baseDictList != null) {
										List<Integer> branchIdList=pbranch_cbranch_map.get(resultSet.getInt("branch_id"));
										for (BaseDict baseDict : baseDictList) {
											int branchcount=0;
											for (Integer branchId : branchIdList) {
												branchcount+=getCount(branchId_EnrollmentWayId_count,branchId+"_"+baseDict.getId());
											}
											xuexiMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_branch_count",branchcount);
											xuexiMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_branch_percentage",getP(format,branchcount,getDayuOne(branch_sumcount_)));
										}
									}
									
									xuexiMap.put("count", branch_sumcount_);
									xuexiMap.put("countP", getP(format,branch_sumcount_,getDayuOne(manager_sum_count)));
									xuexiMap.put("fuwuList", fuwuList);

									return xuexiMap;
								}
							});
					Collections.sort(xuexiList, new Comparator() {
						public int compare(Object arg0, Object arg1) {
							Comparator cmp = Collator
									.getInstance(java.util.Locale.CHINA);
							String name1 = ((Map) arg0).get("xuexiName")
									.toString();
							String name2 = ((Map) arg1).get("xuexiName")
									.toString();
							return cmp.compare(name1, name2);
						}
					});

					quyuMap.put("xuexiList", xuexiList);
					if (baseDictList != null) {
						for (BaseDict baseDict : baseDictList) {
							int managercount=getCount(managerId_EnrollmentWayId_count,resultSet.getInt("manager_id")+"_"+baseDict.getId());
							
							quyuMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_zong_count", managercount);
							quyuMap.put(PingYingHanZiUtil.getPingYingNameToLowerNew(baseDict.getName())+ "_zong_percentage", getP(format,managercount,getDayuOne(manager_sum_count)));
						}
					}
					quyuMap.put("count", manager_sum_count);
					quyuMap.put("countP", "");

					return quyuMap;
				}
			});
			jdbcTemplatePlus.releaseConnection();
			return quyuList;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}

	}

	/**
	 * 获取市场途径名称
	 * 
	 * @return
	 */
	final public List<BaseDict> getEnrollmentWayColumnNames(String baseDictIds) {
		String sql = "select id,name from tb_e_base_dict where " 
				+" types="+ Constants.BASEDICT_STYLE_ENROLLMENTWAY 
				+" and delete_flag="+ Constants.DELETE_FALSE 
				//+" and baseDictIds in ("+baseDictIds+")"
				+ " order by order_number";
		@SuppressWarnings("unchecked")
		List<BaseDict> list = jdbcTemplatePlus.query(sql, new RowMapper() {
			public BaseDict mapRow(ResultSet resultSet, int index)
					throws SQLException {
				BaseDict baseDict = new BaseDict();
				baseDict.setName(resultSet.getString("name"));
				baseDict.setId(resultSet.getInt("id"));
				return baseDict;
			}
		});
		return list;
	}

	/**
	 * 获取用户在不同的市场途径对应的学生数量
	 * 
	 * @return  key:用户ID_市场途径ID    value:学生数量
	 */
	final public Map<String, Integer> getUserEnrollmentWayStudentCountMap(int school, int batch, int studentDataSource, int way, int source,int xuexiId,Date startDate,Date endDate) {
		String sql = "select user_id,enrollment_way,count(id) as count_ from tb_e_student where user_id<>0 and enrollment_way<>0 ";

		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

		// 批次
		if (batch != -2) {
			sql += " and enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 报名时间
		if(startDate!=null&&endDate!=null){
			sql +=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql +=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql +=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		sql += " and status>="+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
		sql += " and status<="+ Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI;
		sql += " group by user_id,enrollment_way";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("user_id") + "_"+ resultSet.getString("enrollment_way"));
						map.put("value", resultSet.getString("count_"));
						//System.out.println(resultSet.getInt("user_id") + "_"+ resultSet.getString("enrollment_way")+"_____"+resultSet.getString("count_"));
						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	
	/**
	 * 获取用户在不同的市场途径对应的学生总数量
	 * 
	 * @return  key:用户ID_    value:学生数量
	 */
	final public Map<String, Integer> getUserEnrollmentWayStudentSumCountMap(int school, int batch, int studentDataSource, int way, int source,int xuexiId,Date startDate,Date endDate) {
		String sql = "select user_id,count(id) as count_ from tb_e_student where 1=1  and user_id<>0 and enrollment_way<>0 and status>=7 ";

		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

		// 批次
		if (batch != -2) {
			sql += " and enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 报名时间
		if(startDate!=null&&endDate!=null){
			sql +=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql +=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql +=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		sql += " and status>="+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
		sql += " and status<="+ Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI;
		sql += " group by user_id";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("user_id")+"_");
						map.put("value", resultSet.getString("count_"));
						//System.out.println(resultSet.getInt("user_id") + "_"+ resultSet.getString("enrollment_way")+"_____"+resultSet.getString("count_"));
						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	
	/**
	 * 获取学习中心在不同的市场途径对应的学生数量
	 * 
	 * @return  key:学习中心ID_市场途径ID    value:学生数量
	 */
	final public Map<String, Integer> getBranchEnrollmentWayStudentCountMap(int school, int batch, int studentDataSource, int way, int source,int xuexiId,Date startDate,Date endDate) {
		String sql = "select branch_id,enrollment_way,count(id) as count_ from tb_e_student where user_id<>0 and enrollment_way<>0 ";

		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

		// 批次
		if (batch != -2) {
			sql += " and enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 报名时间
		if(startDate!=null&&endDate!=null){
			sql +=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql +=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql +=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		sql += " and status<>"+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING;
		sql += " group by branch_id,enrollment_way";
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("branch_id") + "_"+ resultSet.getString("enrollment_way"));
						map.put("value", resultSet.getString("count_"));
						//System.out.println(resultSet.getInt("user_id") + "_"+ resultSet.getString("enrollment_way")+"_____"+resultSet.getString("count_"));
						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	/**
	 * 获取学习中心在不同的市场途径对应的学生总数量
	 * 
	 * @return  key:学习中心ID_    value:学生数量
	 */
	final public Map<String, Integer> getBranchEnrollmentWayStudentSumCountMap(int school, int batch, int studentDataSource, int way, int source,int xuexiId,Date startDate,Date endDate) {
		String sql = "select branch_id,count(id) as count_ from tb_e_student where user_id<>0 and enrollment_way<>0 ";

		// 院校
		if (school != -2) {
			sql += " and academy_id=" + school;
		}

		// 批次
		if (batch != -2) {
			sql += " and enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and student_data_source=" + studentDataSource;
		}

		// 招生途径
		if (source != -2) {
			sql += " and enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and branch_id=" + xuexiId;
		}
		// 报名时间
		if(startDate!=null&&endDate!=null){
			sql +=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql +=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql +=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		sql += " and status>="+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
		sql += " and status<="+ Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI;
		sql += " group by branch_id";
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("branch_id") + "_");
						map.put("value", resultSet.getString("count_"));
						//System.out.println(resultSet.getInt("user_id") + "_"+ resultSet.getString("enrollment_way")+"_____"+resultSet.getString("count_"));
						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	
	
	/**
	 * 获取区域经理在不同的市场途径对应的学生总数量
	 * 
	 * @return  key:区域经理ID_    value:学生数量
	 */
	final public Map<String, Integer> getManagerEnrollmentWayStudentSumCountMap(int school, int batch, int studentDataSource, int way, int source,int xuexiId,Date startDate,Date endDate) {
		String sql = "select a.manager_id,count(s.id) as count_ from tb_e_student s right join tb_r_area_manager_branch a on a.branch_id=s.branch_id where s.user_id<>0 and s.enrollment_way<>0  ";

		// 院校
		if (school != -2) {
			sql += " and s.academy_id=" + school;
		}

		// 批次
		if (batch != -2) {
			sql += " and s.enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and s.student_data_source=" + studentDataSource;
		}

		// 招生途径
		if (source != -2) {
			sql += " and s.enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and s.enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and s.branch_id=" + xuexiId;
		}
		// 报名时间
		if(startDate!=null&&endDate!=null){
			sql +=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql +=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql +=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		sql += " and s.status>="+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
		sql += " and s.status<="+ Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI;
		sql += " group by a.manager_id";
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("manager_id") + "_");
						map.put("value", resultSet.getString("count_"));
						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	
	/**
	 * 获取区域经理在不同的市场途径对应的学生数量
	 * 
	 * @return  key:区域经理ID_    value:学生数量
	 */
	final public Map<String, Integer> getManagerEnrollmentWayStudentCountMap(int school, int batch, int studentDataSource, int way, int source,int xuexiId,Date startDate,Date endDate) {
		String sql = "select a.manager_id,s.enrollment_way,count(s.id) as count_ from tb_e_student s right join tb_r_area_manager_branch a on a.branch_id=s.branch_id where s.user_id<>0 and s.enrollment_way<>0  ";

		// 院校
		if (school != -2) {
			sql += " and s.academy_id=" + school;
		}

		// 批次
		if (batch != -2) {
			sql += " and s.enrollment_batch_id in ("
					+ getEnrollmentBatchId(school, batch) + ")";
		}
		// 数据来源
		if (studentDataSource != -2) {
			sql += " and s.student_data_source=" + studentDataSource;
		}

		// 招生途径
		if (source != -2) {
			sql += " and s.enrollment_source=" + source;
		}
		// 市场途径
		if (way != -2) {
			sql += " and s.enrollment_way=" + way;
		}
		// 学习中心
		if (xuexiId != -2) {
			sql += " and s.branch_id=" + xuexiId;
		}
		// 报名时间
		if(startDate!=null&&endDate!=null){
			sql +=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql +=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql +=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		sql += " and s.status>="+ Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI;
		sql += " and s.status<="+ Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI;
		sql += " group by a.manager_id,s.enrollment_way";
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("key", resultSet.getInt("manager_id") + "_"+resultSet.getInt("enrollment_way"));
						map.put("value", resultSet.getString("count_"));
						return map;
					}
				});
		if (list != null) {
			Map<String, Integer> resultMap = new HashMap<String, Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(),
						Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	//获取学习中心_对应的下属学习中心的Map集合
	
	final private Map<Integer,List<Integer>> getBranchMap(){
		//查询父级的学习中心
		Map<Integer,List<Integer>> result=new HashMap<Integer,List<Integer>>();
		String psql ="select id from tb_e_branch where levels=1";
		List<Integer> branch_parent_list=jdbcTemplatePlus.queryForList(psql, java.lang.Integer.class);
		if(branch_parent_list!=null){
			for (Integer integer : branch_parent_list) {
				List<Integer> list=new ArrayList<Integer>();
				list.add(integer);
				result.put(integer, list);
			}
		}
		String csql="select id,parent_id from tb_e_branch where levels=2";
		@SuppressWarnings("unchecked")
		List<Map<String,Integer>> pid_cid_list=jdbcTemplatePlus.query(csql,new RowMapper(){
			public Map<String,Integer> mapRow(ResultSet arg0, int arg1) throws SQLException {
				Map<String,Integer> map =new HashMap<String,Integer>();
				map.put("cid", arg0.getInt("id"));
				map.put("pid", arg0.getInt("parent_id"));
				return map;
			}});
		
		if(pid_cid_list!=null){
			for (Map<String, Integer> map : pid_cid_list) {
				int pid=map.get("pid");
				int cid=map.get("cid");
				if(result.containsKey(pid)){
					result.get(pid).add(cid);
				}
			}
		}
		
		return result;
	}
	
	//通过全局批次，以及院校ID查询院校的招生批次Id字符串
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
	final private Integer getCount(Map<String,Integer> map,String key){
		return map.get(key)==null?0:map.get(key);
	}
	final public int getDayuOne(int number){
		return number;
		//return number==0?1:number;
	}
	final private String getP(final NumberFormat format,int number1,int number2){
		if(number2==0){
			return "-";
		}
		return format.format(new Float(number1)/ new Float(getDayuOne(getDayuOne(number2))));
	}
}
