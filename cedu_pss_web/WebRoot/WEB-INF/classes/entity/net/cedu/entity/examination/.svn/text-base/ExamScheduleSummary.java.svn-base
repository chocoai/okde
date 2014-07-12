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
 * Ѳ���ܽ�
 */
@Entity
@Table(name="tb_e_exam_schedule_summary",catalog="cedu_master")
public class ExamScheduleSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8682231877429809902L;

	/*
	 * 
	 * ����ID
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="schedule_id")
	private Integer scheduleId;
	
	@Column(name="title")
	private String title;
	
	@Column(name="summary_path")
	private String summaryPath;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	} 
	

}
