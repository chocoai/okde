package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsStudentDataSourceMap {

	private static Map<Integer, String> studentDataSourceMap = null;// 身份证代码表

	public static String getCode(int id) {
		if (studentDataSourceMap == null)
			init();
		return studentDataSourceMap.get(id) == null ? "未知" : studentDataSourceMap.get(id);
	}

	public static void init() {
		if (studentDataSourceMap == null) {
			studentDataSourceMap = new HashMap<Integer, String>();
			studentDataSourceMap.put(Constants.STU_SOURCE_LC, "学习中心");
			studentDataSourceMap.put(Constants.STU_SOURCE_NP, "网络报名");
			studentDataSourceMap.put(Constants.STU_SOURCE_CC, "呼叫中心");
			studentDataSourceMap.put(Constants.STU_SOURCE_HS, "历史数据");
			
		}
	}
}