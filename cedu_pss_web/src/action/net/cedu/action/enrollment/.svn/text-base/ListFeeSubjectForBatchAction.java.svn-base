package net.cedu.action.enrollment;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyBatchFeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.AcademyBatchFeeSubject;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json"),@Result(name="error", type="json")})
public class ListFeeSubjectForBatchAction extends BaseAction 
{
	/**
	 * @date 2011-08-09 15:20
	 */
	private static final long serialVersionUID = -3163403407966407495L;
	private int academyId;
	private int batchId;
	private int stage; //招生／非招生
	
	private List<FeeSubject> list;
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyBatchFeeSubjectBiz academyBatchFeeSubjectBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
//	@Action("list_fee_subject_for_enroll_batch")
	@Override
	public String execute() throws Exception
	{
		AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId);
		
		if(batch==null) return ERROR;
		academyId = batch.getAcademyId();
		
		List<AcademyBatchFeeSubject> aefsList = academyBatchFeeSubjectBiz.findByAcademyAndBatchAndStage(academyId, batchId, stage);
		if(aefsList != null)
		{
			list = new LinkedList<FeeSubject>();
			
			Iterator<AcademyBatchFeeSubject> iter = aefsList.iterator();
			
			while(iter.hasNext())
			{
				AcademyBatchFeeSubject aefs = iter.next();
				FeeSubject fs = feeSubjectBiz.findFeeSubjectById(aefs.getFeeSubjectId());
				list.add(fs);
			}
		}
		
		
		return SUCCESS;
	}

	public List<FeeSubject> getList() {
		return list;
	}

//	public void setAcademyId(int academyId) {
//		this.academyId = academyId;
//	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
}
