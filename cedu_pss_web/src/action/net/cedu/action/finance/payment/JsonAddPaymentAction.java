package net.cedu.action.finance.payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.DiscountApplicationBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.DiscountApplication;
import net.cedu.entity.enrollment.StudentDiscountPolicy;
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
 * @author lixiaojun
 * 
 */
@ParentPackage("json-default")
public class JsonAddPaymentAction extends BaseAction 
{
	
	@Autowired
	private PaymentBiz paymentBiz;//缴费单业务接口
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务接口
	
	@Autowired
	private DiscountApplicationBiz discountApplicationBiz;//优惠券业务接口
	private List<DiscountApplication> applist=new ArrayList<DiscountApplication>();//优惠券集合
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//优惠标准业务层接口
	
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准明细业务接口
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;//待缴费单业务接口
	private List<PendingFeePayment> pendinglist=new ArrayList<PendingFeePayment>();//待缴费集合
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务接口
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	@Autowired
	private StudentBiz studentBiz;//学生业务接口
	private Student stu=new Student();//学生实体
	
	
	
	//获取可以使用的优惠卷参数
	private int studentId;//学生Id
	private int feeSubjectId;//费用科目Id
	private int feePaymentId;//缴费次数
	
	private int pendingFeePaymentId;//待缴费Id(用来查询费用科目Id和缴费次数Id)
	
	//使用优惠券参数
	private String reducemoney;//优惠金额
	private int discountpolicyId;//优惠券Id
	private String reduceaftermoney;//优惠后金额
	private String allmoney;//应缴总金额
	
	//添加测试费相关参数
	@Autowired
	private ReceiptBiz receiptBiz;//收据业务接口
	
	private FeePayment feePayment=new FeePayment();//缴费单实体
	private FeePaymentDetail feePaymentDetail=new FeePaymentDetail();//缴费单明细实体
	private int isFee;//是否收款
	private String code;//缴费单号
	private boolean replayadd=false;//判断重复添加
	private boolean barcodeadd=false;//判断收据号是否被使用
	private boolean isfail=false;//返回参数
	private int indexcount;//返回参数
	
	private boolean isback=false;//报名成功
	private boolean notfeepolice=false;//无相关政策
	private boolean notceshipolice=false;//无测试费政策
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	private Academy academy=new Academy();//院校实体
	private int academyId;//院校Id
	private int count;//返回参数
	
	
	//******************************学生充值账户相关*******************************//
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;//学生充值账户业务接口
	
	private String studentAccount;//学生单个费用科目账户剩余金额
	private String studentAllAccount;//学生总账户剩余金额
	
	
	private String useaccount;//要使用充值账户的金额（其他两个参数盗用上面的）
	
	//打印相关的缴费单数据
	private double baomingfei=0;
	private double ceshifei=0;
	private Student studentprint=new Student();//学生实体（返回页面参数，为打印服务）
	private String namefee;//收款人
	
	/**
	 * 获取学生可以使用的优惠卷1
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_feesubject_discount_application_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findDicountCount1() throws Exception 
	{
		applist=this.discountApplicationBiz.findDiscountApplicationListByStudentIdFeeSubjectIdFeePaymentId(studentId, feeSubjectId, feePaymentId);
		return SUCCESS;
	}
	
	/**
	 * 获取学生可以使用的优惠卷2
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_can_using_application_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findDicountCount2() throws Exception 
	{
		PendingFeePayment pendingFeePayment=this.pendingFeePaymentBiz.findPendingFeePaymentById(pendingFeePaymentId);
		if(pendingFeePayment!=null && pendingFeePayment.getFeeStandardId()!=0)
		{
			if(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId())!=null && this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getPolicyFeeDetailId())!=null)
			{
				feePaymentId=this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getFeeBatchId();
				feeSubjectId=this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId();
				applist=this.discountApplicationBiz.findDiscountApplicationListByStudentIdFeeSubjectIdFeePaymentId(studentId, feeSubjectId, feePaymentId);
			}
			
		}
		return SUCCESS;
	}
	
	/**
	 * 获取学生可以使用的优惠卷3（学费等其他多次缴费获取优惠卷与缴费次数无关）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_can_using_application2_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findDicountCount3() throws Exception 
	{
		if(feeSubjectId>0 && studentId > 0)
		{
			applist=this.discountApplicationBiz.findDiscountApplicationListByStudentIdFeeSubjectIdFeePaymentId(studentId, feeSubjectId, feePaymentId);
		}
		return SUCCESS;
	}
	
	/**
	 * 使用优惠卷
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "using_student_feesubject_discount_application_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String usingDicountCount() throws Exception 
	{
//		if(reduceaftermoney!=null && !reduceaftermoney.equals("") && discountpolicyId!=0)
//		{
//			if(this.discountApplicationBiz.findDiscountApplicationById(discountpolicyId)!=null)
//			{
//				StudentDiscountPolicy studentDiscountPolicy=studentDiscountPolicyBiz.findStudentDiscountPolicyById(this.discountApplicationBiz.findDiscountApplicationById(discountpolicyId).getPolicyStandardId());
//				if(studentDiscountPolicy!=null)
//				{
//					long timeLong = 0;
//					int dayNum = 0;
//					String delta="0";//渐变差额
//					String nowmoney="0";//当前优惠金额
//					if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_GRADIENT)
//					{
//						timeLong =DateUtil.getNowTimestamp("yyyy-MM-dd").getTime()-studentDiscountPolicy.getUseBeginDate().getTime();
//						dayNum = (int) (((timeLong / 1000) / 3600) / 24);
//						delta=(new BigDecimal(dayNum).multiply(new BigDecimal(studentDiscountPolicy.getDelta()))).toString();
//					}
//					if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_RATIO)
//					{
//						nowmoney=(new BigDecimal(reduceaftermoney).multiply((studentDiscountPolicy.getMoney().add(new BigDecimal(delta))).divide(new BigDecimal("100"),2))).toString();
//					}
//					else if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
//					{
//						nowmoney=(studentDiscountPolicy.getMoney().add(new BigDecimal(delta))).toString();
//					}
//					if((new BigDecimal(nowmoney).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
//					{
//						reducemoney=(new BigDecimal(reducemoney).add(new BigDecimal(nowmoney))).toString();//总优惠金额
//						reduceaftermoney=(new BigDecimal(reduceaftermoney).subtract(new BigDecimal(nowmoney))).toString();//总优惠后的金额
//						if((new BigDecimal(reduceaftermoney).compareTo(new BigDecimal(0)))<0)
//						{
//							reducemoney=(new BigDecimal(reducemoney).add(new BigDecimal(reduceaftermoney))).toString();//总优惠金额
//							reduceaftermoney=(new BigDecimal(0)).toString();//总优惠后的金额
//						}
//					}
//				}
//			}
//		}
		if(allmoney!=null && !allmoney.equals("") && discountpolicyId!=0)
		{
			reduceaftermoney=(new BigDecimal(allmoney).subtract(new BigDecimal(reducemoney))).toString();
			if(this.discountApplicationBiz.findDiscountApplicationById(discountpolicyId)!=null)
			{
				DiscountApplication discountapp= this.discountApplicationBiz.findDiscountApplicationById(discountpolicyId);
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
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 使用优惠卷2（缴纳除去报名费和测试费之外其他费用所使用的优惠券）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "using_student_feesubject_discount_application_two_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String usingDicountCount2() throws Exception 
	{
		
		if(allmoney!=null && !allmoney.equals("") && discountpolicyId!=0)
		{
			reduceaftermoney=(new BigDecimal(allmoney).subtract(new BigDecimal(reducemoney))).toString();
			if(this.discountApplicationBiz.findDiscountApplicationById(discountpolicyId)!=null)
			{
				StudentDiscountPolicy studentDiscountPolicy=studentDiscountPolicyBiz.findStudentDiscountPolicyById(this.discountApplicationBiz.findDiscountApplicationById(discountpolicyId).getPolicyStandardId());
				if(studentDiscountPolicy!=null)
				{
					long timeLong = 0;
					int dayNum = 0;
					String delta="0";//渐变差额
					String nowmoney="0";//当前优惠金额
					if(studentDiscountPolicy.getMutable()==Constants.MONEY_FORM_GRADIENT)
					{
						timeLong =DateUtil.getNowTimestamp("yyyy-MM-dd").getTime()-studentDiscountPolicy.getUseBeginDate().getTime();
						dayNum = (int) (((timeLong / 1000) / 3600) / 24);
						delta=(new BigDecimal(dayNum).multiply(new BigDecimal(studentDiscountPolicy.getDelta()))).toString();
					}
					if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_RATIO)
					{
						nowmoney=(new BigDecimal(reduceaftermoney).multiply((studentDiscountPolicy.getMoney().add(new BigDecimal(delta))).divide(new BigDecimal("100"),2))).toString();
					}
					else if(studentDiscountPolicy.getDiscountWayId()==Constants.MONEY_FORM_AMOUNT)
					{
						nowmoney=(studentDiscountPolicy.getMoney().add(new BigDecimal(delta))).toString();
					}
					if((new BigDecimal(nowmoney).compareTo(new BigDecimal(0)))>0)//和0比较大于返回1小于返回-1等于返回0
					{
						reducemoney=(new BigDecimal(reducemoney).add(new BigDecimal(nowmoney))).toString();//总优惠金额
						reduceaftermoney=(new BigDecimal(reduceaftermoney).subtract(new BigDecimal(nowmoney))).toString();//总优惠后的金额
						if((new BigDecimal(reduceaftermoney).compareTo(new BigDecimal(0)))<0)
						{
							reducemoney=(new BigDecimal(reducemoney).add(new BigDecimal(reduceaftermoney))).toString();//总优惠金额
							reduceaftermoney=(new BigDecimal(0)).toString();//总优惠后的金额
						}
					}
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 添加学生的测试费
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_test_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String dtestpayment() throws Exception 
	{
		int count=this.feePaymentDetailBiz.findFeePaymentCountByStudentIdFeeStandardId(feePayment.getStudentId(),feePaymentDetail.getPolicyStandardId());
		if(count>0)
		{
			replayadd=true;
			return SUCCESS;
		}
		if(feePayment.getBarCode()!=null && !feePayment.getBarCode().equals("") && feePayment.getBarCode()!="")
		{
			//System.out.println(this.receiptBiz.updateReceiptStatusByCode(feePayment.getBarCode()));
			if(this.receiptBiz.updateReceiptStatusByCode(feePayment.getBarCode())!=1)
			{
				barcodeadd=true;
				return SUCCESS;
			}
			else
			{
				feePayment.setCreatorId(super.getUser().getUserId());
				feePayment.setUpdaterId(super.getUser().getUserId());
				feePaymentDetail.setCreatorId(super.getUser().getUserId());
				feePaymentDetail.setUpdaterId(super.getUser().getUserId());
				isfail=this.paymentBiz.addFeePaymentDetailFeePaymentPending(feePaymentDetail, feePayment, isFee);
				
//				String a =""+isFee;
//				String b=feePayment.getCode()+feePayment.getStudentId()+feePayment.getFeeWayId()+feePayment.getIsPrint()+feePayment.getBarCode();
//				String c=feePaymentDetail.getFeeSubjectId()+""+feePaymentDetail.getBatchId()+feePaymentDetail.getPolicyStandardId();
//				String d=feePaymentDetail.getDiscountAmount()+feePaymentDetail.getDiscountPolicyDetailId();
			}
			
		}
		else
		{
			feePayment.setCreatorId(super.getUser().getUserId());
			feePayment.setUpdaterId(super.getUser().getUserId());
			feePaymentDetail.setCreatorId(super.getUser().getUserId());
			feePaymentDetail.setUpdaterId(super.getUser().getUserId());
			isfail=this.paymentBiz.addFeePaymentDetailFeePaymentPending(feePaymentDetail, feePayment, isFee);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加学生的报名费和测试费
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_test_apply_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String adrtestpayment() throws Exception 
	{
		Student student=this.studentBiz.findStudentById(feePayment.getStudentId());
		student.setIsExemption(stu.getIsExemption());
		student.setNotApplyFee(stu.getNotApplyFee());
		if(student!=null)
		{
			if(student.getStatus()>=Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI)
			{
				replayadd=true;
				return SUCCESS;
			}
			if(student.getNotApplyFee()==Constants.STUDENT_IS_EXEMPTION_TRUE && student.getIsExemption() == Constants.STUDENT_IS_EXEMPTION_TRUE)
			{
				student.setStatus(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI);
				student.setRegistrationTime(new Date());
				studentBiz.updateStudentInfo(student);
				isback=true;
				return SUCCESS;
			}
			
			
			//先判断要缴哪些费用
			List<PendingFeePayment> pendinglist=new ArrayList<PendingFeePayment>();
			//查询报名费和测试费的待缴费单
			if (student.getNotApplyFee()== Constants.STUDENT_IS_EXEMPTION_FALSE)
			{
				List<PendingFeePayment> pendingname=this.pendingFeePaymentBiz.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(student.getId(), FeeSubjectEnum.RegistrationFee.value());
				
				if(pendingname!=null && pendingname.size()>0)
				{
					for(PendingFeePayment pend:pendingname)
					{
						pendinglist.add(pend);
					}
				}
				else
				{
					notfeepolice=true;
					return SUCCESS;
				}
			}	
			if(student.getIsExemption()==Constants.STUDENT_IS_EXEMPTION_FALSE)//是否是免试生
			{
				//测试费
				List<PendingFeePayment> pendingtest=this.pendingFeePaymentBiz.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(student.getId(), FeeSubjectEnum.TestFee.value());
				if(pendingtest!=null && pendingtest.size()>0)
				{
					for(PendingFeePayment pend:pendingtest)
					{
						pendinglist.add(pend);
					}
				}
				else
				{
					notceshipolice=true;
					return SUCCESS;
				}
			}
			if(pendinglist!=null && pendinglist.size()>0 )
			{
				//打印数据
				studentprint=student;
				namefee=super.getUser().getFullName();
				
				int counts=this.feePaymentDetailBiz.findFeePaymentCountByStudentIdFeeStandardId(feePayment.getStudentId(),pendinglist.get(0).getFeeStandardId());
				if(counts>0)
				{
					replayadd=true;
					return SUCCESS;
				}
				else
				{
					feePayment.setCreatorId(super.getUser().getUserId());
					feePayment.setUpdaterId(super.getUser().getUserId());
					feePayment.setCode(buildCodeBiz.getCodes(CodeEnum.Payment.getCode(),CodeEnum.PaymentPrefix.getCode()));		
					//String a =""+isFee;
					//String b=feePayment.getCode()+feePayment.getStudentId()+feePayment.getFeeWayId()+feePayment.getIsPrint()+feePayment.getBarCode();
					List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();
					for(PendingFeePayment pend:pendinglist)
					{
						String discountamount="discountAmount";
						String amountPaied="amountPaied";
						String discountIds="discountIds";
						FeePaymentDetail feedetail=new FeePaymentDetail();
						feedetail.setPolicyStandardId(pend.getFeeStandardId());
						discountamount+=pend.getId();
						feedetail.setDiscountAmount(Double.valueOf(request.getParameter(discountamount)));
						amountPaied+=pend.getId();
						feedetail.setAmountPaied(Double.valueOf(request.getParameter(amountPaied)));
						discountIds+=pend.getId();
						feedetail.setDiscountPolicyDetailId(request.getParameter(discountIds));	
						feedetail.setPendingPaymentId(pend.getId());
						
						//打印缴费数据
						if(pend.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
						{
							baomingfei=feedetail.getAmountPaied();						
						}
						else if(pend.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST)
						{
							ceshifei=feedetail.getAmountPaied();
						}
						
						feePaymentDetailList.add(feedetail);
					}
					if(isFee==1)
					{
						count=this.paymentBiz.addAllFeePaymentDetailFeePaymentPending(feePaymentDetailList, feePayment, isFee,true);
					}
					else
					{
						count=this.paymentBiz.addAllFeePaymentDetailFeePaymentPending(feePaymentDetailList, feePayment, isFee,false);
					}
				}
			}
			else
			{
				notfeepolice=true;
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获取学生需要交纳的费用（除去报名费和测试费）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_all_need_feepayment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findNeedDicountCount() throws Exception 
	{
		//院校
		academy=this.academyBiz.findAcademyById(academyId);
		//其他费
		 pendinglist=this.pendingFeePaymentBiz.findPendingFeePaymentListByStudentIdFeeSubjectId(studentId, FeeSubjectEnum.OtherFee.value());
		return SUCCESS;
	}
	
	/**
	 * 添加学生的除去报名费和测试费之外的其他任何费用
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_other_fee_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String adrotherpayment() throws Exception 
	{
		Student student=this.studentBiz.findStudentById(feePayment.getStudentId());
		if(student!=null)
		{
			//先判断要缴哪些费用
			List<PendingFeePayment> pendinglists=this.pendingFeePaymentBiz.findPendingFeePaymentListByStudentIdFeeSubjectId(feePayment.getStudentId(), FeeSubjectEnum.OtherFee.value());
			//查询报名费和测试费的待缴费单
			
			if(pendinglists!=null && pendinglists.size()>0)
			{
				//  这个判断有问题  需修改
				
				if(indexcount>0)
				{
					replayadd=true;
					return SUCCESS;
				}
				else
				{
					feePayment.setCreatorId(super.getUser().getUserId());
					feePayment.setUpdaterId(super.getUser().getUserId());		
					//String a =""+isFee;
					//String b=feePayment.getCode()+feePayment.getStudentId()+feePayment.getFeeWayId()+feePayment.getIsPrint()+feePayment.getBarCode();
					List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();
					for(PendingFeePayment pend:pendinglists)
					{
						String discountamount="discountAmount";
						String amountPaied="paymentfee";
						String discountIds="discountIds";
						String rechargeAmount="rechargeAmount";
						FeePaymentDetail feedetail=new FeePaymentDetail();
						feedetail.setPolicyStandardId(pend.getFeeStandardId());
						discountamount+=pend.getId();
						feedetail.setDiscountAmount(Double.valueOf(request.getParameter(discountamount)));
						amountPaied+=pend.getId();
						feedetail.setAmountPaied(Double.valueOf(request.getParameter(amountPaied)));
						discountIds+=pend.getId();
						feedetail.setDiscountPolicyDetailId(request.getParameter(discountIds));	
						rechargeAmount+=pend.getId();
						feedetail.setRechargeAmount(Double.valueOf(request.getParameter(rechargeAmount)));
						feedetail.setPendingPaymentId(pend.getId());
						
						
						feePaymentDetailList.add(feedetail);
					}
					
					count=this.paymentBiz.addAllOtherFeePaymentDetailFeePaymentPending(feePaymentDetailList, feePayment, isFee);
				}
			}
			else
			{
				replayadd=true;
				return SUCCESS;
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 获取学生相关缴费科目的账户余额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "show_student_using_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findacclunt() throws Exception 
	{
		PendingFeePayment pendingFeePayment=this.pendingFeePaymentBiz.findPendingFeePaymentById(pendingFeePaymentId);
		if(pendingFeePayment!=null && pendingFeePayment.getFeeStandardId()!=0)
		{
			if(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId())!=null && this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getPolicyFeeDetailId())!=null)
			{
				feeSubjectId=this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId();
				if(feeSubjectId!=0 && pendingFeePayment.getStudentId()!=0)
				{
					//studentAccount=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementBySidAndFeeSubject(pendingFeePayment.getStudentId(), feeSubjectId).toString();
					//2012-01-09  重构
					studentAccount=this.studentAccountAmountManagementBiz.findStudentAccountFeesubjectBalanceByStudentIdFeeSubjectId(pendingFeePayment.getStudentId(), feeSubjectId).toString();
					
					if(this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(pendingFeePayment.getStudentId())!=null)
					{
						studentAllAccount=this.studentAccountManagementBiz.findStudentAccountManagementByStudentId(pendingFeePayment.getStudentId()).getAccountBalance().toString();
					}
				}
				
			}
			
		}
		return SUCCESS;
	}
	
	/**
	 * 使用学生相关缴费科目的账户余额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "show_student_used_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String usingacclunt() throws Exception 
	{
		if(studentAccount!=null && studentAllAccount!=null && useaccount!=null)
		{
			studentAccount=(new BigDecimal(studentAllAccount).subtract(new BigDecimal(studentAccount).add(new BigDecimal(useaccount)))).toString();//总优惠后的金额
			if((new BigDecimal(studentAccount).compareTo(new BigDecimal(0)))<0)//和0比较大于返回1小于返回-1等于返回0
			{
				replayadd=true;
			}
		}
		else
		{
			isfail=true;
		}
		return SUCCESS;
	}
	
	public List<DiscountApplication> getApplist() {
		return applist;
	}

	public void setApplist(List<DiscountApplication> applist) {
		this.applist = applist;
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

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public String getReducemoney() {
		return reducemoney;
	}

	public void setReducemoney(String reducemoney) {
		this.reducemoney = reducemoney;
	}

	public int getDiscountpolicyId() {
		return discountpolicyId;
	}

	public void setDiscountpolicyId(int discountpolicyId) {
		this.discountpolicyId = discountpolicyId;
	}

	public String getReduceaftermoney() {
		return reduceaftermoney;
	}

	public void setReduceaftermoney(String reduceaftermoney) {
		this.reduceaftermoney = reduceaftermoney;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public FeePaymentDetail getFeePaymentDetail() {
		return feePaymentDetail;
	}

	public void setFeePaymentDetail(FeePaymentDetail feePaymentDetail) {
		this.feePaymentDetail = feePaymentDetail;
	}

	public int getIsFee() {
		return isFee;
	}

	public void setIsFee(int isFee) {
		this.isFee = isFee;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public boolean isReplayadd() {
		return replayadd;
	}

	public void setReplayadd(boolean replayadd) {
		this.replayadd = replayadd;
	}

	public boolean isBarcodeadd() {
		return barcodeadd;
	}

	public void setBarcodeadd(boolean barcodeadd) {
		this.barcodeadd = barcodeadd;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public int getPendingFeePaymentId() {
		return pendingFeePaymentId;
	}

	public void setPendingFeePaymentId(int pendingFeePaymentId) {
		this.pendingFeePaymentId = pendingFeePaymentId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<PendingFeePayment> getPendinglist() {
		return pendinglist;
	}

	public void setPendinglist(List<PendingFeePayment> pendinglist) {
		this.pendinglist = pendinglist;
	}

	public int getIndexcount() {
		return indexcount;
	}

	public void setIndexcount(int indexcount) {
		this.indexcount = indexcount;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
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

	public String getUseaccount() {
		return useaccount;
	}

	public void setUseaccount(String useaccount) {
		this.useaccount = useaccount;
	}

	public String getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(String allmoney) {
		this.allmoney = allmoney;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public boolean isNotfeepolice() {
		return notfeepolice;
	}

	public void setNotfeepolice(boolean notfeepolice) {
		this.notfeepolice = notfeepolice;
	}

	public boolean isNotceshipolice() {
		return notceshipolice;
	}

	public void setNotceshipolice(boolean notceshipolice) {
		this.notceshipolice = notceshipolice;
	}

	public double getBaomingfei() {
		return baomingfei;
	}

	public void setBaomingfei(double baomingfei) {
		this.baomingfei = baomingfei;
	}

	public double getCeshifei() {
		return ceshifei;
	}

	public void setCeshifei(double ceshifei) {
		this.ceshifei = ceshifei;
	}

	public Student getStudentprint() {
		return studentprint;
	}

	public void setStudentprint(Student studentprint) {
		this.studentprint = studentprint;
	}

	public String getNamefee() {
		return namefee;
	}

	public void setNamefee(String namefee) {
		this.namefee = namefee;
	}
	
	
}
