package net.cedu.biz.finance.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz;
import net.cedu.common.Constants;
import net.cedu.dao.finance.AcademyRebateFenPeiBranchDao;
import net.cedu.entity.finance.AcademyRebateFenPeiBranch;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 院校返款调整金额分配给学习中心
 * 
 * @author lixiaojun
 * 
 */
@Service
public class AcademyRebateFenPeiBranchBizImpl implements AcademyRebateFenPeiBranchBiz
{

	@Autowired
	private AcademyRebateFenPeiBranchDao academyRebateFenPeiBranchDao;
	
	/*
	 * 添加院校返款调整金额分配给学习中心
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#addAcademyRebateFenPeiBranch(net.cedu.entity.finance.AcademyRebateFenPeiBranch)
	 */
	public boolean addAcademyRebateFenPeiBranch(AcademyRebateFenPeiBranch academyRebateFenPeiBranch) throws Exception 
	{
		if (academyRebateFenPeiBranch != null)
		{
			Object object = academyRebateFenPeiBranchDao.save(academyRebateFenPeiBranch);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 删除院校返款调整金额分配给学习中心（物理删除）
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#deleteAcademyRebateFenPeiBranchById(int)
	 */
	public boolean deleteAcademyRebateFenPeiBranchById(int id) throws Exception 
	{
		if (id != 0) 
		{
			Object object = academyRebateFenPeiBranchDao.deleteById(id);
			if (object != null)
			{
				return true;
			}
		}		
		return false;
	}
	
	/*
	 * 修改院校返款调整金额分配给学习中心
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#updateAcademyRebateFenPeiBranch(net.cedu.entity.finance.AcademyRebateFenPeiBranch)
	 */
	public boolean updateAcademyRebateFenPeiBranch(AcademyRebateFenPeiBranch academyRebateFenPeiBranch) throws Exception 
	{
		if (academyRebateFenPeiBranch != null) 
		{
			Object object = academyRebateFenPeiBranchDao.update(academyRebateFenPeiBranch);
			if (object != null) 
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * 根据Id查找院校返款调整金额分配给学习中心
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#findAcademyRebateFenPeiBranchById(int)
	 */
	public AcademyRebateFenPeiBranch findAcademyRebateFenPeiBranchById(int id) throws Exception 
	{
		return this.academyRebateFenPeiBranchDao.findById(id);
	}

	/*
	 * 根据条件查找院校返款调整金额分配给学习中心数量
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#findAcademyRebateFenPeiBranchCountBy(net.cedu.entity.finance.AcademyRebateFenPeiBranch)
	 */
	public int findAcademyRebateFenPeiBranchCountBy(AcademyRebateFenPeiBranch academyRebateFenPeiBranch) throws Exception 
	{
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academyRebateFenPeiBranch!= null)
		{			
			//院校返款单Id
			if(academyRebateFenPeiBranch.getPayAcademyCeduId()>0)
			{
				hqlparam += " and payAcademyCeduId ="+ Constants.PLACEHOLDER;
				list.add(academyRebateFenPeiBranch.getPayAcademyCeduId());
			}
			//学习中心Id
			if(academyRebateFenPeiBranch.getBranchId()>0)
			{
				hqlparam += " and branchId ="+ Constants.PLACEHOLDER;
				list.add(academyRebateFenPeiBranch.getBranchId());
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
		return academyRebateFenPeiBranchDao.getCounts(p);
	}

	/*
	 * 根据条件查找院校返款调整金额分配给学习中心列表
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#findAcademyRebateFenPeiBranchListBy(net.cedu.entity.finance.AcademyRebateFenPeiBranch)
	 */
	public List<AcademyRebateFenPeiBranch> findAcademyRebateFenPeiBranchListBy(AcademyRebateFenPeiBranch academyRebateFenPeiBranch) throws Exception
	{
		List<AcademyRebateFenPeiBranch> arfpbList=null;
		PageParame p = new PageParame();
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (academyRebateFenPeiBranch!= null)
		{		
			//院校返款单Id
			if(academyRebateFenPeiBranch.getPayAcademyCeduId()>0)
			{
				hqlparam += " and payAcademyCeduId ="+ Constants.PLACEHOLDER;
				list.add(academyRebateFenPeiBranch.getPayAcademyCeduId());
			}
			//学习中心Id
			if(academyRebateFenPeiBranch.getBranchId()>0)
			{
				hqlparam += " and branchId ="+ Constants.PLACEHOLDER;
				list.add(academyRebateFenPeiBranch.getBranchId());
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
		Long[] arfpbIds = academyRebateFenPeiBranchDao.getIDs(p);
		if (arfpbIds != null && arfpbIds.length != 0) 
		{
			arfpbList=new ArrayList<AcademyRebateFenPeiBranch>(); 
			for (int i = 0; i < arfpbIds.length; i++) 
			{
				// 内存获取
				AcademyRebateFenPeiBranch arfpb = academyRebateFenPeiBranchDao.findById(Integer.valueOf(arfpbIds[i].toString()));
				if(arfpb!=null)
				{
					arfpbList.add(arfpb);
				}
			}
		}
		return arfpbList;
	}

	/*
	 * 通过院校返款单Id查询对应院校返款调整金额分配给学习中心列表
	 * 
	 * @see net.cedu.biz.finance.AcademyRebateFenPeiBranchBiz#findAcademyRebateFenPeiBranchListByPayAcademyCeduId(int)
	 */
	public List<AcademyRebateFenPeiBranch> findAcademyRebateFenPeiBranchListByPayAcademyCeduId(int payAcademyCeduId) throws Exception
	{
		List<AcademyRebateFenPeiBranch> abrclist=null;
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		if (payAcademyCeduId != 0) 
		{
			hqlparam += "and payAcademyCeduId =" + Constants.PLACEHOLDER;
			list.add(payAcademyCeduId);
		}
		abrclist = academyRebateFenPeiBranchDao.getByProperty(hqlparam, list);
		return abrclist;
	}

}
