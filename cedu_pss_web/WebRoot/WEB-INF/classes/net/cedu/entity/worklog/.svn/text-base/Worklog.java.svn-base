package net.cedu.entity.worklog;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.entity.admin.User;
import net.cedu.entity.orgstructure.Department;

/**
 * 工作日志
 * 
 * @author Jack
 * 
 */
@Entity
@Table(name = "tb_e_worklog")
public class Worklog implements java.io.Serializable {

	private static final long serialVersionUID = -8861845186857898628L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	private int id; // 日志ID

	@Column(name = "original_id")
	private int originalId = 0; // 日志原件ID

	@Transient
	transient private Worklog original; // 日志原件

	@Column(name = "title")
	private String title; // 日志标题

	@Column(name = "content")
	private String content; // 日志内容

	@Column(name = "score")
	private String score = "0"; // 评分

	@Column(name = "create_by")
	private int createBy = 0; // 创建人ID

	@Transient
	transient private User createUser; // 创建人

	@Column(name = "create_on")
	private Date createOn = new Date(); // 创建时间

	@Column(name = "cu_department_id")
	private int cuDepartmentId = 0; // 创建人部门

	@Transient
	transient private Department cuDepartment; // 创建人

	@Column(name = "cu_job_level")
	private int cuJobLevel = 0; // 创建人岗位级别

	@Column(name = "audit_id")
	private int auditId = 0; // 审核者id

	@Transient
	transient private User auditUser; // 审核者

	@Column(name = "au_department_id")
	private int auDepartmentId = 0; // 审核人部门

	@Transient
	transient private Department auDepartment; // 审核者部门

	@Column(name = "au_job_level")
	private int auJobLevel = 0; // 审核人岗位级别

	@Column(name = "status")
	private int status = 0; // 0:初始化,1：提交审批，2：已审批

	@Transient
	transient private String statusIds; // 状态集合

	@Transient
	transient private String userIds; // 查询用户IDs

	@Transient
	transient private String linDaoPingJia; // 领导评价

	@Transient
	transient private List<Worklog> records = null;// 审批记录

	@Transient
	transient private boolean isPingFen = true;// 是否可以评分默认都可以

	@Transient
	transient private boolean isShowPingFen = true;// 是否显示评分

	@Transient
	transient private boolean isDelete;// 是否已删除

	public Worklog() {
	}

	public Worklog(int originalId, String title, String content, String score,
			int createBy, int cuDepartmentId, int cuJobLevel, int auditId,
			int auDepartmentId, int auJobLevel, int status) {
		this.originalId = originalId;
		this.title = title;
		this.content = content;
		this.score = score;
		this.createBy = createBy;
		this.cuDepartmentId = cuDepartmentId;
		this.cuJobLevel = cuJobLevel;
		this.auditId = auditId;
		this.auDepartmentId = auDepartmentId;
		this.auJobLevel = auJobLevel;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOriginalId() {
		return originalId;
	}

	public void setOriginalId(int originalId) {
		this.originalId = originalId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public int getCuDepartmentId() {
		return cuDepartmentId;
	}

	public void setCuDepartmentId(int cuDepartmentId) {
		this.cuDepartmentId = cuDepartmentId;
	}

	public int getCuJobLevel() {
		return cuJobLevel;
	}

	public void setCuJobLevel(int cuJobLevel) {
		this.cuJobLevel = cuJobLevel;
	}

	public int getAuditId() {
		return auditId;
	}

	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}

	public int getAuDepartmentId() {
		return auDepartmentId;
	}

	public void setAuDepartmentId(int auDepartmentId) {
		this.auDepartmentId = auDepartmentId;
	}

	public int getAuJobLevel() {
		return auJobLevel;
	}

	public void setAuJobLevel(int auJobLevel) {
		this.auJobLevel = auJobLevel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Department getCuDepartment() {
		return cuDepartment;
	}

	public void setCuDepartment(Department cuDepartment) {
		this.cuDepartment = cuDepartment;
	}

	public User getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(User auditUser) {
		this.auditUser = auditUser;
	}

	public Department getAuDepartment() {
		return auDepartment;
	}

	public void setAuDepartment(Department auDepartment) {
		this.auDepartment = auDepartment;
	}

	public Worklog getOriginal() {
		return original;
	}

	public void setOriginal(Worklog original) {
		this.original = original;
	}

	public String getStatusIds() {
		return statusIds;
	}

	public void setStatusIds(String statusIds) {
		this.statusIds = statusIds;
	}

	public List<Worklog> getRecords() {
		return records == null ? new ArrayList<Worklog>() : records;
	}

	public void setRecords(List<Worklog> records) {
		this.records = records;
	}

	public String getLinDaoPingJia() {
		return linDaoPingJia;
	}

	public void setLinDaoPingJia(String linDaoPingJia) {
		this.linDaoPingJia = linDaoPingJia;
	}

	public boolean isPingFen() {
		return isPingFen;
	}

	public void setPingFen(boolean isPingFen) {
		this.isPingFen = isPingFen;
	}

	public boolean isShowPingFen() {
		return isShowPingFen;
	}

	public void setShowPingFen(boolean isShowPingFen) {
		this.isShowPingFen = isShowPingFen;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

}