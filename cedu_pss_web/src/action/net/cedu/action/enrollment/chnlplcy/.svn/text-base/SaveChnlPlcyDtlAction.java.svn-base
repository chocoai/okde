package net.cedu.action.enrollment.chnlplcy;

import static com.opensymphony.xwork2.Action.SUCCESS;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailBiz.WrapType;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.entity.enrollment.ChannelPolicyDetail;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
@Results({
	@Result(name=SUCCESS, type="json")
})
public class SaveChnlPlcyDtlAction extends BaseAction
{
	private static final long serialVersionUID = 970247478512234022L;
	
	private int channelId;
	private int academyId;
	private int batchId;
	private int levelId;
	private int feeSubjectId;
	private int[] majorId;
	private int[] branchIds;
	private boolean isGeneral; //是否通用政策
	private int policyId; //政策标准ID
	
	private List<ChannelPolicyDetail> conflictList;
	
	private ChannelPolicy policy;
	
	List<ChannelPolicyDetail> list = new LinkedList<ChannelPolicyDetail>();
	
	@Autowired
	private AcademyMajorBiz academyMajorBiz;
	@Autowired
	private AcademyLevelBiz academyLevelBiz;
	@Autowired
	private ChannelPolicyBiz channelPolicyBiz;
	@Autowired
	private ChannelPolicyDetailBiz channelPolicyDetailBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;

	public String execute() throws Exception
	{
		policy = channelPolicyBiz.findChannelPolicyById(policyId);
		feeSubjectId = policy.getFeeSubjectId();
		
		makeList(); 
		
		conflictList = channelPolicyDetailBiz.addList(list);
		channelPolicyDetailBiz.wrapList(conflictList, WrapType.ToName);
		
		return SUCCESS;
	}
	
	/**
	 * 生成待添加招生返款政策列表
	 * <br/>
	 * TODO 添加ChannelPolicyDetail时可以不选批次与层次吗？
	 */
	private void makeList() throws Exception
	{
		/* 如果是通用合作方政策 */
		if(isGeneral)
		{
			list.add(makeDetail(-1, -1, -1));
			return;
		}
		
		/* 否则，即为院校合作方政策 */
		for(int branchId : branchIds) //不处理学习中心ID列表为空的情况（让其抛出异常），学习中心ID列表为必填项
		{
			if(levelId>0) //若选择了层次
			{
				if(majorId != null)
				{
					for(int i=0; i<majorId.length; i++)
					{
						ChannelPolicyDetail detail = makeDetail(branchId, levelId, majorId[i]);
						list.add(detail);
					}
				}
				else
				{
					List<AcademyMajor> aml = academyMajorBiz.findAcademyMajorByLevelId(batchId, levelId);
					if(aml != null)
					{
						Iterator<AcademyMajor> amIter = aml.iterator();
						while(amIter.hasNext())
						{
							AcademyMajor am = amIter.next();
							list.add(makeDetail(branchId, levelId, am.getMajorId()));
						}
					}
				}
			}
			else //否则，查询当前批次下的所有层次，并遍历其子级专业列表
			{
				List<AcademyLevel> als= academyLevelBiz.findBatchId(batchId);
				
				if(als != null)
					for(AcademyLevel al : als)
					{
						List<AcademyMajor> ams = academyMajorBiz.findAcademyMajorByLevel(al.getId());
						
						if(ams==null) continue;
						
						for(AcademyMajor am : ams)
						{
							list.add(makeDetail(branchId, al.getLevelId(), am.getMajorId()));
						}
					}
			}
		}
	}
	
	private ChannelPolicyDetail makeDetail(int branchId, int levelId, int majorId) throws Exception
	{
		ChannelPolicyDetail detail = new ChannelPolicyDetail();
		if(!isGeneral)
		{
			detail.setAcademyId(academyId);
			detail.setBatchId(batchId);
			detail.setLevelId(levelId);
			detail.setMajorId(majorId);
			detail.setBranchId(branchId);
		}
		else
		{
			detail.setAcademyId(-1);
			detail.setBatchId(-1);
			detail.setLevelId(-1);
			detail.setMajorId(-1);
			detail.setBranchId(-1); //TODO 也是-1?
		}
		
		detail.setPolicyId(policyId);
		detail.setChannelId(channelId);
		detail.setCreatorId(getUser().getUserId());
		detail.setCreatedTime(new Date());
		detail.setUpdaterId(getUser().getUserId());
		detail.setUpdatedTime(new Date());
		
		if(policy.getRequiresAudit()==Constants.TRUE){ //如果需要审批，则招生返款政策的
			detail.setAuditStatus(Constants.POLICY_AUDIT_STATUS_DEFAULT);
			detail.setEnable(Constants.STATUS_AUTHOR_FALSE);
		} else { // 若不需要，则招生返款政策的默认状态即为“已审批”
			detail.setAuditStatus(Constants.POLICY_AUDIT_STATUS_GOOD);
			// 如果不需要审批，默认启用 edited by dongminghao
			detail.setEnable(Constants.STATUS_AUTHOR_TRUE);
		}
		
		detail.setDeleteFlag(Constants.DELETE_FALSE);
		detail.setFeeSubjectId(feeSubjectId);
		
		detail.setOrder(RandomUtils.nextInt()); //TODO delete
		//detail.setEnable(Constants.FALSE);
		
		String code = buildCodeBiz.getCodes(CodeEnum.ChannelPolicyDetailTB.getCode(), CodeEnum.ChannelPolicyDetail.getCode());

		detail.setCode(code);
		
		return detail;
	}

	public void setGeneral(boolean isGeneral) {
		this.isGeneral = isGeneral;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
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

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public int getChannelId() {
		return channelId;
	}

	public List<ChannelPolicyDetail> getConflictList() {
		return conflictList;
	}

	public void setBranchIds(int[] branchIds) {
		this.branchIds = branchIds;
	}
}
