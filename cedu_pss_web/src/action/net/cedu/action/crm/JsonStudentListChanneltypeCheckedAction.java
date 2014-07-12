package net.cedu.action.crm;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.entity.crm.Student;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生查询列表（招生途径复核）
 * 
 * @author xiao
 * 
 */
@ParentPackage("json-default")
public class JsonStudentListChanneltypeCheckedAction extends BaseAction
{
	@Autowired
	private StudentBiz studentBiz;
	
	// 分页结果
	private PageResult<Student> result = new PageResult<Student>();
	// 查询条件
	private Student student=new Student();
	
	@Autowired 
	private StuStatusBiz stuStatusBiz;//学生状态_业务层接口
	private String stuStage;//阶段编码
	private String stuIds;//学生ids
	
	/**
	 * 查询学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_channeltype_checked_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cbStudentCount() throws Exception 
	{
		// 查询数量
		if(stuStage!=null && !"".equals(stuStage) && !stuStage.equals("0") && student.getStatus()==0)
		{
			List<StudentStatus> stuStatusList=this.stuStatusBiz.findStatusNamesByStageCode(stuStage);
//			String status="";
			StringBuffer statusSB = new StringBuffer("");
			if(stuStatusList!=null && stuStatusList.size()>0)
			{
				for(StudentStatus ss:stuStatusList)
				{
//					status+=","+ss.getId();
					if(statusSB.toString().equals("")){
						statusSB = new StringBuffer(ss.getId()+"");
					}else{
						statusSB.append(","+ss.getId());
					}
				}
//				if(!"".equals(status) && status.length()>0)
//				{
//					student.setStatusIds(status.substring(1, status.length()));
//				}
				if(!statusSB.toString().equals("")){
					student.setStatusIds(statusSB.toString());
				}
			}
		}
		result.setRecordCount(studentBiz.findStudentsPageCountByChannelTypeChecked(student));
		return SUCCESS;
	}

	/**
	 * 查询学生列表集合
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_channeltype_checked_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String lbStudentList() throws Exception
	{
		// 查询集合
		if(stuStage!=null && !"".equals(stuStage) && !stuStage.equals("0") && student.getStatus()==0)
		{
			List<StudentStatus> stuStatusList=this.stuStatusBiz.findStatusNamesByStageCode(stuStage);
//			String status="";
			StringBuffer statusSB = new StringBuffer("");
			if(stuStatusList!=null && stuStatusList.size()>0)
			{
				for(StudentStatus ss:stuStatusList)
				{
//					status+=","+ss.getId();
					if(statusSB.toString().equals("")){
						statusSB = new StringBuffer(ss.getId()+"");
					}else{
						statusSB.append(","+ss.getId());
					}
				}
//				if(!"".equals(status) && status.length()>0)
//				{
//					student.setStatusIds(status.substring(1, status.length()));
//				}
				if(!statusSB.toString().equals("")){
					student.setStatusIds(statusSB.toString());
				}
			}
		}
		result.setList(studentBiz.findStudentsPageListByChannelTypeChecked(student, result));
		return SUCCESS;
	}
	
	/**
	 * 批量更新学生复核
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "upate_batch_student_is_channel_type_checked_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String upateBatchStudentIsChannelTypeChecked() throws Exception 
	{
		if(stuIds!=null&&!stuIds.equals("")){
			studentBiz.updateStudentIsChannelTypeChecked(stuIds);
		}
		return SUCCESS;
	}

	public PageResult<Student> getResult() {
		return result;
	}

	public void setResult(PageResult<Student> result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getStuStage() {
		return stuStage;
	}

	public void setStuStage(String stuStage) {
		this.stuStage = stuStage;
	}

	public String getStuIds() {
		return stuIds;
	}

	public void setStuIds(String stuIds) {
		this.stuIds = stuIds;
	}
	
	
}
