package net.cedu.biz.teaching;

import java.util.List;

import net.cedu.entity.crm.Student;
import net.cedu.entity.teaching.Diploma;
import net.cedu.model.page.PageResult;

/**
 * 毕业证书管理业务逻辑
 * @author wangmingjie
 *
 */
public interface DiplomaBiz {
	  /**
     * 查询毕业证书数量
     * @param student
     * @param diploma
     * @param result
     * @return
     * @throws Exception
     */
	public int findDiplomaCountByConditions(Student student,PageResult<Student> result) throws Exception;
	/**
	 * 查询毕业证书列表集合
	 * @param student
	 * @param diploma
	 * @param pr
	 * @return
	 * @throws Exception
	 */
	public List<Student> findDiplomaListByConditions(Student student,PageResult<Student> pr) throws Exception;
	/*
	 * 查询所有毕业证书
	 * 
	 * 
	 */
	public List<Diploma> findAllDiplomas() throws Exception;
	/**
	 * 根据学生id查询毕业证书
	 * @param studentId
	 * @return
	 * @throws Exception
	 */
	public  Diploma findDiplomaByStudentId(int  studentId) throws Exception ;
	/*
	 * 更新毕业证书实体
	 * 
	 * 
	 */
	public void updateDiploma(Diploma diploma) throws Exception;
	/**
	 * 添加毕业证书
	 */
	
	public void addDiploma(Diploma diploma) throws Exception;
	/**
	 * 查看学生
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Student findStudentById(int id) throws Exception;
}
