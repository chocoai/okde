package net.cedu.action.admin.areamanager;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.AreaManagerBranchBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.UserEnum;
import net.cedu.common.string.StringUtil;
import net.cedu.entity.admin.AreaManagerBranch;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.admin.User;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * json区域经理设置
 * @author gaolei
 *
 */
@ParentPackage("json-default")
public class JsonSettingsAreaBranchAction extends BaseAction {

	@Autowired
	private UserBiz userbiz;       //用户biz
	@Autowired
	private BranchBiz branchbiz;   //学习中心biz
	@Autowired
	private AreaManagerBranchBiz areaManagerBranchbiz;   //区域经理学习中心biz
	private User userinfo;         //用户实体
	private List<User> userlst=new ArrayList<User>(); //用户集合
	private Branch branch;         //学习中心实体
	private List<Branch> branchlst=new ArrayList<Branch>(); //学习中心集合
	private List<AreaManagerBranch> areaManagerBranchlst=new ArrayList<AreaManagerBranch>(); //区域经理学习中心集合
	// 分页结果
	private PageResult<User> result = new PageResult<User>();
	private int userId;            //用户Id
	private int isManager;         //是否为区域经理
	private int managerId;         //区域经理Id
	private String branchIds;      //学习中心Id(多)
	
	
	
	
	/**
	 * 区域经理(分页数据)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listareamanagerpage", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"result.*.password," +
			"userinfo.password"
	}) })
	public String ListAreaManagerPage() throws Exception {
		userinfo=new User();
		userinfo.setOrgId(UserEnum.IsOrgTrue.value());
		userinfo.setIsManager(UserEnum.ManagerYes.value());
		result.setList(userbiz.findUserPageListByBranchId(userinfo, result));
		return SUCCESS;
	}
	
	/**
	 * 区域经理(分页数量)
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "countareamanagerpage", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String CountAreaManagerPage() throws Exception {
		userinfo=new User();
		userinfo.setOrgId(UserEnum.IsOrgTrue.value());
		userinfo.setIsManager(UserEnum.ManagerYes.value());
		result.setRecordCount(userbiz.findUserPageCountByBranchId(userinfo, result));
		return SUCCESS;
	}
	
	
	
	
	
	/**
	 * 非区域经理数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listareamanager", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"userlst.*.password"
	}) })
	public String ListAreaManager() throws Exception {
		userlst=userbiz.findUsersByOrgId(UserEnum.ManagerYes.value(),Constants.IS_MANAGER_FALSE);
		if(userlst!=null && userlst.size()>0)
		{
			Collections.sort(userlst, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((User) arg0).getFullName();
					String name2 = ((User) arg1).getFullName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}
	
	/**
	 * 区域经理数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listareamanagertrue", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListAreaManagerTrue() throws Exception {
		userlst=userbiz.findUsersByOrgId(UserEnum.ManagerYes.value(),Constants.IS_MANAGER_TRUE);
		if(userlst!=null && userlst.size()>0)
		{
			Collections.sort(userlst, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((User) arg0).getFullName();
					String name2 = ((User) arg1).getFullName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}
	
	/**
	 * 查询区域经理的所有学习中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "listmanagerwithbranchs", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ListManagerWithBranchs() throws Exception {
		areaManagerBranchlst=areaManagerBranchbiz.findAreaManagerBranchByManagerId(managerId);
		if(areaManagerBranchlst!=null && areaManagerBranchlst.size()>0)
		{
			Collections.sort(areaManagerBranchlst, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((AreaManagerBranch) arg0).getBranchName();
					String name2 = ((AreaManagerBranch) arg1).getBranchName();
					return cmp.compare(name1, name2);
				}
			});
		}
//		String branchIds="";
		StringBuffer branchIdsSB = new StringBuffer("");
		if(areaManagerBranchlst!=null && areaManagerBranchlst.size()>0)
		{
			for(int i=0;i<areaManagerBranchlst.size();i++)
			{
//				branchIds+=areaManagerBranchlst.get(i).getBranchId()+",";
				if(branchIdsSB.toString().equals("")){
					branchIdsSB = new StringBuffer(areaManagerBranchlst.get(i).getBranchId()+"");
				}else{
					branchIdsSB.append(","+areaManagerBranchlst.get(i).getBranchId());
				}
			}
//			branchIds=branchIds.substring(0, branchIds.length()-1);
		}
		branchlst=branchbiz.findAllNotInIds(branchIdsSB.toString());
		if(branchlst!=null && branchlst.size()>0)
		{
			Collections.sort(branchlst, new Comparator() {
				public int compare(Object arg0, Object arg1) {
					Comparator cmp = Collator
							.getInstance(java.util.Locale.CHINA);
					String name1 = ((Branch) arg0).getName();
					String name2 = ((Branch) arg1).getName();
					return cmp.compare(name1, name2);
				}
			});
		}
		return SUCCESS;
	}
	
	
	
	

	/**
	 * 用户设置区域经理
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "updateareamanager", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String UpdateAreaManager() throws Exception {
		
		try {
			User users=userbiz.findUserById(userId);
			users.setIsManager(isManager);
			users.setUpdaterId(super.getUser().getUserId());
			users.setUpdatedTime(new Date());
			//取消区域经理资格
			userbiz.modify(users);
			//删除区域经理与学习中心关系
			areaManagerBranchbiz.deleteAreaManagerBranchByManagerId(users.getId());
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加区域经理分配学习中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "addmanagerbranch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String AddManagerBranch() throws Exception {
		
		try {
		
			Object [] bids=StringUtil.strToObject(branchIds);
			areaManagerBranchbiz.addAreaManagerBranch(managerId, bids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	

	public User getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(User userinfo) {
		this.userinfo = userinfo;
	}
	public List<User> getUserlst() {
		return userlst;
	}
	public void setUserlst(List<User> userlst) {
		this.userlst = userlst;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public List<Branch> getBranchlst() {
		return branchlst;
	}
	public void setBranchlst(List<Branch> branchlst) {
		this.branchlst = branchlst;
	}
	public PageResult<User> getResult() {
		return result;
	}
	public void setResult(PageResult<User> result) {
		this.result = result;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getIsManager() {
		return isManager;
	}

	public void setIsManager(int isManager) {
		this.isManager = isManager;
	}

	public List<AreaManagerBranch> getAreaManagerBranchlst() {
		return areaManagerBranchlst;
	}

	public void setAreaManagerBranchlst(List<AreaManagerBranch> areaManagerBranchlst) {
		this.areaManagerBranchlst = areaManagerBranchlst;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getBranchIds() {
		return branchIds;
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}
	
	
	

}
