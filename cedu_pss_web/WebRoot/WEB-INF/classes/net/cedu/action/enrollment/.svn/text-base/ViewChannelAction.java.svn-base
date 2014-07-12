package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.enrollment.Channel;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 合作方详情
 * 
 * @author Sauntor
 *
 */



public class ViewChannelAction extends BaseAction
{
	private static final long serialVersionUID = -4130535704428454124L;


	
	@Autowired
	private ChannelBiz channelBiz;                            //合作方业务逻辑
	@Autowired
	private BranchBiz branchbiz;                              //学习中心业务逻辑
	@Autowired
	private EnrollmentSourceBiz enrollmentsourcebiz;          //基础设置（合作方类别）业务逻辑
	
	private Branch branch;                                    //学习中心
	private Channel channel;                                  //合作方
	private EnrollmentSource enrollmentsource;                //基础设置（合作方类别）
	private int id;                                           //主键Id
	
	
	public String execute() throws Exception
	{
		channel=channelBiz.findChannel(id);
		branch=branchbiz.findBranchById(channel.getBranchId());
		enrollmentsource=enrollmentsourcebiz.findEnrollmentSourceById(channel.getType());
		
		return SUCCESS;
	}
	public Branch getBranch() {
		return branch;
	}



	public void setBranch(Branch branch) {
		this.branch = branch;
	}



	public Channel getChannel() {
		return channel;
	}



	public void setChannel(Channel channel) {
		this.channel = channel;
	}



	public EnrollmentSource getEnrollmentsource() {
		return enrollmentsource;
	}



	public void setEnrollmentsource(EnrollmentSource enrollmentsource) {
		this.enrollmentsource = enrollmentsource;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	
}
