package net.cedu.action.crm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.entity.basesetting.MonitorResults;

/**
 * 学生列表（招生途径复核）
 * @author xiao
 *
 */
public class StudentListChanneltypeCheckedAction extends BaseAction
{
	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	private List<MonitorResults> monitorResultslist;
	
	@Override
	public String execute() throws Exception {
		monitorResultslist = monitorResultsBiz.findAllMonitorResults();
		return super.execute();
	}

	public List<MonitorResults> getMonitorResultslist() {
		return monitorResultslist;
	}

	public void setMonitorResultslist(List<MonitorResults> monitorResultslist) {
		this.monitorResultslist = monitorResultslist;
	}
	
}
