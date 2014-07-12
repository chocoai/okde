package net.cedu.action.admin.privilege;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.entity.admin.privilege.Privilege;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 查询权限数量_json
 * @author Jack
 *
 */
@ParentPackage("json-default")
public class JsonPageCountPrivilegeAction extends BaseAction implements ModelDriven<Privilege> 
{
	private static final long serialVersionUID = -8620752958733775163L;

	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	private Privilege privilege=new Privilege();
	
	// 分页结果
	private PageResult<Privilege> result = new PageResult<Privilege>();
	
	@Action(value = "page_count_privilege", 
			results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" 
			}) })
	public String execute() throws Exception 
	{
		result.setRecordCount(privilegeBiz.findCountByConditionForHQL(privilege));
		return SUCCESS;
	}

	public PageResult<Privilege> getResult() {
		return result;
	}

	public Privilege getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

	public Privilege getModel() {
		return privilege;
	}
}
