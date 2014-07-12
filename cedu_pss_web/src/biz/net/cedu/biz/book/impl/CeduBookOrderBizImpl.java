package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.CeduBookOrderDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.book.CeduBookOrder;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 总部订购单
 * @author YY
 *
 */
@Service
public class CeduBookOrderBizImpl implements CeduBookOrderBiz{
	
	@Autowired
	private CeduBookOrderDao ceduBookOrderDao; //总部采购数据层
	@Autowired
	private UserBiz userBiz; //用户业务层
	/**
	 * 查询全部
	 * @return
	 * @throws Exception
	 */
	public List<CeduBookOrder> findAll() throws Exception {
	 
		return ceduBookOrderDao.findAll();
	}
	/**
	 * 根据id查询对象
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public CeduBookOrder findIdByCeduBookOrder(int id) throws Exception {		 
		return ceduBookOrderDao.findById(id);
	}
	/**
	 * 根据状态和学习中心查询订单
	 * @param stauts
	 * @param branch
	 * @return list
	 */
	public List<CeduBookOrder> findStautsAndBranchByCeduBookOrder(int stauts,
			int[] branch) throws Exception {
		return null;
	}
	/**
	 * 根据编号查询对象
	 */
	public CeduBookOrder findOrderCedeByCeduBookOrder(String orderCode)
			throws Exception {
		CeduBookOrder order=new CeduBookOrder();
		String sql="";
	    List<Object> list=new ArrayList<Object>();
	    if (null!=orderCode) {
			sql+="and code = "+Constants.PLACEHOLDER;
			list.add(orderCode);
		}
	    order=ceduBookOrderDao.getObjByProperty(sql, list.toArray());
		return order;
	}
	/**
	 * 分页数量(总部代理书商发货)
	 */
	public int findPageCountByCeduBookOrder(CeduBookOrder ceduBookOrder,
			PageResult<CeduBookOrder> pr) throws Exception {	 
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (ceduBookOrder != null) {
			if(-1<(ceduBookOrder.getStatus())&&ceduBookOrder.getStatus()!=3)
			{
				hql+="and status = "+Constants.PLACEHOLDER;
				paramlist.add(ceduBookOrder.getStatus());
			}
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=ceduBookOrderDao.getCounts(p);
		
		return count;
	}
	/**
	 * 分页集合(总部代理书商发货)
	 */
	public List<CeduBookOrder> findPageListByCeduBookOrder(
			CeduBookOrder ceduBookOrder, PageResult<CeduBookOrder> pr)
			throws Exception {
		List<CeduBookOrder> cedulist = new ArrayList<CeduBookOrder>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (ceduBookOrder != null) {		
			if(-1<(ceduBookOrder.getStatus())&&ceduBookOrder.getStatus()!=3)
			{
				hql+="and  status = "+Constants.PLACEHOLDER;
				paramlist.add(ceduBookOrder.getStatus());
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		Long[] cedubookids = ceduBookOrderDao.getIDs(p);
		if (cedubookids != null && cedubookids.length != 0) {
			for (int i = 0; i < cedubookids.length; i++) {
				CeduBookOrder  cedu= ceduBookOrderDao.findById(Integer.parseInt(cedubookids[i]
						.toString()));
				if(cedu!=null)
				{
					CeduBookOrder  order=cedu;
					User user=userBiz.findUserById(order.getOrderOperator());
					if(user!=null)
					{
						order.setOrderoperatorname(user.getFullName());
					}
					 
				cedulist.add(cedu);
				}
			}
		}
		return cedulist;
	}
	/**
	 * 增加派发单
	 */
	public void addCeduBookOrder(CeduBookOrder ceduBookOrder)
			throws Exception {
		 
		  ceduBookOrderDao.save(ceduBookOrder);
	}
}
