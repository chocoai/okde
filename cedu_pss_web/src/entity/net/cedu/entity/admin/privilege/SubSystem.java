package net.cedu.entity.admin.privilege;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 子系统
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_p_e_sub_system")
public class SubSystem implements Serializable{
	private static final long serialVersionUID = 37255836028407440L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	private String name; 					//子系统名称varchar(64)
	
	@Column(name="orders")
	private int orders=0; 					//子系统显示排序

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
}
