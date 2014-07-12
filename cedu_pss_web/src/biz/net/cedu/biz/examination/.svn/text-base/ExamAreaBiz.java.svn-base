package net.cedu.biz.examination;

import java.util.List;

import net.cedu.entity.examination.ExamArea;
import net.cedu.model.page.PageResult;

public interface ExamAreaBiz {
	 public boolean createNew(ExamArea examarea) throws Exception ;
	 public List<ExamArea> page(PageResult<ExamArea> result)throws Exception;
	 public ExamArea findByName(String name) throws Exception;
		/**
		 * 多条件查询
		 * 
		 * */
	public List<ExamArea> findByConditions(ExamArea examarea, PageResult<ExamArea> pr)
		throws Exception;
		
	public Object updateExamArea(ExamArea examarea)throws Exception;
		/**
		 * 多条件查询结果数量
		 * 
		 * */
	 public int findExamAreaPageCount(ExamArea examarea, PageResult<ExamArea> pr)
		  throws Exception;
		 /**
		  * 根据ID删除考场
		  * */
	public Object deleteExamAreaById(int id)throws Exception;
	 public ExamArea findByExamAreaId(int id)throws Exception;
	 public List<ExamArea> findAllexamarea()throws Exception;
		 

}
