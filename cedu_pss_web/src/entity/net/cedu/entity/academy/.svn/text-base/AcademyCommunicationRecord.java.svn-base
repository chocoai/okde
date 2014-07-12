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

import org.apache.struts2.json.annotations.JSON;

import net.cedu.entity.admin.User;


/**
 * 沟通记录(院校)
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_academy_communication_record")
public class AcademyCommunicationRecord implements Serializable {
	
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
		
	@Column(name = "communication_user_id")
	private int communicationUserId;  //沟通人（用户ID）
	
	@Column(name = "academy_id")
	private int academyId;     //院校ID
	
	@Column(name = "academy_linkman_id")
	private int academyLinkmanId;     //院校联系人ID
	
	@Column(name = "subject")
	private String subject;  //主题
	
	@Column(name = "content")
	private String content;  //内容
	
	@Column(name = "result")
	private String result;  //结果
	
	@Column(name = "comtime")
	private Date comtime;  //时间
	
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
	
	@Transient
	transient private String academylinkman;  //院校联系人名称
	
	@Transient
	transient private String academyName;  //院校名称
	
	@Transient
	transient private String communicationUserName;  //沟通人
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommunicationUserId() {
		return communicationUserId;
	}

	public void setCommunicationUserId(int communicationUserId) {
		this.communicationUserId = communicationUserId;
	}

	public int getAcademyLinkmanId() {
		return academyLinkmanId;
	}

	public void setAcademyLinkmanId(int academyLinkmanId) {
		this.academyLinkmanId = academyLinkmanId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
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

	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	public String getAcademylinkman() {
		return academylinkman;
	}

	public void setAcademylinkman(String academylinkman) {
		this.academylinkman = academylinkman;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getComtime() {
		return comtime;
	}

	public void setComtime(Date comtime) {
		this.comtime = comtime;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
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

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getCommunicationUserName() {
		return communicationUserName;
	}

	public void setCommunicationUserName(String communicationUserName) {
		this.communicationUserName = communicationUserName;
	}
	
}
