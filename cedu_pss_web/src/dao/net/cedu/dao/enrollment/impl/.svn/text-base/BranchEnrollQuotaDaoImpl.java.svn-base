package net.cedu.dao.enrollment.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.dao.enrollment.BranchEnrollQuotaDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.enrollment.BranchEnrollQuota;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * 学习中心指标 数据访问层实现
 * 
 * @author gaolei
 * 
 */
@Repository
public class BranchEnrollQuotaDaoImpl extends BaseMDDaoImpl<BranchEnrollQuota>
		implements BranchEnrollQuotaDao {

	public List<BranchEnrollQuota> findBranchEnrollQuotaList(int batchId)
			throws Exception {

		String sql = "select * from tb_e_branch_enroll_quota where  delete_flag="+Constants.DELETE_FALSE+" and batch_id="+ batchId;
		@SuppressWarnings("unchecked")
		List<BranchEnrollQuota> list = super.getJdbcTemplate().query(sql,
				new RowMapper() {
			public BranchEnrollQuota mapRow(ResultSet resultSet, int index)
					throws SQLException {
				BranchEnrollQuota beq = new BranchEnrollQuota();
				beq.setBatchId(resultSet.getInt("batch_id"));
				beq.setBranchId(resultSet.getInt("branch_id"));
				beq.setCreatedTime(resultSet.getDate("created_time"));
				beq.setCreatorId(resultSet.getInt("creator_id"));
				beq.setDeleteFlag(resultSet.getInt("delete_flag"));
				beq.setId(resultSet.getInt("id"));
				beq.setTarget(resultSet.getInt("target"));
				beq.setUpdatedTime(resultSet.getDate("updated_time"));
				beq.setUpdaterId(resultSet.getInt("updater_id"));
				return beq;
			}
		});
		return list != null ? list : new ArrayList<BranchEnrollQuota>();
	}

	public Map<String, Integer> findBranchEnrollQuotaMapByBatch(int batchId)
			throws Exception {
		Map<String,Integer> map = new HashMap<String, Integer>();
		if(batchId!=0)
		{
			String sql = "select branch_id,target from tb_e_branch_enroll_quota where batch_id = "+batchId+" and delete_flag= "+Constants.DELETE_FALSE;
			@SuppressWarnings("unchecked")
			List<Map<String,Integer>> list =this.getJdbcTemplatePlus().queryForList(sql);
			if(list!=null){
				for(Map<String,Integer> m : list){
					map.put(m.get("branch_id").toString(), Integer.valueOf(m.get("target").toString()));
				}
			}
		}
		return map;
	}
}
