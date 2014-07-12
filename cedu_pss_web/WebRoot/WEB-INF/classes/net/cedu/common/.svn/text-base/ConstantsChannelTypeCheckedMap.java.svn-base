package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 来源复核代码表
 * 
 * @author xiao
 * 
 */
public class ConstantsChannelTypeCheckedMap {

	private static Map<Integer, String> channelTypeCheckedMap = null;// 来源复核代码表

	public static String getCode(int id) {
		if (channelTypeCheckedMap == null)
			init();
		return channelTypeCheckedMap.get(id) == null ? "未知" : channelTypeCheckedMap.get(id);
	}

	public static void init() {
		if (channelTypeCheckedMap == null) {
			channelTypeCheckedMap = new HashMap<Integer, String>();
			channelTypeCheckedMap.put(Constants.STUDENT_CHANNEL_TYPE_CHECKED_FALSE, "未复核");
			channelTypeCheckedMap.put(Constants.STUDENT_CHANNEL_TYPE_CHECKED_TRUE, "已复核");

		}
	}
}
