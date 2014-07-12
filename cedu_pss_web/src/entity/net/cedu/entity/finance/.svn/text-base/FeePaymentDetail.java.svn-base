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

import net.cedu.entity.crm.Student;

import org.apache.struts2.json.annotations.JSON;

/**
 * 缴费单明细
 * 
 * @author gaolei
 * 
 */
@Entity
@Table(name = "tb_e_fee_payment_detail")
public class FeePaymentDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	// 主建ID
	private int id;

	@Column(name = "code")
	private String code; // 编号
	
	@Column(name = "student_id")// 学生ID
	private int studentId;

	@Column(name = "fee_payment_id")
	// 缴费单ID
	private int feePaymentId;
	
	@Column(name = "pending_payment_id")
	// 待缴费单ID
	private int pendingPaymentId;

	@Column(name = "policy_fee_detail_id")
	// 收费政策ID
	private int policyFeeDetailId;

	@Column(name = "policy_standard_id")
	// 收费政策标准明细ID
	private int policyStandardId;

	@Column(name = "fee_subject_id")
	// 费用项目ID
	private int feeSubjectId;

	@Column(name = "batch_id")
	// 缴费期ID(与政策标准冗余)
	private int batchId;

	@Column(name = "credit_point_to_pay")
	// 应缴学分数(与政策标准冗余)
	private int creditPointToPay;

	@Column(name = "credit_point_paied")
	//实缴学分数(与政策标准冗余)
	private int creditPointPaied;

	@Column(name = "money_to_pay")
	// 应缴金额(与政策标准冗余)
	private double moneyToPay;

	@Column(name = "discount_policy_detail_id")
	private String discountPolicyDetailId; // 优惠政策明细ID(_多_)

	@Column(name = "discount_amount")
	// 总优惠金额
	private double discountAmount;
	
	@Column(name = "academy_discount")
	private double academyDiscount;//院校优惠金额
	
	@Column(name = "academy_cedu_discount")
	private double academyCeduDiscount; //院校_弘成共同优惠金额
	
	@Column(name = "cedu_discount")
	private double ceduDiscount;//弘成优惠金额
	
	@Column(name = "branch_discount")
	private double branchDiscount;//中心优惠金额
	
	@Column(name = "channel_discount")
	private double channelDiscount;//渠道优惠金额

	@Column(name = "amount_paied")
	// 实缴金额
	private double amountPaied;
	
	@Column(name = "recharge_amount")
	private double rechargeAmount; //使用充值金额
	

	@Column(name = "pay_branch_cedu")
	// 汇款总部金额
	private double payBranchCedu;

	@Column(name = "pay_cedu_academy")
	// 院校打款金额
	private double payCeduAcademy;

	@Column(name = "pay_academy_cedu")
	// 院校返款金额
	private double payAcademyCedu;

	@Column(name = "payment_by_channel")
	// 渠道返款金额
	private double paymentByChannel;

	@Column(name = "status")
	// 缴费单明细状态
	private int status;
	
	@Column(name = "rebate_status")
	private int rebateStatus;// 缴费单明细返款状态

	@Column(name = "types")
	// 缴费单明细类型 
	private int types;

	@Column(name = "is_from_channel")
	// 是否渠道返款
	private int isFromChannel;

	@Column(name = "is_supper")
	// 是否母明细
	private int isSupper;

	@Column(name = "supper_id")
	// 母明细ID
	private int supperId;

	@Column(name = "order_branch_cedu_id")
	// 汇款总部单ID
	private int orderBranchCeduId;

	@Column(name = "order_cedu_academy_id")
	// 院校打款单ID
	private int orderCeduAcademyId;

	@Column(name = "order_academy_cedu_id")
	// 院校返款单ID
	private int orderAcademyCeduId;

	@Column(name = "order_cedu_channel_id")
	// 渠道返款单ID
	private int orderCeduChannelId;

	@Column(name = "is_invoiced")
	// 是否已开发票(冗余)
	private int isInvoiced;
	
	@Column(name = "channel_policy_standard_id",columnDefinition="int default '0'")
	// 招生返款政策标准明细ID
	private int channelPolicyStandardId;
	
	@Column(name="channel_rebate_time_id",columnDefinition="int default '0'")
	private int channelRebateTimeId; //招生返款期Id
	
	@Column(name = "channel_rebate_count",columnDefinition="int default '0'")
	private int channelRebateCount; //本次招生返款总人数
	
	@Column(name = "academy_policy_standard_id",columnDefinition="int default '0'")
	private int academyPolicyStandardId;// 院校返款政策标准明细ID
	
	@Column(name = "academy_rebate_count",columnDefinition="int default '0'")
	private int academyRebateCount;// 本次院校返款实际匹配政策人数
	
	@Column(name = "academy_rebate_benci_count",columnDefinition="int default '0'")
	private int academyRebateBenciCount;// 本次院校返款计算应返的人数
	
	@Column(name = "is_academy_rebate_year_count",columnDefinition="int default '0'")
	private int isAcademyRebateYearCount;// 是否已经院校年度返款结算      0未结算     1已结算
	
	@Column(name="academy_rebate_add_money",columnDefinition="decimal default '0'")
	private BigDecimal academyRebateAddMoney; //院校返款追加总金额
	
	@Column(name="academy_year_rebate_add_money",columnDefinition="decimal default '0'")
	private BigDecimal academyYearRebateAddMoney; //院校年度追加金额(这个金额包含在院校返款追加总金额里面)
	
	@Column(name="academy_year_rebate_id",columnDefinition="int default '0'")
	private int academyYearRebateId; //院校年度返款单Id
	
	@Column(name = "cedu_confirm_time")
	private Date ceduConfirmTime; // 总部确认时间
	
	@Column(name = "cedu_confirm_id")
	private int ceduConfirmId; // 总部确认人

	@Column(name = "delete_flag")
	private int deleteFlag; // 删除标记

	@Column(name = "creator_id")
	private int creatorId; // 创建人

	@Column(name = "created_time")
	private Date createdTime; // 创建时间

	@Column(name = "updater_id")
	private int updaterId; // 最后修改人

	@Column(name = "updated_time")
	private Date updatedTime; // 最后修改时间
	
	//*******退费新字段*********//
	@Column(name = "refund_amount")
	private double refundAmount; //退费金额
	@Column(name = "branch_account")
	private double branchAccount; //中心账户金额
	@Column(name = "cedu_account")
	private double ceduAccount; //总部账户金额
	@Column(name = "academy_account")
	private double academyAccount; //院校账户金额
	@Column(name = "channel_account")
	private double channelAccount; //合作方账户金额
	@Column(name = "refund_lock")
	private int refundLock; //退费锁定
	
	@Column(name = "refund_base")
	private double refundBase; //退费基数（退费金额/退费基数=比例系数）r
	@Column(name = "supper_status")
	private int supperStatus; //原单据状态（退费单明细记录原单据状态用）

	@Transient
	transient private String paymentSubjectName; // 缴费科目名称
	@Transient
	transient private int feeWayId; // 缴费方式ID

	@Transient
	transient private String studentName; // 学生姓名

	@Transient
	transient private String schoolName; // 院校名称

	@Transient
	transient private String academyenrollbatchName; // 院校批次
	
	@Transient
	transient private String globalBatchName;// 全局批次名称

	@Transient
	transient private String levelName; // 层次

	@Transient
	transient private String majorName; // 专业
	
	@Transient
	transient private String paymentCode; // 缴费单ID
	
	@Transient
	transient private String branchName; // 学习中心名称
	
	@Transient
	transient private BigDecimal moneyToCedu; // 院校应返款金额
	
	@Transient
	transient private int rebateWay; //返款方式
	@Transient
	transient private double rebateValue; //返款值（金额/比例）
	
	@Transient
	transient private double moneyToChannel; //应返款合作方金额
	
	@Transient
	transient private String statusName;//状态名称 
	
	@Transient
	transient private String rebateStatusName;//招生返款状态名称  
	
	@Transient
	transient private int startStatusId;//状态开始Id
	
	@Transient
	transient private int endStatusId;//状态结束Id
	@Transient
	transient private String invoiceCodes;//发票单号集合
	
	@Transient
	transient private Student student; // 学生信息
	
	@Transient
	transient private FeePayment feePayment; // 缴费单信息
	
	@Transient
	transient private double jiaofeiValue; //缴费金额(实缴金额+充值金额)
	
	@Transient
	transient private String channelName;//合作方名称
	
	@Transient
	transient private int isStartCourse;//是否开课
	
	@Transient
	transient private int isChannelTypeChecked;//是否招生途径
	
	@Transient
	transient private int enrollmentSource;//招生途径
	
	@Transient
	transient private String channelRebateTimeName;//招生返款期名称
	
	@Transient
	transient private BigDecimal YearAddMoneyShow; // 院校年度返款追加金额
	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public int getPolicyFeeDetailId() {
		return policyFeeDetailId;
	}

	public void setPolicyFeeDetailId(int policyFeeDetailId) {
		this.policyFeeDetailId = policyFeeDetailId;
	}

	public int getPolicyStandardId() {
		return policyStandardId;
	}

	public void setPolicyStandardId(int policyStandardId) {
		this.policyStandardId = policyStandardId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getCreditPointToPay() {
		return creditPointToPay;
	}

	public void setCreditPointToPay(int creditPointToPay) {
		this.creditPointToPay = creditPointToPay;
	}

	public int getCreditPointPaied() {
		return creditPointPaied;
	}

	public void setCreditPointPaied(int creditPointPaied) {
		this.creditPointPaied = creditPointPaied;
	}

	public double getMoneyToPay() {
		return moneyToPay;
	}

	public void setMoneyToPay(double moneyToPay) {
		this.moneyToPay = moneyToPay;
	}

	public String getDiscountPolicyDetailId() {
		return discountPolicyDetailId;
	}

	public void setDiscountPolicyDetailId(String discountPolicyDetailId) {
		this.discountPolicyDetailId = discountPolicyDetailId;
	}

	public double getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(double discountAmount) {
		this.discountAmount = discountAmount;
	}

	public double getAmountPaied() {
		return amountPaied;
	}

	public void setAmountPaied(double amountPaied) {
		this.amountPaied = amountPaied;
	}

	public double getPayBranchCedu() {
		return payBranchCedu;
	}

	public void setPayBranchCedu(double payBranchCedu) {
		this.payBranchCedu = payBranchCedu;
	}

	public double getPayCeduAcademy() {
		return payCeduAcademy;
	}

	public void setPayCeduAcademy(double payCeduAcademy) {
		this.payCeduAcademy = payCeduAcademy;
	}

	public double getPayAcademyCedu() {
		return payAcademyCedu;
	}

	public void setPayAcademyCedu(double payAcademyCedu) {
		this.payAcademyCedu = payAcademyCedu;
	}

	public double getPaymentByChannel() {
		return paymentByChannel;
	}

	public void setPaymentByChannel(double paymentByChannel) {
		this.paymentByChannel = paymentByChannel;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public int getIsFromChannel() {
		return isFromChannel;
	}

	public void setIsFromChannel(int isFromChannel) {
		this.isFromChannel = isFromChannel;
	}

	public int getIsSupper() {
		return isSupper;
	}

	public void setIsSupper(int isSupper) {
		this.isSupper = isSupper;
	}

	public int getSupperId() {
		return supperId;
	}

	public void setSupperId(int supperId) {
		this.supperId = supperId;
	}

	public int getOrderBranchCeduId() {
		return orderBranchCeduId;
	}

	public void setOrderBranchCeduId(int orderBranchCeduId) {
		this.orderBranchCeduId = orderBranchCeduId;
	}

	public int getOrderCeduAcademyId() {
		return orderCeduAcademyId;
	}

	public void setOrderCeduAcademyId(int orderCeduAcademyId) {
		this.orderCeduAcademyId = orderCeduAcademyId;
	}

	public int getOrderAcademyCeduId() {
		return orderAcademyCeduId;
	}

	public void setOrderAcademyCeduId(int orderAcademyCeduId) {
		this.orderAcademyCeduId = orderAcademyCeduId;
	}

	public int getOrderCeduChannelId() {
		return orderCeduChannelId;
	}

	public void setOrderCeduChannelId(int orderCeduChannelId) {
		this.orderCeduChannelId = orderCeduChannelId;
	}

	public int getIsInvoiced() {
		return isInvoiced;
	}

	public void setIsInvoiced(int isInvoiced) {
		this.isInvoiced = isInvoiced;
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

	@JSON(format="yyyy-MM-dd")
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

	public String getPaymentSubjectName() {
		return paymentSubjectName;
	}

	public void setPaymentSubjectName(String paymentSubjectName) {
		this.paymentSubjectName = paymentSubjectName;
	}

	public int getFeeWayId() {
		return feeWayId;
	}

	public void setFeeWayId(int feeWayId) {
		this.feeWayId = feeWayId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getAcademyenrollbatchName() {
		return academyenrollbatchName;
	}

	public void setAcademyenrollbatchName(String academyenrollbatchName) {
		this.academyenrollbatchName = academyenrollbatchName;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getPendingPaymentId() {
		return pendingPaymentId;
	}

	public void setPendingPaymentId(int pendingPaymentId) {
		this.pendingPaymentId = pendingPaymentId;
	}

	public BigDecimal getMoneyToCedu() {
		return moneyToCedu;
	}

	public void setMoneyToCedu(BigDecimal moneyToCedu) {
		this.moneyToCedu = moneyToCedu;
	}

	public int getRebateWay() {
		return rebateWay;
	}

	public void setRebateWay(int rebateWay) {
		this.rebateWay = rebateWay;
	}

	public double getRebateValue() {
		return rebateValue;
	}

	public void setRebateValue(double rebateValue) {
		this.rebateValue = rebateValue;
	}

	public double getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(double rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public double getMoneyToChannel() {
		return moneyToChannel;
	}

	public void setMoneyToChannel(double moneyToChannel) {
		this.moneyToChannel = moneyToChannel;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public double getAcademyCeduDiscount() {
		return academyCeduDiscount;
	}

	public void setAcademyCeduDiscount(double academyCeduDiscount) {
		this.academyCeduDiscount = academyCeduDiscount;
	}

	public double getAcademyDiscount() {
		return academyDiscount;
	}

	public void setAcademyDiscount(double academyDiscount) {
		this.academyDiscount = academyDiscount;
	}

	public double getCeduDiscount() {
		return ceduDiscount;
	}

	public void setCeduDiscount(double ceduDiscount) {
		this.ceduDiscount = ceduDiscount;
	}

	public double getBranchDiscount() {
		return branchDiscount;
	}

	public void setBranchDiscount(double branchDiscount) {
		this.branchDiscount = branchDiscount;
	}

	public double getChannelDiscount() {
		return channelDiscount;
	}

	public void setChannelDiscount(double channelDiscount) {
		this.channelDiscount = channelDiscount;
	}

	public int getStartStatusId() {
		return startStatusId;
	}

	public void setStartStatusId(int startStatusId) {
		this.startStatusId = startStatusId;
	}

	public int getEndStatusId() {
		return endStatusId;
	}

	public void setEndStatusId(int endStatusId) {
		this.endStatusId = endStatusId;
	}

	public double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(double refundAmount) {
		this.refundAmount = refundAmount;
	}

	public double getBranchAccount() {
		return branchAccount;
	}

	public void setBranchAccount(double branchAccount) {
		this.branchAccount = branchAccount;
	}

	public double getCeduAccount() {
		return ceduAccount;
	}

	public void setCeduAccount(double ceduAccount) {
		this.ceduAccount = ceduAccount;
	}

	public double getAcademyAccount() {
		return academyAccount;
	}

	public void setAcademyAccount(double academyAccount) {
		this.academyAccount = academyAccount;
	}

	public double getChannelAccount() {
		return channelAccount;
	}

	public void setChannelAccount(double channelAccount) {
		this.channelAccount = channelAccount;
	}

	public int getRefundLock() {
		return refundLock;
	}

	public void setRefundLock(int refundLock) {
		this.refundLock = refundLock;
	}

	

	public double getRefundBase() {
		return refundBase;
	}

	public void setRefundBase(double refundBase) {
		this.refundBase = refundBase;
	}

	public int getSupperStatus() {
		return supperStatus;
	}

	public void setSupperStatus(int supperStatus) {
		this.supperStatus = supperStatus;
	}

	public String getInvoiceCodes() {
		return invoiceCodes;
	}

	public void setInvoiceCodes(String invoiceCodes) {
		this.invoiceCodes = invoiceCodes;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public int getRebateStatus() {
		return rebateStatus;
	}

	public void setRebateStatus(int rebateStatus) {
		this.rebateStatus = rebateStatus;
	}

	public Date getCeduConfirmTime() {
		return ceduConfirmTime;
	}

	public void setCeduConfirmTime(Date ceduConfirmTime) {
		this.ceduConfirmTime = ceduConfirmTime;
	}

	public int getCeduConfirmId() {
		return ceduConfirmId;
	}

	public void setCeduConfirmId(int ceduConfirmId) {
		this.ceduConfirmId = ceduConfirmId;
	}

	public String getRebateStatusName() {
		return rebateStatusName;
	}

	public void setRebateStatusName(String rebateStatusName) {
		this.rebateStatusName = rebateStatusName;
	}

	public int getChannelPolicyStandardId() {
		return channelPolicyStandardId;
	}

	public void setChannelPolicyStandardId(int channelPolicyStandardId) {
		this.channelPolicyStandardId = channelPolicyStandardId;
	}

	public double getJiaofeiValue() {
		return jiaofeiValue;
	}

	public void setJiaofeiValue(double jiaofeiValue) {
		this.jiaofeiValue = jiaofeiValue;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public int getIsStartCourse() {
		return isStartCourse;
	}

	public void setIsStartCourse(int isStartCourse) {
		this.isStartCourse = isStartCourse;
	}

	public int getIsChannelTypeChecked() {
		return isChannelTypeChecked;
	}

	public void setIsChannelTypeChecked(int isChannelTypeChecked) {
		this.isChannelTypeChecked = isChannelTypeChecked;
	}

	public int getAcademyPolicyStandardId() {
		return academyPolicyStandardId;
	}

	public void setAcademyPolicyStandardId(int academyPolicyStandardId) {
		this.academyPolicyStandardId = academyPolicyStandardId;
	}

	public Map<String, String> getParamsString() {
		if(paramsString==null)
		{
			paramsString=new HashMap<String, String>();
		}
		return paramsString;
	}

	public void setParamsString(Map<String, String> paramsString) {
		this.paramsString = paramsString;
	}

	public Map<String, Integer> getParamsInt() {
		return paramsInt;
	}

	public void setParamsInt(Map<String, Integer> paramsInt) {
		this.paramsInt = paramsInt;
	}

	public int getEnrollmentSource() {
		return enrollmentSource;
	}

	public void setEnrollmentSource(int enrollmentSource) {
		this.enrollmentSource = enrollmentSource;
	}

	public int getChannelRebateTimeId() {
		return channelRebateTimeId;
	}

	public void setChannelRebateTimeId(int channelRebateTimeId) {
		this.channelRebateTimeId = channelRebateTimeId;
	}

	public int getChannelRebateCount() {
		return channelRebateCount;
	}

	public void setChannelRebateCount(int channelRebateCount) {
		this.channelRebateCount = channelRebateCount;
	}

	public String getGlobalBatchName() {
		return globalBatchName;
	}

	public void setGlobalBatchName(String globalBatchName) {
		this.globalBatchName = globalBatchName;
	}

	public String getChannelRebateTimeName() {
		return channelRebateTimeName;
	}

	public void setChannelRebateTimeName(String channelRebateTimeName) {
		this.channelRebateTimeName = channelRebateTimeName;
	}

	public int getAcademyRebateCount() {
		return academyRebateCount;
	}

	public void setAcademyRebateCount(int academyRebateCount) {
		this.academyRebateCount = academyRebateCount;
	}

	public int getAcademyRebateBenciCount() {
		return academyRebateBenciCount;
	}

	public void setAcademyRebateBenciCount(int academyRebateBenciCount) {
		this.academyRebateBenciCount = academyRebateBenciCount;
	}

	public int getIsAcademyRebateYearCount() {
		return isAcademyRebateYearCount;
	}

	public void setIsAcademyRebateYearCount(int isAcademyRebateYearCount) {
		this.isAcademyRebateYearCount = isAcademyRebateYearCount;
	}

	public BigDecimal getAcademyRebateAddMoney() {
		return academyRebateAddMoney;
	}

	public void setAcademyRebateAddMoney(BigDecimal academyRebateAddMoney) {
		this.academyRebateAddMoney = academyRebateAddMoney;
	}

	public BigDecimal getYearAddMoneyShow() {
		return YearAddMoneyShow;
	}

	public void setYearAddMoneyShow(BigDecimal yearAddMoneyShow) {
		YearAddMoneyShow = yearAddMoneyShow;
	}

	public BigDecimal getAcademyYearRebateAddMoney() {
		return academyYearRebateAddMoney;
	}

	public void setAcademyYearRebateAddMoney(BigDecimal academyYearRebateAddMoney) {
		this.academyYearRebateAddMoney = academyYearRebateAddMoney;
	}

	public int getAcademyYearRebateId() {
		return academyYearRebateId;
	}

	public void setAcademyYearRebateId(int academyYearRebateId) {
		this.academyYearRebateId = academyYearRebateId;
	}

	

	
	
}
