package net.cedu.dao.meterial.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.meterial.MeterialStoreroomDao;
import net.cedu.entity.meterial.MeterialStorage;
import net.cedu.entity.meterial.MeterialStoreroom;

@Repository
/**
 * 物料库房设置数据层
 * @author YY
 * */
public class MeterialStoreroomDaoImpl extends BaseMDDaoImpl<MeterialStoreroom> implements MeterialStoreroomDao{

	
	@Autowired
	private MasterMysqlDao masterMysqlDao;
	
	public boolean findMeterialStoreroomByName(String name)  
	{
		DetachedCriteria cri=DetachedCriteria.forClass(MeterialStoreroom.class);
		cri.add(Restrictions.eq("name", name));
	List<MeterialStoreroom> list= masterMysqlDao.getHibernateTemplate().findByCriteria(cri);
	MeterialStoreroom meterialstoreroom =list.get(0);
		if(meterialstoreroom!=null)
		{
			return false;
		}else
			return true;

	}
}
