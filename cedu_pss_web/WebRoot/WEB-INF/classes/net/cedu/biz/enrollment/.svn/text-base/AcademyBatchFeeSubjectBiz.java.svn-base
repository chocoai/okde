package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.enrollment.AcademyBatchFeeSubject;

/**
 * 院校费用科目(招生阶段) 业务接口
 * @author Sauntor
 *
 */
public interface AcademyBatchFeeSubjectBiz {
	/**
	 * 批量添加 院校费用科目
	 * @param list
	 * @throws Exception
	 */
	public void addList(List<AcademyBatchFeeSubject> list) throws Exception;
	
	/**
	 * 查找院校所有费用科目
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByAcademyId(int academyId) throws Exception;
	
	/**
	 * 根据院校ID、招生批次查询所有费用科目
	 * @param academyId
	 * @param batchId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByAcademyAndBatch(int academyId, int batchId) throws Exception;
	
	/**
	 * 根据院校ID、招生批次查询所有费用科目
	 * @param academyId
	 * @param batchId
	 * @param stage 阶段（招生／非招生）<code>Constants.BATCH_TYPE_ENROLL/Constants.BATCH_TYPE_NON_ENROLL</code>
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByAcademyAndBatchAndStage(int academyId, int batchId, int stage) throws Exception;
	
	/**
	 * 查询指定院校招生批次下所有可用费用科目
	 * @param batchId
	 * @param stage 阶段（招生／非招生）<code>Constants.BATCH_TYPE_ENROLL/Constants.BATCH_TYPE_NON_ENROLL</code>
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchFeeSubject> findByBatchAndStage(int batchId, int stage) throws Exception;
	
	/**
	 * 费用科目是否存在指定批次中
	 * 
	 * @param batchId
	 * @param feeSubjectId
	 * @return
	 * @throws Exception
	 */
	public boolean isFeeSubjectInBatch(int batchId, int feeSubjectId) throws Exception;
	
	/**
	 * 批量列表删除
	 * 
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int deleteList(List<AcademyBatchFeeSubject> list) throws Exception;
}
