package net.cedu.action.enrollment.enrollmonitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.entity.basesetting.MonitorResults;

public class ListEnrollMonitorAllAction extends BaseAction{

	private static final long serialVersionUID = -5754966985949913610L;

	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	private int viewtype;
	private List<MonitorResults> monitorResultslist;
	public String execute()throws Exception{	
		monitorResultslist = monitorResultsBiz.findAllMonitorResults();
		return SUCCESS;
	}
	
	
	//---------------------------get and set methods-------------------------------
	public int getViewtype() {
		return viewtype;
	}
	public void setViewtype(int viewtype) {
		this.viewtype = viewtype;
	}


	public List<MonitorResults> getMonitorResultslist() {
		return monitorResultslist;
	}


	public void setMonitorResultslist(List<MonitorResults> monitorResultslist) {
		this.monitorResultslist = monitorResultslist;
	}
	
	
}
