package net.cedu.action.finance.orderbranchcedu;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.finance.OrderBranchCeduBiz;
import net.cedu.biz.finance.PaymentBiz;
import net.cedu.common.Constants;
import net.cedu.entity.finance.FeePaymentDetail;
import net.cedu.entity.finance.OrderBranchCedu;

import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 确认汇款总部单（中心，Ajax更新、保存操作）
 * 
 * @author Sauntor
 *
 */
@ParentPackage("json-default")
@Results({
	@Result(name="success", type="json", params={"contentType", "text/html"})
})
public class ConfirmOrderBranchCeduByCeduAction extends BaseAction
{
	private static final long serialVersionUID = 3142930782943357187L;
	private int orderId;
//	private Date remittanceDate;
//	private String orderNo;
	
	private int errno = 0;
	
//	private File orderImage;
//	private String orderImageFileName;
	
	@Autowired
	private OrderBranchCeduBiz orderBranchCeduBiz;
	@Autowired
	private PaymentBiz paymentBiz;

	public String execute() throws Exception {
		OrderBranchCedu order = orderBranchCeduBiz.findById(orderId);
		
		order.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
		
//		order.setOrderNo(orderNo);
//		order.setRemittanceDate(remittanceDate);
		
		List<FeePaymentDetail> fpdList = paymentBiz.findDetailByOrderBranchCeduId(orderId);
		
		Date updatedTime = new Date();
		
		for(FeePaymentDetail fpd : fpdList){
			fpd.setStatus(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN);
			fpd.setUpdatedTime(updatedTime);
			fpd.setUpdaterId(getUser().getUserId());
		}
		
		try{
//			String imageUrl = saveImage();
//			
//			order.setImgUrl(imageUrl);
			
			orderBranchCeduBiz.updateStatus(order, fpdList);
		}
//		catch(IOException ioe){
//			errno = -1;
//		}
		catch (Exception e) {
//			errno = -2;
			errno = -1;
		}
		
		return SUCCESS;
	}
	
//	private String saveImage() throws IOException {
//		String path = ResourcesTool.getText("finance_payment", "order.branchcedu.uploadpath");
//		path = ServletActionContext.getServletContext().getRealPath(path);
//		
//		try {
//			return FileUtil.FileUploads(path, orderImageFileName, orderImage);
//		} catch (Exception e) {
//			throw new IOException();
//		}
//	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	public String getErrMsg(){
		setIl8nName("finance_payment");
		
		return getText("order.branchcedu.cedu.confirm."+errno);
	}

//	public void setOrderImage(File orderImage) {
//		this.orderImage = orderImage;
//	}
//
//	public void setOrderImageFileName(String orderImageFileName) {
//		this.orderImageFileName = orderImageFileName;
//	}

	public int getErrno() {
		return errno;
	}

//	public void setRemittanceDate(Date remittanceDate) {
//		this.remittanceDate = remittanceDate;
//	}
//
//	public void setOrderNo(String orderNo) {
//		this.orderNo = orderNo;
//	}
}
