package net.cedu.action.crm;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生信息导入
 * 
 * @author yangdongdong
 * 
 */
public class StudentImportRecordAction extends BaseAction {

	@Autowired
	private BranchBiz branchBiz; // 学习中心biz

	private List<Branch> branchlist = new ArrayList<Branch>();// 学习中心集合

	@Override
	public String execute() throws Exception {
		// 学习中心
		//branchlist = branchBiz.findListById(super.getUser().getOrgId());
		request.setAttribute("branchObject", branchBiz.findBranchById(super.getUser().getOrgId()));
		
		return super.execute();
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

}
