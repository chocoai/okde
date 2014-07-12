package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校授权学习中心
 * @author Sauntor
 *
 */
public class ViewAcademyBranchAction extends BaseAction
{
	private static final long serialVersionUID = -1453355513230279934L;

	private int academyId;
	
	private List<AcademyEnrollBatch> batches;
	
	private Academy academy = null;
	
	@Autowired
	protected AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
	public String execute() throws Exception
	{
		academy = academyBiz.findAcademyById(academyId);
		request.setAttribute("academy", academy);
		
		batches = academyEnrollBatchBiz.findByAcademyId(academyId);
		
		return SUCCESS;
	}

	public List<AcademyEnrollBatch> getBatches() {
		return batches;
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
