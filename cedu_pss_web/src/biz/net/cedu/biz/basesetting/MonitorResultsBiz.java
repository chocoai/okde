package net.cedu.biz.basesetting;

import java.util.List;

import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.model.page.PageResult;

/**
 * 监控结果
 * @author HXJ
 *
 */
public interface MonitorResultsBiz {
	
	/**
	 * 根据回访记录的监控结果查询(id)监控结果名称(name)
	 * @return
	 * @throws Exception
	 */
	public String findMoniterResultsNameByid(int id)throws Exception;
	
	/**
	 * 添加监控结果
	 * @param monitorResults
	 * @return
	 */
	public boolean addMoniterResults(MonitorResults monitorResults)  throws Exception;
	
	/**
	 * 查询全部监控结果
	 * @return
	 * @throws Exception
	 */
	public List<MonitorResults> findAllMonitorResults()throws Exception;
	
	/**
	 * 修改监控结果
	 * @param monitorResults
	 * @return
	 */
	public boolean modifyMonitorResults(MonitorResults monitorResults)throws Exception;
	
	/**
	 * 删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public  MonitorResults deleteConfigMonitorResults(int id);
	
	/**
	 * 根据主键id查询
	 * @param id
	 * @return
	 */
	public MonitorResults findbyresultid(int id);
	
	/**
	 * 查询监控结果总数量
	 * @param monitorResults
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findMonitorResultsPageCount(MonitorResults monitorResults, PageResult<MonitorResults> pr)
	throws Exception;
	
	/**
	 * 查询监控结果总集合
	 * @param monitorResults
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<MonitorResults> findMonitorResultsPageList(MonitorResults monitorResults,
			PageResult<MonitorResults> pr) throws Exception;
	
	/**
	 * 查询学习中心下的监控结果集合
	 * @return
	 * @throws Exception
	 */
	public List<MonitorResults> findBranchMonitorResults()throws Exception;
}
