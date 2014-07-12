package net.cedu.dao.enrollment.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyEnrollQuotaDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.enrollment.AcademyEnrollQuota;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *  院校指标 数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class AcademyEnrollQuotaDaoImpl extends BaseMDDaoImpl<AcademyEnrollQuota> implements AcademyEnrollQuotaDao {

	public Map<String, Integer> getTargetByBatch(int batchId) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		String sql = "select "+
					" branch_id, "+
					" academy_id, "+
					" target "+
					" from tb_e_academy_enroll_quota "+
					" where 1=1 "+
					" and batch_id = "+batchId+" "+
					" and delete_flag = "+Constants.DELETE_FALSE+" ";
		@SuppressWarnings("unchecked")
		List<Map<String, Integer>> list = super.getJdbcTemplate().query(sql,
				new RowMapper() {
					public Map<String, Integer> mapRow(ResultSet resultSet,
							int index) throws SQLException {
						Map<String, Integer> map = new HashMap<String, Integer>();
						// 学习中心ID
						map.put("branch_id", resultSet.getInt("branch_id"));
						// 院校ID
						map.put("academy_id", resultSet.getInt("academy_id"));
						map.put("target", resultSet.getInt("target"));
						return map;
					}
				});
		if (list != null) {
			for (Map<String, Integer> map2 : list) {
				map.put(map2.get("branch_id") + "_" + map2.get("academy_id"), map2.get("target"));
			}
		}
		return map;
	}
}
