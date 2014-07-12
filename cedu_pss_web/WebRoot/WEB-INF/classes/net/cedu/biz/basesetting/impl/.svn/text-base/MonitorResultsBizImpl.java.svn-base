package net.cedu.biz.basesetting.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.MonitorResultsDao;
import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonitorResultsBizImpl implements MonitorResultsBiz{

	@Autowired
	private MonitorResultsDao monitorResultsDao;
	
	/*根据学生基础表里的监控结果(id)查询监控结果名称(name)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#findMoniterResultsNameByid(int)
	 */
	public String findMoniterResultsNameByid(int id)throws Exception{
		MonitorResults monitorResults = new MonitorResults();
		String monitorname="";
		if (id>0) {
			monitorResults = monitorResultsDao.findById(id);
			if(monitorResults!=null&&monitorResults.getId()>0){
				monitorname = monitorResults.getName();
			}
		}
		return monitorname;
	}
	
	/*	添加监控结果
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#addMoniterResultsName(net.cedu.entity.basesetting.MonitorResults)
	 */
	public boolean addMoniterResults(MonitorResults monitorResults) throws Exception{
		if (0>=this.findTotalByProperty(monitorResults)) {
			monitorResultsDao.save(monitorResults);
			return true;
		}
		return false;
	}
	
	/* 查询全部监控结果
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#findAllMonitorResults()
	 */
	public List<MonitorResults> findAllMonitorResults()throws Exception{
		return monitorResultsDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	/* 修改监控结果
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#modifyMonitorResults(net.cedu.entity.basesetting.MonitorResults)
	 */
	public boolean modifyMonitorResults(MonitorResults monitorResults) throws Exception{
		if (0>=findTotalByProperty(monitorResults)) {
			monitorResultsDao.update(monitorResults);
			return true;
		}
		return false;
	}
	
	/* 删除(读取配置文件)
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#deleteConfigMonitorResults(int)
	 */
	public  MonitorResults deleteConfigMonitorResults(int id){
		return monitorResultsDao.deleteConfig(id);
	}
	
	//查询数据中是否有重复的数据
	@SuppressWarnings("unchecked")
	private int findTotalByProperty(MonitorResults monitorResults)throws Exception{
		String hql = "";
		List objs = new ArrayList();

		if(monitorResults.getId()>0){
			hql+=" and id <> "+Constants.PLACEHOLDER;
			objs.add(monitorResults.getId());
		}
		if (StringUtils.isNotBlank(monitorResults.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			objs.add(monitorResults.getName());
		}
		
		return monitorResultsDao.findCountByProperty(hql, objs);
	}
	
	/* 根据主键id查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#findbyresultid(int)
	 */
	public MonitorResults findbyresultid(int id){
		return monitorResultsDao.findById(id);
	}
	
	/* 查询监控结果总数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#findMonitorResultsPageCount(net.cedu.entity.basesetting.MonitorResults, net.cedu.model.page.PageResult)
	 */
	@SuppressWarnings("unchecked")
	public int findMonitorResultsPageCount(MonitorResults monitorResults, PageResult<MonitorResults> pr)
	throws Exception{
		PageParame p=null;
		try {
			p = new PageParame(pr);
			String sql = "";
			List list = new ArrayList();
			if(monitorResults!=null){
				//编码
				if(monitorResults.getCode()!=null&&!("").equals(monitorResults.getCode())){
					sql+=" and code like "+Constants.PLACEHOLDER;
					list.add("%"+monitorResults.getCode()+"%");
				}
				//监控结果名称
				if(monitorResults.getName()!=null&&!("").equals(monitorResults.getName())){
					sql+=" and name like "+Constants.PLACEHOLDER;
					list.add("%"+monitorResults.getName()+"%");
				}
			}
			
			sql += " and deleteFlag=" + Constants.PLACEHOLDER;
			list.add(Constants.DELETE_FALSE);
			p.setHqlConditionExpression(sql);
			p.setValues(list.toArray());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return monitorResultsDao.getCounts(p);
	}
	
	/* 查询监控结果总集合
	 * (non-Javadoc)
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#findMonitorResultsPageList(net.cedu.entity.basesetting.MonitorResults, net.cedu.model.page.PageResult)
	 */
	@SuppressWarnings("unchecked")
	public List<MonitorResults> findMonitorResultsPageList(MonitorResults monitorResults,
			PageResult<MonitorResults> pr) throws Exception{
		List<MonitorResults> resultslist = null;
		
		try {
			PageParame p = new PageParame(pr);
			String sql = "";
			List list = new ArrayList();
			if(monitorResults!=null){
				//编码
				if(monitorResults.getCode()!=null&&!("").equals(monitorResults.getCode())){
					sql+=" and code like "+Constants.PLACEHOLDER;
					list.add("%"+monitorResults.getCode()+"%");
				}
				//监控结果名称
				if(monitorResults.getName()!=null&&!("").equals(monitorResults.getName())){
					sql+=" and name like "+Constants.PLACEHOLDER;
					list.add("%"+monitorResults.getName()+"%");
				}
			}
			sql+=" and deleteFlag="+Constants.PLACEHOLDER;
			list.add(Constants.DELETE_FALSE);
			
			p.setHqlConditionExpression(sql);
			p.setValues(list.toArray());
			
			Long[] resultIds = monitorResultsDao.getIDs(p);
			
			if(resultIds != null&& resultIds.length>0){
				resultslist = new ArrayList<MonitorResults>();
				MonitorResults results=null;
				for (Long id : resultIds) {
					results = this.findbyresultid(Integer.parseInt(id.toString()));
					if(null != results){
						resultslist.add(results);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultslist;
	}
	
	/*
	 * 查询学习中心下的监控结果集合
	 * @see net.cedu.biz.basesetting.MonitorResultsBiz#findBranchMonitorResults()
	 */
	public List<MonitorResults> findBranchMonitorResults()throws Exception{
		String hql = "";
		List<Object> objs = new ArrayList<Object>();
		hql+=" and id not in ( "+Constants.PLACEHOLDER + " ) ";
		objs.add("$"+Constants.STU_MONITOR_STATUS_ZHENG_CHANG+","+Constants.STU_MONITOR_RESULTS_SOURCE_ID);
		return monitorResultsDao.getByProperty(hql, objs);
	}
}
