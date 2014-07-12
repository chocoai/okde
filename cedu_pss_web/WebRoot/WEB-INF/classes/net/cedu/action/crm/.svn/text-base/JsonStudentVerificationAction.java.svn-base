/**
 * 文件名：JsonStudentVerificationAction.java
 * 包名：net.cedu.action.crm
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-4 上午11:21:02
 *
 */
package net.cedu.action.crm;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.entity.crm.Student;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @功能：学生跟踪学生信息验证以及其他（如：信息调出）
 * 
 * @作者： 杨栋栋
 * @作成时间：2012-1-4 上午11:27:11
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 * 
 */
@ParentPackage("json-default")
public class JsonStudentVerificationAction extends BaseAction {

	@Autowired
	private StudentBiz studentBiz;

	// 手机或座机
	private String phone;

	// 学生列表集合
	private List<Student> studentList = new ArrayList<Student>();

	/**
	 * 
	 * @功能：通过学生手机号，以及座机查询学生信息列表
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-4 上午11:27:07
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "search_student_by_phone_and_mobile", results = { @Result(name = "success", type = "json", params = {"contentType", "text/json" }) })
	public String searchStudentByPhoneAndMobile() throws Exception {
		if (!StringUtils.isEmpty(phone)) {
			studentList = studentBiz.findStudentsByPhone(phone);
		}
		return SUCCESS;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Student> getStudentList() {
		return studentList;
	}
	
}
