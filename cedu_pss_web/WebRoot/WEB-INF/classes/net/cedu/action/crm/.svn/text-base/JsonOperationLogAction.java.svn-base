package net.cedu.action.crm;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.OperationLogBiz;
import net.cedu.entity.crm.OperationLog;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 操作日志
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonOperationLogAction extends BaseAction {

	@Autowired
	private OperationLogBiz operationLogBiz;
	/**
	 * 查询条件
	 */
	private OperationLog operationLog;
	
	// 分页结果
	private PageResult<OperationLog> result = new PageResult<OperationLog>();

	/**
	 * 查询操作日志数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_operationlog_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmOperationlogCount() throws Exception {
		// 查询数量
		result.setRecordCount(operationLogBiz.findOperationLogsPageCount(operationLog, result));
		return SUCCESS;
	}
	/**
	 * 查询操作日志集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_operationlog_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmOperationlogList() throws Exception {
		// 查询集合
//		result.setOrder("createTime");
//		result.setSort("desc");
		result.setList(operationLogBiz.findOperationLogsPageList(operationLog,
				result));
		return SUCCESS;
	}

	public OperationLog getOperationLog() {
		return operationLog;
	}

	public void setOperationLog(OperationLog operationLog) {
		this.operationLog = operationLog;
	}

	public PageResult<OperationLog> getResult() {
		return result;
	}

	public void setResult(PageResult<OperationLog> result) {
		this.result = result;
	}
	
	
	
}
