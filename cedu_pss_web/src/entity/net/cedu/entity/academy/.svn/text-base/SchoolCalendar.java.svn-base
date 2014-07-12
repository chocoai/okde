package net.cedu.entity.academy;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 院历实体
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_schoolcalendar")
public class SchoolCalendar implements Serializable {
	private static final long serialVersionUID = 7767347304211267854L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "title", length = 128)
	private String title; // 日程标题
	@Column(name = "content", length = 512)
	private String content; // 日程内容，备注
	@Column(name = "start_date")
	private Date startDate; // 开始日期
	@Column(name = "end_date")
	private Date endDate; // 结束日期
	@Column(name = "creator_id")
	private int creatorId; // 创建者ID
	@Column(name = "create_date")
	private Date createDate; // 创建时间
	@Column(name = "update_id")
	private int updateId;// 更新ID
	@Column(name = "update_date")
	private Date updateDate; // 更新时间
	@Column(name = "school_id")
	private int schoolId;// 院校ID
	@Column(name = "repeat_type")
	private int repeatType;// 重复类型
	@Column(name = "repeat_end_type")
	private int repeatEndType;// 重复结束类型
	@Column(name = "repeat_end_date")
	private Date repeatEndDate;// 重复结束时间
	@Column(name = "location")
	private String location;// 位置
	@Column(name = "is_all_day")
	private int isAllDay;// 是否全天事件

	@Column(name = "invitee")
	private String invitee;// 被邀请人
	private String color;// 颜色

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUpdateId() {
		return updateId;
	}

	public void setUpdateId(int updateId) {
		this.updateId = updateId;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getIsAllDay() {
		return isAllDay;
	}

	public void setIsAllDay(int isAllDay) {
		this.isAllDay = isAllDay;
	}

	public String getInvitee() {
		return invitee;
	}

	public void setInvitee(String invitee) {
		this.invitee = invitee;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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

}
