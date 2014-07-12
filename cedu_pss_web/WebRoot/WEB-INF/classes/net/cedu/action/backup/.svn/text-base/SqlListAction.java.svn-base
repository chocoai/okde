package net.cedu.action.backup;

import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.common.db.SingletonDatabaseUtil;

public class SqlListAction extends BaseAction {

	private List<Map<String, String>> fileList;

	@Override
	public String execute() throws Exception {
		// fileNameList =
		// FileUtil.getDirFileNameList(request.getRealPath(SingletonDatabaseUtil.getDatabaseBackupPath()),".sql.gz");
		// request.setAttribute("sql_tmp_dir",
		// Constants.WEB_ATTACHMENT+SingletonDatabaseUtil.getDatabaseBackupPath());
		fileList=SingletonDatabaseUtil.sqlFileList();
		return SUCCESS;
	}

	public List<Map<String, String>> getFileList() {
		return fileList;
	}

}
