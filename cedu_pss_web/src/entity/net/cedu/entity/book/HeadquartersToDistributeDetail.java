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
 * 总部派发单明细
 * @author YY
 *
 */
@Entity
@Table(name="tb_e_Headquarters_to_distribute_detail")
public class HeadquartersToDistributeDetail implements Serializable {
 
	private static final long serialVersionUID = -5253724590242875792L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="distribute_id")
	private int distributeId;//派发单Id
	
	
	@Column(name="branch_id")
	private int branchId;//受派发中心ID
	
	@Transient
	transient private String branchName; //受派发中心名称
 
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
	
	@Transient
	transient private double bookpurchasePrice; // 教材采购价;
 
	@Column(name="amount")
	private double amount;//金额
	
	@Column(name="distribute_number")
	private int distributeNumber;//派发数量
	
	@Column(name="distributeer")
	private int  distributeer;//派发人
	
	@Column(name="distribute_time")
	private Date distributetime;//派发时间
 
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

	public int getDistributeId() {
		return distributeId;
	}

	public void setDistributeId(int distributeId) {
		this.distributeId = distributeId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
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

	public double getBookpurchasePrice() {
		return bookpurchasePrice;
	}

	public void setBookpurchasePrice(double bookpurchasePrice) {
		this.bookpurchasePrice = bookpurchasePrice;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getDistributeNumber() {
		return distributeNumber;
	}

	public void setDistributeNumber(int distributeNumber) {
		this.distributeNumber = distributeNumber;
	}

	public int getDistributeer() {
		return distributeer;
	}

	public void setDistributeer(int distributeer) {
		this.distributeer = distributeer;
	}

	public Date getDistributetime() {
		return distributetime;
	}

	public void setDistributetime(Date distributetime) {
		this.distributetime = distributetime;
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
	
}