package net.cedu.biz.crm;

import java.util.List;

import net.cedu.entity.crm.FollowUp;
import net.cedu.model.page.PageResult;

/**
 * 学生跟进Biz
 * 
 * @author yangdongdong
 * 
 */
public interface FollowUpBiz {
	/**
	 * 新建学生跟进
	 * 
	 * @param operationLog
	 *            学生跟进实体
	 * @return
	 * @throws
	 */
	public void addFollowUp(FollowUp followUp) throws Exception;

	/**
	 * 删除学生跟进
	 * 
	 * @param id
	 *            学生跟进标识ID
	 * @return
	 * @throws
	 */
	public void deleteFollowUpById(int id) throws Exception;

	/**
	 * 查看学生跟进
	 * 
	 * @param id
	 *            学生跟进ID
	 * @return
	 * @throws
	 */
	public FollowUp findFollowUpById(int id) throws Exception;

	/**
	 * 查询学生跟进总条数
	 * 
	 * @param followUp
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public int findFollowUpsPageCount(FollowUp followUp,
			PageResult<FollowUp> pr) throws Exception;

	/**
	 * 查询学生跟进集合
	 * 
	 * @param followUp
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public List<FollowUp> findFollowUpsPageList(FollowUp followUp,
			PageResult<FollowUp> pr) throws Exception;
	
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
