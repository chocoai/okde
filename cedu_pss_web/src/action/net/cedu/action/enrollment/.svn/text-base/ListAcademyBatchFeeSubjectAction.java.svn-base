package net.cedu.action.enrollment;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
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

/**
 * 院校招生费用科目列表查询(分批次，数据不分页)
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results(@Result(name="success", type="json"))
public class ListAcademyBatchFeeSubjectAction extends BaseAction
{
	/**
	 * @date 2011-08-08 09:35
	 */
	private static final long serialVersionUID = -8056732675033562204L;
	
	private int academyId;
	private int stage; //阶段
	
	List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyBatchFeeSubjectBiz academyBatchFeeSubjectBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
//	@Action("list_academy_batch_fee_subject")
	public String execute() throws Exception
	{
		List<AcademyEnrollBatch> aebList = academyEnrollBatchBiz.findByAcademyId(academyId);
		if(aebList != null){
			Iterator<AcademyEnrollBatch> aebIter = aebList.iterator();
			while(aebIter.hasNext()){
				Map<String, Object> entry = new HashMap<String, Object>();
				
				AcademyEnrollBatch aeb = aebIter.next();
				entry.put("batch", aeb);
				
				List<AcademyBatchFeeSubject> abfsList = academyBatchFeeSubjectBiz.findByAcademyAndBatchAndStage(academyId, aeb.getId(), stage);
				if(abfsList != null){
					List<FeeSubject> fslist = new LinkedList<FeeSubject>();
					
					Iterator<AcademyBatchFeeSubject> aefsIter = abfsList.iterator();
					while(aefsIter.hasNext()){
						AcademyBatchFeeSubject aefs = aefsIter.next();
						
						fslist.add(feeSubjectBiz.findFeeSubjectById(aefs.getFeeSubjectId()));
					}
					
					entry.put("fslist", fslist);
				}

				list.add(entry);
			}
		}

		return SUCCESS;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public List<Map<String, Object>> getList() {
		return list;
	}
}
