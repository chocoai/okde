package net.cedu.dao.meterial.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.dao.impl.MasterMysqlDao;
import net.cedu.dao.meterial.MurDetailDao;
import net.cedu.entity.meterial.MeterialApplicationDetail;
import net.cedu.entity.meterial.MurDetail;

@Repository
/**
 *  领用单明细数据层
 * @author YY
 * */
public class MurDetailDaoImpl extends BaseMDDaoImpl<MurDetail> implements
		MurDetailDao {

	@Autowired
	private MasterMysqlDao masterMysqlDao;

	/*
	 * 根据ID查询对象 (non-Javadoc)
	 * 
	 * @see net.cedu.dao.meterial.MurDetailDao#findMurDetailByid(int)
	 */
	public List<MurDetail> findMurDetailByid(int id) throws Exception {
		List<MurDetail> list = new ArrayList<MurDetail>();
		DetachedCriteria cri = DetachedCriteria.forClass(MurDetail.class);
		cri.add(Restrictions.eq("id", id));
		list = masterMysqlDao.getHibernateTemplate().findByCriteria(cri);
		return list;
	}

}
