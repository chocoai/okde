package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.AcademyRebateAddRecords;

/**
 * 院校返款追加记录
 * 
 * @author lixiaojun
 * 
 */
public interface AcademyRebateAddRecordsBiz 
{
	
	/**
	 * 添加院校返款追加记录
	 * @param academyRebateAddRecords
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyRebateAddRecords(AcademyRebateAddRecords academyRebateAddRecords) throws Exception;
	
	
	/**
	 * 删除院校返款追加记录（物理删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademyRebateAddRecordsById(int id) throws Exception;
	
	
	/**
	 * 修改院校返款追加记录
	 * @param academyRebateAddRecords
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademyRebateAddRecords(AcademyRebateAddRecords academyRebateAddRecords) throws Exception;
	
	
	/**
	 * 根据Id查找院校返款追加记录
	 * @return
	 * @throws Exception
	 */
	public AcademyRebateAddRecords findAcademyRebateAddRecordsById(int id)throws Exception;
	
	
	/**
	 * 根据条件查找院校返款追加记录
	 * @param academyRebateAddRecords
	 * @return
	 * @throws Exception
	 */
	public int findAcademyRebateAddRecordsCountBy(AcademyRebateAddRecords academyRebateAddRecords) throws Exception;
	
	/**
	 * 通过院校返款单Id查询对应院校返款追加记录集合
	 * @param payAcademyCeduId 院校返款单Id
	 * @return
	 * @throws Exception
	 */
	public List<AcademyRebateAddRecords> findAcademyRebateAddRecordsListByPayAcademyCeduId(int payAcademyCeduId) throws Exception;
	
}
