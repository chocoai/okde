package net.cedu.action.enrollment.chnlplcy;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.Channel;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加 招生返款政策明细
 */
public class AddChnlPlcyDtlAction extends BaseAction
{
	private static final long serialVersionUID = 4391775199549512499L;
	
	private int channelId;
	
	private List<Academy> academies;
	private List<FeeSubject> feeSubjects;
	
	private Channel channel;
	
	EnrollmentSource channelType;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private ChannelBiz channelBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	
	public String execute() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		if(academies!=null && academies.size()>0)
		{
			Collections.sort(academies, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Academy) arg0).getName();
					String name2 = ((Academy) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		feeSubjects = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		channel = channelBiz.findChannel(channelId);
		int channelTypeId = channel.getType();
		channelType = enrollmentSourceBiz.findEnrollmentSourceById(channelTypeId);
		
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<FeeSubject> getFeeSubjects() {
		return feeSubjects;
	}

	public EnrollmentSource getChannelType() {
		return channelType;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public Channel getChannel() {
		return channel;
	}
}
