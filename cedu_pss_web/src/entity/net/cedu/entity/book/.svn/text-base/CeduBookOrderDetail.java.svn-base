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
 * 订购单明细
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_cedu_book_order_detail")
public class CeduBookOrderDetail implements Serializable {
 
	private static final long serialVersionUID = 1383182002951354705L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="branch_id")
	private int branchId;//学习中心ID
	
	@Column(name="order_id")
	private int orderId;//订购单ID	
	
	@Transient
	transient private String suppilername; //书商名称;
	
	@Transient
	transient private String ordercode; //教材编号;
	
	@Column(name="book_id")
	private int bookId;//教材ID
	
	@Transient
	transient private String bookcode; //教材编号;
	
	@Transient
	transient private String bookname; //教材名称;
	
	@Transient
	transient private String bookedition; //教材版次;
	
	@Transient
	transient private String bookisbn; //教材isbn;
	
	@Transient
	transient private String bookpress; //教材出版社;
	
	@Transient
	transient private String bookauthor; //教材作者;
	
	@Transient
	transient private double bookprice; //教材定价;
 
	
	@Column(name="booked_total")
	private int bookedTotal;//订购数量
	
	@Transient
	transient private int beginTotal; //预发货数量
	
	@Column(name="sended_total")
	private int sendedTotal;//发货数量
	
	@Column(name="booking_price")
	private double bookingPrice;//订购单价
	
	@Column(name="purchaser")
	private int purchaser;//订购人
	
	@Column(name="order_time")
	private Date orderTime;//订购时间
	
	@Column(name="status")
	private int status;//状态
 
	@Transient
	transient private String statusname; //状态显示
	
	@Column(name="delete_flag")
    private int deleteFlag; 	//删除标记
	
	@Column(name="creator_id")
    private int creatorId;     //创始人
	
	@Column(name="created_time")
    private Date createdTime;  //创建时间
	
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

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookcode() {
		return bookcode;
	}

	public void setBookcode(String bookcode) {
		this.bookcode = bookcode;
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

	public String getBookisbn() {
		return bookisbn;
	}

	public void setBookisbn(String bookisbn) {
		this.bookisbn = bookisbn;
	}

	public String getBookpress() {
		return bookpress;
	}

	public void setBookpress(String bookpress) {
		this.bookpress = bookpress;
	}

	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}

	

	public int getBookedTotal() {
		return bookedTotal;
	}

	public void setBookedTotal(int bookedTotal) {
		this.bookedTotal = bookedTotal;
	}

	public int getSendedTotal() {
		return sendedTotal;
	}

	public void setSendedTotal(int sendedTotal) {
		this.sendedTotal = sendedTotal;
	}

	public double getBookingPrice() {
		return bookingPrice;
	}

	public void setBookingPrice(double bookingPrice) {
		this.bookingPrice = bookingPrice;
	}

	public int getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(int purchaser) {
		this.purchaser = purchaser;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public int getBeginTotal() {
		return beginTotal;
	}

	public void setBeginTotal(int beginTotal) {
		this.beginTotal = beginTotal;
	}

	public String getSuppilername() {
		return suppilername;
	}

	public void setSuppilername(String suppilername) {
		this.suppilername = suppilername;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	
	
}
