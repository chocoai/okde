package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.SettlementWayBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.SettlementWayDao;
import net.cedu.entity.book.SettlementWay;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 结算方式业务层实现
 * @author XFY
 *
 */

@Service
public class SettlementWayBizImpl implements SettlementWayBiz {
	
	@Autowired
	private SettlementWayDao settlementwaydao;
	
	
	/**
	 * 增加
	 */
	
	public Object addSettlementWay(SettlementWay settlementway)
			throws Exception {
		Object obj=null;
		obj=settlementwaydao.save(settlementway);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	
	/**
	 * 分页-list
	 */
	
	public List<SettlementWay> findAllSettlementWay(PageResult<SettlementWay> pr)
			throws Exception {
			List<SettlementWay> settlementwaylist=new ArrayList<SettlementWay>();
			PageParame p = new PageParame(pr);
			Long[] settlementwayIDs= settlementwaydao.getIDs(p);
			if(null!=settlementwayIDs && settlementwayIDs.length>0)
			{
				for(int i=0;i<settlementwayIDs.length;i++)
				{
					SettlementWay sw= settlementwaydao.findById(Integer.parseInt(settlementwayIDs[i].toString()));
					if(null!=sw)
					{
						settlementwaylist.add(sw);
					}
				}
			}
			return settlementwaylist;
	}
	
	/**
	 * 分页-数量
	 */
	
	public int findAllSettlementWayCount(PageResult<SettlementWay> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		
		return settlementwaydao.getCounts( p);
	}
	
	/**
	 * 删除实现
	 */
	
	public void deleteSettlementWay(int id) throws Exception {
		settlementwaydao.deleteById(id);		
	}
	
	/**
	 * 按ID查询
	 */
	
	public SettlementWay findSettlementWayById(int id) throws Exception {
		return settlementwaydao.findById(id);
	}
	
	/**
	 * 修改
	 */
	
	public void updateSettlementWay(SettlementWay settlementway)
			throws Exception {
		settlementwaydao.update(settlementway);
		
	}
	/**
	 * 查询所有
	 */
	public List<SettlementWay> findAllSettlementWay() throws Exception {
		return settlementwaydao.findAll();
	}
	/**
	 *验证方法 
	 */
	public SettlementWay findByNameOrCodeSettlementWay(String code,String name) throws Exception
	{
		SettlementWay way=new SettlementWay();
		String hql="";
		List<Object> list=new ArrayList<Object>();
		if(null!=code)
		{
			hql+=" and code ="+Constants.PLACEHOLDER;
			list.add(code);
		}
		if(null!=name)
		{
			hql+=" and name = "+Constants.PLACEHOLDER;
			list.add(name);
		}
		 way=settlementwaydao.getObjByProperty(hql, list.toArray());
		 
		return  way;
		
	}
}
