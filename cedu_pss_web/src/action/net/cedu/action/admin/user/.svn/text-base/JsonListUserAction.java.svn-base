package net.cedu.action.admin.user;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.string.PingYingHanZiUtil;
import net.cedu.entity.admin.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 根据条件查询用户列表
 * 
 * @return
 * @throws Exception
 */
@ParentPackage("json-default")
public class JsonListUserAction extends BaseAction implements ModelDriven<User> {
	private static final long serialVersionUID = -1813626596721638819L;

	@Autowired
	private UserBiz userBiz;

	private User users = new User();

	private List<User> ulist = new ArrayList<User>();

	@Action(value = "list_user", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"ulist.*",
			"excludeProperties",
			"ulist.*.createdTime,ulist.*.introduction,ulist.*.creatorId," +
			"ulist.*.deleteFlag,ulist.*.email,ulist.*.password," +
			"ulist.*.academylst,ulist.*.code,ulist.*.department," +
			"ulist.*.departmentId,ulist.*.job," +
			"ulist.*.jobId,ulist.*.org,ulist.*.orgId," +
			"ulist.*.photoUrl,ulist.*.status,ulist.*.telephone," +
			"ulist.*.updatedTime,ulist.*.updatePasswordTime,ulist.*.mobile," +
			"ulist.*.type,ulist.*.userName," +
			"ulist.*.address,department.*.updaterId,ulist.*.updaterId,"  
 
			}) })
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		if (users.getOrgId() != 0) {

		} else {
			if (this.getUser().getOrgId() == BranchEnum.Admin.value()
					|| this.getUser().getOrgId() == BranchEnum.Root.value()) {
				users.setOrgId(-2);
			} else {
				users.setOrgId(this.getUser().getOrgId());
			}
		}
		ulist = userBiz.findUsersByCondition(users);
		if (ulist != null) {
			for (User user : ulist) {
				user.setFullName(PingYingHanZiUtil
						.getNameFirstZiMuToLowerCase(user.getFullName())
						+ user.getFullName());
			}
		}

		Collections.sort(ulist, new Comparator() {
			public int compare(Object obj0, Object obj1) {
				Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
				String name1 = ((User) obj0).getFullName();
				String name2 = ((User) obj1).getFullName();
				return cmp.compare(name1, name2);
			}
		});
		return SUCCESS;
	}

	public List<User> getUlist() {
		return ulist;
	}

	public User getModel() {
		return users;
	}

	public User getUsers() {
		return users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

}
