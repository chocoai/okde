package net.cedu.action.meterial.meterialapplication;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.meterial.biz.MeterialApplicationBiz;
import net.cedu.entity.meterial.MeterialApplication;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

public class FindByCriteriaMeterialApplication extends BaseAction {

 
	private static final long serialVersionUID = 2159421317026595039L;

	@Autowired
	private MeterialApplicationBiz meterialapplicationbiz;
	
	private MeterialApplication meterialapplication;
	private List<MeterialApplication> list=new ArrayList<MeterialApplication>();
	
 public MeterialApplication getMeterialapplication() {
		return meterialapplication;
	}

	public void setMeterialapplication(MeterialApplication meterialapplication) {
		this.meterialapplication = meterialapplication;
	}

	public List<MeterialApplication> getList() {
		return list;
	}

	public void setList(List<MeterialApplication> list) {
		this.list = list;
	}

@Action(value="find_bycriteriameterialapplication",results={@Result(name="success",location="index_meterialapplication.jsp")})
	public String execute()   {
		try{
		list	 =	meterialapplicationbiz.cribyMeterialApplication(meterialapplication);
		
		}catch (Exception e) {
			e.getStackTrace();
			
			 
		}
		return SUCCESS;
		}
	
}
