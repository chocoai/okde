package net.cedu.action.finance.branchchannelpayment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.entity.enrollment.Channel;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 合作方查询（根据其类型）
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonListChannelByTypeAction extends BaseAction
{
	private static final long serialVersionUID = 3086445297418146405L;

	private int channelType;
	
	private List<Channel> list;
	
	@Autowired
	private ChannelBiz channelBiz;
	
	public String execute() throws Exception
	{
		list = channelBiz.findAllChannelByChannelTypeId(channelType);
		
		return SUCCESS;
	}

	public List<Channel> getList() {
		return list;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}
}
