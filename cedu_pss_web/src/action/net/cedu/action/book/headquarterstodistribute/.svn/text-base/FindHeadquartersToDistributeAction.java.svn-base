package net.cedu.action.book.headquarterstodistribute;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.academy.AcademyBiz;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;

import org.springframework.beans.factory.annotation.Autowired;

public class FindHeadquartersToDistributeAction extends BaseAction {
 
	private static final long serialVersionUID = 8525878191253301665L;
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //申购单明细业务层
	@Autowired
	private PurchaseRequisitionBiz purchaseRequisitionBiz; //申购单业务层
	@Autowired
	private BookBiz bookBiz;  //教材业务层
	@Autowired
	private AcademyBiz academyBiz; //学院业务层
 
	private int purchaseRequisitionId; //申购单id
	private String branchName; //中心名称
	private String code;//申购单编号
	private String academyName; //院校名称
	private String requiredName;//申购人
	private List<PurchaseRequisitionDetail> purchaseList=new ArrayList<PurchaseRequisitionDetail>();//业务层明细集合
	
	
	public String execute()
	{
		try {
			purchaseList=purchaseRequisitionDetailBiz.findorderIdByPurchaseRequisition(purchaseRequisitionId);
			for (PurchaseRequisitionDetail prd : purchaseList) {
				Book book=bookBiz.findBookById(prd.getBookId());
				prd.setBookname(book.getName());
				prd.setBookedition(book.getEdition());
				PurchaseRequisition pr =purchaseRequisitionBiz.findById(purchaseRequisitionId);
				academyName=academyBiz.findAcademyById(pr.getAcademyId()).getName();
				 
			}
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
	public String getAcademyName() {
		return academyName;
	}
	public void setAcademyName(String academyName) {
		this.academyName = academyName;
	}
	public List<PurchaseRequisitionDetail> getPurchaseList() {
		return purchaseList;
	}
	public void setPurchaseList(List<PurchaseRequisitionDetail> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public String getRequiredName() {
		return requiredName;
	}
	public void setRequiredName(String requiredName) {
		this.requiredName = requiredName;
	}
	
	
	
}
