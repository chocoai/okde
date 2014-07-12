package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.entity.basesetting.BaseDict;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 缴费单查询(总部)
 * 
 * @author gaolei
 * 
 */
public class PaymentSearchAdmin2Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BaseDictBiz baseDictBiz;
	
	private List<BaseDict> list=new ArrayList<BaseDict>();
	
	@Override
	public String execute() throws Exception {
		list=baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
		return super.execute();
	}

	public List<BaseDict> getList() {
		return list;
	}
}
