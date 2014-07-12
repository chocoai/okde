package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.PurchaseRequisitionDetailDao;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 申购单明细业务层
 * @author YY
 *
 */
@Service
public class PurchaseRequisitionDetailBizImpl implements PurchaseRequisitionDetailBiz {
	
	@Autowired
	private PurchaseRequisitionDetailDao purchaseRequisitionDetailDao;//申购单明细数据访问层
	/**
	 * 根据申购单编号查询集合
	 */
	public List<PurchaseRequisitionDetail> findorderIdByPurchaseRequisition(int purchaseRequisitionId)
			throws Exception {
		String sql="";
		List<PurchaseRequisitionDetail> list=new ArrayList<PurchaseRequisitionDetail>();
		List<Object> objlist=new ArrayList<Object>();
		if(0!=purchaseRequisitionId)
		{
			sql+="and purchaseRequisitionId ="+Constants.PLACEHOLDER;
			objlist.add(purchaseRequisitionId);	
		}
			list=purchaseRequisitionDetailDao.getByProperty(sql, objlist);
		return list;
	}
	
	/**
	 * 修改方法
	 */
	public void updatePurchaseRequisitionDetail(
			PurchaseRequisitionDetail purchaseRequisitionDetail)
			throws Exception {
		purchaseRequisitionDetailDao.update(purchaseRequisitionDetail);
		
	}
	
	/**
	 * 增加方法
	 */
	public void addPurchaseRequisitionDetail(
			PurchaseRequisitionDetail purchaseRequisitionDetail)
			throws Exception {
		purchaseRequisitionDetailDao.save(purchaseRequisitionDetail);
		
	}
	
	/**
	 * 根据主键id查询对象
	 */
	public PurchaseRequisitionDetail findByid(int id) throws Exception {
		 return purchaseRequisitionDetailDao.findById(id);
	}

}
