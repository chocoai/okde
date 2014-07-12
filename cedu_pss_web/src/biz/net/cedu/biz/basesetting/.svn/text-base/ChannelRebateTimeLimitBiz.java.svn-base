package net.cedu.biz.basesetting;

import java.util.List;

import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.model.page.PageResult;


/**
 * 基础设置_招生返款时间限定（返款期、到账确认时间）
 * 
 * @author xiao
 *
 */
public interface ChannelRebateTimeLimitBiz 
{

	/**
	 * 添加招生返款时间限定
	 * @param channelRebateTimeLimit
	 * @return
	 * @throws Exception
	 */
	public boolean addChannelRebateTimeLimit(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception;
	
	
	/**
	 * 删除招生返款时间限定（物理删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteChannelRebateTimeLimitById(int id) throws Exception;
	
	
	/**
	 * 修改招生返款时间限定
	 * @param channelRebateTimeLimit
	 * @return
	 * @throws Exception
	 */
	public boolean updateChannelRebateTimeLimit(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception;
	
	/**
	 * 批量修改招生返款时间限定
	 * @param channelRebateTimeLimitList
	 * @return
	 * @throws Exception
	 */
	public boolean updateBatchChannelRebateTimeLimit(List<ChannelRebateTimeLimit> channelRebateTimeLimitList) throws Exception;
	
	/**
	 * 根据Id查找招生返款时间限定
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ChannelRebateTimeLimit findChannelRebateTimeLimitById(int id)throws Exception;
	
	/**
	 * 查询启用的招生返款时间限定
	 * @return
	 * @throws Exception
	 */
	public List<ChannelRebateTimeLimit> findAllChannelRebateTimeLimitByIsUsing() throws Exception;
	
	/**
	 * 查询所有招生返款时间限定
	 * @return
	 * @throws Exception
	 */
	public List<ChannelRebateTimeLimit> findAllChannelRebateTimeLimit() throws Exception;
	
	/**
	 * 查询招生返款时间限定数量
	 * 
	 * @param channelRebateTimeLimit
	 * @return
	 * @throws Exception
	 */
	public int findChannelRebateTimeLimitCountByPage(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception;
	
	/**
	 * 查询招生返款时间限定集合
	 * @param channelRebateTimeLimit
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<ChannelRebateTimeLimit> findChannelRebateTimeLimitListByPage(ChannelRebateTimeLimit channelRebateTimeLimit,PageResult<ChannelRebateTimeLimit> pr) throws Exception;
	
	/**
	 * 查询招生返款时间限定数量根据招生返款期时间(添加、修改判断时用到)
	 * @param channelRebateTimeLimit
	 * @return
	 * @throws Exception
	 */
	public int findChannelRebateTimeLimitCountForAddUpdate(ChannelRebateTimeLimit channelRebateTimeLimit) throws Exception;
	
	
}
