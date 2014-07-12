package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsCallStatusMap {

	private static Map<Integer, String> callStatusMap = null;// 呼叫等级表

	public static String getCode(int id) {
		if (callStatusMap == null)
			init();
		return callStatusMap.get(id) == null ? "未知" : callStatusMap.get(id);
	}

	public static void init() {
		if (callStatusMap == null) {
			callStatusMap = new HashMap<Integer, String>();
			callStatusMap.put(Constants.CALL_GRADE_A, "A级：++++++");
			callStatusMap.put(Constants.CALL_GRADE_B, "B级：+++++");
			callStatusMap.put(Constants.CALL_GRADE_C, "C级：++++");
			callStatusMap.put(Constants.CALL_GRADE_D, "D级：+++");
			callStatusMap.put(Constants.CALL_GRADE_E, "E级：++");
			callStatusMap.put(Constants.CALL_GRADE_F, "F级：+");
		}
	}
}
