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
 * 总部发货单明细
 * 
 * @作者： 杨阳
 * @作成时间：2012-2-22 下午04:22:44
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 */
@Entity
@Table(name = "tb_e_invoice_detail")
public class InvoiceDetail implements Serializable {

	private static final long serialVersionUID = 7690343998906540942L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;// 主键id

	@Column(name = "invoice_id")
	private int invoiceId;// 订购单ID

	@Column(name = "branch_id")
	private int branchId;// 学习中心ID

	@Column(name = "book_id")
	private int bookId;// 教材ID

	@Transient
	transient private String bookcode; // 教材编号;

	@Transient
	transient private String bookname; // 教材名称;

	@Transient
	transient private String bookedition; // 教材版次;

	@Transient
	transient private String bookisbn; // 教材isbn;

	@Transient
	transient private String bookpress; // 教材出版社;

	@Transient
	transient private String bookauthor; // 教材作者;

	@Transient
	transient private double bookprice; // 教材定价;

	@Column(name = "booked_total")
	private int bookedTotal;// 订购数量

	@Column(name = "sended_total")
	private int sendedTotal;// 发货数量

	@Column(name = "booking_price")
	private double bookingPrice;// 订购单价

	@Column(name = "purchaser")
	private int purchaser;// 订购人

	@Column(name = "order_time")
	private Date orderTime;// 订购时间

	@Column(name = "status")
	private int status;// 状态

	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创始人

	@Column(name = "created_time")
	private Date createdTime; // 创建时间

	@Column(name = "updater_id")
	private int updater_id; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(int invoiceId) {
		this.invoiceId = invoiceId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
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

	public int getBookedTotal() {
		return bookedTotal;
	}

	public void setBookedTotal(int bookedTotal) {
		this.bookedTotal = bookedTotal;
	}

}
