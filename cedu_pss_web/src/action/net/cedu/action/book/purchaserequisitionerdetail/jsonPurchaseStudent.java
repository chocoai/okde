package net.cedu.action.book.purchaserequisitionerdetail;

import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.LevelBiz;
import net.cedu.biz.book.PurchaseRequisitionerDetailBiz;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.enrollment.AcademyEnrollBatchBiz;
import net.cedu.biz.enrollment.MajorBiz;
import net.cedu.entity.basesetting.Level;
import net.cedu.entity.book.PurchaseRequisitionerDetail;
import net.cedu.entity.crm.Student;
import net.cedu.entity.enrollment.AcademyEnrollBatch;
import net.cedu.entity.enrollment.Major;
/**
 * 查询申购单学生明细中的学生ajax  
 * @author YY
 *
 */
@ParentPackage("json-default")
public class jsonPurchaseStudent  extends BaseAction{
 
	private static final long serialVersionUID = -8490480133537588296L;
	
	@Autowired
	private PurchaseRequisitionerDetailBiz purchaseRequisitionerDetailBiz;  //申购单学生明细
	
	private List<PurchaseRequisitionerDetail> purchaseList=new ArrayList<PurchaseRequisitionerDetail>();//申购单学生明细集合
	
	private int purchaseRequisitionDetailId; //申购单明细Id
	@Autowired
	private StudentBiz studentBiz; //学生业务层
	@Autowired
	private AcademyEnrollBatchBiz academyEnrollBatchBiz; //批次业务层
	@Autowired
	private  LevelBiz levelBiz; //层次业务层  
	@Autowired
	private MajorBiz majorBiz; //专业业务层
 
	
	
	@Action(value="find_purchaserequisitionerdetail_student_ajax", 
			results={
			@Result(name = "success", type = "json", 
					params={
					"contentType",  "text/json"
					} )})
	public String execute()
	{
		try {
			purchaseList=purchaseRequisitionerDetailBiz.findStudentByPurchaseRequisitionerDetail(purchaseRequisitionDetailId);
		for (PurchaseRequisitionerDetail prd : purchaseList) {
			
			//查询批次并赋值
			AcademyEnrollBatch batch= academyEnrollBatchBiz.findAcademyEnrollBatchById(prd.getBatchId());
			if(batch!=null)
			{
			  prd.setBatchname(batch.getEnrollmentName());
			}
			//查询专业并赋值
			Level level=levelBiz.findLevelById(prd.getLevelId());
			if(level!=null)
			{
				prd.setLevelname(level.getName());
			}
			//查询专业并赋值
			Major major=majorBiz.findMajorById(prd.getMajorId());
			if(major!=null)
			{
				prd.setMajorname(major.getName());
			}
			//查询学生并赋值
			Student student=studentBiz.findStudentById(prd.getStudentId());
			if(student!=null)
			{
				prd.setStudentname(student.getName());
				prd.setStudentcode(student.getNumber());				
			}
		}
		} catch (Exception e) {
			 
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public List<PurchaseRequisitionerDetail> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<PurchaseRequisitionerDetail> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public int getPurchaseRequisitionDetailId() {
		return purchaseRequisitionDetailId;
	}

	public void setPurchaseRequisitionDetailId(int purchaseRequisitionDetailId) {
		this.purchaseRequisitionDetailId = purchaseRequisitionDetailId;
	}
	
	
	
}
