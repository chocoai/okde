package net.cedu.action.enrollment.academyrebatestd;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款标准列表查询 
 */
public class IndexAcademyRbtStdAction extends BaseAction
{
	private static final long serialVersionUID = -1937701043073227397L;
	
	private List<Academy> academies;
	private List<FeeSubject> feeSubjects;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;

	@Override
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
		feeSubjects=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		Collections.sort(feeSubjects, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((FeeSubject) arg0).getName();
				String name2 = ((FeeSubject) arg1).getName();
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
}
