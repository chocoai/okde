package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.entity.enrollment.PolicyFeeDetail;

/**
 * 修改收费政策
 * @author lixiaojun
 *
 */
public class ModifyPolicyFeeDetailAction extends BaseAction
{
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	private Academy academy=new Academy();//院校实体
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生批次业务接口
	private List<AcademyEnrollBatch> batchlist=new ArrayList<AcademyEnrollBatch>();//招生批次集合
	
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务接口
	private PolicyFee policyFee=new PolicyFee();//收费标准实体
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
		
	@Autowired 
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策业务接口接口
	private PolicyFeeDetail policyFeeDetail=new PolicyFeeDetail();//收费政策实体
	
	/** *****级联******* */
	@Autowired
	private AcademyBatchBranchBiz academyBztchBranchBiz;//院校授权中心
	private List<Branch> branchlist=new ArrayList<Branch>();//学习中心集合
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private List<AcademyLevel> levellist=new ArrayList<AcademyLevel>();//关系集合
	@Autowired
	private LevelBiz levelBiz;//层次biz
	private Level level=null;//层次
	
	@Autowired
	private AcademyMajorBiz academyMajorBiz;//层次关系与专业之间的关系
	private List<AcademyMajor> academymajorlist=new ArrayList<AcademyMajor>();//关系集合
	
	private List<Major> majorlist=new ArrayList<Major>();//专业集合
	
	private int levelgx;//层次关系id
	
	//跳转参数
	private int id;//收费政策Id
	
	public String execute() throws Exception 
	{
		if(super.isGetRequest())
		{	
			if(id!=0)
			{
				policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(id);				
				//院校
				academylist=this.academyBiz.findAllAcademyByFlagFalse();
				//费用科目
				feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
				//批次list
				batchlist=this.academyEnrollBatchBiz.findBatchNotInFinishedByAcademyId(policyFeeDetail.getAcademyId());
				
				//学习中心list
				branchlist=this.academyBztchBranchBiz.findGrantedBranch(policyFeeDetail.getAcademyId(), policyFeeDetail.getBatchId());
				//层次关系list
				levelgx=0;
				levellist=this.academyLevelBiz.findBatchId(policyFeeDetail.getBatchId());
				Level level=null;
				if(levellist!=null&&levellist.size()>0)
				{
					for(AcademyLevel academylevel:levellist)
					{
						level=new Level();
						level.setName(this.levelBiz.findLevelById(academylevel.getLevelId()).getName());
						if(academylevel.getLevelId()==policyFeeDetail.getLevelId())
						{
							levelgx=academylevel.getId();
						}
						academylevel.setLevel(level);
					}
				}
				//专业list
				majorlist=this.academyMajorBiz.findMajorListByLevelId(levelgx);
				
			}
			return INPUT;
		}
		return SUCCESS;
	}

	public List<AcademyEnrollBatch> getBatchlist() {
		return batchlist;
	}

	public void setBatchlist(List<AcademyEnrollBatch> batchlist) {
		this.batchlist = batchlist;
	}

	public PolicyFee getPolicyFee() {
		return policyFee;
	}

	public void setPolicyFee(PolicyFee policyFee) {
		this.policyFee = policyFee;
	}

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}

	public PolicyFeeDetail getPolicyFeeDetail() {
		return policyFeeDetail;
	}

	public void setPolicyFeeDetail(PolicyFeeDetail policyFeeDetail) {
		this.policyFeeDetail = policyFeeDetail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}

	public List<AcademyLevel> getLevellist() {
		return levellist;
	}

	public void setLevellist(List<AcademyLevel> levellist) {
		this.levellist = levellist;
	}

	public List<AcademyMajor> getAcademymajorlist() {
		return academymajorlist;
	}

	public void setAcademymajorlist(List<AcademyMajor> academymajorlist) {
		this.academymajorlist = academymajorlist;
	}

	public List<Major> getMajorlist() {
		return majorlist;
	}

	public void setMajorlist(List<Major> majorlist) {
		this.majorlist = majorlist;
	}

	public int getLevelgx() {
		return levelgx;
	}

	public void setLevelgx(int levelgx) {
		this.levelgx = levelgx;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public List<Academy> getAcademylist() {
		return academylist;
	}

	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}
	
}
