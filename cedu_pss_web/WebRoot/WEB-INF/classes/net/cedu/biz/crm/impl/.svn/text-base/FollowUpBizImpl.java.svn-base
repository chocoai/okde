package net.cedu.biz.crm.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.crm.FollowUpBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.admin.UserDao;
import net.cedu.dao.crm.FollowUpDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.crm.FollowUp;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 跟进biz
 * 
 * @author yangdongdong
 * 
 */

@Service
public class FollowUpBizImpl implements FollowUpBiz {

	@Autowired
	private FollowUpDao followUpDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BranchDao branchDao;

	/*
	 * 增加跟进纪录
	 * 
	 * @see
	 * net.cedu.biz.crm.FollowUpBiz#addFollowUp(net.cedu.entity.crm.FollowUp)
	 */
	public void addFollowUp(FollowUp followUp) throws Exception {
		followUpDao.save(followUp);
	}

	/*
	 * 删除跟进纪录
	 * 
	 * @see net.cedu.biz.crm.FollowUpBiz#deleteFollowUpById(int)
	 */
	public void deleteFollowUpById(int id) throws Exception {
		followUpDao.deleteById(id);
	}

	/*
	 * 查询跟进纪录
	 * 
	 * @see net.cedu.biz.crm.FollowUpBiz#findFollowUpById(int)
	 */
	public FollowUp findFollowUpById(int id) throws Exception {
		return followUpDao.findById(id);
	}

	/*
	 * 查询跟进纪录条数
	 * 
	 * @see
	 * net.cedu.biz.crm.FollowUpBiz#findFollowUpsPageCount(net.cedu.entity.crm
	 * .FollowUp, net.cedu.model.page.PageResult)
	 */
	public int findFollowUpsPageCount(FollowUp followUp, PageResult<FollowUp> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		String paramsString = "";
		List list = new ArrayList();
		if (followUp.getStudentId() != -1) {
			paramsString += " and studentId = " + Constants.PLACEHOLDER;
			list.add(followUp.getStudentId());
		}
		if (followUp.getStatusId() != 0) {
			paramsString += " and statusId = " + Constants.PLACEHOLDER;
			list.add(followUp.getStatusId());
		}

		p.setHqlConditionExpression(paramsString);
		p.setValues(list.toArray());
		return followUpDao.getCounts(p);
	}

	/*
	 * 查询跟进集合
	 * 
	 * @see
	 * net.cedu.biz.crm.FollowUpBiz#findFollowUpsPageList(net.cedu.entity.crm
	 * .FollowUp, net.cedu.model.page.PageResult)
	 */
	public List<FollowUp> findFollowUpsPageList(FollowUp followUp,
			PageResult<FollowUp> pr) throws Exception {
		List<FollowUp> followUps = null;
		// Ids集合
		PageParame p = new PageParame(pr);
//		p.setCurrentPageIndex(pr.getCurrentPageIndex());
//		p.setPageSize(pr.getPageSize());
//		p.setOrder(pr.getOrder());
//		p.setSort(pr.getSort());
		String paramsString = "";
		List list = new ArrayList();
		if (followUp.getStudentId() != -1) {
			paramsString += " and studentId = " + Constants.PLACEHOLDER;
			list.add(followUp.getStudentId());
		}
		if (followUp.getStatusId() != 0) {
			paramsString += " and statusId = " + Constants.PLACEHOLDER;
			list.add(followUp.getStatusId());
		}

		p.setHqlConditionExpression(paramsString);
		p.setValues(list.toArray());
		Long[] followIds = followUpDao.getIDs(p);

		if (followIds != null && followIds.length != 0) {
			followUps = new ArrayList<FollowUp>();
			FollowUp f = null;
			for (Long id : followIds) {
				if (null != (f = followUpDao.findById(Integer.parseInt(id
						.toString())))) {
					User user=userDao.findById(f.getCreatorId());
					if (user!=null) {
						f.setFollowUpName(user.getFullName());
						if(branchDao.findById(user.getOrgId())!=null)
						{
							f.setFollowUpBranchName(branchDao.findById(user.getOrgId()).getName());
						}
					}
					
					followUps.add(f);
				}
			}
		}
		return followUps;

	}

	/*
	 * 根据学生id查询学习中心首次跟进记录
	 * @see net.cedu.biz.crm.FollowUpBiz#findFirstFollowUpByStudentId(int)
	 */
	public FollowUp findFirstFollowUpByStudentId(int studentId)
			throws Exception {
		return followUpDao.findFirstFollowUpByStudentId(studentId);
	}

	/*
	 * 根据学生id查询学习中心最新跟进记录
	 * @see net.cedu.biz.crm.FollowUpBiz#findLatestFollowUpByStudentId(int)
	 */
	public FollowUp findLatestFollowUpByStudentId(int studentId)
			throws Exception {
		return followUpDao.findLatestFollowUpByStudentId(studentId);
	}
	
}
