package net.cedu.biz.crm;

import net.cedu.entity.crm.StudentSlave;

/**
 * 学生基础信息
 * @author wangmingjie
 *
 */
public interface StudentSlaveBiz {
	/**
	 * 根据学生id查询学生信息
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public StudentSlave  findByStudentId(int sid)throws Exception;
}
