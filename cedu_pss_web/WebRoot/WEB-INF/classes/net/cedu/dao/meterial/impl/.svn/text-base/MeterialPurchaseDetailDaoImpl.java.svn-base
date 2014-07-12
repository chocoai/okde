package net.cedu.dao.meterial.impl;

import java.util.ArrayList;
import java.util.List;

 

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.meterial.MeterialPurchaseDetailDao;
 
import net.cedu.entity.meterial.MeterialPurchaseDetail;

@Repository
/**
 * 采购单明细数据层
 * @author YY
 * */
public class MeterialPurchaseDetailDaoImpl extends BaseMDDaoImpl<MeterialPurchaseDetail> implements MeterialPurchaseDetailDao {

	
	@Autowired
	private MasterMysqlDao masterMysqlDao;

	@SuppressWarnings("unchecked")
	public List<MeterialPurchaseDetail> findMeterialPurchaseDetailByid(int id) {
		List<MeterialPurchaseDetail> list = new ArrayList<MeterialPurchaseDetail>();
		DetachedCriteria cri = DetachedCriteria
				.forClass(MeterialPurchaseDetail.class);
		cri.add(Restrictions.eq("id", id));
		list = masterMysqlDao.getHibernateTemplate().findByCriteria(cri);
		return list;
	}
}
