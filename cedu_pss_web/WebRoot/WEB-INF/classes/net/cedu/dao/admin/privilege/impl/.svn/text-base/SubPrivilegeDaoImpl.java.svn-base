package net.cedu.dao.admin.privilege.impl;

import java.util.ArrayList;
import java.util.List;

import net.cedu.common.Constants;
import net.cedu.dao.admin.privilege.SubPrivilegeDao;
import net.cedu.dao.impl.BaseMDDaoImpl;
import net.cedu.entity.admin.privilege.SubPrivilege;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * 权限子项_数据层实现类
 * @author Jack
 *
 */
@Repository
public class SubPrivilegeDaoImpl extends BaseMDDaoImpl<SubPrivilege> implements SubPrivilegeDao 
{
	/**
	 * 根据条件查找权限子项数量
	 */
	@SuppressWarnings("unchecked")
	public int selectCountByCondition(SubPrivilege subPrivilege) throws Exception 
	{
		
		String hql = "";
		List objs = new ArrayList();

		if(0<subPrivilege.getPrivilegeId()) {
			hql += " and privilegeId = " + Constants.PLACEHOLDER;
			objs.add(subPrivilege.getPrivilegeId());
		}

		// 中心名称
		if(StringUtils.isNotBlank(subPrivilege.getFullPath())) {
			hql += " and fullPath = " + Constants.PLACEHOLDER;
			objs.add(subPrivilege.getFullPath());
		}
		return super.findCountByProperty(hql, objs);
	}
	
}
