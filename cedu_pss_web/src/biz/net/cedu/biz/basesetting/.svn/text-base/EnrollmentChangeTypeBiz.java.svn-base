package net.cedu.biz.basesetting;

import java.io.Serializable;
import java.util.List;
import net.cedu.entity.basesetting.EnrollmentChangeType;

/**
 * 
 * @author HXJ
 */
public interface EnrollmentChangeTypeBiz {

	//增加学籍异动类别设置
	public Object addEnrollmentChangeType(EnrollmentChangeType enrollmentchangetype); 
	
	//修改学籍异动类别设置
	public EnrollmentChangeType modifyEnrollmentChangeType(EnrollmentChangeType enrollmentchangetype);
	
//	//按主键删除学籍异动类别设置(物理删除)
//	public EnrollmentChangeType deleteEnrollmentChangeTypeById(Serializable id);
//	
//	//按主键删除学籍异动类别设置(逻辑删除)
//	public int deleteEnrollmentChangeTypeByFlag(int id);
	
	//查询所有学籍异动类别设置
	public List<EnrollmentChangeType> findAllEnrollmentChangeTypes() throws Exception;
	
	//查询所有学籍异动类别设置(delete_flag=0)
	public List<EnrollmentChangeType> findAllEnrollmentChangeTypeByDeleteFlag() throws Exception;
	
	//按主键id查询学籍异动类别设置
	public EnrollmentChangeType findEnrollmentChangeTypeById(Serializable id);
}
