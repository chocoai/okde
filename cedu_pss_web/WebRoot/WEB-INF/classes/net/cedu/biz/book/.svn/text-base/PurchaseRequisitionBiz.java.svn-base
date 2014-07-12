package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.model.page.PageResult;

/**
 * 申购单业务层接口
 * @author YY
 *
 */
public interface PurchaseRequisitionBiz {

	
	/**
	 * 根据订购单编号查询集合
	 * @param id
	 * @return list
	 * @throws Exception
	 */
	List<PurchaseRequisition> findIdByPurchaseRequisition(int id)throws Exception;
	
	/**
	 * 根据id查询申购单
	 * @param id
	 * @return PurchaseRequisition
	 */
	PurchaseRequisition findById(int id)throws Exception;
	/**
	 * 修改申购单
	 * @param purchaseRequisition
	 */
	void updatePurchaseRequisition(PurchaseRequisition purchaseRequisition)throws Exception;
	
	/**
	 * 增加申购单
	 * @param purchaseRequisition
	 * @throws Exception
	 */
	void addPurchaseRequisition(PurchaseRequisition purchaseRequisition)throws Exception;

	/**
	 * 分页结果
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PurchaseRequisition> findPurchaseRequisitionPageListByConditions(PurchaseRequisition purchaseRequisition, PageResult<PurchaseRequisition> pr)throws Exception;
	/**
	 * 分页数量
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findPurchaseRequisitionPageCountByConditions(PurchaseRequisition purchaseRequisition, PageResult<PurchaseRequisition> pr)throws Exception;
	 /**
	  * 总部派发分页集合
	  * @param purchaseRequisition
	  * @param pr
	  * @return
	  * @throws Exception
	  */
	public List<PurchaseRequisition> findHeadquartersToDistributePageListByConditions(PurchaseRequisition purchaseRequisition, PageResult<PurchaseRequisition> pr)throws Exception;
	
	/**
	 * 总部派发分页数量
	 * @param purchaseRequisition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findHeadquartersToDistributePageCountByConditions(PurchaseRequisition purchaseRequisition, PageResult<PurchaseRequisition> pr)throws Exception;
	
	/**
	 * 查询所有中心申购单
	 * @return
	 * @throws Exception
	 */
	public List<PurchaseRequisition> findByPrePurchaseRequisition(int status,int [] branchId) throws Exception;
}
