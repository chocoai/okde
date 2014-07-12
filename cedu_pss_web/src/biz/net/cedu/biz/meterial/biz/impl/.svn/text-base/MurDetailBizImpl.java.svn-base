package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MurDetailBiz;
import net.cedu.common.Constants;
import net.cedu.dao.meterial.MurDetailDao;
import net.cedu.entity.meterial.MurDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
/**
 *  领用单明细 业务逻辑实现层
 * @author YY
 * */
public class MurDetailBizImpl implements MurDetailBiz {

	@Autowired
	private MurDetailDao murdetaildao;
	
	/*
	 * 根据ID查询集合
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MurDetailBiz#findById(int)
	 */
	public List<MurDetail> findById(int id) throws Exception {
		List<MurDetail> meteriallist = new ArrayList<MurDetail>();
		String hqlparam = "";
		List<Object> paramList = new ArrayList<Object>();
		if (0 != id) {
 
				hqlparam += " and  applicationId= " + Constants.PLACEHOLDER;
				paramList.add(id);
 
		}

		meteriallist = murdetaildao.getByProperty(hqlparam, paramList);
		return meteriallist;
	}

	/*
	 * 增加方法
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MurDetailBiz#saveMurDetail(net.cedu.entity.meterial.MurDetail)
	 */
	public void saveMurDetail(MurDetail murdetail) throws Exception {
 
		murdetaildao.save(murdetail);
	}
}
