package net.cedu.action.admin.privilege.sub;

import java.util.ArrayList;
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

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限子项首页
 * @author Jack
 *
 */
public class IndexSubPrivilegeAction extends BaseAction 
{
	private static final long serialVersionUID = 8272042796098000524L;
	
	@Autowired
	private ModularBiz modularBiz;
	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	@Autowired
	private SubSystemBiz subSystemBiz;
	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	private List<SubSystem> slist=new ArrayList<SubSystem>();	
	private List<Modular> mlist=new ArrayList<Modular>();				//模块集合
	private List<PrivilegeSet> pslist=new ArrayList<PrivilegeSet>();	//权限集集合
	private List<Privilege> plist=new ArrayList<Privilege>();			//权限集集合
	
	public String execute()
	{
		try
		{
			setIl8nName("admin");
			initSubSystem();
			initModular();
			initPrivilegeSet();		
			initPrivilege();			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化子系统下拉菜单
	 * @throws Exception
	 */
	private void initSubSystem()throws Exception
	{
		SubSystem s=new SubSystem();
		s.setId(0);
		s.setName(getText("select_default"));
		slist=subSystemBiz.findListByCondition(new SubSystem());
		if(0>=slist.size())
			slist.add(0,s);
	}
	
	/**
	 * 初始化模块下拉菜单
	 * @throws Exception
	 */
	private void initModular()throws Exception
	{
		Modular m=new Modular();
		m.setSubSystemId(slist.get(0).getId());
		mlist=modularBiz.findListByCondition(m);
		m.setId(0);
		m.setName(getText("select_default"));
		if(0>=mlist.size())
			mlist.add(0,m);
	}
	
	/**
	 * 初始化权限集下拉菜单
	 * @throws Exception
	 */
	private void initPrivilegeSet()throws Exception
	{
		PrivilegeSet ps=new PrivilegeSet();
		ps.setModularId(mlist.get(0).getId());
		pslist=privilegeSetBiz.findListByCondition(ps);
		ps.setId(-1);
		ps.setName(getText("select_default"));
		ps.setModular(mlist.get(0));
		pslist.add(0,ps);
	}
	
	/**
	 * 初始化权限下拉菜单
	 * @throws Exception
	 */
	private void initPrivilege()throws Exception
	{
		Privilege p=new Privilege();
		p.setSetId(pslist.get(0).getId());
		plist=privilegeBiz.findListByCondition(p);
		p.setId(-1);
		p.setName(getText("select_default"));
		plist.add(0,p);
	}

	public List<Modular> getMlist() {
		return mlist;
	}

	public List<PrivilegeSet> getPslist() {
		return pslist;
	}

	public List<Privilege> getPlist() {
		return plist;
	}

	public List<SubSystem> getSlist() {
		return slist;
	}
}
