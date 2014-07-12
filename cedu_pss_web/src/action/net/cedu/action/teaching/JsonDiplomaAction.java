package net.cedu.action.teaching;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.teaching.DiplomaBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.crm.Student;
import net.cedu.entity.teaching.Diploma;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.tools.internal.xjc.model.CAdapter;

/**
 * 毕业证书查询列表（条件查询）
 * @author wangmingjie
 *
 */
@ParentPackage("json-default")
public class JsonDiplomaAction extends BaseAction {
	@Autowired
	private DiplomaBiz  diplomaBiz;
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private AcademyBiz  academyBiz;
	// 分页结果
	private PageResult<Student> result=new PageResult<Student>();
	Diploma diploma=new Diploma();
	Student student=new Student();
	private boolean isfail=false;//返回参数
	private String diplomaIds;
	private String stuIds;
	/**
	 * 查询毕业证书数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_diploma_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String teachingNoticeCount() throws Exception 
	{
		result.setRecordCount(diplomaBiz.findDiplomaCountByConditions(student,result));
		return SUCCESS;
	}
	
	/**
	 * 查询毕业证书列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_diploma_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String teachingNoticeList() throws Exception
	{
		result.setList(diplomaBiz.findDiplomaListByConditions(student, result));
		return SUCCESS;
	}
/**
 * 领取
 * @return
 * @throws Exception
 */
	
	@Action(value = "upate_diplomastatus_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upStudentStatus() throws Exception
	{
		if(student.getId()>0)
		{
			Student stu=this.studentBiz.findStudentById(student.getId());
			if(stu!=null)
			{
				    stu.setGraduateTime(new Date());//修改学生的毕业时间，颁发时间
					this.studentBiz.updateStudentInfo(stu);
			}
			Diploma diploma=diplomaBiz.findDiplomaByStudentId(student.getId());//毕业状态
			if(diploma!=null)
			{
				diploma.setStatus(Constants.DIPLOMA_STATUS_YES_QU); //修改学生证书状态
				diploma.setGraduation(new Date());
				diplomaBiz.updateDiploma(diploma);
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量颁发
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_batch_diplomastatus_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upBatchStudentStatus() throws Exception
	{
		if(stuIds!=null && !stuIds.equals(""))
		{
			String[] ids=stuIds.split(",");
			for(int i=0 ;i<ids.length;i++)
			{
				if(ids[i]!=null && !ids[i].equals(""))
				{
					Student stu=this.studentBiz.findStudentById(Integer.valueOf(ids[i]));
					if(stu!=null)
					{
						    stu.setGraduateTime(new Date());//修改学生的毕业时间，颁发时间
							this.studentBiz.updateStudentInfo(stu);
					}
					Diploma diploma=new Diploma ();
					diploma.setAcademyId(stu.getAcademyId());
					diploma.setStudentId(Integer.valueOf(ids[i]));
					diploma.setStudentStatus(stu.getStatus());
					diploma.setGraduation(stu.getGraduateTime());
					diploma.setStatus(Constants.DIPLOMA_STATUS_YES_FA);
					diploma.setDeleteFlag(0);
					diploma.setCreatorId(super.getUser().getUserId());
					diploma.setCreatedTime(new Date());
					diploma.setUpdaterId(super.getUser().getUserId());
					diploma.setUpdatedTime(new Date());
					diploma.setAdmissionTime(stu.getRegistrationTime());//入学时间
					//diploma.setDiplomaUrl(diplomaUrl);//?
					//Diploma diploma=diplomaBiz.findDiplomaByStudentId(Integer.valueOf(ids[i]));//毕业状态
					//if(diploma!=null)
					//{
					//	diploma.setStatus(Constants.DIPLOMA_STATUS_YES_QU); //修改学生证书状态
					//	diploma.setGraduation(new Date());
					//	diplomaBiz.updateDiploma(diploma);
					//}
					if(diploma!=null)
					{
						diplomaBiz.addDiploma(diploma);	
					}	
				}
			}
			
		}
		else
		{
			isfail=true;
		}
		return SUCCESS;
	}
	public Diploma getDiploma() {
		return diploma;
	}

	public void setDiploma(Diploma diploma) {
		this.diploma = diploma;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public String getDiplomaIds() {
		return diplomaIds;
	}

	public void setDiplomaIds(String diplomaIds) {
		this.diplomaIds = diplomaIds;
	}

	public String getStuIds() {
		return stuIds;
	}

	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}
	
	
}
