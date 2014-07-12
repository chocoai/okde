package net.cedu.entity.crm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

/**
 * 学生跟进
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_follow_up")
public class FollowUp implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "student_id")
	private int studentId;// 学生ID

	@Column(name = "code")
	private String code;// 索引编码

	@Column(name = "status_id")
	private int statusId;// 状态ID

	@Column(name = "call_status_id")
	private int callStatusId;// 呼叫等级ID

	@Column(name = "creator_id")
	private int creatorId;// 跟进人

	@Column(name = "created_time")
	private Date createdTime;// 跟进时间

	@Column(name = "remark")
	private String remark;// 跟进内容

	@Transient
	transient private String followUpName;// 跟进人姓名
	@Transient
	transient private String upFollowUpName;// 上一跟进人
	@Transient
	transient private String followUpBranchName;// 跟进人机构名称
	@Transient
	transient private String upFollowUpBranchName;// 上一跟进人机构名称

	public String getFollowUpBranchName() {
		return followUpBranchName;
	}

	public void setFollowUpBranchName(String followUpBranchName) {
		this.followUpBranchName = followUpBranchName;
	}

	public String getUpFollowUpBranchName() {
		return upFollowUpBranchName;
	}

	public void setUpFollowUpBranchName(String upFollowUpBranchName) {
		this.upFollowUpBranchName = upFollowUpBranchName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getCallStatusId() {
		return callStatusId;
	}

	public void setCallStatusId(int callStatusId) {
		this.callStatusId = callStatusId;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
	@JSON(format="yyyy-MM-dd HH:mm")
	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public String getFollowUpName() {
		return followUpName;
	}

	public void setFollowUpName(String followUpName) {
		this.followUpName = followUpName;
	}

	public String getUpFollowUpName() {
		return upFollowUpName;
	}

	public void setUpFollowUpName(String upFollowUpName) {
		this.upFollowUpName = upFollowUpName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
