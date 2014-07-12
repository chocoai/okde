package net.cedu.dao.enrollment.impl;

import java.sql.SQLException;
import java.util.List;

import net.cedu.common.Constants;
import net.cedu.dao.enrollment.AcademyBatchBranchDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.enrollment.AcademyBatchBranch;
import net.cedu.model.page.PageParame;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

/**
 *  院校授权学习中心(关系)  数据访问层实现
 * @author Sauntor
 *
 */
@Repository
public class AcademyBatchBranchDaoImpl extends BaseMDDaoImpl<AcademyBatchBranch> implements AcademyBatchBranchDao {
//	/**
//	 * 查询院校在指定批次<b>已授权</b>中心
//	 * @param academyId 院校ID
//	 * @param batchId 批次ID
//	 * @return
//	 * @throws Exception 
//	 */
//	public List<AcademyBatchBranch> findGrantedBranch(int academyId, int batchId) throws Exception
//	{
//		return super.getByProperty(" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER, academyId, batchId);
//	}

//	/**
//	 * 清除院校在指定招生批次的授权学习中心
//	 * 
//	 * @param academyId
//	 * @param batchId
//	 * @return
//	 * @throws Exception
//	 */
//	public int deleteByAcademyAndEnrollBatch(final int academyId, final int batchId) throws Exception
//	{
//		int rowCount = 0;
//		
//		PageParame param = new PageParame();
//		param.setPage(false);
//		param.setHqlConditionExpression(" and academyId="+Constants.PLACEHOLDER+" and batchId="+Constants.PLACEHOLDER);
//		
//		Long[] ids = super.getIDs(param);
//		if(ids!=null && ids.length>0)
//		{
//			try
//			{
//				for(rowCount=0; rowCount<ids.length; rowCount++)
//				{
//					super.deleteById(ids[rowCount].intValue());
//				}
//			}
//			catch(Exception e)
//			{
//				throw new RuntimeException("Exception occurred with id:"+ids[rowCount], e);
//			}
//		}
//		
//		return rowCount;
//	}
}
