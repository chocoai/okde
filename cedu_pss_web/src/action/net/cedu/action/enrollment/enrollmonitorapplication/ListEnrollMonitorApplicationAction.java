package net.cedu.action.enrollment.enrollmonitorapplication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.MonitorResults;

public class ListEnrollMonitorApplicationAction extends BaseAction{
	private static final long serialVersionUID = 3443021890053624483L;

	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private MonitorResultsBiz monitorResultsBiz;

	private Branch branch;
	private List<MonitorResults> monitorResultslist;

	/**
	 * 获取当前用户所在学习中心的信息
	 */
	public String execute()throws Exception{	
		int branchid = super.getUser().getOrgId();
		branch = branchBiz.findBranchById(branchid);
		monitorResultslist = monitorResultsBiz.findBranchMonitorResults();
		return SUCCESS;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public List<MonitorResults> getMonitorResultslist() {
		return monitorResultslist;
	}
	public void setMonitorResultslist(List<MonitorResults> monitorResultslist) {
		this.monitorResultslist = monitorResultslist;
	}
}
