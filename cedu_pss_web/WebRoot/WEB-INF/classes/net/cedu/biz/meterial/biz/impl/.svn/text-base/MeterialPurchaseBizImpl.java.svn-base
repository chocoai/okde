package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialPurchaseBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.meterial.MeterialPurchaseDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.meterial.MeterialPurchase;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
/**
 * 总部采购 业务逻辑实现层
 * @author YY
 * */
public class MeterialPurchaseBizImpl  implements MeterialPurchaseBiz {

	@Autowired
	private MeterialPurchaseDao mpdao;
	@Autowired
	private UserBiz userBiz; 
	
	/*
	 * 根据id删除
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialPurchaseBiz#deleteMeterialPurchase(int)
	 */
	public void deleteMeterialPurchase(int id) throws Exception {
		mpdao.deleteById(id);
		
	}

	/*
	 * 根据id查询对象
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialPurchaseBiz#findByIdMeterialPurchase(int)
	 */
	public MeterialPurchase findByIdMeterialPurchase(int id) throws Exception {
		
		return mpdao.findById(id);
	}
	//分页数量
	public int findMeterialPurchasePageCountByCodeApplication(
			MeterialPurchase meterialpurchase, PageResult<MeterialPurchase> pr)
			throws Exception {
		 
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (meterialpurchase != null) {
			
			if(StringUtils.isNotBlank(meterialpurchase.getCode()))
			{
				hql+="and code like "+Constants.PLACEHOLDER;
				paramlist.add("%"+meterialpurchase.getCode()+"%");
			}
			if(StringUtils.isNotBlank(meterialpurchase.getOperatorsname()))
			{
				Long [] ids=userBiz.findUserByNames(meterialpurchase.getOperatorsname());
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
				hql+="and operators in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	 
				 
			}
			if(-1<meterialpurchase.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());			 
			}
//			hql+="and id=" + Constants.PLACEHOLDER;
//			paramlist.add(meterialapplication.getId());
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=mpdao.getCounts(p);
		
		return count;
	}
	//分页集合
	public List<MeterialPurchase> findMeterialPurchasePageListByCodeApplication(
			MeterialPurchase meterialpurchase, PageResult<MeterialPurchase> pr)
			throws Exception {
		List<MeterialPurchase> meteriallist = new ArrayList<MeterialPurchase>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (meterialpurchase != null) {
			
			if(StringUtils.isNotBlank(meterialpurchase.getCode()))
			{
				hql+="and code like "+Constants.PLACEHOLDER;
				paramlist.add("%"+meterialpurchase.getCode()+"%");
			}
			if(StringUtils.isNotBlank(meterialpurchase.getOperatorsname()))
			{
				Long [] ids=userBiz.findUserByNames(meterialpurchase.getOperatorsname());
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
				 hql+="and operators in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	 
			}
			if(-1<meterialpurchase.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
				 
			}
//			hql+="and id=" + Constants.PLACEHOLDER;
//			paramlist.add(meterialapplication.getId());
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] mpIds = mpdao.getIDs(p);
		if (mpIds != null && mpIds.length != 0) {
			for (int i = 0; i < mpIds.length; i++) {
				MeterialPurchase m = mpdao.findById(Integer.parseInt(mpIds[i].toString()));
				if(m!=null){
					MeterialPurchase purchase=m;
					User user=userBiz.findUserById(purchase.getCreatorId());
					if(user!=null)
					{
						purchase.setOperatorsname(user.getFullName());
					}
				}
				if (m != null) {					
					meteriallist.add(m);
				}
			}
		}

		return meteriallist;

	}
	//查询全部
	public List<MeterialPurchase> findall() throws Exception {
		return mpdao.findAll();
	}
	//增加
	public void saveMeterialPurchase(MeterialPurchase meterialpurchase)
			throws Exception {
		mpdao.save(meterialpurchase);
		
	}
	//修改
	public void updateMeterialPurchase(MeterialPurchase mp) throws Exception {
		mpdao.update(mp);
		
	}

}
