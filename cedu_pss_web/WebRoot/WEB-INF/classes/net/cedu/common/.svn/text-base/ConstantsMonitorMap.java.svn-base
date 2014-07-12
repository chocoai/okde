package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsMonitorMap {

	private static Map<Integer, String> jianKongZhuangTai = null;//

	public static String getCode(int id) {
		if (jianKongZhuangTai == null)
			init();
		return jianKongZhuangTai.get(id) == null ? "未知" : jianKongZhuangTai.get(id);
	}

	public static void init() {
		if (jianKongZhuangTai == null) {
			jianKongZhuangTai = new HashMap<Integer, String>();
			jianKongZhuangTai.put(Constants.STU_MONITOR_STATUS_WEI_JIAN_KONG, "未监控");
			jianKongZhuangTai.put(Constants.STU_MONITOR_STATUS_JIAN_KONG_ZAI_SHENG_QIN, "监控再申请");
			jianKongZhuangTai.put(Constants.STU_MONITOR_STATUS_JIAN_KONG_YI_CHENG_GONG, "已监控已成功");
			jianKongZhuangTai.put(Constants.STU_MONITOR_STATUS_JIAN_KONG_WEI_CHENG_GONG, "已监控未成功"); 
		}
	}
}