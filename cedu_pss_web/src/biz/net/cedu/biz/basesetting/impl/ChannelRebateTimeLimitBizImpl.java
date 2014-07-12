package net.cedu.biz.basesetting.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.ChannelRebateTimeLimitDao;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 基础设置_招生返款时间限定（返款期、到账确认时间）
 * 
 * @author xiao
 *
 */
@Service
public class ChannelRebateTimeLimitBizImpl implements ChannelRebateTimeLimitBiz
{
	@Autowired
	private ChannelRebateTimeLimitDao channelRebateTimeLimitDao;//
	
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;//招生返款单_业务层接口
	

	/*
	 * 添加招生返款时间限定
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#addChannelRebateTimeLimit(net.cedu.entity.basesetting.ChannelRebateTimeLimit)
	 */
	public boolean addChannelRebateTimeLimit(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception
	{
		if (channelRebateTimeLimit != null)
		{
			Object object = channelRebateTimeLimitDao.save(channelRebateTimeLimit);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除招生返款时间限定（物理删除）
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#deleteChannelRebateTimeLimitById(int)
	 */
	public boolean deleteChannelRebateTimeLimitById(int id) throws Exception 
	{
		if (id != 0) 
		{
			Object object = channelRebateTimeLimitDao.deleteById(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * 修改招生返款时间限定
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#updateChannelRebateTimeLimit(net.cedu.entity.basesetting.ChannelRebateTimeLimit)
	 */
	public boolean updateChannelRebateTimeLimit(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception
	{
		if (channelRebateTimeLimit != null) 
		{
			Object object = channelRebateTimeLimitDao.update(channelRebateTimeLimit);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 批量修改招生返款时间限定
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#updateBatchChannelRebateTimeLimit(java.util.List)
	 */
	public boolean updateBatchChannelRebateTimeLimit(List<ChannelRebateTimeLimit> channelRebateTimeLimitList) throws Exception
	{
		boolean isback=false;
		if (channelRebateTimeLimitList != null && channelRebateTimeLimitList.size()>0) 
		{
			for(ChannelRebateTimeLimit crtl:channelRebateTimeLimitList)
			{
				isback = updateChannelRebateTimeLimit(crtl);
			}
		}
		return isback;
	}

	/*
	 * 根据Id查找招生返款时间限定
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#findChannelRebateTimeLimitById(int)
	 */
	public ChannelRebateTimeLimit findChannelRebateTimeLimitById(int id) throws Exception 
	{
		return this.channelRebateTimeLimitDao.findById(id);
	}
	
	/*
	 * 查询启用的招生返款时间限定
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#findAllChannelRebateTimeLimitByIsUsing()
	 */
	public List<ChannelRebateTimeLimit> findAllChannelRebateTimeLimitByIsUsing() throws Exception
	{
		
		return channelRebateTimeLimitDao.getByProperty(" and isUsing="+Constants.PLACEHOLDER+" order by startTime desc ",new Object[]{Constants.STATUS_ENABLED});
	}
	
	/*
	 * 查询所有招生返款时间限定
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#findAllChannelRebateTimeLimit()
	 */
	public List<ChannelRebateTimeLimit> findAllChannelRebateTimeLimit() throws Exception
	{		
		return channelRebateTimeLimitDao.getByProperty(" order by startTime desc ");
	}
	
	/*
	 * 查询招生返款时间限定数量
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#findChannelRebateTimeLimitCountByPage(net.cedu.entity.basesetting.ChannelRebateTimeLimit)
	 */
	public int findChannelRebateTimeLimitCountByPage(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (channelRebateTimeLimit != null)
		{				
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return channelRebateTimeLimitDao.getCounts(p);
		
	}
	
	/*
	 * 查询招生返款时间限定集合
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#findChannelRebateTimeLimitListByPage(net.cedu.entity.basesetting.ChannelRebateTimeLimit, net.cedu.model.page.PageResult)
	 */
	public List<ChannelRebateTimeLimit> findChannelRebateTimeLimitListByPage(ChannelRebateTimeLimit channelRebateTimeLimit,PageResult<ChannelRebateTimeLimit> pr) throws Exception
	{
		List<ChannelRebateTimeLimit> crtlList=null;
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (channelRebateTimeLimit != null)
		{				
			
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		
		Long[] crtlIds = channelRebateTimeLimitDao.getIDs(p);
		if (crtlIds != null && crtlIds.length != 0) 
		{
			crtlList=new ArrayList<ChannelRebateTimeLimit>();
			for (int i = 0; i < crtlIds.length; i++) 
			{
				// 内存获取
				ChannelRebateTimeLimit crtl = channelRebateTimeLimitDao.findById(Integer.valueOf(crtlIds[i].toString()));
				if(crtl!=null)
				{
					crtl.setCount(orderCeduChannelBiz.findOrderCeduChannelCountByChannelRebateTimeIdStatus(crtl.getId(),Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO));
					crtlList.add(crtl);
				}
			}
		}
		return crtlList;
		
	}

	/*
	 * 查询招生返款时间限定数量根据招生返款期时间(添加、修改判断时用到)
	 * 
	 * @see net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz#findChannelRebateTimeLimitCountByPage(net.cedu.entity.basesetting.ChannelRebateTimeLimit)
	 */
	public int findChannelRebateTimeLimitCountForAddUpdate(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (channelRebateTimeLimit != null)
		{				
			//Id
			if(channelRebateTimeLimit.getId()>0)
			{
				hqlparam += " and id <>"
					+ Constants.PLACEHOLDER;
				list.add(channelRebateTimeLimit.getId());
			}
			//招生返款时间
			if(channelRebateTimeLimit.getStartTime()!=null && !channelRebateTimeLimit.getStartTime().equals("") && channelRebateTimeLimit.getEndTime()!=null && !channelRebateTimeLimit.getEndTime().equals(""))
			{
				hqlparam += " and (( startTime <="+ Constants.PLACEHOLDER+" and endTime >="+ Constants.PLACEHOLDER+" ) or ( startTime <="+ Constants.PLACEHOLDER+" and endTime >="+ Constants.PLACEHOLDER+" ) or ( startTime >="+ Constants.PLACEHOLDER+" and endTime <="+ Constants.PLACEHOLDER+" ))";
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");				
				list.add(df.format(channelRebateTimeLimit.getStartTime()));
				list.add(df.format(channelRebateTimeLimit.getStartTime()));
				list.add(df.format(channelRebateTimeLimit.getEndTime()));
				list.add(df.format(channelRebateTimeLimit.getEndTime()));
				list.add(df.format(channelRebateTimeLimit.getStartTime()));
				list.add(df.format(channelRebateTimeLimit.getEndTime()));
			}
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}

		return channelRebateTimeLimitDao.getCounts(p);
		
	}

}
