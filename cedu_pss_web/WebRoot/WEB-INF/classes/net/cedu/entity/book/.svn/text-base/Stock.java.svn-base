package net.cedu.entity.book;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 库存
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_stock")
public class Stock implements Serializable {
 
	private static final long serialVersionUID = 2388402536228574579L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="storeroom_id")
	private int storeroomId;//库房ID
	
	@Transient
	transient private String storeroomweizhi;  //库房名称 
	
	@Transient
	transient private String storeroomname;  //库房名称 
	
	@Column(name="book_id")
	private int bookId;//教材ID
	
	@Transient
	transient private String bookname;; //教材名称
	
	@Transient
	transient private String bookedition;; //教材版次
	
	@Transient
	transient private double bookprice;; //教材定价
	
	@Column(name="total")
	private int total;//库存数量
	
	@Column(name="price")
	private double price;//总价
	
	@Column(name="delete_flag")
    private int deleteFlag; 	//删除标记
	
	@Column(name="creator_id")
    private int creatorId;     //创始人
	
	@Column(name="created_time")
    private Date createdTime;  //创建时间
	
	@Column(name="updater_id")
    private int updater_id;     //最后修改人
	
	@Column(name="updated_time")
    private Date updatedTime;  //最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStoreroomId() {
		return storeroomId;
	}

	public void setStoreroomId(int storeroomId) {
		this.storeroomId = storeroomId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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

	public int getUpdater_id() {
		return updater_id;
	}

	public void setUpdater_id(int updaterId) {
		updater_id = updaterId;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getStoreroomname() {
		return storeroomname;
	}

	public void setStoreroomname(String storeroomname) {
		this.storeroomname = storeroomname;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getStoreroomweizhi() {
		return storeroomweizhi;
	}

	public void setStoreroomweizhi(String storeroomweizhi) {
		this.storeroomweizhi = storeroomweizhi;
	}

	public String getBookedition() {
		return bookedition;
	}

	public void setBookedition(String bookedition) {
		this.bookedition = bookedition;
	}

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}
	
}
