package net.cedu.action.enrollment.enrollquota;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.BranchEnrollQuotaBiz;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 刷新该批次下没有的学习中心
 * @author xiao
 *
 */

@ParentPackage("json-default")
public class JsonRefreshBranchEnrollQuotaAction extends BaseAction
{

	@Autowired
	private BranchEnrollQuotaBiz branchEnrollQuotaBiz;//学习中心指标_业务层接口
	
	private int batchId;//批次
	
	private int count;//返回参数
	
	/**
	 * 刷新该批次下没有的学习中心
	 * @return
	 * @throws Exception
	 */
	@Action(value = "refresh_branch_enroll_quota_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String refreshBeqa() throws Exception 
	{
		count=this.branchEnrollQuotaBiz.addRefreshBranchEnrollQuotaBybatchId(batchId, super.getUser().getUserId());
		return SUCCESS;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
