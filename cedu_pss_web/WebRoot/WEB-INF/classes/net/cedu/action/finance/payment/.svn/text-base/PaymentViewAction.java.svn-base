package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;

/**
 * 缴费单明细
 * @author lixiaojun
 *
 */
public class PaymentViewAction extends BaseAction
{
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单业务层接口
	private FeePayment feePayment=new FeePayment();//缴费单实体
	private int feePaymentId;//缴费单Id
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务层接口
	private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//缴费单明细实体
	
	@Autowired
	private StudentBiz studentBiz;//学生业务层接口
	private Student student=new Student();//学生实体
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务层接口
	private String academyName;//院校名称
	
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	private String branchName;//学习中心名称
	
	@Autowired
	private AcademyEnrollBatchBiz batchBiz;//批次业务层接口
	private String batchName;//批次名称
	
	@Autowired
	private LevelBiz levelBiz;//层次业务层接口
	private String levelName;//层次名称
	
	@Autowired
	private MajorBiz majorBiz;//专业业务层接口
	private String majorName;//专业名称
	
	
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	private List<StudentAccountAmountManagement> saamlist=new ArrayList<StudentAccountAmountManagement>();//学生充值账户明细集合
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			feePayment=this.feePaymentBiz.findFeePaymentById(feePaymentId);
			if(feePayment!=null)
			{
				feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
				
				student=this.studentBiz.findStudentById(feePayment.getStudentId());
				if(student!=null)
				{
					if(this.academyBiz.findAcademyById(student.getAcademyId())!=null)
					{
						academyName=this.academyBiz.findAcademyById(student.getAcademyId()).getName();
					}
					else
					{
						academyName="";
					}
					if(this.branchBiz.findBranchById(student.getBranchId())!=null)
					{
						branchName=this.branchBiz.findBranchById(student.getBranchId()).getName();
					}
					else
					{
						branchName="";
					}
					if(this.batchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId())!=null)
					{
						batchName=this.batchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId()).getEnrollmentName();
					}
					else
					{
						batchName="";
					}
					if(this.levelBiz.findLevelById(student.getLevelId())!=null)
					{
						levelName=this.levelBiz.findLevelById(student.getLevelId()).getName();
					}
					else
					{
						levelName="";
					}
					if(this.majorBiz.findMajorById(student.getMajorId())!=null)
					{
						majorName=this.majorBiz.findMajorById(student.getMajorId()).getName();
					}
					else
					{
						majorName="";
					}
				}
				if(feePayment.getId()>0)
				{
					saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdForViewFeePayment(feePayment.getId());
				}
			}
			return INPUT;
		}
		return SUCCESS;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}

	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getAcademyName() {
		return academyName;
	}

	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
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

	public List<StudentAccountAmountManagement> getSaamlist() {
		return saamlist;
	}

	public void setSaamlist(List<StudentAccountAmountManagement> saamlist) {
		this.saamlist = saamlist;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
}
