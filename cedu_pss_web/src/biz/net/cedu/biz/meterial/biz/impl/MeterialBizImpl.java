package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.meterial.MeterialCategoryDao;
import net.cedu.dao.meterial.MeterialDao;
import net.cedu.entity.meterial.Meterial;
import net.cedu.entity.meterial.MeterialCategory;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物料 业务逻辑实现层
 * 
 * @author yy
 * 
 */
@Service
public class MeterialBizImpl implements MeterialBiz {
	@Autowired
	private MeterialDao meterialdao;
	@Autowired 
	private MeterialCategoryDao meterialCategoryDao;

	/*
	 * 增加物料 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialBiz#addMeterial(net.cedu.entity.meterial
	 * .Meterial)
	 */
	public void addMeterial(Meterial meterial) throws Exception {
		meterialdao.save(meterial);
	}

	/*
	 * 删除 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.meterial.biz.MeterialBiz#deleteMeterial(int)
	 */
	public boolean deleteMeterial(int id) throws Exception {
		if(0!=id)
		{
		meterialdao.deleteById(id);
		return true;
		}else{
		return false;
		}
	}

 

	/*
	 * 修改物料信息 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialBiz#updateMeterial(net.cedu.entity.
	 * meterial.Meterial)
	 */
	public void updateMeterial(Meterial meterial) throws Exception {

		meterialdao.update(meterial);
	}

	
	public List<Meterial> findAllMeterial(PageResult<Meterial> pr)
			throws Exception {
		List<Meterial> list = new ArrayList<Meterial>();

		PageParame page = new PageParame(pr);
		Long[] longs = meterialdao.getIDs(page);
		for (int i = 0; i < longs.length; i++) {
			Meterial m = meterialdao.findById(Integer.parseInt(longs[i]
					.toString()));
			list.add(m);
		}
		return list;
	}

	
	public int findAllMeterialCount(PageResult<Meterial> pr) throws Exception {

		PageParame p = new PageParame(pr);
		//
		// p.setHqlConditionExpression(" and deleteFlag = "
		// + Constants.PLACEHOLDER);
		// p.setValues(new Object[] { Constants.DELETE_FALSE });
		return meterialdao.getCounts(p);

	}
	/*
	 * 显示条数 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialBiz#findAllMeterialCount(net.cedu.model
	 * .page.PageResult)
	 */
	public int findMeterialPageCountByCodeApplication(Meterial meterial,
			PageResult<Meterial> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (meterial != null) {

			if (0!=meterial.getCategoryId()) {
				hql += "and categoryId like " + Constants.PLACEHOLDER;

				paramlist.add("%" + meterial.getCategoryId() + "%");
			}
			 
			if (-1 < meterial.getDeleteFlag()) {
				hql += " and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());

			}
			 
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		int count = meterialdao.getCounts(p);

		return count;
	}
	/*
	 * 显示数据 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialBiz#findAllMeterial(net.cedu.model.
	 * page.PageResult)
	 */
	public List<Meterial> findMeterialPageListByCodeApplication(
			Meterial meterial, PageResult<Meterial> pr) throws Exception {
		List<Meterial> meteriallist = new ArrayList<Meterial>();
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (meterial != null) {

			if (0!=(meterial.getCategoryId())) {
				hql += "and categoryId like " + Constants.PLACEHOLDER;

				paramlist.add("%" + meterial.getCategoryId() + "%");
			}
			 
			if (-1 < meterial.getDeleteFlag()) {
				hql += " and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());

			}
		 
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] userIds = meterialdao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				Meterial m = meterialdao.findById(Integer.parseInt(userIds[i]
						.toString()));
				if (m != null) {
					Meterial met=m;
					MeterialCategory category=meterialCategoryDao.findById(met.getCategoryId());
					
					if(category!=null)
					{
						met.setCategoryname(category.getName());
					}
					meteriallist.add(m);
				}
			}
		}

		return meteriallist;

	}

	/*
	 * 根据id查询物料 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.meterial.biz.MeterialBiz#findById(int)
	 */
	public Meterial findById(int id) throws Exception {

	  return meterialdao.findById(id);
	}

	/*
	 * 查询全部物料名称 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.meterial.biz.MeterialBiz#findall()
	 */
	public List<Meterial> findall() throws Exception {
		return meterialdao.findAll();
	}
	/**
	 * 按照姓名查询对象
	 */
	public Meterial findByName(String name) throws Exception {
		
		String hqlcon = "";
		 Meterial  list = new Meterial();
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(name)) {
			hqlcon += " and name =" + Constants.PLACEHOLDER;
			paramList.add(name);
		}
		 
			list = meterialdao.getObjByProperty(hqlcon, paramList.toArray());
			return list;
	}

	 
	/**
	 * 根据物理名称查询物理集合id
	 */
	public Long[] findMeterialByNames(String name) throws Exception {
		 
		List<Object> list=new ArrayList<Object>();
		Long [] ids =  null;
		String hql="";
		if(StringUtils.isNotBlank(name))
		{
			hql += " and name = " + Constants.PLACEHOLDER;
			list.add(name);
		}
		ids = meterialdao.getIDs(hql, list.toArray());
		return ids;
	}
	/**
	 * 根据物料名称查询id集合
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public List<Meterial> findMeterialByCatrgoryid(int categoryid)
			throws Exception {
		List<Meterial> meteriallist= new ArrayList<Meterial>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>(); 
		if(0<categoryid){
				hqlparam += " and  categoryId= " + Constants.PLACEHOLDER;
				paramList.add(categoryid);
				meteriallist = meterialdao.getByProperty(hqlparam, paramList);
		}
		
		return  meteriallist;
	}
}
