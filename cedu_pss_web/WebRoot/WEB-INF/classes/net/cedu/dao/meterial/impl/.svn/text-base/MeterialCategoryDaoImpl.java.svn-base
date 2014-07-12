package net.cedu.dao.meterial.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.meterial.MeterialCategoryDao;
import net.cedu.entity.meterial.MeterialCategory;
@Repository
/**
 * 物料分类数据层
 * @author YY
 * */
public class MeterialCategoryDaoImpl extends BaseMDDaoImpl<MeterialCategory> implements MeterialCategoryDao {

	@Autowired
	private MasterMysqlDao masterMysqlDao;
	
	public boolean findMeterialcategoryByName(String name)  
	{
		DetachedCriteria cri=DetachedCriteria.forClass(MeterialCategory.class);
		cri.add(Restrictions.eq("name", name));
	List<MeterialCategory> list= masterMysqlDao.getHibernateTemplate().findByCriteria(cri);
		MeterialCategory meterialcategory =list.get(0);
		if(meterialcategory!=null)
		{
			return false;
		}else
			return true;
	   
	}
	
}
