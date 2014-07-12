package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.ChannelBatchRecruitRebateStandardDao;
import net.cedu.entity.finance.ChannelBatchRecruitRebateStandard;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 合作方返款时绑定每个批次第一次招生返款的政策标准
 * 
 * @author lixiaojun
 * 
 */
@Service
public class ChannelBatchRecruitRebateStandardBizImpl implements ChannelBatchRecruitRebateStandardBiz
{

	@Autowired
	private ChannelBatchRecruitRebateStandardDao channelBatchRecruitRebateStandardDao;
	
	
	/*
	 * 添加合作方绑定招生返款标准关系
	 * 
	 * @see net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz#addChannelBatchRecruitRebateStandard(net.cedu.entity.finance.ChannelBatchRecruitRebateStandard)
	 */
	public boolean addChannelBatchRecruitRebateStandard(ChannelBatchRecruitRebateStandard channelBatchRecruitRebateStandard) throws Exception 
	{
		if (channelBatchRecruitRebateStandard != null)
		{
			Object object = channelBatchRecruitRebateStandardDao.save(channelBatchRecruitRebateStandard);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除合作方绑定招生返款标准关系（物理删除）
	 * 
	 * @see net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz#deleteChannelBatchRecruitRebateStandardById(int)
	 */
	public boolean deleteChannelBatchRecruitRebateStandardById(int id) throws Exception 
	{
		if (id != 0) 
		{
			Object object = channelBatchRecruitRebateStandardDao.deleteConfig(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * 修改合作方绑定招生返款标准关系
	 * 
	 * @see net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz#updateChannelBatchRecruitRebateStandard(net.cedu.entity.finance.ChannelBatchRecruitRebateStandard)
	 */
	public boolean updateChannelBatchRecruitRebateStandard(ChannelBatchRecruitRebateStandard channelBatchRecruitRebateStandard) throws Exception
	{
		if (channelBatchRecruitRebateStandard != null) 
		{
			Object object = channelBatchRecruitRebateStandardDao.update(channelBatchRecruitRebateStandard);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 根据Id查找合作方绑定招生返款标准关系
	 * 
	 * @see net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz#findChannelBatchRecruitRebateStandardById(int)
	 */
	public ChannelBatchRecruitRebateStandard findChannelBatchRecruitRebateStandardById(int id) throws Exception
	{
		return this.channelBatchRecruitRebateStandardDao.findById(id);
	}

	/*
	 * 根据条件查找合作方绑定招生返款标准关系数量
	 * 
	 * @see net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz#findChannelBatchRecruitRebateStandardCountBy(net.cedu.entity.finance.ChannelBatchRecruitRebateStandard)
	 */
	public int findChannelBatchRecruitRebateStandardCountBy(ChannelBatchRecruitRebateStandard channelBatchRecruitRebateStandard) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (channelBatchRecruitRebateStandard!= null)
		{			
			//合作方Id
			if(channelBatchRecruitRebateStandard.getChannelId()!=0)
			{
				hqlparam += " and channelId ="+ Constants.PLACEHOLDER;
				list.add(channelBatchRecruitRebateStandard.getChannelId());
			}
			//院校Id
			if(channelBatchRecruitRebateStandard.getAcademyId()!=0)
			{
				hqlparam += " and academyId ="+ Constants.PLACEHOLDER;
				list.add(channelBatchRecruitRebateStandard.getAcademyId());
			}
			//招生批次Id
			if(channelBatchRecruitRebateStandard.getBatchId()!=0)
			{
				hqlparam += " and batchId ="+ Constants.PLACEHOLDER ;
				list.add(channelBatchRecruitRebateStandard.getBatchId());
			}	
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		return channelBatchRecruitRebateStandardDao.getCounts(p);
	}

	/*
	 * 根据条件查找合作方绑定招生返款标准关系实体
	 * 
	 * @see net.cedu.biz.finance.ChannelBatchRecruitRebateStandardBiz#findChannelBatchRecruitRebateStandardListBy(net.cedu.entity.finance.ChannelBatchRecruitRebateStandard)
	 */
	public ChannelBatchRecruitRebateStandard findChannelBatchRecruitRebateStandardListBy(ChannelBatchRecruitRebateStandard channelBatchRecruitRebateStandard) throws Exception
	{
		ChannelBatchRecruitRebateStandard cbrs=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (channelBatchRecruitRebateStandard!= null)
		{			
			//合作方Id
			if(channelBatchRecruitRebateStandard.getChannelId()!=0)
			{
				hqlparam += " and channelId ="+ Constants.PLACEHOLDER;
				list.add(channelBatchRecruitRebateStandard.getChannelId());
			}
			//院校Id
			if(channelBatchRecruitRebateStandard.getAcademyId()!=0)
			{
				hqlparam += " and academyId ="+ Constants.PLACEHOLDER;
				list.add(channelBatchRecruitRebateStandard.getAcademyId());
			}
			//招生批次Id
			if(channelBatchRecruitRebateStandard.getBatchId()!=0)
			{
				hqlparam += " and batchId ="+ Constants.PLACEHOLDER ;
				list.add(channelBatchRecruitRebateStandard.getBatchId());
			}	
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] cbrsIds = channelBatchRecruitRebateStandardDao.getIDs(p);
		if (cbrsIds != null && cbrsIds.length != 0) 
		{
			for (int i = 0; i < cbrsIds.length; i++) 
			{
				// 内存获取
				cbrs = channelBatchRecruitRebateStandardDao.findById(Integer.valueOf(cbrsIds[i].toString()));
				if(cbrs!=null)
				{
					break;
				}
			}
		}
		return cbrs;
	}

	
}
