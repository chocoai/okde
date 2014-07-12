package net.cedu.biz.book.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.MeasuringUnitBiz;
import net.cedu.common.Constants;
import net.cedu.dao.book.MeasuringUnitDao;
import net.cedu.entity.book.MeasuringUnit;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 计量单位业务层实现类
 * 
 * @author XFY
 * 
 */
@Service
public class MeasuringUnitBizImpl extends BaseAction implements MeasuringUnitBiz{
 
	private static final long serialVersionUID = -6567182885675718003L;
	@Autowired
	private MeasuringUnitDao measuringunitdao;
	/**
	 * 增加计量单位
	 */
	public Object addMeasuringUnit(MeasuringUnit measuringunit)
			throws Exception {
		Object obj=null;
		obj=measuringunitdao.save(measuringunit);
		if(obj==null)
			return obj;
		else
			return obj;
	}
	/**
	 * 计量单位分页
	 */
	public List<MeasuringUnit> findAllMeasuringUnit(PageResult<MeasuringUnit> pr)
			throws Exception {
			List<MeasuringUnit> measuringunitlist=new ArrayList<MeasuringUnit>();
			PageParame p = new PageParame(pr);
			Long[] measuringunitIDs= measuringunitdao.getIDs(p);
			if(null!=measuringunitIDs && measuringunitIDs.length>0)
			{
				for(int i=0;i<measuringunitIDs.length;i++)
				{
					MeasuringUnit mu= measuringunitdao.findById(Integer.parseInt(measuringunitIDs[i].toString()));
					if(null!=mu)
					{
						measuringunitlist.add(mu);
					}
				}
			}
			return measuringunitlist;
	}
	/**
	 * 计量单位分页数量
	 */
	public int findAllMeasuringUnitCount(PageResult<MeasuringUnit> pr)
			throws Exception {
		
		PageParame p = new PageParame(pr);
		
		return measuringunitdao.getCounts( p);
	}
	/**
	 * 删除
	 */
	public void deleteMeasuringUnitById(int id) throws Exception {
		measuringunitdao.deleteById(id);
		
	}
	/**
	 * 按ID查询
	 */
	public MeasuringUnit findMeasuringUnitById(int id) throws Exception {
		return measuringunitdao.findById(id);
	}
	public void updateMeasuringUnit(MeasuringUnit measuringunit)
			throws Exception {
		measuringunitdao.update(measuringunit);
		
	}
	/**
	 * 查询所有
	 */
	public List<MeasuringUnit> findMeasuringUnitAll() throws Exception {
		return measuringunitdao.findAll();
	}
	/**
	 *验证方法 
	 */
	public MeasuringUnit findByNameOrCodeMeasuringUnit(String code,String name) throws Exception
	{
		MeasuringUnit unit=new MeasuringUnit();
		String hql="";
		List<Object> list=new ArrayList<Object>();
		if(null!=code)
		{
			hql+=" and code ="+Constants.PLACEHOLDER;
			list.add(code);
		}
		if(null!=name)
		{
			hql+=" and name = "+Constants.PLACEHOLDER;
			list.add(name);
		}
		unit=measuringunitdao.getObjByProperty(hql, list.toArray());
		 
		return  unit;
		
	}

}
