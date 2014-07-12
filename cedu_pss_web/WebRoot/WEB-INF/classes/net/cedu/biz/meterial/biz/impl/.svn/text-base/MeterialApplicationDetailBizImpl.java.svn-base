package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialApplicationDetailBiz;
import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.common.Constants;
import net.cedu.dao.meterial.MeterialApplicationDetailDao;
import net.cedu.entity.meterial.MeterialApplicationDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 申请单明细业务逻辑实现层
 * @author YY
 * */
public class MeterialApplicationDetailBizImpl implements
		MeterialApplicationDetailBiz {

	@Autowired
	private MeterialApplicationDetailDao maddao; //中心申请数据称接口
	@Autowired
	private MeterialBiz meterialBiz; //物料业务层

	/*
	 * 根据ID查询明细 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.meterial.biz.MeterialApplicationDetailBiz#findById(int)
	 */
	public List<MeterialApplicationDetail> findById(int code) throws Exception {
	 
		List<MeterialApplicationDetail> meteriallist = new ArrayList<MeterialApplicationDetail>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (0 != code) {
 
				hqlparam += " and  applicationId= " + Constants.PLACEHOLDER;
				paramList.add(code);
 
		}
		
		meteriallist = maddao.getByProperty(hqlparam, paramList);
		
		for (MeterialApplicationDetail mad : meteriallist) {
			//为物料名称赋值
			if(mad!=null)
			mad.setMeterialname(meterialBiz.findById(mad.getMeterialId()).getName());			
		}
		
		return meteriallist;
	}

	/*
	 * 增加方法 (non-Javadoc)
	 * 
	 * @seenet.cedu.biz.meterial.biz.MeterialApplicationDetailBiz#
	 * saveMeterialApplicationDetail
	 * (net.cedu.entity.meterial.MeterialApplicationDetail)
	 */
	public void saveMeterialApplicationDetail(
			MeterialApplicationDetail meterialapplicationdetail)
			throws Exception {
	 
		maddao.save(meterialapplicationdetail);

	}
	
	/**
	 * 更新方法
	 */
	public void UpdateMeterialApplicationDetail(
			MeterialApplicationDetail meterialapplicationdetail)
			throws Exception {
		maddao.update(meterialapplicationdetail);
		
	}

}
