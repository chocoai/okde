package net.cedu.action.enrollment;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询院校的招生批次
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json")})
public class ListEnrollBatchForAcademyAction extends BaseAction
{
	private static final long serialVersionUID = 6478550705453741567L;
	
	private int academyId; //院校ID
	
	private List<AcademyEnrollBatch> batches;
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
	@Override
	public String execute() throws Exception
	{
		batches = academyEnrollBatchBiz.findByAcademyId(academyId);
		return SUCCESS;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public List<AcademyEnrollBatch> getBatches() {
		return batches;
	}
}
