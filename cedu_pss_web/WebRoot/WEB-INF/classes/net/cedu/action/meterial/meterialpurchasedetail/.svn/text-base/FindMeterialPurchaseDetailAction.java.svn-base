package net.cedu.action.meterial.meterialpurchasedetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialPurchaseBiz;
import net.cedu.biz.meterial.biz.MeterialPurchaseDetailBiz;
import net.cedu.entity.meterial.MeterialPurchase;
import net.cedu.entity.meterial.MeterialPurchaseDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 根据id查询采购单明细
 * @author YY
 *
 */
public class FindMeterialPurchaseDetailAction extends BaseAction {

 
	private static final long serialVersionUID = 2934481322500147015L;

	@Autowired
	private MeterialPurchaseDetailBiz mpdbiz; //采购单明细业务逻辑层
	
	@Autowired
	private MeterialPurchaseBiz meterialPurchaseBiz; //采购单业务逻辑层
	@Autowired
	private UserBiz userBiz; //用户实体业务逻辑层
	private int meterid; //采购号主键id
	private MeterialPurchase purchase=new MeterialPurchase();; //采购单编号
	private String name; //采购人

	private double avg; //总价格
	

	private List<MeterialPurchaseDetail> list = new ArrayList<MeterialPurchaseDetail>();

	@Action(value = "find_meterialpurchasedetail", results = { @Result(name = "success", location = "find_meterialpurchasedetail.jsp") })
	public String execute() {
		try {
			list = mpdbiz.findById(meterid);
			purchase=meterialPurchaseBiz.findByIdMeterialPurchase(meterid);
			avg=purchase.getAmount();
			name=userBiz.findUserById(meterialPurchaseBiz.findByIdMeterialPurchase(meterid).getCreatorId()).getUserName();
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public int getMeterid() {
		return meterid;
	}

	public void setMeterid(int meterid) {
		this.meterid = meterid;
	}

 
	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}
	public List<MeterialPurchaseDetail> getList() {
		return list;
	}

	public void setList(List<MeterialPurchaseDetail> list) {
		this.list = list;
	}

 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MeterialPurchase getPurchase() {
		return purchase;
	}

	public void setPurchase(MeterialPurchase purchase) {
		this.purchase = purchase;
	}


}
