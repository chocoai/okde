package net.cedu.action.finance.orderbranchcedu;

import java.util.Iterator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询汇款总部单
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListFeePaymentDetailForOrderBranchCedu extends BaseAction
{
	private static final long serialVersionUID = 7605943719168137622L;

	private int branchId;
	
	private Student student = new Student();
	
	private Branch branch;
	
	private PageResult<FeePaymentDetail> result = new PageResult<FeePaymentDetail>();
	
	@Autowired
	private PaymentBiz paymentBiz;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	public String execute() throws Exception
	{
		branch = branchBiz.findBranchById(branchId);

		FeePaymentDetail feePaymentDetail = new FeePaymentDetail();
		feePaymentDetail.setStatus(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN);
		
		FeePayment feePayment = new FeePayment();
		feePayment.setFeeWayId(Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU); // 只有汇款方式为“现金汇款总部”的才需要添加汇款总部单
		
		student.setBranchId(branchId);
		
		List<FeePaymentDetail> list = paymentBiz.findFeePaymentDetailsPageList(feePaymentDetail, feePayment, student, result);
		
		wrapDetail(list);
		
		result.setList(list);
		
		return SUCCESS;
	}
	
	private void wrapDetail(List<FeePaymentDetail> list) throws Exception
	{
		if(list==null) return;
		
		Iterator<FeePaymentDetail> iter = list.iterator();
		
		while(iter.hasNext()){
			FeePaymentDetail detail = iter.next();
			FeePayment fp = feePaymentBiz.findFeePaymentById(detail.getFeePaymentId());
			Student student = studentBiz.findStudentById(fp.getStudentId());
			
			detail.setStudentName(student.getName());
			detail.setPaymentCode(fp.getCode());
			
			FeeSubject fs = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
			if(fs != null)
				detail.setPaymentSubjectName(fs.getName());
			
			Academy academy = academyBiz.findAcademyById(student.getAcademyId());
			if(academy != null)
				detail.setSchoolName(academy.getName());
			
			if(branch != null)
				detail.setBranchName(branch.getName());
			
			AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(student.getBatchId());
			if(batch != null)
				detail.setAcademyenrollbatchName(batch.getEnrollmentName());
			
			Level level = levelBiz.findLevelById(student.getLevelId());
			if(level != null)
				detail.setLevelName(level.getName());
			
			Major major = majorBiz.findMajorById(student.getMajorId());
			if(major != null)
				detail.setMajorName(major.getName());
		}
	}

	public Student getStudent() {
		return student;
	}

	public Branch getBranch() {
		return branch;
	}

	public PageResult<FeePaymentDetail> getResult() {
		return result;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
}
