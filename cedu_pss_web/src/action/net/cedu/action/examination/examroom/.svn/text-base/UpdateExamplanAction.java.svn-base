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
import net.cedu.entity.examination.ExamRoom;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class UpdateExamplanAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ExamPlanBiz examplanbiz;
	private ExamPlan examplan;
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private List<Academy> academylist=new ArrayList<Academy>();//院校集合
	private int id;
	
	@Autowired
	private ExamBatchBiz exambatchbiz;
	private List<ExamBatch> exambatchlist=new ArrayList<ExamBatch>();
	
	
	public List<ExamBatch> getExambatchlist() {
		return exambatchlist;
	}




	public void setExambatchlist(List<ExamBatch> exambatchlist) {
		this.exambatchlist = exambatchlist;
	}




	public ExamPlan getExamplan() {
		return examplan;
	}




	public void setExamplan(ExamPlan examplan) {
		this.examplan = examplan;
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}




	@Action(results = {
			@Result(name = "input", location = "update_examplan.jsp"),
			@Result(name = "success", type = "redirect", location = "index_exam_plan") })
	public String excute() throws Exception {

		if (super.isGetRequest()) {
			// 获取该详细数据
			examplan=examplanbiz.findExamplanByExamplanId(id);
			exambatchlist=exambatchbiz.findAllexambatch();
			academylist=this.academyBiz.findAllAcademyByFlagFalse();
			return INPUT;
		}
		ExamPlan er=new ExamPlan();
		try {
			// 获取该原始详细数据
			er=examplanbiz.findExamplanByExamplanId(examplan.getId());
			if (examplan != null) {
				er.setAcademyId(examplan.getAcademyId());
				er.setBatchId(examplan.getBatchId());
				er.setBranchId(examplan.getBranchId());
				er.setExamBatchId(examplan.getExamBatchId());
				er.setPlan(examplan.getPlan());
				er.setPlanCount(examplan.getPlanCount());
				er.setExamEndTime(examplan.getExamEndTime());
				er.setExamBeginTime(examplan.getExamBeginTime());
				er.setExamDate(examplan.getExamDate());
			   er.setUpdaterId(super.getUser().getUserId());
			  er.setUpdatedTime(new Date());
			id=examplan.getId();
			// 执行修改
			examplanbiz.updateExamPlan(er);}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}


	

	public List<Academy> getAcademylist() {
		return academylist;
	}


	public void setAcademylist(List<Academy> academylist) {
		this.academylist = academylist;
	}
	
	}


