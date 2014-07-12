package net.cedu.action.enrollment.academyrebatepolicy;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.enrollment.AcademyRebatePolicyDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.enrollment.AcademyRebatePolicyDetail;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 院校返款政策明细列表查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({@Result(name="success", type="json"),@Result(name="error", type="json")})
public class ListDataAcademyRebatePolicyDetailAction extends BaseAction
implements ModelDriven<AcademyRebatePolicyDetail>
{
	/**
	 * @date 2011-08-05 14:16
	 */
	private static final long serialVersionUID = -1696834930973699618L;

	private AcademyRebatePolicyDetail condition = new AcademyRebatePolicyDetail();
	
	// 分页结果
	private PageResult<AcademyRebatePolicyDetail> result = new PageResult<AcademyRebatePolicyDetail>();

	@Autowired
	private AcademyRebatePolicyDetailBiz academyRebatePolicyDetailBiz;
	
	@Override
	public String execute() throws Exception
	{
		condition.setDeleteFlag(Constants.DELETE_FALSE);
		List<AcademyRebatePolicyDetail> list = academyRebatePolicyDetailBiz.findWrappedARPDByCondition(condition, result);
		result.setList(list);
	
		return SUCCESS;
	}
	
	public PageResult<AcademyRebatePolicyDetail> getResult() {
		return result;
	}
	
	public AcademyRebatePolicyDetail getModel() {
		return condition;
	}
}