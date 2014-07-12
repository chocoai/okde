package net.cedu.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户类 demo
 * 
 * @author Administrator
 * 
 */
@Entity
// 指定此为entity对象
@Table(name = "d_tb_e_user")
// 绑定数据库物理表
public class DUser implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	@Column(name = "cname")
	// 自动创建数据库时的字段长度，建议实际发布时去掉长度限制,方便维护
	private String name;
	@Column(name = "age")
	private int age;
	@Column(name = "code")
	private String code;
	@Column(name = "eamil")
	private String eamil;
	@Column(name = "sid")
	private int sid;
	@Column(name = "cid")
	private int cid;

	@Transient
	private DSex sex;
	@Transient
	private DCity city;

	@Transient
	private String[] creditIds;
	@Transient
	private List<DCredit> credits = new ArrayList<DCredit>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getEamil() {
		return eamil;
	}

	public void setEamil(String eamil) {
		this.eamil = eamil;
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public DSex getSex() {
		return sex;
	}

	public void setSex(DSex sex) {
		this.sex = sex;
	}

	public DCity getCity() {
		return city;
	}

	public void setCity(DCity city) {
		this.city = city;
	}

	public List<DCredit> getCredits() {
		return credits;
	}

	public void setCredits(List<DCredit> credits) {
		this.credits = credits;
	}

	public String[] getCreditIds() {
		return creditIds;
	}

	public void setCreditIds(String[] creditIds) {
		this.creditIds = creditIds;
	}

}
