package net.cedu.action.basesetting.monitorresults;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.entity.basesetting.MonitorResults;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonUpdateMonitorResultsAction extends BaseAction{

	private static final long serialVersionUID = -6162515343935395967L;

	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	
	private MonitorResults monitorResults;//监控结果实体
	private int resultid;//监控结果id
	private boolean updaterltbool=true;//判断修改操作是否成功
	private boolean sameinfobool=true;
	
	/**
	 * 修改监控结果信息
	 */
	@Action(value="update_monitor_results",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
		MonitorResults oldresult=null;
		try {
			if (resultid > 0) {
				oldresult = new MonitorResults();
				int userid = super.getUser().getUserId();
				oldresult = monitorResultsBiz.findbyresultid(resultid);
				
				oldresult.setName(monitorResults.getName());
				oldresult.setUpdaterId(userid);
				oldresult.setUpdatedTime(new Date());
				
				sameinfobool = monitorResultsBiz.modifyMonitorResults(oldresult);
			}else{
				updaterltbool = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			updaterltbool = false;
		}
		return SUCCESS;
	}
	//-----------------------------------------get and set methods-------------------------------------------
	public MonitorResults getMonitorResults() {
		return monitorResults;
	}
	public void setMonitorResults(MonitorResults monitorResults) {
		this.monitorResults = monitorResults;
	}
	public int getResultid() {
		return resultid;
	}
	public void setResultid(int resultid) {
		this.resultid = resultid;
	}
	public boolean isUpdaterltbool() {
		return updaterltbool;
	}
	public void setUpdaterltbool(boolean updaterltbool) {
		this.updaterltbool = updaterltbool;
	}
	public boolean isSameinfobool() {
		return sameinfobool;
	}
	public void setSameinfobool(boolean sameinfobool) {
		this.sameinfobool = sameinfobool;
	}
}
