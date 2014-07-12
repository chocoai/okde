package net.cedu.action.finance.payment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.finance.PendingFeePaymentBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.PendingFeePayment;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 增加测试费
 * @author yangdongdong
 *
 */
public class AddTestPayment extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;
	@Autowired
	private LevelBiz levelbiz;
	@Autowired
	private MajorBiz majorbiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	@Autowired
	private PendingFeePaymentBiz pendingFeePaymentBiz;//待缴费业务接口
	private PendingFeePayment pendingFeePayment;
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准明细业务层
	private FeeStandard feeStandard=new FeeStandard();//收费政策标准明细
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务层
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务层

	public Student student;

	private String code;
	
	private String feedate;//缴费时间
	
	private int feeSubjectId;//费用科目Id
	

	@Override
	public String execute() throws Exception {
		if (student.getId() != 0) 
		{
			student = studentBiz.findStudentById(student.getId());
			if (student != null) {
				Academy academy = academyBiz.findAcademyById(student
						.getAcademyId());

				if (academy != null) {
					student.setSchoolName(academy.getName());
				}

				AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
						.findAcademyEnrollBatchById(student
								.getEnrollmentBatchId());
				if (academyenrollbatch != null) {
					student.setAcademyenrollbatchName(academyenrollbatch
							.getEnrollmentName());
				}

				Level level = levelbiz.findLevelById(student.getLevelId());
				if (level != null) {
					student.setLevelName(level.getName());
				}

				Major major = majorbiz.findMajorById(student.getMajorId());
				if (major != null)
					student.setMajorName(major.getName());
				// 缴费单号
				code = buildCodeBiz.getCodes(CodeEnum.Payment.getCode(),
						CodeEnum.PaymentPrefix.getCode());
				
				feedate=DateUtil.getNowTimestamp("yyyy-MM-dd").toString().substring(0,10);
				//测试费
				List<PendingFeePayment> pendinglist=this.pendingFeePaymentBiz.findStudentsPendingFeePaymentListByStudentIdFeeSubjectId(student.getId(), FeeSubjectEnum.TestFee.value());
				if(pendinglist!=null && pendinglist.size()>0)
				{
					pendingFeePayment=pendinglist.get(0);
					if(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId())!=null && this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getPolicyFeeDetailId())!=null)
					{
						feeStandard=this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId());
						feeSubjectId=this.policyFeeBiz.findPolicyFeeById(this.feeStandardBiz.findFeeStandardById(pendingFeePayment.getFeeStandardId()).getPolicyFeeDetailId()).getFeeSubjectId();
					}
				}
			}
		}
		return super.execute();
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getFeeSubjectId() {
		return feeSubjectId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public FeeStandard getFeeStandard() {
		return feeStandard;
	}

	public void setFeeStandard(FeeStandard feeStandard) {
		this.feeStandard = feeStandard;
	}

	public PendingFeePayment getPendingFeePayment() {
		return pendingFeePayment;
	}

	public void setPendingFeePayment(PendingFeePayment pendingFeePayment) {
		this.pendingFeePayment = pendingFeePayment;
	}

	public String getFeedate() {
		return feedate;
	}

	public void setFeedate(String feedate) {
		this.feedate = feedate;
	}
	
}
