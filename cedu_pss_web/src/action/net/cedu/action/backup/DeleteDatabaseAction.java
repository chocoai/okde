package net.cedu.action.backup;

import net.cedu.action.BaseAction;
import net.cedu.common.db.SingletonDatabaseUtil;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ @Result(name = "success", location = "/backup/sql_list", type = "redirect") })
public class DeleteDatabaseAction extends BaseAction {

	private String sql;
	@Override
	public String execute() throws Exception {
		if(sql!=null){
			//FileUtil.deleteFile(request.getRealPath(SingletonDatabaseUtil.getDatabaseBackupPath())+File.separator+sql);
			SingletonDatabaseUtil.deleteSqlFile(sql);
		}
		return SUCCESS;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	
}
