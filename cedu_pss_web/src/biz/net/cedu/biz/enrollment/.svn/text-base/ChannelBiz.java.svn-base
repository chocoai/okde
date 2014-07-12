package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.model.page.PageResult;

/**
 * 渠道接口
 * 
 * @author lixiaojun
 * 
 */
public interface ChannelBiz {
	
	
	
	
	/**
	 * 添加重复校验
	 * @param channel
	 * @throws Exception
	 */
	public int countChannelByName(String name,String telephone)throws Exception;
		
	/**
	 * 添加渠道
	 * @param channel
	 * @throws Exception
	 */
	public void addChannel(Channel channel)throws Exception;
	/**
	 * 修改渠道
	 * @param channel
	 */
	public boolean modifyChannel(Channel channel)throws Exception;
	/**
	 * 删除渠道(假删)
	 * @param 
	 */
	public boolean deleteChannenlByFlag(int id)throws Exception;
	/**
	 * 删除渠道(物理删除)
	 * @param 
	 */
	public boolean deleteChannenl(int id)throws Exception;
	/**
	 * 根据id查找渠道
	 * @param id
	 * @return
	 */
	public Channel findChannel(int id)throws Exception;
	
	/**
	 * 获取渠道数量
	 * @param type
	 * @param channelname
	 * @param branchlst
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public int findChannelCountByDetails(int type,String channelname,List<Branch> branchlst,int isusing,PageResult<Channel> pr)throws Exception;
	
	
	/**
	 * 获取渠道列表
	 * @param type
	 * @param channelname
	 * @param branchlst
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findChannelByDetails(int type,String channelname,List<Branch> branchlst,int isusing, PageResult<Channel> pr)throws Exception;
	
	/**
	 * 根据删除标记查询所有渠道
	 * 
	 * @param deleteFlag 删除标记
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAll(int deleteFlag) throws Exception;
	
	/**
	 * 根据渠道类别查询所有渠道
	 * 
	 * @param deleteFlag 删除标记
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAllChannelByChannelTypeId(int ChannelTypeId) throws Exception;
	
	/**
	 * 根据渠道类别查询所有渠道
	 * @param ChannelTypeId 市场途径Id
	 * @param branchId      学习中心Id
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAllChannelByChannelTypeIdAndBranchId(int ChannelTypeId,int branchId) throws Exception;
	
	
	/**
	 * 根据渠道类别 和学习中心Id查询该学习中心所有渠道（包括总部的渠道）
	 * @param ChannelTypeId 渠道类别Id
	 * @param branchId 学习中心Id
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findChannelListByChannelTypeIdAndBranchId(int ChannelTypeId, int branchId) throws Exception;
	
	/**
	 * 根据渠道类别和中心ids查询所有渠道
	 * @param ChannelTypeId
	 * @param branchIds
	 * @return
	 * @throws Exception
	 */
	public List<Channel> findAllChannelByChannelTypeIdAndBranchIds(int ChannelTypeId,String branchIds) throws Exception;
	
}
