/**
 * 文件名：IndividualDailyReportImpl.java
 * 包名：net.cedu.student.report.dao.impl
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-6 上午09:56:41
 *
*/
package net.cedu.student.report.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
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
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.dao.orgstructure.JurisdictionDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.orgstructure.Jurisdiction;
import net.cedu.entity.worklog.Worklog;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.IndividualDailyReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
/**
 * 
 * @功能：个人日报表实现类
 *
 * @作者： 杨栋栋
 * @作成时间：2012-1-6 上午09:57:17
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Repository
public class IndividualDailyReportImpl extends BaseReportDaoImpl implements IndividualDailyReport{

	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	@Autowired
	private UserDao userDao;
	@Autowired
	private JurisdictionDao jurisdictionDao;
	/**
	 * 通过单个用户统计时间段的信息
	  * @see net.cedu.student.report.dao.IndividualDailyReport#statistics(java.util.Map, java.util.Map)
	 */
	public List statisticsByUser(Map<String, Integer> map,
			Map<String, Date> dateParams) {
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int userId=map.get("user");//用户ID
		final int batch = map.get("batch");// 批次
		final Date startDate=dateParams.get("startDate");//开始日期
		final Date endDate=dateParams.get("endDate");//结束日期
		
		
		
		//用户跟踪数量map   key=userId+_+日期   value=跟踪数量
		Map<String,Integer> userDateCountMap=getDangTianXueShengGenZongCount(userId,startDate,endDate);
		//招生途径学生数量统计map
		Map<String,Integer> zhao_sheng_tu_jing_count_map=getUserZhaoShengTuJinCount(userId,startDate,endDate);
		//缴费Map  key=userId+_+日期
		Map<String,Double> jiao_fei_jin_e_map=getJiaoFeiMap(batch,userId,startDate,endDate);
		//日报表Map  key=userId+_+日期
		 Map<String,Worklog> ri_bao_biao_map=getWorklogMap(userId,startDate,endDate);
		
		//获取所有日期
		List<Date> dateRange=DateUtil.getDateRange(startDate, endDate);
		//返回的结果
		List<Map<String,Object>> resultMap=new ArrayList<Map<String,Object>>();
		for (Date date : dateRange) {
			Map<String,Object> mapResultObject=new HashMap<String,Object>();
			String d=DateUtil.getDate(date, "yyyy-MM-dd");
			//日报日期
			mapResultObject.put("daily_date", d);
			//当天跟踪学生数量 
			mapResultObject.put("dang_tian_gen_zong_xue_sheng_count", getCountByMap(userDateCountMap,userId+"_"+d));
			//新增报名人数 
			
			//社招
			mapResultObject.put("she_zhao_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_SOURCE_DEFAULT+"_"+d));
			//渠道
			mapResultObject.put("qu_dao_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_QV_DAO+"_"+d));
			//大客户
			mapResultObject.put("da_ke_hu_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_DA_KE_HU+"_"+d));
			//老带新
			mapResultObject.put("lao_dai_xin_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_ENROLLMENT_SOURCE+"_"+d));
			//老生续读
			mapResultObject.put("lao_sheng_xu_du_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_LAO_SHENG_XU_DU+"_"+d));
			//加盟
			mapResultObject.put("jia_meng_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_JIA_MENG+"_"+d));
			//共建
			mapResultObject.put("gong_jian_count",getCountByMap(zhao_sheng_tu_jing_count_map,userId+"_"+Constants.WEB_STU_GONG_JIAN+"_"+d));
			
			//当天缴费金额 
			//当前批次
			mapResultObject.put("dang_qian_pi_ci_money", getMoneyByMap(jiao_fei_jin_e_map,userId+"_"+d+"_"+true)+"0");
			//老批次
			mapResultObject.put("lao_pi_ci_money", getMoneyByMap(jiao_fei_jin_e_map,userId+"_"+d+"_"+false)+"0");
			
			Worklog worklog=ri_bao_biao_map.get(userId+"_"+d);
			if(worklog!=null){
				//当天主要工作
				mapResultObject.put("dang_tian_zhu_yao_gong_zuo",HtmlRegexpUtil.filterHtml(worklog.getContent()));
				//当日领导评价
				mapResultObject.put("ling_dao_ping_jia", HtmlRegexpUtil.filterHtml(worklog.getLinDaoPingJia()));
				//当日领导评分
				mapResultObject.put("ling_dao_ping_fen", worklog.getScore());
			}else{
				//当天主要工作
				mapResultObject.put("dang_tian_zhu_yao_gong_zuo", "--");
				//当日领导评价
				mapResultObject.put("ling_dao_ping_jia", "--");
				//当日领导评分
				mapResultObject.put("ling_dao_ping_fen", "--");
			}
			
			
			
			
			resultMap.add(mapResultObject);
		}
		
		return resultMap;
	}
	
	/**
	 * 通过单一时间统计所有在管辖范围内的用户日报
	  * @see net.cedu.student.report.dao.IndividualDailyReport#statisticsByDate(java.util.Map, java.util.Map, java.util.Map)
	 */
	public List statisticsByDate(Map<String, Integer> map,Map<String, String> strMap,
			Map<String, Date> dateParams) {
		
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int currentUserId=map.get("currentUserId");//当前用户ID
		final int batch = map.get("batch");// 批次
		final Date date=dateParams.get("date");//开始日期
		final String userIds=strMap.get("userIds");
		
		
		//用户跟踪数量map   key=userId+_+日期   value=跟踪数量
		Map<String,Integer> userDateCountMap=getDangTianXueShengGenZongCount(0,date,date);
		//招生途径学生数量统计map
		Map<String,Integer> zhao_sheng_tu_jing_count_map=getUserZhaoShengTuJinCount(0,date,date);
		//缴费Map  key=userId+_+日期
		Map<String,Double> jiao_fei_jin_e_map=getJiaoFeiMap(batch,0,date,date);
		//日报表Map  key=userId+_+日期
		 Map<String,Worklog> ri_bao_biao_map=getWorklogMap(0,date,date);
		
		//获取所有授权用户
		 List<User> userList=getJurisdictionUserList(currentUserId,userIds);
		//返回的结果
		List<Map<String,Object>> resultMap=new ArrayList<Map<String,Object>>();
		for (User user : userList) {
			Map<String,Object> mapResultObject=new HashMap<String,Object>();
			String d=DateUtil.getDate(date, "yyyy-MM-dd");
			//用户姓名
			mapResultObject.put("name", user.getFullName());
			//当天跟踪学生数量 
			mapResultObject.put("dang_tian_gen_zong_xue_sheng_count", getCountByMap(userDateCountMap,user.getId()+"_"+d));
			//新增报名人数 
			
			//社招
			mapResultObject.put("she_zhao_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_SOURCE_DEFAULT+"_"+d));
			//渠道
			mapResultObject.put("qu_dao_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_QV_DAO+"_"+d));
			//大客户
			mapResultObject.put("da_ke_hu_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_DA_KE_HU+"_"+d));
			//老带新
			mapResultObject.put("lao_dai_xin_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_ENROLLMENT_SOURCE+"_"+d));
			//老生续读
			mapResultObject.put("lao_sheng_xu_du_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_LAO_SHENG_XU_DU+"_"+d));
			//加盟
			mapResultObject.put("jia_meng_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_JIA_MENG+"_"+d));
			//共建
			mapResultObject.put("gong_jian_count",getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_GONG_JIAN+"_"+d));
			
			//当天缴费金额 
			//当前批次
			mapResultObject.put("dang_qian_pi_ci_money", getMoneyByMap(jiao_fei_jin_e_map,user.getId()+"_"+d+"_"+true)+"0");
			//老批次
			mapResultObject.put("lao_pi_ci_money", getMoneyByMap(jiao_fei_jin_e_map,user.getId()+"_"+d+"_"+false)+"0");
			
			Worklog worklog=ri_bao_biao_map.get(user.getId()+"_"+d);
			if(worklog!=null){
				//当天主要工作
				mapResultObject.put("dang_tian_zhu_yao_gong_zuo",HtmlRegexpUtil.filterHtml(worklog.getContent()));
				//当日领导评价
				String content=HtmlRegexpUtil.filterHtml(worklog.getLinDaoPingJia());
				mapResultObject.put("ling_dao_ping_jia", content.equals("")?"--":content);
				//当日领导评分
				mapResultObject.put("ling_dao_ping_fen", worklog.getScore().equals("0")?"--":worklog.getScore());
			}else{
				//当天主要工作
				mapResultObject.put("dang_tian_zhu_yao_gong_zuo", "--");
				//当日领导评价
				mapResultObject.put("ling_dao_ping_jia", "--");
				//当日领导评分
				mapResultObject.put("ling_dao_ping_fen", "--");
			}
			
			resultMap.add(mapResultObject);
		}
		Collections.sort(resultMap, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Map) arg0).get("name").toString();
				String name2 = ((Map) arg1).get("name").toString();
				return cmp.compare(name1, name2);
			}
		});
		return resultMap;
		
	}
	/**
	 * 
	 * @功能：通过时间段获取用户的跟踪数量
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-6 上午11:39:58
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId 用户ID
 	 * @param start
	 * @param end
	 * @return  key   userID+_+日期   value 跟踪数量
	 */
	final public Map<String,Integer> getDangTianXueShengGenZongCount(int userId,Date start,Date end){
		String sql=" select creator_id,DATE_FORMAT(created_time,'%Y-%m-%d') as creator_date,count(DISTINCT student_id) as s_count " +
				   " from tb_e_follow_up " +
				   " where created_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' " +
				   " and creator_id="+userId +
				   " group by creator_id,DATE_FORMAT(created_time,'%Y-%m-%d')";
		
		if(userId==0){
			sql=" select creator_id,DATE_FORMAT(created_time,'%Y-%m-%d') as creator_date,count(DISTINCT student_id) as s_count " +
			    " from tb_e_follow_up " +
			    " where created_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' " +
			    " group by creator_id,DATE_FORMAT(created_time,'%Y-%m-%d')";
		}
		
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("creator_id")+"_"+resultSet.getString("creator_date"));
				map.put("value", resultSet.getString("s_count"));
				return map;
			}
		});
		if(list!=null){
			Map<String,Integer> resultMap =new HashMap<String,Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key"), Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
			
		
		return new HashMap<String,Integer>();
	}

	/**
	 * 
	 * @功能：已报名 ，招生途径 ，的学生统计
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-6 下午02:09:01
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId
	 * @param start
	 * @param end
	 * @return key:用户ID_招生途径ID_报名时间   value:count
	 */
	final public Map<String,Integer> getUserZhaoShengTuJinCount(int userId,Date start,Date end){
		String sql="select " +
				" user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d') registration_time,count(id) as s_count from tb_e_student " +
				" where status="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and registration_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' and user_id= "+userId+" " +
				" group by user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d')";
		if(userId==0){
			sql="select " +
				" user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d') registration_time,count(id) as s_count from tb_e_student " +
				" where status="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and registration_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' " +
				" group by user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d')";
		}
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("user_id")+"_"+resultSet.getString("enrollment_source")+"_"+resultSet.getString("registration_time"));
				map.put("value", resultSet.getString("s_count"));
				return map;
			}
		});
		if(list!=null){
			Map<String,Integer> resultMap =new HashMap<String,Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key"), Integer.valueOf(map.get("value")));
				//System.out.println(map.get("key")+":"+Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
			
		
		return new HashMap<String,Integer>();
	}
	/**
	 * 
	 * @功能：查询缴费金额Map
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-6 下午04:18:43
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * @param batch批次
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	final public Map<String,Double> getJiaoFeiMap(int batch,int userId,Date start,Date end){
			//除当前批次
			String sql="select creator_id,DATE_FORMAT(created_time,'%Y-%m-%d') as creator_date,sum(fee_payment) as paymentSum from tb_e_fee_payment " +
					" where (status>"+Constants.PAYMENT_STATUS_YI_KAI_DAN + " or status<"+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI+")"+
					" and common_batch !=" +batch+" "+
					" and created_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59'" +
					" group by creator_id,DATE_FORMAT(created_time,'%Y-%m-%d')";
			@SuppressWarnings("unchecked")
			List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
					new RowMapper() {
				public Map<String,String> mapRow(ResultSet resultSet, int index)
						throws SQLException {
					Map<String,String> map =new HashMap<String,String>();
					map.put("key", resultSet.getString("creator_id")+"_"+resultSet.getString("creator_date")+"_"+false);
					map.put("value", resultSet.getString("paymentSum"));
					return map;
				}
			});
			//当前批次
			String sql1="select creator_id,DATE_FORMAT(created_time,'%Y-%m-%d') as creator_date,sum(fee_payment) as paymentSum from tb_e_fee_payment " +
					" where (status>"+Constants.PAYMENT_STATUS_YI_KAI_DAN + " or status<"+Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI+")"+
					" and common_batch =" +batch+" "+
					" and created_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59'" +
					" group by creator_id,DATE_FORMAT(created_time,'%Y-%m-%d')";
			//System.out.println(sql1);
			@SuppressWarnings("unchecked")
			List<Map<String,String>> list1 = jdbcTemplatePlus.query(sql1,
					new RowMapper() {
				public Map<String,String> mapRow(ResultSet resultSet, int index)
						throws SQLException {
					Map<String,String> map =new HashMap<String,String>();
					map.put("key", resultSet.getString("creator_id")+"_"+resultSet.getString("creator_date")+"_"+true);
					map.put("value", resultSet.getString("paymentSum"));
					return map;
				}
			});
			
			if(list!=null){
				Map<String,Double> resultMap =new HashMap<String,Double>();
				for (Map<String, String> map : list) {
					resultMap.put(map.get("key"), Double.valueOf(map.get("value")));
				}
				for (Map<String, String> map : list1) {
					resultMap.put(map.get("key"), Double.valueOf(map.get("value")));
				}
				return resultMap;
			}
				
			
			return new HashMap<String,Double>();
	}
	/**
	 * 
	 * @功能：获取日报表
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-6 下午06:31:15
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId
	 * @param start
	 * @param end
	 * @return
	 */
	final public Map<String,Worklog> getWorklogMap(int userId,Date start,Date end){
		String sql="select  " +
				" create_by,create_on,id,content,score, " +
				" (select content from tb_e_worklog w1 where w1.original_id=w2.id order by create_on desc limit 1) as lingdaopingjia " +
				" from tb_e_worklog w2 " +
				" WHERE original_id=0 " +
				" and create_by="+userId+" "+
				" and create_on between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59'group by create_by,create_on";
		if(userId==0){
			sql="select  " +
				" create_by,create_on,id,content,score, " +
				" (select content from tb_e_worklog w1 where w1.original_id=w2.id order by create_on desc limit 1) as lingdaopingjia " +
				" from tb_e_worklog w2 " +
				" WHERE original_id=0 " +
				" and create_on between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59'group by create_by,create_on";
		}
			
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
	
	/**
	 * 
	 * @功能：通过当前用户ID获取管辖的用户
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-12 下午03:27:32
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param currentUserId
	 * @return
	 */
	final public List<User> getJurisdictionUserList(int currentUserId,String userIds){
		if(userIds!=null&&!"".equals(userIds)){
			return userDao.getByProperty(" and id in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+userIds});
		}else{
			//获取管辖的部门ID
			String departmentIds=",";
			Jurisdiction jurisdiction=jurisdictionDao.getObjByProperty("userId", currentUserId);
			if(jurisdiction!=null&&jurisdiction.getDepartmentIds()!=null){
				String[] departmentIdArray=jurisdiction.getDepartmentIds().split("@");
				for (String s : departmentIdArray) {
					if(s!=null&&!s.equals("")){
						if(departmentIds.equals(",")){
							departmentIds=s;
						}else{
							departmentIds+=","+s;
						}
					}
				}
				
			}
			if(departmentIds.equals(",")){
				departmentIds="0";
			}
			
			return userDao.getByProperty(" and departmentId in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+departmentIds});
		}
	}
	
	
	
	final public int getCountByMap( Map<String,Integer> map,String key){
		//System.out.println(key);
		return map.get(key)!=null?map.get(key):0;
	}
	final public double getMoneyByMap( Map<String,Double> map,String key){
		//System.out.println(key);
		return map.get(key)!=null?map.get(key):0;
	}

	
}
