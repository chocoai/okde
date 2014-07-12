package net.cedu.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 学分demo
 * 
 * @author yangdongdong
 * 
 */
@Entity
@Table(name = "d_tb_e_credit")
public class DCredit implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	// 用户ID
	@Column(name = "uid", length = 50)
	private long uid;
	
	@Transient
	private DUser user;
	
	// 学科
	@Column(name = "course", length = 50)
	private String course;
	// 分数
	@Column(name = "fraction")
	private double fraction;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public double getFraction() {
		return fraction;
	}

	public void setFraction(double fraction) {
		this.fraction = fraction;
	}

	public DUser getUser() {
		return user;
	}

	public void setUser(DUser user) {
		this.user = user;
	}

}
