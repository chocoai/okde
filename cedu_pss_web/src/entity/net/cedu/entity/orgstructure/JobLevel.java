package net.cedu.entity.orgstructure;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.cedu.common.hibernate.SortChineseAnnotation;

/**
 * 岗位级别
 * @author 谭必庆
 *
 */
@Entity
@Table(name = "tb_hr_e_job_level")
public class JobLevel implements java.io.Serializable {
	private static final long serialVersionUID = -3783555258518738999L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;				//岗位级别ID
	
	@Column(name = "name" )
	@SortChineseAnnotation(sort = true)
	private String name;		//岗位级别名称
	
	@Column(name = "levels" )
	private int levels;			//岗位级别
	
	@Column(name = "create_by" )
	private int createBy;		//创建人ID
	
	@Column(name = "create_on" )
	private Date createOn;		//创建时间

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

	public int getLevels() {
		return levels;
	}

	public void setLevels(int levels) {
		this.levels = levels;
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
}