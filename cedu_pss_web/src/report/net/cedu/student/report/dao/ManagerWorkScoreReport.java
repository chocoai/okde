/**
 * 文件名：ManagerWorkScoreReport.java
 * 包名：net.cedu.report.dao
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：dongminghao    
 * 日期：2012-02-09 上午10:00:57
 *
 */
package net.cedu.student.report.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @功能：工作评分统计接口
 * 
 * @作者： 董溟浩
 * @作成时间：2012-02-09 上午10:01:49
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
public interface ManagerWorkScoreReport {
	
	/**
	 * 
	 * @功能：评分统计接口
	 * 
	 * @作者： 董溟浩
	 * @作成时间：2012-02-09 上午10:03:49
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param map
	 *            参数
	 * @param dateParams
	 *            日期
	 * @return
	 */
	public List statistics(Map<String, Integer> map,Map<String, Date> dateParams);
}
