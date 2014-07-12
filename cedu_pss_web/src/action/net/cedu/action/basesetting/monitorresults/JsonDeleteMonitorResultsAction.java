package net.cedu.action.basesetting.monitorresults;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.entity.basesetting.MonitorResults;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonDeleteMonitorResultsAction extends BaseAction{

	private static final long serialVersionUID = 498373846126777078L;

	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	
	private int resultid;//监控结果id
	private boolean delrltbool=false;//判断删除操作是否成功

	/**
	 * 删除监控结果(读取配置文件)
	 */
	@Action(value="delete_monitor_results_by_config",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		MonitorResults monitorResults = new MonitorResults();
		try {
			monitorResults = monitorResultsBiz
					.deleteConfigMonitorResults(resultid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(monitorResults!=null){
			delrltbool = true;
		}
		return SUCCESS;
	}
	//-------------------------------------------------GET AND SET methods-------------------------------------
	public int getResultid() {
		return resultid;
	}
	public void setResultid(int resultid) {
		this.resultid = resultid;
	}
	public boolean isDelrltbool() {
		return delrltbool;
	}
	public void setDelrltbool(boolean delrltbool) {
		this.delrltbool = delrltbool;
	}
}
