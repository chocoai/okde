package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.common.Constants;
import net.cedu.dao.meterial.MeterialStoreroomDao;
import net.cedu.entity.meterial.MeterialStoreroom;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 物料库房设置 业务逻辑实现层
 * @author YY
 * */
public class MeterialStoreroomBizImpl implements MeterialStoreroomBiz {

	@Autowired
	private MeterialStoreroomDao meterialStoreroomdao;

	/*
	 * 判断是否名称存在 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStoreroomBiz#FinMeterialStoreroomByName
	 * (java.lang.String)
	 */
	public boolean FinMeterialStoreroomByName(String name) throws Exception {

		return true;
	}

	/*
	 * 删除物料库房设置 方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStoreroomBiz#deleteMeterialStoreroom
	 * (int)
	 */
	public void deleteMeterialStoreroom(int id) throws Exception {
		meterialStoreroomdao.deleteById(id);

	}

	/*
	 * 查询全部物料库房设置 方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStoreroomBiz#finMeterialStoreroomall()
	 */
	public List<MeterialStoreroom> finMeterialStoreroomall() throws Exception {

		return meterialStoreroomdao.findAll();
	}

	/*
	 * 增加物料库房设置 方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStoreroomBiz#saveMeterialStoreroom(
	 * net.cedu.entity.meterial.MeterialStoreroom)
	 */
	public boolean saveMeterialStoreroom(MeterialStoreroom meterialstoreroom)
			throws Exception {
		//if (FinMeterialStoreroomByName(meterialstoreroom.getName()) == true) {
			meterialStoreroomdao.save(meterialstoreroom);
			return true;
		//} else
		//	return false;
	}

	/*
	 * 修改物料库房设置 方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStoreroomBiz#updateMeterialStoreroom
	 * (net.cedu.entity.meterial.MeterialStoreroom)
	 */
	public boolean updateMeterialStoreroom(MeterialStoreroom meterialstoreroom)
			throws Exception {
		//if (FinMeterialStoreroomByName(meterialstoreroom.getName()) == true) {
			meterialStoreroomdao.update(meterialstoreroom);
			return true;
		//} else
		//	return false;
	}
	/**
	 * 根据id查询
	 */
	public MeterialStoreroom findMeterialStoreroomById(int id) throws Exception {
		
		return meterialStoreroomdao.findById(id);
	}
	/*根据库房位置查询库房名称
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialStoreroomBiz#findMeterialStoreroomByPosition(java.lang.String)
	 */
	public List<MeterialStoreroom> findMeterialStoreroomByPosition(int position)
			throws Exception {
		List<MeterialStoreroom> meteriallist= new ArrayList<MeterialStoreroom>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>(); 
		if(0<position){
				hqlparam += " and  position= " + Constants.PLACEHOLDER;
				paramList.add(position);
				
		}
		meteriallist = meterialStoreroomdao.getByProperty(hqlparam, paramList);
		return  meteriallist;
	}
	/*根据库房名称查库房Id
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialStoreroomBiz#findMeterialStoreroomByPosition(java.lang.String)
	 */
	public Long[] findMeterialStoreroomByNames(String storeroomname) 
				throws Exception {		 
				List<Object> list=new ArrayList<Object>();
				Long [] ids =  null;
				String hql="";
				if(StringUtils.isNotBlank(storeroomname))
				{
					hql += " and name =  " + Constants.PLACEHOLDER;
					list.add(storeroomname);
				}
				 
				
				ids = meterialStoreroomdao.getIDs(hql, list.toArray());
				return ids;
			}
	/**
	 * 根据名称查询库房设置对象
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public MeterialStoreroom findMeterialMeterialStoreroomByName(String name,String code) throws Exception
	{
		String hqlcon = "";
		List<MeterialStoreroom> list = null;
		List<Object> paramList = new ArrayList<Object>();

		// 名称
		if (StringUtils.isNotBlank(name)) {
			hqlcon += " and name =" + Constants.PLACEHOLDER;
			paramList.add(name);
		}
		// 编号
		if (StringUtils.isNotBlank(code)) {
			hqlcon += " and code =" + Constants.PLACEHOLDER;
			paramList.add(code);
		}
		if (0 < hqlcon.length())
			list = meterialStoreroomdao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
		
	}

	public MeterialStoreroom findStoreroomByPosition(int position)
			throws Exception {
		String hqlcon = "";
		List<MeterialStoreroom> list = null;
		List<Object> paramList = new ArrayList<Object>();

		if(0<position){
			hqlcon += " and  position= " + Constants.PLACEHOLDER;
			paramList.add(position);
			
	}
		if (0 < hqlcon.length())
			list = meterialStoreroomdao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
}
}


