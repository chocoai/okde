package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;

/**
 * 添加历史缴费
 * @author lixiaojun
 *
 */
public class AddHistoryPaymentAction extends BaseAction 
{
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
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	public Student student;

	private String code;
	
	private String feedate;//缴费时间
	
	private String namefee;//收费人
	
	

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
				
				namefee=super.getUser().getFullName();
			}
		}
		feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
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

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}

	public String getFeedate() {
		return feedate;
	}

	public void setFeedate(String feedate) {
		this.feedate = feedate;
	}

	public String getNamefee() {
		return namefee;
	}

	public void setNamefee(String namefee) {
		this.namefee = namefee;
	}
	
}
