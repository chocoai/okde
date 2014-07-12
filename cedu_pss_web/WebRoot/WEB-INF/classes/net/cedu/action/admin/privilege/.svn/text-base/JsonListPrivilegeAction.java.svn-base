package net.cedu.action.admin.privilege;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.privilege.PrivilegeBiz;
import net.cedu.entity.admin.privilege.Privilege;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询权限集列表
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonListPrivilegeAction extends BaseAction implements ModelDriven<Privilege>
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private PrivilegeBiz privilegeBiz;
	
	private Privilege privilege=new Privilege();
	
	private List<Privilege> plist = new ArrayList<Privilege>();
	
	@Action(value="list_privilege", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()throws Exception
	{
		plist=privilegeBiz.findListByCondition(privilege);
		return SUCCESS;
	}

	public List<Privilege> getPlist() {
		return plist;
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
