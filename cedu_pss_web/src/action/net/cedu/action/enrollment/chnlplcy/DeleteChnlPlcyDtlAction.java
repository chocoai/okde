package net.cedu.action.enrollment.chnlplcy;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;


@ParentPackage("json-default")
@Results({@Result(name="success", type="json")})
public class DeleteChnlPlcyDtlAction extends BaseAction
{
	private static final long serialVersionUID = -4720935029488802914L;

	private int detailId;
	private int errno = 0;
	
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	
	public String execute() throws Exception
	{
		try {
			channelPolicyDetailBiz.deleteById(detailId);
		} catch (Exception e) {
			errno = 1;
		}
		
		return SUCCESS;
	}

	public int getErrno() {
		return errno;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
}
