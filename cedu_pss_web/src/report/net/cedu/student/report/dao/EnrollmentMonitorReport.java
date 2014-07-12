package net.cedu.student.report.dao;

import java.util.Date;
import java.util.Map;

/**
 * 
 * @功能：招生监控表
 * 
 * @作者： 董溟浩
 * @作成时间：2012-06-26 下午15:36:23
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
public interface EnrollmentMonitorReport {

	/**
	 * 招生监控统计
	 * 
	 * @param map
	 * @param dateParams
	 * @return
	 */
	public Map statistics(Map<String, Integer> map, Map<String, Date> dateParams);
}
