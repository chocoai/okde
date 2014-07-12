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
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.ChannelBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.StudentDiscountDetailBiz;
import net.cedu.biz.enrollment.StudentDiscountPolicyBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.CodeEnum;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Channel;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.enrollment.StudentDiscountDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生优惠政策
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListStudentDiscountDetailAction extends BaseAction
{
	// 分页结果
	private PageResult<StudentDiscountDetail> result = new PageResult<StudentDiscountDetail>();
	
	@Autowired
	private StudentDiscountDetailBiz studentDiscountDetailBiz;//学生优惠政策业务层接口
	private StudentDiscountDetail studentDiscount=new StudentDiscountDetail();//学生优惠政策实体
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;//code生成业务接口
	
	///////////////////////级联(合作方与合作方类别)、、、、、、、、、、、
	@Autowired
	private ChannelBiz channelBiz;//合作方业务接口
	private List<Channel> channellist=new ArrayList<Channel>();//合作方集合
	private int channelType;//合作方类别Id
	
	//查询单个合作方
	private int channelId;//合作方Id
	private Channel channel=new Channel();//合作方实体
	
	
	///////////////////////添加学生优惠政策\\\\\\\\\\\\\\\\\\\\\\\\\
	@Autowired
	private BaseDictBiz baseDictBiz;//数据字典_业务层接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	@Autowired
	private BranchBiz branchBiz;//学习中心业务接口
	
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
	private AcademyMajorBiz academyMajorBiz;//层次与专业之间的关系
	private List<AcademyMajor> academymajorlist=new ArrayList<AcademyMajor>();//关系集合
	
	@Autowired
	private StudentDiscountPolicyBiz studentDiscountPolicyBiz;//学生优惠政策标准业务接口
	
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;//招生途径业务接口(合作方类型)
	
	
	private List<StudentDiscountDetail> discountlist=new ArrayList<StudentDiscountDetail>();
	private StudentDiscountDetail discountDetail=null;
	
	@Autowired
	private LevelBiz levelBiz;//层次biz
	@Autowired
	private MajorBiz majorBiz;//批次biz
	private List<Major> majorlist=new ArrayList<Major>();//基础专业集合
	//添加参数
	private String branchIds;//学习中心Id集合
	private String majorIds;//专业Id集合
	private int policyFeeId;//优惠标准id
	private int academyId;//院校Id
	private int batchId;//批次Id
	private int levelId;//层次关系Id
	private int feesubjectId;//费用科目ID
	private int studentDataSource;//数据来源Id
	private int enrollmentWay;//市场途径Id
	private String enrollmentWayName;//市场途径名称

	//返回参数
	private boolean backtracks=false;
	
	//覆盖政策主体参数
	private String deleteflags;
	private int operate;//1:覆盖    2:追加
	
	//、、、、、、、、、、、修改、、、、、、、、、、、、、
	//参数
	private String discountPolicyId;//政策标准
	private int studentDetailId;//政策Id
	
	//启用、停用参数
	private int isUsing;
	
	//批量审批参数
	private String ids;//优惠政策id集合
	
	/**
	 * 获取优费政策数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String detailCount() throws Exception 
	{
		result.setRecordCount(this.studentDiscountDetailBiz.findStudentDiscountDetailCountByDetails(studentDiscount, result));
		return SUCCESS;
	}
	
	/**
	 * 获取优费政策列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String detailList() throws Exception
	{		
		result.setList(this.studentDiscountDetailBiz.findStudentDiscountDetailListByDetails(studentDiscount, result));
		return SUCCESS;
	}
	
	/**
	 * 合作方类别与合作方级联
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_channeltype_channel_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelList() throws Exception
	{		
		channellist=this.channelBiz.findAllChannelByChannelTypeId(channelType);
		if(channellist!=null && channellist.size()>0)
		{
			for (Channel channel : channellist)
			{
				channel.setName(PingYingHanZiUtil.getNameFirstZiMuToUpperCaseCase(channel.getName())+channel.getName());
			}
			Collections.sort(channellist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Channel) arg0).getName();
					String name2 = ((Channel) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}
	
	/**
	 * 查询合作方信息
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_channel_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String channelDetail() throws Exception
	{		
		channel=this.channelBiz.findChannel(channelId);
		return SUCCESS;
	}

	/**
	 * ajax添加优费政策主体
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"branchlist,"+
			"levellist,"+
			"majorlist"
			}) })
	public String addStudentDiscountDetails() throws Exception 
	{
		//全局变量
		int index=1;
		//销毁session
		super.session.removeAttribute("studentdiscountdetaillist");//
		
		//批量添加
		List<StudentDiscountDetail> sdlist=new ArrayList<StudentDiscountDetail>();
		
		if(academyId!=0 && batchId!=0)
		{
			//取中心id集合
			if(branchIds.equals("-1"))
			{
				if(channelId==-1)
				{
//					branchlist=this.academyBztchBranchBiz.findGrantedBranch(academyId, batchId);
//					branchIds="";
//					for(Branch bra:branchlist)
//					{
//						branchIds+=bra.getId()+",";
//					}
					//重构 2012-01-15
					branchIds=-1+",";
				}
				else
				{
					channel=this.channelBiz.findChannel(channelId);
					if(channel.getIsAll()==Constants.IS_ALL_FALSE)
					{
//						branchlist=this.academyBztchBranchBiz.findGrantedBranch(academyId, batchId);
//						branchIds="";
//						for(Branch bra:branchlist)
//						{
//							branchIds+=bra.getId()+",";
//						}
						//重构 2012-01-15
						branchIds=-1+",";
					}
					else
					{
						branchIds=channel.getBranchId()+",";
					}
				}
			}
			if(branchIds.length()>0)
			{
				branchIds=branchIds.substring(0,(branchIds.length()-1));
			}
			//取层次集合
			String levelIds="";
//			if(levelId==-1)
//			{
//				levellist=this.academyLevelBiz.findBatchId(batchId);
//				
//				for(AcademyLevel lel:levellist)
//				{
//					levelIds+=lel.getId()+",";
//				}
//				if(levelIds.length()>0)
//				{
//					levelIds=levelIds.substring(0,(levelIds.length()-1));
//				}
//			}
//			else
//			{
//				levelIds=levelId+"";
//			}
			//重构 2012-01-15
			levelIds=levelId+"";
			
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
//						majorlist=this.academyMajorBiz.findMajorListByLevelId(Integer.valueOf(lIds[j]));
//						if(majorlist!=null && majorlist.size()>0)
//						{
//							for(Major maj:majorlist)
//							{
//								newmajorIds+=maj.getId()+",";
//							}	
//						}
//						else
//						{
//							continue;
//						}
						//重构 2012-01-15
						newmajorIds=majorIds+",";
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
						discountDetail=new StudentDiscountDetail();
						discountDetail.setAcademyId(academyId);
						discountDetail.setAduitStatus(Constants.AUDIT_STATUS_FALSE);
						discountDetail.setBatchId(batchId);
						discountDetail.setBranchId(Integer.valueOf(bIds[i]));
						discountDetail.setCode(buildCodeBiz.getCodes(CodeEnum.StudentDiscountDetailTB.getCode(),CodeEnum.StudentDiscountDetailPrefix.getCode()));
						discountDetail.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						discountDetail.setCreatorId(super.getUser().getUserId());
						discountDetail.setDeleteFlag(Constants.DELETE_FALSE);
						discountDetail.setFeeSubjectId(feesubjectId);
						discountDetail.setIsUsing(Constants.STATUS_DISABLE);
						if(Integer.valueOf(lIds[j])!=-1 && this.academyLevelBiz.findById(Integer.valueOf(lIds[j]))!=null)
						{
							discountDetail.setLevelId(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId());
						}
						else if(Integer.valueOf(lIds[j])==-1)
						{
							discountDetail.setLevelId(Integer.valueOf(lIds[j]));
						}
						discountDetail.setMajorId(Integer.valueOf(mIds[k]));
						discountDetail.setDiscountPolicyId(policyFeeId+"");
						discountDetail.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						discountDetail.setUpdaterId(super.getUser().getUserId());
						discountDetail.setChannelId(channelId);
						discountDetail.setChannelType(channelType);
						discountDetail.setStudentDataSource(studentDataSource);
						discountDetail.setEnrollmentWay(enrollmentWay);
						//如果招生途径为社招
						if(channelType!=Constants.WEB_STU_SOURCE_DEFAULT && enrollmentWay!=-1)
						{
							if(enrollmentWayName!=null && !enrollmentWayName.equals(""))
							{
								//根据来源名称查询市场途径
								BaseDict bd=baseDictBiz.findBaseDictsByTypeAndName(Constants.BASEDICT_STYLE_ENROLLMENTWAY, enrollmentWayName);
								if(bd!=null)
								{
									discountDetail.setEnrollmentWay(bd.getId());
								}
								
							}
						}
						discountDetail.setDiscountType(Constants.POLICY_REGISTRATION_AFTER);
						if(studentDiscountPolicyBiz.findStudentDiscountPolicyById(policyFeeId)!=null)
						{
							discountDetail.setFeeCountId(studentDiscountPolicyBiz.findStudentDiscountPolicyById(policyFeeId).getFeePaymentId());
						}
						//int feeCountId=studentDiscountPolicyBiz.findStudentDiscountPolicyById(policyFeeId).getFeePaymentId();
						int feeCountId=discountDetail.getFeeCountId();
						List<StudentDiscountDetail> pfeelist=studentDiscountDetailBiz.getByProperty(studentDataSource,discountDetail.getEnrollmentWay(),channelType,channelId,feeCountId,academyId, batchId,Integer.valueOf(bIds[i]),discountDetail.getLevelId(),Integer.valueOf(mIds[k]), feesubjectId);
						if(pfeelist!=null && pfeelist.size()>0)
						{
							discountDetail=this.studentDiscountDetailBiz.findStudentDiscountDetailById(pfeelist.get(0).getId());
							discountDetail.setDeleteFlag(index);
							discountDetail.setDiscountPolicyId(policyFeeId+"");
							if(academyId!=-1)
							{
								discountDetail.setAcademyname(this.academyBiz.findAcademyById(academyId).getName());
							}
							else
							{
								discountDetail.setAcademyname(ResourcesTool.getText("enrollment","#public_all"));
							}
							if(Integer.valueOf(bIds[i])!=-1)
							{
								discountDetail.setBranchname(this.branchBiz.findBranchById(Integer.valueOf(bIds[i])).getName());
							}
							else
							{
								discountDetail.setBranchname(ResourcesTool.getText("enrollment","#public_all"));
							}
							if(batchId!=-1)
							{
								discountDetail.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(batchId).getEnrollmentName());
							}
							else
							{
								discountDetail.setBatchname(ResourcesTool.getText("enrollment","#public_all"));
							}
							if(Integer.valueOf(lIds[j])!=-1)
							{
								if(this.academyLevelBiz.findById(Integer.valueOf(lIds[j]))!=null && this.levelBiz.findLevelById(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId())!=null)
								{
									discountDetail.setLevelname(this.levelBiz.findLevelById(this.academyLevelBiz.findById(Integer.valueOf(lIds[j])).getLevelId()).getName());
								}
								else
								{
									discountDetail.setLevelname("");
								}
							}
							else
							{
								discountDetail.setLevelname(ResourcesTool.getText("enrollment","#public_all"));
							}
							if(Integer.valueOf(mIds[k])!=-1)
							{
								discountDetail.setMajorname(this.majorBiz.findMajorById(Integer.valueOf(mIds[k])).getName());
							}
							else
							{
								discountDetail.setMajorname(ResourcesTool.getText("enrollment","#public_all"));
							}
							discountDetail.setChanneltypename(this.enrollmentSourceBiz.findEnrollmentSourceById(channelType).getName());
							if(channelId!=-1)
							{
								if(this.channelBiz.findChannel(channelId)!=null)
								{
									discountDetail.setChannelname(this.channelBiz.findChannel(channelId).getName());
								}
								else
								{
									discountDetail.setChannelname("");
								}
							}
							else
							{
								discountDetail.setChannelname(ResourcesTool.getText("enrollment","#public_all"));
							}
							discountlist.add(discountDetail);
							index++;
						}
						else
						{
							//this.backtracks=this.studentDiscountDetailBiz.addStudentDiscountDetail(discountDetail);
							sdlist.add(discountDetail);
							
						}					
					}
				}
			}
			//批量添加list
			if(sdlist!=null && sdlist.size()>0)
			{
				this.backtracks=this.studentDiscountDetailBiz.addBatchStudentDiscountDetail(sdlist);
			}
			
			//覆盖list
			if(discountlist!=null && discountlist.size()>0)
			{
				super.session.setAttribute("studentdiscountdetaillist", discountlist);//为覆盖准备
			}	
			
		}
		return SUCCESS;
	}
	
	/**
	 * ajax覆盖政策主体
	 * @return
	 * @throws Exception
	 */
	@Action(value = "cover_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"policylist"
			}) })
	public String coverDiscount() throws Exception {
		discountlist=(List)super.session.getAttribute("studentdiscountdetaillist");
		//批量修改
		List<StudentDiscountDetail> sdlist=new ArrayList<StudentDiscountDetail>();
		
		if(discountlist!=null && discountlist.size()>0 && deleteflags!=null && !deleteflags.equals("") && deleteflags.length()>0)
		{
			deleteflags=deleteflags.substring(0,(deleteflags.length()-1));
			String dIds[]=deleteflags.split(",");
			if(this.operate==1)//覆盖
			{
				for(StudentDiscountDetail fee:discountlist)
				{
					for(int k=0;k<dIds.length;k++)
					{
						if(Integer.valueOf(dIds[k])==fee.getDeleteFlag())
						{
							fee.setDeleteFlag(Constants.DELETE_FALSE);
							fee.setUpdaterId(super.getUser().getUserId());
							fee.setUpdatedTime(new Date());
							//backtracks=this.studentDiscountDetailBiz.modifyStudentDiscountDetail(fee);
							sdlist.add(fee);
						}
					}
				}
			}
			else if(this.operate==2)//追加
			{
				for(StudentDiscountDetail fee:discountlist)
				{
					for(int k=0;k<dIds.length;k++)
					{
						if(Integer.valueOf(dIds[k])==fee.getDeleteFlag())
						{
							fee.setDeleteFlag(Constants.DELETE_FALSE);
							//判断是否追加重复
							boolean booltrue=false;
							String[] fids=this.studentDiscountDetailBiz.findStudentDiscountDetailById(fee.getId()).getDiscountPolicyId().split(",");
							for(int i=0;i<fids.length;i++)
							{
								if(fids[i].equals(fee.getDiscountPolicyId()))
								{
									booltrue=true;
									backtracks=true;
									break;
								}
							}	
							if(!booltrue)
							{
								fee.setDiscountPolicyId(this.studentDiscountDetailBiz.findStudentDiscountDetailById(fee.getId()).getDiscountPolicyId()+","+fee.getDiscountPolicyId());					
								fee.setUpdaterId(super.getUser().getUserId());
								fee.setUpdatedTime(new Date());
								//try{
								//backtracks=this.studentDiscountDetailBiz.modifyStudentDiscountDetail(fee);
								sdlist.add(fee);
								//}catch(Exception ex)
								//{
								//	System.out.print(ex);
								//}
							}	
						}
					}
				}
			}
			//批量覆盖、追加
			if(sdlist!=null && sdlist.size()>0)
			{
				backtracks=this.studentDiscountDetailBiz.updateBatchStudentDiscountDetail(sdlist);
			}
			
		}
		
		//销毁session
		super.session.removeAttribute("studentdiscountdetaillist");//
		return SUCCESS;
	}
	
	/**
	 * ajax添加优费政策主体(报名前)
	 * @return
	 * @throws Exception
	 */
	@Action(value = "add_before_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"branchlist,"+
			"levellist,"+
			"majorlist"
			}) })
	public String addbeforeStudentDiscountDetails() throws Exception 
	{
		//全局变量
		int index=1;
		//销毁session
		super.session.removeAttribute("studentdiscountdetaillist");//
		
		//批量添加
		List<StudentDiscountDetail> sdlist=new ArrayList<StudentDiscountDetail>();
		
		//取中心id集合
		if(branchIds.equals("-1"))
		{
//			branchlist=this.branchBiz.findBranchForModel();
//			branchIds="";
//			for(Branch bra:branchlist)
//			{
//				branchIds+=bra.getId()+",";
//			}
			branchIds=-1+",";
		}
		if(branchIds.length()>0)
		{
			branchIds=branchIds.substring(0,(branchIds.length()-1));
		}
		//开始循环添加
		String bIds[]=branchIds.split(",");
			
			for(int i=0;i<bIds.length;i++)
			{
				discountDetail=new StudentDiscountDetail();
				discountDetail.setAcademyId(-1);
				discountDetail.setAduitStatus(Constants.AUDIT_STATUS_FALSE);
				discountDetail.setBranchId(Integer.valueOf(bIds[i]));
				discountDetail.setCode(DateUtil.getNowDate("yyyyMMddHHmmss"));
				discountDetail.setCreatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				discountDetail.setCreatorId(super.getUser().getUserId());
				discountDetail.setDeleteFlag(Constants.DELETE_FALSE);
				discountDetail.setFeeSubjectId(feesubjectId);
				discountDetail.setIsUsing(Constants.STATUS_DISABLE);
				discountDetail.setDiscountPolicyId(policyFeeId+"");
				discountDetail.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				discountDetail.setUpdaterId(super.getUser().getUserId());						
				discountDetail.setDiscountType(Constants.POLICY_REGISTRATION_BEFORE);
				discountDetail.setFeeCountId(studentDiscountPolicyBiz.findStudentDiscountPolicyById(policyFeeId).getFeePaymentId());
				int feeCountId=studentDiscountPolicyBiz.findStudentDiscountPolicyById(policyFeeId).getFeePaymentId();
				List<StudentDiscountDetail> pfeelist=studentDiscountDetailBiz.getBeforeDiscountByProperty(feeCountId,-1,Integer.valueOf(bIds[i]),feesubjectId);
				if(pfeelist!=null && pfeelist.size()>0)
				{
					discountDetail=this.studentDiscountDetailBiz.findStudentDiscountDetailById(pfeelist.get(0).getId());
					discountDetail.setDeleteFlag(index);
					discountDetail.setDiscountPolicyId(policyFeeId+"");
					if(Integer.valueOf(bIds[i])!=-1)
					{
						discountDetail.setBranchname(this.branchBiz.findBranchById(Integer.valueOf(bIds[i])).getName());
					}
					else
					{
						discountDetail.setBranchname(ResourcesTool.getText("enrollment","#public_all"));
					}
					discountDetail.setFeesubjectname(this.feeSubjectBiz.findFeeSubjectById(feesubjectId).getName());
					discountlist.add(discountDetail);
					index++;
				}
				else
				{
					//this.backtracks=this.studentDiscountDetailBiz.addStudentDiscountDetail(discountDetail);
					sdlist.add(discountDetail);
				}					
			}
			//批量添加
			if(sdlist!=null && sdlist.size()>0)
			{
				this.backtracks=this.studentDiscountDetailBiz.addBatchStudentDiscountDetail(sdlist);
			}
			
			//覆盖list
			if(discountlist!=null && discountlist.size()>0)
			{
				super.session.setAttribute("studentdiscountdetaillist", discountlist);//为覆盖准备
			}	

		return SUCCESS;
	}
	
	/**
	 * 查询不包含总部的所有学习中心
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_all_branch_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchall() throws Exception
	{		
		branchlist=this.branchBiz.findBranchForModel();
		if(branchlist!=null && branchlist.size()>0)
		{
			Collections.sort(branchlist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}
	
	/**
	 * 修改学生优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatediscount() throws Exception 
	{
		studentDiscount=this.studentDiscountDetailBiz.findStudentDiscountDetailById(studentDetailId);
		if(studentDiscount!=null)
		{
			studentDiscount.setDiscountPolicyId(discountPolicyId);
			backtracks=this.studentDiscountDetailBiz.modifyStudentDiscountDetail(studentDiscount);
		}
		return SUCCESS;
	}
	
	/**
	 * 启用/停用学生优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "isusing_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String isusingdetail() throws Exception {
		studentDiscount=this.studentDiscountDetailBiz.findStudentDiscountDetailById(studentDetailId);
		if(studentDiscount!=null)
		{
			studentDiscount.setIsUsing(isUsing);
			backtracks=this.studentDiscountDetailBiz.modifyStudentDiscountDetail(studentDiscount);
		}
		return SUCCESS;
	}
	
	/**
	 * 批量审批学生优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "aduit_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String auditingdetail() throws Exception {
		String[] feeid=ids.split(",");
		
		//批量审批list
		List<StudentDiscountDetail> sdlist=new ArrayList<StudentDiscountDetail>();
		
		for(int i=0;i<feeid.length;i++)
		{
			studentDiscount=this.studentDiscountDetailBiz.findStudentDiscountDetailById(Integer.valueOf(feeid[i]));
			if(studentDiscount!=null)
			{
				if(isUsing==1)
				{
					studentDiscount.setAduitStatus(Constants.AUDIT_STATUS_TRUE);
					studentDiscount.setIsUsing(Constants.STATUS_ENABLED);
				}
				else
				{
					studentDiscount.setAduitStatus(Constants.AUDIT_STATUS_FAIL);	
				}
				studentDiscount.setAuditDate(DateUtil.getNowTimestamp("yyyy-MM-dd"));
				studentDiscount.setAuditer(super.getUser().getUserId());
				//backtracks=this.studentDiscountDetailBiz.modifyStudentDiscountDetail(studentDiscount);
				sdlist.add(studentDiscount);
			}
		}
		
		//批量审批
		if(sdlist!=null && sdlist.size()>0)
		{
			backtracks=this.studentDiscountDetailBiz.updateBatchStudentDiscountDetail(sdlist);
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量修改学生优惠政策
	 * @return
	 * @throws Exception
	 */
	@Action(value = "update_batch_student_discount_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String updatebatchdetail() throws Exception {
		
		backtracks=this.studentDiscountDetailBiz.updateBatchStudentDiscountDetail(ids,discountPolicyId);
		return SUCCESS;
	}
	
	public PageResult<StudentDiscountDetail> getResult() {
		return result;
	}

	public void setResult(PageResult<StudentDiscountDetail> result) {
		this.result = result;
	}

	public StudentDiscountDetail getStudentDiscount() {
		return studentDiscount;
	}

	public void setStudentDiscount(StudentDiscountDetail studentDiscount) {
		this.studentDiscount = studentDiscount;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public List<Channel> getChannellist() {
		return channellist;
	}

	public void setChannellist(List<Channel> channellist) {
		this.channellist = channellist;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public List<StudentDiscountDetail> getDiscountlist() {
		return discountlist;
	}

	public void setDiscountlist(List<StudentDiscountDetail> discountlist) {
		this.discountlist = discountlist;
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

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getLevelId() {
		return levelId;
	}

	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}

	public int getFeesubjectId() {
		return feesubjectId;
	}

	public void setFeesubjectId(int feesubjectId) {
		this.feesubjectId = feesubjectId;
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

	public int getOperate() {
		return operate;
	}

	public void setOperate(int operate) {
		this.operate = operate;
	}

	public List<Branch> getBranchlist() {
		return branchlist;
	}

	public void setBranchlist(List<Branch> branchlist) {
		this.branchlist = branchlist;
	}

	public String getDiscountPolicyId() {
		return discountPolicyId;
	}

	public void setDiscountPolicyId(String discountPolicyId) {
		this.discountPolicyId = discountPolicyId;
	}

	public int getStudentDetailId() {
		return studentDetailId;
	}

	public void setStudentDetailId(int studentDetailId) {
		this.studentDetailId = studentDetailId;
	}

	public int getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(int isUsing) {
		this.isUsing = isUsing;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getStudentDataSource() {
		return studentDataSource;
	}

	public void setStudentDataSource(int studentDataSource) {
		this.studentDataSource = studentDataSource;
	}

	public int getEnrollmentWay() {
		return enrollmentWay;
	}

	public void setEnrollmentWay(int enrollmentWay) {
		this.enrollmentWay = enrollmentWay;
	}

	public String getEnrollmentWayName() {
		return enrollmentWayName;
	}

	public void setEnrollmentWayName(String enrollmentWayName) {
		this.enrollmentWayName = enrollmentWayName;
	}

	
	
}
