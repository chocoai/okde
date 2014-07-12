package net.cedu.action.enrollment.enrollaudit;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.Student;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateEnrollAuditAction extends BaseAction{

	private static final long serialVersionUID = 4540732135518240076L;

	@Autowired
	private StudentBiz stubiz;
	
	private Student student;//学生信息实体
	private boolean addrltbool=true;//判断添加操作是否成功
	
	/**
	 * 修改
	 */
	@Action(value="update_enroll_audit_stuinfo",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		try {
			int userid = super.getUser().getUserId();
			Student stu = new Student();
			if (student != null) {
				stu = stubiz.findStudentById(student.getId());
				
				stu.setName(student.getName());
				stu.setMobile(student.getMobile());
				stu.setGender(student.getGender());
				stu.setLivingPlace(student.getLivingPlace());
				stu.setCertNo(student.getCertNo());
				stu.setMsn(student.getMsn());
				stu.setQq(student.getQq());
				stu.setEmail(student.getEmail());
				stu.setRemark(student.getRemark());
				stu.setWorkUnitInfo(student.getWorkUnitInfo());
				stu.setStatus(Constants.STU_CALL_STATUS_YI_FU_HE);
				stu.setUpdaterId(userid);
				stu.setModifiedTime(new Date());
				
				stubiz.updateStudentInfo(stu);
			}else{
				addrltbool=false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			addrltbool=false;
		}
		return SUCCESS;
	}
	//---------------------------------------get and set methods----------------------------------------

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public boolean isAddrltbool() {
		return addrltbool;
	}

	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
	
}
