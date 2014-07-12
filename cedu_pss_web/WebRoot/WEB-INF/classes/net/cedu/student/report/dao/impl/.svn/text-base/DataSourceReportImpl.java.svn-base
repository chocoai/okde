/**
 * 文件名：DataSourceReportImpl.java
 * 包名：net.cedu.student.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-26 下午03:38:48
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.DataSourceReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：学生数据来源报表实现类
 * 
 * @作者： 杨栋栋
 * @作成时间：2011-12-26 下午03:40:13
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Repository
public class DataSourceReportImpl extends BaseReportDaoImpl implements
		DataSourceReport {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private UGroupUserDao uGroupUserDao;
	
	@Autowired
	private BranchBiz branchBiz;
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	

	/**
	 * 接口方法实现
	  * @see net.cedu.report.dao.EnrollmentSourceReport#statistics(java.util.Map)
	 */
	public Map statistics(Map<String, Integer> params) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int school = params.get("school");// 学院
		final int batch = params.get("batch");// 批次
		final int serachStudentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int source = params.get("source");// 招生途径
		final int quyuId = params.get("manager");// 区域经理ID
		final int xuexiId = params.get("branch");// 学习中心
		final int fuwuId = params.get("fuwu");// 服务站ID
		final int userId = params.get("user");// 用户ID
		try{
				//long s = System.currentTimeMillis();
			//生成%数
				final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
				format.setMinimumFractionDigits(2);// 设置小数位
				
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
				final List<Integer> wangluobaomingSumList=new ArrayList<Integer>();
				final List<Integer> hujiaozhongxinSumList=new ArrayList<Integer>();
				final List<Integer> xuexizhongxinSumList=new ArrayList<Integer>();
				final List<Integer> lishishujuSumList=new ArrayList<Integer>();
				
				final List<Integer> hejiList=new ArrayList<Integer>();
				
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
						
						xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0 and parent_id=1)";
						
						if(xuexiId!=-2){
							//xuxiSql+=" and branch_id="+xuexiId;
							// 如果单独查学习中心，则有可能是服务站，所以排除parent_id=1参数
							xuxiSql="select DISTINCT branch_id from tb_r_area_manager_branch where manager_id="+ resultSet.getInt("manager_id");
							xuxiSql+= " and branch_id in (select id from tb_e_branch where delete_flag=0)";
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
									public Object mapRow(ResultSet resultSet, int index)
											throws SQLException {
										Map<String, Object> xuexiMap = new HashMap<String, Object>();
										// 学习中心ID
										xuexiMap.put("xuexiId",resultSet.getInt("branch_id"));
										// 学习中心名称
										Branch branch = branchDao.findById(resultSet.getInt("branch_id"));
										xuexiMap.put("xuexiName",branch != null ? branch.getName() : "");
										
										//System.out.println(branch.getName());
										
										// 学习中心下面的服务站
		
										//学生所有数量
										//final int studentCount = jdbcTemplatePlus.queryForInt("select count(*) from tb_e_student");
										//招生批次IDs字符串
										final String batchIds=getEnrollmentBatchId(school,batch);
										
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
														//String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("fuwuId")+ " and u.status=0 ";
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
																			private int getCount(int studentDataSource,int userId,int branchId){
																				return getStudentCount(school,branchId ,batchIds, studentDataSource, way, source, serachStudentDataSource,userId);
																			}
																			public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																				Map<String, Object> userMap = new HashMap<String, Object>();
																				userMap.put("userId",resultSet.getInt("userId"));
																				userMap.put("name",resultSet.getString("name"));
																				//招生指标
																				int userZhaoShengZhiBiao=gerUserEnrollQuota(resultSet.getInt("userId"));
																				userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																				
																				int studentCount=getStudentCountByUserId(resultSet.getInt("userId"));
																				
																				// 网络报名人数
																				int wangluobaomingCount=getCount(Constants.STU_SOURCE_NP,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("wangluobaomingCount",wangluobaomingCount);
																				userMap.put("wangluobaomingCountP",getP(format,wangluobaomingCount,studentCount));
																				
																				// 呼叫中心人数
																				int hujiaozhongxinCount=getCount(Constants.STU_SOURCE_CC,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("hujiaozhongxinCount",hujiaozhongxinCount);
																				userMap.put("hujiaozhongxinCountP",getP(format,hujiaozhongxinCount,studentCount));
																				
																				// 学习中心人数
																				int xuexizhongxinCount=getCount(Constants.STU_SOURCE_LC,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("xuexizhongxinCount",xuexizhongxinCount);
																				userMap.put("xuexizhongxinCountP",getP(format,xuexizhongxinCount,studentCount));
																				
																				// 历史数据人数
																				int lishishujuCount=getCount(Constants.STU_SOURCE_HS,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("lishishujuCount",lishishujuCount);
																				userMap.put("lishishujuCountP",getP(format,lishishujuCount,studentCount));
																				
																				// 合计
																				int count = wangluobaomingCount+hujiaozhongxinCount+xuexizhongxinCount+lishishujuCount;
																				userMap.put("hejiCount",count);
																				//userMap.put("hejiCountP",format.format(new Float(count)/ new Float(studentCount)));
																				return userMap;
																			}
																		});
														fuwuMap.put("userList",userList);
														//服务站合计
														Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
														//渠道服务站合计
														int userZhaoShengZhiBiaoSum=0;
														int wangluobaomingCountSum=0;
														int hujiaozhongxingCountSum=0;
														int xuexizhongxinCountSum=0;
														int lishishujuCountSum=0;
														

														for (Object object : userList) {
															Map<String, Object> map=(Map<String, Object>)object;
															int wangluobaomingCount=Integer.valueOf(map.get("wangluobaomingCount").toString());
															int hujiaozhongxinCount=Integer.valueOf(map.get("hujiaozhongxinCount").toString());
															int xuexizhongxinCount=Integer.valueOf(map.get("xuexizhongxinCount").toString());
															int lishishujuCount=Integer.valueOf(map.get("lishishujuCount").toString());
															
															userZhaoShengZhiBiaoSum+=Integer.valueOf(map.get("userZhaoShengZhiBiao").toString());
															wangluobaomingCountSum+=wangluobaomingCount;
															hujiaozhongxingCountSum+=hujiaozhongxinCount;
															xuexizhongxinCountSum+=xuexizhongxinCount;
															lishishujuCountSum+=lishishujuCount;
															
															
														}
														int fuwuzhanStudentCount=wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum;
														for (Object object : userList) {
															Map<String, Object> map=(Map<String, Object>)object;

															map.put("hejiCountP",getP(format,Integer.valueOf(map.get("hejiCount").toString()),fuwuzhanStudentCount));
														}
														//fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
														Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_");
														fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
														//没有分配给用户的指标
														fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
														fuwuzhanHeJiMap.put("wangluobaomingCountSum", wangluobaomingCountSum);
														fuwuzhanHeJiMap.put("hujiaozhongxingCountSum", hujiaozhongxingCountSum);
														fuwuzhanHeJiMap.put("xuexizhongxinCountSum", xuexizhongxinCountSum);
														fuwuzhanHeJiMap.put("lishishujuCountSum", lishishujuCountSum);
														
														fuwuzhanHeJiMap.put("hejiCountSum", wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum);
														
														fuwuzhanHeJiMap.put("wangluobaomingCountPSum", getP(format,wangluobaomingCountSum,fuwuzhanStudentCount));
														fuwuzhanHeJiMap.put("hujiaozhongxingCountPSum", getP(format,hujiaozhongxingCountSum,fuwuzhanStudentCount));
														fuwuzhanHeJiMap.put("xuexizhongxinCountPSum", getP(format,xuexizhongxinCountSum,fuwuzhanStudentCount));
														fuwuzhanHeJiMap.put("lishishujuCountPSum", getP(format,lishishujuCountSum,fuwuzhanStudentCount));
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
											fuwuMap.put("zhurenName", branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+"")==null?"无":branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+""));
											
											//String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id")+ " and u.status=0 ";
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
																private int getCount(int studentDataSource,int userId,int branchId){
																	return getStudentCount(school,branchId ,batchIds, studentDataSource, way, source,serachStudentDataSource, userId);
																}
																//获取招生指标
																public int gerUserEnrollQuota(int userId){
																	return gerUserEnrollQuotaSum(school,batch,xuexiId,userId);
																}
																public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																	Map<String, Object> userMap = new HashMap<String, Object>();
																	userMap.put("userId",resultSet.getInt("userId"));
																	userMap.put("name",resultSet.getString("name"));
																	
																	//招生指标
																	int userZhaoShengZhiBiao=gerUserEnrollQuota(resultSet.getInt("userId"));
																	userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																	
																	int studentCount=getStudentCountByUserId(resultSet.getInt("userId"));
																	
																	// 网络报名人数
																	int wangluobaomingCount=getCount(Constants.STU_SOURCE_NP,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("wangluobaomingCount",wangluobaomingCount);
																	userMap.put("wangluobaomingCountP",getP(format,wangluobaomingCount,studentCount));
																	
																	// 呼叫中心人数
																	int hujiaozhongxinCount=getCount(Constants.STU_SOURCE_CC,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("hujiaozhongxinCount",hujiaozhongxinCount);
																	userMap.put("hujiaozhongxinCountP",getP(format,hujiaozhongxinCount,studentCount));
																	
																	// 学习中心人数
																	int xuexizhongxinCount=getCount(Constants.STU_SOURCE_LC,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("xuexizhongxinCount",xuexizhongxinCount);
																	userMap.put("xuexizhongxinCountP",getP(format,xuexizhongxinCount,studentCount));
																	
																	// 历史数据人数
																	int lishishujuCount=getCount(Constants.STU_SOURCE_HS,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("lishishujuCount",lishishujuCount);
																	userMap.put("lishishujuCountP",getP(format,lishishujuCount,studentCount));
																	
																	// 合计
																	int count = wangluobaomingCount+hujiaozhongxinCount+xuexizhongxinCount+lishishujuCount;
																	userMap.put("hejiCount",count);
																	//userMap.put("hejiCountP",format.format(new Float(count)/ new Float(studentCount)));
																	return userMap;
																}
															});
											fuwuMap.put("userList", userList);
											//服务站合计
											Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
											//渠道服务站合计
											int userZhaoShengZhiBiaoSum=0;
											int wangluobaomingCountSum=0;
											int hujiaozhongxingCountSum=0;
											int xuexizhongxinCountSum=0;
											int lishishujuCountSum=0;
											

											for (Object object : userList) {
												Map<String, Object> map=(Map<String, Object>)object;
												int wangluobaomingCount=Integer.valueOf(map.get("wangluobaomingCount").toString());
												int hujiaozhongxinCount=Integer.valueOf(map.get("hujiaozhongxinCount").toString());
												int xuexizhongxinCount=Integer.valueOf(map.get("xuexizhongxinCount").toString());
												int lishishujuCount=Integer.valueOf(map.get("lishishujuCount").toString());
												
												
												userZhaoShengZhiBiaoSum+=Integer.valueOf(map.get("userZhaoShengZhiBiao").toString());
												wangluobaomingCountSum+=wangluobaomingCount;
												hujiaozhongxingCountSum+=hujiaozhongxinCount;
												xuexizhongxinCountSum+=xuexizhongxinCount;
												lishishujuCountSum+=lishishujuCount;
												
												
											}
											int fuwuzhanStudentCount=wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum;
											for (Object object : userList) {
												Map<String, Object> map=(Map<String, Object>)object;

												map.put("hejiCountP",getP(format,Integer.valueOf(map.get("hejiCount").toString()),fuwuzhanStudentCount));
											}
											//fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
											Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_");
											fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
											//没有分配给用户的指标
											fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
											fuwuzhanHeJiMap.put("wangluobaomingCountSum", wangluobaomingCountSum);
											fuwuzhanHeJiMap.put("hujiaozhongxingCountSum", hujiaozhongxingCountSum);
											fuwuzhanHeJiMap.put("xuexizhongxinCountSum", xuexizhongxinCountSum);
											fuwuzhanHeJiMap.put("lishishujuCountSum", lishishujuCountSum);
											
											fuwuzhanHeJiMap.put("hejiCountSum", wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum);
											
											fuwuzhanHeJiMap.put("wangluobaomingCountPSum", getP(format,wangluobaomingCountSum,fuwuzhanStudentCount));
											fuwuzhanHeJiMap.put("hujiaozhongxingCountPSum", getP(format,hujiaozhongxingCountSum,fuwuzhanStudentCount));
											fuwuzhanHeJiMap.put("xuexizhongxinCountPSum", getP(format,xuexizhongxinCountSum,fuwuzhanStudentCount));
											fuwuzhanHeJiMap.put("lishishujuCountPSum", getP(format,lishishujuCountSum,fuwuzhanStudentCount));
											fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
											//fuwuzhanHeJiMap.put("hejiCountPSum",format.format(new Float(wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum+laodaixinCountSum+gongjianCountSum+laoshengxuduCountSum)/ new Float(getDayuOne(studentCount)));
											fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
											fuwuList.add(0, fuwuMap);
										}
										xuexiMap.put("fuwuList", fuwuList);
										
										//服务站合计
										Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
										//渠道服务站合计
										int userZhaoShengZhiBiaoSum=0;
										int wangluobaomingCountSum=0;
										int hujiaozhongxingCountSum=0;
										int xuexizhongxinCountSum=0;
										int lishishujuCountSum=0;
										

										for (Object object : fuwuList) {
											Map<String, Object> map=(Map<String, Object>)object;
											int wangluobaomingCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("wangluobaomingCountSum").toString());
											int hujiaozhongxinCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("hujiaozhongxingCountSum").toString());
											int xuexizhongxinCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("xuexizhongxinCountSum").toString());
											int lishishujuCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("lishishujuCountSum").toString());
											
											userZhaoShengZhiBiaoSum+=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userZhaoShengZhiBiaoSum").toString());
											wangluobaomingCountSum+=wangluobaomingCount;
											hujiaozhongxingCountSum+=hujiaozhongxinCount;
											xuexizhongxinCountSum+=xuexizhongxinCount;
											lishishujuCountSum+=lishishujuCount;
											
											
										}
										
										int fuwuzhanStudentCount=wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum;
										for (Object object : fuwuList) {
											Map<String, Object> map=(Map<String, Object>)object;

											((Map)map.get("fuwuzhanHeJiMap")).put("hejiCountPSum",getP(format,Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("hejiCountSum").toString()),fuwuzhanStudentCount));
										}
										
										fuwuzhanHeJiMap.put("x_userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
										fuwuzhanHeJiMap.put("x_wangluobaomingCountSum", wangluobaomingCountSum);
										fuwuzhanHeJiMap.put("x_hujiaozhongxingCountSum", hujiaozhongxingCountSum);
										fuwuzhanHeJiMap.put("x_xuexizhongxinCountSum", xuexizhongxinCountSum);
										fuwuzhanHeJiMap.put("x_lishishujuCountSum", lishishujuCountSum);
										
										fuwuzhanHeJiMap.put("x_hejiCountSum", wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum);
										
										fuwuzhanHeJiMap.put("x_wangluobaomingCountPSum",  getP(format,wangluobaomingCountSum,fuwuzhanStudentCount));
										fuwuzhanHeJiMap.put("x_hujiaozhongxingCountPSum",getP(format,hujiaozhongxingCountSum,fuwuzhanStudentCount));
										fuwuzhanHeJiMap.put("x_xuexizhongxinCountPSum", getP(format,xuexizhongxinCountSum,fuwuzhanStudentCount));
										fuwuzhanHeJiMap.put("x_lishishujuCountPSum",getP(format,lishishujuCountSum,fuwuzhanStudentCount));
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
						//服务站合计
						Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
						//渠道服务站合计
						int userZhaoShengZhiBiaoSum=0;
						int wangluobaomingCountSum=0;
						int hujiaozhongxingCountSum=0;
						int xuexizhongxinCountSum=0;
						int lishishujuCountSum=0;
						

						for (Object object : xuexiList) {
							Map<String, Object> map=(Map<String, Object>)object;
							int wangluobaomingCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_wangluobaomingCountSum").toString());
							int hujiaozhongxinCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_hujiaozhongxingCountSum").toString());
							int xuexizhongxinCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_xuexizhongxinCountSum").toString());
							int lishishujuCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_lishishujuCountSum").toString());
							
							
							userZhaoShengZhiBiaoSum+=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_userZhaoShengZhiBiaoSum").toString());
							wangluobaomingCountSum+=wangluobaomingCount;
							hujiaozhongxingCountSum+=hujiaozhongxinCount;
							xuexizhongxinCountSum+=xuexizhongxinCount;
							lishishujuCountSum+=lishishujuCount;
							
							
						}
						int fuwuzhanStudentCount=wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum;
						
						for (Object object : xuexiList) {
							Map<String, Object> map=(Map<String, Object>)object;
							((Map)map.get("fuwuzhanHeJiMap")).put("x_hejiCountPSum",getP(format,Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("x_hejiCountSum").toString()),fuwuzhanStudentCount));
						}
						
						fuwuzhanHeJiMap.put("z_x_userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
						fuwuzhanHeJiMap.put("z_x_wangluobaomingCountSum", wangluobaomingCountSum);
						fuwuzhanHeJiMap.put("z_x_hujiaozhongxingCountSum", hujiaozhongxingCountSum);
						fuwuzhanHeJiMap.put("z_x_xuexizhongxinCountSum", xuexizhongxinCountSum);
						fuwuzhanHeJiMap.put("z_x_lishishujuCountSum", lishishujuCountSum);
						
						fuwuzhanHeJiMap.put("z_x_hejiCountSum", wangluobaomingCountSum+hujiaozhongxingCountSum+xuexizhongxinCountSum+lishishujuCountSum);
						
						fuwuzhanHeJiMap.put("z_x_wangluobaomingCountPSum", getP(format,wangluobaomingCountSum,fuwuzhanStudentCount));
						fuwuzhanHeJiMap.put("z_x_hujiaozhongxingCountPSum",getP(format,hujiaozhongxingCountSum,fuwuzhanStudentCount));
						fuwuzhanHeJiMap.put("z_x_xuexizhongxinCountPSum",getP(format,xuexizhongxinCountSum,fuwuzhanStudentCount));
						fuwuzhanHeJiMap.put("z_x_lishishujuCountPSum",getP(format,lishishujuCountSum,fuwuzhanStudentCount));
						//总合计
						hejiList.add(fuwuzhanStudentCount);
						wangluobaomingSumList.add(wangluobaomingCountSum);
						hujiaozhongxinSumList.add(hujiaozhongxingCountSum);
						xuexizhongxinSumList.add(xuexizhongxinCountSum);
						lishishujuSumList.add(lishishujuCountSum);
						zhibiaoSumList.add(userZhaoShengZhiBiaoSum);
						
						
						
						quyuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
						return quyuMap;
					}
				});
				jdbcTemplatePlus.releaseConnection();
				List result=new ArrayList();
				Map<String,Object> sumMap=new HashMap<String, Object>();
				sumMap.put("zhubiaoSum", getListSum(zhibiaoSumList));
				sumMap.put("wangluobaomingSum", getListSum(wangluobaomingSumList));
				sumMap.put("wangluobaomingSumP",  getP(format,getListSum(wangluobaomingSumList),getListSum(hejiList)));
				
				sumMap.put("hujiaozhongxinSum", getListSum(hujiaozhongxinSumList));
				sumMap.put("hujiaozhongxinSumP",  getP(format,getListSum(hujiaozhongxinSumList),getListSum(hejiList)));
				
				sumMap.put("xuexizhongxinSum", getListSum(xuexizhongxinSumList));
				sumMap.put("xuexizhongxinSumP",  getP(format,getListSum(xuexizhongxinSumList),getListSum(hejiList)));
				
				sumMap.put("lishishujuSum", getListSum(lishishujuSumList));
				sumMap.put("lishishujuSumP",  getP(format,getListSum(lishishujuSumList),getListSum(hejiList)));

				sumMap.put("heji", getListSum(hejiList));
				
				//区域经理总和记的％
				for (Object object : quyuList) {
					if(object!=null){
						Map map=(Map)object;
						Map map1=(Map)map.get("fuwuzhanHeJiMap");
						//区域合计数
						int quyuCount=Integer.valueOf(map1.get("z_x_hejiCountSum").toString());
						map1.put("z_x_hejiCountSumP", getP(format,quyuCount,getListSum(hejiList)));
						//System.out.println(quyuCount);
					}
				}
				
				sumMap.put("quyuList", quyuList);

				return sumMap;
				//long e = System.currentTimeMillis();
				//System.out.println("倒入数据用时：" + (e - s));
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	//通过 用户ID，招生途径，院校，批次，数据来源，市场途径查询数量
	final public int getStudentCount(int school,int branchId,String batch,int studentDataSource,int way,int enrollmentSource,int searchStudentDataSource,int userId){
		
		if(searchStudentDataSource!=-2){
			if(studentDataSource==searchStudentDataSource){
				//System.out.println("2222222222222222");
			}else{
				return 0;
			}
		}
		
		int count = 0;
		String sql="select IFNULL(count(*),0) from tb_e_student where status<>"+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and  user_id="+ userId+ " and student_data_source="+studentDataSource;
		//院校
		if(school!=-2){
			sql+=" and academy_id="+school;
		}
		//批次
		if(batch!=null&&!batch.equals("0")){
			sql+=" and enrollment_batch_id in ("+batch+")";
		}
	
//		//学习中心
//		if(branchId!=-2){
//			sql+=" and branch_id="+branchId;
//		}
//		//数据来源
//		if(studentDataSource!=-2){
//			sql+=" and student_data_source="+studentDataSource;
//		}
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
		//招生途径
		if(enrollmentSource!=-2){
			sql+=" and enrollment_source="+enrollmentSource;
		}
		System.out.println(sql);
		count= jdbcTemplatePlus.queryForInt(sql);
		return count;
	}
	final public int getStudentCountByUserId(int userId){
		String sql="select IFNULL(count(*),0) from tb_e_student where  user_id="+ userId;
		int count = jdbcTemplatePlus.queryForInt(sql);
		if(count==0){
			//避免0做除数
			count=1;
		}
//		System.out.println(count);
		return  count;
		
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
		return number;
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
//		System.out.println(sql);
		return jdbcTemplatePlus.queryForInt(sql);
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
	final private String getP(final NumberFormat format,int number1,int number2){
		if(number2==0){
			return "-";
		}
		return format.format(new Float(number1)/ new Float(getDayuOne(getDayuOne(number2))));
	}
}
