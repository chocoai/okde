package net.cedu.action.enrollment.chnlplcystd;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策 信息查看
 */
public class ViewChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = 4016983878254671023L;
	
	private int id;
	
	private ChannelPolicy policy;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourcebiz;
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;
	
	@Override
	public String  execute() throws Exception
	{
		policy = channelPolicyBiz.findChannelPolicyById(id);
		List<ChannelPolicyDetailStandard> stdList = channelPolicyDetailStandardBiz.findByPolicyId(id);
		policy.setStandards(stdList);
		
		EnrollmentSource channelType = enrollmentSourcebiz.findEnrollmentSourceById(policy.getChannelTypeId());
		if(channelType != null)
			policy.setChannelTypeName(channelType.getName());
		
		FeeSubject fs = feeSubjectBiz.findFeeSubjectById(policy.getFeeSubjectId());
		if(fs != null)
			policy.setFeeSubjectName(fs.getName());
		
		Academy academy = academyBiz.findAcademyById(policy.getAcademyId());
		if(academy != null)
			policy.setAcademyName(academy.getName());
		
		return SUCCESS;
	}

	public ChannelPolicy getPolicy() {
		return policy;
	}

	public void setId(int id) {
		this.id = id;
	}
}
