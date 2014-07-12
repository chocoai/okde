package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsFeeWayMap {

	private static Map<Integer, String> feeWayMap = null;//

	public static String getCode(int id) {
		if (feeWayMap == null)
			init();
		return feeWayMap.get(id) == null ? "未知" : feeWayMap.get(id);
	}

	public static void init() {
		if (feeWayMap == null) {
			feeWayMap = new HashMap<Integer, String>();
			feeWayMap.put(Constants.PAYMENT_METHOD_DI_SAN_FAN_ZHI_FU, "第三方支付");
			feeWayMap.put(Constants.PAYMENT_METHOD_XIAN_JIN_HUI_ZONG_BU, "现金汇总部");
			feeWayMap.put(Constants.PAYMENT_METHOD_XIAN_JIN_HUI_YUAN_XIAO, "现金汇院校");
			feeWayMap.put(Constants.PAYMENT_METHOD_POS_ZHI_HUI_ZONG_BU, "POS直汇总部");
			feeWayMap.put(Constants.PAYMENT_METHOD_POS_ZHI_HUI_YUAN_XIAO, "POS直汇院校");
			feeWayMap.put(Constants.PAYMENT_METHOD_WANG_YIN_ZONG_BU, "网银总部");
			feeWayMap.put(Constants.PAYMENT_METHOD_WANG_YIN_YUAN_XIAO, "网银院校");
		}
	}
}