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
 * 模块
 * @author Jack
 *
 */
@Entity		   
@Table(name = "tb_p_e_modular")
public class Modular implements Serializable
{
	private static final long serialVersionUID = 2938523904724669925L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="name")
	private String name; 					//模块名称varchar(64)
	
	@Column(name="orders")
	private int orders=0; 					//模块显示排序
	
	@Column(name="sub_system_id")
	private int subSystemId;				//子系统ID
	
	@Transient
	transient private SubSystem subSystem;	//子系统
	
	@Column(name="image_url")
	private String imageUrl;				//图片路径

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

	public int getSubSystemId() {
		return subSystemId;
	}

	public void setSubSystemId(int subSystemId) {
		this.subSystemId = subSystemId;
	}

	public SubSystem getSubSystem() {
		return subSystem;
	}

	public void setSubSystem(SubSystem subSystem) {
		this.subSystem = subSystem;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
