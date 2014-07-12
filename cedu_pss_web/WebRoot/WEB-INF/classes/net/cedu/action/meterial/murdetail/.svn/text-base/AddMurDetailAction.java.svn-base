package net.cedu.action.meterial.murdetail;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialUsageRecordBiz;
import net.cedu.biz.meterial.biz.MurDetailBiz;
import net.cedu.entity.meterial.MeterialUsageRecord;
import net.cedu.entity.meterial.MurDetail;
/**
 * 批量增加领用单明细
 * @author YY
 *
 */
public class AddMurDetailAction extends BaseAction {
 
	private static final long serialVersionUID = -3835864708031859249L;
	@Autowired
	private MurDetailBiz murbiz;   //领用单明细业务逻辑层
	@Autowired
	private MeterialUsageRecordBiz mbiz; //领用单业务逻辑层
 
	private MurDetail murdetail = new MurDetail(); //领用单明细实体

	private MeterialUsageRecord meterialusagerecord = new MeterialUsageRecord();//领用单实体

	private String username;
	private  String pric; //单价数组	
	private String  array; //物料数组
	private String quntion; //数量数组
	private String application; //领用单编号
	private double amount;  //领用总金额
	/*
	 * 个人领用增加 (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	@Action(value = "add_murdetail", results = {
			@Result(name = "success", type = "redirect", location = "/meterial/meterialusagerecord/index_meterialusagerecord"),
			@Result(name = "input", location = "add_murdetail.jsp") })
	public String execute() {
		try {
			if(super.isGetRequest())
			{
				return INPUT;
			}
			if(null!=array)
			{	
				//增加领用单
				meterialusagerecord.setCreatorId(this.getUser().getUserId());
				meterialusagerecord.setCode(application);
				meterialusagerecord.setUpdatedTime(new Date());
				meterialusagerecord.setUpdaterId(this.getUser().getUserId());
				meterialusagerecord.setDeleteFlag(0);
				meterialusagerecord.setApplicant(this.getUser().getUserId());
				meterialusagerecord.setUseTime(new Date());
				mbiz.saveMeterialUsageRecord(meterialusagerecord);
				String[] arr = array.split(","); //物料转换成数组
				String[] quan =quntion.split(","); //数量转换成数组
				String[] pp   =pric.split(","); //价格转换价格
				//循环创建采购明细单
				for (int i = 0; i < arr.length; i++) {
			murdetail.setCreatorId(this.getUser().getUserId());
			murdetail.setUpdaterId(this.getUser().getUserId());
			murdetail.setDeleteFlag(0);
			murdetail.setUpdatedTime(new Date());
			murdetail.setApplicationId(meterialusagerecord.getId());
			murdetail.setPrice(Double.parseDouble(pp[i]));
			murdetail.setMeterialId(Integer.parseInt(arr[i]));
			murdetail.setAmount(Integer.parseInt(quan[i]));
			amount+=((murdetail.getPrice() * murdetail
					.getAmount()));
			murbiz.saveMurDetail(murdetail);
				}
				meterialusagerecord.setAmount(amount);
				mbiz.updateMeterialUsageRecord(meterialusagerecord);
  
			}
		} catch (Exception e) {
			e.printStackTrace();
			return INPUT;
		}
		return SUCCESS;
	}


	public MurDetail getMurdetail() {
		return murdetail;
	}

	public void setMurdetail(MurDetail murdetail) {
		this.murdetail = murdetail;
	}

	public MeterialUsageRecord getMeterialusagerecord() {
		return meterialusagerecord;
	}

	public void setMeterialusagerecord(MeterialUsageRecord meterialusagerecord) {
		this.meterialusagerecord = meterialusagerecord;
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


	public String getApplication() {
		return application;
	}


	public void setApplication(String application) {
		this.application = application;
	}

 
	 

}
