/**
 * 文件名：CeduAnnualIncomeAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-26 下午04:07:48
 *
*/
package net.cedu.action.report;

import java.util.ArrayList;
import java.util.List;

import net.cedu.action.BaseAction;
import net.cedu.biz.basesetting.FeeSubjectBiz;
import net.cedu.entity.basesetting.FeeSubject;

import org.springframework.beans.factory.annotation.Autowired;

public class CeduAnnualIncomeAction extends BaseAction {

	@Autowired
	private FeeSubjectBiz feeSubjectBiz;//费用科目biz
	private List<FeeSubject> feesubjectlist=new ArrayList<FeeSubject>();//费用科目集合
	
	@Override
	public String execute() throws Exception {
		//费用科目
		feesubjectlist=this.feeSubjectBiz.findAllFeeSubjectByDeleteFlag();
		return SUCCESS;
	}

	public List<FeeSubject> getFeesubjectlist() {
		return feesubjectlist;
	}

	public void setFeesubjectlist(List<FeeSubject> feesubjectlist) {
		this.feesubjectlist = feesubjectlist;
	}
}
