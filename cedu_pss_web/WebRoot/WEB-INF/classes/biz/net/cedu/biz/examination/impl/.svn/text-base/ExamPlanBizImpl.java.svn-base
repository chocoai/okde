package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.AcademyLevelBiz;
import net.cedu.biz.enrollment.AcademyMajorBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.common.Constants;
import net.cedu.dao.academy.AcademyDao;
import net.cedu.dao.examination.ExamPlanDao;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.AcademyLevel;
import net.cedu.entity.enrollment.AcademyMajor;
import net.cedu.entity.enrollment.Major;
import net.cedu.entity.examination.ExamPlan;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamPlanBizImpl implements ExamPlanBiz {
	@Autowired
	private ExamPlanDao examplandao;
	@Autowired
	private AcademyDao academydao;
	@Autowired
	private LevelBiz levelbiz;
	@Autowired
	private AcademyLevelBiz academyLevelBiz ;
	@Autowired
	private MajorBiz majorbiz;
	
	@Autowired
	private AcademyEnrollBatchBiz academyenrollbatchbiz;
	
	@Autowired
	private BranchBiz branchbiz;
	//private AcademyBatchBranchBiz  academyBatchBranchBiz;
	
	@Autowired
	private AcademyMajorBiz academymajorbiz;

	public boolean createNew(ExamPlan examplan) throws Exception {
		// TODO Auto-generated method stub
		if (examplan.getPlan() != null) {
			examplandao.save(examplan);
			return true;
		}
		return false;
	}

	public Object deleteExamPlanById(int id) throws Exception {
		// TODO Auto-generated method stub
		return examplandao.deleteById(id);
	}

	public List<ExamPlan> findByConditions(ExamPlan examplan,
			PageResult<ExamPlan> pr) throws Exception {
		List<ExamPlan> examplanlist = null;
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql = "";
		if (examplan != null) {
			if (examplan != null) {
				if (examplan.getBatchId() != null && examplan.getBatchId() > 0)// 批次
				{
					hql += "and batchId = " + Constants.PLACEHOLDER;
					list.add(examplan.getBatchId());
				}
				if (examplan.getLevelId() != null && examplan.getLevelId() > 0)// 层次
				{
					hql += "and levelId = " + Constants.PLACEHOLDER;
					list.add(examplan.getLevelId());
				}
				if (examplan.getMajorId() != null && examplan.getMajorId() > 0) // 专业
				{
					hql += "and majorId = " + Constants.PLACEHOLDER;
					list.add(examplan.getMajorId());
				}
				if (examplan.getBranchId()!=null && examplan.getBranchId() >0) {
					hql += "and branchId = " + Constants.PLACEHOLDER;// 学习中心
					list.add(examplan.getBranchId());
				}
				if (examplan.getStatus()!=null && examplan.getStatus()>-1)// 状态
				{
					hql += "and status = " + Constants.PLACEHOLDER;
					list.add(examplan.getStatus());
				}

				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			}
			Long[] examplanIds = examplandao.getIDs(p);
			if (examplanIds != null && examplanIds.length != 0) {
				examplanlist = new ArrayList<ExamPlan>();
			    ExamPlan d=null;
			    for(Long id : examplanIds){
			    	if (null !=(d = examplandao.findById(Integer.parseInt(id
							.toString())))) {
			    		if(d.getLevelId()!=null && d.getLevelId()>0){
			    		AcademyLevel acadelevel=academyLevelBiz.findById(d.getLevelId());
			    			Level level = levelbiz.findLevelById(acadelevel.getLevelId());
						if (level != null) {
							d.setLevelname(level.getName());
						}
						}
			    		if(d.getMajorId()!=null && d.getMajorId()>0){
			    			//AcademyMajor academymajor=academymajorbiz.findById(d.getMajorId());
						Major major = majorbiz.findMajorById(d.getMajorId());
						if (major != null){
							d.setMajorname(major.getName());
						}
						}
			    		if(d.getBatchId()!=null && d.getBatchId()>0){
			    		AcademyEnrollBatch academyenrollbatch=academyenrollbatchbiz.findAcademyEnrollBatchById(d.getBatchId());
			    		if(academyenrollbatch!=null){
			    			d.setBatchname(academyenrollbatch.getEnrollmentName());
			    		}
			    		}
			    		if(d.getBranchId()!=null && d.getBranchId()>0){
			    			Branch branch=branchbiz.findBranchById(d.getBranchId());
			    			if(branch!=null){
			    				d.setBranchname(branch.getName());
			    			}
			    		}
						examplanlist.add(d);
					}
			    	
			    }
				
			}

		}
		return examplanlist;
		}

	public List<ExamPlan> findByConditions(int id, int batchId,int branchId,int levelId,int majorId,
			PageResult<ExamPlan> pr) throws Exception {
		List<ExamPlan> examplanlist = new ArrayList<ExamPlan>();
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql = "";
			if (id > 0) {
				hql += "and academyId = " + Constants.PLACEHOLDER;
				list.add(id);
			}
				if (batchId > 0)// 批次
				{
					hql += "and batchId = " + Constants.PLACEHOLDER;
					list.add(batchId);
				}
				if (levelId > 0)// 层次
				{
					hql += "and levelId = " + Constants.PLACEHOLDER;
					list.add(levelId);
				}
				if (majorId > 0) // 专业
				{
					hql += "and majorId = " + Constants.PLACEHOLDER;
					list.add(majorId);
				}
				if (branchId >0) {
					hql += "and branchId = " + Constants.PLACEHOLDER;// 学习中心
					list.add(branchId);
				}

				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
			
			Long[] examplanIds = examplandao.getIDs(p);
			if (examplanIds != null && examplanIds.length != 0) {
				examplanlist = new ArrayList<ExamPlan>();
			    ExamPlan d=null;
			    for(Long ids : examplanIds){
			    	if (null !=(d = examplandao.findById(Integer.parseInt(ids
							.toString())))) {
			    		if(d.getLevelId()!=null && d.getLevelId()>0){
			    			AcademyLevel acadelevel=academyLevelBiz.findById(d.getLevelId());
			    			Level level = levelbiz.findLevelById(acadelevel.getLevelId());
						if (level != null) {
							d.setLevelname(level.getName());
						}
						}
			    		if(d.getMajorId()!=null && d.getMajorId()>0){
			    			//AcademyMajor academymajor=academymajorbiz.f(d.getMajorId());
						Major major = majorbiz.findMajorById(d.getMajorId());
						if (major != null){
							d.setMajorname(major.getName());
						}
						}
			    		if(d.getBatchId()!=null && d.getBatchId()>0){
			    		AcademyEnrollBatch academyenrollbatch=academyenrollbatchbiz.findAcademyEnrollBatchById(d.getBatchId());
			    		if(academyenrollbatch!=null){
			    			d.setBatchname(academyenrollbatch.getEnrollmentName());
			    		}
			    		}
			    		if(d.getBranchId()!=null && d.getBranchId()>0){
			    			Branch branch=branchbiz.findBranchById(d.getBranchId());
			    			if(branch!=null){
			    				d.setBranchname(branch.getName());
			    			}
			    		}
						examplanlist.add(d);
					}
				}
			}

		
		return examplanlist;
	}

	public ExamPlan findByExamPlanId(int id) throws Exception {
		
		// TODO Auto-generated method stub
		return examplandao.findById(id);
	}
	public ExamPlan findExamplanByExamplanId(int id)throws Exception{
		ExamPlan examplan=findByExamPlanId(id);
		if(examplan.getLevelId()!=null && examplan.getLevelId()>0){
			AcademyLevel acadelevel=academyLevelBiz.findById(examplan.getLevelId());
			Level level = levelbiz.findLevelById(acadelevel.getLevelId());
		if (level != null) {
			examplan.setLevelname(level.getName());
		}
		}
		if(examplan.getMajorId()!=null && examplan.getMajorId()>0){
			//AcademyMajor academymajor=academymajorbiz.findById(examplan.getMajorId());
		Major major = majorbiz.findMajorById(examplan.getMajorId());
		if (major != null){
			examplan.setMajorname(major.getName());
		}
		}
		if(examplan.getBatchId()!=null && examplan.getBatchId()>0){
		AcademyEnrollBatch academyenrollbatch=academyenrollbatchbiz.findAcademyEnrollBatchById(examplan.getBatchId());
		if(academyenrollbatch!=null){
			examplan.setBatchname(academyenrollbatch.getEnrollmentName());
		}
		}
		if(examplan.getBranchId()!=null && examplan.getBranchId()>0){
			Branch branch=branchbiz.findBranchById(examplan.getBranchId());
			if(branch!=null){
				examplan.setBranchname(branch.getName());
			}
		}
		
		return examplan;
	}

	public int findExamPlanPageCount(ExamPlan examplan, PageResult<ExamPlan> pr)
			throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql = "";
		if (examplan != null) {
			if (examplan.getBatchId() != null && examplan.getBatchId() > 0)// 批次
			{
				hql += "and batchId = " + Constants.PLACEHOLDER;
				list.add(examplan.getBatchId());
			}
			if (examplan.getLevelId() != null && examplan.getLevelId() > 0)// 层次
			{
				hql += "and levelId = " + Constants.PLACEHOLDER;
				list.add(examplan.getLevelId());
			}
			if (examplan.getMajorId() != null && examplan.getMajorId() > 0) // 专业
			{
				hql += "and majorId = " + Constants.PLACEHOLDER;
				list.add(examplan.getMajorId());
			}
			if (examplan.getBranchId()!=null && examplan.getBranchId() >0) {
				hql += "and branchId = " + Constants.PLACEHOLDER;// 学习中心
				list.add(examplan.getBranchId());
			}
			if (examplan.getStatus()!=null && examplan.getStatus()>-1)// 状态
			{
				hql += "and status = " + Constants.PLACEHOLDER;
				list.add(examplan.getStatus());
			}

			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
		}

		return examplandao.getCounts(p);
	}

	public int findExamPlanPageCount(int id, int batchId,int branchId,int levelId,int majorId,
			PageResult<ExamPlan> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql = "";
		if (id > 0) {
			hql += "and academyId = " + Constants.PLACEHOLDER;
			list.add(id);
		}
		
			if (batchId > 0)// 批次
			{
				hql += "and batchId = " + Constants.PLACEHOLDER;
				list.add(batchId);
			}
			if (levelId > 0)// 层次
			{
				hql += "and levelId = " + Constants.PLACEHOLDER;
				list.add(levelId);
			}
			if (majorId > 0) // 专业
			{
				hql += "and majorId = " + Constants.PLACEHOLDER;
				list.add(majorId);
			}
			if (branchId >0) {
				hql += "and branchId = " + Constants.PLACEHOLDER;// 学习中心
				list.add(branchId);
			}
			p.setHqlConditionExpression(hql);
			p.setValues(list.toArray());
		

		return examplandao.getCounts(p);
	}

	public List<ExamPlan> page(PageResult<ExamPlan> result) throws Exception {
		List<ExamPlan> examplanlist = new ArrayList<ExamPlan>();
		PageParame pp = new PageParame(result);
		Long[] list = examplandao.getIDs(pp);
		for (int i = 0; i < list.length; i++) {
			examplanlist.add(examplandao.findById(list[i].intValue()));
		}
		return examplanlist;
	}

	public Object updateExamPlan(ExamPlan examplan) throws Exception {
		return examplandao.update(examplan);
	}

	/*
	 * 考试计划的院校分页个数
	 * 
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.cedu.biz.examination.ExamPlanBiz#findAllAcademyCount(net.cedu.model
	 * .page.PageResult)
	 */
	public int findAllAcademyCount(PageResult<Academy> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql = "";
		hql += " and deleteFlag = " + Constants.PLACEHOLDER
				+ " and usingStatus = " + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		list.add(Constants.STATUS_ENABLED);
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		return academydao.getCounts(p);
		

	}
//public ExamPlan findExamplanBybatchId(int id)throws Exception{
//	if(examplandao.getByProperty("examBatchId",id)!=null){
//		return examplandao.getByProperty("examBatchId",id).get(0);
//	}
//	return null;
//}

	public List<Academy> findAllAcademyConditions(PageResult<Academy> pr) {
		List<Academy> academylist = new ArrayList<Academy>();
		PageParame p = new PageParame(pr);
		List<Object> list = new ArrayList<Object>();
		String hql = "";
		hql += " and deleteFlag = " + Constants.PLACEHOLDER
				+ " and usingStatus = " + Constants.PLACEHOLDER;
		list.add(Constants.DELETE_FALSE);
		list.add(Constants.STATUS_ENABLED);
		p.setHqlConditionExpression(hql);
		p.setValues(list.toArray());
		Long[] lists = academydao.getIDs(p);
		if (lists != null && lists.length != 0) {

			for (int i = 0; i < lists.length; i++) {
				Academy academy = academydao.findById(lists[i].intValue());
				if (academy != null) {
					academylist.add(academy);
				}
			}
		}
		return academylist;
	}

}
