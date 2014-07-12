package net.cedu.biz.basesetting;

import java.io.Serializable;
import java.util.List;

import net.cedu.entity.basesetting.FeeSubject;

/**
 * 费用科目
 * @author HXJ
 *
 */
public interface FeeSubjectBiz {
	
	/**
	 * 查询所有费用科目列表
	 * @return
	 * @throws Exception
	 */
	public List<FeeSubject> findAllFeeSubjects() throws Exception;
	
	/**
	 * 查询所有费用科目列表(delete_flag=0)
	 * @return
	 * @throws Exception
	 */
	public List<FeeSubject> findAllFeeSubjectByDeleteFlag() throws Exception;
	
	/**
	 * 按主键id查询 FeeSubject
	 * @param id
	 * @return
	 */
	public FeeSubject findFeeSubjectById(Serializable id);
	
	/**
	 * 添加费用科目
	 * @param feeSubject
	 * @return
	 * @throws Exception
	 */
	public boolean addFeeSubject(FeeSubject feeSubject)throws Exception; 
	
	//修改费用科目
	public boolean modifyFeeSubject(FeeSubject feeSubject) throws Exception;
	
//	//按主键删除(物理删除)
//	public FeeSubject deleteFeeSubjectById(Serializable id);
//	
//	//按主键删除(逻辑删除)
//	public int deleteFeeSubjectByFlag(int id);
	
	/**
	 * 按批次类型
	 * @param batchtype
	 * @return
	 */
	public List<FeeSubject> findAllFeeSubjectBybatchType(int batchtype);
	
	/**
	 * 	删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public FeeSubject deleteConfigFeeSubject(int id);
	
	/**
	 * 查询所有缴费科目列表(delete_flag=0，过滤掉报名费和测试费)
	 * @return
	 * @throws Exception
	 */
	public List<FeeSubject> findSchoolFeeSubjectByDeleteFlag() throws Exception;
}
