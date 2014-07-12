package net.cedu.action.finance.branchchannelpayment;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.entity.enrollment.Channel;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款（中心）
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonListBranchChannelPaymentAction extends BaseAction
{
	
	@Autowired
	private ChannelBiz channelBiz;//合作方_业务层接口
	private int channelType; //合作方类别
	private int branchId;//学习中心Id
	private List<Channel> list; //合作方集合
	
	/**
	 * 合作方级联（通过学习中心Id和合作方类别Id）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_branch_channel_list_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findbcla() throws Exception 
	{
		if(channelType!=0 && branchId!=0)
		{
			list=this.channelBiz.findChannelListByChannelTypeIdAndBranchId(channelType, branchId);
			if(list!=null && list.size()>0)
			{
				for (Channel channel : list)
				{
					channel.setName(PingYingHanZiUtil.getNameFirstZiMuToUpperCaseCase(channel.getName())+channel.getName());
				}
				Collections.sort(list, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Channel) arg0).getName();
						String name2 = ((Channel) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
		}
		return SUCCESS;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public List<Channel> getList() {
		return list;
	}

	public void setList(List<Channel> list) {
		this.list = list;
	}
	
}
