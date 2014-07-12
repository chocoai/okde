package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;

/**
 * 缴费单查询(总部)
 * 
 * @author gaolei
 * 
 */
public class PaymentSearchAdminAction extends BaseAction {

	
	@Autowired
	private BaseDictBiz baseDictBiz;//字典_业务层接口
	private List<BaseDict> feeWayList=new ArrayList<BaseDict>();//缴费方式集合
	
	
	@Override
	public String execute() throws Exception {

		feeWayList=baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
		return super.execute();
	}


	public List<BaseDict> getFeeWayList() {
		return feeWayList;
	}


	public void setFeeWayList(List<BaseDict> feeWayList) {
		this.feeWayList = feeWayList;
	}



}
