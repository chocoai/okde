package net.cedu.entity.crm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

/**
 * 学生市场途径变更纪录表
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_student_enrollment_source_change_log")
public class StudentEnrollmentSourceChangeLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "change_id")
	private int changeId;// 变更者
	@Column(name = "student_id")
	private int studentId;// 学生ID
	@Column(name = "change_date")
	private Date changeDate;// 变更时间
	@Column(name = "old_enrollment_source_id")
	private int oldEnrollmentSourceId;// 原招生途径
	@Column(name = "new_enrollment_source_id")
	private int newEnrollmentSourceId;// 新招生途径
	private String remark;// 备注
	@Transient
	transient private Map<String,String> params=new HashMap<String, String>();

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

	@JSON(format="yyyy-MM-dd HH:mm")
	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public int getOldEnrollmentSourceId() {
		return oldEnrollmentSourceId;
	}

	public void setOldEnrollmentSourceId(int oldEnrollmentSourceId) {
		this.oldEnrollmentSourceId = oldEnrollmentSourceId;
	}

	public int getNewEnrollmentSourceId() {
		return newEnrollmentSourceId;
	}

	public void setNewEnrollmentSourceId(int newEnrollmentSourceId) {
		this.newEnrollmentSourceId = newEnrollmentSourceId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getChangeId() {
		return changeId;
	}

	public void setChangeId(int changeId) {
		this.changeId = changeId;
	}

	public Map<String, String> getParams() {
		return params;
	}

	public void setParams(Map<String, String> params) {
		this.params = params;
	}

}
