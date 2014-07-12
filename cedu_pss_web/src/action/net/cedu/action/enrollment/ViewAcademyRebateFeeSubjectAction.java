package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;


public class ViewAcademyRebateFeeSubjectAction extends BaseAction
{

	/**
	 * @date 2011-08-05 15:09
	 */
	private static final long serialVersionUID = 6545703081483058343L;

	private int academyId;
	
	private Academy academy = null;
	
	private List<FeeSubject> allEnrollFS; //所有招生阶段费用科目
	private List<FeeSubject> allRegisterFS; //所有非招生阶段费用科目
	
	@Autowired
	protected AcademyBiz academyBiz;
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	public String execute() throws Exception
	{
		prepare();
		
		allEnrollFS = feeSubjectBiz.findAllFeeSubjectBybatchType(Constants.BATCH_TYPE_ENROLL);
		allRegisterFS = feeSubjectBiz.findAllFeeSubjectBybatchType(Constants.BATCH_TYPE_NON_ENROLL);
		
		return SUCCESS;
	}
	
	private void prepare() throws Exception
	{
		academy = academyBiz.findAcademyById(academyId);
		request.setAttribute("academy", academy);
	}

	public List<FeeSubject> getAllEnrollFS() {
		return allEnrollFS;
	}

	public List<FeeSubject> getAllRegisterFS() {
		return allRegisterFS;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public Academy getAcademy() {
		return academy;
	}
}
