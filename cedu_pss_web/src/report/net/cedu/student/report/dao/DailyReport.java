/**
 * 文件名：DailyReport.java
 * 包名：net.cedu.report.dao
 * 功能： TODO /请自行添加
 *
 * 作者：Administrator    
 * 日期：2011-12-23 上午11:03:26
*/
package net.cedu.student.report.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @功能：日报表
 *
 * @作者： 杨栋栋
 * @作成时间：2012-1-9 下午03:42:20
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
public interface DailyReport {
	
	public List statistics(Map<String, Integer> map,Map<String, Date> dateParams);
}
