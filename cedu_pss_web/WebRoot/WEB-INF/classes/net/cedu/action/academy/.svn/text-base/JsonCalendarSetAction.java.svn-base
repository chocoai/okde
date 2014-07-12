package net.cedu.action.academy;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.SchoolCalendarBiz;
import net.cedu.entity.academy.SchoolCalendarSet;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学校院历定制
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonCalendarSetAction extends BaseAction {

	@Autowired
	private SchoolCalendarBiz schoolCalendarBiz;

	private SchoolCalendarSet schoolCalendarSet;

	private String[] ids;

	@Action(value = "find_school_calendar_set", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,s,schedule,schedules,start,id" }) })
	public String findSchoolCalendarSet() throws Exception {
		schoolCalendarSet = schoolCalendarBiz
				.findSchoolCalendarSetByBranchId(super.getUser().getOrgId());
		return SUCCESS;
	}

	@Action(value = "update_school_calendar_set", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json", "excludeProperties",
			"end,remind,s,schedule,schedules,start,id" }) })
	public String updateSchoolCalendarSet() throws Exception {
		if (ids != null && ids.length != 0) {
//			String idString = ",";
			StringBuffer idsSB = new StringBuffer(",");
			for (int i = 0; i < ids.length; i++) {
//				if (idString.startsWith(",")) {
//					idString = ids[i];
//				} else {
//					idString += "," + ids[i];
//				}
				if (idsSB.toString().equals(",")){
					idsSB = new StringBuffer(ids[i]);
				} else {
					idsSB.append("," + ids[i]);
				}
			}
			if(idsSB.toString().equals(",")){
				idsSB = new StringBuffer("0");
			}

			SchoolCalendarSet schoolCalendarSet = schoolCalendarBiz
					.findSchoolCalendarSetByBranchId(super.getUser().getOrgId());
			if (schoolCalendarSet != null) {
				schoolCalendarSet.setSchoolIds(idsSB.toString());
				schoolCalendarBiz.updateSchoolCalendarSet(schoolCalendarSet);
			} else {
				schoolCalendarSet = new SchoolCalendarSet();
				schoolCalendarSet.setBranchId(super.getUser().getOrgId());
				schoolCalendarSet.setSchoolIds(idsSB.toString());
				schoolCalendarBiz.addSchoolCalendarSet(schoolCalendarSet);
			}

		}else {
			SchoolCalendarSet schoolCalendarSet = schoolCalendarBiz
			.findSchoolCalendarSetByBranchId(super.getUser().getOrgId());
	if (schoolCalendarSet != null) {
		schoolCalendarSet.setSchoolIds("");
		schoolCalendarBiz.updateSchoolCalendarSet(schoolCalendarSet);
	} else {
		schoolCalendarSet = new SchoolCalendarSet();
		schoolCalendarSet.setBranchId(super.getUser().getOrgId());
		schoolCalendarSet.setSchoolIds("");
		schoolCalendarBiz.addSchoolCalendarSet(schoolCalendarSet);
	}
		}
		return SUCCESS;
	}

	public SchoolCalendarSet getSchoolCalendarSet() {
		return schoolCalendarSet;
	}

	public void setSchoolCalendarSet(SchoolCalendarSet schoolCalendarSet) {
		this.schoolCalendarSet = schoolCalendarSet;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

}
