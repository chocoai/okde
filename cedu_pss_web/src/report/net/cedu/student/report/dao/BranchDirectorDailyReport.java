/**
 * 文件名：BranchDirectorDailyReport.java
 * 包名：net.cedu.student.report.dao
 * 工程：cedu_pss_web
 * 功能： TODO 学习中心主任以及学习中心总体日报表
 *
 * 作者：yangdongdong    
 * 日期：2012-2-6 下午04:18:50
 *
 */
package net.cedu.student.report.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BranchDirectorDailyReport {

	public List statistics(Map<String, Integer> intparams);
	public List statisticsByDate(Map<String, Integer> map,Map<String, String> strMap,Map<String, Date> dateParams);
}
