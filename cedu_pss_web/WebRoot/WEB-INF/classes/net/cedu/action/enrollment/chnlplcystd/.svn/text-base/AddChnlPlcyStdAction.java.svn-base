package net.cedu.action.enrollment.chnlplcystd;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;

public class AddChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = 929247034137719077L;
	
	private List<Academy> academies;
	private List<FeeSubject> feeSubjects;
//	private List<Channel> channels;
	private List<EnrollmentSource> channelTypes;
	private List<Branch> branchs;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
//	@Autowired
//	private ChannelBiz channelBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;

	@Override
	public String execute() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		feeSubjects = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		channelTypes = enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag();
		branchs=branchBiz.findBranchAllByDeleteFlag();
		
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<FeeSubject> getFeeSubjects() {
		return feeSubjects;
	}

	public List<EnrollmentSource> getChannelTypes() {
		return channelTypes;
	}

	public List<Branch> getBranchs() {
		return branchs;
	}

	public void setBranchs(List<Branch> branchs) {
		this.branchs = branchs;
	}

	public void setAcademies(List<Academy> academies) {
		this.academies = academies;
	}

	public void setFeeSubjects(List<FeeSubject> feeSubjects) {
		this.feeSubjects = feeSubjects;
	}

	public void setChannelTypes(List<EnrollmentSource> channelTypes) {
		this.channelTypes = channelTypes;
	}
	
}
