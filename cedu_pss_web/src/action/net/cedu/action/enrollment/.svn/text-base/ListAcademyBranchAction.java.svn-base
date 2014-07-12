package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({@Result(name="success", type="json"), @Result(name="error", type="json")})
public class ListAcademyBranchAction extends BaseAction
{
	/**
	 * @date 2011-08-08 17:27
	 */
	private static final long serialVersionUID = 30670141261107206L;
	
	private int academyId;
	private int batchId;
	
	private boolean includeCandidate = false; //是否生成可选学习中心列表
	
	List<Branch> grantedList;
	List<Branch> candidateList;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatcBiz;
	@Autowired
	private AcademyBatchBranchBiz academyBatchBranchBiz;
	
//	@Action("list_academy_branch")
	public String execute() throws Exception
	{
		if(academyId==0)
		{
			AcademyEnrollBatch batch = academyEnrollBatcBiz.findAcademyEnrollBatchById(batchId);
			
			if(batch==null) return ERROR;
			academyId = batch.getAcademyId();
		}

		grantedList = academyBatchBranchBiz.findGrantedBranch(academyId, batchId);
		if(grantedList!=null && grantedList.size()>0)
		{
			Collections.sort(grantedList, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		if(includeCandidate)
		{
			candidateList = academyBatchBranchBiz.findUngrantedBranch(academyId, batchId);
			if(candidateList!=null && candidateList.size()>0)
			{
				Collections.sort(candidateList, new Comparator() {
					public int compare(Object arg0, Object arg1) {
						Comparator cmp = Collator
								.getInstance(java.util.Locale.CHINA);
						String name1 = ((Branch) arg0).getName();
						String name2 = ((Branch) arg1).getName();
						return cmp.compare(name1, name2);
					}
				});
			}
		}
	
		return SUCCESS;
	}
	
	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public List<Branch> getGrantedList() {
		return grantedList;
	}

	public List<Branch> getCandidateList() {
		return candidateList;
	}

	public void setIncludeCandidate(boolean includeCandidate) {
		this.includeCandidate = includeCandidate;
	}
}
