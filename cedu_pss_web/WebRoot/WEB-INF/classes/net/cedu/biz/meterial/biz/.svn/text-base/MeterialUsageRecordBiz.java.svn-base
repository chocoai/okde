package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialUsageRecord;
import net.cedu.model.page.PageResult;

/**
 * 个人领用单 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface MeterialUsageRecordBiz {

	
	/**
	 * 按编号和名称查询个人领用单
	 * @param meterialusagerecord
	 * @param pr
	 * @return list
	 * @throws Exception
	 */
	public List<MeterialUsageRecord> findMeterialUsageRecordPageListByCodeApplication(MeterialUsageRecord meterialusagerecord, PageResult<MeterialUsageRecord> pr)
			throws Exception;

		 
	/**
	 * 按编号和名称查询个人领用单的数量
	 * @param meterialusagerecord
	 * @param pr
	 * @return int
	 * @throws Exception
	 */
	public int findMeterialUsageRecordPageCountByCodeApplication(MeterialUsageRecord meterialusagerecord, PageResult<MeterialUsageRecord> pr)
			throws Exception ;
	
 
	/**
	 * 增加方法
	 * @param meterialusagerecord
	 * @throws Exception
	 */
	public void saveMeterialUsageRecord(MeterialUsageRecord meterialusagerecord) throws Exception;
	
	/**
	 * 修改方法
	 * @param meterialusagerecord
	 * @throws Exception
	 */
	void updateMeterialUsageRecord(MeterialUsageRecord meterialusagerecord) throws Exception;
	
	/**
	 * 根据主键ID查询领用单
	 * @param id
	 * @return int
	 */
	MeterialUsageRecord findById(int id);	

}
