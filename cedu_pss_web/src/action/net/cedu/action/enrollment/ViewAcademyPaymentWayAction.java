package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校缴费方式设置
 * @author sunflower
 *
 */
public class ViewAcademyPaymentWayAction extends BaseAction
{
	private static final long serialVersionUID = 3828608969818891709L;
	
	private int academyId;
	
	private Academy academy;
	
	private List<AcademyEnrollBatch> batches;
	
	@Autowired
	protected AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;

	public String execute() throws Exception
	{
		prepare();
		
		batches = academyEnrollBatchBiz.findByAcademyIdAndFlag(academyId);
		
		return SUCCESS;
	}
	
	private void prepare() throws Exception
	{
		academy = academyBiz.findAcademyById(academyId);
		request.setAttribute("academy", academy);
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public List<AcademyEnrollBatch> getBatches() {
		return batches;
	}

	public int getAcademyId() {
		return academyId;
	}

	public Academy getAcademy() {
		return academy;
	}
}
