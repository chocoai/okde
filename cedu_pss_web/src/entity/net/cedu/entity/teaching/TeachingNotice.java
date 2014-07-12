package net.cedu.entity.teaching;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 教学公告
 * @author wangmingjie
 *
 */
@Entity
@Table(name = "tb_e_teaching_notice")
public class TeachingNotice implements Serializable{
	private static final long serialVersionUID = 7570493319671427694L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "academy_id")
	private int academyId;  //院校ID
	
	@Column(name = "title")
	private String title;  //标题
	
	@Column(name="publisher")
	private int publisher;//发布人
	
	@Column(name = "publish_time")
	private Date publishTime;// 发布日期
	
	@Column(name = "content")
	private String content;  //内容
	
	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;  //创建人
	
	@Column(name = "created_time")
	private Date createdTime;  //创建时间
	
	@Column(name = "updater_id")
	private int updaterId;  //最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;  //最后修改时间

	
	@Transient
	transient private Date publishStartTime; // 发布的开始时间
	@Transient
	transient private Date publishEndTime; // 发布的结束时间
	
	@Transient
	transient private String schoolName; // 院校名称
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPublisher() {
		return publisher;
	}

	public void setPublisher(int publisher) {
		this.publisher = publisher;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Date getPublishStartTime() {
		return publishStartTime;
	}

	public void setPublishStartTime(Date publishStartTime) {
		this.publishStartTime = publishStartTime;
	}

	public Date getPublishEndTime() {
		return publishEndTime;
	}

	public void setPublishEndTime(Date publishEndTime) {
		this.publishEndTime = publishEndTime;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	
}
