package net.cedu.biz.enrollment.impl;

import java.util.List;

import net.cedu.biz.enrollment.ChannelPolicyChannelBiz;
import net.cedu.dao.enrollment.ChannelPolicyChannelDao;
import net.cedu.entity.enrollment.ChannelPolicyChannel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 合作方与招生返款政策 业务逻辑层实现
 * 
 * @author gaolei
 *
 */
@Service
public class ChannelPolicyChannelBizImpl implements ChannelPolicyChannelBiz
{
	@Autowired 
	private ChannelPolicyChannelDao channelpolicychanneldao;   //合作方与招生返款政策 业务接口

	/*
	 * 查询合作方返款政策按照channelId
	 * @see net.cedu.biz.enrollment.ChannelPolicyChannelBiz#findChannelPolicyChannelBychannelId(int)
	 */
	public List<ChannelPolicyChannel> findChannelPolicyChannelBychannelId(int channelId)throws Exception {
	
		return channelpolicychanneldao.getByProperty("channelId",channelId);
	}
	
	/*
	 * 添加合作方招生返款政策
	 * @see net.cedu.biz.enrollment.ChannelPolicyChannelBiz#addChannelPolicyChannel(net.cedu.entity.enrollment.ChannelPolicyChannel)
	 */
	public boolean addChannelPolicyChannel(ChannelPolicyChannel channelpolicychannel)throws Exception
	{
			 channelpolicychanneldao.save(channelpolicychannel);
			 return true;
	}
	
	
}
