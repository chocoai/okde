package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.enums.FeeSubjectEnum;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.enrollment.PolicyFeeDetailDao;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.entity.enrollment.PolicyFeeDetail;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 收费政策
 * 
 * @author lixaojun
 * 
 */
@Service
public class PolicyFeeDetailBizImpl implements PolicyFeeDetailBiz
{
	
	@Autowired
	private PolicyFeeDetailDao policyFeeDetailDao;//收费政策dao
	
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz;//批次biz
	
	@Autowired
	private BranchBiz branchBiz;//学习中心biz
	
	@Autowired
	private LevelBiz levleBiz;//层次biz
	
	@Autowired
	private MajorBiz majorBiz;//专业biz
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务层接口
	
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准明细biz
	
	@Autowired
	private FeeBatchBiz feeBatchBiz;//缴费次数biz
	
	@Autowired
	private AcademyLevelBiz academyLevelBiz;//招生批次与层次的关系
	
	@Autowired
	private AcademyBiz academyBiz;//院校业务接口
	
	/**
	 * 根据Id查找收费政策
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFeeDetail findPolicyFeeDetailById(int id)throws Exception
	{
		return this.policyFeeDetailDao.findById(id);
	}
	
	/**
	 * 根据Id查找收费政策详细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFeeDetail findPolicyFeeDetailDetailsById(int id)throws Exception
	{
		PolicyFeeDetail obj=this.policyFeeDetailDao.findById(id);
		//院校
		if(obj.getAcademyId()!=0)
		{
			if(this.academyBiz.findAcademyById(obj.getAcademyId())!=null)
			{
				obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
			}
		}
		//批次
		
		if(obj.getBatchId()!=0)
		{
			if(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(obj.getBatchId())!=null)
			{
				obj.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(obj.getBatchId()).getEnrollmentName());
			}
		}
		//学习中心
		if(obj.getBranchId()!=0)
		{
			if(this.branchBiz.findBranchById(obj.getBranchId())!=null)
			{
				obj.setBranchname(this.branchBiz.findBranchById(obj.getBranchId()).getName());
			}
		}
		
		//层次					
		if(obj.getLevelId()!=0)
		{
			if(this.levleBiz.findLevelById(obj.getLevelId())!=null)
			{
				obj.setLevelname(this.levleBiz.findLevelById(obj.getLevelId()).getName());
			}
		}
		
		//专业
		if(obj.getMajorId()!=0)
		{
			if(this.majorBiz.findMajorById(obj.getMajorId())!=null)
			{	
				obj.setMajorname(this.majorBiz.findMajorById(obj.getMajorId()).getName());
			}
		}
		
		//费用科目
		if(obj.getFeeSubjectId()!=0)
		{
			if(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId())!=null)
			{
				obj.setFeeSubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
			}
			
		}
		return obj;
	}
	
	/**
	 * 添加收费政策
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addPolicyFeeDetail(PolicyFeeDetail policyFeeDetail) throws Exception
	{
		if (policyFeeDetail != null) 
		{
			Object object = policyFeeDetailDao.save(policyFeeDetail);
			if (object != null)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 批量添加收费政策明细
	 * @see net.cedu.biz.enrollment.PolicyFeeDetailBiz#addBatchPolicyFeeDetail(java.util.List)
	 */
	public boolean addBatchPolicyFeeDetail(List<PolicyFeeDetail> policyFeeDetailList) throws Exception
	{
		boolean isfail=false;
		if(policyFeeDetailList!=null && policyFeeDetailList.size()>0)
		{
			for(PolicyFeeDetail pfd:policyFeeDetailList)
			{
				isfail=this.addPolicyFeeDetail(pfd);
			}
		}
		return isfail;
	}
	
	/**
	 * 删除收费政策(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigPolicyFeeDetailById(int id) throws Exception
	{
		if (id != 0) 
		{
			Object object = policyFeeDetailDao.deleteConfig(id);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 删除收费政策(物理删除)
	 * @param 
	 */
	public boolean deletePolicyFeeDetailById(int id) throws Exception
	{
		if (id != 0) 
		{
			Object object = policyFeeDetailDao.deleteById(id);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
//	
//	/**
//	 * 删除收费政策(逻辑删除)
//	 * @param 
//	 */
//	public boolean deletePolicyFeeDetailFlag(int id) throws Exception
//	{
//		if (id != 0) 
//		{
//			int num = policyFeeDetailDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	/**
	 * 修改收费政策
	 * @param feeBatch
	 */
	public boolean modifyPolicyFeeDetail(PolicyFeeDetail policyFeeDetail) throws Exception
	{
		if (policyFeeDetail != null)
		{
			Object object = policyFeeDetailDao.update(policyFeeDetail);
			if (object != null)
			{
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * 批量 修改收费政策明细
	 * @see net.cedu.biz.enrollment.PolicyFeeDetailBiz#updateBatchPolicyFeeDetail(java.util.List)
	 */
	public boolean updateBatchPolicyFeeDetail(List<PolicyFeeDetail> policyFeeDetailList) throws Exception
	{
		boolean isfail=false;
		if(policyFeeDetailList!=null && policyFeeDetailList.size()>0)
		{
			for(PolicyFeeDetail pfd:policyFeeDetailList)
			{
				isfail=this.modifyPolicyFeeDetail(pfd);
			}
		}
		return isfail;
	}
	
	/**
	 * 获取收费政策数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findPolicyFeeDetailCountByDetails(int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId,int aduitStatus,PageResult<PolicyFeeDetail> pr) throws Exception 
	{
		PageParame p = new PageParame();
		String params="";
		String canshu="";
		if(academyId!=0)
		{
			params=" and academyId="+ Constants.PLACEHOLDER;
			canshu=academyId+",";
		}
		if(batchId!=0)
		{
			params+=" and batchId="+ Constants.PLACEHOLDER;
			canshu+=batchId+",";
		}
		if(branchId!=0)
		{
			params+=" and branchId="+ Constants.PLACEHOLDER;
			canshu+=branchId+",";
		}
		if(levelId!=0)
		{
			params+=" and levelId="+ Constants.PLACEHOLDER;
			canshu+=levelId+",";
		}
		if(majorId!=0)
		{
			params+=" and majorId="+ Constants.PLACEHOLDER;
			canshu+=majorId+",";
		}
		if(feesubjectId!=0)
		{
			params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
			canshu+=feesubjectId+",";
		}
		if(aduitStatus!=3)
		{
			params+=" and aduitStatus="+ Constants.PLACEHOLDER;
			canshu+=aduitStatus+",";
		}
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		canshu+=Constants.DELETE_FALSE;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		return policyFeeDetailDao.getCounts(p);
	}
	
	/**
	 * 获取收费政策列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<PolicyFeeDetail> findPolicyFeeDetailListByDetails(int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId,int aduitStatus,PageResult<PolicyFeeDetail> pr) throws Exception
	{
			List<PolicyFeeDetail> fees = null;
			// Ids集合
			PageParame p = new PageParame(pr);
//			p.setCurrentPageIndex(pr.getCurrentPageIndex());
//			p.setPageSize(pr.getPageSize());
//			// 分页或者不分页
//			p.setPage(pr.isPage());
			String params="";
			String canshu="";
//			if(batchId!=0)
//			{
//				params=" and ( batchId="+ Constants.PLACEHOLDER+" or batchId="+ Constants.PLACEHOLDER+")";
//				canshu=batchId+","+-1+",";
//			}
//			if(branchId!=0)
//			{
//				params+=" and ( branches like "+ Constants.PLACEHOLDER+" or branches="+ Constants.PLACEHOLDER+")";
//				canshu+="%_"+branchId+"_%"+","+-1+",";
//			}
//			if(levelId!=0)
//			{
//				params+=" and ( levelId="+ Constants.PLACEHOLDER+" or levelId="+ Constants.PLACEHOLDER+")";
//				canshu+=levelId+","+-1+",";
//			}
//			if(majorId!=0)
//			{
//				params+=" and ( majores like "+ Constants.PLACEHOLDER+" or majores="+ Constants.PLACEHOLDER+")";
//				canshu+="%_"+majorId+"_%"+","+-1+",";
//			}
//			if(feesubjectId!=0)
//			{
//				params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
//				canshu+=feesubjectId+",";
//			}
//			if(policyFeeId!=0)
//			{
//				params+=" and policyFeeId="+ Constants.PLACEHOLDER;
//				canshu+=policyFeeId+",";
//			}
			if(academyId!=0)
			{
				params=" and academyId="+ Constants.PLACEHOLDER;
				canshu=academyId+",";
			}
			if(batchId!=0)
			{
				params+=" and batchId="+ Constants.PLACEHOLDER;
				canshu+=batchId+",";
			}
			if(branchId!=0)
			{
				params+=" and branchId="+ Constants.PLACEHOLDER;
				canshu+=branchId+",";
			}
			if(levelId!=0)
			{
				params+=" and levelId="+ Constants.PLACEHOLDER;
				canshu+=levelId+",";
			}
			if(majorId!=0)
			{
				params+=" and majorId="+ Constants.PLACEHOLDER;
				canshu+=majorId+",";
			}
			if(feesubjectId!=0)
			{
				params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
				canshu+=feesubjectId+",";
			}
			if(aduitStatus!=3)
			{
				params+=" and aduitStatus="+ Constants.PLACEHOLDER;
				canshu+=aduitStatus+",";
			}
			params+=" and deleteFlag="+ Constants.PLACEHOLDER;
			canshu+=0;
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			
			Long[] feeIds = policyFeeDetailDao.getIDs(p);
			if (feeIds != null && feeIds.length != 0) {
				fees = new ArrayList<PolicyFeeDetail>();
				for (int i = 0; i < feeIds.length; i++) {
					// 内存获取
					PolicyFeeDetail feeObj = policyFeeDetailDao.findById(Integer.valueOf(feeIds[i].toString()));
					if (feeObj != null) {
						PolicyFeeDetail obj = feeObj;
						//院校
						if(obj.getAcademyId()!=0)
						{
							obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
						}
						//批次
						
						if(obj.getBatchId()!=0)
						{
							obj.setBatchname(this.academyEnrollBatchBiz.findAcademyEnrollBatchById(obj.getBatchId()).getEnrollmentName());
						}
						//学习中心
						if(obj.getBranchId()!=0)
						{
							obj.setBranchname(this.branchBiz.findBranchById(obj.getBranchId()).getName());
						}
						
//						//学习中心
//						if(!obj.getBranches().equals("-1")&&obj.getBranches()!=null)
//						{
//							String branches=obj.getBranches().substring(1);
//							String[] branes=branches.split("_");
//							String branchlist="";
//							for(int k=0;k<branes.length;k++)
//							{
//								branchlist+=branchBiz.findBranchById(Integer.valueOf(branes[k].toString())).getName()+"<br/>";
//							}
//							obj.setBranchlist(branchlist);
//						}
//						else
//						{
//							obj.setBranchlist("");
//						}
						
						//层次					
						if(obj.getLevelId()!=0)
						{
							obj.setLevelname(this.levleBiz.findLevelById(obj.getLevelId()).getName());
						}
						
						//专业
						if(obj.getMajorId()!=0)
						{
							obj.setMajorname(this.majorBiz.findMajorById(obj.getMajorId()).getName());
						}
						
						//费用科目
						if(obj.getFeeSubjectId()!=0)
						{
							obj.setFeeSubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
						}
						
						//收费标准
//						String standes="";
						List<FeeStandard> standardlist=this.feeStandardBiz.findFeeStandardListByFeeId(obj.getPolicyFeeId());
						if(standardlist!=null&&standardlist.size()>0)
						{
							StringBuffer standesSB = new StringBuffer("");
							for(FeeStandard stand:standardlist)
							{
								//standes+=DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+" "+ResourcesTool.getText("enrollment","moneyceil")+stand.getChargingCeil()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
//								standes+=DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
								standesSB.append(DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>");
							}
							obj.setFeestandardes(standesSB.toString());
						}
											
						fees.add(obj);
					}
				}
			}

			return fees;
		
	}
	
	/**
	 * 按多个条件查询收费政策   
	 * @param hqlConditionExpression
	 * @param obj
	 * @return
	 */
	public List<PolicyFeeDetail> getByProperty(int academyId,int batchId,int branchId,int levelId,int majorId,int feesubjectId) throws Exception
	{
		String con=" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER+" and branchId="+Constants.PLACEHOLDER+" and levelId="+Constants.PLACEHOLDER+" and majorId="+Constants.PLACEHOLDER+" and feeSubjectId="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		return this.policyFeeDetailDao.getByProperty(con, new Object[]{academyId,batchId,branchId,levelId,majorId,feesubjectId,Constants.DELETE_FALSE});
	}
	
	/*
	 * 批量审批收费政策
	 * @see net.cedu.biz.enrollment.PolicyFeeDetailBiz#aduitBatchPolicyFeeDetail(java.lang.String, int)
	 */
	public void updateBatchPolicyFeeDetail(String policyFeeDetailIds,int aduitStatus,int userId) throws Exception
	{
		int status=0;
		if(aduitStatus==1)
		{
			status=Constants.AUDIT_STATUS_TRUE;							
		}
		else
		{
			status=Constants.AUDIT_STATUS_FAIL;	
		}
		policyFeeDetailDao.update(" set aduitStatus="+ Constants.PLACEHOLDER+" ,auditDate="+ Constants.PLACEHOLDER+" ,auditer="+ Constants.PLACEHOLDER+" ,isUsing="+ Constants.PLACEHOLDER,policyFeeDetailIds,new Object[] { status,"'"+DateUtil.getNowTimestamp("yyyy-MM-dd")+"'",userId,Constants.STATUS_ENABLED } );		
		
	}
	
	
	/*
	 * 根据学生信息和缴费科目Id匹配收费标准
	 * 
	 * @see net.cedu.biz.enrollment.PolicyFeeDetailBiz#findPolicyFeeByStudent(net.cedu.entity.crm.Student, int)
	 */
	public List<PolicyFee> findPolicyFeeByStudent(Student student,int feeSubjectId) throws Exception
	{
		List<PolicyFee> policyFeeList=null;
		if(student!=null)
		{
			PageParame p = new PageParame();
			String hqlparam = "";
			List list = new ArrayList();
			//院校Id
	
				hqlparam += " and academyId= " + Constants.PLACEHOLDER;
				list.add(student.getAcademyId());

			//招生批次Id
			
				hqlparam += " and batchId= " + Constants.PLACEHOLDER;
				list.add(student.getEnrollmentBatchId());
			
			//学习中心Id
			
				hqlparam += " and branchId= " + Constants.PLACEHOLDER;
				list.add(student.getBranchId());
			
			//层次Id
			
				hqlparam += " and levelId= " + Constants.PLACEHOLDER;
				list.add(student.getLevelId());
						
			//专业Id
			
				hqlparam += " and majorId= " + Constants.PLACEHOLDER;
				list.add(student.getMajorId());
			
			//费用科目
			if (feeSubjectId!=FeeSubjectEnum.OtherFee.value()) 
			{
				hqlparam += " and feeSubjectId= " + Constants.PLACEHOLDER;
				list.add(feeSubjectId);
			}
			//审核通过
			hqlparam += " and aduitStatus= " + Constants.PLACEHOLDER;
			list.add(Constants.AUDIT_STATUS_TRUE);
			//已启用
			hqlparam += " and isUsing= " + Constants.PLACEHOLDER;
			list.add(Constants.STATUS_ENABLED);
			
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
			Long[] ids = policyFeeDetailDao.getIDs(p);
			if (ids != null && ids.length != 0) 
			{
				policyFeeList = new ArrayList<PolicyFee>();
				for (int i = 0; i < ids.length; i++)
				{
					PolicyFeeDetail policyFeeDetail=this.policyFeeDetailDao.findById(Integer.valueOf(ids[i].toString()));
					if(policyFeeDetail!=null && policyFeeDetail.getPolicyFeeId()!=0);
					{
						if(this.policyFeeBiz.findPolicyFeeById(policyFeeDetail.getPolicyFeeId())!=null)
						{
							PolicyFee policyFee=this.policyFeeBiz.findPolicyFeeById(policyFeeDetail.getPolicyFeeId());
							policyFee.setPolicyFeeDetail(policyFeeDetail.getId());
							policyFeeList.add(policyFee);
						}
					}
				}
			}
			
		}
		return policyFeeList;
	}
	
	/*
	 * 根据收费标准Id和审批状态查询政策数量
	 * @see net.cedu.biz.enrollment.PolicyFeeDetailBiz#findPolicyFeeDetailCountByPolicyFeeIdAuditStatus(int, int)
	 */
	public int findPolicyFeeDetailCountByPolicyFeeIdAuditStatus(int policyFeeId,int auditStatus)throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List list = new ArrayList();
		hqlparam += " and policyFeeId= " + Constants.PLACEHOLDER;
		list.add(policyFeeId);
		hqlparam += " and aduitStatus= " + Constants.PLACEHOLDER;
		list.add(auditStatus);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		return policyFeeDetailDao.getCounts(p);
	}
}
