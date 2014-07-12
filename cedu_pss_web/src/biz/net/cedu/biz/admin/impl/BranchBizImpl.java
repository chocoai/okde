package net.cedu.biz.admin.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.AreaManagerBranchBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.dao.admin.BranchDao;
import net.cedu.entity.admin.AreaManagerBranch;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学习中心 业务实现类
 * 
 * @author Jack
 * 
 */
@Service
public class BranchBizImpl implements BranchBiz {
	@Autowired
	private BranchDao branchDao; // 学习中心接口

	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private UserBiz userBiz; // 用户Biz
	@Autowired
	private GlobalEnrollBatchBiz globalEnrollBatchBiz; //全局批次
	

	@Autowired
	private AreaManagerBranchBiz areaManagerBranchBiz; // 区域经理与学习中心关系Biz
	@Autowired
	private BranchEnrollQuotaBiz branchEnrollQuotaBiz; // 学习中心招生指标Biz

	/**
	 * 添加学习中心
	 * 
	 * @param branch
	 * @throws Exception
	 */
	public void addNew(Branch branch) throws Exception {
		if (0 >= findCountByConditionForAdd(branch))
			branchDao.save(branch);
	}

	/**
	 * 修改学习中心
	 * 
	 * @param branch
	 * @throws Exception
	 */
	public void modify(Branch branch) throws Exception {
		branch.setUpdatedTime(new Date());
		branchDao.update(branch);
	}

	/**
	 * 根据ID删除学习中心
	 * 
	 * @param branch
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception {
		branchDao.deleteConfig(id);
	}

	/**
	 * 根据ID查询学习中心
	 */
	public Branch findBranchById(int id) throws Exception {
		return branchDao.findById(id);
	}

	/**
	 * 查询学习中心集合(供其他模块调用)
	 * 
	 * @return 返回不包括总部的其他所有正常数据的学习中心
	 * @throws Exception
	 */
	public List<Branch> findBranchForModel() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		hqlcon += " and id > " + Constants.PLACEHOLDER;
		paramList.add(BranchEnum.Admin.value());
		// 删除标记
		hqlcon += " and deleteFlag=" + Constants.PLACEHOLDER;
		paramList.add(Constants.DELETE_FALSE);
		hqlcon += " order by logicNode";
		return branchDao.getByProperty(hqlcon, paramList);
	}

	/**
	 * 根据学习中心ID查询其及下属学习中心集合
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findListById(int id) throws Exception {
		return this.findListComposite(id, null, null, BranchEnum.Default);
	}

	/*
	 * 查询区域经理没有分配的学习中心
	 * 
	 * @see net.cedu.biz.admin.BranchBiz#findAllNotInIds(java.lang.String)
	 */
	public List<Branch> findAllNotInIds(String branchIds) throws Exception {
		List<Branch> branchlst = null;

		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (branchIds != null && !branchIds.equals("")) {
			hqlparam += "and id not in (" + BranchEnum.Root.value() + ","
					+ BranchEnum.Admin.value() + "," + Constants.PLACEHOLDER
					+ ")";
			list.add("$" + branchIds);
		} else {
			hqlparam += "and id not in (" + BranchEnum.Root.value() + ","
					+ Constants.PLACEHOLDER + ")";
			list.add("$" + BranchEnum.Admin.value());
		}
		hqlparam += "and deleteFlag=" + Constants.PLACEHOLDER + ")";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		// Ids集合
		Long[] branchids = branchDao.getIDs(p);
		if (branchids != null && branchids.length != 0) {
			branchlst = new ArrayList<Branch>();
			for (int i = 0; i < branchids.length; i++) {
				// 内存获取
				Branch branchObj = branchDao.findById(Integer
						.valueOf(branchids[i].toString()));
				branchlst.add(branchObj);
			}
		}
		return branchlst;

	}

	/*
	 * 获取学习中心Ids
	 * 
	 * @see
	 * net.cedu.biz.admin.BranchBiz#findBranchIds(net.cedu.model.page.PageParame
	 * )
	 */
	public Long[] findBranchIds(PageParame p) {
		return branchDao.getIDs(p);
	}

	/**
	 * 根据条件查找学习中心数量_添加校验重复性用
	 */
	private int findCountByConditionForAdd(Branch branch) throws Exception {
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		// 中心名称
		if (StringUtils.isNotBlank(branch.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			list.add(branch.getName());
		}
		if (0 > list.size()) {
			// 简称
			if (StringUtils.isNotBlank(branch.getShortName())) {
				hql += " and shortName = " + Constants.PLACEHOLDER;
				list.add(branch.getShortName());
			}
		} else {
			// 简称
			if (StringUtils.isNotBlank(branch.getShortName())) {
				hql += " or shortName = " + Constants.PLACEHOLDER;
				list.add(branch.getShortName());
			}
		}
		return branchDao.findCountByProperty(hql, list);
	}

	/**
	 * 根据条件查找学习中心
	 */
	public List<Branch> findListByCondition(Branch branch) throws Exception {
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		// 中心名称
		if (StringUtils.isNotBlank(branch.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			list.add(branch.getName());
		}

		hql += " and parentId = " + Constants.PLACEHOLDER;
		list.add(branch.getParentId());

		return branchDao.getByProperty(hql, list);
	}

	/**
	 * 根据学习中心ID查询条数_综合查询方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public int findCountComposite(int id, Branch branchs, BranchEnum branchEnum)
			throws Exception {
		Branch old = branchDao.findById(id);
		Branch branch = new Branch();
		branch.setLogicNode(getLogicNode(old.getLogicNode()));
		branch.setLevel(old.getLevel());
		branch.setId(old.getId());
		if (null != branchs) {
			branch.setName(branchs.getName());
			branch.setShortName(branchs.getShortName());
		}

		int count = 0;
		switch (branchEnum) {
		case Default:
			;
		case Root:
			;
		default:
			count = this.findCountByCondition(branch);
			break;
		}
		return count;
	}

	private String getLogicNode(String logicNode) {
		logicNode = logicNode.substring(1);
		logicNode = logicNode.substring(0, logicNode.length() - 1);
		return "%\\_" + logicNode + "\\_%";
	}

	/**
	 * 根据条件查找学习中心数量_下属学习中心
	 */
	private int findCountByCondition(Branch branch) throws Exception {
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		// 层级
		if (0 != branch.getLevel()) {
			hql += " and (level>=" + Constants.PLACEHOLDER + " and level<"
					+ Constants.PLACEHOLDER + ")";
			list.add(branch.getLevel());
			list.add(branch.getLevel() + BranchEnum.Level.value() + 1);
		}
		// 逻辑节点
		if (null != branch.getLogicNode() && 0 < branch.getLogicNode().length()) {
			hql += " and logicNode like " + Constants.PLACEHOLDER;
			list.add(branch.getLogicNode());
		}
		// 名称
		if (StringUtils.isNotBlank(branch.getName())) {
			hql += " and name like " + Constants.PLACEHOLDER;
			list.add("%" + branch.getName() + "%");
		}
		// 简称
		if (StringUtils.isNotBlank(branch.getShortName())) {
			hql += " and shortName like " + Constants.PLACEHOLDER;
			list.add("%" + branch.getShortName() + "%");
		}
		hql += " and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(branch.getDeleteFlag());
		hql += " and id<>" + Constants.PLACEHOLDER;
		list.add(branch.getId());
		return branchDao.findCountByProperty(hql, list);
	}

	/**
	 * 根据学习中心ID查询集合_综合查询方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Branch> findListComposite(int id, Branch branchs,
			PageResult<Branch> pr, BranchEnum branchEnum) throws Exception {
		Branch old = branchDao.findById(id);
		Branch branch = new Branch();
		branch.setLogicNode(getLogicNode(old.getLogicNode()));
		branch.setLevel(old.getLevel());
		branch.setId(old.getId());
		if (null != branchs) {
			branch.setName(branchs.getName());
			branch.setShortName(branchs.getShortName());
		}
		List<Branch> list = null;
		switch (branchEnum) {
		case Default:
			;

		case Root:
			;

		default:
			if (null != pr) {
				list = this.findPageListByCondition(pr, branch);
			} else
				list = this.findListByConditionPri(branch);
			break;
		}
		return list;
	}

	/**
	 * 根据条件查找学习中心列表_分页(下属中心)
	 */
	private List<Branch> findPageListByCondition(PageResult<Branch> pr,
			Branch branch) {
		try {
			List<Branch> branchs = null;

			PageParame p = new PageParame(pr);
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			// 层级
			if (0 != branch.getLevel()) {
				hql += " and (level>=" + Constants.PLACEHOLDER + " and level<"
						+ Constants.PLACEHOLDER + ")";
				list.add(branch.getLevel());
				list.add(branch.getLevel() + BranchEnum.Level.value() + 1);
			}
			// 逻辑节点
			if (null != branch.getLogicNode()
					&& 0 < branch.getLogicNode().length()) {
				hql += " and logicNode like " + Constants.PLACEHOLDER;
				list.add(branch.getLogicNode());
			}
			// 名称
			if (StringUtils.isNotBlank(branch.getName())) {
				hql += " and name like " + Constants.PLACEHOLDER;
				list.add("%" + branch.getName() + "%");
			}
			// 简称
			if (StringUtils.isNotBlank(branch.getShortName())) {
				hql += " and shortName like " + Constants.PLACEHOLDER;
				list.add("%" + branch.getShortName() + "%");
			}
			hql += " and deleteFlag=" + Constants.PLACEHOLDER;
			list.add(branch.getDeleteFlag());
			hql += " and id<>" + Constants.PLACEHOLDER;
			list.add(branch.getId());
			if (!hql.equals("")) {
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			}
			// Ids集合
			Long[] branchIds = branchDao.getIDs(p);
			if (branchIds != null && branchIds.length != 0) {
				branchs = new ArrayList<Branch>();
				for (int i = 0; i < branchIds.length; i++) {
					// 内存获取
					Branch branchObj = branchDao.findById(Integer
							.valueOf(branchIds[i].toString()));
					if (branchObj != null) {
						branchObj.setCreator(userBiz.findUserById(branchObj
								.getCreatorId()));
						branchObj.setUpdater(userBiz.findUserById(branchObj
								.getCreatorId()));
						branchObj.setParent(branchDao.findById(branchObj
								.getParentId()));
						branchs.add(branchObj);
					}
				}
			}
			return branchs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据条件查找学习中心列表(本中心及下属中心)
	 */
	private List<Branch> findListByConditionPri(Branch branch) {
		try {
			List<Branch> branchs = null;

			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();

			// 层级
			if (0 != branch.getLevel()
					&& -1 == branch.getLogicNode().indexOf(
							"" + BranchEnum.Root.value())) {
				hql += " and (level>=" + Constants.PLACEHOLDER + " and level<"
						+ Constants.PLACEHOLDER + ")";
				list.add(branch.getLevel());
				list.add(branch.getLevel() + BranchEnum.Level.value() + 1);
			}
			// 逻辑节点
			if (null != branch.getLogicNode()
					&& 0 < branch.getLogicNode().length()
					&& -1 == branch.getLogicNode().indexOf(
							"" + BranchEnum.Root.value())) {
				hql += " and logicNode like " + Constants.PLACEHOLDER;
				list.add(branch.getLogicNode());
			}
			hql += " and deleteFlag=" + Constants.PLACEHOLDER;
			list.add(branch.getDeleteFlag());

			if (!hql.equals("")) {
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			}
			// Ids集合
			Long[] branchIds = branchDao.getIDs(p);
			if (branchIds != null && branchIds.length != 0) {
				branchs = new ArrayList<Branch>();
				for (int i = 0; i < branchIds.length; i++) {
					// 内存获取
					Branch branchObj = branchDao.findById(Integer
							.valueOf(branchIds[i].toString()));
					if (branchObj != null) {
						branchObj.setCreator(userBiz.findUserById(branchObj
								.getCreatorId()));
						branchObj.setUpdater(userBiz.findUserById(branchObj
								.getCreatorId()));
						branchObj.setParent(branchDao.findById(branchObj
								.getParentId()));
						branchs.add(branchObj);
					}
				}
			}
			return branchs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 查询学习中心
	 * 
	 * @see net.cedu.biz.admin.BranchBiz#findBranchByName(java.lang.String)
	 */
	public Branch findBranchByName(String name) throws Exception {

		return branchDao.getObjByProperty("name", name);
	}

	/*
	 * 查询所有学习中心(未删除)
	 * 
	 * @see net.cedu.biz.admin.BranchBiz#findBranchAllByDeleteFlag()
	 */
	public List<Branch> findBranchAllByDeleteFlag() throws Exception {
		String hqlcon = "";
		List<Object> paramList = new ArrayList<Object>();
		// 筛选超级用户
		hqlcon += " and type !=" + Constants.PLACEHOLDER;
		paramList.add(BranchEnum.Root.value());
		// 删除标记
		hqlcon += " and deleteFlag=" + Constants.PLACEHOLDER;
		paramList.add(Constants.DELETE_FALSE);
		return branchDao.getByProperty(hqlcon, paramList);
	}

	/*
	 * 查询学习中心按照多个Id
	 * 
	 * @see net.cedu.biz.admin.BranchBiz#findAllByIds(java.lang.String)
	 */
	public List<Branch> findAllByIds(String branchIds) throws Exception {

		List<Branch> branchlst = null;

		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (branchIds != null && !branchIds.equals("")) {
			hqlparam += "and id in (" + Constants.PLACEHOLDER + ")";
			list.add("$" + branchIds);
		}
		hqlparam += "and deleteFlag=" + Constants.PLACEHOLDER + ")";
		list.add(Constants.DELETE_FALSE);

		// Ids集合
		branchlst = branchDao.getByProperty(hqlparam, list);
		return branchlst;
	}

}
