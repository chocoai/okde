package net.cedu.action.finance.payacademycedu;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.model.common.DateTimeRange;
import net.cedu.model.page.PageResult;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 院校返款单列表查询(总数)
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListPayAcademyCeduCountAction extends BaseAction implements ModelDriven<PayAcademyCedu>
{
	private static final long serialVersionUID = -7011039876452219454L;

	private PayAcademyCedu model = new PayAcademyCedu();
	
	private DateTimeRange remittanceDateRange = new DateTimeRange();
	
	private PageResult<PayAcademyCedu> result = new PageResult<PayAcademyCedu>();
	
	@Autowired
	private PayAcademyCeduBiz payAcademyCeduBiz;
	
	@Override
	public String execute() throws Exception
	{
		int count = payAcademyCeduBiz.count(model, remittanceDateRange, result);
		result.setRecordCount(count);
		
		return SUCCESS;
	}

	public PayAcademyCedu getModel() {
		return model;
	}

	public DateTimeRange getRemittanceDateRange() {
		return remittanceDateRange;
	}

	public PageResult<PayAcademyCedu> getResult() {
		return result;
	}
}
