package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.book.HeadquartersToDistributeBiz;
import net.cedu.dao.book.HeadquartersToDistributeDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.book.HeadquartersToDistribute;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 总部派发业务层
 * @author YY
 *
 */
@Service
public class HeadquartersToDistributeBizImpl  implements HeadquartersToDistributeBiz{

	@Autowired
	private HeadquartersToDistributeDao headquartersToDistributeDao; //总部派发数据层
	
	@Autowired
	private UserBiz userBiz; //用户业务层
	@Autowired
	private BranchBiz branchBiz; //学习中心业务层
 
	/**
	 * 增加方法
	 */
	public void addHeadquartersToDistribute(
			HeadquartersToDistribute headquartersToDistribute) throws Exception {
		headquartersToDistributeDao.save(headquartersToDistribute);		
	}
	
	/**
	 * 分页方法
	 */
	public int findHeadquartersToDistributePageCount(
			HeadquartersToDistribute headquartersToDistribute,
			PageResult<HeadquartersToDistribute> pr) throws Exception {		 
		PageParame p = new PageParame(pr);
		return headquartersToDistributeDao.getCounts(p);
	}
	/**
	 * 分页集合
	 */
	public List<HeadquartersToDistribute> findHeadquartersToDistributePageList(
			HeadquartersToDistribute headquartersToDistribute,
			PageResult<HeadquartersToDistribute> pr) throws Exception {
		List<HeadquartersToDistribute> list = new ArrayList<HeadquartersToDistribute>();
		PageParame page = new PageParame(pr);
		Long[] longs = headquartersToDistributeDao.getIDs(page);
		for (int i = 0; i < longs.length; i++) {
			HeadquartersToDistribute h = headquartersToDistributeDao.findById(Integer
					.parseInt(longs[i].toString()));
			HeadquartersToDistribute h1=h;
			if(h1!=null)
			{
				User u= userBiz.findUserById(h1.getDistributeer());
				if(u!=null)
				{
					h1.setDistributename(u.getFullName());
				}
				Branch branch=branchBiz.findBranchById(h1.getBranchId());
				if(branch!=null)
				{
					h1.setBranchName(branch.getName());
				}
			
			}
			list.add(h);
		}
		return list;
	}
	/**
	 * 更新派发
	 */
	public void updateHeadquartersToDistribute(
			HeadquartersToDistribute headquartersToDistribute) throws Exception {
		headquartersToDistributeDao.update(headquartersToDistribute);
		
	}

}
