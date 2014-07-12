package net.cedu.biz.enrollment.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.basesetting.GlobalEnrollBatchBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyBatchBranchBiz;
import net.cedu.biz.enrollment.AcademyEnrollQuotaBiz;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;
import net.cedu.biz.enrollment.EnrollQuotaBatchBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.dao.admin.BranchDao;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.enrollment.BranchEnrollQuotaDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyEnrollQuota;
import net.cedu.entity.enrollment.BranchEnrollQuota;
import net.cedu.entity.enrollment.EnrollQuotaBatch;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchEnrollQuotaBizImpl implements BranchEnrollQuotaBiz {
	@Autowired
	private BranchEnrollQuotaDao branchenrollquotaDao; // 学习中心批次指标接口
	@Autowired
	private AcademyBatchBranchBiz academybatchbranchBiz; // 学习中心授权院校Biz

	@Autowired
	private AcademyEnrollQuotaBiz academyEnrollQuotaBiz; // 院校招生指标Biz

	@Autowired
	private GlobalEnrollBatchBiz globalenrollbatchBiz; // 全局批次Biz

	@Autowired
	private EnrollQuotaBatchBiz eqbBiz; // 批次指标Biz

	@Autowired
	private UserBiz userBiz; // 用户Biz

	@Autowired
	private BranchBiz branchBiz; // 学习中心Biz
	
	@Autowired
	private BranchDao branchDao; // 学习中心

	@Autowired
	private StudentBiz studentBiz; // 学生Biz
	
	@Autowired
	private StudentDao studentDao; // 学生Dao


	/*
	 * 添加学习中心指标
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#addBranchEnrollQuota(net
	 * .cedu.entity.enrollment.BranchEnrollQuota)
	 */
	public boolean addBranchEnrollQuota(BranchEnrollQuota branchenrollquota)
			throws Exception {
		branchenrollquotaDao.save(branchenrollquota);
		return true;
	}

	/*
	 * 查询学习中心指标
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#findBranchEnrollQuotaAll()
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaAll() throws Exception {

		return branchenrollquotaDao.findAll();
	}

	/*
	 * 查询学习中心指标 按Id
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#findBranchEnrollQuotaById
	 * (int)
	 */
	public BranchEnrollQuota findBranchEnrollQuotaById(int id) throws Exception {

		return branchenrollquotaDao.findById(id);
	}

	/*
	 * 查询学习中心指标
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#findBranchEnrollQuotalist
	 * (net.cedu.model.page.PageResult)
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotalist(int batchId,
			PageResult<BranchEnrollQuota> pr) throws Exception {
		// 学习中心招生批次集合
		List<BranchEnrollQuota> branchEnrollQuotaList = branchenrollquotaDao.findBranchEnrollQuotaList(batchId);
		if (branchEnrollQuotaList != null && branchEnrollQuotaList.size() != 0) {
			BranchEnrollQuota branchenrollquota = null;
			//key:学习中心ID_全局批次ID_院校ID value:招生指标
			Map<String,Integer> targetMap=academyEnrollQuotaBiz.getTargetByBatch(batchId);
			//key:学习中心ID_全局批次ID_院校ID value:完成指标
			Map<String,Integer> targetComMap=studentDao.getCompleteCountAll(batchId);
			for (int i = 0; i < branchEnrollQuotaList.size(); i++) {
				branchenrollquota = branchEnrollQuotaList.get(i);
				//学习中心名称
				Branch branch = branchBiz.findBranchById(branchenrollquota.getBranchId());
				if (branch != null) {
					branchenrollquota.setBranchName(branch.getName());
				}
				//
				List<Academy> academylst = academybatchbranchBiz.findAcademyByBranchIdAndGlobalBatchId(branchenrollquota.getBranchId(),branchenrollquota.getBatchId());
				if (academylst != null && academylst.size() > 0) {
					for (int j = 0; j < academylst.size(); j++) {
						//克隆类
						Academy academy1 = academylst.get(j);
						Academy academy = academy1.clone();
						// 指标
						//academy.setTarget(getZhibiao(branchenrollquota.getBatchId(),branchenrollquota.getBranchId(), academy.getId()));
						Integer target=targetMap.get(branchenrollquota.getBranchId()+"_"+academy.getId());
						academy.setTarget(target==null?0:target);
						// 完成指标
						//academy.setComplete(studentBiz.findStudentCount(batchId,academy.getId(),0));
						Integer targetCom=targetComMap.get(branchenrollquota.getBranchId()+"_"+academy.getId());
						academy.setComplete(targetCom==null?0:targetCom);
						academylst.set(j, academy);
					}

					branchenrollquota.setAcademylst(academylst);
				}
			}
		}

		return branchEnrollQuotaList;
	}

	// 获取指标
	private int getZhibiao(int batchId, int branchId, int academyId)
			throws Exception {
		int zhibiao = 0;
		AcademyEnrollQuota aeq = academyEnrollQuotaBiz
				.findAcademyEnrollQuotaByBatchIdAndBranchIdAndAcademyId(
						batchId, branchId, academyId);
		if (null != aeq) {
			zhibiao = aeq.getTarget();
		}
		return zhibiao;
	}

	/*
	 * 批量添加学习中心指标
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#addBranchEnrollQuotas(int,
	 * int[], int[], int)
	 */
	public boolean addBranchEnrollQuotas(int batchId, Object[] branchIds,
			Object[] branchTarget, int managerId) throws Exception {

		if (batchId != 0 && branchIds != null && branchIds.length > 0
				&& branchTarget != null && branchTarget.length > 0) {

			deleteBranchEnrollQuotaBybatchId(batchId);
			for (int i = 0; i < branchIds.length; i++) {
				BranchEnrollQuota beq = new BranchEnrollQuota();
				beq.setBatchId(batchId);
				beq.setBranchId(Integer.valueOf(branchIds[i].toString()));
				beq.setTarget(Integer.valueOf(branchTarget[i].toString()));
				beq.setCreatorId(managerId);
				beq.setCreatedTime(new Date());
				this.addBranchEnrollQuota(beq);

			}
			EnrollQuotaBatch eqb = eqbBiz.findEnrollQuotaBatchByQuotaIdBatchId(
					0, batchId);
			eqb.setTypes(Constants.STATUS_ENABLED);
			eqbBiz.updateEnrollQuotaBatch(eqb);
			return true;
		}
		return false;
	}

	/*
	 * 批量删除学习中心指标
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#deleteBranchEnrollQuotaBybatchId
	 * (int)
	 */
	public boolean deleteBranchEnrollQuotaBybatchId(int batchId)
			throws Exception {
		List lst = new ArrayList();
		String hql = " and batchId=" + Constants.PLACEHOLDER;
		lst.add(batchId);
		branchenrollquotaDao.deleteByProperty(hql, lst.toArray());
		return true;
	}
	
	/*
	 * 刷新并添加没有的学习中心指标
	 * 
	 * @see net.cedu.biz.enrollment.BranchEnrollQuotaBiz#addRefreshBranchEnrollQuotaBybatchId(int, int)
	 */
	public int addRefreshBranchEnrollQuotaBybatchId(int batchId,int userId) throws Exception 
	{
		int count=0;
		List<Object> lst = new ArrayList();
		String hql = "";
		//批次
		hql+=" and batchId=" + Constants.PLACEHOLDER;
		lst.add(batchId);
		//未删除的
		hql += "and deleteFlag=" + Constants.PLACEHOLDER;
		lst.add(Constants.DELETE_FALSE);
		List<BranchEnrollQuota> brqlist=branchenrollquotaDao.getByProperty(hql,lst.toArray());
		List<Branch> branchlist=branchDao.getByProperty(" and parentId > " + Constants.PLACEHOLDER+"and deleteFlag=" + Constants.PLACEHOLDER+" order by id desc ",new Object[]{BranchEnum.Parent.value(),Constants.DELETE_FALSE});
		if(brqlist!=null && brqlist.size()>0 && branchlist!=null && branchlist.size()>0)
		{
			int branchsize=branchlist.size();
			int brqsize=brqlist.size();
			if(branchsize<=brqsize)
			{
				count=1;
			}
			else
			{
				int index=0;
				for(Branch branch:branchlist)
				{
					boolean isback=false;
					for(BranchEnrollQuota brq:brqlist)
					{
						if(branch.getId()==brq.getId())
						{
							isback=true;
							break;
						}
					}
					if(!isback)
					{
						BranchEnrollQuota beq = new BranchEnrollQuota();
						beq.setBatchId(batchId);
						beq.setBranchId(branch.getId());
						beq.setTarget(0);
						beq.setCreatorId(userId);
						beq.setCreatedTime(new Date());
						this.addBranchEnrollQuota(beq);
						index++;
					}
					if(brqsize+index>=branchsize)
					{
						break;
					}
				}
				count=2;
			}
		}
		return count;
	}

	/*
	 * 查询所有学习中心(分配指标)按批次
	 * 
	 * @see net.cedu.biz.admin.BranchBiz#findBranchByBtachId(int,
	 * net.cedu.model.page.PageResult)
	 */
	public List<Branch> findBranchByBtachId(int batchId, PageResult<Branch> pr)
			throws Exception {
		List<Branch> branchlst = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		hqlparam += " and parentId > " + Constants.PLACEHOLDER;
		list.add(BranchEnum.Parent.value());
		hqlparam += "and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] branchids = branchBiz.findBranchIds(p);
//		int year = 0;
//		int month = 0;
//		int oldyear = Integer.valueOf(ResourcesTool.getText("enrollment",
//				"year"));
//		List<GlobalEnrollBatch> glst = new ArrayList<GlobalEnrollBatch>();

		if (branchids != null && branchids.length != 0) {
			branchlst = new ArrayList<Branch>();
			//Map key:branch value:当前批次指标
			Map<String,Integer> batchTargetMap = branchenrollquotaDao.findBranchEnrollQuotaMapByBatch(batchId);
			//上一批次id
			int lastBatchId = globalenrollbatchBiz.findLastGlobalEnrollBatchIdByGlobalBatchId(batchId);
			Map<String,Integer> lastBatchTargetMap = new HashMap<String, Integer>();
			Map<String,Integer> lastBatchCompleteTargetMap = new HashMap<String, Integer>();
			if(lastBatchId!=0)
			{
				//Map key:branch value:上一批次指标
				lastBatchTargetMap = branchenrollquotaDao.findBranchEnrollQuotaMapByBatch(lastBatchId);
				//Map key:branch value:上一批次完成指标
				lastBatchCompleteTargetMap = studentDao.getCompleteCountAllByGlobalEnrollBatchId(lastBatchId);
			}
			for (int i = 0; i < branchids.length; i++) {
				// 内存获取
				Branch branchObj = branchBiz.findBranchById(Integer.valueOf(branchids[i].toString()));
				
//				GlobalEnrollBatch geb = globalenrollbatchBiz
//						.findGlobalEnrollBatchById(batchId);
//				if (geb != null) {
//					year = geb.getBelongYear();
//				}
//
//				month = Integer.valueOf(geb.getBatch().substring(
//						geb.getBatch().length() - 2));
//				while (true) {
//					if (year <= oldyear) {
//						break;
//					}
//					glst = globalenrollbatchBiz
//							.findGlobalEnrollBatchByYear(year);
//					if (glst == null) {
//						year--;
//						continue;
//					} else {
//
//						int batchid = BatchIdInt(glst, month);
//						BranchEnrollQuota beq = this
//								.findBranchEnrollQuotaByBtachIdAndBranchId(
//										batchid, branchObj.getId());
//						if (beq != null) {
//							branchObj.setBatchTarget(beq.getTarget());
//						}
//						break;
//					}
//
//				}
				//员工数量
				branchObj.setUserCount(userBiz.countUserByOrgId(branchObj.getId()));
				//上一批次指标
				Integer lastTarget = lastBatchTargetMap.get(branchObj.getId()+"");
				branchObj.setBatchTarget(lastTarget==null?0:lastTarget);
				//上一批次完成指标
				Integer lastCompleteTarget = lastBatchCompleteTargetMap.get(branchObj.getId()+"");
				branchObj.setBatchComplete(lastCompleteTarget==null?0:lastCompleteTarget);
				//当前批次指标
				Integer currentTarget = batchTargetMap.get(branchObj.getId()+"");
				branchObj.setCurrentBatchTarget(currentTarget==null?0:currentTarget);
				branchlst.add(branchObj);
			}
		}
		return branchlst;
	}

	// 获取上批次Id
//	public int BatchIdInt(List<GlobalEnrollBatch> glst, int month) {
//		List lst = new ArrayList();
//		int batchid = 0;
//		if (glst.size() > 0) {
//
//			for (int j = 0; j < glst.size(); j++) {
//				int mm = Integer.valueOf(glst.get(j).getBatch()
//						.substring(glst.get(j).getBatch().length() - 2));
//				if (month > mm) {
//					lst.add(glst.get(j).getId() + "_" + mm);
//				}
//			}
//			if (lst != null && lst.size() > 0) {
//
//				for (int k = 0; k < lst.size(); k++) {
//					for (int x = k + 1; x < lst.size(); x++) {
//						String kstr = lst.get(k).toString();
//						String xstr = lst.get(x).toString();
//						int k1 = Integer.valueOf(lst.get(k).toString()
//								.substring(lst.get(k).toString().indexOf('_')));
//						int x1 = Integer.valueOf(lst.get(x).toString()
//								.substring(lst.get(x).toString().indexOf('_')));
//						if (Integer.valueOf(lst.get(k).toString()
//								.substring(lst.get(k).toString().indexOf('_'))) > Integer
//								.valueOf(lst
//										.get(x)
//										.toString()
//										.substring(
//												lst.get(x).toString()
//														.indexOf('_')))) {
//							String temp = kstr;
//							kstr = xstr;
//							xstr = temp;
//						}
//					}
//				}
//				batchid = Integer.valueOf(lst.get(0).toString()
//						.substring(0, lst.get(0).toString().indexOf('_')));
//			}
//		}
//		return batchid;
//	}

	/*
	 * 查询学习中心指标按批次和中心Id
	 * 
	 * @see net.cedu.biz.enrollment.BranchEnrollQuotaBiz#
	 * findBranchEnrollQuotaByBtachIdAndBranchId(int, int)
	 */
	public BranchEnrollQuota findBranchEnrollQuotaByBtachIdAndBranchId(
			int batchId, int branchId) throws Exception {
		BranchEnrollQuota beq = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (batchId != 0) {
			hqlparam += " and batchId=" + Constants.PLACEHOLDER;
			list.add(batchId);
		}
		if(branchId != 0) {
			hqlparam += " and branchId=" + Constants.PLACEHOLDER;
			list.add(branchId);
		}
		hqlparam += " and deleteFlag=";
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());
		Long[] branchenrollquotaids = branchenrollquotaDao.getIDs(p);
		if (branchenrollquotaids != null && branchenrollquotaids.length != 0) {

			for (int i = 0; i < branchenrollquotaids.length; i++) {
				//修改人董溟浩，原作者循环取最后一个，逻辑有问题，所以改为跳过之前的直接取最后一个
				if(i==branchenrollquotaids.length-1)
				{
					beq = new BranchEnrollQuota();
					beq = this.findBranchEnrollQuotaById(Integer
							.valueOf(branchenrollquotaids[i].toString()));
					GlobalEnrollBatch globalenrollbatch = globalenrollbatchBiz
							.findGlobalEnrollBatchById(beq.getBatchId());
					if (globalenrollbatch != null) {
						beq.setBatchName(globalenrollbatch.getBatch());
					}
					Branch branch = branchBiz.findBranchById(beq.getBranchId());
					if (branch != null) {
						beq.setBranchName(branch.getName());
					}
				}
			}
		}
		return beq;
	}

	/*
	 * 查询批次下的所有学习中心
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#findBranchEnrollQuotaByBId
	 * (int)
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaByBId(int batchId)
			throws Exception {
		List<BranchEnrollQuota> branchenrollquotalst = null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (batchId != 0) {
			hqlparam += " and batchId =" + Constants.PLACEHOLDER;
			list.add(batchId);
		}
		hqlparam += " and deleteFlag=" + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		p.setHqlConditionExpression(hqlparam);
		p.setValues(list.toArray());

		Long[] branchenrollquotaids = branchenrollquotaDao.getIDs(p);

		if (branchenrollquotaids != null && branchenrollquotaids.length != 0) {
			branchenrollquotalst = new ArrayList<BranchEnrollQuota>();
			for (int i = 0; i < branchenrollquotaids.length; i++) {
				BranchEnrollQuota beq = this.findBranchEnrollQuotaById(Integer
						.valueOf(branchenrollquotaids[i].toString()));
				BranchEnrollQuota branchenrollquota = beq;
				Branch branch = branchBiz.findBranchById(branchenrollquota
						.getBranchId());
				if (branch != null) {
					branchenrollquota.setBranchName(branch.getName());
				}

				branchenrollquotalst.add(branchenrollquota);
			}
		}
		return branchenrollquotalst;
	}

	/*
	 * 批量修改学习中心招生指标
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#updateBranchEnrollQuotas
	 * (java.lang.String, java.lang.String)
	 */
	public boolean updateBranchEnrollQuotas(String branchIds, String targets,int userId)
			throws Exception {
		if ((null != branchIds && branchIds.indexOf(",") != -1)
				&& (null != targets && targets.indexOf(",") != -1)) {
			// 获取学习中心Id数组
			Object[] bidsObj = branchIds.split(",");
			// 获取学习中心指标数组
			Object[] targetsObj = targets.split(",");
			if (((null != bidsObj && bidsObj.length > 0) && (null != targetsObj && targetsObj.length > 0))
					&& bidsObj.length == targetsObj.length) {
				DateFormat now = DateFormat.getDateTimeInstance();
				for (int i = 1; i < bidsObj.length; i++) {
					branchenrollquotaDao.update("set target="
							+ Constants.PLACEHOLDER + ",updater_id="
							+ Constants.PLACEHOLDER + ",updated_time=", bidsObj[i].toString(),
							new Object[] { targetsObj[i].toString(),userId,now.format(new Date()) });
				}
				return true;
			}
		}
		return false;
	}

	/*
	 * 查询学习中心 按学习中心Id&(多),批次Id&(多)
	 * 
	 * @see
	 * net.cedu.biz.enrollment.BranchEnrollQuotaBiz#findBranchEnrollQuotaByBranchId
	 * (int)
	 */
	public List<BranchEnrollQuota> findBranchEnrollQuotaByBranchIds(
			String batchIds, String branchIds) throws Exception {
		if ((null != batchIds && !"".equals(batchIds))
				&& (null != branchIds && !"".equals(branchIds))) {
			return branchenrollquotaDao.getByProperty("and batchId in ("
					+ Constants.PLACEHOLDER + ")" + "and branchId in ("
					+ Constants.PLACEHOLDER + ")" + " and deleteFlag="
					+ Constants.PLACEHOLDER, new Object[] { "$" + batchIds,
					"$" + branchIds, Constants.DELETE_FALSE });
		} else if ((null != batchIds && !"".equals(batchIds))
				&& (null == branchIds || "".equals(branchIds))) {
			return branchenrollquotaDao.getByProperty("and batchId in ("
					+ Constants.PLACEHOLDER + ")" + " and deleteFlag="
					+ Constants.PLACEHOLDER, new Object[] { "$" + batchIds,
					Constants.DELETE_FALSE });
		} else if ((null == batchIds || "".equals(batchIds))
				&& (null != branchIds || !"".equals(branchIds))) {
			return branchenrollquotaDao.getByProperty("and branchId in ("
					+ Constants.PLACEHOLDER + ")" + " and deleteFlag="
					+ Constants.PLACEHOLDER, new Object[] { "$" + branchIds,
					Constants.DELETE_FALSE });
		} else {
			return branchenrollquotaDao.findAllNotDeleted();
		}

	}

}
