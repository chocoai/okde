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
 * 中心申购单人员明细
 * @author XFY
 *
 */
@Entity
@Table(name="tb_e_purchase_requisitioner_detail")
public class PurchaseRequisitionerDetail implements Serializable {
 
	private static final long serialVersionUID = 194221534639789137L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;//主键id
	
	@Column(name="purchase_requisition_id")
	private int purchaseRequisitionId;//中心申购单明细ID
	
	@Column(name="book_id")
	private int bookId;//教材ID
	
	@Transient
	transient private String bookname; //教材名称;
	
	@Transient
	transient private String bookedition; //教材版次;
	
	@Transient
	transient private double bookprice; //教材定价;
	
	@Column(name="student_id")
	private int studentId;//学生ID
			
	@Transient
	transient private String studentname; //学生名称;
	
	@Transient
	transient private String studentcode; //学生编号;
	
	@Transient
	transient private String studentstatus; //学生状态;
	
	@Transient
	transient private String academyname; //院校名称;
	
	@Transient
	transient private int academyId; //院校名称;
	
	@Column(name="batch_id")
	private int batchId;//批次ID
	
	@Transient
	transient private String batchname; //院校批次名称;
	
	@Column(name="level_id")
	private int levelId;//层次ID
	
	@Transient
	transient private String levelname; //院校层次名称;
	
	@Column(name="major_id")
	private int majorId;//专业ID
	
	@Transient
	transient private String majorname; //院校专业名称;
	
	@Column(name="required_amount")
	private int requiredAmount;//申购数量
	
	@Transient
	transient private double avg; //院校总数;
	
	@Column(name="status")
	private int status;//领用状态
	
	@Column(name="is_charging")
	private int isCharging;//是否扣费
	
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public int getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(int requiredAmount) {
		this.requiredAmount = requiredAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getIsCharging() {
		return isCharging;
	}

	public void setIsCharging(int isCharging) {
		this.isCharging = isCharging;
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

 

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
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

	public double getBookprice() {
		return bookprice;
	}

	public void setBookprice(double bookprice) {
		this.bookprice = bookprice;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public String getAcademyname() {
		return academyname;
	}

	public void setAcademyname(String academyname) {
		this.academyname = academyname;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getBatchname() {
		return batchname;
	}

	public void setBatchname(String batchname) {
		this.batchname = batchname;
	}

	public String getLevelname() {
		return levelname;
	}

	public void setLevelname(String levelname) {
		this.levelname = levelname;
	}

	public String getMajorname() {
		return majorname;
	}

	public void setMajorname(String majorname) {
		this.majorname = majorname;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(int updaterId) {
		this.updaterId = updaterId;
	}

	public String getStudentcode() {
		return studentcode;
	}

	public void setStudentcode(String studentcode) {
		this.studentcode = studentcode;
	}

	public String getStudentstatus() {
		return studentstatus;
	}

	public void setStudentstatus(String studentstatus) {
		this.studentstatus = studentstatus;
	}
	
	
}
