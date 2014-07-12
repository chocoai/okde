package net.cedu.dao.crm.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.date.DateUtil;
import net.cedu.dao.crm.FollowUpDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.crm.FollowUp;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 跟进实现类
 * 
 * @author yangdongdong
 * 
 */
@Repository
public class FollowUpDaoImpl extends BaseMDDaoImpl<FollowUp> implements
		FollowUpDao {

	/*
	 * 根据学生id查询学习中心首次跟进记录
	 * @see net.cedu.dao.crm.FollowUpDao#findFirstFollowUpByStudentId(int)
	 */
	public FollowUp findFirstFollowUpByStudentId(int studentId)
			throws Exception {
		if(studentId>0){
			String sql = " select f.id,f.student_id,f.status_id,f.call_status_id,f.creator_id,f.created_time,f.code,f.remark "
						+" from tb_e_follow_up f,tb_p_e_user u "
						+" where 1=1 "
						+" and f.creator_id = u.id "
						+" and u.org_id>1 "// 非总部
						+" and student_id = " + studentId + " "
						+" order by created_time asc "
						+" limit 1 ";
			return getFollowUp(sql);
		}
		return null;
	}

	/*
	 * 根据学生id查询学习中心最新跟进记录
	 * @see net.cedu.dao.crm.FollowUpDao#findLatestFollowUpByStudentId(int)
	 */
	public FollowUp findLatestFollowUpByStudentId(int studentId)
			throws Exception {
		if(studentId>0){
			String sql = " select f.id,f.student_id,f.status_id,f.call_status_id,f.creator_id,f.created_time,f.code,f.remark "
						+" from tb_e_follow_up f,tb_p_e_user u "
						+" where 1=1 "
						+" and f.creator_id = u.id "
						+" and u.org_id>1 "// 非总部
						+" and student_id = " + studentId + " "
						+" order by created_time desc "
						+" limit 1 ";
			return getFollowUp(sql);
		}
		return null;
	}
	
	/*
	 * 查询跟进记录公共方法
	 */
	private FollowUp getFollowUp(String sql) throws Exception{
		FollowUp followUp = null;
		@SuppressWarnings("unchecked")
		List<Map<String, String>> list = super.getJdbcTemplate().query(sql,
				new RowMapper() {
					public Map<String, String> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, String> map = new HashMap<String, String>();
						map.put("id", resultSet.getString("id"));
						map.put("student_id", resultSet.getString("student_id"));
						map.put("status_id", resultSet.getString("status_id"));
						map.put("call_status_id", resultSet.getString("call_status_id"));
						map.put("creator_id", resultSet.getString("creator_id"));
						map.put("created_time", resultSet.getString("created_time"));
						map.put("code", resultSet.getString("code"));
						map.put("remark", resultSet.getString("remark"));
						return map;
					}
				});
		if (list != null && list.size()>0) {
			followUp = new FollowUp();
			followUp.setId(Integer.parseInt(list.get(0).get("id")));
			followUp.setStudentId(Integer.parseInt(list.get(0).get("student_id")));
			followUp.setStatusId(Integer.parseInt(list.get(0).get("status_id")));
			followUp.setCallStatusId(Integer.parseInt(list.get(0).get("call_status_id")));
			followUp.setCreatorId(Integer.parseInt(list.get(0).get("creator_id")));
			followUp.setCreatedTime(list.get(0).get("created_time")==null?null:DateUtil.StringToDate(list.get(0).get("created_time"),"yyyy-MM-dd HH:mm:ss"));
			followUp.setCode(list.get(0).get("code"));
			followUp.setRemark(list.get(0).get("remark"));
		}
		return followUp;
	}

}
