package net.cedu.action.enrollment.chnlplcy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetail;
import net.cedu.entity.enrollment.ChannelPolicyDetailStandard;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

public class ViewChnlPlcyDtlAction extends BaseAction
{
	private static final long serialVersionUID = -3382396404701674763L;

	private int detailId;
	
	private ChannelPolicyDetail detail;
	private Channel channel;
	private ChannelPolicy policy;
	private AcademyEnrollBatch batch;
	private Level level;
	private Major major;
	private Academy academy;
	private FeeSubject feeSubject;
	private EnrollmentSource channelType;
	
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	@Autowired
	private ChannelPolicyDetailStandardBiz channelPolicyDetailStandardBiz;
	
	public String execute() throws Exception
	{
		detail = channelPolicyDetailBiz.findById(detailId);
		level = levelBiz.findLevelById(detail.getLevelId());
		
		major = majorBiz.findMajorById(detail.getMajorId()); 
		
		academy = academyBiz.findAcademyById(detail.getAcademyId());
		
		feeSubject = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
		
		batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
		
		
		policy = channelPolicyBiz.findChannelPolicyById(detail.getPolicyId());
		
		List<ChannelPolicyDetailStandard> standardList = channelPolicyDetailStandardBiz.findByPolicyId(policy.getId());
		
		policy.setStandards(standardList);
		
		channel = channelBiz.findChannel(detail.getChannelId());
		channelType = enrollmentSourceBiz.findEnrollmentSourceById(channel.getType());
		
		return SUCCESS;
	}
	
	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public ChannelPolicyDetail getDetail() {
		return detail;
	}

	public AcademyEnrollBatch getBatch() {
		return batch;
	}

	public Level getLevel() {
		return level;
	}

	public Major getMajor() {
		return major;
	}

	public Academy getAcademy() {
		return academy;
	}

	public ChannelPolicy getPolicy() {
		return policy;
	}

	public FeeSubject getFeeSubject() {
		return feeSubject;
	}

	public Channel getChannel() {
		return channel;
	}

	public EnrollmentSource getChannelType() {
		return channelType;
	}
}
