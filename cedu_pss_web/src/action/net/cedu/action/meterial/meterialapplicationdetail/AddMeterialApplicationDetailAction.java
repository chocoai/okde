package net.cedu.action.meterial.meterialapplicationdetail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.biz.meterial.biz.MeterialApplicationDetailBiz;
import net.cedu.biz.meterial.biz.MeterialCategoryBiz;
import net.cedu.entity.meterial.MeterialApplication;
import net.cedu.entity.meterial.MeterialApplicationDetail;
import net.cedu.entity.meterial.MeterialCategory;
 /**
  * 批量增加中心申请，中心申请明细
  * @author YY
  *
  */
public class AddMeterialApplicationDetailAction extends BaseAction {

	@Autowired
	private MeterialApplicationDetailBiz madbiz;  //中心申请单明细业务逻辑层
	@Autowired
	private MeterialApplicationBiz mabiz;    //中心申请单业务逻辑层
	@Autowired
	private MeterialCategoryBiz meterialCategoryBiz; //物料分类
	
	private List<MeterialCategory> categoryList=new ArrayList<MeterialCategory>(); //物料分类集合
	private static final long serialVersionUID = -548197174432600789L;

	private MeterialApplicationDetail meterialapplicationdetail = new MeterialApplicationDetail(); //中心申请单明细实体

	private MeterialApplication meterialapplication = new MeterialApplication(); //中心申请单实体

	private String username;//申请人
	
	private  String pric; //单价数组	
	private String  array; //物料数组
	private String quntion; //数量数组
	private String application; //申请单编号
	private double amount; //总数量
	
	/*
	 * 物料中心增加 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Action(value = "add_meterial_application_deta", results = {
			@Result(name = "success", type = "redirect", location = "/meterial/meterialapplication/index_meterialapplication"),
			@Result(name = "input", location = "add_meterial_application_deta.jsp") })
	public String execute() {
		try {
			if(super.isGetRequest())
			{	
				//查询全部物料分类
				categoryList=meterialCategoryBiz.findall();
				return INPUT;
			}
			//增加中心申请
			if(null!=array)
			{	
				meterialapplication.setCode(application);
				meterialapplication.setCreatorId(this.getUser().getUserId());
				meterialapplication.setUpdatedTime(new Date());
				meterialapplication.setUpdaterId(this.getUser().getUserId());
				meterialapplication.setDeleteFlag(0);
				meterialapplication.setStatus(0);
				mabiz.saveMeterialApplication(meterialapplication);
				
				String[] arr = array.split(","); //转换成数组
				String[] quan =quntion.split(","); //转换成数组
				String[] pp   =pric.split(",");
				//循环批量增加中心申请明细
				for (int i = 0; i <pp.length ; i++) {      //循环实现批量增加
					meterialapplicationdetail.setCreatorId(this.getUser().getUserId());
					meterialapplicationdetail.setUpdaterId(this.getUser().getUserId());
					meterialapplicationdetail.setDeleteFlag(0);
					meterialapplicationdetail.setSuppliedCount(0);
					meterialapplicationdetail.setUpdatedTime(new Date());
					meterialapplicationdetail.setPrice(Double.parseDouble(pp[i]));
					meterialapplicationdetail.setMeterialId(Integer.parseInt(arr[i]));
					meterialapplicationdetail.setAppliedCount(Integer.parseInt(quan[i]));
					meterialapplicationdetail.setApplicationId(meterialapplication.getId());
					amount+=(meterialapplicationdetail.getPrice() * meterialapplicationdetail
							.getAppliedCount());
					madbiz.saveMeterialApplicationDetail(meterialapplicationdetail);
				}
					
				meterialapplication.setAmount(amount);
				mabiz.updateMeterialApplication(meterialapplication);
			
					username = this.getUser().getUserName();
				}	
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}

	public MeterialApplication getMeterialapplication() {
		return meterialapplication;
	}

	public void setMeterialapplication(MeterialApplication meterialapplication) {
		this.meterialapplication = meterialapplication;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MeterialApplicationDetail getMeterialapplicationdetail() {
		return meterialapplicationdetail;
	}

	public void setMeterialapplicationdetail(
			MeterialApplicationDetail meterialapplicationdetail) {
		this.meterialapplicationdetail = meterialapplicationdetail;
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

	public String getApplication() {
		return application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public List<MeterialCategory> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<MeterialCategory> categoryList) {
		this.categoryList = categoryList;
	}
	
	
	
	
}
