package net.cedu.demo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 实体配置说明
 * 
 * @author yangdongdong
 * 
 */
// 定义持久化实体类及数据库表名
@Entity
@Table(name = "d_tb_e_entity")
public class DEntityDemo implements Serializable {
	// 定义主键自增长列以及数据库列名
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	// 定义不保存在数据库当中的字段
	@Transient
	private String noSave;

	// 定义数据库列名以及长度
	@Column(name = "code", length = 50)
	private String code;

	@Column(name = "count")
	private int count;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNoSave() {
		return noSave;
	}

	public void setNoSave(String noSave) {
		this.noSave = noSave;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
