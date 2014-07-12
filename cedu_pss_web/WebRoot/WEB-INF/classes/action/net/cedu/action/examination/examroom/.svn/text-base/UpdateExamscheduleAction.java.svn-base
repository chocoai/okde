package net.cedu.action.examination.examroom;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.examination.ExamAreaBiz;
import net.cedu.biz.examination.ExamBatchBiz;
import net.cedu.biz.examination.ExamScheduleBiz;
import net.cedu.entity.examination.ExamArea;
import net.cedu.entity.examination.ExamBatch;
import net.cedu.entity.examination.ExamRoom;
import net.cedu.entity.examination.ExamSchedule;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

public class UpdateExamscheduleAction extends BaseAction  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3802384791077277450L;
	@Autowired
	private ExamScheduleBiz examschedulebiz;
	private ExamSchedule examschedule;
	@Autowired
	private ExamBatchBiz exambatchbiz;
	private List<ExamBatch> exambatchlist=new ArrayList<ExamBatch>();
	@Autowired
	private ExamAreaBiz examareabiz;
	private List<ExamArea> examarealist=new ArrayList<ExamArea>();
	public List<ExamBatch> getExambatchlist() {
		return exambatchlist;
	}
	public void setExambatchlist(List<ExamBatch> exambatchlist) {
		this.exambatchlist = exambatchlist;
	}
	public List<ExamArea> getExamarealist() {
		return examarealist;
	}
	public void setExamarealist(List<ExamArea> examarealist) {
		this.examarealist = examarealist;
	}
	private int id;
	
	@Action(results = {
			@Result(name = "input", location = "update_examschedule.jsp"),
			@Result(name = "success", type = "redirect", location = "index_exam_schedule") })
	public String excute() throws Exception {

		if (super.isGetRequest()) {
			// 获取该详细数据
			examschedule = examschedulebiz.findByExamScheduleId(id);
			exambatchlist=exambatchbiz.findAllexambatch();
			examarealist=examareabiz.findAllexamarea();
			return INPUT;
		}
		ExamSchedule er=new ExamSchedule();
		try {
			// 获取该原始详细数据
			er = examschedulebiz.findByExamScheduleId(examschedule.getId());
			if (examschedule != null) {
				er.setActualCost(examschedule.getActualCost());
				er.setExamAreaId(examschedule.getExamAreaId());
				er.setPlanedCost(examschedule.getPlanedCost());
				er.setInspectorMark(examschedule.getInspectorMark());
			   er.setUpdaterId(super.getUser().getUserId());
			  er.setUpdatedTime(new Date());
			id=examschedule.getId();
			// 执行修改
			examschedulebiz.updateExamSchedule(er);}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return SUCCESS;
	}
	public ExamSchedule getExamschedule() {
		return examschedule;
	}

	public void setExamschedule(ExamSchedule examschedule) {
		this.examschedule = examschedule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public ExamSchedule getModel(){
		return examschedule;
	}
	

}
