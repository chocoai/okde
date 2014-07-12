package net.cedu.entity.orgstructure;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;

/**
 * 岗位
 * @author 谭必庆
 *
 */
@Entity
@Table(name = "tb_hr_e_job")
public class Job implements java.io.Serializable {
	private static final long serialVersionUID = -3783555258518738999L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;				//岗位ID
	
	@Column(name = "department_id" )
	private int departmentId;	//部门ID
	
	@Column(name = "name" )
	@SortChineseAnnotation(sort = true)
	private String name;		//岗位名称
	
	@Column(name = "job_level" )
	private int jobLevelId;		//岗位级别
	
	@Transient
	transient private JobLevel jobLevel;//父节点
	
//	@Column(name = "parent_node" )
//	private int parentNode;		//父节点的ID
//	
//	@Column(name = "logic_node" )
//	private String logicNode;	//逻辑路径，包含自身ID的全路径
	
	@Column(name = "create_by" )
	private int createBy;		//创建人ID
	
	@Column(name = "create_on" )
	private Date createOn=new Date();		//创建时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getJobLevelId() {
		return jobLevelId;
	}

	public void setJobLevelId(int jobLevelId) {
		this.jobLevelId = jobLevelId;
	}

	public JobLevel getJobLevel() {
		return jobLevel;
	}

	public void setJobLevel(JobLevel jobLevel) {
		this.jobLevel = jobLevel;
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

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
}