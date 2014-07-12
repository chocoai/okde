package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.MonitorResultsBiz;
import net.cedu.biz.enrollment.ReturningVisitBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.ReturningVisitDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.basesetting.MonitorResults;
import net.cedu.entity.enrollment.ReturningVisit;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 回访记录
 * @author hxj
 *
 */
@Service
public class ReturningVisitBizImpl implements ReturningVisitBiz{

	@Autowired
	private ReturningVisitDao returningVisitDao;
	@Autowired
	private MonitorResultsBiz monitorResultsBiz;
	@Autowired
	private UserBiz userBiz;
	
	/*增加一条回访记录
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.ReturningVisitBiz#addReturningVisit(net.cedu.entity.enrollment.ReturningVisit)
	 */
	public Object addReturningVisit(ReturningVisit returningVisit){
		return returningVisitDao.save(returningVisit);
	}
	
	/* 查询回访记录总数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.ReturningVisitBiz#findReturningVisitPageCount(net.cedu.entity.enrollment.ReturningVisit, net.cedu.model.page.PageResult)
	 */
	@SuppressWarnings("unchecked")
	public int findReturningVisitPageCount(ReturningVisit returningVisit, PageResult<ReturningVisit> pr)
		throws Exception{
		
		PageParame p=null;
		try {
			
			p = new PageParame(pr);
			String sql = "";
			List list  = new ArrayList();
			
			if (returningVisit != null) {
				
				//学生ID
				if (returningVisit.getStudentId() != 0) {
					sql += " and studentId =" + Constants.PLACEHOLDER;
					list.add(returningVisit.getStudentId());
				}
				//监控结果
				if (returningVisit.getMonitorResults() != 0) {
					sql += " and monitorResults =" + Constants.PLACEHOLDER;
					list.add(returningVisit.getMonitorResults());
				}
				//回访结果
				if (returningVisit.getVisitResults() != 0) {
					sql += " and visitResults =" + Constants.PLACEHOLDER;
					list.add(returningVisit.getVisitResults());
				}
				//类型
				if (returningVisit.getTypes() > 0) {
					sql = " and types =" + Constants.PLACEHOLDER;
					list.add(returningVisit.getTypes());
				}
			}
			sql += " and deleteFlag=" + Constants.PLACEHOLDER;
			list.add(Constants.DELETE_FALSE);
			p.setHqlConditionExpression(sql);
			p.setValues(list.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returningVisitDao.getCounts(p);
	}
	
	/* 查询回访记录总集合
	 * (non-Javadoc)
	 * @see net.cedu.biz.enrollment.ReturningVisitBiz#findReturningVisitPageList(net.cedu.entity.enrollment.ReturningVisit, net.cedu.model.page.PageResult)
	 */
	@SuppressWarnings("unchecked")
	public List<ReturningVisit> findReturningVisitPageList(ReturningVisit returningVisit,
			PageResult<ReturningVisit> pr) throws Exception{
		List<ReturningVisit> visitlist = null;
		try {
			PageParame p = new PageParame(pr);
			String sql = "";
			List list = new ArrayList();
			if(returningVisit!=null){
				
				//学生ID
				if(returningVisit.getStudentId()!=0){
					sql+=" and studentId ="+Constants.PLACEHOLDER;
					list.add(returningVisit.getStudentId());
				}
				//监控结果
				if(returningVisit.getMonitorResults()!=0){
					sql+=" and monitorResults ="+Constants.PLACEHOLDER;
					list.add(returningVisit.getMonitorResults());
				}
				//回访结果
				if(returningVisit.getVisitResults()!=0){
					sql+=" and visitResults ="+Constants.PLACEHOLDER;
					list.add(returningVisit.getVisitResults());
				}
				//类型
				if(returningVisit.getTypes()>0){
					sql=" and types ="+Constants.PLACEHOLDER;
					list.add(returningVisit.getTypes());
				}
			}
			sql+=" and deleteFlag="+Constants.PLACEHOLDER+" order by createdTime desc";
			list.add(Constants.DELETE_FALSE);
			
			p.setHqlConditionExpression(sql);
			p.setValues(list.toArray());
			
			Long[] visitIds = returningVisitDao.getIDs(p);
			
			if(visitIds != null&& visitIds.length>0){
				visitlist = new ArrayList<ReturningVisit>();
				ReturningVisit returnvisit=null;
				MonitorResults monitorrlt=null;
				User user=null;
				for (Long id : visitIds) {
					returnvisit = returningVisitDao.findById(Integer.parseInt(id.toString()));
					if(null != returnvisit){
						//增加回访人
						user=userBiz.findUserById(returnvisit.getCreatorId());
						if(user!=null){
							returnvisit.getStrParams().put("returningVisitName", user.getFullName());
						}else{
							returnvisit.getStrParams().put("returningVisitName", "--");
						}
						
						monitorrlt = monitorResultsBiz.findbyresultid(returnvisit.getMonitorResults());
						if(null!=monitorrlt&&null!=monitorrlt.getName()&&!("").equals(monitorrlt.getName())){
							returnvisit.setMonitorname(monitorrlt.getName());
						}
						visitlist.add(returnvisit);
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return visitlist;
	}
}
