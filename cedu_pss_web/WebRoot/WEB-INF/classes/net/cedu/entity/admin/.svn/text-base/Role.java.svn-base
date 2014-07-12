package net.cedu.entity.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户角色
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_p_e_role")
public class Role implements Serializable 
{
	private static final long serialVersionUID = 5287199158098884729L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					
	private int id;			//主建ID
	
	@Column(name="name")
	private String name; 	//名称varchar(128)

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
}