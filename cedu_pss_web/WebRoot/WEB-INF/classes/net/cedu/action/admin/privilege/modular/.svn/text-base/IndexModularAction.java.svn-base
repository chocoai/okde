package net.cedu.action.admin.privilege.modular;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.SubSystemBiz;
import net.cedu.entity.admin.privilege.SubSystem;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块设置首页
 * @author Jack
 *
 */
public class IndexModularAction extends BaseAction 
{
	private static final long serialVersionUID = 8272042796098000524L;
	
	@Autowired
	private SubSystemBiz subSystemBiz;
	
	private List<SubSystem> slist=new ArrayList<SubSystem>();
	
	public String execute()
	{
		SubSystem s=new SubSystem();
		s.setId(-1);
		setIl8nName("admin");
		s.setName(getText("select_default"));
		slist=subSystemBiz.findListByCondition(new SubSystem());
		slist.add(0,s);
		return SUCCESS;
	}

	public List<SubSystem> getSlist() {
		return slist;
	}
}
