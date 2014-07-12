package net.cedu.action.crm;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 呼叫学生详情
 * 
 * @author yangdongdong
 * 
 */
public class ViewHomeAction extends BaseAction {

	// 电话
	private String phone;
	// 座机
	private String tel;
	// 学生实体
	private List<Student> students;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentsourceBiz;
	@Autowired
	private BaseDictBiz basedictBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private GlobalEnrollBatchBiz globalenrollbatchBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private MajorBiz majorBiz;
	// 学生
	private Student student;
	// 学生ID
	private int studentId;

	@Override
	public String execute() throws Exception {
		if (phone != null && !"".equals(phone))
		{
			// 通过手机号码查询用户信息，如果存在，返回实体，如果不存在把手机号码返回到页面
			students = studentBiz.findStudentsByPhone(phone);
			if (students != null)
			{
				for (Student s : students)
				{
					//招生批次
					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz.findAcademyEnrollBatchById(s.getEnrollmentBatchId());
					if(academyenrollbatch!=null)
					{
						s.setEnrollmentBatchName(academyenrollbatch.getEnrollmentName());
					}
					else
					{
						s.setEnrollmentBatchName("无");
					}
					if (studentId == s.getId())
					{
						student = s;
//						students.remove(student);
						return SUCCESS;
					}
				}
				if (student == null)
				{
					student=students.get(0);
//					students.remove(student);
				}
			}
		}
		else if(tel != null && !"".equals(tel))
		{
			// 通过座机号码查询用户信息，如果存在，返回实体，如果不存在把座机号码返回到页面
			students = studentBiz.findStudentsByPhone(tel);
			if (students != null)
			{
				for (Student s : students)
				{
					//招生批次
					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz.findAcademyEnrollBatchById(s.getEnrollmentBatchId());
					if(academyenrollbatch!=null)
					{
						s.setEnrollmentBatchName(academyenrollbatch.getEnrollmentName());
					}
					else
					{
						s.setEnrollmentBatchName("无");
					}
					if (studentId == s.getId())
					{
						student = s;
//						students.remove(student);
						return SUCCESS;
					}
				}
				if (student == null)
				{
					student=students.get(0);
//					students.remove(student);
				}
			}
		}
		else
		{
			if (studentId != 0)
			{
				student = studentBiz.findStudentById(studentId);
			}
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
			//合作方
			Channel channel=channelBiz.findChannel(student.getChannelId());
			if(channel!=null)
			{
				student.setChannelName(channel.getName());
			}
			else
			{
				student.setChannelName("无");
			}
			//市场途径
			BaseDict basedict = basedictBiz.findBaseDictById(student.getEnrollmentWay());
			if(basedict!=null)
			{
				student.setEnrollmentWayName(basedict.getName());
			}
			else
			{
				student.setEnrollmentWayName("无");
				if(enrollmentsource!=null && student.getEnrollmentSource()!=Constants.WEB_STU_SOURCE_DEFAULT)
				{
					student.setEnrollmentWayName(student.getEnrollmentSourceName());
				}
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
			//批次
			GlobalEnrollBatch globalenrollbatch = globalenrollbatchBiz.findGlobalEnrollBatchById(student.getGlobalBatch());
			if(globalenrollbatch!=null)
			{
				student.setGlobalBatchName(globalenrollbatch.getBatch());
			}
			else
			{
				student.setGlobalBatchName("无");
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
		}
		return super.execute();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}