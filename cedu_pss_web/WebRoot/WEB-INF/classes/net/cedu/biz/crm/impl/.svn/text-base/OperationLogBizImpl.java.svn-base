package net.cedu.biz.crm.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.crm.OperationLogBiz;
import net.cedu.common.Constants;
import net.cedu.dao.crm.OperationLogDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.crm.OperationLog;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 操作日志实现类
 * 
 * @author yangdongdong
 * 
 */
@Service
public class OperationLogBizImpl implements OperationLogBiz {
	@Autowired
	private OperationLogDao operationLogDao;
	@Autowired
	private UserBiz userBiz;

	/*
	 * 增加操作日志
	 * 
	 * @see
	 * net.cedu.biz.crm.OperationLogBiz#addOperationLog(net.cedu.entity.crm.
	 * OperationLog)
	 */
	public void addOperationLog(OperationLog operationLog) throws Exception {
		operationLogDao.save(operationLog);
	}

	/*
	 * 删除操作日志
	 * 
	 * @see net.cedu.biz.crm.OperationLogBiz#deleteOperationLogById(int)
	 */
	public void deleteOperationLogById(int id) throws Exception {
		operationLogDao.deleteById(id);

	}

	/*
	 * 查看操作日志
	 * 
	 * @see net.cedu.biz.crm.OperationLogBiz#findOperationLogById(int)
	 */
	public OperationLog findOperationLogById(int id) throws Exception {

		return operationLogDao.findById(id);
	}

	/*
	 * 操作日志总条数
	 * 
	 * @see
	 * net.cedu.biz.crm.OperationLogBiz#findOperationLogsPageCount(net.cedu.
	 * entity.crm.OperationLog, net.cedu.model.page.PageResult)
	 */
	public int findOperationLogsPageCount(OperationLog operationLog,
			PageResult<OperationLog> pr) throws Exception {
		PageParame p = new PageParame(pr);
//		p.setOrder(pr.getOrder());
		if (operationLog != null && operationLog.getContent() != null
				&& !operationLog.getContent().equals("")) {
			p.setHqlConditionExpression("and (content like "
					+ Constants.PLACEHOLDER + " or ip like "
					+ Constants.PLACEHOLDER + " )");
			p.setValues(new Object[] { "%" + operationLog.getContent() + "%",
					"%" + operationLog.getContent() + "%" });
		}
		return operationLogDao.getCounts(p);
	}

	/*
	 * 查询操作日志集合
	 * 
	 * @see
	 * net.cedu.biz.crm.OperationLogBiz#findOperationLogsPageList(net.cedu.entity
	 * .crm.OperationLog, net.cedu.model.page.PageResult)
	 */
	public List<OperationLog> findOperationLogsPageList(
			OperationLog operationLog, PageResult<OperationLog> pr)
			throws Exception {
		List<OperationLog> operationLogs = null;
		// Ids集合
		PageParame p = new PageParame(pr);
		// 构造查询条件
//		p.setCurrentPageIndex(pr.getCurrentPageIndex());
//		p.setPageSize(pr.getPageSize());
//		p.setOrder(pr.getOrder());
//		p.setSort(pr.getSort());

		if (operationLog != null && operationLog.getContent() != null
				&& !operationLog.getContent().equals("")) {
			p.setHqlConditionExpression("and (content like "
					+ Constants.PLACEHOLDER + " or ip like "
					+ Constants.PLACEHOLDER + " )");
			p.setValues(new Object[] { "%" + operationLog.getContent() + "%",
					"%" + operationLog.getContent() + "%" });
		}

		Long[] opLongs = operationLogDao.getIDs(p);

		if (opLongs != null && opLongs.length != 0) {
			operationLogs = new ArrayList<OperationLog>();
			OperationLog o = null;
			for (Long id : opLongs) {
				if (null != (o = operationLogDao.findById(Integer.parseInt(id
						.toString())))) {
					// 查询创建者
					User user = userBiz.findUserById(o.getCreateId());
					if (null != user) {
						o.setCreateName(user.getFullName());
					}
					operationLogs.add(o);
				}
			}
		}
		return operationLogs;
	}

}
