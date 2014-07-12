package net.cedu.entity.meterial;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * 物料
 * @author YY
 */
@Entity
@Table(name = "tb_e_meterial", catalog = "cedu_master")
public class Meterial implements java.io.Serializable {
 
	private static final long serialVersionUID = -6186337392415143358L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;          //主键id  
	
	@Column(name = "code" )
	private String code;         //编号
	
	@Column(name = "name")
	private String name;         //物料名称
	
	@Column(name = "picture")
	private String picture;      //物料图片
	
	@Column(name = "category_id")
	private int categoryId;  //物料分类
	
	@Transient
	transient private String categoryname; //物料分类名称
	
	@Column(name = "specification")
	private String specification;//规格
	
	@Column(name = "price")
	private Double price;        //单价
	
	@Column(name = "note")
	private String note;         //备注
	
	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;   //创建人
	
	@Column(name = "created_time")
	private Date createdTime=new Date();//创建时间
	
	@Column(name = "updater_id")
	private int updaterId;    //最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;//最后修改时间

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

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
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

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

 
 

	
	 

	 
	
	 

	 

	
	 

 

 
 

	
 

	 

	 

 

}