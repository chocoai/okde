package net.cedu.biz.academy;

import java.util.Date;
import java.util.List;

import net.cedu.entity.academy.Remind;
import net.cedu.entity.academy.SchoolCalendar;
import net.cedu.entity.academy.SchoolCalendarSet;

/**
 * 院历业务
 * 
 * @author yangdongdong
 * 
 */
public interface SchoolCalendarBiz {

	/**
	 * 查询院历
	 * 
	 * @param id
	 * @return
	 */
	public SchoolCalendar findCalendarById(int id) throws Exception;

	/**
	 * 增加院历,及提醒
	 * 
	 * @param sc
	 *            院历
	 * @param remind
	 *            提醒
	 * @return
	 */
	public boolean addCalendar(SchoolCalendar sc, Remind remind)
			throws Exception;

	/**
	 * 更新院历,及提醒
	 * 
	 * @param sc
	 *            院历
	 * @param remind
	 *            提醒
	 * @return
	 */
	public boolean modifyCalendar(SchoolCalendar sc, Remind remind)
			throws Exception;

	/**
	 * 删除院历
	 * 
	 * @param id
	 *            院历ID
	 * @return
	 */
	public boolean deleteCalendar(int id) throws Exception;

	/**
	 * 查询院历
	 * 
	 * @param schoolId
	 * @param start
	 * @param end
	 * @return
	 */
	public List<SchoolCalendar> findCalendar(String schoolIds, Date start, Date end)
			throws Exception;

	/**
	 * 增加院历定制
	 * 
	 * @param schoolCalendarSet
	 * @throws Exception
	 */
	public void addSchoolCalendarSet(SchoolCalendarSet schoolCalendarSet)
			throws Exception;

	/**
	 * 修改院历定制
	 * 
	 * @param schoolCalendarSet
	 * @throws Exception
	 */
	public void updateSchoolCalendarSet(SchoolCalendarSet schoolCalendarSet)
			throws Exception;

	/**
	 * 查询院里定制通过中心ID
	 * 
	 * @param branchId
	 * @throws Exception
	 */
	public SchoolCalendarSet findSchoolCalendarSetByBranchId(int branchId)
			throws Exception;
}
