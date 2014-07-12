package net.cedu.action.admin.user;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.UserEnum;
import net.cedu.entity.admin.Branch;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户删除
 * @author dongminghao
 *
 */
public class DeleteFalseUserAction extends BaseAction
{
	private static final long serialVersionUID = 6035821999343665939L;
	
	@Autowired
	private BranchBiz branchBiz;
	
	private List<Branch> plist=new ArrayList<Branch>();
	private int type;
	
	public String execute()throws Exception
	{
		Branch branch=new Branch();
		branch.setLevel(BranchEnum.Level.value());
		plist=branchBiz.findListComposite(getUser().getOrgId(),null,null,BranchEnum.Default);
		if(0==plist.size())
		{
			setIl8nName("admin");
			branch.setId(0);
			branch.setName(getText("select_default"));
			plist.add(branch);
		}
		type=UserEnum.TypeBranch.value();
		return SUCCESS;
	}

	public List<Branch> getPlist() {
		return plist;
	}
	
	public int getType() {
		return type;
	}
}
