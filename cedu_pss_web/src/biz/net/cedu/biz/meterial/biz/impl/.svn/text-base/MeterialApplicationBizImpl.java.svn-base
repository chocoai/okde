package net.cedu.biz.meterial.biz.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.dao.meterial.MeterialApplicationDao;
import net.cedu.entity.admin.User;
import net.cedu.entity.meterial.MeterialApplication;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 中心申请单业务逻辑实现层
 * 
 * @author YY
 * 
 * 
 * */
@Service
public class MeterialApplicationBizImpl implements MeterialApplicationBiz {

	@Autowired
	private MeterialApplicationDao meterialdao;
	@Autowired
	private UserBiz userBiz;
	/*
	 * 查询全部 (non-Javadoc)
	 * 
	 * @see net.cedu.biz.meterial.biz.MeterialApplicationBiz#findall()
	 */
	public List<MeterialApplication> findall() throws Exception {
		 
		return meterialdao.findAll();
	}

	/*
	 * 条件查询 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialApplicationBiz#findbyMeterialApplication
	 * (net.cedu.entity.meterial.MeterialApplication)
	 */
	public List<MeterialApplication> cribyMeterialApplication(
			MeterialApplication meterialapplication) throws Exception {
		return meterialdao.findbyMeterialApplication(meterialapplication);
	}
	/*
	 * 增加方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialApplicationBiz#saveMeterialApplication
	 * (net.cedu.entity.meterial.MeterialApplication)
	 */
	public void saveMeterialApplication(MeterialApplication meterialapplication)
			throws Exception {

		meterialdao.save(meterialapplication);
	}

	/*
	 * 分页方法 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialApplicationBiz#findAllMeterialApplication
	 * (net.cedu.model.page.PageResult)
	 */
	public List<MeterialApplication> findAllMeterialApplication(
			PageResult<MeterialApplication> pr) throws Exception {
		List<MeterialApplication> list = new ArrayList<MeterialApplication>();
		PageParame page = new PageParame(pr);
		Long[] longs = meterialdao.getIDs(page);
		for (int i = 0; i < longs.length; i++) {
			MeterialApplication m = meterialdao.findById(Integer
					.parseInt(longs[i].toString()));
			list.add(m);
		}
		return list;

	}

	/*
	 * 分页数量查询 (non-Javadoc)
	 * 
	 * @seenet.cedu.biz.meterial.biz.MeterialApplicationBiz#
	 * findAllMeterialApplicationCount(net.cedu.model.page.PageResult)
	 */
	public int findAllMeterialApplicationCount(
			PageResult<MeterialApplication> pr) throws Exception {
		PageParame p = new PageParame(pr);
 
		return meterialdao.getCounts(p);
	}

	/*
	 * 删除操作 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialApplicationBiz#deleteMeterialApplication
	 * (net.cedu.entity.meterial.MeterialApplication)
	 */
	public void deleteMeterialApplication(int id) throws Exception {
		meterialdao.deleteById(id);

	}

	/*
	 * 修改操作 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialApplicationBiz#updateMeterialApplication
	 * (net.cedu.entity.meterial.MeterialApplication)
	 */
	public void updateMeterialApplication(MeterialApplication ma)
			throws Exception {
		  meterialdao.update(ma);
	}

	/*
	 * 根据ID查对象 (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.meterial.biz.MeterialApplicationBiz#findByIdMeterialApplication
	 * (int)
	 */
	public MeterialApplication findByIdMeterialApplication(int id)
			throws Exception {

		return meterialdao.findById(id);
	}

	/*
	 * 按编号和名称查询中心申请单 
	 * (non-Javadoc) 
	 * @seenet.cedu.biz.meterial.biz.MeterialApplicationBiz#
	 * findMeterialApplicationPageListByCodeApplication
	 * (net.cedu.entity.meterial.MeterialApplication,
	 * net.cedu.model.page.PageResult)
	 */
	public List<MeterialApplication> findMeterialApplicationPageListByCodeApplication(MeterialApplication meterialapplication, PageResult<MeterialApplication> pr)
			throws Exception {
		List<MeterialApplication> meteriallist = new ArrayList<MeterialApplication>();
		PageParame p = new PageParame(pr);
		
		List<Object> paramlist=new ArrayList<Object>();
		String hql="";
		if (meterialapplication != null) {
			//编号
			if(StringUtils.isNotBlank(meterialapplication.getCode()))
			{
				hql+="and code like "+Constants.PLACEHOLDER;
				paramlist.add("%"+meterialapplication.getCode()+"%");
			}
			//姓名
			if(StringUtils.isNotBlank(meterialapplication.getApplicantname()))
			{
				Long [] ids=userBiz.findUserByNames(meterialapplication.getApplicantname());
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
			if(-1<meterialapplication.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());		 
			}
			p.setHqlConditionExpression(hql);
			p.setValues(paramlist.toArray());
		}
		Long[] userIds = meterialdao.getIDs(p);
		if (userIds != null && userIds.length != 0) {
			for (int i = 0; i < userIds.length; i++) {
				MeterialApplication m = meterialdao.findById(Integer.parseInt(userIds[i]
						.toString()));
				if(m!=null)
				{
					MeterialApplication application=m;
					User user=userBiz.findUserById(application.getCreatorId());
					if(user!=null)
					{
					application.setApplicantname(user.getFullName());
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
	 * 按编号和名称查询中心申请单 (non-Javadoc)
	 * (non-Javadoc)
	 * 
	 * @seenet.cedu.biz.meterial.biz.MeterialApplicationBiz#
	 * findUserPageCountByBranchIdAdmin(net.cedu.entity.admin.User,
	 * net.cedu.model.page.PageResult)
	 */
	public int findMeterialApplicationPageCountByCodeApplication(MeterialApplication meterialapplication, PageResult<MeterialApplication> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> paramlist = new ArrayList<Object>();
		String hql = "";
		if (meterialapplication != null) {
			//编号
			if (StringUtils.isNotBlank(meterialapplication.getCode())) {
				hql += "and code like " + Constants.PLACEHOLDER;
				
				paramlist.add("%" + meterialapplication.getCode() + "%");
			}
			//姓名
			if(StringUtils.isNotBlank(meterialapplication.getApplicantname()))
			{
				Long [] ids=userBiz.findUserByNames(meterialapplication.getApplicantname());
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
			if(-1<meterialapplication.getDeleteFlag())
			{
				hql+=" and deleteFlag=" + Constants.PLACEHOLDER;
				paramlist.add(UserEnum.DeleteNo.value());			 
			}
			p.setHqlConditionExpression(hql);	
			p.setValues(paramlist.toArray());
		}
		int count=meterialdao.getCounts(p);
		
		return count;
	}
	/**
	 * 根据主键查询ID
	 */
	public MeterialApplication findbyId(int id) throws Exception {
		 
		return meterialdao.findById(id);
	}
	
	/**
	 *  根据编号查询对象
	 */
	public MeterialApplication findByMeterialId(String code) throws Exception {
			MeterialApplication meterialApplication = new MeterialApplication();
			String hqlparam = "";
			List<Object> paramList = new ArrayList<Object>();
			if (null != code) {	 
					hqlparam += " and  code= " + Constants.PLACEHOLDER;
					paramList.add(code);	 
			}
			hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
			paramList.add(UserEnum.DeleteNo.value());
			
			meterialApplication = meterialdao.getObjByProperty(hqlparam, paramList.toArray());
			return meterialApplication;
		}
	}
 