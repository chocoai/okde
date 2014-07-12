package net.cedu.entity.examination;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*
 * 
 * ¼à¿Ø±¨¸æ
 */

@Entity
@Table(name="tb_e_exam_monitor_report",catalog="cedu_master")
public class ExamMonitorReport implements Serializable {
	/*
	 * 
	 * Ö÷¼üID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="schedule_id")
	private Integer scheduleId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="rep_path")
	private String repPath;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRepPath() {
		return repPath;
	}

	public void setRepPath(String repPath) {
		this.repPath = repPath;
	}
	

}
