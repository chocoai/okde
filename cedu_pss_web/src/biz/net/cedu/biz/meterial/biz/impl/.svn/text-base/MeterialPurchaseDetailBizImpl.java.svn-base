package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialPurchaseDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.meterial.MeterialPurchaseDetailDao;
import net.cedu.entity.meterial.MeterialPurchaseDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 采购单明细 业务逻辑实现层
 * @author YY
 * */
public class MeterialPurchaseDetailBizImpl implements MeterialPurchaseDetailBiz  {

	@Autowired
	private MeterialPurchaseDetailDao  meterialpurchasedetaildao;
	
	/*
	 *  总部采购查询
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialPurchaseDetailBiz#findById(int)
	 */
	public List<MeterialPurchaseDetail> findById(int id) throws Exception {
		List<MeterialPurchaseDetail> meteriallist = new ArrayList<MeterialPurchaseDetail>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (0 != id) {
 
				hqlparam += " and  purchaseId= " + Constants.PLACEHOLDER;
				paramList.add(id);
 
		}

		meteriallist = meterialpurchasedetaildao.getByProperty(hqlparam, paramList);
		return meteriallist;
	}

	/*
	 * 增加总部采购
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialPurchaseDetailBiz#saveMeterialPurchaseDetail(net.cedu.entity.meterial.MeterialPurchaseDetail)
	 */
	public void saveMeterialPurchaseDetail(
			MeterialPurchaseDetail meterialpurchasedetail) throws Exception {
		 
		meterialpurchasedetaildao.save(meterialpurchasedetail);
		
	}

	
}
