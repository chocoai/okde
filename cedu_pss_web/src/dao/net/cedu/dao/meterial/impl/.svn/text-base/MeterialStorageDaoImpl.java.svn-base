package net.cedu.dao.meterial.impl;

import java.util.List;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.meterial.MeterialStorageDao;
import net.cedu.entity.meterial.MeterialStorage;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
/**
 * 物料入库方式数据层
 * @author YY
 * */
public class MeterialStorageDaoImpl extends BaseMDDaoImpl<MeterialStorage> implements MeterialStorageDao {

	
	@Autowired
	private MasterMysqlDao masterMysqlDao;
	
	public boolean findMeterialStorageByName(String name)  
	{
		DetachedCriteria cri=DetachedCriteria.forClass(MeterialStorage.class);
		cri.add(Restrictions.eq("name", name));
	List<MeterialStorage> list= masterMysqlDao.getHibernateTemplate().findByCriteria(cri);
	MeterialStorage meterialStorage =list.get(0);
		if(meterialStorage!=null)
		{
			return false;
		}else
			return true;
	   
	}
}
