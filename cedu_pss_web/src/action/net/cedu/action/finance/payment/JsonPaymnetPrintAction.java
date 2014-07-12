package net.cedu.action.finance.payment;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.biz.finance.ReceiptBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 打印缴费单
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonPaymnetPrintAction extends BaseAction
{
	
	@Autowired
	private PaymentBiz paymentBiz;//缴费单_业务层接口
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单_业务层接口
	@Autowired
	private StudentBiz studentBiz;//学生_业务层接口
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细_业务层接口
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;
	@Autowired
	private LevelBiz levelbiz;
	@Autowired
	private MajorBiz majorbiz;
	
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	
	@Autowired
	private ReceiptBiz receiptBiz;//收据业务接口
	
	// 查询条件
	private FeePayment feePayment;//缴费单实体
	//返回参数
	private Student student=new Student();//学生实体
	private String namefee;//收费人
	private double baomingfei=0;//报名费
	private double ceshifei=0;//测试费
	private double jiaocaifei=0;//教材费
	private double bukaofei=0;//补考费
	private double xuefei=0;//学费
	private double qitafei=0;//其他费
	private double allmoney=0;//所有费用
	
	//返回参数
	private boolean isback=false;
	private boolean isfail=false;
	
	/**
	 * 获取缴费单打印数据
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_payment_print_status_printing", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePaymentPrintStatusAndPrint() throws Exception {
		if (feePayment != null && feePayment.getBarCode()!=null && !feePayment.getBarCode().equals("") && feePayment.getBarCode()!="") 
		{
			if(!this.receiptBiz.findReceiptCanUsing(feePayment.getBarCode()))
			{
				isback=true;
				return SUCCESS;
			}
			
			//查询缴费单明细
			List<FeePaymentDetail> fpdlist=this.feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
			if(fpdlist!=null && fpdlist.size()>0)
			{
				for(FeePaymentDetail fpd:fpdlist)
				{
					if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
					{
						baomingfei=Double.valueOf(new BigDecimal(baomingfei).add(new BigDecimal(fpd.getAmountPaied())).toString());
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TEST)
					{
						ceshifei=Double.valueOf(new BigDecimal(ceshifei).add(new BigDecimal(fpd.getAmountPaied())).toString());
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_TUITION)
					{
						xuefei=Double.valueOf(new BigDecimal(xuefei).add(new BigDecimal(fpd.getAmountPaied())).toString());
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_BOOK)
					{
						jiaocaifei=Double.valueOf(new BigDecimal(jiaocaifei).add(new BigDecimal(fpd.getAmountPaied())).toString());
					}
					else if(fpd.getFeeSubjectId()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
					{
						bukaofei=Double.valueOf(new BigDecimal(bukaofei).add(new BigDecimal(fpd.getAmountPaied())).toString());
					}
					else
					{
						qitafei=Double.valueOf(new BigDecimal(qitafei).add(new BigDecimal(fpd.getAmountPaied())).toString());
					}
					//allmoney=Double.valueOf(new BigDecimal(allmoney).add(new BigDecimal(fpd.getAmountPaied())).toString());
				}
			}
			if(feePaymentBiz.findFeePaymentById(feePayment.getId())!=null)
			{
				feePayment.setCreatedTime(feePaymentBiz.findFeePaymentById(feePayment.getId()).getCreatedTime());
				feePayment.setCode(feePaymentBiz.findFeePaymentById(feePayment.getId()).getCode());
				allmoney=feePaymentBiz.findFeePaymentById(feePayment.getId()).getTotalAmount();
				student=this.studentBiz.findStudentById(feePaymentBiz.findFeePaymentById(feePayment.getId()).getStudentId());
				if(student!=null)
				{
					Academy academy = academyBiz.findAcademyById(student
							.getAcademyId());

					if (academy != null) 
					{
						student.setSchoolName(academy.getName());
					}

					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
							.findAcademyEnrollBatchById(student
									.getEnrollmentBatchId());
					if (academyenrollbatch != null) 
					{
						student.setAcademyenrollbatchName(academyenrollbatch
								.getEnrollmentName());
					}

					Level level = levelbiz.findLevelById(student.getLevelId());
					if (level != null) 
					{
						student.setLevelName(level.getName());
					}

					Major major = majorbiz.findMajorById(student.getMajorId());
					if (major != null)
					{
						student.setMajorName(major.getName());
					}
					namefee=super.getUser().getFullName();
				}
				
				//充值金额
				List<StudentAccountAmountManagement> saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(feePayment.getId());
				if(saamlist!=null && saamlist.size()>0)
				{
					for(StudentAccountAmountManagement saam:saamlist)
					{
						if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_REGISTRATION)
						{
							baomingfei=Double.valueOf(new BigDecimal(baomingfei).add(saam.getAccountMoney()).toString());
						}
						else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_TEST)
						{
							ceshifei=Double.valueOf(new BigDecimal(ceshifei).add(saam.getAccountMoney()).toString());
						}
						else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_TUITION)
						{
							xuefei=Double.valueOf(new BigDecimal(xuefei).add(saam.getAccountMoney()).toString());
						}
						else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_BOOK)
						{
							jiaocaifei=Double.valueOf(new BigDecimal(jiaocaifei).add(saam.getAccountMoney()).toString());
						}
						else if(saam.getFeeSubject()==Constants.FEE_SUBJECT_TYPE_MAKEUP)
						{
							bukaofei=Double.valueOf(new BigDecimal(bukaofei).add(saam.getAccountMoney()).toString());
						}
						else
						{
							qitafei=Double.valueOf(new BigDecimal(qitafei).add(saam.getAccountMoney()).toString());
						}
					}
				}
			}
			//paymentBiz.updateFeePaymentPrintStatus(feePayment.getIsPrint(),
			//		feePayment.getId());
			
		}
		else
		{
			isfail=true;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新缴费单打印状态
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_payment_print_status_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePaymentPrintStatus() throws Exception 
	{
		
		if (feePayment != null) 
		{
			//paymentBiz.updateFeePaymentPrintStatus(feePayment.getIsPrint(),feePayment.getId());
			FeePayment fp=this.feePaymentBiz.findFeePaymentById(feePayment.getId());
			if(fp!=null)
			{
				if(fp.getIsPrint()==feePayment.getIsPrint())
				{
					//从新打印     作废以前的收据
					this.receiptBiz.updateReceiptByCode(fp.getBarCode());
				}
				fp.setIsPrint(feePayment.getIsPrint());
				fp.setBarCode(feePayment.getBarCode());
				fp.setUpdaterId(super.getUser().getUserId());
				fp.setUpdatedTime(new Date());
				this.receiptBiz.updateReceiptUsedByPayment(feePayment.getBarCode());
				this.feePaymentBiz.updateFeePayment(fp);
			}
		}
		return SUCCESS;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getNamefee() {
		return namefee;
	}

	public void setNamefee(String namefee) {
		this.namefee = namefee;
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

	public double getJiaocaifei() {
		return jiaocaifei;
	}

	public void setJiaocaifei(double jiaocaifei) {
		this.jiaocaifei = jiaocaifei;
	}

	public double getBukaofei() {
		return bukaofei;
	}

	public void setBukaofei(double bukaofei) {
		this.bukaofei = bukaofei;
	}

	public double getXuefei() {
		return xuefei;
	}

	public void setXuefei(double xuefei) {
		this.xuefei = xuefei;
	}

	public double getQitafei() {
		return qitafei;
	}

	public void setQitafei(double qitafei) {
		this.qitafei = qitafei;
	}

	public double getAllmoney() {
		return allmoney;
	}

	public void setAllmoney(double allmoney) {
		this.allmoney = allmoney;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
	
}
