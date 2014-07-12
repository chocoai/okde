package net.cedu.action.admin.setup;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.SQLException;

import net.cedu.action.BaseAction;
import net.cedu.biz.crm.StudentBiz;
import net.cedu.biz.finance.FeePaymentBiz;
import net.cedu.common.Constants;
import net.cedu.common.md5.Encryption;
import net.cedu.common.properties.Config;
import net.cedu.common.properties.PropertiesUtil;
import net.cedu.dao.crm.StudentDao;
import net.cedu.dao.finance.FeePaymentDao;
import net.cedu.dao.finance.StudentAccountAmountManagementDao;
import net.cedu.init.SysBootStrap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;

/**
 * 系统设置
 * 
 * @author yangdongdong
 * 
 */
@ParentPackage("json-default")
public class JsonSystemSetupAction extends BaseAction {
	
	private final Log log = LogFactory.getLog(JsonSystemSetupAction.class);
	
	@Autowired
	private StudentBiz studentBiz;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private FeePaymentDao feePaymentDao;
	@Autowired
	private FeePaymentBiz feePaymentBiz;
	
	@Autowired
	private StudentAccountAmountManagementDao saamDao;//充值明细_数据访问层接口

	private String result = ",";// 主，从，memcache结果

	private String M_URL;
	private String M_USER;
	private String M_PASS;

	private String S_URL;
	private String S_USER;
	private String S_PASS;

	private String MEMCACHED_URL;

	/**
	 * 重启tomcat
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "reload_project", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String reloadProject() throws Exception {
		
		log.info("开始删除服务器缓存...");
		Runtime runtime = Runtime.getRuntime();
		try {
			String[] dirArray=Config.getProperty("runtime.exec.delete.server.cache.dir").split(";");
			Process process=null;
			BufferedReader input=null;
			String line = null;
			for (String dir : dirArray) {
				if(dir==null||dir.equals("")){
					continue;
				}
				process = runtime.exec("rm -d -r -v "+dir);
				input = new BufferedReader(new InputStreamReader(process.getInputStream()));
					while ((line = input.readLine()) != null)
						log.info("删除目录:"+line);
				input.close();
			}
		} catch (IOException e) {
			log.error("删除服务器缓存失败...错误信息:"+e.getMessage());
		}catch (Exception e) {
			log.error("删除服务器缓存失败...错误信息:"+e.getMessage());
		}
		runtime.gc();
		log.info("删除服务器缓存结束...");
		log.info("开始重新启动服务器...");
		runtime.exec(Config.getProperty("runtime.exec.reload.project"));
		runtime.gc();
		// BufferedReader input = new BufferedReader(new
		// InputStreamReader(process.getInputStream()));
		// String line = null, result = "";
		// while ((line = input.readLine()) != null)
		// result += line + "\r\n";
		// input.close();
		return SUCCESS;
	}
	
	/**
	 * 修复学生专业
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_professional_students", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairProfessionalStudents() throws Exception {
		studentBiz.updateRepairProfessionalStudents();
		return SUCCESS;
	}
	
	/**
	 * 修复学生性别
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_students_sex", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairStudentsSex() throws Exception {
		studentDao.repairStudentsSex();
		return SUCCESS;
	}
	
	/**
	 * 修复缴费单全局批次
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_fee_payment_common_batch", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairFeePaymentCommonBatch() throws Exception {
		feePaymentDao.repairFeePaymentCommonBatch();
		return SUCCESS;
	}
	
	/**
	 * 修复缴费单全局批次
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_students_user_id", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairStudentsUserId() throws Exception {
		studentDao.repairStudentsUserId();
		return SUCCESS;
	}
	
	/**
	 * 修复缴费单明细各个账户值（只能是完成缴费还没进行其他缴费流程才能修复）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_fee_payment_detail_all_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairFeePaymentAllAccount() throws Exception {
		this.feePaymentBiz.updateRepairFeePamymentDetailAllAccount();
		return SUCCESS;
	}
	
	/**
	 * 修复缴费单充值金额和总金额（明细的使用充值金额，在缴费单里的充值金额应为负数
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_fee_payment_total_recharge_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairFeePaymentTotalAccount() throws Exception {
		this.feePaymentDao.repairFeePamymentTotalAmountAndRechargeAmount();
		return SUCCESS;
	}
	
	/**
	 * 修复单纯充值情况，把单纯充值情况都作为预缴费单处理
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_student_account_dan_chun_chong_zhi_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairStuAccountDanChunChongZhi() throws Exception {
		this.saamDao.repairStudentAccountDanChunChongZhi();
		return SUCCESS;
	}
	/**
	 * 还原pos直汇院校和网银院校缴费单明细各个账户值、状态还原到前一个状态
	 * （由于需求变动，历史数据处理问题    只允许用一次————2012-04-16已用）
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "revert_fpd_status_account_for_pos_ebank_academy_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String revertFStatusAccountForPosEbank() throws Exception {
		this.feePaymentDao.revertFeePamymentDetailStatusAccountForPosEbank();
		return SUCCESS;
	}
	
	/**
	 * 修复缴费单优惠金额和总金额
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "repair_fee_payment_total_discount_account_ajax", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String repairFpTotalRechargeAccount() throws Exception {
		this.feePaymentDao.repairFeePamymentTotalAmountAndDiscountAmount();
		return SUCCESS;
	}
	

	/**
	 * 检查连接
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "check_connection", results = { @Result(name = "success", type = "json", params = {
			"contentType", "text/json" }) })
	public String findSystemSetup() throws Exception {
		try {
			String path = Constants.PATH + File.separator + "jdbc.properties";
			PropertiesUtil proper = new PropertiesUtil(path, "UTF-8");
			String mUrl = proper.getProperties("datasource.master.url");
			//String sUrl = proper.getProperties("datasource.slave.url");
			if (result.startsWith(",")) {
				boolean mR = checkMysql(
						mUrl.substring(0, mUrl.indexOf("//") + 2) + M_URL
								+ mUrl.substring(mUrl.indexOf("?")), M_USER,
						M_PASS);
				if (mR) {
					proper.setProperties("datasource.master.url",
							mUrl.substring(0, mUrl.indexOf("//") + 2) + M_URL
									+ mUrl.substring(mUrl.indexOf("?")));
					proper.setProperties(
							"datasource.master.username",
							new String(Hex.encode(Encryption
									.encryptionString(M_USER))));
					proper.setProperties(
							"datasource.master.password",
							new String(Hex.encode(Encryption
									.encryptionString(M_PASS))));
				}
				result = mR + "";
			}
//			boolean sR = checkMysql(sUrl.substring(0, sUrl.indexOf("//") + 2)
//					+ S_URL + sUrl.substring(sUrl.indexOf("?")), S_USER, S_PASS);
//			if (sR) {
//				proper.setProperties("datasource.slave.url",
//						sUrl.substring(0, sUrl.indexOf("//") + 2) + S_URL
//								+ sUrl.substring(sUrl.indexOf("?")));
//				proper.setProperties("datasource.slave.username", new String(
//						Hex.encode(Encryption.encryptionString(S_USER))));
//				proper.setProperties("datasource.slave.password", new String(
//						Hex.encode(Encryption.encryptionString(S_PASS))));
//			}
//			result += "," + sR;
			boolean memR = checkMemcached(MEMCACHED_URL);
			// if (memR) {
			proper.setProperties("memcached.address1", MEMCACHED_URL);

			// }
			result += "," + memR;

			proper.saveFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return SUCCESS;
	}

	/**
	 * 检查数据库连接是否正确
	 * 
	 * @param url
	 * @param username
	 * @param pass
	 * @return
	 */
	private static boolean checkMysql(String url, String username, String pass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DriverManager.getConnection(url, username, pass);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		} catch (SQLException e) {
			return false;
		}

	}

	/**
	 * 检查缓存服务器是否开启
	 * 
	 * @param url
	 * @return
	 */
	private static boolean checkMemcached(String url) {
		MemCachedClient mcc = new MemCachedClient();
		String[] servers = new String[1];
		servers[0] = url;
		Integer[] weights = { 3 };
		// 创建一个实例对象SockIOPool
		SockIOPool pool = SockIOPool.getInstance();

		// set the servers and the weights
		// 设置Memcached Server
		pool.setServers(servers);
		pool.setWeights(weights);

		// set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn
		// to 6 hours
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);

		// Tcp的规则就是在发送一个包之前，本地机器会等待远程主机
		// 对上一次发送的包的确认信息到来；这个方法就可以关闭套接字的缓存，
		// 以至这个包准备好了就发；
		pool.setNagle(false);
		// 连接建立后对超时的控制
		pool.setSocketTO(3000);
		// 连接建立时对超时的控制
		pool.setSocketConnectTO(0);

		// initialize the connection pool
		// 初始化一些值并与MemcachedServer段建立连接
		pool.initialize();
		if (mcc == null) {
			return false;
		}

		if (mcc.stats().size() == 0) {
			return false;
		}
		return true;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getM_URL() {
		return M_URL;
	}

	public void setM_URL(String mURL) {
		M_URL = mURL;
	}

	public String getM_USER() {
		return M_USER;
	}

	public void setM_USER(String mUSER) {
		M_USER = mUSER;
	}

	public String getM_PASS() {
		return M_PASS;
	}

	public void setM_PASS(String mPASS) {
		M_PASS = mPASS;
	}

	public String getS_URL() {
		return S_URL;
	}

	public void setS_URL(String sURL) {
		S_URL = sURL;
	}

	public String getS_USER() {
		return S_USER;
	}

	public void setS_USER(String sUSER) {
		S_USER = sUSER;
	}

	public String getS_PASS() {
		return S_PASS;
	}

	public void setS_PASS(String sPASS) {
		S_PASS = sPASS;
	}

	public String getMEMCACHED_URL() {
		return MEMCACHED_URL;
	}

	public void setMEMCACHED_URL(String mEMCACHEDURL) {
		MEMCACHED_URL = mEMCACHEDURL;
	}

}
