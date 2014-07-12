package net.cedu.dao.meterial;

import java.util.List;

import net.cedu.dao.BaseDao;
import net.cedu.entity.meterial.MeterialApplicationDetail;
import net.cedu.entity.meterial.MurDetail;

/**
 * 领用单明细  数据访问接口
 * @author YY
 *
 */
public interface MurDetailDao extends BaseDao<MurDetail> {

	/**
	 * 根据ID查询集合
	 * @param id
	 * @return
	 */
	public List<MurDetail> findMurDetailByid(int id) throws Exception;
	
	
}
