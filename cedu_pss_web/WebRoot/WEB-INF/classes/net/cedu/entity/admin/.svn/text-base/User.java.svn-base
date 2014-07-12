package net.cedu.entity.admin;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.orgstructure.Department;
import net.cedu.entity.orgstructure.Job;

/**
 * 用户实体
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_p_e_user")
public class User implements Serializable {
	private static final long serialVersionUID = 3950863883401035406L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name = "code")
	private String code;  					//编码 varchar(32)
	
	@Column(name="user_name")
	private String userName; 				//用户名varchar(32)
	
	@Column(name="password")
	private String password;				//密码varchar(32)
	
	@Column(name="full_name")
	@SortChineseAnnotation(sort=true)
	private String fullName;				//姓名varchar(128)
	
	@Column(name="telephone")
	private String telephone;				//电话varchar(32)
	
	@Column(name="mobile")
	private String mobile;					//手机varchar(16)
	
	@Column(name="email")
	private String email;					//电子邮箱varchar(256)
	
	@Column(name="status")
	private int status=0;					//状态 0:启用，1：停用
	
	@Column(name="photo_url")
	private String photoUrl;				//头像图片路径varchar(512)	
	
	@Column(name = "org_id")
	private int orgId;  				    //机构ID（总部/中心）
	
	@Transient
	transient Branch org;    				//所属机构
	
	@Column(name = "ismanager")
	private int isManager=0;  				//是否为区域经理 0:不是 ,1:是
	
	@Column(name = "creator_id")
	private int creatorId;  				//创建人

	@Column(name = "created_time")
	private Date createdTime=new Date();  	//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;  				//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime=new Date();	//最后修改时间
	
	@Column(name = "types")
	private int type=0;  					//用户类型	0:总部用户，1：学习中心用户 
	
	@Column(name = "delete_flag")
	private int deleteFlag=0;  				//删除标记	0:未删除，1：已删除

	@Transient
	transient private List<Academy> academylst;    //授权院校
	
	@Column(name="update_password_time")
	private Date updatePasswordTime=new Date(); //密码修改时间
	
	@Column(name="department_id")
	private int departmentId=0; //部门ID
	
	@Transient
	transient private Department department;    //部门
	
	@Column(name="job_id")
	private int jobId=0; //岗位ID
	
	@Transient
	transient private Job job;	//岗位
	
	@Transient
	private Map<String, Object> objParams = null;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password=password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
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

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}

	public List<Academy> getAcademylst() {
		return academylst;
	}

	public void setAcademylst(List<Academy> academylst) {
		this.academylst = academylst;
	}

	public Branch getOrg() {
		return org;
	}

	public void setOrg(Branch org) {
		this.org = org;
	}

	public Date getUpdatePasswordTime() {
		return updatePasswordTime;
	}

	public void setUpdatePasswordTime(Date updatePasswordTime) {
		this.updatePasswordTime = updatePasswordTime;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}	
	
	public Map<String, Object> getObjParams() {
		if(objParams==null){
			objParams = new HashMap<String, Object>();
		}
		return objParams;
	}
	
}
