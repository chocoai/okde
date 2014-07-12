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

/**
 * 修改用户组用户关系
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonUpdateGroupUserAction extends BaseAction
{
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private UGroupUserBiz uGroupUserBiz;
	
	private List<Integer> uidlist = new ArrayList<Integer>();
	private int gId;
	private boolean results=false;
	
	@Action(value="update_group_user", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()
	{
		try
		{
			UGroupUser ugu=new UGroupUser();
			ugu.setGroupId(gId);
			uGroupUserBiz.deleteByGroupId(ugu);
			for(int i=0,j=uidlist.size();i<j;i++)
			{
				ugu.setUserId(uidlist.get(i));
				uGroupUserBiz.createNew(ugu);
			}
			results=true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public void setUidlist(List<Integer> uidlist) {
		this.uidlist = uidlist;
	}

	public void setGId(int gId) {
		this.gId = gId;
	}

	public boolean getResults() {
		return results;
	}
}
