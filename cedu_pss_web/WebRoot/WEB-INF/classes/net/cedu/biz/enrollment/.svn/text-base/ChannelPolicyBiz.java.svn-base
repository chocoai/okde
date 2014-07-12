package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.model.page.PageResult;

/**
 * 招生返款政策 业务接口
 * 
 * @author Sauntor
 *
 */
public interface ChannelPolicyBiz
{
	/**
	 * 添加 渠道政策
	 * 
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int addChannelPolicy(ChannelPolicy entity) throws Exception;
	
	/**
	 * 根据条件查询渠道政策 列表
	 * @param condition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findByExample(ChannelPolicy condition, PageResult<ChannelPolicy> pr) throws Exception;
	
	/**
	 * 根据条件查询招生返款政策 列表，并附带查询外键ID对应的‘名称’等字段
	 * @param condition
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findAndWrapp(ChannelPolicy condition, PageResult<ChannelPolicy> pr) throws Exception;
	
	/**
	 * 根据条件查询渠道政策 总数
	 * 
	 * @param condition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public int countByExample(ChannelPolicy condition) throws Exception;
	
	/**
	 * 根据类型(招生/非招生)查询
	 * 
	 * @param type 值为ChannelPolicy.TYPE_ENROLL/ChannelPolicy.TYPE_NON_ENROLL
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public List<ChannelPolicy> findByType(int type) throws Exception;
	
	/**
	 * 查询招生返款政策
	 * 
	 * @return
	 * @throws Exception
	 */
	public ChannelPolicy findChannelPolicyById(int id) throws Exception;
	
	/**
	 * 添加招生返款政策标准，并保存起对应标准准则
	 * 
	 * @param policy
	 * @return 成功添加时返回新添加的政策标准的ID
	 * @throws Exception
	 */
	public int addPolicyWithStandards(ChannelPolicy policy) throws Exception;
	
	/**
	 * 更新招生返款政策标准，并保存相应的政策标准
	 * @param policy 政策
	 * @throws Exception
	 */
	public void updateWithStandards(ChannelPolicy policy, List<ChannelPolicyDetailStandard> oldStdList) throws Exception;
	
	/**
	 * 查询合作方可用的返款标准
	 * 
	 * @param channelId 合作方ID
	 * @param academyId 院校ID
	 * @param feeSubject 费用项目
	 * @param branchId 学习中心Id
	 * @return
	 * @throws Exception
	 */
	public List<ChannelPolicy> findAvaliableToChannel(int channelId, int academyId, int feeSubject,int branchId) throws Exception;
	
	/**
	 * 包装关联表ID到对应的Name
	 * @param cp
	 * @throws Exception
	 */
	public void wrap(ChannelPolicy cp) throws Exception;
}
