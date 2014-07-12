package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.PurchaseRequisitionDetail;

/**
 * 申购单明细接口
 * @author YY
 *
 */
public interface PurchaseRequisitionDetailBiz {

	
	/**
	 * 根据申购单编号查询集合
	 * @param id
	 * @return list
	 * @throws Exception
	 */
	List<PurchaseRequisitionDetail> findorderIdByPurchaseRequisition(int purchaseRequisitionId)throws Exception;
	
	/**
	 * 修改申购单明细
	 * @param purchaseRequisitionDetail
	 * @throws Exception
	 */
	void updatePurchaseRequisitionDetail(PurchaseRequisitionDetail purchaseRequisitionDetail)throws Exception;
	
	/**
	 * 增加申购单明细
	 * @param purchaseRequisitionDetail
	 * @throws Exception
	 */
	void addPurchaseRequisitionDetail(PurchaseRequisitionDetail purchaseRequisitionDetail)throws Exception;
	
	/**
	 * 根据主键id查询申购单明细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PurchaseRequisitionDetail findByid(int id)throws Exception;
	 
}
 
