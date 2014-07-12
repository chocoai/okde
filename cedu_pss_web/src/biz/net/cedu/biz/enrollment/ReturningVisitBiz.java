package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.ReturningVisit;
import net.cedu.model.page.PageResult;

/**
 * 回访记录
 * @author hxj
 *
 */
public interface ReturningVisitBiz {

	/**
	 * 增加一条回访记录
	 * @param returningVisit
	 * @return
	 */
	public Object addReturningVisit(ReturningVisit returningVisit);
	
	/**
	 * 查询回访记录总数量
	 * @param returningVisit
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findReturningVisitPageCount(ReturningVisit returningVisit, PageResult<ReturningVisit> pr)
		throws Exception;
	
	/**
	 * 查询回访记录总集合
	 * @param returningVisit
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<ReturningVisit> findReturningVisitPageList(ReturningVisit returningVisit,
			PageResult<ReturningVisit> pr) throws Exception;
}
