package net.cedu.dao.enrollment.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.common.Constants;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.enrollment.UserEnrollQuotaDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.UserEnrollQuota;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *  中心人员指标 数据访问层实现
 * @author gaolei
 *
 */
@Repository
public class UserEnrollQuotaDaoImpl extends BaseMDDaoImpl<UserEnrollQuota> implements UserEnrollQuotaDao {
	@Autowired
	private UserDao userDao;

	public int findUserEnrollQuotaSumByUserId(int userId) throws Exception {
		User user = userDao.findById(userId);
		if(user!=null)
		{
			String sql = "select IFNULL(sum(target),0) from tb_e_user_enroll_quota where user_id = "+user.getId()+" and branch_id = "+user.getOrgId();
			System.out.println(sql);
			return super.getJdbcTemplate().queryForInt(sql);
		} 
		return 0;
	}
	
	public Map<String,String> findUserEnrollQuotaMapByBatchIdAndBranchId(int batchId,int branchId) throws Exception
	{
		Map<String,String> map = new HashMap<String, String>();
		String sql = "select branch_id,academy_id,user_id,target,expect_target from tb_e_user_enroll_quota where delete_flag="+Constants.DELETE_FALSE+" and batch_id="+batchId+" ";
		if(branchId!=0)
		{
			sql += " and branch_id="+branchId+" ";
		}
		@SuppressWarnings("unchecked")
		List<Map<String,Integer>> list =this.getJdbcTemplatePlus().queryForList(sql);
		if(list!=null){
			for(Map<String,Integer> m : list){
				map.put(m.get("branch_id").toString()+"_"+m.get("academy_id").toString()+"_"+m.get("user_id").toString(), m.get("target").toString()+"_"+m.get("expect_target").toString());
			}
		}
		return map;
	}
}
