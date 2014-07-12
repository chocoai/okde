package net.cedu.biz.examination;

import java.util.List;


import net.cedu.entity.examination.ClassRoom;

import net.cedu.model.page.PageResult;

public interface ClassRoomBiz {
	public boolean createNew(ClassRoom classroom) throws Exception ;
	 public List<ClassRoom> page(PageResult<ClassRoom> result)throws Exception;
	
	 public int findAllClassRoomCount(PageResult<ClassRoom> pr) throws Exception ;
	
		/**
		 * 多条件查询
		 * 
		 * */
	public List<ClassRoom> findByConditions(ClassRoom classroom, PageResult<ClassRoom> pr)
		throws Exception;
	public ClassRoom findClassroomById(int id)throws Exception;
	public Object updateClassroom(ClassRoom classroom)throws Exception;
		/**
		 * 多条件查询结果数量
		 * 
		 * */
	 public int findClassRoomPageCount(ClassRoom classroom, PageResult<ClassRoom> pr)
		  throws Exception;
		 /**
		  * 根据ID删除考场
		  * */
	public Object deleteClassRoomById(int id)throws Exception;
	public List<ClassRoom> findAllclassroom()throws Exception;
		 


}
