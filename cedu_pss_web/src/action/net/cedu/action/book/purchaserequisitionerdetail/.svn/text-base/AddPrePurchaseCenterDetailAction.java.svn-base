package net.cedu.action.book.purchaserequisitionerdetail;

import java.util.Date;

import net.cedu.action.BaseAction;
import net.cedu.biz.book.PurchaseRequisitionerDetailBiz;
import net.cedu.common.Constants;
import net.cedu.entity.book.PurchaseRequisitionerDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 预审购单人员明细列表增加
 * @author YY
 *
 */
public class AddPrePurchaseCenterDetailAction extends BaseAction{
 
	private static final long serialVersionUID = -5321289973506204601L;
	@Autowired
	private PurchaseRequisitionerDetailBiz purchaseRequisitionerDetailBiz;//中心申购单人员明细业务层
	
	private PurchaseRequisitionerDetail purchaseRequisitionerDetail=new PurchaseRequisitionerDetail();//中心申购单人员明细实体
	private  int academyId;//院校id
	private  int batchId; //批次id
	private  int levelId; //层次id
	private  int majorId; //专业
	private  String stuids;  //学生id字符串
	@Action(results={@Result(name="success",type="redirect",location="../purchaserequisition/pre_purchase_center_book?stuids=${stuids}&academyId=${academyId}")})

	
	public String execute()
	{
		try {
			//把字符串转换成数组
			String[] studentids =stuids.split(",");
			//循环为预审购单人员明细单赋值并增加
			for (int i = 0; i < studentids.length; i++) {
				purchaseRequisitionerDetail.setAcademyId(academyId);
				purchaseRequisitionerDetail.setBatchId(batchId);
				purchaseRequisitionerDetail.setLevelId(levelId);
				purchaseRequisitionerDetail.setMajorId(majorId);
				purchaseRequisitionerDetail.setStudentId(Integer.parseInt(studentids[i]));
				purchaseRequisitionerDetail.setStatus(Constants.BOOK_PURCHASE_REQUISITIONER_NOT_COLLAR);
				purchaseRequisitionerDetail.setDeleteFlag(0);
				purchaseRequisitionerDetail.setCreatorId(this.getUser().getUserId());
				purchaseRequisitionerDetail.setCreatedTime(new Date());
				purchaseRequisitionerDetail.setUpdatedTime(new Date());
				purchaseRequisitionerDetail.setUpdaterId(this.getUser().getUserId());
				purchaseRequisitionerDetailBiz.addPurchaseRequisitionerDetail(purchaseRequisitionerDetail);
			}
		} catch (Exception e) {	
			e.printStackTrace();
			 return INPUT;
		}		
		return SUCCESS;
		
	}
	public PurchaseRequisitionerDetail getPurchaseRequisitionerDetail() {
		return purchaseRequisitionerDetail;
	}
	public void setPurchaseRequisitionerDetail(
			PurchaseRequisitionerDetail purchaseRequisitionerDetail) {
		this.purchaseRequisitionerDetail = purchaseRequisitionerDetail;
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
