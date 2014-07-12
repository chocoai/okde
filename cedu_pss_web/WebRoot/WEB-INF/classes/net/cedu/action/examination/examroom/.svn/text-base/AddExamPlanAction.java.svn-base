package net.cedu.action.examination.examroom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;

import net.cedu.biz.examination.ExamBatchBiz;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.entity.academy.Academy;

import net.cedu.entity.examination.ExamBatch;
import net.cedu.entity.examination.ExamPlan;


import org.springframework.beans.factory.annotation.Autowired;

public class AddExamPlanAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8965332007309933034L;

	@Autowired
	private ExamPlanBiz examplanbiz;
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合	
	
	public List<Academy> getAcademylist() {
		return academylist;
	}
	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}
	@Autowired
	private ExamBatchBiz exambatchbiz;
	private List<ExamBatch> exambatchlist=new ArrayList<ExamBatch>();
	
	public List<ExamBatch> getExambatchlist() {
		return exambatchlist;
	}
	public void setExambatchlist(List<ExamBatch> exambatchlist) {
		this.exambatchlist = exambatchlist;
	}
	
	
	
	
	private ExamPlan examplan=new ExamPlan();
	public ExamPlan getExamplan() {
		return examplan;
	}
	public void setExamplan(ExamPlan examplan) {
		this.examplan = examplan;
	}
	public String execute(){
		try {
			exambatchlist=exambatchbiz.findAllexambatch();
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
			examplan.setCreatorId(super.getUser().getUserId());
			examplan.setCreatedTime(new Date());
			examplan.setDeleteFlag(0);
			examplan.setStatus(0);
			examplan.setUpdaterId(super.getUser().getUserId());
			examplanbiz.createNew(examplan);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	

}
