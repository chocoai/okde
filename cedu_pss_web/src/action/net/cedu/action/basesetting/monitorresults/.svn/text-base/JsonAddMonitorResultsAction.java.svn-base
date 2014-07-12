package net.cedu.action.basesetting.monitorresults;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.basesetting.MonitorResults;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonAddMonitorResultsAction extends BaseAction{

	private static final long serialVersionUID = -7569464331592841580L;

	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	private MonitorResults monitorResults;//监控结果实体
	public String code;//code生成器生成的code(4位)
	private boolean addrltbool=true;//判断添加操作是否成功
	private boolean errormsg=true;//有重复数据时的判断
	
	/**
	 * 添加监控结果
	 */
	@Action(value="add_monitor_Results",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}
					   )
			})	
	public String execute(){
	
			try {
				if(monitorResults!=null){
					int userid = super.getUser().getUserId();
					code = buildCodeBiz.getShortCodes(CodeEnum.MonitorResultsTB
							.getCode(), CodeEnum.MonitorResults.getCode());
					monitorResults.setCode(code);
					monitorResults.setCreatorId(userid);
					monitorResults.setUpdaterId(userid);
					monitorResults.setCreatedTime(new Date());
					monitorResults.setUpdatedTime(new Date());
					monitorResults.setDeleteFlag(Constants.DELETE_FALSE);
					
					errormsg = monitorResultsBiz.addMoniterResults(monitorResults);
				}
			} catch (Exception e) {
				e.printStackTrace();
				addrltbool = false;
			}
		
		return SUCCESS;
	}
	//---------------------------------------------get and set methods-------------------------------------------
	public MonitorResults getMonitorResults() {
		return monitorResults;
	}
	public void setMonitorResults(MonitorResults monitorResults) {
		this.monitorResults = monitorResults;
	}
	public boolean isAddrltbool() {
		return addrltbool;
	}
	public void setAddrltbool(boolean addrltbool) {
		this.addrltbool = addrltbool;
	}
	public boolean isErrormsg() {
		return errormsg;
	}
	public void setErrormsg(boolean errormsg) {
		this.errormsg = errormsg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
