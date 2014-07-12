package net.cedu.model.academy;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 业务实体，院历
 * 
 * @author yangdongdong
 * 
 */
public class Schedule {
	private int id;

	private int schoolId;// 院历ID
	private String title; // 标题
	private String start; // 开始时间
	private String end; // 结束时间
	private boolean allDay;// 是否全天事件
	private String calssName;// 类名
	private boolean editable;// 编辑表格
	private String color = "#FFDEAD";// 字体颜色
	private String backgroundColor;// 背景颜色
	private String borderColor;// 边框颜色
	private String textColor;// 文本颜色
	
	private int repeatType;// 重复类型
	private int repeatEndType;// 重复结束类型
	private Date repeatEndDate;// 重复结束时间
	private String location;// 位置
	private String invitee;// 被邀请人

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public boolean isAllDay() {
		return allDay;
	}

	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	public String getCalssName() {
		return calssName;
	}

	public void setCalssName(String calssName) {
		this.calssName = calssName;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public String getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(String borderColor) {
		this.borderColor = borderColor;
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public int getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}

	public int getRepeatType() {
		return repeatType;
	}

	public void setRepeatType(int repeatType) {
		this.repeatType = repeatType;
	}

	public int getRepeatEndType() {
		return repeatEndType;
	}

	public void setRepeatEndType(int repeatEndType) {
		this.repeatEndType = repeatEndType;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getRepeatEndDate() {
		return repeatEndDate;
	}

	public void setRepeatEndDate(Date repeatEndDate) {
		this.repeatEndDate = repeatEndDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


	public String getInvitee() {
		return invitee;
	}

	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}

}