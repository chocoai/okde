package net.cedu.action.finance.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PendingFeePayment;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加缴费单
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonSchoolPaymentAction extends BaseAction 
{
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务接口
	private List<FeePaymentDetail> feepDetailList=new ArrayList<FeePaymentDetail>();//缴费明细集合
	
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;//待缴费单业务接口
	private List<PendingFeePayment> pendingList=new ArrayList<PendingFeePayment>();//待缴费集合
	
	
	//获取学生待缴费单信息、缴费记录
	private int studentId;//学生Id
	private int feeSubjectId;//费用科目Id
	
	//******************************学生确认缴费时，为了防止账面金额变动重新计算优惠*******************************//
	@Autowired
	private DiscountApplicationBiz discountApplicationBiz;//优惠券业务接口
	//使用优惠券参数
	private String reducemoney;//优惠金额
	private String discountpolicyIds;//优惠券Id
	private String reduceaftermoney;//优惠后金额
	private String allmoney;//应缴总金额
	
	
	//******************************学生充值账户相关*******************************//
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;//学生充值账户业务接口
	
	private String studentAccount;//学生单个费用科目账户剩余金额
	private String studentAllAccount;//学生总账户剩余金额
	
	//******************************学生缴费相关*******************************//
	@Autowired
	private PaymentBiz paymentBiz;//缴费单业务接口
	@Autowired
	private StudentBiz studentBiz;//学生业务接口
	//页面参数
	private FeePayment feePayment=new FeePayment();//缴费单实体
	private int isFee;//是否收款
	private int indexcount;//判断重复添加的参数
	private String feeSubjectIds;//需要缴费的费用科目ids字符串集合
	//返回参数
	private boolean replayadd=false;//判断重复添加
	private int count;//判断是否成功
	
	//******************************设置学生强制收费*******************************//
	private int isForceFee;//是否强制收费
	
	
	//刷新学生新设置政策时待缴费单重新生成
	@Autowired
	private BuildCodeBiz buildCodeBiz;// code生成业务接口;
	private boolean isfail=false;
	
	/**
	 * 获取学生历史缴费记录和待缴费单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "show_student_school_feepayment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String showssf() throws Exception 
	{
		if(studentId>0 && feeSubjectId>0)
		{
			//方法重构2012-01-09  缴费增加退费的显示
			FeePaymentDetail fpd=new FeePaymentDetail();
			fpd.setStudentId(studentId);
			fpd.setFeeSubjectId(feeSubjectId);
			fpd.setStartStatusId(Constants.PAYMENT_STATUS_YI_KAI_DAN);
			fpd.setEndStatusId(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
			feepDetailList=feePaymentDetailBiz.findFeePaymentDetailListByStudentIdFeeSubjectIdStartStatusIdEndStatusId(fpd);
			pendingList=pendingFeePaymentBiz.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(studentId, feeSubjectId);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取学生相关缴费科目的账户余额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "show_student_using_account_rewrite_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findacclunt() throws Exception 
	{
		if(feeSubjectId!=0 && studentId!=0)
		{
			//studentAccount=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementBySidAndFeeSubject(studentId, feeSubjectId).toString();
			//2012-01-09    重构
			studentAccount=this.studentAccountAmountManagementBiz.findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(studentId, feeSubjectId).toString();
			if(this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(studentId)!=null)
			{
				studentAllAccount=this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(studentId).getAccountBalance().toString();
			}
		}

		return SUCCESS;
	}
	
	/**
	 * 学生确认缴费时，为了防止账面金额变动重新计算优惠
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "using_student_feesubject_discount_application_rewrite_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String usingDicountCount() throws Exception 
	{
		if(allmoney!=null && !allmoney.equals("") && discountpolicyIds!=null && !discountpolicyIds.equals(""))
		{
			reduceaftermoney=(new BigDecimal(allmoney).subtract(new BigDecimal(reducemoney))).toString();
			if(discountpolicyIds.length()>0)
			{
				String[] ids=discountpolicyIds.split(",");
				for(int i=0;i<ids.length;i++)
				{
					if(this.discountApplicationBiz.findDiscountApplicationById(Integer.valueOf(ids[i]))!=null)
					{
						DiscountApplication discountapp= this.discountApplicationBiz.findDiscountApplicationById(Integer.valueOf(ids[i]));
						if(discountapp!=null)
						{
							String nowmoney="0";//当前优惠金额
							if(discountapp.getDiscountWay()==Constants.MONEY_FORM_RATIO)
							{
								if((discountapp.getMoney().compareTo(new BigDecimal(0)))>0)
								{
									nowmoney=(new BigDecimal(allmoney).multiply((discountapp.getMoney().divide(new BigDecimal("100"),2)))).toString();				
								}
							}
							else if(discountapp.getDiscountWay()==Constants.MONEY_FORM_AMOUNT)
							{
								nowmoney=discountapp.getMoney().toString();
							}
							reducemoney=(new BigDecimal(reducemoney).add(new BigDecimal(nowmoney)).setScale(2)).toString();//总优惠金额
							reduceaftermoney=(new BigDecimal(reduceaftermoney).subtract(new BigDecimal(nowmoney)).setScale(2)).toString();//总优惠后的金额
							if((new BigDecimal(reduceaftermoney).compareTo(new BigDecimal(0)))<0)
							{
								reducemoney=(new BigDecimal(reducemoney).add(new BigDecimal(reduceaftermoney)).setScale(2)).toString();//总优惠金额
								reduceaftermoney=(new BigDecimal(0)).toString();//总优惠后的金额
								break;
							}
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 添加学生的除去报名费和测试费之外的其他任何费用2(重构之后的方法)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_other_fee_payment_rewrite_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String adrotherpaymentrewrite() throws Exception 
	{
		Student student=this.studentBiz.findStudentById(feePayment.getStudentId());
		if(student!=null && feeSubjectIds!=null && !feeSubjectIds.equals("") && feeSubjectIds.length()>0)
		{
			if(indexcount>0)
			{
				replayadd=true;
				return SUCCESS;
			}
			else
			{
				feePayment.setCreatorId(super.getUser().getUserId());
				feePayment.setUpdaterId(super.getUser().getUserId());	
				List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();
				String[] feeids=feeSubjectIds.split(",");
				for(int i=0;i<feeids.length;i++)
				{
					String discountamount="moneyDisPut";//总优惠金额
					String amountPaied="moneyToPut";//实缴金额
					String discountIds="discountIds";//优惠政策明细Id字符串集合
					String rechargeAmount="moneyHasPut";//充值金额
					String moneyForPut="moneyForPut";//账面金额（应缴金额）
					String moneyAllPut="moneyAllPut";//参考金额
					String cedumoneyDisPut="cedumoneyDisPut";//弘成优惠（补录历史数据）
					FeePaymentDetail feedetail=new FeePaymentDetail();
					//feedetail.setPolicyStandardId(pend.getFeeStandardId());
					discountamount+=feeids[i];
					feedetail.setDiscountAmount(Double.valueOf(request.getParameter(discountamount)));
					amountPaied+=feeids[i];
					feedetail.setAmountPaied(Double.valueOf(request.getParameter(amountPaied)));
					discountIds+=feeids[i];
					feedetail.setDiscountPolicyDetailId(request.getParameter(discountIds));	
					rechargeAmount+=feeids[i];
					feedetail.setRechargeAmount(Double.valueOf(request.getParameter(rechargeAmount)));
					moneyForPut+=feeids[i];
					feedetail.setMoneyToPay(Double.valueOf(request.getParameter(moneyForPut)));//账面金额
					moneyAllPut+=feeids[i];
					feedetail.setRebateValue(Double.valueOf(request.getParameter(moneyAllPut)));//参考金额
					feedetail.setFeeSubjectId(Integer.valueOf(feeids[i]));
					
					cedumoneyDisPut+=feeids[i];
					feedetail.setCeduDiscount(Double.valueOf(request.getParameter(cedumoneyDisPut)));//弘成优惠（补录历史数据）
					//feedetail.setPendingPaymentId(pend.getId());
	
					feePaymentDetailList.add(feedetail);
				}
					
				count=this.paymentBiz.addSchoolPaymentRewrite(feePaymentDetailList, feePayment, isFee);
			}
		}
		return SUCCESS;
	}

	/**
	 * 设置学生强制收费
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_force_fee_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String usffee() throws Exception 
	{
		if(studentId!=0)
		{
			Student student=this.studentBiz.findStudentById(studentId);
			if(student!=null)
			{
				student.setIsForceFee(isForceFee);
				student.setUpdaterId(super.getUser().getUserId());
				student.setModifiedTime(new Date());
				this.studentBiz.updateStudentInfo(student);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 刷新学生新政策下的待缴费单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_penging_fee_payments_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String uspfee() throws Exception 
	{
		if(studentId!=0)
		{
			List<PendingFeePayment> pendingList=this.pendingFeePaymentBiz
				.generatePendingFeePaymentListByStudentIdFeeSubjectId(studentId, -1);
			if (pendingList != null && pendingList.size() > 0) {
				for (PendingFeePayment pending : pendingList) 
				{
					pending.setCode(buildCodeBiz.getCodes(
							CodeEnum.PendingFeePaymentTB.getCode(),
							CodeEnum.PendingFeePaymentPrefix.getCode()));
					pending.setCreatorId(super.getUser().getUserId());
					pending.setUpdaterId(super.getUser().getUserId());
				}
				isfail = this.pendingFeePaymentBiz
						.addBatchPendingFeePayment(pendingList);
			}
		}
		return SUCCESS;
	}
	
	public List<FeePaymentDetail> getFeepDetailList() {
		return feepDetailList;
	}

	public void setFeepDetailList(List<FeePaymentDetail> feepDetailList) {
		this.feepDetailList = feepDetailList;
	}

	public List<PendingFeePayment> getPendingList() {
		return pendingList;
	}

	public void setPendingList(List<PendingFeePayment> pendingList) {
		this.pendingList = pendingList;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public String getStudentAccount() {
		return studentAccount;
	}

	public void setStudentAccount(String studentAccount) {
		this.studentAccount = studentAccount;
	}

	public String getStudentAllAccount() {
		return studentAllAccount;
	}

	public void setStudentAllAccount(String studentAllAccount) {
		this.studentAllAccount = studentAllAccount;
	}

	public String getReducemoney() {
		return reducemoney;
	}

	public void setReducemoney(String reducemoney) {
		this.reducemoney = reducemoney;
	}

	public String getDiscountpolicyIds() {
		return discountpolicyIds;
	}

	public void setDiscountpolicyIds(String discountpolicyIds) {
		this.discountpolicyIds = discountpolicyIds;
	}

	public String getReduceaftermoney() {
		return reduceaftermoney;
	}

	public void setReduceaftermoney(String reduceaftermoney) {
		this.reduceaftermoney = reduceaftermoney;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public int getIsFee() {
		return isFee;
	}

	public void setIsFee(int isFee) {
		this.isFee = isFee;
	}

	public int getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}

	public String getFeeSubjectIds() {
		return feeSubjectIds;
	}

	public void setFeeSubjectIds(String feeSubjectIds) {
		this.feeSubjectIds = feeSubjectIds;
	}

	public boolean isReplayadd() {
		return replayadd;
	}

	public void setReplayadd(boolean replayadd) {
		this.replayadd = replayadd;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIsForceFee() {
		return isForceFee;
	}

	public void setIsForceFee(int isForceFee) {
		this.isForceFee = isForceFee;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
	
}
