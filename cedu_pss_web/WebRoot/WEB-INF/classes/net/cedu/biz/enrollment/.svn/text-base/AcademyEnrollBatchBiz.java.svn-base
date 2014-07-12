package net.cedu.biz.enrollment;

import java.util.List;

import net.cedu.entity.basesetting.GlobalEnrollBatch;
import net.cedu.entity.enrollment.AcademyEnrollBatch;

/**
 * 院校招生批次 接口
 * @author 
 *
 */
public interface AcademyEnrollBatchBiz {
		
	/**
	 * 根据ID查找院校招生批次
	 * @param id
	 * @return
	 */
	public AcademyEnrollBatch findAcademyEnrollBatchById(int id) throws Exception;
	
	/**
	 * 添加院校招生批次
	 * @param 
	 */
	public boolean addAcademyEnrollBatch(AcademyEnrollBatch  academyEnrollBatch) throws Exception;
	
//	/**
//	 * 删除院校招生批次(物理删除)
//	 * @param 
//	 */
//	public boolean deleteAcademyEnrollBatchById(int id) throws Exception;
//	/**
//	 * 删除院校招生批次(逻辑删除)
//	 * @param 
//	 */
//	public boolean deleteAcademyEnrollBatchByFlag(int id) throws Exception;
	
	/**
	 * 修改院校招生批次
	 * @param 
	 */
	public boolean modifyAcademyEnrollBatch(AcademyEnrollBatch academyEnrollBatch) throws Exception;
	
	/**
	 * 查询院校所有招生批次
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findByAcademyId(int academyId) throws Exception;

	/**
	 * 查询院校所有招生批次(deleteFlag=0)
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findByAcademyIdAndFlag(int academyId) throws Exception;
	/**
	 * 查询院校所有启用的招生批次(deleteFlag=0)
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findByAcademyIdAndFlagAndIsEnable(int academyId,int isEnable) throws Exception;
	
	//根据获取的院校ID 循环获取其对应的层次(院校批次),然后循环赋给List<AcademyEnrollBatch>
	public List<AcademyEnrollBatch> addAcademyLevelforAcademyEnrollBatch(List<AcademyEnrollBatch> academyEnrollBatchList) throws Exception;
	
	/**
	 * 查询所非结束状态的招生批次
	 * @param academyId
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findBatchNotInFinishedByAcademyId(int academyId) throws Exception;
	
	/**
	 * 根据院校ID查询当前选中院校招生批次以外的院校招生批次(含结束状态)
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findOtherBatchByAcademyId(int academyid,int batchid) throws Exception;
	
	/**
	 * 删除(读取配置文件)
	 * @param id
	 * @return
	 */
	public AcademyEnrollBatch deleteConfigAcademyEnrollBatch(int id);
	
	/**
	 *  根据全局招生批次id查询其对应的全部院校招生批次
	 * @param batchid
	 * @return
	 * @throws Exception
	 */
	public List<AcademyEnrollBatch> findAllAcademyBatchbyGlobalBatchId(int batchid) throws Exception;
	
	/**
	 * 根据全局招生批次id和院校id查询院校招生批次(包含院校招生批次状态为 结束状态的)
	 * @param batchid
	 * @param academyid
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollBatch findAcademyBatchByGlobalBatchIdAndAcademyId(int batchid,int academyid)throws Exception;
	
	/**
	 * 根据全局招生批次id和院校id查询院校招生批次(过滤掉 院校招生批次为结束状态的)
	 * @param batchid
	 * @param academyid
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollBatch findNonFinishedAcademyBatchByGlobalBatchIdAndAcademyId(int batchid,int academyid)throws Exception;
	
	/**
	 * 根据全局招生批次id和院校id查询院校招生批次( 院校招生批次为启用状态的)
	 * @param batchid
	 * @param academyid
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollBatch findQiYongAcademyBatchByGlobalBatchIdAndAcademyId(int batchid,int academyid)throws Exception;
	
	/**
	 * 查询招生批次
	 * @param academyid
	 * @param enrollmentName
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollBatch findAcademyEnrollBatchByEnrollmentName(int academyid,String enrollmentName)throws Exception;
	/**
	 * 查询学籍批次
	 * @param academyid
	 * @param registerName
	 * @return
	 * @throws Exception
	 */
	public AcademyEnrollBatch findAcademyEnrollBatchByRegisterName(int academyid,String registerName)throws Exception;
	
	/**
	 * 
	 * @功能：通过院校招生批次ID，院校ID查询全局批次ID
	 *
	 * @作者： 韩晓佳
	 * @作成时间：2011-11-29 下午03:49:17
	 *
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 *
	 * @param academyEnrollBatchId 院校招生批次
	 * @param academyId	院校id
	 * @return	全局招生批次实体
	 * @throws Exception
	 */
	public GlobalEnrollBatch findGlobalBatchIdByAcademyEnrollBatchIdAndAcademyId(int academyEnrollBatchId,int academyId)throws Exception;
	
	
	/**
	 * 根据多个院校批次查询全局批次(多)
	 * @param enrollBatchIds
	 * @return
	 * @throws Exception
	 */
	public List<GlobalEnrollBatch> findGlobalBatchIdByEnrollBatchIds(String enrollBatchIds)throws Exception;

	/**
	 * 根据全局批次ID查找院校招生批次
	 * @param gid 全局批次Id
	 * @return
	 */
	public List<AcademyEnrollBatch>  findAcademyEnrollBatchByGId(int gid) throws Exception;
}
