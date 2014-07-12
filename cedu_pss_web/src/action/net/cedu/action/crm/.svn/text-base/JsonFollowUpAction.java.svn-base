package net.cedu.action.crm;

import java.util.HashMap;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.FollowUpBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.crm.FollowUp;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生跟进Action
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonFollowUpAction extends BaseAction {

	@Autowired
	private FollowUpBiz followUpBiz;
	@Autowired
	private StudentBiz studentBiz;
	/**
	 * 查询条件
	 */
	private FollowUp followUp;

	// 分页结果
	private PageResult<FollowUp> result = new PageResult<FollowUp>();
	// 状态统计数
	private Map<String, Integer> statusCountList = new HashMap<String, Integer>();

	/**
	 * 查询跟进总数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_followup_count", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmFollowUpCount() throws Exception {
		// 查询数量
		result.setRecordCount(followUpBiz.findFollowUpsPageCount(followUp,
				result));
		return SUCCESS;
	}

	/**
	 * 查询跟进集合通过条件
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "crm_followup_list", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String crmFollowUpList() throws Exception {
		// 查询集合
//		result.setOrder("createdTime");
//		result.setSort("desc");
		result.setList(followUpBiz.findFollowUpsPageList(followUp, result));
		return SUCCESS;
	}

	/**
	 * 查询状态数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "search_status_counts", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String searchStatusCounts() throws Exception {
		//已分配
		statusCountList.put("status_"+Constants.STU_CALL_STATUS_YI_FENG_PEI, studentBiz.searchStatusCounts(super.getUser().getUserId(), Constants.STU_CALL_STATUS_YI_FENG_PEI, -1));
		//无意愿
		statusCountList.put("status_"+Constants.STU_CALL_STATUS_WU_YI_YUAN, studentBiz.searchStatusCounts(super.getUser().getUserId(), Constants.STU_CALL_STATUS_WU_YI_YUAN, -1));
		//跟进中
		statusCountList.put("status_"+Constants.STU_CALL_STATUS_GENG_JIN_ZHONG, studentBiz.searchStatusCounts(super.getUser().getUserId(), Constants.STU_CALL_STATUS_GENG_JIN_ZHONG, -1));
		//已报名
		statusCountList.put("status_"+Constants.STU_CALL_STATUS_YI_DAO_MING, studentBiz.searchStatusCounts(super.getUser().getUserId(), Constants.STU_CALL_STATUS_YI_DAO_MING, -1));
		return SUCCESS;
	}

	public FollowUp getFollowUp() {
		return followUp;
	}

	public void setFollowUp(FollowUp followUp) {
		this.followUp = followUp;
	}

	public PageResult<FollowUp> getResult() {
		return result;
	}

	public void setResult(PageResult<FollowUp> result) {
		this.result = result;
	}

	public Map<String, Integer> getStatusCountList() {
		return statusCountList;
	}

	public void setStatusCountList(Map<String, Integer> statusCountList) {
		this.statusCountList = statusCountList;
	}

}
