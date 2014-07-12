package net.cedu.action.admin.privilege.privilegeset;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeSetBiz;
import net.cedu.entity.admin.privilege.PrivilegeSet;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 查询模块数量_json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountPrivilegeSetAction extends BaseAction implements ModelDriven<PrivilegeSet> 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private PrivilegeSetBiz privilegeSetBiz;
	
	private PrivilegeSet privilegeSet=new PrivilegeSet();
	
	// 分页结果
	private PageResult<PrivilegeSet> result = new PageResult<PrivilegeSet>();
	
	@Action(value = "page_count_privilegeset", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(privilegeSetBiz.findCountByConditionForHQL(privilegeSet));
		return SUCCESS;
	}

	public PageResult<PrivilegeSet> getResult() {
		return result;
	}

	public PrivilegeSet getPrivilegeSet() {
		return privilegeSet;
	}

	public void setPrivilegeSet(PrivilegeSet privilegeSet) {
		this.privilegeSet = privilegeSet;
	}

	public PrivilegeSet getModel() {
		return privilegeSet;
	}
}
