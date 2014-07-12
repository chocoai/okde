/**
 * 文件名：BranchEnrollmentWay.java
 * 包名：net.cedu.entity.basesetting
 * 工程：cedu_pss_web/教师培训平台
 * 功能： TODO /请自行添加
 *
 * 作者：Administrator    
 * 日期：2012-1-9 上午12:25:31
 *
 * Copyright (c) 2012, 北京弘成教育科技有限公司 All Rights Reserved.
*/
package net.cedu.entity.basesetting;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @功能：学习中心授权市场途径
 *
 * @作者： 谭必庆
 * @作成时间：2012-1-9 上午12:27:25
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Entity		   
@Table(name = "tb_r_branch_enrollment_way")
public class BranchEnrollmentWay implements Serializable 
{
	private static final long serialVersionUID = 19546496411345748L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="branch_id")
	private int branchId; 					//学习中心ID
	
	@Column(name="enrollment_ways")
	private String enrollmentWays; 			//授权市场途径

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getEnrollmentWays() {
		return enrollmentWays;
	}

	public void setEnrollmentWays(String enrollmentWays) {
		this.enrollmentWays = enrollmentWays;
	}
}
