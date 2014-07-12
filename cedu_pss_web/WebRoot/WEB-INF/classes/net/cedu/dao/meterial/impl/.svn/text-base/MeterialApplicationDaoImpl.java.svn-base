package net.cedu.dao.meterial.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.meterial.MeterialApplicationDao;
import net.cedu.entity.meterial.MeterialApplication;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * 中心申请单数据层
 * 
 * @author YY
 * 
 * 
 * */
@Repository
public class MeterialApplicationDaoImpl extends
		BaseMDDaoImpl<MeterialApplication> implements MeterialApplicationDao {

	@Autowired
	private MasterMysqlDao masterMysqlDao;

	/*条件查询
	 * (non-Javadoc)
	 * @see net.cedu.dao.meterial.MeterialApplicationDao#findbyMeterialApplication(net.cedu.entity.meterial.MeterialApplication)
	 */
	public List<MeterialApplication> findbyMeterialApplication(
			MeterialApplication meterialapplication) {
		List<MeterialApplication> list=null;
		DetachedCriteria cri = DetachedCriteria
				.forClass(MeterialApplication.class);
		if (meterialapplication != null) {
			if (meterialapplication.getCode() != null) {
				cri.add(Restrictions.eq("code", meterialapplication.getCode()));
			}
			if (meterialapplication.getApplicant()!=0) {
				cri.add(Restrictions.eq("applicant", meterialapplication
						.getApplicant()));
			}

		}
		 list=new ArrayList<MeterialApplication>();
		list =masterMysqlDao.getHibernateTemplate().findByCriteria(cri);
		return list;
		

	}
}