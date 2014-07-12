package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.meterial.biz.MeterialBiz;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.biz.meterial.biz.MeterialStoreroomBiz;
import net.cedu.biz.meterial.biz.MeterialTransferBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.meterial.MeterialTransferDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.meterial.Meterial;
import net.cedu.entity.meterial.MeterialCategory;
import net.cedu.entity.meterial.MeterialStoreroom;
import net.cedu.entity.meterial.MeterialTransfer;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 物料移库 业务逻辑实现层
 * @author YY
 * */
public class MeterialTransferBizImpl implements MeterialTransferBiz {

	@Autowired
	private MeterialTransferDao meterialTransferDao; //移库数据层
 
 
	
	@Autowired 
	private MeterialCategoryBiz meterialCategoryBiz;   //物料分类数据层
	
	@Autowired
	private MeterialStoreroomBiz meterialStoreroomBiz;  //库房业务层
	@Autowired
	private MeterialBiz meterialBiz;  //物料业务层
	@Autowired
	private BranchDao branchDao; //学习中心业务层
	
	/*
	 * 分页方法（数量）
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialTransferBiz#countMeterialTransferByParams(net.cedu.entity.meterial.MeterialTransfer, net.cedu.model.page.PageResult)
	 */
	public int countMeterialTransferByParams(MeterialTransfer meterialTransfer,
			PageResult<MeterialTransfer> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (meterialTransfer != null) {
			//查询库房移库位置
			if (0<(meterialTransfer.getFromId())) {
				hql += "and fromId = " + Constants.PLACEHOLDER;

				paramlist.add(meterialTransfer.getFromId());
			}
			//查询库房移出名称
			if(StringUtils.isNotBlank(meterialTransfer.getFromname()))
			{
				Long [] ids=meterialStoreroomBiz.findMeterialStoreroomByNames(meterialTransfer.getFromname());
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
					 hql+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());
				}
				
			}
			//入库位置
			if (0<(meterialTransfer.getToId())) {
				hql += "and toId = " + Constants.PLACEHOLDER;

				paramlist.add( meterialTransfer.getToId());
			}
			//入库名称
			if(StringUtils.isNotBlank(meterialTransfer.getToname()))
			{
				Long [] ids=meterialStoreroomBiz.findMeterialStoreroomByNames(meterialTransfer.getToname());
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
					 hql+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());	
				}
				 
			}
			//物料名称
			if (0<(meterialTransfer.getMeterialId())) {
				hql += "and meterialId like " + Constants.PLACEHOLDER;

				paramlist.add("%" + meterialTransfer.getMeterialId() + "%");
			}
			//物料分类
			if(StringUtils.isNotBlank(meterialTransfer.getMeterialname()))
			{
				Long [] ids=meterialBiz.findMeterialByNames(meterialTransfer.getMeterialname());
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
					 hql+="and meterialId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());	 
				}
				
			}
			
			if (-1 < meterialTransfer.getDeleteFlag()) {
				hql += " and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());

			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		int count = meterialTransferDao.getCounts(p);

		return count;
	}
	
	/*分页方法（集合）
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialTransferBiz#findMeterialTransferByParams(net.cedu.entity.meterial.MeterialTransfer, net.cedu.model.page.PageResult)
	 */
	public List<MeterialTransfer> findMeterialTransferByParams(
			MeterialTransfer meterialTransfer, PageResult<MeterialTransfer> pr)
			throws Exception {
		List<MeterialTransfer> meteriallist = new ArrayList<MeterialTransfer>();
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (meterialTransfer != null) {
			//查询库房移库位置
			if (0<(meterialTransfer.getFromId())) {
				hql += "and fromId = " + Constants.PLACEHOLDER;

				paramlist.add(meterialTransfer.getFromId());
			}
			//查询库房移出名称
			if(StringUtils.isNotBlank(meterialTransfer.getFromname()))
			{
				Long [] ids=meterialStoreroomBiz.findMeterialStoreroomByNames(meterialTransfer.getFromname());
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
					 hql+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());
				}
				
			}
			//入库位置
			if (0<(meterialTransfer.getToId())) {
				hql += "and toId = " + Constants.PLACEHOLDER;

				paramlist.add( meterialTransfer.getToId());
			}
			//入库名称
			if(StringUtils.isNotBlank(meterialTransfer.getToname()))
			{
				Long [] ids=meterialStoreroomBiz.findMeterialStoreroomByNames(meterialTransfer.getToname());
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
					 hql+="and storeroomId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());	
				}
				 
			}
			//物料名称
			if (0<(meterialTransfer.getMeterialId())) {
				hql += "and meterialId like " + Constants.PLACEHOLDER;

				paramlist.add("%" + meterialTransfer.getMeterialId() + "%");
			}
			//物料分类
			if(StringUtils.isNotBlank(meterialTransfer.getMeterialname()))
			{
				Long [] ids=meterialBiz.findMeterialByNames(meterialTransfer.getMeterialname());
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
					 hql+="and meterialId in ("+Constants.PLACEHOLDER+")";
					 paramlist.add("$"+sb.toString());	 
				}
				
			}
			
			if (-1 < meterialTransfer.getDeleteFlag()) {
				hql += " and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());

			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] meterialIds = meterialTransferDao.getIDs(p);
		if (meterialIds != null && meterialIds.length != 0) {
			for (int i = 0; i < meterialIds.length; i++) {
				MeterialTransfer mt = meterialTransferDao.findById(Integer.parseInt(meterialIds[i].toString()));
				if (mt != null) {
					MeterialTransfer mTransfer=mt;
					Meterial meterial=meterialBiz.findById(mTransfer.getMeterialId());
					MeterialStoreroom ms=meterialStoreroomBiz.findStoreroomByPosition((mTransfer.getFromId()));
					MeterialStoreroom msr=meterialStoreroomBiz.findStoreroomByPosition(mTransfer.getToId());
					
					if(meterial!=null)
					{
						mTransfer.setMeterialname(meterial.getName());
						MeterialCategory mc=meterialCategoryBiz.findById(meterial.getCategoryId());
						if(mc!=null)
							{
								mTransfer.setMeterialfen(mc.getName());
							}
					}
					
					if(ms!=null)
					{
						mTransfer.setFromname(ms.getName());
						Branch br=branchDao.findById(ms.getPosition());
						if(null!=br){
						mTransfer.setFromplace(br.getName());
						}
					}
					if(msr!=null)
					{
						mTransfer.setToname(msr.getName());
						Branch br=branchDao.findById(msr.getPosition());
						if(null!=br){
						mTransfer.setToplace(br.getName());
						}
					}
					meteriallist.add(mTransfer);
				}
			}
		}

		return meteriallist;
	}

	/*
	 * 增加移库数据
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialTransferBiz#saveMeterialTransfer(net.cedu.entity.meterial.MeterialTransfer)
	 */
	public void saveMeterialTransfer(
			MeterialTransfer meterialTransfer) throws Exception {
		 
		meterialTransferDao.save(meterialTransfer);
	}
}