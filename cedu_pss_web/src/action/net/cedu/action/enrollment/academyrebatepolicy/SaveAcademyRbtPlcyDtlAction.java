package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyBiz;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.AcademyRebatePolicy;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.entity.enrollment.Major;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 保存院校返款政策明细
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json"),
	@Result(name="error", type="json")
})
public class SaveAcademyRbtPlcyDtlAction extends BaseAction
{
	private static final long serialVersionUID = 8203286991309960798L;
	
	private int academyId;	//院校ID
	private int policyId;	//政策标准ID
	private int batchId;	//批次ID
	private int[] branchId;	//学习中心ID列表
	private int levelId;	//层次ID
	private int[] majorId;	//专业ID列表
	private int feeSubjectId; //费用项目ID
	
//	private int detailId;
	
	private int errno;
	
	private List<AcademyRebatePolicyDetail> conflictList = new LinkedList<AcademyRebatePolicyDetail>();
	private List<AcademyRebatePolicyDetail> list = new LinkedList<AcademyRebatePolicyDetail>();
	
	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private AcademyMajorBiz academyMajorBiz;
	@Autowired
	private AcademyRebatePolicyBiz academyRebatePolicyBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;
	@Autowired
	private MajorBiz majorBiz;
	@Autowired
	private LevelBiz levelBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	@Override
	public String execute() throws Exception
	{
		//构造待添加列表
		makeList();
		
		//若无需要添加的新政策
		if(list.size()==0)
		{
			errno = 1;
			return SUCCESS;
		}
		
		try
		{
			conflictList = academyRebatePolicyDetailBiz.addList(list);
			//如果存在冲突政策
			if(conflictList.size()>0)
			{
				errno = 2;
				wrapperConflictList(); //查询冲突政策中的关联Id字段对应的名称
			}
		}
		catch (Exception ex)
		{
			errno = -1; //添加新政策时发生错误
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * 构建政策明细列表
	 * 
	 * @throws Exception
	 */
	private void makeList() throws Exception
	{
		for(int i=0; i<branchId.length; i++)
		{
			if(levelId>0) //若已选择层次
			{
				if(majorId != null)
				{
					for(int j=0; j<majorId.length; j++)
					{
						buildDetailInfo(branchId[i], majorId[j], levelId);
					}
				}
				else
				{
					List<AcademyMajor> ams = academyMajorBiz.findAcademyMajorByLevel(levelId);
					
					if(ams==null) continue;
					
					for(AcademyMajor am : ams)
					{
						buildDetailInfo(branchId[i], am.getMajorId(), levelId);
					}
				}
			}
			else //若没有选择层次，代表选择所有层次
			{
				List<AcademyLevel> als= academyLevelBiz.findBatchId(batchId);
				
				if(als == null || als.size()==0) continue;
				
				for(AcademyLevel al : als)
				{
					List<AcademyMajor> ams = academyMajorBiz.findAcademyMajorByLevel(al.getLevelId());
					
					if(ams==null) continue;
					
					for(AcademyMajor am : ams)
					{
						buildDetailInfo(branchId[i], am.getMajorId(), al.getLevelId());
					}
				}
			}
		}
	}
	
	/**
	 * 填充单个政策明细之内容
	 * @param branchId
	 * @param majorId
	 * @param levelId
	 */
	private void buildDetailInfo(int branchId, int majorId, int levelId) throws Exception
	{
		AcademyRebatePolicyDetail detail = new AcademyRebatePolicyDetail();
		Date now = new Date();
		
		detail.setCreatorId(getUser().getUserId());
		detail.setCreatedTime(now);
		detail.setUpdaterId(getUser().getUserId());
		detail.setUpdatedTime(now);
		detail.setLevelId(levelId);
		detail.setPolicyId(policyId);
		detail.setBatchId(batchId);
		detail.setFeeSubjectId(feeSubjectId);
		detail.setBranchId(branchId);
		detail.setMajorId(majorId);
		
		detail.setAcademyId(academyId);
		detail.setDeleteFlag(Constants.DELETE_FALSE);
		detail.setAuditStatus(Constants.POLICY_AUDIT_STATUS_DEFAULT);
		detail.setEnable(Constants.FALSE);
		
		String code = buildCodeBiz.getCodes(CodeEnum.AcademyRebatePolicyDetailTB.getCode(), CodeEnum.AcademyRebatePolicyDetail.getCode());
		detail.setCode(code);
		
		list.add(detail);
	}
	
	/**
	 * 包装发生冲突的政策明细，添加院校名称、批次名称、政策标准等内容
	 * 
	 * @throws Exception
	 */
	private void wrapperConflictList() throws Exception
	{
		Iterator<AcademyRebatePolicyDetail> iter = conflictList.listIterator();
		while(iter.hasNext())
		{
			AcademyRebatePolicyDetail detail = iter.next();
			
			Academy academy = academyBiz.findAcademyById(detail.getAcademyId());
			if(academy != null)
				detail.setAcademyName(academy.getName());
			
			AcademyEnrollBatch aeb = academyEnrollBatchBiz.findAcademyEnrollBatchById(detail.getBatchId());
			if(aeb != null)
				detail.setBatchName(aeb.getEnrollmentName());
			
			Major major = majorBiz.findMajorById(detail.getMajorId());
			if(major != null)
				detail.setMajorName(major.getName());
			
			Level level = levelBiz.findLevelById(detail.getLevelId());
			if(level != null)
				detail.setLevelName(level.getName());
			
			Branch branch = branchBiz.findBranchById(detail.getBranchId());
			if(branch != null)
				detail.setBranchName(branch.getName());
			
			FeeSubject feeSubject = feeSubjectBiz.findFeeSubjectById(detail.getFeeSubjectId());
			if(feeSubject != null)
				detail.setFeeSubjectName(feeSubject.getName());
			
			AcademyRebatePolicy policy = academyRebatePolicyBiz.getAcademyRebatePolicyById(detail.getPolicyId());
			detail.setPolicy(policy);
		}
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public void setMajorId(int[] majorId) {
		this.majorId = majorId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}

	public void setBranchId(int[] branchId) {
		this.branchId = branchId;
	}

//	public int getDetailId() {
//		return detailId;
//	}

	public int getAcademyId() {
		return academyId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public List<AcademyRebatePolicyDetail> getConflictList() {
		return conflictList;
	}

	public int getErrno() {
		return errno;
	}
}
