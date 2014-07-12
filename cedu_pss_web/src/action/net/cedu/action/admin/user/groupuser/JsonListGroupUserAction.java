package net.cedu.action.admin.user.groupuser;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UGroupUserBiz;
import net.cedu.entity.admin.UGroupUser;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询用户组用户关系
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonListGroupUserAction extends BaseAction implements ModelDriven<UGroupUser>
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private UGroupUserBiz uGroupUserBiz;
	
	private UGroupUser uGroupUser=new UGroupUser();
	
	private List<UGroupUser> ugulist = new ArrayList<UGroupUser>();
	
	@Action(value="list_group_user", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json",
					"excludeProperties","ugulist.*.user"
					} )})
	public String execute()throws Exception
	{
		ugulist=uGroupUserBiz.findUGroupUserListByCondition(uGroupUser);
		return SUCCESS;
	}

	public List<UGroupUser> getUgulist() {
		return ugulist;
	}

	public UGroupUser getModel() {
		return uGroupUser;
	}
}
