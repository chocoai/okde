package net.cedu.action.teaching;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.crm.StudentSlaveBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.crm.StudentSlave;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.teaching.Diploma;

public class ViewDiplomaStudentAction extends BaseAction {
	private int id;           //获取连接参数ID
	private Student student=new Student();
	private StudentSlave  studentSlave=new StudentSlave();
	private Diploma diploma=new Diploma();
	private boolean isback=false;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private DiplomaBiz  diplomaBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private StudentSlaveBiz  studentSlaveBiz;
	
	public String execute() throws Exception
	{
		if(id!=0)
		{
			student = diplomaBiz.findStudentById(id);
		}
		if(student!=null)
		{
			//招生途径
			EnrollmentSource enrollmentsource = enrollmentsourceBiz.findEnrollmentSourceById(student.getEnrollmentSource());
			if(enrollmentsource!=null)
			{
				student.setEnrollmentSourceName(enrollmentsource.getName());
			}
			else
			{
				student.setEnrollmentSourceName("无");
			}
			//学习中心
			Branch branch = branchBiz.findBranchById(student.getBranchId());
			if(branch!=null)
			{
				student.setBranchName(branch.getName());
			}
			else
			{
				student.setBranchName("无");
			}
			//院校
			Academy academy = academyBiz.findAcademyById(student.getAcademyId());
			if(academy!=null)
			{
				student.setSchoolName(academy.getName());
			}
			else
			{
				student.setSchoolName("无");
			}
			//招生批次
			AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz.findAcademyEnrollBatchById(student.getEnrollmentBatchId());
			if(academyenrollbatch!=null)
			{
				student.setEnrollmentBatchName(academyenrollbatch.getEnrollmentName());
			}
			else
			{
				student.setEnrollmentBatchName("无");
			}
			//层次
			Level level = levelBiz.findLevelById(student.getLevelId());
			if(level!=null)
			{
				student.setLevelName(level.getName());
			}
			else
			{
				student.setLevelName("无");
			}
			//专业
			Major major = majorBiz.findMajorById(student.getMajorId());
			if(major!=null)
			{
				student.setMajorName(major.getName());
			}
			else
			{
				student.setMajorName("无");
			}
			//数据来源
		    BaseDict bd=this.basedictBiz.findBaseDictById(student.getStudentDataSource());
		    if(bd!=null)
		    {
		    	student.setStudentDataSourceName(bd.getName());
		    }
		    else
		    {
		    	student.setStudentDataSourceName("无");
		    }
		    studentSlave=studentSlaveBiz.findByStudentId(student.getId());
		     diploma = diplomaBiz.findDiplomaByStudentId(id);
		}
		return SUCCESS;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public StudentSlave getStudentSlave() {
		return studentSlave;
	}
	public void setStudentSlave(StudentSlave studentSlave) {
		this.studentSlave = studentSlave;
	}
	public Diploma getDiploma() {
		return diploma;
	}
	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}
	public boolean isIsback() {
		return isback;
	}
	public void setIsback(boolean isback) {
		this.isback = isback;
	}
	
	
}
