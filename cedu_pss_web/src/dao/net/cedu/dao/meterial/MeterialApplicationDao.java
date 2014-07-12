package net.cedu.dao.meterial;

import java.util.List;

import net.cedu.dao.BaseDao;
import net.cedu.entity.meterial.MeterialApplication;

/**
 * 中心申请单  数据访问接口
 * @author YY
 *
 */
public interface MeterialApplicationDao extends BaseDao<MeterialApplication> {

	/**
	 * 条件查询 
	 * @param meterialapplication
	 * @return
	 */
	public List<MeterialApplication> findbyMeterialApplication(
			MeterialApplication meterialapplication);
}
