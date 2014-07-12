package net.cedu.biz.academy.impl;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

                                                                                              
import net.cedu.biz.academy.AcademyContractBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.dao.academy.AcademyContractDao;
import net.cedu.dao.admin.UserDao;

import net.cedu.entity.academy.AcademyContract;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 院校合同业务逻辑
 * @author gaolei
 * */
@Service
public class AcademyContractBizImpl implements AcademyContractBiz {

	@Autowired
	private AcademyContractDao academycontractdao;   //院校合同接口
	@Autowired
	private UserDao userBiz;                         //用户接口
	
	/*
	 * 院校合同新增
	 * @see net.cedu.biz.academy.AcademyContractBiz#addAcademyContract(net.cedu.entity.academy.AcademyContract)
	 */
	public boolean addAcademyContract(AcademyContract academycontract)throws Exception {
		
			 academycontractdao.save(academycontract);	 
			
			 return true;	
	}
	
	/*
	 * 查询院校合同按主键ID
	 * @see net.cedu.biz.academy.AcademyContractBiz#findAcademyContractById(int)
	 */
	public AcademyContract findAcademyContractById(int id)throws Exception
	{
		return academycontractdao.findById(id);
	}

	/*
	 * 查询院校合同总数量
	 * @see net.cedu.biz.academy.AcademyContractBiz#findAllAcademyContractCount(int, net.cedu.model.page.PageResult)
	 */
	public int findAllAcademyContractCount(int academyId,PageResult<AcademyContract> pr)throws Exception
	{
		PageParame p = new PageParame(pr);
		p.setHqlConditionExpression(" and deleteFlag = "+Constants.PLACEHOLDER+" and academyId = "+Constants.PLACEHOLDER);
		p.setValues(new Object[]{Constants.DELETE_FALSE,academyId});
		
		return academycontractdao.getCounts(p);
		
	}
	
	
	
	/*
	 *  查询院校合同信息分页
	 * @see net.cedu.biz.academy.AcademyContractBiz#findAllAcademyContract(int, net.cedu.model.page.PageResult)
	 */
	public List<AcademyContract> findAllAcademyContract(int academyId, PageResult<AcademyContract> pr)throws Exception
	{
		
			List<AcademyContract> academycontractlst = null;
			// Ids集合
			PageParame p = new PageParame(pr);
			p.setHqlConditionExpression(" and deleteFlag = "+Constants.PLACEHOLDER+" and academyId = "+Constants.PLACEHOLDER);
			p.setValues(new Object[]{Constants.DELETE_FALSE,academyId});
			Long[] academyIds = academycontractdao.getIDs(p);
			if (academyIds != null && academyIds.length != 0) {
				academycontractlst = new ArrayList<AcademyContract>();
				for (int i = 0; i < academyIds.length; i++) {
					AcademyContract  academycontracts= academycontractdao.findById(Integer.parseInt(academyIds[i].toString()));
					if (academycontracts != null) {
						AcademyContract academycontractobj=academycontracts;
						User user=userBiz.findById(academycontractobj.getSibscriberId());
						if(user!=null)
						{
							academycontractobj.setUserName(user.getFullName());
						}
						
						academycontractlst.add(academycontractobj);
					}
				}
			}

			return academycontractlst;
		
	
	}
	
	/*
	 * 删除院校合同
	 * @see net.cedu.biz.academy.AcademyContractBiz#deleteAcademyContract(int)
	 */
	public boolean deleteAcademyContract(int id)throws Exception
	{
		
			Object obj= academycontractdao.deleteById(id);
			if(obj!=null)
			{
				return true;
			}else
			{
				return false;
			}
		
	}
	
	
	/*
	 * 修改院校合同
	 * @see net.cedu.biz.academy.AcademyContractBiz#updateAcademyContract(net.cedu.entity.academy.AcademyContract)
	 */
	public boolean updateAcademyContract(AcademyContract academycontract)throws Exception
	{
		
			
			Object obj= academycontractdao.update(academycontract);
			if(obj!=null)
			{
				return true;
			}else
			{
				return false;
			}
		
	}

	/*
	 * 重复数据校验
	 * @see net.cedu.biz.academy.AcademyContractBiz#findAcademyContractByNameAndDate(int, java.lang.String, java.util.Date, java.util.Date)
	 */
	public AcademyContract findAcademyContractByNameAndDate(int academyId,
			String name, Date startDate, Date endDate) {
		String hqlparam = "";
		List<Object> list = new ArrayList<Object>();
		
		if(academyId!=0)
		{
			hqlparam+=" and academyId ="+Constants.PLACEHOLDER;
			list.add(academyId);	
		}
		if(name!=null && !name.equals(""))
		{
			hqlparam+=" and name ="+Constants.PLACEHOLDER;
			list.add(name);	
		}
		if(startDate!=null)
		{
			hqlparam+=" and beginDate <="+Constants.PLACEHOLDER;
			list.add(DateUtil.getDate(startDate,"yyyy-MM-dd"));	
			hqlparam+=" and endDate >="+Constants.PLACEHOLDER;
			list.add(DateUtil.getDate(startDate,"yyyy-MM-dd"));	
		}
		if(endDate!=null)
		{
			hqlparam+=" and beginDate >="+Constants.PLACEHOLDER;
			list.add(DateUtil.getDate(endDate,"yyyy-MM-dd"));	
			hqlparam+=" and endDate <="+Constants.PLACEHOLDER;
			list.add(DateUtil.getDate(endDate,"yyyy-MM-dd"));	
		}
		List<AcademyContract> aclst=academycontractdao.getByProperty(hqlparam, list);
		if(aclst!=null && aclst.size()>0)
		{
			return aclst.get(0);
		}
		return null;
	}


	
	
	
}
