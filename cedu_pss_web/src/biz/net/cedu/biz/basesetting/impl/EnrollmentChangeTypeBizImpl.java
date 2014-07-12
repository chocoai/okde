package net.cedu.biz.basesetting.impl;

import java.io.Serializable;
import java.util.List;

import net.cedu.biz.basesetting.EnrollmentChangeTypeBiz;
import net.cedu.common.Constants;
import net.cedu.dao.basesetting.EnrollmentChangeTypeDao;
import net.cedu.entity.basesetting.EnrollmentChangeType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 学籍异动类别设置
 * @author HXJ
 */
@Service
public class EnrollmentChangeTypeBizImpl implements EnrollmentChangeTypeBiz{
	
	@Autowired
	private EnrollmentChangeTypeDao enrollmentChangeTypeDao;
	//增加学籍异动类别设置
	
	public Object addEnrollmentChangeType(EnrollmentChangeType enrollmentchangetype){
		return enrollmentChangeTypeDao.save(enrollmentchangetype);
	}
	
	//修改学籍异动类别设置
	public EnrollmentChangeType modifyEnrollmentChangeType(EnrollmentChangeType enrollmentchangetype){
		return enrollmentChangeTypeDao.update(enrollmentchangetype);
	}
	
//	//按主键删除学籍异动类别设置(物理删除)
//	public EnrollmentChangeType deleteEnrollmentChangeTypeById(Serializable id){
//		return enrollmentChangeTypeDao.deleteById(id);
//	}
//	
////	//按主键删除学籍异动类别设置(逻辑删除)
////	public int deleteEnrollmentChangeTypeByFlag(int id){
////		return enrollmentChangeTypeDao.deleteByFlag(id);
////	}
	
	//查询所有学籍异动类别设置
	public List<EnrollmentChangeType> findAllEnrollmentChangeTypes() throws Exception{
		return enrollmentChangeTypeDao.findAll();
	}
	
	//查询所有学籍异动类别设置(delete_flag=0)
	public List<EnrollmentChangeType> findAllEnrollmentChangeTypeByDeleteFlag() throws Exception{
		return enrollmentChangeTypeDao.getByProperty("deleteFlag", Constants.DELETE_FALSE);
	}
	
	//按主键id查询学籍异动类别设置
	public EnrollmentChangeType findEnrollmentChangeTypeById(Serializable id){
		return enrollmentChangeTypeDao.findById(id);
	}
}
