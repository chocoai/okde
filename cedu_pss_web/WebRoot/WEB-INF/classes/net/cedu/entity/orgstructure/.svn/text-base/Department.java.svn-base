package net.cedu.entity.orgstructure;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;

/**
 * 
 * @功能：部门
 *
 * @作者： 谭必庆
 * @作成时间：2011-12-27 下午01:06:10
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Entity
@Table(name = "tb_hr_e_department")
public class Department  implements java.io.Serializable {

	private static final long serialVersionUID = 6592102102434118516L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;					//部门ID
	
	@Column(name = "name" )
	@SortChineseAnnotation(sort = true)
    private String name;			//部门名称
	
	@Column(name = "parent_node" )
	private int parent_Node;			//父节点的ID
	
	@Transient
	transient private Department parent;		//父节点
	
	@Column(name = "logic_node" )
	private String logicNode;		//逻辑路径，包含自身ID的全路径
	
	@Column(name = "create_by" )
	private int createBy;			//创建人ID
	
	@Column(name = "create_on" )
	private Date createOn=new Date();//创建时间
	
	@Column(name = "office_id" )
	private int officeId;			//办公地点ID（分公司）学习中心ID
	
	@Column(name = "orders" )
	private int orders=0;			//部门排序
	

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

	public int getParent_Node() {
		return parent_Node;
	}

	public void setParent_Node(int parent_Node) {
		this.parent_Node = parent_Node;
	}

	public String getLogicNode() {
		return logicNode;
	}

	public void setLogicNode(String logicNode) {
		this.logicNode = logicNode;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public Date getCreateOn() {
		return createOn;
	}

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}

	public int getOfficeId() {
		return officeId;
	}

	public void setOfficeId(int officeId) {
		this.officeId = officeId;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}
}