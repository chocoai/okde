package net.cedu.action.book.invoicedetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.biz.book.CeduBookOrderDetailBiz;
import net.cedu.biz.book.InvoiceBiz;
import net.cedu.biz.book.InvoiceDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.BookSupplier;
import net.cedu.entity.book.CeduBookOrder;
import net.cedu.entity.book.CeduBookOrderDetail;
import net.cedu.entity.book.Invoice;
import net.cedu.entity.book.InvoiceDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 增加中心发货单
 * 
 * @作者： 杨阳
 * @作成时间：2012-2-27 下午04:22:44
 * 
 * @修改者：
 * @修改内容：
 * @修改时间：
 */
public class AddCenterInvoiceDetailAction extends BaseAction {

	private static final long serialVersionUID = -3475328381795923447L;

	@Autowired
	private InvoiceBiz invoiceBiz; // 发货单业务层
	@Autowired
	private InvoiceDetailBiz invoiceDetailBiz; // 发货单明细业务层
	@Autowired
	private CeduBookOrderBiz ceduBookOrderBiz; // 总部订购单业务层

	@Autowired
	private CeduBookOrderDetailBiz ceduBookOrderDetailBiz; // 总部订购单明细业务层
	@Autowired
	private BookBiz bookBiz; // 教材业务层
	@Autowired
	private BookSupplierBiz bookSupplierBiz; // 书商
	@Autowired
	private BranchBiz branchBiz; // 学习中心
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	private Invoice invoice=new Invoice(); // 发货单实体

	private InvoiceDetail invoiceDetail=new InvoiceDetail(); // 发货单明细实体

	private CeduBookOrder ceduBookOrder=new CeduBookOrder(); // 订购单实体
	private String invoiceCode; // 发货单编号

	private List<CeduBookOrderDetail> ceduBookOrderDetailList = new ArrayList<CeduBookOrderDetail>(); // 订购单实体明集合

	private String orderCode;// 订购单编号

	private String begintotal; // 获取先发货数量字符串

	// 先查询出订购单明细
	@Action(value = "find_cedubook_detail_center", results = { @Result(name = "success", location = "add_invoice_detail_center.jsp") })
	public String find() {
		try {
			//查询订购单
			ceduBookOrder = ceduBookOrderBiz
					.findOrderCedeByCeduBookOrder(orderCode);
			BookSupplier supplier = null;
			if (null != ceduBookOrder) {
				//查询书商
				supplier = bookSupplierBiz.findBookSupplierById(ceduBookOrder
						.getSupplierId());
				ceduBookOrder.setSuppilername(supplier.getName());
				ceduBookOrder.setBranchname(branchBiz.findBranchById(
						ceduBookOrder.getBranchId()).getName());

				invoiceCode = (buildCodeBiz.getCodes(CodeEnum.CeduBookOrderTB
						.getCode(), CodeEnum.CeduBookOrder.getCode()));
			}
			if (null != ceduBookOrder) {
				//查询订购单明细集合
				ceduBookOrderDetailList = ceduBookOrderDetailBiz
						.findorderIdByCeduBookOrderDetail(ceduBookOrder.getId());
				for (CeduBookOrderDetail detail : ceduBookOrderDetailList) {
					Book book = bookBiz.findBookById(detail.getBookId());
					detail.setSuppilername(supplier.getName());
					detail.setOrdercode(ceduBookOrder.getCode());
					detail.setBookname(book.getName());
					if (detail.getStatus() == 0) {
						detail.setStatusname("发货中");
					} else {
						detail.setStatusname("发货完成");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	// 增加
	@Action(value = "add_invoice_detail_center", results = {
			@Result(name = "success", type = "redirect", location = "../invoice/index_invoice"),
			@Result(name = "input", location = "add_invoice_detail_center.jsp") })
	public String execute() {
		try {
			if (this.isGetRequest()) {
				return INPUT;
			}
			//查询订购单和订购单明细集合
			ceduBookOrder = ceduBookOrderBiz
					.findOrderCedeByCeduBookOrder(orderCode);
			if (null != ceduBookOrder) {
				ceduBookOrderDetailList = ceduBookOrderDetailBiz
						.findorderIdByCeduBookOrderDetail(ceduBookOrder.getId());
			}
			//给发货单赋值并增加
			if (null != ceduBookOrder && null != ceduBookOrderDetailList) {
				if (null != begintotal) {
					invoice.setAmount(ceduBookOrder.getAmount());
					invoice.setBranchId(ceduBookOrder.getBranchId());
					invoice.setCode(invoiceCode);
					invoice.setCreatedTime(new Date());
					invoice.setCreatorId(this.getUser().getUserId());
					invoice.setDeleteFlag(0);
					invoice.setInvoiceOperator(this.getUser().getUserId());
					invoice.setInvoiceTime(new Date());
					invoice.setOrderId(ceduBookOrder.getId());
					invoice.setSupplierId(ceduBookOrder.getSupplierId());
					invoice.setUpdatedTime(new Date());
					invoice.setUpdaterId(this.getUser().getUserId());
					if (null != invoice) {
						invoiceBiz.addInvoice(invoice);
					}
					String[] total = begintotal.split(","); // 转换成数组
					int i = 0;
				//循环发货单明细赋值并增加
					for (CeduBookOrderDetail detail : ceduBookOrderDetailList) {
						invoiceDetail.setInvoiceId(invoice.getId());
						invoiceDetail.setBranchId(detail.getBranchId());
						invoiceDetail.setBookId(detail.getBookId());
						invoiceDetail.setBookedTotal(detail.getBookedTotal());						
						invoiceDetail.setPurchaser(detail.getPurchaser());
						invoiceDetail.setOrderTime(new Date());
						invoiceDetail.setDeleteFlag(0);
						invoiceDetail.setCreatorId(this.getUser().getUserId());
						invoiceDetail.setCreatedTime(new Date());
						invoiceDetail.setUpdatedTime(new Date());
						invoiceDetail.setUpdater_id(this.getUser().getUserId());
						if (detail.getSendedTotal()+ Integer.parseInt(total[i]) == detail.getBookedTotal()) {
							invoiceDetail.setSendedTotal(detail.getSendedTotal()+ Integer.parseInt(total[i]));
							invoiceDetail.setStatus(1);
							detail.setSendedTotal(detail.getSendedTotal()+ Integer.parseInt(total[i]));
						//	detail.setStatus(1);
							//ceduBookOrderDetailBiz.UpdateCeduBookByStatus(detail);
						} else {
							invoiceDetail.setSendedTotal(detail.getSendedTotal()+ Integer.parseInt(total[i]));
							invoiceDetail.setStatus(0);
							//detail.setSendedTotal(detail.getSendedTotal()+ Integer.parseInt(total[i]));
							//ceduBookOrderDetailBiz.UpdateCeduBookByStatus(detail);
						}
						invoiceDetailBiz.addInvoiceDetail(invoiceDetail);
						++i;
					}
				}
			}
		} catch (Exception e) {

			e.printStackTrace();
			return INPUT;

		}
		return SUCCESS;
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

	public CeduBookOrder getCeduBookOrder() {
		return ceduBookOrder;
	}

	public void setCeduBookOrder(CeduBookOrder ceduBookOrder) {
		this.ceduBookOrder = ceduBookOrder;
	}

	public List<CeduBookOrderDetail> getCeduBookOrderDetailList() {
		return ceduBookOrderDetailList;
	}

	public void setCeduBookOrderDetailList(
			List<CeduBookOrderDetail> ceduBookOrderDetailList) {
		this.ceduBookOrderDetailList = ceduBookOrderDetailList;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getBegintotal() {
		return begintotal;
	}

	public void setBegintotal(String begintotal) {
		this.begintotal = begintotal;
	}

}
