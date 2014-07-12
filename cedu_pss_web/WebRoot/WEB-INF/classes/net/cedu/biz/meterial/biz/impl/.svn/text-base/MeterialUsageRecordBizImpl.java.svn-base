package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialUsageRecordBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.meterial.MeterialUsagerRecordDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.meterial.MeterialUsageRecord;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
/**
 * 个人领用单 业务逻辑实现层
 * @author YY
 * */
public class MeterialUsageRecordBizImpl implements MeterialUsageRecordBiz {

	@Autowired
	private MeterialUsagerRecordDao meterialusagerrecorddao;
	@Autowired
	private UserBiz userBiz;
	/*
	 * 个人领用单数量
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialUsagerRecordBiz#findMeterialUsageRecordPageCountByCodeApplication(net.cedu.entity.meterial.MeterialUsageRecord, net.cedu.model.page.PageResult)
	 */
	public int findMeterialUsageRecordPageCountByCodeApplication(
			MeterialUsageRecord meterialusagerecord,
			PageResult<MeterialUsageRecord> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (meterialusagerecord != null) {

			if (StringUtils.isNotBlank(meterialusagerecord.getCode())) {
				hql += "and code like " + Constants.PLACEHOLDER;
				
				paramlist.add("%" + meterialusagerecord.getCode() + "%");
			}
			if(StringUtils.isNotBlank(meterialusagerecord.getApplicantname()))
			{
				Long [] ids=userBiz.findUserByNames(meterialusagerecord.getApplicantname());
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
				 hql+="and applicant in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	 
				 
			}
			if(-1<meterialusagerecord.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
				 
			}
 
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=meterialusagerrecorddao.getCounts(p);
		
		return count;
	}

	/*
	 * 个人领用单分页
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialUsagerRecordBiz#findMeterialUsageRecordPageListByCodeApplication(net.cedu.entity.meterial.MeterialUsageRecord, net.cedu.model.page.PageResult)
	 */
	public List<MeterialUsageRecord> findMeterialUsageRecordPageListByCodeApplication(
			MeterialUsageRecord meterialusagerecord,
			PageResult<MeterialUsageRecord> pr) throws Exception {
		List<MeterialUsageRecord> meteriallist = new ArrayList<MeterialUsageRecord>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (meterialusagerecord != null) {
			
			if(StringUtils.isNotBlank(meterialusagerecord.getCode()))
			{
				hql+="and code like "+Constants.PLACEHOLDER;
				paramlist.add("%"+meterialusagerecord.getCode()+"%");
			}
			if(StringUtils.isNotBlank(meterialusagerecord.getApplicantname()))
			{
				Long [] ids=userBiz.findUserByNames(meterialusagerecord.getApplicantname());
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
				 hql+="and applicant in ("+Constants.PLACEHOLDER+")";
				 paramlist.add("$"+sb.toString());	 
				 
			}
			if(-1<meterialusagerecord.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());
				 
			}
 
 
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}

		Long[] userIds = meterialusagerrecorddao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				MeterialUsageRecord m = meterialusagerrecorddao.findById(Integer.parseInt(userIds[i].toString()));
				if(m!=null){
					MeterialUsageRecord Record=m;
					User user=userBiz.findUserById(Record.getCreatorId());
					if(user!=null)
					{
						Record.setApplicantname(user.getFullName());
					}
				}
				if (m != null) {
					meteriallist.add(m);
				}
			}
		}

		return meteriallist;

	}

	/*
	 * 增加方法
	 * (non-Javadoc)
	 * @see net.cedu.biz.meterial.biz.MeterialUsageRecordBiz#saveMeterialUsageRecord(net.cedu.entity.meterial.MeterialUsageRecord)
	 */
	public void saveMeterialUsageRecord(MeterialUsageRecord meterialusagerecord)
			throws Exception {
		meterialusagerrecorddao.save(meterialusagerecord);
		
	}
	/**
	 * 修改方法
	 */
	public void updateMeterialUsageRecord(
			MeterialUsageRecord meterialusagerecord) throws Exception {
		meterialusagerrecorddao.update(meterialusagerecord);
		
	}

	public MeterialUsageRecord findById(int id) {
		return  meterialusagerrecorddao.findById(id);
	}

}
