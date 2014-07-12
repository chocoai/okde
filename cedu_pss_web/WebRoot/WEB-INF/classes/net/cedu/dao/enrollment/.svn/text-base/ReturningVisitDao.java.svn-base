package net.cedu.dao.enrollment;

import java.util.Map;

import net.cedu.dao.BaseDao;
import net.cedu.entity.enrollment.ReturningVisit;

/**
 * 回访记录
 * @author HXJ
 *
 */
public interface ReturningVisitDao extends BaseDao<ReturningVisit>{
	
	/**
	 * 根据学生ids集合返回学生最新回访内容和监控结果Map key:studentId value:ReturningVisit(monitor_results,content)
	 */
	public Map<String,ReturningVisit> findReturningVisitByIds(String ids) throws Exception;
	
	/**
	 * 根据学生ids集合返回学生全部回访内容Map key:studentId value:content
	 */
	public Map<String,String> findReturningContentByIds(String ids) throws Exception;
}
