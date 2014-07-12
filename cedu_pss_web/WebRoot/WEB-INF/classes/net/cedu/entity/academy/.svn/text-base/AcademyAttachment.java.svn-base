package net.cedu.entity.academy;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;
import net.cedu.entity.admin.User;

import org.apache.struts2.json.annotations.JSON;


/**
 * 院校附件
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_academy_attachment")


public class AcademyAttachment implements Serializable {
	
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
	

	@Column(name = "uploader_uid")
	private int uploaderUid;  //上传人(用户ID)
	
	@Transient
	transient private String userName;         //登录用户
	
	@Column(name = "title")
	@SortChineseAnnotation(sort = true)
	private String title;  //标题
	
	@Column(name = "name")
	private String name;  //附件名称
	
	@Column(name = "types")
	private int type;  //附件类型
	
	@Column(name = "attachment_url")
	private String attachmentUrl;  //附件路径
	
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

	public int getUploaderUid() {
		return uploaderUid;
	}

	public void setUploaderUid(int uploaderUid) {
		this.uploaderUid = uploaderUid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@JSON(format="yyyy-MM-dd hh:mm:ss")
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

	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	
	
	

}
