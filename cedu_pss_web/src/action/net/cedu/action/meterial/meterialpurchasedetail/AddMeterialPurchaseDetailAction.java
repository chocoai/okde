package net.cedu.action.meterial.meterialpurchasedetail;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialPurchaseBiz;
import net.cedu.biz.meterial.biz.MeterialPurchaseDetailBiz;
import net.cedu.entity.meterial.MeterialPurchase;
import net.cedu.entity.meterial.MeterialPurchaseDetail;

/**
 * 批量增加采购单明细，采购单
 * @author YY
 *
 */
public class AddMeterialPurchaseDetailAction extends BaseAction {

	private static final long serialVersionUID = -548197174432600789L;
	@Autowired
	private MeterialPurchaseDetailBiz mpdbiz; //采购明细业务逻辑层
	@Autowired
	private MeterialPurchaseBiz mpbiz;//采购单业务逻辑层
 
	private MeterialPurchaseDetail meterialpurchasedetail = new MeterialPurchaseDetail();//采购明细

	private MeterialPurchase meterialpurchase = new MeterialPurchase();//采购 

	private String username;//申请人
	
	private  String pric; //单价数组	
	private String  array; //物料数组
	private String quntion; //数量数组
	private String purchase; //申请单编号
	private double amount; //总数量

	/*
	 * 总部采购增加 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Action(value = "add_meterialpurchasedetail", results = {
			@Result(name = "success", type = "redirect", location = "/meterial/meterialpurchase/index_meterialpurchase")})
	public String execute() {
		try {
			if(super.isGetRequest())
			{
				return INPUT;
			}
			if(null!=array)
			{	
				//先创建采购单
				meterialpurchase.setCreatorId(this.getUser().getUserId());
				meterialpurchase.setCode(purchase);
				meterialpurchase.setUpdatedTime(new Date());
				meterialpurchase.setUpdaterId(this.getUser().getUserId());
				meterialpurchase.setDeleteFlag(0);
				meterialpurchase.setPurchaseTime(new Date());
				meterialpurchase.setOperators(this.getUser().getUserId());
				meterialpurchase.setOperatorsname(this.getUser().getUserName());
				mpbiz.saveMeterialPurchase(meterialpurchase);
				
				String[] arr = array.split(","); //转换成数组
				String[] quan =quntion.split(","); //转换成数组
				String[] pp   =pric.split(","); //转换价格
				//循环创建采购明细单
				for (int i = 0; i < arr.length; i++) {
					meterialpurchasedetail.setCreatorId(this.getUser().getUserId());
					meterialpurchasedetail.setUpdaterId(this.getUser().getUserId());
					meterialpurchasedetail.setDeleteFlag(0);
					meterialpurchasedetail.setUpdatedTime(new Date());
					meterialpurchasedetail.setPurchaseId(meterialpurchase.getId());
					meterialpurchasedetail.setPrice(Double.parseDouble(pp[i]));
					meterialpurchasedetail.setMeterialId(Integer.parseInt(arr[i]));
					meterialpurchasedetail.setTotal(Integer.parseInt(quan[i]));
					amount+= (meterialpurchasedetail.getPrice() * meterialpurchasedetail
							.getTotal());
					mpdbiz.saveMeterialPurchaseDetail(meterialpurchasedetail);
				}
				//更新采购单 给总金额赋值
				meterialpurchase.setAmount(amount);
				mpbiz.updateMeterialPurchase(meterialpurchase);
			
			
		
			
			
			username = this.getUser().getUserName();
			}
		} catch (Exception e) {

			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	
	
	public MeterialPurchaseDetail getMeterialpurchasedetail() {
		return meterialpurchasedetail;
	}

	public void setMeterialpurchasedetail(
			MeterialPurchaseDetail meterialpurchasedetail) {
		this.meterialpurchasedetail = meterialpurchasedetail;
	}

	public MeterialPurchase getMeterialpurchase() {
		return meterialpurchase;
	}

	public void setMeterialpurchase(MeterialPurchase meterialpurchase) {
		this.meterialpurchase = meterialpurchase;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



	public String getPric() {
		return pric;
	}



	public void setPric(String pric) {
		this.pric = pric;
	}



	public String getArray() {
		return array;
	}



	public void setArray(String array) {
		this.array = array;
	}



	public String getQuntion() {
		return quntion;
	}



	public void setQuntion(String quntion) {
		this.quntion = quntion;
	}



	public String getPurchase() {
		return purchase;
	}



	public void setPurchase(String purchase) {
		this.purchase = purchase;
	}



	public double getAmount() {
		return amount;
	}



	public void setAmount(double amount) {
		this.amount = amount;
	}

	
}
