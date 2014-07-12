package net.cedu.action.admin.setup;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import net.cedu.action.BaseAction;
import net.cedu.common.Constants;
import net.cedu.common.md5.Encryption;
import net.cedu.common.properties.PropertiesUtil;

import org.bouncycastle.util.encoders.Hex;

/**
 * 系统设置
 * 
 * @author yangdongdong
 * 
 */
public class SystemSetupAction extends BaseAction {

	@Override
	public String execute() throws Exception {
		String path = Constants.PATH + File.separator + "jdbc.properties";
		PropertiesUtil proper = new PropertiesUtil(path, "UTF-8");
		// 主库
		String mUrl = proper.getProperties("datasource.master.url");
		mUrl = mUrl.substring(mUrl.indexOf("//") + 2, mUrl.indexOf("?"));
		request.setAttribute("M_URL", mUrl);
		request.setAttribute("M_USER", Encryption.decryptionString(Hex
				.decode(proper.getProperties("datasource.master.username"))));
		request.setAttribute("M_PASS", Encryption.decryptionString(Hex
				.decode(proper.getProperties("datasource.master.password"))));
		// 从库
//		String cUrl = proper.getProperties("datasource.slave.url");
//		cUrl = cUrl.substring(cUrl.indexOf("//") + 2, cUrl.indexOf("?"));
//		request.setAttribute("S_URL", cUrl);
//		request.setAttribute("S_USER", Encryption.decryptionString(Hex
//				.decode(proper.getProperties("datasource.slave.username"))));
//		request.setAttribute("S_PASS", Encryption.decryptionString(Hex
//				.decode(proper.getProperties("datasource.slave.password"))));
		// Memcached
		request.setAttribute("MEMCACHED_URL",
				proper.getProperties("memcached.address1"));
		return SUCCESS;
	}
}
