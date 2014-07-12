package net.cedu.action.enrollment.chnlplcystd;

import java.util.LinkedList;
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

/**
 * 修改招生返款政策标准
 * 
 * @author Sauntor
 *
 */
public class EditChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = -759685563216388523L;
	
	private List<Academy> academies = new LinkedList<Academy>();
	private List<FeeSubject> feeSubjects = new LinkedList<FeeSubject>();
	private List<EnrollmentSource> channelTypes;
	private List<Branch> branchs;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private BranchBiz branchBiz;

	@Override
	public String execute() throws Exception
	{
		feeSubjects = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		academies = academyBiz.findAllAcademyByFlagFalse();
		branchs=branchBiz.findBranchAllByDeleteFlag();
		channelTypes = enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag();
		
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
