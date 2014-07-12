package net.cedu.biz.worklog;

import java.util.List;

import net.cedu.entity.worklog.Worklog;
import net.cedu.model.page.PageResult;

/**
 * 工作日志Biz底层方法
 * @author Jack
 *
 */
public interface WorklogBiz {
	/**
	 * 添加工作日志
	 * @param log
	 * @throws Exception
	 */
	public void createNew(Worklog log) throws Exception;
	
//	/**
//	 * 提交并添加
//	 * @param log
//	 * @param partmentids
//	 * @param userids
//	 * @throws Exception
//	 */
//	public void SaveAndSubmit(Worklog log) throws Exception;
//	
//	/**
//	 * 提交并修改
//	 * @param log
//	 * @throws Exception
//	 */
//	public void UpdateAndSubmit(Worklog log) throws Exception;
	/**
	 * 修改日志
	 * @param log
	 * @throws Exception
	 */
	public void update(Worklog log) throws Exception;
	
	/**
	 * 根据主键删除日志
	 * @param id
	 * @throws Exception
	 */
	public void deleteMonthWorklog (int id) throws Exception;
	
	/**
	 * 根据主键查询日志
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Worklog getById(int id) throws Exception;
	
	/**
	 * 根据条件查找日报数量_个人
	 */
	public int findCountByConditionForHQL(Worklog worklog,String starttime,String endtime);
	
	/**
	 * 根据条件查找日报列表_分页
	 */
	public List<Worklog> findListByCondition(PageResult<Worklog> pr,Worklog workoog,String starttime,String endtime);
	
	/**
	 * 根据条件查找日报数量_审核
	 */
	public int findCountByConditionForHQLAudit(Worklog worklog,String starttime,String endtime,String departmentIds);
	
	/**
	 * 根据条件查找日报列表_审核
	 */
	public List<Worklog> findListByConditionAudit(PageResult<Worklog> pr,Worklog workoog,String starttime,String endtime,String departmentIds);
	
	
	/**
	 * 
	 * @功能：日报是否存在
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-4 下午07:06:18
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param useId
	 * @param date
	 * @return
	 */
	public boolean findWorklogByParams(int useId,String date);
	/**
	 * 
	 * @功能：查询评论List
	 *
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-19 下午07:23:28
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param id
	 * @return
	 */
	public List<Worklog> findChilds(int id);
}
