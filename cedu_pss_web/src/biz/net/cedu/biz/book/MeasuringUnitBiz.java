package net.cedu.biz.book;

import java.util.List;

import net.cedu.entity.book.MeasuringUnit;
import net.cedu.model.page.PageResult;

/**
 * 计量单位业务层接口
 * 
 * @author XFY
 * 
 */
public interface MeasuringUnitBiz {
	/**
	 * 增加
	 * @param textbook
	 * @return
	 * @throws Exception
	 */
	Object addMeasuringUnit(MeasuringUnit measuringunit)throws Exception;
	
	/**
	 * 查询-分页
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	List<MeasuringUnit> findAllMeasuringUnit( PageResult<MeasuringUnit> pr)throws Exception;
	
	/**
	 * 分页-数量
	 * @param condition
	 * @param result
	 * @return 
	 * @throws Exception
	 */
	int findAllMeasuringUnitCount(PageResult<MeasuringUnit> pr)throws Exception;
	/**
	 * 删除
	 */
	void deleteMeasuringUnitById(int id)throws Exception;
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	MeasuringUnit findMeasuringUnitById(int id)throws Exception;
	/**
	 * 修改
	 * @param measuringunit
	 * @throws Exception
	 */
	void updateMeasuringUnit(MeasuringUnit measuringunit)throws Exception;
	/**
	 * 查询所有
	 * @return
	 * @throws Exception
	 */
	List<MeasuringUnit> findMeasuringUnitAll() throws Exception;
	/**
	 *验证方法 
	 */
	public MeasuringUnit findByNameOrCodeMeasuringUnit(String code,String name) throws Exception;
}
