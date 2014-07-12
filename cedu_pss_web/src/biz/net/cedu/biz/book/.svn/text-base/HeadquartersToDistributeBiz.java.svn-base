package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.HeadquartersToDistribute;
import net.cedu.model.page.PageResult;

/**
 * 总部派发业务层
 * @author YY
 *
 */
public interface HeadquartersToDistributeBiz {

	/**
	 * 增加方法
	 * @param headquartersToDistribute
	 */
	void addHeadquartersToDistribute(HeadquartersToDistribute headquartersToDistribute)throws Exception;
	
	
	 /**
	  * 总部派发分页集合
	  * @param purchaseRequisition
	  * @param pr
	  * @return
	  * @throws Exception
	  */
	public List<HeadquartersToDistribute> findHeadquartersToDistributePageList(HeadquartersToDistribute headquartersToDistribute, PageResult<HeadquartersToDistribute> pr)throws Exception;
	
	/**
	 * 总部派发分页数量
	 * @param purchaseRequisition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findHeadquartersToDistributePageCount(HeadquartersToDistribute headquartersToDistribute, PageResult<HeadquartersToDistribute> pr)throws Exception;
	
	/**
	 * 更新派发单
	 * @param headquartersToDistribute
	 * @throws Exception
	 */
	public void updateHeadquartersToDistribute(HeadquartersToDistribute headquartersToDistribute)throws Exception;
}
