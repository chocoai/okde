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
 *移库
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_stock_transfer") 
public class StockTransfer implements Serializable {
 
	private static final long serialVersionUID = -1495955893544265600L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="from_id")
	private int fromId;//移出库房ID
	
	@Transient 
	transient private String fromname; //移出库房名称
	
	@Transient 
	transient private String fromweizhi; //移出库房位置
	
	@Column(name="to_id")
	private int toId;//移入库房ID
	
	@Transient
	transient private String toname; //移入库房名称
	
	@Transient 
	transient private String toweizhi; //移入库房位置
	
	@Column(name="book_id")
	private int bookId;//教材ID
	
	@Transient
	transient private String bookname;//教材名称
	
	@Transient 
	transient private String bookedition;  //版次
	
	@Column(name="amount")
	private int amount;//移库数量
	
	@Column(name="delete_flag")
    private int deleteFlag; 	//删除标记
	
	@Column(name="creator_id")
    private int creatorId;     //创始人
	
	@Column(name="created_time")
    private Date createdTime=new Date();  //创建时间
	
	@Column(name="updater_id")
    private int updaterId;     //最后修改人
	
	@Column(name="updated_time")
    private Date updatedTime;  //最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFromId() {
		return fromId;
	}

	public void setFromId(int fromId) {
		this.fromId = fromId;
	}

	public int getToId() {
		return toId;
	}

	public void setToId(int toId) {
		this.toId = toId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
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

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getBookedition() {
		return bookedition;
	}

	public void setBookedition(String bookedition) {
		this.bookedition = bookedition;
	}

	public String getFromweizhi() {
		return fromweizhi;
	}

	public void setFromweizhi(String fromweizhi) {
		this.fromweizhi = fromweizhi;
	}

	public String getToweizhi() {
		return toweizhi;
	}

	public void setToweizhi(String toweizhi) {
		this.toweizhi = toweizhi;
	}
	
	
}
