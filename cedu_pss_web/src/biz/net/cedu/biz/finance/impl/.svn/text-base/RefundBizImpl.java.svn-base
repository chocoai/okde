package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.RefundBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退费单
 * 
 * @author dongminghao
 *
 */
@Service
public class RefundBizImpl implements RefundBiz {
	@Autowired
	private FeePaymentDao feePaymentDao;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;

	/*
	 * 根据条件查询退费单ids（含时间范围查询）
	 * @see net.cedu.biz.finance.RefundBiz#findFeePaymentsByParams(net.cedu.entity.finance.FeePayment)
	 */
	public String findFeePaymentsByParams(FeePayment feePayment,String starttime,String endtime)throws Exception 
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = null;
		if(feePayment!=null)
		{
			list = new ArrayList<Object>();
			// 全局批次
			if(feePayment.getCommonBatch()!=0)
			{
				hqlparam += "and commonBatch = " + Constants.PLACEHOLDER;
				list.add(feePayment.getCommonBatch());
			}
			// 退费单号
			if(feePayment.getCode()!=null && !feePayment.getCode().equals(""))
			{
				hqlparam += " and code = " + Constants.PLACEHOLDER;
				list.add(feePayment.getCode());
			}
			// 退费单状态
			if(feePayment.getStatus()!=0)
			{
				hqlparam += " and  status = " + Constants.PLACEHOLDER;
				list.add(feePayment.getStatus());
			}
			// 退费日期起
			if (starttime != null && !starttime.equals("")) {
				hqlparam += " and  createdTime >= " + Constants.PLACEHOLDER;
				list.add(starttime);
			}
			// 退费日期止
			if (endtime != null && !endtime.equals("")) {
				hqlparam += " and  createdTime <= " + Constants.PLACEHOLDER;
				list.add(endtime);
			}
			//退费单
			hqlparam += " and  status < " + Constants.PLACEHOLDER;
			list.add(Constants.PAYMENT_STATUS_ZUO_FEI);
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			StringBuffer ids = new StringBuffer();
			ids.append("");
			Long[] idArray = feePaymentDao.getIDs(p);
			if (idArray != null) {
				for (Long id : idArray) {
					if (ids.toString().equals("")) {
						ids.append("" + id);
					} else {
						ids.append("," + id);
					}
				}
			}
			if (ids.toString().equals(",")) {
				return "0";
			}
			return ids.toString();
		}
		return "0";
	}

	/*
	 * 根据id和要修改的状态判断
	 * 该退费单下的明细知否全为该状态，如果全是则修改退费单的状态
	 * @see net.cedu.biz.finance.RefundBiz#updateFeepaymentsStatusByIdAndDetailStatus(net.cedu.entity.finance.FeePayment)
	 */
	public boolean updateFeepaymentsStatusByIdAndDetailStatus(FeePayment feePayment)
			throws Exception {
		if(feePayment!=null && feePayment.getId()>0)
		{
			List<FeePaymentDetail> fpdList = feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
			if(fpdList!=null && fpdList.size()>0)
			{
				for(FeePaymentDetail fpd : fpdList)
				{
					//只要有其中一个退费单明细不一样则不修改
					if(fpd.getStatus()!=feePayment.getStatus())
					{
						return false;
					}
				}
				Object obj = feePaymentDao.update(feePayment);
				return obj==null?false:true;
			}
		}
		return false;
	}

}
