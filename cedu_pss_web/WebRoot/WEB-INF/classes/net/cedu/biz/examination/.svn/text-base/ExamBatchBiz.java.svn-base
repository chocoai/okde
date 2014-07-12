package net.cedu.biz.examination;

import java.util.List;

import net.cedu.entity.examination.ExamBatch;
import net.cedu.model.page.PageResult;

public interface ExamBatchBiz {
	public List<ExamBatch> page(PageResult<ExamBatch> result)throws Exception;
	public Object deleteExamBatchById(int id)throws Exception;
	public List<ExamBatch> findByConditions(ExamBatch exambatch, PageResult<ExamBatch> pr)
	throws Exception;
	 public int findExamRoomPageCount(ExamBatch exambatch, PageResult<ExamBatch> pr)
	  throws Exception;
	 public boolean createNew(ExamBatch exambatch) throws Exception ;
	 public ExamBatch findByExambatchName(String name)throws Exception;
	 
	 public ExamBatch findByExamBatchId(int id)throws Exception;
	 public List<ExamBatch> findAllexambatch()throws Exception;
}
