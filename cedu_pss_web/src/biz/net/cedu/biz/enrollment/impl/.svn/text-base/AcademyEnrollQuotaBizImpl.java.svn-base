package net.cedu.biz.enrollment.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.enrollment.AcademyEnrollQuotaBiz;
import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyEnrollQuotaDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.User;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademyEnrollQuotaBizImpl implements AcademyEnrollQuotaBiz {
	
	@Autowired
	private AcademyEnrollQuotaDao academyenrollquotaDao;   //院校招生指标接口
	
	@Autowired
	private AcademyBiz academyBiz;        //院校Biz
	
	@Autowired
	private UserBiz userBiz;              //用户Biz
	

	/*
	 * 添加院校指标
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#addAcademyEnrollQuota(int, int, int, int, int)
	 */
	public boolean addAcademyEnrollQuota(int batchId,int branchId,int academyId,int target,int managerId)
			throws Exception {
		AcademyEnrollQuota aeq=null;
		if(batchId!=0 && branchId!=0 && academyId!=0)
		{
			deleteAcademyEnrollQuotaBybranchIdAndAcademyId(batchId,branchId,academyId);
			aeq=new AcademyEnrollQuota();
			aeq.setBatchId(batchId);
			aeq.setBranchId(branchId);
			aeq.setAcademyId(academyId);
			aeq.setTarget(target);
			aeq.setCreatorId(managerId);
			aeq.setCreatedTime(new Date());
			academyenrollquotaDao.save(aeq);
			return true;
		}
		return false;
	}

	
	/*
	 * 删除院校指标
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#deleteAcademyEnrollQuotaBybranchIdAndAcademyId(int, int)
	 */
	public boolean deleteAcademyEnrollQuotaBybranchIdAndAcademyId(int batchId,int branchId,
			int academyId) throws Exception {
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(batchId!=0)
		{
			hqlparam+=" and batchId ="+Constants.PLACEHOLDER;
			list.add(batchId);	
		}
		if(branchId!=0)
		{
			hqlparam+=" and branchId ="+Constants.PLACEHOLDER;
			list.add(branchId);	
		}
		if(academyId!=0)
		{
			hqlparam+=" and academyId ="+Constants.PLACEHOLDER;
			list.add(academyId);	
		}
		academyenrollquotaDao.deleteByProperty(hqlparam, list.toArray());
		return true;
	}

	/*
	 * 查询院校指标按Id
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#findAcademyEnrollQuotaById(int)
	 */
	public AcademyEnrollQuota findAcademyEnrollQuotaById(int id)
			throws Exception {
		
		return academyenrollquotaDao.findById(id);
	}


	/*
	 * 查询院校指标按批次和学习中心
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#findAcademyEnrollQuotaByBatchIdAndBranchId(int, int)
	 */
	public List<AcademyEnrollQuota> findAcademyEnrollQuotaByBatchIdAndBranchId(
			int batchId, int branchId) throws Exception {
		List<AcademyEnrollQuota> academyenrollquotalst=null;
		PageParame p = new PageParame();
		
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(batchId!=0)
		{
			hqlparam+=" and batchId ="+Constants.PLACEHOLDER;
			list.add(batchId);	
		}
		if(branchId!=0)
		{
			hqlparam+=" and branchId ="+Constants.PLACEHOLDER;
			list.add(branchId);	
		}
		hqlparam+=" and deleteFlag="+Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long [] academyenrollquotaids=academyenrollquotaDao.getIDs(p);
		
		if (academyenrollquotaids != null && academyenrollquotaids.length != 0) {
			academyenrollquotalst = new ArrayList<AcademyEnrollQuota>();
			for (int i = 0; i < academyenrollquotaids.length; i++) {
				AcademyEnrollQuota aeq = this.findAcademyEnrollQuotaById(Integer.valueOf(academyenrollquotaids[i].toString()));
				AcademyEnrollQuota academyenrollquota=aeq;
				Academy academy =academyBiz.findAcademyById(academyenrollquota.getAcademyId());
				if(academy!=null)
				{
					academyenrollquota.setAcademyName(academy.getName());
				}
				academyenrollquotalst.add(academyenrollquota);
			}
		}
		return academyenrollquotalst;
	}


	/*
	 * 查询院校指标按批次和学习中心(列表)
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#findAcademyEnrollQuotaByBatchIdAndBranchIdPage(int, int, net.cedu.model.page.PageResult)
	 */
	public List<AcademyEnrollQuota> findAcademyEnrollQuotaByBatchIdAndBranchIdPage(
			int batchId, int branchId, PageResult<AcademyEnrollQuota> pr)throws Exception {
		List<AcademyEnrollQuota> academyenrollquotalst=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(batchId!=0)
		{
			hqlparam+=" and batchId ="+Constants.PLACEHOLDER;
			list.add(batchId);	
		}
		if(branchId!=0)
		{
			hqlparam+=" and branchId ="+Constants.PLACEHOLDER;
			list.add(branchId);	
		}
		hqlparam+=" and deleteFlag="+Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		
		Long [] academyenrollquotaids=academyenrollquotaDao.getIDs(p);
		
		if (academyenrollquotaids != null && academyenrollquotaids.length != 0) {
			academyenrollquotalst = new ArrayList<AcademyEnrollQuota>();
			for (int i = 0; i < academyenrollquotaids.length; i++) {
				AcademyEnrollQuota aeq = this.findAcademyEnrollQuotaById(Integer.valueOf(academyenrollquotaids[i].toString()));
				AcademyEnrollQuota academyenrollquota=aeq;
				Academy academy =academyBiz.findAcademyById(academyenrollquota.getAcademyId());
				if(academy!=null)
				{
					academyenrollquota.setAcademyName(academy.getName());
					User userinfo=userBiz.findUserById(academy.getProjectManagerId());
					if(userinfo!=null)
					{
						academyenrollquota.setUserName(userinfo.getFullName());
					}
				}
				academyenrollquotalst.add(academyenrollquota);
			}
		}
		return academyenrollquotalst;
		
	}


	/*
	 * 查询院校指标按批次Id学习中心Id院校ID
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#findAcademyEnrollQuotaByBatchIdAndBranchIdAndAcademyId(int, int, int)
	 */
	public AcademyEnrollQuota findAcademyEnrollQuotaByBatchIdAndBranchIdAndAcademyId(
			int batchId, int branchId, int academyId) throws Exception {
		
//		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if(batchId!=0)
		{
			hqlparam+=" and batchId ="+Constants.PLACEHOLDER;
			list.add(batchId);	
		}
		if(branchId!=0)
		{
			hqlparam+=" and branchId ="+Constants.PLACEHOLDER;
			list.add(branchId);	
		}
		if(academyId!=0)
		{
			hqlparam+=" and academyId ="+Constants.PLACEHOLDER;
			list.add(academyId);	
		}
		hqlparam+=" and deleteFlag="+Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		List<AcademyEnrollQuota> aeqlst=academyenrollquotaDao.getByProperty(hqlparam, list);
		if(aeqlst!=null && aeqlst.size()>0)
		{
			return aeqlst.get(0);
		}
		return null; 
	}

	/*
	 * 根据批次查询院校招生指标
	 * @see net.cedu.biz.enrollment.AcademyEnrollQuotaBiz#getTargetByBatch(int)
	 */
	public Map<String, Integer> getTargetByBatch(int batchId) throws Exception {
		return academyenrollquotaDao.getTargetByBatch(batchId);
	}
	
	
}
