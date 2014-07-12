package net.cedu.action.enrollment.chnlplcystd;

import java.util.Iterator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.ChannelPolicyBiz;
import net.cedu.biz.enrollment.ChannelPolicyDetailStandardBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.ChannelPolicy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 招生返款政策标准 列表查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="input", type="dispatcher", location="list_channel_policy_standard.jsp"),
	@Result(name="success", type="json"),
	@Result(name="error", type="json")
})
public class ListChnlPlcyStdAction extends BaseAction
{
	private static final long serialVersionUID = -5227388966391326495L;

	/* 查询条件 */
	private int academyId;
	private int channelTypeId;
	private int feeSubjectId;
	private int branchId;
	
	private PageResult<ChannelPolicy> result = new PageResult<ChannelPolicy>();
	
	@Autowired
	private ChannelPolicyBiz channelPolciyBiz;

	public String execute() throws Exception
	{	
		try {
			ChannelPolicy condition = new ChannelPolicy();
			condition.setAcademyId(academyId);
			condition.setFeeSubjectId(feeSubjectId);
			condition.setChannelTypeId(channelTypeId);
			if(branchId>0)
			{
				condition.setBranchIds("#"+branchId+"#");
			}
			List<ChannelPolicy> list = channelPolciyBiz.findAndWrapp(condition, result);
			
			result.setList(list);
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setChannelTypeId(int channelTypeId) {
		this.channelTypeId = channelTypeId;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
	
	public PageResult<ChannelPolicy> getResult() {
		return result;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
}
