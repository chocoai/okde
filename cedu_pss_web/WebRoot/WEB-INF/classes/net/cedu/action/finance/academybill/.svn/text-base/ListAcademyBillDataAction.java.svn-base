package net.cedu.action.finance.academybill;

import java.util.Iterator;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.finance.PlanedAcademyBillBiz;
import net.cedu.common.Constants;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.finance.PlanedAcademyBill;
import net.cedu.model.page.PageResult;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;

/**
 * 应收院校款 列表 (数据) Ajax查询
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class ListAcademyBillDataAction extends BaseAction implements ModelDriven<PlanedAcademyBill>
{
	private static final long serialVersionUID = -3314087188895082223L;

	PlanedAcademyBill model = new PlanedAcademyBill();
	
	private PageResult<PlanedAcademyBill> result = new PageResult<PlanedAcademyBill>();
	
	@Autowired
	private PlanedAcademyBillBiz planedAcademyBillBiz;
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BaseDictBiz baseDictBiz;
	@Autowired
	private BranchBiz branchBiz; //学习中心_业务层接口
	
	public String execute() throws Exception
	{
		model.setDeleteFlag(Constants.DELETE_FALSE);
		
		List<PlanedAcademyBill> list = planedAcademyBillBiz.find(model, result);
		
		if(list != null)
		{
			Iterator<PlanedAcademyBill> iter = list.iterator();
			while(iter.hasNext())
			{
				PlanedAcademyBill bill = iter.next();
				
				Academy academy = academyBiz.findAcademyById(bill.getAcademyId());
				bill.setAcademyName(academy.getName());
				
				BaseDict receivedWay = baseDictBiz.findBaseDictById(bill.getReceivedWay());
				bill.setReceivedWayName(receivedWay.getName());
				
				Branch branch=this.branchBiz.findBranchById(bill.getBranchId());
				if(branch!=null)
				{
					bill.setBranchName(branch.getName());
				}
			}
		}
		
		result.setList(list);
		
		return SUCCESS;
	}

	public PlanedAcademyBill getModel() {
		return model;
	}

	public PageResult<PlanedAcademyBill> getResult() {
		return result;
	}
}
