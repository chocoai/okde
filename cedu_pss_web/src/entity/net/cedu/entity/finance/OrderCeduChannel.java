package net.cedu.entity.finance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.cedu.common.Constants;

import org.apache.struts2.json.annotations.JSON;

/**
 * 合作方返款单
 * 
 * @author Sauntor
 *
 */
@Entity
@Table(name="tb_e_order_cedu_channel")
public class OrderCeduChannel implements Serializable {
	private static final long serialVersionUID = -7509892738261211250L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; //ID
	
	@Column(name="code")
	private String code; //返款单号
	
	@Column(name="channel_id")
	private int channelId; //主合作方ID
	
	@Column(name="channel_type")
	private int channelType; //主合作方类型
	
	@Column(name="branch_id")
	private int branchId; //学习中心Id
	
	@Column(name="amount_to_pay")
	private BigDecimal amountToPay; //应返款额
	
	@Column(name="amount_paied")
	private BigDecimal amountPaied; //实返款额
	
	@Column(name="adjust_paied")
	private BigDecimal adjustPaied; //调整款额
	
	@Column(name="pay_date")
	private Date payDate; //汇款日期
	
	@Column(name="channel_rebate_time_id",columnDefinition="int default '0'")
	private int channelRebateTimeId; //招生返款期Id
	
	@Column(name="status")
	private int status; //状态  Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO＝15表示fankuan成功
	
	@Column(name="types")
	private int types; //回退状态       1.已提交    2.已回退
	
	@Column(name="roll_back_reason")
	private String rollBackReason; //回退原因
	
	@Column(name="adjust_reason")
	private String adjustReason; //调整原因
	
	@Column(name="doc_no")
	private String docNo; //批件号
	
	@Column(name="police_status")
	private int policeStatus; //返款政策类型
	
	@Column(name="minor_channel_ids")
	private String minorChannelIds; //次合作方IDs字符串
	
	@Column(name="minor_channel_id")
	private int minorChannelId; //次合作方ID
	
	@Column(name="minor_channel_type")
	private int minorChannelType; //次合作方类型
	
	@Column(name = "delete_flag")
	private int deleteFlag;  //删除标记
	
	@Column(name = "creator_id")
	private int creatorId;  //创建人
	
	@Column(name = "created_time")
	private Date createdTime;  //创建时间
	
	@Column(name = "updater_id")
	private int updaterId;  //最后修改人
	
	@Column(name = "updated_time")
	private Date updatedTime;  //最后修改时间
	
	/* 用于界面显示的字段 */
	@Transient
	private transient String statusName; //状态名称
	@Transient
	private transient String channelName;
	@Transient
	private transient String channelTypeName;
	@Transient
	private transient String branchName;
	
	@Transient
	transient private Map<String,String> paramsString=new HashMap<String, String>();
	
	@Transient
	transient private Map<String,Integer> paramsInt=new HashMap<String, Integer>();


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public BigDecimal getAmountToPay() {
		return amountToPay;
	}

	public void setAmountToPay(BigDecimal amountToPay) {
		this.amountToPay = amountToPay;
	}

	public BigDecimal getAmountPaied() {
		return amountPaied;
	}

	public void setAmountPaied(BigDecimal amountPaied) {
		this.amountPaied = amountPaied;
	}

	@JSON(format=Constants.DATE_FORMAT)
	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getPoliceStatus() {
		return policeStatus;
	}

	public void setPoliceStatus(int policeStatus) {
		this.policeStatus = policeStatus;
	}

	public int getMinorChannelId() {
		return minorChannelId;
	}

	public void setMinorChannelId(int minorChannelId) {
		this.minorChannelId = minorChannelId;
	}

	public int getMinorChannelType() {
		return minorChannelType;
	}

	public void setMinorChannelType(int minorChannelType) {
		this.minorChannelType = minorChannelType;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public String getMinorChannelIds() {
		return minorChannelIds;
	}

	public void setMinorChannelIds(String minorChannelIds) {
		this.minorChannelIds = minorChannelIds;
	}

	public int getChannelRebateTimeId() {
		return channelRebateTimeId;
	}

	public void setChannelRebateTimeId(int channelRebateTimeId) {
		this.channelRebateTimeId = channelRebateTimeId;
	}

	public String getRollBackReason() {
		return rollBackReason;
	}

	public void setRollBackReason(String rollBackReason) {
		this.rollBackReason = rollBackReason;
	}

	public Map<String, Integer> getParamsInt() {
		if(paramsInt==null)
		{
			paramsInt=new HashMap<String, Integer>();
		}
		return paramsInt;
	}

	public void setParamsInt(Map<String, Integer> paramsInt) {
		this.paramsInt = paramsInt;
	}

	public Map<String, String> getParamsString()
	{
		if(paramsString==null)
		{
			paramsString=new HashMap<String, String>();
		}
		return paramsString;
	}

	public void setParamsString(Map<String, String> paramsString) {
		this.paramsString = paramsString;
	}

	public BigDecimal getAdjustPaied() {
		return adjustPaied;
	}

	public void setAdjustPaied(BigDecimal adjustPaied) {
		this.adjustPaied = adjustPaied;
	}
	
}
