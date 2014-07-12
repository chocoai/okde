package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.CeduBookOrderDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.CeduBookOrderDetailDao;
import net.cedu.entity.book.CeduBookOrderDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 总部订购明细业务层
 * @author YY
 *
 */
@Service
public class CeduBookOrderDetailBizImpl implements CeduBookOrderDetailBiz {

	@Autowired
	private CeduBookOrderDetailDao  ceduBookOrderDetailDao;//数据层
	/**
	 * 根据总部订购id查询订购单明细
	 */
	public List<CeduBookOrderDetail> findorderIdByCeduBookOrderDetail(int order)throws Exception {
		 String sql="";
		 List<CeduBookOrderDetail> detaillist=new ArrayList<CeduBookOrderDetail>();
		 List<Object> list=new ArrayList<Object>();
		 if(0<order)
		 {
			 sql+=" and orderId = "+Constants.PLACEHOLDER;
			 list.add(order);
		 }
		 detaillist=ceduBookOrderDetailDao.getByProperty(sql, list);
		 return detaillist;
	}
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CeduBookOrderDetail findByid(int id) throws Exception
	{
		return ceduBookOrderDetailDao.findById(id);
	}
	/**
	 * 按照id集合查詢出訂單集合
	 * @param ids
	 * @return list
	 * @throws Exception
	 */
	public List<CeduBookOrderDetail> findIdsByCeduBookOrderDetail(int[] ids)
			throws Exception {
		List<CeduBookOrderDetail> list=new ArrayList<CeduBookOrderDetail>();
		for (int id : ids) {
			CeduBookOrderDetail ceduBookOrderDetail=ceduBookOrderDetailDao.findById(id);
			list.add(ceduBookOrderDetail);
		} 
		return list;
	}
	/**
	 * 修改方法
	 */
	public void UpdateCeduBookByStatus(CeduBookOrderDetail ceduBookOrderDetail)
			throws Exception {
		ceduBookOrderDetailDao.update(ceduBookOrderDetail);
		
	}
	public void addCeduBookOrder(CeduBookOrderDetail ceduBookOrderDetail)
			throws Exception {		 
		ceduBookOrderDetailDao.save(ceduBookOrderDetail);
	}
}
