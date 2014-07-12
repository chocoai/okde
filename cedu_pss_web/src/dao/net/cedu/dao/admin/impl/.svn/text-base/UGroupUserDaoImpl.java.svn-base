package net.cedu.dao.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.dao.admin.UGroupUserDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.admin.UGroupUser;
import net.cedu.entity.admin.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户组数据层实现类
 * @author Jack
 *
 */
@Repository
public class UGroupUserDaoImpl extends BaseMDDaoImpl<UGroupUser> implements UGroupUserDao
{

	@Autowired
	private UserDao userDao;
	
	/*
	 * 获取学习中心id对应的学习主任
	 * @see net.cedu.dao.admin.UGroupUserDao#findAllBranchDirector()
	 */
	public Map<String, String> findAllBranchDirector() throws Exception {
		Map<String, String> map=new HashMap<String, String>();
		String sql= "select user_id from tb_p_r_ugroup_user where group_id in (select id from tb_p_e_user_group where name like '%中心主任组%')";
		@SuppressWarnings("unchecked")
		List<Map<String,Integer>> list =this.getJdbcTemplatePlus().queryForList(sql);
		if(list!=null){
			for (Map<String,Integer> m : list) {
				User user=userDao.findById(m.get("user_id"));
				if(user!=null){
					map.put(user.getOrgId()+"", user.getFullName());
				}
				
			}
		}
		return map;
	}
	
}