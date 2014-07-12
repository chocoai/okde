package net.cedu.biz.admin.impl;

import java.util.ArrayList;
import java.util.List;
import net.cedu.biz.admin.AreaManagerBranchBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.AreaManagerBranchDao;
import net.cedu.entity.admin.AreaManagerBranch;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 区域经理学习中心业务层实现类
 * 
 * @author gaolei
 * 
 */
@Service
public class AreaManagerBranchBizImpl implements AreaManagerBranchBiz {

	@Autowired
	private AreaManagerBranchDao areamanagerbranchDao;         //区域经理接口
	
	@Autowired
	private BranchBiz branchBiz;   //学习中心Biz
	

	/*
	 * 查询区域经理学习中心按区域经理Id
	 * @see net.cedu.biz.admin.AreaManagerBranchBiz#findAreaManagerBranchByManagerId(int)
	 */
	public List<AreaManagerBranch> findAreaManagerBranchByManagerId(
			int managerId) throws Exception {
		List<AreaManagerBranch> AreaManagerBranchlst = null;
		
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam+=" and managerId="+ Constants.PLACEHOLDER;
		list.add(managerId);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] areaManagerBranchIds=areamanagerbranchDao.getIDs(p);
		
		if(areaManagerBranchIds!=null && areaManagerBranchIds.length>0)
		{
			AreaManagerBranchlst=new ArrayList<AreaManagerBranch>();
			for(int i=0;i<areaManagerBranchIds.length;i++)
			{
				AreaManagerBranch amb=this.findAreaManagerBranchById(Integer.valueOf(areaManagerBranchIds[i].toString()));
				AreaManagerBranch areamanagerbranch=amb;
				Branch branch=branchBiz.findBranchById(areamanagerbranch.getBranchId());
				if(branch!=null)
				{
					areamanagerbranch.setBranchName(branch.getName());
				}
				AreaManagerBranchlst.add(areamanagerbranch);
			}
		}
		return AreaManagerBranchlst;
	}


	/*
	 * 查询区域经理学习中心按主键Id
	 * @see net.cedu.biz.admin.AreaManagerBranchBiz#findAreaManagerBranchById(int)
	 */
	public AreaManagerBranch findAreaManagerBranchById(int id) throws Exception {
		
		return areamanagerbranchDao.findById(id);
	}


	/*
	 * 添加区域经理学习中心
	 * @see net.cedu.biz.admin.AreaManagerBranchBiz#addAreaManagerBranch(net.cedu.entity.admin.AreaManagerBranch)
	 */
	public boolean addAreaManagerBranch(int managerid,Object [] branchIds)
			throws Exception {
		deleteAreaManagerBranchByManagerId(managerid);
		AreaManagerBranch amb;
		for(int i=0;i<branchIds.length;i++)
		{
			amb=new AreaManagerBranch();
			amb.setManagerId(managerid);
			amb.setBranchId(Integer.valueOf(branchIds[i].toString()));
			areamanagerbranchDao.save(amb);
		}
		return true;
	}


	/*
	 * 删除区域经理所分配学习中心
	 * @see net.cedu.biz.admin.AreaManagerBranchBiz#deleteAreaManagerBranchByManagerId(int)
	 */
	public boolean deleteAreaManagerBranchByManagerId(int managerId)
			throws Exception {
		String hqlparam = "and managerId="+Constants.PLACEHOLDER;
		List<Object> list = new ArrayList<Object>();
		list.add(managerId);
		areamanagerbranchDao.deleteByProperty(hqlparam, list);
		return true;
	}
	
	/*
	 * 查询某个区域经理所关联的所有学习中心
	 * 
	 * @see net.cedu.biz.admin.AreaManagerBranchBiz#findBranchListByManagerId(int)
	 */
	public List<Branch> findBranchListByManagerId(int managerId) throws Exception 
	{
		List<Branch> branchList = null;		
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam+=" and managerId="+ Constants.PLACEHOLDER;
		list.add(managerId);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] areaManagerBranchIds=areamanagerbranchDao.getIDs(p);
		
		if(areaManagerBranchIds!=null && areaManagerBranchIds.length>0)
		{
			branchList=new ArrayList<Branch>();
			Branch branch=null;
			for(int i=0;i<areaManagerBranchIds.length;i++)
			{
				AreaManagerBranch amb=this.findAreaManagerBranchById(Integer.valueOf(areaManagerBranchIds[i].toString()));
				AreaManagerBranch areamanagerbranch=amb;
				if(areamanagerbranch!=null)
				{
					branch=branchBiz.findBranchById(areamanagerbranch.getBranchId());
					if(branch!=null)
					{
						branchList.add(branch);
					}
				}
			}
		}
		return branchList;
	}

	
}
