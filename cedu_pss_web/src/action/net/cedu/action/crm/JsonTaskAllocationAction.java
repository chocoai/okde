package net.cedu.action.crm;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.User;
import net.cedu.model.crm.CrmUserModel;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonTaskAllocationAction extends BaseAction {
	@Autowired
	private UserBiz userBiz;
	@Autowired
	private StudentBiz studentBiz;

	// 分页结果
	private PageResult<CrmUserModel> result = new PageResult<CrmUserModel>();
	// 查询条件
	private User userInfo;
	
	private int userId=0;//用户ID
	private int count=0;//分配数量,以及未分配数量
	
	
	/**
	 * 随机分配学生给用户
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_task_random_distribute_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmTaskRandomDistributeStudent() throws Exception {
		String studentIds=studentBiz.randomStudentIdsByCount(super.getUser().getOrgId(), count);
		studentBiz.addUserStudent(userId, studentIds);
		//studentBiz.updateStudentStatus(studentIds, Constants.STU_CALL_STATUS_YI_FENG_PEI);
		return SUCCESS;
	}
	/**
	 * 未分配学生数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_task_no_distribute_student", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmTaskNoDistributeStudent() throws Exception {
		count=studentBiz.findNoDistributeStudentCount(super.getUser().getOrgId());
		return SUCCESS;
	}
	/**
	 * 查询用户总数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_task_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmTaskCount() throws Exception {
		// 查询数量

		PageResult<User> r = new PageResult<User>();
		r.setOrder(result.getOrder());
		r.setPage(result.isPage());
		r.setSort(result.getSort());

		if (userInfo == null) {
			userInfo = new User();
		}
		userInfo.setOrgId(super.getUser().getOrgId());
		userInfo.setIsManager(-1);
		userInfo.setOrgId(super.getUser().getOrgId());
		result.setRecordCount(userBiz.findUserPageCountByBranchId(userInfo, r));
		return SUCCESS;
	}

	/**
	 * 查询用户集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_task_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmTaskList() throws Exception {

		List<CrmUserModel> cuCrmUserModels = null;
		PageResult<User> r = new PageResult<User>();
		r.setCurrentPageIndex(result.getCurrentPageIndex());
		r.setOrder(result.getOrder());
		r.setPage(result.isPage());
		r.setPageSize(result.getPageSize());
		r.setSort(result.getSort());
		if (userInfo == null) {
			userInfo = new User();
		}
		userInfo.setOrgId(super.getUser().getOrgId());
		userInfo.setIsManager(-1);
		
		List<User> users = userBiz.findUserPageListByBranchId(userInfo, r);
		if (users != null && users.size() != 0) {
			cuCrmUserModels = new ArrayList<CrmUserModel>();
			CrmUserModel crmUserModel = null;
			for (User user : users) {
				if (user != null) {
					crmUserModel = new CrmUserModel();
					crmUserModel.setFullName(user.getFullName());
					crmUserModel.setUserId(user.getId());
					crmUserModel.setUserName(user.getUserName());
					crmUserModel.setEmail(user.getEmail());
					crmUserModel.setMobile(user.getMobile());
					crmUserModel.setCreatedTime(user.getCreatedTime());
					// 已分配人数
					crmUserModel.setAssignedCount(studentBiz.distributionStudentCount(user.getId()));
					//无意愿人数
					crmUserModel.setYiyuanCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_WU_YI_YUAN,-1));
					//已报名人数
					crmUserModel.setBaomingCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_YI_DAO_MING,-1));
					//跟进中等级为所有
					crmUserModel.setFollowUpAllCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,-1));
					//跟进中等级为A
					crmUserModel.setFollowUpACount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,Constants.CALL_GRADE_A));
					//跟进中等级为B
					crmUserModel.setFollowUpBCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,Constants.CALL_GRADE_B));
					//跟进中等级为C
					crmUserModel.setFollowUpCCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,Constants.CALL_GRADE_C));
					//跟进中等级为D
					crmUserModel.setFollowUpDCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,Constants.CALL_GRADE_D));
					//跟进中等级为E
					crmUserModel.setFollowUpECount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,Constants.CALL_GRADE_E));
					//跟进中等级为F
					crmUserModel.setFollowUpFCount(studentBiz.searchStatusCounts(user.getId(),Constants.STU_CALL_STATUS_GENG_JIN_ZHONG,Constants.CALL_GRADE_F));
					cuCrmUserModels.add(crmUserModel);
				}
			}
		}
		// 查询集合
		result.setList(cuCrmUserModels);
		return SUCCESS;
	}

	public PageResult<CrmUserModel> getResult() {
		return result;
	}

	public void setResult(PageResult<CrmUserModel> result) {
		this.result = result;
	}

	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
