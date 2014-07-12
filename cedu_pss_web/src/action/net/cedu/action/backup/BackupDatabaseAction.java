package net.cedu.action.backup;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import net.cedu.action.BaseAction;
import net.cedu.common.db.SingletonDatabaseUtil;

@Results({ @Result(name = "success", location = "/backup/sql_list", type = "redirect") })
public class BackupDatabaseAction extends BaseAction {

	private String sqlRemark;
	@Override
	public String execute() throws Exception {
		SingletonDatabaseUtil.backupDatabseAllTable(sqlRemark);
		return SUCCESS;
	}
	public String getSqlRemark() {
		return sqlRemark;
	}
	public void setSqlRemark(String sqlRemark) {
		this.sqlRemark = sqlRemark;
	}
	
	
	
}
