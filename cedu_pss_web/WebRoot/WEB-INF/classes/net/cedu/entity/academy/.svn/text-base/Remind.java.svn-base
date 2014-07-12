package net.cedu.entity.academy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 日程提醒
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_remind")
public class Remind implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "content", length = 128)
	private String content;// 提醒内容

	@Column(name = "type")
	private int type;// 提醒类型

	@Column(name = "remind_time_type")
	private int remindTimeType;// 提醒时间的类型

	@Column(name = "remind_time")
	private Date remindTime;// 提醒时间

	@Column(name = "remind_email")
	private String remindEmail;// 提醒Email

	@Column(name = "school_id")
	private int schoolId;// 院校ID

	@Column(name = "school_calendar_id")
	private int schoolCalendarId;// 院历ID

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getSchoolCalendarId() {
		return schoolCalendarId;
	}

	public void setSchoolCalendarId(int schoolCalendarId) {
		this.schoolCalendarId = schoolCalendarId;
	}

	public int getRemindTimeType() {
		return remindTimeType;
	}

	public void setRemindTimeType(int remindTimeType) {
		this.remindTimeType = remindTimeType;
	}

	public Date getRemindTime() {
		return remindTime;
	}

	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
	}

	public String getRemindEmail() {
		return remindEmail;
	}

	public void setRemindEmail(String remindEmail) {
		this.remindEmail = remindEmail;
	}

}
