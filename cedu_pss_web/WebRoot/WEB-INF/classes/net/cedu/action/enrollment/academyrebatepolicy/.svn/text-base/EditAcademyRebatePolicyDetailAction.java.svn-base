package net.cedu.action.enrollment.academyrebatepolicy;

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
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校返款政策明细 修改
 */
public class EditAcademyRebatePolicyDetailAction extends BaseAction
{
	private static final long serialVersionUID = 5380678427979116184L;
	
	private int detailId;
	
	private AcademyRebatePolicyDetail detail;
	
	private Academy academy;
	private AcademyEnrollBatch batch; //招生批次
	private Branch branch; //学习中心
	private Level level; //层次
	private Major major; //专业
	private FeeSubject feeSubject; //费用科目
	private List<AcademyRebatePolicy> policyList; //标准

	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private BranchBiz branchBiz;
	
	@Override
	public String execute() throws Exception
	{
		detail = academyRebatePolicyDetailBiz.findPolicyDetailById(detailId);
		academy = academyBiz.findAcademyById(detail.getAcademyId());
		batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
		level = levelBiz.findLevelById(detail.getLevelId());
		major = majorBiz.findMajorById(detail.getMajorId());
		feeSubject = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
		branch = branchBiz.findBranchById(detail.getBranchId());
		policyList = academyRebatePolicyBiz.findAvailableForAcademy(detail.getAcademyId(), detail.getFeeSubjectId());
		
		return SUCCESS;
	}

	public AcademyRebatePolicyDetail getDetail() {
		return detail;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getDetailId() {
		return detailId;
	}

	public Academy getAcademy() {
		return academy;
	}

	public AcademyEnrollBatch getBatch() {
		return batch;
	}

	public Branch getBranch() {
		return branch;
	}

	public Level getLevel() {
		return level;
	}

	public Major getMajor() {
		return major;
	}

	public FeeSubject getFeeSubject() {
		return feeSubject;
	}

	public List<AcademyRebatePolicy> getPolicyList() {
		return policyList;
	}
}
