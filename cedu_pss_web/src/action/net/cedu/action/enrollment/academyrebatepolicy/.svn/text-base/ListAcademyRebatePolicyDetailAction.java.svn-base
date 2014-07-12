package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.Major;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 院校返款政策明细列表查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"),@Result(name="error", type="json")})
public class ListAcademyRebatePolicyDetailAction extends BaseAction implements ModelDriven<AcademyRebatePolicyDetail>
{
	/**
	 * @date 2011-08-05 14:16
	 */
	private static final long serialVersionUID = -1696834930973699618L;

//	private int policyId;
//	private int academyId;
	private int batchId;
	private int branchId;
	private int feeSubjectId;
	private int levelId;
	private int majorId;
//	private int auditStatus;
	
	
	private AcademyRebatePolicyDetail condition = new AcademyRebatePolicyDetail();
	
	private int pageSize;
	private int pageIndex;
	
	private int total;
	
	private List<AcademyRebatePolicyDetail> list;

	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	
	@Override
	public String execute() throws Exception
	{
		condition.setBatchId(batchId);
		condition.setDeleteFlag(Constants.DELETE_FALSE);
		condition.setLevelId(levelId);
		condition.setFeeSubjectId(feeSubjectId);
		condition.setMajorId(majorId);
		
		total = academyRebatePolicyDetailBiz.countByCondition(condition);
		if(total>0){
			PageResult<AcademyRebatePolicyDetail> prPageResult=new PageResult<AcademyRebatePolicyDetail>();
			prPageResult.setCurrentPageIndex(pageIndex);
			prPageResult.setPageSize(pageSize);
			list = academyRebatePolicyDetailBiz.findByCondition(condition, prPageResult);
//			list = academyRebatePolicyDetailBiz.findByCondition(condition, pageIndex, pageSize);
		}
		
		if(list!=null){
			AcademyRebatePolicyDetail detail = null;
			Iterator<AcademyRebatePolicyDetail> iter = list.iterator();
			while(iter.hasNext()){
				detail = iter.next();
				
				/* 院校 */
				Academy academy = academyBiz.findAcademyById(detail.getAcademyId());
				if(academy != null)
					detail.setAcademyName(academy.getName());
				
				/*  */
				AcademyEnrollBatch aeb = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
				if(aeb != null)
					detail.setBatchName(aeb.getEnrollmentName());
				
				detail.setPolicy(academyRebatePolicyBiz.getAcademyRebatePolicyById(detail.getPolicyId()));
				detail.setStandards(academyRebatePolicyDetailStandardBiz.findByPolicyId(detail.getPolicyId()));
				
				/* 学习中心 */
				Branch branch = branchBiz.findBranchById(detail.getBranchId());
				if(branch != null)
					detail.setBranchName(branch.getName());
				
				/* 费用科目 */
				FeeSubject feeSubject = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
				
				if(feeSubject != null)
					detail.setFeeSubjectName(feeSubject.getName());
				
				/* 专业 */
				Major major = majorBiz.findMajorById(detail.getMajorId());
				if(major != null)
					detail.setMajorName(major.getName());
				
				/* 层次 */
				Level level = levelBiz.findLevelById(detail.getLevelId());
				detail.setLevel(level);
				
				/* 批次 */
				AcademyEnrollBatch batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
				detail.setBatch(batch);
				detail.setBatchName(batch.getEnrollmentName());
			}
		}
		
		return SUCCESS;
	}

	public int getTotal() {
		return total;
	}

	public List<AcademyRebatePolicyDetail> getList() {
		return list;
	}

//	public void setPolicyId(int policyId) {
//		this.policyId = policyId;
//	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public AcademyRebatePolicyDetail getModel() {
		return condition;
	}

//	public void setAcademyId(int academyId) {
//		this.academyId = academyId;
//	}
//
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
//
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
//
//	public void setFeeSubjectId(int feeSubjectId) {
//		this.feeSubjectId = feeSubjectId;
//	}
//
//	public void setLevelId(int levelId) {
//		this.levelId = levelId;
//	}
//
	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}
}
