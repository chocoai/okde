/**
 * 文件名：Jurisdiction.java
 * 包名：net.cedu.entity.orgstructure
 * 工程：cedu_pss_web/教师培训平台
 * 功能： TODO /请自行添加
 *
 * 作者：Administrator    
 * 日期：2011-12-27 下午01:14:01
 *
 * Copyright (c) 2011, 北京弘成教育科技有限公司 All Rights Reserved.
*/
package net.cedu.entity.orgstructure;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_hr_e_jurisdiction")
public class Jurisdiction implements java.io.Serializable
{
	private static final long serialVersionUID = 2363859383376443823L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;					//管辖范围ID
	
	@Column(name = "user_id" )
    private int userId;			//用户ID
	
	@Column(name = "department_ids" )
    private String departmentIds;	//管辖部门范围(##部门ID##)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}	
}	
