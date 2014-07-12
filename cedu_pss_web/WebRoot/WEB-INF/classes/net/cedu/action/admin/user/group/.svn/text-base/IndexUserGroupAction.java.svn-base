package net.cedu.action.admin.user.group;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.enrollment.Major;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模块设置首页
 * @author Jack
 *
 */
public class IndexUserGroupAction extends BaseAction 
{
	private static final long serialVersionUID = 8272042796098000524L;
	
	@Autowired
	private BranchBiz branchBiz;
	
	private List<Branch> blist=new ArrayList<Branch>();
	
	public String execute()
	{
		try
		{
			blist=branchBiz.findListById(getUser().getOrgId());
			Collections.sort(blist, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
			
			setIl8nName("admin");
			Branch b=new Branch();
			b.setId(-1);
			b.setName(getText("select_default"));
			blist.add(0,b);
		}
		catch(Exception e)
		{e.printStackTrace();}
		return SUCCESS;
	}

	public List<Branch> getBlist() {
		return blist;
	}
}
