package net.cedu.entity.code;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * 编码生成器
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_s_system_key")
public class BuildCode implements Serializable 
{
	private static final long serialVersionUID = 7137580378299201419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="tbname")
	private String tbName; 					//表名varchar(128)
	
	@Column(name="keyname")
	private String keyName;					//键名varchar(128)
	
	@Column(name="currentvalue")
	private String currentValue;			//当前值varchar(16)(包含日期长编码用)
	
	@Column(name="short_value")
	private String shortValue;				//短码(不含日期短编码用)

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTbName() {
		return tbName;
	}

	public void setTbName(String tbName) {
		this.tbName = tbName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public String getShortValue() {
		return shortValue;
	}

	public void setShortValue(String shortValue) {
		this.shortValue = shortValue;
	}	
}
