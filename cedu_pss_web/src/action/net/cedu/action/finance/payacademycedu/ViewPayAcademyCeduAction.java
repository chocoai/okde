package net.cedu.action.finance.payacademycedu;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.common.enums.BranchEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.PayAcademyCedu;

import org.springframework.beans.factory.annotation.Autowired;

public class ViewPayAcademyCeduAction extends BaseAction
{
	private static final long serialVersionUID = 6192176667579423687L;

	private Branch cedu;
	
	private int payAcademyCeduId;
	
	private PayAcademyCedu payAcademyCedu;

	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private PayAcademyCeduBiz payAcademyCeduBiz;
	
	@Override
	public String execute() throws Exception
	{
	
		cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
		
		payAcademyCedu = payAcademyCeduBiz.findById(payAcademyCeduId);
		Academy academy=this.academyBiz.findAcademyById(payAcademyCedu.getRemitterId());
		if(payAcademyCedu!=null && academy!=null)
		{
			payAcademyCedu.setRemitterName(academy.getName());
		}
		else
		{
			payAcademyCedu.setRemitterName("");
		}
		return INPUT;
	}


	public Branch getCedu() {
		return cedu;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}

	public void setPayAcademyCeduId(int payAcademyCeduId) {
		this.payAcademyCeduId = payAcademyCeduId;
	}

	public PayAcademyCedu getPayAcademyCedu() {
		return payAcademyCedu;
	}
}
