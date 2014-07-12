package net.cedu.biz.examination.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.examination.ExamAreaBiz;
import net.cedu.biz.examination.ExamBatchBiz;
import net.cedu.biz.examination.ExamPlanBiz;
import net.cedu.biz.examination.ExamScheduleBiz;
import net.cedu.common.Constants;
import net.cedu.dao.examination.ExamScheduleDao;
import net.cedu.entity.examination.ExamArea;
import net.cedu.entity.examination.ExamBatch;
import net.cedu.entity.examination.ExamPlan;
import net.cedu.entity.examination.ExamSchedule;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ExamScheduleBizImpl implements ExamScheduleBiz {
	@Autowired
	private ExamScheduleDao examscheduledao;
	@Autowired
	private ExamBatchBiz exambatchbiz;
	@Autowired
	private ExamAreaBiz examareaabiz;
	@Autowired
	private ExamPlanBiz examplanbiz;
	
	

	public boolean createNew(ExamSchedule examschedule) throws Exception {
		// TODO Auto-generated method stub
		
		examscheduledao.save(examschedule);
			return true;
	}

	public Object deleteExamScheduleById(int id) throws Exception {
		// TODO Auto-generated method stub
		return examscheduledao.deleteById(id);
	}

	public List<ExamSchedule> findByConditions(ExamSchedule examschedule,
			PageResult<ExamSchedule> pr) throws Exception {
		List<ExamSchedule> examschedules =null;
			PageParame p = new PageParame(pr);
			List<Object> list=new ArrayList<Object>();
			String hql="";
			
			if (examschedule!= null) 
			{
				if(examschedule.getExamBatchId()>0)
				{
					hql+="and examBatchId = "+Constants.PLACEHOLDER;
					list.add(examschedule.getExamBatchId());
				}
				if(examschedule.getExamAreaId()>0)
				{
					hql+="and examAreaId = "+Constants.PLACEHOLDER;
					list.add(examschedule.getExamAreaId());
				}
					p.setHqlConditionExpression(hql);
					p.setValues(list.toArray());
				}

				Long[] ScheudleIds = examscheduledao.getIDs(p);
				if (ScheudleIds != null && ScheudleIds.length != 0) {
					 examschedules = new ArrayList<ExamSchedule>();
					 ExamSchedule d=null;
				    for(Long id : ScheudleIds){
				    	if (null != (d = examscheduledao.findById(Integer.parseInt(id
								.toString())))) {
				    		
							ExamBatch exambatch = exambatchbiz.findByExamBatchId(d.getExamBatchId());
							if (exambatch != null) {
								d.setExamBatchname(exambatch.getCode());
							}

							ExamArea examarea = examareaabiz.findByExamAreaId(d.getExamAreaId());
							if (examarea != null){
								d.setExamAreaname(examarea.getName());
						
							}
//							if(d.getExamBatchId()!=null && d.getExamBatchId()>0){
//							ExamPlan examplan=examplanbiz.findExamplanBybatchId(d.getExamBatchId());
//							if(examplan!=null){
//								d.setPlan(examplan.getPlan());
//							}
							}
							examschedules.add(d);
						}
				    	
				    }
				return examschedules;
				}
	public ExamSchedule findByExamScheduleId(int id) throws Exception {
		// TODO Auto-generated method stub
		return examscheduledao.findById(id);
	}
	public ExamSchedule findExamscheduleByExamplanId(int id)throws Exception{
		ExamSchedule examschedule=findByExamScheduleId(id);
		if(examschedule.getExamAreaId()!=null && examschedule.getExamAreaId()>0){
		ExamArea examarea = examareaabiz.findByExamAreaId(examschedule.getExamAreaId());
		if(examarea!=null){
			examschedule.setExamAreaname(examarea.getName());
		}
		}
		if(examschedule.getExamBatchId()!=null && examschedule.getExamBatchId()>0){
		ExamBatch exambatch = exambatchbiz.findByExamBatchId(examschedule.getExamBatchId());
		if(exambatch!=null){
			examschedule.setExamBatchname(exambatch.getCode());
		}
		}
		return examschedule;
	}

	

	public int findExamSchedulePageCount(ExamSchedule examschedule,
		PageResult<ExamSchedule> pr) throws Exception {
		PageParame p = new PageParame(pr);
		List<Object> list=new ArrayList<Object>();
		String hql="";
			if (examschedule!= null) 
			{
				if(examschedule.getExamBatchId()>0)
				{
					hql+="and examBatchId = "+Constants.PLACEHOLDER;
					list.add(examschedule.getExamBatchId());
				}
				if(examschedule.getExamAreaId()>0)
				{
					hql+="and examAreaId = "+Constants.PLACEHOLDER;
					list.add(examschedule.getExamAreaId());
				}
				
				p.setHqlConditionExpression(hql);
				p.setValues(list.toArray());
				
			}
			return examscheduledao.getCounts(p);
    }
	

	public List<ExamSchedule> page(PageResult<ExamSchedule> result)
			throws Exception {
		List<ExamSchedule> examschedulelist =new ArrayList<ExamSchedule>();

		PageParame pp = new PageParame(result);
		Long[]list=examscheduledao.getIDs(pp);
		
		for(int i=0;i<list.length;i++)
		{
			examschedulelist.add(examscheduledao.findById(list[i].intValue()));
		}
		return examschedulelist;
	}

	public Object updateExamSchedule(ExamSchedule examschedule) throws Exception {
		// TODO Auto-generated method stub
		return examscheduledao.update(examschedule);
	}

}
