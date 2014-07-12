package net.cedu.biz.finance;

import java.util.List;

import net.cedu.entity.finance.AcademyBatchRebateCount;


/**
 * 院校返款时记录每次返款时每个批次的返款总人数
 * 
 * @author lixiaojun
 * 
 */
public interface AcademyBatchRebateCountBiz 
{
	/**
	 * 添加院校返款每个批次的返款总人数
	 * @param academyBatchRebateCount
	 * @return
	 * @throws Exception
	 */
	public boolean addAcademyBatchRebateCount(AcademyBatchRebateCount academyBatchRebateCount) throws Exception;
	
	
	/**
	 * 删除院校返款每个批次的返款总人数（物理删除）
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public boolean deleteAcademyBatchRebateCountById(int id) throws Exception;
	
	
	/**
	 * 修改院校返款每个批次的返款总人数
	 * @param academyBatchRebateCount
	 * @return
	 * @throws Exception
	 */
	public boolean updateAcademyBatchRebateCount(AcademyBatchRebateCount academyBatchRebateCount) throws Exception;
	
	
	/**
	 * 根据Id查找院校返款每个批次的返款总人数
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public AcademyBatchRebateCount findAcademyBatchRebateCountById(int id)throws Exception;
	
	
	/**
	 * 根据条件查找院校返款每个批次的返款总人数数量
	 * @param academyBatchRebateCount
	 * @return
	 * @throws Exception
	 */
	public int findAcademyBatchRebateCountCountBy(AcademyBatchRebateCount academyBatchRebateCount) throws Exception;
	
	
	/**
	 * 根据条件查找院校返款每个批次的返款总人数
	 * @param academyBatchRebateCount
	 * @return
	 * @throws Exception
	 */
	public AcademyBatchRebateCount findAcademyBatchRebateCountListBy(AcademyBatchRebateCount academyBatchRebateCount) throws Exception;
	
	/**
	 * 通过院校返款单Id查询对应院校返款每个批次的返款总人数集合
	 * @param payAcademyCeduId 院校返款单ID
	 * @return
	 * @throws Exception
	 */
	public List<AcademyBatchRebateCount> findAcademyBatchRebateCountListByPayAcademyCeduId(int payAcademyCeduId) throws Exception;
	
}
