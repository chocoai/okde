/**
 * 文件名：BranchStudentNewEnrollmentAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-31 上午01:19:53
 *
 */
package net.cedu.action.report;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

@Results( {
	@Result(name = "success", location = "/WEB-INF/content/report/student_new_enrollment.jsp") 
})
public class BranchStudentNewEnrollmentAction extends BaseAction {

	private Branch branch;
	@Autowired
	private BranchBiz branchBiz;

	@Override
	public String execute() throws Exception {
		int branchId = super.getUser().getOrgId()==BranchEnum.Admin.value()||super.getUser().getOrgId()==BranchEnum.Root.value()?-999:super.getUser().getOrgId();
		
		if(branchId==-999){
			branch=null;
		}else{
			branch=branchBiz.findBranchById(branchId);
		}
		
		return SUCCESS;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	
}
