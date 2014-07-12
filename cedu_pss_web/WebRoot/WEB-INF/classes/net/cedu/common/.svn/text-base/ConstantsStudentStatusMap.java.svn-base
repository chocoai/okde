package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsStudentStatusMap {

	private static Map<Integer, String> studentStatusCodeMap = null;// 身份证代码表

	public static String getCode(int id) {
		if (studentStatusCodeMap == null)
			init();
		return studentStatusCodeMap.get(id) == null ? "未知" : studentStatusCodeMap.get(id);
	}

	public static void init() {
		if (studentStatusCodeMap == null) {
			studentStatusCodeMap = new HashMap<Integer, String>();
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YU_BAO_MING, "预报名");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_DAO_RU, "已导入未分配");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_FENG_PEI, "已分配");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_WU_YI_YUAN, "无意愿(学习中心)");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_GENG_JIN_ZHONG, "跟进中");
			
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_DAO_MING, "已报名未缴费");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_JIAO_CE_SHI_FEI, "已报名");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_CE_SHI, "已测试");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_JIAN_KONG_ZAI_SHENG_QIN, "监控再申请");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_JIAN_KONG_YI_CHENG_GONG, "已监控已成功");
			
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_JIAN_KONG_WEI_CHENG_GONG, "已监控未成功");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_FU_HE, "已复核");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_LU_QU, "已录取");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_JIAO_XUE_FEI, "已缴学费");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_QU_DE_XUE_JI, "已取得学籍");
			
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_KE_CHENG_JING_XIU, "课程进修");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_FU_XUE, "已复学");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_XIU_XUE, "已修学");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_BI_YI, "已毕业");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_YI_TUI_XUE, "已退学");
			
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_QU_XIAO_XUE_JI, "取消学籍");
			studentStatusCodeMap.put(Constants.STU_CALL_STATUS_WU_YI_YUAN_CHUSHI, "无意愿(呼叫中心)");
		}
	}
}