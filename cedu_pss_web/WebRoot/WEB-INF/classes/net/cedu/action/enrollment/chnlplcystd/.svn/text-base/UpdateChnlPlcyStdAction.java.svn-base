package net.cedu.action.enrollment.chnlplcystd;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

@Results({
	@Result(name="success", type="redirect", location="/enrollment/chnlplcystd/view_chnl_plcy_std?id=${model.id}")
})
public class UpdateChnlPlcyStdAction extends BaseAction implements ModelDriven<ChannelPolicy>
{
	private static final long serialVersionUID = -7911358019756492331L;

	private ChannelPolicy model = new ChannelPolicy(new LinkedList<ChannelPolicyDetailStandard>());
	
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;
	
	@Override
	public String execute() throws Exception
	{
		Date now = new Date();
		model.setUpdatedTime(now);
		model.setUpdaterId(getUser().getUserId());
		
		try {
			List<ChannelPolicyDetailStandard> oldList = channelPolicyDetailStandardBiz.findByPolicyId(model.getId());
			
			channelPolicyBiz.updateWithStandards(model, oldList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public ChannelPolicy getModel() { return model; }
	
	public int getId() { return model==null ? 0 : model.getId(); }
}
