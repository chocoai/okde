package net.cedu.action.finance.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import net.cedu.action.BaseAction;
import net.cedu.biz.admin.BranchBiz;
import net.cedu.biz.basesetting.BaseDictBiz;
import net.cedu.common.Constants;
import net.cedu.entity.admin.Branch;
import net.cedu.entity.basesetting.BaseDict;

/**
 * 缴费单查询
 * 
 * @author yangdongdong
 * 
 */
public class PaymentSearchAction extends BaseAction {
	@Autowired
	private BranchBiz branchBiz;// 学习中心业务接口
	private Branch branch;// 学习中心集合
	
	@Autowired
	private BaseDictBiz baseDictBiz;//字典_业务层接口
	private List<BaseDict> feeWayList=new ArrayList<BaseDict>();//缴费方式集合
	

	@Override
	public String execute() throws Exception {
		branch = this.branchBiz.findBranchById(super.getUser().getOrgId());
		feeWayList=baseDictBiz.findAllBaseDictsByTypeAndFlag(Constants.BASEDICT_STYLE_FEEWAY);
		return super.execute();
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public List<BaseDict> getFeeWayList() {
		return feeWayList;
	}

	public void setFeeWayList(List<BaseDict> feeWayList) {
		this.feeWayList = feeWayList;
	}

}
