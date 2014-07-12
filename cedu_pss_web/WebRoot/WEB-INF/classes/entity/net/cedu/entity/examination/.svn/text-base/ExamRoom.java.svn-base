package net.cedu.entity.examination;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 考场.
 * @author panyuheng
 */
@Entity
@Table(name = "tb_e_exam_room", catalog = "cedu_master")
public class ExamRoom implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -1769832992269795735L;
	private Integer id;
	private String code;
	private String name;
	private String address;
	private String linkman;
	private String phone;
	private String mobile;
	private String email;
	private Integer isSignable;
	private Integer isLongTerm;
	private Integer hasInvigilator;
	private Integer roomCount;
	private Integer status;
	private Integer deleteFlag;
	private Integer creatorId;
	private Date createdTime;
	private Integer updaterId;
	private Date updatedTime;

	

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "code", length = 16)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@Column(name="signable_season")
	private String signableSeason;

	
	@Column(name="long_term_season")
	private String longTermSeason;
	
	@Column(name="has_invigilator_season")
	private String hasInvigilatorSeason;

	

	public String getSignableSeason() {
		return signableSeason;
	}

	public void setSignableSeason(String signableSeason) {
		this.signableSeason = signableSeason;
	}

	public String getLongTermSeason() {
		return longTermSeason;
	}

	public void setLongTermSeason(String longTermSeason) {
		this.longTermSeason = longTermSeason;
	}

	public String getHasInvigilatorSeason() {
		return hasInvigilatorSeason;
	}

	public void setHasInvigilatorSeason(String hasInvigilatorSeason) {
		this.hasInvigilatorSeason = hasInvigilatorSeason;
	}

	@Column(name = "name", length = 64)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", length = 256)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "linkman", length = 32)
	public String getLinkman() {
		return this.linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	@Column(name = "phone", length = 32)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "mobile", length = 16)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "is_signable")
	public Integer getIsSignable() {
		return this.isSignable;
	}

	public void setIsSignable(Integer isSignable) {
		this.isSignable = isSignable;
	}

	@Column(name = "is_long_term")
	public Integer getIsLongTerm() {
		return this.isLongTerm;
	}

	public void setIsLongTerm(Integer isLongTerm) {
		this.isLongTerm = isLongTerm;
	}

	@Column(name = "has_invigilator")
	public Integer getHasInvigilator() {
		return this.hasInvigilator;
	}

	public void setHasInvigilator(Integer hasInvigilator) {
		this.hasInvigilator = hasInvigilator;
	}

	@Column(name = "room_count")
	public Integer getRoomCount() {
		return this.roomCount;
	}

	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "delete_flag")
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Column(name = "creator_id")
	public Integer getCreatorId() {
		return this.creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Column(name = "created_time", length = 19)
	public Date getCreatedTime() {
		return this.createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	@Column(name = "updater_id")
	public Integer getUpdaterId() {
		return this.updaterId;
	}

	public void setUpdaterId(Integer updaterId) {
		this.updaterId = updaterId;
	}

	@Column(name = "updated_time", length = 19)
	public Date getUpdatedTime() {
		return this.updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

}