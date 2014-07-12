package net.cedu.action.finance.payacademycedu;

import java.util.Iterator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz;
import net.cedu.biz.finance.PayCeduAcademyBiz.SearchCase;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.PayCeduAcademy;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 查询 待返款院校打款单
 * @author Sauntor
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListUnrebatedPayCeduAcademy extends BaseAction implements ModelDriven<PayCeduAcademy>
{
	private static final long serialVersionUID = -6059844613066857660L;

	private PayCeduAcademy model = new PayCeduAcademy();
	
	PageResult<PayCeduAcademy> result = new PageResult<PayCeduAcademy>();
	
	private SearchCase searchCase;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private PayCeduAcademyBiz payCeduAcademyBiz;
	
	public String execute() throws Exception
	{
		// 仅筛选出状态为 “已打款院校”的打款单(需要返款的打款单)
//		model.setStatus(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO);
		
		searchCase = SearchCase.AddPayAcademyCedu;
		
		List<PayCeduAcademy> list = payCeduAcademyBiz.find(model, null, result, searchCase);
		
		if(list != null){
			Iterator<PayCeduAcademy> iter = list.iterator();
			while(iter.hasNext()){
				PayCeduAcademy pca = iter.next();
				
				// 收款单位为院校
				Academy academy = academyBiz.findAcademyById(pca.getRemitteeId());
				pca.setRemitteeName(academy.getName());
				
				// 汇款单位为学习中心或总部
				Branch branch = branchBiz.findBranchById(pca.getRemitterId());
				pca.setRemitterName(branch.getName());
				
				// 计算应返款金额
				payCeduAcademyBiz.calculateMoneyToCedu(pca);
			}
		}
		
		result.setList(list);
		
		return SUCCESS;
	}

	public PayCeduAcademy getModel() {
		return model;
	}

	public PageResult<PayCeduAcademy> getResult() {
		return result;
	}

	public void setSearchCase(SearchCase searchCase) {
		this.searchCase = searchCase;
	}
}
