package net.cedu.entity.examination;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/*
 * 
 * 考场安排
 * 
 */


@Entity
@Table(name = "tb_r_examroom_plan", catalog = "cedu_master")
public class ExamroomPlan implements Serializable{
	/*
	 * 
	 * 主键Id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id") 
	private Integer id;
	
	@Column(name="exam_plan_id")
	private Integer examPlanId;
	
	@Column(name="exam_room_id")
	private Integer examRoomId;
	
	@Column(name="classroom_id")
	private Integer classroomId;
	
	@Column(name="invigilator_ids")
	private String invigilatorIds;
	
	@Column(name="student_no")
	private Integer studentNo;
	
	@Column(name="delete_flag")
	private Integer deleteFlag;
	
	@Column(name="creator_id")
	private Integer creatorId;
	
	@Column(name="created_time")
	private Date createdTime;
	
	@Column(name="updater_id")
	private Integer updaterId;
	
	@Column(name="updated_time")
	private Date updatedTime;
	@Transient
	transient private String examRoomname; 
	
	@Transient
	transient private String classroomname;
	

	public String getClassroomname() {
		return classroomname;
	}

	public void setClassroomname(String classroomname) {
		this.classroomname = classroomname;
	}

	public String getExamRoomname() {
		return examRoomname;
	}

	public void setExamRoomname(String examRoomname) {
		this.examRoomname = examRoomname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExamPlanId() {
		return examPlanId;
	}

	public void setExamPlanId(Integer examPlanId) {
		this.examPlanId = examPlanId;
	}

	public Integer getExamRoomId() {
		return examRoomId;
	}

	public void setExamRoomId(Integer examRoomId) {
		this.examRoomId = examRoomId;
	}

	public Integer getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(Integer classroomId) {
		this.classroomId = classroomId;
	}

	public String getInvigilatorIds() {
		return invigilatorIds;
	}

	public void setInvigilatorIds(String invigilatorIds) {
		this.invigilatorIds = invigilatorIds;
	}

	public Integer getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(Integer studentNo) {
		this.studentNo = studentNo;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}
	
	

}
