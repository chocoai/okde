package net.cedu.action.basesetting.channelrebatetimelimit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.ChannelRebateTimeLimitBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.ChannelRebateTimeLimit;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础设置_招生返款时间限定（返款期、到账确认时间）
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonListChannelRebateTimeLimitAction extends BaseAction
{
	
	// 分页结果
	private PageResult<ChannelRebateTimeLimit> result = new PageResult<ChannelRebateTimeLimit>();
	
	@Autowired
	private ChannelRebateTimeLimitBiz channelRebateTimeLimitBiz;//
	private ChannelRebateTimeLimit channelRebateTimeLimit=new ChannelRebateTimeLimit();
	private boolean isback=false;
	
	private int id;//招生返款时间限定id
	
	//增加修改和添加时的判断
	private int ccount=0;
	
	
	/**
	 * 获取适合招生返款的缴费单明细数量_重构_2012-05-21
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_channel_rebate_time_limit_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewCount() throws Exception 
	{			
		result.setRecordCount(this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitCountByPage(channelRebateTimeLimit));
		return SUCCESS;
	}

	/**
	 * 获取适合招生返款的缴费单明细列表_重构_2012-05-21
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channel_rebate_time_limit_page_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelRebateReviewList() throws Exception 
	{			
		result.setList(this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitListByPage(channelRebateTimeLimit, result));
		return SUCCESS;
	}
	
	
	/**
	 * 更新招生返款时间限定（返款期、到账确认时间）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_channel_rebate_limit_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatecrl() throws Exception 
	{	
		if(channelRebateTimeLimit!=null && channelRebateTimeLimit.getId()>0)
		{
			ccount=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitCountForAddUpdate(channelRebateTimeLimit);
			if(ccount==0)
			{
				ChannelRebateTimeLimit oldcrtl=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(channelRebateTimeLimit.getId());
				if(oldcrtl!=null)
				{
					String endtime="";
					if(channelRebateTimeLimit.getEndTime()!=null)
					{
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						endtime=df.format(channelRebateTimeLimit.getEndTime()).substring(0,10)+" 23:59:59";
						channelRebateTimeLimit.setEndTime(df.parse(endtime));
					}
					if(channelRebateTimeLimit.getCeduConfirmTime()!=null)
					{
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						endtime=df.format(channelRebateTimeLimit.getCeduConfirmTime()).substring(0,10)+" 23:59:59";
						channelRebateTimeLimit.setCeduConfirmTime(df.parse(endtime));
					}
					channelRebateTimeLimit.setCreatedTime(oldcrtl.getCreatedTime());
					channelRebateTimeLimit.setCreatorId(oldcrtl.getCreatorId());
					channelRebateTimeLimit.setIsUsing(oldcrtl.getIsUsing());
					channelRebateTimeLimit.setUpdatedTime(new Date());
					channelRebateTimeLimit.setUpdaterId(super.getUser().getUserId());
					isback=this.channelRebateTimeLimitBiz.updateChannelRebateTimeLimit(channelRebateTimeLimit);
				}
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 查询招生返款期（返款期、到账确认时间）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_channel_rebate_limit_by_id_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findcrl() throws Exception 
	{	
		if(id>0)
		{
			channelRebateTimeLimit=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(id);
		}
		return SUCCESS;
	}
	
	/**
	 * 添加招生返款时间限定（返款期、到账确认时间）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_channel_rebate_limit_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String addcrl() throws Exception 
	{	
		if(channelRebateTimeLimit!=null)
		{
			ccount=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitCountForAddUpdate(channelRebateTimeLimit);
			if(ccount==0)
			{
				String endtime="";
				if(channelRebateTimeLimit.getEndTime()!=null)
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					endtime=df.format(channelRebateTimeLimit.getEndTime()).substring(0,10)+" 23:59:59";
					channelRebateTimeLimit.setEndTime(df.parse(endtime));
				}
				if(channelRebateTimeLimit.getCeduConfirmTime()!=null)
				{
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					endtime=df.format(channelRebateTimeLimit.getCeduConfirmTime()).substring(0,10)+" 23:59:59";
					channelRebateTimeLimit.setCeduConfirmTime(df.parse(endtime));
				}
				channelRebateTimeLimit.setCreatedTime(new Date());
				channelRebateTimeLimit.setCreatorId(super.getUser().getUserId());
				channelRebateTimeLimit.setUpdatedTime(new Date());
				channelRebateTimeLimit.setUpdaterId(super.getUser().getUserId());
				isback=this.channelRebateTimeLimitBiz.addChannelRebateTimeLimit(channelRebateTimeLimit);
		
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 启用招生返款期（返款期、到账确认时间）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_channel_rebate_limit_using_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String isusingcrl() throws Exception 
	{	
		if(id>0)
		{
			ChannelRebateTimeLimit crtl=this.channelRebateTimeLimitBiz.findChannelRebateTimeLimitById(id);
			if(crtl!=null)
			{
				crtl.setIsUsing(Constants.STATUS_ENABLED);
				crtl.setUpdatedTime(new Date());
				crtl.setUpdaterId(super.getUser().getUserId());
				
				//查询已经启用的招生返款期
				List<ChannelRebateTimeLimit> crtlList=this.channelRebateTimeLimitBiz.findAllChannelRebateTimeLimitByIsUsing();
				if(crtlList!=null && crtlList.size()>0)
				{
					for(ChannelRebateTimeLimit cr:crtlList)
					{
						cr.setIsUsing(Constants.STATUS_DISABLE);
						cr.setUpdatedTime(new Date());
						cr.setUpdaterId(super.getUser().getUserId());
					}
				}
				crtlList.add(crtl);
				
				isback=this.channelRebateTimeLimitBiz.updateBatchChannelRebateTimeLimit(crtlList);
			}
			
		}
		return SUCCESS;
	}

	public ChannelRebateTimeLimit getChannelRebateTimeLimit() {
		return channelRebateTimeLimit;
	}

	public void setChannelRebateTimeLimit(
			ChannelRebateTimeLimit channelRebateTimeLimit) {
		this.channelRebateTimeLimit = channelRebateTimeLimit;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public PageResult<ChannelRebateTimeLimit> getResult() {
		return result;
	}

	public void setResult(PageResult<ChannelRebateTimeLimit> result) {
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCcount() {
		return ccount;
	}

	public void setCcount(int ccount) {
		this.ccount = ccount;
	}
	

}
