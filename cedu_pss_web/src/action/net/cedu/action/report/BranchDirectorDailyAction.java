/**
 * 文件名：BranchDirectorDailyAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：dongminghao
 * 日期：2012-03-06 上午10:10:00
 *
 */
package net.cedu.action.report;

import net.cedu.action.BaseAction;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

@Results({ @Result(name = "success", location = "/WEB-INF/content/report/branch_director_daily.jsp") })
public class BranchDirectorDailyAction extends BaseAction {

	@Override
	public String execute() throws Exception {

		return SUCCESS;
	}
}
