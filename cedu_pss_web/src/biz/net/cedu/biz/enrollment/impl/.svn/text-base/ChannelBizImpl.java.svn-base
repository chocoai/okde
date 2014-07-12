package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.dao.enrollment.ChannelDao;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.enrollment.Channel;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 渠道接口实现类
 * 
 * @author gaolei
 * 
 */
@Service
public class ChannelBizImpl implements ChannelBiz{

	@Autowired
	private ChannelDao channelDao;//渠道dao
	@Autowired 
	private BranchBiz branchBiz;//学习中心biz
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径biz
	@Autowired
	private ChannelPolicyChannelBiz  channelpolicychannelbiz;//招生途径biz
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz; //招生返款政策 业务接口
	 
	
	
	
	/**
	 * 添加渠道
	 * @param channel
	 * @throws Exception
	 */
	public void addChannel(Channel channel)throws Exception
	{
		this.channelDao.save(channel);
	}
	/**
	 * 修改渠道
	 * @param channel
	 */
	public boolean modifyChannel(Channel channel)throws Exception
	{	
	
			if (channel != null) {
				Object object = channelDao.update(channel);
				if (object != null) {
					return true;
				}
			}
		
		return false;
	}
	/**
	 * 删除渠道(假删)
	 * @param 
	 */
	public boolean deleteChannenlByFlag(int id)throws Exception
	{
	
			if (id != 0) {
				Object object = channelDao.deleteConfig(id);
				if (object != null) {
					return true;
				}
			}
		
		return false;
	}
	/**
	 * 删除渠道(物理删除)
	 * @param 
	 */
	public boolean deleteChannenl(int id)throws Exception
	{
		
			if (id != 0) {
				Object object = channelDao.deleteById(id);
				if (object != null) {
					return true;
				}
			}
		
		return false;
	}
	/**
	 * 根据id查找渠道
	 * @param id
	 * @return
	 */
	public Channel findChannel(int id)throws Exception
	{
		return this.channelDao.findById(id);
	}
	/**
	 * 获取渠道数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findChannelCountByDetails(int type,String channelname,List<Branch> branchlst,int isusing, PageResult<Channel> pr)throws Exception
	{
		PageParame p = new PageParame(pr);
		String params="";
		List<Object> list=new ArrayList<Object>();
		if(type!=0)
		{
			params=" and type="+ Constants.PLACEHOLDER;
			list.add(type);
		}
		if(channelname!=null&&!channelname.equals(""))
		{
			params+=" and name like "+ Constants.PLACEHOLDER;
			list.add("%"+channelname+"%");
		}
		if(branchlst!=null && branchlst.size()>0)
		{
			String branchIds="";
			StringBuffer branchIdsSB = new StringBuffer(",");
			for(int lst=0;lst<branchlst.size();lst++)
			{
//				branchIds+=branchlst.get(lst).getId()+",";
				if(branchIdsSB.toString().equals(",")){
					branchIdsSB = new StringBuffer(branchlst.get(lst).getId()+"");
				}else{
					branchIdsSB.append(","+branchlst.get(lst).getId());
				}
			}
			if(branchIdsSB.toString().equals(",")){
				branchIdsSB = new StringBuffer("0");
			}
			branchIds = branchIdsSB.toString();
			params+=" and branchId in ("+ Constants.PLACEHOLDER+")";
			list.add("$"+branchIds);
		}
		if(isusing>=0)
		{
			params+=" and aduitStatus="+ Constants.PLACEHOLDER;
			list.add(isusing);
		}
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(params);
		p.setValues(list.toArray());
		return channelDao.getCounts(p);
	}
	/**
	 * 获取渠道列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<Channel> findChannelByDetails(int type,String channelname,List<Branch> branchlst,int isusing, PageResult<Channel> pr)throws Exception
	{
		
			List<Channel> channels = null;
			// Ids集合
			PageParame p = new PageParame(pr);
			String params="";
			List<Object> list=new ArrayList<Object>();
			if(type!=0)
			{
				params=" and type="+ Constants.PLACEHOLDER;
				list.add(type);
			}
			if(channelname!=null&&!channelname.equals(""))
			{
				params+=" and name like "+ Constants.PLACEHOLDER;
				list.add("%"+channelname+"%");
			}
			if(branchlst!=null && branchlst.size()>0)
			{
				String branchIds="";
				StringBuffer branchIdsSB = new StringBuffer(",");
				for(int lst=0;lst<branchlst.size();lst++)
				{
//					branchIds+=branchlst.get(lst).getId()+",";
					if(branchIdsSB.toString().equals(",")){
						branchIdsSB = new StringBuffer(branchlst.get(lst).getId()+"");
					}else{
						branchIdsSB.append(","+branchlst.get(lst).getId());
					}
				}
				if(branchIdsSB.toString().equals(",")){
					branchIdsSB = new StringBuffer("0");
				}
				branchIds = branchIdsSB.toString();
				params+=" and branchId in ("+ Constants.PLACEHOLDER+")";
				list.add("$"+branchIds);
			}
			if(isusing>=0)
			{
				params+=" and aduitStatus="+ Constants.PLACEHOLDER;
				list.add(isusing);
			}
			params+=" and deleteFlag="+ Constants.PLACEHOLDER;
			list.add(Constants.DELETE_FALSE);
			p.setHqlConditionExpression(params);
			p.setValues(list.toArray());
			Long[] channelIds = channelDao.getIDs(p);
			if (channelIds != null && channelIds.length != 0) {
				channels = new ArrayList<Channel>();
				for (int i = 0; i < channelIds.length; i++) {
					// 内存获取
					Channel channelObj = channelDao.findById(Integer.valueOf(channelIds[i].toString()));
					if (channelObj != null) {
						Channel obj = channelObj;
						Branch branch=branchBiz.findBranchById(obj.getBranchId());
						EnrollmentSource enrollmentsource=enrollmentSourceBiz.findEnrollmentSourceById(obj.getType());
						int count=channelPolicyDetailBiz.findChannelPolicyDetailBychannelId(obj.getId());
						int number=channelPolicyDetailBiz.findChannelPolicyDetailBychannelIdAndauditStatus(obj.getId(),Constants.POLICY_AUDIT_STATUS_GOOD);
						if(branch!=null)
						{
							obj.setBranchName(branch.getName());
						}
						if(enrollmentsource!=null)
						{
						obj.setChannelTypeName(enrollmentsource.getName());	
						}
						obj.setStatistics(number+"/"+count); 							
						
						channels.add(obj);
					}
				}
			}
			return channels;
		

	}
	
	
	/**
	 * 添加重复校验
	 * @param channel
	 * @throws Exception
	 */
	public int countChannelByName(String name,String telephone)throws Exception {
		PageParame p = new PageParame();
		String params="";
		String canshu="";
		if(name!=null)
		{
			params=" and name="+ Constants.PLACEHOLDER;
			canshu=name+",";
		}
		if(telephone!=null)
		{
			params=" and telephone="+ Constants.PLACEHOLDER;
			canshu=telephone;
		}
		
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		return channelDao.getCounts(p);
	}
	
	/**
	 * 根据删除标记查询所有渠道
	 * 
	 * @param deleteFlag 删除标记
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAll(int deleteFlag) throws Exception
	{
		return channelDao.getByProperty("deleteFlag", deleteFlag);
	}
	
	/*
	 * 根据渠道类别查询所有渠道
	 * @see net.cedu.biz.enrollment.ChannelBiz#findAllChannelByChannelTypeId(int)
	 */
	public List<Channel> findAllChannelByChannelTypeId(int ChannelTypeId) throws Exception
	{
		String con=" and type="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+" and isUsing="+Constants.PLACEHOLDER;
		return this.channelDao.getByProperty(con, new Object[]{ChannelTypeId,Constants.DELETE_FALSE,Constants.STATUS_ENABLED});
	}
	/*
	 * 根据渠道类别查询所有渠道
	 * @see net.cedu.biz.enrollment.ChannelBiz#findAllChannelByChannelTypeIdAndBranchId(int, int)
	 */
	public List<Channel> findAllChannelByChannelTypeIdAndBranchId(
			int ChannelTypeId, int branchId) throws Exception {
		String con=" and type="+Constants.PLACEHOLDER+" and branchId ="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER+" and isUsing="+Constants.PLACEHOLDER;
		return this.channelDao.getByProperty(con, new Object[]{ChannelTypeId,branchId,Constants.DELETE_FALSE,Constants.STATUS_ENABLED});
		
	}
	
	/*
	 * 根据渠道类别 和学习中心Id查询该学习中心所有渠道（包括总部的渠道）
	 * @see net.cedu.biz.enrollment.ChannelBiz#findChannelListByChannelTypeIdAndBranchId(int, int)
	 */
	public List<Channel> findChannelListByChannelTypeIdAndBranchId(
			int ChannelTypeId, int branchId) throws Exception {
		String con=" and type="+Constants.PLACEHOLDER+" and (branchId ="+Constants.PLACEHOLDER+" or branchId ="+Constants.PLACEHOLDER+" ) and deleteFlag="+Constants.PLACEHOLDER+" and isUsing="+Constants.PLACEHOLDER;
		return this.channelDao.getByProperty(con, new Object[]{ChannelTypeId,branchId,BranchEnum.Admin.value(),Constants.DELETE_FALSE,Constants.STATUS_ENABLED});
		
	}

	/*
	 * 根据渠道类别和中心ids查询所有渠道
	 * @see net.cedu.biz.enrollment.ChannelBiz#findAllChannelByChannelTypeIdAndBranchIds(int, java.lang.String)
	 */
	public List<Channel> findAllChannelByChannelTypeIdAndBranchIds(
			int ChannelTypeId, String branchIds) throws Exception {
		String con=" and type="+Constants.PLACEHOLDER+" and branchId in ( "+Constants.PLACEHOLDER+" ) and deleteFlag="+Constants.PLACEHOLDER+" and isUsing="+Constants.PLACEHOLDER;
		return this.channelDao.getByProperty(con, new Object[]{ChannelTypeId,"$"+branchIds,Constants.DELETE_FALSE,Constants.STATUS_ENABLED});
		
	}
}
