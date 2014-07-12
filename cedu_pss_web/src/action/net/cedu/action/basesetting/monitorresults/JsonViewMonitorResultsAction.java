package net.cedu.action.basesetting.monitorresults;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewMonitorResultsAction extends BaseAction{
	private static final long serialVersionUID = 4252770439043286028L;

	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	
	private MonitorResults monitorResults;//监控结果实体
	private PageResult<MonitorResults> result = new PageResult<MonitorResults>();// 分页结果
	private List<MonitorResults> resultlist;//监控结果列表
	private boolean lstrltbool=true;//判断页面加载列表是否正常
	
	/**
	 * 查询回访记录列表集合通过条件
	 */
	@Action(value="list_monitor_results",
			results={
			@Result(type="json", name = "success",
					params={"contentType","text/json",
							}			
			 		)
		})	
	public String execute()throws Exception{;
		result.setList(monitorResultsBiz.findMonitorResultsPageList(monitorResults, result));
		return SUCCESS;
	}
	
	/**
	 *  回访记录 列表纪录数量
	 * @return
	 * @throws Exception
	 */
	@Action(value="count_monitor_results",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String resultCount() throws Exception {
		result.setRecordCount(monitorResultsBiz.findMonitorResultsPageCount(monitorResults, result));
		return SUCCESS;
	}
	
	/**
	 *  回访记录 列表纪录数量
	 * @return
	 * @throws Exception
	 */
	@Action(value="list_monitor_results_by_flag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String resultsList() throws Exception {
		try {
			resultlist = monitorResultsBiz.findAllMonitorResults();
		} catch (Exception e) {
			e.printStackTrace();
			lstrltbool = false;
		}
		return SUCCESS;
	}

	//----------------------------------------get and set methods------------------------------------

	public MonitorResults getMonitorResults() {
		return monitorResults;
	}

	public void setMonitorResults(MonitorResults monitorResults) {
		this.monitorResults = monitorResults;
	}

	public PageResult<MonitorResults> getResult() {
		return result;
	}

	public void setResult(PageResult<MonitorResults> result) {
		this.result = result;
	}

	public List<MonitorResults> getResultlist() {
		return resultlist;
	}

	public void setResultlist(List<MonitorResults> resultlist) {
		this.resultlist = resultlist;
	}

	public boolean isLstrltbool() {
		return lstrltbool;
	}

	public void setLstrltbool(boolean lstrltbool) {
		this.lstrltbool = lstrltbool;
	}
	
}
