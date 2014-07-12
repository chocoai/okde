package net.cedu.entity.examination;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tb_e_invigilator_attachment")
public class InvigilatorAttachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8763240442581282822L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  //主建ID
	private int id;
	
	@Column(name = "uploader_uid")
	private int uploaderUid;  //上传人(用户ID)
	
	@Column(name = "title")
	private String title;  //标题
	
	@Column(name = "types")
	private int types;  //附件类型
	
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
	
	@Column(name = "name")
	private String name;  //附件名称
	
	@Column(name= "invigilator_id")
	private int invigilatorId;
	
	@Transient
	transient private String userName;         //登录用户

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getInvigilatorId() {
		return invigilatorId;
	}

	public void setInvigilatorId(int invigilatorId) {
		this.invigilatorId = invigilatorId;
	}
	
	
	
	
	

}
