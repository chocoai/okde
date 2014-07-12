package net.cedu.biz.crm.impl;


import net.cedu.biz.crm.StudentSlaveBiz;
import net.cedu.dao.crm.StudentSlaveDao;
import net.cedu.entity.crm.StudentSlave;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学生基础信息
 * @author wangmingjie
 *
 */
@Service
public class StudentSlaveBizImpl implements  StudentSlaveBiz {
	@Autowired
	private StudentSlaveDao studentSlaveDao;
	/**
	 * 根据学生id查询学生信息
	 * @param sid
	 * @return
	 * @throws Exception
	 */
	public StudentSlave  findByStudentId(int sid)throws Exception
	{
		return studentSlaveDao.findById(sid);
	}
}
