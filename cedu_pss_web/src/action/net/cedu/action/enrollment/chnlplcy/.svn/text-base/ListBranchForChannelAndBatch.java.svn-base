package net.cedu.action.enrollment.chnlplcy;

import java.util.Iterator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.Channel;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="error", type="json")})
public class ListBranchForChannelAndBatch extends BaseAction
{
	/**
	 * @date 2011-08-08 17:27
	 */
	private static final long serialVersionUID = 30670141261107206L;
	
	private int channelId;
	private int batchId;
	
	List<Branch> list;

	@Autowired
	private AcademyBatchBranchBiz academyBatchBranchBiz;
	@Autowired
	private ChannelBiz channelBiz;
	
	public String execute() throws Exception
	{
		list = academyBatchBranchBiz.findBranchByBatchId(batchId);
		
		Channel channel = channelBiz.findChannel(channelId);
		
		if(list!=null && channel.getIsAll()==Constants.IS_ALL_TRUE){
			Iterator<Branch> iter = list.iterator();
			while(iter.hasNext()){
				Branch branch = iter.next();
				if(branch.getId() != channel.getBranchId()){
					iter.remove();
				}
			}
		}
		
		return SUCCESS;
	}

	public List<Branch> getList() {
		return list;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
