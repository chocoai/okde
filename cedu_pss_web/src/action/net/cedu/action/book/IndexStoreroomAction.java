package net.cedu.action.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.entity.admin.Branch;
/**
 * 库房位置
 * @author XFY
 *
 */
public class IndexStoreroomAction extends BaseAction {
 
	private static final long serialVersionUID = 906361020037207570L;

	@Autowired
	private BranchBiz branchBiz; //中心业务层
	
	private List<Branch> branches=new ArrayList<Branch>();  
	private Branch cedu; //实体
	
	public String execute()
	{
		try {
			branches = branchBiz.findBranchForModel();
		} catch (Exception e) {
 
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List<Branch> getBranches() {
		return branches;
	}

	public void setBranches(List<Branch> branches) {
		this.branches = branches;
	}

	public Branch getCedu() {
		return cedu;
	}

	public void setCedu(Branch cedu) {
		this.cedu = cedu;
	}
	
	
}
