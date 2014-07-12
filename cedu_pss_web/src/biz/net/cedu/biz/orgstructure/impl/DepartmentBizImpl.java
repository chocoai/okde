package net.cedu.biz.orgstructure.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.orgstructure.DepartmentBiz;
import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.dao.orgstructure.DepartmentDao;
import net.cedu.entity.orgstructure.Department;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentBizImpl implements DepartmentBiz 
{
	@Autowired
	private DepartmentDao departmentDao;	
	@Autowired
	private JurisdictionBiz jurisdictionBiz;

	/**
	 * 添加部门
	 * 
	 * @param branch
	 * @throws Exception
	 */
	public void addNew(Department department) throws Exception {
		if (0 >= findCountByConditionForAdd(department))
			departmentDao.save(department);
	}

	/**
	 * 修改部门
	 * 
	 * @param Department
	 * @throws Exception
	 */
	public void modify(Department department) throws Exception {
		departmentDao.update(department);
	}

	/**
	 * 根据ID删除部门
	 * 
	 * @param Department
	 * @throws Exception
	 */
	public void deleteConfigById(int id) throws Exception {
		departmentDao.deleteConfig(id);
	}

	/**
	 * 根据ID查询部门
	 */
	public Department findDepartmentById(int id) throws Exception {
		return departmentDao.findById(id);
	}
	
	/**
	 * 根据学习中心ID查询条数_综合查询方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public int findCountComposite(Department departments, BranchEnum branchEnum)
			throws Exception {
		Department old = departmentDao.findById(departments.getParent_Node());
		Department department = new Department();
		if(null!=old)
		{
			department.setLogicNode(getLogicNode(old.getLogicNode()));
			department.setId(old.getId());
			if (null != departments) {
				department.setName(departments.getName());
			}
		}
		department.setOfficeId(departments.getOfficeId());
		department.setOrders(departments.getOrders());
		int count = 0;
		switch (branchEnum) {
		case Default:
			;
		case Root:
			;
		default:
			count = this.findCountByCondition(department);
			break;
		}
		return count;
	}
	
	/**
	 * 根据条件查找学习中心数量_下属学习中心
	 */
	private int findCountByCondition(Department department) throws Exception {
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		// 逻辑节点
		if (null != department.getLogicNode() && 0 < department.getLogicNode().length()) {
			hql += " and logicNode like " + Constants.PLACEHOLDER;
			list.add(department.getLogicNode());
		}
		// 名称
		if (StringUtils.isNotBlank(department.getName())) {
			hql += " and name like " + Constants.PLACEHOLDER;
			list.add("%" + department.getName() + "%");
		}
		// 排序
		if (0<department.getOrders()) {
			hql += " and orders=" + Constants.PLACEHOLDER;
			list.add(department.getOrders());
		}
		hql += " and officeId=" + Constants.PLACEHOLDER;
		list.add(department.getOfficeId());
		return departmentDao.findCountByProperty(hql, list);
	}
	
	
	/**
	 * 根据学习中心ID查询集合_综合查询方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Department> findListComposite(Department departments,
			PageResult<Department> pr, BranchEnum branchEnum) throws Exception {
		Department old = departmentDao.findById(departments.getParent_Node());
		Department department = new Department();
		if(null!=old)
		{
			department.setLogicNode(getLogicNode(old.getLogicNode()));
			department.setId(old.getId());
			if (null != departments) {
				department.setName(departments.getName());
			}
		}
		department.setOfficeId(departments.getOfficeId());
		department.setOrders(departments.getOrders());
		List<Department> list = null;
		switch (branchEnum) {
		case Default:
			;

		case Root:
			;

		default:
			if (null != pr) {
				list = this.findPageListByCondition(pr, department);
			} else
				list = this.findListByConditionPri(department);
			break;
		}
		return list;
	}
	
	private String getLogicNode(String logicNode) {
		logicNode = logicNode.substring(1);
		logicNode = logicNode.substring(0, logicNode.length() - 1);
		return "%\\_" + logicNode + "\\_%";
	}
	
	/**
	 * 根据条件查找学习中心列表_分页(下属中心)
	 */
	private List<Department> findPageListByCondition(PageResult<Department> pr,
			Department department) {
		try {
			List<Department> branchs = null;

			PageParame p = new PageParame(pr);
			String hql = "";
			List<Object> list = new ArrayList<Object>();
			
			// 逻辑节点
			if (null != department.getLogicNode()
					&& 0 < department.getLogicNode().length()) {
				hql += " and logicNode like " + Constants.PLACEHOLDER;
				list.add(department.getLogicNode());
			}
			// 名称
			if (StringUtils.isNotBlank(department.getName())) {
				hql += " and name like " + Constants.PLACEHOLDER;
				list.add("%" + department.getName() + "%");
			}
			
			// 排序
			if (0<department.getOrders()) {
				hql += " and orders=" + Constants.PLACEHOLDER;
				list.add(department.getOrders());
			}
		
			hql += " and officeId=" + Constants.PLACEHOLDER;
			list.add(department.getOfficeId());
			
			
			hql+=" order by orders asc";
			
			list.add(department.getId());
			if (!hql.equals("")) {
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			}
			// Ids集合
			Long[] branchIds = departmentDao.getIDs(p);
			if (branchIds != null && branchIds.length != 0) {
				branchs = new ArrayList<Department>();
				for (int i = 0; i < branchIds.length; i++) {
					// 内存获取
					Department departmentObj = departmentDao.findById(Integer
							.valueOf(branchIds[i].toString()));
					if (departmentObj != null) {
						departmentObj.setParent(departmentDao.findById(departmentObj.getParent_Node()));
						branchs.add(departmentObj);
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
	 * 根据条件查找部门
	 */
	public List<Department> findListByCondition(Department department) throws Exception {
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		// 中心名称
		if (StringUtils.isNotBlank(department.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			list.add(department.getName());
		}
		hql+=" and officeId = "+Constants.PLACEHOLDER;
		list.add(department.getOfficeId());
		if(0<department.getParent_Node())
		{
			hql += " and parent_Node = " + Constants.PLACEHOLDER;
			list.add(department.getParent_Node());
		}

		return departmentDao.getByProperty(hql, list);
	}
	
	/**
	 * 根据条件查找部门列表(本部门及下属部门)
	 */
	private List<Department> findListByConditionPri(Department department) {
		try {
			List<Department> branchs = null;

			PageParame p = new PageParame();
			String hql = "";
			List<Object> list = new ArrayList<Object>();

			
			// 逻辑节点
			if (null != department.getLogicNode()
					&& 0 < department.getLogicNode().length()
					&& -1 == department.getLogicNode().indexOf(
							"" + BranchEnum.Root.value())) {
				hql += " and logicNode like " + Constants.PLACEHOLDER;
				list.add(department.getLogicNode());
			}
			hql += " and officeId=" + Constants.PLACEHOLDER;
			list.add(department.getOfficeId());
			
			hql+=" order by orders asc";
			
			if (!hql.equals("")) {
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			}
			// Ids集合
			Long[] branchIds = departmentDao.getIDs(p);
			if (branchIds != null && branchIds.length != 0) {
				branchs = new ArrayList<Department>();
				for (int i = 0; i < branchIds.length; i++) {
//					// 内存获取
					Department departmentObj = departmentDao.findById(Integer
							.valueOf(branchIds[i].toString()));
					if (departmentObj != null) {
						departmentObj.setParent(departmentDao.findById(departmentObj.getParent_Node()));
						branchs.add(departmentObj);
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
	 * 根据条件查找部门数量_添加校验重复性用
	 */
	private int findCountByConditionForAdd(Department department) throws Exception {
		String hql = "";
		List<Object> list = new ArrayList<Object>();
		// 部门名称
		if (StringUtils.isNotBlank(department.getName())) {
			hql += " and name = " + Constants.PLACEHOLDER;
			list.add(department.getName());
			hql+=" and officeId = "+Constants.PLACEHOLDER;
			list.add(department.getOfficeId());
		}		
		return departmentDao.findCountByProperty(hql, list);
	}

	public List<Department> findDepartmentsByUserIdAndBranchId(int userId,
			int branchId) throws Exception {
		String departmentIds=jurisdictionBiz.findByUserId(userId);
		return departmentDao.getByProperty(" and id in("+Constants.PLACEHOLDER+") and officeId="+Constants.PLACEHOLDER, new Object[]{"$"+departmentIds,branchId});
	}

	public List<Department> findDepartmentsByDepartmentIds(String departmentIds)
			throws Exception {
		return departmentDao.getByProperty(" and id in ("+Constants.PLACEHOLDER+")", new Object[]{"$"+departmentIds});
	}
}
