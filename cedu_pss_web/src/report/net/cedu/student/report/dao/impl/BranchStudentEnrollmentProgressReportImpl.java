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
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.BranchStudentEnrollmentProgressReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @功能：周例会招生进展表
 * 
 * @作者： 董溟浩
 * @作成时间：2012-06-25 上午10:20:16
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@Repository
public class BranchStudentEnrollmentProgressReportImpl extends BaseReportDaoImpl implements
		BranchStudentEnrollmentProgressReport {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private UGroupUserDao uGroupUserDao;
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	
	private Map<String,Integer> userId_zhibiao_map=new HashMap<String, Integer>();
	private Map<String,Integer> userId_yubaomingCount_map=new HashMap<String,Integer>();
	private Map<String,Integer> userId_weijiaofeiCount_map=new HashMap<String,Integer>();
	private Map<String,Integer> userId_yibaomingCount_map=new HashMap<String,Integer>();
	
	/*
	 * 周例会招生进展表接口实现方法
	 * @see net.cedu.student.report.dao.BranchStudentEnrollmentProgressReport#statistics(java.util.Map, java.util.Map)
	 */
	public Map statistics(Map<String, Integer> params, Map<String, Date> dateParams) {
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
			//key ：用户ID_学习中心ID	value：用户招生指标
			userId_zhibiao_map=getUserZhiBiaoCount(school, batch, userId);
			//key ：用户ID_学习中心ID_数据来源   value：预报名学生总数
			userId_yubaomingCount_map=getYuBaoMingStudentCount(school, batchIds,serachStudentDataSource ,way, source, startDate, endDate);
			//key ：用户ID_学习中心ID_数据来源   value：已报名未缴费学生总数
			userId_weijiaofeiCount_map=getWeiJiaoFeiStudentCount(school, batchIds,serachStudentDataSource ,way, source, startDate, endDate);
			//key ：用户ID_学习中心ID_数据来源   value：已报名学生总数
			userId_yibaomingCount_map=getYiBaoMingStudentCount(school, batchIds,serachStudentDataSource ,way, source, startDate, endDate);
			
			final Map<String,String> branchId_zhongxinzhurenName_map=uGroupUserDao.findAllBranchDirector();
			
			//学习中心ID_＝招生指标数
			final Map<String,Integer> key_b_value_zhaoshengzhibiao_map=initZhaoShengZhiBiaoMap(batch, xuexiId);
			
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
			final List<Integer> zhiBiaoSumList=new ArrayList<Integer>();
			final List<Integer> cc_yuBaoMingSumList=new ArrayList<Integer>();
			final List<Integer> cc_wanChengSumList=new ArrayList<Integer>();
			final List<Integer> zz_baoMingSumList=new ArrayList<Integer>();
			final List<Integer> zz_wanChengSumList=new ArrayList<Integer>();
			final List<Integer> leiJiSumList=new ArrayList<Integer>();
			
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
//													if(xuexiId!=-2&&fuwuId==-2){
//														userSql+=" and u.org_id="+xuexiId;
//													}
													
													List userList = jdbcTemplatePlus.query(userSql,
																	new RowMapper() {
																		//获取招生指标
																		public int getUserEnrollQuota(int userId,int branchId){
																			return getUserEnrollQuotaSum(userId,branchId);
																		}
																		//预报名人数
																		public int getYuBaoMingCount(int userId,int branchId,int dataSource){
																			return getYuBaoMingCountSum(userId,branchId,dataSource);
																		}
																		//已报名未缴费人数
																		public int getWeiJiaoFeiCount(int userId,int branchId,int dataSource){
																			return getWeiJiaoFeiCountSum(userId,branchId,dataSource);
																		}
																		//报名完成人数（已报名）
																		public int getWanChengCount(int userId,int branchId,int dataSource){
																			return getWanChengCountSum(userId,branchId,dataSource);
																		}
																		public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																			Map<String, Object> userMap = new HashMap<String, Object>();
																			int userId=resultSet.getInt("userId");
																			userMap.put("userId",userId);
																			userMap.put("name",resultSet.getString("name"));
																			//招生指标
																			int userZhaoShengZhiBiao=getUserEnrollQuota(userId,resultSet.getInt("branch_id"));
																			userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																			// CC_预报名(呼叫中心+网络报名)
																			int CC_yuBaoMingCount=getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_CC)
																								+getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_NP);
																			userMap.put("cc_yuBaoMingCount", CC_yuBaoMingCount);
																			// CC_报名完成(呼叫中心+网络报名)
																			int CC_wanChengCount=getWanChengCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_CC)
																								+getWanChengCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_NP);
																			userMap.put("cc_wanChengCount", CC_wanChengCount);
																			// ZZ_报名(预报名+未缴费)(学习中心+历史数据)
																			int ZZ_yuBaoMingCount=getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_LC)
																								+getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_HS);
																			int ZZ_weiJiaoFeiCount=getWeiJiaoFeiCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_LC)
																								+getWeiJiaoFeiCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_HS);
																			userMap.put("zz_baoMingCount", ZZ_yuBaoMingCount+ZZ_weiJiaoFeiCount);
																			// ZZ_报名完成(学习中心+历史数据)
																			int ZZ_wanChengCount=getWanChengCount(userId, resultSet.getInt("branch_id"), Constants.STU_SOURCE_LC)
																								+getWanChengCount(userId, resultSet.getInt("branch_id"), Constants.STU_SOURCE_HS);
																			userMap.put("zz_wanChengCount", ZZ_wanChengCount);
																			// 累计人数(CC_报名完成 + ZZ_报名 + ZZ_报名完成)
																			int leiJiCount=CC_wanChengCount+ZZ_yuBaoMingCount+ZZ_weiJiaoFeiCount+ZZ_wanChengCount;
																			userMap.put("leiJiCount", leiJiCount);
																			//累计完成率(累计人数/招生指标)
																			if(userZhaoShengZhiBiao!=0){
																				userMap.put("leiJiCountP", format.format(new Float(leiJiCount)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiao)))));
																			}else{
																				userMap.put("leiJiCountP", "-");
																			}
																			return userMap;
																		}
													});
													//累计人数排名
													sort(userList,"leiJiCount");
													//完成率排名
													sortP(userList,"leiJiCountP");
													
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
													int CC_yuBaoMingCountSum=0;
													int CC_wanChengCountSum=0;
													int ZZ_baoMingCountSum=0;
													int ZZ_wanChengCountSum=0;
													int leijiCountSum=0;
													
													for (Object object : userList) {
														Map userObject=(Map)object;
														userZhaoShengZhiBiaoSum+=Integer.parseInt(userObject.get("userZhaoShengZhiBiao").toString());
														CC_yuBaoMingCountSum+=Integer.parseInt(userObject.get("cc_yuBaoMingCount").toString());
														CC_wanChengCountSum+=Integer.parseInt(userObject.get("cc_wanChengCount").toString());
														ZZ_baoMingCountSum+=Integer.parseInt(userObject.get("zz_baoMingCount").toString());
														ZZ_wanChengCountSum+=Integer.parseInt(userObject.get("zz_wanChengCount").toString());
														leijiCountSum+=Integer.parseInt(userObject.get("leiJiCount").toString());
													}
													// 总指标
													Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("fuwuId")+"_");
													fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
													// 没有分配给用户的指标
													fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
													// CC_预报名
													fuwuzhanHeJiMap.put("cc_yuBaoMingCountSum", CC_yuBaoMingCountSum);
													fuwuzhanHeJiMap.put("cc_yuBaoMingCountSumSort", 0);
													// CC_报名完成
													fuwuzhanHeJiMap.put("cc_wanChengCountSum", CC_wanChengCountSum);
													fuwuzhanHeJiMap.put("cc_wanChengCountSumSort", 0);
													// ZZ_报名
													fuwuzhanHeJiMap.put("zz_baoMingCountSum", ZZ_baoMingCountSum);
													fuwuzhanHeJiMap.put("zz_baoMingCountSumSort", 0);
													// ZZ_报名完成
													fuwuzhanHeJiMap.put("zz_wanChengCountSum", ZZ_wanChengCountSum);
													fuwuzhanHeJiMap.put("zz_wanChengCountSumSort", 0);
													// 累计人数
													fuwuzhanHeJiMap.put("leiJiCountSum", leijiCountSum);
													fuwuzhanHeJiMap.put("leiJiCountSumSort", 0);
													// 完成率
													if(userZhaoShengZhiBiaoSum_!=0){
														fuwuzhanHeJiMap.put("leiJiCountSumP", format.format(new Float(leijiCountSum)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiaoSum_)))));
													}else{
														fuwuzhanHeJiMap.put("leiJiCountSumP", "-");
													}
													fuwuzhanHeJiMap.put("leiJiCountSumPSort", 0);
													
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
//										String userSql="select u.id as userId,u.full_name as name,u.org_id as branch_id from tb_p_e_user u where u.delete_flag=0 and u.org_id="+ resultSet.getInt("branch_id")+ " and u.status=0 ";
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
//										if(xuexiId!=-2&&fuwuId==-2){
//											userSql+=" and u.org_id="+xuexiId;
//										}
										// 服务站下的所有用户
										// 过滤已删除，停用，root，总部，禁用
										List userList = jdbcTemplatePlus.query(userSql,
														new RowMapper() {
															//获取招生指标
															public int getUserEnrollQuota(int userId,int branchId){
																return getUserEnrollQuotaSum(userId,branchId);
															}
															//预报名人数
															public int getYuBaoMingCount(int userId,int branchId,int dataSource){
																return getYuBaoMingCountSum(userId,branchId,dataSource);
															}
															//已报名未缴费人数
															public int getWeiJiaoFeiCount(int userId,int branchId,int dataSource){
																return getWeiJiaoFeiCountSum(userId,branchId,dataSource);
															}
															//报名完成人数（已报名）
															public int getWanChengCount(int userId,int branchId,int dataSource){
																return getWanChengCountSum(userId,branchId,dataSource);
															}
															public Object mapRow(ResultSet resultSet,int index)throws SQLException {
																Map<String, Object> userMap = new HashMap<String, Object>();
																int userId=resultSet.getInt("userId");
																userMap.put("userId",userId);
																userMap.put("name",resultSet.getString("name"));
																//招生指标
																int userZhaoShengZhiBiao=getUserEnrollQuota(userId,resultSet.getInt("branch_id"));
																userMap.put("userZhaoShengZhiBiao", userZhaoShengZhiBiao);
																// CC_预报名(呼叫中心+网络报名)
																int CC_yuBaoMingCount=getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_CC)
																					+getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_NP);
																userMap.put("cc_yuBaoMingCount", CC_yuBaoMingCount);
																// CC_报名完成(呼叫中心+网络报名)
																int CC_wanChengCount=getWanChengCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_CC)
																					+getWanChengCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_NP);
																userMap.put("cc_wanChengCount", CC_wanChengCount);
																// ZZ_报名(预报名+未缴费)(学习中心+历史数据)
																int ZZ_yuBaoMingCount=getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_LC)
																					+getYuBaoMingCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_HS);
																int ZZ_weiJiaoFeiCount=getWeiJiaoFeiCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_LC)
																					+getWeiJiaoFeiCount(userId,resultSet.getInt("branch_id"),Constants.STU_SOURCE_HS);
																userMap.put("zz_baoMingCount", ZZ_yuBaoMingCount+ZZ_weiJiaoFeiCount);
																// ZZ_报名完成(学习中心+历史数据)
																int ZZ_wanChengCount=getWanChengCount(userId, resultSet.getInt("branch_id"), Constants.STU_SOURCE_LC)
																					+getWanChengCount(userId, resultSet.getInt("branch_id"), Constants.STU_SOURCE_HS);
																userMap.put("zz_wanChengCount", ZZ_wanChengCount);
																// 累计人数(CC_报名完成 + ZZ_报名 + ZZ_报名完成)
																int leiJiCount=CC_wanChengCount+ZZ_yuBaoMingCount+ZZ_weiJiaoFeiCount+ZZ_wanChengCount;
																userMap.put("leiJiCount", leiJiCount);
																//累计完成率
																if(userZhaoShengZhiBiao!=0){
																	userMap.put("leiJiCountP", format.format(new Float(leiJiCount)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiao)))));
																}else{
																	userMap.put("leiJiCountP", "-");
																}
																return userMap;
															}
										});
										//累计报名人数排名
										sort(userList,"leiJiCount");
										//累计完成率排名
										sortP(userList,"leiJiCountP");
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
										int CC_yuBaoMingCountSum=0;
										int CC_wanChengCountSum=0;
										int ZZ_baoMingCountSum=0;
										int ZZ_wanChengCountSum=0;
										int leijiCountSum=0;
										
										for (Object object : userList) {
											Map userObject=(Map)object;
											userZhaoShengZhiBiaoSum+=Integer.parseInt(userObject.get("userZhaoShengZhiBiao").toString());
											CC_yuBaoMingCountSum+=Integer.parseInt(userObject.get("cc_yuBaoMingCount").toString());
											CC_wanChengCountSum+=Integer.parseInt(userObject.get("cc_wanChengCount").toString());
											ZZ_baoMingCountSum+=Integer.parseInt(userObject.get("zz_baoMingCount").toString());
											ZZ_wanChengCountSum+=Integer.parseInt(userObject.get("zz_wanChengCount").toString());
											leijiCountSum+=Integer.parseInt(userObject.get("leiJiCount").toString());
										}
										// 总指标
										Integer userZhaoShengZhiBiaoSum_ =key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_")==null?0:key_b_value_zhaoshengzhibiao_map.get(resultSet.getInt("branch_id")+"_");
										fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_);
										// 没有分配给用户的指标
										fuwuzhanHeJiMap.put("nouserZhaoShengZhiBiaoSum", userZhaoShengZhiBiaoSum_-userZhaoShengZhiBiaoSum);
										// CC_预报名
										fuwuzhanHeJiMap.put("cc_yuBaoMingCountSum", CC_yuBaoMingCountSum);
										fuwuzhanHeJiMap.put("cc_yuBaoMingCountSumSort", 0);
										// CC_报名完成
										fuwuzhanHeJiMap.put("cc_wanChengCountSum", CC_wanChengCountSum);
										fuwuzhanHeJiMap.put("cc_wanChengCountSumSort", 0);
										// ZZ_报名
										fuwuzhanHeJiMap.put("zz_baoMingCountSum", ZZ_baoMingCountSum);
										fuwuzhanHeJiMap.put("zz_baoMingCountSumSort", 0);
										// ZZ_报名完成
										fuwuzhanHeJiMap.put("zz_wanChengCountSum", ZZ_wanChengCountSum);
										fuwuzhanHeJiMap.put("zz_wanChengCountSumSort", 0);
										// 累计人数
										fuwuzhanHeJiMap.put("leiJiCountSum", leijiCountSum);
										fuwuzhanHeJiMap.put("leiJiCountSumSort", 0);
										// 完成率
										if(userZhaoShengZhiBiaoSum_!=0){
											fuwuzhanHeJiMap.put("leiJiCountSumP", format.format(new Float(leijiCountSum)/ new Float(getDayuOne(getDayuOne(userZhaoShengZhiBiaoSum_)))));
										}else{
											fuwuzhanHeJiMap.put("leiJiCountSumP", "-");
										}
										fuwuzhanHeJiMap.put("leiJiCountSumPSort", 0);
										
										fuwuMap.put("fuwuzhanHeJiMap", fuwuzhanHeJiMap);
										fuwuList.add(0, fuwuMap);
									}
									//服务站排名
									sortF(fuwuList,"leiJiCountSum");
									sortPF(fuwuList,"leiJiCountSumP");
									
									xuexiMap.put("fuwuList", fuwuList);
									
									//学习中心合计
									Map<String, Object> x_fuwuzhanHeJiMap = new HashMap<String, Object>();
									
									int x_userZhaoShengZhiBiaoSum=0;
									int x_CC_yuBaoMingCountSum=0;
									int x_CC_wanChengCountSum=0;
									int x_ZZ_baoMingCountSum=0;
									int x_ZZ_wanChengCountSum=0;
									int x_leiJiCountSum=0;
									
									for (Object object : fuwuList) {
										Map fuwuObject=(Map)((Map)object).get("fuwuzhanHeJiMap");
										x_userZhaoShengZhiBiaoSum+=Integer.parseInt(fuwuObject.get("userZhaoShengZhiBiaoSum").toString());
										x_CC_yuBaoMingCountSum+=Integer.parseInt(fuwuObject.get("cc_yuBaoMingCountSum").toString());
										x_CC_wanChengCountSum+=Integer.parseInt(fuwuObject.get("cc_wanChengCountSum").toString());
										x_ZZ_baoMingCountSum+=Integer.parseInt(fuwuObject.get("zz_baoMingCountSum").toString());
										x_ZZ_wanChengCountSum+=Integer.parseInt(fuwuObject.get("zz_wanChengCountSum").toString());
										x_leiJiCountSum+=Integer.parseInt(fuwuObject.get("leiJiCountSum").toString());
									}
									
									// 指标
									x_fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", x_userZhaoShengZhiBiaoSum);
									// CC_预报名
									x_fuwuzhanHeJiMap.put("cc_yuBaoMingCountSum", x_CC_yuBaoMingCountSum);
									x_fuwuzhanHeJiMap.put("cc_yuBaoMingCountSumSort", 0);
									// CC_报名完成
									x_fuwuzhanHeJiMap.put("cc_wanChengCountSum", x_CC_wanChengCountSum);
									x_fuwuzhanHeJiMap.put("cc_wanChengCountSumSort", 0);
									// ZZ_报名
									x_fuwuzhanHeJiMap.put("zz_baoMingCountSum", x_ZZ_baoMingCountSum);
									x_fuwuzhanHeJiMap.put("zz_baoMingCountSumSort", 0);
									// ZZ_报名完成
									x_fuwuzhanHeJiMap.put("zz_wanChengCountSum", x_ZZ_wanChengCountSum);
									x_fuwuzhanHeJiMap.put("zz_wanChengCountSumSort", 0);
									// 累计人数
									x_fuwuzhanHeJiMap.put("leiJiCountSum", x_leiJiCountSum);
									x_fuwuzhanHeJiMap.put("leiJiCountSumSort", 0);
									// 完成率
									if(x_userZhaoShengZhiBiaoSum!=0){
										x_fuwuzhanHeJiMap.put("leiJiCountSumP", format.format(new Float(x_leiJiCountSum)/ new Float(getDayuOne(getDayuOne(x_userZhaoShengZhiBiaoSum)))));
									}else{
										x_fuwuzhanHeJiMap.put("leiJiCountSumP", "-");
									}
									x_fuwuzhanHeJiMap.put("leiJiCountSumPSort", 0);
									
									xuexiMap.put("fuwuzhanHeJiMap", x_fuwuzhanHeJiMap);
									return xuexiMap;
								}
							});
					//学习中心排名
//					sortF(xuexiList,"leiJiCountSum");
//					sortPF(xuexiList,"leiJiCountSumP");
					
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
					int x_CC_yuBaoMingCountSum=0;
					int x_CC_wanChengCountSum=0;
					int x_ZZ_baoMingCountSum=0;
					int x_ZZ_wanChengCountSum=0;
					int x_leiJiCountSum=0;
					
					for (Object object : xuexiList) {
						Map fuwuObject=(Map)((Map)object).get("fuwuzhanHeJiMap");
						x_userZhaoShengZhiBiaoSum+=Integer.parseInt(fuwuObject.get("userZhaoShengZhiBiaoSum").toString());
						x_CC_yuBaoMingCountSum+=Integer.parseInt(fuwuObject.get("cc_yuBaoMingCountSum").toString());
						x_CC_wanChengCountSum+=Integer.parseInt(fuwuObject.get("cc_wanChengCountSum").toString());
						x_ZZ_baoMingCountSum+=Integer.parseInt(fuwuObject.get("zz_baoMingCountSum").toString());
						x_ZZ_wanChengCountSum+=Integer.parseInt(fuwuObject.get("zz_wanChengCountSum").toString());
						x_leiJiCountSum+=Integer.parseInt(fuwuObject.get("leiJiCountSum").toString());
					}
					// 指标
					x_fuwuzhanHeJiMap.put("userZhaoShengZhiBiaoSum", x_userZhaoShengZhiBiaoSum);
					// CC_预报名
					x_fuwuzhanHeJiMap.put("cc_yuBaoMingCountSum", x_CC_yuBaoMingCountSum);
					x_fuwuzhanHeJiMap.put("cc_yuBaoMingCountSumSort", 0);
					// CC_报名完成
					x_fuwuzhanHeJiMap.put("cc_wanChengCountSum", x_CC_wanChengCountSum);
					x_fuwuzhanHeJiMap.put("cc_wanChengCountSumSort", 0);
					// ZZ_报名
					x_fuwuzhanHeJiMap.put("zz_baoMingCountSum", x_ZZ_baoMingCountSum);
					x_fuwuzhanHeJiMap.put("zz_baoMingCountSumSort", 0);
					// ZZ_报名完成
					x_fuwuzhanHeJiMap.put("zz_wanChengCountSum", x_ZZ_wanChengCountSum);
					x_fuwuzhanHeJiMap.put("zz_wanChengCountSumSort", 0);
					// 累计人数
					x_fuwuzhanHeJiMap.put("leiJiCountSum", x_leiJiCountSum);
					x_fuwuzhanHeJiMap.put("leiJiCountSumSort", 0);
					// 完成率
					if(x_userZhaoShengZhiBiaoSum!=0){
						x_fuwuzhanHeJiMap.put("leiJiCountSumP", format.format(new Float(x_leiJiCountSum)/ new Float(getDayuOne(getDayuOne(x_userZhaoShengZhiBiaoSum)))));
					}else{
						x_fuwuzhanHeJiMap.put("leiJiCountSumP", "-");
					}
					x_fuwuzhanHeJiMap.put("leiJiCountSumPSort", 0);
					
					//总合计
					zhiBiaoSumList.add(x_userZhaoShengZhiBiaoSum);
					cc_yuBaoMingSumList.add(x_CC_yuBaoMingCountSum);
					cc_wanChengSumList.add(x_CC_wanChengCountSum);
					zz_baoMingSumList.add(x_ZZ_baoMingCountSum);
					zz_wanChengSumList.add(x_ZZ_wanChengCountSum);
					leiJiSumList.add(x_leiJiCountSum);
					
					quyuMap.put("fuwuzhanHeJiMap", x_fuwuzhanHeJiMap);
					return quyuMap;
				}
			});
			sortF(quyuList,"leiJiCountSum");
			sortPF(quyuList,"leiJiCountSumP");
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
			
			sumMap.put("zhiBiaoSum", getListSum(zhiBiaoSumList));
			sumMap.put("cc_yuBaoMingSum", getListSum(cc_yuBaoMingSumList));
			sumMap.put("cc_wanChengSum", getListSum(cc_wanChengSumList));
			sumMap.put("zz_baoMingSum", getListSum(zz_baoMingSumList));
			sumMap.put("zz_wanChengSum", getListSum(zz_wanChengSumList));
			sumMap.put("leiJiSum", getListSum(leiJiSumList));
			if(getListSum(zhiBiaoSumList)!=0){
				sumMap.put("leiJiSumP", format.format(new Float(getListSum(leiJiSumList))/ new Float(getDayuOne(getDayuOne(getListSum(zhiBiaoSumList))))));
			}else{
				sumMap.put("leiJiSumP", "-");
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
				sortF(allBranchList,"leiJiCountSum");
				sortPF(allBranchList,"leiJiCountSumP");
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
				sortF(allBranchList,"leiJiCountSum");
				sortPF(allBranchList,"leiJiCountSumP");
			}
			sumMap.put("quyuList", quyuList);
			return sumMap;
		}catch (Exception e) {
			e.printStackTrace();
			return new HashMap();
		}
	}
	
	/**
	 * 通过全局批次，以及院校ID查询院校的招生批次Id字符串
	 * 
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
	
	/**
	 * 招生指标(Map初始化)
	 * @param school 院校
	 * @param gbatch 全局批次
	 * @param branchId 学习中心
	 * @return
	 */
	private Map<String,Integer> initZhaoShengZhiBiaoMap(int gbatch,int branchId){
		
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		
		String sql="select branch_id,IFNULL(sum(target),0) as target  from tb_e_branch_enroll_quota where delete_flag=0 ";
		//全局批次
		if(gbatch!=-2){
			sql+=" and batch_id = "+gbatch;
		}
		//学习中心
		if(branchId!=-2){
			sql+=" and branch_id = "+branchId;
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
	
	/**
	 * 获取用户对应的招生指标Map
	 * 
	 * @return  key:userId   value:招生指标数
	 */
	final public Map<String,Integer> getUserZhiBiaoCount(int school,int batch,int userId){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select user_id,branch_id,IFNULL(sum(target),0) as target_sum from tb_e_user_enroll_quota " +
					" where 1=1 " +
					" and delete_flag = 0 ";
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
						map.put("target_sum", resultSet.getInt("target_sum"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("user_id")+"_"+map.get("branch_id"), map.get("target_sum"));
			}
		}
		return mapResult;
	}
	
	/**
	 * 获取用户对应的预报名学生数量Map
	 * 预报名：状态为“跟进中”学生，且满足姓名、手机或座机号码有一个、层次、院校、批次均不为空
	 * 
	 * @return  key:userId_branchId_dataSource   value:预报名学生人数
	 */
	final public Map<String,Integer> getYuBaoMingStudentCount(int school,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select branch_id,user_id,student_data_source,count(id) as yubaoming_count from tb_e_student " +
					" where 1=1 " +
					" and status = " + Constants.STU_CALL_STATUS_GENG_JIN_ZHONG + " " +
					" and ((mobile is not null and mobile <> '') or (phone is not null and phone <> '')) " +
					" and academy_id <> 0 " +
					" and enrollment_batch_id <> 0 " +
					" and level_id <> 0 " +
					" and user_id <> 0 ";
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
		//开始时间
		if(startDate!=null){
			sql +=" and created_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		//结束时间
		if(endDate!=null){
			sql +=" and created_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		sql+=" group by user_id,branch_id,student_data_source";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("user_id", resultSet.getInt("user_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("student_data_source", resultSet.getInt("student_data_source"));
						map.put("yubaoming_count", resultSet.getInt("yubaoming_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("user_id")+"_"+map.get("branch_id")+"_"+map.get("student_data_source"), map.get("yubaoming_count"));
			}
		}
		return mapResult;
	}
	
	/**
	 * 获取用户对应的已报名未缴费学生数量Map
	 * 
	 * @return  key:userId_branchId   value:已报名未缴费学生人数
	 */
	final public Map<String,Integer> getWeiJiaoFeiStudentCount(int school,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select branch_id,user_id,student_data_source,count(id) as weijiaofei_count from tb_e_student " +
					" where 1=1 " +
					" and status = " + Constants.STU_CALL_STATUS_YI_DAO_MING + " " +
					" and user_id <> 0 ";
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
		//开始时间
		if(startDate!=null){
			sql +=" and created_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		//结束时间
		if(endDate!=null){
			sql +=" and created_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		sql+=" group by user_id,branch_id,student_data_source";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("user_id", resultSet.getInt("user_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("student_data_source", resultSet.getInt("student_data_source"));
						map.put("weijiaofei_count", resultSet.getInt("weijiaofei_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("user_id")+"_"+map.get("branch_id")+"_"+map.get("student_data_source"), map.get("weijiaofei_count"));
			}
		}
		return mapResult;
	}
	
	/**
	 * 获取用户对应的已报名学生数量Map
	 * 
	 * @return  key:userId_branchId   value:已报名学生人数
	 */
	final public Map<String,Integer> getYiBaoMingStudentCount(int school,String batch,int studentDataSource,int way,int enrollmentSource,Date startDate,Date endDate){
		Map<String,Integer> mapResult=new HashMap<String,Integer>();
		String sql = " select branch_id,user_id,student_data_source,count(id) as yibaoming_count from tb_e_student " +
					" where 1=1 " +
					" and status >= " + Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI + " " + 
					" and status <= " + Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI + " " +
					" and user_id <> 0 ";
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
		//开始时间
		if(startDate!=null){
			sql +=" and created_time >= '"+DateUtil.getDate(startDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		//结束时间
		if(endDate!=null){
			sql +=" and created_time <= '"+DateUtil.getDate(endDate, "yyyy-MM-dd")+" 00:00:00' ";
		}
		sql+=" group by user_id,branch_id,student_data_source";
		System.out.println(sql);
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("user_id", resultSet.getInt("user_id"));
						map.put("branch_id", resultSet.getInt("branch_id"));
						map.put("student_data_source", resultSet.getInt("student_data_source"));
						map.put("yibaoming_count", resultSet.getInt("yibaoming_count"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("user_id")+"_"+map.get("branch_id")+"_"+map.get("student_data_source"), map.get("yibaoming_count"));
			}
		}
		return mapResult;
	}
	
	/**
	 * 获取用户招生指标
	 * @param userId
	 * @param branchId
	 * @return
	 */
	final public int getUserEnrollQuotaSum(int userId,int branchId){
		return userId_zhibiao_map.get(userId+"_"+branchId)==null?0:userId_zhibiao_map.get(userId+"_"+branchId);
	}
	
	/**
	 * 获取预报名人数
	 * @param userId
	 * @param branchId
	 * @param 
	 * @return
	 */
	final public int getYuBaoMingCountSum(int userId,int branchId,int dataSource){
		int yubaoming = userId_yubaomingCount_map.get(userId+"_"+branchId+"_"+dataSource)==null?0:userId_yubaomingCount_map.get(userId+"_"+branchId+"_"+dataSource);
		return yubaoming;
	}
	
	/**
	 * 获取已报名未缴费人数
	 * @param userId
	 * @param branchId
	 * @return
	 */
	final public int getWeiJiaoFeiCountSum(int userId,int branchId,int dataSource){
		int weijiaofei = userId_weijiaofeiCount_map.get(userId+"_"+branchId+"_"+dataSource)==null?0:userId_weijiaofeiCount_map.get(userId+"_"+branchId+"_"+dataSource);
		return weijiaofei;
	}
	
	/**
	 * 获取报名完成人数(已报名)
	 * @param userId
	 * @param branchId
	 * @return
	 */
	final public int getWanChengCountSum(int userId,int branchId,int dataSource){
		return userId_yibaomingCount_map.get(userId+"_"+branchId+"_"+dataSource)==null?0:userId_yibaomingCount_map.get(userId+"_"+branchId+"_"+dataSource);
	}
	
	final public int getDayuOne(int number){
		return number==0?1:number;
	}
	
	//排序
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
		for (int i=0;i<list.size();i++) {
			Map userObject= (Map) list.get(i);
			if(Integer.valueOf(userObject.get(filed).toString())==0){
				userObject.put(filed+"Sort", "-");
			}else{
				userObject.put(filed+"Sort", i+1);
			}
			
		}
	}
	
	//排序
	@SuppressWarnings("unchecked")
	final public void  sortP(List list,final String filed){
		Collections.sort(list, new Comparator<Object>() {
			public int compare(final Object arg0, final Object arg1) {
				try{
					float count1;
					try {
						count1 = Float.valueOf(((Map)arg0).get(filed).toString().substring(0, ((Map)arg0).get(filed).toString().length()-1).replaceAll(",", ""));
					} catch (Exception e1) {
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
		for (int i=0;i<list.size();i++) {
			Map userObject= (Map) list.get(i);
			if(userObject.get(filed).toString().equals("-")){
				userObject.put(filed+"Sort", "-");
			}else{
				if(userObject.get(filed).toString().equals("0.00%")){
					userObject.put(filed+"Sort", "-");
				}else{
					userObject.put(filed+"Sort", i+1);
				}
			}
		}
	}
	
	//排序
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
		for (int i=0;i<list.size();i++) {
			Map fuwuzhanHeJiMap= (Map)((Map) list.get(i)).get("fuwuzhanHeJiMap");
			if(Integer.valueOf(fuwuzhanHeJiMap.get(filed).toString())==0){
				fuwuzhanHeJiMap.put(filed+"Sort", "-");
			}else{
				fuwuzhanHeJiMap.put(filed+"Sort", i+1);
			}
			
		}
	}
	
	//排序
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
		for (int i=0;i<list.size();i++) {
			Map fuwuzhanHeJiMap= (Map)((Map) list.get(i)).get("fuwuzhanHeJiMap");
			if(fuwuzhanHeJiMap.get(filed).toString().equals("-")){
				fuwuzhanHeJiMap.put(filed+"Sort", "-");
			}else{
				if(fuwuzhanHeJiMap.get(filed).toString().equals("0.00%")){
					fuwuzhanHeJiMap.put(filed+"Sort", "-");
				}else{
					fuwuzhanHeJiMap.put(filed+"Sort", i+1);
				}
			}
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
