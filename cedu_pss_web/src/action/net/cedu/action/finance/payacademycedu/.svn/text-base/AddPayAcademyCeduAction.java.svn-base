package net.cedu.action.finance.payacademycedu;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.finance.PayAcademyCeduBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.academy.Academy;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.PayAcademyCedu;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 院校反款单 添加操作
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="input", type="dispatcher", location="add_pay_academy_cedu.jsp"),
	@Result(name="success", type="json")
})
public class AddPayAcademyCeduAction extends BaseAction
{
	private static final long serialVersionUID = 6192176667579423687L;

	private Branch cedu; //总部信息
	
	private List<Academy> academies;
	
	private int academyId; // 院校ID
	private int[] paymentDetailIds; // 缴费单明细IDs
	private int[] otherPaymentIds; //其他应收院校款IDs
	private String note; //备注
	
	private int payAcademyCeduId;

	@Autowired
	private AcademyBiz academyBiz;
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private PayAcademyCeduBiz payAcademyCeduBiz;
	
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	
	@Override
	public String execute() throws Exception
	{
		if(isGetRequest()){
			return doView();
		}
		
		return doSave();
	}
	
	// 显示添加界面
	private String doView() throws Exception {
		academies = academyBiz.findAllAcademyByFlagFalse();
		
		cedu = branchBiz.findBranchById(BranchEnum.Admin.value());
		
		return INPUT;
	}
	
	// 执行数据保存(添加)
	private String doSave() throws Exception{
		String code = buildCodeBiz.getCodes(CodeEnum.PayAcademyCeduTB.getCode(), CodeEnum.PayAcademyCedu.getCode());
		
		PayAcademyCedu rebateBill = new PayAcademyCedu();

		rebateBill.setCreatedTime(new Date());
		rebateBill.setCreatorId(getUser().getUserId());
		rebateBill.setDeleteFlag(Constants.DELETE_FALSE);
		rebateBill.setRemittanceDate(new Date());
		rebateBill.setRemittanceNo(code);
		rebateBill.setRemitterId(academyId);
		rebateBill.setUpdatedTime(new Date());
		rebateBill.setUpdaterId(getUser().getUserId());
		rebateBill.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN);
		rebateBill.setNote(note);
		
		payAcademyCeduId = payAcademyCeduBiz.add(rebateBill, paymentDetailIds, otherPaymentIds);
		
		return SUCCESS;
	}

	public List<Academy> getAcademies() {
		return academies;
	}

	public Branch getCedu() {
		return cedu;
	}

	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPaymentDetailIds(int[] paymentDetailIds) {
		this.paymentDetailIds = paymentDetailIds;
	}

	public void setOtherPaymentIds(int[] otherPaymentIds) {
		this.otherPaymentIds = otherPaymentIds;
	}

	public int[] getPaymentDetailIds() {
		return paymentDetailIds;
	}

	public int getPayAcademyCeduId() {
		return payAcademyCeduId;
	}
}
