package net.cedu.biz.crm;

import java.util.List;

import net.cedu.entity.crm.OperationLog;
import net.cedu.model.page.PageResult;

/**
 * 操作日志Biz
 * 
 * @author yangdongdong
 * 
 */
public interface OperationLogBiz {
	/**
	 * 新建操作日志
	 * 
	 * @param operationLog
	 *            操作日志实体
	 * @return
	 * @throws
	 */
	public void addOperationLog(OperationLog operationLog) throws Exception;

	/**
	 * 删除操作日志
	 * 
	 * @param id
	 *            操作日志标识ID
	 * @return
	 * @throws
	 */
	public void deleteOperationLogById(int id) throws Exception;

	/**
	 * 查看操作日志
	 * 
	 * @param id
	 *            操作日志ID
	 * @return
	 * @throws
	 */
	public OperationLog findOperationLogById(int id) throws Exception;

	/**
	 * 查询操作日志总条数
	 * 
	 * @param operationLog
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public int findOperationLogsPageCount(OperationLog operationLog,
			PageResult<OperationLog> pr) throws Exception;

	/**
	 * 查询操作日志集合
	 * 
	 * @param operationLog
	 *            查询条件
	 * @param pr
	 *            分页实体
	 * @return
	 * @throws
	 */
	public List<OperationLog> findOperationLogsPageList(OperationLog operationLog,
			PageResult<OperationLog> pr) throws Exception;
}
