package net.cedu.action.book.invoicedetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.BookSupplierBiz;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.biz.book.CeduBookOrderDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.BookSupplier;
import net.cedu.entity.book.CeduBookOrder;
import net.cedu.entity.book.CeduBookOrderDetail;
import net.cedu.entity.book.Invoice;
import net.cedu.entity.book.InvoiceDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 查询订购单
 * @author YY
 *
 */
@ParentPackage("json-default")
public class JsonFindInoviceAction extends BaseAction {
 
	private static final long serialVersionUID = 8603963482758670504L;
 
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
	@Action(value = "find_cedubook_detail", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
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
				if(supplier!=null)
				{
				ceduBookOrder.setSuppilername(supplier.getName());
				ceduBookOrder.setBranchname(branchBiz.findBranchById(
						ceduBookOrder.getBranchId()).getName());
				}
				invoiceCode = (buildCodeBiz.getCodes(CodeEnum.CeduBookOrderTB
						.getCode(), CodeEnum.CeduBookOrder.getCode()));
			}
			if (null != ceduBookOrder) {
				//查询订购单明细集合
				ceduBookOrderDetailList = ceduBookOrderDetailBiz
						.findorderIdByCeduBookOrderDetail(ceduBookOrder.getId());

				for (CeduBookOrderDetail detail : ceduBookOrderDetailList) {
					Book book = bookBiz.findBookById(detail.getBookId());
					if(book!=null)
					{
						detail.setSuppilername(book.getName());
						detail.setOrdercode(ceduBookOrder.getCode());
						detail.setBookname(book.getName());
					}
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



	public String getInvoiceCode() {
		return invoiceCode;
	}



	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
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



	public String getBegintotal() {
		return begintotal;
	}



	public void setBegintotal(String begintotal) {
		this.begintotal = begintotal;
	}
	
	
	
	
}
