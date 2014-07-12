package net.cedu.action.enrollment.academyrebatestd;

import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;

public class EditAcademyRbtStdAction extends BaseAction
{
	private static final long serialVersionUID = -759685563216388523L;
	
	private List<Academy> academies = new LinkedList<Academy>();
	private List<FeeSubject> feeSubjects = new LinkedList<FeeSubject>();
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;

	@Override
	public String execute() throws Exception
	{
		feeSubjects = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		academies = academyBiz.findAllAcademyByFlagFalse();
		
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<FeeSubject> getFeeSubjects() {
		return feeSubjects;
	}
}
