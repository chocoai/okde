package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetailStandard;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查看返款政策明细
 * 
 * @author Sauntor
 *
 */
public class ViewAcademyRebatePolicyDetailAction extends BaseAction
{
	private static final long serialVersionUID = 4954557126011285874L;
	private int detailId;
	
	private int stage;
	
	private Level level;
	private AcademyEnrollBatch batch;
	private List<Branch> branches;
	private List<FeeSubject> feeSubjects;
	private List<Major> majors;
	private List<AcademyRebatePolicyDetailStandard> standards;
	
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private AcademyRebatePolicyDetailStandardBiz academyRebatePolicyDetailStandardBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private MajorBiz majorBiz;
	
	public String execute() throws Exception
	{
		AcademyRebatePolicyDetail detail = academyRebatePolicyDetailBiz.findPolicyDetailById(detailId);
		
//		AcademyRebatePolicy policy = academyRebatePolicyBiz.getAcademyRebatePolicyById(detail.getPolicyId());
//		stage = policy.getType();
		
		batch = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
		
//		String fsids = detail.getFeeSubjectIds();
//		if(fsids != null)
//		{
//			feeSubjects = new LinkedList<FeeSubject>();
//			
//			String[] ids = fsids.split("_");
//			for(int i=1; i<ids.length; i++)
//			{
//				feeSubjects.add(feeSubjectBiz.findFeeSubjectById(Integer.valueOf(ids[i])));
//			}
//		}
		
//TODO		String bchids = detail.getBranchIds();
//		if(bchids != null)
//		{
//			branches = new LinkedList<Branch>();
//			String[] ids = bchids.split("_");
//			for(int i=1; i<ids.length; i++)
//			{
//				branches.add(branchBiz.findBranchById(Integer.valueOf(ids[i])));
//			}
//		}
		
//TODO		String mjids = detail.getMajorIds();
//		if(mjids != null)
//		{
//			majors = new LinkedList<Major>();
//			String[] ids = mjids.split("_");
//			for(int i=1; i<ids.length; i++)
//			{
//				majors.add(majorBiz.findMajorById(Integer.valueOf(ids[i])));
//			}
//		}
		
		level = levelBiz.findLevelById(detail.getLevelId());
		//detail.setLevel(levelBiz.findLevelById(detail.getLevelId()));
		
		standards = academyRebatePolicyDetailStandardBiz.findByPolicyId(detail.getPolicyId());
		
		this.setIl8nName("enrollment");
		
		return SUCCESS;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public List<FeeSubject> getFeeSubjects() {
		return feeSubjects;
	}

	public Level getLevel() {
		return level;
	}

	public AcademyEnrollBatch getBatch() {
		return batch;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}
	
	
	public List<Major> getMajors() {
		return majors;
	}

	public String getStage()
	{
		return this.getText("policy.type."+stage);
	}

	public List<AcademyRebatePolicyDetailStandard> getStandards() {
		return standards;
	}
}
