package net.cedu.entity.persontool;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @功能：对比文件存放路径
 *
 * @作者： Administrator
 * @作成时间：2012-4-8 下午03:25:27
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Entity
@Table(name = "tb_e_person_tool_duibifile")
public class DuibiFile implements Serializable {

	private static final long serialVersionUID = 5825801730147050111L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	// 公服文件路径
	@Column(name = "gongfu_path")
	private String gongfuPath;
	
	// 预报名文件路径
	@Column(name = "yubaoming_path")
	private String yubaomingPath;
	
	// 创建时间
	@Column(name = "creat_on")
	private Date creatOn=new Date();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGongfuPath() {
		return gongfuPath;
	}

	public void setGongfuPath(String gongfuPath) {
		this.gongfuPath = gongfuPath;
	}

	public String getYubaomingPath() {
		return yubaomingPath;
	}

	public void setYubaomingPath(String yubaomingPath) {
		this.yubaomingPath = yubaomingPath;
	}

	public Date getCreatOn() {
		return creatOn;
	}

	public void setCreatOn(Date creatOn) {
		this.creatOn = creatOn;
	}
}
