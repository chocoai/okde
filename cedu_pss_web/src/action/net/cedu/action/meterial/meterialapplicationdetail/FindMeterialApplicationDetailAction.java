package net.cedu.action.meterial.meterialapplicationdetail;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.UserBiz;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.biz.meterial.biz.MeterialApplicationDetailBiz;
import net.cedu.entity.meterial.MeterialApplication;
import net.cedu.entity.meterial.MeterialApplicationDetail;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 跟据id查询中心申请单明细
 * @author YY
 *
 */
public class FindMeterialApplicationDetailAction extends BaseAction {
 
	private static final long serialVersionUID = -6495796235644673340L;

	@Autowired
	private MeterialApplicationDetailBiz madbiz; //中心申请明细业务逻辑
	@Autowired
	private MeterialApplicationBiz meterialApplicationBiz;//中心申请明务逻辑
	@Autowired
	private UserBiz userBiz; //用户实体业务逻辑层
	private MeterialApplication meterialapplication=new MeterialApplication();//中心申请实体
	private int meterid; //主键

	private double avg;//总金额
	private String applicationname; //申请人

	private List<MeterialApplicationDetail> list = new ArrayList<MeterialApplicationDetail>();
	
	@Action(value = "find_meterialapplicationdetail", results = { @Result(name = "success", location = "find_meterialapplicationdetail.jsp") })
	public String execute() {
		try {
			list = madbiz.findById(meterid);
			meterialapplication=meterialApplicationBiz.findbyId(meterid);
			avg=meterialapplication.getAmount();
			applicationname=userBiz.findUserById(meterialapplication.getCreatorId()).getUserName();
		} catch (Exception e) {
			e.printStackTrace();
			 
		}
		return SUCCESS;
	}

 

	public List<MeterialApplicationDetail> getList() {
		return list;
	}

	public void setList(List<MeterialApplicationDetail> list) {
		this.list = list;
	}
	
	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}



	public MeterialApplication getMeterialapplication() {
		return meterialapplication;
	}



	public void setMeterialapplication(MeterialApplication meterialapplication) {
		this.meterialapplication = meterialapplication;
	}



	public int getMeterid() {
		return meterid;
	}



	public void setMeterid(int meterid) {
		this.meterid = meterid;
	}



	public String getApplicationname() {
		return applicationname;
	}



	public void setApplicationname(String applicationname) {
		this.applicationname = applicationname;
	}



	 
	

}
