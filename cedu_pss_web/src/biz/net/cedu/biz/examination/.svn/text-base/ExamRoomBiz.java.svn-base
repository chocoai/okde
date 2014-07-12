package net.cedu.biz.examination;

import java.util.List;

import net.cedu.entity.examination.ExamRoom;
import net.cedu.model.page.PageResult;

/**
 * 考场业务层接口
 * @author panyuheng
 * */
public interface ExamRoomBiz {
	
	public List<ExamRoom> page(PageResult<ExamRoom> result)throws Exception;

	/**
	 * 多条件查询
	 * 
	 * */
	public List<ExamRoom> findByConditions(ExamRoom examroom, PageResult<ExamRoom> pr)
	throws Exception;
	
	 public Object updateExamroom(ExamRoom examroom)throws Exception;
	/**
	 * 多条件查询结果数量
	 * 
	 * */
	 public int findExamRoomPageCount(ExamRoom examroom, PageResult<ExamRoom> pr)
	  throws Exception;
	 /**
	  * 根据ID删除考场
	  * */
	 public Object deleteExamRoomById(int id)throws Exception;
	 
	 public boolean createNew(ExamRoom examroom) throws Exception ;
	 
	 
	 public ExamRoom findByExamroomId(int id)throws Exception;
	 public List<ExamRoom> findAllexamroom()throws Exception;
}


