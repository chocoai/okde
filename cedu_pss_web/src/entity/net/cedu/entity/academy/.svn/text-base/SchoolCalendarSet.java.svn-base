package net.cedu.entity.academy;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 院历定置
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "tb_e_schoolcalendar_set")
public class SchoolCalendarSet implements Serializable {
	private static final long serialVersionUID = 7767347304211267854L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "branch_id")
	private int branchId;// 学习中心ID
	@Column(name = "school_ids")
	private String schoolIds;// 学校编号ID集合

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getSchoolIds() {
		return schoolIds;
	}

	public void setSchoolIds(String schoolIds) {
		this.schoolIds = schoolIds;
	}

}
