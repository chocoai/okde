package net.cedu.biz.examination;

import java.util.List;


import net.cedu.entity.examination.ExamSchedule;
import net.cedu.model.page.PageResult;

public interface ExamScheduleBiz {
	public List<ExamSchedule> page(PageResult<ExamSchedule> result)throws Exception;

	/**
	 * 多条件查询
	 * 
	 * */
	public List<ExamSchedule> findByConditions(ExamSchedule examschedule, PageResult<ExamSchedule> pr)
	throws Exception;
	
	 public Object updateExamSchedule(ExamSchedule examschedule)throws Exception;
	/**
	 * 多条件查询结果数量
	 * 
	 * */
	 public int findExamSchedulePageCount(ExamSchedule examschedule, PageResult<ExamSchedule> pr)
	  throws Exception;
	 /**
	  * 根据ID删除考场
	  * */
	 public Object deleteExamScheduleById(int id)throws Exception;
	 
	 public boolean createNew(ExamSchedule examschedule) throws Exception ;
	 
	 
	 public ExamSchedule findByExamScheduleId(int id)throws Exception;
	 public ExamSchedule findExamscheduleByExamplanId(int id)throws Exception;

}
