package net.cedu.action.book.cedubookorder;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.book.PurchaseRequisition;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 总部订购单列表
 * @author YY
 *
 */
public class IndexCeduBookOrderAction  extends BaseAction{

	private static final long serialVersionUID = 4014350877529554294L;
 
	@Autowired
	private PurchaseRequisitionBiz purchaseRequisitionBiz; //中心申购
	@Autowired
	private BranchBiz  branchBiz; //学习中心业务层
	@Autowired
	private UserBiz userBiz; //用户业务层
	private List<Branch> branchList=new ArrayList<Branch>(); //中心名称
	private List<PurchaseRequisition> purchaselist= new ArrayList<PurchaseRequisition>();//总部订购集合
	
	private String branchId; //中心id字符串 
	private int status; //状态
	
	
	
	public String execute()
	{
		try {
			
			branchList=branchBiz.findBranchForModel();
			int[] br = null;
			String bran []=null;
			//学习中心数组
			if(branchId!=null&&!branchId.equals("")){
				 bran =branchId.split(",");
				 br=new int[bran.length];				 
					 for (int i = 0; i < bran.length; i++) {
						br[i]=Integer.parseInt(bran[i]);
					}				 
			}
			//查询申购单
			purchaselist=purchaseRequisitionBiz.findByPrePurchaseRequisition(status,br);
			//循环为状态名赋值
			for (PurchaseRequisition Purchase : purchaselist) {
				
			 if(Purchase!=null)
			 {
				if(Purchase.getStatus()==Constants.BOOK_STATUS_CANCEL)
				{
					Purchase.setStatusname("已取消");				
				}
				if(Purchase.getStatus()==Constants.BOOK_STATUS_ORDER)
				{
					Purchase.setStatusname("已订购");				
				}
				if(Purchase.getStatus()==Constants.BOOK_STATUS_DELIVER)
				{
					Purchase.setStatusname("已发货");				
				}
				if(Purchase.getStatus()==Constants.BOOK_STATUS_PURCHASE)
				{
					Purchase.setStatusname("已申购");				
				}
				if(Purchase.getStatus()==Constants.BOOK_STATUS_IN_DELIVERY)
				{
					Purchase.setStatusname("发货中");				
				}
				if(Purchase.getStatus()==Constants.BOOK_STATUS_ORDER_END)
				{
					Purchase.setStatusname("订购完");				
				}
				//根据中心id查询中心
				Branch branch=branchBiz.findBranchById(Purchase.getBranchId());
				if(branch!=null)
				{
				Purchase.setBranchName(branch.getName());
				}
				//查询申购人明细
				if(Purchase.getRequisitioner()!=0)
				{
					String name=userBiz.findUserById(Purchase.getRequisitioner()).getFullName();				 
					Purchase.setRequisitionername(name);
				}
			 }
			}

		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public List<Branch> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<Branch> branchList) {
		this.branchList = branchList;
	}

	public List<PurchaseRequisition> getPurchaselist() {
		return purchaselist;
	}

	public void setPurchaselist(List<PurchaseRequisition> purchaselist) {
		this.purchaselist = purchaselist;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
