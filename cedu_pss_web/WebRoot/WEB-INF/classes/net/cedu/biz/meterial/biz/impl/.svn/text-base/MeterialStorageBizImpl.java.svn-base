package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialStorageBiz;
import net.cedu.common.Constants;
import net.cedu.dao.meterial.impl.MeterialStorageDaoImpl;
import net.cedu.entity.meterial.MeterialStorage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 物料入库方式 业务逻辑实现层
 * @author YY
 * */
public class MeterialStorageBizImpl implements MeterialStorageBiz {

	@Autowired
	private MeterialStorageDaoImpl meterialstoragedao;

	/*
	 * 判断用户明是否存在 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStorageBiz#FinMeterialStorageByName
	 * (java.lang.String)
	 */
	public boolean FinMeterialStorageByName(String name) throws Exception {

		return meterialstoragedao.findMeterialStorageByName(name);
	}

	/*
	 * 删除物料入库方式方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStorageBiz#deleteMeterialStorage(int)
	 */
	public void deleteMeterialStorage(int id) throws Exception {
 
		meterialstoragedao.deleteById(id);
	}

	/*
	 * 查询全部物料入库方式方法 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.meterial.biz.MeterialStorageBiz#finmeterialstorageall()
	 */
	public List<MeterialStorage> finmeterialstorageall() throws Exception {
 
		return meterialstoragedao.findAll();
	}

	/*
	 * 增加物料入库方式方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStorageBiz#saveMeterialStorage(net.
	 * cedu.entity.meterial.MeterialStorage)
	 */
	public boolean saveMeterialStorage(MeterialStorage meterialstoryage)
			throws Exception {

		//if (meterialstoragedao.findMeterialStorageByName(meterialstoryage
			//	.getName()) == true) {
			meterialstoragedao.save(meterialstoryage);
			return true;
		//} else
		//	return false;
	}

	/*
	 * 修改物料入库方式方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStorageBiz#updateMeterialStoryage(net
	 * .cedu.entity.meterial.MeterialStorage)
	 */
	public boolean updateMeterialStoryage(MeterialStorage meterialstorage)
			throws Exception {
		//if (meterialstoragedao.findMeterialStorageByName(meterialstorage
			//	.getName()) == true) {
			meterialstoragedao.update(meterialstorage);
		//	return true;
	//	} else
			return false;
	}

	public MeterialStorage findMeterialStorageById(int id) throws Exception {
		 
		return meterialstoragedao.findById(id);
		
	}
	public MeterialStorage findmeterialStorageByName(String name,String code) throws Exception{
		String hqlcon = "";
		List<MeterialStorage> list = null;
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
			list = meterialstoragedao.getByProperty(hqlcon, paramList);
		if (null != list && 0 < list.size())
			return list.get(0);
		return null;
	}

}
