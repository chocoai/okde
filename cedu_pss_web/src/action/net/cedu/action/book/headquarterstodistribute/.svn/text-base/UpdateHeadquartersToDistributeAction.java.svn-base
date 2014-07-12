package net.cedu.action.book.headquarterstodistribute;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.HeadquartersToDistributeBiz;
import net.cedu.biz.book.HeadquartersToDistributeDetailBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.HeadquartersToDistribute;
import net.cedu.entity.book.HeadquartersToDistributeDetail;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创建派发单，派发单明细
 * @author YY
 *
 */
public class UpdateHeadquartersToDistributeAction extends BaseAction {
 
	private static final long serialVersionUID = 7440028018093087450L;
	
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //申购单明细业务层
	@Autowired
	private PurchaseRequisitionBiz purchaseRequisitionBiz; //申购单业务层
	@Autowired
	private BookBiz bookBiz;  //教材业务层
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	@Autowired
	private HeadquartersToDistributeBiz headquartersToDistributeBiz;//总部派发业务层
	@Autowired
	private HeadquartersToDistributeDetailBiz headquartersToDistributeDetailBiz;//总部派发业务层
	
	private HeadquartersToDistribute headquartersToDistribute=new HeadquartersToDistribute();//总部派发单实体
	
	private HeadquartersToDistributeDetail headquartersToDistributeDetail=new HeadquartersToDistributeDetail();//总部派发单实体
	
	private int purchaseRequisitionId; //申购单id
	private String branchName; //中心名称
	private String code;//申购单编号
	private String requiredName;//申购人
	private List<PurchaseRequisitionDetail> purchaseList=new ArrayList<PurchaseRequisitionDetail>();//业务层明细集合
	
	private String array;  //派发数量集合
 
	@Action( results={@Result(name="success",type="redirect",location="list_hradquarters_to_distribut")})
	public String execute()
	{
		try {
			//GET从申购单列表提交到本页面
			if(this.isGetRequest())
			{	
				//查询申购单
				purchaseList=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaseRequisitionId);
				//循环赋值
				for (PurchaseRequisitionDetail prd : purchaseList) {
					Book book=bookBiz.findBookById(prd.getBookId());
					prd.setBookname(book.getName());
					prd.setBookedition(book.getEdition());
 
				}
				return INPUT;
			}
	
	//-----------------页面post提交生成派发单及派发单明细部分--------------------
			//查询申购单实体 为更新申购单做准备
			PurchaseRequisition pr=new PurchaseRequisition();
			pr=purchaseRequisitionBiz.findById(purchaseRequisitionId);
			//为派发单赋值
			headquartersToDistribute.setBranchId(purchaseRequisitionBiz.findById(purchaseRequisitionId).getBranchId());
			headquartersToDistribute.setCode((buildCodeBiz.getCodes(CodeEnum.HeadquartersToDistributeTB
					.getCode(), CodeEnum.HeadquartersToDistribute.getCode())));
			headquartersToDistribute.setCreatedTime(new Date());
			headquartersToDistribute.setCreatorId(this.getUser().getUserId());
			headquartersToDistribute.setDeleteFlag(0);
			headquartersToDistribute.setDistributeer(this.getUser().getUserId());
			headquartersToDistribute.setDistributetime(new Date());
			headquartersToDistribute.setPurchaseRequisitionId(purchaseRequisitionId);
			headquartersToDistribute.setUpdatedTime(new Date());
			headquartersToDistribute.setUpdaterId(this.getUser().getUserId());
			//创建派发单
			headquartersToDistributeBiz.addHeadquartersToDistribute(headquartersToDistribute);
			
			String ary[] =array.split(","); //把现派发数量转换成集合
			purchaseList=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaseRequisitionId);//查询申购单
			//按照对应数量循环更新已派发数量
			 double avg=0; //总金额
			for (int i = 0; i < ary.length; i++) {
				PurchaseRequisitionDetail prd=new PurchaseRequisitionDetail();
				prd=purchaseList.get(i);				
				headquartersToDistributeDetail.setAmount(prd.getPrice());
				headquartersToDistributeDetail.setBookId(prd.getBookId());
				headquartersToDistributeDetail.setBranchId(headquartersToDistribute.getBranchId());
				headquartersToDistributeDetail.setDistributeNumber(Integer.parseInt(ary[i]));
				headquartersToDistributeDetail.setDistributeId(headquartersToDistribute.getId());
				headquartersToDistributeDetail.setDeleteFlag(0);
				headquartersToDistributeDetail.setDistributeer(this.getUser().getUserId());
				headquartersToDistributeDetail.setDistributetime(new Date());
				headquartersToDistributeDetail.setCreatedTime(new Date());
				headquartersToDistributeDetail.setCreatorId(this.getUser().getUserId());
				headquartersToDistributeDetail.setUpdatedTime(new Date());
				headquartersToDistributeDetail.setUpdaterId(this.getUser().getUserId());
				//金额总数
				avg+=prd.getPrice();
				prd.setHasDistributed(Integer.parseInt(ary[i]));
				purchaseRequisitionDetailBiz.updatePurchaseRequisitionDetail(prd);
				//判断是否需要更新
				if(prd.getRequiredAmount()<(prd.getOrderedAmount()+Integer.parseInt(ary[i])))
				{
					if(prd.getRequiredAmount()==(prd.getOrderedAmount()+Integer.parseInt(ary[i])))
					{	
						//更新申购单明细状态
						prd.setStatus(Constants.BOOK_STATUS_ORDER_END);
						purchaseRequisitionDetailBiz.updatePurchaseRequisitionDetail(prd);
						pr.setStatus(Constants.BOOK_STATUS_ORDER_END);					
						purchaseRequisitionBiz.updatePurchaseRequisition(pr);						
					}else
					{	
						pr.setStatus(Constants.BOOK_STATUS_IN_DELIVERY);					
						purchaseRequisitionBiz.updatePurchaseRequisition(pr);	
					}
				}
				headquartersToDistributeDetailBiz.addHeadquartersToDistributeDetail(headquartersToDistributeDetail);
				//更新派发单				
			}
			//更新派发单总数量
			headquartersToDistribute.setAmount(avg);
			headquartersToDistributeBiz.updateHeadquartersToDistribute(headquartersToDistribute);
			
		} catch (Exception e) {
			 e.printStackTrace();
		}	
		return SUCCESS;
	}

	public int getPurchaseRequisitionId() {
		return purchaseRequisitionId;
	}

	public void setPurchaseRequisitionId(int purchaseRequisitionId) {
		this.purchaseRequisitionId = purchaseRequisitionId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRequiredName() {
		return requiredName;
	}

	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}

	public List<PurchaseRequisitionDetail> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseRequisitionDetail> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public HeadquartersToDistribute getHeadquartersToDistribute() {
		return headquartersToDistribute;
	}

	public void setHeadquartersToDistribute(
			HeadquartersToDistribute headquartersToDistribute) {
		this.headquartersToDistribute = headquartersToDistribute;
	}

	public HeadquartersToDistributeDetail getHeadquartersToDistributeDetail() {
		return headquartersToDistributeDetail;
	}

	public void setHeadquartersToDistributeDetail(
			HeadquartersToDistributeDetail headquartersToDistributeDetail) {
		this.headquartersToDistributeDetail = headquartersToDistributeDetail;
	}

	public String getArray() {
		return array;
	}

	public void setArray(String array) {
		this.array = array;
	}
 		
}
