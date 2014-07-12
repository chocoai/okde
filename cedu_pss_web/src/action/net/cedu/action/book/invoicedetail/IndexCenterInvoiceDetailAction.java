package net.cedu.action.book.invoicedetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.biz.book.InvoiceBiz;
import net.cedu.biz.book.InvoiceDetailBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.BookSupplier;
import net.cedu.entity.book.Invoice;
import net.cedu.entity.book.InvoiceDetail;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 显示中心发货详细信息
 * 
 * @author YY
 * 
 */
public class IndexCenterInvoiceDetailAction extends BaseAction {
 
	private static final long serialVersionUID = 4338169246398671614L;
	@Autowired
	private InvoiceDetailBiz invoiceDetailBiz; // 发货单明细业务层
	@Autowired
	private InvoiceBiz invoiceBiz; // 发后单业务层
	@Autowired
	private BookSupplierBiz bookSupplierBiz; // 书商业务层
	@Autowired
	private BookBiz bookBiz; // 教材业务层
	private List<InvoiceDetail> invoicelist = new ArrayList<InvoiceDetail>();// 发货单明细集合
	private int invoiceid; // 发货单id
	private Invoice invoice = new Invoice(); // 发货单实体

	public String execute() {
		try 
		{
			invoice = invoiceBiz.findIdByInvoice(invoiceid);
			BookSupplier bookSupplier = bookSupplierBiz
					.findBookSupplierById(invoice.getSupplierId());
			invoice.setSuppliername(bookSupplier.getName());
			invoicelist = invoiceDetailBiz
					.findorderIdByInvoiceDetail(invoiceid);
			for (InvoiceDetail invoiceDetail : invoicelist) {
				Book book = bookBiz.findBookById(invoiceDetail.getBookId());
				invoiceDetail.setBookname(book.getName());
			}
			return super.execute();
		} catch (Exception e) {
			return INPUT;
		}
	}

	public List<InvoiceDetail> getInvoicelist() {
		return invoicelist;
	}

	public void setInvoicelist(List<InvoiceDetail> invoicelist) {
		this.invoicelist = invoicelist;
	}

	public int getInvoiceid() {
		return invoiceid;
	}

	public void setInvoiceid(int invoiceid) {
		this.invoiceid = invoiceid;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

}
