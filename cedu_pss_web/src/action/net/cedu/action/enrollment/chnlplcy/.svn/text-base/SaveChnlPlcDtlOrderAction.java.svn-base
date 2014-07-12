package net.cedu.action.enrollment.chnlplcy;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.entity.enrollment.ChannelPolicyDetail;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策优先顺序修改
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json")})
public class SaveChnlPlcDtlOrderAction extends BaseAction
{
	private static final long serialVersionUID = 8963093171530935970L;
	
	private int[] detailIds;
	private int[] orders;
	
	private int errno;
	
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;

	public String execute() throws Exception
	{
		if(detailIds==null || orders==null || detailIds.length != orders.length)
		{
			errno = 1;
		}
		
		Date updatedTime = new Date();
		
		List<ChannelPolicyDetail> list = new LinkedList<ChannelPolicyDetail>();
		for(int i=0; i<detailIds.length; i++)
		{
			ChannelPolicyDetail detail = channelPolicyDetailBiz.findById(detailIds[i]);
			detail.setOrder(orders[i]);
			detail.setUpdatedTime(updatedTime);
			detail.setUpdaterId(getUser().getUserId());
			
			list.add(detail);
		}
		
		channelPolicyDetailBiz.updateList(list);
		
//		channelPolicyDetailBiz.updateOrdersByIds(detailIds, orders, getUser().getUserId());
		
		return SUCCESS;
	}

	public void setDetailIds(int[] detailIds) {
		this.detailIds = detailIds;
	}

	public void setOrders(int[] orders) {
		this.orders = orders;
	}

	public int getErrno() {
		return errno;
	}
}
