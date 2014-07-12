package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.AcademyRegisterFeeSubject;

/**
 * 院校费用科目(学籍阶段) 业务接口
 * @author Sauntor
 *
 */
public interface AcademyRegisterFeeSubjectBiz {
	/**
	 * 批量添加 院校费用科目
	 * @param batch
	 * @throws Exception
	 */
	public void addList(List<AcademyRegisterFeeSubject> batch) throws Exception;
	
	/**
	 * 查找院校所有费用科目
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRegisterFeeSubject> findByAcademyId(int academyId) throws Exception;
	
	/**
	 * 根据院校ID、学籍批次查询所有费用科目
	 * @param academyId
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRegisterFeeSubject> findByAcademyAndBatch(int academyId, int batchId) throws Exception;
	
	/**
	 * 批量列表删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteList(List<AcademyRegisterFeeSubject> list) throws Exception;
}
