package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.PurchaseRequisitionerDetail;
import net.cedu.model.page.PageResult;
/**
 * 中心申购单人员明细
 * @author YY
 *
 */
public interface PurchaseRequisitionerDetailBiz {
	
	
	/**
	 * 分页结果
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<PurchaseRequisitionerDetail> findPurchaseRequisitionerDetailPageListByConditions(PurchaseRequisitionerDetail purchaseRequisitionerDetail, PageResult<PurchaseRequisitionerDetail> pr)throws Exception;
	/**
	 * 分页数量
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findPurchaseRequisitionerDetailPageCountByConditions(PurchaseRequisitionerDetail purchaseRequisitionerDetail, PageResult<PurchaseRequisitionerDetail> pr)throws Exception;
	 
	/**
	 * 修改领用单
	 * @param id
	 * @return
	 */
	PurchaseRequisitionerDetail updateStatusByPurchaseRequisitionerDetail(PurchaseRequisitionerDetail purchaseRequisitionerDetail)throws Exception;
	
	/**
	 * 根据id查询领用单
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PurchaseRequisitionerDetail findByid(int id)throws Exception;
	
	/**
	 * 增加中心预申购人员明细
	 * @param purchaseRequisitionerDetail
	 * @return
	 */
	void addPurchaseRequisitionerDetail(PurchaseRequisitionerDetail purchaseRequisitionerDetail) throws Exception;

	/**
	 * 修改中心预申购人员明细
	 * @param purchaseRequisitionerDetail
	 * @throws Exception
	 */
	void updatePurchaseRequisitionerDetail(PurchaseRequisitionerDetail purchaseRequisitionerDetail) throws Exception;

	/**
	 * 按照申购单明细编号查询申购单学生明细
	 * @param student
	 * @return
	 */
	List<PurchaseRequisitionerDetail> findStudentByPurchaseRequisitionerDetail(int purchaseRequisitionDetailId ) throws Exception;

}
