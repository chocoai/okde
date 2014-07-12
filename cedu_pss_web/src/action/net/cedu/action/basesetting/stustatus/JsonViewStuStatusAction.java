package net.cedu.action.basesetting.stustatus;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.StuStatusBiz;
import net.cedu.entity.basesetting.StudentStatus;
import net.cedu.model.page.PageResult;

@ParentPackage("json-default")
public class JsonViewStuStatusAction extends BaseAction {

	private static final long serialVersionUID = -9120829405594029990L;

	@Autowired
	private StuStatusBiz stustatusbiz;

//	private List<StudentStatus> stustatuslst;// 学生状态列表
//	private boolean lstrltbool = true;// 判断页面加载列表是否正常

	private PageResult<StudentStatus> result = new PageResult<StudentStatus>();

	/**
	 * 查询所有学生状态信息 BY HXJ
	 */
	@Action(value = "list_stu_status", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json" }) })
	public String execute() throws Exception {
//		try {
//			stustatuslst = stustatusbiz.findAllStudentStatus();
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			//lstrltbool = false;
//		}

		return SUCCESS;
	}

	/**
	 * 查询所有学生状态信息(delete_flag=0) BY HXJ
	 */
	@Action(value = "list_stu_status_flag", results = { @Result(type = "json", name = "success", params = {
			"contentType", "text/json", "excludeProperties",
			"stustatuslst.*.createdTime," + "stustatuslst.*.updatedTime" }) })
	public String showStuStatusListByDeleteFlag() throws Exception {
//		try {
//			stustatuslst = stustatusbiz.findAllStudentStatusByDeleteFlag();
		result.setList(stustatusbiz.findAllStudentStatusByDeleteFlag());
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			lstrltbool = false;
//		}

		return SUCCESS;
	}

	// -----------------------------------------------get and set
	// methods------------------------------

//	public List<StudentStatus> getStustatuslst() {
//		return stustatuslst;
//	}
//
//	public void setStustatuslst(List<StudentStatus> stustatuslst) {
//		this.stustatuslst = stustatuslst;
//	}
//
//	public boolean isLstrltbool() {
//		return lstrltbool;
//	}
//
//	public void setLstrltbool(boolean lstrltbool) {
//		this.lstrltbool = lstrltbool;
//	}

	public PageResult<StudentStatus> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentStatus> result) {
		this.result = result;
	}
}
