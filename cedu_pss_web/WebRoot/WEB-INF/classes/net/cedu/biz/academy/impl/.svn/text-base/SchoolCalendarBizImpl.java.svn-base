package net.cedu.biz.academy.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.biz.academy.SchoolCalendarBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.RemindDao;
import net.cedu.dao.academy.SchoolCalendarDao;
import net.cedu.dao.academy.SchoolCalendarSetDao;
import net.cedu.entity.academy.Remind;
import net.cedu.entity.academy.SchoolCalendar;
import net.cedu.entity.academy.SchoolCalendarSet;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院历业务实现累
 * 
 * @author yangdongdong
 * 
 */
@Service
public class SchoolCalendarBizImpl implements SchoolCalendarBiz {
	@Autowired
	private SchoolCalendarDao schoolCalendarDao;
	@Autowired
	private SchoolCalendarSetDao schoolCalendarSetDao;
	@Autowired
	private RemindDao remindDao;

	/*
	 * 增加院历及提醒
	 * 
	 * @see
	 * net.cedu.biz.academy.SchoolCalendarBiz#addCalendar(net.cedu.entity.academy
	 * .SchoolCalendar, net.cedu.entity.academy.Remind)
	 */
	public boolean addCalendar(SchoolCalendar sc, Remind remind)
			throws Exception {
		if (sc != null) {
			// 保存日程
			Object object = schoolCalendarDao.save(sc);

			if (object != null) {
				return true;
			}
		}

		return false;
	}

	/*
	 * 删除院历
	 * 
	 * @see net.cedu.biz.academy.SchoolCalendarBiz#deleteCalendar(int)
	 */
	public boolean deleteCalendar(int id) throws Exception {
		if (id != 0) {
			// 删除
			Object object = schoolCalendarDao.deleteById(id);
			if (object != null) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 查询院历
	 * 
	 * @see net.cedu.biz.academy.SchoolCalendarBiz#findCalendar(String,
	 * java.util.Date, java.util.Date)
	 */
	public List<SchoolCalendar> findCalendar(String schoolIds, Date start,
			Date end) throws Exception {
		List<SchoolCalendar> schoolCalendars = new ArrayList<SchoolCalendar>();

		PageParame pageParame = new PageParame("schoolId,startDate");

		String hqlString = "";
		List objs = new ArrayList();

		if (schoolIds != null && "-1".equals(schoolIds)) {

		} else {
			hqlString += " and schoolId in(" + Constants.PLACEHOLDER + ")";
			objs.add("$" + (schoolIds==null||schoolIds.equals("")?0:schoolIds));
		}
		// if (start != null && end != null) {
		// hqlString += " and ((startDate BETWEEN " + Constants.PLACEHOLDER
		// + " AND " + Constants.PLACEHOLDER + ")";
		// objs.add(DateUtil.getDate(start, "yyyy-MM-dd HH:mm:ss"));
		// objs.add(DateUtil.getDate(end, "yyyy-MM-dd HH:mm:ss"));
		// hqlString += " or (endDate BETWEEN " + Constants.PLACEHOLDER
		// + " AND " + Constants.PLACEHOLDER + "))";
		// objs.add(DateUtil.getDate(start, "yyyy-MM-dd HH:mm:ss"));
		// objs.add(DateUtil.getDate(end, "yyyy-MM-dd HH:mm:ss"));
		// }
		if (!hqlString.equals("")) {
			pageParame.setHqlConditionExpression(hqlString);
			pageParame.setValues(objs.toArray());
		}

		Long[] idsLongs = schoolCalendarDao.getIDs(pageParame);
		if (idsLongs != null) {
			for (int i = 0; i < idsLongs.length; i++) {
				SchoolCalendar schoolCalendar = schoolCalendarDao
						.findById(Integer.parseInt(idsLongs[i].toString()));
				if (schoolCalendar != null) {
					schoolCalendars.add(schoolCalendar);
				}
			}
		}
		// return schoolCalendarDao.findAll();
		return schoolCalendars;
	}

	/*
	 * 修改院历
	 * 
	 * @see
	 * net.cedu.biz.academy.SchoolCalendarBiz#modifyCalendar(net.cedu.entity
	 * .academy.SchoolCalendar, net.cedu.entity.academy.Remind)
	 */
	public boolean modifyCalendar(SchoolCalendar sc, Remind remind)
			throws Exception {
		if (sc != null) {
			// 修改日程
			Object object = schoolCalendarDao.update(sc);
			if (object != null) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 查询院历
	 * 
	 * @see net.cedu.biz.academy.SchoolCalendarBiz#findCalendarById(int)
	 */
	public SchoolCalendar findCalendarById(int id) throws Exception {
		return schoolCalendarDao.findById(id);
	}

	/*
	 * 增加院历定制
	 * 
	 * @see
	 * net.cedu.biz.academy.SchoolCalendarBiz#addSchoolCalendarSet(net.cedu.
	 * entity.academy.SchoolCalendarSet)
	 */
	public void addSchoolCalendarSet(SchoolCalendarSet schoolCalendarSet)
			throws Exception {
		schoolCalendarSetDao.save(schoolCalendarSet);
	}

	/*
	 * 修改院历定制
	 * 
	 * @see
	 * net.cedu.biz.academy.SchoolCalendarBiz#updateSchoolCalendarSet(net.cedu
	 * .entity.academy.SchoolCalendarSet)
	 */
	public void updateSchoolCalendarSet(SchoolCalendarSet schoolCalendarSet)
			throws Exception {
		schoolCalendarSetDao.update(schoolCalendarSet);

	}

	/*
	 * 查询院历定置(non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.academy.SchoolCalendarBiz#findSchoolCalendarSetByBranchId
	 * (int)
	 */
	public SchoolCalendarSet findSchoolCalendarSetByBranchId(int branchId)
			throws Exception {
		List<SchoolCalendarSet> schoolCalendarSets = schoolCalendarSetDao
				.getByProperty("branchId", branchId + "");
		if (schoolCalendarSets != null && schoolCalendarSets.size() != 0) {
			return schoolCalendarSets.get(0);
		}

		return null;
	}

}
