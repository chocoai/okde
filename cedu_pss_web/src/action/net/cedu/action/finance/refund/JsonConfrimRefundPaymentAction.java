package net.cedu.action.finance.refund;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.biz.finance.FeePaymentDetailBiz;
import net.cedu.biz.finance.StudentAccountAmountManagementBiz;
import net.cedu.biz.finance.StudentAccountManagementBiz;
import net.cedu.common.Constants;
import net.cedu.common.date.DateUtil;
import net.cedu.entity.finance.FeePayment;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.StudentAccountAmountManagement;
import net.cedu.entity.finance.StudentAccountManagement;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 审批退费单
 * @author xiao
 *
 */
@ParentPackage("json-default")
public class JsonConfrimRefundPaymentAction extends BaseAction
{
	@Autowired
	private FeePaymentBiz feePaymentBiz;//缴费单业务层接口
	private FeePayment feePayment=new FeePayment();//缴费单实体
	private int feePaymentId;//缴费单Id
	private int types;//审批状态
	
	@Autowired
	private FeePaymentDetailBiz feePaymentDetailBiz;//缴费单明细业务层接口
	private List<FeePaymentDetail> feePaymentDetailList=new ArrayList<FeePaymentDetail>();//缴费单明细实体
	
	@Autowired
	private StudentAccountAmountManagementBiz studentAccountAmountManagementBiz;//学生充值账户明细业务接口
	
	@Autowired
	private StudentAccountManagementBiz studentAccountManagementBiz;//学生充值账户_业务层接口
	
	//返回值
	private boolean isback=false;
	
	/**
	 * 审批退费单，待修改
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "confirm_refund_payment_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String showsaafa() throws Exception 
	{
		if(feePaymentId!=0)
		{
			//退费单
			feePayment=this.feePaymentBiz.findFeePaymentById(feePaymentId);
			//历史缴费单
			List<FeePaymentDetail> historyfpdlist=new ArrayList<FeePaymentDetail>();
			//充值账户明细
			List<StudentAccountAmountManagement> saamlist=new ArrayList<StudentAccountAmountManagement>();
			if(feePayment!=null && feePayment.getStatus()>Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN)
			{
				//feePayment.setStatus(types);
				boolean isstatus=false;//判断退费单状态
				feePayment.setUpdaterId(super.getUser().getUserId());
				feePayment.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
				//退费单明细
				feePaymentDetailList=this.feePaymentDetailBiz.findFeePaymentDetailListByFeePaymentId(feePayment.getId());
				//重新计算退费的值
				if(feePaymentDetailList!=null && feePaymentDetailList.size()>0)
				{
					String allmoneyc="0";
					for(FeePaymentDetail fpd:feePaymentDetailList)
					{
						//fpd.setStatus(types);
						fpd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
						fpd.setUpdaterId(super.getUser().getUserId());
						allmoneyc="0";
						if(fpd.getSupperId()!=0 && this.feePaymentDetailBiz.findById(fpd.getSupperId())!=null)
						{
							FeePaymentDetail feepd=this.feePaymentDetailBiz.findById(fpd.getSupperId());
							allmoneyc=(new BigDecimal(feepd.getBranchAccount()).add(new BigDecimal(feepd.getCeduAccount())).add(new BigDecimal(feepd.getAcademyAccount())).add(new BigDecimal(feepd.getChannelAccount()))).toString();
							if(Double.valueOf(allmoneyc)-0==0)
							{
								allmoneyc="1";
							}
							//String payAcademyCedu="payAcademyCedu";//总部退学生金额
							//String payCeduAcademy="payCeduAcademy";//院校退学生金额
							//String paymentByChannel="paymentByChannel";//和作方退总部金额
							//String payBranchCedu="payBranchCedu";//中心退学生金额
							fpd.setCeduAccount(0-(new BigDecimal(feepd.getCeduAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							fpd.setAcademyAccount(0-(new BigDecimal(feepd.getAcademyAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							fpd.setChannelAccount(0-(new BigDecimal(feepd.getChannelAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							fpd.setBranchAccount(0-(new BigDecimal(feepd.getBranchAccount()).multiply(new BigDecimal(0-fpd.getAmountPaied())).divide(new BigDecimal(allmoneyc),2,BigDecimal.ROUND_HALF_UP)).doubleValue());
							//记录原缴费单明细的状态、退费基数
							fpd.setSupperStatus(feepd.getStatus());
							fpd.setRefundBase(Double.valueOf(allmoneyc));
							//判断原单据的状态从而判断退费单明细状态
							if(types==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
							{
								if(feepd.getStatus()==Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN || feepd.getStatus()==Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN || feepd.getStatus()==Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO || feepd.getStatus()==Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN || feepd.getStatus()==Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO)
								{
									fpd.setStatus(types);
									feepd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
								}
								else
								{
									fpd.setStatus(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
									isstatus=true;
								}
							}
							else
							{
								fpd.setStatus(types);
								feepd.setRefundLock(Constants.LOCKING_FALSE);//解除原始缴费单的锁定
							}
							
							//账户金额要变动
							if(types==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
							{
								feepd.setCeduAccount((new BigDecimal(feepd.getCeduAccount()).add(new BigDecimal(fpd.getCeduAccount()))).doubleValue());
								feepd.setAcademyAccount((new BigDecimal(feepd.getAcademyAccount()).add(new BigDecimal(fpd.getAcademyAccount()))).doubleValue());
								feepd.setChannelAccount((new BigDecimal(feepd.getChannelAccount()).add(new BigDecimal(fpd.getChannelAccount()))).doubleValue());
								feepd.setBranchAccount((new BigDecimal(feepd.getBranchAccount()).add(new BigDecimal(fpd.getBranchAccount()))).doubleValue());
								feepd.setRefundAmount((new BigDecimal(feepd.getRefundAmount()).add(new BigDecimal(0-fpd.getAmountPaied()))).doubleValue());
							}
							//feepd.setRefundLock(Constants.LOCKING_FALSE);
							feepd.setUpdatedTime(DateUtil.getNowTimestamp("yyyy-MM-dd HH:mm:ss"));
							feepd.setUpdaterId(super.getUser().getUserId());
							historyfpdlist.add(feepd);
						}
					}
				}
				//退费单状态
				if(isstatus)
				{
					feePayment.setStatus(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN);
				}
				else
				{
					feePayment.setStatus(types);
				}
				//学生充值账户
				StudentAccountManagement studentAccountManagement = this.studentAccountManagementBiz
						.updateStudentAccountManagementByStudentIdForFee(
								feePayment.getStudentId(),
								super.getUser().getUserId());
				BigDecimal allacountmoney=new BigDecimal(0);
				
				//充值账户明细状态修改
				saamlist=this.studentAccountAmountManagementBiz.findStudentAccountAmountManagementListByFeePaymentIdTypes(feePayment.getId(), Constants.STATUS_APPLY_CONSUMPTION_TRUE);
				if(saamlist!=null && saamlist.size()>0)
				{
					for(StudentAccountAmountManagement saam:saamlist)
					{
						if(types==Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN)
						{
							saam.setTypes(Constants.STATUS_APPLY_CONSUMPTION_TRUE);
							allacountmoney=allacountmoney.add(saam.getAccountMoney());
						}
						else
						{
							saam.setTypes(Constants.STATUS_APPLY_CONSUMPTION_FALSE);
						}
					}
				}
				studentAccountManagement
				.setAccountBalance(studentAccountManagement
						.getAccountBalance().subtract(allacountmoney));
				
				isback=this.feePaymentBiz.updateRefundPaymentForConfirm(feePayment, feePaymentDetailList,studentAccountManagement, saamlist, historyfpdlist);
			}
		}
		return SUCCESS;
	}

	public FeePayment getFeePayment() {
		return feePayment;
	}

	public void setFeePayment(FeePayment feePayment) {
		this.feePayment = feePayment;
	}

	public int getFeePaymentId() {
		return feePaymentId;
	}

	public void setFeePaymentId(int feePaymentId) {
		this.feePaymentId = feePaymentId;
	}

	public List<FeePaymentDetail> getFeePaymentDetailList() {
		return feePaymentDetailList;
	}

	public void setFeePaymentDetailList(List<FeePaymentDetail> feePaymentDetailList) {
		this.feePaymentDetailList = feePaymentDetailList;
	}

	public boolean isIsback() {
		return isback;
	}

	public void setIsback(boolean isback) {
		this.isback = isback;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}
	
}
