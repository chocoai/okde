package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.biz.meterial.biz.MeterialStockBiz;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.meterial.MeterialStockDao;
import net.cedu.entity.meterial.MeterialStock;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 物料库存 业务逻辑实现层
 * @author YY
 * */
public class MeterialStockBizImpl implements MeterialStockBiz {

	@Autowired
	private MeterialStockDao meterialStockDao;
	@Autowired
	private MeterialStoreroomBiz meterialStoreroomBiz;
	@Autowired
	private MeterialBiz meterialBiz;
	

	/*
	 * 条件查询 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialStockBiz#findall(net.cedu.entity.meterial
	 * .MeterialStock)
	 */
	public List<MeterialStock> findMeterialStockByStock(
			MeterialStock meterialstock) throws Exception {
		List<MeterialStock> meterialStocklist = new ArrayList<MeterialStock>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (null != meterialstock) {
			if (0 < meterialstock.getStoreroomId()) {
				hqlparam += " and  storeroomId= " + Constants.PLACEHOLDER;
				paramList.add(meterialstock.getStoreroomId());
			}
			if(StringUtils.isNotBlank(meterialstock.getStoreroomname()))
			{
				Long [] ids=meterialStoreroomBiz.findMeterialStoreroomByNames(meterialstock.getStoreroomname());
				StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					 
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }	 
				}
				 hqlparam+="and storeroomId in ("+Constants.PLACEHOLDER+")";
				 paramList.add("$"+sb.toString());	 
			}
			if (0 < meterialstock.getMeterialId()) {
				hqlparam += " and  meterialId= " + Constants.PLACEHOLDER;
				paramList.add(meterialstock.getMeterialId());
			}
			if(StringUtils.isNotBlank(meterialstock.getMeterialname()))
			{
				Long [] ids=meterialBiz.findMeterialByNames(meterialstock.getMeterialname());
				StringBuffer sb = new StringBuffer();
				if(null!=ids && ids.length>0)
				{
					 
					 sb.append(",");
					 for(Long id :ids){
						if(sb.equals(",")){
							sb.replace(0, sb.length(), id+"");
						}else{
							sb.append(","+id);
						}
					 }
				}
				 hqlparam+="and meterialId in ("+Constants.PLACEHOLDER+")";
				 paramList.add("$"+sb.toString());	 
				 
			}
			hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
			paramList.add(UserEnum.DeleteNo.value());

		}

		meterialStocklist = meterialStockDao.getByProperty(hqlparam, paramList);
		return meterialStocklist;
	}

	/**
	 * 根据库房id查询库房物料库存
	 */
	public MeterialStock findMeterialStockByroomId(int id) throws Exception{
		MeterialStock meterialStock = new MeterialStock();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (0 != id) {
			hqlparam += " and  storeroomId= " + Constants.PLACEHOLDER;
			paramList.add(id);
		}
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		paramList.add(UserEnum.DeleteNo.value());

		meterialStock = meterialStockDao.getObjByProperty(hqlparam, paramList);
		return meterialStock;
	}

}
