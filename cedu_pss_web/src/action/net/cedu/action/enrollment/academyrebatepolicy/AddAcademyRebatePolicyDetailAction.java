package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加返款政策明细页面
 * 
 * @author Sauntor
 *
 */
public class AddAcademyRebatePolicyDetailAction extends BaseAction
{
	/**
	 * @date 2011-08-09 13:58
	 */
	private static final long serialVersionUID = -4412029315889475739L;

	private List<Academy> academies;
	private List<FeeSubject> feeSubjects;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	public String execute() throws Exception
	{
		academies = academyBiz.findAllAcademyByFlagFalse();
		feeSubjects = feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public List<FeeSubject> getFeeSubjects() {
		return feeSubjects;
	}
}
