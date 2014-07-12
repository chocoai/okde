package net.cedu.action.admin.privilege.roleprivilege;

import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.entity.admin.privilege.Modular;
import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.entity.admin.privilege.PrivilegeSet;
import net.cedu.entity.admin.privilege.SubSystem;
import net.cedu.model.admin.TreeModular;
import net.cedu.model.admin.TreePrivilegeSet;
import net.cedu.model.admin.TreeSubSystem;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 角色权限范围
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonTreeRolePrivilegeAction extends BaseAction 
{
	private static final long serialVersionUID = 4384055175835481869L;
	
	@Autowired
	private SubSystemBiz subSystemBiz;
	@Autowired
	private ModularBiz modularBiz;
	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	List<TreeSubSystem> trslist=new LinkedList<TreeSubSystem>();
	
	@Action(value = "tree_role_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute()
	{
		try
		{		
			int ssId=0;
			//获取子系统
			List<SubSystem> sslist=subSystemBiz.findAll();
			//循环子系统
			for(int i=0,ic=sslist.size();i<ic;i++)
			{
				TreeSubSystem trs=new TreeSubSystem();
				
				//存储子系统树
				trs.setSubSystem(sslist.get(i));
				System.out.println(trs.getSubSystem().getName());
				//获取子系统ID
				ssId=trs.getSubSystem().getId();
				//根据子系统ID获取模块集合
				List<Modular> list=modularBiz.findModularBySubSystemId(ssId);
				//获取模块树集合
				List<TreeModular> trmlist=initTreeModular(list);
				if(0<trmlist.size())
				{
					//存储模块集合
					trs.setMlist(trmlist);
					//存储子系统树集合
					trslist.add(trs);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		print(trslist);
		return SUCCESS;
	}
	
//	private void print(List<TreeSubSystem> trslist)
//	{
//		for(int i=0,ic=trslist.size();i<ic;i++)
//		{
//			System.out.println(trslist.get(i).getSubSystem().getName());
//			for(int j=0,jc=trslist.get(i).getMlist().size();j<jc;j++)
//			{
//				System.out.println("\t"+trslist.get(i).getMlist().get(j).getModular().getName());
//			}
//		}
//	}
	
	/**
	 * 获取模块树集合
	 * @param mlist
	 * @return
	 * @throws Exception
	 */
	private List<TreeModular> initTreeModular(List<Modular> mlist)throws Exception
	{
		List<TreeModular> trmlist=new LinkedList<TreeModular>();
		int mId=0;
		//循环模块
		for(int j=0,jc=mlist.size();j<jc;j++)
		{
			TreeModular trm=new TreeModular();
			//存储模块树
			trm.setModular(mlist.get(j));
//			System.out.println("\t"+trm.getModular().getName());
			//获取模块ID
			mId=trm.getModular().getId();
			//根据模块ID获取权限集集合
			List<PrivilegeSet> pslist=privilegeSetBiz.findPrivilegeSetByModularId(mId);
			//根据权限集获取权限
			List<TreePrivilegeSet> trpslist=initTreePrivilegeSet(pslist);
			if(0<trpslist.size())
			{
				//存储权限集树集合
				trm.setPslist(trpslist);
				trmlist.add(trm);
			}					
		}
		return trmlist;
	}
	
	/**
	 * 获取权限集合树集合
	 * @param pslist
	 * @return
	 * @throws Exception
	 */
	private List<TreePrivilegeSet> initTreePrivilegeSet(List<PrivilegeSet> pslist)throws Exception
	{
		List<TreePrivilegeSet> trpslist=new LinkedList<TreePrivilegeSet>();
		int psId=0;
		//循环权限集
		for(int k=0,kc=pslist.size();k<kc;k++)
		{
			TreePrivilegeSet trps=new TreePrivilegeSet();
			//存储权限集树
			trps.setPrivilegeSet(pslist.get(k));
//			System.out.println("\t\t"+trps.getPrivilegeSet().getName());
			//获取权限集ID
			psId=trps.getPrivilegeSet().getId();
			//根据权限集ID获取权限集合
			List<Privilege> plist=privilegeBiz.findPrivilegeBySetId(psId);
			//存储权限集合
			if(null!=plist&&0<plist.size())
			{
				trps.setPlist(plist);
				trpslist.add(trps);
//				for(int s=0,sc=plist.size();s<sc;s++)
//				{
//					System.out.println("\t\t\t"+plist.get(s).getName());
//				}
			}
		}
		return trpslist;
	}

	public List<TreeSubSystem> getTrslist() {
		return trslist;
	}
}
