package net.cedu.common;

import java.util.HashMap;
import java.util.Map;

public class ConstantsPaymentStatusMap {

	private static Map<Integer, String> paymentStatusMap = null;// 身份证代码表

	public static String getCode(int id) {
		if (paymentStatusMap == null)
			init();
		return paymentStatusMap.get(id) == null ? "未知" : paymentStatusMap.get(id);
	}

	public static void init() {
		if (paymentStatusMap == null) {
			paymentStatusMap = new HashMap<Integer, String>();
			paymentStatusMap.put(Constants.PAYMENT_STATUS_TUI_FEI_SUCCESS, "已成功退费");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_KE_YI_ZHI_JIE_TUI_FEI, "可以直接退费");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TUI_FEI_QUE_REN, "退费审批通过已确认");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_DAI_QUE_REN, "退费审批通过待确认");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_SHI_BAI, "退费审批失败");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_TUI_FEI_SHEN_PI_TONG_GUO, "已退费确认");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TIAN_TUI_FEI_DAN, "已申请退费");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TUI_FEI, "已退费");
			
			paymentStatusMap.put(Constants.PAYMENT_STATUS_ZUO_FEI, "已作废");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_KAI_DAN, "已开单");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_SHOU_FEI_QUE_REN, "已收费确认");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TIAN_HUI_KUAN_DAN, "已填汇款单");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_HUI_KUAN_ZONG_BU, "已汇款总部");
			
			paymentStatusMap.put(Constants.PAYMENT_STATUS_ZONG_BU_QUE_REN, "总部确认");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TIAN_DA_KUAN_DAN, "已填打款单");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_QUE_REN_DAI_HUI_KUAN, "已确认待汇款");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_DA_KUAN_YUAN_XIAO, "已打款院校");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TIAN_FAN_KUAN_DAN, "已填返款单");
			
			paymentStatusMap.put(Constants.PAYMENT_STATUS_FAN_KUAN_QUE_REN, "返款确认");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_TIAN_ZHAO_SHENG_FAN_KUAN, "已填招生返款");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_QU_DAO_YI_SHENG_HE, "渠道已审核");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_SHANG_WU_YI_SHENG_HE, "行政已审核");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_CAI_WU_YI_SHEN_HE, "商务已审核");
			paymentStatusMap.put(Constants.PAYMENT_STATUS_YI_HUI_KUAN_QU_DAO, "已汇款");

		}
	}
}