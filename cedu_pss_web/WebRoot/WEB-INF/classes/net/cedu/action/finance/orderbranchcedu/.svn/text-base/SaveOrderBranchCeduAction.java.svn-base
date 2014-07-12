package net.cedu.action.finance.orderbranchcedu;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.BranchEnum;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;


/**
 * 汇款总部单 保存(新建)
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json")
})
public class SaveOrderBranchCeduAction extends BaseAction implements ModelDriven<OrderBranchCedu>
{
	private static final long serialVersionUID = 2662391448744405565L;
	
	private int branchId; //学习中心ID
	private int[] detailIds; //缴费单明细ID
	
	private Branch branch; //当前学习中心
	
	private OrderBranchCedu order = new OrderBranchCedu();
	
	private int orderId;
	
	@Autowired
	private BranchBiz branchBiz;
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;
	@Autowired
	private BuildCodeBiz buildCodeBiz;
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;

	public String execute() throws Exception
	{
		branch = branchBiz.findBranchById(getUser().getOrgId());
		
		List<FeePaymentDetail> feePaymentDetailList = new LinkedList<FeePaymentDetail>();
		
		BigDecimal amount = new BigDecimal(0);
		
		for(int id : detailIds){ //遍历选中的缴费单明细
			FeePaymentDetail detail = feePaymentDetailBiz.findFeePaymentDetailByFeePaymentId(id);

			amount = amount.add(new BigDecimal(detail.getAmountPaied())); //累加实缴金额
			amount = amount.add(new BigDecimal(detail.getRechargeAmount())); //累加充值金额
			
			
			feePaymentDetailList.add(detail); //存储，下面用来更新其状态并设置其中的汇款中心单ID
		}
		
		
		order.setAmount(amount); //设置总金额
		
		//生成Code
		String code = buildCodeBiz.getCodes(CodeEnum.OrderBranchCeduTB.getCode(),CodeEnum.OrderBranchCedu.getCode());
		order.setCode(code);
		
		// 汇/收款双方ID
		order.setRemitterId(branchId);
		order.setRemitteeId(BranchEnum.Admin.value());
		
		//设置 汇款总部单 初始状态为  已填汇款单
		order.setStatus(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN);
		
		// 执行添加操作
		orderId = orderBranchCeduBiz.add(order, feePaymentDetailList, 0);
		
		return SUCCESS;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public Branch getBranch() {
		return branch;
	}

	public OrderBranchCedu getModel() {
		return order;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setDetailIds(int[] detailIds) {
		this.detailIds = detailIds;
	}
}
