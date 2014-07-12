package net.cedu.dao.crm;

import net.cedu.dao.BaseDao;
import net.cedu.entity.crm.FollowUp;

/**
 * 跟进纪录
 * 
 * @author yangdongdong
 * 
 */
public interface FollowUpDao extends BaseDao<FollowUp> {

	/**
	 * 根据学生id查询学习中心首次跟进记录
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public FollowUp findFirstFollowUpByStudentId(int studentId) throws Exception;
	
	/**
	 * 根据学生id查询学习中心最新跟进记录
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public FollowUp findLatestFollowUpByStudentId(int studentId) throws Exception;
}
