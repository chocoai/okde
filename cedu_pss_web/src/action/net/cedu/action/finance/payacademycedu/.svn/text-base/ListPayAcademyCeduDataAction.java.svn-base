package net.cedu.action.finance.payacademycedu;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.finance.PayAcademyCedu;
import net.cedu.model.common.DateTimeRange;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 院校返款单列表查询(列表数据)
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListPayAcademyCeduDataAction extends BaseAction implements ModelDriven<PayAcademyCedu>
{
	private static final long serialVersionUID = -2728389705478923250L;

	private PayAcademyCedu model = new PayAcademyCedu();
	
	private DateTimeRange remittanceDateRange = new DateTimeRange();
	
	private PageResult<PayAcademyCedu> result = new PageResult<PayAcademyCedu>();
	
	@Autowired
	private PayAcademyCeduBiz payAcademyCeduBiz;
	@Autowired
	private AcademyBiz academyBiz;
	
	public String execute() throws Exception
	{
		List<PayAcademyCedu> list = payAcademyCeduBiz.find(model, remittanceDateRange, result);
		
		if(list != null){
			for(PayAcademyCedu pac : list){
				Academy academy = academyBiz.findAcademyById(pac.getRemitterId());
				if(academy != null){
					pac.setRemitterName(academy.getName());
				}
			}
		}
		
		result.setList(list);
		
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
