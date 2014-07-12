package net.cedu.entity.finance;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 学生绑定院校返款标准关系表
 * 
 * @author lixiaojun
 * 
 */
@Entity
@Table(name = "tb_r_student_academy_rebate")
public class StudentAcademyRebate implements Serializable
{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "student_id")
	private int studentId; // 学生Id
	
	@Column(name = "fee_subject_id")
	private int feeSubjectId; // 费用科目Id
	
	@Column(name = "academy_police_standard_id")
	private int academyPoliceStandardId; // 院校返款标准Id

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

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public int getAcademyPoliceStandardId() {
		return academyPoliceStandardId;
	}

	public void setAcademyPoliceStandardId(int academyPoliceStandardId) {
		this.academyPoliceStandardId = academyPoliceStandardId;
	}

	
	
}
