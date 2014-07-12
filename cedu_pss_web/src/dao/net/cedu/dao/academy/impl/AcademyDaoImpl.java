package net.cedu.dao.academy.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.cedu.dao.academy.AcademyDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.enrollment.AcademyBatchBranch;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;



/**
 * 院校数据层
 * @author gaolei
 * */

@Repository
public class AcademyDaoImpl extends BaseMDDaoImpl<Academy> implements AcademyDao{

	public List<AcademyBatchBranch> findAcademyByBranchIdAndGlobalBatchId(int branchId,
			int globalBatchId) throws Exception {
		String sql = "select * from tb_r_academy_batch_branch where global_id="+globalBatchId+" and branch_id="+branchId;
		@SuppressWarnings("unchecked")
		List<AcademyBatchBranch> list = super.getJdbcTemplate().query(sql,
				new RowMapper() {
			public AcademyBatchBranch mapRow(ResultSet resultSet, int index)
					throws SQLException {
				AcademyBatchBranch academyBatchBranch = new AcademyBatchBranch();
				
				academyBatchBranch.setAcademyId(resultSet.getInt("academy_id"));
				academyBatchBranch.setBatchId(resultSet.getInt("batch_id"));
				academyBatchBranch.setBranchId(resultSet.getInt("branch_id"));
				academyBatchBranch.setGlobalBatchId(resultSet.getInt("global_id"));
				academyBatchBranch.setId(resultSet.getInt("id"));
				
				return academyBatchBranch;
			}
		});
		return list != null ? list : new ArrayList<AcademyBatchBranch>();
	}
	
	
	
	
	
	
	
	
	
	

}
