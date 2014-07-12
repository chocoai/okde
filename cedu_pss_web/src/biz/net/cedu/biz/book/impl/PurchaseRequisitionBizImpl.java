package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.PurchaseRequisitionDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 申购单业务层
 * @author YY
 *
 */
@Service
public class PurchaseRequisitionBizImpl implements PurchaseRequisitionBiz{
	
	@Autowired
	private PurchaseRequisitionDao purchaseRequisitionDao; //申购单数据访问层
	
	@Autowired
	private UserBiz userBiz; //用户业务层
	@Autowired
	private BranchBiz branchBiz; //学习中心业务层
 
	/**
	 * 根据订购单id查询申购单集合
	 */
	public List<PurchaseRequisition> findIdByPurchaseRequisition(int orderid)
			throws Exception {
		String sql="";
		List<PurchaseRequisition> list=new ArrayList<PurchaseRequisition>();
		List<Object> objlist=new ArrayList<Object>();
		if(0!=orderid)
		{
			sql+="and orderId ="+Constants.PLACEHOLDER;
			objlist.add(orderid);	
		}
			list=purchaseRequisitionDao.getByProperty(sql,objlist);
		return list;
	}
	
	/**
	 * 根据主键id查询申购单
	 */
	public PurchaseRequisition findById(int id) throws Exception {
		 
		return purchaseRequisitionDao.findById(id);
	}
	/**
	 * 修改申购单
	 */
	public void updatePurchaseRequisition(
			PurchaseRequisition purchaseRequisition) throws Exception {
		 
		purchaseRequisitionDao.update(purchaseRequisition);
	}
	
	/**
	 * 增加方法
	 */
	public void addPurchaseRequisition(PurchaseRequisition purchaseRequisition)
			throws Exception {
		purchaseRequisitionDao.save(purchaseRequisition);		
	}
	
	/**
	 * 分页方法（数量）
	 */
	public int findPurchaseRequisitionPageCountByConditions(
			PurchaseRequisition purchaseRequisition,
			PageResult<PurchaseRequisition> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (purchaseRequisition != null) {
			//学习中心申请的申购单
			hql+=" and branchId > "+Constants.PLACEHOLDER;
			paramlist.add(purchaseRequisition.getBranchId());
			//状态为预申购的
			hql+=" and status = "+Constants.PLACEHOLDER;
			paramlist.add(Constants.BOOK_STATUS_PRE_CANCEL);
			//院校			
			if(0!=purchaseRequisition.getAcademyId())
			{
				hql+=" and academyId = "+Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisition.getAcademyId());
			} 
			//类型
			if(-1!=purchaseRequisition.getTypes())
			{
				hql+=" and types = " + Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisition.getTypes());		 
			}
 
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=purchaseRequisitionDao.getCounts(p);
		
		return count;
	}
	/**
	 * 分页方法（集合）
	 */
	public List<PurchaseRequisition> findPurchaseRequisitionPageListByConditions(
			PurchaseRequisition purchaseRequisition,
			PageResult<PurchaseRequisition> pr) throws Exception {
			List<PurchaseRequisition> purchaseList = new ArrayList<PurchaseRequisition>();
			PageParame p = new PageParame(pr);
			List<Object> paramlist=new ArrayList<Object>();
			String hql="";
			if (purchaseRequisition != null) {
				//学习中心申请的申购单
				hql+=" and branchId > "+Constants.PLACEHOLDER;
				paramlist.add(purchaseRequisition.getBranchId());
				//状态为预申购的
				hql+=" and status = "+Constants.PLACEHOLDER;
				paramlist.add(Constants.BOOK_STATUS_PRE_CANCEL);
				//院校			
				if(0!=purchaseRequisition.getAcademyId())
				{
					hql+=" and academyId = "+Constants.PLACEHOLDER;
					paramlist.add(purchaseRequisition.getAcademyId());
				} 
				//类型
				if(-1!=purchaseRequisition.getTypes())
				{
					hql+=" and types = " + Constants.PLACEHOLDER;
					paramlist.add(purchaseRequisition.getTypes());		 
				}
 
				p.setHqlConditionExpression(hql);
				p.setValues(paramlist.toArray());
			}
			Long[] purchase = purchaseRequisitionDao.getIDs(p);
			if (purchase != null && purchase.length != 0) {
				for (int i = 0; i < purchase.length; i++) {
					PurchaseRequisition Purchase = purchaseRequisitionDao.findById(Integer.parseInt(purchase[i]
							.toString()));
					PurchaseRequisition p1=Purchase;
					if(p1!=null)
					{
						User u= userBiz.findUserById(p1.getRequisitioner());
						if(u!=null)
						{
						p1.setRequisitionername(u.getFullName());
						}
						purchaseList.add(p1);
					}
					
				 
				}
			}
			
			return purchaseList;
	}
	/**
	 * 总部派发分页数量
	 */
	public int findHeadquartersToDistributePageCountByConditions(
			PurchaseRequisition purchaseRequisition,
			PageResult<PurchaseRequisition> pr) throws Exception {
		 
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (purchaseRequisition != null) {
			//学习中心申请的申购单
			hql+=" and branchId > "+Constants.PLACEHOLDER;
			paramlist.add(purchaseRequisition.getBranchId());
			//状态为预申购的
			hql+=" and status != "+Constants.PLACEHOLDER;
			paramlist.add(Constants.BOOK_STATUS_PRE_CANCEL);
 
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=purchaseRequisitionDao.getCounts(p);
		
		return count;
	}
	
	/**
	 *  总部派发分页集合
	 */
	public List<PurchaseRequisition> findHeadquartersToDistributePageListByConditions(
			PurchaseRequisition purchaseRequisition,
			PageResult<PurchaseRequisition> pr) throws Exception {
		 
		List<PurchaseRequisition> purchaseList = new ArrayList<PurchaseRequisition>();
		PageParame p = new PageParame(pr);
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (purchaseRequisition != null) {
			//学习中心申请的申购单
			hql+=" and branchId > "+Constants.PLACEHOLDER;
			paramlist.add(purchaseRequisition.getBranchId());
			//状态为预申购的
			hql+=" and status != "+Constants.PLACEHOLDER;
			paramlist.add(Constants.BOOK_STATUS_PRE_CANCEL);
	
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		Long[] purchase = purchaseRequisitionDao.getIDs(p);
		if (purchase != null && purchase.length != 0) {
			for (int i = 0; i < purchase.length; i++) {
				PurchaseRequisition Purchase = purchaseRequisitionDao.findById(Integer.parseInt(purchase[i]
						.toString()));
				PurchaseRequisition p1=Purchase;
				if(p1!=null)
				{
						Branch branch= branchBiz.findBranchById(p1.getBranchId());
						if(branch!=null)
						{
						p1.setBranchName(branch.getName());
						}
						 
						
						User u= userBiz.findUserById(p1.getRequisitioner());
						if(u!=null)
						{
						p1.setRequisitionername(u.getFullName());
						}
						purchaseList.add(p1);				 
				}			 
			}
		}	
		return purchaseList;
	}
	
	/**
	 * 查询中心申购单
	 */
	public List<PurchaseRequisition> findByPrePurchaseRequisition(int status,
			int [] branchId) throws Exception {
		String sql="";
		List<PurchaseRequisition> list=new ArrayList<PurchaseRequisition>();
		List<Object> objlist=new ArrayList<Object>();
		//所有非预申购的
		sql+="and status > "+Constants.PLACEHOLDER;
		objlist.add(-1);
		//状态
		if(6!=status)
		{
			sql+="and status ="+Constants.PLACEHOLDER;
			objlist.add(status);	
		}
		if(null!=branchId&&!branchId.equals(""))
		{
			 StringBuffer sb = new StringBuffer();
				if(null!=branchId && branchId.length>0)
				{
					
					 sb.append(",");
					 for(int id :branchId){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }	
					 sql+="and branchId in ("+Constants.PLACEHOLDER+")";
					 objlist.add("$"+sb.toString());	
				}
				 
		}
			list=purchaseRequisitionDao.getByProperty(sql,objlist);
		return list;
	}
}
