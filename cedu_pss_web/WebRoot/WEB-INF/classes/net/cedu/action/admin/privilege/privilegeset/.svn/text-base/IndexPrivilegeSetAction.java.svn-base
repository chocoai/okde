package net.cedu.action.admin.privilege.privilegeset;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.ModularBiz;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.entity.admin.privilege.Modular;
import net.cedu.entity.admin.privilege.SubSystem;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 权限集首页
 * @author Jack
 *
 */
public class IndexPrivilegeSetAction extends BaseAction 
{
	private static final long serialVersionUID = 8272042796098000524L;
	
	@Autowired
	private ModularBiz modularBiz;
	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private List<SubSystem> slist=new ArrayList<SubSystem>();	
	private List<Modular> mlist=new ArrayList<Modular>();
	
	public String execute()
	{
		try
		{
			SubSystem s=new SubSystem();
			s.setId(-1);
			setIl8nName("admin");
			s.setName(getText("select_default"));
			slist=subSystemBiz.findListByCondition(new SubSystem());
			slist.add(0,s);
			Modular m=new Modular();
			m.setId(-1);
			setIl8nName("admin");
			m.setName(getText("select_default"));
			mlist=modularBiz.findListByCondition(new Modular());
			mlist.add(0,m);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Modular> getMlist() {
		return mlist;
	}

	public List<SubSystem> getSlist() {
		return slist;
	}	
}
