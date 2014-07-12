package net.cedu.dao.admin;

import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.admin.UGroupUser;

/**
 * 用户组数据层接口
 * @author Jack
 *
 */
public interface UGroupUserDao  extends BaseDao<UGroupUser>{

	/**
	 * 获取学习中心id对应的学习主任
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> findAllBranchDirector()throws Exception;
	
}
