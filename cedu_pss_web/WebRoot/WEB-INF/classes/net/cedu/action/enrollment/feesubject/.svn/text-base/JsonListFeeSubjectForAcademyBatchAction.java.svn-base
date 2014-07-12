package net.cedu.action.enrollment.feesubject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.AcademyBatchFeeSubjectBiz;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.AcademyBatchFeeSubject;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询院校指定批次下的费用项目
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class JsonListFeeSubjectForAcademyBatchAction extends BaseAction
{
	private static final long serialVersionUID = 1578627890819083083L;

	private int academyId;
	private int batchId;
	
	List<FeeSubject> list = new LinkedList<FeeSubject>();
	
	@Autowired
	private AcademyBatchFeeSubjectBiz academyBatchFeeSubjectBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	
	public String execute() throws Exception
	{
		List<AcademyBatchFeeSubject> list = academyBatchFeeSubjectBiz.findByAcademyAndBatch(academyId, batchId);
		if(list!=null){
			Iterator<AcademyBatchFeeSubject> iter = list.iterator();
			while(iter.hasNext()){
				AcademyBatchFeeSubject abfs = iter.next();
				FeeSubject fs = feeSubjectBiz.findFeeSubjectById(abfs.getFeeSubjectId());
				this.list.add(fs);
			}
			
		}
		return SUCCESS;
	}

	public List<FeeSubject> getList() {
		return list;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
}
