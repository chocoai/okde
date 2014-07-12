package net.cedu.entity.admin;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.hibernate.SortChineseAnnotation;

/**
 * 中心实体（包含总部）
 * @author Jack
 *
 */
@Entity
@Table(name = "tb_e_branch")
public class Branch implements Serializable {
	private static final long serialVersionUID = -9023983415052236266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")  					//主建ID
	private int id;
	
	@Column(name = "code")
	private String code;  					//编码 varchar(32)
	
	@Column(name="name")
	@SortChineseAnnotation(sort = true)
	private String name; 					//名称varchar(128)
	
	@Column(name="short_name")
	private String shortName;				//简称varcahr(128)
	
	@Column(name="parent_id")
	private int parentId;					//父节点Id varchar(32)
	
	@Transient
	transient private Branch parent;		//父节点
	
	@Column(name="logic_node")
	private String logicNode;				//逻辑节点 varchar(32)  例:0_2_3
	
	@Column(name="levels")
	private int level;						//层级
	
	@Column(name="types")
	private int type=1;						//类别,0:总部,1:学习中心
		
	@Column(name = "creator_id")
	private int creatorId;  				//创建人ID
	
	@Transient
	transient private User creator;			//创建人

	@Column(name = "created_time")
	private Date createdTime=new Date();  	//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;  				//最后修改人ID
	
	@Transient
	private User updater;					//最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime=new Date();	//最后修改时间
	
	@Column(name = "delete_flag")
	private int deleteFlag=0;  				//删除标记
	
	
	@Transient
	transient private int userCount;        //人员数量
	
	@Transient
	transient private int batchTarget;      //上批次指标
	
	@Transient
	transient private int batchComplete;    //上批次完成指标
	
	@Transient
	transient private String branchIds;    //上批次完成指标
	
	@Transient
	transient private int currentBatchTarget;	//当前批次指标

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getLogicNode() {
		return logicNode;
	}

	public void setLogicNode(String logicNode) {
		this.logicNode = logicNode;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public User getUpdater() {
		return updater;
	}

	public void setUpdater(User updater) {
		this.updater = updater;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Branch getParent() {
		return parent;
	}

	public void setParent(Branch parent) {
		this.parent = parent;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getBatchTarget() {
		return batchTarget;
	}

	public void setBatchTarget(int batchTarget) {
		this.batchTarget = batchTarget;
	}

	public int getBatchComplete() {
		return batchComplete;
	}

	public void setBatchComplete(int batchComplete) {
		this.batchComplete = batchComplete;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public int getCurrentBatchTarget() {
		return currentBatchTarget;
	}

	public void setCurrentBatchTarget(int currentBatchTarget) {
		this.currentBatchTarget = currentBatchTarget;
	}

	
}
