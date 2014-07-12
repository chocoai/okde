package net.cedu.entity.meterial;

 
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 中心申请单
 * @author YY
 *  
 */
@Entity
@Table(name = "tb_e_meterial_application", catalog = "cedu_master")
public class MeterialApplication implements java.io.Serializable {
 
	private static final long serialVersionUID = -1604364025972271626L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id" )
	private int id;            //主键id
	
	@Column(name = "code" )
	private String code;            //申请单编号
	
	@Column(name = "applicant")
	private int applicant;      //申请人   
	
	@Transient
	transient private String applicantname; //申请人姓名
	
	@Column(name = "amount")
	private Double amount;          //申请总金额
	
	@Column(name = "submit_time")
	private Date submitTime=new Date();   //申请时间
	
	@Column(name = "status")
	private int status;         //状态
	
	@Column(name = "delete_flag")
	private int deleteFlag;     //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;     //创建人
	
	@Column(name = "created_time" )
	private Date createdTime=new Date();  //创建时间
	
	@Column(name = "updater_id")
	private int updaterId;     //最后修改人

	@Column(name = "updated_time" )
	private Date updatedTime;  //最后修改时间

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

	public int getApplicant() {
		return applicant;
	}

	public void setApplicant(int applicant) {
		this.applicant = applicant;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
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

	public String getApplicantname() {
		return applicantname;
	}

	public void setApplicantname(String applicantname) {
		this.applicantname = applicantname;
	}
	

 

	 


	 

	 

	
	 

	 

 
 
 
 

 
}