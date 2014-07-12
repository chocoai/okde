package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.book.DeliveredWayBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.DeliveredWayDao;
import net.cedu.entity.book.DeliveredWay;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 配送方式业务层实现
 * @author XFY
 *
 */
@Service
public class DeliveredWayBizImpl implements DeliveredWayBiz {
	@Autowired
	private DeliveredWayDao deliveredwaydao;
	/**
	 * 增加配送方法
	 */
	public Object addDeliveredWay(DeliveredWay deliveredway) throws Exception {
		Object obj=null;
		obj=deliveredwaydao.save(deliveredway);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	/**
	 * 分页集合
	 */
	public List<DeliveredWay> findAllDeliveredWay(PageResult<DeliveredWay> pr)
			throws Exception {
		List<DeliveredWay> deliveredwaylist=new ArrayList<DeliveredWay>();
		PageParame p = new PageParame(pr);
		Long[] deliveredwayIDs= deliveredwaydao.getIDs(p);
		if(null!=deliveredwayIDs && deliveredwayIDs.length>0)
		{
			for(int i=0;i<deliveredwayIDs.length;i++)
			{
				DeliveredWay dw= deliveredwaydao.findById(Integer.parseInt(deliveredwayIDs[i].toString()));
				if(null!=dw)
				{
					deliveredwaylist.add(dw);
				}
			}
		}
		return deliveredwaylist;
	}
	
	/**
	 * 分页数量
	 */
	public int findAllDeliveredWayCount(PageResult<DeliveredWay> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		
		return deliveredwaydao.getCounts( p);
	}
	
	/**
	 * 根据id删除
	 */
	public void deleteDeliveredWayById(int id) throws Exception {
		deliveredwaydao.deleteById(id);
		
	}
	
	/**
	 * 根据id查询
	 */
	public DeliveredWay findDeliveredWayById(int id) throws Exception {
		return deliveredwaydao.findById(id);
	}
	
	/**
	 * 修改配送方法
	 */
	public void updateDeliveredWay(DeliveredWay deliveredway) throws Exception {
		deliveredwaydao.update(deliveredway);
	}
	
	/**
	 *验证方法 
	 */
	public DeliveredWay findByNameOrCodeDeliveredWay(String code,String name) throws Exception
	{
		DeliveredWay way=new DeliveredWay();
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
		 way=deliveredwaydao.getObjByProperty(hql, list.toArray());
		 
		return  way;
		
	}


}
