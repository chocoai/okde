package net.cedu.biz.examination;

import java.util.List;

import net.cedu.entity.academy.Academy;
import net.cedu.entity.examination.ExamPlan;
import net.cedu.model.page.PageResult;

public interface ExamPlanBiz {
	public List<ExamPlan> page(PageResult<ExamPlan> result)throws Exception;
	public Object deleteExamPlanById(int id)throws Exception;
	public List<ExamPlan> findByConditions(ExamPlan examplan, PageResult<ExamPlan> pr)
	throws Exception;
	public List<ExamPlan> findByConditions(int id,int batchId,int branchId,int levelId,int majorId, PageResult<ExamPlan> pr)
	throws Exception;
	public int findExamPlanPageCount(ExamPlan examplan, PageResult<ExamPlan> pr)
	throws Exception;
	public int findExamPlanPageCount(int id,int batchId,int branchId,int levelId,int majorId, PageResult<ExamPlan> pr)
	throws Exception;
	public boolean createNew(ExamPlan examplan) throws Exception ; 
	public ExamPlan findByExamPlanId(int id)throws Exception;
	public List<Academy> findAllAcademyConditions(PageResult<Academy> pr)throws Exception;
	public int findAllAcademyCount(PageResult<Academy> pr)throws Exception;
	public Object updateExamPlan(ExamPlan examplan)throws Exception;
	public ExamPlan findExamplanByExamplanId(int id)throws Exception;
	//public ExamPlan findExamplanBybatchId(int id)throws Exception;
}
