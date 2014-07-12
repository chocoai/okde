package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.finance.AcademyRebateAddRecordsBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.AcademyRebateAddRecordsDao;
import net.cedu.entity.finance.AcademyRebateAddRecords;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返款追加记录
 * 
 * @author lixiaojun
 * 
 */
@Service
public class AcademyRebateAddRecordsBizImpl implements AcademyRebateAddRecordsBiz
{

	@Autowired
	private AcademyRebateAddRecordsDao academyRebateAddRecordsDao;//

	/*
	 * 添加院校返款追加记录
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateAddRecordsBiz#addAcademyRebateAddRecords(net.cedu.entity.finance.AcademyRebateAddRecords)
	 */
	public boolean addAcademyRebateAddRecords(AcademyRebateAddRecords academyRebateAddRecords) throws Exception
	{
		if (academyRebateAddRecords != null)
		{
			Object object = academyRebateAddRecordsDao.save(academyRebateAddRecords);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除院校返款追加记录（物理删除）
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateAddRecordsBiz#deleteAcademyRebateAddRecordsById(int)
	 */
	public boolean deleteAcademyRebateAddRecordsById(int id) throws Exception
	{
		if (id != 0) 
		{
			Object object = academyRebateAddRecordsDao.deleteById(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * 修改院校返款追加记录
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateAddRecordsBiz#updateAcademyRebateAddRecords(net.cedu.entity.finance.AcademyRebateAddRecords)
	 */
	public boolean updateAcademyRebateAddRecords(AcademyRebateAddRecords academyRebateAddRecords) throws Exception
	{
		if (academyRebateAddRecords != null) 
		{
			Object object = academyRebateAddRecordsDao.update(academyRebateAddRecords);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 根据Id查找院校返款追加记录
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateAddRecordsBiz#findAcademyRebateAddRecordsById(int)
	 */
	public AcademyRebateAddRecords findAcademyRebateAddRecordsById(int id) throws Exception
	{
		return this.academyRebateAddRecordsDao.findById(id);
	}

	/*
	 * 根据条件查找院校返款追加记录数量
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateAddRecordsBiz#findAcademyRebateAddRecordsCountBy(net.cedu.entity.finance.AcademyRebateAddRecords)
	 */
	public int findAcademyRebateAddRecordsCountBy(AcademyRebateAddRecords academyRebateAddRecords) throws Exception
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academyRebateAddRecords!= null)
		{			
			//追加的院校返款单Id
			if(academyRebateAddRecords.getPayAcademyCeduAddId()!=0)
			{
				hqlparam += " and payAcademyCeduAddId ="+ Constants.PLACEHOLDER;
				list.add(academyRebateAddRecords.getPayAcademyCeduAddId());
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
		return academyRebateAddRecordsDao.getCounts(p);
	}
	
	/*
	 * 通过院校返款单Id查询对应院校返款追加记录集合
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateAddRecordsBiz#findAcademyRebateAddRecordsListByPayAcademyCeduId(int)
	 */
	public List<AcademyRebateAddRecords> findAcademyRebateAddRecordsListByPayAcademyCeduId(int payAcademyCeduId) throws Exception
	{
		List<AcademyRebateAddRecords> abrclist=null;
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (payAcademyCeduId != 0) 
		{
			hqlparam += "and payAcademyCeduId =" + Constants.PLACEHOLDER;
			list.add(payAcademyCeduId);
		}
		abrclist = academyRebateAddRecordsDao.getByProperty(hqlparam, list);
		return abrclist;
	}
	
}
