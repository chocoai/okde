package net.cedu.action.enrollment.chnlplcystd;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;

public class IndexChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = 1L;

	private List<Academy> academies;
	private List<FeeSubject> feeSubjects;
	private List<EnrollmentSource> channelTypes;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	
	@Autowired
	private BranchBiz branchBiz;// 学习中心_业务层接口
	private List<Branch> branchList = new ArrayList<Branch>();// 学习中心集合(不包括总部)
	
	public String execute() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		Collections.sort(academies, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Academy) arg0).getName();
				String name2 = ((Academy) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		feeSubjects = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		Collections.sort(feeSubjects, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((FeeSubject) arg0).getName();
				String name2 = ((FeeSubject) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		channelTypes = enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag();
		Collections.sort(channelTypes, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((EnrollmentSource) arg0).getName();
				String name2 = ((EnrollmentSource) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		
		branchList = this.branchBiz.findBranchForModel();
		Collections.sort(branchList, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Branch) arg0).getName();
				String name2 = ((Branch) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
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

	public List<Branch> getBranchList() {
		return branchList;
	}

	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}
	
}
