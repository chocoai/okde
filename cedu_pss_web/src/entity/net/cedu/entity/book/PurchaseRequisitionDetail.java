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
 * 中心申购单明细
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_purchase_requisition_detail")
public class PurchaseRequisitionDetail implements Serializable {
 
	private static final long serialVersionUID = -4084828598346896538L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id; //主键id
	
	@Column(name="purchase_requisition_id")
	private int purchaseRequisitionId; //中心申购单ID
	
	@Column(name="book_id")
	private int bookId; //教材ID
 
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

	
	@Column(name="required_amount")
	private int requiredAmount;//申购数量
	
	@Column(name="ordered_amount") 
	private int orderedAmount; //已另购的数量
	
	@Column(name="has_distributed")
	private int hasDistributed; //已派发数量
	
	@Column(name="price")
	private int price; //定价
	
	@Column(name="purchase_price")
	private int purchasePrice; //采购价格
	
	@Column(name="purchase_time")
	private Date purchaseTime; //申购时间
	
	@Column(name="requisitioner")
	private int requisitioner; //申购人
	
	@Column(name="status")
	private int status;//状态


	@Column(name="delete_flag")
    private int deleteFlag; 	//删除标记
	
	@Column(name="creator_id")
    private int creatorId;     //创建人
	
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

	public int getPurchaseRequisitionId() {
		return purchaseRequisitionId;
	}

	public void setPurchaseRequisitionId(int purchaseRequisitionId) {
		this.purchaseRequisitionId = purchaseRequisitionId;
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

	public int getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(int requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	public int getOrderedAmount() {
		return orderedAmount;
	}

	public void setOrderedAmount(int orderedAmount) {
		this.orderedAmount = orderedAmount;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Date purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public int getRequisitioner() {
		return requisitioner;
	}

	public void setRequisitioner(int requisitioner) {
		this.requisitioner = requisitioner;
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

	public int getHasDistributed() {
		return hasDistributed;
	}

	public void setHasDistributed(int hasDistributed) {
		this.hasDistributed = hasDistributed;
	}

 
 
	
	 
	
}
