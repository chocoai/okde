package net.cedu.action.academy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.academy.SchoolCalendarBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.academy.Remind;
import net.cedu.entity.academy.SchoolCalendar;
import net.cedu.entity.academy.SchoolCalendarSet;
import net.cedu.model.academy.Schedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学校院历
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonCalendarAction extends BaseAction {

	@Autowired
	private SchoolCalendarBiz schoolCalendarBiz;

	@Autowired
	private AcademyBiz academybiz; // 院校Biz

	// 院历集合
	private List<Schedule> schedules = new ArrayList<Schedule>();
	// 院历持久化实体
	private SchoolCalendar schedule;
	// 院历数据载体
	private Schedule s;
	// 提醒实体
	private Remind remind;
	// 院历视图开始时间
	private Date start;
	// 院历视图结束时间
	private Date end;

	// 院校
	private int id;
	private Academy academy;

	@Action(value = "findacademy", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,s,schedule,schedules,start,id" }) })
	public String findAcademy() throws Exception {
		academy = academybiz.findAcademyById(id);
		return SUCCESS;
	}
	/**
	 * 按条件查询院历
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "calendarlist_id", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,s,schedule,start,academy,id" }) })
	public String calendarListById() throws Exception {
		if (start == null || end == null) {
			String year = DateUtil.getDate(new Date(), "yyyy");
			start = DateUtil.StringToDate(year + "-01-01 00:00:00",
					Constants.DATE_TIME_FORMAT);
			end = DateUtil.StringToDate(year + "-12-31 23:59:59",
					Constants.DATE_TIME_FORMAT);
		}
		List<SchoolCalendar> schoolCalendars = schoolCalendarBiz.findCalendar(
				id+"", start, end);
		// 遍历院历
		listSchedules(schoolCalendars);

		List<Schedule> schsList = new ArrayList<Schedule>();
		// 过滤数据
		int i = 1;
		for (Schedule sch : schedules) {
			try {
				if (DateUtil.StringToDate(sch.getEnd(),
						Constants.DATE_TIME_FORMAT).getTime() >= start
						.getTime()) {

					schsList.add(sch);
				}
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		schedules = schsList;
		return SUCCESS;
	}
	
	/**
	 * 按条件查询院历(院历设置)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "calendarsetuplist", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,s,schedule,start,academy,id" }) })
	public String calendarSetupList() throws Exception {
		if (start == null || end == null) {
			String year = DateUtil.getDate(new Date(), "yyyy");
			start = DateUtil.StringToDate(year + "-01-01 00:00:00",
					Constants.DATE_TIME_FORMAT);
			end = DateUtil.StringToDate(year + "-12-31 23:59:59",
					Constants.DATE_TIME_FORMAT);
		}
		//SchoolCalendarSet schoolCalendarSet = schoolCalendarBiz.findSchoolCalendarSetByBranchId(super.getUser().getOrgId());
		//查询所有
		List<SchoolCalendar> schoolCalendars = schoolCalendarBiz.findCalendar("-1", start, end);
		// 遍历院历
		listSchedules(schoolCalendars);

		List<Schedule> schsList = new ArrayList<Schedule>();
		// 过滤数据
		int i = 1;
		for (Schedule sch : schedules) {
			try {
//				if (DateUtil.StringToDate(sch.getEnd(),
//						Constants.DATE_TIME_FORMAT).getTime() >= start
//						.getTime()) {

					schsList.add(sch);
//				}
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		schedules = schsList;
		return SUCCESS;
	}
	
	/**
	 * 按条件查询院历
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "calendarlist", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,s,schedule,start,academy,id" }) })
	public String calendarList() throws Exception {
		if (start == null || end == null) {
			String year = DateUtil.getDate(new Date(), "yyyy");
			start = DateUtil.StringToDate(year + "-01-01 00:00:00",
					Constants.DATE_TIME_FORMAT);
			end = DateUtil.StringToDate(year + "-12-31 23:59:59",
					Constants.DATE_TIME_FORMAT);
		}
		SchoolCalendarSet schoolCalendarSet = schoolCalendarBiz.findSchoolCalendarSetByBranchId(super.getUser().getOrgId());
		List<SchoolCalendar> schoolCalendars = schoolCalendarBiz.findCalendar(
				schoolCalendarSet==null?null:schoolCalendarSet.getSchoolIds(), start, end);
		// 遍历院历
		listSchedules(schoolCalendars);

		List<Schedule> schsList = new ArrayList<Schedule>();
		// 过滤数据
		int i = 1;
		for (Schedule sch : schedules) {
			try {
//				if (DateUtil.StringToDate(sch.getEnd(),
//						Constants.DATE_TIME_FORMAT).getTime() >= start
//						.getTime()) {

					schsList.add(sch);
//				}
				i++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		schedules = schsList;
		return SUCCESS;
	}

	/**
	 * 删除院历
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deletecalendar", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,schedules,schedule,start,s,academy,id" }) })
	public String deleteCalendar() throws Exception {
		try {
			// 删除院历
			if (schedule != null && schedule.getId() != 0) {
				schoolCalendarBiz.deleteCalendar(schedule.getId());
			}
		} catch (Exception e) {
			return SUCCESS;
		}

		return SUCCESS;
	}

	/**
	 * 查询单条院历
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "findcalendar", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,schedules,s,start,academy,id" }) })
	public String findCalendar() throws Exception {
		//
		try {
			if (s != null && s.getId() != 0) {
				schedule = schoolCalendarBiz.findCalendarById(s.getId());
			} else {
				s = null;
			}
		} catch (Exception e) {
			s = null;
		}
		return SUCCESS;
	}

	/**
	 * 增加院历
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "addcalendar", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,schedules,start,s,academy,id" }) })
	public String addCalendar() throws Exception {
		// 创建者ID
		schedule.setCreatorId(super.getUser().getUserId());
		// 创建时间
		schedule.setCreateDate(DateUtil.getNowTimestamp());
		// 创建院历操作
		schoolCalendarBiz.addCalendar(schedule, null);
		return SUCCESS;
	}

	/**
	 * 更新院历所有信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatecalendar", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,schedules,start,s,academy,id" }) })
	public String updateCalendar() throws Exception {
		try {
			// new院历
			SchoolCalendar sc = schoolCalendarBiz.findCalendarById(schedule
					.getId());
			if (sc != null) {
				sc.setSchoolId(schedule.getSchoolId());
				sc.setTitle(schedule.getTitle());
				sc.setLocation(schedule.getLocation());
				sc.setIsAllDay(schedule.getIsAllDay());
				sc.setStartDate(schedule.getStartDate());
				sc.setEndDate(schedule.getEndDate());
				sc.setRepeatType(schedule.getRepeatType());
				sc.setRepeatEndType(schedule.getRepeatEndType());
				sc.setRepeatEndDate(schedule.getRepeatEndDate());
				sc.setColor(schedule.getColor());
				sc.setInvitee(schedule.getInvitee());
				sc.setContent(schedule.getContent());
				sc.setUpdateId(super.getUser().getUserId());
				sc.setUpdateDate(DateUtil.getNow());
				schoolCalendarBiz.modifyCalendar(sc, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}

		return SUCCESS;
	}

	/**
	 * 更新院历时间
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updatecalendardate", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,schedules,schedule,start,s,academy,id" }) })
	public String updateCalendarDate() throws Exception {
		try {
			// new院历
			SchoolCalendar sc = schoolCalendarBiz.findCalendarById(s.getId());
			if (sc != null) {
				// 开始时间
				sc.setStartDate(DateUtil.StringToDate(s.getStart(),
						Constants.DATE_TIME_FORMAT));
				// 结束事件
				if (s.getEnd() != null && !s.getEnd().equals("")) {
					sc.setEndDate(DateUtil.StringToDate(s.getEnd(),
							Constants.DATE_TIME_FORMAT));
				} else {
					sc.setEndDate(null);
				}
				schoolCalendarBiz.modifyCalendar(sc, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}

		return SUCCESS;
	}

	/**
	 * 遍历院历
	 * 
	 * @param schoolCalendars
	 */
	private void listSchedules(List<SchoolCalendar> schoolCalendars) {

		for (SchoolCalendar schoolCalendar : schoolCalendars) {

			// 当重复院历没有结束日期时
			if (Constants.CAL_REPEAT_END_NEVER == schoolCalendar
					.getRepeatEndType()) {
				schoolCalendar.setRepeatEndDate(end);
			}
			switch (schoolCalendar.getRepeatType()) {
			case Constants.CAL_REPEAT_DAY:// 按天重复
				// 结束于日期
				// if (schoolCalendar.getRepeatEndType() ==
				// Constants.CAL_REPEAT_END_DATE) {
				int days = DateUtil
						.daysOfTwo(schoolCalendar.getStartDate(),
								schoolCalendar.getRepeatEndDate(),
								Calendar.DAY_OF_YEAR);
				for (int i = 0; i <= days; i++) {
					// 创建院历实体
					createSchedule(schoolCalendar, Calendar.DATE, i);
				}

				// }
				break;
			case Constants.CAL_REPEAT_WEEK:// 按周重复

				int weeks = DateUtil.daysOfTwo(schoolCalendar.getStartDate(),
						schoolCalendar.getRepeatEndDate(),
						Calendar.WEEK_OF_YEAR);
				for (int i = 0; i <= weeks; i++) {
					// 创建院历实体
					createSchedule(schoolCalendar, Calendar.DATE, i * 7);
				}
				break;
			case Constants.CAL_REPEAT_MONTH:// 按月重复
				int months = DateUtil.daysOfTwo(schoolCalendar.getStartDate(),
						schoolCalendar.getRepeatEndDate(), Calendar.MONTH);
				for (int i = 0; i <= months; i++) {
					// 创建院历实体
					createSchedule(schoolCalendar, Calendar.MONTH, i);
				}
				break;
			case Constants.CAL_REPEAT_YEAR:// 按年重复
				int years = DateUtil.daysOfTwo(schoolCalendar.getStartDate(),
						schoolCalendar.getRepeatEndDate(), Calendar.YEAR);
				for (int i = 0; i <= years; i++) {
					// 创建院历实体
					createSchedule(schoolCalendar, Calendar.YEAR, i);
				}
				break;
			default:// 无重复
				createSchedule(schoolCalendar, -1, 0);
				break;
			}
		}
	}

	/**
	 * 生成日程对象
	 * 
	 * @param schoolCalendar
	 *            院历实体
	 * @param type
	 *            类型
	 * @param step
	 *            步长
	 */
	private void createSchedule(SchoolCalendar schoolCalendar, int type,
			int step) {
		s = new Schedule();
		s.setId(schoolCalendar.getId());
		s.setTitle(schoolCalendar.getTitle());
		s.setAllDay(schoolCalendar.getIsAllDay() == 0 ? false : true);
		s.setColor(schoolCalendar.getColor());
		s.setSchoolId(schoolCalendar.getSchoolId());

		s.setRepeatEndDate(schoolCalendar.getRepeatEndDate());
		s.setRepeatEndType(schoolCalendar.getRepeatEndType());
		s.setRepeatType(schoolCalendar.getRepeatType());
		s.setLocation(schoolCalendar.getLocation());
		s.setInvitee(schoolCalendar.getInvitee());
		// 日程重复
		if (type > 0) {
			// 小于院历边界
			if (start.getTime() > schoolCalendar.getStartDate().getTime()) {
				// schoolCalendar.setStartDate(start);
			}
			// 日期递增
			s.setStart(DateUtil.getDate(DateUtil.increaseDate(schoolCalendar
					.getStartDate(), type, step), Constants.DATE_TIME_FORMAT));

			// 判断开始时间是否大于重复结束时间
			if (DateUtil.daysOfTwo(schoolCalendar.getStartDate(),
					schoolCalendar.getRepeatEndDate(), Calendar.DAY_OF_YEAR) >= 0) {
				// 递增以后的时间
				Date iDate = DateUtil.increaseDate(schoolCalendar.getEndDate(),
						type, step);
				if (iDate.getTime()
						- schoolCalendar.getRepeatEndDate().getTime() > 0) {
					s.setEnd(DateUtil.getDate(
							schoolCalendar.getRepeatEndDate(),
							Constants.DATE_TIME_FORMAT));
				} else {
					s.setEnd(DateUtil
							.getDate(iDate, Constants.DATE_TIME_FORMAT));
				}
				// System.out.println(DateUtil.getDate(schoolCalendar
				// .getStartDate(), "yyyy-MM-dd HH:mm:ss")
				// + "_____"
				// + DateUtil.getDate(schoolCalendar.getEndDate(),
				// "yyyy-MM-dd HH:mm:ss"));

				schedules.add(s);
			}
		} else {// 日程不重复

			s.setStart(DateUtil.getDate(schoolCalendar.getStartDate(),
					Constants.DATE_TIME_FORMAT));
			s.setEnd(DateUtil.getDate(schoolCalendar.getEndDate(),
					Constants.DATE_TIME_FORMAT));
			schedules.add(s);
		}

	}

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	public SchoolCalendar getSchedule() {
		return schedule;
	}

	public void setSchedule(SchoolCalendar schedule) {
		this.schedule = schedule;
	}

	public Remind getRemind() {
		return remind;
	}

	public void setRemind(Remind remind) {
		this.remind = remind;
	}

	public Schedule getS() {
		return s;
	}

	public void setS(Schedule s) {
		this.s = s;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

}
