package net.cedu.action.admin.branch;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学习中心设置
 * @author Jack
 *
 */
public class IndexBranchAction extends BaseAction
{
	private static final long serialVersionUID = 6035821999343665939L;
	
	@Autowired
	private BranchBiz branchBiz;
	
	private List<Branch> plist;
	private int types;
	
	public String execute()throws Exception
	{
		plist=branchBiz.findListComposite(getUser().getOrgId(),null,null,BranchEnum.Default);
		Collections.sort(plist, new Comparator() {
			public int compare(Object arg0, Object arg1) {
				Comparator cmp = Collator
						.getInstance(java.util.Locale.CHINA);
				String name1 = ((Branch) arg0).getName();
				String name2 = ((Branch) arg1).getName();
				return cmp.compare(name1, name2);
			}
		});
		types=getUser().getType();
		if(BranchEnum.Root.value()==types) types=BranchEnum.Admin.value();
		return SUCCESS;
	}

	public List<Branch> getPlist() {
		return plist;
	}

	public int getTypes() {
		return types;
	}
}
