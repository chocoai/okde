package net.cedu.biz.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.biz.admin.privilege.SubPrivilegeBiz;
import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.SubPrivilegeDao;
import net.cedu.entity.admin.privilege.SubPrivilege;
import net.cedu.model.page.PageParame;
import net.cedu.model.page.PageResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 权限子项业务层实现类
 * @author Jack
 *
 */
@Service
public class SubPrivilegeBizImpl implements SubPrivilegeBiz {

	@Autowired
	private SubPrivilegeDao subPrivilegeDao;
	
	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	/**
	 * 添加权限子项
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void addNew(SubPrivilege subPrivilege)throws Exception
	{
		if(0>=findCountByConditionForAdd(subPrivilege))
		{
			subPrivilege.setCode(subPrivilege.getFullPath().substring(subPrivilege.getFullPath().lastIndexOf("/")+1));
			subPrivilegeDao.save(subPrivilege);
		}
	}
	
	/**
	 * 修改权限子项
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void modify(SubPrivilege SubPrivilege)throws Exception
	{
		subPrivilegeDao.update(SubPrivilege);
	}
	
	/**
	 * 根据ID删除权限子项
	 * @param SubPrivilege
	 * @throws Exception
	 */
	public void deleteById(int id)throws Exception
	{
		subPrivilegeDao.deleteById(id);
	}
	
	/**
	 * 根据条件查找权限子项列表_分页
	 */
	public List<SubPrivilege> findListByCondition(PageResult<SubPrivilege> pr,SubPrivilege subPrivilege)
	{
		try {
			List<SubPrivilege> subPrivileges = null;
			
			PageParame p = new PageParame(pr);
			
			List<Object> list=new ArrayList<Object>();
			String params="";
			
			//权限ID
			if(-1<subPrivilege.getPrivilegeId())
			{
				params+=" and privilegeId="+ Constants.PLACEHOLDER;
				list.add(subPrivilege.getPrivilegeId());
			}
			//排序
			if(0<subPrivilege.getOrders())
			{
				params+=" and orders="+ Constants.PLACEHOLDER;
				list.add(subPrivilege.getOrders());
			}
			//名称
			if(StringUtils.isNotBlank(subPrivilege.getName()))
			{
				params+=" and name like "+ Constants.PLACEHOLDER;
				list.add("'%"+subPrivilege.getName()+"%'");
			}
			//全路径
			if(StringUtils.isNotBlank(subPrivilege.getFullPath()))
			{
				params+=" and fullPath like "+ Constants.PLACEHOLDER;
				list.add("%"+subPrivilege.getFullPath()+"%");
			}
			//权限代码
			if(StringUtils.isNotBlank(subPrivilege.getCode()))
			{
				params+=" and code="+ Constants.PLACEHOLDER;
				list.add(subPrivilege.getCode());
			}
			//是否显示
			if(-1<subPrivilege.getIsShow())
			{
				params+=" and isShow="+ Constants.PLACEHOLDER;
				list.add(subPrivilege.getIsShow());
			}
						
			if(!params.equals(""))
			{
				p.setHqlConditionExpression(params);
				p.setValues(list.toArray());
			}
			// Ids集合
			Long[] SubPrivilegeIds = subPrivilegeDao.getIDs(p);
			if (SubPrivilegeIds != null && SubPrivilegeIds.length != 0) {
				subPrivileges = new ArrayList<SubPrivilege>();
				for (int i = 0; i < SubPrivilegeIds.length; i++) {
					// 内存获取
					SubPrivilege subPrivilegeObj = subPrivilegeDao.findById(Integer.valueOf(SubPrivilegeIds[i].toString()));
					if (subPrivilegeObj != null) 
					{
						//写入权限对象
						subPrivilegeObj.setPrivilege(privilegeBiz.findPrivilegeById(subPrivilegeObj.getPrivilegeId()));
						subPrivileges.add(subPrivilegeObj);
					}
				}
			}
			return subPrivileges;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据条件查找权限子项列表
	 */
	public List<SubPrivilege> findListByCondition(SubPrivilege subPrivilege)
	{
		String hqlcon="";
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(-1<subPrivilege.getPrivilegeId())
		{
			hqlcon+=" and privilegeId="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getPrivilegeId());
		}
		//排序
		if(0<subPrivilege.getOrders())
		{
			hqlcon+=" and orders="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getOrders());
		}
		//名称
		if(StringUtils.isNotBlank(subPrivilege.getName()))
		{
			hqlcon+=" and name like "+ Constants.PLACEHOLDER;
			paramList.add("%"+subPrivilege.getName()+"%");
		}
		//全路径
		if(StringUtils.isNotBlank(subPrivilege.getFullPath()))
		{
			hqlcon+=" and fullPath like "+ Constants.PLACEHOLDER;
			paramList.add("%"+subPrivilege.getFullPath()+"%");
		}
		//权限代码
		if(StringUtils.isNotBlank(subPrivilege.getCode()))
		{
			hqlcon+=" and code="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getCode());
		}
		//是否显示
		if(-1<subPrivilege.getIsShow())
		{
			hqlcon+=" and isShow="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getIsShow());
		}
				
		hqlcon+=" order by privilegeId";
		
		return subPrivilegeDao.getByProperty(hqlcon,paramList);
	}
	
	/**
	 * 根据权限ID查找显示的权限子项列表
	 * @param privilegeId 权限ID
	 * 
	 */
	public List<SubPrivilege> findViewListByPrId(int privilegeId)
	{
		SubPrivilege subPrivilege=new SubPrivilege();
		subPrivilege.setIsShow(1);//显示
		subPrivilege.setPrivilegeId(privilegeId);
		return findListByCondition(subPrivilege);
	}
	
	/**
	 * 根据条件查找权限子项数量_分页
	 */
	public int findCountByConditionForHQL(SubPrivilege subPrivilege)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		//权限ID
		if(-1<subPrivilege.getPrivilegeId())
		{
			hqlcon+=" and privilegeId="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getPrivilegeId());
		}
		//排序
		if(0<subPrivilege.getOrders())
		{
			hqlcon+=" and orders="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getOrders());
		}
		//名称
		if(StringUtils.isNotBlank(subPrivilege.getName()))
		{
			hqlcon+=" and name like "+ Constants.PLACEHOLDER;
			paramList.add("%"+subPrivilege.getName()+"%");
		}
		//全路径
		if(StringUtils.isNotBlank(subPrivilege.getFullPath()))
		{
			hqlcon+=" and fullPath like "+ Constants.PLACEHOLDER;
			paramList.add("%"+subPrivilege.getFullPath()+"%");
		}
		//权限代码
		if(StringUtils.isNotBlank(subPrivilege.getCode()))
		{
			hqlcon+=" and code="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getCode());
		}
		//是否显示
		if(-1<subPrivilege.getIsShow())
		{
			hqlcon+=" and isShow="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getIsShow());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		int count=subPrivilegeDao.getCounts(p);
		return count;
	}
	
	/**
	 * 根据条件查找权限子项数量_添加用
	 */
	public int findCountByConditionForAdd(SubPrivilege subPrivilege)
	{
		String hqlcon="";
		PageParame p = new PageParame();
		List<Object> paramList=new ArrayList<Object>();
		
		//全路径
		if(StringUtils.isNotBlank(subPrivilege.getFullPath()))
		{
			hqlcon+=" and fullPath = "+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getFullPath());
		}
		//权限代码
		if(StringUtils.isNotBlank(subPrivilege.getCode()))
		{
			hqlcon+=" and code="+ Constants.PLACEHOLDER;
			paramList.add(subPrivilege.getCode());
		}
		p.setHqlConditionExpression(hqlcon);
		p.setValues(paramList.toArray());
		int count=subPrivilegeDao.getCounts(p);
		return count;
	}
	
	/**
	 * 查询所有权限子项(包含总部)
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<SubPrivilege> findAll() throws Exception {
		return subPrivilegeDao.findAll();
	}
	
	/**
	 * 根据ID查询权限子项
	 */
	public SubPrivilege findSubPrivilegeById(int id) throws Exception
	{
		return subPrivilegeDao.findById(id);
	}
}
