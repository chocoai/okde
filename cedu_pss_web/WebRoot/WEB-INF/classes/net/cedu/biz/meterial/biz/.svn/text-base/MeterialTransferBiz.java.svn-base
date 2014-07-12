package net.cedu.biz.meterial.biz;

import java.util.List;

import net.cedu.entity.meterial.MeterialTransfer;
import net.cedu.model.page.PageResult;


/**
 * 物料移库 业务逻辑接口
 * 
 * @author YY
 * 
 */
public interface MeterialTransferBiz {

	/**
	 * 查询移库(数量)
	 * @param pr 
	 * @return int
	 * @throws Exception
	 */
	public int countMeterialTransferByParams(MeterialTransfer meterialTransfer,PageResult<MeterialTransfer> pr)throws Exception;
	
	/**
	 * 查询移库(集合)
	 * @param pr 
	 * @return List
	 * @throws Exception
	 */
	public List<MeterialTransfer> findMeterialTransferByParams(MeterialTransfer meterialTransfer,PageResult<MeterialTransfer> pr)throws Exception;
	
	/**
	 * 增加方法
	 * @param meterialTransfer
	 * @return void
	 * @throws Exception
	 */
     public void saveMeterialTransfer(MeterialTransfer meterialTransfer) throws Exception;
	 
}
