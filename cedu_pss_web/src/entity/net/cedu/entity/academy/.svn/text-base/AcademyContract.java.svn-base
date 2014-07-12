package net.cedu.entity.academy;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

import net.cedu.common.hibernate.SortChineseAnnotation;
import net.cedu.entity.admin.User;


/**
 * 院校合同
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_academy_contract")

public class AcademyContract  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "code")
	private String code;  //编号
	
	@Column(name = "academy_id")
	private int academyId;  //院校ID
	
	@Column(name = "sibscriber_id")
	private int sibscriberId;  //签约人(用户ID)
	
	@Column(name = "name")
	@SortChineseAnnotation(sort = true)
	private String name;  //合同名称
	
	@Temporal(TemporalType.DATE)
	@Column(name = "signup_date")
	private Date signupDate;  //签约日期
	
	@Temporal(TemporalType.DATE)
	@Column(name = "begin_date")
	private Date beginDate;  //开始日期
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end_date")
	private Date endDate;  //结束日期
	
	@Column(name = "note")
	private String note;  //备注
	
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
	transient private String userName;         //登录用户
	
	

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

	public int getSibscriberId() {
		return sibscriberId;
	}

	public void setSibscriberId(int sibscriberId) {
		this.sibscriberId = sibscriberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
	
	
	
	
}
