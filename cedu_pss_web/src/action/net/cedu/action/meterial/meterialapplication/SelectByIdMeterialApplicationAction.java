package net.cedu.action.meterial.meterialapplication;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.entity.meterial.MeterialApplication;

public class SelectByIdMeterialApplicationAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2123596651626666672L;

	@Autowired
	private MeterialApplicationBiz meterialapplicationbiz;
	
	private int id;
	
	
	
	private MeterialApplication meterialapplication;
	
	@Action(value="find_meterialapplicationdeta",results={@Result(name="success",location="find_meterialapplicationdeta.jsp")})
	public String execute() throws Exception {
		
		meterialapplicationbiz.findByIdMeterialApplication(id);
		return super.execute();
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MeterialApplication getMeterialapplication() {
		return meterialapplication;
	}
	public void setMeterialapplication(MeterialApplication meterialapplication) {
		this.meterialapplication = meterialapplication;
	}
	
}
