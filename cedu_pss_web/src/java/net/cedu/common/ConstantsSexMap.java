package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 性别代码表
 * 
 * @author yangdongdong
 * 
 */
public class ConstantsSexMap {

	private static Map<Integer, String> sexCodeMap = null;// 身份证代码表

	public static String getCode(int id) {
		if (sexCodeMap == null)
			init();
		return sexCodeMap.get(id) == null ? "未知" : sexCodeMap.get(id);
	}

	public static void init() {
		if (sexCodeMap == null) {
			sexCodeMap = new HashMap<Integer, String>();
			sexCodeMap.put(Constants.SEX_FAMALE, "女");
			sexCodeMap.put(Constants.SEX_MALE, "男");

		}
	}
}
