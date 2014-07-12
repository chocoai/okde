package net.cedu.biz.finance;

import net.cedu.entity.finance.FeePayment;

/**
 * （财务管理）退费Biz
 * 
 * @author dongminghao
 * 
 */
public interface RefundBiz {
	/**
	 * 根据条件查询退费单ids（含时间范围查询）
	 * @param feePayment
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws Exception
	 */
	public String findFeePaymentsByParams(FeePayment feePayment,String starttime,String endtime)throws Exception;
	
	/**
	 * 根据id和要修改的状态判断
	 * 该退费单下的明细知否全为该状态，如果全是则修改退费单的状态
	 * @param feePayment
	 * @return
	 * @throws Exception
	 */
	public boolean updateFeepaymentsStatusByIdAndDetailStatus(FeePayment feePayment)throws Exception;
}
