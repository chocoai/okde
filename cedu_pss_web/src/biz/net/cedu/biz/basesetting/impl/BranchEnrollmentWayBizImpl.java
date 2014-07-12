package net.cedu.biz.basesetting.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.BranchEnrollmentWayBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.BranchEnrollmentWayDao;
import net.cedu.entity.basesetting.BranchEnrollmentWay;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学习中心授权市场途径业务层实现类
 * @author Jack
 *
 */
@Service
public class BranchEnrollmentWayBizImpl implements BranchEnrollmentWayBiz {

	@Autowired
	private BranchEnrollmentWayDao branchEnrollmentWayDao;
	
	/**
	 * 添加学习中心授权市场途径
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(BranchEnrollmentWay branchEnrollmentWay)throws Exception
	{
		branchEnrollmentWayDao.save(branchEnrollmentWay);
	}
	
	/**
	 * 修改学习中心授权市场途径
	 * @param BranchEnrollmentWay
	 * @throws Exception
	 */
	public void modify(BranchEnrollmentWay branchEnrollmentWay)throws Exception
	{
		BranchEnrollmentWay old=findByBranchId(branchEnrollmentWay.getBranchId());
		if(null==old)
		{
			addNew(branchEnrollmentWay);
		}
		else
		{
			old.setEnrollmentWays(branchEnrollmentWay.getEnrollmentWays());
			branchEnrollmentWayDao.update(old);
		}
	}
	
	/**
	 * 根据ID删除学习中心授权市场途径
	 * @param BranchEnrollmentWay
	 * @throws Exception
	 */
	public void deleteConfigById(int id)throws Exception
	{
		branchEnrollmentWayDao.deleteConfig(id);
	}
	
	/**
	 * 根据ID查询学习中心授权市场途径
	 */
	public BranchEnrollmentWay findBranchEnrollmentWayById(int id) throws Exception
	{
		return branchEnrollmentWayDao.findById(id);
	}
	
	/**
	 * 根据条件查找学习中心授权市场途径数量
	 */
	public int findCountByConditionForHQL(BranchEnrollmentWay branchEnrollmentWay)throws Exception
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		if(0<branchEnrollmentWay.getBranchId())
		{
			hqlcon+=" and branchId="+ Constants.PLACEHOLDER;
			paramList.add(branchEnrollmentWay.getBranchId());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return branchEnrollmentWayDao.getCounts(p);
	}
	
	/**
	 * 根据条件查找学习中心授权市场途径
	 */
	public List<BranchEnrollmentWay> findListByConditionForHQL(BranchEnrollmentWay branchEnrollmentWay)throws Exception
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		if(0<branchEnrollmentWay.getBranchId())
		{
			hqlcon+=" and branchId="+ Constants.PLACEHOLDER;
			paramList.add(branchEnrollmentWay.getBranchId());
		}
		return branchEnrollmentWayDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据角色ID查找学习中心授权市场途径
	 */
	public BranchEnrollmentWay findByBranchId(int branchId)throws Exception
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		hqlcon+=" and branchId="+ Constants.PLACEHOLDER;
		paramList.add(branchId);
		
		List<BranchEnrollmentWay> list=branchEnrollmentWayDao.getByProperty(hqlcon,paramList);
		if(null!=list&&0<list.size())
			return list.get(0);
		else
			return null;
	}

}
