package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsStartCourseStatusMap {

	private static Map<Integer, String> startCourseStatusMap = null;// 开课状态表

	public static String getCode(int id) {
		if (startCourseStatusMap == null)
			init();
		return startCourseStatusMap.get(id) == null ? "未知" : startCourseStatusMap.get(id);
	}

	public static void init() {
		if (startCourseStatusMap == null) {
			startCourseStatusMap = new HashMap<Integer, String>();
			startCourseStatusMap.put(Constants.STU_IS_START_COURSE_FALSE, "未开课");
			startCourseStatusMap.put(Constants.STU_IS_START_COURSE_TRUE, "已开课");
		}
	}
}
