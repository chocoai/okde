package net.cedu.biz.finance;

import net.cedu.entity.finance.StudentAcademyRebate;

/**
 * 学生绑定院校返款标准关系表
 * 
 * @author lixiaojun
 * 
 */
public interface StudentAcademyRebateBiz 
{
	
	
	/**
	 * 添加学生绑定院校返款标准关系表
	 * @param StudentAcademyRebate
	 * @return
	 * @throws Exception
	 */
	public boolean addStudentAcademyRebate(StudentAcademyRebate studentAcademyRebate) throws Exception;
	
	
	/**
	 * 删除学生绑定院校返款标准关系表（物理删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteStudentAcademyRebateById(int id) throws Exception;
	
	
	/**
	 * 修改学生绑定院校返款标准关系表
	 * @param StudentAcademyRebate
	 * @return
	 * @throws Exception
	 */
	public boolean updateStudentAcademyRebate(StudentAcademyRebate studentAcademyRebate) throws Exception;
	
	
	/**
	 * 根据Id查找学生绑定院校返款标准关系表
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public StudentAcademyRebate findStudentAcademyRebateById(int id)throws Exception;
	
	
	/**
	 * 根据条件查找学生绑定院校返款标准关系表数量
	 * @param studentAcademyRebate
	 * @return
	 * @throws Exception
	 */
	public int findStudentAcademyRebateCountBy(StudentAcademyRebate studentAcademyRebate) throws Exception;
	
	
	/**
	 * 根据条件查找学生绑定院校返款标准关系表实体
	 * @param studentAcademyRebate
	 * @return
	 * @throws Exception
	 */
	public StudentAcademyRebate findStudentAcademyRebateListBy(StudentAcademyRebate studentAcademyRebate) throws Exception;
	
	
}
