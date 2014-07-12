package net.cedu.action.finance.studentaccountmanagement;

import java.math.BigDecimal;
import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.finance.StudentAccountManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生账户详情
 * 
 * @author gaolei
 * 
 */

public class ViewStudentAccountManagementAction extends BaseAction {

	@Autowired
	private StudentAccountManagementBiz studentaccountmanagementbiz;   //学生账户管理biz
	@Autowired
	private StudentBiz studentbiz;                                     //学生biz
	private StudentAccountManagement sam;                              //学生账户实体
	private int id;                                                    //学生账户Id
	
	@Autowired
	private BranchBiz branchBiz;//学习中心_业务层接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校_业务层接口
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchBiz;//招生批次_业务层接口
	
	@Autowired
	private LevelBiz levelbiz;//层次_业务层接口
	
	@Autowired
	private MajorBiz majorbiz;//专业_业务层接口
	

	@Action(results = { @Result(name = "success", location = "view_student_account_management.jsp")
	})
	public String excute() throws Exception
	{
		sam=studentaccountmanagementbiz.findStudentAccountManagementByStudentId(id);
		StudentAccountManagement sams=null;
		//创建学生账户
		if(null==sam)
		{
			sams=new StudentAccountManagement();
			sams.setStudentId(id);
			sams.setCode("SA0000000"+id);
			sams.setAccountBalance(new BigDecimal(0.00));
			sams.setUsingStatus(Constants.STATUS_ENABLED);
			sams.setCreatorId(super.getUser().getUserId());
			sams.setCreatedTime(new Date());
			studentaccountmanagementbiz.addStudentAccountManagement(sams);
			if(null!=sams)
			{
				sam=studentaccountmanagementbiz.findStudentAccountManagementById(sams.getId());
				if(null!=sam)
				{
					Student student=studentbiz.findStudentById(sam.getStudentId());
					if(null!=student)
					{
						sam.setStudentName(student.getName());
						//学习中心
						Branch branch=this.branchBiz.findBranchById(student.getBranchId());
						if(branch!=null)
						{
							sam.setBranchName(branch.getName());
						}
						//院校
						Academy academy = academyBiz.findAcademyById(student.getAcademyId());
						if (academy != null)
						{
							sam.setAcademyName(academy.getName());
						}
						//招生批次
						AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
						.findAcademyEnrollBatchById(student
								.getEnrollmentBatchId());
						if (academyenrollbatch != null)
						{
							sam.setBatchName(academyenrollbatch.getEnrollmentName());
						}
						//层次
						Level level = levelbiz.findLevelById(student.getLevelId());
						if (level != null) 
						{
							sam.setLevelName(level.getName());
						}
						//专业
						Major major = majorbiz.findMajorById(student.getMajorId());
						if (major != null)
						{
							sam.setMajorName(major.getName());
						}
					}
					
				}
			}
			
		}else
		{
			sam=studentaccountmanagementbiz.findStudentAccountManagementById(sam.getId());
			if(null!=sam)
			{
				Student student=studentbiz.findStudentById(sam.getStudentId());
				if(null!=student)
				{
					sam.setStudentName(student.getName());
					//学习中心
					Branch branch=this.branchBiz.findBranchById(student.getBranchId());
					if(branch!=null)
					{
						sam.setBranchName(branch.getName());
					}
					//院校
					Academy academy = academyBiz.findAcademyById(student.getAcademyId());
					if (academy != null)
					{
						sam.setAcademyName(academy.getName());
					}
					//招生批次
					AcademyEnrollBatch academyenrollbatch = academyenrollbatchBiz
					.findAcademyEnrollBatchById(student
							.getEnrollmentBatchId());
					if (academyenrollbatch != null)
					{
						sam.setBatchName(academyenrollbatch.getEnrollmentName());
					}
					//层次
					Level level = levelbiz.findLevelById(student.getLevelId());
					if (level != null) 
					{
						sam.setLevelName(level.getName());
					}
					//专业
					Major major = majorbiz.findMajorById(student.getMajorId());
					if (major != null)
					{
						sam.setMajorName(major.getName());
					}
				}
				
			}
		}
		
		
		
		return SUCCESS;	
	}

	public StudentAccountManagement getSam() {
		return sam;
	}

	public void setSam(StudentAccountManagement sam) {
		this.sam = sam;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	


	
	
}
