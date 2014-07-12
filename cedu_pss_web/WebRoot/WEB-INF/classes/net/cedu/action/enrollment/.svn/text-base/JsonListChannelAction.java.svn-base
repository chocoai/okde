package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 渠道列表ajax
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListChannelAction extends BaseAction {
	@Autowired
	private ChannelBiz channelBiz;                              //合作方Biz
	@Autowired
	private ChannelPolicyBiz channelpolicyBiz;                  //招生返款标准biz
	@Autowired
	private BranchBiz branchBiz;  //学习中心Biz  

	// 分页结果
	private PageResult<Channel> result = new PageResult<Channel>();
	private List<ChannelPolicy> channelpolicylst=new ArrayList<ChannelPolicy>();
	private List<ChannelPolicyDetail> channelpolicydetaillst=new ArrayList<ChannelPolicyDetail>();	
	private List<Branch> branchlst=new ArrayList<Branch>();	
	//查询条件
	private int type;//渠道类型
	private String channelname;//合作方名称
	private int branchid;//学习中心
	private int channelpolicyId;  //政策ID
	private int isusing=-1;//审批状态
	
	//删除条件
	private int id;//渠道ID

	//修改状态条件
	private int status;//状态
	
	private int isUsing=-1;  //启用状态
	
	private String ids;//渠道ID集合
	
	//返回值
	private boolean blean;//

	@Action(value = "countchannel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,type,channelname,branchlst,isusing"
	}) })
	public String execute() throws Exception {
		
		branchlst=branchBiz.findListById(super.getUser().getOrgId());
		if(branchid==0)
		{
			// 查询数量
			result.setRecordCount(channelBiz.findChannelCountByDetails(type,channelname,branchlst,isusing, result));	
		}else
		{
			branchlst=new ArrayList<Branch>();
			Branch branch=new Branch();
			branch.setId(branchid);
			branchlst.add(branch);
			// 查询数量
			result.setRecordCount(channelBiz.findChannelCountByDetails(type,channelname,branchlst,isusing, result));	
		}
		
		return SUCCESS;
	}

	@Action(value = "listchannel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"result.*.creatorId,"+
			"result.*.createdTime,"+
			"result.*.updaterId,"+
			"result.*.updatedTime,"+
			"result.*.enrollmentsource.type,"+
			"result.*.enrollmentsource.sourceRebatesFeesubject",
			"includeProperties",
			"result.*,type,channelname,branchlst,isusing"
			
			
	}) })
	public String userJsonList() throws Exception {
		// 查询集合
		try {
			branchlst=branchBiz.findListById(super.getUser().getOrgId());
			if(branchid==0)
			{
				result.setList(channelBiz.findChannelByDetails(type,channelname,branchlst,isusing, result));
			}
			else
			{
				branchlst=new ArrayList<Branch>();
				Branch branch=new Branch();
				branch.setId(branchid);
				branchlst.add(branch);
				// 查询数量
				result.setList(channelBiz.findChannelByDetails(type,channelname,branchlst,isusing, result));
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return SUCCESS;
	}
	
	/**
	 * 删除渠道
	 * @return
	 * @throws Exception
	 */
	@Action(value = "deletechannel", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channeldelete() throws Exception {
		this.channelBiz.deleteChannenlByFlag(id);
		return SUCCESS;
	}
	/**
	 * 修改渠道状态
	 * @return
	 * @throws Exception
	 */
	@Action(value = "channelconfirm", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelconfirm() throws Exception {
		Channel channel=this.channelBiz.findChannel(id);
		channel.setStatus(status);
		channel.setUpdaterId(super.getUser().getUserId());
		channel.setUpdatedTime(new Date());
		this.channelBiz.modifyChannel(channel);
		return SUCCESS;
	}
	
	/**
	 * 审核渠道
	 * @return
	 * @throws Exception
	 */
	@Action(value = "channelconfirmon", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelconfirmon() throws Exception {
		Channel channel=this.channelBiz.findChannel(id);
		channel.setAduitStatus(Constants.AUDIT_STATUS_TRUE);
		channel.setAuditer(super.getUser().getUserId());
		channel.setUpdaterId(super.getUser().getUserId());
		channel.setUpdatedTime(new Date());
		channel.setIsUsing(Constants.STATUS_AUTHOR_TRUE);
		blean=this.channelBiz.modifyChannel(channel);
		return SUCCESS;
	}
	
	
	/**
	 * 合作方启用或停用
	 * @return
	 * @throws Exception
	 */
	@Action(value = "channelusing", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ChannelUsing() throws Exception {
		Channel channel=this.channelBiz.findChannel(id);
		channel.setIsUsing(isUsing);
		channel.setUpdaterId(super.getUser().getUserId());
		channel.setUpdatedTime(new Date());
		blean=this.channelBiz.modifyChannel(channel);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	/**
	 * 查询政策类别按照招生阶段
	 * @return
	 * @throws Exception
	 */
	@Action(value = "findpolicychannelbystage", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String FindPolicyChannelbyStage() throws Exception {
		channelpolicylst= channelpolicyBiz.findByType(type);
		return SUCCESS;
	}
	
	
	/**
	 * 批量审核渠道
	 * @return
	 * @throws Exception
	 */
	@Action(value = "batchchannelconfirmon", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String batchchannelconfirmon() throws Exception {
		String[] feeid=ids.split(",");
		System.out.println("1");
		for(int i=0;i<feeid.length;i++)
		{
			System.out.println(feeid[i]);
			Channel channel=this.channelBiz.findChannel(Integer.valueOf(feeid[i]));
			channel.setAduitStatus(Constants.AUDIT_STATUS_TRUE);
			channel.setAuditer(super.getUser().getUserId());
			channel.setUpdaterId(super.getUser().getUserId());
			channel.setUpdatedTime(new Date());
			channel.setIsUsing(Constants.STATUS_AUTHOR_TRUE);
			blean=this.channelBiz.modifyChannel(channel);
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public List<ChannelPolicy> getChannelpolicylst() {
		return channelpolicylst;
	}

	public void setChannelpolicylst(List<ChannelPolicy> channelpolicylst) {
		this.channelpolicylst = channelpolicylst;
	}

	public PageResult<Channel> getResult() {
		return result;
	}

	public void setResult(PageResult<Channel> result) {
		this.result = result;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getChannelname() {
		return channelname;
	}

	public void setChannelname(String channelname) {
		this.channelname = channelname;
	}

	public int getBranchid() {
		return branchid;
	}

	public void setBranchid(int branchid) {
		this.branchid = branchid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public boolean isBlean() {
		return blean;
	}

	public void setBlean(boolean blean) {
		this.blean = blean;
	}

	public List<ChannelPolicyDetail> getChannelpolicydetaillst() {
		return channelpolicydetaillst;
	}

	public void setChannelpolicydetaillst(
			List<ChannelPolicyDetail> channelpolicydetaillst) {
		this.channelpolicydetaillst = channelpolicydetaillst;
	}

	public int getChannelpolicyId() {
		return channelpolicyId;
	}

	public void setChannelpolicyId(int channelpolicyId) {
		this.channelpolicyId = channelpolicyId;
	}

	public int getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}
	
	public int getIsusing() {
		return isusing;
	}

	public void setIsusing(int isusing) {
		this.isusing = isusing;
	}
	
	
	

}
