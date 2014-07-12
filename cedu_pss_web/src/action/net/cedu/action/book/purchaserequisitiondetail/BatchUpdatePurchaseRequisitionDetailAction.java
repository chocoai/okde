package net.cedu.action.book.purchaserequisitiondetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.biz.book.CeduBookOrderDetailBiz;
import net.cedu.biz.book.InvoiceBiz;
import net.cedu.biz.book.InvoiceDetailBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.Invoice;
import net.cedu.entity.book.InvoiceDetail;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 批量发货
 * @author YY
 *
 */
public class BatchUpdatePurchaseRequisitionDetailAction  extends BaseAction{
 
	private static final long serialVersionUID = -8652825262425978077L;
	
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz;  //申购单明细业务层
	@Autowired
	private PurchaseRequisitionBiz  purchaseRequisitionBiz; //申购单业务层
	private PurchaseRequisition purchaserequisition; //申购单明细
	@Autowired 
	private CeduBookOrderBiz ceduBookOrderBiz;//订购单业务层
	@Autowired
	private CeduBookOrderDetailBiz ceduBookOrderDetailBiz; //订购单明细业务层
	@Autowired
	private InvoiceBiz invoiceBiz; //总部发货单业务层
	private Invoice invoice =new Invoice() ; //总部发货单实体
	@Autowired
	private InvoiceDetailBiz invoiceDetailBiz; //总部发货单明细业务层
	private InvoiceDetail invoiceDetail=new InvoiceDetail(); //总部发货单明细实体
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	private String batch; //订购单id字符串
	
	@Action(results={@Result(name="success",type="redirect",location="../cedubookorder/index_headquarters_on_behalf")})
	public String execute() throws Exception {
 
		List<PurchaseRequisitionDetail> purchaseDetailList=new ArrayList<PurchaseRequisitionDetail>();//申购单集合
		
		String[] ary =batch.split(","); //转换成数组
		for (int i = 0; i <ary.length ; i++) {      //循环
			//查询申购单集合
			purchaserequisition=purchaseRequisitionBiz.findById(Integer.parseInt(ary[i]));
			//给发货单赋值
			if(purchaserequisition!=null)
			{
				invoice.setAmount(purchaserequisition.getAmount());
				invoice.setBranchId(purchaserequisition.getBranchId());
				//invoice.setCode(buildCodeBiz.getCodes(CodeEnum.CeduBookOrderTB
					//	.getCode(), CodeEnum.CeduBookOrder.getCode()));
				invoice.setCode("cssj"); //测试
				invoice.setCreatedTime(new Date());
				invoice.setCreatorId(this.getUser().getUserId());
				invoice.setDeleteFlag(0);
				invoice.setInvoiceOperator(this.getUser().getUserId());
				invoice.setInvoiceTime(new Date());			 
				invoice.setOrderId(purchaserequisition.getId());
				invoice.setSupplierId(0);//书商id
				invoice.setUpdatedTime(new Date());
				invoice.setUpdaterId(this.getUser().getUserId());
				invoiceBiz.addInvoice(invoice);
			}
			 
					//查询申购单明细集合
					purchaseDetailList=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaserequisition.getId());
					for (PurchaseRequisitionDetail detail : purchaseDetailList) {
						invoiceDetail.setInvoiceId(invoice.getId());
						invoiceDetail.setBranchId(invoice.getBranchId());
						invoiceDetail.setBookId(detail.getBookId());
						invoiceDetail.setBookedTotal(detail.getOrderedAmount());						
						invoiceDetail.setPurchaser(this.getUser().getUserId());
						invoiceDetail.setOrderTime(new Date());
						invoiceDetail.setDeleteFlag(0);
						invoiceDetail.setCreatorId(this.getUser().getUserId());
						invoiceDetail.setCreatedTime(new Date());
						invoiceDetail.setUpdatedTime(new Date());
						invoiceDetail.setUpdater_id(this.getUser().getUserId());
						invoiceDetail.setSendedTotal(detail.getOrderedAmount());
							invoiceDetail.setStatus(Constants.BOOK_STATUS_ORDER_END);
//							detail.setOrderedAmount(detail.getRequiredAmount());
//							detail.setStatus(Constants.BOOK_STATUS_ORDER_END);
//							purchaseRequisitionBiz.UpdateCeduBookByStatus(detail);
//					 
//							invoiceDetail.setSendedTotal(detail.getSendedTotal()+ Integer.parseInt(total[i]));
//							invoiceDetail.setStatus(0);
							//detail.setSendedTotal(detail.getSendedTotal()+ Integer.parseInt(total[i]));
							//ceduBookOrderDetailBiz.UpdateCeduBookByStatus(detail);
 
						invoiceDetailBiz.addInvoiceDetail(invoiceDetail);
						++i;
					 
					
				}
		}
		return SUCCESS;
	}

	public PurchaseRequisition getPurchaserequisition() {
		return purchaserequisition;
	}

	public void setPurchaserequisition(PurchaseRequisition purchaserequisition) {
		this.purchaserequisition = purchaserequisition;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public InvoiceDetail getInvoiceDetail() {
		return invoiceDetail;
	}

	public void setInvoiceDetail(InvoiceDetail invoiceDetail) {
		this.invoiceDetail = invoiceDetail;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}
	
	
	
	
}
