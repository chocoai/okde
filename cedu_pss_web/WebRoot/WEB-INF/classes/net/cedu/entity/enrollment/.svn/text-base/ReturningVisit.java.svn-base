package net.cedu.entity.enrollment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.entity.base.StringTransient;

import org.apache.struts2.json.annotations.JSON;

/**
 * 回访记录
 * @author HXJ
 *
 */
@Entity
@Table(name="tb_e_returning_visit")
public class ReturningVisit extends StringTransient implements Serializable{
	
	private static final long serialVersionUID = -7599912558180241905L;
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //主键ID
	
	@Column(name="student_id")
	private int studentId; //学生ID

	@Column(name="content")
	private String content; //记录内容
	
	@Column(name="visit_results")
	private int visitResults; //回访结果
	
	@Column(name="monitor_results")
	private int monitorResults; //监控结果
	
	@Column(name="types")
	private int types; //类型
	
	@Column(name="delete_flag")
	private int deleteFlag; //删除标记
	
	@Column(name="creator_id")
	private int creatorId; //创建人
	
	@Column(name="created_time")
	private Date createdTime; //创建时间
	
	@Column(name="updater_id")
	private int updaterId; //最后修改人
	
	@Column(name="updated_time")
	private Date updatedTime; //最后修改时间
	
	@Transient
	transient String monitorname;//基础数据的监控结果namess
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getVisitResults() {
		return visitResults;
	}

	public void setVisitResults(int visitResults) {
		this.visitResults = visitResults;
	}

	public int getMonitorResults() {
		return monitorResults;
	}

	public void setMonitorResults(int monitorResults) {
		this.monitorResults = monitorResults;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
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

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getMonitorname() {
		return monitorname;
	}

	public void setMonitorname(String monitorname) {
		this.monitorname = monitorname;
	}

	 
}
