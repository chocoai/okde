package net.cedu.action.finance.branchchannelpayment;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.EnrollmentSourceBiz;
import net.cedu.biz.finance.OrderCeduChannelBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.EnrollmentSource;
import net.cedu.entity.crm.Student;
import net.cedu.entity.finance.OrderCeduChannel;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 添加招生返款单
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
//	@Result(name="input", type="dispathce")
	@Result(name="success", type="json")
})
public class PayBranchChannelAction extends BaseAction
{
	private static final long serialVersionUID = 4091854905025798002L;
	
	private List<EnrollmentSource> channelTypes;
	private List<Academy> academies;
	
	private Student condition = new Student();
	private int[] fpdIds; //返款的缴费单明细ID列表
	private OrderCeduChannel order = new OrderCeduChannel(); //返款单内容
	private double adjustedAmount = 0D; //调整金额
	
	private int errno = 0;
	
	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private EnrollmentSourceBiz enrollmentSourceBiz;
	@Autowired
	private OrderCeduChannelBiz orderCeduChannelBiz;
	
	@Autowired
	private BranchBiz branchBiz;
	private Branch branch;

	public String execute() throws Exception
	{
		if(isGetRequest()){
			branch = branchBiz.findBranchById(getUser().getOrgId());
			return doView();
		}
		
		return doAdd();
	}
	
	public String doView() throws Exception
	{
		channelTypes = enrollmentSourceBiz.findAllEnrollmentSourceByDeleteFlag();
		academies = academyBiz.findAllAcademyByFlagFalse();
		
		return INPUT;
	}
	
	public String doAdd() throws Exception
	{
		order.setCreatorId(getUser().getUserId());
		order.setUpdaterId(getUser().getUserId());
		
		condition.setBranchId(getUser().getOrgId());
		
		try{
			orderCeduChannelBiz.add(order, adjustedAmount, condition, fpdIds);
		} catch(Exception e){
			errno = -1;
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public List<EnrollmentSource> getChannelTypes() {
		return channelTypes;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public Student getCondition() {
		return condition;
	}

	public OrderCeduChannel getOrder() {
		return order;
	}

	public int getErrno() {
		return errno;
	}

	public void setFpdIds(int[] fpdIds) {
		this.fpdIds = fpdIds;
	}

	public void setAdjustedAmount(double adjustedAmount) {
		this.adjustedAmount = adjustedAmount;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
}
