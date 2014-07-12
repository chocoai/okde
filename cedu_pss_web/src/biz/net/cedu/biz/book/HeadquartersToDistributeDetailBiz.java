package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.HeadquartersToDistributeDetail;

/**
 * 总部派发明细业务层
 * @author YY
 *
 */
public interface HeadquartersToDistributeDetailBiz {

	/**
	 * 增加方法
	 * @param headquartersToDistribute
	 */
	void addHeadquartersToDistributeDetail(HeadquartersToDistributeDetail headquartersToDistributeDetail);
	
	/**
	 * 根据派发单id查询派发明细单
	 * @param disteributeId
	 * @return list
	 * @throws Exception
	 */
	List<HeadquartersToDistributeDetail> findHeadquartersToDistributeDetail(int disteributeId) throws Exception;


}
