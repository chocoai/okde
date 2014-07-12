package net.cedu.biz.orgstructure.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.orgstructure.JurisdictionBiz;
import net.cedu.common.Constants;
import net.cedu.dao.orgstructure.JurisdictionDao;
import net.cedu.entity.orgstructure.Jurisdiction;
import net.cedu.model.page.PageParame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @功能：岗位业务层实现类
 *
 * @作者： 谭必庆
 * @作成时间：2011-12-29 上午11:55:51
 *
 * @修改者：
 * @修改内容：
 * @修改时间：
 *
 */
@Service
public class JurisdictionBizImpl implements JurisdictionBiz {
	
	@Autowired
	private JurisdictionDao jurisdictionDao;				//团队DAO

	/**
	 * 添加子系统
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public boolean addNew(Jurisdiction jurisdiction)throws Exception
	{
		if(0>=findCountByConditionForHQL(jurisdiction))
		{
			jurisdictionDao.save(jurisdiction);
			return true;
		}
		return false;
	}
	
	/**
	 * 修改子系统
	 * @param Job
	 * @throws Exception
	 */
	public void modify(Jurisdiction jurisdiction)throws Exception
	{
		jurisdictionDao.update(jurisdiction);
	}
	
	/**
	 * 根据条件查找子系统列表
	 */
	private List<Jurisdiction> findListByCondition(Jurisdiction jurisdiction)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		
		//岗位级别
		if(0<jurisdiction.getUserId())
		{
			hqlcon+=" and userId="+ Constants.PLACEHOLDER;
			paramList.add(jurisdiction.getUserId());
		}
		return jurisdictionDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据条件查找子系统数量
	 */
	public int findCountByConditionForHQL(Jurisdiction jurisdiction)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();

		//用户
		if(0<jurisdiction.getUserId())
		{
			hqlcon+=" and userId="+ Constants.PLACEHOLDER;
			paramList.add(jurisdiction.getUserId());
		}
		
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		return jurisdictionDao.getCounts(p);
	}
	
	/**
	 * 根据ID查询子系统
	 */
	public Jurisdiction findById(int id) throws Exception
	{
		Jurisdiction j=new Jurisdiction();
		j.setUserId(id);
		if(0<findCountByConditionForHQL(j))
			return this.findListByCondition(j).get(0);
		return null;
	}

	/**
	 * 通过用户ID查询部门IDs集合
	  * @see net.cedu.biz.orgstructure.JurisdictionBiz#findByUserId(int)
	 */
	public String findByUserId(int userId) throws Exception {
//		String departmentIds=",";
		StringBuffer departmentIdsSB= new StringBuffer(",");
		Jurisdiction jurisdiction=jurisdictionDao.getObjByProperty("userId", userId);
		if(jurisdiction!=null){
			String dIds=jurisdiction.getDepartmentIds();
			String[] departmentIdArray=dIds.split("@");
			for (String s : departmentIdArray) {
				if(s!=null&&!s.equals("")){
//					if(departmentIds.equals(",")){
//						departmentIds=s;
//					}else{
//						departmentIds+=","+s;
//					}
					if(departmentIdsSB.toString().equals(",")){
						departmentIdsSB = new StringBuffer(s);
					}else{
						departmentIdsSB.append(","+s);
					}
				}
			}
		}
		if(departmentIdsSB.toString().equals(",")){
			return "0";
		}
		return departmentIdsSB.toString();
	}

}
