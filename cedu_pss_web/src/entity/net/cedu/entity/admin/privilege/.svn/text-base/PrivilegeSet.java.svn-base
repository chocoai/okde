package net.cedu.entity.admin.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 权限集
 * @author Jack
 *
 */
@Entity		   
@Table(name = "tb_p_e_privilege_set")
public class PrivilegeSet implements Serializable
{

	private static final long serialVersionUID = 2938523904724669925L;
		
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
		
	@Column(name="name")
	private String name; 					//权限集名称varchar(64)
	
	@Column(name="modular")
	private int modularId;					//模块Id
	
	@Transient
	transient private Modular modular;		//模块
		
	@Column(name="orders")
	private int orders=0; 					//权限集显示排序

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

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public int getModularId() {
		return modularId;
	}

	public void setModularId(int modularId) {
		this.modularId = modularId;
	}

	public Modular getModular() {
		return modular;
	}

	public void setModular(Modular modular) {
		this.modular = modular;
	}
}
