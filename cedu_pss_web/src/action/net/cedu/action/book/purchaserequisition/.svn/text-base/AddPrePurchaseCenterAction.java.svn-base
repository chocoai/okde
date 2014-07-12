package net.cedu.action.book.purchaserequisition;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.BookBiz;
import net.cedu.biz.book.PurchaseRequisitionBiz;
import net.cedu.biz.book.PurchaseRequisitionDetailBiz;
import net.cedu.biz.book.PurchaseRequisitionerDetailBiz;
import net.cedu.biz.code.BuildCodeBiz;
import net.cedu.common.Constants;
import net.cedu.common.enums.CodeEnum;
import net.cedu.entity.book.Book;
import net.cedu.entity.book.PurchaseRequisition;
import net.cedu.entity.book.PurchaseRequisitionDetail;
import net.cedu.entity.book.PurchaseRequisitionerDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 中心预申购单，中心预申购单明细增加，中心申购单人员明细
 * @author YY
 *
 */
public class AddPrePurchaseCenterAction extends BaseAction {

	private static final long serialVersionUID = 2443805231758360630L;

	@Autowired
	private PurchaseRequisitionBiz  purchaseRequisitionBiz; //中心申购单业务层
	@Autowired
	private PurchaseRequisitionDetailBiz purchaseRequisitionDetailBiz; //中心申购单明细业务层
	@Autowired
	private BuildCodeBiz buildCodeBiz; // code生成器
	@Autowired
	private BookBiz bookBiz; //教材业务层
	@Autowired
	private PurchaseRequisitionerDetailBiz purchaseRequisitionerDetailBiz;//中心申购单人员明细业务层
	
	private PurchaseRequisitionerDetail purchaseRequisitionerDetail=new PurchaseRequisitionerDetail();//中心申购单人员明细实体
	private  int academyId;//院校id
	private  int batchId; //批次id
	private  int levelId; //层次id
	private  int majorId; //专业
	private  String stuids;  //学生id字符串
	private PurchaseRequisitionDetail purchaseRequisitionDetail=new PurchaseRequisitionDetail();//申购单明细实体
	 
	private PurchaseRequisition  purchaseRequisition =new PurchaseRequisition(); //申购单实体
	
	private String array;   //申购数量字符串
	private String bookids; //教材id字符串
	private int order; //类型
	private double avg; //总金额
 
	@Action(results={@Result(name="success",type="redirect",location="index_pre_purchase_center")})
	public String execute()   {
		try{
			//code生成器生成一个code
			String code=(buildCodeBiz.getCodes(CodeEnum.PurchaseRequisitionTB
					.getCode(), CodeEnum.PurchaseRequisition.getCode()));
			purchaseRequisition.setCode(code);			
			purchaseRequisition.setDeleteFlag(0);
			purchaseRequisition.setCreatorId(this.getUser().getUserId());
			purchaseRequisition.setUpdaterId(0);
			purchaseRequisition.setUpdatedTime(new Date());
			purchaseRequisition.setCreatedTime(new Date());
			purchaseRequisition.setRequisitioner(this.getUser().getUserId());
			purchaseRequisition.setRequisitiontime(new Date());
			purchaseRequisition.setAcademyId(academyId);
			purchaseRequisition.setBranchId(this.getUser().getOrgId());
			purchaseRequisition.setTypes(order);
			purchaseRequisition.setStatus(Constants.BOOK_STATUS_PRE_CANCEL);
			purchaseRequisitionBiz.addPurchaseRequisition(purchaseRequisition);
			//教材实体
			Book book=new Book();
			String[] ary =array.split(","); //转换成数组
			String[] bok=bookids.split(","); //转换数组
			//增加明细单
			if(purchaseRequisition!=null)
			{			
				//循环增加申购明细单
				for (int i = 0; i < ary.length; i++) {
					purchaseRequisitionDetail.setPurchaseRequisitionId(purchaseRequisition.getId());
					purchaseRequisitionDetail.setBookId(Integer.parseInt(bok[i]));			 
					purchaseRequisitionDetail.setRequiredAmount(Integer.parseInt(ary[i]));
					purchaseRequisitionDetail.setOrderedAmount(0);
					purchaseRequisitionDetail.setDeleteFlag(0);
					purchaseRequisitionDetail.setCreatorId(this.getUser().getUserId());
					purchaseRequisitionDetail.setUpdaterId(0);
					purchaseRequisitionDetail.setUpdatedTime(new Date());
					purchaseRequisitionDetail.setCreatedTime(new Date());
					purchaseRequisitionDetail.setPurchaseTime(new Date());
					book=bookBiz.findBookById(Integer.parseInt(bok[i]));
					purchaseRequisitionDetail.setPrice((int)book.getPrice());
					avg+=(purchaseRequisitionDetail.getRequiredAmount()*book.getPrice());
					purchaseRequisitionDetailBiz.addPurchaseRequisitionDetail(purchaseRequisitionDetail);
				}
			}
			if(purchaseRequisitionDetail!=null)
			{
				if(order==Constants.PRE_PURCHASE_CENTER_STATUS_STUDENTS_PURCHASE)
				{
					//把字符串转换成数组
					String[] studentids =stuids.split(",");
					//循环为预审购单人员明细单赋值并增加
					for (int i = 0; i < studentids.length; i++) {
						purchaseRequisitionerDetail.setPurchaseRequisitionId(purchaseRequisitionDetail.getId());
						purchaseRequisitionerDetail.setAcademyId(academyId);
						purchaseRequisitionerDetail.setBatchId(batchId);
						purchaseRequisitionerDetail.setLevelId(levelId);
						purchaseRequisitionerDetail.setMajorId(majorId);
						purchaseRequisitionerDetail.setStudentId(Integer.parseInt(studentids[i]));
						purchaseRequisitionerDetail.setStatus(Constants.BOOK_PURCHASE_REQUISITIONER_NOT_COLLAR);
						purchaseRequisitionerDetail.setDeleteFlag(0);
						purchaseRequisitionerDetail.setBookId(purchaseRequisitionDetail.getBookId());
						purchaseRequisitionerDetail.setRequiredAmount(Integer.parseInt(ary[i]));
						purchaseRequisitionerDetail.setCreatorId(this.getUser().getUserId());
						purchaseRequisitionerDetail.setCreatedTime(new Date());
						purchaseRequisitionerDetail.setUpdatedTime(new Date());
						purchaseRequisitionerDetail.setUpdaterId(this.getUser().getUserId());
						purchaseRequisitionerDetailBiz.addPurchaseRequisitionerDetail(purchaseRequisitionerDetail);
					}
				}
			}
			//给申购单总金额赋值
			if(0!=avg)
			{
				 purchaseRequisition.setAmount(avg);
				 purchaseRequisitionBiz.updatePurchaseRequisition(purchaseRequisition);
			}
		}catch (Exception e) {
			return INPUT;
		}
		return SUCCESS;
	}
	
	
	public PurchaseRequisitionDetail getPurchaseRequisitionDetail() {
		return purchaseRequisitionDetail;
	}
	public void setPurchaseRequisitionDetail(
			PurchaseRequisitionDetail purchaseRequisitionDetail) {
		this.purchaseRequisitionDetail = purchaseRequisitionDetail;
	}
	public PurchaseRequisition getPurchaseRequisition() {
		return purchaseRequisition;
	}
	public void setPurchaseRequisition(PurchaseRequisition purchaseRequisition) {
		this.purchaseRequisition = purchaseRequisition;
	}
	public String getArray() {
		return array;
	}
	public void setArray(String array) {
		this.array = array;
	}
	public String getBookids() {
		return bookids;
	}
	public void setBookids(String bookids) {
		this.bookids = bookids;
	}
	
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public int getOrder() {
		return order;
	}


	public void setOrder(int order) {
		this.order = order;
	}


	public int getAcademyId() {
		return academyId;
	}


	public void setAcademyId(int academyId) {
		this.academyId = academyId;
	}


	public int getBatchId() {
		return batchId;
	}


	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}


	public int getLevelId() {
		return levelId;
	}


	public void setLevelId(int levelId) {
		this.levelId = levelId;
	}


	public int getMajorId() {
		return majorId;
	}


	public void setMajorId(int majorId) {
		this.majorId = majorId;
	}


	public String getStuids() {
		return stuids;
	}


	public void setStuids(String stuids) {
		this.stuids = stuids;
	}
 
	
}
