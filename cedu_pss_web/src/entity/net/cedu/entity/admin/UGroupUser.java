package net.cedu.entity.admin;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 用户组用户关系
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_p_r_ugroup_user")
public class UGroupUser implements Serializable 
{
	private static final long serialVersionUID = 7137580378299201419L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name="group_id")
	private int groupId;					//用户组ID
	
	@Column(name="user_id")
	private int userId;						//用户ID
	
	@Transient
	transient private User user;			//所属机构

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
