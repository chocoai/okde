/**
 * 文件名：StudentSourceReportImpl.java
 * 包名：net.cedu.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-23 上午09:58:29
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
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.EnrollmentSourceReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：学生招生途径报表接口实现类
 *
 * @作者： 杨栋栋
 * @作成时间：2011-12-26 下午03:14:30
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Repository
public class EnrollmentSourceReportImpl extends BaseReportDaoImpl implements
		EnrollmentSourceReport {

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
	public Map statistics(Map<String, Integer> params, Map<String, Date> dateParams) {
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
		
		final Date startDate = dateParams.get("startDate");// 开始日期
		final Date endDate = dateParams.get("endDate");// 结束日期
		
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
				final List<Integer> shezhaoSumList=new ArrayList<Integer>();
				final List<Integer> qudaoSumList=new ArrayList<Integer>();
				final List<Integer> dakehuSumList=new ArrayList<Integer>();
				final List<Integer> laodaixinSumList=new ArrayList<Integer>();
				final List<Integer> laoshengxuduSumList=new ArrayList<Integer>();
				final List<Integer> jiamengSumList=new ArrayList<Integer>();
				final List<Integer> gongjianSumList=new ArrayList<Integer>();
				
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
										
										xuexiMap.put("zhurenName", branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+"")==null?"无":branchId_zhongxinzhurenName_map.get(resultSet.getInt("branch_id")+""));
										
										
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
													private int getCount(int enrollmentSource,int userId,int branchId){
														return getStudentCount(school,branchId, batchIds, studentDataSource, way, enrollmentSource,source, userId, startDate, endDate);
													}
													//获取招生指标
													public int gerUserEnrollQuota(int userId){
														return gerUserEnrollQuotaSum(school,batch,xuexiId,userId);
													}
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
																			public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																				Map<String, Object> userMap = new HashMap<String, Object>();
																				userMap.put("userId",resultSet.getInt("userId"));
																				userMap.put("name",resultSet.getString("name"));
																				//招生指标
																				int userZhaoShengZhiBiao=gerUserEnrollQuota(resultSet.getInt("userId"));
																				userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																				// 渠道人数
																				int qudaoCount=getCount(Constants.WEB_STU_QV_DAO,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("qudaoCount",qudaoCount);
																				
																				// 大客户人数
																				int dakehuCount = getCount(Constants.WEB_STU_DA_KE_HU,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("dakehuCount",dakehuCount);
																				// 社招人数
																				int shezhaoCount = getCount(Constants.WEB_STU_SOURCE_DEFAULT,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("shezhaoCount",shezhaoCount);
																				// 加盟人数
																				int jiamengCount = getCount(Constants.WEB_STU_JIA_MENG,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("jiamengCount",jiamengCount);
																				// 老带新人数
																				int laodaixinCount = getCount(Constants.WEB_STU_ENROLLMENT_SOURCE,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("laodaixinCount",laodaixinCount);
																				// 共建人数
																				int gongjianCount = getCount(Constants.WEB_STU_GONG_JIAN,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("gongjianCount",gongjianCount);
																				//老生续读
																				int laoshengxuduCount=getCount(Constants.WEB_STU_LAO_SHENG_XU_DU,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																				userMap.put("laoshengxuduCount",laoshengxuduCount);
																				// 合计
																				int count = qudaoCount+ dakehuCount+ shezhaoCount+ jiamengCount+ laodaixinCount+ gongjianCount;
																				
																				userMap.put("qudaoCountP",getP(format,qudaoCount,getDayuOne(count)));
																				userMap.put("dakehuCountP",getP(format,dakehuCount,getDayuOne(count)));
																				userMap.put("shezhaoCountP",getP(format,shezhaoCount,getDayuOne(count)));
																				userMap.put("jiamengCountP",getP(format,jiamengCount,getDayuOne(count)));
																				userMap.put("laodaixinCountP",getP(format,laodaixinCount,getDayuOne(count)));
																				userMap.put("gongjianCountP",getP(format,gongjianCount,getDayuOne(count)));
																				userMap.put("laoshengxuduCountP",getP(format,laoshengxuduCount,getDayuOne(count)));

																				userMap.put("hejiCount",count);
																				//userMap.put("hejiCountP",getP(format,count,getDayuOne(studentCount)));
																				return userMap;
																			}
																		});
														fuwuMap.put("userList",userList);
														
														//服务站合计
														Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
														//渠道服务站合计
														int userZhaoShengZhiBiaoSum=0;
														int qudaoCountSum=0;
														int dakehuCountSum=0;
														int shezhaoCountSum=0;
														int jiamengCountSum=0;
														int laodaixinCountSum=0;
														int gongjianCountSum=0;
														int laoshengxuduCountSum=0;

														for (Object object : userList) {
															Map<String, Object> map=(Map<String, Object>)object;
															int qudaoCount=Integer.valueOf(map.get("qudaoCount").toString());
															int dakehuCount=Integer.valueOf(map.get("dakehuCount").toString());
															int shezhaoCount=Integer.valueOf(map.get("shezhaoCount").toString());
															int jiamengCount=Integer.valueOf(map.get("jiamengCount").toString());
															int laodaixinCount=Integer.valueOf(map.get("laodaixinCount").toString());
															int gongjianCount=Integer.valueOf(map.get("gongjianCount").toString());
															int laoshengxuduCount=Integer.valueOf(map.get("laoshengxuduCount").toString());
															
															userZhaoShengZhiBiaoSum+=Integer.valueOf(map.get("userZhaoShengZhiBiao").toString());
															qudaoCountSum+=qudaoCount;
															dakehuCountSum+=dakehuCount;
															shezhaoCountSum+=shezhaoCount;
															jiamengCountSum+=jiamengCount;
															laodaixinCountSum+=laodaixinCount;
															gongjianCountSum+=gongjianCount;
															laoshengxuduCountSum+=laoshengxuduCount;
															
														}
														int fuwuzhanStudentCount=qudaoCountSum+dakehuCountSum+shezhaoCountSum+jiamengCountSum+laodaixinCountSum+gongjianCountSum+laoshengxuduCountSum;
														for (Object object : userList) {
															Map<String, Object> map=(Map<String, Object>)object;

															map.put("hejiCountP",getP(format,Integer.valueOf(map.get("hejiCount").toString()),getDayuOne(fuwuzhanStudentCount)));
														}
														//fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
														
														Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_");
														fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
														//没有分配给用户的指标
														fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
														fuwuzhanHeJiMap.put("qudaoCountSum", qudaoCountSum);
														fuwuzhanHeJiMap.put("dakehuCountSum", dakehuCountSum);
														fuwuzhanHeJiMap.put("shezhaoCountSum", shezhaoCountSum);
														fuwuzhanHeJiMap.put("jiamengCountSum", jiamengCountSum);
														fuwuzhanHeJiMap.put("laodaixinCountSum", laodaixinCountSum);
														fuwuzhanHeJiMap.put("gongjianCountSum", gongjianCountSum);
														fuwuzhanHeJiMap.put("laoshengxuduCountSum", laoshengxuduCountSum);
														fuwuzhanHeJiMap.put("hejiCountSum", qudaoCountSum+dakehuCountSum+shezhaoCountSum+jiamengCountSum+laodaixinCountSum+gongjianCountSum+laoshengxuduCountSum);
														
														fuwuzhanHeJiMap.put("qudaoCountPSum",  getP(format,qudaoCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("dakehuCountPSum", getP(format,dakehuCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("shezhaoCountPSum", getP(format,shezhaoCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("jiamengCountPSum", getP(format,jiamengCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("laodaixinCountPSum", getP(format,laodaixinCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("gongjianCountPSum", getP(format,gongjianCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("laoshengxuduCountPSum", getP(format,laoshengxuduCountSum,getDayuOne(fuwuzhanStudentCount)));
														fuwuzhanHeJiMap.put("hejiCountPSum","");
														//fuwuzhanHeJiMap.put("hejiCountPSum","300%");
														//fuwuzhanHeJiMap.put("hejiCountPSum",getP(format,qudaoCountSum+dakehuCountSum+shezhaoCountSum+jiamengCountSum+laodaixinCountSum+gongjianCountSum+laoshengxuduCountSum,getDayuOne(studentCount)));
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
																private int getCount(int enrollmentSource,int userId,int branchId){
																	return getStudentCount(school, branchId,batchIds, studentDataSource, way, enrollmentSource,source, userId, startDate, endDate);
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
																	// 渠道人数
																	int qudaoCount=getCount(Constants.WEB_STU_QV_DAO,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("qudaoCount",qudaoCount);
																	// 大客户人数
																	int dakehuCount = getCount(Constants.WEB_STU_DA_KE_HU,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("dakehuCount",dakehuCount);
																	// 社招人数
																	int shezhaoCount = getCount(Constants.WEB_STU_SOURCE_DEFAULT,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("shezhaoCount",shezhaoCount);
																	// 加盟人数
																	int jiamengCount = getCount(Constants.WEB_STU_JIA_MENG,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("jiamengCount",jiamengCount);
																	// 老带新人数
																	int laodaixinCount = getCount(Constants.WEB_STU_ENROLLMENT_SOURCE,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("laodaixinCount",laodaixinCount);
																	// 共建人数
																	int gongjianCount = getCount(Constants.WEB_STU_GONG_JIAN,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("gongjianCount",gongjianCount);
																	//老生续读
																	int laoshengxuduCount=getCount(Constants.WEB_STU_LAO_SHENG_XU_DU,resultSet.getInt("userId"),resultSet.getInt("branch_id"));
																	userMap.put("laoshengxuduCount",laoshengxuduCount);
																	// 合计
																	int count = qudaoCount+ dakehuCount+ shezhaoCount+ jiamengCount+ laodaixinCount+ gongjianCount;
																	
																	userMap.put("qudaoCountP",getP(format,qudaoCount,getDayuOne(count)));
																	userMap.put("dakehuCountP",getP(format,dakehuCount,getDayuOne(count)));
																	userMap.put("shezhaoCountP",getP(format,shezhaoCount,getDayuOne(count)));
																	userMap.put("jiamengCountP",getP(format,jiamengCount,getDayuOne(count)));
																	userMap.put("laodaixinCountP",getP(format,laodaixinCount,getDayuOne(count)));
																	userMap.put("gongjianCountP",getP(format,gongjianCount,getDayuOne(count)));
																	userMap.put("laoshengxuduCountP",getP(format,laoshengxuduCount,getDayuOne(count)));
																	userMap.put("hejiCount",count);
																	//userMap.put("hejiCountP",getP(format,count,getDayuOne(studentCount)));
																	return userMap;
																}
															});
											fuwuMap.put("userList", userList);
											//服务站合计
											Map<String, Object> fuwuzhanHeJiMap = new HashMap<String, Object>();
											//渠道服务站合计
											int userZhaoShengZhiBiaoSum=0;
											int qudaoCountSum=0;
											int dakehuCountSum=0;
											int shezhaoCountSum=0;
											int jiamengCountSum=0;
											int laodaixinCountSum=0;
											int gongjianCountSum=0;
											int laoshengxuduCountSum=0;

											for (Object object : userList) {
												Map<String, Object> map=(Map<String, Object>)object;
												int qudaoCount=Integer.valueOf(map.get("qudaoCount").toString());
												int dakehuCount=Integer.valueOf(map.get("dakehuCount").toString());
												int shezhaoCount=Integer.valueOf(map.get("shezhaoCount").toString());
												int jiamengCount=Integer.valueOf(map.get("jiamengCount").toString());
												int laodaixinCount=Integer.valueOf(map.get("laodaixinCount").toString());
												int gongjianCount=Integer.valueOf(map.get("gongjianCount").toString());
												int laoshengxuduCount=Integer.valueOf(map.get("laoshengxuduCount").toString());
												
												userZhaoShengZhiBiaoSum+=Integer.valueOf(map.get("userZhaoShengZhiBiao").toString());
												qudaoCountSum+=qudaoCount;
												dakehuCountSum+=dakehuCount;
												shezhaoCountSum+=shezhaoCount;
												jiamengCountSum+=jiamengCount;
												laodaixinCountSum+=laodaixinCount;
												gongjianCountSum+=gongjianCount;
												laoshengxuduCountSum+=laoshengxuduCount;
												
											}
											int fuwuzhanStudentCount=qudaoCountSum+dakehuCountSum+shezhaoCountSum+jiamengCountSum+laodaixinCountSum+gongjianCountSum+laoshengxuduCountSum;
											for (Object object : userList) {
												Map<String, Object> map=(Map<String, Object>)object;

												map.put("hejiCountP",getP(format,Integer.valueOf(map.get("hejiCount").toString()),getDayuOne(fuwuzhanStudentCount)));
											}
											//int fuwuzhanStudentCount=getStudentCountByBranchId(resultSet.getInt("branch_id"));
											
											//System.out.println(fuwuzhanStudentCount);
											//fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum);
											Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_");
											fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
											//没有分配给用户的指标
											fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
											
											fuwuzhanHeJiMap.put("qudaoCountSum", qudaoCountSum);
											fuwuzhanHeJiMap.put("dakehuCountSum", dakehuCountSum);
											fuwuzhanHeJiMap.put("shezhaoCountSum", shezhaoCountSum);
											fuwuzhanHeJiMap.put("jiamengCountSum", jiamengCountSum);
											fuwuzhanHeJiMap.put("laodaixinCountSum", laodaixinCountSum);
											fuwuzhanHeJiMap.put("gongjianCountSum", gongjianCountSum);
											fuwuzhanHeJiMap.put("laoshengxuduCountSum", laoshengxuduCountSum);
											fuwuzhanHeJiMap.put("hejiCountSum", qudaoCountSum+dakehuCountSum+shezhaoCountSum+jiamengCountSum+laodaixinCountSum+gongjianCountSum+laoshengxuduCountSum);
											
											fuwuzhanHeJiMap.put("qudaoCountPSum",  getP(format,qudaoCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuzhanHeJiMap.put("dakehuCountPSum", getP(format,dakehuCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuzhanHeJiMap.put("shezhaoCountPSum", getP(format,shezhaoCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuzhanHeJiMap.put("jiamengCountPSum", getP(format,jiamengCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuzhanHeJiMap.put("laodaixinCountPSum", getP(format,laodaixinCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuzhanHeJiMap.put("gongjianCountPSum", getP(format,gongjianCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuzhanHeJiMap.put("laoshengxuduCountPSum", getP(format,laoshengxuduCountSum,getDayuOne(fuwuzhanStudentCount)));
											fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
											fuwuList.add(0, fuwuMap);
										}
										xuexiMap.put("fuwuList", fuwuList);
										
										Map<String, Object> x_fuwuzhanHeJiMap = new HashMap<String, Object>();
										int x_userZhaoShengZhiBiaoSum=0;
										int x_qudaoCountSum=0;
										int x_dakehuCountSum=0;
										int x_shezhaoCountSum=0;
										int x_jiamengCountSum=0;
										int x_laodaixinCountSum=0;
										int x_gongjianCountSum=0;
										int x_laoshengxuduCountSum=0;
										for (Object object : fuwuList) {
											Map<String, Object> map=(Map<String, Object>)object;
											int x_qudaoCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("qudaoCountSum").toString());
											int x_dakehuCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("dakehuCountSum").toString());
											int x_shezhaoCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("shezhaoCountSum").toString());
											int x_jiamengCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("jiamengCountSum").toString());
											int x_laodaixinCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("laodaixinCountSum").toString());
											int x_gongjianCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("gongjianCountSum").toString());
											int x_laoshengxuduCount=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("laoshengxuduCountSum").toString());
											
											x_userZhaoShengZhiBiaoSum+=Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("userZhaoShengZhiBiaoSum").toString());
											x_qudaoCountSum+=x_qudaoCount;
											x_dakehuCountSum+=x_dakehuCount;
											x_shezhaoCountSum+=x_shezhaoCount;
											x_jiamengCountSum+=x_jiamengCount;
											x_laodaixinCountSum+=x_laodaixinCount;
											x_gongjianCountSum+=x_gongjianCount;
											x_laoshengxuduCountSum+=x_laoshengxuduCount;

										}
										
										int x_fuwuzhanStudentCount=x_qudaoCountSum+x_dakehuCountSum+x_shezhaoCountSum+x_jiamengCountSum+x_laodaixinCountSum+x_gongjianCountSum+x_laoshengxuduCountSum;
										for (Object object : fuwuList) {
											Map<String, Object> map=(Map<String, Object>)object;
											((Map)map.get("fuwuzhanHeJiMap")).put("hejiCountPSum",getP(format,Integer.valueOf(((Map)map.get("fuwuzhanHeJiMap")).get("hejiCountSum").toString()),getDayuOne(x_fuwuzhanStudentCount)));
										}
										
										x_fuwuzhanHeJiMap.put("x_userZhaoShengZhiBiaoSum", x_userZhaoShengZhiBiaoSum);
										
										x_fuwuzhanHeJiMap.put("x_qudaoCountSum", x_qudaoCountSum);
										x_fuwuzhanHeJiMap.put("x_dakehuCountSum", x_dakehuCountSum);
										x_fuwuzhanHeJiMap.put("x_shezhaoCountSum", x_shezhaoCountSum);
										x_fuwuzhanHeJiMap.put("x_jiamengCountSum", x_jiamengCountSum);
										x_fuwuzhanHeJiMap.put("x_laodaixinCountSum", x_laodaixinCountSum);
										x_fuwuzhanHeJiMap.put("x_gongjianCountSum", x_gongjianCountSum);
										x_fuwuzhanHeJiMap.put("x_laoshengxuduCountSum", x_laoshengxuduCountSum);
										x_fuwuzhanHeJiMap.put("x_hejiCountSum", x_qudaoCountSum+x_dakehuCountSum+x_shezhaoCountSum+x_jiamengCountSum+x_laodaixinCountSum+x_gongjianCountSum+x_laoshengxuduCountSum);
										
										x_fuwuzhanHeJiMap.put("x_qudaoCountPSum",  getP(format,x_qudaoCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										x_fuwuzhanHeJiMap.put("x_dakehuCountPSum", getP(format,x_dakehuCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										x_fuwuzhanHeJiMap.put("x_shezhaoCountPSum", getP(format,x_shezhaoCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										x_fuwuzhanHeJiMap.put("x_jiamengCountPSum", getP(format,x_jiamengCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										x_fuwuzhanHeJiMap.put("x_laodaixinCountPSum", getP(format,x_laodaixinCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										x_fuwuzhanHeJiMap.put("x_gongjianCountPSum", getP(format,x_gongjianCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										x_fuwuzhanHeJiMap.put("x_laoshengxuduCountPSum", getP(format,x_laoshengxuduCountSum,getDayuOne(x_fuwuzhanStudentCount)));
										xuexiMap.put("x_fuwuzhanHeJiMap", x_fuwuzhanHeJiMap);
										
		
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
						
						
						
						Map<String, Object> z_x_fuwuzhanHeJiMap = new HashMap<String, Object>();
						int z_x_userZhaoShengZhiBiaoSum=0;
						int z_x_qudaoCountSum=0;
						int z_x_dakehuCountSum=0;
						int z_x_shezhaoCountSum=0;
						int z_x_jiamengCountSum=0;
						int z_x_laodaixinCountSum=0;
						int z_x_gongjianCountSum=0;
						int z_x_laoshengxuduCountSum=0;
						for (Object object : xuexiList) {
							Map<String, Object> map=(Map<String, Object>)object;
							int z_x_qudaoCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_qudaoCountSum").toString());
							int z_x_dakehuCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_dakehuCountSum").toString());
							int z_x_shezhaoCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_shezhaoCountSum").toString());
							int z_x_jiamengCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_jiamengCountSum").toString());
							int z_x_laodaixinCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_laodaixinCountSum").toString());
							int z_x_gongjianCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_gongjianCountSum").toString());
							int z_x_laoshengxuduCount=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_laoshengxuduCountSum").toString());
							
							
							z_x_userZhaoShengZhiBiaoSum+=Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_userZhaoShengZhiBiaoSum").toString());
							z_x_qudaoCountSum+=z_x_qudaoCount;
							z_x_dakehuCountSum+=z_x_dakehuCount;
							z_x_shezhaoCountSum+=z_x_shezhaoCount;
							z_x_jiamengCountSum+=z_x_jiamengCount;
							z_x_laodaixinCountSum+=z_x_laodaixinCount;
							z_x_gongjianCountSum+=z_x_gongjianCount;
							z_x_laoshengxuduCountSum+=z_x_laoshengxuduCount;

						}
						
						int z_x_fuwuzhanStudentCount=z_x_qudaoCountSum+z_x_dakehuCountSum+z_x_shezhaoCountSum+z_x_jiamengCountSum+z_x_laodaixinCountSum+z_x_gongjianCountSum+z_x_laoshengxuduCountSum;
						for (Object object : xuexiList) {
							Map<String, Object> map=(Map<String, Object>)object;
							((Map)map.get("x_fuwuzhanHeJiMap")).put("z_x_hejiCountSum",getP(format,Integer.valueOf(((Map)map.get("x_fuwuzhanHeJiMap")).get("x_hejiCountSum").toString()),getDayuOne(z_x_fuwuzhanStudentCount)));
						
						}
						
						
						z_x_fuwuzhanHeJiMap.put("z_x_userZhaoShengZhiBiaoSum", z_x_userZhaoShengZhiBiaoSum);
						
						z_x_fuwuzhanHeJiMap.put("z_x_qudaoCountSum", z_x_qudaoCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_dakehuCountSum", z_x_dakehuCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_shezhaoCountSum", z_x_shezhaoCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_jiamengCountSum", z_x_jiamengCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_laodaixinCountSum", z_x_laodaixinCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_gongjianCountSum", z_x_gongjianCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_laoshengxuduCountSum", z_x_laoshengxuduCountSum);
						z_x_fuwuzhanHeJiMap.put("z_x_hejiCountSum", z_x_qudaoCountSum+z_x_dakehuCountSum+z_x_shezhaoCountSum+z_x_jiamengCountSum+z_x_laodaixinCountSum+z_x_gongjianCountSum+z_x_laoshengxuduCountSum);
						
						z_x_fuwuzhanHeJiMap.put("z_x_qudaoCountPSum",  getP(format,z_x_qudaoCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						z_x_fuwuzhanHeJiMap.put("z_x_dakehuCountPSum", getP(format,z_x_dakehuCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						z_x_fuwuzhanHeJiMap.put("z_x_shezhaoCountPSum", getP(format,z_x_shezhaoCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						z_x_fuwuzhanHeJiMap.put("z_x_jiamengCountPSum", getP(format,z_x_jiamengCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						z_x_fuwuzhanHeJiMap.put("z_x_laodaixinCountPSum", getP(format,z_x_laodaixinCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						z_x_fuwuzhanHeJiMap.put("z_x_gongjianCountPSum", getP(format,z_x_gongjianCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						z_x_fuwuzhanHeJiMap.put("z_x_laoshengxuduCountPSum", getP(format,z_x_laoshengxuduCountSum,getDayuOne(z_x_fuwuzhanStudentCount)));
						
						//总合计
						zhibiaoSumList.add(z_x_userZhaoShengZhiBiaoSum);
						shezhaoSumList.add(z_x_shezhaoCountSum);
						qudaoSumList.add(z_x_qudaoCountSum);
						dakehuSumList.add(z_x_dakehuCountSum);
						laodaixinSumList.add(z_x_laodaixinCountSum);
						laoshengxuduSumList.add(z_x_laoshengxuduCountSum);
						jiamengSumList.add(z_x_jiamengCountSum);
						gongjianSumList.add(z_x_gongjianCountSum);
						hejiList.add(z_x_qudaoCountSum+z_x_dakehuCountSum+z_x_shezhaoCountSum+z_x_jiamengCountSum+z_x_laodaixinCountSum+z_x_gongjianCountSum+z_x_laoshengxuduCountSum);
						
						quyuMap.put("z_x_fuwuzhanHeJiMap", z_x_fuwuzhanHeJiMap);
		
						return quyuMap;
					}
				});
				jdbcTemplatePlus.releaseConnection();
				//long e = System.currentTimeMillis();
				//System.out.println("倒入数据用时：" + (e - s));
				Map<String,Object> sumMap=new HashMap<String, Object>();
				sumMap.put("zhubiaoSum", getListSum(zhibiaoSumList));
				sumMap.put("shezhaoSum", getListSum(shezhaoSumList));
				sumMap.put("shezhaoSumP", getP(format,getListSum(shezhaoSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("qudaoSum", getListSum(qudaoSumList));
				sumMap.put("qudaoSumP", getP(format,getListSum(qudaoSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("dakehuSum", getListSum(dakehuSumList));
				sumMap.put("dakehuSumP", getP(format,getListSum(dakehuSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("laodaixinSum", getListSum(laodaixinSumList));
				sumMap.put("laodaixinSumP", getP(format, getListSum(laodaixinSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("laoshengxuduSum", getListSum(laoshengxuduSumList));
				sumMap.put("laoshengxuduSumP", getP(format,getListSum(laoshengxuduSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("jiamengSum", getListSum(jiamengSumList));
				sumMap.put("jiamengSumP", getP(format,getListSum(jiamengSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("gongjianSum", getListSum(gongjianSumList));
				sumMap.put("gongjianSumP", getP(format,getListSum(gongjianSumList),getDayuOne(getListSum(hejiList))));
				
				sumMap.put("heji", getListSum(hejiList));
				
				//区域经理总和记的％
				for (Object object : quyuList) {
					if(object!=null){
						Map map=(Map)object;
						Map map1=(Map)map.get("z_x_fuwuzhanHeJiMap");
						//区域合计数
						int quyuCount=Integer.valueOf(map1.get("z_x_hejiCountSum").toString());
						map1.put("z_x_hejiCountSumP", getP(format,quyuCount,getDayuOne(getListSum(hejiList))));
						//System.out.println(quyuCount);
					}
				}
				
				sumMap.put("quyuList", quyuList);

				return sumMap;
		}catch(Exception e){
			e.printStackTrace();
			return new HashMap();
		}
		
	}
	//通过 用户ID，招生途径，院校，批次，数据来源，市场途径查询数量
	final public int getStudentCount(int school,int branchId,String batch,int studentDataSource,int way,int enrollmentSource,int serachSource,int userId, Date startDate, Date endDate){
		
		if(serachSource!=-2){
			if(enrollmentSource==serachSource){
				//System.out.println("2222222222222222");
			}else{
				return 0;
			}
		}
		
		int count = 0;
		String sql="select IFNULL(count(*),0) from tb_e_student where status<>"+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>"+Constants.STU_CALL_STATUS_YI_DAO_MING+" and  user_id="+ userId+ " and enrollment_source="+enrollmentSource;
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
		//数据来源
		if(studentDataSource!=-2){
			sql+=" and student_data_source="+studentDataSource;
		}
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
		//报名时间
		if(startDate!=null){
			sql+=" and registration_time >= '" + DateUtil.getDate(startDate, "yyyy-MM-dd") + " 00:00:00' ";
		}
		if(endDate!=null){
			sql+=" and registration_time <= '" + DateUtil.getDate(endDate, "yyyy-MM-dd") + " 23:59:59' ";
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
		return  count;
		
	}
	
	final public int getStudentCountByBranchId(int branchId){
		String sql="select IFNULL(count(*),0) from tb_e_student where  branch_id="+ branchId;
		int count = jdbcTemplatePlus.queryForInt(sql);
		if(count==0){
			//避免0做除数
			count=1;
		}
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
//		return number==0?1:number;
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
			sql+=" and branch_id in ( "+branchIds + " ) ";
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
