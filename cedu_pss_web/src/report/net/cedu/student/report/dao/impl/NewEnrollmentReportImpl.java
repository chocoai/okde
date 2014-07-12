/**
 * 文件名：NewEnrollmentReportImpl.java
 * 包名：net.cedu.student.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-29 上午09:58:47
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

import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.NewEnrollmentReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：新生招生报表实现类
 * 
 * @作者： 杨栋栋
 * @作成时间：2011-12-29 上午09:59:25
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Repository
public class NewEnrollmentReportImpl extends BaseReportDaoImpl implements
		NewEnrollmentReport {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private UGroupUserDao uGroupUserDao;
	
	@Autowired
	private BranchBiz branchBiz;
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	
	private Map<String,Integer> userId_jiaofeiCount_map=new HashMap<String,Integer>();
	
	private Map<String,Integer> userId_jiaofeiCount_map_leiji=new HashMap<String,Integer>();
	

	/**
	 * 接口方法实现
	  * @see net.cedu.student.report.dao.NewEnrollmentReport#statistics(java.util.Map, java.util.Map)
	 */
	public Map statistics(Map<String, Integer> params,Map<String, Date> dateParams) {
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
		
		final int searchType=params.get("searchType")==null?1:params.get("searchType");//查询类型 1,所有学习中心，以及服务站    2,只查询学习中心   3,只查询服务站
		
		
		try{
				long s = System.currentTimeMillis();
				//招生批次IDs字符串
				final String batchIds=getEnrollmentBatchId(school,batch);
				//key ：用户ID_学习中心ID   value：学生缴费总数
				userId_jiaofeiCount_map=getDateJiaofeiStudentCount( school, batchIds,serachStudentDataSource ,way, source, startDate, endDate);
				userId_jiaofeiCount_map_leiji=getJiaofeiStudentCount( school, batchIds,serachStudentDataSource ,way, source, startDate, endDate);
				
				final Map<String,String> branchId_zhongxinzhurenName_map=uGroupUserDao.findAllBranchDirector();
				
				// 获取学习中心及其下属服务站id
				String branchIds = "";
				if(xuexiId!=-2){
					try {
						List<Branch> branchList = branchBiz.findListById(xuexiId);
						if(branchList!=null && branchList.size()>0){
							for(Branch branch : branchList){
								if(branchIds.equals("")){
									branchIds = branch.getId()+"";
								}else{
									branchIds += ","+branch.getId();
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				//学习中心ID_＝招生指标数
				final Map<String,Integer> key_b_value_zhaoshengzhibiao_map=initZhaoShengZhiBiaoMap(batch, branchIds);
				
				
				//生成%数
				final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
				format.setMinimumFractionDigits(2);// 设置小数位
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
				
				
				//总合计变量
				final List<Integer> zhibiaoSumList=new ArrayList<Integer>();
				final List<Integer> dateBaomingSumList=new ArrayList<Integer>();
				final List<Integer> leijiBaomingSumList=new ArrayList<Integer>();
				final List<Integer> dateLuquSumList=new ArrayList<Integer>();
				final List<Integer> leijiLuquSumList=new ArrayList<Integer>();
				final List<Integer> dateJiaofeiSumList=new ArrayList<Integer>();
				final List<Integer> leijiJiaofeiSumList=new ArrayList<Integer>();
				
				List quyuList = jdbcTemplatePlus.query(quyuIdsSql, new RowMapper() {
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
						if(searchType==1){
							xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0 and parent_id=1)";
						}else if(searchType==2){
							xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0 and parent_id=1)";
						}else if(searchType==3){
							xuxiSql+= " and branch_id in (select parent_id from tb_e_branch where delete_flag=0 and parent_id>1)";
						}
						
						if(xuexiId!=-2){
							//xuxiSql+=" and branch_id="+xuexiId;
							// 如果单独查学习中心，则有可能是服务站，所以排除parent_id=1参数
							xuxiSql="select DISTINCT branch_id from tb_r_area_manager_branch where manager_id="+ resultSet.getInt("manager_id");
							if(searchType==1){
								xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0)";
							}else if(searchType==2){
								xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0)";
							}else if(searchType==3){
								xuxiSql+= " and branch_id in (select parent_id from tb_e_branch where delete_flag=0 and parent_id>1)";
							}
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
										xuexiMap.put("zhurenName", branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+"")==null?"无":branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+""));
										
										
										// 学习中心下面的服务站
		
										//学生所有数量
										//final int studentCount = jdbcTemplatePlus.queryForInt("select count(*) from tb_e_student");
										
										//服务站集合
										String fuwuSql="select id as fuwuId from tb_e_branch where parent_id="+ resultSet.getInt("branch_id");
										if(fuwuId!=-2){
											fuwuSql+=" and id="+fuwuId;
										}
										if(userId!=-2){
											fuwuSql+=" and id=(select org_id from tb_p_e_user where id="+userId+")";
										}
										List fuwuList = jdbcTemplatePlus.query(fuwuSql,
												new RowMapper() {
													
													public Object mapRow(ResultSet resultSet,int index)throws SQLException {
														Map<String, Object> fuwuMap = new HashMap<String, Object>();
														// 服务ID
														fuwuMap.put("fuwuId", resultSet.getInt("fuwuId"));
														// 服务站名称
														Branch branch = branchDao.findById(resultSet.getInt("fuwuId"));
														fuwuMap.put("fuwuName",branch != null ? branch.getName() : "");
														fuwuMap.put("zhurenName", branchId_zhongxinzhurenName_map.get(resultSet.getInt("fuwuId")+"")==null?"无":branchId_zhongxinzhurenName_map.get(resultSet.getInt("fuwuId")+""));
														
														// 服务站下的所有用户
														// 查询所有用户学习中心ID
														// 过滤已删除，停用，root，总部，禁用
														//String userSql="select u.id as userId,u.full_name as name,u.org_id as branch_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("fuwuId")+ " and u.status=0 ";
														String userSql="select u.id as userId,u.full_name as name,u.org_id as branch_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("fuwuId");
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
//														if(xuexiId!=-2&&fuwuId==-2){
//															userSql+=" and u.org_id="+xuexiId;
//														}
														
														List userList = jdbcTemplatePlus.query(userSql,
																		new RowMapper() {
																			//获取招生指标
																			public int gerUserEnrollQuota(int userId){
																				return gerUserEnrollQuotaSum(school,batch,xuexiId,userId);
																			}
																			//报名人数
																			public int getBaoMingCountByUserId(int branchId,int userId){
																				return getBaoMingCount(school, branchId,batchIds, serachStudentDataSource, way, source,startDate,endDate,userId);
																			}
																			//累计报名人数
																			public int getLeiJiBaoMingCountByUserId(int branchId,int userId){
																				return getBaoMingCount(school, branchId,batchIds, serachStudentDataSource, way, source,null,null,userId);
																			}
																			//录取人数
																			public int getLuQuCountByUserId(int branchId,int userId){
																				return getLuQuCount(school, branchId,batchIds, serachStudentDataSource, way, source,startDate,endDate,userId);
																			}
																			//累计录取人数
																			public int getLeiJiLuQuCountByUserId(int branchId,int userId){
																				return getLuQuCount(school, branchId,batchIds, serachStudentDataSource, way, source,null,null,userId);
																			}
																			//缴费人数
																			public int getJiaoFeiCountByUserId(int branchId,int userId){
																				return getJiaoFeiCount(userId, branchId);
																			}
																			//累计缴费人数
																			public int getLeiJiJiaoFeiCountByUserId(int branchId,int userId){
																				return getLeijiJiaoFeiCount(userId, branchId);
																			}
																			public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																				Map<String, Object> userMap = new HashMap<String, Object>();
																				int userId=resultSet.getInt("userId");
																				userMap.put("userId",userId);
																				userMap.put("name",resultSet.getString("name"));
																				//用户对应的招生指标，新生报名情况，新生录取情况，，新生缴费情况
																				
																				//招生指标
																				int userZhaoShengZhiBiao=gerUserEnrollQuota(userId);
																				userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																				//时间段内报名人数
																				int dateBaoMingCount=getBaoMingCountByUserId(resultSet.getInt("branch_id"),userId);
																				userMap.put("dateBaoMingCount",dateBaoMingCount);
																				//累计报名人数
																				int leijiBaoMingCount=getLeiJiBaoMingCountByUserId(resultSet.getInt("branch_id"),userId);
																				userMap.put("leijiBaoMingCount",leijiBaoMingCount);
																				//累计完成率
																				if(userZhaoShengZhiBiao!=0){
																					userMap.put("leijiBaoMingCountP", format.format(new Float(leijiBaoMingCount)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiao)))));
																				}else{
																					userMap.put("leijiBaoMingCountP", "-");
																				}
																				
																				
																				//时间段内录取人数
																				int dateLuQuCount=getLuQuCountByUserId(resultSet.getInt("branch_id"),userId);
																				userMap.put("dateLuQuCount",dateLuQuCount);
																				//累计录取人数
																				int leijiLuQuCount=getLeiJiLuQuCountByUserId(resultSet.getInt("branch_id"),userId);
																				userMap.put("leijiLuQuCount",leijiLuQuCount);
																				//累计完成率
																				if(leijiBaoMingCount!=0){
																					userMap.put("leijiLuQuCountP", format.format(new Float(leijiLuQuCount)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCount)))));
																				}else{
																					userMap.put("leijiLuQuCountP", "-");
																				}
																				
																				
																				
																				//时间段内缴费人数
																				int dateJiaoFeiCount=getJiaoFeiCountByUserId(resultSet.getInt("branch_id"),userId);
																				userMap.put("dateJiaoFeiCount",dateJiaoFeiCount);
																				//累计缴费人数
																				int leijiJiaoFeiCount=getLeiJiJiaoFeiCountByUserId(resultSet.getInt("branch_id"),userId);
																				userMap.put("leijiJiaoFeiCount",leijiJiaoFeiCount);
																				//累计完成率
																				if(leijiBaoMingCount!=0){
																					userMap.put("leijiJiaoFeiCountP", format.format(new Float(leijiJiaoFeiCount)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCount)))));
																				}else{
																					userMap.put("leijiJiaoFeiCountP", "-");
																				}
																				
																				
																				
																				
																				
																				
																				return userMap;
																			}
																		});
														//时间段内报名排名
														sort(userList,"dateBaoMingCount");
														//累计报名人数排名
														sort(userList,"leijiBaoMingCount");
														//累计完成率排名
														sortP(userList,"leijiBaoMingCountP");
														
														//时间段内录取人数 
														sort(userList,"dateLuQuCount");
														//累计录取人数排名
														sort(userList,"leijiLuQuCount");
														//累计完成率排名
														sortP(userList,"leijiLuQuCountP");
														
														//时间段内缴费排名 
														sort(userList,"dateJiaoFeiCount");
														//累计缴费人数排名
														sort(userList,"leijiJiaoFeiCount");
														//累计完成率排名
														sortP(userList,"leijiJiaoFeiCountP");
														
														Collections.sort(userList, new Comparator() {
															public int compare(Object arg0, Object arg1) {
																Comparator cmp = Collator
																		.getInstance(java.util.Locale.CHINA);
																String name1 = ((Map) arg0).get("name").toString();
																String name2 = ((Map) arg1).get("name").toString();
																return cmp.compare(name1, name2);
															}
														});
														
														fuwuMap.put("userList",userList);
														//服务站合计
														Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
														int userZhaoShengZhiBiaoSum=0;
														
														int dateBaoMingCountSum=0;
														int leijiBaoMingCountSum=0;
														
														int dateLuQuCountSum=0;
														int leijiLuQuCountSum=0;
														
														int dateJiaoFeiCountSum=0;
														int leijiJiaoFeiCountSum=0;
														
														for (Object object : userList) {
															Map userObject=(Map)object;
															userZhaoShengZhiBiaoSum+=Integer.parseInt(userObject.get("userZhaoShengZhiBiao").toString());
															dateBaoMingCountSum+=Integer.parseInt(userObject.get("dateBaoMingCount").toString());
															leijiBaoMingCountSum+=Integer.parseInt(userObject.get("leijiBaoMingCount").toString());
															dateLuQuCountSum+=Integer.parseInt(userObject.get("dateLuQuCount").toString());
															leijiLuQuCountSum+=Integer.parseInt(userObject.get("leijiLuQuCount").toString());
															dateJiaoFeiCountSum+=Integer.parseInt(userObject.get("dateJiaoFeiCount").toString());
															leijiJiaoFeiCountSum+=Integer.parseInt(userObject.get("leijiJiaoFeiCount").toString());
														}
														//fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
														Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_");
														fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
														//没有分配给用户的指标
														fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
														//报名
														fuwuzhanHeJiMap.put("dateBaoMingCountSum", dateBaoMingCountSum);
														fuwuzhanHeJiMap.put("dateBaoMingCountSumSort", 0);
														
														
														fuwuzhanHeJiMap.put("leijiBaoMingCountSum", leijiBaoMingCountSum);
														fuwuzhanHeJiMap.put("leijiBaoMingCountSumSort", 0);

														if(userZhaoShengZhiBiaoSum_!=0){
															fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", format.format(new Float(leijiBaoMingCountSum)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiaoSum_)))));
														}else{
															fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", "-");
														}
														
														fuwuzhanHeJiMap.put("leijiBaoMingCountPSumSort", 0);
														//录取
														fuwuzhanHeJiMap.put("dateLuQuCountSum", dateLuQuCountSum);
														fuwuzhanHeJiMap.put("dateLuQuCountSumSort", 0);
														
														fuwuzhanHeJiMap.put("leijiLuQuCountSum", leijiLuQuCountSum);
														fuwuzhanHeJiMap.put("leijiLuQuCountSumSort", 0);
														if(leijiBaoMingCountSum!=0){
															fuwuzhanHeJiMap.put("leijiLuQuCountPSum", format.format(new Float(leijiLuQuCountSum)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCountSum)))));
														}else{
															fuwuzhanHeJiMap.put("leijiLuQuCountPSum", "-");
														}
														
														fuwuzhanHeJiMap.put("leijiLuQuCountPSumSort", 0);
														
														//缴费
														fuwuzhanHeJiMap.put("dateJiaoFeiCountSum", dateJiaoFeiCountSum);
														fuwuzhanHeJiMap.put("dateJiaoFeiCountSumSort", 0);
														
														fuwuzhanHeJiMap.put("leijiJiaoFeiCountSum", leijiJiaoFeiCountSum);
														fuwuzhanHeJiMap.put("leijiJiaoFeiCountSumSort", 0);
														if(leijiBaoMingCountSum!=0){
															fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  format.format(new Float(leijiJiaoFeiCountSum)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCountSum)))));
														}else{
															fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  "-");
														}
														
														fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSumSort", 0);
														
														
														
														fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
														

														return fuwuMap;
													}
												});
										if(fuwuId==-2&&searchType==1){
											Map<String, Object> fuwuMap = new HashMap<String, Object>();
											// 服务ID
											fuwuMap.put("fuwuId",resultSet.getInt("branch_id"));
											final Branch zongBuBranch = branchDao.findById(resultSet.getInt("branch_id"));
											fuwuMap.put("fuwuName",zongBuBranch != null ? zongBuBranch.getName() + "总部" : "");
											fuwuMap.put("zhurenName", branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+"")==null?"无":branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+""));
//											String userSql="select u.id as userId,u.full_name as name,u.org_id as branch_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id")+ " and u.status=0 ";
											String userSql="select u.id as userId,u.full_name as name,u.org_id as branch_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id");
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
//											if(xuexiId!=-2&&fuwuId==-2){
//												userSql+=" and u.org_id="+xuexiId;
//											}
											// 服务站下的所有用户
											// 过滤已删除，停用，root，总部，禁用
											List userList = jdbcTemplatePlus.query(userSql,
															new RowMapper() {
																//获取招生指标
																public int gerUserEnrollQuota(int userId){
																	return gerUserEnrollQuotaSum(school,batch,xuexiId,userId);
																}
																//报名人数
																public int getBaoMingCountByUserId(int branchId,int userId){
																	return getBaoMingCount(school, branchId,batchIds, serachStudentDataSource, way, source,startDate,endDate,userId);
																}
																//累计报名人数
																public int getLeiJiBaoMingCountByUserId(int branchId,int userId){
																	return getBaoMingCount(school, branchId,batchIds, serachStudentDataSource, way, source,null,null,userId);
																}
																//录取人数
																public int getLuQuCountByUserId(int branchId,int userId){
																	return getLuQuCount(school, branchId,batchIds, serachStudentDataSource, way, source,startDate,endDate,userId);
																}
																//累计录取人数
																public int getLeiJiLuQuCountByUserId(int branchId,int userId){
																	return getLuQuCount(school, branchId,batchIds, serachStudentDataSource, way, source,null,null,userId);
																}
																//缴费人数
																public int getJiaoFeiCountByUserId(int branchId,int userId){
																	return getJiaoFeiCount(userId,branchId);
																}
																//累计缴费人数
																public int getLeiJiJiaoFeiCountByUserId(int branchId,int userId){
																	return getLeijiJiaoFeiCount(userId,branchId);
																}
																public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																	Map<String, Object> userMap = new HashMap<String, Object>();
																	int userId=resultSet.getInt("userId");
																	userMap.put("userId",userId);
																	userMap.put("name",resultSet.getString("name"));
																	//用户对应的招生指标，新生报名情况，新生录取情况，，新生缴费情况
																	
																	//招生指标
																	int userZhaoShengZhiBiao=gerUserEnrollQuota(userId);
																	userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																	//时间段内报名人数
																	int dateBaoMingCount=getBaoMingCountByUserId(resultSet.getInt("branch_id"),userId);
																	userMap.put("dateBaoMingCount",dateBaoMingCount);
																	//累计报名人数
																	int leijiBaoMingCount=getLeiJiBaoMingCountByUserId(resultSet.getInt("branch_id"),userId);
																	userMap.put("leijiBaoMingCount",leijiBaoMingCount);
																	//累计完成率
																	if(userZhaoShengZhiBiao!=0){
																		userMap.put("leijiBaoMingCountP", format.format(new Float(leijiBaoMingCount)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiao)))));
																	}else{
																		userMap.put("leijiBaoMingCountP", "-");
																	}
																	
																	
																	//时间段内录取人数
																	int dateLuQuCount=getLuQuCountByUserId(resultSet.getInt("branch_id"),userId);
																	userMap.put("dateLuQuCount",dateLuQuCount);
																	//累计录取人数
																	int leijiLuQuCount=getLeiJiLuQuCountByUserId(resultSet.getInt("branch_id"),userId);
																	userMap.put("leijiLuQuCount",leijiLuQuCount);
																	//累计完成率
																	if(leijiBaoMingCount!=0){
																		userMap.put("leijiLuQuCountP", format.format(new Float(leijiLuQuCount)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCount)))));
																	}else{
																		userMap.put("leijiLuQuCountP", "-");
																	}
																	
																	
																	
																	//时间段内缴费人数
																	int dateJiaoFeiCount=getJiaoFeiCountByUserId(resultSet.getInt("branch_id"),userId);
																	userMap.put("dateJiaoFeiCount",dateJiaoFeiCount);
																	//累计缴费人数
																	int leijiJiaoFeiCount=getLeiJiJiaoFeiCountByUserId(resultSet.getInt("branch_id"),userId);
																	userMap.put("leijiJiaoFeiCount",leijiJiaoFeiCount);
																	//累计完成率
																	if(leijiBaoMingCount!=0){
																		userMap.put("leijiJiaoFeiCountP", format.format(new Float(leijiJiaoFeiCount)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCount)))));
																	}else{
																		userMap.put("leijiJiaoFeiCountP","-");
																	}
																	
																	
																	
																	return userMap;
																}
															});
											//时间段内报名排名
											sort(userList,"dateBaoMingCount");
											//累计报名人数排名
											sort(userList,"leijiBaoMingCount");
											//累计完成率排名
											sortP(userList,"leijiBaoMingCountP");
											
											//时间段内录取人数 
											sort(userList,"dateLuQuCount");
											//累计录取人数排名
											sort(userList,"leijiLuQuCount");
											//累计完成率排名
											sortP(userList,"leijiLuQuCountP");
											
											//时间段内缴费排名 
											sort(userList,"dateJiaoFeiCount");
											//累计缴费人数排名
											sort(userList,"leijiJiaoFeiCount");
											//累计完成率排名
											sortP(userList,"leijiJiaoFeiCountP");
											
											Collections.sort(userList, new Comparator() {
												public int compare(Object arg0, Object arg1) {
													Comparator cmp = Collator
															.getInstance(java.util.Locale.CHINA);
													String name1 = ((Map) arg0).get("name").toString();
													String name2 = ((Map) arg1).get("name").toString();
													return cmp.compare(name1, name2);
												}
											});
											fuwuMap.put("userList", userList);
											//服务站合计
											
											Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
											int userZhaoShengZhiBiaoSum=0;
											
											int dateBaoMingCountSum=0;
											int leijiBaoMingCountSum=0;
											
											int dateLuQuCountSum=0;
											int leijiLuQuCountSum=0;
											
											int dateJiaoFeiCountSum=0;
											int leijiJiaoFeiCountSum=0;
											
											for (Object object : userList) {
												Map userObject=(Map)object;
												userZhaoShengZhiBiaoSum+=Integer.parseInt(userObject.get("userZhaoShengZhiBiao").toString());
												dateBaoMingCountSum+=Integer.parseInt(userObject.get("dateBaoMingCount").toString());
												leijiBaoMingCountSum+=Integer.parseInt(userObject.get("leijiBaoMingCount").toString());
												dateLuQuCountSum+=Integer.parseInt(userObject.get("dateLuQuCount").toString());
												leijiLuQuCountSum+=Integer.parseInt(userObject.get("leijiLuQuCount").toString());
												dateJiaoFeiCountSum+=Integer.parseInt(userObject.get("dateJiaoFeiCount").toString());
												leijiJiaoFeiCountSum+=Integer.parseInt(userObject.get("leijiJiaoFeiCount").toString());
											}
											//fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
											//学习中心指标
											Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_");
											fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
											//没有分配给用户的指标
											fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
											
											
											//报名
											fuwuzhanHeJiMap.put("dateBaoMingCountSum", dateBaoMingCountSum);
											fuwuzhanHeJiMap.put("dateBaoMingCountSumSort", 0);
											
											fuwuzhanHeJiMap.put("leijiBaoMingCountSum", leijiBaoMingCountSum);
											fuwuzhanHeJiMap.put("leijiBaoMingCountSumSort", 0);
											if(userZhaoShengZhiBiaoSum_!=0){
												fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", format.format(new Float(leijiBaoMingCountSum)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiaoSum_)))));
											}else{
												fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", "-");
											}
											
											fuwuzhanHeJiMap.put("leijiBaoMingCountPSumSort", 0);
											//录取
											fuwuzhanHeJiMap.put("dateLuQuCountSum", dateLuQuCountSum);
											fuwuzhanHeJiMap.put("dateLuQuCountSumSort", 0);
											
											fuwuzhanHeJiMap.put("leijiLuQuCountSum", leijiLuQuCountSum);
											fuwuzhanHeJiMap.put("leijiLuQuCountSumSort", 0);
											if(leijiBaoMingCountSum!=0){
												fuwuzhanHeJiMap.put("leijiLuQuCountPSum", format.format(new Float(leijiLuQuCountSum)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCountSum)))));
											}else{
												fuwuzhanHeJiMap.put("leijiLuQuCountPSum", "-");
											}
											
											fuwuzhanHeJiMap.put("leijiLuQuCountPSumSort", 0);
											
											//缴费
											fuwuzhanHeJiMap.put("dateJiaoFeiCountSum", dateJiaoFeiCountSum);
											fuwuzhanHeJiMap.put("dateJiaoFeiCountSumSort", 0);
											
											fuwuzhanHeJiMap.put("leijiJiaoFeiCountSum", leijiJiaoFeiCountSum);
											fuwuzhanHeJiMap.put("leijiJiaoFeiCountSumSort", 0);
											if(leijiBaoMingCountSum!=0){
												fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  format.format(new Float(leijiJiaoFeiCountSum)/ new Float(getDayuOne(getDayuOne(leijiBaoMingCountSum)))));
											}else{
												fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum", "-");
											}
											
											fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSumSort", 0);
											
											
											
											fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
											
											
											fuwuList.add(0, fuwuMap);
										}
										
										//服务站排名
										sortF(fuwuList,"dateBaoMingCountSum");
										sortF(fuwuList,"leijiBaoMingCountSum");
										sortPF(fuwuList,"leijiBaoMingCountPSum");
										
										sortF(fuwuList,"dateLuQuCountSum");
										sortF(fuwuList,"leijiLuQuCountSum");
										sortPF(fuwuList,"leijiLuQuCountPSum");
										
										sortF(fuwuList,"dateJiaoFeiCountSum");
										sortF(fuwuList,"leijiJiaoFeiCountSum");
										sortPF(fuwuList,"leijiJiaoFeiCountPSum");
										
										
										
										xuexiMap.put("fuwuList", fuwuList);
										//学习中心合计
										
										Map<String, Object> x_fuwuzhanHeJiMap = new HashMap<String, Object>();
										
										int x_userZhaoShengZhiBiaoSum=0;
										
										int x_dateBaoMingCountSum=0;
										int x_leijiBaoMingCountSum=0;
										
										int x_dateLuQuCountSum=0;
										int x_leijiLuQuCountSum=0;
										
										int x_dateJiaoFeiCountSum=0;
										int x_leijiJiaoFeiCountSum=0;
										
										for (Object object : fuwuList) {
											Map fuwuObject=(Map)((Map)object).get("fuwuzhanHeJiMap");
											x_userZhaoShengZhiBiaoSum+=Integer.parseInt(fuwuObject.get("userZhaoShengZhiBiaoSum").toString());
											x_dateBaoMingCountSum+=Integer.parseInt(fuwuObject.get("dateBaoMingCountSum").toString());
											x_leijiBaoMingCountSum+=Integer.parseInt(fuwuObject.get("leijiBaoMingCountSum").toString());
											x_dateLuQuCountSum+=Integer.parseInt(fuwuObject.get("dateLuQuCountSum").toString());
											x_leijiLuQuCountSum+=Integer.parseInt(fuwuObject.get("leijiLuQuCountSum").toString());
											x_dateJiaoFeiCountSum+=Integer.parseInt(fuwuObject.get("dateJiaoFeiCountSum").toString());
											x_leijiJiaoFeiCountSum+=Integer.parseInt(fuwuObject.get("leijiJiaoFeiCountSum").toString());
										}
										x_fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", x_userZhaoShengZhiBiaoSum);
										//报名
										x_fuwuzhanHeJiMap.put("dateBaoMingCountSum", x_dateBaoMingCountSum);
										x_fuwuzhanHeJiMap.put("dateBaoMingCountSumSort", 0);
										
										x_fuwuzhanHeJiMap.put("leijiBaoMingCountSum", x_leijiBaoMingCountSum);
										x_fuwuzhanHeJiMap.put("leijiBaoMingCountSumSort", 0);
										if(x_userZhaoShengZhiBiaoSum!=0){
											x_fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", format.format(new Float(x_leijiBaoMingCountSum)/ new Float(getDayuOne(getDayuOne(x_userZhaoShengZhiBiaoSum)))));
										}else{
											x_fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", "-");
										}
										
										x_fuwuzhanHeJiMap.put("leijiBaoMingCountPSumSort", 0);
										//录取
										x_fuwuzhanHeJiMap.put("dateLuQuCountSum", x_dateLuQuCountSum);
										x_fuwuzhanHeJiMap.put("dateLuQuCountSumSort", 0);
										
										x_fuwuzhanHeJiMap.put("leijiLuQuCountSum", x_leijiLuQuCountSum);
										x_fuwuzhanHeJiMap.put("leijiLuQuCountSumSort", 0);
										if(x_leijiBaoMingCountSum!=0){
											x_fuwuzhanHeJiMap.put("leijiLuQuCountPSum", format.format(new Float(x_leijiLuQuCountSum)/ new Float(getDayuOne(getDayuOne(x_leijiBaoMingCountSum)))));
										}else{
											x_fuwuzhanHeJiMap.put("leijiLuQuCountPSum", "-");
										}
										
										x_fuwuzhanHeJiMap.put("leijiLuQuCountPSumSort", 0);
										
										//缴费
										x_fuwuzhanHeJiMap.put("dateJiaoFeiCountSum", x_dateJiaoFeiCountSum);
										x_fuwuzhanHeJiMap.put("dateJiaoFeiCountSumSort", 0);
										
										x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountSum", x_leijiJiaoFeiCountSum);
										x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountSumSort", 0);
										if(x_leijiBaoMingCountSum!=0){
											x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  format.format(new Float(x_leijiJiaoFeiCountSum)/ new Float(getDayuOne(getDayuOne(x_leijiBaoMingCountSum)))));
										}else{
											x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  "-");
										}
										
										x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSumSort", 0);
										xuexiMap.put("fuwuzhanHeJiMap", x_fuwuzhanHeJiMap);
										return xuexiMap;
									}
								});
						
						//学习中心排名
//						sortF(xuexiList,"dateBaoMingCountSum");
//						sortF(xuexiList,"leijiBaoMingCountSum");
//						sortPF(xuexiList,"leijiBaoMingCountPSum");
//						
//						sortF(xuexiList,"dateLuQuCountSum");
//						sortF(xuexiList,"leijiLuQuCountSum");
//						sortPF(xuexiList,"leijiLuQuCountPSum");
//						
//						sortF(xuexiList,"dateJiaoFeiCountSum");
//						sortF(xuexiList,"leijiJiaoFeiCountSum");
//						sortPF(xuexiList,"leijiJiaoFeiCountPSum");
						quyuMap.put("xuexiList", xuexiList);
						Collections.sort(xuexiList, new Comparator() {
							public int compare(Object arg0, Object arg1) {
								Comparator cmp = Collator
										.getInstance(java.util.Locale.CHINA);
								String name1 = ((Map) arg0).get("xuexiName").toString();
								String name2 = ((Map) arg1).get("xuexiName").toString();
								return cmp.compare(name1, name2);
							}
						});
						//区域经理总合计
						
						Map<String, Object> x_fuwuzhanHeJiMap = new HashMap<String, Object>();
						
						int x_userZhaoShengZhiBiaoSum=0;
						
						int x_dateBaoMingCountSum=0;
						int x_leijiBaoMingCountSum=0;
						
						int x_dateLuQuCountSum=0;
						int x_leijiLuQuCountSum=0;
						
						int x_dateJiaoFeiCountSum=0;
						int x_leijiJiaoFeiCountSum=0;
						
						for (Object object : xuexiList) {
							Map fuwuObject=(Map)((Map)object).get("fuwuzhanHeJiMap");
							x_userZhaoShengZhiBiaoSum+=Integer.parseInt(fuwuObject.get("userZhaoShengZhiBiaoSum").toString());
							x_dateBaoMingCountSum+=Integer.parseInt(fuwuObject.get("dateBaoMingCountSum").toString());
							x_leijiBaoMingCountSum+=Integer.parseInt(fuwuObject.get("leijiBaoMingCountSum").toString());
							x_dateLuQuCountSum+=Integer.parseInt(fuwuObject.get("dateLuQuCountSum").toString());
							x_leijiLuQuCountSum+=Integer.parseInt(fuwuObject.get("leijiLuQuCountSum").toString());
							x_dateJiaoFeiCountSum+=Integer.parseInt(fuwuObject.get("dateJiaoFeiCountSum").toString());
							x_leijiJiaoFeiCountSum+=Integer.parseInt(fuwuObject.get("leijiJiaoFeiCountSum").toString());
						}
						x_fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", x_userZhaoShengZhiBiaoSum);
						//报名
						x_fuwuzhanHeJiMap.put("dateBaoMingCountSum", x_dateBaoMingCountSum);
						x_fuwuzhanHeJiMap.put("dateBaoMingCountSumSort", 0);
						
						x_fuwuzhanHeJiMap.put("leijiBaoMingCountSum", x_leijiBaoMingCountSum);
						x_fuwuzhanHeJiMap.put("leijiBaoMingCountSumSort", 0);
						if(x_userZhaoShengZhiBiaoSum!=0){
							x_fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", format.format(new Float(x_leijiBaoMingCountSum)/ new Float(getDayuOne(getDayuOne(x_userZhaoShengZhiBiaoSum)))));
						}else{
							x_fuwuzhanHeJiMap.put("leijiBaoMingCountPSum", "-");
						}
						
						x_fuwuzhanHeJiMap.put("leijiBaoMingCountPSumSort", 0);
						//录取
						x_fuwuzhanHeJiMap.put("dateLuQuCountSum", x_dateLuQuCountSum);
						x_fuwuzhanHeJiMap.put("dateLuQuCountSumSort", 0);
						
						x_fuwuzhanHeJiMap.put("leijiLuQuCountSum", x_leijiLuQuCountSum);
						x_fuwuzhanHeJiMap.put("leijiLuQuCountSumSort", 0);
						if(x_leijiBaoMingCountSum!=0){
							x_fuwuzhanHeJiMap.put("leijiLuQuCountPSum", format.format(new Float(x_leijiLuQuCountSum)/ new Float(getDayuOne(getDayuOne(x_leijiBaoMingCountSum)))));
						}else{
							x_fuwuzhanHeJiMap.put("leijiLuQuCountPSum", "-");
						}
						
						x_fuwuzhanHeJiMap.put("leijiLuQuCountPSumSort", 0);
						
						//缴费
						x_fuwuzhanHeJiMap.put("dateJiaoFeiCountSum", x_dateJiaoFeiCountSum);
						x_fuwuzhanHeJiMap.put("dateJiaoFeiCountSumSort", 0);
						
						x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountSum", x_leijiJiaoFeiCountSum);
						x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountSumSort", 0);
						if(x_leijiBaoMingCountSum!=0){
							x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  format.format(new Float(x_leijiJiaoFeiCountSum)/ new Float(getDayuOne(getDayuOne(x_leijiBaoMingCountSum)))));
						}else{
							x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSum",  "-");
						}
						
						x_fuwuzhanHeJiMap.put("leijiJiaoFeiCountPSumSort", 0);
						
						//总合计
						zhibiaoSumList.add(x_userZhaoShengZhiBiaoSum);
						
						dateBaomingSumList.add(x_dateBaoMingCountSum);
						leijiBaomingSumList.add(x_leijiBaoMingCountSum);
						
						dateLuquSumList.add(x_dateLuQuCountSum);
						leijiLuquSumList.add(x_leijiLuQuCountSum);
						
						dateJiaofeiSumList.add(x_dateJiaoFeiCountSum);
						leijiJiaofeiSumList.add(x_leijiJiaoFeiCountSum);
						
						quyuMap.put("fuwuzhanHeJiMap", x_fuwuzhanHeJiMap);
						
						
						return quyuMap;
					}
				});
				
				sortF(quyuList,"dateBaoMingCountSum");
				sortF(quyuList,"leijiBaoMingCountSum");
				sortPF(quyuList,"leijiBaoMingCountPSum");
				
				sortF(quyuList,"dateLuQuCountSum");
				sortF(quyuList,"leijiLuQuCountSum");
				sortPF(quyuList,"leijiLuQuCountPSum");
				
				sortF(quyuList,"dateJiaoFeiCountSum");
				sortF(quyuList,"leijiJiaoFeiCountSum");
				sortPF(quyuList,"leijiJiaoFeiCountPSum");
				Collections.sort(quyuList, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Map) arg0).get("quyuName").toString();
						String name2 = ((Map) arg1).get("quyuName").toString();
						return cmp.compare(name1, name2);
					}
				});
				
				jdbcTemplatePlus.releaseConnection();
				long e = System.currentTimeMillis();
				System.out.println("倒入数据用时：" + (e - s));
				List result=new ArrayList();
				Map<String,Object> sumMap=new HashMap<String, Object>();
				
				sumMap.put("zhibiaoSum", getListSum(zhibiaoSumList));
				
				sumMap.put("dateBaomingSum", getListSum(dateBaomingSumList));
				sumMap.put("leijiBaomingSum", getListSum(leijiBaomingSumList));
				if(getListSum(zhibiaoSumList)!=0){
					sumMap.put("leijiBaomingSumP", format.format(new Float(getListSum(leijiBaomingSumList))/ new Float(getDayuOne(getDayuOne(getListSum(zhibiaoSumList))))));
				}else{
					sumMap.put("leijiBaomingSumP", "-");
				}
				if(getListSum(zhibiaoSumList)!=0){
					sumMap.put("leijiBaomingSumP", format.format(new Float(getListSum(leijiBaomingSumList))/ new Float(getDayuOne(getDayuOne(getListSum(zhibiaoSumList))))));
				}else{
					sumMap.put("leijiBaomingSumP", "-");
				}
				
				
				sumMap.put("dateLuquSum", getListSum(dateLuquSumList));
				sumMap.put("leijiLuquSum", getListSum(leijiLuquSumList));
				if(getListSum(leijiBaomingSumList)!=0){
					sumMap.put("leijiLuquSumP", format.format(new Float(getListSum(leijiLuquSumList))/ new Float(getDayuOne(getDayuOne(getListSum(leijiBaomingSumList))))));
				}else{
					sumMap.put("leijiLuquSumP", "-");
				}
				
				
				sumMap.put("dateJiaofeiSum", getListSum(dateJiaofeiSumList));
				sumMap.put("leijiJiaofeiSum", getListSum(leijiJiaofeiSumList));
				if(getListSum(leijiBaomingSumList)!=0){
					sumMap.put("leijiJiaofeiSumP", format.format(new Float(getListSum(leijiJiaofeiSumList))/ new Float(getDayuOne(getDayuOne(getListSum(leijiBaomingSumList))))));
				}else{
					sumMap.put("leijiJiaofeiSumP", "-");
				}
				
				
				if(searchType==1){
					//学习中心排序(整体排序)
					//所有学习中心List
					List allBranchList=new ArrayList();
					Map<String,Object> allBranchMap=new HashMap<String,Object>();
					
					for (Object object : quyuList) {
						if(object!=null){
							Map map=(Map)object;
							List xuexiList=(List)map.get("xuexiList");
							for (Object object2 : xuexiList) {
								allBranchList.add(object2);
							}
							
						}
					}
					//学习中心排名
					sortF(allBranchList,"dateBaoMingCountSum");
					sortF(allBranchList,"leijiBaoMingCountSum");
					sortPF(allBranchList,"leijiBaoMingCountPSum");
					
					sortF(allBranchList,"dateLuQuCountSum");
					sortF(allBranchList,"leijiLuQuCountSum");
					sortPF(allBranchList,"leijiLuQuCountPSum");
					
					sortF(allBranchList,"dateJiaoFeiCountSum");
					sortF(allBranchList,"leijiJiaoFeiCountSum");
					sortPF(allBranchList,"leijiJiaoFeiCountPSum");
				}else if(searchType==3){
					//服务站排序(整体排序)
					//所有服务站List
					List allBranchList=new ArrayList();
					Map<String,Object> allBranchMap=new HashMap<String,Object>();
					
					for (Object object : quyuList) {
						if(object!=null){
							Map map=(Map)object;
							List xuexiList=(List)map.get("xuexiList");
							for (Object object2 : xuexiList) {
								Map map2=(Map)object2;
								List fuWuList=(List)map2.get("fuwuList");
								for (Object object3 : fuWuList) {
									allBranchList.add(object3);
								}
								
							}
							
						}
					}
					//学习中心排名
					sortF(allBranchList,"dateBaoMingCountSum");
					sortF(allBranchList,"leijiBaoMingCountSum");
					sortPF(allBranchList,"leijiBaoMingCountPSum");
					
					sortF(allBranchList,"dateLuQuCountSum");
					sortF(allBranchList,"leijiLuQuCountSum");
					sortPF(allBranchList,"leijiLuQuCountPSum");
					
					sortF(allBranchList,"dateJiaoFeiCountSum");
					sortF(allBranchList,"leijiJiaoFeiCountSum");
					sortPF(allBranchList,"leijiJiaoFeiCountPSum");
				}
				
				
				sumMap.put("quyuList", quyuList);

				return sumMap;
		}catch(Exception e){
			e.printStackTrace();
			return new HashMap();
		}
		
	}
	
	
	/**
	 * 招生指标(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param branchId 学习中心
	 * @return
	 */
	private Map<String,Integer> initZhaoShengZhiBiaoMap(int gbatch,String branchIds){
		
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		
		String sql="select branch_id,IFNULL(sum(target),0) as target  from tb_e_branch_enroll_quota where delete_flag=0 ";
		//全局批次
		if(gbatch!=-2){
			sql+=" and batch_id = "+gbatch;
		}
		//学习中心
		if(!branchIds.equals("")){
			sql+=" and branch_id in ( "+branchIds +" ) ";
		}
		
		sql+=" group by branch_id";
		
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("target", resultSet.getInt("target"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("branch_id")+"_", map.get("target"));
			}
		}
		return mapResult;
	}
	
	//通过院校ID，全局批次，学习中心，用户ID（用户ID必须选择）查询用户指标
	final public int gerUserEnrollQuotaSum(int school,int batch,int branch,int userId){
		String sql="select IFNULL(sum(target),0)  from tb_e_user_enroll_quota where user_id="+userId;
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//全局批次
		if(batch!=-2){
			sql+=" and batch_id = "+batch;
		}
		//学习中心
		User user = userDao.findById(userId);
		if(user!=null)
		{
			sql+=" and branch_id = "+user.getOrgId();
		}
//		if(branch!=-2){
//			sql+=" and branch_id = "+branch;
//		}
		return jdbcTemplatePlus.queryForInt(sql);
	}
	//通过报名开始时间，报名结束时间，用户ID，查询时间段内报名人数
	//如果时间为空，查询的是累计报名人数
	final public int getBaoMingCount(int school,int branchId,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate,int userId){
		String sql="select IFNULL(count(*),0) from tb_e_student where status<>"+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and user_id="+userId;
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		//批次
		if(batch!=null&&!batch.equals("0")){
			sql+=" and enrollment_batch_id in ("+batch+")";
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
			sql+=" and registration_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql+=" and registration_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql+=" and registration_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		
		int count =jdbcTemplatePlus.queryForInt(sql);
		//System.out.println(count+"--"+sql);
		return count;
	}
	
	//通过录取开始时间，录取结束时间，用户ID，查询时间段内录取人数
	//如果时间为空，查询的是累计录取人数
	final public int getLuQuCount(int school,int branchId,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate,int userId){
		String sql="select IFNULL(count(*),0) from tb_e_student where status="+Constants.STU_CALL_STATUS_YI_LU_QU+" and user_id="+userId;
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id="+branchId;
		}
		
		//批次
		if(batch!=null&&!batch.equals("0")){
			sql+=" and enrollment_batch_id in ("+batch+")";
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
			sql+=" and admit_time between '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"' and '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		if(startDate!=null&&endDate==null){
			sql+=" and admit_time >='"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00"+"'";
		}
		if(startDate==null&&endDate!=null){
			sql+=" and admit_time <='"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 23:59:59"+"'";
		}
		return jdbcTemplatePlus.queryForInt(sql);
	}
	
	//通过缴费开始时间，缴费结束时间，用户ID，查询时间段内缴费人数
	//如果时间为空，查询的是累计缴费人数
	final public int getJiaoFeiCount(int userId,int branchId){
		return userId_jiaofeiCount_map.get(userId+"_"+branchId)==null?0:userId_jiaofeiCount_map.get(userId+"_"+branchId);
	}
	
	final public int getLeijiJiaoFeiCount(int userId,int branchId){
		return userId_jiaofeiCount_map_leiji.get(userId+"_"+branchId)==null?0:userId_jiaofeiCount_map_leiji.get(userId+"_"+branchId);
	}
	
	/**
	 * 获取用户对应的缴费学生数量Map
	 * 
	 * @return  key:userId_branchId   value:缴费学生人数
	 */
	final public Map<String,Integer> getJiaofeiStudentCount(int school,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql1 ="select DISTINCT student_id from tb_e_fee_payment_detail where fee_subject_id="+FeeSubjectEnum.TuitionFee.value();
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
		
		//String sql ="select user_id,branch_id,count(id) as jiaofei_count from tb_e_student where  id in ("+sql1+")  and tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";
		String sql ="select user_id,branch_id,count(id) as jiaofei_count from tb_e_student where status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and  tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";
		
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		
		//批次
		if(batch!=null&&!batch.equals("0")){
			sql+=" and enrollment_batch_id in ("+batch+")";
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
		sql+=" group by user_id,branch_id";
		//System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("user_id", resultSet.getInt("user_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("jiaofei_count", resultSet.getInt("jiaofei_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("user_id")+"_"+map.get("branch_id"), map.get("jiaofei_count"));
			}
		}
		return mapResult;
	}
	
	
	
	/**
	 * 获取用户对应的缴费学生数量Map
	 * 
	 * @return  key:userId_branchId   value:缴费学生人数
	 */
	final public Map<String,Integer> getDateJiaofeiStudentCount(int school,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql1 ="select DISTINCT student_id from tb_e_fee_payment_detail where fee_subject_id="+FeeSubjectEnum.TuitionFee.value();
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
		
		String sql ="select user_id,branch_id,count(id) as jiaofei_count from tb_e_student where  status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and id in ("+sql1+")  and tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";
		//String sql ="select user_id,branch_id,count(id) as jiaofei_count from tb_e_student where status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and  tuition_status>"+Constants.STU_TUITION_STATUS_WEI_JIAO_XUE_FEI+" ";
		
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		
		//批次
		if(batch!=null&&!batch.equals("0")){
			sql+=" and enrollment_batch_id in ("+batch+")";
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
		sql+=" group by user_id,branch_id";
		//System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("user_id", resultSet.getInt("user_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("jiaofei_count", resultSet.getInt("jiaofei_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("user_id")+"_"+map.get("branch_id"), map.get("jiaofei_count"));
			}
		}
		return mapResult;
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
	
	final public int getDayuOne(int number){
		return number==0?1:number;
	}
	
	//排序
//	final public void  sort(List list,final String filed){
//		Collections.sort(list, new Comparator<Object>() {
//			public int compare(final Object arg0, final Object arg1) {
//				final int count1= Integer.parseInt(((Map)arg0).get(filed).toString());
//				final int count2= Integer.parseInt(((Map)arg1).get(filed).toString());
//				
//				if(count1<count2){
//					return 1;
//				}else if(count1>count2){
//					return -1;
//				}else{
//					return 0;
//				}
//				
//			}
//		});
//		for (int i=0;i<list.size();i++) {
//			Map userObject= (Map) list.get(i);
//			if(Integer.valueOf(userObject.get(filed).toString())==0){
//				userObject.put(filed+"Sort", "-");
//			}else{
//				userObject.put(filed+"Sort", i+1);
//			}
//			
//		}
//	}
	
	/**
	 * 排序 by dongminghao
	 * 备注：修改原排序规则，如果数值相等则排名相等
	 * 如：21 16 21 则排名为 1 3 1 而不是 1 2 1
	 */
	@SuppressWarnings("unchecked")
	final public void  sort(List list,final String filed){
		Collections.sort(list, new Comparator<Object>() {
			public int compare(final Object arg0, final Object arg1) {
				final int count1= Integer.parseInt(((Map)arg0).get(filed).toString());
				final int count2= Integer.parseInt(((Map)arg1).get(filed).toString());
				
				if(count1<count2){
					return 1;
				}else if(count1>count2){
					return -1;
				}else{
					return 0;
				}
				
			}
		});
		int temp1 = -9999;//当前数值
		int temp2 = -9999;//上次数值
		int order = 1;//排名
		for (int i=0;i<list.size();i++) {
			Map userObject= (Map) list.get(i);
			//记录当前
			temp1 = Integer.valueOf(userObject.get(filed).toString());
			if(Integer.valueOf(userObject.get(filed).toString())==0){
				userObject.put(filed+"Sort", "-");
			}else{
				//第一次比较
				if(i==0){
					userObject.put(filed+"Sort", order);
				}
				else{
					if(temp1 == temp2){
						userObject.put(filed+"Sort", order);
					}
					else{
						//重新矫正排名
						order=i+1;
						userObject.put(filed+"Sort", order);
					}
				}
			}
			//记录上次
			temp2 = Integer.valueOf(userObject.get(filed).toString());
		}
	}
	
	//排序
//	final public void  sortP(List list,final String filed){
//		Collections.sort(list, new Comparator<Object>() {
//			public int compare(final Object arg0, final Object arg1) {
//				try{
//					final float count1= Float.valueOf(((Map)arg0).get(filed).toString().substring(0, ((Map)arg0).get(filed).toString().length()-1).replaceAll(",", ""));
//					final float count2= Float.valueOf(((Map)arg1).get(filed).toString().substring(0, ((Map)arg1).get(filed).toString().length()-1).replaceAll(",", ""));
//					
//					if(count1<count2){
//						return 1;
//					}else if(count1>count2){
//						return -1;
//					}else{
//						return 0;
//					}
//				}catch(Exception e){
//					return 0;
//				}
//				
//			}
//		});
//		for (int i=0;i<list.size();i++) {
//			Map userObject= (Map) list.get(i);
//			if(userObject.get(filed).toString().equals("-")){
//				userObject.put(filed+"Sort", "-");
//			}else{
//				if(userObject.get(filed).toString().equals("0.00%")){
//					userObject.put(filed+"Sort", "-");
//				}else{
//					userObject.put(filed+"Sort", i+1);
//				}
//			}
//			
//			
//		}
//	}
	
	/**
	 * 排序 by dongminghao
	 * 备注：修改原排序规则，如果数值相等则排名相等
	 * 如：21 16 21 则排名为 1 3 1 而不是 1 2 1
	 */
	@SuppressWarnings("unchecked")
	final public void  sortP(List list,final String filed){
		Collections.sort(list, new Comparator<Object>() {
			public int compare(final Object arg0, final Object arg1) {
				try{
					float count1;
					try {
						count1 = Float.valueOf(((Map)arg0).get(filed).toString().substring(0, ((Map)arg0).get(filed).toString().length()-1).replaceAll(",", ""));
					} catch (Exception e) {
						return 1;
					}
					float count2;
					try {
						count2 = Float.valueOf(((Map)arg1).get(filed).toString().substring(0, ((Map)arg1).get(filed).toString().length()-1).replaceAll(",", ""));
					} catch (Exception e) {
						return -1;
					}
					
					if(count1<count2){
						return 1;
					}else if(count1>count2){
						return -1;
					}else{
						return 0;
					}
				}catch(Exception e){
					return 0;
				}
				
			}
		});
		String temp1 = null;//当前数值
		String temp2 = null;//上次数值
		int order = 1;//排名
		for (int i=0;i<list.size();i++) {
			Map userObject= (Map) list.get(i);
			//记录当前
			temp1 = userObject.get(filed).toString();
			if(userObject.get(filed).toString().equals("-")){
				userObject.put(filed+"Sort", "-");
			}else{
				if(userObject.get(filed).toString().equals("0.00%")){
					userObject.put(filed+"Sort", "-");
				}else{
					//第一次比较
					if(i==0){
						userObject.put(filed+"Sort", order);
					}
					else{
						if(temp1.equals(temp2)){
							userObject.put(filed+"Sort", order);
						}
						else{
							//重新矫正排名
							order=i+1;
							userObject.put(filed+"Sort", order);
						}
					}
				}
			}
			//记录上次
			temp2 = userObject.get(filed).toString();
		}
	}
	
	
	
	//排序
//	final public void  sortF(List list,final String filed){
//		Collections.sort(list, new Comparator<Object>() {
//			public int compare(final Object arg0, final Object arg1) {
//				final int count1= Integer.parseInt(((Map)((Map)arg0).get("fuwuzhanHeJiMap")).get(filed).toString());
//				final int count2= Integer.parseInt(((Map)((Map)arg1).get("fuwuzhanHeJiMap")).get(filed).toString());
//				
//				if(count1<count2){
//					return 1;
//				}else if(count1>count2){
//					return -1;
//				}else{
//					return 0;
//				}
//				
//			}
//		});
//		for (int i=0;i<list.size();i++) {
//			Map fuwuzhanHeJiMap= (Map)((Map) list.get(i)).get("fuwuzhanHeJiMap");
//			if(Integer.valueOf(fuwuzhanHeJiMap.get(filed).toString())==0){
//				fuwuzhanHeJiMap.put(filed+"Sort", "-");
//			}else{
//				fuwuzhanHeJiMap.put(filed+"Sort", i+1);
//			}
//			
//		}
//	}
	
	/**
	 * 排序 by dongminghao
	 * 备注：修改原排序规则，如果数值相等则排名相等
	 * 如：21 16 21 则排名为 1 3 1 而不是 1 2 1
	 */
	@SuppressWarnings("unchecked")
	final public void  sortF(List list,final String filed){
		Collections.sort(list, new Comparator<Object>() {
			public int compare(final Object arg0, final Object arg1) {
				final int count1= Integer.parseInt(((Map)((Map)arg0).get("fuwuzhanHeJiMap")).get(filed).toString());
				final int count2= Integer.parseInt(((Map)((Map)arg1).get("fuwuzhanHeJiMap")).get(filed).toString());
				
				if(count1<count2){
					return 1;
				}else if(count1>count2){
					return -1;
				}else{
					return 0;
				}
				
			}
		});
		int temp1 = -9999;//当前数值
		int temp2 = -9999;//上次数值
		int order = 1;//排名
		for (int i=0;i<list.size();i++) {
			Map fuwuzhanHeJiMap= (Map)((Map) list.get(i)).get("fuwuzhanHeJiMap");
			//记录当前
			temp1 = Integer.valueOf(fuwuzhanHeJiMap.get(filed).toString());
			if(Integer.valueOf(fuwuzhanHeJiMap.get(filed).toString())==0){
				fuwuzhanHeJiMap.put(filed+"Sort", "-");
			}else{
				//第一次比较
				if(i==0){
					fuwuzhanHeJiMap.put(filed+"Sort", order);
				}
				else{
					if(temp1 == temp2){
						fuwuzhanHeJiMap.put(filed+"Sort", order);
					}
					else{
						//重新矫正排名
						order=i+1;
						fuwuzhanHeJiMap.put(filed+"Sort", order);
					}
				}
			}
			//记录上次
			temp2 = Integer.valueOf(fuwuzhanHeJiMap.get(filed).toString());
		}
	}
	
	//排序
//	final public void  sortPF(List list,final String filed){
//		Collections.sort(list, new Comparator<Object>() {
//			public int compare(final Object arg0, final Object arg1) {
//				try{
//					final float count1= Float.valueOf(((Map)((Map)arg0).get("fuwuzhanHeJiMap")).get(filed).toString().substring(0, ((Map)((Map)arg0).get("fuwuzhanHeJiMap")).get(filed).toString().length()-1).replaceAll(",", ""));
//					final float count2= Float.valueOf(((Map)((Map)arg1).get("fuwuzhanHeJiMap")).get(filed).toString().substring(0, ((Map)((Map)arg1).get("fuwuzhanHeJiMap")).get(filed).toString().length()-1).replaceAll(",", ""));
//					
//					if(count1<count2){
//						return 1;
//					}else if(count1>count2){
//						return -1;
//					}else{
//						return 0;
//					}
//				}catch(Exception e){
//					return 0;
//				}
//				
//			}
//		});
//		for (int i=0;i<list.size();i++) {
//			Map fuwuzhanHeJiMap= (Map)((Map) list.get(i)).get("fuwuzhanHeJiMap");
//			if(fuwuzhanHeJiMap.get(filed).toString().equals("-")){
//				fuwuzhanHeJiMap.put(filed+"Sort", "-");
//			}else{
//				if(fuwuzhanHeJiMap.get(filed).toString().equals("0.00%")){
//					fuwuzhanHeJiMap.put(filed+"Sort", "-");
//				}else{
//					fuwuzhanHeJiMap.put(filed+"Sort", i+1);
//				}
//			}
//			
//			
//		}
//	}
	
	/**
	 * 排序 by dongminghao
	 * 备注：修改原排序规则，如果数值相等则排名相等
	 * 如：21 16 21 则排名为 1 3 1 而不是 1 2 1
	 */
	@SuppressWarnings("unchecked")
	final public void  sortPF(List list,final String filed){
		Collections.sort(list, new Comparator<Object>() {
			public int compare(final Object arg0, final Object arg1) {
				try{
					float count1;
					try {
						count1 = Float.valueOf(((Map)((Map)arg0).get("fuwuzhanHeJiMap")).get(filed).toString().substring(0, ((Map)((Map)arg0).get("fuwuzhanHeJiMap")).get(filed).toString().length()-1).replaceAll(",", ""));
					} catch (Exception e) {
						return 1;
					}
					float count2;
					try {
						count2 = Float.valueOf(((Map)((Map)arg1).get("fuwuzhanHeJiMap")).get(filed).toString().substring(0, ((Map)((Map)arg1).get("fuwuzhanHeJiMap")).get(filed).toString().length()-1).replaceAll(",", ""));
					} catch (Exception e) {
						return -1;
					}
					
					if(count1<count2){
						return 1;
					}else if(count1>count2){
						return -1;
					}else{
						return 0;
					}
				}catch(Exception e){
					return 0;
				}
				
			}
		});
		String temp1 = null;//当前数值
		String temp2 = null;//上次数值
		int order = 1;//排名
		for (int i=0;i<list.size();i++) {
			Map fuwuzhanHeJiMap= (Map)((Map) list.get(i)).get("fuwuzhanHeJiMap");
			//记录当前
			temp1 = fuwuzhanHeJiMap.get(filed).toString();
			if(fuwuzhanHeJiMap.get(filed).toString().equals("-")){
				fuwuzhanHeJiMap.put(filed+"Sort", "-");
			}else{
				if(fuwuzhanHeJiMap.get(filed).toString().equals("0.00%")){
					fuwuzhanHeJiMap.put(filed+"Sort", "-");
				}else{
					//第一次比较
					if(i==0){
						fuwuzhanHeJiMap.put(filed+"Sort", order);
					}
					else{
						if(temp1.equals(temp2)){
							fuwuzhanHeJiMap.put(filed+"Sort", order);
						}
						else{
							//重新矫正排名
							order=i+1;
							fuwuzhanHeJiMap.put(filed+"Sort", order);
						}
					}
				}
			}
			//记录上次
			temp2 = fuwuzhanHeJiMap.get(filed).toString();
		}
	}
	
	private int getListSum(List<Integer> list){
		int count=0;
		if(list!=null){
			for (Integer c : list) {
				count+=c;
			}
		}
		return count;
	}
}
