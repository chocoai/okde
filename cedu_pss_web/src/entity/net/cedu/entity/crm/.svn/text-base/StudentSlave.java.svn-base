package net.cedu.entity.crm;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生基础信息
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_student_slave")
public class StudentSlave implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "student_id")
	private int studentId;// 用户ID

	@Column(name = "ethnic_group")
	private int ethnicGroup;// 民族

	@Column(name = "alias")
	private String alias;// 别名

	@Column(name = "pen_name")
	private String penName;// 笔名

	@Column(name = "former_name")
	private String formerName;// 曾用名
	@Column(name = "homeplace")
	private String homeplace;// 出生地

	@Column(name = "political_status")
	private int politicalStatus;// 政治面貌

	@Column(name = "training_form")
	private String trainingForm;// 培训方式
	@Column(name = "uee_no")
	private String ueeNo;// 高考号

	@Column(name = "admission_time")
	private Date admissionTime;// 入学时间

	@Column(name = "admission_way")
	private int admissionWay;// 入学方式

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


	public int getEthnicGroup() {
		return ethnicGroup;
	}

	public void setEthnicGroup(int ethnicGroup) {
		this.ethnicGroup = ethnicGroup;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPenName() {
		return penName;
	}

	public void setPenName(String penName) {
		this.penName = penName;
	}

	public String getFormerName() {
		return formerName;
	}

	public void setFormerName(String formerName) {
		this.formerName = formerName;
	}

	public String getHomeplace() {
		return homeplace;
	}

	public void setHomeplace(String homeplace) {
		this.homeplace = homeplace;
	}

	public int getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(int politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getTrainingForm() {
		return trainingForm;
	}

	public void setTrainingForm(String trainingForm) {
		this.trainingForm = trainingForm;
	}

	public String getUeeNo() {
		return ueeNo;
	}

	public void setUeeNo(String ueeNo) {
		this.ueeNo = ueeNo;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(Date admissionTime) {
		this.admissionTime = admissionTime;
	}

	public int getAdmissionWay() {
		return admissionWay;
	}

	public void setAdmissionWay(int admissionWay) {
		this.admissionWay = admissionWay;
	}

}
