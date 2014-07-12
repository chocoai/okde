package net.cedu.action.enrollment;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.FeeBatch;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.entity.enrollment.PolicyFeeDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收费政策明细
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListPolicyFeeDetailAction extends BaseAction
{
	@Autowired
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策明细biz
		
	// 分页结果
	private PageResult<PolicyFeeDetail> result = new PageResult<PolicyFeeDetail>();
	
	//查询参数
	private int academyId;//院校Id
	private int batchId;//批次Id
	private int branchId;//学习中心Id
	private int levelId;//层次Id
	private int majorId;//专业Id
	private int feesubjectId;//费用科目Id
	private int aduitStatus;//是否审批
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	
	/** *****级联******* */
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//院校招生(学籍)批次  业务接口
	private List<AcademyEnrollBatch> batchlist=new ArrayList<AcademyEnrollBatch>();//批次集合
	
	@Autowired
	private AcademyBatchBranchBiz academyBztchBranchBiz;//院校授权中心
	private List<Branch> branchlist=new ArrayList<Branch>();//学习中心集合
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//院校招生批次与层次的关系
	private List<AcademyLevel> levellist=new ArrayList<AcademyLevel>();//关系集合
	@Autowired
	private LevelBiz levelBiz;//层次biz
	private Level level=new Level();//层次
	
	@Autowired
	private AcademyMajorBiz academyMajorBiz;//层次与批次之间的关系
	private List<AcademyMajor> academymajorlist=new ArrayList<AcademyMajor>();//关系集合
	@Autowired
	private MajorBiz majorBiz;//批次biz
	private Major major=new Major();//批次
	private List<Major> majorlist=new ArrayList<Major>();//专业集合

	
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	private FeeSubject feeSubject=new FeeSubject();//费用科目实体
	@Autowired
	private FeeBatchBiz feeBatchBiz;
	private List<FeeBatch> feelist=new ArrayList<FeeBatch>();//默认缴费次数 
	//查询缴费次数参数
	private int feeId;//费用科目Id
	
	
	/*************************添加收费政策主体************************/
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准_业务层接口
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心业务接口
	
	private List<PolicyFeeDetail> policylist=new ArrayList<PolicyFeeDetail>();
	private PolicyFeeDetail policyFeeDetail=null;
	//添加参数
	private String branchIds;//学习中心Id集合
	private String majorIds;//专业Id集合
	private int policyFeeId;//收费标准id
	//返回参数
	private boolean backtracks=false;
	
	//覆盖政策主体参数
	private String deleteflags;
	
	
	/****************************修改收费政策主体参数**************************/
	private int policyFeeDetailId;//收费政策id
	
	
	/*************启用、停用收费政策****************/
	private int isUsing;//启用、停用
	private boolean isfail=false;//返回结果
	
	private String ids;//批量审批参数
	
	/**
	 * 获取收费政策数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_policy_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,levelId,academyId,batchId,branchId,majorId,feesubjectId,aduitStatus"		
	}) })
	public String policyCount() throws Exception {
		//转换为基础的层次id
		if(levelId!=0)
		{
			levelId=this.academyLevelBiz.findById(levelId).getLevelId();
		}
		result.setRecordCount(this.policyFeeDetailBiz.findPolicyFeeDetailCountByDetails(academyId,batchId, branchId, levelId, majorId, feesubjectId,aduitStatus, result));
		return SUCCESS;
	}
	
	/**
	 * 获取收费政策列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_policy_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,levelId,academyId,batchId,branchId,majorId,feesubjectId,aduitStatus"
	}) })
	public String policyList() throws Exception {
		//转换为基础的层次id
		if(levelId!=0)
		{
			levelId=this.academyLevelBiz.findById(levelId).getLevelId();
		}
		result.setList(this.policyFeeDetailBiz.findPolicyFeeDetailListByDetails(academyId,batchId, branchId, levelId, majorId, feesubjectId,aduitStatus, result));
		return SUCCESS;
	}
	
	/**
	 * 院校与院校招生批次级联(全部的批次)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_academy_batch_all_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cascadeAllBatch() throws Exception {
		batchlist=this.academyEnrollBatchBiz.findByAcademyIdAndFlag(academyId);
		return SUCCESS;
	}
	
	/**
	 * 院校与院校招生批次级联(过滤掉已结束的批次)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_academy_batch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"batchlist.*,academyId"
	}) })
	public String cascadeBatch() throws Exception {
		batchlist=this.academyEnrollBatchBiz.findBatchNotInFinishedByAcademyId(academyId);
		return SUCCESS;
	}
	
	/**
	 * 院校招生批次与学习中心级联
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Action(value = "cascade_batch_branch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"branchlist.*,academyId,batchId"
	}) })
	public String cascadeBranch() throws Exception {
		branchlist=this.academyBztchBranchBiz.findGrantedBranch(academyId, batchId);
		Collections.sort(branchlist, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Branch) arg0).getName();
				String name2 = ((Branch) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}
	
	/**
	 * 院校招生批次与层次级联
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_batch_level_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"levellist.*,batchId",
			"excludeProperties",
			"levellist.*.academyEnrollBatch,levellist.*.academyMajorNames,levellist.*.code," +
			"levellist.*.createdTime,levellist.*.creatorId,levellist.*.deleteFlag," +
			"levellist.*.levelName,levellist.*.updatedTime,levellist.*.updaterId," +
			"levellist.*.level.code,levellist.*.level.createdTime,levellist.*.level.creatorId," +
			"levellist.*.level.deleteFlag,levellist.*.level.updatedTime,levellist.*.level.updaterId"
	}) })
	public String cascadeLevel() throws Exception {
		levellist=this.academyLevelBiz.findBatchId(batchId);
		Level level=null;
		if(levellist!=null&&levellist.size()>0)
		{
			for(AcademyLevel academylevel:levellist)
			{
				level=new Level();
				level.setName(this.levelBiz.findLevelById(academylevel.getLevelId()).getName());
				level.setId(this.levelBiz.findLevelById(academylevel.getLevelId()).getId());
				academylevel.setLevel(level);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 层次与专业级联
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_level_major_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"majorlist.*,levelId",
			"excludeProperties",
			"majorlist.*.basemajorname,majorlist.*.code,majorlist.*.createdTime," +
			"majorlist.*.creatorId,majorlist.*.deleteFlag,majorlist.*.updatedTime," +
			"majorlist.*.updaterId"
	}) })
	public String cascadeMajor() throws Exception {
		majorlist=this.academyMajorBiz.findMajorListByLevelId(levelId);
		Collections.sort(majorlist, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Major) arg0).getName();
				String name2 = ((Major) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}
	
	/**
	 * 费用项目对应的缴费次数
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cascade_feesubject_pf_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String cascadepf() throws Exception {
		feeSubject=this.feeSubjectBiz.findFeeSubjectById(feeId);
		if(batchId==0||feeSubject.getIsMultiplePayment()==0)
		{
			feelist=this.feeBatchBiz.getBystatus(1);//默认的缴费次数

			if(feelist.size()>0&&feeSubject.getIsMultiplePayment()==0)
			{
				//只取第一条数据
				FeeBatch feebs=null;
				for(FeeBatch fees:feelist)
				{
					feebs=fees;
					break;
				}
				feelist.clear();
				feelist.add(feebs);
			}
		}
		else
		{
			feelist=this.feeBatchBiz.getByProperty(0,academyId,batchId);
		}
		
		return SUCCESS;
	}
	
	/**
	 * ajax添加收费政策主体
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_polify_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"branchlist,"+
			"levellist,"+
			"majorlist"
			}) })
	public String addPolicyFeeDetails() throws Exception 
	{
		//全局变量
		int index=1;
		//销毁session
		super.session.removeAttribute("policyfeedetaillist");//
		
		//批量添加政策List
		List<PolicyFeeDetail> policyFeeDetailLists=new ArrayList<PolicyFeeDetail>();
		//获取费用科目Id
		PolicyFee pf=this.policyFeeBiz.findPolicyFeeById(policyFeeId);
		if(pf!=null && pf.getFeeSubjectId()>0)
		{
			feesubjectId=pf.getFeeSubjectId();
		}
		if(academyId!=0 && batchId!=0)
		{
			//取中心id集合
			if(branchIds.equals("-1"))
			{
				branchlist=this.academyBztchBranchBiz.findGrantedBranch(academyId, batchId);
				branchIds="";
				StringBuffer branchIdsSB = new StringBuffer("");
				for(Branch bra:branchlist)
				{
//					branchIds+=bra.getId()+",";
					branchIdsSB.append(bra.getId()+",");
				}
				branchIds=branchIdsSB.toString();
			}
			if(branchIds.length()>0)
			{
				branchIds=branchIds.substring(0,(branchIds.length()-1));
			}
			//取层次集合
			String levelIds="";
			if(levelId==-1)
			{
				levellist=this.academyLevelBiz.findBatchId(batchId);
				StringBuffer levelIdsSB = new StringBuffer("");
				for(AcademyLevel lel:levellist)
				{
//					levelIds+=lel.getId()+",";
					levelIdsSB.append(lel.getId()+",");
				}
				levelIds = levelIdsSB.toString();
				if(levelIds.length()>0)
				{
					levelIds=levelIds.substring(0,(levelIds.length()-1));
				}
			}
			else
			{
				levelIds=levelId+"";
			}
			//开始循环添加
			String bIds[]=branchIds.split(",");
			String lIds[]=levelIds.split(",");
			for(int i=0;i<bIds.length;i++)
			{
				for(int j=0;j<lIds.length;j++)
				{
					//取专业
					String newmajorIds=""; 
					if(majorIds.equals("-1"))
					{
						majorlist=this.academyMajorBiz.findMajorListByLevelId(Integer.valueOf(lIds[j]));
						if(majorlist!=null && majorlist.size()>0)
						{
							StringBuffer newmajorIdsSB = new StringBuffer("");
							for(Major maj:majorlist)
							{
//								newmajorIds+=maj.getId()+",";
								newmajorIdsSB.append(maj.getId()+",");
							}
							newmajorIds=newmajorIdsSB.toString();
						}
						else
						{
							continue;
						}
					}
					else
					{
						newmajorIds=majorIds;
					}
					if(newmajorIds.length()>0)
					{
						newmajorIds=newmajorIds.substring(0,(newmajorIds.length()-1));
					}
					String mIds[]=newmajorIds.split(",");
					for(int k=0;k<mIds.length;k++)
					{
						policyFeeDetail=new PolicyFeeDetail();
						policyFeeDetail.setAcademyId(academyId);
						policyFeeDetail.setAduitStatus(Constants.AUDIT_STATUS_FALSE);
						policyFeeDetail.setBatchId(batchId);
						policyFeeDetail.setBranchId(Integer.valueOf(bIds[i]));
						policyFeeDetail.setCode(buildCodeBiz.getCodes(CodeEnum.PolicyFeeDetailTB.getCode(),CodeEnum.PolicyFeeDetailPrefix.getCode()));
						policyFeeDetail.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						policyFeeDetail.setCreatorId(super.getUser().getUserId());
						policyFeeDetail.setDeleteFlag(Constants.DELETE_FALSE);
						policyFeeDetail.setFeeSubjectId(feesubjectId);
						policyFeeDetail.setIsUsing(Constants.STATUS_DISABLE);
						policyFeeDetail.setLevelId(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId());
						policyFeeDetail.setMajorId(Integer.valueOf(mIds[k]));
						policyFeeDetail.setPolicyFeeId(policyFeeId);
						policyFeeDetail.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						policyFeeDetail.setUpdaterId(super.getUser().getUserId());
						List<PolicyFeeDetail> pfeelist=policyFeeDetailBiz.getByProperty(academyId, batchId,Integer.valueOf(bIds[i]),this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId(),Integer.valueOf(mIds[k]), feesubjectId);
						if(pfeelist!=null && pfeelist.size()>0)
						{
							policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(pfeelist.get(0).getId());
							policyFeeDetail.setDeleteFlag(index);
							policyFeeDetail.setPolicyFeeId(policyFeeId);
							policyFeeDetail.setAcademyname(this.academyBiz.findAcademyById(academyId).getName());
							policyFeeDetail.setBranchname(this.branchBiz.findBranchById(Integer.valueOf(bIds[i])).getName());
							policyFeeDetail.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId).getEnrollmentName());
							policyFeeDetail.setLevelname(this.levelBiz.findLevelById(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId()).getName());
							policyFeeDetail.setMajorname(this.majorBiz.findMajorById(Integer.valueOf(mIds[k])).getName());
							policylist.add(policyFeeDetail);
							index++;
						}
						else
						{
							//this.backtracks=this.policyFeeDetailBiz.addPolicyFeeDetail(policyFeeDetail);
							policyFeeDetailLists.add(policyFeeDetail);
						}					
					}
				}
			}
			//批量添加
			if(policyFeeDetailLists!=null && policyFeeDetailLists.size()>0)
			{
				this.backtracks=this.policyFeeDetailBiz.addBatchPolicyFeeDetail(policyFeeDetailLists);
			}
			
			//覆盖的list
			if(policylist!=null && policylist.size()>0)
			{
				super.session.setAttribute("policyfeedetaillist", policylist);//为覆盖准备
			}	
			
		}
		return SUCCESS;
	}
	
	/**
	 * ajax覆盖政策主体
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cover_polify_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"policylist"
			}) })
	public String coverPolify() throws Exception {
		policylist=(List)super.session.getAttribute("policyfeedetaillist");
		//批量修改list
		List<PolicyFeeDetail> pLists=new ArrayList<PolicyFeeDetail>();
		if(policylist!=null && policylist.size()>0 && deleteflags!=null && !deleteflags.equals("") && deleteflags.length()>0)
		{
			deleteflags=deleteflags.substring(0,(deleteflags.length()-1));
			String dIds[]=deleteflags.split(",");
			for(PolicyFeeDetail fee:policylist)
			{
				for(int k=0;k<dIds.length;k++)
				{
					if(Integer.valueOf(dIds[k])==fee.getDeleteFlag())
					{
						fee.setDeleteFlag(Constants.DELETE_FALSE);
						//backtracks=this.policyFeeDetailBiz.modifyPolicyFeeDetail(fee);
						pLists.add(fee);
					}
				}
			}
		}
		//批量修改
		if(pLists!=null && pLists.size()>0)
		{
			backtracks=this.policyFeeDetailBiz.updateBatchPolicyFeeDetail(pLists);
		}
		
		//销毁session
		super.session.removeAttribute("policyfeedetaillist");//
		return SUCCESS;
	}
	
	/**
	 * ajax修改收费政策主体
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_polify_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatePolicyFeeDetails() throws Exception {
		if(policyFeeDetailId!=0 && policyFeeId!=0)
		{
			policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(policyFeeDetailId);
			if(policyFeeDetail!=null)
			{
				policyFeeDetail.setPolicyFeeId(policyFeeId);
				policyFeeDetail.setUpdaterId(super.getUser().getUserId());
				policyFeeDetail.setUpdatedTime(new Date());
				backtracks=this.policyFeeDetailBiz.modifyPolicyFeeDetail(policyFeeDetail);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * ajax修改收费政策主体(no)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "modify_polify_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"branchlist,"+
			"levellist,"+
			"majorlist"
			}) })
	public String modifyPolicyFeeDetails() throws Exception 
	{
		//全局变量
		int index=1;
		//销毁session
		super.session.removeAttribute("policyfeedetaillist");//
		//要修改的政策(删除)
		//this.policyFeeDetailBiz.deletePolicyFeeDetailById(policyFeeDetailId);
		
		if(academyId!=0 && batchId!=0)
		{
			//取中心id集合
			if(branchIds.equals("-1"))
			{
				branchlist=this.academyBztchBranchBiz.findGrantedBranch(academyId, batchId);
				branchIds="";
				StringBuffer branchIdsSB = new StringBuffer("");
				for(Branch bra:branchlist)
				{
//					branchIds+=bra.getId()+",";
					branchIdsSB.append(bra.getId()+",");
				}
				branchIds=branchIdsSB.toString();
			}
			if(branchIds.length()>0)
			{
				branchIds=branchIds.substring(0,(branchIds.length()-1));
			}
			//取层次集合
			String levelIds="";
			if(levelId==-1)
			{
				levellist=this.academyLevelBiz.findBatchId(batchId);
				StringBuffer levelIdsSB = new StringBuffer("");
				for(AcademyLevel lel:levellist)
				{
//					levelIds+=lel.getId()+",";
					levelIdsSB.append(lel.getId()+",");
				}
				levelIds=levelIdsSB.toString();
				if(levelIds.length()>0)
				{
					levelIds=levelIds.substring(0,(levelIds.length()-1));
				}
			}
			else
			{
				levelIds=levelId+"";
			}
			//开始循环添加
			String bIds[]=branchIds.split(",");
			String lIds[]=levelIds.split(",");
			for(int i=0;i<bIds.length;i++)
			{
				for(int j=0;j<lIds.length;j++)
				{
					//取专业
					String newmajorIds=""; 
					if(majorIds.equals("-1"))
					{
						majorlist=this.academyMajorBiz.findMajorListByLevelId(Integer.valueOf(lIds[j]));
						if(majorlist!=null && majorlist.size()>0)
						{
							StringBuffer newmajorIdsSB = new StringBuffer("");
							for(Major maj:majorlist)
							{
//								newmajorIds+=maj.getId()+",";
								newmajorIdsSB.append(maj.getId()+",");
							}
							newmajorIds=newmajorIdsSB.toString();
						}
						else
						{
							continue;
						}
					}
					else
					{
						newmajorIds=majorIds;
					}
					if(newmajorIds.length()>0)
					{
						newmajorIds=newmajorIds.substring(0,(newmajorIds.length()-1));
					}
					String mIds[]=newmajorIds.split(",");
					for(int k=0;k<mIds.length;k++)
					{
						//if(academyId==oldfeedetail.getAcademyId() && batchId==oldfeedetail.getBatchId() && Integer.valueOf(bIds[i])==oldfeedetail.getBranchId() &&this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId()==oldfeedetail.getLevelId() && Integer.valueOf(mIds[k])==oldfeedetail.getMajorId() && feesubjectId==oldfeedetail.getFeeSubjectId())
						policyFeeDetail=new PolicyFeeDetail();
						policyFeeDetail.setAcademyId(academyId);
						policyFeeDetail.setAduitStatus(Constants.AUDIT_STATUS_FALSE);
						policyFeeDetail.setBatchId(batchId);
						policyFeeDetail.setBranchId(Integer.valueOf(bIds[i]));
						policyFeeDetail.setCode(DateUtil.getNowDate("yyyyMMddHHmmss"));
						policyFeeDetail.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						policyFeeDetail.setCreatorId(super.getUser().getUserId());
						policyFeeDetail.setDeleteFlag(Constants.DELETE_FALSE);
						policyFeeDetail.setFeeSubjectId(feesubjectId);
						policyFeeDetail.setIsUsing(Constants.STATUS_DISABLE);
						policyFeeDetail.setLevelId(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId());
						policyFeeDetail.setMajorId(Integer.valueOf(mIds[k]));
						policyFeeDetail.setPolicyFeeId(policyFeeId);
						policyFeeDetail.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						policyFeeDetail.setUpdaterId(super.getUser().getUserId());
						List<PolicyFeeDetail> pfeelist=policyFeeDetailBiz.getByProperty(academyId, batchId,Integer.valueOf(bIds[i]),this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId(),Integer.valueOf(mIds[k]), feesubjectId);
						if(pfeelist!=null && pfeelist.size()>0)
						{
							policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(pfeelist.get(0).getId());
							policyFeeDetail.setDeleteFlag(index);
							policyFeeDetail.setPolicyFeeId(policyFeeId);
							policyFeeDetail.setAcademyname(this.academyBiz.findAcademyById(academyId).getName());
							policyFeeDetail.setBranchname(this.branchBiz.findBranchById(Integer.valueOf(bIds[i])).getName());
							policyFeeDetail.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId).getEnrollmentName());
							policyFeeDetail.setLevelname(this.levelBiz.findLevelById(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId()).getName());
							policyFeeDetail.setMajorname(this.majorBiz.findMajorById(Integer.valueOf(mIds[k])).getName());
							policylist.add(policyFeeDetail);
							index++;
						}
						else
						{
							this.backtracks=this.policyFeeDetailBiz.addPolicyFeeDetail(policyFeeDetail);
						}					
					}
				}
			}
			if(policylist!=null && policylist.size()>0)
			{
				super.session.setAttribute("policyfeedetaillist", policylist);//为覆盖准备
			}	
			
		}
		return SUCCESS;
	}
	
	/**
	 * 启用/停用院校收费政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "isusing_policy_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String isusingdetail() throws Exception {
		policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(policyFeeDetailId);
		policyFeeDetail.setIsUsing(isUsing);
		policyFeeDetail.setUpdaterId(super.getUser().getUserId());
		policyFeeDetail.setUpdatedTime(new Date());
		isfail=this.policyFeeDetailBiz.modifyPolicyFeeDetail(policyFeeDetail);
		return SUCCESS;
	}
	
	/**
	 * 批量审批院校收费政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "aduit_policy_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String auditingdetail() throws Exception {
		this.policyFeeDetailBiz.updateBatchPolicyFeeDetail(ids, isUsing, super.getUser().getUserId());		
		return SUCCESS;
	}
	
	/**
	 * 批量修改院校收费政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_batch_policy_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatebatchdetail() throws Exception {
		String[] feeid=ids.split(",");
		//批量修改list
		List<PolicyFeeDetail> pLists=new ArrayList<PolicyFeeDetail>();
		
		for(int i=0;i<feeid.length;i++)
		{
			policyFeeDetail=this.policyFeeDetailBiz.findPolicyFeeDetailById(Integer.valueOf(feeid[i]));
			if(policyFeeDetail!=null)
			{
				
				policyFeeDetail.setPolicyFeeId(policyFeeId);
				policyFeeDetail.setUpdaterId(super.getUser().getUserId());
				policyFeeDetail.setUpdatedTime(new Date());
				pLists.add(policyFeeDetail);
				//isfail=this.policyFeeDetailBiz.modifyPolicyFeeDetail(policyFeeDetail);
			}
		}
		//批量修改
		if(pLists!=null && pLists.size()>0)
		{
			isfail=this.policyFeeDetailBiz.updateBatchPolicyFeeDetail(pLists);
		}
		
		return SUCCESS;
	}
	
	public PageResult<PolicyFeeDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<PolicyFeeDetail> result) {
		this.result = result;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getMajorId() {
		return majorId;
	}

	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}

	public int getFeesubjectId() {
		return feesubjectId;
	}

	public void setFeesubjectId(int feesubjectId) {
		this.feesubjectId = feesubjectId;
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

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public List<FeeBatch> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<FeeBatch> feelist) {
		this.feelist = feelist;
	}

	public int getFeeId() {
		return feeId;
	}

	public void setFeeId(int feeId) {
		this.feeId = feeId;
	}

	public List<AcademyEnrollBatch> getBatchlist() {
		return batchlist;
	}

	public void setBatchlist(List<AcademyEnrollBatch> batchlist) {
		this.batchlist = batchlist;
	}

	public int getAduitStatus() {
		return aduitStatus;
	}

	public void setAduitStatus(int aduitStatus) {
		this.aduitStatus = aduitStatus;
	}

	public List<PolicyFeeDetail> getPolicylist() {
		return policylist;
	}

	public void setPolicylist(List<PolicyFeeDetail> policylist) {
		this.policylist = policylist;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getMajorIds() {
		return majorIds;
	}

	public void setMajorIds(String majorIds) {
		this.majorIds = majorIds;
	}

	public int getPolicyFeeId() {
		return policyFeeId;
	}

	public void setPolicyFeeId(int policyFeeId) {
		this.policyFeeId = policyFeeId;
	}

	public boolean isBacktracks() {
		return backtracks;
	}

	public void setBacktracks(boolean backtracks) {
		this.backtracks = backtracks;
	}

	public String getDeleteflags() {
		return deleteflags;
	}

	public void setDeleteflags(String deleteflags) {
		this.deleteflags = deleteflags;
	}

	public int getPolicyFeeDetailId() {
		return policyFeeDetailId;
	}

	public void setPolicyFeeDetailId(int policyFeeDetailId) {
		this.policyFeeDetailId = policyFeeDetailId;
	}

	public int getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	
	
}
