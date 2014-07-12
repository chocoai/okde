package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.biz.enrollment.PolicyFeeDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 收费政策
 * @author lixiaojun
 *
 */
@ParentPackage("json-default")
public class JsonListPolicyFeeAction extends BaseAction
{
	
	// 分页结果
	private PageResult<PolicyFee> result = new PageResult<PolicyFee>();
	
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准
	
	//查询参数
	private int academyId;//院校Id
	private int feesubjectId;//费用科目Id
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目业务接口
	private FeeSubject feeSubject=new FeeSubject();//费用科目实体
	private int id;//费用科目id
	
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准规则业务接口
	private List<FeeStandard> feelist=new ArrayList<FeeStandard>();//收费标准规则集合
	
	
	@Autowired
	private PolicyFeeDetailBiz policyFeeDetailBiz;//收费政策业务接口
	private int count;//数量
	
	//删除参数
	private int policeFeeId;//收费标准Id
	private boolean isfail=false;//返回从参数
	
	/**
	 * 获取收费标准数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_policy_fee_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String policyCount() throws Exception 
	{		
		result.setRecordCount(this.policyFeeBiz.findPolicyFeeCountByDetails(academyId, feesubjectId, result));
		return SUCCESS;
	}
	
	/**
	 * 获取收费标准列表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "list_policy_fee_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json",
			"includeProperties",
			"result.*,feesubjectId,academyId"
	}) })
	public String policyList() throws Exception {
		result.setList(this.policyFeeBiz.findPolicyFeeListByDetails(academyId, feesubjectId, result));
		return SUCCESS;
	}
	 
	/**
	 * 获取费用科目
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_fee_subject_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fndFeeSubject() throws Exception {
		feeSubject=this.feeSubjectBiz.findFeeSubjectById(id);
		return SUCCESS;
	}
	
	/**
	 * 获取单个收费标准下的所有收费规则
	 * @return
	 * @throws Exception
	 */
	@Action(value = "find_fee_standard_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String fndFeestandardlist() throws Exception {
		feelist=this.feeStandardBiz.findFeeStandardListByFeeId(id);
		return SUCCESS;
	}
	
	/**
	 * 获取收费标准挂在政策体上已审批通过的数量
	 * @return
	 * @throws Exception
	 */
	@Action(value = "count_policy_fee_fee_detail_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String policyfeedetiCount() throws Exception 
	{		
		count=this.policyFeeDetailBiz.findPolicyFeeDetailCountByPolicyFeeIdAuditStatus(id, Constants.AUDIT_STATUS_TRUE);
		return SUCCESS;
	}
	
	/**
	 * 删除收费标准及其明细
	 * @return
	 * @throws Exception
	 */
	@Action(value = "delete_policy_fee_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String policyfeedel() throws Exception 
	{		
		isfail=this.policyFeeBiz.deletePolicyFeeAndFeeStandById(policeFeeId);
		return SUCCESS;
	}
	
	public PageResult<PolicyFee> getResult() {
		return result;
	}

	public void setResult(PageResult<PolicyFee> result) {
		this.result = result;
	}

	public int getAcademyId() {
		return academyId;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public int getFeesubjectId() {
		return feesubjectId;
	}

	public void setFeesubjectId(int feesubjectId) {
		this.feesubjectId = feesubjectId;
	}

	public FeeSubject getFeeSubject() {
		return feeSubject;
	}

	public void setFeeSubject(FeeSubject feeSubject) {
		this.feeSubject = feeSubject;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<FeeStandard> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<FeeStandard> feelist) {
		this.feelist = feelist;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPoliceFeeId() {
		return policeFeeId;
	}

	public void setPoliceFeeId(int policeFeeId) {
		this.policeFeeId = policeFeeId;
	}

	public boolean isIsfail() {
		return isfail;
	}

	public void setIsfail(boolean isfail) {
		this.isfail = isfail;
	}
	
}
