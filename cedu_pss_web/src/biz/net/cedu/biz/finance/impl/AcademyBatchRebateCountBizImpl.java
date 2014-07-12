package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.finance.AcademyBatchRebateCountBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.AcademyBatchRebateCountDao;
import net.cedu.entity.finance.AcademyBatchRebateCount;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返款时记录每次返款时每个批次的返款总人数
 * 
 * @author lixiaojun
 * 
 */
@Service
public class AcademyBatchRebateCountBizImpl implements AcademyBatchRebateCountBiz
{
	@Autowired
	private AcademyBatchRebateCountDao academyBatchRebateCountDao;//

	/*
	 * 添加院校返款每个批次的返款总人数
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#addAcademyBatchRebateCount(net.cedu.entity.finance.AcademyBatchRebateCount)
	 */
	public boolean addAcademyBatchRebateCount(AcademyBatchRebateCount academyBatchRebateCount) throws Exception
	{
		if (academyBatchRebateCount != null)
		{
			Object object = academyBatchRebateCountDao.save(academyBatchRebateCount);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除院校返款每个批次的返款总人数（物理删除）
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#deleteAcademyBatchRebateCountById(int)
	 */
	public boolean deleteAcademyBatchRebateCountById(int id) throws Exception 
	{
		if (id != 0) 
		{
			Object object = academyBatchRebateCountDao.deleteById(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * 修改院校返款每个批次的返款总人数
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#updateAcademyBatchRebateCount(net.cedu.entity.finance.AcademyBatchRebateCount)
	 */
	public boolean updateAcademyBatchRebateCount(AcademyBatchRebateCount academyBatchRebateCount) throws Exception
	{
		if (academyBatchRebateCount != null) 
		{
			Object object = academyBatchRebateCountDao.update(academyBatchRebateCount);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 根据Id查找院校返款每个批次的返款总人数
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#findAcademyBatchRebateCountById(int)
	 */
	public AcademyBatchRebateCount findAcademyBatchRebateCountById(int id) throws Exception
	{
		return this.academyBatchRebateCountDao.findById(id);
	}

	/*
	 * 根据条件查找院校返款每个批次的返款总人数数量
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#findAcademyBatchRebateCountCountBy(net.cedu.entity.finance.AcademyBatchRebateCount)
	 */
	public int findAcademyBatchRebateCountCountBy(AcademyBatchRebateCount academyBatchRebateCount) throws Exception 
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academyBatchRebateCount!= null)
		{			
			//院校
			if(academyBatchRebateCount.getAcademyId()>0)
			{
				hqlparam += " and academyId ="+ Constants.PLACEHOLDER;
				list.add(academyBatchRebateCount.getAcademyId());
			}
			//招生批次
			if(academyBatchRebateCount.getBatchId()>0)
			{
				hqlparam += " and batchId ="+ Constants.PLACEHOLDER;
				list.add(academyBatchRebateCount.getBatchId());
			}		
			//费用科目
			if(academyBatchRebateCount.getFeeSubjectId()>0)
			{
				hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
				list.add(academyBatchRebateCount.getFeeSubjectId());
			}
		}
		else
		{
			return 0;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		return academyBatchRebateCountDao.getCounts(p);
	}

	/*
	 * 根据条件查找院校返款每个批次的返款总人数
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#findAcademyBatchRebateCountListBy(net.cedu.entity.finance.AcademyBatchRebateCount)
	 */
	public AcademyBatchRebateCount findAcademyBatchRebateCountListBy(AcademyBatchRebateCount academyBatchRebateCount) throws Exception
	{
		AcademyBatchRebateCount abrc=null;
		PageResult<AcademyBatchRebateCount> pr=new PageResult<AcademyBatchRebateCount>();
		pr.setOrder("rebateCount");
		pr.setSort("desc");
		pr.setPage(false);//不分页
		PageParame p = new PageParame(pr);
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academyBatchRebateCount!= null)
		{		
			//院校
			if(academyBatchRebateCount.getAcademyId()>0)
			{
				hqlparam += " and academyId ="+ Constants.PLACEHOLDER;
				list.add(academyBatchRebateCount.getAcademyId());
			}
			//招生批次
			if(academyBatchRebateCount.getBatchId()>0)
			{
				hqlparam += " and batchId ="+ Constants.PLACEHOLDER;
				list.add(academyBatchRebateCount.getBatchId());
			}		
			//费用科目
			if(academyBatchRebateCount.getFeeSubjectId()>0)
			{
				hqlparam += " and feeSubjectId ="+ Constants.PLACEHOLDER;
				list.add(academyBatchRebateCount.getFeeSubjectId());
			}
		}
		else
		{
			return null;
		}
		if (!hqlparam.equals("")) 
		{
			p.setHqlConditionExpression(hqlparam);
			p.setValues(list.toArray());
		}
		Long[] abrcIds = academyBatchRebateCountDao.getIDs(p);
		if (abrcIds != null && abrcIds.length != 0) 
		{
			for (int i = 0; i < abrcIds.length; i++) 
			{
				// 内存获取
				abrc = academyBatchRebateCountDao.findById(Integer.valueOf(abrcIds[i].toString()));
				if(abrc!=null)
				{
					break;
				}
			}
		}
		return abrc;
	}
	
	/*
	 * 通过院校返款单Id查询对应院校返款每个批次的返款总人数集合
	 * 
	 * @see net.cedu.biz.finance.AcademyBatchRebateCountBiz#findAcademyBatchRebateCountListByPayAcademyCeduId(int)
	 */
	public List<AcademyBatchRebateCount> findAcademyBatchRebateCountListByPayAcademyCeduId(int payAcademyCeduId) throws Exception
	{
		List<AcademyBatchRebateCount> abrclist=null;
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (payAcademyCeduId != 0) 
		{
			hqlparam += "and payAcademyCeduId =" + Constants.PLACEHOLDER;
			list.add(payAcademyCeduId);
		}
		abrclist = academyBatchRebateCountDao.getByProperty(hqlparam, list);
		return abrclist;
	}

	
}
