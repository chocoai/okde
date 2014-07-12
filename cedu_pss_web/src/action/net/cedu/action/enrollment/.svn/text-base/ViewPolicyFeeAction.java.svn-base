package net.cedu.action.enrollment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.biz.enrollment.FeeStandardBiz;
import net.cedu.biz.enrollment.PolicyFeeBiz;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.basesetting.BaseDict;
import net.cedu.entity.basesetting.FeeSubject;
import net.cedu.entity.enrollment.FeeStandard;
import net.cedu.entity.enrollment.PolicyFee;

/**
 * 收费政策标准
 * @author lixiaojun
 *
 */

public class ViewPolicyFeeAction extends BaseAction
{
	
	@Autowired
	private PolicyFeeBiz policyFeeBiz;//收费标准业务接口
	private PolicyFee policyFee=new PolicyFee();//收费业务标准实体
	
	@Autowired
	private BaseDictBiz BaseDictBiz;//基础表业务接口
	private BaseDict baseDict=new BaseDict();//基础表实体
	
	@Autowired 
	private AcademyBiz academyBiz;//院校biz
	private Academy academy=new Academy();//院校实体
	
	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private FeeSubject feeSubject=new FeeSubject();//费用科目实体
	
	@Autowired
	private FeeStandardBiz feeStandardBiz;//收费标准规则业务接口
	private List<FeeStandard> feelist=new ArrayList<FeeStandard>();//收费标准规则集合
	
	//跳转参数
	private int id;//收费标准id
	
	//反应到页面参数
	private int revisedCredit;//学分
	private double creditFee;//金额
	
	public String execute() throws Exception 
	{
		policyFee=this.policyFeeBiz.findPolicyFeeById(id);
		academy=this.academyBiz.findAcademyById(policyFee.getAcademyId());
		baseDict=this.BaseDictBiz.findBaseDictById(policyFee.getModeId());
		feeSubject=this.feeSubjectBiz.findFeeSubjectById(policyFee.getFeeSubjectId());
		feelist=this.feeStandardBiz.findFeeStandardListByFeeId(policyFee.getId());
		if(feelist!=null&&feelist.size()>0)
		{
			revisedCredit=this.feelist.get(0).getRevisedCredit();
			creditFee=this.feelist.get(0).getCreditFee();
		}
		if(super.isGetRequest())
		{	
			return INPUT;
		}
		return SUCCESS;
	}

	public PolicyFee getPolicyFee() {
		return policyFee;
	}

	public void setPolicyFee(PolicyFee policyFee) {
		this.policyFee = policyFee;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BaseDict getBaseDict() {
		return baseDict;
	}

	public void setBaseDict(BaseDict baseDict) {
		this.baseDict = baseDict;
	}

	public Academy getAcademy() {
		return academy;
	}

	public void setAcademy(Academy academy) {
		this.academy = academy;
	}

	public FeeSubject getFeeSubject() {
		return feeSubject;
	}

	public void setFeeSubject(FeeSubject feeSubject) {
		this.feeSubject = feeSubject;
	}

	public List<FeeStandard> getFeelist() {
		return feelist;
	}

	public void setFeelist(List<FeeStandard> feelist) {
		this.feelist = feelist;
	}

	public int getRevisedCredit() {
		return revisedCredit;
	}

	public void setRevisedCredit(int revisedCredit) {
		this.revisedCredit = revisedCredit;
	}

	public double getCreditFee() {
		return creditFee;
	}

	public void setCreditFee(double creditFee) {
		this.creditFee = creditFee;
	}
	
}
