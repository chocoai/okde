/**
 * 文件名：NewEnrollmentReport.java
 * 包名：net.cedu.student.report.dao
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-29 上午09:57:25
 *
 */
package net.cedu.student.report.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 
 * @功能：新生招生报表
 * 
 * @作者： 杨栋栋
 * @作成时间：2011-12-29 上午09:57:31
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
public interface NewEnrollmentReport {

	/**
	 * 
	 * @功能：新生招生报表接口
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-29 上午09:58:06
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @param map
	 *            查询的参数
	 * @param dateParams
	 *            日期
	 * @return
	 */
	public Map statistics(Map<String, Integer> map,Map<String, Date> dateParams);
}
