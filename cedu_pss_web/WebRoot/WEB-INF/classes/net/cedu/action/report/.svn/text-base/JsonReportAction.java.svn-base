/**
 * 文件名：JsonReportAction.java
 * 包名：net.cedu.action.report
 * 工程：cedu_pss_web
 * 功能： TODO /请自行添加
 *
 * 作者：yangdongdong    
 * 日期：2011-12-23 上午10:06:15
 *
 */
package net.cedu.action.report;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.cedu.action.BaseAction;
import net.cedu.student.report.dao.AcademyEnrollmentReport;
import net.cedu.student.report.dao.BranchDirectorDailyReport;
import net.cedu.student.report.dao.BranchEnrollmentReport;
import net.cedu.student.report.dao.BranchRebateReport;
import net.cedu.student.report.dao.BranchStudentEnrollmentProgressReport;
import net.cedu.student.report.dao.BranchStudentStatusReport;
import net.cedu.student.report.dao.CeduAnnualIncomeReport;
import net.cedu.student.report.dao.DailyReport;
import net.cedu.student.report.dao.DataSourceReport;
import net.cedu.student.report.dao.EnrollmentMonitorReport;
import net.cedu.student.report.dao.EnrollmentSourceReport;
import net.cedu.student.report.dao.EnrollmentWayReport;
import net.cedu.student.report.dao.IndividualDailyReport;
import net.cedu.student.report.dao.ManagerWorkScoreReport;
import net.cedu.student.report.dao.NewEnrollmentReport;
import net.cedu.student.report.dao.TuitionStandardReport;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

@ParentPackage("json-default")
public class JsonReportAction extends BaseAction {

	@Autowired
	private EnrollmentSourceReport enrollmentSourceReport;
	@Autowired
	private DataSourceReport dataSourceReport;
	@Autowired
	private EnrollmentWayReport enrollmentWayReport;
	@Autowired
	private NewEnrollmentReport newEnrollmentReport;
	@Autowired
	private IndividualDailyReport individualDailyReport;
	@Autowired
	private DailyReport dailyReport;
	@Autowired
	private BranchStudentStatusReport branchStudentStatusReport;
	@Autowired
	private ManagerWorkScoreReport managerWorkScoreReport;
	@Autowired
	private BranchDirectorDailyReport branchDirectorDailyReport;
	@Autowired
	private AcademyEnrollmentReport academyEnrollmentReport;
	@Autowired
	private CeduAnnualIncomeReport ceduAnnualIncomeReport;
	@Autowired
	private BranchRebateReport branchRebateReport;
	@Autowired
	private BranchStudentEnrollmentProgressReport branchStudentEnrollmentProgressReport;
	@Autowired
	private BranchEnrollmentReport branchEnrollmentReport;
	@Autowired
	private EnrollmentMonitorReport enrollmentMonitorReport;
	@Autowired
	private TuitionStandardReport tuitionStandardReport;

	private Map<String, Integer> mapParams = new HashMap<String, Integer>();

	private Map<String, String> strParams = new HashMap<String, String>();

	private Map<String, Date> dateParams = new HashMap<String, Date>();

	private List reportList = new ArrayList();
	
	private Map resultMap=new HashMap();

	/**
	 * 
	 * @功能：招生途径来源报表
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-23 上午10:11:15
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_student_enrollment_source", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentEnrollmentSource() throws Exception {
		resultMap = enrollmentSourceReport.statistics(mapParams,dateParams);
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：数据来源
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-26 下午04:01:01
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_student_data_source", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentDataSource() throws Exception {
		resultMap = dataSourceReport.statistics(mapParams);
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：新生招生统计
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-29 下午03:20:42
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_student_new_enrollment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentNewEnrollmentReport() throws Exception {
		resultMap = newEnrollmentReport.statistics(mapParams, dateParams);
		return SUCCESS;
	}
	/**
	 * 服务站统计表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_service_station", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String serviceStationReport() throws Exception {
		resultMap = newEnrollmentReport.statistics(mapParams, dateParams);
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：市场途径
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-26 下午04:04:20
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_student_enrollment_way", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String studentEnrollmentWay() throws Exception {
		reportList = enrollmentWayReport.statistics(mapParams,strParams,dateParams);
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：查询时间段内的日志
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-26 下午04:04:20
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_individual_daily", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String individualDaily() throws Exception {
		reportList = individualDailyReport.statisticsByUser(mapParams,
				dateParams);
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：查询管辖范围内的用户的日报
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2012-1-12 下午03:45:52
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_individual_daily_date", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String individualDailyUser() throws Exception {
		mapParams.put("currentUserId", super.getUser().getUserId());
		reportList = individualDailyReport.statisticsByDate(mapParams,
				strParams, dateParams);
		return SUCCESS;
	}
	
	

	/**
	 * 
	 * @功能：个人日报表
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2011-12-26 下午04:04:20
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_daily", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String daily() throws Exception {
		try {
			reportList = dailyReport.statistics(mapParams, dateParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @功能：学生统计信息
	 * 
	 * @作者： 杨栋栋
	 * @作成时间：2012-2-6 下午05:04:50
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_branch_student_status", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchStudentStatus() throws Exception {
		try {
			reportList = branchStudentStatusReport.statistics(mapParams,dateParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 院校招生统计表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_academy_enrollment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String academyEnrollment() throws Exception {
		try {
			resultMap = academyEnrollmentReport.statistics(mapParams, dateParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * @功能：工作评分
	 * 
	 * @作者： 董溟浩
	 * @作成时间：2012-02-10 下午16:38:37
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_user_work_score", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String userWorkScore() throws Exception {
		reportList = managerWorkScoreReport.statistics(mapParams, dateParams);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @功能：学习中心主任日报表
	 * 
	 * @作者： 董溟浩
	 * @作成时间：2012-03-05 上午12:14:29
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_branch_director_daily", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchDirectorDaily() throws Exception {
		mapParams.put("currentUserId", super.getUser().getUserId());
		reportList = branchDirectorDailyReport.statisticsByDate(mapParams, strParams, dateParams);
		return SUCCESS;
	}
	/**
	 * 年收入情况统计表（院校返款）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_cedu_annual_income", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String ceduAnnualIncome() throws Exception {
		resultMap = ceduAnnualIncomeReport.statistics(mapParams,strParams,dateParams);
		return SUCCESS;
	}
	
	/**
	 * 年收入情况统计表（学习中心返款）
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_branch_rebate", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchRebate() throws Exception {
		resultMap = branchRebateReport.statistics(mapParams,dateParams);
		return SUCCESS;
	}
	
	/**
	 * 
	 * @功能：周例会招生进展表
	 * 
	 * @作者：董溟浩
	 * @作成时间：2012-06-25 上午10:13:15
	 * 
	 * @修改者：
	 * @修改内容：
	 * @修改时间：
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_branch_student_enrollment_progress", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String BranchStudentEnrollmentProgressReport() throws Exception {
		resultMap = branchStudentEnrollmentProgressReport.statistics(mapParams, dateParams);
		return SUCCESS;
	}
	
	/**
	 * 学习中心项目明细表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_branch_enrollment", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String branchEnrollment() throws Exception {
		try {
			resultMap = branchEnrollmentReport.statistics(mapParams, dateParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 招生监控表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_student_enrollment_monitor", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String StudentEnrollmentMonitorReport() throws Exception {
		try {
			resultMap = enrollmentMonitorReport.statistics(mapParams, dateParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 学费标准汇总表
	 * @return
	 * @throws Exception
	 */
	@Action(value = "report_tuition_standard", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String TuitionStandardReport() throws Exception {
		try {
			resultMap = tuitionStandardReport.statistics(mapParams);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public List getReportList() {
		return reportList;
	}

	public void setMapParams(Map<String, Integer> mapParams) {
		this.mapParams = mapParams;
	}

	public Map<String, Integer> getMapParams() {
		return mapParams;
	}

	public Map<String, Date> getDateParams() {
		return dateParams;
	}

	public void setDateParams(Map<String, Date> dateParams) {
		this.dateParams = dateParams;
	}

	public Map<String, String> getStrParams() {
		return strParams;
	}

	public void setStrParams(Map<String, String> strParams) {
		this.strParams = strParams;
	}

	public Map getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map resultMap) {
		this.resultMap = resultMap;
	}

	

}
