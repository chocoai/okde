package net.cedu.action.crm;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.UserEnrollQuotaBiz;
import net.cedu.entity.admin.User;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 个人招生指标
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonUserEnrollQuotaAction extends BaseAction {

	@Autowired
	private UserEnrollQuotaBiz userEnrollQuotaBiz;

	private List<User> users = new ArrayList<User>();

	// 批次ID
	private int batchId;

	/**
	 * 增加学生，客服中心
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_user_enroll_quota", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"excludeProperties",
			"users.*.code,users.*.createdTime,users.*.creatorId,users.*.deleteFlag,users.*.department," +
			"users.*.email,users.*.job,users.*.jobId,users.*.mobile," +
			"users.*.org,users.*.orgId,users.*.password,users.*.photoUrl,users.*.status," +
			"users.*.telephone,users.*.type,users.*.updatePasswordTime,users.*.updatedTime,users.*.updaterId," +
			"users.*.academylst.*.account,users.*.academylst.*.address,users.*.academylst.*.auditStatus," +
			"users.*.academylst.*.auditedDate,users.*.academylst.*.auditer,users.*.academylst.*.code," +
			"users.*.academylst.*.foundedYear,users.*.academylst.*.contractEndtime,users.*.academylst.*.createdTime," +
			"users.*.academylst.*.creatorId,users.*.academylst.*.deleteFlag,users.*.academylst.*.interfaceSettingStatus," +
			"users.*.academylst.*.introduction,users.*.academylst.*.isForceFeePolicy,users.*.academylst.*.pictureUrl," +
			"users.*.academylst.*.projectManagerId,users.*.academylst.*.projectManagerName,users.*.academylst.*.rebatesFeesubject," +
			"users.*.academylst.*.scale,users.*.academylst.*.shortName,users.*.academylst.*.telephone," +
			"users.*.academylst.*.updatedTime,users.*.academylst.*.updaterId,users.*.academylst.*.usingStatus," +
			"users.*.academylst.*.website"
	}) })
	public String findUserEnrollQuota() throws Exception {
		users = userEnrollQuotaBiz.findUserEnrollQuotaByBatchIdAndUserId(
				batchId, super.getUser().getUserId(), null);
		return SUCCESS;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

}
