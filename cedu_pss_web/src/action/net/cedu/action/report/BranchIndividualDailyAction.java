/**
 * 文件名：BranchIndividualDailyAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2012-1-9 上午11:30:12
 *
 */
package net.cedu.action.report;

import net.cedu.action.BaseAction;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ @Result(name = "success", location = "/WEB-INF/content/report/individual_daily.jsp") })
public class BranchIndividualDailyAction extends BaseAction {

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
}
