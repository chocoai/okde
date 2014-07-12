package net.cedu.dao.enrollment.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.dao.admin.UserDao;
import net.cedu.dao.enrollment.ReturningVisitDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.ReturningVisit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 回访记录
 * @author HXJ
 *
 */
@Repository
public class ReturningVisitDaoImpl extends BaseMDDaoImpl<ReturningVisit> implements ReturningVisitDao{
	
	@Autowired
	private UserDao userDao;
	/**
	 * 根据学生ids集合返回学生最新回访内容和监控结果Map key:studentId value:ReturningVisit(monitor_results,content)
	 */
	public Map<String, ReturningVisit> findReturningVisitByIds(String ids)
			throws Exception {
		Map<String,ReturningVisit> map =new HashMap<String,ReturningVisit>();
		String sql="select max(id) as id from tb_e_returning_visit where student_id in ( "+ids+" ) group by student_id";
		@SuppressWarnings("unchecked")
		List<String> returningVisitList = super.getJdbcTemplatePlus().query(sql,
				new RowMapper() {
					public String mapRow(ResultSet resultSet, int index) throws SQLException
					{
						return resultSet.getString("id");
					}
		});
		String returningVisitIds = "";
		for(String id : returningVisitList)
		{
			returningVisitIds += returningVisitIds==""?id:","+id;
		}
		returningVisitIds = returningVisitIds==""?"0":returningVisitIds; 
		String sql2="select monitor_results,content,student_id,created_time,creator_id from tb_e_returning_visit where id in ( "+returningVisitIds+" )";
		@SuppressWarnings("unchecked")
		List<Map<String,ReturningVisit>> list = super.getJdbcTemplatePlus().query(sql2,
				new RowMapper() {
					public Map<String,ReturningVisit> mapRow(ResultSet resultSet, int index) throws SQLException
					{
						Map<String,ReturningVisit> map =new HashMap<String,ReturningVisit>();
						try {
							ReturningVisit temp = new ReturningVisit();
							temp.setMonitorResults(resultSet.getInt("monitor_results"));
							User user = userDao.findById(resultSet.getInt("creator_id"));
							temp.setContent("("+user==null?"无此人":user.getFullName()+")("+resultSet.getString("created_time").substring(0,resultSet.getString("created_time").length()-2)+")"+resultSet.getString("content"));
							map.put(resultSet.getInt("student_id")+"", temp);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return map;
					}
		});
		if(list!=null){
			for (Map<String, ReturningVisit> map2 : list) {
				for(Map.Entry<String, ReturningVisit> entry:map2.entrySet()){
					map.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return map;
	}
	
	/**
	 * 根据学生ids集合返回学生全部回访内容Map key:studentId value:content
	 */
	public Map<String, String> findReturningContentByIds(String ids)
			throws Exception {
		Map<String,String> map = new HashMap<String, String>();
		String sql="select student_id,remark,created_time,creator_id from tb_e_follow_up where student_id in ( "+ids+" ) order by student_id,created_time";
		@SuppressWarnings("unchecked")
		List<String> list = super.getJdbcTemplatePlus().query(sql,
				new RowMapper() {
					public String mapRow(ResultSet resultSet, int index) throws SQLException
					{
						User user = userDao.findById(resultSet.getInt("creator_id"));
						return resultSet.getString("student_id")+"_("+(user==null?"无此人":user.getFullName())+")("+resultSet.getString("created_time").substring(0,resultSet.getString("created_time").length()-2)+")"+resultSet.getString("remark");
					}
		});
		if(list!=null){
			int i = 0;
			String id1 = "";//当前循环id
			String id2 = "";//上次循环id
			StringBuffer sbcontent = new StringBuffer();//内容
			for (String temp : list) {
				if(i==0)//第一次
				{
					id1 = temp.substring(0, temp.indexOf("_"));
					sbcontent.append(temp.substring(temp.indexOf("_")+1)+"\n");
				}
				else if(i == list.size()-1)//最后一次
				{
					id1 = temp.substring(0, temp.indexOf("_"));
					if(id2.equals(id1))//学生id等于上次的id
					{
						//记录内容并写入map
						sbcontent.append(temp.substring(temp.indexOf("_")+1)+"\n");
						map.put(id2, sbcontent.toString());
					}
					else//学生id不等于上次id
					{
						//将上次内容记录map 并清空内容 写入新内容记录map
						map.put(id2, sbcontent.toString());
						sbcontent = new StringBuffer();
						sbcontent.append(temp.substring(temp.indexOf("_")+1)+"\n");
						map.put(id1, sbcontent.toString());
					}
				}
				else//中间
				{
					id1 = temp.substring(0, temp.indexOf("_"));
					if(id2.equals(id1))//学生id等于上次的id
					{
						//记录内容
						sbcontent.append(temp.substring(temp.indexOf("_")+1)+"\n");
					}
					else//学生id不等于上次id
					{
						//把之前记录写进map 并清空内容记录新内容
						map.put(id2, sbcontent.toString());
						sbcontent = new StringBuffer();
						sbcontent.append(temp.substring(temp.indexOf("_")+1)+"\n");
					}
				}
				//保存上次id
				id2 = id1;
				i++;
			}
		}
		return map;
	}

}
