package net.cedu.action.book.cedubookorder;

import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.CeduBookOrderBiz;
import net.cedu.biz.book.CeduBookOrderDetailBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.CeduBookOrder;
import net.cedu.entity.book.CeduBookOrderDetail;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 批量增加订购单，订购单明细
 * @author YY
 *
 */
public class AddCeduBookOrderAction extends BaseAction {
 
	private static final long serialVersionUID = -1559497750068833725L;
	@Autowired
	private CeduBookOrderBiz ceduBookOrderBiz; //订购单业务层
	@Autowired
	private PurchaseRequisitionBiz purchaseRequisitionBiz; //申购单业务层
	@Autowired
	private CeduBookOrderDetailBiz ceduBookOrderDetailBiz; //订购单明细业务层
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //申购单明细业务层	
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	private CeduBookOrder ceduBookOrder=new CeduBookOrder();  //订购单实体
	private CeduBookOrderDetail ceduBookOrderDetail=new CeduBookOrderDetail(); //订购单明细实体
	private String purchaseId; //申购单id
	@Action(results={@Result(name="success",type="redirect",location="../cedubookorder/index_cedu_book_order.action")})
	public String execute()
	{
		   try {
			   //增加订购单 可批量增加
				String[] purch= purchaseId.split(","); // 转换成数组
				//循环赋值
				for (int i = 0; i < purch.length; i++) {
					 PurchaseRequisition pr=new PurchaseRequisition();
					 pr=purchaseRequisitionBiz.findById(Integer.parseInt(purch[i]));
					 ceduBookOrder.setAmount(pr.getAmount());
					 ceduBookOrder.setBranchId(pr.getBranchId());
					 ceduBookOrder.setCode(buildCodeBiz.getCodes(CodeEnum.InvoiceTB
								.getCode(), CodeEnum.Invoice.getCode()));
					 ceduBookOrder.setCreatedTime(new Date());
					 ceduBookOrder.setCreatorId(this.getUser().getUserId());
					 ceduBookOrder.setDeleteFlag(0);
					 ceduBookOrder.setOrderOperator(pr.getCreatorId());
					 ceduBookOrder.setOrderTime(pr.getCreatedTime());
					 ceduBookOrder.setStatus(0);
					 ceduBookOrder.setSupplierId(0);
					 ceduBookOrder.setTypes(0);
					 ceduBookOrder.setUpdatedTime(new Date());
					 ceduBookOrder.setUpdaterId(this.getUser().getUserId());
					 ceduBookOrderBiz.addCeduBookOrder(ceduBookOrder);
					 //更新申购单状态
					 pr.setStatus(Constants.BOOK_STATUS_ORDER);
					 purchaseRequisitionBiz.updatePurchaseRequisition(pr);					
					// 增加订购单明细
					if(ceduBookOrder!=null)
					{
					  List<PurchaseRequisitionDetail> detailList=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition((Integer.parseInt(purch[i])));				  
						  for (PurchaseRequisitionDetail prd : detailList) {
							  ceduBookOrderDetail.setBookId(prd.getBookId());
							  ceduBookOrderDetail.setBranchId(ceduBookOrder.getBranchId());
							  ceduBookOrderDetail.setBookingPrice(prd.getPrice());
							  ceduBookOrderDetail.setBookedTotal(prd.getOrderedAmount());
							  ceduBookOrderDetail.setOrderId(ceduBookOrder.getId());
							  ceduBookOrderDetail.setOrderTime(new Date());
							  ceduBookOrderDetail.setCreatedTime(new Date());
							  ceduBookOrderDetail.setCreatorId(this.getUser().getUserId());
							  ceduBookOrderDetail.setDeleteFlag(0);
							  ceduBookOrderDetail.setPurchaser(this.getUser().getUserId());
							  ceduBookOrderDetail.setSendedTotal(0);
							  ceduBookOrderDetail.setStatus(0);
							  ceduBookOrderDetail.setUpdatedTime(new Date());
							  ceduBookOrderDetail.setUpdaterId(this.getUser().getUserId());
							  ceduBookOrderDetailBiz.addCeduBookOrder(ceduBookOrderDetail);
						}					
					}			
				}
		} catch (Exception e) {		 
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public CeduBookOrder getCeduBookOrder() {
		return ceduBookOrder;
	}

	public void setCeduBookOrder(CeduBookOrder ceduBookOrder) {
		this.ceduBookOrder = ceduBookOrder;
	}

	public String getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(String purchaseId) {
		this.purchaseId = purchaseId;
	}

	public CeduBookOrderDetail getCeduBookOrderDetail() {
		return ceduBookOrderDetail;
	}

	public void setCeduBookOrderDetail(CeduBookOrderDetail ceduBookOrderDetail) {
		this.ceduBookOrderDetail = ceduBookOrderDetail;
	}
}
