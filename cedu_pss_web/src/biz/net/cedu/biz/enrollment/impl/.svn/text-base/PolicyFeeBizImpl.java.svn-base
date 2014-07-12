package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.common.il8n.ResourcesTool;
import net.cedu.dao.enrollment.PolicyFeeDao;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校收费政策标准
 * 
 * @author lixaojun
 * 
 */
@Service
public class PolicyFeeBizImpl implements PolicyFeeBiz
{
	
	@Autowired
	private PolicyFeeDao policyFeeDao;//院校收费政策标准dao
	
	@Autowired
	private PolicyFeeDetailBiz policyFeeDetailBiz;//院校收费政策业务接口
	
	@Autowired
	private AcademyBiz academyBiz;//院校biz
	
	@Autowired
	private BaseDictBiz baseDictBiz;//基础表  (查询学制)
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准规则业务接口
	
	/**
	 * 根据Id查找院校收费政策标准
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFee findPolicyFeeById(int id)throws Exception
	{
		return this.policyFeeDao.findById(id);
	}
	
	/**
	 * 添加院校收费政策标准
	 * @param policyFee
	 * @return
	 * @throws Exception
	 */
	public boolean addPolicyFee(PolicyFee policyFee) throws Exception
	{
		if (policyFee != null)
		{
			Object object = policyFeeDao.save(policyFee);
			if (object != null) 
			{
				return true;
			}
		}
		
		return false;
	}
	

	/*
	 * 添加院校收费政策标准及其明细列表
	 * @see net.cedu.biz.enrollment.PolicyFeeBiz#addPolicyFeeFeeStandardList(net.cedu.entity.enrollment.PolicyFee, java.util.List)
	 */
	public boolean addPolicyFeeFeeStandardList(PolicyFee policyFee,List<FeeStandard> feeStandardList) throws Exception
	{
		boolean isfail=false;
		if (policyFee != null && feeStandardList!=null && feeStandardList.size()>0)
		{
			isfail=this.addPolicyFee(policyFee);
			for(FeeStandard feeStandard:feeStandardList)
			{
				feeStandard.setPolicyFeeDetailId(policyFee.getId());
				isfail=this.feeStandardBiz.addFeeStandard(feeStandard);
			}
		}
		
		return isfail;
	}
	
	/*
	 * 删除院校收费政策标准及其明细（物理删除）
	 * @see net.cedu.biz.enrollment.PolicyFeeBiz#deletePolicyFeeAndFeeStandById(int)
	 */
	public boolean deletePolicyFeeAndFeeStandById(int id) throws Exception
	{
		boolean isfail=false;
		List<FeeStandard> feeslist=this.feeStandardBiz.findFeeStandardListByFeeId(id);
		if(feeslist!=null && feeslist.size()>0)
		{
			for(FeeStandard fs:feeslist)
			{
				isfail=this.feeStandardBiz.deleteConfigFeeStandardById(fs.getId());
			}
		}
		Object object=this.policyFeeDao.deleteById(id);
		if(object!=null)
		{
			isfail=true;
		}
		return isfail;
	}
	
	/**
	 * 删除院校收费政策标准(读取配置文件)
	 * @param 
	 */
	public boolean deleteConfigPolicyFeeById(int id) throws Exception
	{
		if (id != 0) {
			Object object = policyFeeDao.deleteConfig(id);
			if (object != null) {
				return true;
			}
		}
		
		return false;
	}
	
//	/**
//	 * 删除院校收费政策标准(物理删除)
//	 * @param 
//	 */
//	public boolean deletePolicyFeeById(int id) throws Exception
//	{
//		if (id != 0) {
//			Object object = policyFeeDao.deleteById(id);
//			if (object != null) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
//	
//	/**
//	 * 删除院校收费政策标准(逻辑删除)
//	 * @param 
//	 */
//	public boolean deletePolicyFeeFlag(int id) throws Exception
//	{
//		if (id != 0)
//		{
//			int num = policyFeeDao.deleteByFlag(id);
//			if (num>0) 
//			{
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	/**
	 * 修改院校收费政策标准
	 * @param feeBatch
	 */
	public boolean modifyPolicyFee(PolicyFee policyFee) throws Exception
	{
		if (policyFee != null) 
		{
			Object object = policyFeeDao.update(policyFee);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 修改院校收费政策标准及其明细列表
	 * @see net.cedu.biz.enrollment.PolicyFeeBiz#updatePolicyFeeFeeStandardList(net.cedu.entity.enrollment.PolicyFee, java.util.List)
	 */
	public boolean updatePolicyFeeFeeStandardList(PolicyFee policyFee,List<FeeStandard> feeStandardList) throws Exception
	{
		boolean isfail=false;
		if (policyFee != null && feeStandardList!=null && feeStandardList.size()>0)
		{
			isfail=this.modifyPolicyFee(policyFee);
			//删除原先的标准
			List<FeeStandard> feelist=this.feeStandardBiz.findFeeStandardListByFeeId(policyFee.getId());
			if(feelist!=null&&feelist.size()>0)
			{
				for(FeeStandard fee:feelist)
				{
					this.feeStandardBiz.deleteConfigFeeStandardById(fee.getId());
				}
			}
			//添加新标准
			for(FeeStandard feeStandard:feeStandardList)
			{
				feeStandard.setPolicyFeeDetailId(policyFee.getId());
				isfail=this.feeStandardBiz.addFeeStandard(feeStandard);
			}
		}
		return isfail;
	}
	
	/**
	 * 获取院校收费政策标准数量
	 * @param type
	 * @param pr
	 * @return
	 */
	public int findPolicyFeeCountByDetails(int academyId, int feesubjectId,PageResult<PolicyFee> pr) throws Exception
	{
		PageParame p = new PageParame();
		String params="";
		String canshu="";
		if(academyId!=0)
		{
			params+=" and academyId="+ Constants.PLACEHOLDER;
			canshu+=academyId+",";
		}
		if(feesubjectId!=0)
		{
			params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
			canshu+=feesubjectId+",";
		}
		params+=" and deleteFlag="+ Constants.PLACEHOLDER;
		canshu+=0;
		if(!params.equals(""))
		{
			p.setHqlConditionExpression(params);
			p.setValues(canshu.split(","));
		}
		return policyFeeDao.getCounts(p);
	}
	
	/**
	 * 获取院校收费政策标准列表
	 * @param type
	 * @param pr
	 * @return
	 */
	public List<PolicyFee> findPolicyFeeListByDetails(int academyId, int feesubjectId,PageResult<PolicyFee> pr) throws Exception
	{
			List<PolicyFee> fees = null;
			// Ids集合
			PageParame p = new PageParame(pr);
//			p.setCurrentPageIndex(pr.getCurrentPageIndex());
//			p.setPageSize(pr.getPageSize());
//			// 分页或者不分页
//			p.setPage(pr.isPage());
			String params="";
			String canshu="";
			if(academyId!=0)
			{
				params+=" and academyId="+ Constants.PLACEHOLDER;
				canshu+=academyId+",";
			}
			if(feesubjectId!=0)
			{
				params+=" and feeSubjectId="+ Constants.PLACEHOLDER;
				canshu+=feesubjectId+",";
			}
			params+=" and deleteFlag="+ Constants.PLACEHOLDER;
			canshu+=0;
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(canshu.split(","));
			}
			
			
			Long[] feeIds = policyFeeDao.getIDs(p);
			if (feeIds != null && feeIds.length != 0) {
				fees = new ArrayList<PolicyFee>();
				for (int i = 0; i < feeIds.length; i++) {
					// 内存获取
					PolicyFee feeObj = policyFeeDao.findById(Integer.valueOf(feeIds[i].toString()));
					if (feeObj != null) {
						PolicyFee obj = feeObj;
						//院校名称
						if(obj.getAcademyId()!=0)
						{
							obj.setAcademyname(this.academyBiz.findAcademyById(obj.getAcademyId()).getName());
						}
						//学制
						if(obj.getModeId()!=0)
						{
							obj.setModename(this.baseDictBiz.findBaseDictById(obj.getModeId()).getName());
						}
						//费用科目
						if(obj.getFeeSubjectId()!=0)
						{
							obj.setFeeSubjectname(this.feeSubjectBiz.findFeeSubjectById(obj.getFeeSubjectId()).getName());
						}
						//收费标准
//						String standes="";
						List<FeeStandard> standardlist=this.feeStandardBiz.findFeeStandardListByFeeId(obj.getId());
						if(standardlist!=null&&standardlist.size()>0)
						{
							StringBuffer standesSB = new StringBuffer("");
							for(FeeStandard stand:standardlist)
							{
								//standes+=DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+"~"+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+" "+ResourcesTool.getText("enrollment","moneyceil")+stand.getChargingCeil()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
//								standes+=DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+"~"+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
								standesSB.append(DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+"~"+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>");
							}
							obj.setFeestandardes(standesSB.toString());
						}
						obj.setIndexcount(this.policyFeeDetailBiz.findPolicyFeeDetailCountByPolicyFeeIdAuditStatus(obj.getId(), Constants.AUDIT_STATUS_TRUE));
						fees.add(obj);
					}
				}
			}

			return fees;

	}
	/**
	 * 根据Id查找院校收费政策标准及其规则详细
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PolicyFee findPolicyFeeAndFeeStandardById(int id)throws Exception
	{
		PolicyFee policyFee=this.policyFeeDao.findById(id);
		//费用科目
		if(policyFee.getFeeSubjectId()!=0)
		{
			policyFee.setFeeSubjectname(this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId()).getName());
		}
		//收费标准
//		String standes="";
		List<FeeStandard> standardlist=this.feeStandardBiz.findFeeStandardListByFeeId(policyFee.getId());
		if(standardlist!=null&&standardlist.size()>0)
		{
			StringBuffer standesSB = new StringBuffer("");
			for(FeeStandard stand:standardlist)
			{
//				standes+=DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+" "+ResourcesTool.getText("enrollment","moneyceil")+stand.getChargingCeil()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>";
				standesSB.append(DateUtil.getDateStr(stand.getStartTime(), "yyyy-MM-dd")+ResourcesTool.getText("enrollment","tag")+DateUtil.getDateStr(stand.getEndTime(), "yyyy-MM-dd")+" "+stand.getFeeBatchName()+" "+ResourcesTool.getText("enrollment","moneyfloor")+stand.getChargingFloor()+ResourcesTool.getText("enrollment","moneyunit")+" "+ResourcesTool.getText("enrollment","moneyceil")+stand.getChargingCeil()+ResourcesTool.getText("enrollment","moneyunit")+"<br/>");
			}
			policyFee.setFeestandardes(standesSB.toString());
		}
		
		return policyFee;
	}
}
