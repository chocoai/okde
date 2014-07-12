package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.common.Constants;
import net.cedu.dao.meterial.MeterialCategoryDao;
import net.cedu.entity.meterial.MeterialCategory;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 物料分类 业务逻辑实现层
 * 
 * @author YY
 * */
@Service
public class MeterialCategoryBizImpl implements MeterialCategoryBiz {

	@Autowired
	private MeterialCategoryDao meterialcategorydao;

	/*
	 * 增加物料类型 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#addMeterialCategory(net
	 * .cedu.entity.meterial.MeterialCategory)
	 */
	public Object addMeterialCategory(MeterialCategory meterialcategory)
			throws Exception {
		Object obj = null;
		obj = meterialcategorydao.save(meterialcategory);
		if (obj == null)
			return obj;
		else
			return obj;
	}

	/*
	 * 分页数量 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#findAllMeterialCategoryCount
	 * (net.cedu.model.page.PageResult)
	 */
	public int findAllMeterialCategoryCount(PageResult<MeterialCategory> pr)
			throws Exception {

		PageParame p = new PageParame(pr);
		//
		// p.setHqlConditionExpression(" and deleteFlag = "
		// + Constants.PLACEHOLDER);
		// p.setValues(new Object[] { Constants.DELETE_FALSE });
		return meterialcategorydao.getCounts(p);
	}

	/*
	 * 查询物料分类类型 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#findAllMeterialCategory
	 * (net.cedu.model.page.PageResult)
	 */
	public List<MeterialCategory> findAllMeterialCategoryList(
			PageResult<MeterialCategory> pr) throws Exception {
		List<MeterialCategory> list = new ArrayList<MeterialCategory>();

		PageParame page = new PageParame(pr);
		Long[] longs = meterialcategorydao.getIDs(page);
		for (int i = 0; i < longs.length; i++) {
			MeterialCategory m = meterialcategorydao.findById(Integer
					.parseInt(longs[i].toString()));
			list.add(m);
		}
		return list;
	}

	/*
	 * 按用户名，和编号查找用户 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#findByMeterialCategoryName
	 * (java.lang.String)
	 */
	public MeterialCategory findByMeterialCategoryNameAndMeterialCategoryCode(
			String meterialcategoryname, String code) throws Exception {
		String hqlcon = "";
		List<MeterialCategory> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(meterialcategoryname)) {
			hqlcon += " and name =" + Constants.PLACEHOLDER;
			paramList.add(meterialcategoryname);
		}
		// 编号
		if (StringUtils.isNotBlank(code)) {
			hqlcon += " and code =" + Constants.PLACEHOLDER;
			paramList.add(code);
		}
		if (0 < hqlcon.length())
			list = meterialcategorydao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}

	/*
	 * 按用户名查找用户 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#findByMeterialCategoryName
	 * (java.lang.String)
	 */
	public MeterialCategory findByMeterialCategoryName(
			String meterialcategoryname) throws Exception {
		String hqlcon = "";
		List<MeterialCategory> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(meterialcategoryname)) {
			hqlcon += " and name =" + Constants.PLACEHOLDER;
			paramList.add(meterialcategoryname);
		}

		if (0 < hqlcon.length())
			list = meterialcategorydao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}

	/*
	 * 查询所有物料分类 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#findMeterialCategoryAll()
	 */
	public List<MeterialCategory> findMeterialCategoryAll() throws Exception {
		return meterialcategorydao.findAll();
	}

	/*
	 * 根据ID删除物料分类 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#deleteMeterialCategoryById
	 * (int)
	 */
	public void deleteMeterialCategoryById(int id) throws Exception {
		meterialcategorydao.deleteById(id);
	}

	/*
	 * 按ID查询物料分类 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#findMeterialCategoryById
	 * (int)
	 */
	public MeterialCategory findMeterialCategoryById(int id) throws Exception {
		return meterialcategorydao.findById(id);
	}

	/*
	 * 修改物料分类 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialCategoryBiz#updateMeterialCategory(
	 * net.cedu.entity.meterial.MeterialCategory)
	 */
	public void updateMeterialCategory(MeterialCategory meterialcategory)
			throws Exception {
		meterialcategorydao.update(meterialcategory);

	}
	
	/**
	 * 查询全部
	 */
	public List<MeterialCategory> findall() throws Exception {

		return meterialcategorydao.findAll();
	}

	/**
	 * 按照id查询对象
	 */
	public MeterialCategory findById(int id) throws Exception {

		return meterialcategorydao.findById(id);
	}

}
