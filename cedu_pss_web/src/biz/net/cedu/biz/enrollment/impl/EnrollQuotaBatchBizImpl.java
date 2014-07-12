package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.biz.enrollment.EnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.EnrollQuotaBatchDao;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.EnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollQuotaBatchBizImpl implements EnrollQuotaBatchBiz {
	@Autowired
	private EnrollQuotaBatchDao enrollquotaDao;         //批次指标接口
	@Autowired
	private GlobalEnrollBatchBiz globalenrollbatchBiz;  //全局批次Biz
	@Autowired
	private EnrollQuotaBiz eqbiz;                       //年度总指标Biz
	@Autowired
	private StudentBiz studentBiz;                      //学生Biz
	
	
	

	/*
	 * 添加批次总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#addEnrollQuotaBatch(net.cedu.entity.enrollment.EnrollQuotaBatch)
	 */
	public boolean addEnrollQuotaBatch(int quotaId,List<GlobalEnrollBatch> globalenrollbatchlst ,int managerId)
			throws Exception {
		
		EnrollQuotaBatch enrollquotabatch ;
		if(globalenrollbatchlst!=null && globalenrollbatchlst.size()>0)
		{
			for(int i=0;i<globalenrollbatchlst.size();i++)	
			{
				EnrollQuotaBatch eqb=this.findEnrollQuotaBatchByQuotaIdBatchId(quotaId,globalenrollbatchlst.get(i).getId());
				if(eqb==null)
				{
					enrollquotabatch=new EnrollQuotaBatch();
					enrollquotabatch.setQuotaId(quotaId);
					enrollquotabatch.setBatchId(globalenrollbatchlst.get(i).getId());
					enrollquotabatch.setCreatorId(managerId);
					enrollquotabatch.setCreatedTime(new Date());
					enrollquotaDao.save(enrollquotabatch);
				}
			}
			return true;
		}else
		{
			return false;
		}
		
		
	}
	
	/*
	 * 修改批次总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#updateEnrollQuotaBatch(net.cedu.entity.enrollment.EnrollQuotaBatch)
	 */
	public boolean updateEnrollQuotaBatch(EnrollQuotaBatch enrollquotabatch)
			throws Exception {
		enrollquotaDao.update(enrollquotabatch);
		return true;
	}

	/*
	 * 删除批次总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#deleteEnrollQuotaBatch(int)
	 */
	public boolean deleteEnrollQuotaBatch(int id) throws Exception {
		enrollquotaDao.deleteConfig(id);
		return true;
	}

	/*
	 * 查询批次总指标按Id
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#findEnrollQuotaBatchById(int)
	 */
	public EnrollQuotaBatch findEnrollQuotaBatchById(int id) throws Exception {
		
		return enrollquotaDao.findById(id);
	}

	
	/*
	 * 查询批次总指标
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#findEnrollQuotaBatchBybatchIds(java.lang.String)
	 */
	public List<EnrollQuotaBatch> findEnrollQuotaBatchBybatchIds(int quotaId,String batchIds)
			throws Exception {
		
		List<EnrollQuotaBatch> enrollquotalst=null;   
//		String bIds="";     //院校批次Ids
		
		
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(quotaId!=0)
		{
			hqlparam+=" and quotaId ="+Constants.PLACEHOLDER;
			list.add(quotaId);	
		}
		if(batchIds!=null && !batchIds.equals(""))
		{
			hqlparam+=" and batchId in ("+Constants.PLACEHOLDER+")";
			list.add("$"+batchIds);
		}
		hqlparam+=" and deleteFlag="+Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		hqlparam+=" order by createdTime desc";
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long [] enrollquotabatchids=enrollquotaDao.getIDs(p);
		
		//获取报名数(Map: key-全局批次id value-学生数)
		Map<String,Integer> baomingMap = studentBiz.getBaoMingCountByGlobalEnrollBatchIds(batchIds);
		//获取缴费数(Map: key-全局批次id value-学生数)
		Map<String,Integer> jiaofeiMap = studentBiz.getJiaoFeiCountByGlobalEnrollBatchIds(batchIds);
		//获取录取数(Map: key-全局批次id value-学生数)
		Map<String,Integer> luquMap = studentBiz.getLuQuCountByGlobalEnrollBatchIds(batchIds);
		
		if (enrollquotabatchids != null && enrollquotabatchids.length != 0) {
			enrollquotalst = new ArrayList<EnrollQuotaBatch>();
			for (int i = 0; i < enrollquotabatchids.length; i++) {
				EnrollQuotaBatch eqb = this.findEnrollQuotaBatchById(Integer.valueOf(enrollquotabatchids[i].toString()));
				EnrollQuotaBatch enrollquotabatch=eqb;
				GlobalEnrollBatch globalenrollbatch=globalenrollbatchBiz.findGlobalEnrollBatchById(enrollquotabatch.getBatchId());
				if(globalenrollbatch!=null)
				{
					enrollquotabatch.setBatchName(globalenrollbatch.getBatch());
				}
				if(enrollquotabatch.getTypes()==Constants.IS_POST_TRUE)
				{
					//按全局招生批次查询院校招生批次
//					int [] academyEnrollBatchIds=globalenrollbatchBiz.findAcademyBatchIdsbyGlobalBatchId(enrollquotabatch.getBatchId());
//					if(academyEnrollBatchIds!=null && academyEnrollBatchIds.length>0)
//					{
//						StringBuffer bIdsSB = new StringBuffer(",");
//						for(int j=0;j<academyEnrollBatchIds.length;j++)
//						{
////							bIds+=academyEnrollBatchIds[j]+",";
//							if(bIdsSB.toString().equals(",")){
//								bIdsSB = new StringBuffer(academyEnrollBatchIds[j]+"");
//							}else{
//								bIdsSB.append(","+academyEnrollBatchIds[j]);
//							}
//						}
//						if(bIdsSB.toString().equals(",")){
//							bIdsSB = new StringBuffer("0");
//						}
//						//获取招生数
//						enrollquotabatch.setRegistNumber(studentBiz.findStudentByEnrollBatchIdsStatus(bIdsSB.toString(), Constants.STU_CALL_STATUS_GENG_JIN_ZHONG));
//						//获取缴费数
//						enrollquotabatch.setFeePaymentNumber(studentBiz.findStudentByEnrollBatchIdsStatus(bIdsSB.toString(), Constants.STU_CALL_STATUS_YI_LU_QU));
//						//获取录取数
//						enrollquotabatch.setAdmitNumber(studentBiz.findStudentByEnrollBatchIdsStatus(bIdsSB.toString(), Constants.STU_CALL_STATUS_YI_FU_HE));
//					}
					//获取招生数
					enrollquotabatch.setRegistNumber(baomingMap.get(enrollquotabatch.getBatchId()+""));
					//获取缴费数
					enrollquotabatch.setFeePaymentNumber(jiaofeiMap.get(enrollquotabatch.getBatchId()+""));
					//获取录取数
					enrollquotabatch.setAdmitNumber(luquMap.get(enrollquotabatch.getBatchId()+""));
				}
				enrollquotalst.add(enrollquotabatch);
			}
		}
		
		return enrollquotalst;
	}

	/*
	 * 查询批次总指标按年度ID，批次ID
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#findEnrollQuotaBatchByQuotaIdBatchId(int, int)
	 */
	public EnrollQuotaBatch findEnrollQuotaBatchByQuotaIdBatchId(
			int quotaId, int batchId) throws Exception {
		String hqlConditionExpression="";
		List<Object> list=new ArrayList<Object>();
		if(quotaId!=0)
		{
			hqlConditionExpression+=" and quotaId="+Constants.PLACEHOLDER;
			list.add(quotaId);
		}
		if(batchId!=0)
		{
			hqlConditionExpression+=" and batchId="+Constants.PLACEHOLDER;
			list.add(batchId);
		}
		if(quotaId==0 && batchId==0 )
		{
			return null;
		}
		List<EnrollQuotaBatch> eqblst=enrollquotaDao.getByProperty(hqlConditionExpression, list);
		if(eqblst!=null && eqblst.size()>0)
		{
			return eqblst.get(0);
		}
		return null;
	}

	/*
	 * 查询批次总指标附带年度信息
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#findEnrollQuotaBatchAllById(int)
	 */
	public EnrollQuotaBatch findEnrollQuotaBatchAllById(int id)
			throws Exception {
		String hqlConditionExpression=" and id="+Constants.PLACEHOLDER+" and deleteFlag="+Constants.PLACEHOLDER;
		List<Object> list=new ArrayList<Object>();
		list.add(id);
		list.add(Constants.DELETE_FALSE);
		List<EnrollQuotaBatch> enrollQuotaBatchlst=enrollquotaDao.getByProperty(hqlConditionExpression, list);
		EnrollQuotaBatch enrollquotabatch=null;
		if(enrollQuotaBatchlst!=null && enrollQuotaBatchlst.size()>0)
		{
			enrollquotabatch=new EnrollQuotaBatch();
			enrollquotabatch=enrollQuotaBatchlst.get(0);
		}
		if(enrollquotabatch!=null)
		{
			GlobalEnrollBatch globalenrollbatch=globalenrollbatchBiz.findGlobalEnrollBatchById(enrollquotabatch.getBatchId());
			if(globalenrollbatch!=null)
			{
				enrollquotabatch.setBatchName(globalenrollbatch.getBatch());
			}
			EnrollQuota enrollQuota=eqbiz.findEnrollQuotaById(enrollquotabatch.getQuotaId());
			if(enrollQuota!=null)
			{
				enrollquotabatch.setEnrollYear(enrollQuota.getEnrollYear());
				enrollquotabatch.setEnrollQuota(enrollQuota.getQuota());
			}
		}
		return enrollquotabatch;
	}

	/*
	 *  查询批次总指标批次
	 * @see net.cedu.biz.enrollment.EnrollQuotaBatchBiz#findEnrollQuotaBatch()
	 */
	public List<EnrollQuotaBatch> findEnrollQuotaBatch() throws Exception {
		
		List<EnrollQuotaBatch> enrollquotalst=null;   
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam+=" and deleteFlag="+Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		hqlparam+=" order by createdTime desc ";
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long [] enrollquotabatchids=enrollquotaDao.getIDs(p);
		if (enrollquotabatchids != null && enrollquotabatchids.length != 0) {
			enrollquotalst = new ArrayList<EnrollQuotaBatch>();
			for (int i = 0; i < enrollquotabatchids.length; i++) {
				EnrollQuotaBatch eqb = this.findEnrollQuotaBatchById(Integer.valueOf(enrollquotabatchids[i].toString()));
				EnrollQuotaBatch enrollquotabatch=eqb;
				GlobalEnrollBatch globalenrollbatch=globalenrollbatchBiz.findGlobalEnrollBatchById(enrollquotabatch.getBatchId());
				if(globalenrollbatch!=null)
				{
					enrollquotabatch.setBatchName(globalenrollbatch.getBatch());
				}
				enrollquotalst.add(enrollquotabatch);
			}
		}
		
		return enrollquotalst;
	}


	

	

	
	
}
