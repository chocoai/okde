package net.cedu.demo.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.common.Constants;
import net.cedu.demo.biz.DUserBiz;
import net.cedu.demo.dao.user.DCityDao;
import net.cedu.demo.dao.user.DCreditCacheDao;
import net.cedu.demo.dao.user.DSexDao;
import net.cedu.demo.dao.user.DUserDao;
import net.cedu.demo.entity.DCity;
import net.cedu.demo.entity.DCredit;
import net.cedu.demo.entity.DSex;
import net.cedu.demo.entity.DUser;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 接口实现类
 * 
 * @author yangdongdong
 * 
 */
@Service
public class DUserBizImpl implements DUserBiz {

	@Autowired
	private DUserDao duserDao;
	@Autowired
	private DSexDao dsexDao;
	@Autowired
	private DCityDao dcityDao;
	@Autowired
	private DCreditCacheDao dcreditCacheDao;

	public int findCreditCountByDetails(int type, PageResult<DCredit> pr) {
		PageParame p = new PageParame(pr);
		if (type == -1) {
			p.setHqlConditionExpression(null);

		} else if (type == 0) {
			p.setHqlConditionExpression(" and fraction<"
					+ Constants.PLACEHOLDER);
			p.setValues(new Object[] { 60 });
		} else if (type == 1) {
			p.setHqlConditionExpression(" and fraction>="
					+ Constants.PLACEHOLDER);
			p.setValues(new Object[] { 60 });
		}
		return dcreditCacheDao.getCounts(p);
	}

	public List<DCredit> findCreditByDetails(int type, PageResult<DCredit> pr) {
		try {
			List<DCredit> credits = null;
			// Ids集合
			PageParame p = new PageParame(pr);
			if (type == -1) {
				p.setHqlConditionExpression(null);

			} else if (type == 0) {
				p.setHqlConditionExpression(" and fraction<"
						+ Constants.PLACEHOLDER);
				p.setValues(new Object[] { 60 });
			} else if (type == 1) {
				p.setHqlConditionExpression(" and fraction>="
						+ Constants.PLACEHOLDER);
				p.setValues(new Object[] { 60 });
			}

			Long[] creditIds = dcreditCacheDao.getIDs(p);
			if (creditIds != null && creditIds.length != 0) {
				credits = new ArrayList<DCredit>();
				for (int i = 0; i < creditIds.length; i++) {
					// 内存获取成绩
					DCredit creditObj = dcreditCacheDao.findById(creditIds[i]);
					if (creditObj != null) {
						DCredit obj = creditObj;
						DUser user = duserDao.findById(obj.getUid());
						DCity city = dcityDao.findById(user.getCid());
						DSex sex = dsexDao.findById(user.getSid());
						user.setCity(city);
						user.setSex(sex);
						obj.setUser(user);
						credits.add(obj);
					}
				}
			}

			return credits;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

}
