package net.cedu.action.finance.refund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加退费单
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonAddRefundPaymentAction extends BaseAction
{
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务接口
	private List<FeePaymentDetail> feepDetailList=new ArrayList<FeePaymentDetail>();//缴费明细集合
	
	//******************************学生充值账户相关*******************************//
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;//学生充值账户业务接口
	
	private String studentAccount;//学生单个费用科目账户剩余金额
	private String studentAllAccount;//学生总账户剩余金额
	
	
	//获取学生充值消费记录和缴费记录修改参数
	private int studentId;//学生Id
	private int feeSubjectId;//费用科目Id
	
	
	//******************************添加学生退费单*******************************//
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	
	//页面参数
	private FeePayment feePayment=new FeePayment();//缴费单实体
	private int indexcount;//判断重复添加的参数
	private String feeSubjectIds;//需要退费的费用科目ids字符串集合
	private String feePaymentDetailIds;//需要退费的缴费单明细Ids字符串集合
	//返回参数
	private boolean replayadd=false;//判断重复添加
	private boolean isback=false;//判断添加是否成功
	
	
	
	/**
	 * 获取学生历史缴费记录和充值消费记录
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "show_student_account_and_feepayment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String showsaafa() throws Exception 
	{
		if(feeSubjectId!=0 && studentId!=0)
		{
			//studentAccount=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementBySidAndFeeSubject(studentId, feeSubjectId).toString();
			//2012-01-09 重构
			studentAccount=this.studentAccountAmountManagementBiz.findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(studentId, feeSubjectId).toString();
			if(this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(studentId)!=null)
			{
				studentAllAccount=this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(studentId).getAccountBalance().toString();
			}
			else
			{
				studentAllAccount="0";
			}
			feepDetailList=feePaymentDetailBiz.findFeePaymentDetailListByStudentIdFeeSubjectId(studentId, feeSubjectId,Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加学生退费单
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_student_refund_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String adrotherpaymentrewrite() throws Exception 
	{
		if(feeSubjectIds!=null || feePaymentDetailIds!=null)
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

				List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//退费单明细集合
				List<FeePaymentDetail> historyfpdList=new ArrayList<FeePaymentDetail>();//历史缴费单明细集合
				List<StudentAccountAmountManagement> stuaaList=new ArrayList<StudentAccountAmountManagement>();//充值账户退款集合
				//充值金额退费
				if(feeSubjectIds!=null && !feeSubjectIds.equals("") && feeSubjectIds.length()>0)
				{
					String[] feeids=feeSubjectIds.split(",");
					for(int i=0;i<feeids.length;i++)
					{
						//退费单明细  充值账户不放入退费单明细里    总账户必须加上这部分钱
//						String rechargeAmount="moneyToAccount";//充值账户退学生金额					
//						FeePaymentDetail feedetail=new FeePaymentDetail();
//						rechargeAmount+=feeids[i];
//						feedetail.setRechargeAmount(0-Double.valueOf(request.getParameter(rechargeAmount)));
//						//feedetail.setAmountPaied(0-Double.valueOf(request.getParameter(rechargeAmount)));
//						feedetail.setMoneyToPay(0-Double.valueOf(request.getParameter(rechargeAmount)));
//						feedetail.setFeeSubjectId(Integer.valueOf(feeids[i]));
//						feePaymentDetailList.add(feedetail);
						
						//充值账户
						String rechargeAmount="moneyToAccount";//充值账户退学生金额					
						rechargeAmount+=feeids[i];
						// 学生账户明细
						StudentAccountAmountManagement studentAccountAmountManagement = new StudentAccountAmountManagement();
						
						studentAccountAmountManagement.setAccountMoney(new BigDecimal(request.getParameter(rechargeAmount)));
						studentAccountAmountManagement.setCreatedTime(DateUtil
								.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						studentAccountAmountManagement.setCreatorId(super.getUser().getUserId());
						studentAccountAmountManagement.setDeleteFlag(Constants.DELETE_FALSE);
					
						studentAccountAmountManagement.setFeeSubject(Integer.valueOf(feeids[i]));
						studentAccountAmountManagement
								.setTypes(Constants.STATUS_APPLY_CONSUMPTION);
						stuaaList.add(studentAccountAmountManagement);
					}
				}
				//缴费单退费
				if(feePaymentDetailIds!=null && !feePaymentDetailIds.equals("") && feePaymentDetailIds.length()>0)
				{
					String[] feeids=feePaymentDetailIds.split(",");
					for(int i=0;i<feeids.length;i++)
					{
						
						String amountPaied="amountPaied";//缴费单退学生金额
						String payAcademyCedu="payAcademyCedu";//总部退学生金额
						String payCeduAcademy="payCeduAcademy";//院校退学生金额
						String paymentByChannel="paymentByChannel";//和作方退总部金额
						String payBranchCedu="payBranchCedu";//中心退学生金额
						//退费单明细
						FeePaymentDetail feedetail=new FeePaymentDetail();
						
						amountPaied+=feeids[i];
						feedetail.setAmountPaied(0-Double.valueOf(request.getParameter(amountPaied)));
						payAcademyCedu+=feeids[i];
						feedetail.setCeduAccount(0-Double.valueOf(request.getParameter(payAcademyCedu)));
						payCeduAcademy+=feeids[i];
						feedetail.setAcademyAccount(0-Double.valueOf(request.getParameter(payCeduAcademy)));
						paymentByChannel+=feeids[i];
						feedetail.setChannelAccount(0-Double.valueOf(request.getParameter(paymentByChannel)));
						payBranchCedu+=feeids[i];
						feedetail.setBranchAccount(0-Double.valueOf(request.getParameter(payBranchCedu)));
						feedetail.setMoneyToPay(0-Double.valueOf(request.getParameter(amountPaied)));
						if(this.feePaymentDetailBiz.findById(Integer.valueOf(feeids[i]))!=null)
						{
							feedetail.setFeeSubjectId(this.feePaymentDetailBiz.findById(Integer.valueOf(feeids[i])).getFeeSubjectId());
							feedetail.setSupperId(Integer.valueOf(feeids[i]));
							//feedetail.setIsSupper(this.feePaymentDetailBiz.findById(Integer.valueOf(feeids[i])).getStatus());
							historyfpdList.add(this.feePaymentDetailBiz.findById(Integer.valueOf(feeids[i])));
						}
						feePaymentDetailList.add(feedetail);
						
					}
				}	
				isback=this.feePaymentBiz.addRefundPayment(feePayment, feePaymentDetailList, stuaaList,historyfpdList);
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

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
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

	public String getFeePaymentDetailIds() {
		return feePaymentDetailIds;
	}

	public void setFeePaymentDetailIds(String feePaymentDetailIds) {
		this.feePaymentDetailIds = feePaymentDetailIds;
	}

	public boolean isReplayadd() {
		return replayadd;
	}

	public void setReplayadd(boolean replayadd) {
		this.replayadd = replayadd;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
	
	
	
	
}
