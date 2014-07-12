/**
 * 文件名：DailyReportImpl.java
 * 包名：net.cedu.report.dao.impl
 * 功能： TODO /请自行添加
 *
 * 作者：Administrator    
 * 日期：2011-12-23 上午11:05:39
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
import net.cedu.common.string.HtmlRegexpUtil;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.worklog.Worklog;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.DailyReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：日报表
 *
 * @作者： 杨栋栋
 * @作成时间：2012-1-9 下午03:42:59
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Repository
public class DailyReportImpl extends BaseReportDaoImpl implements DailyReport{
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;


	/**
	 * 接口方法实现
	  * @see net.cedu.student.report.dao.DailyReport#statistics(java.util.Map, java.util.Map)
	 */
	public List statistics(Map<String, Integer> params,Map<String, Date> dateParams) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int school = params.get("school");// 学院
		final int batch1 = params.get("batch1");// 全局批次
		final int batch2 = params.get("batch2");// 全局批次
		final int studentDataSource = params.get("studentDataSource");// 数据来源
		final int way = params.get("way");// 市场途径
		final int source = params.get("source");// 学生来源
		final int quyuId = params.get("manager");// 区域经理ID
		final int xuexiId = params.get("branch");// 学习中心
		final int fuwuId = params.get("fuwu");// 服务站ID
		final int userId = params.get("user");// 用户ID
		final Date date=dateParams.get("date");//开始日期
		try{
				//long s = System.currentTimeMillis();
			//生成%数
//				final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
//				format.setMinimumFractionDigits(2);// 设置小数位
				
				//日报表Map  key=userId+_+日期
				final Map<String,Worklog> ri_bao_biao_map=getWorklogMap(date);
				final String dateStr=DateUtil.getDate(date, "yyyy-MM-dd");
				
				// 查询所有区域经理ID
				String quyuIdsSql = "select DISTINCT manager_id from tb_r_area_manager_branch where 1=1";
		
				// 默认查询所有
				if (quyuId != -2) {
					quyuIdsSql += " and manager_id=" + quyuId;
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
									//private int i=1;
									public Object mapRow(ResultSet resultSet, int index)
											throws SQLException {
										Map<String, Object> xuexiMap = new HashMap<String, Object>();
										// 学习中心序号
										//xuexiMap.put("xuexiNumber",i++);
										// 学习中心ID
										xuexiMap.put("xuexiId",resultSet.getInt("branch_id"));
										// 学习中心名称
										Branch branch = branchDao.findById(resultSet.getInt("branch_id"));
										xuexiMap.put("xuexiName",branch != null ? branch.getName() : "");
										// 学习中心下面的服务站
		
										//学生所有数量
										//final int studentCount = jdbcTemplatePlus.queryForInt("select count(*) from tb_e_student");
										//招生批次IDs字符串
										final String batchIds=getEnrollmentBatchId(school,batch2);
										
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
													private int getCount(int enrollmentSource,int userId){
														return getStudentCount(school, batchIds, studentDataSource, way, enrollmentSource,source, userId);
													}
													//获取招生指标
													public int gerUserEnrollQuota(int userId){
														return gerUserEnrollQuotaSum(school,batch1,xuexiId,userId);
													}
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
														String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("fuwuId")+ " and u.status=0 ";
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
																				
																				//全年指标
																				userMap.put("quanmianzhibiaoCount", 0);
																				
																				//本批次招生指标
																				int userZhaoShengZhiBiao=gerUserEnrollQuota(resultSet.getInt("userId"));
																				userMap.put("benpiciZhiBiao", userZhaoShengZhiBiao);
																				//本批次累计招生人数
																				userMap.put("beipicileijizhaoshengCount", 0);
																				//当天跟踪学生数量
																				userMap.put("dangtiangengzongxueshengCount", 0);
																				// 社招人数
																				int shezhaoCount = getCount(Constants.WEB_STU_SOURCE_DEFAULT,resultSet.getInt("userId"));
																				userMap.put("shezhaoCount",shezhaoCount);
																				
																				// 渠道人数
																				int qudaoCount=getCount(Constants.WEB_STU_QV_DAO,resultSet.getInt("userId"));
																				userMap.put("qudaoCount",qudaoCount);
																				
																				// 大客户人数
																				int dakehuCount = getCount(Constants.WEB_STU_DA_KE_HU,resultSet.getInt("userId"));
																				userMap.put("dakehuCount",dakehuCount);
																				
																				// 老带新人数
																				int laodaixinCount = getCount(Constants.WEB_STU_ENROLLMENT_SOURCE,resultSet.getInt("userId"));
																				userMap.put("laodaixinCount",laodaixinCount);
																				
																				//老生续读
																				int laoshengxuduCount=getCount(Constants.WEB_STU_LAO_SHENG_XU_DU,resultSet.getInt("userId"));
																				userMap.put("laoshengxuduCount",laoshengxuduCount);
																				
																				// 加盟人数
																				int jiamengCount = getCount(Constants.WEB_STU_JIA_MENG,resultSet.getInt("userId"));
																				userMap.put("jiamengCount",jiamengCount);
																				
																				// 共建人数
																				int gongjianCount = getCount(Constants.WEB_STU_GONG_JIAN,resultSet.getInt("userId"));
																				userMap.put("gongjianCount",gongjianCount);
																				
																				//当天缴费金额 
																				//当前批次
																				userMap.put("dang_qian_pi_ci_money", 0);
																				//老批次
																				userMap.put("lao_pi_ci_money", 0);
																				
																				Worklog worklog=ri_bao_biao_map.get(userId+"_"+dateStr);
																				if(worklog!=null){
																					//当天主要工作
																					userMap.put("dang_tian_zhu_yao_gong_zuo",HtmlRegexpUtil.filterHtml(worklog.getContent()));
																					//当日领导评价
																					userMap.put("ling_dao_ping_jia", HtmlRegexpUtil.filterHtml(worklog.getLinDaoPingJia()));
																					//当日领导评分
																					userMap.put("ling_dao_ping_fen", worklog.getScore());
																				}else{
																					//当天主要工作
																					userMap.put("dang_tian_zhu_yao_gong_zuo", "--");
																					//当日领导评价
																					userMap.put("ling_dao_ping_jia", "--");
																					//当日领导评分
																					userMap.put("ling_dao_ping_fen", "--");
																				}
																				return userMap;
																			}
																		});
														fuwuMap.put("userList",userList);
														return fuwuMap;
													}
												});
										if(fuwuId==-2){
											Map<String, Object> fuwuMap = new HashMap<String, Object>();
											// 服务ID
											fuwuMap.put("fuwuId",resultSet.getInt("branch_id"));
											Branch zongBuBranch = branchDao.findById(resultSet.getInt("branch_id"));
											fuwuMap.put("fuwuName",zongBuBranch != null ? zongBuBranch.getName() + "总部" : "");
											String userSql="select u.id as userId,u.full_name as name from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id")+ " and u.status=0 ";
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
																private int getCount(int enrollmentSource,int userId){
																	return getStudentCount(school, batchIds, studentDataSource, way, enrollmentSource,source, userId);
																}
																//获取招生指标
																public int gerUserEnrollQuota(int userId){
																	return gerUserEnrollQuotaSum(school,batch1,xuexiId,userId);
																}
																public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																	Map<String, Object> userMap = new HashMap<String, Object>();
																	userMap.put("userId",resultSet.getInt("userId"));
																	userMap.put("name",resultSet.getString("name"));
																	//全年指标
																	userMap.put("quanmianzhibiaoCount", 0);
																	
																	//本批次招生指标
																	int userZhaoShengZhiBiao=gerUserEnrollQuota(resultSet.getInt("userId"));
																	userMap.put("benpiciZhiBiao", userZhaoShengZhiBiao);
																	//本批次累计招生人数
																	userMap.put("beipicileijizhaoshengCount", 0);
																	//当天跟踪学生数量
																	userMap.put("dangtiangengzongxueshengCount", 0);
																	// 社招人数
																	int shezhaoCount = getCount(Constants.WEB_STU_SOURCE_DEFAULT,resultSet.getInt("userId"));
																	userMap.put("shezhaoCount",shezhaoCount);
																	
																	// 渠道人数
																	int qudaoCount=getCount(Constants.WEB_STU_QV_DAO,resultSet.getInt("userId"));
																	userMap.put("qudaoCount",qudaoCount);
																	
																	// 大客户人数
																	int dakehuCount = getCount(Constants.WEB_STU_DA_KE_HU,resultSet.getInt("userId"));
																	userMap.put("dakehuCount",dakehuCount);
																	
																	// 老带新人数
																	int laodaixinCount = getCount(Constants.WEB_STU_ENROLLMENT_SOURCE,resultSet.getInt("userId"));
																	userMap.put("laodaixinCount",laodaixinCount);
																	
																	//老生续读
																	int laoshengxuduCount=getCount(Constants.WEB_STU_LAO_SHENG_XU_DU,resultSet.getInt("userId"));
																	userMap.put("laoshengxuduCount",laoshengxuduCount);
																	
																	// 加盟人数
																	int jiamengCount = getCount(Constants.WEB_STU_JIA_MENG,resultSet.getInt("userId"));
																	userMap.put("jiamengCount",jiamengCount);
																	
																	// 共建人数
																	int gongjianCount = getCount(Constants.WEB_STU_GONG_JIAN,resultSet.getInt("userId"));
																	userMap.put("gongjianCount",gongjianCount);
																	
																	//当天缴费金额 
																	//当前批次
																	userMap.put("dang_qian_pi_ci_money", 0);
																	//老批次
																	userMap.put("lao_pi_ci_money", 0);
																	
																	Worklog worklog=ri_bao_biao_map.get(userId+"_"+dateStr);
																	if(worklog!=null){
																		//当天主要工作
																		userMap.put("dang_tian_zhu_yao_gong_zuo",HtmlRegexpUtil.filterHtml(worklog.getContent()));
																		//当日领导评价
																		userMap.put("ling_dao_ping_jia", HtmlRegexpUtil.filterHtml(worklog.getLinDaoPingJia()));
																		//当日领导评分
																		userMap.put("ling_dao_ping_fen", worklog.getScore());
																	}else{
																		//当天主要工作
																		userMap.put("dang_tian_zhu_yao_gong_zuo", "--");
																		//当日领导评价
																		userMap.put("ling_dao_ping_jia", "--");
																		//当日领导评分
																		userMap.put("ling_dao_ping_fen", "--");
																	}
																	
																	return userMap;
																}
															});
											fuwuList.add(0, fuwuMap);
											fuwuMap.put("userList", userList);
										}
										xuexiMap.put("fuwuList", fuwuList);
										
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
						return quyuMap;
					}
				});
				jdbcTemplatePlus.releaseConnection();
				//long e = System.currentTimeMillis();
				//System.out.println("倒入数据用时：" + (e - s));
				return quyuList;
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList();
		}
		
	}
	//通过 用户ID，招生途径，院校，批次，数据来源，市场途径查询数量
	final public int getStudentCount(int school,String batch,int studentDataSource,int way,int enrollmentSource,int serachSource,int userId){
		
		if(serachSource!=-2){
			if(enrollmentSource==serachSource){
				//System.out.println("2222222222222222");
			}else{
				return 0;
			}
		}
		
		int count = 0;
		String sql="select IFNULL(count(*),0) from tb_e_student where status>="+Constants.STU_CALL_STATUS_YI_DAO_MING+" and  user_id="+ userId+ " and enrollment_source="+enrollmentSource;
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
		//市场途径
		if(way!=-2){
			sql+=" and enrollment_way="+way;
		}
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
		return number==0?1:number;
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
		if(branch!=-2){
			sql+=" and branch_id = "+branch;
		}
		
		return jdbcTemplatePlus.queryForInt(sql);
	}
	
	/**
	 * 
	 * @功能：获取日报表
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-9 下午04:42:52
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId
	 * @param date
	 * @return
	 */
	final public Map<String,Worklog> getWorklogMap(Date date){
		String sql="select  " +
				" create_by,create_on,id,content,score, " +
				" (select content from tb_e_worklog w1 where w1.original_id=w2.id order by create_on desc limit 1) as lingdaopingjia " +
				" from tb_e_worklog w2 " +
				" WHERE original_id=0 " +
				//" and create_by="+userId+" "+
				" and create_on between '"+DateUtil.getDate(date, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(date, "yyyy-MM-dd")+" 23:59:59'group by create_by,create_on";
			@SuppressWarnings("unchecked")
			List<Map<String,Object>> list = jdbcTemplatePlus.query(sql,
					new RowMapper() {
				public Map<String,Object> mapRow(ResultSet resultSet, int index)
						throws SQLException {
					Map<String,Object> map =new HashMap<String,Object>();
					map.put("key", resultSet.getInt("create_by")+"_"+resultSet.getString("create_on"));
					Worklog worklog=new Worklog();
					worklog.setId(resultSet.getInt("id"));
					worklog.setScore(resultSet.getString("score"));
					worklog.setContent(resultSet.getString("content"));
					worklog.setLinDaoPingJia(resultSet.getString("lingdaopingjia"));
					map.put("value", worklog);
					
					return map;
				}
			});
			if(list!=null){
				Map<String,Worklog> resultMap =new HashMap<String,Worklog>();
				for (Map<String, Object> map : list) {
					resultMap.put(map.get("key").toString(), (Worklog)map.get("value"));
					//System.out.println(map.get("key")+":"+Integer.valueOf(map.get("value")));
				}
				return resultMap;
			}
			return new HashMap<String,Worklog>();
	}

	
}
