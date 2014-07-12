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
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.JdbcTemplatePlus;
import net.cedu.dao.orgstructure.JurisdictionDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.orgstructure.Jurisdiction;
import net.cedu.entity.worklog.Worklog;
import net.cedu.report.dao.impl.BaseReportDaoImpl;
import net.cedu.student.report.dao.BranchDirectorDailyReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BranchDirectorDailyReportImpl extends BaseReportDaoImpl implements
		BranchDirectorDailyReport {
	
	private  JdbcTemplatePlus jdbcTemplatePlus = null;
	@Autowired
	private UserDao userDao;
	@Autowired
	private JurisdictionDao jurisdictionDao;
	
	private List<User> userList = new ArrayList<User>();

	public List statistics(Map<String, Integer> intparams) {
		return null;
	}
	
	/**
	 * 通过单一时间统计所有主任日报
	  * @see net.cedu.student.report.dao.IndividualDailyReport#statisticsByDate(java.util.Map, java.util.Map, java.util.Map)
	 */
	public List statisticsByDate(Map<String, Integer> map,Map<String, String> strMap,
			Map<String, Date> dateParams){
		
		jdbcTemplatePlus = getJdbcTemplatePlus();
		final int currentUserId=map.get("currentUserId");//当前用户ID
		final int batch = map.get("batch");// 批次
		final Date date=dateParams.get("date");//开始日期
		final String userIds=strMap.get("userIds");
		
		//用户跟踪数量map   key=userId+_+日期   value=跟踪数量
		Map<String,Integer> userDateCountMap=getDangTianXueShengGenZongCount(0,date,date);
		//招生途径学生数量统计map
		Map<String,Integer> zhao_sheng_tu_jing_count_map=getUserZhaoShengTuJinCount(0,date,date,batch);
		//日报表Map  key=userId+_+日期
		Map<String,Worklog> ri_bao_biao_map=getWorklogMap(0,date,date);
		
		//获取所有授权用户
		userList=getJurisdictionUserList(currentUserId,userIds);
		
		//当前批次全部招生数量map key:userId
		Map<String,Integer> all_zhao_sheng_count_map=getUserZhaoShengTuJinCount(0,batch);
		
		//招生指标Map  key=userId
		Map<String,Integer> zhi_biao_map=getUserEnrollQuotaMap(0,batch);
		
		//获取用户跟踪数量（中心总和）map  key=orgid+_+日期   value=跟踪数量
		Map<String,Integer> branchUserDateCountMap=getBranchDangTianXueShengGenZongCount(date,date);
		
		//招生途径学生数量统计（中心总和）map
		Map<String,Integer> branch_zhao_sheng_tu_jing_count_map=getBranchUserZhaoShengTuJinCount(date,date,batch);
		
		//当前批次全部招生途径学生数量统计（中心总和）map
		Map<String,Integer> branch_all_zhao_sheng_count_map=getBranchUserZhaoShengTuJinCount(batch);

		//招生指标（中心总和）Map  key=orgid
		Map<String,Integer> branch_zhi_biao_map=getBranchUserEnrollQuotaMap(batch);
		
		//返回的结果
		List<Map<String,Object>> resultMap=new ArrayList<Map<String,Object>>();
		
		//生成%数
		final NumberFormat format = NumberFormat.getPercentInstance();// 获取格式化类实例
		format.setMinimumFractionDigits(2);// 设置小数位
		
		for (User user : userList) {
			Map<String,Object> mapResultObject=new HashMap<String,Object>();
			String d=DateUtil.getDate(date, "yyyy-MM-dd");
			//用户姓名
			mapResultObject.put("name", user.getFullName());
			
			Worklog worklog=ri_bao_biao_map.get(user.getId()+"_"+d);
			if(worklog!=null){
				//当天主要工作
				mapResultObject.put("dang_tian_zhu_yao_gong_zuo",HtmlRegexpUtil.filterHtml(worklog.getContent()));
			}
			else{
				//当天主要工作
				mapResultObject.put("dang_tian_zhu_yao_gong_zuo", "--");
			}
			//当天跟踪学生数量 
			mapResultObject.put("dang_tian_gen_zong_xue_sheng_count", getCountByMap(userDateCountMap,user.getId()+"_"+d));
			
			//新增报名人数 
			//社招
			int shezhao = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_SOURCE_DEFAULT+"_"+d);
			mapResultObject.put("she_zhao_count",shezhao);
			//渠道
			int qudao = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_QV_DAO+"_"+d);
			mapResultObject.put("qu_dao_count",qudao);
			//大客户
			int dakehu = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_DA_KE_HU+"_"+d);
			mapResultObject.put("da_ke_hu_count",dakehu);
			//老带新
			int laodaixin = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_ENROLLMENT_SOURCE+"_"+d);
			mapResultObject.put("lao_dai_xin_count",laodaixin);
			//老生续读
			int laoshengxudu = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_LAO_SHENG_XU_DU+"_"+d);
			mapResultObject.put("lao_sheng_xu_du_count",laoshengxudu);
			//加盟
			int jiameng = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_JIA_MENG+"_"+d);
			mapResultObject.put("jia_meng_count",jiameng);
			//共建
			int gongjian = getCountByMap(zhao_sheng_tu_jing_count_map,user.getId()+"_"+Constants.WEB_STU_GONG_JIAN+"_"+d);
			mapResultObject.put("gong_jian_count",gongjian);
			//合计
			int baomingSum = shezhao+qudao+dakehu+laodaixin+laoshengxudu+jiameng+gongjian;
			mapResultObject.put("sum_count",baomingSum);
			// 需求改变：如果指标数为0 则指标数和指标完成率为"-"
			//指标情况
			//指标
			Integer zhibiao = getCountByMap(zhi_biao_map,user.getId()+"");
			mapResultObject.put("zhi_biao",zhibiao);
			// 累计招生人数
			Double sum = Double.valueOf(all_zhao_sheng_count_map.get(user.getId()+"")==null?"0":all_zhao_sheng_count_map.get(user.getId()+"").toString());
			mapResultObject.put("lei_ji",sum);
			if(zhibiao!=0)
			{
				//指标完成率
				Double avg = Double.valueOf(mapResultObject.get("zhi_biao").toString())==0?sum/1:sum/Double.valueOf(mapResultObject.get("zhi_biao").toString());
				mapResultObject.put("zhi_biao_avg",format.format(avg));
			}
			else
			{
				mapResultObject.put("zhi_biao_avg","-");
			}
			
			//当天跟踪学生数量 (中心)
			mapResultObject.put("branch_dang_tian_gen_zong_xue_sheng_count", getCountByMap(branchUserDateCountMap,user.getOrgId()+"_"+d));
			
			//新增报名人数 (中心)
			//社招(中心)
			int shezhaoBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_SOURCE_DEFAULT+"_"+d);
			mapResultObject.put("branch_she_zhao_count",shezhaoBranch);
			//渠道(中心)
			int qudaoBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_QV_DAO+"_"+d);
			mapResultObject.put("branch_qu_dao_count",qudaoBranch);
			//大客户(中心)
			int dakehuBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_DA_KE_HU+"_"+d);
			mapResultObject.put("branch_da_ke_hu_count",dakehuBranch);
			//老带新(中心)
			int laodaixinBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_ENROLLMENT_SOURCE+"_"+d);
			mapResultObject.put("branch_lao_dai_xin_count",laodaixinBranch);
			//老生续读(中心)
			int laoshengxuduBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_LAO_SHENG_XU_DU+"_"+d);
			mapResultObject.put("branch_lao_sheng_xu_du_count",laoshengxuduBranch);
			//加盟(中心)
			int jiamengBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_JIA_MENG+"_"+d);
			mapResultObject.put("branch_jia_meng_count",jiamengBranch);
			//共建(中心)
			int gongjianBranch = getCountByMap(branch_zhao_sheng_tu_jing_count_map,user.getOrgId()+"_"+Constants.WEB_STU_GONG_JIAN+"_"+d);
			mapResultObject.put("branch_gong_jian_count",gongjianBranch);
			//合计(中心)
			int baomingSumBranch = shezhaoBranch+qudaoBranch+dakehuBranch+laodaixinBranch+laoshengxuduBranch+jiamengBranch+gongjianBranch;
			mapResultObject.put("branch_sum_count",baomingSumBranch);
			
			// 需求改变：如果指标数为0 则指标数和指标完成率为"-"
			//指标情况(中心)
			//指标(中心)
			Integer zhongxinzhibiao = getCountByMap(branch_zhi_biao_map,user.getOrgId()+"_");
			mapResultObject.put("branch_zhi_biao",zhongxinzhibiao);
			//累计招生人数(中心)
			Double branch_sum = Double.valueOf(branch_all_zhao_sheng_count_map.get(user.getOrgId()+"")==null?"0":branch_all_zhao_sheng_count_map.get(user.getOrgId()+"").toString());
			mapResultObject.put("branch_lei_ji",branch_sum);
			if(zhongxinzhibiao!=0)
			{
				//指标完成率(中心)
				Double branchavg = Double.valueOf(mapResultObject.get("branch_zhi_biao").toString())==0?branch_sum/1:branch_sum/Double.valueOf(mapResultObject.get("branch_zhi_biao").toString());
				mapResultObject.put("branch_zhi_biao_avg",format.format(branchavg));
			}
			else
			{
				mapResultObject.put("branch_zhi_biao_avg","-");
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
	final public Map<String,Integer> getUserZhaoShengTuJinCount(int userId,Date start,Date end,int batch){
		String sql="select " +
				" user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d') registration_time,count(id) as s_count from tb_e_student " +
				" where status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and registration_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' and user_id= "+userId+" ";
		if(batch!=-2)
		{
			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(batch)+") ";
		}
		sql+=" group by user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d')";
		if(userId==0){
			sql="select " +
				" user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d') registration_time,count(id) as s_count from tb_e_student " +
				" where status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and registration_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' ";
			if(batch!=-2)
			{
				sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(batch)+") ";
			}
			sql+=" group by user_id,enrollment_source,DATE_FORMAT(registration_time,'%Y-%m-%d')";
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
	 * @功能：获取用户当前批次的指标数
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-06 上午09:50:14
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
	final public Map<String,Integer> getUserEnrollQuotaMap(int userId,int batchId){
		String userIds = "";
		if(userList!=null)
		{
			for(User user : userList)
			{
				if(userIds.equals(""))
				{
					userIds += user.getId();
				}
				else
				{
					userIds += "," + user.getId();
				}
			}
		}
		if(userIds.equals("")){
			userIds="0";
		}
		String sql = " select user_id as userid , sum(target) as target " +
		  " from tb_e_user_enroll_quota " +
		  " where batch_id = " + batchId + " " +
		  " and user_id in ( " + userIds + " ) " +
		  " group by user_id ";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("userid"));
				map.put("value", resultSet.getString("target"));
				return map;
			}
		});
		if(list!=null && list.size()>0){
			Map<String,Integer> resultMap =new HashMap<String,Integer>();
			for (Map<String, String> map : list) {
				resultMap.put(map.get("key").toString(), map.get("value")!=null?Integer.valueOf(map.get("value").toString()):0);
				//System.out.println(map.get("key")+":"+Integer.valueOf(map.get("value")));
			}
			return resultMap;
		}
		return new HashMap<String, Integer>();
	}
	
	/**
	 * 
	 * @功能：获取所有中心当天跟踪学生数
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-06 上午09:50:14
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
	final public Map<String,Integer> getBranchDangTianXueShengGenZongCount(Date start,Date end){
		String sql="select u.org_id as orgid, " +
				  " DATE_FORMAT(f.created_time,'%Y-%m-%d') as creator_date,count(DISTINCT f.student_id) as s_count " +
				  " from tb_e_follow_up f,tb_p_e_user u " +
				  " where f.created_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' " +
				  " and f.creator_id=u.id " +
				  " group by DATE_FORMAT(f.created_time,'%Y-%m-%d'),orgid ";
		
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("orgid")+"_"+resultSet.getString("creator_date"));
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
	 * @功能：获取所有中心的已报名 ，招生途径 ，的学生统计
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-06 上午09:50:14
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
	final public Map<String,Integer> getBranchUserZhaoShengTuJinCount(Date start,Date end,int batch){
		String sql="select "+ 
			" u.org_id as orgid,s.enrollment_source,DATE_FORMAT(s.registration_time,'%Y-%m-%d') registration_time,count(s.id) as s_count " +
			" from tb_e_student s,tb_p_e_user u " +
			" where s.user_id = u.id and s.status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and s.status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and s.registration_time between '"+DateUtil.getDate(start, "yyyy-MM-dd")+" 00:00:00' and '"+DateUtil.getDate(end, "yyyy-MM-dd")+" 23:59:59' ";
		if(batch!=-2)
		{
			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(batch)+") ";
		}
		sql+=" group by u.org_id,s.enrollment_source,DATE_FORMAT(s.registration_time,'%Y-%m-%d')";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("orgid")+"_"+resultSet.getString("enrollment_source")+"_"+resultSet.getString("registration_time"));
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
		return new HashMap<String, Integer>();
	}
	
	/**
	 * 
	 * @功能：获取所有中心总指标数
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-06 上午09:50:14
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
	final public Map<String, Integer> getBranchUserEnrollQuotaMap(int batchId){
//		String sql = " select u.org_id as orgid , sum(q.target) as target " +
//				 " from tb_e_user_enroll_quota q , tb_p_e_user u " +
//				 " where q.batch_id = " + batchId + " and q.user_id = u.id " +
//				 " and u.status = 0 and u.delete_flag = 0 " +
//				 " group by u.org_id ";
//		@SuppressWarnings("unchecked")
//		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
//				new RowMapper() {
//			public Map<String,String> mapRow(ResultSet resultSet, int index)
//					throws SQLException {
//				Map<String,String> map =new HashMap<String,String>();
//				map.put("key", resultSet.getString("orgid"));
//				map.put("value", resultSet.getString("target"));
//				return map;
//			}
//		});
//		if(list!=null && list.size()>0){
//			Map<String,Integer> resultMap =new HashMap<String,Integer>();
//			for (Map<String, String> map : list) {
//				resultMap.put(map.get("key").toString(), map.get("value")!=null?Integer.valueOf(map.get("value").toString()):0);
//				//System.out.println(map.get("key")+":"+Integer.valueOf(map.get("value")));
//			}
//			return resultMap;
//		}
//		return new HashMap<String, Integer>();
Map<String,Integer> mapResult=new HashMap<String,Integer>();
		
		String sql="select branch_id,IFNULL(sum(target),0) as target  from tb_e_branch_enroll_quota where delete_flag=0 ";
		//全局批次
		if(batchId!=-2){
			sql+=" and batch_id = "+batchId;
		}
//		//学习中心
//		if(branchId!=-2){
//			sql+=" and branch_id = "+branchId;
//		}
		
		sql+=" group by branch_id";
		
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> listResult = jdbcTemplatePlus.query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						map.put("orgid", resultSet.getInt("branch_id"));
						map.put("target", resultSet.getInt("target"));
						return map;
					}
				});
		if(listResult!=null){
			for (Map<String, Integer> map : listResult) {
				mapResult.put(map.get("orgid")+"_", map.get("target"));
			}
		}
		return mapResult;
	}
	
	/**
	 * 
	 * @功能：已报名 ，招生途径 ，的学生统计(全部时间)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-21 上午11:45:23
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param userId
	 * @return key:用户ID   value:count
	 */
	final public Map<String,Integer> getUserZhaoShengTuJinCount(int userId,int batch){
		String sql="select " +
				" user_id,count(id) as s_count from tb_e_student " +
				" where status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" and user_id= "+userId+" ";
		if(batch!=-2)
		{
			sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(batch)+") ";
		}
		sql += " group by user_id ";
		if(userId==0){
			sql="select " +
				" user_id,count(id) as s_count from tb_e_student " +
				" where status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI + " ";
			if(batch!=-2)
			{
				sql+=" and enrollment_batch_id in ("+getEnrollmentBatchId(batch)+") ";
			}
			sql += " group by user_id ";
		}
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("user_id"));
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
	 * @功能：获取所有中心的已报名 ，招生途径 ，的学生统计(全部时间)
	 *
	 * @作者： 董溟浩
	 * @作成时间：2012-03-21 上午11:49:05
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
	final public Map<String,Integer> getBranchUserZhaoShengTuJinCount(int batch){
		String sql="select "+ 
			" u.org_id as orgid,count(s.id) as s_count " +
			" from tb_e_student s,tb_p_e_user u " +
			" where s.user_id = u.id and s.status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and s.status<> "+Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI+" and s.status>="+Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI+" ";
		if(batch!=-2)
		{
			sql+=" and s.enrollment_batch_id in ("+getEnrollmentBatchId(batch)+") ";
		}
		sql += " group by u.org_id";
		@SuppressWarnings("unchecked")
		List<Map<String,String>> list = jdbcTemplatePlus.query(sql,
				new RowMapper() {
			public Map<String,String> mapRow(ResultSet resultSet, int index)
					throws SQLException {
				Map<String,String> map =new HashMap<String,String>();
				map.put("key", resultSet.getString("orgid"));
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
		return new HashMap<String, Integer>();
	}
	
	//通过全局批次，以及院校ID查询院校的招生批次Id字符串
	final public String getEnrollmentBatchId(int globalEnrollBatchId){
		
		if(globalEnrollBatchId==-2){
			return "0";
		}
		
		String ids=",";
		String sql="select IFNULL(id,0) as id from tb_e_academy_enroll_batch where global_enroll_batch_id="+globalEnrollBatchId;
		
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
	
	final public int getCountByMap( Map<String,Integer> map,String key){
		//System.out.println(key);
		return map.get(key)!=null?map.get(key):0;
	}
	final public double getMoneyByMap( Map<String,Double> map,String key){
		//System.out.println(key);
		return map.get(key)!=null?map.get(key):0;
	}

}
