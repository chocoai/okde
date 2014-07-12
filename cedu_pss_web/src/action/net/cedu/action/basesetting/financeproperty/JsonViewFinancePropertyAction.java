package net.cedu.action.basesetting.financeproperty;

import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeBatchBiz;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.basesetting.FeeBatch;
import net.cedu.entity.basesetting.FeeSubject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonViewFinancePropertyAction extends BaseAction{

	private static final long serialVersionUID = -5542742649605002696L;

	@Autowired
	private FeeBatchBiz feeBatchBiz;//缴费次数BIZ
	@Autowired
	private FeeSubjectBiz feesubjectbiz;//费用科目BIZ

	private List<FeeBatch> feebatchlst;//缴费次数列表
	private List<FeeSubject> feesubjectlst;//费用科目列表
	
	private FeeSubject feeSubject;
	private int feeSubjectId;
	
	//判断页面加载列表是否正常
	private boolean feebatchrltbool=true;
	private boolean feesubjectrltbool=true;
	private boolean feepropertyrltbool=true;
	private boolean billreceivedwayrltbool=true;
	
	/**
	 * 查询所有缴费次数列表  BY  HXJ
	 */
	@Action(value="listshowfeebatch",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"feebatchlst.*.createdTime,"+
								"feebatchlst.*.updatedTime,"+
								"feebatchlst.*.creatorId,"+
								"feebatchlst.*.updaterId,"+
								"feebatchlst.*.status,"+
								"feebatchlst.*.academyId,"+
								"feebatchlst.*.batchId,"+
								"feebatchlst.*.deleteFlag"
							   }
					   )
			})	
	public String execute()throws Exception{
		try {
			feebatchlst=feeBatchBiz.getBystatus(1);
		} catch (Exception e) {
			e.printStackTrace();
			feebatchrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有费用科目列表(delete_flag=0)   BY  HXJ
	 */
	@Action(value="listshowfeesubjectbydeleteflag",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json",
								"excludeProperties",
								"feesubjectlst.*.createdTime,"+
								"feesubjectlst.*.updatedTime,"+
								"feesubjectlst.*.deleteFlag,"+
								"feesubjectlst.*.creatorId,"+
								"feesubjectlst.*.updaterId"
								}			
				 		)
			})	
	public String showFeeSubjectListByDeleteFlag()throws Exception{
		try {
			feesubjectlst=feesubjectbiz.findAllFeeSubjectByDeleteFlag();
		} catch (Exception e) {
			e.printStackTrace();
			feesubjectrltbool=false;
		}
		
		return "success";
	}
	
	/**
	 * 查询所有费用科目列表(delete_flag=0)   BY  HXJ
	 */
	@Action(value="viewfeesubjectbyid",
			results={
				@Result(type="json", name = "success",
						params={"contentType","text/json"}			
				 		)
			})	
	public String showFeeSubjectById()throws Exception{
		try {
			feeSubject=feesubjectbiz.findFeeSubjectById(feeSubjectId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
//	/**
//	 * 查询所有费用性质列表(delete_flag=0)   BY  HXJ
//	 */
//	@Action(value="listshowfeepropertybydeleteflag",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json",
//								"excludeProperties",
//								"feepropertylst.*.createdTime,"+
//								"feepropertylst.*.updatedTime,"+
//								"feepropertylst.*.deleteFlag,"+
//								"feepropertylst.*.creatorId,"+
//								"feepropertylst.*.updaterId"
//								}			
//				 		)
//			})	
//	public String showFeePropertyListByDeleteFlag()throws Exception{
//		try {
//			feepropertylst=feepropertybiz.findAllFeePropertysByDeleteFlag();
//		} catch (Exception e) {
//			e.printStackTrace();
//			feepropertyrltbool=false;
//		}
//		
//		return "success";
//	}

//	/**
//	 * 查询所有缴费次数列表  BY  HXJ
//	 */
//	@Action(value="listshowbillreceivedwaybydeleteflag",
//			results={
//				@Result(type="json", name = "success",
//						params={"contentType","text/json",
//								"excludeProperties",
//								"billreceivedwaylst.*.createdTime,"+
//								"billreceivedwaylst.*.updatedTime,"+
//								"billreceivedwaylst.*.deleteFlag,"+
//								"billreceivedwaylst.*.creatorId,"+
//								"billreceivedwaylst.*.updaterId"
//							   }
//					   )
//			})	
//	public String showBillReceivedWayList()throws Exception{
//		try {
//			billreceivedwaylst = billreceivedwaybiz.findAllBillReceivedWaysByDeleteFlag();
//		} catch (Exception e) {
//			e.printStackTrace();
//			billreceivedwayrltbool=false;
//		}
//		
//		return "success";
//	}
	//-----------------------------------------get and set methods-----------------------------
	public List<FeeBatch> getFeebatchlst() {
		return feebatchlst;
	}

	public void setFeebatchlst(List<FeeBatch> feebatchlst) {
		this.feebatchlst = feebatchlst;
	}

	public List<FeeSubject> getFeesubjectlst() {
		return feesubjectlst;
	}

	public void setFeesubjectlst(List<FeeSubject> feesubjectlst) {
		this.feesubjectlst = feesubjectlst;
	}

	public boolean isFeebatchrltbool() {
		return feebatchrltbool;
	}

	public void setFeebatchrltbool(boolean feebatchrltbool) {
		this.feebatchrltbool = feebatchrltbool;
	}

	public boolean isFeesubjectrltbool() {
		return feesubjectrltbool;
	}

	public void setFeesubjectrltbool(boolean feesubjectrltbool) {
		this.feesubjectrltbool = feesubjectrltbool;
	}

//	public List<FeeProperty> getFeepropertylst() {
//		return feepropertylst;
//	}
//
//	public void setFeepropertylst(List<FeeProperty> feepropertylst) {
//		this.feepropertylst = feepropertylst;
//	}

	public boolean isFeepropertyrltbool() {
		return feepropertyrltbool;
	}

	public void setFeepropertyrltbool(boolean feepropertyrltbool) {
		this.feepropertyrltbool = feepropertyrltbool;
	}

//	public List<BillReceivedWay> getBillreceivedwaylst() {
//		return billreceivedwaylst;
//	}
//
//	public void setBillreceivedwaylst(List<BillReceivedWay> billreceivedwaylst) {
//		this.billreceivedwaylst = billreceivedwaylst;
//	}

	public boolean isBillreceivedwayrltbool() {
		return billreceivedwayrltbool;
	}

	public void setBillreceivedwayrltbool(boolean billreceivedwayrltbool) {
		this.billreceivedwayrltbool = billreceivedwayrltbool;
	}

	public FeeSubject getFeeSubject() {
		return feeSubject;
	}

	public void setFeeSubject(FeeSubject feeSubject) {
		this.feeSubject = feeSubject;
	}

	public void setFeeSubjectId(int feeSubjectId) {
		this.feeSubjectId = feeSubjectId;
	}
}
