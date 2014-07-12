package net.cedu.action.enrollment;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.entity.enrollment.ChannelPolicyDetail;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策明细
 * 
 * @author gaolei
 *
 */
public class ViewPolicyChannelDetailAction extends BaseAction
{
	private static final long serialVersionUID = -4130535704428454124L;

	
	@Autowired
	private ChannelPolicyDetailBiz channelpolicydetailBiz;        //招生返款政策明细业务逻辑
	private ChannelPolicyDetail cpd;                              //招生返款政策明细
	private int id;                                               //招生返款政策明细Id
	
	public String execute() throws Exception
	{
		//cpd=channelpolicydetailBiz.findChannelPolicyDetailById(id);
		return SUCCESS;
	}

	public ChannelPolicyDetail getCpd() {
		return cpd;
	}

	public void setCpd(ChannelPolicyDetail cpd) {
		this.cpd = cpd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
}
