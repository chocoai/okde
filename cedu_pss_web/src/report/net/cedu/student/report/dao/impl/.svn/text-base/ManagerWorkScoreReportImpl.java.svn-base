/**
 * 文件名：ManagerWorkScoreReportImpl.java
 * 包名：net.cedu.student.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：dongminghao
 * 日期：2012-02-09 上午10:13:26
 *
 */
package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.date.DateUtil;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.ManagerWorkScoreReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：工作评分统计表
 * 
 * @作者：董溟浩
 * @作成时间：2012-03-06 下午14:49:39
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Repository
public class ManagerWorkScoreReportImpl extends BaseReportDaoImpl implements
		ManagerWorkScoreReport {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BranchDao branchDao;
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	
	/**
	 * 接口方法实现
	  * @see net.cedu.student.report.dao.ManagerWorkScoreReport#statistics(java.util.Map, java.util.Map)
	 */
	public List statistics(Map<String, Integer> params,Map<String, Date> dateParams) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int school = params.get("school");// 学院
		final int batch = params.get("batch");// 全局批次
		final int serachStudentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int source = params.get("source");// 招生途径
		final int quyuId = params.get("manager");// 区域经理ID
		final int xuexiId = params.get("branch");// 学习中心
		final int fuwuId = params.get("fuwu");// 服务站ID
		final int userId = params.get("user");// 用户ID
		
		final Date startDate=dateParams.get("startDate");//开始日期
		final Date endDate=dateParams.get("endDate");//结束日期
		
		try
		{
			//long s = System.currentTimeMillis();
			//生成%数
//			final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
//			format.setMinimumFractionDigits(2);// 设置小数位
			
			// 查询所有区域经理ID
			String quyuIdsSql = "select DISTINCT manager_id from tb_r_area_manager_branch where 1=1";
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
			
			//查询所有工作人员的工作评分
			final Map<String,Double> workscoreMap = gerUserWorkScoreAvg(startDate,endDate);
//			final Map<String,Double> workscoreMap = new HashMap<String, Double>();
			
			List quyuList = jdbcTemplatePlus.query(quyuIdsSql, new RowMapper() {
				@SuppressWarnings("unchecked")
				public Object mapRow(ResultSet resultSet, int index)
						throws SQLException {
					Map<String, Object> quyuMap = new HashMap<String, Object>();
					// 区域经理ID
					quyuMap.put("quyuId", resultSet.getInt("manager_id"));
					// 区域经理名称
					User user = userDao.findById(resultSet.getInt("manager_id"));
					quyuMap.put("quyuName", user != null ? user.getFullName() : "");
					// 区域经理下的所有学习中心
					String xuxiSql="select DISTINCT branch_id from tb_r_area_manager_branch where manager_id="+ resultSet.getInt("manager_id");
					xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0 and parent_id=1)";
					if(xuexiId!=-2){
						xuxiSql+=" and branch_id="+xuexiId;
					}
					
					if(fuwuId!=-2){
						xuxiSql+=" and branch_id=(select parent_id from tb_e_branch where id="+fuwuId+")";
					}
					
					if(userId!=-2){
						xuxiSql+=" and branch_id=(select org_id from tb_p_e_user where id="+userId+")";
					}
					
					List xuexiList = jdbcTemplatePlus.query(xuxiSql,
							new RowMapper() {
								@SuppressWarnings("unchecked")
								public Object mapRow(ResultSet resultSet, int index)
										throws SQLException {
									Map<String, Object> xuexiMap = new HashMap<String, Object>();
									// 学习中心ID
									xuexiMap.put("xuexiId",resultSet.getInt("branch_id"));
									// 学习中心名称
									Branch branch = branchDao.findById(resultSet.getInt("branch_id"));
									xuexiMap.put("xuexiName",branch != null ? branch.getName() : "");
									// 学习中心下面的服务站
	
									//学生所有数量
									//final int studentCount = jdbcTemplatePlus.queryForInt("select count(*) from tb_e_student");
									//招生批次IDs字符串
									//final String batchIds=getEnrollmentBatchId(school,batch);
									
									//服务站集合
									String fuwuSql="select id as fuwuId from tb_e_branch where parent_id="+ resultSet.getInt("branch_id");
									if(fuwuId!=-2){
										fuwuSql+=" and id="+fuwuId;
									}
									if(userId!=-2){
										fuwuSql+=" and id=(select org_id from tb_p_e_user where id="+userId+")";
									}
									
									List fuwuList = jdbcTemplatePlus.query(fuwuSql,
										new RowMapper(){
										
												public Object mapRow(ResultSet resultSet,int index)throws SQLException {
													Map<String, Object> fuwuMap = new HashMap<String, Object>();
													// 服务ID
													fuwuMap.put("fuwuId", resultSet.getInt("fuwuId"));
													// 服务站名称
													Branch branch = branchDao.findById(resultSet.getInt("fuwuId"));
													fuwuMap.put("fuwuName",branch != null ? branch.getName() : "");
													// 服务站下的所有用户
													// 查询所有用户学习中心ID
													// 过滤已删除，停用，root，总部，禁用
													//String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("fuwuId")+ " and u.status=0 ";
													String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("fuwuId");
													if(userId!=-2){
														userSql+=" and u.id="+userId;
													}
													//服务站
													if(xuexiId==-2&&fuwuId!=-2){
														userSql+=" and u.org_id="+fuwuId;
													}
													if(xuexiId!=-2&&fuwuId!=-2){
														userSql+=" and u.org_id="+fuwuId;
													}
//													if(xuexiId!=-2&&fuwuId==-2){
//														userSql+=" and u.org_id="+xuexiId;
//													}
													List userList = jdbcTemplatePlus.query(userSql,
															new RowMapper() {
																DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
																
																
																public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																	Map<String, Object> userMap = new HashMap<String, Object>();
																	userMap.put("userId",resultSet.getInt("userId"));
																	userMap.put("name",resultSet.getString("name"));
																	//1月工作平均分
																	double userScoreYi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_01");
																	userMap.put("userScoreYi", userScoreYi);
																	//2月工作平均分
																	double userScoreEr=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_02");
																	userMap.put("userScoreEr", userScoreEr);
																	//3月工作平均分
																	double userScoreSan=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_03");
																	userMap.put("userScoreSan", userScoreSan);
																	//4月工作平均分
																	double userScoreSi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_04");
																	userMap.put("userScoreSi", userScoreSi);
																	//5月工作平均分
																	double userScoreWu=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_05");
																	userMap.put("userScoreWu", userScoreWu);
																	//6月工作平均分
																	double userScoreLiu=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_06");
																	userMap.put("userScoreLiu", userScoreLiu);
																	//7月工作平均分
																	double userScoreQi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_07");
																	userMap.put("userScoreQi", userScoreQi);
																	//8月工作平均分
																	double userScoreBa=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_08");
																	userMap.put("userScoreBa", userScoreBa);
																	//9月工作平均分
																	double userScoreJiu=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_09");
																	userMap.put("userScoreJiu", userScoreJiu);
																	//10月工作平均分
																	double userScoreShi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_10");
																	userMap.put("userScoreShi", userScoreShi);
																	//11月工作平均分
																	double userScoreSY=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_11");
																	userMap.put("userScoreSY", userScoreSY);
																	//12月工作平均分
																	double userScoreSE=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_12");
																	userMap.put("userScoreSE", userScoreSE);
																	//总平均分
																	double userAvg = Double.parseDouble(df.format((userScoreYi+userScoreEr+userScoreSan+userScoreSi+
																			userScoreWu+userScoreLiu+userScoreQi+userScoreBa+
																			userScoreJiu+userScoreShi+userScoreSY+userScoreSE)/12.0));
																	userMap.put("userAvg",userAvg);
																	return userMap;
																}
													});
													DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
													fuwuMap.put("userList",userList);
													//服务站合计
													Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
													//评分合计
													double userScoreYiSum=0;
													double userScoreErSum=0;
													double userScoreSanSum=0;
													double userScoreSiSum=0;
													double userScoreWuSum=0;
													double userScoreLiuSum=0;
													double userScoreQiSum=0;
													double userScoreBaSum=0;
													double userScoreJiuSum=0;
													double userScoreShiSum=0;
													double userScoreSYSum=0;
													double userScoreSESum=0;
													
													for (Object object : userList) {
														Map<String, Object> map=(Map<String, Object>)object;
														double userScoreYi=Double.valueOf(map.get("userScoreYi").toString());
														double userScoreEr=Double.valueOf(map.get("userScoreEr").toString());
														double userScoreSan=Double.valueOf(map.get("userScoreSan").toString());
														double userScoreSi=Double.valueOf(map.get("userScoreSi").toString());
														double userScoreWu=Double.valueOf(map.get("userScoreWu").toString());
														double userScoreLiu=Double.valueOf(map.get("userScoreLiu").toString());
														double userScoreQi=Double.valueOf(map.get("userScoreQi").toString());
														double userScoreBa=Double.valueOf(map.get("userScoreBa").toString());
														double userScoreJiu=Double.valueOf(map.get("userScoreJiu").toString());
														double userScoreShi=Double.valueOf(map.get("userScoreShi").toString());
														double userScoreSY=Double.valueOf(map.get("userScoreSY").toString());
														double userScoreSE=Double.valueOf(map.get("userScoreSE").toString());
														
														userScoreYiSum += userScoreYi;
														userScoreErSum += userScoreEr;
														userScoreSanSum += userScoreSan;
														userScoreSiSum += userScoreSi;
														userScoreWuSum += userScoreWu;
														userScoreLiuSum += userScoreLiu;
														userScoreQiSum += userScoreQi;
														userScoreBaSum += userScoreBa;
														userScoreJiuSum += userScoreJiu;
														userScoreShiSum += userScoreShi;
														userScoreSYSum += userScoreSY;
														userScoreSESum += userScoreSE;
													}
													
													fuwuzhanHeJiMap.put("userScoreYiSum", Double.parseDouble(df.format(userScoreYiSum)));
													fuwuzhanHeJiMap.put("userScoreErSum", Double.parseDouble(df.format(userScoreErSum)));
													fuwuzhanHeJiMap.put("userScoreSanSum", Double.parseDouble(df.format(userScoreSanSum)));
													fuwuzhanHeJiMap.put("userScoreSiSum", Double.parseDouble(df.format(userScoreSiSum)));
													fuwuzhanHeJiMap.put("userScoreWuSum", Double.parseDouble(df.format(userScoreWuSum)));
													fuwuzhanHeJiMap.put("userScoreLiuSum", Double.parseDouble(df.format(userScoreLiuSum)));
													fuwuzhanHeJiMap.put("userScoreQiSum", Double.parseDouble(df.format(userScoreQiSum)));
													fuwuzhanHeJiMap.put("userScoreBaSum", Double.parseDouble(df.format(userScoreBaSum)));
													fuwuzhanHeJiMap.put("userScoreJiuSum", Double.parseDouble(df.format(userScoreJiuSum)));
													fuwuzhanHeJiMap.put("userScoreShiSum", Double.parseDouble(df.format(userScoreShiSum)));
													fuwuzhanHeJiMap.put("userScoreSYSum", Double.parseDouble(df.format(userScoreSYSum)));
													fuwuzhanHeJiMap.put("userScoreSESum", Double.parseDouble(df.format(userScoreSESum)));
													
													double avg = Double.parseDouble(df.format((userScoreYiSum+userScoreErSum+userScoreSanSum+userScoreSiSum+
															userScoreWuSum+userScoreLiuSum+userScoreQiSum+userScoreBaSum+
															userScoreJiuSum+userScoreShiSum+userScoreSYSum+userScoreSESum)/12.0));
													fuwuzhanHeJiMap.put("hejiScoreAvg",avg);
													
													fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
													return fuwuMap;
												}
									});
									if(fuwuId==-2){
										Map<String, Object> fuwuMap = new HashMap<String, Object>();
										// 服务ID
										fuwuMap.put("fuwuId",resultSet.getInt("branch_id"));
										Branch zongBuBranch = branchDao.findById(resultSet.getInt("branch_id"));
										fuwuMap.put("fuwuName",zongBuBranch != null ? zongBuBranch.getName() + "总部" : "");
										
//										String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id")+ " and u.status=0 ";
										String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id");
										if(userId!=-2){
											userSql+=" and u.id="+userId;
										}
										//服务站
										if(xuexiId==-2&&fuwuId!=-2){
											userSql+=" and u.org_id="+fuwuId;
										}
										if(xuexiId!=-2&&fuwuId!=-2){
											userSql+=" and u.org_id="+fuwuId;
										}
//										if(xuexiId!=-2&&fuwuId==-2){
//											userSql+=" and u.org_id="+xuexiId;
//										}
										// 服务站下的所有用户
										// 过滤已删除，停用，root，总部，禁用
										List userList = jdbcTemplatePlus.query(userSql,
														new RowMapper() {
															DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
															public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																Map<String, Object> userMap = new HashMap<String, Object>();
																userMap.put("userId",resultSet.getInt("userId"));
																userMap.put("name",resultSet.getString("name"));
																//1月工作平均分
																double userScoreYi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_01");
																userMap.put("userScoreYi", userScoreYi);
																//2月工作平均分
																double userScoreEr=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_02");
																userMap.put("userScoreEr", userScoreEr);
																//3月工作平均分
																double userScoreSan=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_03");
																userMap.put("userScoreSan", userScoreSan);
																//4月工作平均分
																double userScoreSi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_04");
																userMap.put("userScoreSi", userScoreSi);
																//5月工作平均分
																double userScoreWu=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_05");
																userMap.put("userScoreWu", userScoreWu);
																//6月工作平均分
																double userScoreLiu=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_06");
																userMap.put("userScoreLiu", userScoreLiu);
																//7月工作平均分
																double userScoreQi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_07");
																userMap.put("userScoreQi", userScoreQi);
																//8月工作平均分
																double userScoreBa=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_08");
																userMap.put("userScoreBa", userScoreBa);
																//9月工作平均分
																double userScoreJiu=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_09");
																userMap.put("userScoreJiu", userScoreJiu);
																//10月工作平均分
																double userScoreShi=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_10");
																userMap.put("userScoreShi", userScoreShi);
																//11月工作平均分
																double userScoreSY=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_11");
																userMap.put("userScoreSY", userScoreSY);
																//12月工作平均分
																double userScoreSE=getDoubleByMap(workscoreMap,resultSet.getInt("userId")+"_12");
																userMap.put("userScoreSE", userScoreSE);
																//总平均分
																double userAvg = Double.parseDouble(df.format((userScoreYi+userScoreEr+userScoreSan+userScoreSi+
																		userScoreWu+userScoreLiu+userScoreQi+userScoreBa+
																		userScoreJiu+userScoreShi+userScoreSY+userScoreSE)/12.0));
																userMap.put("userAvg",userAvg);
																return userMap;
															}
														});
										fuwuMap.put("userList", userList);
										DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
										//服务站合计
										Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
										//评分合计
										double userScoreYiSum=0;
										double userScoreErSum=0;
										double userScoreSanSum=0;
										double userScoreSiSum=0;
										double userScoreWuSum=0;
										double userScoreLiuSum=0;
										double userScoreQiSum=0;
										double userScoreBaSum=0;
										double userScoreJiuSum=0;
										double userScoreShiSum=0;
										double userScoreSYSum=0;
										double userScoreSESum=0;
										
										for (Object object : userList) {
											Map<String, Object> map=(Map<String, Object>)object;
											double userScoreYi=Double.valueOf(map.get("userScoreYi").toString());
											double userScoreEr=Double.valueOf(map.get("userScoreEr").toString());
											double userScoreSan=Double.valueOf(map.get("userScoreSan").toString());
											double userScoreSi=Double.valueOf(map.get("userScoreSi").toString());
											double userScoreWu=Double.valueOf(map.get("userScoreWu").toString());
											double userScoreLiu=Double.valueOf(map.get("userScoreLiu").toString());
											double userScoreQi=Double.valueOf(map.get("userScoreQi").toString());
											double userScoreBa=Double.valueOf(map.get("userScoreBa").toString());
											double userScoreJiu=Double.valueOf(map.get("userScoreJiu").toString());
											double userScoreShi=Double.valueOf(map.get("userScoreShi").toString());
											double userScoreSY=Double.valueOf(map.get("userScoreSY").toString());
											double userScoreSE=Double.valueOf(map.get("userScoreSE").toString());
											
											userScoreYiSum += userScoreYi;
											userScoreErSum += userScoreEr;
											userScoreSanSum += userScoreSan;
											userScoreSiSum += userScoreSi;
											userScoreWuSum += userScoreWu;
											userScoreLiuSum += userScoreLiu;
											userScoreQiSum += userScoreQi;
											userScoreBaSum += userScoreBa;
											userScoreJiuSum += userScoreJiu;
											userScoreShiSum += userScoreShi;
											userScoreSYSum += userScoreSY;
											userScoreSESum += userScoreSE;
										}
										
										fuwuzhanHeJiMap.put("userScoreYiSum", Double.parseDouble(df.format(userScoreYiSum)));
										fuwuzhanHeJiMap.put("userScoreErSum", Double.parseDouble(df.format(userScoreErSum)));
										fuwuzhanHeJiMap.put("userScoreSanSum", Double.parseDouble(df.format(userScoreSanSum)));
										fuwuzhanHeJiMap.put("userScoreSiSum", Double.parseDouble(df.format(userScoreSiSum)));
										fuwuzhanHeJiMap.put("userScoreWuSum", Double.parseDouble(df.format(userScoreWuSum)));
										fuwuzhanHeJiMap.put("userScoreLiuSum", Double.parseDouble(df.format(userScoreLiuSum)));
										fuwuzhanHeJiMap.put("userScoreQiSum", Double.parseDouble(df.format(userScoreQiSum)));
										fuwuzhanHeJiMap.put("userScoreBaSum", Double.parseDouble(df.format(userScoreBaSum)));
										fuwuzhanHeJiMap.put("userScoreJiuSum", Double.parseDouble(df.format(userScoreJiuSum)));
										fuwuzhanHeJiMap.put("userScoreShiSum", Double.parseDouble(df.format(userScoreShiSum)));
										fuwuzhanHeJiMap.put("userScoreSYSum", Double.parseDouble(df.format(userScoreSYSum)));
										fuwuzhanHeJiMap.put("userScoreSESum", Double.parseDouble(df.format(userScoreSESum)));
										
										double avg = Double.parseDouble(df.format((userScoreYiSum+userScoreErSum+userScoreSanSum+userScoreSiSum+
												userScoreWuSum+userScoreLiuSum+userScoreQiSum+userScoreBaSum+
												userScoreJiuSum+userScoreShiSum+userScoreSYSum+userScoreSESum)/12.0));
										fuwuzhanHeJiMap.put("hejiScoreAvg",avg);
										
										fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
										fuwuList.add(0, fuwuMap);
										}
									xuexiMap.put("fuwuList", fuwuList);
									DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
									//服务站合计
									Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
									//评分合计
									double userScoreYiSum=0;
									double userScoreErSum=0;
									double userScoreSanSum=0;
									double userScoreSiSum=0;
									double userScoreWuSum=0;
									double userScoreLiuSum=0;
									double userScoreQiSum=0;
									double userScoreBaSum=0;
									double userScoreJiuSum=0;
									double userScoreShiSum=0;
									double userScoreSYSum=0;
									double userScoreSESum=0;
									
									for (Object object : fuwuList) {
										Map<String, Object> map=(Map<String, Object>)object;
										double userScoreYi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreYiSum").toString());
										double userScoreEr=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreErSum").toString());
										double userScoreSan=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreSanSum").toString());
										double userScoreSi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreSiSum").toString());
										double userScoreWu=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreWuSum").toString());
										double userScoreLiu=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreLiuSum").toString());
										double userScoreQi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreQiSum").toString());
										double userScoreBa=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreBaSum").toString());
										double userScoreJiu=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreJiuSum").toString());
										double userScoreShi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreShiSum").toString());
										double userScoreSY=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreSYSum").toString());
										double userScoreSE=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userScoreSESum").toString());
										
										userScoreYiSum += userScoreYi;
										userScoreErSum += userScoreEr;
										userScoreSanSum += userScoreSan;
										userScoreSiSum += userScoreSi;
										userScoreWuSum += userScoreWu;
										userScoreLiuSum += userScoreLiu;
										userScoreQiSum += userScoreQi;
										userScoreBaSum += userScoreBa;
										userScoreJiuSum += userScoreJiu;
										userScoreShiSum += userScoreShi;
										userScoreSYSum += userScoreSY;
										userScoreSESum += userScoreSE;
									}
									fuwuzhanHeJiMap.put("x_userScoreYiSum", Double.parseDouble(df.format(userScoreYiSum)));
									fuwuzhanHeJiMap.put("x_userScoreErSum", Double.parseDouble(df.format(userScoreErSum)));
									fuwuzhanHeJiMap.put("x_userScoreSanSum", Double.parseDouble(df.format(userScoreSanSum)));
									fuwuzhanHeJiMap.put("x_userScoreSiSum", Double.parseDouble(df.format(userScoreSiSum)));
									fuwuzhanHeJiMap.put("x_userScoreWuSum", Double.parseDouble(df.format(userScoreWuSum)));
									fuwuzhanHeJiMap.put("x_userScoreLiuSum", Double.parseDouble(df.format(userScoreLiuSum)));
									fuwuzhanHeJiMap.put("x_userScoreQiSum", Double.parseDouble(df.format(userScoreQiSum)));
									fuwuzhanHeJiMap.put("x_userScoreBaSum", Double.parseDouble(df.format(userScoreBaSum)));
									fuwuzhanHeJiMap.put("x_userScoreJiuSum", Double.parseDouble(df.format(userScoreJiuSum)));
									fuwuzhanHeJiMap.put("x_userScoreShiSum", Double.parseDouble(df.format(userScoreShiSum)));
									fuwuzhanHeJiMap.put("x_userScoreSYSum", Double.parseDouble(df.format(userScoreSYSum)));
									fuwuzhanHeJiMap.put("x_userScoreSESum", Double.parseDouble(df.format(userScoreSESum)));
									
									double avg = Double.parseDouble(df.format((userScoreYiSum+userScoreErSum+userScoreSanSum+userScoreSiSum+
											userScoreWuSum+userScoreLiuSum+userScoreQiSum+userScoreBaSum+
											userScoreJiuSum+userScoreShiSum+userScoreSYSum+userScoreSESum)/12.0));
									fuwuzhanHeJiMap.put("x_hejiScoreAvg",avg);
									
									xuexiMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
									return xuexiMap;
								}
							});
					
					Collections.sort(xuexiList, new Comparator() {
						public int compare(Object arg0, Object arg1) {
							Comparator cmp = Collator
									.getInstance(java.util.Locale.CHINA);
							String name1 = ((Map) arg0).get("xuexiName").toString();
							String name2 = ((Map) arg1).get("xuexiName").toString();
							return cmp.compare(name1, name2);
						}
					});
					quyuMap.put("xuexiList", xuexiList);
					DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
					//服务站合计
					Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
					//评分合计
					double userScoreYiSum=0;
					double userScoreErSum=0;
					double userScoreSanSum=0;
					double userScoreSiSum=0;
					double userScoreWuSum=0;
					double userScoreLiuSum=0;
					double userScoreQiSum=0;
					double userScoreBaSum=0;
					double userScoreJiuSum=0;
					double userScoreShiSum=0;
					double userScoreSYSum=0;
					double userScoreSESum=0;
					
					for (Object object : xuexiList) {
						Map<String, Object> map=(Map<String, Object>)object;
						double userScoreYi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreYiSum").toString());
						double userScoreEr=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreErSum").toString());
						double userScoreSan=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreSanSum").toString());
						double userScoreSi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreSiSum").toString());
						double userScoreWu=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreWuSum").toString());
						double userScoreLiu=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreLiuSum").toString());
						double userScoreQi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreQiSum").toString());
						double userScoreBa=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreBaSum").toString());
						double userScoreJiu=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreJiuSum").toString());
						double userScoreShi=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreShiSum").toString());
						double userScoreSY=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreSYSum").toString());
						double userScoreSE=Double.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userScoreSESum").toString());
						
						userScoreYiSum += userScoreYi;
						userScoreErSum += userScoreEr;
						userScoreSanSum += userScoreSan;
						userScoreSiSum += userScoreSi;
						userScoreWuSum += userScoreWu;
						userScoreLiuSum += userScoreLiu;
						userScoreQiSum += userScoreQi;
						userScoreBaSum += userScoreBa;
						userScoreJiuSum += userScoreJiu;
						userScoreShiSum += userScoreShi;
						userScoreSYSum += userScoreSY;
						userScoreSESum += userScoreSE;
					}
					fuwuzhanHeJiMap.put("z_x_userScoreYiSum", Double.parseDouble(df.format(userScoreYiSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreErSum", Double.parseDouble(df.format(userScoreErSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreSanSum", Double.parseDouble(df.format(userScoreSanSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreSiSum", Double.parseDouble(df.format(userScoreSiSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreWuSum", Double.parseDouble(df.format(userScoreWuSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreLiuSum", Double.parseDouble(df.format(userScoreLiuSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreQiSum", Double.parseDouble(df.format(userScoreQiSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreBaSum", Double.parseDouble(df.format(userScoreBaSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreJiuSum", Double.parseDouble(df.format(userScoreJiuSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreShiSum", Double.parseDouble(df.format(userScoreShiSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreSYSum", Double.parseDouble(df.format(userScoreSYSum)));
					fuwuzhanHeJiMap.put("z_x_userScoreSESum", Double.parseDouble(df.format(userScoreSESum)));
					
					double avg = Double.parseDouble(df.format((userScoreYiSum+userScoreErSum+userScoreSanSum+userScoreSiSum+
							userScoreWuSum+userScoreLiuSum+userScoreQiSum+userScoreBaSum+
							userScoreJiuSum+userScoreShiSum+userScoreSYSum+userScoreSESum)/12.0));
					fuwuzhanHeJiMap.put("z_x_hejiScoreAvg",avg);
					
					quyuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
					return quyuMap;
				}
			});
			jdbcTemplatePlus.releaseConnection();
			//long e = System.currentTimeMillis();
			//System.out.println("倒入数据用时：" + (e - s));
			return quyuList;
		}
		catch(Exception e){
			e.printStackTrace();
			return new ArrayList();
		}
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
	
	//通过用户ID，时间段，查询工作评分
	final public Map<String,Double> gerUserWorkScoreAvg(Date startDate, Date endDate){
		String sql="select IFNULL(avg(score),0) as avg , DATE_FORMAT(create_on,'%m') as month , create_by as userid from tb_e_worklog where 1=1 ";
		
		//时间
		if(startDate!=null&&endDate!=null){
			sql+=" and create_on between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
		}
		if(startDate!=null&&endDate==null){
			sql+=" and create_on >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+"'";
		}
		if(startDate==null&&endDate!=null){
			sql+=" and create_on <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+"'";
		}
		sql+="group by DATE_FORMAT(create_on,'%m'),create_by";
		
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("userid")+"_"+resultSet.getString("month"));
				map.put("value", resultSet.getString("avg"));
				return map;
			}
		});
		if(list!=null && list.size()>0){
			Map<String,Double> resultMap =new HashMap<String,Double>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(), map.get("value")!=null?Double.valueOf(map.get("value").toString()):0);
				//System.out.println(map.get("key")+":"+Double.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Double>();
	}
	
	final public double getDoubleByMap( Map<String,Double> map,String key){
		//System.out.println(key);
		DecimalFormat df = new DecimalFormat("0.00");//设置2位小数（四舍五入）
		return map.get(key)!=null?Double.valueOf(df.format(map.get(key))):0;
	}

}
